package R3;

import android.app.ActivityManager;
import android.content.Context;
import b4.C0995a;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class c implements b {
    public float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f7385m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f7386n;
    public Object o;

    public c(Context context) {
        this.l = 1;
        this.f7385m = context;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        this.f7386n = activityManager;
        this.o = new e7.f(context.getResources().getDisplayMetrics(), 9);
        if (activityManager.isLowRamDevice()) {
            this.l = 0.0f;
        }
    }

    public C0995a a(float f10) {
        List list = (List) this.f7385m;
        C0995a c0995a = (C0995a) list.get(list.size() - 1);
        if (f10 >= c0995a.b()) {
            return c0995a;
        }
        for (int size = list.size() - 2; size >= 1; size--) {
            C0995a c0995a2 = (C0995a) list.get(size);
            if (((C0995a) this.f7386n) != c0995a2 && f10 >= c0995a2.b() && f10 < c0995a2.a()) {
                return c0995a2;
            }
        }
        return (C0995a) list.get(0);
    }

    @Override // R3.b
    public boolean isEmpty() {
        return false;
    }

    @Override // R3.b
    public float p() {
        return ((C0995a) ((List) this.f7385m).get(r1.size() - 1)).a();
    }

    @Override // R3.b
    public boolean q(float f10) {
        C0995a c0995a = (C0995a) this.o;
        C0995a c0995a2 = (C0995a) this.f7386n;
        if (c0995a == c0995a2 && this.l == f10) {
            return true;
        }
        this.o = c0995a2;
        this.l = f10;
        return false;
    }

    @Override // R3.b
    public float r() {
        return ((C0995a) ((List) this.f7385m).get(0)).b();
    }

    @Override // R3.b
    public C0995a t() {
        return (C0995a) this.f7386n;
    }

    @Override // R3.b
    public boolean x(float f10) {
        C0995a c0995a = (C0995a) this.f7386n;
        if (f10 >= c0995a.b() && f10 < c0995a.a()) {
            return !((C0995a) this.f7386n).c();
        }
        this.f7386n = a(f10);
        return true;
    }

    public c(List list) {
        this.o = null;
        this.l = -1.0f;
        this.f7385m = list;
        this.f7386n = a(0.0f);
    }
}
