package b.d.a.a.d0;

import android.graphics.Paint;
import android.graphics.Path;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static final int[] i = new int[3];
    public static final float[] j = {0.0f, 0.5f, 1.0f};
    public static final int[] k = new int[4];
    public static final float[] l = {0.0f, 0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f1819b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Paint f1820c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1821d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1822e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1823f;
    public final Path g = new Path();
    public Paint h = new Paint();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f1818a = new Paint();

    public a() {
        a(-16777216);
        this.h.setColor(0);
        Paint paint = new Paint(4);
        this.f1819b = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f1820c = new Paint(this.f1819b);
    }

    public void a(int i2) {
        this.f1821d = a.h.e.a.c(i2, 68);
        this.f1822e = a.h.e.a.c(i2, 20);
        this.f1823f = a.h.e.a.c(i2, 0);
        this.f1818a.setColor(this.f1821d);
    }
}
