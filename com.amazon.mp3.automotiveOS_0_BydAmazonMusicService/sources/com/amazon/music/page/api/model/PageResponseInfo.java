package com.amazon.music.page.api.model;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public class PageResponseInfo implements Serializable {

    @SerializedName(Splash.PARAMS_DEVICE_TYPE)
    private String deviceType = null;

    @SerializedName(TetheredMessageKey.requestId)
    private String requestId = null;
}
