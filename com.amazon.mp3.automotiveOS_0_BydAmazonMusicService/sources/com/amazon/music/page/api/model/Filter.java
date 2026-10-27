package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Filter {

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("entities")
    private List<Entity> entities = null;

    @SerializedName("id")
    private String id = null;

    @SerializedName("title")
    private String title = null;

    public String getBlockRef() {
        return this.blockRef;
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}
