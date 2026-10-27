package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetContent implements Serializable {

    @SerializedName("entities")
    private List<Entity> entities = null;

    @SerializedName("nextToken")
    private String nextToken = null;

    public List<Entity> getEntities() {
        return this.entities;
    }

    public String getNextToken() {
        return this.nextToken;
    }
}
