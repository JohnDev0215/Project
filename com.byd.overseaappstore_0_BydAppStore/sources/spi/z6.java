package spi;

import android.view.ViewTreeObserver;
import androidx.core.view.GravityCompat;
import com.byd.overseaappstore.databinding.DialogOneButtonConfirmBinding;
import com.byd.overseaappstore.databinding.DialogTwoButtonConfirmBinding;
import com.byd.overseaappstore.ui.view.base.BaseDialog;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.dialog.DownloadDeleteDialog;
import com.byd.overseaappstore.ui.view.dialog.NotPBanDialog;
import com.byd.overseaappstore.ui.view.dialog.StorageLeakDialog;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class z6 implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ BaseDialog b;

    public /* synthetic */ z6(BaseDialog baseDialog, int i) {
        this.a = i;
        this.b = baseDialog;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        switch (this.a) {
            case 0:
                DownloadDeleteDialog this$0 = (DownloadDeleteDialog) this.b;
                int i = DownloadDeleteDialog.l;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                DialogTwoButtonConfirmBinding dialogTwoButtonConfirmBinding = (DialogTwoButtonConfirmBinding) this$0.a;
                if (dialogTwoButtonConfirmBinding != null) {
                    int lineCount = dialogTwoButtonConfirmBinding.b.getLineCount();
                    SkinAppCompatTextView skinAppCompatTextView = dialogTwoButtonConfirmBinding.b;
                    if (lineCount <= 1) {
                        skinAppCompatTextView.setGravity(1);
                    } else {
                        skinAppCompatTextView.setGravity(GravityCompat.START);
                    }
                }
                break;
            case 1:
                NotPBanDialog this$02 = (NotPBanDialog) this.b;
                int i2 = NotPBanDialog.j;
                Intrinsics.checkNotNullParameter(this$02, "this$0");
                DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding = (DialogOneButtonConfirmBinding) this$02.a;
                if (dialogOneButtonConfirmBinding != null) {
                    int lineCount2 = dialogOneButtonConfirmBinding.b.getLineCount();
                    SkinTextView skinTextView = dialogOneButtonConfirmBinding.b;
                    if (lineCount2 != 1) {
                        skinTextView.setGravity(GravityCompat.START);
                    } else {
                        skinTextView.setGravity(1);
                    }
                }
                break;
            default:
                StorageLeakDialog this$03 = (StorageLeakDialog) this.b;
                int i3 = StorageLeakDialog.h;
                Intrinsics.checkNotNullParameter(this$03, "this$0");
                DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding2 = (DialogOneButtonConfirmBinding) this$03.a;
                if (dialogOneButtonConfirmBinding2 != null) {
                    int lineCount3 = dialogOneButtonConfirmBinding2.b.getLineCount();
                    SkinTextView skinTextView2 = dialogOneButtonConfirmBinding2.b;
                    if (lineCount3 != 1) {
                        skinTextView2.setGravity(GravityCompat.START);
                    } else {
                        skinTextView2.setGravity(1);
                    }
                }
                break;
        }
        return true;
    }
}
