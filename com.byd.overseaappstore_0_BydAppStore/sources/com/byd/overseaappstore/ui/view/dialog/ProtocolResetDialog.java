package com.byd.overseaappstore.ui.view.dialog;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.DialogTwoButtonConfirmBinding;
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
import spi.ct;
import spi.d;
import spi.fj;
import spi.gk;
import spi.hk;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/dialog/ProtocolResetDialog;", "Lcom/byd/overseaappstore/ui/view/base/BaseDialog;", "Lcom/byd/overseaappstore/databinding/DialogTwoButtonConfirmBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class ProtocolResetDialog extends BaseDialog<DialogTwoButtonConfirmBinding> {
    public static final /* synthetic */ int k = 0;

    @Nullable
    public Function1<? super View, Unit> e;

    @NotNull
    public String f = "";

    @NotNull
    public String g = "";

    @NotNull
    public String h = "";

    @NotNull
    public String i = "";

    @NotNull
    public final hk j = new hk(this, 2);

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final ViewBinding b() {
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBindingA = DialogTwoButtonConfirmBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(dialogTwoButtonConfirmBindingA, "inflate(layoutInflater)");
        return dialogTwoButtonConfirmBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void c() {
        SkinView skinView;
        SkinView skinView2;
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding = (DialogTwoButtonConfirmBinding) this.a;
        if (dialogTwoButtonConfirmBinding != null && (skinView2 = dialogTwoButtonConfirmBinding.i) != null) {
            skinView2.setOnClickListener(new gk(this, 2));
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding2 = (DialogTwoButtonConfirmBinding) this.a;
        if (dialogTwoButtonConfirmBinding2 == null || (skinView = dialogTwoButtonConfirmBinding2.h) == null) {
            return;
        }
        skinView.setOnClickListener(new fj(this, 5));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void d() {
        SkinAppCompatTextView skinAppCompatTextView;
        ViewTreeObserver viewTreeObserver;
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding = (DialogTwoButtonConfirmBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView2 = dialogTwoButtonConfirmBinding != null ? dialogTwoButtonConfirmBinding.g : null;
        if (skinAppCompatTextView2 != null) {
            skinAppCompatTextView2.setText(this.f);
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding2 = (DialogTwoButtonConfirmBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView3 = dialogTwoButtonConfirmBinding2 != null ? dialogTwoButtonConfirmBinding2.b : null;
        if (skinAppCompatTextView3 != null) {
            skinAppCompatTextView3.setText(this.g);
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding3 = (DialogTwoButtonConfirmBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView4 = dialogTwoButtonConfirmBinding3 != null ? dialogTwoButtonConfirmBinding3.e : null;
        if (skinAppCompatTextView4 != null) {
            skinAppCompatTextView4.setText(this.h);
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding4 = (DialogTwoButtonConfirmBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView5 = dialogTwoButtonConfirmBinding4 != null ? dialogTwoButtonConfirmBinding4.d : null;
        if (skinAppCompatTextView5 != null) {
            skinAppCompatTextView5.setText(this.i);
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding5 = (DialogTwoButtonConfirmBinding) this.a;
        if (dialogTwoButtonConfirmBinding5 == null || (skinAppCompatTextView = dialogTwoButtonConfirmBinding5.b) == null || (viewTreeObserver = skinAppCompatTextView.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnPreDrawListener(this.j);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void e(@NotNull Configuration newConfig) {
        SkinAppCompatTextView skinAppCompatTextView;
        SkinAppCompatTextView skinAppCompatTextView2;
        SkinAppCompatTextView skinAppCompatTextView3;
        SkinAppCompatTextView skinAppCompatTextView4;
        SkinAppCompatTextView skinAppCompatTextView5;
        SkinAppCompatTextView skinAppCompatTextView6;
        SkinAppCompatTextView skinAppCompatTextView7;
        SkinAppCompatTextView skinAppCompatTextView8;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        av avVar = av.a;
        getContext();
        boolean zE = avVar.e();
        if (!zE) {
            if (zE) {
                return;
            }
            DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding = (DialogTwoButtonConfirmBinding) this.a;
            SkinView skinView = dialogTwoButtonConfirmBinding != null ? dialogTwoButtonConfirmBinding.c : null;
            if (skinView != null) {
                vm vmVar = vm.a;
                Context contextRequireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                skinView.setBackground(vmVar.c(contextRequireContext, R.drawable.bg_confirm_dialog_background));
            }
            DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding2 = (DialogTwoButtonConfirmBinding) this.a;
            SkinView skinView2 = dialogTwoButtonConfirmBinding2 != null ? dialogTwoButtonConfirmBinding2.i : null;
            if (skinView2 != null) {
                vm vmVar2 = vm.a;
                Context contextRequireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
                skinView2.setBackground(vmVar2.c(contextRequireContext2, R.drawable.common_dialog_start_button));
            }
            DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding3 = (DialogTwoButtonConfirmBinding) this.a;
            SkinView skinView3 = dialogTwoButtonConfirmBinding3 != null ? dialogTwoButtonConfirmBinding3.h : null;
            if (skinView3 != null) {
                vm vmVar3 = vm.a;
                Context contextRequireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
                skinView3.setBackground(vmVar3.c(contextRequireContext3, R.drawable.bg_confirm_dialog_right_button));
            }
            DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding4 = (DialogTwoButtonConfirmBinding) this.a;
            SkinView skinView4 = dialogTwoButtonConfirmBinding4 != null ? dialogTwoButtonConfirmBinding4.j : null;
            if (skinView4 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_over_line, null), skinView4);
            }
            DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding5 = (DialogTwoButtonConfirmBinding) this.a;
            if (dialogTwoButtonConfirmBinding5 != null && (skinAppCompatTextView4 = dialogTwoButtonConfirmBinding5.g) != null) {
                skinAppCompatTextView4.setStyleTextColor(R.color.dialog_content_text);
            }
            DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding6 = (DialogTwoButtonConfirmBinding) this.a;
            if (dialogTwoButtonConfirmBinding6 != null && (skinAppCompatTextView3 = dialogTwoButtonConfirmBinding6.b) != null) {
                skinAppCompatTextView3.setStyleTextColor(R.color.dialog_content_text);
            }
            DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding7 = (DialogTwoButtonConfirmBinding) this.a;
            if (dialogTwoButtonConfirmBinding7 != null && (skinAppCompatTextView2 = dialogTwoButtonConfirmBinding7.e) != null) {
                skinAppCompatTextView2.setStyleTextColor(R.color.positive_protocol_dialog_positive_enable);
            }
            DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding8 = (DialogTwoButtonConfirmBinding) this.a;
            if (dialogTwoButtonConfirmBinding8 != null && (skinAppCompatTextView = dialogTwoButtonConfirmBinding8.d) != null) {
                skinAppCompatTextView.setStyleTextColor(R.color.dialog_negative_text);
            }
            ct.a("txs 左舵", new Object[0]);
            return;
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding9 = (DialogTwoButtonConfirmBinding) this.a;
        SkinView skinView5 = dialogTwoButtonConfirmBinding9 != null ? dialogTwoButtonConfirmBinding9.c : null;
        if (skinView5 != null) {
            vm vmVar4 = vm.a;
            Context contextRequireContext4 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
            skinView5.setBackground(vmVar4.c(contextRequireContext4, R.drawable.bg_confirm_dialog_background));
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding10 = (DialogTwoButtonConfirmBinding) this.a;
        SkinView skinView6 = dialogTwoButtonConfirmBinding10 != null ? dialogTwoButtonConfirmBinding10.i : null;
        if (skinView6 != null) {
            vm vmVar5 = vm.a;
            Context contextRequireContext5 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext5, "requireContext()");
            skinView6.setBackground(vmVar5.c(contextRequireContext5, R.drawable.common_dialog_start_button));
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding11 = (DialogTwoButtonConfirmBinding) this.a;
        SkinView skinView7 = dialogTwoButtonConfirmBinding11 != null ? dialogTwoButtonConfirmBinding11.h : null;
        if (skinView7 != null) {
            vm vmVar6 = vm.a;
            Context contextRequireContext6 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext6, "requireContext()");
            skinView7.setBackground(vmVar6.c(contextRequireContext6, R.drawable.bg_confirm_dialog_right_button));
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding12 = (DialogTwoButtonConfirmBinding) this.a;
        SkinView skinView8 = dialogTwoButtonConfirmBinding12 != null ? dialogTwoButtonConfirmBinding12.j : null;
        if (skinView8 != null) {
            d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_over_line, null), skinView8);
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding13 = (DialogTwoButtonConfirmBinding) this.a;
        SkinView skinView9 = dialogTwoButtonConfirmBinding13 != null ? dialogTwoButtonConfirmBinding13.k : null;
        if (skinView9 != null) {
            d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_center_line, null), skinView9);
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding14 = (DialogTwoButtonConfirmBinding) this.a;
        if (dialogTwoButtonConfirmBinding14 != null && (skinAppCompatTextView8 = dialogTwoButtonConfirmBinding14.g) != null) {
            skinAppCompatTextView8.setStyleTextColor(R.color.dialog_content_text);
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding15 = (DialogTwoButtonConfirmBinding) this.a;
        if (dialogTwoButtonConfirmBinding15 != null && (skinAppCompatTextView7 = dialogTwoButtonConfirmBinding15.b) != null) {
            skinAppCompatTextView7.setStyleTextColor(R.color.dialog_content_text);
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding16 = (DialogTwoButtonConfirmBinding) this.a;
        if (dialogTwoButtonConfirmBinding16 != null && (skinAppCompatTextView6 = dialogTwoButtonConfirmBinding16.e) != null) {
            skinAppCompatTextView6.setStyleTextColor(R.color.positive_protocol_dialog_positive_enable);
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding17 = (DialogTwoButtonConfirmBinding) this.a;
        if (dialogTwoButtonConfirmBinding17 != null && (skinAppCompatTextView5 = dialogTwoButtonConfirmBinding17.d) != null) {
            skinAppCompatTextView5.setStyleTextColor(R.color.dialog_negative_text);
        }
        ct.a("txs 右舵", new Object[0]);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void f(@NotNull Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding = (DialogTwoButtonConfirmBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView = dialogTwoButtonConfirmBinding != null ? dialogTwoButtonConfirmBinding.g : null;
        if (skinAppCompatTextView != null) {
            vm vmVar = vm.a;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            skinAppCompatTextView.setText(vmVar.e(contextRequireContext, R.string.protocol_dialog_end_button_reset));
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding2 = (DialogTwoButtonConfirmBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView2 = dialogTwoButtonConfirmBinding2 != null ? dialogTwoButtonConfirmBinding2.b : null;
        if (skinAppCompatTextView2 != null) {
            vm vmVar2 = vm.a;
            Context contextRequireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
            skinAppCompatTextView2.setText(vmVar2.e(contextRequireContext2, R.string.protocol_dialog_reset_content));
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding3 = (DialogTwoButtonConfirmBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView3 = dialogTwoButtonConfirmBinding3 != null ? dialogTwoButtonConfirmBinding3.e : null;
        if (skinAppCompatTextView3 != null) {
            vm vmVar3 = vm.a;
            Context contextRequireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
            skinAppCompatTextView3.setText(vmVar3.e(contextRequireContext3, R.string.protocol_dialog_end_button_reset));
        }
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding4 = (DialogTwoButtonConfirmBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView4 = dialogTwoButtonConfirmBinding4 != null ? dialogTwoButtonConfirmBinding4.d : null;
        if (skinAppCompatTextView4 == null) {
            return;
        }
        vm vmVar4 = vm.a;
        Context contextRequireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
        skinAppCompatTextView4.setText(vmVar4.e(contextRequireContext4, R.string.tv_setting_dialog_cancel));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void g() {
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding = (DialogTwoButtonConfirmBinding) this.a;
        if (dialogTwoButtonConfirmBinding != null) {
            ConstraintLayout rootDialogTwoButtonConfirm = dialogTwoButtonConfirmBinding.f;
            Intrinsics.checkNotNullExpressionValue(rootDialogTwoButtonConfirm, "rootDialogTwoButtonConfirm");
            SkinView vDialogTwoButtonPositiveButton = dialogTwoButtonConfirmBinding.i;
            Intrinsics.checkNotNullExpressionValue(vDialogTwoButtonPositiveButton, "vDialogTwoButtonPositiveButton");
            SkinView vDialogTwoButtonNegativeButton = dialogTwoButtonConfirmBinding.h;
            Intrinsics.checkNotNullExpressionValue(vDialogTwoButtonNegativeButton, "vDialogTwoButtonNegativeButton");
            SkinView dialogBackground = dialogTwoButtonConfirmBinding.c;
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
        DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding = (DialogTwoButtonConfirmBinding) this.a;
        if (dialogTwoButtonConfirmBinding == null || (skinAppCompatTextView = dialogTwoButtonConfirmBinding.b) == null || (viewTreeObserver = skinAppCompatTextView.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.removeOnPreDrawListener(this.j);
    }
}
