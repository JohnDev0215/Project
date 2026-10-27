package com.amazon.avod.content.smoothstream;

import androidx.annotation.Keep;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class EventMessage {
    private final TimeSpan mEventDuration;
    private final int mId;
    private final ByteBuffer mMessageData;
    private final TimeSpan mPresentationTimeDelta;
    private final String mSchemeIdUri;
    private final String mValue;

    @Keep
    public EventMessage(int i, @Nonnull String str, @Nonnull String str2, @Nonnull ByteBuffer byteBuffer, int i2, int i3, int i4) {
        this.mId = i;
        this.mSchemeIdUri = (String) Preconditions.checkNotNull(str, "schemeIdUri");
        this.mValue = (String) Preconditions.checkNotNull(str2, "value");
        this.mMessageData = (ByteBuffer) Preconditions.checkNotNull(byteBuffer, "messageData");
        long j = i2;
        this.mPresentationTimeDelta = new TimeSpan(i3, j);
        this.mEventDuration = new TimeSpan(i4, j);
    }

    public int getId() {
        return this.mId;
    }

    @Nonnull
    public String getSchemeIdUri() {
        return this.mSchemeIdUri;
    }

    @Nonnull
    public String getValue() {
        return this.mValue;
    }

    @Nonnull
    public ByteBuffer getMessageData() {
        return this.mMessageData;
    }

    @Nonnull
    public TimeSpan getPresentationTimeDelta() {
        return this.mPresentationTimeDelta;
    }

    @Nonnull
    public TimeSpan getEventDuration() {
        return this.mEventDuration;
    }

    public String toString() {
        return "EventMessage{mId=" + this.mId + ", mSchemeIdUri='" + this.mSchemeIdUri + "', mValue='" + this.mValue + "', mMessageData=" + this.mMessageData + ", mPresentationTimeDelta=" + this.mPresentationTimeDelta + ", mEventDuration=" + this.mEventDuration + '}';
    }
}
