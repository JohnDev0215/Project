package com.amazon.avod.media.events;

import com.amazon.avod.media.events.clientapi.EventConfig;
import com.amazon.avod.media.events.model.MediaEvent;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface MediaEventQueue {

    public interface PersistentMediaEvent {
        @Nonnull
        String getAppInstanceId();

        @Nonnull
        MediaEvent.MediaEventType getEventType();

        @Nonnull
        String getPayload();

        @Nonnegative
        long getSequence();
    }

    void add(@Nonnull MediaEvent mediaEvent);

    @Nonnull
    List<PersistentMediaEvent> bootstrap();

    int clear();

    String getAppInstanceId();

    @Nonnull
    List<PersistentMediaEvent> peek(long j);

    void registerMediaEventQueueListener(MediaEventQueueListener mediaEventQueueListener);

    void remove(String str, List<Long> list);

    void setAppInstanceId(String str);

    void setEventConfig(Map<MediaEvent.MediaEventType, EventConfig> map);

    long size();

    long sizeIncludingNullAppInstanceId();
}
