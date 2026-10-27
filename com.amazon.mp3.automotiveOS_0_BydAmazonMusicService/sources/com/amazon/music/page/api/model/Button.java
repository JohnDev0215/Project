package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Button implements Serializable {

    @SerializedName("images")
    private List<Image> images = null;

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("isSelected")
    private Boolean isSelected = null;

    @SerializedName("id")
    private String id = null;

    @SerializedName("title")
    private String title = null;

    @SerializedName("target")
    private String target = null;

    public List<Image> getImages() {
        return this.images;
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public Boolean getIsSelected() {
        return this.isSelected;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTarget() {
        return this.target;
    }
}
