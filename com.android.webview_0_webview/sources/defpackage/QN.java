package defpackage;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class QN extends WindowAndroid {
    public int H;
    public SparseArray I;

    public QN(Context context) {
        super(context);
        this.I = new SparseArray();
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public int X(Intent intent, InterfaceC0492aO interfaceC0492aO, Integer num) {
        int i = this.H;
        int i2 = i + 1000;
        this.H = (i + 1) % 100;
        if (!a0(intent, i2)) {
            return -1;
        }
        this.I.put(i2, interfaceC0492aO);
        this.v.put(Integer.valueOf(i2), num == null ? null : Is.a.getString(num.intValue()));
        return i2;
    }

    public abstract boolean a0(Intent intent, int i);
}
