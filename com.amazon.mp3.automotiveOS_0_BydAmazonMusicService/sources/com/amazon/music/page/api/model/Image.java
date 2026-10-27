package com.amazon.music.page.api.model;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public class Image implements Serializable {

    @SerializedName("src")
    private String src = null;

    @SerializedName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)
    private ImageType type = null;

    public String getSrc() {
        return this.src;
    }

    public ImageType getType() {
        return this.type;
    }
}
