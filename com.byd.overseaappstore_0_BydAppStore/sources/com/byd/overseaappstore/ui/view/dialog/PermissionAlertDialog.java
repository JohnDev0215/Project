package com.byd.overseaappstore.ui.view.dialog;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.DialogPermissionAlertBinding;
import com.byd.overseaappstore.ui.view.base.BaseDialog;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.av;
import spi.fj;
import spi.gk;
import spi.hk;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/dialog/PermissionAlertDialog;", "Lcom/byd/overseaappstore/ui/view/base/BaseDialog;", "Lcom/byd/overseaappstore/databinding/DialogPermissionAlertBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class PermissionAlertDialog extends BaseDialog<DialogPermissionAlertBinding> {
    public static final /* synthetic */ int l = 0;

    @Nullable
    public Function1<? super View, Unit> e;

    @Nullable
    public Function1<? super View, Unit> f;

    @NotNull
    public String g = "";

    @NotNull
    public String h = "";

    @NotNull
    public String i = "";

    @NotNull
    public String j = "";

    @NotNull
    public final hk k = new hk(this, 1);

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final ViewBinding b() {
        DialogPermissionAlertBinding dialogPermissionAlertBindingA = DialogPermissionAlertBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(dialogPermissionAlertBindingA, "inflate(layoutInflater)");
        return dialogPermissionAlertBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void c() {
        SkinView skinView;
        SkinView skinView2;
        DialogPermissionAlertBinding dialogPermissionAlertBinding = (DialogPermissionAlertBinding) this.a;
        if (dialogPermissionAlertBinding != null && (skinView2 = dialogPermissionAlertBinding.i) != null) {
            skinView2.setOnClickListener(new gk(this, 1));
        }
        DialogPermissionAlertBinding dialogPermissionAlertBinding2 = (DialogPermissionAlertBinding) this.a;
        if (dialogPermissionAlertBinding2 == null || (skinView = dialogPermissionAlertBinding2.h) == null) {
            return;
        }
        skinView.setOnClickListener(new fj(this, 4));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void d() {
        SkinAppCompatTextView skinAppCompatTextView;
        ViewTreeObserver viewTreeObserver;
        DialogPermissionAlertBinding dialogPermissionAlertBinding = (DialogPermissionAlertBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView2 = dialogPermissionAlertBinding != null ? dialogPermissionAlertBinding.g : null;
        if (skinAppCompatTextView2 != null) {
            skinAppCompatTextView2.setText(this.g);
        }
        DialogPermissionAlertBinding dialogPermissionAlertBinding2 = (DialogPermissionAlertBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView3 = dialogPermissionAlertBinding2 != null ? dialogPermissionAlertBinding2.b : null;
        if (skinAppCompatTextView3 != null) {
            skinAppCompatTextView3.setText(this.h);
        }
        DialogPermissionAlertBinding dialogPermissionAlertBinding3 = (DialogPermissionAlertBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView4 = dialogPermissionAlertBinding3 != null ? dialogPermissionAlertBinding3.e : null;
        if (skinAppCompatTextView4 != null) {
            skinAppCompatTextView4.setText(this.i);
        }
        DialogPermissionAlertBinding dialogPermissionAlertBinding4 = (DialogPermissionAlertBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView5 = dialogPermissionAlertBinding4 != null ? dialogPermissionAlertBinding4.d : null;
        if (skinAppCompatTextView5 != null) {
            skinAppCompatTextView5.setText(this.j);
        }
        DialogPermissionAlertBinding dialogPermissionAlertBinding5 = (DialogPermissionAlertBinding) this.a;
        if (dialogPermissionAlertBinding5 == null || (skinAppCompatTextView = dialogPermissionAlertBinding5.b) == null || (viewTreeObserver = skinAppCompatTextView.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnPreDrawListener(this.k);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void e(@NotNull Configuration newConfig) {
        SkinView skinView;
        SkinAppCompatTextView skinAppCompatTextView;
        SkinAppCompatTextView skinAppCompatTextView2;
        SkinAppCompatTextView skinAppCompatTextView3;
        SkinAppCompatTextView skinAppCompatTextView4;
        SkinAppCompatTextView skinAppCompatTextView5;
        SkinAppCompatTextView skinAppCompatTextView6;
        SkinAppCompatTextView skinAppCompatTextView7;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        av avVar = av.a;
        getContext();
        boolean zE = avVar.e();
        if (zE) {
            DialogPermissionAlertBinding dialogPermissionAlertBinding = (DialogPermissionAlertBinding) this.a;
            SkinView skinView2 = dialogPermissionAlertBinding != null ? dialogPermissionAlertBinding.c : null;
            if (skinView2 != null) {
                vm vmVar = vm.a;
                Context contextRequireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                skinView2.setBackground(vmVar.c(contextRequireContext, R.drawable.bg_confirm_dialog_background));
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding2 = (DialogPermissionAlertBinding) this.a;
            SkinView skinView3 = dialogPermissionAlertBinding2 != null ? dialogPermissionAlertBinding2.i : null;
            if (skinView3 != null) {
                vm vmVar2 = vm.a;
                Context contextRequireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
                skinView3.setBackground(vmVar2.c(contextRequireContext2, R.drawable.common_dialog_start_button));
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding3 = (DialogPermissionAlertBinding) this.a;
            SkinView skinView4 = dialogPermissionAlertBinding3 != null ? dialogPermissionAlertBinding3.h : null;
            if (skinView4 != null) {
                vm vmVar3 = vm.a;
                Context contextRequireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
                skinView4.setBackground(vmVar3.c(contextRequireContext3, R.drawable.bg_confirm_dialog_right_button));
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding4 = (DialogPermissionAlertBinding) this.a;
            skinView = dialogPermissionAlertBinding4 != null ? dialogPermissionAlertBinding4.j : null;
            if (skinView != null) {
                vm vmVar4 = vm.a;
                Context contextRequireContext4 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
                skinView.setBackground(vmVar4.c(contextRequireContext4, R.color.setting_over_line));
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding5 = (DialogPermissionAlertBinding) this.a;
            if (dialogPermissionAlertBinding5 != null && (skinAppCompatTextView7 = dialogPermissionAlertBinding5.g) != null) {
                skinAppCompatTextView7.setStyleTextColor(R.color.dialog_content_text);
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding6 = (DialogPermissionAlertBinding) this.a;
            if (dialogPermissionAlertBinding6 != null && (skinAppCompatTextView6 = dialogPermissionAlertBinding6.b) != null) {
                skinAppCompatTextView6.setStyleTextColor(R.color.dialog_content_text);
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding7 = (DialogPermissionAlertBinding) this.a;
            if (dialogPermissionAlertBinding7 != null && (skinAppCompatTextView5 = dialogPermissionAlertBinding7.e) != null) {
                skinAppCompatTextView5.setStyleTextColor(R.color.positive_protocol_dialog_positive_enable);
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding8 = (DialogPermissionAlertBinding) this.a;
            if (dialogPermissionAlertBinding8 == null || (skinAppCompatTextView = dialogPermissionAlertBinding8.d) == null) {
                return;
            }
        } else {
            if (zE) {
                return;
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding9 = (DialogPermissionAlertBinding) this.a;
            SkinView skinView5 = dialogPermissionAlertBinding9 != null ? dialogPermissionAlertBinding9.c : null;
            if (skinView5 != null) {
                vm vmVar5 = vm.a;
                Context contextRequireContext5 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext5, "requireContext()");
                skinView5.setBackground(vmVar5.c(contextRequireContext5, R.drawable.bg_confirm_dialog_background));
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding10 = (DialogPermissionAlertBinding) this.a;
            SkinView skinView6 = dialogPermissionAlertBinding10 != null ? dialogPermissionAlertBinding10.i : null;
            if (skinView6 != null) {
                vm vmVar6 = vm.a;
                Context contextRequireContext6 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext6, "requireContext()");
                skinView6.setBackground(vmVar6.c(contextRequireContext6, R.drawable.common_dialog_start_button));
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding11 = (DialogPermissionAlertBinding) this.a;
            SkinView skinView7 = dialogPermissionAlertBinding11 != null ? dialogPermissionAlertBinding11.h : null;
            if (skinView7 != null) {
                vm vmVar7 = vm.a;
                Context contextRequireContext7 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext7, "requireContext()");
                skinView7.setBackground(vmVar7.c(contextRequireContext7, R.drawable.bg_confirm_dialog_right_button));
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding12 = (DialogPermissionAlertBinding) this.a;
            skinView = dialogPermissionAlertBinding12 != null ? dialogPermissionAlertBinding12.j : null;
            if (skinView != null) {
                vm vmVar8 = vm.a;
                Context contextRequireContext8 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext8, "requireContext()");
                skinView.setBackground(vmVar8.c(contextRequireContext8, R.color.setting_over_line));
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding13 = (DialogPermissionAlertBinding) this.a;
            if (dialogPermissionAlertBinding13 != null && (skinAppCompatTextView4 = dialogPermissionAlertBinding13.g) != null) {
                skinAppCompatTextView4.setStyleTextColor(R.color.dialog_content_text);
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding14 = (DialogPermissionAlertBinding) this.a;
            if (dialogPermissionAlertBinding14 != null && (skinAppCompatTextView3 = dialogPermissionAlertBinding14.b) != null) {
                skinAppCompatTextView3.setStyleTextColor(R.color.dialog_content_text);
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding15 = (DialogPermissionAlertBinding) this.a;
            if (dialogPermissionAlertBinding15 != null && (skinAppCompatTextView2 = dialogPermissionAlertBinding15.e) != null) {
                skinAppCompatTextView2.setStyleTextColor(R.color.positive_protocol_dialog_positive_enable);
            }
            DialogPermissionAlertBinding dialogPermissionAlertBinding16 = (DialogPermissionAlertBinding) this.a;
            if (dialogPermissionAlertBinding16 == null || (skinAppCompatTextView = dialogPermissionAlertBinding16.d) == null) {
                return;
            }
        }
        skinAppCompatTextView.setStyleTextColor(R.color.dialog_negative_text);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void f(@NotNull Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        DialogPermissionAlertBinding dialogPermissionAlertBinding = (DialogPermissionAlertBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView = dialogPermissionAlertBinding != null ? dialogPermissionAlertBinding.g : null;
        if (skinAppCompatTextView != null) {
            vm vmVar = vm.a;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            skinAppCompatTextView.setText(vmVar.e(contextRequireContext, R.string.permission_denied_dialog_title));
        }
        DialogPermissionAlertBinding dialogPermissionAlertBinding2 = (DialogPermissionAlertBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView2 = dialogPermissionAlertBinding2 != null ? dialogPermissionAlertBinding2.b : null;
        if (skinAppCompatTextView2 != null) {
            vm vmVar2 = vm.a;
            Context contextRequireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
            skinAppCompatTextView2.setText(vmVar2.e(contextRequireContext2, R.string.permission_denied_dialog_warning_text));
        }
        DialogPermissionAlertBinding dialogPermissionAlertBinding3 = (DialogPermissionAlertBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView3 = dialogPermissionAlertBinding3 != null ? dialogPermissionAlertBinding3.e : null;
        if (skinAppCompatTextView3 != null) {
            vm vmVar3 = vm.a;
            Context contextRequireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
            skinAppCompatTextView3.setText(vmVar3.e(contextRequireContext3, R.string.permission_denied_dialog_start_button_setting));
        }
        DialogPermissionAlertBinding dialogPermissionAlertBinding4 = (DialogPermissionAlertBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView4 = dialogPermissionAlertBinding4 != null ? dialogPermissionAlertBinding4.d : null;
        if (skinAppCompatTextView4 == null) {
            return;
        }
        vm vmVar4 = vm.a;
        Context contextRequireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
        skinAppCompatTextView4.setText(vmVar4.e(contextRequireContext4, R.string.permission_denied_dialog_end_button_cancel));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void g() {
        DialogPermissionAlertBinding dialogPermissionAlertBinding = (DialogPermissionAlertBinding) this.a;
        if (dialogPermissionAlertBinding != null) {
            ConstraintLayout rootDialogTwoButtonConfirm = dialogPermissionAlertBinding.f;
            Intrinsics.checkNotNullExpressionValue(rootDialogTwoButtonConfirm, "rootDialogTwoButtonConfirm");
            SkinView vDialogTwoButtonPositiveButton = dialogPermissionAlertBinding.i;
            Intrinsics.checkNotNullExpressionValue(vDialogTwoButtonPositiveButton, "vDialogTwoButtonPositiveButton");
            SkinView vDialogTwoButtonNegativeButton = dialogPermissionAlertBinding.h;
            Intrinsics.checkNotNullExpressionValue(vDialogTwoButtonNegativeButton, "vDialogTwoButtonNegativeButton");
            SkinView dialogBackground = dialogPermissionAlertBinding.c;
            Intrinsics.checkNotNullExpressionValue(dialogBackground, "dialogBackground");
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(rootDialogTwoButtonConfirm);
            constraintSet.clear(vDialogTwoButtonPositiveButton.getId(), 1);
            constraintSet.clear(vDialogTwoButtonPositiveButton.getId(), 2);
            constraintSet.clear(vDialogTwoButtonNegativeButton.getId(), 1);
            constraintSet.clear(vDialogTwoButtonNegativeButton.getId(), 2);
            constraintSet.connect(vDialogTwoButtonNegativeButton.getId(), 1, dialogBackground.getId(), 1, requireContext().getResources().getDimensionPixelSize(R.dimen.dp_80));
            constraintSet.connect(vDialogTwoButtonPositiveButton.getId(), 2, dialogBackground.getId(), 2, requireContext().getResources().getDimensionPixelSize(R.dimen.dp_80));
            constraintSet.applyTo(rootDialogTwoButtonConfirm);
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onDestroyView() {
        SkinAppCompatTextView skinAppCompatTextView;
        ViewTreeObserver viewTreeObserver;
        super.onDestroyView();
        DialogPermissionAlertBinding dialogPermissionAlertBinding = (DialogPermissionAlertBinding) this.a;
        if (dialogPermissionAlertBinding == null || (skinAppCompatTextView = dialogPermissionAlertBinding.b) == null || (viewTreeObserver = skinAppCompatTextView.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.removeOnPreDrawListener(this.k);
    }
}
