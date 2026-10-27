package com.amazon.music.page.api.model;

import com.google.android.gms.cast.MediaTrack;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Widget implements Serializable {

    @SerializedName("button")
    private Button button = null;

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("renderingType")
    private RenderingType renderingType = null;

    @SerializedName(MediaTrack.ROLE_SUBTITLE)
    private String subtitle = null;

    @SerializedName("id")
    private String id = null;

    @SerializedName("title")
    private String title = null;

    @SerializedName("content")
    private WidgetContent content = null;

    @SerializedName("tags")
    private List<WidgetTag> tags = null;

    public Button getButton() {
        return this.button;
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public RenderingType getRenderingType() {
        return this.renderingType;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public WidgetContent getContent() {
        return this.content;
    }

    public List<WidgetTag> getTags() {
        return this.tags;
    }
}
