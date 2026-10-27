package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class J2 implements Callable {
    public final /* synthetic */ Context a;
    public final /* synthetic */ I2 b;
    public final /* synthetic */ int c;
    public final /* synthetic */ String d;

    public J2(Context context, I2 i2, int i, String str) {
        this.a = context;
        this.b = i2;
        this.c = i;
        this.d = str;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        P2 p2B = Q2.b(this.a, this.b, this.c);
        Typeface typeface = p2B.a;
        if (typeface != null) {
            Q2.a.b(this.d, typeface);
        }
        return p2B;
    }
}
