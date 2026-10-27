package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Barker implements Serializable {

    @SerializedName("images")
    private List<Image> images = null;

    @SerializedName("subTitle")
    private String subTitle = null;

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("title")
    private String title = null;

    @SerializedName("altDescription")
    private String altDescription = null;

    @SerializedName("target")
    private String target = null;

    public List<Image> getImages() {
        return this.images;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAltDescription() {
        return this.altDescription;
    }

    public String getTarget() {
        return this.target;
    }
}
