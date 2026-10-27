package defpackage;

import J.N;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Kx extends View {
    public static Comparator R = new Hx();
    public final int A;
    public final int B;
    public final int C;
    public final int D;
    public final int E;
    public Gx F;
    public final WindowAndroid G;
    public int H;
    public RectF[] I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public RectF f5J;
    public ArrayList K;
    public int L;
    public Animator M;
    public boolean N;
    public final Paint O;
    public final Paint P;
    public boolean Q;
    public final int s;
    public final int t;
    public final int u;
    public final int v;
    public final int w;
    public final int x;
    public final int y;
    public final int z;

    public Kx(Context context, FrameLayout frameLayout, WindowAndroid windowAndroid, Gx gx) {
        super(context);
        this.H = -1;
        this.I = new RectF[0];
        this.K = new ArrayList(0);
        this.L = -1;
        Resources resources = context.getResources();
        this.s = resources.getColor(AbstractC0281Pm.j3);
        this.t = resources.getColor(AbstractC0281Pm.i3);
        this.u = resources.getColor(AbstractC0281Pm.n3);
        this.v = resources.getColor(AbstractC0281Pm.m3);
        this.w = resources.getColor(AbstractC0281Pm.h3);
        this.x = resources.getColor(AbstractC0281Pm.g3);
        this.y = resources.getDimensionPixelSize(AbstractC0299Qm.O2);
        this.z = resources.getDimensionPixelSize(AbstractC0299Qm.M2) + resources.getDimensionPixelSize(AbstractC0299Qm.J2);
        this.A = resources.getDimensionPixelSize(AbstractC0299Qm.L2);
        this.B = resources.getDimensionPixelSize(AbstractC0299Qm.I2);
        this.C = resources.getDimensionPixelSize(AbstractC0299Qm.P2);
        this.D = resources.getDimensionPixelSize(AbstractC0299Qm.K2);
        this.E = resources.getDimensionPixelSize(AbstractC0299Qm.N2);
        this.O = new Paint();
        this.P = new Paint();
        this.O.setAntiAlias(true);
        this.P.setAntiAlias(true);
        this.O.setStyle(Paint.Style.FILL);
        this.P.setStyle(Paint.Style.STROKE);
        this.P.setStrokeWidth(1.0f);
        this.F = gx;
        frameLayout.addView(this, new FrameLayout.LayoutParams(this.y, -1, 8388613));
        int i = this.y;
        setTranslationX(LocalizationUtils.isLayoutRtl() ? -i : i);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<Kx, Float>) View.TRANSLATION_X, 0.0f);
        this.M = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(200L);
        this.M.setInterpolator(InterpolatorC1217nO.i);
        this.G = windowAndroid;
        if (windowAndroid == null) {
            throw new IllegalArgumentException("WindowAndroid must be non null.");
        }
        windowAndroid.Z(this.M);
    }

    public final Jx a(Jx jx, boolean z) {
        int i = z ? this.B : this.A;
        float f = jx.t;
        float f2 = jx.s;
        float f3 = i - (f - f2);
        if (f3 <= 0.0f) {
            return jx;
        }
        float f4 = f3 / 2.0f;
        return new Jx(this, f2 - f4, f + f4);
    }

    public void b(int i, RectF[] rectFArr, RectF rectF) {
        if (this.H != i) {
            this.H = i;
            this.I = rectFArr;
            this.K.clear();
            Arrays.sort(this.I, R);
            this.L = -1;
        }
        this.f5J = rectF;
        invalidate();
    }

    public final Jx c(RectF rectF, boolean z) {
        int i = this.L;
        int i2 = this.C;
        float f = i - (i2 * 2);
        return a(new Jx(this, (rectF.top * f) + i2, (rectF.bottom * f) + i2), z);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boolean z = false;
        int width = LocalizationUtils.isLayoutRtl() ? 0 : getWidth() - this.z;
        this.O.setColor(this.s);
        this.P.setColor(this.t);
        float f = width;
        canvas.drawRect(f, 0.0f, this.z + width, getHeight(), this.O);
        float f2 = LocalizationUtils.isLayoutRtl() ? (width + this.z) - 0.5f : f + 0.5f;
        canvas.drawLine(f2, 0.0f, f2, getHeight(), this.P);
        if (this.I.length == 0) {
            return;
        }
        if (this.L != getHeight()) {
            this.L = getHeight();
            this.K = new ArrayList(this.I.length);
            Jx jxC = c(this.I[0], false);
            float f3 = -this.D;
            int i = 0;
            while (i < this.I.length) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(jxC);
                while (true) {
                    i++;
                    RectF[] rectFArr = this.I;
                    if (i >= rectFArr.length) {
                        break;
                    }
                    jxC = c(rectFArr[i], z);
                    if (jxC.s > ((Jx) arrayList.get(arrayList.size() - 1)).t + this.D) {
                        break;
                    } else {
                        arrayList.add(jxC);
                    }
                }
                int size = arrayList.size();
                float f4 = f3 + this.D;
                int i2 = size - 1;
                float f5 = ((Jx) arrayList.get(i2)).t;
                float f6 = (f5 - (this.E * i2)) - this.A;
                float f7 = ((Jx) arrayList.get(z ? 1 : 0)).s;
                float f8 = f4 > f7 ? f7 : f4;
                if (f4 <= f7) {
                    f4 = f7;
                }
                if (f6 >= f8) {
                    f8 = f6 > f4 ? f4 : f6;
                }
                float fRound = Math.round(f8);
                float f9 = fRound >= f6 ? 1.0f : (f5 - fRound) / (f5 - f6);
                float f10 = size == 1 ? 0.0f : ((f5 - fRound) - (this.A * f9)) / i2;
                int i3 = 0;
                while (i3 < size) {
                    Jx jx = (Jx) arrayList.get(i3);
                    float f11 = (i3 * f10) + fRound;
                    jx.s = f11;
                    if (i3 != i2) {
                        jx.t = (this.A * f9) + f11;
                    }
                    this.K.add(jx);
                    i3++;
                    z = false;
                }
                f3 = f5;
            }
        }
        this.O.setColor(this.u);
        this.P.setColor(this.v);
        Iterator it = this.K.iterator();
        while (it.hasNext()) {
            RectF rectFB = ((Jx) it.next()).b();
            canvas.drawRoundRect(rectFB, 2.0f, 2.0f, this.O);
            canvas.drawRoundRect(rectFB, 2.0f, 2.0f, this.P);
        }
        RectF rectF = this.f5J;
        if (rectF != null) {
            int iBinarySearch = Arrays.binarySearch(this.I, rectF, R);
            RectF rectFB2 = (iBinarySearch >= 0 ? a((Jx) this.K.get(iBinarySearch), true) : c(this.f5J, true)).b();
            this.O.setColor(this.w);
            this.P.setColor(this.x);
            canvas.drawRoundRect(rectFB2, 2.0f, 2.0f, this.O);
            canvas.drawRoundRect(rectFB2, 2.0f, 2.0f, this.P);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.N || this.I.length <= 0) {
            return;
        }
        Gx gx = this.F;
        N.M4m8QCn$(gx.a, gx, this.H);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.N && this.K.size() > 0 && this.K.size() == this.I.length && !this.Q && motionEvent.getAction() != 3) {
            Objects.requireNonNull(AN.b);
            ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
            int iBinarySearch = Collections.binarySearch(this.K, new Jx(this, motionEvent.getY(), motionEvent.getY()));
            if (iBinarySearch < 0) {
                int i = (-1) - iBinarySearch;
                if (i != 0) {
                    if (i == this.K.size()) {
                        size = this.K.size() - 1;
                    } else {
                        size = i - (Math.abs(motionEvent.getY() - ((Jx) this.K.get(i + (-1))).a()) <= Math.abs(motionEvent.getY() - ((Jx) this.K.get(i)).a()) ? 1 : 0);
                    }
                }
            } else {
                size = iBinarySearch;
            }
            this.Q = true;
            Gx gx = this.F;
            N.MqpwqIAC(gx.a, gx, this.I[size].centerX(), this.I[size].centerY());
        }
        return true;
    }
}
