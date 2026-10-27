package b.c.a.h;

import android.content.Context;
import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static volatile a f1769f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Bitmap f1770a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f1773d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1771b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f1772c = 0.125f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public EnumC0033a f1774e = EnumC0033a.RS_BLUR;

    /* JADX INFO: renamed from: b.c.a.h.a$a, reason: collision with other inner class name */
    public enum EnumC0033a {
        RS_BLUR,
        FAST_BLUR
    }

    public a(Context context) {
        this.f1773d = context.getApplicationContext();
    }
}
