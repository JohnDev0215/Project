package com.bydux.api.common.webs.okhttp;

import com.bydux.api.common.webs.okhttp.bean.UploadInfo;

/* JADX INFO: loaded from: classes.dex */
public interface BydUploadListener {
    void onNetworkDisconnect(final int fn);

    void onUploadChanged(UploadInfo uploadInfo);
}
