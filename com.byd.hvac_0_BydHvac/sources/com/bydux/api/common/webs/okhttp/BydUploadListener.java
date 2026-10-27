package com.bydux.api.common.webs.okhttp;

import com.bydux.api.common.webs.okhttp.bean.UploadInfo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface BydUploadListener {
    void onNetworkDisconnect(int i);

    void onUploadChanged(UploadInfo uploadInfo);
}
