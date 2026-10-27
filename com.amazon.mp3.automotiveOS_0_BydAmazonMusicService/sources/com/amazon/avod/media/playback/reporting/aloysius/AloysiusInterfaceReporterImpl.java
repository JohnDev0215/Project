package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.qos.reporter.ContinuousPlayInputOption;
import com.amazon.avod.qos.reporter.InterfaceSource;
import com.amazon.avod.qos.reporter.InterfaceState;
import com.amazon.avod.qos.reporter.InterfaceType;
import com.amazon.avod.qos.reporter.NextUpCardType;
import com.amazon.avod.qos.reporter.NotificationDescription;
import com.amazon.avod.qos.reporter.SkipCardType;
import com.amazon.avod.util.AppVisibilityTracker;
import com.amazon.avod.util.ApplicationVisibility;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusInterfaceReporterImpl implements AloysiusInterfaceReporter {
    private final AppVisibilityTracker mAppVisibilityTracker;
    private final MediaEventQueue mMediaEventQueue;
    private final boolean mShouldListenToStateChangeEvent;
    private final AppVisibilityTracker.ApplicationVisibilityListener mStateListener;

    public AloysiusInterfaceReporterImpl(@Nonnull MediaEventQueue mediaEventQueue, boolean z) {
        this(mediaEventQueue, AppVisibilityTracker.getInstance(), z);
    }

    @VisibleForTesting
    public AloysiusInterfaceReporterImpl(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull AppVisibilityTracker appVisibilityTracker, boolean z) {
        this.mStateListener = new AppVisibilityTracker.ApplicationVisibilityListener() { // from class: com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporterImpl.1
            @Override // com.amazon.avod.util.AppVisibilityTracker.ApplicationVisibilityListener
            public void onApplicationVisibilityChanged(@Nonnull ApplicationVisibility applicationVisibility, @Nonnull ApplicationVisibility applicationVisibility2) {
                if (applicationVisibility.isAppInForeground() && !applicationVisibility2.isAppInForeground()) {
                    AloysiusInterfaceReporterImpl.this.reportPlayerToBackgroundEvent();
                } else {
                    if (applicationVisibility.isAppInForeground() || !applicationVisibility2.isAppInForeground()) {
                        return;
                    }
                    AloysiusInterfaceReporterImpl.this.reportPlayerToForegroundEvent();
                }
            }
        };
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mAppVisibilityTracker = (AppVisibilityTracker) Preconditions.checkNotNull(appVisibilityTracker, "applicationVisibilityTracker");
        this.mShouldListenToStateChangeEvent = z;
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter, com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
        if (this.mShouldListenToStateChangeEvent) {
            this.mAppVisibilityTracker.addAppVisibilityListenerWithImmediateCallback(this.mStateListener);
        }
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter, com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
        if (this.mShouldListenToStateChangeEvent) {
            this.mAppVisibilityTracker.removeAppVisibilityListener(this.mStateListener);
        }
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter
    public void reportTrickPlayEvent(@Nonnull InterfaceSource interfaceSource, @Nonnull InterfaceState interfaceState) {
        Preconditions.checkNotNull(interfaceSource, "interfaceSource");
        Preconditions.checkNotNull(interfaceState, "interfaceState");
        InterfaceMediaEvent.Builder builder = new InterfaceMediaEvent.Builder();
        builder.component(InterfaceType.TrickPlay).source(interfaceSource).state(interfaceState);
        this.mMediaEventQueue.add(builder.build());
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter
    public void reportBufferingSpinnerShowEvent(@Nonnull InterfaceSource interfaceSource) {
        Preconditions.checkNotNull(interfaceSource, "interfaceSource");
        InterfaceMediaEvent.Builder builder = new InterfaceMediaEvent.Builder();
        builder.component(InterfaceType.Spinner).source(interfaceSource).state(InterfaceState.Show);
        this.mMediaEventQueue.add(builder.build());
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter
    public void reportBufferingSpinnerHideEvent(@Nonnull InterfaceSource interfaceSource) {
        Preconditions.checkNotNull(interfaceSource, "interfaceSource");
        InterfaceMediaEvent.Builder builder = new InterfaceMediaEvent.Builder();
        builder.component(InterfaceType.Spinner).source(interfaceSource).state(InterfaceState.Hide);
        this.mMediaEventQueue.add(builder.build());
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter
    public void reportPlayerToBackgroundEvent() {
        InterfaceMediaEvent.Builder builder = new InterfaceMediaEvent.Builder();
        builder.component(InterfaceType.Background).source(InterfaceSource.Player).state(InterfaceState.Show);
        this.mMediaEventQueue.add(builder.build());
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter
    public void reportPlayerToForegroundEvent() {
        InterfaceMediaEvent.Builder builder = new InterfaceMediaEvent.Builder();
        builder.component(InterfaceType.Background).source(InterfaceSource.Player).state(InterfaceState.Hide);
        this.mMediaEventQueue.add(builder.build());
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter
    public void reportSkipCardShowEvent(@Nonnull SkipCardType skipCardType) {
        InterfaceMediaEvent.Builder builder = new InterfaceMediaEvent.Builder();
        builder.component(InterfaceType.SkipCard, skipCardType).source(InterfaceSource.Player).state(InterfaceState.Show);
        this.mMediaEventQueue.add(builder.build());
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter
    public void reportSkipCardHideEvent(@Nonnull SkipCardType skipCardType) {
        InterfaceMediaEvent.Builder builder = new InterfaceMediaEvent.Builder();
        builder.component(InterfaceType.SkipCard, skipCardType).source(InterfaceSource.Player).state(InterfaceState.Hide);
        this.mMediaEventQueue.add(builder.build());
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter
    public void reportNotificationShowEvent(@Nonnull InterfaceSource interfaceSource, @Nullable NotificationDescription notificationDescription) {
        Preconditions.checkNotNull(interfaceSource, PageUriUtils.SOURCE_QUERY_PARAM);
        InterfaceMediaEvent.Builder builder = new InterfaceMediaEvent.Builder();
        builder.component(InterfaceType.Notification, notificationDescription != null ? notificationDescription.getText() : null).source(interfaceSource).state(InterfaceState.Show);
        this.mMediaEventQueue.add(builder.build());
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter
    public void reportNextUpCardShowEvent(int i, @Nonnull String str, NextUpCardType nextUpCardType, @Nonnull ImmutableSet<ContinuousPlayInputOption> immutableSet) {
        InterfaceMediaEvent.Builder builder = new InterfaceMediaEvent.Builder();
        builder.nextUpCardComponent(i, str, nextUpCardType, immutableSet).source(InterfaceSource.Player).state(InterfaceState.Show);
        this.mMediaEventQueue.add(builder.build());
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter
    public void reportNextUpCardHideEvent(int i, @Nonnull String str, NextUpCardType nextUpCardType, @Nonnull ImmutableSet<ContinuousPlayInputOption> immutableSet) {
        InterfaceMediaEvent.Builder builder = new InterfaceMediaEvent.Builder();
        builder.nextUpCardComponent(i, str, nextUpCardType, immutableSet).source(InterfaceSource.Player).state(InterfaceState.Hide);
        this.mMediaEventQueue.add(builder.build());
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter
    public void reportInPlaybackRatingEvent(@Nonnull InterfaceState interfaceState, @Nullable String str) {
        Preconditions.checkNotNull(interfaceState, "interfaceState");
        InterfaceMediaEvent.Builder builder = new InterfaceMediaEvent.Builder();
        builder.MaturityRatingComponent(InterfaceType.MaturityRating, str).source(InterfaceSource.Player).state(interfaceState);
        this.mMediaEventQueue.add(builder.build());
    }

    @VisibleForTesting
    static class InterfaceMediaEvent implements MediaEvent {

        @Nonnull
        private final Component mComponent;

        @Nonnull
        private final InterfaceSource mSource;

        @Nonnull
        private final InterfaceState mState;

        private InterfaceMediaEvent(@Nonnull Builder builder) {
            Preconditions.checkNotNull(builder, "builder");
            this.mComponent = builder.mComponent;
            this.mSource = builder.mSource;
            this.mState = builder.mState;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        @Nonnull
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Interface;
        }

        @Nonnull
        public InterfaceState getState() {
            return this.mState;
        }

        @Nonnull
        public InterfaceSource getSource() {
            return this.mSource;
        }

        @Nonnull
        public Component getComponent() {
            return this.mComponent;
        }

        @VisibleForTesting
        static class Builder {
            private Component mComponent;
            private InterfaceSource mSource;
            private InterfaceState mState;

            Builder() {
            }

            public Builder source(@Nonnull InterfaceSource interfaceSource) {
                this.mSource = (InterfaceSource) Preconditions.checkNotNull(interfaceSource, PageUriUtils.SOURCE_QUERY_PARAM);
                return this;
            }

            public Builder state(@Nonnull InterfaceState interfaceState) {
                this.mState = (InterfaceState) Preconditions.checkNotNull(interfaceState, "state");
                return this;
            }

            public Builder component(@Nonnull InterfaceType interfaceType) {
                Preconditions.checkNotNull(interfaceType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                this.mComponent = new Component(interfaceType);
                return this;
            }

            public Builder component(@Nonnull InterfaceType interfaceType, @Nonnull SkipCardType skipCardType) {
                Preconditions.checkNotNull(interfaceType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                Preconditions.checkNotNull(skipCardType, "skipCardType");
                this.mComponent = new SkipCardComponent(interfaceType, skipCardType);
                return this;
            }

            public Builder nextUpCardComponent(int i, @Nonnull String str, @Nonnull NextUpCardType nextUpCardType, @Nonnull ImmutableSet<ContinuousPlayInputOption> immutableSet) {
                Preconditions.checkNotNull(nextUpCardType, "nextUpCardType");
                this.mComponent = new NextUpCardComponent(InterfaceType.NextUpCard, i, str, nextUpCardType, immutableSet);
                return this;
            }

            public Builder component(@Nonnull InterfaceType interfaceType, @Nullable String str) {
                Preconditions.checkNotNull(interfaceType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                this.mComponent = new NotificationComponent(interfaceType, str);
                return this;
            }

            public Builder TrickPlayComponent() {
                this.mComponent = new Component(InterfaceType.TrickPlay);
                return this;
            }

            public Builder MaturityRatingComponent(@Nonnull InterfaceType interfaceType, @Nullable String str) {
                Preconditions.checkNotNull(interfaceType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                this.mComponent = new MaturityRatingComponent(interfaceType, str);
                return this;
            }

            public MediaEvent build() {
                return new InterfaceMediaEvent(this);
            }
        }

        @VisibleForTesting
        static class Component {

            @Nonnull
            private final InterfaceType mType;

            private Component(@Nonnull InterfaceType interfaceType) {
                this.mType = interfaceType;
            }

            @JsonProperty("_type")
            @Nonnull
            public InterfaceType getType() {
                return this.mType;
            }
        }

        @VisibleForTesting
        static class SkipCardComponent extends Component {

            @Nonnull
            private final SkipCardType mCardType;

            private SkipCardComponent(@Nonnull InterfaceType interfaceType, @Nonnull SkipCardType skipCardType) {
                super(interfaceType);
                this.mCardType = skipCardType;
            }

            @Nonnull
            public SkipCardType getCardType() {
                return this.mCardType;
            }
        }

        @VisibleForTesting
        static class NextUpCardComponent extends Component {

            @Nonnull
            private final NextUpCardType mCardType;

            @Nonnull
            private final ImmutableSet<ContinuousPlayInputOption> mInputOptions;

            @Nonnull
            private final String mRefMarker;
            private final int mTimeRemaining;

            private NextUpCardComponent(@Nonnull InterfaceType interfaceType, int i, @Nonnull String str, @Nonnull NextUpCardType nextUpCardType, @Nonnull ImmutableSet<ContinuousPlayInputOption> immutableSet) {
                super(interfaceType);
                this.mTimeRemaining = i;
                this.mRefMarker = str;
                this.mCardType = nextUpCardType;
                this.mInputOptions = immutableSet;
            }

            public int getTimeRemaining() {
                return this.mTimeRemaining;
            }

            @Nonnull
            public String getRefMarker() {
                return this.mRefMarker;
            }

            @Nonnull
            public NextUpCardType getCardType() {
                return this.mCardType;
            }

            @Nonnull
            public ImmutableSet<ContinuousPlayInputOption> getInputOptions() {
                return this.mInputOptions;
            }
        }

        @VisibleForTesting
        static class NotificationComponent extends Component {
            private final String mDescription;

            private NotificationComponent(@Nonnull InterfaceType interfaceType, @Nullable String str) {
                super(interfaceType);
                this.mDescription = str;
            }

            @Nonnull
            public String getDescription() {
                return this.mDescription;
            }
        }

        @VisibleForTesting
        static class MaturityRatingComponent extends Component {
            private final String mMaturityRating;

            private MaturityRatingComponent(@Nonnull InterfaceType interfaceType, @Nullable String str) {
                super(interfaceType);
                this.mMaturityRating = str;
            }

            @Nullable
            public String getMaturityRating() {
                return this.mMaturityRating;
            }
        }
    }
}
