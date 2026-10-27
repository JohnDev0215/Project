package com.byd.overseaappstore.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.DialogWorkOnlyWifiBinding;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.net.NetContextState;
import com.byd.overseaappstore.ui.view.base.BaseDialog;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import java.util.List;
import kotlin.Metadata;
import kotlin.Triple;
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
import spi.qq;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/dialog/OnlyWifiOrCellularDialog;", "Lcom/byd/overseaappstore/ui/view/base/BaseDialog;", "Lcom/byd/overseaappstore/databinding/DialogWorkOnlyWifiBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class OnlyWifiOrCellularDialog extends BaseDialog<DialogWorkOnlyWifiBinding> {
    public static final /* synthetic */ int j = 0;

    @Nullable
    public Function1<? super Triple<NetContextState, ? extends List<SoftwareOptParam>, ? extends List<qq>>, Unit> e;

    @Nullable
    public Function1<? super Triple<NetContextState, ? extends List<SoftwareOptParam>, ? extends List<qq>>, Unit> f;

    @Nullable
    public Triple<NetContextState, ? extends List<SoftwareOptParam>, ? extends List<qq>> g;

    @NotNull
    public String h = "";

    @NotNull
    public final hk i = new hk(this, 0);

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final ViewBinding b() {
        DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBindingA = DialogWorkOnlyWifiBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(dialogWorkOnlyWifiBindingA, "inflate(layoutInflater)");
        return dialogWorkOnlyWifiBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void c() {
        DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding = (DialogWorkOnlyWifiBinding) this.a;
        if (dialogWorkOnlyWifiBinding != null) {
            dialogWorkOnlyWifiBinding.c.setOnClickListener(new gk(this, 0));
            dialogWorkOnlyWifiBinding.d.setOnClickListener(new fj(this, 3));
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void d() {
        Window window;
        SkinTextView skinTextView;
        ViewTreeObserver viewTreeObserver;
        DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding = (DialogWorkOnlyWifiBinding) this.a;
        SkinTextView skinTextView2 = dialogWorkOnlyWifiBinding != null ? dialogWorkOnlyWifiBinding.e : null;
        if (skinTextView2 != null) {
            skinTextView2.setText(this.h);
        }
        DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding2 = (DialogWorkOnlyWifiBinding) this.a;
        if (dialogWorkOnlyWifiBinding2 != null && (skinTextView = dialogWorkOnlyWifiBinding2.e) != null && (viewTreeObserver = skinTextView.getViewTreeObserver()) != null) {
            viewTreeObserver.addOnPreDrawListener(this.i);
        }
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
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding = (DialogWorkOnlyWifiBinding) this.a;
            SkinView skinView = dialogWorkOnlyWifiBinding != null ? dialogWorkOnlyWifiBinding.j : null;
            if (skinView != null) {
                vm vmVar = vm.a;
                Context contextRequireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                skinView.setBackground(vmVar.c(contextRequireContext, R.drawable.bg_common_dialog));
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding2 = (DialogWorkOnlyWifiBinding) this.a;
            SkinView skinView2 = dialogWorkOnlyWifiBinding2 != null ? dialogWorkOnlyWifiBinding2.h : null;
            if (skinView2 != null) {
                vm vmVar2 = vm.a;
                Context contextRequireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
                skinView2.setBackground(vmVar2.c(contextRequireContext2, R.drawable.bg_confirm_dialog_right_button));
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding3 = (DialogWorkOnlyWifiBinding) this.a;
            SkinView skinView3 = dialogWorkOnlyWifiBinding3 != null ? dialogWorkOnlyWifiBinding3.g : null;
            if (skinView3 != null) {
                vm vmVar3 = vm.a;
                Context contextRequireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
                skinView3.setBackground(vmVar3.c(contextRequireContext3, R.drawable.common_dialog_start_button));
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding4 = (DialogWorkOnlyWifiBinding) this.a;
            SkinView skinView4 = dialogWorkOnlyWifiBinding4 != null ? dialogWorkOnlyWifiBinding4.i : null;
            if (skinView4 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_over_line, null), skinView4);
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding5 = (DialogWorkOnlyWifiBinding) this.a;
            if (dialogWorkOnlyWifiBinding5 != null && (skinTextView4 = dialogWorkOnlyWifiBinding5.f) != null) {
                skinTextView4.setStyleTextColor(R.color.dialog_content_text);
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding6 = (DialogWorkOnlyWifiBinding) this.a;
            if (dialogWorkOnlyWifiBinding6 != null && (skinTextView3 = dialogWorkOnlyWifiBinding6.d) != null) {
                skinTextView3.setStyleTextColor(R.color.dialog_positive_text_enable);
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding7 = (DialogWorkOnlyWifiBinding) this.a;
            if (dialogWorkOnlyWifiBinding7 != null && (skinTextView2 = dialogWorkOnlyWifiBinding7.c) != null) {
                skinTextView2.setStyleTextColor(R.color.dialog_negative_text);
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding8 = (DialogWorkOnlyWifiBinding) this.a;
            if (dialogWorkOnlyWifiBinding8 == null || (skinTextView = dialogWorkOnlyWifiBinding8.e) == null) {
                return;
            }
        } else {
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding9 = (DialogWorkOnlyWifiBinding) this.a;
            SkinView skinView5 = dialogWorkOnlyWifiBinding9 != null ? dialogWorkOnlyWifiBinding9.j : null;
            if (skinView5 != null) {
                vm vmVar4 = vm.a;
                Context contextRequireContext4 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
                skinView5.setBackground(vmVar4.c(contextRequireContext4, R.drawable.bg_common_dialog));
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding10 = (DialogWorkOnlyWifiBinding) this.a;
            SkinView skinView6 = dialogWorkOnlyWifiBinding10 != null ? dialogWorkOnlyWifiBinding10.h : null;
            if (skinView6 != null) {
                vm vmVar5 = vm.a;
                Context contextRequireContext5 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext5, "requireContext()");
                skinView6.setBackground(vmVar5.c(contextRequireContext5, R.drawable.common_dialog_start_button));
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding11 = (DialogWorkOnlyWifiBinding) this.a;
            SkinView skinView7 = dialogWorkOnlyWifiBinding11 != null ? dialogWorkOnlyWifiBinding11.g : null;
            if (skinView7 != null) {
                vm vmVar6 = vm.a;
                Context contextRequireContext6 = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext6, "requireContext()");
                skinView7.setBackground(vmVar6.c(contextRequireContext6, R.drawable.bg_confirm_dialog_right_button));
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding12 = (DialogWorkOnlyWifiBinding) this.a;
            SkinView skinView8 = dialogWorkOnlyWifiBinding12 != null ? dialogWorkOnlyWifiBinding12.i : null;
            if (skinView8 != null) {
                d.q(ResourcesCompat.getColor(requireContext().getResources(), R.color.setting_over_line, null), skinView8);
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding13 = (DialogWorkOnlyWifiBinding) this.a;
            if (dialogWorkOnlyWifiBinding13 != null && (skinTextView7 = dialogWorkOnlyWifiBinding13.f) != null) {
                skinTextView7.setStyleTextColor(R.color.dialog_content_text);
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding14 = (DialogWorkOnlyWifiBinding) this.a;
            if (dialogWorkOnlyWifiBinding14 != null && (skinTextView6 = dialogWorkOnlyWifiBinding14.d) != null) {
                skinTextView6.setStyleTextColor(R.color.dialog_positive_text_enable);
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding15 = (DialogWorkOnlyWifiBinding) this.a;
            if (dialogWorkOnlyWifiBinding15 != null && (skinTextView5 = dialogWorkOnlyWifiBinding15.c) != null) {
                skinTextView5.setStyleTextColor(R.color.dialog_negative_text);
            }
            DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding16 = (DialogWorkOnlyWifiBinding) this.a;
            if (dialogWorkOnlyWifiBinding16 == null || (skinTextView = dialogWorkOnlyWifiBinding16.e) == null) {
                return;
            }
        }
        skinTextView.setStyleTextColor(R.color.dialog_content_text);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void f(@NotNull Configuration newConfig) {
        Context contextRequireContext;
        int i;
        NetContextState first;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding = (DialogWorkOnlyWifiBinding) this.a;
        SkinTextView skinTextView = dialogWorkOnlyWifiBinding != null ? dialogWorkOnlyWifiBinding.f : null;
        if (skinTextView != null) {
            vm vmVar = vm.a;
            Context contextRequireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
            skinTextView.setText(vmVar.e(contextRequireContext2, R.string.workWifi_dialog_title));
        }
        DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding2 = (DialogWorkOnlyWifiBinding) this.a;
        SkinTextView skinTextView2 = dialogWorkOnlyWifiBinding2 != null ? dialogWorkOnlyWifiBinding2.d : null;
        if (skinTextView2 != null) {
            vm vmVar2 = vm.a;
            Context contextRequireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
            skinTextView2.setText(vmVar2.e(contextRequireContext3, R.string.workWifi_dialog_continue));
        }
        DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding3 = (DialogWorkOnlyWifiBinding) this.a;
        SkinTextView skinTextView3 = dialogWorkOnlyWifiBinding3 != null ? dialogWorkOnlyWifiBinding3.c : null;
        if (skinTextView3 != null) {
            vm vmVar3 = vm.a;
            Context contextRequireContext4 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
            skinTextView3.setText(vmVar3.e(contextRequireContext4, R.string.workWifi_dialog_cancel));
        }
        DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding4 = (DialogWorkOnlyWifiBinding) this.a;
        SkinTextView skinTextView4 = dialogWorkOnlyWifiBinding4 != null ? dialogWorkOnlyWifiBinding4.e : null;
        if (skinTextView4 == null) {
            return;
        }
        Triple<NetContextState, ? extends List<SoftwareOptParam>, ? extends List<qq>> triple = this.g;
        boolean z = (triple == null || (first = triple.getFirst()) == null || !first.getWorkWifiOnly()) ? false : true;
        vm vmVar4 = vm.a;
        if (z) {
            contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            i = R.string.workWifi_dialog_content;
        } else {
            contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            i = R.string.cellular_consume_dialog_content;
        }
        skinTextView4.setText(vmVar4.e(contextRequireContext, i));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void g() {
        DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding = (DialogWorkOnlyWifiBinding) this.a;
        if (dialogWorkOnlyWifiBinding != null) {
            ConstraintLayout rootDialogWorkOnlyWifi = dialogWorkOnlyWifiBinding.b;
            Intrinsics.checkNotNullExpressionValue(rootDialogWorkOnlyWifi, "rootDialogWorkOnlyWifi");
            SkinTextView tvDialogSettingCleanButton = dialogWorkOnlyWifiBinding.d;
            Intrinsics.checkNotNullExpressionValue(tvDialogSettingCleanButton, "tvDialogSettingCleanButton");
            SkinTextView tvDialogSettingCancelButton = dialogWorkOnlyWifiBinding.c;
            Intrinsics.checkNotNullExpressionValue(tvDialogSettingCancelButton, "tvDialogSettingCancelButton");
            SkinView vDialogSettingCleanButton = dialogWorkOnlyWifiBinding.h;
            Intrinsics.checkNotNullExpressionValue(vDialogSettingCleanButton, "vDialogSettingCleanButton");
            SkinView vDialogSettingCancelButton = dialogWorkOnlyWifiBinding.g;
            Intrinsics.checkNotNullExpressionValue(vDialogSettingCancelButton, "vDialogSettingCancelButton");
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(rootDialogWorkOnlyWifi);
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
            constraintSet.applyTo(rootDialogWorkOnlyWifi);
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onDestroyView() {
        SkinTextView skinTextView;
        ViewTreeObserver viewTreeObserver;
        DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding = (DialogWorkOnlyWifiBinding) this.a;
        if (dialogWorkOnlyWifiBinding != null && (skinTextView = dialogWorkOnlyWifiBinding.e) != null && (viewTreeObserver = skinTextView.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnPreDrawListener(this.i);
        }
        super.onDestroyView();
    }
}
