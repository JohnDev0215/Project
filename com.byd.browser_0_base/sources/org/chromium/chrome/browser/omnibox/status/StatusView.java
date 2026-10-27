package org.chromium.chrome.browser.omnibox.status;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.au2;
import defpackage.bu2;
import defpackage.cw;
import defpackage.h21;
import defpackage.l91;
import defpackage.m91;
import defpackage.pr1;
import defpackage.ql0;
import defpackage.us;
import defpackage.uw;
import defpackage.v91;
import defpackage.vj;
import defpackage.vs;
import defpackage.yt2;
import defpackage.za3;
import defpackage.zt2;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: loaded from: classes2.dex */
public class StatusView extends LinearLayout {
    public static final /* synthetic */ int E = 0;
    public Integer A;
    public RotateAnimation B;
    public boolean C;
    public v91 D;
    public View e;
    public int f;
    public int g;
    public ImageView h;
    public View i;
    public StatusIconView j;
    public TextView k;
    public View l;
    public View m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public int r;
    public int s;
    public Drawable t;
    public TouchDelegate u;
    public cw v;
    public boolean w;
    public Rect x;
    public vj y;
    public int z;

    public StatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.z = -1;
    }

    public static void a(StatusView statusView, us usVar) {
        statusView.getClass();
        us.b bVarB = usVar.b();
        long iconAnimationDuration = statusView.getIconAnimationDuration();
        ValueAnimator valueAnimator = bVarB.a;
        valueAnimator.setDuration(iconAnimationDuration);
        zt2 zt2Var = new zt2(statusView, 1);
        valueAnimator.removeAllListeners();
        valueAnimator.addListener(new vs(zt2Var));
    }

    private int getIconAnimationDuration() {
        Integer num = this.A;
        if (num == null) {
            return 225;
        }
        return num.intValue();
    }

    public final void b() {
        vj vjVar = this.y;
        if (vjVar != null) {
            vjVar.u(this.z);
            this.z = -1;
        }
    }

    public final void c(int i, Runnable runnable) {
        Drawable drawable;
        v91 v91Var;
        Drawable drawable2 = this.t;
        boolean z = drawable2 == null;
        if (this.C && (v91Var = this.D) != null && v91Var.i() != null && !this.D.i().O()) {
            e(this.C, this.D);
            return;
        }
        RotateAnimation rotateAnimation = this.B;
        if (rotateAnimation != null) {
            rotateAnimation.cancel();
        }
        this.h.clearAnimation();
        boolean z2 = this.j.getIconVisibility() == 8;
        if (!z && (z2 || this.p)) {
            if (this.p) {
                this.h.animate().cancel();
            }
            this.p = false;
            this.o = true;
            d();
            this.j.setVisibility(0);
            this.h.animate().alpha(1.0f).setDuration(getIconAnimationDuration()).withEndAction(new ql0(20, this)).start();
        } else if (!z || (z2 && !this.o)) {
            f();
        } else {
            if (this.o) {
                this.h.animate().cancel();
            }
            this.o = false;
            this.p = true;
            d();
            this.h.animate().setDuration(this.n ? getIconAnimationDuration() : 0L).alpha(0.0f).withEndAction(new zt2(this, 0)).start();
        }
        if (drawable2 != null) {
            if (z2) {
                this.h.setImageDrawable(drawable2);
                return;
            }
            Drawable drawable3 = this.h.getDrawable();
            if (drawable3 instanceof us) {
                us usVar = (us) drawable3;
                if (usVar.h.isRunning()) {
                    usVar.h.cancel();
                }
                usVar.a(255);
                drawable3 = usVar.g;
            }
            if (i == 1) {
                RotateDrawable rotateDrawable = new RotateDrawable();
                rotateDrawable.setDrawable(drawable2);
                rotateDrawable.setToDegrees(180.0f);
                rotateDrawable.setLevel(TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE);
                drawable = rotateDrawable;
            } else {
                drawable = drawable2;
            }
            us usVar2 = new us(drawable3, drawable);
            this.h.setImageDrawable(usVar2);
            if (i == 0) {
                this.q = true;
                long iconAnimationDuration = this.n ? getIconAnimationDuration() : 0L;
                if (iconAnimationDuration > 0) {
                    d();
                }
                usVar2.i = true;
                usVar2.f.setAlpha(255 - usVar2.j);
                ValueAnimator valueAnimator = usVar2.b().a;
                valueAnimator.setDuration(iconAnimationDuration);
                yt2 yt2Var = new yt2(this, 1);
                valueAnimator.removeAllListeners();
                valueAnimator.addListener(new vs(yt2Var));
            } else {
                this.q = true;
                d();
                this.h.animate().setDuration(250L).rotationBy(180.0f).setInterpolator(h21.d).withStartAction(new l91(this, 21, usVar2)).withEndAction(new m91(this, 6, drawable2, runnable)).start();
            }
            if (this.o) {
                return;
            }
            f();
        }
    }

    public final void d() {
        vj vjVar;
        if (!isShown() || (vjVar = this.y) == null) {
            return;
        }
        this.z = vjVar.w(this.z);
    }

    public final void e(boolean z, v91 v91Var) {
        this.C = z;
        this.D = v91Var;
        if (!z || this.h.getVisibility() != 0 || v91Var == null || v91Var.i() == null || v91Var.i().O()) {
            if (v91Var != null && v91Var.b() != null && !TextUtils.isEmpty(v91Var.b().a)) {
                c(0, null);
                return;
            }
            RotateAnimation rotateAnimation = this.B;
            if (rotateAnimation != null) {
                rotateAnimation.cancel();
            }
            this.h.clearAnimation();
            return;
        }
        this.h.setImageDrawable(za3.b(getContext(), R$drawable.refresh_url_bar, R$color.default_icon_color_tint_list));
        if (this.B == null) {
            this.B = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            this.B.setInterpolator(new LinearInterpolator());
            this.B.setDuration(1000L);
            this.B.setRepeatCount(-1);
            this.B.setFillAfter(false);
        }
        this.B.reset();
        this.h.startAnimation(this.B);
    }

    public final void f() {
        if (this.v == null) {
            return;
        }
        if (!((this.t == null || this.j.getIconVisibility() == 8 || this.h.getAlpha() == 0.0f) ? false : true)) {
            TouchDelegate touchDelegate = this.u;
            if (touchDelegate != null) {
                this.v.a.remove(touchDelegate);
                this.u = null;
                this.x = new Rect();
                return;
            }
            return;
        }
        Rect rect = new Rect();
        this.h.getHitRect(rect);
        if (rect.equals(new Rect())) {
            return;
        }
        boolean z = getLayoutDirection() == 1;
        if (this.f == 0 && !pr1.e(getContext())) {
            this.f = getResources().getDimensionPixelSize(R$dimen.location_bar_start_padding);
        }
        if (this.g == 0) {
            this.g = getResources().getDimensionPixelSize(R$dimen.location_bar_icon_margin_end);
        }
        rect.left -= z ? this.g : this.f;
        rect.right += z ? this.f : this.g;
        if (DeviceFormFactor.a(getContext())) {
            int i = rect.top;
            Resources resources = getResources();
            int i2 = R$dimen.modern_toolbar_background_vertical_offset;
            rect.top = i - resources.getDimensionPixelSize(i2);
            rect.bottom = getResources().getDimensionPixelSize(i2) + rect.bottom;
        }
        if (this.u != null && rect.equals(this.x) && this.w == z) {
            return;
        }
        this.x = rect;
        TouchDelegate touchDelegate2 = this.u;
        if (touchDelegate2 != null) {
            this.v.a.remove(touchDelegate2);
        }
        TouchDelegate touchDelegate3 = new TouchDelegate(rect, this.h);
        this.u = touchDelegate3;
        this.v.a.add(touchDelegate3);
        this.w = z;
    }

    public View getSecurityView() {
        return this.h;
    }

    public int getStatusIconWidth() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        return marginLayoutParams.getMarginEnd() + getMeasuredWidth() + marginLayoutParams.getMarginStart();
    }

    public TouchDelegate getTouchDelegateForTesting() {
        return this.u;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.h = (ImageView) findViewById(R$id.location_bar_status_icon);
        this.i = findViewById(R$id.location_bar_status_icon_bg);
        this.j = (StatusIconView) findViewById(R$id.location_bar_status_icon_view);
        this.k = (TextView) findViewById(R$id.location_bar_verbose_status);
        this.l = findViewById(R$id.location_bar_verbose_status_separator);
        this.m = findViewById(R$id.location_bar_verbose_status_extra_space);
        setOnLongClickListener(new au2(this));
        setAccessibilityDelegate(new bu2(this));
    }

    public void setAnimationsEnabled(boolean z) {
        this.n = z;
    }

    public void setBrowserControlsVisibilityDelegate(vj vjVar) {
        this.y = vjVar;
    }

    public void setCompositeTouchDelegate(cw cwVar) {
        this.v = cwVar;
        this.h.addOnLayoutChangeListener(new uw(1, this));
    }

    public void setIconAnimationDurationForTesting(int i) {
        this.A = Integer.valueOf(i);
    }

    public void setIncognitoBadgeVisibility(boolean z) {
        View view = this.e;
        if (view != null || z) {
            if (view == null) {
                this.e = ((ViewStub) findViewById(R$id.location_bar_incognito_badge_stub)).inflate();
            }
            this.e.setVisibility(z ? 0 : 8);
            f();
        }
    }

    public void setSeparatorColor(int i) {
        this.l.setBackgroundColor(i);
    }

    public void setStatusAccessibilityDoubleTapDescription(int i) {
        this.s = i;
    }

    public void setStatusAccessibilityToast(int i) {
        this.r = i;
    }

    public void setStatusClickListener(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
    }

    public void setStatusIconAlpha(float f) {
        ImageView imageView = this.h;
        if (imageView == null) {
            return;
        }
        imageView.setAlpha(f);
    }

    public void setStatusIconBackgroundVisibility(boolean z) {
        View view;
        if (this.h == null || (view = this.i) == null) {
            return;
        }
        view.setVisibility(z ? 0 : 4);
    }

    public void setStatusIconDescription(int i) {
        String string;
        int i2;
        if (i != 0) {
            string = getResources().getString(i);
            i2 = 1;
        } else {
            string = null;
            i2 = 2;
        }
        this.h.setContentDescription(string);
        setImportantForAccessibility(i2);
    }

    public void setStatusIconShown(boolean z) {
        if (this.j == null) {
            return;
        }
        boolean zIsLayoutRequested = isLayoutRequested();
        this.j.setVisibility(z ? 0 : 8);
        f();
        if (this.q && !z) {
            this.q = false;
            b();
        }
        if (!zIsLayoutRequested || getHandler() == null) {
            return;
        }
        getHandler().post(new yt2(this, 0));
    }

    public void setVerboseStatusTextColor(int i) {
        this.k.setTextColor(i);
    }

    public void setVerboseStatusTextContent(int i) {
        this.k.setText(i);
    }

    public void setVerboseStatusTextVisible(boolean z) {
        int i = z ? 0 : 8;
        this.k.setVisibility(i);
        this.l.setVisibility(i);
        this.m.setVisibility(i);
    }

    public void setVerboseStatusTextWidth(int i) {
        this.k.setMaxWidth(i);
    }
}
