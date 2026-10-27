package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Page implements Serializable {

    @SerializedName("images")
    private List<Image> images = null;

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("header")
    private Header header = null;

    @SerializedName("filters")
    private List<Filter> filters = null;

    @SerializedName("title")
    private String title = null;

    @SerializedName("secondaryTitle")
    private String secondaryTitle = null;

    @SerializedName("tertiaryTitle")
    private String tertiaryTitle = null;

    @SerializedName("uri")
    private String uri = null;

    @SerializedName("entity")
    private Entity entity = null;

    @SerializedName("content")
    private PageContent content = null;

    public List<Image> getImages() {
        return this.images;
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public Header getHeader() {
        return this.header;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSecondaryTitle() {
        return this.secondaryTitle;
    }

    public String getTertiaryTitle() {
        return this.tertiaryTitle;
    }

    public String getUri() {
        return this.uri;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public PageContent getContent() {
        return this.content;
    }
}
