package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import defpackage.du0;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class gt2 implements Runnable {
    public final /* synthetic */ int e = 1;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;
    public final /* synthetic */ int h;
    public final /* synthetic */ Object i;
    public final /* synthetic */ Object j;
    public final /* synthetic */ Object k;

    public /* synthetic */ gt2(du0.a aVar, int i, int i2, int i3, Bitmap bitmap, du0.b bVar) {
        this.i = aVar;
        this.f = i;
        this.g = i2;
        this.h = i3;
        this.j = bitmap;
        this.k = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bitmap bitmap;
        int i = this.h;
        int i2 = this.g;
        int i3 = this.f;
        int i4 = this.e;
        boolean z = false;
        Object obj = this.k;
        Object obj2 = this.j;
        Object obj3 = this.i;
        switch (i4) {
            case 0:
                qt2 qt2Var = (qt2) obj3;
                qt2Var.e.p(rt2.a, (String) obj2);
                qt2Var.e.p(rt2.b, (Drawable) obj);
                qt2Var.e.m(rt2.f, 0.0f);
                qt2Var.e.n(rt2.e, i3);
                qt2Var.e.n(rt2.g, i2);
                qt2Var.e.n(rt2.h, i);
                qt2Var.e.n(rt2.c, 4);
                qt2Var.e.l(rt2.j, false);
                qt2Var.j = new zx1(13, qt2Var);
                break;
            default:
                du0.a aVar = (du0.a) obj3;
                du0.b bVar = (du0.b) obj;
                aVar.getClass();
                du0.a.C0056a c0056a = new du0.a.C0056a(i3, i2, i, (Bitmap) obj2);
                aVar.d = c0056a;
                float width = bVar.b.getWidth();
                float f = bVar.c;
                int i5 = (int) (width * f);
                int height = (int) (bVar.b.getHeight() * f);
                du0.a aVar2 = du0.this.b;
                du0.this.a.getClass();
                Bitmap bitmap2 = c0056a.b;
                if (bitmap2 != null && i3 == i5 && i2 == height) {
                    aVar2.c = 2;
                    if (c0056a.a != 0) {
                        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap2, 0, 0, i5, height);
                        c0056a.b = bitmapCreateBitmap;
                        bitmapCreateBitmap.setHasAlpha(true);
                        c0056a.a = 0;
                        bitmap2.recycle();
                    }
                    z = true;
                }
                if (z && (bitmap = c0056a.b) != null) {
                    bVar.d.onResult(bitmap);
                    break;
                }
                break;
        }
    }

    public /* synthetic */ gt2(qt2 qt2Var, String str, Drawable drawable, int i, int i2, int i3) {
        this.i = qt2Var;
        this.j = str;
        this.k = drawable;
        this.f = i;
        this.g = i2;
        this.h = i3;
    }
}
