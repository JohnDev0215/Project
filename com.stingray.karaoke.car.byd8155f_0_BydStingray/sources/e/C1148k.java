package e;

import B5.H;
import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.os.Bundle;
import android.os.Message;

/* JADX INFO: renamed from: e.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1148k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsBYDAutoLightListener f13668a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public H f13669b;

    public final void a(String str, Object... objArr) {
        H h10 = this.f13669b;
        if (h10 != null) {
            Bundle bundle = new Bundle();
            bundle.putString("what", str);
            for (int i7 = 0; i7 < objArr.length; i7++) {
                if (objArr[i7] instanceof Integer) {
                    bundle.putInt(String.valueOf(i7 + 1), ((Integer) objArr[i7]).intValue());
                }
                if (objArr[i7] instanceof Float) {
                    bundle.putFloat(String.valueOf(i7 + 1), ((Float) objArr[i7]).floatValue());
                }
            }
            Message messageObtain = Message.obtain();
            messageObtain.obj = bundle;
            h10.sendMessage(messageObtain);
        }
    }
}
