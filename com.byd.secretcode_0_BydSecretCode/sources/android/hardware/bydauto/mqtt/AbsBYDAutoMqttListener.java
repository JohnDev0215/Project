package android.hardware.bydauto.mqtt;

import android.hardware.bydauto.BYDAutoEventValue;
import defpackage.d;
import defpackage.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoMqttListener implements e {
    public static final String TAG = "AbsBYDAutoMqttListener";

    public AbsBYDAutoMqttListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(d dVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public abstract /* synthetic */ void onError(int i, String str);
}
