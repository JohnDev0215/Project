package spi;

import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.navigation.fragment.FragmentKt;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.FragmentSoftwareDetailBinding;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import com.byd.overseaappstore.module.ability.util.notify.router.net.NetContextState;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.view.dialog.OnlyWifiOrCellularDialog;
import com.byd.overseaappstore.ui.view.dialog.PermissionAlertDialog;
import com.byd.overseaappstore.ui.view.dialog.ProtocolResetDialog;
import com.byd.overseaappstore.ui.view.dialog.SettingCleanCacheDialog;
import com.byd.overseaappstore.ui.view.dialog.StartUpProtocolDialog;
import com.byd.overseaappstore.ui.view.dialog.UpdateDeleteDialog;
import com.byd.overseaappstore.ui.view.dialog.iuu.IUUDialog;
import com.byd.overseaappstore.ui.view.fragment.FeatureFragment;
import com.byd.overseaappstore.ui.view.fragment.SettingFragment;
import com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment;
import com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment;
import com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment;
import com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment;
import com.byd.overseaappstore.ui.vm.SettingViewModel;
import java.util.List;
import java.util.Objects;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class gk implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ gk(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View it) {
        SkinImageView skinImageView;
        int i;
        switch (this.a) {
            case 0:
                OnlyWifiOrCellularDialog this$0 = (OnlyWifiOrCellularDialog) this.b;
                int i2 = OnlyWifiOrCellularDialog.j;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                this$0.dismissAllowingStateLoss();
                Function1<? super Triple<NetContextState, ? extends List<SoftwareOptParam>, ? extends List<qq>>, Unit> function1 = this$0.e;
                if (function1 != null) {
                    function1.invoke(this$0.g);
                }
                break;
            case 1:
                PermissionAlertDialog this$02 = (PermissionAlertDialog) this.b;
                int i3 = PermissionAlertDialog.l;
                Intrinsics.checkNotNullParameter(this$02, "this$0");
                Function1<? super View, Unit> function12 = this$02.e;
                if (function12 != null) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    function12.invoke(it);
                }
                break;
            case 2:
                ProtocolResetDialog this$03 = (ProtocolResetDialog) this.b;
                int i4 = ProtocolResetDialog.k;
                Intrinsics.checkNotNullParameter(this$03, "this$0");
                Function1<? super View, Unit> function13 = this$03.e;
                if (function13 != null) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    function13.invoke(it);
                }
                this$03.dismissAllowingStateLoss();
                break;
            case 3:
                SettingCleanCacheDialog this$04 = (SettingCleanCacheDialog) this.b;
                int i5 = SettingCleanCacheDialog.f;
                Intrinsics.checkNotNullParameter(this$04, "this$0");
                this$04.dismissAllowingStateLoss();
                SettingCleanCacheDialog.a aVar = this$04.e;
                if (aVar != null) {
                    aVar.b();
                }
                break;
            case 4:
                StartUpProtocolDialog this$05 = (StartUpProtocolDialog) this.b;
                int i6 = StartUpProtocolDialog.e;
                Intrinsics.checkNotNullParameter(this$05, "this$0");
                Objects.requireNonNull(this$05);
                this$05.dismissAllowingStateLoss();
                break;
            case 5:
                UpdateDeleteDialog this$06 = (UpdateDeleteDialog) this.b;
                int i7 = UpdateDeleteDialog.l;
                Intrinsics.checkNotNullParameter(this$06, "this$0");
                Function1<? super View, Unit> function14 = this$06.e;
                if (function14 != null) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    function14.invoke(it);
                }
                this$06.dismissAllowingStateLoss();
                break;
            case 6:
                IUUDialog this$07 = (IUUDialog) this.b;
                int i8 = IUUDialog.h;
                Intrinsics.checkNotNullParameter(this$07, "this$0");
                this$07.dismissAllowingStateLoss();
                Function1<? super IUUState, Unit> function15 = this$07.f;
                if (function15 != null) {
                    function15.invoke(this$07.g);
                }
                break;
            case 7:
                FeatureFragment this$08 = (FeatureFragment) this.b;
                int i9 = FeatureFragment.p;
                Intrinsics.checkNotNullParameter(this$08, "this$0");
                b.g(this$08, R.id.searchActiveFragment);
                break;
            case 8:
                SettingFragment this$09 = (SettingFragment) this.b;
                int i10 = SettingFragment.k;
                Intrinsics.checkNotNullParameter(this$09, "this$0");
                if (((SettingViewModel) this$09.j.getValue()).j.getValue().longValue() != 0) {
                    SettingCleanCacheDialog settingCleanCacheDialog = new SettingCleanCacheDialog();
                    settingCleanCacheDialog.e = new cp(this$09);
                    FragmentManager supportFragmentManager = this$09.requireActivity().getSupportFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
                    settingCleanCacheDialog.show(supportFragmentManager, SettingCleanCacheDialog.class.getName());
                } else {
                    Toast.makeText(this$09.getContext(), this$09.getResources().getText(R.string.tv_setting_toast_no_cache), 0).show();
                }
                break;
            case 9:
                FragmentSoftwareDetailBinding it2 = (FragmentSoftwareDetailBinding) this.b;
                int i11 = SoftwareDetailFragment.o;
                Intrinsics.checkNotNullParameter(it2, "$it");
                if (it2.m.getMaxLines() == 3) {
                    it2.m.setMaxLines(Integer.MAX_VALUE);
                    skinImageView = it2.d;
                    i = R.drawable.ic_overall_fold;
                } else {
                    it2.m.setMaxLines(3);
                    skinImageView = it2.d;
                    i = R.drawable.ic_overall_unfold;
                }
                skinImageView.setImageResource(i);
                break;
            case 10:
                TopicDetailFragment this$010 = (TopicDetailFragment) this.b;
                int i12 = TopicDetailFragment.n;
                Intrinsics.checkNotNullParameter(this$010, "this$0");
                FragmentKt.findNavController(this$010).navigateUp();
                break;
            case 11:
                DownloadListFragment this$011 = (DownloadListFragment) this.b;
                int i13 = DownloadListFragment.j;
                Intrinsics.checkNotNullParameter(this$011, "this$0");
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$011), null, null, new c7(this$011, null), 3, null);
                break;
            default:
                ManagementFragment this$012 = (ManagementFragment) this.b;
                int i14 = ManagementFragment.l;
                Intrinsics.checkNotNullParameter(this$012, "this$0");
                b.g(this$012, R.id.settingFragment);
                break;
        }
    }
}
