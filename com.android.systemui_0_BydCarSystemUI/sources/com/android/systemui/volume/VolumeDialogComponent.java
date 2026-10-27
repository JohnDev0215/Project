package com.android.systemui.volume;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.AsyncTask;
import com.android.systemui.R;
import com.android.systemui.statusbar.phone.LauncherSelectDialog;
import com.android.systemui.util.InterestingConfigChanges;
import com.android.systemui.wireless.WirelessDialog;
import com.byd.card.utils.BitmapUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class VolumeDialogComponent implements VolumeComponent {
    private final InterestingConfigChanges mConfigChanges;
    private final Context mContext;
    private VolumeDialog mDialog;
    private LauncherSelectDialog mLauncherSelectDialog;
    private WirelessDialog mWirelessDialog;

    @Override // com.android.systemui.volume.VolumeComponent
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    public VolumeDialogComponent(Context context) {
        InterestingConfigChanges interestingConfigChanges = new InterestingConfigChanges(-1073740924);
        this.mConfigChanges = interestingConfigChanges;
        this.mContext = context;
        interestingConfigChanges.applyNewConfig(context.getResources());
        VolumeDialog volumeDialogCreateDefault = createDefault();
        this.mDialog = volumeDialogCreateDefault;
        volumeDialogCreateDefault.init();
    }

    @Override // com.android.systemui.volume.VolumeComponent
    public void showLauncherDialog() {
        if (this.mLauncherSelectDialog == null) {
            this.mLauncherSelectDialog = new LauncherSelectDialog(this.mContext);
        }
        if (this.mLauncherSelectDialog.isShowing()) {
            return;
        }
        new BlurViewTask().execute(new Void[0]);
    }

    @Override // com.android.systemui.volume.VolumeComponent
    public void dismissLauncherDialog() {
        LauncherSelectDialog launcherSelectDialog = this.mLauncherSelectDialog;
        if (launcherSelectDialog != null) {
            launcherSelectDialog.dismiss();
        }
        this.mLauncherSelectDialog = null;
    }

    @Override // com.android.systemui.volume.VolumeComponent
    public void showWirelessDialog() {
        if (this.mWirelessDialog == null) {
            this.mWirelessDialog = new WirelessDialog(this.mContext);
        }
        if (this.mWirelessDialog.isShowing()) {
            return;
        }
        this.mWirelessDialog.show();
    }

    @Override // com.android.systemui.volume.VolumeComponent
    public void dismissWirelessDialog() {
        WirelessDialog wirelessDialog = this.mWirelessDialog;
        if (wirelessDialog != null) {
            wirelessDialog.dismiss();
        }
        this.mWirelessDialog = null;
    }

    @Override // com.android.systemui.volume.VolumeComponent
    public void setWirelessPowerLevel(boolean level) {
        WirelessDialog wirelessDialog = this.mWirelessDialog;
        if (wirelessDialog != null) {
            wirelessDialog.setPowerLevel(level);
        }
    }

    private VolumeDialog createDefault() {
        return new VolumeDialogImpl(this.mContext);
    }

    @Override // com.android.systemui.volume.VolumeComponent
    public void onConfigurationChanged(Configuration newConfig) {
        BydLogHelper.logD("onConfigurationChanged : " + newConfig.toString());
        boolean zApplyNewConfig = this.mConfigChanges.applyNewConfig(this.mContext.getResources());
        BydLogHelper.logD("isChange : " + zApplyNewConfig);
        VolumeDialog volumeDialog = this.mDialog;
        if (volumeDialog != null) {
            volumeDialog.updateDialog();
        }
        if (zApplyNewConfig) {
            LauncherSelectDialog launcherSelectDialog = this.mLauncherSelectDialog;
            if (launcherSelectDialog != null) {
                launcherSelectDialog.onCreate();
                this.mLauncherSelectDialog.setBlurBitmap();
            }
            WirelessDialog wirelessDialog = this.mWirelessDialog;
            if (wirelessDialog != null) {
                wirelessDialog.onCreate();
            }
        }
    }

    @Override // com.android.systemui.volume.VolumeComponent
    public void dismiss() {
        VolumeDialog volumeDialog = this.mDialog;
        if (volumeDialog != null) {
            volumeDialog.dismiss();
        }
    }

    public class BlurViewTask extends AsyncTask<Void, Void, Bitmap> {
        public BlurViewTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(Void... voids) {
            VolumeDialogComponent volumeDialogComponent = VolumeDialogComponent.this;
            if (volumeDialogComponent.isLand(volumeDialogComponent.mContext)) {
                return BitmapUtils.getScreenshot1(VolumeDialogComponent.this.mContext, new Rect(VolumeDialogComponent.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_32), VolumeDialogComponent.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_75), VolumeDialogComponent.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_1248), VolumeDialogComponent.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_645)), VolumeDialogComponent.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_1232), VolumeDialogComponent.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_570));
            }
            return BitmapUtils.getScreenshot1(VolumeDialogComponent.this.mContext, new Rect(VolumeDialogComponent.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_24), VolumeDialogComponent.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_76), VolumeDialogComponent.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_696), VolumeDialogComponent.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_1203)), VolumeDialogComponent.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_672), VolumeDialogComponent.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_1128));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap blurBitmap) {
            if (VolumeDialogComponent.this.mLauncherSelectDialog != null) {
                VolumeDialogComponent.this.mLauncherSelectDialog.setBlurBitmap(blurBitmap);
                VolumeDialogComponent.this.mLauncherSelectDialog.show();
            }
        }
    }

    public boolean isLand(Context context) {
        return context.getResources().getConfiguration().orientation != 1;
    }
}
