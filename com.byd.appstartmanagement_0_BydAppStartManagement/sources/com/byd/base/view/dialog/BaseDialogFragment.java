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
import b.a.b.b.g;
import b.a.d.f.d;
import com.byd.base.R$style;
import com.byd.mvp.common.BydLayoutDirection;
import com.byd.mvp.utils.AppConfig;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseDialogFragment extends LeakDialogFragment {
    public Dialog s;
    public final String n = "BaseDialogFragment";
    public final int o = 200;
    public volatile int p = -1;
    public volatile int q = -1;
    public volatile BydLayoutDirection r = null;
    public boolean t = true;

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
        public static final /* synthetic */ int[] f562a;

        static {
            int[] iArr = new int[BydLayoutDirection.values().length];
            f562a = iArr;
            try {
                iArr[BydLayoutDirection.LTR_Rudder_L.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f562a[BydLayoutDirection.LTR_Rudder_R.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f562a[BydLayoutDirection.RTL_Rudder_L.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f562a[BydLayoutDirection.RTL_Rudder_R.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public int i() {
        return R$style.base_byd_dialog_common_anim;
    }

    public abstract int j();

    public void k(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        b.a.d.b.a.d("BaseDialogFragment", "---onBydLayoutDirectionChanged----direction: " + bydLayoutDirection + ", rtl: " + z + ", land: " + z2 + ", rightRudder: " + z3);
        int i = b.f562a[bydLayoutDirection.ordinal()];
        if (i == 1) {
            m(z2);
        } else if (i == 2) {
            n(z2);
        } else {
            if (i != 3) {
                return;
            }
            o(z2);
        }
    }

    public void l(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
    }

    public void m(boolean z) {
    }

    public void n(boolean z) {
    }

    public void o(boolean z) {
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        p(configuration);
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
        return layoutInflater.inflate(j(), viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.a.b.e.b.a aVar) {
        g.a().a(new a(), 100L);
        Dialog dialog = this.s;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        this.s = dialog;
        if (dialog != null) {
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = 0.0f;
            attributes.windowAnimations = i();
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(0);
            if (this.t) {
                window.getDecorView().setSystemUiVisibility(1792);
            }
            window.setAttributes(attributes);
        }
        p(getResources().getConfiguration());
    }

    public void p(Configuration configuration) {
        if (d.a()) {
            return;
        }
        int layoutDirection = configuration.getLayoutDirection();
        int i = configuration.orientation;
        if (this.p == layoutDirection && this.q == i) {
            return;
        }
        this.p = layoutDirection;
        this.q = i;
        boolean z = layoutDirection == 1;
        boolean z2 = i == 2;
        boolean zB = AppConfig.b();
        BydLayoutDirection bydLayoutDirection = z ? zB ? BydLayoutDirection.RTL_Rudder_R : BydLayoutDirection.RTL_Rudder_L : zB ? BydLayoutDirection.LTR_Rudder_R : BydLayoutDirection.LTR_Rudder_L;
        b.a.d.b.a.d("BaseDialogFragment", "---setLayoutDirection----direction: " + bydLayoutDirection + ", " + getClass().getName());
        l(bydLayoutDirection, z, z2, AppConfig.b());
        if (bydLayoutDirection == this.r) {
            return;
        }
        k(bydLayoutDirection, z, z2, AppConfig.b());
    }
}
