package com.bydux.api.common.views.ctrl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.bydux.api.common.views.ctrl.BydTimer;
import d.a.a.e;
import d.a.a.f.c.a.c.c;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class BydFrameAnimation extends TextView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3571b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3572c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b f3573d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private BydTimer f3574e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f3575f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3576g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f3577h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f3578i;
    private Drawable[] j;
    private int k;
    private int l;
    private boolean m;

    class a implements BydTimer.b {
        a() {
        }

        @Override // com.bydux.api.common.views.ctrl.BydTimer.b
        public void a(View view) {
            BydFrameAnimation.c(BydFrameAnimation.this);
            if (BydFrameAnimation.this.l >= BydFrameAnimation.this.f3576g) {
                if (!BydFrameAnimation.this.f3577h) {
                    BydFrameAnimation.this.k();
                    return;
                } else {
                    BydFrameAnimation.this.l = 0;
                    if (BydFrameAnimation.this.f3573d != null) {
                        BydFrameAnimation.this.f3573d.b(BydFrameAnimation.this);
                    }
                }
            }
            BydFrameAnimation.this.l();
        }
    }

    public interface b {
        void a(View view);

        void b(View view);
    }

    public BydFrameAnimation(Context context) {
        super(context);
        this.f3571b = getClass().getSimpleName();
        this.f3575f = null;
        this.f3576g = 0;
        this.f3577h = true;
        this.f3578i = false;
        this.j = null;
        this.k = 40;
        this.l = 0;
        this.m = true;
        h(context);
    }

    static /* synthetic */ int c(BydFrameAnimation bydFrameAnimation) {
        int i2 = bydFrameAnimation.l;
        bydFrameAnimation.l = i2 + 1;
        return i2;
    }

    private void h(Context context) {
        this.f3572c = context;
        BydTimer bydTimer = new BydTimer(context);
        this.f3574e = bydTimer;
        bydTimer.setBydTimerListener(new a());
    }

    private void i(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = this.f3572c.obtainStyledAttributes(attributeSet, e.BydFrameAnimation);
            this.f3575f = typedArrayObtainStyledAttributes.getString(e.BydFrameAnimation_byd_frame_prefix);
            this.f3576g = typedArrayObtainStyledAttributes.getInteger(e.BydFrameAnimation_byd_frame_total, this.f3576g);
            this.k = typedArrayObtainStyledAttributes.getInteger(e.BydFrameAnimation_byd_frame_speed, this.k);
            this.f3577h = typedArrayObtainStyledAttributes.getBoolean(e.BydFrameAnimation_byd_frame_repeat, this.f3577h);
            this.f3578i = typedArrayObtainStyledAttributes.getBoolean(e.BydFrameAnimation_byd_frame_manual, this.f3578i);
            typedArrayObtainStyledAttributes.recycle();
            j(this.f3575f, this.f3576g);
            setFrameSpeed(this.k);
            setFrameRepeat(this.f3577h);
            if (this.f3578i) {
                this.m = false;
            } else {
                this.m = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        int i2;
        Drawable[] drawableArr = this.j;
        if (drawableArr == null || (i2 = this.l) >= drawableArr.length) {
            return;
        }
        setBackground(drawableArr[i2]);
    }

    public void j(String str, int i2) {
        this.f3575f = str;
        this.f3576g = i2;
        if (str == null || i2 <= 0) {
            return;
        }
        this.j = new Drawable[i2];
        for (int i3 = 0; i3 < this.f3576g; i3++) {
            this.j[i3] = c.n().m(this.f3575f + i3);
        }
    }

    public void k() {
        this.m = false;
        BydTimer bydTimer = this.f3574e;
        if (bydTimer != null) {
            bydTimer.g();
        }
        b bVar = this.f3573d;
        if (bVar != null) {
            bVar.a(this);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        BydTimer bydTimer;
        super.onAttachedToWindow();
        l();
        if (!this.m || (bydTimer = this.f3574e) == null) {
            return;
        }
        bydTimer.e(this.k);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydTimer bydTimer = this.f3574e;
        if (bydTimer != null) {
            bydTimer.g();
        }
    }

    public void setBydFrameAnimationListener(b bVar) {
        this.f3573d = bVar;
    }

    public void setFrameRepeat(boolean z) {
        this.f3577h = z;
    }

    public void setFrameSpeed(int i2) {
        BydTimer bydTimer;
        if (i2 > 0 && this.k != i2) {
            this.k = i2;
            if (!this.m || (bydTimer = this.f3574e) == null) {
                return;
            }
            bydTimer.e(i2);
        }
    }

    public BydFrameAnimation(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3571b = getClass().getSimpleName();
        this.f3575f = null;
        this.f3576g = 0;
        this.f3577h = true;
        this.f3578i = false;
        this.j = null;
        this.k = 40;
        this.l = 0;
        this.m = true;
        h(context);
        i(attributeSet);
    }
}
