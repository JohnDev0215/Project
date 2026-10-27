package com.amazon.mp3.web.webtarget;

import androidx.annotation.NonNull;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class BasicWebTarget extends WebTarget {
    private final String url;

    public BasicWebTarget(@NonNull final String url) {
        Validate.notEmpty(url);
        this.url = url;
    }

    @Override // com.amazon.mp3.web.webtarget.WebTarget
    public String getUrl() {
        return this.url;
    }
}
