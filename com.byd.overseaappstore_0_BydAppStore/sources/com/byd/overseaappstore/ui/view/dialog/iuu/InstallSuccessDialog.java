package com.byd.overseaappstore.ui.view.dialog.iuu;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewbinding.ViewBinding;
import com.bumptech.glide.a;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.DialogInstallSuccessBinding;
import com.byd.overseaappstore.module.ability.R$drawable;
import com.byd.overseaappstore.ui.view.base.BaseDialog;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.av;
import spi.ct;
import spi.d;
import spi.fj;
import spi.qn;
import spi.tm;
import spi.vd;
import spi.vm;
import spi.wd;
import spi.y6;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/dialog/iuu/InstallSuccessDialog;", "Lcom/byd/overseaappstore/ui/view/base/BaseDialog;", "Lcom/byd/overseaappstore/databinding/DialogInstallSuccessBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class InstallSuccessDialog extends BaseDialog<DialogInstallSuccessBinding> {
    public static final /* synthetic */ int e = 0;

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final ViewBinding b() {
        DialogInstallSuccessBinding dialogInstallSuccessBindingA = DialogInstallSuccessBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(dialogInstallSuccessBindingA, "inflate(layoutInflater)");
        return dialogInstallSuccessBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void c() {
        DialogInstallSuccessBinding dialogInstallSuccessBinding = (DialogInstallSuccessBinding) this.a;
        if (dialogInstallSuccessBinding != null) {
            dialogInstallSuccessBinding.e.setOnClickListener(new fj(this, 10));
            dialogInstallSuccessBinding.f.setOnClickListener(new y6(this, 4));
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
        attributes.width = -2;
        attributes.height = -2;
        Dialog dialog2 = getDialog();
        Window window2 = dialog2 != null ? dialog2.getWindow() : null;
        if (window2 != null) {
            window2.setAttributes(attributes);
        }
        vm vmVar = vm.a;
        Context applicationContext = requireContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "requireContext().applicationContext");
        String strF = vmVar.f(applicationContext, R.string.iuu_installed_content, "");
        Context applicationContext2 = requireContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "requireContext().applicationContext");
        String strE = vmVar.e(applicationContext2, R.string.iuu_open_tip);
        Context applicationContext3 = requireContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext3, "requireContext().applicationContext");
        String strE2 = vmVar.e(applicationContext3, R.string.iuu_done_tip);
        DialogInstallSuccessBinding dialogInstallSuccessBinding = (DialogInstallSuccessBinding) this.a;
        if (dialogInstallSuccessBinding != null) {
            vd vdVar = vd.a;
            Context context = requireContext();
            Intrinsics.checkNotNullExpressionValue(context, "requireContext()");
            ImageView intoImageView = dialogInstallSuccessBinding.b;
            Intrinsics.checkNotNullExpressionValue(intoImageView, "it.ivIcon");
            Objects.requireNonNull(vdVar);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intoImageView, "intoImageView");
            Intrinsics.checkNotNullParameter(InstallSuccessDialog.class, "clazz");
            a.e(context).o("").h(vmVar.a(context, R$drawable.bg_app_detial_default_bg)).a(new tm().x(new qn(context.getResources().getDimensionPixelSize(R.dimen.dp_10)), true)).A(new wd(InstallSuccessDialog.class)).G(intoImageView);
            dialogInstallSuccessBinding.d.setText(strF);
            dialogInstallSuccessBinding.f.setText(strE);
            dialogInstallSuccessBinding.e.setText(strE2);
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void e(@NotNull Configuration newConfig) {
        SkinTextView skinTextView;
        SkinTextView skinTextView2;
        SkinTextView skinTextView3;
        SkinTextView skinTextView4;
        SkinTextView skinTextView5;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        av avVar = av.a;
        getContext();
        boolean zE = avVar.e();
        if (zE) {
            DialogInstallSuccessBinding dialogInstallSuccessBinding = (DialogInstallSuccessBinding) this.a;
            SkinView skinView = dialogInstallSuccessBinding != null ? dialogInstallSuccessBinding.j : null;
            if (skinView != null) {
                vm vmVar = vm.a;
                Context contextRequireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                skinView.setBackground(vmVar.c(contextRequireContext, R.drawable.bg_setting_dialog));
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding2 = (DialogInstallSuccessBinding) this.a;
            SkinView skinView2 = dialogInstallSuccessBinding2 != null ? dialogInstallSuccessBinding2.h : null;
            if (skinView2 != null) {
                vm vmVar2 = vm.a;
                Context contextRequireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
                skinView2.setBackground(vmVar2.c(contextRequireContext2, R.drawable.bg_confirm_dialog_right_button));
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding3 = (DialogInstallSuccessBinding) this.a;
            SkinView skinView3 = dialogInstallSuccessBinding3 != null ? dialogInstallSuccessBinding3.g : null;
            if (skinView3 != null) {
                vm vmVar3 = vm.a;
                Context contextRequireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
                skinView3.setBackground(vmVar3.c(contextRequireContext3, R.drawable.common_dialog_start_button));
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding4 = (DialogInstallSuccessBinding) this.a;
            SkinView skinView4 = dialogInstallSuccessBinding4 != null ? dialogInstallSuccessBinding4.i : null;
            if (skinView4 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_over_line, null), skinView4);
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding5 = (DialogInstallSuccessBinding) this.a;
            SkinView skinView5 = dialogInstallSuccessBinding5 != null ? dialogInstallSuccessBinding5.k : null;
            if (skinView5 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_center_line, null), skinView5);
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding6 = (DialogInstallSuccessBinding) this.a;
            if (dialogInstallSuccessBinding6 != null && (skinTextView5 = dialogInstallSuccessBinding6.d) != null) {
                skinTextView5.setStyleTextColor(R.color.setting_text_color);
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding7 = (DialogInstallSuccessBinding) this.a;
            if (dialogInstallSuccessBinding7 != null && (skinTextView4 = dialogInstallSuccessBinding7.f) != null) {
                skinTextView4.setStyleTextColor(R.color.dialog_positive_text_enable);
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding8 = (DialogInstallSuccessBinding) this.a;
            if (dialogInstallSuccessBinding8 == null || (skinTextView = dialogInstallSuccessBinding8.e) == null) {
                return;
            }
        } else {
            if (zE) {
                return;
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding9 = (DialogInstallSuccessBinding) this.a;
            SkinView skinView6 = dialogInstallSuccessBinding9 != null ? dialogInstallSuccessBinding9.j : null;
            if (skinView6 != null) {
                vm vmVar4 = vm.a;
                Context contextRequireContext4 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
                skinView6.setBackground(vmVar4.c(contextRequireContext4, R.drawable.bg_setting_dialog));
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding10 = (DialogInstallSuccessBinding) this.a;
            SkinView skinView7 = dialogInstallSuccessBinding10 != null ? dialogInstallSuccessBinding10.h : null;
            if (skinView7 != null) {
                vm vmVar5 = vm.a;
                Context contextRequireContext5 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext5, "requireContext()");
                skinView7.setBackground(vmVar5.c(contextRequireContext5, R.drawable.common_dialog_start_button));
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding11 = (DialogInstallSuccessBinding) this.a;
            SkinView skinView8 = dialogInstallSuccessBinding11 != null ? dialogInstallSuccessBinding11.g : null;
            if (skinView8 != null) {
                vm vmVar6 = vm.a;
                Context contextRequireContext6 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext6, "requireContext()");
                skinView8.setBackground(vmVar6.c(contextRequireContext6, R.drawable.bg_confirm_dialog_right_button));
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding12 = (DialogInstallSuccessBinding) this.a;
            SkinView skinView9 = dialogInstallSuccessBinding12 != null ? dialogInstallSuccessBinding12.i : null;
            if (skinView9 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_over_line, null), skinView9);
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding13 = (DialogInstallSuccessBinding) this.a;
            SkinView skinView10 = dialogInstallSuccessBinding13 != null ? dialogInstallSuccessBinding13.k : null;
            if (skinView10 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_center_line, null), skinView10);
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding14 = (DialogInstallSuccessBinding) this.a;
            if (dialogInstallSuccessBinding14 != null && (skinTextView3 = dialogInstallSuccessBinding14.d) != null) {
                skinTextView3.setStyleTextColor(R.color.setting_text_color);
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding15 = (DialogInstallSuccessBinding) this.a;
            if (dialogInstallSuccessBinding15 != null && (skinTextView2 = dialogInstallSuccessBinding15.f) != null) {
                skinTextView2.setStyleTextColor(R.color.dialog_positive_text_enable);
            }
            DialogInstallSuccessBinding dialogInstallSuccessBinding16 = (DialogInstallSuccessBinding) this.a;
            if (dialogInstallSuccessBinding16 == null || (skinTextView = dialogInstallSuccessBinding16.e) == null) {
                return;
            }
        }
        skinTextView.setStyleTextColor(R.color.dialog_negative_text);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void f(@NotNull Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        DialogInstallSuccessBinding dialogInstallSuccessBinding = (DialogInstallSuccessBinding) this.a;
        SkinTextView skinTextView = dialogInstallSuccessBinding != null ? dialogInstallSuccessBinding.d : null;
        if (skinTextView != null) {
            vm vmVar = vm.a;
            Context applicationContext = requireContext().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "requireContext().applicationContext");
            skinTextView.setText(vmVar.f(applicationContext, R.string.iuu_installed_content, ""));
        }
        DialogInstallSuccessBinding dialogInstallSuccessBinding2 = (DialogInstallSuccessBinding) this.a;
        SkinTextView skinTextView2 = dialogInstallSuccessBinding2 != null ? dialogInstallSuccessBinding2.f : null;
        if (skinTextView2 != null) {
            vm vmVar2 = vm.a;
            Context applicationContext2 = requireContext().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "requireContext().applicationContext");
            skinTextView2.setText(vmVar2.e(applicationContext2, R.string.iuu_open_tip));
        }
        DialogInstallSuccessBinding dialogInstallSuccessBinding3 = (DialogInstallSuccessBinding) this.a;
        SkinTextView skinTextView3 = dialogInstallSuccessBinding3 != null ? dialogInstallSuccessBinding3.e : null;
        if (skinTextView3 == null) {
            return;
        }
        vm vmVar3 = vm.a;
        Context applicationContext3 = requireContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext3, "requireContext().applicationContext");
        skinTextView3.setText(vmVar3.e(applicationContext3, R.string.iuu_done_tip));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void g() {
        DialogInstallSuccessBinding dialogInstallSuccessBinding = (DialogInstallSuccessBinding) this.a;
        if (dialogInstallSuccessBinding != null) {
            ConstraintLayout rootInstallSuccessDialog = dialogInstallSuccessBinding.c;
            Intrinsics.checkNotNullExpressionValue(rootInstallSuccessDialog, "rootInstallSuccessDialog");
            SkinTextView tvDialogPositiveButton = dialogInstallSuccessBinding.f;
            Intrinsics.checkNotNullExpressionValue(tvDialogPositiveButton, "tvDialogPositiveButton");
            SkinTextView tvDialogNegativeButton = dialogInstallSuccessBinding.e;
            Intrinsics.checkNotNullExpressionValue(tvDialogNegativeButton, "tvDialogNegativeButton");
            SkinView vDialogPositiveButton = dialogInstallSuccessBinding.h;
            Intrinsics.checkNotNullExpressionValue(vDialogPositiveButton, "vDialogPositiveButton");
            SkinView vDialogNegativeButton = dialogInstallSuccessBinding.g;
            Intrinsics.checkNotNullExpressionValue(vDialogNegativeButton, "vDialogNegativeButton");
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(rootInstallSuccessDialog);
            constraintSet.clear(tvDialogPositiveButton.getId(), 6);
            constraintSet.clear(tvDialogPositiveButton.getId(), 7);
            constraintSet.clear(tvDialogNegativeButton.getId(), 6);
            constraintSet.clear(tvDialogNegativeButton.getId(), 7);
            constraintSet.connect(tvDialogNegativeButton.getId(), 6, vDialogPositiveButton.getId(), 6);
            constraintSet.connect(tvDialogNegativeButton.getId(), 7, vDialogPositiveButton.getId(), 7);
            constraintSet.connect(tvDialogPositiveButton.getId(), 7, vDialogNegativeButton.getId(), 7);
            constraintSet.connect(tvDialogPositiveButton.getId(), 6, vDialogNegativeButton.getId(), 6);
            vDialogPositiveButton.setBackground(ResourcesCompat.getDrawable(requireContext().getResources(), R.drawable.bg_confirm_dialog_right_button, null));
            vDialogNegativeButton.setBackground(ResourcesCompat.getDrawable(requireContext().getResources(), R.drawable.common_dialog_start_button, null));
            constraintSet.applyTo(rootInstallSuccessDialog);
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog, androidx.fragment.app.DialogFragment
    public final void show(@NotNull FragmentManager manager, @Nullable String str) {
        Intrinsics.checkNotNullParameter(manager, "manager");
        if (!isAdded()) {
            super.show(manager, str);
            return;
        }
        ct.b("iuu collect iUUState InstallSuccessDialog alreadyAdd do nothing operateState:" + ((Object) null) + " pkn:" + ((String) null), new Object[0]);
    }
}
