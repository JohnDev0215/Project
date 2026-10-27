package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes4.dex */
public class AssetQuality implements Serializable {

    @SerializedName("playbackId")
    private String playbackId = null;

    @SerializedName("pid")
    private BigDecimal pid = null;

    @SerializedName("quality")
    private String quality = null;

    public String getPlaybackId() {
        return this.playbackId;
    }

    public String getQuality() {
        return this.quality;
    }
}
