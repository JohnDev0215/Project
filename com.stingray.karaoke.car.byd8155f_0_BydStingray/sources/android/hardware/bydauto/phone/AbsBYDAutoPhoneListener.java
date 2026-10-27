package android.hardware.bydauto.phone;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPhoneListener implements d {
    protected static final String TAG = "AbsBYDAutoPhoneListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i7, String str) {
    }

    public void onPhoneEventChanged(int i7) {
    }

    public void onPhoneMuteChanged(int i7) {
    }
}
