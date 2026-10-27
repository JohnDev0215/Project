package android.hardware.bydauto.reminder;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoReminderListener implements d {
    protected static final String TAG = "AbsBYDAutoReminderListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i, String str) {
    }

    public void onIndChanged(int i) {
    }

    public void onIndChanged(int i, int i2) {
    }
}
