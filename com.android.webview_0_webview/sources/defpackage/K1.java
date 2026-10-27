package defpackage;

import android.app.PendingIntent;
import android.os.Bundle;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class K1 {
    public final Bundle a;
    public final W1[] b;
    public final W1[] c;
    public boolean d;
    public boolean e;
    public final int f;
    public int g;
    public CharSequence h;
    public PendingIntent i;

    public K1(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        Bundle bundle = new Bundle();
        this.e = true;
        this.g = i;
        this.h = M1.c(charSequence);
        this.i = pendingIntent;
        this.a = bundle;
        this.b = null;
        this.c = null;
        this.d = true;
        this.f = 0;
        this.e = true;
    }
}
