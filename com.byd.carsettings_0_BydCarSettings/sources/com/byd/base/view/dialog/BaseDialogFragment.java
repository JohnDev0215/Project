package com.byd.base.view.dialog;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.f.h;
import b.b.m.g.g;
import com.byd.base.R$style;
import com.byd.mvp.common.BydLayoutDirection;
import com.byd.mvp.utils.AppConfig;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseDialogFragment extends LeakDialogFragment {
    public boolean cancelable;
    public Dialog dialog;
    public int mHeight;
    public int mWidth;
    private final String TAG = "BaseDialogFragment";
    private final int animTime = 200;
    public volatile int mLayoutValue = -1;
    public volatile int mOrientationValue = -1;
    public volatile BydLayoutDirection mDirection = null;
    public boolean isFullScreen = true;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BaseDialogFragment.this.isResumed()) {
                BaseDialogFragment.this.dismiss();
            }
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2485a;

        static {
            int[] iArr = new int[BydLayoutDirection.values().length];
            f2485a = iArr;
            try {
                iArr[BydLayoutDirection.LTR_Rudder_L.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2485a[BydLayoutDirection.LTR_Rudder_R.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2485a[BydLayoutDirection.RTL_Rudder_L.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2485a[BydLayoutDirection.RTL_Rudder_R.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public int getBaseDialogCommonAnim() {
        return R$style.base_byd_dialog_common_anim;
    }

    public abstract int getLayoutResource();

    public void onBydLayoutDirectionChanged(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        b.b.m.c.a.d("BaseDialogFragment", "---onBydLayoutDirectionChanged----direction: " + bydLayoutDirection + ", rtl: " + z + ", land: " + z2 + ", rightRudder: " + z3);
        int i = b.f2485a[bydLayoutDirection.ordinal()];
        if (i == 1) {
            onLtrRudderL(z2);
        } else if (i == 2) {
            onLtrRudderR(z2);
        } else {
            if (i != 3) {
                return;
            }
            onRtlRudderL(z2);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setLayoutDirection(configuration);
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R$style.DialogFullScreen);
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(getLayoutResource(), viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.j.b.b bVar) {
        h.a().g(new a(), 100L);
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void onLayoutDirectionChanged(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
    }

    public void onLtrRudderL(boolean z) {
    }

    public void onLtrRudderR(boolean z) {
    }

    public void onRtlRudderL(boolean z) {
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        this.dialog = dialog;
        if (dialog != null) {
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = 0.0f;
            attributes.windowAnimations = getBaseDialogCommonAnim();
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(0);
            if (this.isFullScreen) {
                window.getDecorView().setSystemUiVisibility(1792);
            }
            window.setAttributes(attributes);
        }
        setLayoutDirection(getResources().getConfiguration());
    }

    public void setAnim(View view, View view2) {
        view.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        view2.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        view2.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }

    public void setLayoutDirection(Configuration configuration) {
        if (g.e()) {
            return;
        }
        int layoutDirection = configuration.getLayoutDirection();
        int i = configuration.orientation;
        if (this.mLayoutValue == layoutDirection && this.mOrientationValue == i) {
            return;
        }
        this.mLayoutValue = layoutDirection;
        this.mOrientationValue = i;
        boolean z = layoutDirection == 1;
        boolean z2 = i == 2;
        boolean zD = AppConfig.d();
        BydLayoutDirection bydLayoutDirection = z ? zD ? BydLayoutDirection.RTL_Rudder_R : BydLayoutDirection.RTL_Rudder_L : zD ? BydLayoutDirection.LTR_Rudder_R : BydLayoutDirection.LTR_Rudder_L;
        b.b.m.c.a.d("BaseDialogFragment", "---setLayoutDirection----direction: " + bydLayoutDirection + ", " + getClass().getName());
        onLayoutDirectionChanged(bydLayoutDirection, z, z2, AppConfig.d());
        if (bydLayoutDirection == this.mDirection) {
            return;
        }
        onBydLayoutDirectionChanged(bydLayoutDirection, z, z2, AppConfig.d());
    }

    public void setViewGroupRtl(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.setLayoutDirection(z ? 1 : 0);
    }
}
