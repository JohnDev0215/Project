package defpackage;

import android.os.Handler;
import android.view.Choreographer;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FN {
    public static final ThreadLocal j = new CN();
    public boolean a;
    public EN b;
    public long c;
    public boolean d;
    public boolean e;
    public final Choreographer f;
    public final Choreographer.FrameCallback g;
    public long h;
    public final Handler i = new Handler();

    public FN(EN en, float f) {
        this.b = en;
        b(f);
        this.f = Choreographer.getInstance();
        this.g = new DN(this);
        this.h = System.nanoTime();
    }

    public void a() {
        if (this.e) {
            return;
        }
        this.e = true;
        this.a = ((Boolean) j.get()).booleanValue();
        this.f.postFrameCallback(this.g);
    }

    public void b(float f) {
        this.d = f < 30.0f;
        if (f <= 0.0f) {
            f = 60.0f;
        }
        this.c = (long) (1.0E9f / f);
    }
}
