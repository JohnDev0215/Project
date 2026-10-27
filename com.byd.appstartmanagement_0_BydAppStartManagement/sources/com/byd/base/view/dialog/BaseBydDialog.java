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
import b.a.b.b.g;
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
    public Dialog n;
    public View o;
    public FrameLayout p;
    public boolean q = true;

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

    public int i() {
        return R$style.base_byd_dialog_common_anim;
    }

    public abstract View j(Bundle bundle);

    public boolean k() {
        return true;
    }

    public final void l() {
        this.o.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        this.p.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        this.p.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
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
        if (this.o != null && k()) {
            this.o.setOnClickListener(null);
            this.o = null;
        }
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.a.b.e.b.a aVar) {
        g.a().a(new a(), 100L);
        Dialog dialog = this.n;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        this.n = dialog;
        if (dialog != null) {
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = 0.0f;
            attributes.windowAnimations = i();
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
        this.p = (FrameLayout) view.findViewById(R$id.base_dialog_frame);
        this.o = view.findViewById(R$id.base_dialog_mask);
        if (k()) {
            this.o.setOnClickListener(this);
        }
        View viewJ = j(bundle);
        if (this.m == null && viewJ != null) {
            h(getContext(), viewJ.getClass().getName());
        }
        if (viewJ != null) {
            this.p.addView(viewJ);
        }
        l();
        if (this.q) {
            this.p.setBackgroundResource(R$drawable.base_common_dialog_background);
        }
    }
}
