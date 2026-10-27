package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public class Message implements Serializable {

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("text")
    private String text = null;

    public String getBlockRef() {
        return this.blockRef;
    }

    public String getText() {
        return this.text;
    }
}
