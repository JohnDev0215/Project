package com.byd.overseaappstore.ui.view.dialog.iuu;

import android.content.Context;
import android.content.res.Configuration;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.DialogIuuBinding;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import com.byd.overseaappstore.ui.view.base.BaseDialog;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
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
import spi.gk;
import spi.vm;
import spi.y6;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/dialog/iuu/IUUDialog;", "Lcom/byd/overseaappstore/ui/view/base/BaseDialog;", "Lcom/byd/overseaappstore/databinding/DialogIuuBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class IUUDialog extends BaseDialog<DialogIuuBinding> {
    public static final /* synthetic */ int h = 0;

    @Nullable
    public Function1<? super IUUState, Unit> e;

    @Nullable
    public Function1<? super IUUState, Unit> f;

    @Nullable
    public IUUState g;

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final ViewBinding b() {
        DialogIuuBinding dialogIuuBindingA = DialogIuuBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(dialogIuuBindingA, "inflate(layoutInflater)");
        return dialogIuuBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void c() {
        DialogIuuBinding dialogIuuBinding = (DialogIuuBinding) this.a;
        if (dialogIuuBinding != null) {
            dialogIuuBinding.e.setOnClickListener(new y6(this, 3));
            dialogIuuBinding.f.setOnClickListener(new gk(this, 6));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0069 A[PHI: r1
  0x0069: PHI (r1v17 java.lang.String) = (r1v4 java.lang.String), (r1v20 java.lang.String) binds: [B:69:0x013e, B:31:0x0066] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d() {
        /*
            Method dump skipped, instruction units count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.view.dialog.iuu.IUUDialog.d():void");
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
        if (!zE) {
            DialogIuuBinding dialogIuuBinding = (DialogIuuBinding) this.a;
            SkinView skinView = dialogIuuBinding != null ? dialogIuuBinding.j : null;
            if (skinView != null) {
                vm vmVar = vm.a;
                Context contextRequireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                skinView.setBackground(vmVar.c(contextRequireContext, R.drawable.bg_setting_dialog));
            }
            DialogIuuBinding dialogIuuBinding2 = (DialogIuuBinding) this.a;
            SkinView skinView2 = dialogIuuBinding2 != null ? dialogIuuBinding2.h : null;
            if (skinView2 != null) {
                vm vmVar2 = vm.a;
                Context contextRequireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
                skinView2.setBackground(vmVar2.c(contextRequireContext2, R.drawable.common_dialog_start_button));
            }
            DialogIuuBinding dialogIuuBinding3 = (DialogIuuBinding) this.a;
            SkinView skinView3 = dialogIuuBinding3 != null ? dialogIuuBinding3.g : null;
            if (skinView3 != null) {
                vm vmVar3 = vm.a;
                Context contextRequireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
                skinView3.setBackground(vmVar3.c(contextRequireContext3, R.drawable.bg_confirm_dialog_right_button));
            }
            DialogIuuBinding dialogIuuBinding4 = (DialogIuuBinding) this.a;
            SkinView skinView4 = dialogIuuBinding4 != null ? dialogIuuBinding4.i : null;
            if (skinView4 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_over_line, null), skinView4);
            }
            DialogIuuBinding dialogIuuBinding5 = (DialogIuuBinding) this.a;
            SkinView skinView5 = dialogIuuBinding5 != null ? dialogIuuBinding5.k : null;
            if (skinView5 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_center_line, null), skinView5);
            }
            DialogIuuBinding dialogIuuBinding6 = (DialogIuuBinding) this.a;
            if (dialogIuuBinding6 != null && (skinTextView5 = dialogIuuBinding6.d) != null) {
                skinTextView5.setStyleTextColor(R.color.setting_text_color);
            }
            DialogIuuBinding dialogIuuBinding7 = (DialogIuuBinding) this.a;
            if (dialogIuuBinding7 != null && (skinTextView4 = dialogIuuBinding7.f) != null) {
                skinTextView4.setStyleTextColor(R.color.dialog_positive_text_enable);
            }
            DialogIuuBinding dialogIuuBinding8 = (DialogIuuBinding) this.a;
            if (dialogIuuBinding8 == null || (skinTextView = dialogIuuBinding8.e) == null) {
                return;
            }
        } else {
            if (!zE) {
                return;
            }
            DialogIuuBinding dialogIuuBinding9 = (DialogIuuBinding) this.a;
            SkinView skinView6 = dialogIuuBinding9 != null ? dialogIuuBinding9.j : null;
            if (skinView6 != null) {
                vm vmVar4 = vm.a;
                Context contextRequireContext4 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
                skinView6.setBackground(vmVar4.c(contextRequireContext4, R.drawable.bg_setting_dialog));
            }
            DialogIuuBinding dialogIuuBinding10 = (DialogIuuBinding) this.a;
            SkinView skinView7 = dialogIuuBinding10 != null ? dialogIuuBinding10.h : null;
            if (skinView7 != null) {
                vm vmVar5 = vm.a;
                Context contextRequireContext5 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext5, "requireContext()");
                skinView7.setBackground(vmVar5.c(contextRequireContext5, R.drawable.bg_confirm_dialog_right_button));
            }
            DialogIuuBinding dialogIuuBinding11 = (DialogIuuBinding) this.a;
            SkinView skinView8 = dialogIuuBinding11 != null ? dialogIuuBinding11.g : null;
            if (skinView8 != null) {
                vm vmVar6 = vm.a;
                Context contextRequireContext6 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext6, "requireContext()");
                skinView8.setBackground(vmVar6.c(contextRequireContext6, R.drawable.common_dialog_start_button));
            }
            DialogIuuBinding dialogIuuBinding12 = (DialogIuuBinding) this.a;
            SkinView skinView9 = dialogIuuBinding12 != null ? dialogIuuBinding12.i : null;
            if (skinView9 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_over_line, null), skinView9);
            }
            DialogIuuBinding dialogIuuBinding13 = (DialogIuuBinding) this.a;
            SkinView skinView10 = dialogIuuBinding13 != null ? dialogIuuBinding13.k : null;
            if (skinView10 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_center_line, null), skinView10);
            }
            DialogIuuBinding dialogIuuBinding14 = (DialogIuuBinding) this.a;
            if (dialogIuuBinding14 != null && (skinTextView3 = dialogIuuBinding14.d) != null) {
                skinTextView3.setStyleTextColor(R.color.setting_text_color);
            }
            DialogIuuBinding dialogIuuBinding15 = (DialogIuuBinding) this.a;
            if (dialogIuuBinding15 != null && (skinTextView2 = dialogIuuBinding15.f) != null) {
                skinTextView2.setStyleTextColor(R.color.dialog_positive_text_enable);
            }
            DialogIuuBinding dialogIuuBinding16 = (DialogIuuBinding) this.a;
            if (dialogIuuBinding16 == null || (skinTextView = dialogIuuBinding16.e) == null) {
                return;
            }
        }
        skinTextView.setStyleTextColor(R.color.dialog_negative_text);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void f(@NotNull Configuration newConfig) {
        SkinTextView skinTextView;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        IUUState iUUState = this.g;
        Integer numValueOf = iUUState != null ? Integer.valueOf(iUUState.getState()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            DialogIuuBinding dialogIuuBinding = (DialogIuuBinding) this.a;
            SkinTextView skinTextView2 = dialogIuuBinding != null ? dialogIuuBinding.d : null;
            if (skinTextView2 != null) {
                vm vmVar = vm.a;
                Context applicationContext = requireContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "requireContext().applicationContext");
                skinTextView2.setText(vmVar.e(applicationContext, R.string.iuu_to_install_content));
            }
            DialogIuuBinding dialogIuuBinding2 = (DialogIuuBinding) this.a;
            SkinTextView skinTextView3 = dialogIuuBinding2 != null ? dialogIuuBinding2.f : null;
            if (skinTextView3 != null) {
                vm vmVar2 = vm.a;
                Context applicationContext2 = requireContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext2, "requireContext().applicationContext");
                skinTextView3.setText(vmVar2.e(applicationContext2, R.string.iuu_install_tip));
            }
            DialogIuuBinding dialogIuuBinding3 = (DialogIuuBinding) this.a;
            skinTextView = dialogIuuBinding3 != null ? dialogIuuBinding3.e : null;
            if (skinTextView == null) {
                return;
            }
        } else if (numValueOf != null && numValueOf.intValue() == 1) {
            DialogIuuBinding dialogIuuBinding4 = (DialogIuuBinding) this.a;
            SkinTextView skinTextView4 = dialogIuuBinding4 != null ? dialogIuuBinding4.d : null;
            if (skinTextView4 != null) {
                vm vmVar3 = vm.a;
                Context applicationContext3 = requireContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext3, "requireContext().applicationContext");
                skinTextView4.setText(vmVar3.e(applicationContext3, R.string.iuu_to_update_content));
            }
            DialogIuuBinding dialogIuuBinding5 = (DialogIuuBinding) this.a;
            SkinTextView skinTextView5 = dialogIuuBinding5 != null ? dialogIuuBinding5.f : null;
            if (skinTextView5 != null) {
                vm vmVar4 = vm.a;
                Context applicationContext4 = requireContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext4, "requireContext().applicationContext");
                skinTextView5.setText(vmVar4.e(applicationContext4, R.string.iuu_install_tip));
            }
            DialogIuuBinding dialogIuuBinding6 = (DialogIuuBinding) this.a;
            skinTextView = dialogIuuBinding6 != null ? dialogIuuBinding6.e : null;
            if (skinTextView == null) {
                return;
            }
        } else if (numValueOf != null && numValueOf.intValue() == 2) {
            DialogIuuBinding dialogIuuBinding7 = (DialogIuuBinding) this.a;
            SkinTextView skinTextView6 = dialogIuuBinding7 != null ? dialogIuuBinding7.d : null;
            if (skinTextView6 != null) {
                vm vmVar5 = vm.a;
                Context applicationContext5 = requireContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext5, "requireContext().applicationContext");
                skinTextView6.setText(vmVar5.e(applicationContext5, R.string.iuu_to_uninstall_content));
            }
            DialogIuuBinding dialogIuuBinding8 = (DialogIuuBinding) this.a;
            SkinTextView skinTextView7 = dialogIuuBinding8 != null ? dialogIuuBinding8.f : null;
            if (skinTextView7 != null) {
                vm vmVar6 = vm.a;
                Context applicationContext6 = requireContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext6, "requireContext().applicationContext");
                skinTextView7.setText(vmVar6.e(applicationContext6, R.string.iuu_uninstall_tip));
            }
            DialogIuuBinding dialogIuuBinding9 = (DialogIuuBinding) this.a;
            skinTextView = dialogIuuBinding9 != null ? dialogIuuBinding9.e : null;
            if (skinTextView == null) {
                return;
            }
        } else {
            DialogIuuBinding dialogIuuBinding10 = (DialogIuuBinding) this.a;
            SkinTextView skinTextView8 = dialogIuuBinding10 != null ? dialogIuuBinding10.d : null;
            if (skinTextView8 != null) {
                vm vmVar7 = vm.a;
                Context applicationContext7 = requireContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext7, "requireContext().applicationContext");
                skinTextView8.setText(vmVar7.e(applicationContext7, R.string.iuu_to_install_content));
            }
            DialogIuuBinding dialogIuuBinding11 = (DialogIuuBinding) this.a;
            SkinTextView skinTextView9 = dialogIuuBinding11 != null ? dialogIuuBinding11.f : null;
            if (skinTextView9 != null) {
                vm vmVar8 = vm.a;
                Context applicationContext8 = requireContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext8, "requireContext().applicationContext");
                skinTextView9.setText(vmVar8.e(applicationContext8, R.string.iuu_install_tip));
            }
            DialogIuuBinding dialogIuuBinding12 = (DialogIuuBinding) this.a;
            skinTextView = dialogIuuBinding12 != null ? dialogIuuBinding12.e : null;
            if (skinTextView == null) {
                return;
            }
        }
        vm vmVar9 = vm.a;
        Context applicationContext9 = requireContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext9, "requireContext().applicationContext");
        skinTextView.setText(vmVar9.e(applicationContext9, R.string.iuu_cancel_tip));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void g() {
        DialogIuuBinding dialogIuuBinding = (DialogIuuBinding) this.a;
        if (dialogIuuBinding != null) {
            ConstraintLayout rootIuuDialog = dialogIuuBinding.c;
            Intrinsics.checkNotNullExpressionValue(rootIuuDialog, "rootIuuDialog");
            SkinTextView tvDialogPositiveButton = dialogIuuBinding.f;
            Intrinsics.checkNotNullExpressionValue(tvDialogPositiveButton, "tvDialogPositiveButton");
            SkinTextView tvDialogNegativeButton = dialogIuuBinding.e;
            Intrinsics.checkNotNullExpressionValue(tvDialogNegativeButton, "tvDialogNegativeButton");
            SkinView vDialogPositiveButton = dialogIuuBinding.h;
            Intrinsics.checkNotNullExpressionValue(vDialogPositiveButton, "vDialogPositiveButton");
            SkinView vDialogNegativeButton = dialogIuuBinding.g;
            Intrinsics.checkNotNullExpressionValue(vDialogNegativeButton, "vDialogNegativeButton");
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(rootIuuDialog);
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
            constraintSet.applyTo(rootIuuDialog);
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog, androidx.fragment.app.DialogFragment
    public final void show(@NotNull FragmentManager manager, @Nullable String str) {
        SoftwareOptParam softwareOptParam;
        Intrinsics.checkNotNullParameter(manager, "manager");
        if (!isAdded()) {
            super.show(manager, str);
            return;
        }
        IUUState iUUState = this.g;
        String packageName = null;
        Integer numValueOf = iUUState != null ? Integer.valueOf(iUUState.getState()) : null;
        IUUState iUUState2 = this.g;
        if (iUUState2 != null && (softwareOptParam = iUUState2.getSoftwareOptParam()) != null) {
            packageName = softwareOptParam.getPackageName();
        }
        ct.b("iuu collect iUUState alreadyAdd do nothing operateState:" + numValueOf + " pkn:" + packageName, new Object[0]);
    }
}
