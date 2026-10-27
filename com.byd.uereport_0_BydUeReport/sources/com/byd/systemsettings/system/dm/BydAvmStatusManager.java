package com.byd.systemsettings.system.dm;

import android.content.Context;
import com.ts.avm.sdk.AvmManager;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class BydAvmStatusManager {
    private static final int ENTER_SCREEN_OFF = 6;
    private static final int EXIT_SCREEN_OFF = 7;
    private static final String TAG = "BydAvmStatusManager";
    private AvmManager.b avmDockerStatusCallback;
    private AvmManager avmManager;

    public static class Singleton {
        private static BydAvmStatusManager instance = new BydAvmStatusManager();

        public static BydAvmStatusManager get() {
            return instance;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doOnAvmDockerStatusChanged(int i8) {
        a.a(TAG, "---doOnAvmDockerStatusChanged---status: " + i8);
    }

    public void init(Context context) {
        if (context == null) {
            return;
        }
        a.a(TAG, "---init---");
        AvmManager avmManagerC = AvmManager.c();
        this.avmManager = avmManagerC;
        avmManagerC.e(context.getApplicationContext(), new x6.a() { // from class: com.byd.systemsettings.system.dm.BydAvmStatusManager.2
            @Override // x6.a
            public void onError(int i8, String str) {
                a.a(BydAvmStatusManager.TAG, "---init---onError--i: " + i8 + ", s: " + str);
            }

            @Override // x6.a
            public void onServiceConnected() {
                a.a(BydAvmStatusManager.TAG, "---init---onServiceConnected--");
                BydAvmStatusManager.this.avmManager.f(BydAvmStatusManager.this.avmDockerStatusCallback);
            }

            @Override // x6.a
            public void onServiceDisConnected() {
                a.a(BydAvmStatusManager.TAG, "---init---onServiceDisConnected--");
                BydAvmStatusManager.this.avmManager.l(BydAvmStatusManager.this.avmDockerStatusCallback);
            }
        });
    }

    public void release() {
        a.a(TAG, "---release---");
        AvmManager avmManager = this.avmManager;
        if (avmManager != null) {
            avmManager.l(this.avmDockerStatusCallback);
        }
    }

    private BydAvmStatusManager() {
        this.avmDockerStatusCallback = new AvmManager.b() { // from class: com.byd.systemsettings.system.dm.BydAvmStatusManager.1
            @Override // com.ts.avm.sdk.AvmManager.b
            public void onAvmDockerStatusChanged(int i8) {
                BydAvmStatusManager.this.doOnAvmDockerStatusChanged(i8);
            }
        };
    }
}
