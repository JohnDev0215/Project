package com.byd.overseaappstore.ui.view.dialog;

import android.content.Context;
import android.content.res.Configuration;
import android.view.ViewTreeObserver;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.DialogOneButtonConfirmBinding;
import com.byd.overseaappstore.ui.view.base.BaseDialog;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.fj;
import spi.vm;
import spi.z6;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/dialog/StorageLeakDialog;", "Lcom/byd/overseaappstore/ui/view/base/BaseDialog;", "Lcom/byd/overseaappstore/databinding/DialogOneButtonConfirmBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class StorageLeakDialog extends BaseDialog<DialogOneButtonConfirmBinding> {
    public static final /* synthetic */ int h = 0;

    @NotNull
    public String e = "";

    @NotNull
    public String f = "";

    @NotNull
    public final z6 g = new z6(this, 2);

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final ViewBinding b() {
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBindingA = DialogOneButtonConfirmBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(dialogOneButtonConfirmBindingA, "inflate(layoutInflater)");
        return dialogOneButtonConfirmBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void c() {
        SkinView skinView;
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding = (DialogOneButtonConfirmBinding) this.a;
        if (dialogOneButtonConfirmBinding == null || (skinView = dialogOneButtonConfirmBinding.d) == null) {
            return;
        }
        skinView.setOnClickListener(new fj(this, 8));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void d() {
        SkinTextView skinTextView;
        ViewTreeObserver viewTreeObserver;
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding = (DialogOneButtonConfirmBinding) this.a;
        SkinTextView skinTextView2 = dialogOneButtonConfirmBinding != null ? dialogOneButtonConfirmBinding.b : null;
        if (skinTextView2 != null) {
            skinTextView2.setText(this.e);
        }
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding2 = (DialogOneButtonConfirmBinding) this.a;
        SkinTextView skinTextView3 = dialogOneButtonConfirmBinding2 != null ? dialogOneButtonConfirmBinding2.c : null;
        if (skinTextView3 != null) {
            skinTextView3.setText(this.f);
        }
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding3 = (DialogOneButtonConfirmBinding) this.a;
        if (dialogOneButtonConfirmBinding3 == null || (skinTextView = dialogOneButtonConfirmBinding3.b) == null || (viewTreeObserver = skinTextView.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnPreDrawListener(this.g);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void e(@NotNull Configuration newConfig) {
        SkinTextView skinTextView;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding = (DialogOneButtonConfirmBinding) this.a;
        SkinView skinView = dialogOneButtonConfirmBinding != null ? dialogOneButtonConfirmBinding.e : null;
        if (skinView != null) {
            vm vmVar = vm.a;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            skinView.setBackground(vmVar.c(contextRequireContext, R.drawable.bg_common_dialog));
        }
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding2 = (DialogOneButtonConfirmBinding) this.a;
        SkinView skinView2 = dialogOneButtonConfirmBinding2 != null ? dialogOneButtonConfirmBinding2.d : null;
        if (skinView2 != null) {
            vm vmVar2 = vm.a;
            Context contextRequireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
            skinView2.setBackground(vmVar2.c(contextRequireContext2, R.drawable.bg_confirm_dialog_one_button));
        }
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding3 = (DialogOneButtonConfirmBinding) this.a;
        if (dialogOneButtonConfirmBinding3 != null && (skinTextView = dialogOneButtonConfirmBinding3.b) != null) {
            skinTextView.setStyleTextColor(R.color.dialog_content_text);
        }
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding4 = (DialogOneButtonConfirmBinding) this.a;
        SkinTextView skinTextView2 = dialogOneButtonConfirmBinding4 != null ? dialogOneButtonConfirmBinding4.c : null;
        if (skinTextView2 == null) {
            return;
        }
        vm vmVar3 = vm.a;
        Context contextRequireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
        skinTextView2.setBackground(vmVar3.c(contextRequireContext3, R.drawable.common_dialog_start_button));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void f(@NotNull Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding = (DialogOneButtonConfirmBinding) this.a;
        SkinTextView skinTextView = dialogOneButtonConfirmBinding != null ? dialogOneButtonConfirmBinding.b : null;
        if (skinTextView != null) {
            vm vmVar = vm.a;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            skinTextView.setText(vmVar.e(contextRequireContext, R.string.no_storage_dialog_title));
        }
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding2 = (DialogOneButtonConfirmBinding) this.a;
        SkinTextView skinTextView2 = dialogOneButtonConfirmBinding2 != null ? dialogOneButtonConfirmBinding2.c : null;
        if (skinTextView2 == null) {
            return;
        }
        vm vmVar2 = vm.a;
        Context contextRequireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
        skinTextView2.setText(vmVar2.e(contextRequireContext2, R.string.no_storage_dialog_button));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void g() {
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onDestroyView() {
        SkinTextView skinTextView;
        ViewTreeObserver viewTreeObserver;
        super.onDestroyView();
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding = (DialogOneButtonConfirmBinding) this.a;
        if (dialogOneButtonConfirmBinding == null || (skinTextView = dialogOneButtonConfirmBinding.b) == null || (viewTreeObserver = skinTextView.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.removeOnPreDrawListener(this.g);
    }
}
