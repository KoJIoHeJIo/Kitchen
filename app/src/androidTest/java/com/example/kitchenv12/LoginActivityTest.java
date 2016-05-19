package com.example.kitchenv12;

/**
 * Created by Екатерина on 20.05.2016.
 */

    import android.app.Instrumentation;
    import android.test.ActivityInstrumentationTestCase2;

    import com.example.LoginAndComeIn.LoginActivity;

public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

        private LoginActivity LoginActivity;
        private Instrumentation mInstrumentation;

        public LoginActivityTest() {
            super(LoginActivity.class);
        }

        @Override
        protected void setUp() throws Exception {
            super.setUp();
            mInstrumentation = getInstrumentation();
            LoginActivity = getActivity();
        }

        public void testRegistration() {
            LoginActivity.runOnUiThread(new Runnable() {
                public void run() {
                    LoginActivity.getLogin().setText("admin");
                    LoginActivity.getPass().setText("admin");
                    assertTrue(LoginActivity.checkRegistration());
                }
            });
            mInstrumentation.waitForIdleSync();

        }


        public void testLogin() {
            if(LoginActivity.getSp().getBoolean("hasVisited", false)) {
                LoginActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        LoginActivity.getLogin().setText("admin");
                        LoginActivity.getPass().setText("admin");

                        assertTrue(LoginActivity.checkLogin());
                    }
                });
                mInstrumentation.waitForIdleSync();
            }
        }

    }

