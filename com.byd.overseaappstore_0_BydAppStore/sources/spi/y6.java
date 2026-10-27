package spi;

import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelKt;
import androidx.navigation.fragment.FragmentKt;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import com.byd.overseaappstore.ui.view.dialog.DownloadDeleteDialog;
import com.byd.overseaappstore.ui.view.dialog.SettingCleanCacheDialog;
import com.byd.overseaappstore.ui.view.dialog.SettingProtocolDialog;
import com.byd.overseaappstore.ui.view.dialog.iuu.IUUDialog;
import com.byd.overseaappstore.ui.view.dialog.iuu.InstallSuccessDialog;
import com.byd.overseaappstore.ui.view.fragment.FeatureFragment;
import com.byd.overseaappstore.ui.view.fragment.SettingFragment;
import com.byd.overseaappstore.ui.view.fragment.SoftwareDetailPreviewImageFragment;
import com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment;
import com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment;
import com.byd.overseaappstore.ui.vm.FeatureViewModel;
import com.byd.overseaappstore.ui.vm.UpgradeViewModel;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class y6 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Fragment b;

    public /* synthetic */ y6(Fragment fragment, int i) {
        this.a = i;
        this.b = fragment;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View it) {
        switch (this.a) {
            case 0:
                DownloadDeleteDialog this$0 = (DownloadDeleteDialog) this.b;
                int i = DownloadDeleteDialog.l;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Function1<? super View, Unit> function1 = this$0.f;
                if (function1 != null) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    function1.invoke(it);
                }
                this$0.dismissAllowingStateLoss();
                break;
            case 1:
                SettingCleanCacheDialog this$02 = (SettingCleanCacheDialog) this.b;
                int i2 = SettingCleanCacheDialog.f;
                Intrinsics.checkNotNullParameter(this$02, "this$0");
                this$02.dismissAllowingStateLoss();
                SettingCleanCacheDialog.a aVar = this$02.e;
                if (aVar != null) {
                    aVar.a();
                }
                break;
            case 2:
                SettingProtocolDialog this$03 = (SettingProtocolDialog) this.b;
                int i3 = SettingProtocolDialog.e;
                Intrinsics.checkNotNullParameter(this$03, "this$0");
                Objects.requireNonNull(this$03);
                break;
            case 3:
                IUUDialog this$04 = (IUUDialog) this.b;
                int i4 = IUUDialog.h;
                Intrinsics.checkNotNullParameter(this$04, "this$0");
                this$04.dismissAllowingStateLoss();
                Function1<? super IUUState, Unit> function12 = this$04.e;
                if (function12 != null) {
                    function12.invoke(this$04.g);
                }
                break;
            case 4:
                InstallSuccessDialog this$05 = (InstallSuccessDialog) this.b;
                int i5 = InstallSuccessDialog.e;
                Intrinsics.checkNotNullParameter(this$05, "this$0");
                this$05.dismissAllowingStateLoss();
                break;
            case 5:
                FeatureFragment this$06 = (FeatureFragment) this.b;
                int i6 = FeatureFragment.p;
                Intrinsics.checkNotNullParameter(this$06, "this$0");
                FeatureViewModel.b(this$06.i());
                break;
            case 6:
                SettingFragment this$07 = (SettingFragment) this.b;
                int i7 = SettingFragment.k;
                Intrinsics.checkNotNullParameter(this$07, "this$0");
                FragmentKt.findNavController(this$07).navigateUp();
                break;
            case 7:
                SoftwareDetailPreviewImageFragment this$08 = (SoftwareDetailPreviewImageFragment) this.b;
                int i8 = SoftwareDetailPreviewImageFragment.p;
                Intrinsics.checkNotNullParameter(this$08, "this$0");
                FragmentKt.findNavController(this$08).navigateUp();
                break;
            case 8:
                DownloadListFragment this$09 = (DownloadListFragment) this.b;
                int i9 = DownloadListFragment.j;
                Intrinsics.checkNotNullParameter(this$09, "this$0");
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$09), null, null, new b7(this$09, null), 3, null);
                break;
            default:
                UpdateListFragment this$010 = (UpdateListFragment) this.b;
                int i10 = UpdateListFragment.j;
                Intrinsics.checkNotNullParameter(this$010, "this$0");
                UpgradeViewModel upgradeViewModelI = this$010.i();
                CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(upgradeViewModelI);
                Objects.requireNonNull(upgradeViewModelI);
                Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new iu(upgradeViewModelI, null), 3, null);
                break;
        }
    }
}
