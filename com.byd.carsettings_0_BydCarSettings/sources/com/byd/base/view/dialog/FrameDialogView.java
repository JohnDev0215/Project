package com.byd.base.view.dialog;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import b.b.m.g.g;
import com.byd.mvp.common.BydLayoutDirection;
import com.byd.mvp.utils.AppConfig;

/* JADX INFO: loaded from: classes.dex */
public abstract class FrameDialogView extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2486a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile int f2487b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f2488c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile BydLayoutDirection f2489d;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2490a;

        static {
            int[] iArr = new int[BydLayoutDirection.values().length];
            f2490a = iArr;
            try {
                iArr[BydLayoutDirection.LTR_Rudder_L.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2490a[BydLayoutDirection.LTR_Rudder_R.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2490a[BydLayoutDirection.RTL_Rudder_L.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2490a[BydLayoutDirection.RTL_Rudder_R.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public FrameDialogView(Context context) {
        this(context, null);
    }

    public abstract void a(View view);

    public void b(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
    }

    public void c(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        int i = a.f2490a[bydLayoutDirection.ordinal()];
        if (i == 1) {
            d(z2);
        } else if (i == 2) {
            e(z2);
        } else {
            if (i != 3) {
                return;
            }
            f(z2);
        }
    }

    public void d(boolean z) {
    }

    public void e(boolean z) {
    }

    public void f(boolean z) {
    }

    public void g(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.setLayoutDirection(z ? 1 : 0);
    }

    public abstract int getDialogViewRes();

    public void setLayoutDirection(Configuration configuration) {
        if (g.e()) {
            return;
        }
        int layoutDirection = configuration.getLayoutDirection();
        int i = configuration.orientation;
        if (this.f2487b == layoutDirection && this.f2488c == i) {
            return;
        }
        this.f2487b = layoutDirection;
        this.f2488c = i;
        boolean z = layoutDirection == 1;
        boolean z2 = i == 2;
        boolean zD = AppConfig.d();
        BydLayoutDirection bydLayoutDirection = z ? zD ? BydLayoutDirection.RTL_Rudder_R : BydLayoutDirection.RTL_Rudder_L : zD ? BydLayoutDirection.LTR_Rudder_R : BydLayoutDirection.LTR_Rudder_L;
        c(bydLayoutDirection, z, z2, AppConfig.d());
        if (bydLayoutDirection == this.f2489d) {
            return;
        }
        b(bydLayoutDirection, z, z2, AppConfig.d());
    }

    public FrameDialogView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FrameDialogView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2486a = getClass().getSimpleName();
        this.f2487b = -1;
        this.f2488c = -1;
        this.f2489d = null;
        View viewInflate = LayoutInflater.from(context).inflate(getDialogViewRes(), (ViewGroup) this, false);
        a(viewInflate);
        addView(viewInflate);
        setLayoutDirection(context.getResources().getConfiguration());
    }
}
