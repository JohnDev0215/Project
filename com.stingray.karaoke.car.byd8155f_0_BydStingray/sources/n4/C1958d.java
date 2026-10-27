package n4;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import h4.InterfaceC1359a;

/* JADX INFO: renamed from: n4.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1958d implements g4.x, g4.u {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f19936m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f19937n;

    public C1958d(Bitmap bitmap, InterfaceC1359a interfaceC1359a) {
        A4.h.c(bitmap, "Bitmap must not be null");
        this.f19936m = bitmap;
        A4.h.c(interfaceC1359a, "BitmapPool must not be null");
        this.f19937n = interfaceC1359a;
    }

    public static C1958d c(Bitmap bitmap, InterfaceC1359a interfaceC1359a) {
        if (bitmap == null) {
            return null;
        }
        return new C1958d(bitmap, interfaceC1359a);
    }

    @Override // g4.u
    public final void a() {
        switch (this.l) {
            case 0:
                ((Bitmap) this.f19936m).prepareToDraw();
                break;
            default:
                g4.x xVar = (g4.x) this.f19937n;
                if (xVar instanceof g4.u) {
                    ((g4.u) xVar).a();
                }
                break;
        }
    }

    @Override // g4.x
    public final int b() {
        switch (this.l) {
            case 0:
                return A4.q.c((Bitmap) this.f19936m);
            default:
                return ((g4.x) this.f19937n).b();
        }
    }

    @Override // g4.x
    public final Class d() {
        switch (this.l) {
            case 0:
                return Bitmap.class;
            default:
                return BitmapDrawable.class;
        }
    }

    @Override // g4.x
    public final void e() {
        switch (this.l) {
            case 0:
                ((InterfaceC1359a) this.f19937n).k((Bitmap) this.f19936m);
                break;
            default:
                ((g4.x) this.f19937n).e();
                break;
        }
    }

    @Override // g4.x
    public final Object get() {
        switch (this.l) {
            case 0:
                return (Bitmap) this.f19936m;
            default:
                return new BitmapDrawable((Resources) this.f19936m, (Bitmap) ((g4.x) this.f19937n).get());
        }
    }

    public C1958d(Resources resources, g4.x xVar) {
        A4.h.c(resources, "Argument must not be null");
        this.f19936m = resources;
        A4.h.c(xVar, "Argument must not be null");
        this.f19937n = xVar;
    }
}
