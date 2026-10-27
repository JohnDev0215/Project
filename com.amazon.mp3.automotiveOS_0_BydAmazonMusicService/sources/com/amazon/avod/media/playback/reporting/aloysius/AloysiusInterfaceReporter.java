package com.amazon.avod.media.playback.reporting.aloysius;

import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.qos.reporter.ContinuousPlayInputOption;
import com.amazon.avod.qos.reporter.InterfaceSource;
import com.amazon.avod.qos.reporter.InterfaceState;
import com.amazon.avod.qos.reporter.NextUpCardType;
import com.amazon.avod.qos.reporter.NotificationDescription;
import com.amazon.avod.qos.reporter.SkipCardType;
import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface AloysiusInterfaceReporter extends MediaEventReporter {
    @Override // com.amazon.avod.media.events.MediaEventReporter
    /* synthetic */ void initialize();

    void reportBufferingSpinnerHideEvent(InterfaceSource interfaceSource);

    void reportBufferingSpinnerShowEvent(InterfaceSource interfaceSource);

    void reportInPlaybackRatingEvent(@Nonnull InterfaceState interfaceState, @Nullable String str);

    void reportNextUpCardHideEvent(int i, @Nonnull String str, NextUpCardType nextUpCardType, @Nonnull ImmutableSet<ContinuousPlayInputOption> immutableSet);

    void reportNextUpCardShowEvent(int i, @Nonnull String str, NextUpCardType nextUpCardType, @Nonnull ImmutableSet<ContinuousPlayInputOption> immutableSet);

    void reportNotificationShowEvent(@Nonnull InterfaceSource interfaceSource, @Nullable NotificationDescription notificationDescription);

    void reportPlayerToBackgroundEvent();

    void reportPlayerToForegroundEvent();

    void reportSkipCardHideEvent(@Nonnull SkipCardType skipCardType);

    void reportSkipCardShowEvent(@Nonnull SkipCardType skipCardType);

    void reportTrickPlayEvent(@Nonnull InterfaceSource interfaceSource, @Nonnull InterfaceState interfaceState);

    @Override // com.amazon.avod.media.events.MediaEventReporter
    /* synthetic */ void terminate();
}
