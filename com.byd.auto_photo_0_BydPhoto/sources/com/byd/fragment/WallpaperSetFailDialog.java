package com.byd.fragment;

import android.os.Bundle;
import android.view.View;
import com.byd.auto_photo.R;

/* JADX INFO: loaded from: classes2.dex */
public class WallpaperSetFailDialog extends AlertDialogFragment {
    public static WallpaperSetFailDialog newInstance(int i, int i2) {
        WallpaperSetFailDialog wallpaperSetFailDialog = new WallpaperSetFailDialog();
        wallpaperSetFailDialog.title = i;
        wallpaperSetFailDialog.msg = i2;
        return wallpaperSetFailDialog;
    }

    @Override // com.byd.fragment.AlertDialogFragment, com.byd.fragment.BaseDialogSuperFragment
    protected int getButtonConfig() {
        return 1;
    }

    @Override // com.byd.fragment.AlertDialogFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.msgContent = String.format(getResources().getString(R.string.video_wallpaper_tips_content), "MP4/3GP/MOV", 20, 100);
        super.onViewCreated(view, bundle);
    }
}
