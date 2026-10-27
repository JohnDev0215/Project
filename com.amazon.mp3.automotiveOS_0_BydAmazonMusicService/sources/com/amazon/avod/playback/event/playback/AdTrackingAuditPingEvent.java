package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdTrackingAuditPingEvent extends PlaybackEvent {

    @Nonnull
    private final String mAdAgencyType;

    @Nullable
    private final String mAuditPingUrl;

    @Nonnull
    private final String mConsumptionId;

    @Nonnull
    private final String mSessionId;

    @Nonnull
    private final String mStreamId;

    public AdTrackingAuditPingEvent(@Nonnull TimeSpan timeSpan, @Nullable String str, @Nonnull String str2, @Nonnull String str3, @Nonnull String str4, @Nonnull String str5) {
        super(timeSpan);
        this.mAuditPingUrl = str;
        this.mConsumptionId = (String) Preconditions.checkNotNull(str2, "consumptionId");
        this.mAdAgencyType = (String) Preconditions.checkNotNull(str3, "adAgencyType");
        this.mSessionId = (String) Preconditions.checkNotNull(str4, Splash.PARAMS_SESSION_ID);
        this.mStreamId = (String) Preconditions.checkNotNull(str5, "streamId");
    }

    @Override // com.amazon.avod.playback.event.PlaybackEvent
    @Nonnull
    public String getConsumptionId() {
        return this.mConsumptionId;
    }

    @Nonnull
    public String getAdAgencyType() {
        return this.mAdAgencyType;
    }

    @Nonnull
    public String getSessionId() {
        return this.mSessionId;
    }

    @Nonnull
    public String getStreamId() {
        return this.mStreamId;
    }
}
