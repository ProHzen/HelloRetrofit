package com.bbk.open.helloretrofit.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bbk.open.helloretrofit.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private EditText cityET;
    private TextView queryTV;
    private TextView weatherTV;
    private static final String WEATHRE_API_URL =
            "http://php.weather.sina.com.cn/xml.php?city=%s&password=DJOYnieT8234jlsK&day=0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityET = (EditText) findViewById(R.id.city);
        queryTV = (TextView) findViewById(R.id.query);
        weatherTV = (TextView) findViewById(R.id.weather);
        //对查询按钮侦听点击事件
        queryTV.setOnClickListener(this);
        weatherTV.setOnTouchListener(this);
        //observableMap();
        observableFlatMap();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.query) {
            weatherTV.setText("");
            String city = cityET.getText().toString();
            if(TextUtils.isEmpty(city)){
                Toast.makeText(this, "城市不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }
            //采用普通写法创建Observable
       //     observableAsNormal(city);
            observableAsLambda(city);
        } else if (view.getId() == R.id.weather) {

        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    /**
     * 获取指定城市的天气情况
     *
     * @param city
     * @return
     * @throws Exception
     */
    private String getWeather(String city) throws Exception {
        BufferedReader reader = null;
        HttpURLConnection connection = null;

        try {
            String urlString = String.format(WEATHRE_API_URL, URLEncoder.encode(city, "GBK"));
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.connect();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while (!TextUtils.isEmpty(line = reader.readLine()))
                buffer.append(line);
            return buffer.toString();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解析xml获取天气情况
     *
     * @param weatherXml
     * @return
     */
    private Weather parseWeather(String weatherXml) {
        //采用Pull方式解析xml
        StringReader reader = new StringReader(weatherXml);
        XmlPullParser xmlPullParser = Xml.newPullParser();
        Weather weather = null;
        try {
            xmlPullParser.setInput(reader);
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        weather = new Weather();
                        break;
                    case XmlPullParser.START_TAG:
                        String nodeName = xmlPullParser.getName();
                        if ("city".equals(nodeName)) {
                            weather.city = xmlPullParser.nextText();
                        } else if ("savedate_weather".equals(nodeName)) {
                            weather.date = xmlPullParser.nextText();
                        } else if ("temperature1".equals(nodeName)) {
                            weather.temperature = xmlPullParser.nextText();
                        } else if ("temperature2".equals(nodeName)) {
                            weather.temperature += "-" + xmlPullParser.nextText();
                        } else if ("direction1".equals(nodeName)) {
                            weather.direction = xmlPullParser.nextText();
                        } else if ("power1".equals(nodeName)) {
                            weather.power = xmlPullParser.nextText();
                        } else if ("status1".equals(nodeName)) {
                            weather.status = xmlPullParser.nextText();
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }
            return weather;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            reader.close();
        }
        return weather;
    }

    private Subscription subscription;
    private final static String TAG = "MainActivity";
    private Subscriber subscriber;

    private void observableAsNormal(final String city) {
        subscription = Observable.create(new Observable.OnSubscribe<Weather>() {
            @Override
            public void call(Subscriber<? super Weather> subscriber) {
                Log.d(TAG, " call  " + Thread.currentThread().getName());
                if (subscriber.isUnsubscribed())
                    return;
                try {
                    String weatherXml = getWeather(city);
                    Weather weather = parseWeather(weatherXml);
                    subscriber.onNext(weather);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }

            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Weather>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Weather weather) {
                        if (weather != null)
                            weatherTV.setText(weather.toString());
                    }
                });
    }

    private void observableAsLambda(final String city) {
        subscription = Observable.create(subscriber->{
            if (subscriber.isUnsubscribed())
                return;
            try {
                String weatherXml = getWeather(city);
                Weather weather = parseWeather(weatherXml);
                subscriber.onNext(weather);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weather->{
                            if (weather != null) {
                                weatherTV.setText(weather.toString());
                            }
                        },
                        e->{
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                );
    }

    /**
     * 打印学生名字的例子
     */
    private void observableMap() {
        List<Course> mCourse = new ArrayList<>();
        mCourse.add(new Course("English"));
        mCourse.add(new Course("Chinese"));
        mCourse.add(new Course("Nath"));
        Student zhangsan = new Student("zhangsan", mCourse);
        Student lisi = new Student("lisi", mCourse);
        Student wangwu = new Student("wangwu", mCourse);

        List<Student> students = new ArrayList<>();
        students.add(zhangsan);
        students.add(lisi);
        students.add(wangwu);

//        Observable.from(students)
//                .map(new Func1<Student, String>() {
//                    @Override
//                    public String call(Student student) {
//                        return student.getName();
//                    }
//                })
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//                    @Override
//                    public void onNext(String s) {
//
//                    }
//                });

        Observable.from(students).map(s->{
            return s.getName();
        }).subscribe(s->{
            Log.e(TAG, "onNext = " + s);
        });
    }

    /**
     * 打印所有课程的名称
     */
    private void observableFlatMap() {
        List<Course> mCourse = new ArrayList<>();
        mCourse.add(new Course("English"));
        mCourse.add(new Course("Chinese"));
        mCourse.add(new Course("Nath"));
        Student zhangsan = new Student("zhangsan", mCourse);
        Student lisi = new Student("lisi", mCourse);
        Student wangwu = new Student("wangwu", mCourse);
        List<Student> students = new ArrayList<>();
        students.add(zhangsan);
        students.add(lisi);
        students.add(wangwu);

//        Observable.from(students)
//                .flatMap(new Func1<Student, Observable<Course>>() {
//                    @Override
//                    public Observable<Course> call(Student student) {
//                        return Observable.from(student.getmList());
//                    }
//                })
//                .subscribe(new Subscriber<Course>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Course course) {
//                        Log.e(TAG, "onNext: " + course.getCoursename());
//                    }
//                });

        Observable.from(students)
                .flatMap(s->{return Observable.from(s.getmList());})
                .subscribe(course ->{ Log.e(TAG, "onNext: " + course.getCoursename());});
    }

    /**
     * buffer转化符
     */
    private void observableBuffer() {
        final String[] mails = new String[]{"Here is an email!", "Another email!", "Yet another email!"};
        Observable<String> endlessMail = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (subscriber.isUnsubscribed()) return;
                    Random random = new Random();
                    while (true) {
                        String mail = mails[random.nextInt(mails.length)];
                        subscriber.onNext(mail);
                        Thread.sleep(1000);
                    }
                } catch (Exception ex) {
                    subscriber.onError(ex);
                }
            }
        });
        endlessMail.buffer(3, TimeUnit.SECONDS).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                System.out.println(String.format("You've got %d new messages!  Here they are!", strings.size()));
                for (int i = 0; i < strings.size(); i++)
                    System.out.println("**" + strings.get(i).toString());
            }
        });
    }

    String BASE_URL = "http://www.izaodao.com/Api/";
    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

















































}
