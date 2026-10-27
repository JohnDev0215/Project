package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class HeaderContent implements Serializable {

    @SerializedName("nextToken")
    private String nextToken = null;

    @SerializedName("widgets")
    private List<Widget> widgets = null;

    public List<Widget> getWidgets() {
        return this.widgets;
    }
}
