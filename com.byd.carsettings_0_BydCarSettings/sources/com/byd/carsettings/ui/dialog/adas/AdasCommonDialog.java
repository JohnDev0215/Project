package com.byd.carsettings.ui.dialog.adas;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import com.byd.base.view.dialog.BaseBydDialog;
import com.byd.base.view.dialog.FrameDialogView;
import com.byd.carsettings.R;
import com.byd.carsettings.databinding.DialogAdasCommonBinding;
import com.byd.dipilot.viewmodel.NoConfigDialogModel;
import com.byd.dipilot.widget.CountDownButton;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class AdasCommonDialog extends BaseBydDialog implements b.b.k.d.b.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f2988e = "AdasCommonDialog";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public DialogAdasCommonBinding f2989f;

    public class a implements ViewTreeObserver.OnPreDrawListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (AdasCommonDialog.this.f2989f.f2801f.getLineCount() > 2) {
                AdasCommonDialog.this.f2989f.f2801f.setGravity(GravityCompat.START);
            } else {
                AdasCommonDialog.this.f2989f.f2801f.setGravity(17);
            }
            AdasCommonDialog.this.f2989f.f2801f.getViewTreeObserver().removeOnPreDrawListener(this);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean C(View view, MotionEvent motionEvent) {
        this.f2989f.f2797b.getHitRect(new Rect());
        return !r2.contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean A() {
        NoConfigDialogModel noConfigDialogModel = (NoConfigDialogModel) new ViewModelProvider(requireActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(NoConfigDialogModel.class);
        noConfigDialogModel.t(this);
        this.f2989f = (DialogAdasCommonBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_adas_common, this.f2481c, true);
        int layoutDirectionFromLocale = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault());
        if (layoutDirectionFromLocale == 1 && this.f2989f.f2800e.getLayoutDirection() == 0) {
            this.f2989f.f2800e.setLayoutDirection(layoutDirectionFromLocale);
        }
        this.f2989f.c(noConfigDialogModel);
        this.f2989f.f2797b.setOnBtnCallBackListener(this);
        if (noConfigDialogModel.d() > 0) {
            this.f2989f.f2798c.setOnTouchListener(new View.OnTouchListener() { // from class: b.b.g.q.d.t.a
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return this.f1500a.C(view, motionEvent);
                }
            });
        }
        BydLogHelper.logI("AdasCommonDialog", "onCreateView is begin initial bind viewModel");
        if (noConfigDialogModel.g() > 0) {
            noConfigDialogModel.u(noConfigDialogModel.g() == R.string.dipilot_dialog_only_effect_cur_journey);
        } else {
            this.f2989f.f2801f.getViewTreeObserver().addOnPreDrawListener(new a());
        }
        return noConfigDialogModel.j();
    }

    @Override // b.b.k.d.b.a
    public void a() {
        DialogAdasCommonBinding dialogAdasCommonBinding = this.f2989f;
        if (dialogAdasCommonBinding == null || dialogAdasCommonBinding.b() == null) {
            return;
        }
        NoConfigDialogModel noConfigDialogModelB = this.f2989f.b();
        if (noConfigDialogModelB.a() != null) {
            noConfigDialogModelB.a().f(true);
        }
        noConfigDialogModelB.o();
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        NoConfigDialogModel noConfigDialogModelB = this.f2989f.b();
        if (noConfigDialogModelB.c() == R.string.adas_tsr_sensitive_notice) {
            this.f2989f.f2801f.getLayoutParams().width = (int) getResources().getDimension(R.dimen.dp_490);
        }
        if (R.string.adas_itac_tip == noConfigDialogModelB.c()) {
            this.f2989f.f2797b.getLayoutParams().width = -2;
        }
        this.f2481c.setBackgroundResource(R.drawable.ambient_dialog_bg);
    }

    @Override // com.byd.base.view.dialog.BaseBydDialog, android.view.View.OnClickListener
    public void onClick(@NonNull View view) {
        DialogAdasCommonBinding dialogAdasCommonBinding;
        if (view.getId() != R.id.base_dialog_mask || (dialogAdasCommonBinding = this.f2989f) == null) {
            super.onClick(view);
        } else {
            dialogAdasCommonBinding.b().o();
        }
    }

    @Override // com.byd.base.view.dialog.BaseBydDialog, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        DialogAdasCommonBinding dialogAdasCommonBinding = this.f2989f;
        if (dialogAdasCommonBinding == null || dialogAdasCommonBinding.b() == null) {
            return;
        }
        NoConfigDialogModel noConfigDialogModelB = this.f2989f.b();
        if (noConfigDialogModelB.e() != null) {
            noConfigDialogModelB.t(null);
        }
        this.f2989f = null;
    }

    @Override // b.b.k.d.b.a
    public void onFinish() {
        FragmentActivity activity = getActivity();
        if (this.f2989f == null || activity == null || activity.isDestroyed()) {
            return;
        }
        CountDownButton countDownButton = this.f2989f.f2797b;
        countDownButton.setBackground(ContextCompat.getDrawable(activity, R.drawable.selector_dialog_blue));
        countDownButton.setTextColor(activity.getColor(R.color.white));
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment
    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        DialogAdasCommonBinding dialogAdasCommonBinding;
        BydLogHelper.logD("AdasCommonDialog", "onKeyEvent keyCode =" + i + ", action =" + keyEvent.getAction());
        if (4 != i || (dialogAdasCommonBinding = this.f2989f) == null || dialogAdasCommonBinding.b() == null) {
            return super.onKeyEvent(i, keyEvent);
        }
        if (this.f2989f.b().k()) {
            return true;
        }
        return super.onKeyEvent(i, keyEvent);
    }

    @Override // com.byd.base.view.dialog.BaseBydDialog
    public boolean u() {
        DialogAdasCommonBinding dialogAdasCommonBinding = this.f2989f;
        if (dialogAdasCommonBinding == null) {
            return A();
        }
        NoConfigDialogModel noConfigDialogModelB = dialogAdasCommonBinding.b();
        return noConfigDialogModelB == null ? super.u() : noConfigDialogModelB.j();
    }

    @Override // com.byd.base.view.dialog.BaseBydDialog
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public FrameDialogView t(Bundle bundle) {
        return null;
    }
}
