package p4;

import A4.h;
import F.l;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import d4.C1071d;
import g4.u;
import g4.x;
import h4.InterfaceC1359a;
import l8.C1789g;
import r4.C2290c;
import r4.C2292e;
import r4.C2294g;

/* JADX INFO: renamed from: p4.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2103b implements x, u {
    public final Drawable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f20897m;

    public C2103b(Drawable drawable, int i7) {
        this.f20897m = i7;
        h.c(drawable, "Argument must not be null");
        this.l = drawable;
    }

    private final void c() {
    }

    @Override // g4.u
    public void a() {
        switch (this.f20897m) {
            case 1:
                ((C2290c) this.l).l.f21814a.l.prepareToDraw();
                break;
            default:
                Drawable drawable = this.l;
                if (drawable instanceof BitmapDrawable) {
                    ((BitmapDrawable) drawable).getBitmap().prepareToDraw();
                } else if (drawable instanceof C2290c) {
                    ((C2290c) drawable).l.f21814a.l.prepareToDraw();
                }
                break;
        }
    }

    @Override // g4.x
    public final int b() {
        switch (this.f20897m) {
            case 0:
                Drawable drawable = this.l;
                return Math.max(1, drawable.getIntrinsicHeight() * drawable.getIntrinsicWidth() * 4);
            default:
                C2294g c2294g = ((C2290c) this.l).l.f21814a;
                C1071d c1071d = c2294g.f21829a;
                return (c1071d.f13368j.length * 4) + c1071d.d.limit() + c1071d.f13367i.length + c2294g.f21839n;
        }
    }

    @Override // g4.x
    public final Class d() {
        switch (this.f20897m) {
            case 0:
                return this.l.getClass();
            default:
                return C2290c.class;
        }
    }

    @Override // g4.x
    public final void e() {
        l lVar;
        l lVar2;
        l lVar3;
        switch (this.f20897m) {
            case 0:
                break;
            default:
                C2290c c2290c = (C2290c) this.l;
                c2290c.stop();
                c2290c.o = true;
                C2294g c2294g = c2290c.l.f21814a;
                c2294g.c.clear();
                Bitmap bitmap = c2294g.l;
                if (bitmap != null) {
                    c2294g.f21831e.k(bitmap);
                    c2294g.l = null;
                }
                c2294g.f21832f = false;
                C2292e c2292e = c2294g.f21835i;
                com.bumptech.glide.l lVar4 = c2294g.d;
                if (c2292e != null) {
                    lVar4.a(c2292e);
                    c2294g.f21835i = null;
                }
                C2292e c2292e2 = c2294g.f21837k;
                if (c2292e2 != null) {
                    lVar4.a(c2292e2);
                    c2294g.f21837k = null;
                }
                C2292e c2292e3 = c2294g.f21838m;
                if (c2292e3 != null) {
                    lVar4.a(c2292e3);
                    c2294g.f21838m = null;
                }
                C1071d c1071d = c2294g.f21829a;
                c1071d.l = null;
                byte[] bArr = c1071d.f13367i;
                C1789g c1789g = c1071d.c;
                if (bArr != null && (lVar3 = (l) c1789g.f18861m) != null) {
                    lVar3.i(bArr);
                }
                int[] iArr = c1071d.f13368j;
                if (iArr != null && (lVar2 = (l) c1789g.f18861m) != null) {
                    lVar2.i(iArr);
                }
                Bitmap bitmap2 = c1071d.f13370m;
                if (bitmap2 != null) {
                    ((InterfaceC1359a) c1789g.l).k(bitmap2);
                }
                c1071d.f13370m = null;
                c1071d.d = null;
                c1071d.f13375s = null;
                byte[] bArr2 = c1071d.f13363e;
                if (bArr2 != null && (lVar = (l) c1789g.f18861m) != null) {
                    lVar.i(bArr2);
                }
                c2294g.f21836j = true;
                break;
        }
    }

    @Override // g4.x
    public final Object get() {
        Drawable drawable = this.l;
        Drawable.ConstantState constantState = drawable.getConstantState();
        return constantState == null ? drawable : constantState.newDrawable();
    }
}
