package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public class Seed implements Serializable {

    @SerializedName("seedType")
    private SeedType seedType = null;

    @SerializedName("id")
    private String id = null;
}
