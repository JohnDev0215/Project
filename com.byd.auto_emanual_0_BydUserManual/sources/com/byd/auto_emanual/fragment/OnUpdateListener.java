package com.byd.auto_emanual.fragment;

import com.byd.auto_emanual.update.NewVersionInfo;

/* JADX INFO: loaded from: classes.dex */
public interface OnUpdateListener {
    void onCheckUpdateError(String msg);

    void onDownloadFail(int updateStyle);

    void onDownloadOk(String savePath, int updateStyle);

    void onDownloadProgress(int progress);

    void onNewVersion(NewVersionInfo info, boolean checkInBackground);
}
