package com.example.kitchenv12;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;
    private Instrumentation mInstrumentation;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mInstrumentation = getInstrumentation();
        mainActivity = getActivity();
    }

    public void testRegistration() {
        mainActivity.runOnUiThread(new Runnable() {
        public void run() {
            mainActivity.getLogin().setText("admin");
            mainActivity.getPass().setText("admin");
            assertTrue(mainActivity.checkRegistration());
            }
        });
        mInstrumentation.waitForIdleSync();

    }


    public void testLogin() {
        if(mainActivity.getSp().getBoolean("hasVisited", false)) {
            mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    mainActivity.getLogin().setText("admin");
                    mainActivity.getPass().setText("admin");

                    assertTrue(mainActivity.checkLogin());
                }
            });
            mInstrumentation.waitForIdleSync();
        }
    }

}