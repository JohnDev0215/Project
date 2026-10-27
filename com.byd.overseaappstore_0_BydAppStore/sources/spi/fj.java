package spi;

import android.view.View;
import android.widget.ImageView;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelKt;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentKt;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.FragmentSoftwareDetailBinding;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.net.NetContextState;
import com.byd.overseaappstore.ui.view.dialog.DownloadDeleteDialog;
import com.byd.overseaappstore.ui.view.dialog.NotPBanDialog;
import com.byd.overseaappstore.ui.view.dialog.OnlyWifiOrCellularDialog;
import com.byd.overseaappstore.ui.view.dialog.PermissionAlertDialog;
import com.byd.overseaappstore.ui.view.dialog.ProtocolResetDialog;
import com.byd.overseaappstore.ui.view.dialog.SettingProtocolDialog;
import com.byd.overseaappstore.ui.view.dialog.StartUpProtocolDialog;
import com.byd.overseaappstore.ui.view.dialog.StorageLeakDialog;
import com.byd.overseaappstore.ui.view.dialog.UpdateDeleteDialog;
import com.byd.overseaappstore.ui.view.dialog.iuu.InstallSuccessDialog;
import com.byd.overseaappstore.ui.view.fragment.FeatureFragment;
import com.byd.overseaappstore.ui.view.fragment.SettingFragment;
import com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment;
import com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment;
import com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment;
import com.byd.overseaappstore.ui.vm.UpgradeViewModel;
import java.util.List;
import java.util.Objects;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class fj implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ fj(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View it) {
        ImageView imageView;
        int i;
        switch (this.a) {
            case 0:
                Navigation.m37createNavigateOnClickListener$lambda1((NavDirections) this.b, it);
                break;
            case 1:
                DownloadDeleteDialog this$0 = (DownloadDeleteDialog) this.b;
                int i2 = DownloadDeleteDialog.l;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Function1<? super View, Unit> function1 = this$0.e;
                if (function1 != null) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    function1.invoke(it);
                }
                this$0.dismissAllowingStateLoss();
                break;
            case 2:
                NotPBanDialog this$02 = (NotPBanDialog) this.b;
                int i3 = NotPBanDialog.j;
                Intrinsics.checkNotNullParameter(this$02, "this$0");
                this$02.dismissAllowingStateLoss();
                Function1<? super View, Unit> function12 = this$02.h;
                if (function12 != null) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    function12.invoke(it);
                }
                break;
            case 3:
                OnlyWifiOrCellularDialog this$03 = (OnlyWifiOrCellularDialog) this.b;
                int i4 = OnlyWifiOrCellularDialog.j;
                Intrinsics.checkNotNullParameter(this$03, "this$0");
                this$03.dismissAllowingStateLoss();
                Function1<? super Triple<NetContextState, ? extends List<SoftwareOptParam>, ? extends List<qq>>, Unit> function13 = this$03.f;
                if (function13 != null) {
                    function13.invoke(this$03.g);
                }
                break;
            case 4:
                PermissionAlertDialog this$04 = (PermissionAlertDialog) this.b;
                int i5 = PermissionAlertDialog.l;
                Intrinsics.checkNotNullParameter(this$04, "this$0");
                Function1<? super View, Unit> function14 = this$04.f;
                if (function14 != null) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    function14.invoke(it);
                }
                break;
            case 5:
                ProtocolResetDialog this$05 = (ProtocolResetDialog) this.b;
                int i6 = ProtocolResetDialog.k;
                Intrinsics.checkNotNullParameter(this$05, "this$0");
                Objects.requireNonNull(this$05);
                this$05.dismissAllowingStateLoss();
                break;
            case 6:
                SettingProtocolDialog this$06 = (SettingProtocolDialog) this.b;
                int i7 = SettingProtocolDialog.e;
                Intrinsics.checkNotNullParameter(this$06, "this$0");
                this$06.dismissAllowingStateLoss();
                break;
            case 7:
                StartUpProtocolDialog this$07 = (StartUpProtocolDialog) this.b;
                int i8 = StartUpProtocolDialog.e;
                Intrinsics.checkNotNullParameter(this$07, "this$0");
                Objects.requireNonNull(this$07);
                this$07.dismissAllowingStateLoss();
                break;
            case 8:
                StorageLeakDialog this$08 = (StorageLeakDialog) this.b;
                int i9 = StorageLeakDialog.h;
                Intrinsics.checkNotNullParameter(this$08, "this$0");
                Objects.requireNonNull(this$08);
                this$08.dismissAllowingStateLoss();
                break;
            case 9:
                UpdateDeleteDialog this$09 = (UpdateDeleteDialog) this.b;
                int i10 = UpdateDeleteDialog.l;
                Intrinsics.checkNotNullParameter(this$09, "this$0");
                Function1<? super View, Unit> function15 = this$09.f;
                if (function15 != null) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    function15.invoke(it);
                }
                this$09.dismissAllowingStateLoss();
                break;
            case 10:
                InstallSuccessDialog this$010 = (InstallSuccessDialog) this.b;
                int i11 = InstallSuccessDialog.e;
                Intrinsics.checkNotNullParameter(this$010, "this$0");
                this$010.dismissAllowingStateLoss();
                break;
            case 11:
                FeatureFragment this$011 = (FeatureFragment) this.b;
                int i12 = FeatureFragment.p;
                Intrinsics.checkNotNullParameter(this$011, "this$0");
                b.g(this$011, R.id.managementFragment);
                break;
            case 12:
                SettingFragment this$012 = (SettingFragment) this.b;
                int i13 = SettingFragment.k;
                Intrinsics.checkNotNullParameter(this$012, "this$0");
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$012), null, null, new bp(this$012, null), 3, null);
                break;
            case 13:
                SoftwareDetailFragment this$013 = (SoftwareDetailFragment) this.b;
                int i14 = SoftwareDetailFragment.o;
                Intrinsics.checkNotNullParameter(this$013, "this$0");
                FragmentKt.findNavController(this$013).navigateUp();
                break;
            case 14:
                FragmentSoftwareDetailBinding it2 = (FragmentSoftwareDetailBinding) this.b;
                int i15 = SoftwareDetailFragment.o;
                Intrinsics.checkNotNullParameter(it2, "$it");
                if (it2.s.getMaxLines() == 3) {
                    it2.s.setMaxLines(Integer.MAX_VALUE);
                    imageView = it2.e;
                    i = R.drawable.ic_overall_fold;
                } else {
                    it2.s.setMaxLines(3);
                    imageView = it2.e;
                    i = R.drawable.ic_overall_unfold;
                }
                imageView.setImageResource(i);
                break;
            case 15:
                ManagementFragment this$014 = (ManagementFragment) this.b;
                int i16 = ManagementFragment.l;
                Intrinsics.checkNotNullParameter(this$014, "this$0");
                FragmentKt.findNavController(this$014).navigateUp();
                break;
            default:
                UpdateListFragment this$015 = (UpdateListFragment) this.b;
                int i17 = UpdateListFragment.j;
                Intrinsics.checkNotNullParameter(this$015, "this$0");
                UpgradeViewModel upgradeViewModelI = this$015.i();
                CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(upgradeViewModelI);
                Objects.requireNonNull(upgradeViewModelI);
                Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new ju(upgradeViewModelI, null), 3, null);
                break;
        }
    }
}
