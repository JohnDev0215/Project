package android.hardware.bydauto.funcnotice;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoFuncNoticeListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoFuncNoticeListener";

    public AbsBYDAutoFuncNoticeListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onFuncNoticeChanged(int i) {
        throw new RuntimeException("Stub!");
    }
}
