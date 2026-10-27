package com.byd.overseaappstore.ui.view.dialog;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.DialogOneButtonConfirmBinding;
import com.byd.overseaappstore.ui.view.base.BaseDialog;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.fj;
import spi.j4;
import spi.vm;
import spi.z6;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/dialog/NotPBanDialog;", "Lcom/byd/overseaappstore/ui/view/base/BaseDialog;", "Lcom/byd/overseaappstore/databinding/DialogOneButtonConfirmBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class NotPBanDialog extends BaseDialog<DialogOneButtonConfirmBinding> {
    public static final /* synthetic */ int j = 0;

    @Nullable
    public Function1<? super View, Unit> h;

    @NotNull
    public String e = "";

    @NotNull
    public String f = "";
    public Long g = j4.c;

    @NotNull
    public final z6 i = new z6(this, 1);

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
        skinView.setOnClickListener(new fj(this, 2));
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
        viewTreeObserver.addOnPreDrawListener(this.i);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void e(@NotNull Configuration newConfig) {
        SkinTextView skinTextView;
        SkinTextView skinTextView2;
        SkinView skinView;
        SkinView skinView2;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding = (DialogOneButtonConfirmBinding) this.a;
        if (dialogOneButtonConfirmBinding != null && (skinView2 = dialogOneButtonConfirmBinding.e) != null) {
            skinView2.setBackgroundDrawableId(R.drawable.bg_common_dialog);
        }
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding2 = (DialogOneButtonConfirmBinding) this.a;
        if (dialogOneButtonConfirmBinding2 != null && (skinView = dialogOneButtonConfirmBinding2.d) != null) {
            skinView.setBackgroundDrawableId(R.drawable.common_dialog_start_button);
        }
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding3 = (DialogOneButtonConfirmBinding) this.a;
        if (dialogOneButtonConfirmBinding3 != null && (skinTextView2 = dialogOneButtonConfirmBinding3.b) != null) {
            skinTextView2.setStyleTextColor(R.color.dialog_content_text);
        }
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding4 = (DialogOneButtonConfirmBinding) this.a;
        if (dialogOneButtonConfirmBinding4 == null || (skinTextView = dialogOneButtonConfirmBinding4.c) == null) {
            return;
        }
        skinTextView.setStyleTextColor(R.color.dialog_positive_text_enable);
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
            skinTextView.setText(vmVar.e(contextRequireContext, R.string.not_p_ban_dialog_title));
        }
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding2 = (DialogOneButtonConfirmBinding) this.a;
        SkinTextView skinTextView2 = dialogOneButtonConfirmBinding2 != null ? dialogOneButtonConfirmBinding2.c : null;
        if (skinTextView2 == null) {
            return;
        }
        vm vmVar2 = vm.a;
        Context contextRequireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
        skinTextView2.setText(vmVar2.f(contextRequireContext2, R.string.not_p_ban_dialog_button, String.valueOf(this.g)));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void g() {
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onDestroyView() {
        SkinTextView skinTextView;
        ViewTreeObserver viewTreeObserver;
        DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding = (DialogOneButtonConfirmBinding) this.a;
        if (dialogOneButtonConfirmBinding != null && (skinTextView = dialogOneButtonConfirmBinding.b) != null && (viewTreeObserver = skinTextView.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnPreDrawListener(this.i);
        }
        super.onDestroyView();
    }
}
