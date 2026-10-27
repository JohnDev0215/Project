package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public class Header implements Serializable {

    @SerializedName("content")
    private HeaderContent content = null;

    public HeaderContent getContent() {
        return this.content;
    }
}
