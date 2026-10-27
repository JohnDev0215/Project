package com.spotify.player.model;

import com.spotify.player.model.AutoValue_PauseResumeOrigin;

/* JADX INFO: loaded from: classes.dex */
public abstract class PauseResumeOrigin {

    public static abstract class Builder {
        public abstract PauseResumeOrigin a();
    }

    public static Builder a(String str) {
        AutoValue_PauseResumeOrigin.Builder builder = new AutoValue_PauseResumeOrigin.Builder();
        builder.a = str;
        return builder;
    }

    public abstract String b();
}
