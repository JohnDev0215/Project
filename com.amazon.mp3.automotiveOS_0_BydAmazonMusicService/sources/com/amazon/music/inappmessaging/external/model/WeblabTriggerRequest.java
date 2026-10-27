package com.amazon.music.inappmessaging.external.model;

import com.amazon.music.inappmessaging.internal.model.Weblab;
import com.google.gson.Gson;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class WeblabTriggerRequest {
    private static final Gson gson = new Gson();
    private final String mtr;
    private final List<Weblab> weblabs;

    public WeblabTriggerRequest(final String mtr, final List<Weblab> weblabs) {
        validateArguments(mtr, weblabs);
        this.mtr = mtr;
        this.weblabs = weblabs;
    }

    public String toJson() {
        return gson.toJson(this);
    }

    private void validateArguments(final String mtr, final List<Weblab> weblabs) {
        if (mtr == null) {
            throw new IllegalArgumentException("MTR cannot be null");
        }
        if (weblabs == null || weblabs.size() == 0) {
            throw new IllegalArgumentException("Weblab list cannot be null or empty");
        }
    }
}
