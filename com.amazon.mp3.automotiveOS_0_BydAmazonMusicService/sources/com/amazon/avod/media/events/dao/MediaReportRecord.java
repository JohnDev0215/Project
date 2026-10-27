package com.amazon.avod.media.events.dao;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MediaReportRecord {
    private final String mAppInstanceId;
    private final Long mId;
    private final String mPayload;

    public MediaReportRecord(@Nonnull String str, @Nonnull String str2) {
        this(null, str, str2);
    }

    public MediaReportRecord(@Nullable Long l, @Nonnull String str, @Nonnull String str2) {
        this.mId = l;
        this.mAppInstanceId = (String) Preconditions.checkNotNull(str, "appInstanceId");
        this.mPayload = (String) Preconditions.checkNotNull(str2, "payload");
    }

    @Nullable
    public Long getId() {
        return this.mId;
    }

    @Nonnull
    public String getAppInstanceId() {
        return this.mAppInstanceId;
    }

    @Nonnull
    public String getPayload() {
        return this.mPayload;
    }
}
