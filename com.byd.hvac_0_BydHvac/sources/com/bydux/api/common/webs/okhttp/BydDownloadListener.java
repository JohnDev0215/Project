package com.bydux.api.common.webs.okhttp;

import com.bydux.api.common.webs.okhttp.bean.DownloadInfo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface BydDownloadListener {
    void onDownloadChanged(DownloadInfo downloadInfo);

    void onNetworkDisconnect(int i);
}
