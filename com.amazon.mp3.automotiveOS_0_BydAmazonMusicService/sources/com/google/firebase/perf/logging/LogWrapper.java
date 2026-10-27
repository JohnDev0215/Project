package com.google.firebase.perf.logging;

import android.util.Log;

/* JADX INFO: loaded from: classes6.dex */
class LogWrapper {
    private static final String LOG_TAG = "FirebasePerformance";
    private static LogWrapper instance;

    public static synchronized LogWrapper getInstance() {
        if (instance == null) {
            instance = new LogWrapper();
        }
        return instance;
    }

    void d(String str) {
        Log.d("FirebasePerformance", str);
    }

    void v(String str) {
        Log.v("FirebasePerformance", str);
    }

    void i(String str) {
        Log.i("FirebasePerformance", str);
    }

    void w(String str) {
        Log.w("FirebasePerformance", str);
    }

    void e(String str) {
        Log.e("FirebasePerformance", str);
    }

    private LogWrapper() {
    }
}
