package com.ts.lib.cluster.adapter;

import android.content.Context;
import android.os.Bundle;
import com.ts.lib.cluster.ClusterCommManager;

/* JADX INFO: loaded from: classes.dex */
public class TestClass {
    private static final String TAG = "TestClass";
    private static Context sContext;
    private static volatile TestClass sInstance;
    private static ClusterAdapter sTestInstance;

    public static TestClass getInstance(ClusterAdapter testInstance, Context context) {
        if (sInstance == null) {
            synchronized (TestClass.class) {
                if (sInstance == null) {
                    sInstance = new TestClass();
                    sTestInstance = testInstance;
                    sContext = context;
                }
            }
        }
        return sInstance;
    }

    private void testMediaData() {
    }

    private void testBtCallData() {
    }

    private void testNaviDate() {
    }

    private void testRadioData() {
        Bundle bundle = new Bundle();
        bundle.putInt("DataType", 5);
        bundle.putInt("RadioType", 0);
        ClusterCommManager clusterCommManager = ClusterCommManager.getInstance(sContext);
        clusterCommManager.sendCommand(2, bundle);
    }

    private void sendTestBytes() {
        sTestInstance.sendTestBytes();
    }

    void testAll() {
        Thread thread = new Thread(new Runnable() { // from class: com.ts.lib.cluster.adapter.TestClass.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000L);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private TestClass() {
    }
}
