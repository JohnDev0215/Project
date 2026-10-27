package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class Refinements implements Serializable {

    @SerializedName("refinementLists")
    Map<String, List<String>> refinementLists = null;

    public void setRefinementLists(Map<String, List<String>> map) {
        this.refinementLists = map;
    }
}
