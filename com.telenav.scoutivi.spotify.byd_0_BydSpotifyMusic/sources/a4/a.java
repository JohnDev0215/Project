package a4;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.vivid.spotify.common.base.e;
import h3.i;
import h3.l;

/* JADX INFO: compiled from: DisplayMetricsManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static float f25g = 1080.0f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f26a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f27b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f28c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f29d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f30e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f31f;

    public a(Application application) {
        this.f31f = false;
        if (application instanceof e) {
            this.f31f = ((e) application).l();
        }
        i.b("DisplayMetricsManager").g("create manager isEnabled = " + this.f31f);
        if (this.f31f) {
            g(application);
        }
    }

    private int d(Context context) {
        if (this.f30e == -1) {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            Point point = new Point();
            windowManager.getDefaultDisplay().getRealSize(point);
            this.f30e = point.y;
        }
        return this.f30e;
    }

    private int e(Context context) {
        int iF = f(context);
        int iD = d(context);
        return iF <= iD ? iF : iD;
    }

    private int f(Context context) {
        if (this.f29d == -1) {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            Point point = new Point();
            windowManager.getDefaultDisplay().getRealSize(point);
            this.f29d = point.x;
        }
        return this.f29d;
    }

    public float a() {
        return this.f26a;
    }

    public int b() {
        return this.f28c;
    }

    public float c() {
        return this.f27b;
    }

    public void g(Context context) {
        i.b("DisplayMetricsManager").b("init density", new Object[0]);
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        if (displayMetrics.densityDpi >= 240) {
            f25g = 720.0f;
        }
        int iE = e(context);
        this.f26a = iE / f25g;
        i.b("DisplayMetricsManager").b("density = " + iE + " / " + f25g + " = " + this.f26a, new Object[0]);
        this.f27b = this.f26a * (displayMetrics.scaledDensity / displayMetrics.density);
        l lVarB = i.b("DisplayMetricsManager");
        StringBuilder sb = new StringBuilder();
        sb.append("scaledDensity = ");
        sb.append(this.f27b);
        lVarB.b(sb.toString(), new Object[0]);
        this.f28c = (int) (this.f26a * 160.0f);
        i.b("DisplayMetricsManager").b("densityDpi = " + this.f28c, new Object[0]);
    }

    public boolean h() {
        return this.f31f;
    }
}
