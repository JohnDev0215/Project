package org.chromium.components.find_in_page;

import J.N;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import defpackage.a8;
import defpackage.pc;
import defpackage.t41;
import defpackage.tm0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class FindResultBar extends View {
    public static final a D = new a();
    public final Paint A;
    public final Paint B;
    public boolean C;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public final int l;
    public final int m;
    public final int n;
    public final int o;
    public final int p;
    public final int q;
    public tm0 r;
    public final WindowAndroid s;
    public int t;
    public RectF[] u;
    public RectF v;
    public ArrayList<b> w;
    public int x;
    public ObjectAnimator y;
    public boolean z;

    public class a implements Comparator<RectF> {
        @Override // java.util.Comparator
        public final int compare(RectF rectF, RectF rectF2) {
            RectF rectF3 = rectF;
            RectF rectF4 = rectF2;
            int iCompare = Float.compare(rectF3.top, rectF4.top);
            return iCompare != 0 ? iCompare : Float.compare(rectF3.left, rectF4.left);
        }
    }

    public class b implements Comparable<b> {
        public float e;
        public float f;

        public b(float f, float f2) {
            this.e = f;
            this.f = f2;
        }

        public final RectF a() {
            RectF rectF = new RectF(FindResultBar.this.getLeftMargin(), this.e, r1 + r0.l, this.f);
            rectF.inset(2.0f, 0.5f);
            rectF.offset(LocalizationUtils.isLayoutRtl() ? -0.5f : 0.5f, 0.0f);
            return rectF;
        }

        @Override // java.lang.Comparable
        public final int compareTo(b bVar) {
            b bVar2 = bVar;
            return Float.compare((this.e + this.f) * 0.5f, (bVar2.e + bVar2.f) * 0.5f);
        }
    }

    public FindResultBar(Context context, ContentView contentView, WindowAndroid windowAndroid, tm0 tm0Var) {
        super(context);
        this.t = -1;
        this.u = new RectF[0];
        this.w = new ArrayList<>(0);
        this.x = -1;
        Resources resources = context.getResources();
        this.e = context.getColor(R$color.find_result_bar_background_color);
        this.f = context.getColor(R$color.find_result_bar_background_border_color);
        this.g = context.getColor(R$color.find_result_bar_result_color);
        this.h = context.getColor(R$color.find_result_bar_result_border_color);
        this.i = context.getColor(R$color.find_result_bar_active_color);
        this.j = context.getColor(R$color.find_result_bar_active_border_color);
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.find_result_bar_touch_width);
        this.k = dimensionPixelSize;
        this.l = resources.getDimensionPixelSize(R$dimen.find_result_bar_separator_width) + resources.getDimensionPixelSize(R$dimen.find_result_bar_draw_width);
        this.m = resources.getDimensionPixelSize(R$dimen.find_result_bar_result_min_height);
        this.n = resources.getDimensionPixelSize(R$dimen.find_result_bar_active_min_height);
        this.o = resources.getDimensionPixelSize(R$dimen.find_result_bar_vertical_padding);
        this.p = resources.getDimensionPixelSize(R$dimen.find_result_bar_min_gap_between_stacks);
        this.q = resources.getDimensionPixelSize(R$dimen.find_result_bar_stacked_result_height);
        Paint paint = new Paint();
        this.A = paint;
        Paint paint2 = new Paint();
        this.B = paint2;
        paint.setAntiAlias(true);
        paint2.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(1.0f);
        this.r = tm0Var;
        contentView.addView(this, new FrameLayout.LayoutParams(dimensionPixelSize, -1, 8388613));
        setTranslationX(LocalizationUtils.isLayoutRtl() ? -dimensionPixelSize : dimensionPixelSize);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<FindResultBar, Float>) View.TRANSLATION_X, 0.0f);
        this.y = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(200L);
        this.y.setInterpolator(pc.e);
        this.s = windowAndroid;
        if (windowAndroid == null) {
            throw new IllegalArgumentException("WindowAndroid must be non null.");
        }
        windowAndroid.v(this.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLeftMargin() {
        if (LocalizationUtils.isLayoutRtl()) {
            return 0;
        }
        return getWidth() - this.l;
    }

    public final b b(b bVar, boolean z) {
        int i = z ? this.n : this.m;
        float f = bVar.f;
        float f2 = bVar.e;
        float f3 = i - (f - f2);
        if (f3 <= 0.0f) {
            return bVar;
        }
        float f4 = f3 / 2.0f;
        return new b(f2 - f4, f + f4);
    }

    public final void c(int i, RectF[] rectFArr, RectF rectF) {
        if (this.t != i) {
            this.t = i;
            this.u = rectFArr;
            this.w.clear();
            Arrays.sort(this.u, D);
            this.x = -1;
        }
        this.v = rectF;
        invalidate();
    }

    public int getRectsVersion() {
        return this.t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v25 */
    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int iBinarySearch;
        b bVar;
        float f;
        super.onDraw(canvas);
        int leftMargin = getLeftMargin();
        Paint paint = this.A;
        paint.setColor(this.e);
        Paint paint2 = this.B;
        paint2.setColor(this.f);
        float f2 = leftMargin;
        float f3 = leftMargin + this.l;
        canvas.drawRect(f2, 0.0f, f3, getHeight(), paint);
        float f4 = LocalizationUtils.isLayoutRtl() ? f3 - 0.5f : f2 + 0.5f;
        canvas.drawLine(f4, 0.0f, f4, getHeight(), paint2);
        if (this.u.length == 0) {
            return;
        }
        int i = 1;
        if (this.x != getHeight()) {
            this.x = getHeight();
            this.w = new ArrayList<>(this.u.length);
            ?? r2 = 0;
            RectF rectF = this.u[0];
            int i2 = this.x;
            int i3 = this.o;
            float f5 = i2 - (i3 * 2);
            float f6 = i3;
            b bVarB = b(new b((rectF.top * f5) + f6, (rectF.bottom * f5) + f6), false);
            int i4 = this.p;
            float f7 = -i4;
            int i5 = 0;
            while (i5 < this.u.length) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(bVarB);
                while (true) {
                    i5 += i;
                    RectF[] rectFArr = this.u;
                    if (i5 >= rectFArr.length) {
                        break;
                    }
                    RectF rectF2 = rectFArr[i5];
                    float f8 = this.x - (i3 * 2);
                    float f9 = i3;
                    bVarB = b(new b((rectF2.top * f8) + f9, (rectF2.bottom * f8) + f9), r2);
                    if (bVarB.e > ((b) arrayList.get(arrayList.size() - i)).f + i4) {
                        break;
                    } else {
                        arrayList.add(bVarB);
                    }
                }
                int size = arrayList.size();
                int i6 = size - 1;
                float f10 = ((b) arrayList.get(i6)).f;
                float f11 = this.m;
                float f12 = (f10 - (this.q * i6)) - f11;
                float fRound = Math.round(a8.o(f12, f7 + i4, ((b) arrayList.get(r2)).e));
                float f13 = fRound >= f12 ? 1.0f : (f10 - fRound) / (f10 - f12);
                if (size == 1) {
                    f = 0.0f;
                    bVar = bVarB;
                } else {
                    bVar = bVarB;
                    f = ((f10 - fRound) - (f11 * f13)) / i6;
                }
                int i7 = 0;
                while (i7 < size) {
                    int i8 = i4;
                    b bVar2 = (b) arrayList.get(i7);
                    int i9 = i3;
                    float f14 = (i7 * f) + fRound;
                    bVar2.e = f14;
                    if (i7 != i6) {
                        bVar2.f = (f11 * f13) + f14;
                    }
                    this.w.add(bVar2);
                    i7++;
                    i3 = i9;
                    i4 = i8;
                }
                f7 = f10;
                bVarB = bVar;
                r2 = 0;
                i = 1;
            }
        }
        paint.setColor(this.g);
        paint2.setColor(this.h);
        Iterator<b> it = this.w.iterator();
        while (it.hasNext()) {
            RectF rectFA = it.next().a();
            canvas.drawRoundRect(rectFA, 2.0f, 2.0f, paint);
            canvas.drawRoundRect(rectFA, 2.0f, 2.0f, paint2);
        }
        RectF rectF3 = this.v;
        if (rectF3 == null || rectF3.isEmpty() || (iBinarySearch = Arrays.binarySearch(this.u, this.v, D)) < 0) {
            return;
        }
        RectF rectFA2 = b(this.w.get(iBinarySearch), true).a();
        paint.setColor(this.i);
        paint2.setColor(this.j);
        canvas.drawRoundRect(rectFA2, 2.0f, 2.0f, paint);
        canvas.drawRoundRect(rectFA2, 2.0f, 2.0f, paint2);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.z || this.u.length <= 0) {
            return;
        }
        tm0 tm0Var = this.r;
        N.M4m8QCn$(tm0Var.b, tm0Var, this.t);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.z && this.w.size() > 0 && this.w.size() == this.u.length && !this.C && motionEvent.getAction() != 3) {
            t41.f.e(this);
            int iBinarySearch = Collections.binarySearch(this.w, new b(motionEvent.getY(), motionEvent.getY()));
            if (iBinarySearch < 0) {
                int i = (-1) - iBinarySearch;
                if (i == 0) {
                    iBinarySearch = 0;
                } else if (i == this.w.size()) {
                    iBinarySearch = this.w.size() - 1;
                } else {
                    float y = motionEvent.getY();
                    b bVar = this.w.get(i - 1);
                    float fAbs = Math.abs(y - ((bVar.e + bVar.f) * 0.5f));
                    float y2 = motionEvent.getY();
                    b bVar2 = this.w.get(i);
                    iBinarySearch = i - (fAbs <= Math.abs(y2 - ((bVar2.e + bVar2.f) * 0.5f)) ? 1 : 0);
                }
            }
            this.C = true;
            tm0 tm0Var = this.r;
            N.MqpwqIAC(tm0Var.b, tm0Var, this.u[iBinarySearch].centerX(), this.u[iBinarySearch].centerY());
        }
        return true;
    }
}
