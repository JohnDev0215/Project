package com.neusoft.na.mots;

import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;
import android.widget.RelativeLayout;
import com.neusoft.na.common.BasicPrivacyView;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016¨\u0006\u0006"}, d2 = {"com/neusoft/na/mots/MainActivity$showPrivacyPolicy$1$2", "Lcom/neusoft/na/common/BasicPrivacyView$IPrivacyViewListener;", "onClickAgree", "", "onClickCancel", "onClickChange", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MainActivity$showPrivacyPolicy$1$2 implements BasicPrivacyView.IPrivacyViewListener {
    final /* synthetic */ BasicPrivacyView $it;
    final /* synthetic */ MainActivity this$0;

    MainActivity$showPrivacyPolicy$1$2(MainActivity mainActivity, BasicPrivacyView basicPrivacyView) {
        this.this$0 = mainActivity;
        this.$it = basicPrivacyView;
    }

    @Override // com.neusoft.na.common.BasicPrivacyView.IPrivacyViewListener
    public void onClickAgree() {
        final MainActivity mainActivity = this.this$0;
        final BasicPrivacyView basicPrivacyView = this.$it;
        mainActivity.runOnUiThread(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$showPrivacyPolicy$1$2$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws RemoteException {
                MainActivity$showPrivacyPolicy$1$2.onClickAgree$lambda$0(mainActivity, basicPrivacyView);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClickAgree$lambda$0(MainActivity this$0, BasicPrivacyView it) throws RemoteException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        FileUtils.getInstance(this$0.getApplicationContext()).updatePrivacyStatus(true);
        if (this$0.isBydHasAll()) {
            this$0.startMotsMainService();
            this$0.sendNaviActivityStartBC();
        } else {
            this$0.checkStartMots();
        }
        RelativeLayout relativeLayout = (RelativeLayout) this$0._$_findCachedViewById(R.id.appRoot);
        if (relativeLayout != null) {
            relativeLayout.removeView(it);
        }
        Log.i("djy", "removeView done");
    }

    @Override // com.neusoft.na.common.BasicPrivacyView.IPrivacyViewListener
    public void onClickCancel() {
        if (CheckBoardKt.isByd8155f() && this.this$0.isByd8155Launcher()) {
            this.this$0.isByd8155Kill = true;
            this.this$0.sendBroadcast(new Intent("com.neusoft.na.navigation.switchToWallpaperDesktop"));
        } else {
            this.this$0.finish();
        }
    }

    @Override // com.neusoft.na.common.BasicPrivacyView.IPrivacyViewListener
    public void onClickChange() {
        Log.i(MainActivity.TAG, "onClickChange");
        this.this$0.sendBroadcast(new Intent("com.neusoft.na.navigation.switchToWallpaperDesktop"));
    }
}
