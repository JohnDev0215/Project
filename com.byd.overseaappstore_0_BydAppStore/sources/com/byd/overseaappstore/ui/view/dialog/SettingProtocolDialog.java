package com.byd.overseaappstore.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.DialogSettingProtocolBinding;
import com.byd.overseaappstore.ui.view.base.BaseDialog;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.ct;
import spi.d;
import spi.fj;
import spi.vm;
import spi.y6;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/dialog/SettingProtocolDialog;", "Lcom/byd/overseaappstore/ui/view/base/BaseDialog;", "Lcom/byd/overseaappstore/databinding/DialogSettingProtocolBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SettingProtocolDialog extends BaseDialog<DialogSettingProtocolBinding> {
    public static final /* synthetic */ int e = 0;

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final ViewBinding b() {
        DialogSettingProtocolBinding dialogSettingProtocolBindingA = DialogSettingProtocolBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(dialogSettingProtocolBindingA, "inflate(layoutInflater)");
        return dialogSettingProtocolBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void c() {
        SkinAppCompatTextView skinAppCompatTextView;
        SkinAppCompatTextView skinAppCompatTextView2;
        DialogSettingProtocolBinding dialogSettingProtocolBinding = (DialogSettingProtocolBinding) this.a;
        if (dialogSettingProtocolBinding != null && (skinAppCompatTextView2 = dialogSettingProtocolBinding.d) != null) {
            skinAppCompatTextView2.setOnClickListener(new fj(this, 6));
        }
        DialogSettingProtocolBinding dialogSettingProtocolBinding2 = (DialogSettingProtocolBinding) this.a;
        if (dialogSettingProtocolBinding2 == null || (skinAppCompatTextView = dialogSettingProtocolBinding2.c) == null) {
            return;
        }
        skinAppCompatTextView.setOnClickListener(new y6(this, 2));
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
        DialogSettingProtocolBinding dialogSettingProtocolBinding = (DialogSettingProtocolBinding) this.a;
        SkinAppCompatTextView skinAppCompatTextView = dialogSettingProtocolBinding != null ? dialogSettingProtocolBinding.b : null;
        if (skinAppCompatTextView == null) {
            return;
        }
        skinAppCompatTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void e(@NotNull Configuration newConfig) {
        SkinAppCompatTextView skinAppCompatTextView;
        SkinAppCompatTextView skinAppCompatTextView2;
        SkinAppCompatTextView skinAppCompatTextView3;
        SkinAppCompatTextView skinAppCompatTextView4;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        DialogSettingProtocolBinding dialogSettingProtocolBinding = (DialogSettingProtocolBinding) this.a;
        SkinView skinView = dialogSettingProtocolBinding != null ? dialogSettingProtocolBinding.j : null;
        if (skinView != null) {
            vm vmVar = vm.a;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            skinView.setBackground(vmVar.c(contextRequireContext, R.drawable.bg_confirm_dialog_background));
        }
        DialogSettingProtocolBinding dialogSettingProtocolBinding2 = (DialogSettingProtocolBinding) this.a;
        SkinView skinView2 = dialogSettingProtocolBinding2 != null ? dialogSettingProtocolBinding2.g : null;
        if (skinView2 != null) {
            vm vmVar2 = vm.a;
            Context contextRequireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
            skinView2.setBackground(vmVar2.c(contextRequireContext2, R.drawable.bg_confirm_dialog_left_button));
        }
        DialogSettingProtocolBinding dialogSettingProtocolBinding3 = (DialogSettingProtocolBinding) this.a;
        SkinView skinView3 = dialogSettingProtocolBinding3 != null ? dialogSettingProtocolBinding3.h : null;
        if (skinView3 != null) {
            vm vmVar3 = vm.a;
            Context contextRequireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
            skinView3.setBackground(vmVar3.c(contextRequireContext3, R.drawable.bg_confirm_dialog_right_button));
        }
        DialogSettingProtocolBinding dialogSettingProtocolBinding4 = (DialogSettingProtocolBinding) this.a;
        SkinView skinView4 = dialogSettingProtocolBinding4 != null ? dialogSettingProtocolBinding4.i : null;
        if (skinView4 != null) {
            d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_center_line, null), skinView4);
        }
        DialogSettingProtocolBinding dialogSettingProtocolBinding5 = (DialogSettingProtocolBinding) this.a;
        if (dialogSettingProtocolBinding5 != null && (skinAppCompatTextView4 = dialogSettingProtocolBinding5.f) != null) {
            skinAppCompatTextView4.setStyleTextColor(R.color.dialog_content_text);
        }
        DialogSettingProtocolBinding dialogSettingProtocolBinding6 = (DialogSettingProtocolBinding) this.a;
        if (dialogSettingProtocolBinding6 != null && (skinAppCompatTextView3 = dialogSettingProtocolBinding6.b) != null) {
            skinAppCompatTextView3.setStyleTextColor(R.color.dialog_content_text);
        }
        DialogSettingProtocolBinding dialogSettingProtocolBinding7 = (DialogSettingProtocolBinding) this.a;
        if (dialogSettingProtocolBinding7 != null && (skinAppCompatTextView2 = dialogSettingProtocolBinding7.d) != null) {
            skinAppCompatTextView2.setStyleTextColor(R.color.positive_protocol_dialog_positive_enable);
        }
        DialogSettingProtocolBinding dialogSettingProtocolBinding8 = (DialogSettingProtocolBinding) this.a;
        if (dialogSettingProtocolBinding8 == null || (skinAppCompatTextView = dialogSettingProtocolBinding8.c) == null) {
            return;
        }
        skinAppCompatTextView.setStyleTextColor(R.color.dialog_negative_text);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void f(@NotNull Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void g() {
        Resources resources;
        Resources resources2;
        DialogSettingProtocolBinding dialogSettingProtocolBinding = (DialogSettingProtocolBinding) this.a;
        if (dialogSettingProtocolBinding != null) {
            ConstraintLayout rootDialogProtocol = dialogSettingProtocolBinding.e;
            Intrinsics.checkNotNullExpressionValue(rootDialogProtocol, "rootDialogProtocol");
            SkinView vDialogProtocolConfirmButton = dialogSettingProtocolBinding.g;
            Intrinsics.checkNotNullExpressionValue(vDialogProtocolConfirmButton, "vDialogProtocolConfirmButton");
            SkinView vDialogProtocolExitButton = dialogSettingProtocolBinding.h;
            Intrinsics.checkNotNullExpressionValue(vDialogProtocolExitButton, "vDialogProtocolExitButton");
            SkinView vProtocolDialog = dialogSettingProtocolBinding.j;
            Intrinsics.checkNotNullExpressionValue(vProtocolDialog, "vProtocolDialog");
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(rootDialogProtocol);
            constraintSet.clear(vDialogProtocolConfirmButton.getId(), 1);
            constraintSet.clear(vDialogProtocolConfirmButton.getId(), 2);
            constraintSet.clear(vDialogProtocolExitButton.getId(), 1);
            constraintSet.clear(vDialogProtocolExitButton.getId(), 2);
            Context context = getContext();
            if (context != null && (resources2 = context.getResources()) != null) {
                Intrinsics.checkNotNullExpressionValue(resources2, "resources");
                constraintSet.connect(vDialogProtocolConfirmButton.getId(), 2, vProtocolDialog.getId(), 2, resources2.getDimensionPixelSize(R.dimen.dp_80));
            }
            Context context2 = getContext();
            if (context2 != null && (resources = context2.getResources()) != null) {
                Intrinsics.checkNotNullExpressionValue(resources, "resources");
                constraintSet.connect(vDialogProtocolExitButton.getId(), 1, vProtocolDialog.getId(), 1, resources.getDimensionPixelSize(R.dimen.dp_80));
            }
            constraintSet.applyTo(rootDialogProtocol);
        }
    }
}
