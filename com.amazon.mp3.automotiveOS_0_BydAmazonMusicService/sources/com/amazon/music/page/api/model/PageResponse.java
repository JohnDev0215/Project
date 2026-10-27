package com.amazon.music.page.api.model;

import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PageResponse implements Serializable {

    @SerializedName("page")
    private Page page = null;

    @SerializedName(MAPWebViewEventHelper.KEY_ERRORS)
    private List<String> errors = null;

    @SerializedName("info")
    private PageResponseInfo info = null;

    @SerializedName("maxCacheTtl")
    private Long maxCacheTtl = null;

    public Page getPage() {
        return this.page;
    }

    public Long maxCacheTtl() {
        return this.maxCacheTtl;
    }
}
