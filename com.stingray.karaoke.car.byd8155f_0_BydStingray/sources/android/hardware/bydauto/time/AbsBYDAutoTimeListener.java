package android.hardware.bydauto.time;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTimeListener implements d {
    protected static final String TAG = "AbsBYDAutoTimeListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i7, String str) {
    }

    public void onTimeChanged(int[] iArr) {
    }

    public void onTimeFormatChanged(int i7) {
    }
}
