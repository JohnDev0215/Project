package com.bydux.api.common.webs.okhttp;

import com.bydux.api.common.webs.okhttp.bean.DownloadInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface BydDownloadListener {
    void onDownloadChanged(DownloadInfo downloadInfo);

    void onNetworkDisconnect(int i8);
}
