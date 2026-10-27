package com.byd.overseaappstore.ui.view.dialog.iuu;

import android.content.Context;
import android.content.res.Configuration;
import androidx.fragment.app.FragmentManager;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.DialogIuuProcessBinding;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import com.byd.overseaappstore.ui.view.base.BaseDialog;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/dialog/iuu/IUUProcessDialog;", "Lcom/byd/overseaappstore/ui/view/base/BaseDialog;", "Lcom/byd/overseaappstore/databinding/DialogIuuProcessBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class IUUProcessDialog extends BaseDialog<DialogIuuProcessBinding> {

    @Nullable
    public IUUState e;

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final ViewBinding b() {
        DialogIuuProcessBinding dialogIuuProcessBindingA = DialogIuuProcessBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(dialogIuuProcessBindingA, "inflate(layoutInflater)");
        return dialogIuuProcessBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void c() {
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e  */
    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d() {
        /*
            r5 = this;
            com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState r0 = r5.e
            if (r0 == 0) goto Ld
            int r0 = r0.getState()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto Le
        Ld:
            r0 = 0
        Le:
            r1 = 3
            r2 = 2131689556(0x7f0f0054, float:1.900813E38)
            java.lang.String r3 = "requireContext().applicationContext"
            if (r0 != 0) goto L18
            goto L30
        L18:
            int r4 = r0.intValue()
            if (r4 != r1) goto L30
        L1e:
            spi.vm r0 = spi.vm.a
            android.content.Context r1 = r5.requireContext()
            android.content.Context r1 = r1.getApplicationContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
        L2b:
            java.lang.String r0 = r0.e(r1, r2)
            goto L66
        L30:
            r1 = 4
            if (r0 != 0) goto L34
            goto L4b
        L34:
            int r4 = r0.intValue()
            if (r4 != r1) goto L4b
            spi.vm r0 = spi.vm.a
            android.content.Context r1 = r5.requireContext()
            android.content.Context r1 = r1.getApplicationContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            r2 = 2131689565(0x7f0f005d, float:1.9008149E38)
            goto L2b
        L4b:
            r1 = 5
            if (r0 != 0) goto L4f
            goto L1e
        L4f:
            int r0 = r0.intValue()
            if (r0 != r1) goto L1e
            spi.vm r0 = spi.vm.a
            android.content.Context r1 = r5.requireContext()
            android.content.Context r1 = r1.getApplicationContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            r2 = 2131689563(0x7f0f005b, float:1.9008145E38)
            goto L2b
        L66:
            VB extends androidx.viewbinding.ViewBinding r1 = r5.a
            com.byd.overseaappstore.databinding.DialogIuuProcessBinding r1 = (com.byd.overseaappstore.databinding.DialogIuuProcessBinding) r1
            if (r1 == 0) goto L71
            com.byd.overseaappstore.ui.view.custom.skin.SkinTextView r1 = r1.b
            r1.setText(r0)
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.view.dialog.iuu.IUUProcessDialog.d():void");
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void e(@NotNull Configuration newConfig) {
        SkinTextView skinTextView;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        DialogIuuProcessBinding dialogIuuProcessBinding = (DialogIuuProcessBinding) this.a;
        SkinView skinView = dialogIuuProcessBinding != null ? dialogIuuProcessBinding.c : null;
        if (skinView != null) {
            vm vmVar = vm.a;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            skinView.setBackground(vmVar.c(contextRequireContext, R.drawable.bg_setting_dialog));
        }
        DialogIuuProcessBinding dialogIuuProcessBinding2 = (DialogIuuProcessBinding) this.a;
        if (dialogIuuProcessBinding2 == null || (skinTextView = dialogIuuProcessBinding2.b) == null) {
            return;
        }
        skinTextView.setStyleTextColor(R.color.iuu_process_text);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void f(@NotNull Configuration newConfig) {
        SkinTextView skinTextView;
        vm vmVar;
        Context applicationContext;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        IUUState iUUState = this.e;
        Integer numValueOf = iUUState != null ? Integer.valueOf(iUUState.getState()) : null;
        int i = R.string.iuu_installing_content;
        if (numValueOf != null && numValueOf.intValue() == 3) {
            DialogIuuProcessBinding dialogIuuProcessBinding = (DialogIuuProcessBinding) this.a;
            skinTextView = dialogIuuProcessBinding != null ? dialogIuuProcessBinding.b : null;
            if (skinTextView == null) {
                return;
            }
            vmVar = vm.a;
            applicationContext = requireContext().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "requireContext().applicationContext");
        } else if (numValueOf != null && numValueOf.intValue() == 4) {
            DialogIuuProcessBinding dialogIuuProcessBinding2 = (DialogIuuProcessBinding) this.a;
            skinTextView = dialogIuuProcessBinding2 != null ? dialogIuuProcessBinding2.b : null;
            if (skinTextView == null) {
                return;
            }
            vmVar = vm.a;
            applicationContext = requireContext().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "requireContext().applicationContext");
            i = R.string.iuu_updating_content;
        } else {
            if (numValueOf == null || numValueOf.intValue() != 5) {
                vm vmVar2 = vm.a;
                Context applicationContext2 = requireContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext2, "requireContext().applicationContext");
                vmVar2.e(applicationContext2, R.string.iuu_installing_content);
                return;
            }
            DialogIuuProcessBinding dialogIuuProcessBinding3 = (DialogIuuProcessBinding) this.a;
            skinTextView = dialogIuuProcessBinding3 != null ? dialogIuuProcessBinding3.b : null;
            if (skinTextView == null) {
                return;
            }
            vmVar = vm.a;
            applicationContext = requireContext().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "requireContext().applicationContext");
            i = R.string.iuu_uninstalling_content;
        }
        skinTextView.setText(vmVar.e(applicationContext, i));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog
    public final void g() {
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseDialog, androidx.fragment.app.DialogFragment
    public final void show(@NotNull FragmentManager manager, @Nullable String str) {
        SoftwareOptParam softwareOptParam;
        Intrinsics.checkNotNullParameter(manager, "manager");
        if (!isAdded()) {
            super.show(manager, str);
            return;
        }
        IUUState iUUState = this.e;
        String packageName = null;
        Integer numValueOf = iUUState != null ? Integer.valueOf(iUUState.getState()) : null;
        IUUState iUUState2 = this.e;
        if (iUUState2 != null && (softwareOptParam = iUUState2.getSoftwareOptParam()) != null) {
            packageName = softwareOptParam.getPackageName();
        }
        ct.b("iuu collect iUUState InstallProcessDialog alreadyAdd do nothing operateState:" + numValueOf + " pkn:" + packageName, new Object[0]);
    }
}
