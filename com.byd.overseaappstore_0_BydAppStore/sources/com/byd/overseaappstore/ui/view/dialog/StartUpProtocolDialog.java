package com.byd.overseaappstore.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.DialogStartUpProtocolBinding;
import com.byd.overseaappstore.ui.view.base.BaseDialog;
import com.byd.overseaappstore.ui.view.dialog.StartUpProtocolDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.ct;
import spi.fj;
import spi.gk;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/dialog/StartUpProtocolDialog;", "Lcom/byd/overseaappstore/ui/view/base/BaseDialog;", "Lcom/byd/overseaappstore/databinding/DialogStartUpProtocolBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class StartUpProtocolDialog extends BaseDialog<DialogStartUpProtocolBinding> {
    public static final /* synthetic */ int e = 0;

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final ViewBinding b() {
        DialogStartUpProtocolBinding dialogStartUpProtocolBindingA = DialogStartUpProtocolBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(dialogStartUpProtocolBindingA, "inflate(layoutInflater)");
        return dialogStartUpProtocolBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void c() {
        final DialogStartUpProtocolBinding dialogStartUpProtocolBinding = (DialogStartUpProtocolBinding) this.a;
        if (dialogStartUpProtocolBinding != null) {
            dialogStartUpProtocolBinding.e.setOnClickListener(new gk(this, 4));
            dialogStartUpProtocolBinding.d.setOnClickListener(new fj(this, 7));
            dialogStartUpProtocolBinding.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: spi.ds
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    DialogStartUpProtocolBinding this_apply = dialogStartUpProtocolBinding;
                    StartUpProtocolDialog this$0 = this;
                    int i = StartUpProtocolDialog.e;
                    Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    this_apply.e.setEnabled(z);
                    this$0.i();
                    this_apply.h.setBackground(AppCompatResources.getDrawable(this$0.requireContext(), z ? R.drawable.bg_confirm_dialog_left_button : R.drawable.bg_confirm_dialog_left_button_not));
                }
            });
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void d() {
        Window window;
        Dialog dialog = getDialog();
        WindowManager.LayoutParams attributes = (dialog == null || (window = dialog.getWindow()) == null) ? null : window.getAttributes();
        if (attributes == null) {
            ct.b("IllegalArgumentException window attributes can not be null.", new Object[0]);
            return;
        }
        attributes.width = requireContext().getResources().getDimensionPixelSize(R.dimen.dp_612);
        attributes.height = requireContext().getResources().getDimensionPixelSize(R.dimen.dp_560);
        Dialog dialog2 = getDialog();
        Window window2 = dialog2 != null ? dialog2.getWindow() : null;
        if (window2 != null) {
            window2.setAttributes(attributes);
        }
        Dialog dialog3 = getDialog();
        if (dialog3 != null) {
            dialog3.setCanceledOnTouchOutside(false);
        }
        DialogStartUpProtocolBinding dialogStartUpProtocolBinding = (DialogStartUpProtocolBinding) this.a;
        AppCompatTextView appCompatTextView = dialogStartUpProtocolBinding != null ? dialogStartUpProtocolBinding.c : null;
        if (appCompatTextView != null) {
            appCompatTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
        DialogStartUpProtocolBinding dialogStartUpProtocolBinding2 = (DialogStartUpProtocolBinding) this.a;
        CheckBox checkBox = dialogStartUpProtocolBinding2 != null ? dialogStartUpProtocolBinding2.b : null;
        if (checkBox == null) {
            return;
        }
        checkBox.setChecked(false);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void e(@NotNull Configuration newConfig) {
        AppCompatTextView appCompatTextView;
        AppCompatTextView appCompatTextView2;
        AppCompatTextView appCompatTextView3;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        DialogStartUpProtocolBinding dialogStartUpProtocolBinding = (DialogStartUpProtocolBinding) this.a;
        View view = dialogStartUpProtocolBinding != null ? dialogStartUpProtocolBinding.j : null;
        if (view != null) {
            vm vmVar = vm.a;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            view.setBackground(vmVar.c(contextRequireContext, R.drawable.bg_confirm_dialog_background));
        }
        DialogStartUpProtocolBinding dialogStartUpProtocolBinding2 = (DialogStartUpProtocolBinding) this.a;
        View view2 = dialogStartUpProtocolBinding2 != null ? dialogStartUpProtocolBinding2.h : null;
        if (view2 != null) {
            vm vmVar2 = vm.a;
            Context contextRequireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
            view2.setBackground(vmVar2.c(contextRequireContext2, R.drawable.bg_confirm_dialog_left_button));
        }
        DialogStartUpProtocolBinding dialogStartUpProtocolBinding3 = (DialogStartUpProtocolBinding) this.a;
        View view3 = dialogStartUpProtocolBinding3 != null ? dialogStartUpProtocolBinding3.i : null;
        if (view3 != null) {
            vm vmVar3 = vm.a;
            Context contextRequireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
            view3.setBackground(vmVar3.c(contextRequireContext3, R.drawable.bg_confirm_dialog_right_button));
        }
        DialogStartUpProtocolBinding dialogStartUpProtocolBinding4 = (DialogStartUpProtocolBinding) this.a;
        if (dialogStartUpProtocolBinding4 != null && (appCompatTextView3 = dialogStartUpProtocolBinding4.g) != null) {
            vm vmVar4 = vm.a;
            Context contextRequireContext4 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
            appCompatTextView3.setTextColor(vmVar4.b(contextRequireContext4, R.color.dialog_content_text));
        }
        DialogStartUpProtocolBinding dialogStartUpProtocolBinding5 = (DialogStartUpProtocolBinding) this.a;
        if (dialogStartUpProtocolBinding5 != null && (appCompatTextView2 = dialogStartUpProtocolBinding5.d) != null) {
            vm vmVar5 = vm.a;
            Context contextRequireContext5 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext5, "requireContext()");
            appCompatTextView2.setTextColor(vmVar5.b(contextRequireContext5, R.color.dialog_negative_text));
        }
        DialogStartUpProtocolBinding dialogStartUpProtocolBinding6 = (DialogStartUpProtocolBinding) this.a;
        if (dialogStartUpProtocolBinding6 != null && (appCompatTextView = dialogStartUpProtocolBinding6.c) != null) {
            vm vmVar6 = vm.a;
            Context contextRequireContext6 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext6, "requireContext()");
            appCompatTextView.setTextColor(vmVar6.b(contextRequireContext6, R.color.dialog_content_text));
        }
        i();
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void f(@NotNull Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void g() {
        DialogStartUpProtocolBinding dialogStartUpProtocolBinding = (DialogStartUpProtocolBinding) this.a;
        if (dialogStartUpProtocolBinding != null) {
            ConstraintLayout rootDialogProtocol = dialogStartUpProtocolBinding.f;
            Intrinsics.checkNotNullExpressionValue(rootDialogProtocol, "rootDialogProtocol");
            View vDialogProtocolConfirmButton = dialogStartUpProtocolBinding.h;
            Intrinsics.checkNotNullExpressionValue(vDialogProtocolConfirmButton, "vDialogProtocolConfirmButton");
            View vDialogProtocolExitButton = dialogStartUpProtocolBinding.i;
            Intrinsics.checkNotNullExpressionValue(vDialogProtocolExitButton, "vDialogProtocolExitButton");
            View vProtocolDialog = dialogStartUpProtocolBinding.j;
            Intrinsics.checkNotNullExpressionValue(vProtocolDialog, "vProtocolDialog");
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(rootDialogProtocol);
            constraintSet.clear(vDialogProtocolConfirmButton.getId(), 1);
            constraintSet.clear(vDialogProtocolConfirmButton.getId(), 2);
            constraintSet.clear(vDialogProtocolExitButton.getId(), 1);
            constraintSet.clear(vDialogProtocolExitButton.getId(), 2);
            constraintSet.connect(vDialogProtocolConfirmButton.getId(), 2, vProtocolDialog.getId(), 2, requireContext().getResources().getDimensionPixelSize(R.dimen.dp_80));
            constraintSet.connect(vDialogProtocolExitButton.getId(), 1, vProtocolDialog.getId(), 1, requireContext().getResources().getDimensionPixelSize(R.dimen.dp_80));
            constraintSet.applyTo(rootDialogProtocol);
        }
    }

    public final void i() {
        CheckBox checkBox;
        vm vmVar;
        Context contextRequireContext;
        int i;
        AppCompatTextView appCompatTextView;
        AppCompatTextView appCompatTextView2;
        CheckBox checkBox2;
        DialogStartUpProtocolBinding dialogStartUpProtocolBinding = (DialogStartUpProtocolBinding) this.a;
        Boolean boolValueOf = (dialogStartUpProtocolBinding == null || (checkBox2 = dialogStartUpProtocolBinding.b) == null) ? null : Boolean.valueOf(checkBox2.isChecked());
        if (Intrinsics.areEqual(boolValueOf, Boolean.TRUE)) {
            DialogStartUpProtocolBinding dialogStartUpProtocolBinding2 = (DialogStartUpProtocolBinding) this.a;
            if (dialogStartUpProtocolBinding2 != null && (appCompatTextView2 = dialogStartUpProtocolBinding2.e) != null) {
                vm vmVar2 = vm.a;
                Context contextRequireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
                appCompatTextView2.setTextColor(vmVar2.b(contextRequireContext2, R.color.positive_protocol_dialog_positive_enable));
            }
            DialogStartUpProtocolBinding dialogStartUpProtocolBinding3 = (DialogStartUpProtocolBinding) this.a;
            if (dialogStartUpProtocolBinding3 == null || (checkBox = dialogStartUpProtocolBinding3.b) == null) {
                return;
            }
            vmVar = vm.a;
            contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            i = R.color.positive_protocol_dialog_checkbox_checked;
        } else {
            if (!Intrinsics.areEqual(boolValueOf, Boolean.FALSE)) {
                return;
            }
            DialogStartUpProtocolBinding dialogStartUpProtocolBinding4 = (DialogStartUpProtocolBinding) this.a;
            if (dialogStartUpProtocolBinding4 != null && (appCompatTextView = dialogStartUpProtocolBinding4.e) != null) {
                vm vmVar3 = vm.a;
                Context contextRequireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
                appCompatTextView.setTextColor(vmVar3.b(contextRequireContext3, R.color.positive_protocol_dialog_positive_not_enable));
            }
            DialogStartUpProtocolBinding dialogStartUpProtocolBinding5 = (DialogStartUpProtocolBinding) this.a;
            if (dialogStartUpProtocolBinding5 == null || (checkBox = dialogStartUpProtocolBinding5.b) == null) {
                return;
            }
            vmVar = vm.a;
            contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            i = R.color.positive_protocol_dialog_checkbox_not_checked;
        }
        checkBox.setTextColor(vmVar.b(contextRequireContext, i));
    }
}
