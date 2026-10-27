package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackStateTracker;
import com.amazon.avod.media.playback.reporting.aloysius.state.InteractionEventStateMachine;
import com.amazon.avod.media.playback.reporting.aloysius.state.InteractionEventTrigger;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.qos.reporter.ContinuousPlayInputOption;
import com.amazon.avod.qos.reporter.SkipCardType;
import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import java.util.Collections;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusInteractionReporter implements MediaEventReporter, AloysiusInteractionReporterInterface {
    private final AloysiusPlaybackStateTracker mAloysiusPlaybackStateTracker;
    private final DeviceIdentity mDeviceProperties;
    private final InteractionEventStateMachine mInteractionEventStateMachine;
    private final MediaEventQueue mMediaEventQueue;
    private final boolean mShouldUseInteractionStateLogic;

    public enum Cause {
        Customer,
        OutOfTimeWindow,
        SeekAfterAdBreak,
        RemoteDevice,
        Client,
        ConcurrencyBreach,
        Platform,
        Autoplay
    }

    public enum Input {
        TouchScreenClick
    }

    public enum NavigationType {
        SkipCard,
        ContinuousPlay,
        Notification
    }

    public enum NotificationInputOption {
        Dismiss,
        Yes,
        No
    }

    public enum Source {
        DetailPage,
        Player,
        PlayerSDK,
        PlayerUI,
        XRay,
        ContinuousPlay,
        LandingPage,
        Remote
    }

    public enum Type {
        Play,
        Pause,
        Stop,
        SeekForward,
        SeekBackward,
        Next,
        Previous,
        ShowControls,
        FullscreenOn,
        FullscreenOff,
        TimedTextOn,
        TimedTextOff,
        TrackChange,
        Navigation,
        StartFromBeginning
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
    }

    public AloysiusInteractionReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nullable AloysiusPlaybackStateTracker aloysiusPlaybackStateTracker) {
        this(mediaEventQueue, aloysiusPlaybackStateTracker, AloysiusConfig.getInstance(), InteractionEventStateMachine.getInstance(), MediaSystemSharedDependencies.getInstance().getDeviceIdentity());
    }

    @VisibleForTesting
    AloysiusInteractionReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull DeviceIdentity deviceIdentity) {
        this(mediaEventQueue, null, AloysiusConfig.getInstance(), InteractionEventStateMachine.getInstance(), deviceIdentity);
    }

    AloysiusInteractionReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nullable AloysiusPlaybackStateTracker aloysiusPlaybackStateTracker, @Nonnull AloysiusConfig aloysiusConfig, @Nonnull InteractionEventStateMachine interactionEventStateMachine, @Nonnull DeviceIdentity deviceIdentity) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mInteractionEventStateMachine = (InteractionEventStateMachine) Preconditions.checkNotNull(interactionEventStateMachine, "interactionEventStateMachine");
        this.mAloysiusPlaybackStateTracker = aloysiusPlaybackStateTracker;
        this.mDeviceProperties = (DeviceIdentity) Preconditions.checkNotNull(deviceIdentity, "deviceProperties");
        this.mShouldUseInteractionStateLogic = ((AloysiusConfig) Preconditions.checkNotNull(aloysiusConfig, "config")).shouldUseInteractionStateLogic();
    }

    private InteractionMediaEvent.Context.ContextBuilder getInteractionContextBuilder(@Nonnull Type type, @Nonnull Source source) {
        return new InteractionMediaEvent.Context.ContextBuilder().type(type).source(source).input(Input.TouchScreenClick);
    }

    private InteractionMediaEvent.Builder getInteractionMediaEventBuilder() {
        return new InteractionMediaEvent.Builder().sourceDeviceId(this.mDeviceProperties.getDeviceId());
    }

    public void reportNotificationOptionAgreeEvent(@Nonnull Source source, @Nullable Cause cause, @Nullable String str) {
        Preconditions.checkNotNull(source, PageUriUtils.SOURCE_QUERY_PARAM);
        Type type = Type.Navigation;
        reportEvent(getInteractionContextBuilder(type, source).agreeNotificationNavigation().cause(cause).build(), new InteractionEventTrigger(type, source), str);
    }

    public void reportNotificationOptionDisagreeEvent(@Nonnull Source source, @Nullable Cause cause, @Nullable String str) {
        Preconditions.checkNotNull(source, PageUriUtils.SOURCE_QUERY_PARAM);
        Type type = Type.Navigation;
        reportEvent(getInteractionContextBuilder(type, source).disagreeNotificationNavigation().cause(cause).build(), new InteractionEventTrigger(type, source), str);
    }

    public void reportContinuousPlayHideEvent(@Nonnull Source source, @Nullable Cause cause, @Nullable String str) {
        Preconditions.checkNotNull(source, PageUriUtils.SOURCE_QUERY_PARAM);
        Type type = Type.Navigation;
        reportEvent(getInteractionContextBuilder(type, source).hideContinuousPlayNavigation().cause(cause).build(), new InteractionEventTrigger(type, source), str);
    }

    public void reportContinuousPlayFollowEvent(@Nonnull Source source, @Nullable Cause cause, @Nullable String str) {
        Preconditions.checkNotNull(source, PageUriUtils.SOURCE_QUERY_PARAM);
        Type type = Type.Navigation;
        reportEvent(getInteractionContextBuilder(type, source).followContinuousPlayNavigation().cause(cause).build(), new InteractionEventTrigger(type, source), str);
    }

    public void reportIntroSkipCardHideEvent(@Nonnull Source source, @Nullable Cause cause, @Nullable String str) {
        Preconditions.checkNotNull(source, PageUriUtils.SOURCE_QUERY_PARAM);
        Type type = Type.Navigation;
        reportEvent(getInteractionContextBuilder(type, source).hideSkipCardNavigation().cause(cause).build(), new InteractionEventTrigger(type, source), str);
    }

    public void reportIntroSkipCardFollowEvent(@Nonnull Source source, @Nullable Cause cause, @Nullable String str) {
        Preconditions.checkNotNull(source, PageUriUtils.SOURCE_QUERY_PARAM);
        Type type = Type.Navigation;
        reportEvent(getInteractionContextBuilder(type, source).followSkipCardNavigation().cause(cause).build(), new InteractionEventTrigger(type, source), str);
    }

    private void enqueInteractionEvent(InteractionMediaEvent.Context context, String str) {
        this.mMediaEventQueue.add(getInteractionMediaEventBuilder().targetDeviceId(str).context(context).build());
    }

    public void reportWatchNow() {
        reportEvent(Type.Play, Source.DetailPage);
    }

    public void reportOpen() {
        AloysiusPlaybackReporter.PlaybackMediaEvent.Builder builder = new AloysiusPlaybackReporter.PlaybackMediaEvent.Builder();
        builder.action(AloysiusPlaybackReporter.PlaybackMediaEvent.Action.Open);
        builder.contentContext(AloysiusPlaybackReporter.PlaybackMediaEvent.ContentContext.Feature);
        builder.frontBuffer(Collections.emptyList());
        builder.backBuffer(Collections.emptyList());
        builder.rendererBuffer(Collections.emptyList());
        AloysiusPlaybackStateTracker aloysiusPlaybackStateTracker = this.mAloysiusPlaybackStateTracker;
        if (aloysiusPlaybackStateTracker != null) {
            aloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.IntentOfPlay);
        }
        this.mMediaEventQueue.add(builder.build());
    }

    public void reportPlaybackStop() {
        reportEvent(Type.Stop, Source.Player);
    }

    public void reportEvent(@Nonnull Type type, @Nonnull Source source) {
        reportEvent(type, source, null, null);
    }

    public void reportEvent(@Nonnull Type type, @Nonnull Source source, @Nullable InteractionMediaEvent.Navigation navigation, @Nullable Cause cause) {
        Preconditions.checkNotNull(type, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        Preconditions.checkNotNull(source, PageUriUtils.SOURCE_QUERY_PARAM);
        reportEvent(getInteractionContextBuilder(type, source).navigation(navigation).cause(cause).build(), new InteractionEventTrigger(type, source), null);
    }

    private void reportEvent(@Nonnull InteractionMediaEvent.Context context, @Nonnull InteractionEventTrigger interactionEventTrigger, @Nullable String str) {
        Preconditions.checkNotNull(context, "context");
        Preconditions.checkNotNull(interactionEventTrigger, "interactionEventTrigger");
        synchronized (this.mInteractionEventStateMachine) {
            if (!this.mShouldUseInteractionStateLogic || this.mInteractionEventStateMachine.doTrigger(interactionEventTrigger)) {
                DLog.devf("Logging Aloysius Interaction event - type: %s, source: %s", interactionEventTrigger.getType(), interactionEventTrigger.getSource());
                enqueInteractionEvent(context, str);
            } else {
                DLog.warnf("Ignoring Aloysius Interaction event - type: %s, source: %s", interactionEventTrigger.getType(), interactionEventTrigger.getSource());
            }
        }
    }

    public void reportSecondScreenEvent(@Nonnull Type type, @Nonnull Source source, @Nullable String str) {
        Preconditions.checkNotNull(type, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        Preconditions.checkNotNull(source, PageUriUtils.SOURCE_QUERY_PARAM);
        reportEvent(getInteractionContextBuilder(type, source).cause(Cause.RemoteDevice).build(), new InteractionEventTrigger(type, source), str);
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporterInterface
    public void reportSeekEvent(@Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2) {
        reportEvent(timeSpan2.getTotalMilliseconds() > timeSpan.getTotalMilliseconds() ? Type.SeekForward : Type.SeekBackward, Source.Player);
    }

    public void reportAudioTrackChange() {
        reportEvent(Type.TrackChange, Source.Player);
    }

    public void reportFullscreenStatusForPIPMode(boolean z) {
        reportEvent(z ? Type.FullscreenOff : Type.FullscreenOn, Source.Player);
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporterInterface
    public void reportNextEvent() {
        reportEvent(Type.Next, Source.Player, null, Cause.Customer);
    }

    public void reportShowControlsEvent() {
        reportEvent(Type.ShowControls, Source.Player);
    }

    public static class InteractionMediaEvent implements MediaEvent {
        private final Context mContext;
        private final String mSourceDeviceId;
        private final String mTargetDeviceId;

        private InteractionMediaEvent(@Nonnull Builder builder) {
            this.mContext = builder.mContext;
            this.mSourceDeviceId = builder.mSourceDeviceId;
            this.mTargetDeviceId = builder.mTargetDeviceId;
        }

        @Nonnull
        public Context getContext() {
            return this.mContext;
        }

        public String getSourceDeviceId() {
            return this.mSourceDeviceId;
        }

        public String getTargetDeviceId() {
            return this.mTargetDeviceId;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Interaction;
        }

        static class Context {
            private final Cause mCause;
            private final Input mInput;
            private final Navigation mNavigation;
            private final Source mSource;
            private final Type mType;

            private Context(@Nonnull ContextBuilder contextBuilder) {
                Preconditions.checkNotNull(contextBuilder, "contextBuilder");
                this.mType = contextBuilder.mType;
                this.mSource = contextBuilder.mSource;
                this.mInput = contextBuilder.mInput;
                this.mCause = contextBuilder.mCause;
                this.mNavigation = contextBuilder.mNavigation;
            }

            @Nonnull
            public Source getSource() {
                return this.mSource;
            }

            @Nonnull
            public Type getType() {
                return this.mType;
            }

            @Nullable
            public Input getInput() {
                return this.mInput;
            }

            @Nullable
            public Cause getCause() {
                return this.mCause;
            }

            @Nullable
            public Navigation getNavigation() {
                return this.mNavigation;
            }

            static class ContextBuilder {
                private Source mSource;
                private Type mType;
                private Input mInput = null;
                private Cause mCause = null;
                private Navigation mNavigation = null;

                ContextBuilder() {
                }

                public ContextBuilder type(@Nonnull Type type) {
                    this.mType = (Type) Preconditions.checkNotNull(type, "interaction event type");
                    return this;
                }

                public ContextBuilder source(@Nonnull Source source) {
                    this.mSource = (Source) Preconditions.checkNotNull(source, "interaction event source");
                    return this;
                }

                public ContextBuilder input(@Nullable Input input) {
                    this.mInput = input;
                    return this;
                }

                public ContextBuilder cause(@Nullable Cause cause) {
                    if (cause == null) {
                        cause = Cause.Customer;
                    }
                    this.mCause = cause;
                    return this;
                }

                public ContextBuilder navigation(@Nullable Navigation navigation) {
                    this.mNavigation = navigation;
                    return this;
                }

                public ContextBuilder agreeNotificationNavigation() {
                    this.mNavigation = new NotificationNavigation(NavigationType.Notification, NotificationInputOption.Yes, null);
                    return this;
                }

                public ContextBuilder disagreeNotificationNavigation() {
                    this.mNavigation = new NotificationNavigation(NavigationType.Notification, NotificationInputOption.No, null);
                    return this;
                }

                public ContextBuilder dismissNotificationNavigation() {
                    this.mNavigation = new NotificationNavigation(NavigationType.Notification, NotificationInputOption.Dismiss, null);
                    return this;
                }

                public ContextBuilder hideContinuousPlayNavigation() {
                    this.mNavigation = new ContinuousPlayNavigation(NavigationType.ContinuousPlay, ContinuousPlayInputOption.Hide, "NextUpCard");
                    return this;
                }

                public ContextBuilder followContinuousPlayNavigation() {
                    this.mNavigation = new ContinuousPlayNavigation(NavigationType.ContinuousPlay, ContinuousPlayInputOption.Follow, "NextUpCard");
                    return this;
                }

                public ContextBuilder hideSkipCardNavigation() {
                    this.mNavigation = new SkipCardNavigation(NavigationType.SkipCard, ContinuousPlayInputOption.Hide, SkipCardType.Intro);
                    return this;
                }

                public ContextBuilder followSkipCardNavigation() {
                    this.mNavigation = new SkipCardNavigation(NavigationType.SkipCard, ContinuousPlayInputOption.Follow, SkipCardType.Intro);
                    return this;
                }

                public Context build() {
                    return new Context(this);
                }
            }
        }

        public static class Builder {
            private Context mContext;
            private String mSourceDeviceId;
            private String mTargetDeviceId = null;

            public Builder context(@Nonnull Context context) {
                this.mContext = context;
                return this;
            }

            public Builder targetDeviceId(@Nullable String str) {
                this.mTargetDeviceId = str;
                return this;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Builder sourceDeviceId(@Nullable String str) {
                this.mSourceDeviceId = str;
                return this;
            }

            public MediaEvent build() {
                return new InteractionMediaEvent(this);
            }
        }

        public static class Navigation {
            private final NavigationType mType;

            public Navigation(@Nonnull NavigationType navigationType) {
                this.mType = (NavigationType) Preconditions.checkNotNull(navigationType, "NavigationType type");
            }

            @JsonProperty("_type")
            @Nonnull
            public NavigationType getType() {
                return this.mType;
            }
        }

        public static class SkipCardNavigation extends Navigation {
            private final SkipCardType mCardType;
            private final ContinuousPlayInputOption mInput;

            public SkipCardNavigation(@Nonnull NavigationType navigationType, @Nonnull ContinuousPlayInputOption continuousPlayInputOption, @Nonnull SkipCardType skipCardType) {
                super(navigationType);
                this.mInput = (ContinuousPlayInputOption) Preconditions.checkNotNull(continuousPlayInputOption, "SkipCardNavigation input");
                this.mCardType = (SkipCardType) Preconditions.checkNotNull(skipCardType, "SkipCardNavigation cardType");
            }

            @Nonnull
            public ContinuousPlayInputOption getInput() {
                return this.mInput;
            }

            @Nonnull
            public SkipCardType getCardType() {
                return this.mCardType;
            }
        }

        public static class ContinuousPlayNavigation extends Navigation {
            private final ContinuousPlayInputOption mInput;
            private final String mRefMarker;

            public ContinuousPlayNavigation(@Nonnull NavigationType navigationType, @Nonnull ContinuousPlayInputOption continuousPlayInputOption, @Nonnull String str) {
                super(navigationType);
                this.mInput = (ContinuousPlayInputOption) Preconditions.checkNotNull(continuousPlayInputOption, "ContinuousPlayNavigation input");
                this.mRefMarker = (String) Preconditions.checkNotNull(str, "ContinuousPlayNavigation refMarker");
            }

            @Nonnull
            public ContinuousPlayInputOption getInput() {
                return this.mInput;
            }

            @Nonnull
            public String getRefMarker() {
                return this.mRefMarker;
            }
        }

        static class NotificationNavigation extends Navigation {
            private final NotificationInputOption mInput;
            private String mPurpose;

            private NotificationNavigation(@Nonnull NavigationType navigationType, @Nonnull NotificationInputOption notificationInputOption, @Nullable String str) {
                super(navigationType);
                this.mInput = notificationInputOption;
                this.mPurpose = str;
            }

            public NotificationInputOption getInput() {
                return this.mInput;
            }

            public String getPurpose() {
                return this.mPurpose;
            }
        }
    }
}
