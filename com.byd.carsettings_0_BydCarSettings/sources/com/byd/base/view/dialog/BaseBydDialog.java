package com.byd.base.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.f.h;
import b.b.a.j.b.b;
import com.byd.base.R$color;
import com.byd.base.R$drawable;
import com.byd.base.R$id;
import com.byd.base.R$layout;
import com.byd.base.R$style;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseBydDialog extends LeakDialogFragment implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Dialog f2479a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f2480b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public FrameLayout f2481c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2482d = true;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BaseBydDialog.this.isResumed()) {
                BaseBydDialog.this.dismiss();
            }
        }
    }

    public int getBaseDialogCommonAnim() {
        return R$style.base_byd_dialog_common_anim;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R$id.base_dialog_mask) {
            dismiss();
        }
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
        return layoutInflater.inflate(R$layout.base_dialog_v2, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.f2480b != null && u()) {
            this.f2480b.setOnClickListener(null);
            this.f2480b = null;
        }
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b bVar) {
        h.a().g(new a(), 100L);
        Dialog dialog = this.f2479a;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        this.f2479a = dialog;
        if (dialog != null) {
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = 0.0f;
            attributes.windowAnimations = getBaseDialogCommonAnim();
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(0);
            window.getDecorView().setSystemUiVisibility(1792);
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f2481c = (FrameLayout) view.findViewById(R$id.base_dialog_frame);
        this.f2480b = view.findViewById(R$id.base_dialog_mask);
        if (u()) {
            this.f2480b.setOnClickListener(this);
        }
        View viewT = t(bundle);
        if (this.windowTitle == null && viewT != null) {
            setWindowTitle(getContext(), viewT.getClass().getName());
        }
        if (viewT != null) {
            this.f2481c.addView(viewT);
        }
        v();
        if (this.f2482d) {
            this.f2481c.setBackgroundResource(R$drawable.base_common_dialog_background);
        }
    }

    public abstract View t(Bundle bundle);

    public boolean u() {
        return true;
    }

    public final void v() {
        this.f2480b.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        this.f2481c.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        this.f2481c.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }

    public void w() {
        View view = this.f2480b;
        if (view != null) {
            view.setBackgroundColor(getResources().getColor(R$color.color_transparent));
        }
    }

    public void x(boolean z) {
        this.f2482d = z;
    }
}
