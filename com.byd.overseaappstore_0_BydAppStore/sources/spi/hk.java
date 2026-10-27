package spi;

import android.view.ViewTreeObserver;
import androidx.core.view.GravityCompat;
import com.byd.overseaappstore.databinding.DialogPermissionAlertBinding;
import com.byd.overseaappstore.databinding.DialogTwoButtonConfirmBinding;
import com.byd.overseaappstore.databinding.DialogWorkOnlyWifiBinding;
import com.byd.overseaappstore.ui.view.base.BaseDialog;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.dialog.OnlyWifiOrCellularDialog;
import com.byd.overseaappstore.ui.view.dialog.PermissionAlertDialog;
import com.byd.overseaappstore.ui.view.dialog.ProtocolResetDialog;
import com.byd.overseaappstore.ui.view.dialog.UpdateDeleteDialog;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class hk implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ BaseDialog b;

    public /* synthetic */ hk(BaseDialog baseDialog, int i) {
        this.a = i;
        this.b = baseDialog;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        switch (this.a) {
            case 0:
                OnlyWifiOrCellularDialog this$0 = (OnlyWifiOrCellularDialog) this.b;
                int i = OnlyWifiOrCellularDialog.j;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                DialogWorkOnlyWifiBinding dialogWorkOnlyWifiBinding = (DialogWorkOnlyWifiBinding) this$0.a;
                if (dialogWorkOnlyWifiBinding != null) {
                    int lineCount = dialogWorkOnlyWifiBinding.e.getLineCount();
                    SkinTextView skinTextView = dialogWorkOnlyWifiBinding.e;
                    if (lineCount != 1) {
                        skinTextView.setGravity(GravityCompat.START);
                    } else {
                        skinTextView.setGravity(1);
                    }
                }
                break;
            case 1:
                PermissionAlertDialog this$02 = (PermissionAlertDialog) this.b;
                int i2 = PermissionAlertDialog.l;
                Intrinsics.checkNotNullParameter(this$02, "this$0");
                DialogPermissionAlertBinding dialogPermissionAlertBinding = (DialogPermissionAlertBinding) this$02.a;
                if (dialogPermissionAlertBinding != null) {
                    int lineCount2 = dialogPermissionAlertBinding.b.getLineCount();
                    SkinAppCompatTextView skinAppCompatTextView = dialogPermissionAlertBinding.b;
                    if (lineCount2 <= 1) {
                        skinAppCompatTextView.setGravity(1);
                    } else {
                        skinAppCompatTextView.setGravity(GravityCompat.START);
                    }
                }
                break;
            case 2:
                ProtocolResetDialog this$03 = (ProtocolResetDialog) this.b;
                int i3 = ProtocolResetDialog.k;
                Intrinsics.checkNotNullParameter(this$03, "this$0");
                DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding = (DialogTwoButtonConfirmBinding) this$03.a;
                if (dialogTwoButtonConfirmBinding != null) {
                    int lineCount3 = dialogTwoButtonConfirmBinding.b.getLineCount();
                    SkinAppCompatTextView skinAppCompatTextView2 = dialogTwoButtonConfirmBinding.b;
                    if (lineCount3 <= 1) {
                        skinAppCompatTextView2.setGravity(1);
                    } else {
                        skinAppCompatTextView2.setGravity(GravityCompat.START);
                    }
                }
                break;
            default:
                UpdateDeleteDialog this$04 = (UpdateDeleteDialog) this.b;
                int i4 = UpdateDeleteDialog.l;
                Intrinsics.checkNotNullParameter(this$04, "this$0");
                DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding2 = (DialogTwoButtonConfirmBinding) this$04.a;
                if (dialogTwoButtonConfirmBinding2 != null) {
                    int lineCount4 = dialogTwoButtonConfirmBinding2.b.getLineCount();
                    SkinAppCompatTextView skinAppCompatTextView3 = dialogTwoButtonConfirmBinding2.b;
                    if (lineCount4 <= 1) {
                        skinAppCompatTextView3.setGravity(1);
                    } else {
                        skinAppCompatTextView3.setGravity(GravityCompat.START);
                    }
                }
                break;
        }
        return true;
    }
}
