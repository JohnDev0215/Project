package com.byd.overseaappstore.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Window;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.DialogSettingCleanCacheBinding;
import com.byd.overseaappstore.ui.view.base.BaseDialog;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.av;
import spi.ct;
import spi.d;
import spi.gk;
import spi.vm;
import spi.y6;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0005B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Lcom/byd/overseaappstore/ui/view/dialog/SettingCleanCacheDialog;", "Lcom/byd/overseaappstore/ui/view/base/BaseDialog;", "Lcom/byd/overseaappstore/databinding/DialogSettingCleanCacheBinding;", "<init>", "()V", "a", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SettingCleanCacheDialog extends BaseDialog<DialogSettingCleanCacheBinding> {
    public static final /* synthetic */ int f = 0;

    @Nullable
    public a e;

    public interface a {
        void a();

        void b();
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final ViewBinding b() {
        DialogSettingCleanCacheBinding dialogSettingCleanCacheBindingA = DialogSettingCleanCacheBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(dialogSettingCleanCacheBindingA, "inflate(layoutInflater)");
        return dialogSettingCleanCacheBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void c() {
        SkinTextView skinTextView;
        SkinTextView skinTextView2;
        DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding = (DialogSettingCleanCacheBinding) this.a;
        if (dialogSettingCleanCacheBinding != null && (skinTextView2 = dialogSettingCleanCacheBinding.c) != null) {
            skinTextView2.setOnClickListener(new y6(this, 1));
        }
        DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding2 = (DialogSettingCleanCacheBinding) this.a;
        if (dialogSettingCleanCacheBinding2 == null || (skinTextView = dialogSettingCleanCacheBinding2.d) == null) {
            return;
        }
        skinTextView.setOnClickListener(new gk(this, 3));
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
        if (window2 == null) {
            return;
        }
        window2.setAttributes(attributes);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void e(@NotNull Configuration newConfig) {
        SkinTextView skinTextView;
        SkinTextView skinTextView2;
        SkinTextView skinTextView3;
        SkinTextView skinTextView4;
        SkinTextView skinTextView5;
        SkinTextView skinTextView6;
        SkinTextView skinTextView7;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        av avVar = av.a;
        getContext();
        if (avVar.e()) {
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding = (DialogSettingCleanCacheBinding) this.a;
            SkinView skinView = dialogSettingCleanCacheBinding != null ? dialogSettingCleanCacheBinding.j : null;
            if (skinView != null) {
                vm vmVar = vm.a;
                Context contextRequireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                skinView.setBackground(vmVar.c(contextRequireContext, R.drawable.bg_common_dialog));
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding2 = (DialogSettingCleanCacheBinding) this.a;
            SkinView skinView2 = dialogSettingCleanCacheBinding2 != null ? dialogSettingCleanCacheBinding2.h : null;
            if (skinView2 != null) {
                vm vmVar2 = vm.a;
                Context contextRequireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
                skinView2.setBackground(vmVar2.c(contextRequireContext2, R.drawable.bg_confirm_dialog_right_button));
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding3 = (DialogSettingCleanCacheBinding) this.a;
            SkinView skinView3 = dialogSettingCleanCacheBinding3 != null ? dialogSettingCleanCacheBinding3.g : null;
            if (skinView3 != null) {
                vm vmVar3 = vm.a;
                Context contextRequireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
                skinView3.setBackground(vmVar3.c(contextRequireContext3, R.drawable.common_dialog_start_button));
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding4 = (DialogSettingCleanCacheBinding) this.a;
            SkinView skinView4 = dialogSettingCleanCacheBinding4 != null ? dialogSettingCleanCacheBinding4.i : null;
            if (skinView4 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_over_line, null), skinView4);
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding5 = (DialogSettingCleanCacheBinding) this.a;
            if (dialogSettingCleanCacheBinding5 != null && (skinTextView4 = dialogSettingCleanCacheBinding5.f) != null) {
                skinTextView4.setStyleTextColor(R.color.dialog_content_text);
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding6 = (DialogSettingCleanCacheBinding) this.a;
            if (dialogSettingCleanCacheBinding6 != null && (skinTextView3 = dialogSettingCleanCacheBinding6.e) != null) {
                skinTextView3.setStyleTextColor(R.color.dialog_content_text);
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding7 = (DialogSettingCleanCacheBinding) this.a;
            if (dialogSettingCleanCacheBinding7 != null && (skinTextView2 = dialogSettingCleanCacheBinding7.d) != null) {
                skinTextView2.setStyleTextColor(R.color.dialog_positive_text_enable);
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding8 = (DialogSettingCleanCacheBinding) this.a;
            if (dialogSettingCleanCacheBinding8 == null || (skinTextView = dialogSettingCleanCacheBinding8.c) == null) {
                return;
            }
        } else {
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding9 = (DialogSettingCleanCacheBinding) this.a;
            SkinView skinView5 = dialogSettingCleanCacheBinding9 != null ? dialogSettingCleanCacheBinding9.j : null;
            if (skinView5 != null) {
                vm vmVar4 = vm.a;
                Context contextRequireContext4 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
                skinView5.setBackground(vmVar4.c(contextRequireContext4, R.drawable.bg_common_dialog));
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding10 = (DialogSettingCleanCacheBinding) this.a;
            SkinView skinView6 = dialogSettingCleanCacheBinding10 != null ? dialogSettingCleanCacheBinding10.h : null;
            if (skinView6 != null) {
                vm vmVar5 = vm.a;
                Context contextRequireContext5 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext5, "requireContext()");
                skinView6.setBackground(vmVar5.c(contextRequireContext5, R.drawable.common_dialog_start_button));
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding11 = (DialogSettingCleanCacheBinding) this.a;
            SkinView skinView7 = dialogSettingCleanCacheBinding11 != null ? dialogSettingCleanCacheBinding11.g : null;
            if (skinView7 != null) {
                vm vmVar6 = vm.a;
                Context contextRequireContext6 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext6, "requireContext()");
                skinView7.setBackground(vmVar6.c(contextRequireContext6, R.drawable.bg_confirm_dialog_right_button));
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding12 = (DialogSettingCleanCacheBinding) this.a;
            SkinView skinView8 = dialogSettingCleanCacheBinding12 != null ? dialogSettingCleanCacheBinding12.i : null;
            if (skinView8 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_over_line, null), skinView8);
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding13 = (DialogSettingCleanCacheBinding) this.a;
            if (dialogSettingCleanCacheBinding13 != null && (skinTextView7 = dialogSettingCleanCacheBinding13.f) != null) {
                skinTextView7.setStyleTextColor(R.color.dialog_content_text);
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding14 = (DialogSettingCleanCacheBinding) this.a;
            if (dialogSettingCleanCacheBinding14 != null && (skinTextView6 = dialogSettingCleanCacheBinding14.e) != null) {
                skinTextView6.setStyleTextColor(R.color.dialog_content_text);
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding15 = (DialogSettingCleanCacheBinding) this.a;
            if (dialogSettingCleanCacheBinding15 != null && (skinTextView5 = dialogSettingCleanCacheBinding15.d) != null) {
                skinTextView5.setStyleTextColor(R.color.dialog_positive_text_enable);
            }
            DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding16 = (DialogSettingCleanCacheBinding) this.a;
            if (dialogSettingCleanCacheBinding16 == null || (skinTextView = dialogSettingCleanCacheBinding16.c) == null) {
                return;
            }
        }
        skinTextView.setStyleTextColor(R.color.dialog_negative_text);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void f(@NotNull Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding = (DialogSettingCleanCacheBinding) this.a;
        SkinTextView skinTextView = dialogSettingCleanCacheBinding != null ? dialogSettingCleanCacheBinding.f : null;
        if (skinTextView != null) {
            vm vmVar = vm.a;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            skinTextView.setText(vmVar.e(contextRequireContext, R.string.tv_setting_dialog_title));
        }
        DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding2 = (DialogSettingCleanCacheBinding) this.a;
        SkinTextView skinTextView2 = dialogSettingCleanCacheBinding2 != null ? dialogSettingCleanCacheBinding2.e : null;
        if (skinTextView2 != null) {
            vm vmVar2 = vm.a;
            Context contextRequireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
            skinTextView2.setText(vmVar2.e(contextRequireContext2, R.string.tv_setting_dialog_content));
        }
        DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding3 = (DialogSettingCleanCacheBinding) this.a;
        SkinTextView skinTextView3 = dialogSettingCleanCacheBinding3 != null ? dialogSettingCleanCacheBinding3.d : null;
        if (skinTextView3 != null) {
            vm vmVar3 = vm.a;
            Context contextRequireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
            skinTextView3.setText(vmVar3.e(contextRequireContext3, R.string.tv_setting_dialog_clean));
        }
        DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding4 = (DialogSettingCleanCacheBinding) this.a;
        SkinTextView skinTextView4 = dialogSettingCleanCacheBinding4 != null ? dialogSettingCleanCacheBinding4.c : null;
        if (skinTextView4 == null) {
            return;
        }
        vm vmVar4 = vm.a;
        Context contextRequireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
        skinTextView4.setText(vmVar4.e(contextRequireContext4, R.string.tv_setting_dialog_cancel));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void g() {
        DialogSettingCleanCacheBinding dialogSettingCleanCacheBinding = (DialogSettingCleanCacheBinding) this.a;
        if (dialogSettingCleanCacheBinding != null) {
            ConstraintLayout rootDialogSettingCleanCache = dialogSettingCleanCacheBinding.b;
            Intrinsics.checkNotNullExpressionValue(rootDialogSettingCleanCache, "rootDialogSettingCleanCache");
            SkinTextView tvDialogSettingCleanButton = dialogSettingCleanCacheBinding.d;
            Intrinsics.checkNotNullExpressionValue(tvDialogSettingCleanButton, "tvDialogSettingCleanButton");
            SkinTextView tvDialogSettingCancelButton = dialogSettingCleanCacheBinding.c;
            Intrinsics.checkNotNullExpressionValue(tvDialogSettingCancelButton, "tvDialogSettingCancelButton");
            SkinView vDialogSettingCleanButton = dialogSettingCleanCacheBinding.h;
            Intrinsics.checkNotNullExpressionValue(vDialogSettingCleanButton, "vDialogSettingCleanButton");
            SkinView vDialogSettingCancelButton = dialogSettingCleanCacheBinding.g;
            Intrinsics.checkNotNullExpressionValue(vDialogSettingCancelButton, "vDialogSettingCancelButton");
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(rootDialogSettingCleanCache);
            constraintSet.clear(tvDialogSettingCleanButton.getId(), 6);
            constraintSet.clear(tvDialogSettingCleanButton.getId(), 7);
            constraintSet.clear(tvDialogSettingCancelButton.getId(), 6);
            constraintSet.clear(tvDialogSettingCancelButton.getId(), 7);
            constraintSet.connect(tvDialogSettingCancelButton.getId(), 6, vDialogSettingCleanButton.getId(), 6);
            constraintSet.connect(tvDialogSettingCancelButton.getId(), 7, vDialogSettingCleanButton.getId(), 7);
            constraintSet.connect(tvDialogSettingCleanButton.getId(), 7, vDialogSettingCancelButton.getId(), 7);
            constraintSet.connect(tvDialogSettingCleanButton.getId(), 6, vDialogSettingCancelButton.getId(), 6);
            vDialogSettingCleanButton.setBackground(ResourcesCompat.getDrawable(requireContext().getResources(), R.drawable.bg_confirm_dialog_right_button, null));
            vDialogSettingCancelButton.setBackground(ResourcesCompat.getDrawable(requireContext().getResources(), R.drawable.common_dialog_start_button, null));
            constraintSet.applyTo(rootDialogSettingCleanCache);
        }
    }
}
