package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Size;
import defpackage.e23;
import defpackage.g72;
import defpackage.ii2;
import defpackage.n3;
import defpackage.rh3;
import defpackage.t02;
import defpackage.y13;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.h;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: MultiThumbnailCardProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h implements j0.p {
    public final TabContentManager e;
    public final y13 f;
    public final a g;
    public final float h;
    public final float i;
    public final Paint j;
    public final Paint k;
    public final Paint l;
    public final Paint m;
    public final Paint n;
    public final Paint o;
    public final Paint p;
    public final h0 q;
    public final Context r;

    /* JADX INFO: compiled from: MultiThumbnailCardProvider.java */
    public class a implements e23 {
        public final /* synthetic */ Context e;

        public a(Context context) {
            this.e = context;
        }

        @Override // defpackage.e23
        public final void k(TabModel tabModel) {
            boolean zIsIncognito = tabModel.isIncognito();
            h hVar = h.this;
            Paint paint = hVar.j;
            Context context = this.e;
            paint.setColor(n3.f0(context, zIsIncognito, false));
            hVar.m.setColor(n3.t0(context, zIsIncognito, false));
            hVar.k.setColor(zIsIncognito ? context.getColor(R$color.tab_grid_card_divider_tint_color_incognito) : ii2.e(context));
            hVar.n.setColor(context.getColor(zIsIncognito ? R$color.favicon_background_color_incognito : R$color.favicon_background_color));
            hVar.o.setColor(n3.f0(context, zIsIncognito, true));
            hVar.p.setColor(n3.t0(context, zIsIncognito, true));
        }
    }

    /* JADX INFO: compiled from: MultiThumbnailCardProvider.java */
    public class b {
        public final g72 a;
        public final Callback<Bitmap> b;
        public final boolean c;
        public final boolean d;
        public final boolean e;
        public Canvas h;
        public Bitmap i;
        public String j;
        public final int n;
        public final int o;
        public final ArrayList f = new ArrayList(4);
        public final AtomicInteger g = new AtomicInteger();
        public final ArrayList k = new ArrayList(4);
        public final ArrayList l = new ArrayList(4);
        public final ArrayList m = new ArrayList(4);

        public b(g72 g72Var, Size size, t02 t02Var, boolean z, boolean z2, boolean z3) {
            this.b = t02Var;
            this.a = g72Var;
            this.c = z;
            this.d = z2;
            this.e = z3;
            if (size != null && size.getHeight() > 0 && size.getWidth() > 0) {
                this.n = size.getWidth();
                this.o = size.getHeight();
            } else {
                float fC = TabUtils.c(h.this.r);
                int dimension = (int) h.this.r.getResources().getDimension(R$dimen.tab_grid_thumbnail_card_default_size);
                this.n = dimension;
                this.o = (int) (dimension / fC);
            }
        }

        public final void a(int i, Drawable drawable) {
            Canvas canvas = this.h;
            RectF rectF = (RectF) this.m.get(i);
            h hVar = h.this;
            float f = hVar.i;
            canvas.drawRoundRect(rectF, f, f, hVar.n);
            drawable.setBounds((Rect) this.k.get(i));
            drawable.draw(this.h);
            if (this.g.decrementAndGet() == 0) {
                PostTask.c(rh3.c, this.b.p1(this.i));
            }
        }

        public final void b(int i, Bitmap bitmap) {
            ArrayList arrayList = this.l;
            h hVar = h.this;
            if (bitmap == null) {
                Paint paint = this.e ? hVar.o : hVar.j;
                Canvas canvas = this.h;
                RectF rectF = (RectF) arrayList.get(i);
                float f = hVar.h;
                canvas.drawRoundRect(rectF, f, f, paint);
                return;
            }
            hVar.l.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            Canvas canvas2 = this.h;
            RectF rectF2 = (RectF) arrayList.get(i);
            float f2 = hVar.h;
            Paint paint2 = hVar.l;
            canvas2.drawRoundRect(rectF2, f2, f2, paint2);
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) ((RectF) arrayList.get(i)).width(), (int) ((RectF) arrayList.get(i)).height(), true);
            paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            this.h.drawBitmap(bitmapCreateScaledBitmap, new Rect(0, 0, bitmapCreateScaledBitmap.getWidth(), bitmapCreateScaledBitmap.getHeight()), (RectF) arrayList.get(i), paint2);
            bitmapCreateScaledBitmap.recycle();
        }
    }

    public h(Context context, TabContentManager tabContentManager, y13 y13Var) {
        this.r = context;
        Resources resources = context.getResources();
        this.e = tabContentManager;
        this.f = y13Var;
        this.h = resources.getDimension(R$dimen.tab_list_mini_card_radius);
        this.i = resources.getDimension(R$dimen.tab_grid_thumbnail_favicon_frame_corner_radius);
        this.q = new h0(context, false);
        Paint paint = new Paint();
        this.j = paint;
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(n3.f0(context, false, false));
        Paint paint2 = new Paint(paint);
        this.o = paint2;
        paint2.setColor(n3.f0(context, false, true));
        Paint paint3 = new Paint(paint);
        this.l = paint3;
        paint3.setColor(-16777216);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Paint paint4 = new Paint();
        this.k = paint4;
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(resources.getDimension(R$dimen.tab_list_mini_card_frame_size));
        paint4.setColor(ii2.e(context));
        paint4.setAntiAlias(true);
        Paint paint5 = new Paint();
        this.m = paint5;
        paint5.setTextSize(resources.getDimension(R$dimen.compositor_tab_title_text_size));
        paint5.setFakeBoldText(true);
        paint5.setAntiAlias(true);
        paint5.setTextAlign(Paint.Align.CENTER);
        paint5.setColor(n3.t0(context, false, false));
        Paint paint6 = new Paint(paint5);
        this.p = paint6;
        paint6.setColor(n3.t0(context, false, true));
        int color = context.getColor(R$color.favicon_background_color);
        Paint paint7 = new Paint();
        this.n = paint7;
        paint7.setAntiAlias(true);
        paint7.setColor(color);
        paint7.setStyle(Paint.Style.FILL);
        paint7.setShadowLayer(resources.getDimension(R$dimen.tab_grid_thumbnail_favicon_background_radius), 0.0f, resources.getDimension(R$dimen.tab_grid_thumbnail_favicon_background_down_shift), resources.getColor(R$color.modern_grey_800_alpha_38));
        a aVar = new a(context);
        this.g = aVar;
        y13Var.m(aVar);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.j0.p
    public final void l(int i, Size size, t02 t02Var, boolean z, boolean z2, boolean z3) {
        boolean z4;
        y13 y13Var = this.f;
        Tab tabS = y13Var.s(i);
        g72 g72VarA = tabS != null ? g72.a(tabS) : g72.b(i);
        Context context = this.r;
        if (g72.e(context, g72VarA, y13Var).size() == 1) {
            this.e.e(i, size, t02Var, z, z2);
            return;
        }
        final b bVar = new b(g72VarA, size, t02Var, z, z2, z3);
        float dimension = context.getResources().getDimension(R$dimen.tab_grid_card_thumbnail_margin);
        int i2 = bVar.n;
        float f = i2;
        float f2 = f * 0.5f;
        int i3 = bVar.o;
        float f3 = i3;
        float f4 = 0.5f * f3;
        float f5 = dimension / 2.0f;
        ArrayList arrayList = bVar.l;
        float f6 = f2 - f5;
        float f7 = f4 - f5;
        arrayList.add(new RectF(0.0f, 0.0f, f6, f7));
        float f8 = f2 + f5;
        arrayList.add(new RectF(f8, 0.0f, f, f7));
        float f9 = f4 + f5;
        arrayList.add(new RectF(0.0f, f9, f6, f3));
        arrayList.add(new RectF(f8, f9, f, f3));
        float dimension2 = context.getResources().getDimension(R$dimen.tab_grid_thumbnail_favicon_frame_size) / 2.0f;
        float dimension3 = context.getResources().getDimension(R$dimen.tab_grid_thumbnail_favicon_padding_from_frame);
        boolean z5 = false;
        for (int i4 = 0; i4 < 4; i4++) {
            RectF rectF = (RectF) arrayList.get(i4);
            float fCenterX = rectF.centerX();
            float fCenterY = rectF.centerY();
            RectF rectF2 = new RectF(fCenterX, fCenterY, fCenterX, fCenterY);
            float f10 = -dimension2;
            rectF2.inset(f10, f10);
            bVar.m.add(rectF2);
            RectF rectF3 = new RectF(rectF2);
            rectF3.inset(dimension3, dimension3);
            Rect rect = new Rect();
            rectF3.roundOut(rect);
            bVar.k.add(rect);
        }
        bVar.i = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bVar.i);
        bVar.h = canvas;
        canvas.drawColor(0);
        g72 g72Var = bVar.a;
        ArrayList arrayListE = g72.e(context, g72Var, y13Var);
        int size2 = arrayListE.size();
        AtomicInteger atomicInteger = bVar.g;
        ArrayList arrayList2 = bVar.f;
        if (size2 <= 4) {
            atomicInteger.set(arrayListE.size());
            arrayList2.add(g72Var);
            arrayListE.remove(g72Var);
            int i5 = 0;
            while (i5 < 3) {
                arrayList2.add(i5 < arrayListE.size() ? (g72) arrayListE.get(i5) : null);
                i5++;
            }
            z4 = true;
        } else {
            bVar.j = "+" + (arrayListE.size() - 3);
            atomicInteger.set(3);
            arrayList2.add(g72Var);
            arrayListE.remove(g72Var);
            arrayList2.add((g72) arrayListE.get(0));
            z4 = true;
            arrayList2.add((g72) arrayListE.get(1));
            arrayList2.add(null);
        }
        int i6 = 0;
        while (i6 < 4) {
            if (arrayList2.get(i6) != null) {
                final GURL gurlJ = ((g72) arrayList2.get(i6)).j();
                final boolean zK = ((g72) arrayList2.get(i6)).k();
                Size size3 = new Size((int) ((RectF) arrayList.get(i6)).width(), (int) ((RectF) arrayList.get(i6)).height());
                final AtomicReference atomicReference = new AtomicReference();
                final int i7 = i6;
                this.e.e(((g72) arrayList2.get(i6)).c(), size3, new Callback() { // from class: rj1
                    @Override // org.chromium.base.Callback
                    public final void onResult(Object obj) {
                        h.b bVar2 = bVar;
                        int i8 = i7;
                        bVar2.b(i8, (Bitmap) obj);
                        AtomicReference atomicReference2 = atomicReference;
                        if (atomicReference2.get() != null) {
                            bVar2.a(i8, (Drawable) atomicReference2.get());
                            return;
                        }
                        h0 h0Var = h.this.q;
                        g2 g2Var = new g2(bVar2, i8, 1, atomicReference2);
                        h0Var.getClass();
                        h0Var.c(gurlJ, zK, new ty2(g2Var, 4));
                    }
                }, (bVar.c && i6 == 0) ? z4 : z5, (bVar.d && i6 == 0) ? z4 : z5);
            } else {
                bVar.b(i6, null);
                String str = bVar.j;
                if (str != null && i6 == 3) {
                    Paint paint = this.m;
                    bVar.h.drawText(str, (((RectF) arrayList.get(i6)).left + ((RectF) arrayList.get(i6)).right) / 2.0f, ((((RectF) arrayList.get(i6)).top + ((RectF) arrayList.get(i6)).bottom) / 2.0f) - ((paint.ascent() + paint.descent()) / 2.0f), bVar.e ? this.p : paint);
                }
                i6++;
                z4 = true;
                z5 = false;
            }
            i6++;
            z4 = true;
            z5 = false;
        }
    }
}
