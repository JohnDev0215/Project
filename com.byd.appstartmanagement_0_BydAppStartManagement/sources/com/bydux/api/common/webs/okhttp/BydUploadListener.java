package com.bydux.api.common.webs.okhttp;

import com.bydux.api.common.webs.okhttp.bean.UploadInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface BydUploadListener {
    void onNetworkDisconnect(int i);

    void onUploadChanged(UploadInfo uploadInfo);
}
