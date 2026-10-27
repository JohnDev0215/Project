package com.byd.base.view.dialog;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import b.a.d.f.d;
import com.byd.mvp.common.BydLayoutDirection;
import com.byd.mvp.utils.AppConfig;

/* JADX INFO: loaded from: classes.dex */
public abstract class FrameDialogView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f563b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f564c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile int f565d;
    public volatile BydLayoutDirection e;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f566a;

        static {
            int[] iArr = new int[BydLayoutDirection.values().length];
            f566a = iArr;
            try {
                iArr[BydLayoutDirection.LTR_Rudder_L.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f566a[BydLayoutDirection.LTR_Rudder_R.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f566a[BydLayoutDirection.RTL_Rudder_L.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f566a[BydLayoutDirection.RTL_Rudder_R.ordinal()] = 4;
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
        int i = a.f566a[bydLayoutDirection.ordinal()];
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

    public abstract int getDialogViewRes();

    public void setLayoutDirection(Configuration configuration) {
        if (d.a()) {
            return;
        }
        int layoutDirection = configuration.getLayoutDirection();
        int i = configuration.orientation;
        if (this.f564c == layoutDirection && this.f565d == i) {
            return;
        }
        this.f564c = layoutDirection;
        this.f565d = i;
        boolean z = layoutDirection == 1;
        boolean z2 = i == 2;
        boolean zB = AppConfig.b();
        BydLayoutDirection bydLayoutDirection = z ? zB ? BydLayoutDirection.RTL_Rudder_R : BydLayoutDirection.RTL_Rudder_L : zB ? BydLayoutDirection.LTR_Rudder_R : BydLayoutDirection.LTR_Rudder_L;
        c(bydLayoutDirection, z, z2, AppConfig.b());
        if (bydLayoutDirection == this.e) {
            return;
        }
        b(bydLayoutDirection, z, z2, AppConfig.b());
    }

    public FrameDialogView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FrameDialogView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f563b = getClass().getSimpleName();
        this.f564c = -1;
        this.f565d = -1;
        this.e = null;
        View viewInflate = LayoutInflater.from(context).inflate(getDialogViewRes(), (ViewGroup) this, false);
        a(viewInflate);
        addView(viewInflate);
        setLayoutDirection(context.getResources().getConfiguration());
    }
}
