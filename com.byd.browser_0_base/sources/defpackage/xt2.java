package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import java.util.HashMap;
import java.util.Objects;
import org.chromium.chrome.R$color;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: StatusProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xt2 {
    public static final PropertyModel.o a;
    public static final PropertyModel.n b;
    public static final PropertyModel.n c;
    public static final PropertyModel.p d;
    public static final PropertyModel.n e;
    public static final PropertyModel.n f;
    public static final PropertyModel.r<View.OnClickListener> g;
    public static final PropertyModel.p h;
    public static final PropertyModel.p i;
    public static final PropertyModel.o j;
    public static final PropertyModel.p k;
    public static final PropertyModel.r<b> l;
    public static final PropertyModel.p m;
    public static final PropertyModel.p n;
    public static final PropertyModel.n o;
    public static final PropertyModel.p p;
    public static final v62[] q;

    /* JADX INFO: compiled from: StatusProperties.java */
    public static class a extends b {
        public final boolean g;

        public a(Drawable drawable, boolean z) {
            super(drawable);
            this.g = z;
        }

        @Override // xt2.b
        public final Drawable a(Context context, Resources resources) {
            Drawable drawableA = super.a(context, resources);
            if (drawableA == null) {
                return null;
            }
            int iA = ao3.a(context, 24.0f);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iA, iA, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            float width = canvas.getWidth() * 0.5f;
            Paint paint = new Paint();
            paint.setColor(this.g ? context.getColor(R$color.toolbar_background_primary_dark) : ii2.a(context));
            paint.setAntiAlias(true);
            canvas.drawCircle(width, width, width, paint);
            int width2 = canvas.getWidth();
            int iA2 = ao3.a(context, 20.0f);
            int i = (width2 - iA2) / 2;
            int i2 = iA2 + i;
            drawableA.setBounds(i, i, i2, i2);
            drawableA.draw(canvas);
            return new BitmapDrawable(resources, bitmapCreateBitmap);
        }
    }

    static {
        PropertyModel.o oVar = new PropertyModel.o();
        a = oVar;
        PropertyModel.n nVar = new PropertyModel.n();
        b = nVar;
        PropertyModel.n nVar2 = new PropertyModel.n();
        c = nVar2;
        PropertyModel.p pVar = new PropertyModel.p();
        d = pVar;
        PropertyModel.n nVar3 = new PropertyModel.n();
        e = nVar3;
        PropertyModel.n nVar4 = new PropertyModel.n();
        f = nVar4;
        PropertyModel.r<View.OnClickListener> rVar = new PropertyModel.r<>();
        g = rVar;
        PropertyModel.p pVar2 = new PropertyModel.p();
        h = pVar2;
        PropertyModel.p pVar3 = new PropertyModel.p();
        i = pVar3;
        PropertyModel.o oVar2 = new PropertyModel.o();
        j = oVar2;
        PropertyModel.p pVar4 = new PropertyModel.p();
        k = pVar4;
        PropertyModel.r<b> rVar2 = new PropertyModel.r<>();
        l = rVar2;
        PropertyModel.p pVar5 = new PropertyModel.p();
        m = pVar5;
        PropertyModel.p pVar6 = new PropertyModel.p();
        n = pVar6;
        PropertyModel.n nVar5 = new PropertyModel.n();
        o = nVar5;
        PropertyModel.p pVar7 = new PropertyModel.p();
        p = pVar7;
        q = new v62[]{oVar, nVar, nVar2, pVar, nVar3, nVar4, rVar, pVar2, pVar3, oVar2, pVar4, rVar2, pVar5, pVar6, nVar5, pVar7};
    }

    /* JADX INFO: compiled from: StatusProperties.java */
    public static class b {
        public final Integer a;
        public final int b;
        public final Bitmap c;
        public final Drawable d;
        public int e;
        public Runnable f;

        public b(Drawable drawable) {
            this.e = 0;
            this.d = drawable;
        }

        public Drawable a(Context context, Resources resources) {
            int i = this.b;
            Bitmap bitmap = this.c;
            if (bitmap != null) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmap);
                if (i != 0) {
                    bitmapDrawable.setTintList(bz.b(context, i));
                }
                return bitmapDrawable;
            }
            Integer num = this.a;
            if (num == null) {
                Drawable drawable = this.d;
                if (drawable != null) {
                    return drawable;
                }
                return null;
            }
            if (i == 0) {
                return AppCompatResources.a(context, num.intValue());
            }
            int iIntValue = num.intValue();
            HashMap map = uh3.a;
            return uh3.f(context, iIntValue, bz.b(context, i));
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.b == bVar.b && Objects.equals(this.a, bVar.a) && this.c == bVar.c && this.d == bVar.d;
        }

        public b(Bitmap bitmap) {
            this.e = 0;
            this.c = bitmap;
            this.b = 0;
        }

        public b(int i, int i2) {
            this.e = 0;
            this.a = Integer.valueOf(i);
            this.b = i2;
        }
    }
}
