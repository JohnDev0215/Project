package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.RetriableServiceErrorEvent;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.download.error.DownloadErrorCode;
import com.amazon.avod.media.error.DrmErrorCode;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.event.playback.FatalPlaybackErrorEvent;
import com.amazon.avod.playback.event.playback.PlaybackErrorEvent;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.playback.session.ConsumptionIdProvider;
import com.amazon.avod.qos.ErrorReportingConfiguration;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusErrorEventReporter implements MediaEventReporter {
    private final Map<Object, ErrorMediaEvent.Type> errorTypeMapping;

    @Nullable
    private AdEventTransport mAdEventTransport;
    private final ConsumptionIdProvider mConsumptionIdProvider;
    private final ErrorReportingConfiguration mErrorReportingConfiguration;
    private final ContentManagementEventBus mEventBus;

    @Nullable
    private final PlaybackEventTransport mEventTransport;
    protected final MediaEventQueue mMediaEventQueue;

    public AloysiusErrorEventReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nullable PlaybackEventTransport playbackEventTransport, @Nullable AdEventTransport adEventTransport, @Nullable ContentManagementEventBus contentManagementEventBus, @Nullable ConsumptionIdProvider consumptionIdProvider) {
        this(mediaEventQueue, playbackEventTransport, adEventTransport, new ErrorReportingConfiguration(), contentManagementEventBus, consumptionIdProvider);
    }

    @VisibleForTesting
    AloysiusErrorEventReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nullable PlaybackEventTransport playbackEventTransport, @Nullable AdEventTransport adEventTransport, @Nonnull ErrorReportingConfiguration errorReportingConfiguration, @Nullable ContentManagementEventBus contentManagementEventBus, @Nullable ConsumptionIdProvider consumptionIdProvider) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        ContentException.ContentError contentError = ContentException.ContentError.CANNOT_WRITE_ADAPTED_SAMPLE;
        ErrorMediaEvent.Type type = ErrorMediaEvent.Type.FragmentParse;
        ImmutableMap.Builder builderPut = builder.put(contentError, type);
        ContentException.ContentError contentError2 = ContentException.ContentError.CDN_ERROR;
        ErrorMediaEvent.Type type2 = ErrorMediaEvent.Type.Unreachable;
        ImmutableMap.Builder builderPut2 = builderPut.put(contentError2, type2);
        ContentException.ContentError contentError3 = ContentException.ContentError.DISK_ERROR;
        ErrorMediaEvent.Type type3 = ErrorMediaEvent.Type.DiskIO;
        ImmutableMap.Builder builderPut3 = builderPut2.put(contentError3, type3).put(ContentException.ContentError.DISK_FULL, type3);
        ContentException.ContentError contentError4 = ContentException.ContentError.FAILED_TO_PARSE_MANIFEST;
        ErrorMediaEvent.Type type4 = ErrorMediaEvent.Type.ManifestParse;
        ImmutableMap.Builder builderPut4 = builderPut3.put(contentError4, type4).put(ContentException.ContentError.INVALID_BASE64_DATA, type).put(ContentException.ContentError.INVALID_CONTENT_LENGTH, type).put(ContentException.ContentError.LICENSE_MISSING, ErrorMediaEvent.Type.DRM).put(ContentException.ContentError.LOW_MEMORY_ERROR, ErrorMediaEvent.Type.LowMemory).put(ContentException.ContentError.MALFORMED_MANIFEST, type4).put(ContentException.ContentError.NETWORK_ERROR, type2).put(ContentException.ContentError.FILE_MISSING, type2).put(ContentException.ContentError.SAMPLE_TOO_LARGE, type).put(ContentException.ContentError.SERVICE_CALL_LOAD_SHED, type2);
        ContentException.ContentError contentError5 = ContentException.ContentError.SERVICE_ERROR;
        ErrorMediaEvent.Type type5 = ErrorMediaEvent.Type.Error;
        this.errorTypeMapping = builderPut4.put(contentError5, type5).put(ContentException.ContentError.LIVE_STALE_MANIFEST, type5).put(ContentException.ContentError.LIVE_SLOW_MANIFEST, type5).put(ContentException.ContentError.TEMPORARILY_UNAVAILABLE, type2).put(ContentException.ContentError.DATA_CONNECTION_UNAVAILABLE_ERROR, type2).put(ContentException.ContentError.UNKNOWN_ERROR, ErrorMediaEvent.Type.Unknown).build();
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mEventTransport = playbackEventTransport;
        this.mAdEventTransport = adEventTransport;
        this.mErrorReportingConfiguration = (ErrorReportingConfiguration) Preconditions.checkNotNull(errorReportingConfiguration, "errorReportingConfiguration");
        this.mEventBus = contentManagementEventBus;
        this.mConsumptionIdProvider = consumptionIdProvider;
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
        PlaybackEventTransport playbackEventTransport = this.mEventTransport;
        if (playbackEventTransport != null) {
            playbackEventTransport.registerEventBusHandler(this);
        }
        AdEventTransport adEventTransport = this.mAdEventTransport;
        if (adEventTransport != null) {
            adEventTransport.registerEventBusHandler(this);
        }
        ContentManagementEventBus contentManagementEventBus = this.mEventBus;
        if (contentManagementEventBus != null) {
            contentManagementEventBus.registerEventBusHandler(this);
        }
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
        PlaybackEventTransport playbackEventTransport = this.mEventTransport;
        if (playbackEventTransport != null) {
            playbackEventTransport.unregisterEventBusHandler(this);
        }
        AdEventTransport adEventTransport = this.mAdEventTransport;
        if (adEventTransport != null) {
            adEventTransport.unregisterEventBusHandler(this);
        }
        ContentManagementEventBus contentManagementEventBus = this.mEventBus;
        if (contentManagementEventBus != null) {
            contentManagementEventBus.unregisterEventBusHandler(this);
        }
    }

    @Subscribe
    public void handleErrorEvent(FatalPlaybackErrorEvent fatalPlaybackErrorEvent) {
        handleMediaException(fatalPlaybackErrorEvent.getPlaybackException(), isFatal(fatalPlaybackErrorEvent), fatalPlaybackErrorEvent.getConsumptionId());
    }

    @Subscribe
    public void handleErrorEvent(RetriablePlaybackErrorEvent retriablePlaybackErrorEvent) {
        handleMediaException(retriablePlaybackErrorEvent.getPlaybackException(), isFatal(retriablePlaybackErrorEvent), retriablePlaybackErrorEvent.getConsumptionId());
    }

    @Subscribe
    public void handleErrorEvent(@Nonnull RetriableServiceErrorEvent retriableServiceErrorEvent) {
        String genericMessage;
        String specificMessage;
        Preconditions.checkNotNull(retriableServiceErrorEvent, NotificationCompat.CATEGORY_EVENT);
        ContentException exception = retriableServiceErrorEvent.getException();
        if (Strings.isNullOrEmpty(retriableServiceErrorEvent.getGenericMessage())) {
            genericMessage = exception.getErrorCode().getExternalCode().toReportableString();
        } else {
            genericMessage = retriableServiceErrorEvent.getGenericMessage();
        }
        if (Strings.isNullOrEmpty(retriableServiceErrorEvent.getSpecificMessage())) {
            specificMessage = Strings.isNullOrEmpty(exception.getMessage()) ? genericMessage : exception.getMessage();
        } else {
            specificMessage = retriableServiceErrorEvent.getSpecificMessage();
        }
        reportError(genericMessage, specificMessage, false, exception);
    }

    private void handleMediaException(MediaException mediaException, boolean z, String str) {
        ErrorMediaEvent.Builder builder = new ErrorMediaEvent.Builder();
        String reportableString = mediaException.getErrorCode().getExternalCode().toReportableString();
        builder.genericMessage(reportableString);
        if (!Strings.isNullOrEmpty(mediaException.getMessage())) {
            reportableString = mediaException.getMessage();
        }
        builder.specificMessage(reportableString);
        builder.type(getErrorType(mediaException));
        builder.fatal(z);
        builder.stackTrace(mediaException.getStackTrace());
        builder.consumptionId(str);
        this.mMediaEventQueue.add(builder.build());
    }

    public void reportError(@Nonnull String str, @Nonnull String str2, boolean z, @Nonnull MediaException mediaException) {
        ErrorMediaEvent.Builder builder = new ErrorMediaEvent.Builder();
        builder.genericMessage((String) Preconditions.checkNotNull(str, "genericMessage"));
        builder.specificMessage((String) Preconditions.checkNotNull(str2, "specificMessage"));
        builder.type(getErrorType(mediaException));
        builder.fatal(z);
        builder.stackTrace(mediaException.getStackTrace());
        ConsumptionIdProvider consumptionIdProvider = this.mConsumptionIdProvider;
        builder.consumptionId(consumptionIdProvider == null ? null : consumptionIdProvider.getConsumptionId());
        this.mMediaEventQueue.add(builder.build());
    }

    private ErrorMediaEvent.Type getErrorType(MediaException mediaException) {
        MediaErrorCode externalCode = mediaException.getErrorCode().getExternalCode();
        if (externalCode instanceof DrmErrorCode) {
            return ErrorMediaEvent.Type.DRM;
        }
        if (externalCode instanceof DownloadErrorCode) {
            return ErrorMediaEvent.Type.DiskIO;
        }
        ErrorMediaEvent.Type type = this.errorTypeMapping.get(mediaException.getErrorCode());
        return type != null ? type : ErrorMediaEvent.Type.Unknown;
    }

    private boolean isFatal(PlaybackErrorEvent playbackErrorEvent) {
        return this.mErrorReportingConfiguration.getSeverityFor(playbackErrorEvent.getPlaybackException(), playbackErrorEvent.isRetriable(), playbackErrorEvent.getHttpErrorCode()) <= 2;
    }

    @VisibleForTesting
    protected static class ErrorMediaEvent implements MediaEvent {
        private final String mConsumptionId;
        private final Context mContext;
        private final Details mDetails;
        private final boolean mFatal;

        enum Source {
            Device,
            Application,
            Service,
            Player
        }

        private ErrorMediaEvent(@Nonnull Builder builder) {
            this.mContext = new Context(((Builder) Preconditions.checkNotNull(builder, "builder")).mType);
            this.mFatal = builder.mFatal;
            this.mDetails = new Details(new Details.Message(builder.mGenericMessage, builder.mSpecificMessage), builder.mStackTrace);
            this.mConsumptionId = builder.mConsumptionId;
        }

        @Nonnull
        public Context getContext() {
            return this.mContext;
        }

        @Nonnull
        public Details getDetails() {
            return this.mDetails;
        }

        public boolean isFatal() {
            return this.mFatal;
        }

        @Nullable
        public String getConsumptionId() {
            return this.mConsumptionId;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Error;
        }

        protected static class Builder {
            private String mConsumptionId;
            private boolean mFatal;
            private String mGenericMessage;
            private String mSpecificMessage;
            private StackTraceElement[] mStackTrace;
            private Type mType;

            protected Builder() {
            }

            public Builder type(@Nonnull Type type) {
                this.mType = (Type) Preconditions.checkNotNull(type, "error type");
                return this;
            }

            public Builder fatal(boolean z) {
                this.mFatal = z;
                return this;
            }

            public Builder genericMessage(@Nonnull String str) {
                this.mGenericMessage = (String) Preconditions.checkNotNull(str, "genericMessage");
                return this;
            }

            public Builder specificMessage(@Nonnull String str) {
                this.mSpecificMessage = (String) Preconditions.checkNotNull(str, "specificMessage");
                return this;
            }

            public Builder stackTrace(@Nonnull StackTraceElement[] stackTraceElementArr) {
                this.mStackTrace = (StackTraceElement[]) Preconditions.checkNotNull(stackTraceElementArr, "stackTrace");
                return this;
            }

            Builder consumptionId(@Nullable String str) {
                this.mConsumptionId = str;
                return this;
            }

            public MediaEvent build() {
                return new ErrorMediaEvent(this);
            }
        }

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'LowMemory' uses external variables
        	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
        	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
        	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
        	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
         */
        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        static final class Type {
            private static final /* synthetic */ Type[] $VALUES;
            public static final Type DRM;
            public static final Type DiskIO;
            public static final Type Error;
            public static final Type FragmentParse;
            public static final Type FragmentRender;
            public static final Type LowMemory;
            public static final Type ManifestParse;
            public static final Type Unknown;
            public static final Type Unreachable;
            private Source mSource;

            public static Type valueOf(String str) {
                return (Type) Enum.valueOf(Type.class, str);
            }

            public static Type[] values() {
                return (Type[]) $VALUES.clone();
            }

            static {
                Source source = Source.Device;
                Type type = new Type("LowMemory", 0, source);
                LowMemory = type;
                Type type2 = new Type("DiskIO", 1, source);
                DiskIO = type2;
                Source source2 = Source.Service;
                Type type3 = new Type("Unreachable", 2, source2);
                Unreachable = type3;
                Type type4 = new Type("Error", 3, source2);
                Error = type4;
                Source source3 = Source.Player;
                Type type5 = new Type("ManifestParse", 4, source3);
                ManifestParse = type5;
                Type type6 = new Type("FragmentParse", 5, source3);
                FragmentParse = type6;
                Type type7 = new Type("FragmentRender", 6, source3);
                FragmentRender = type7;
                Type type8 = new Type("DRM", 7, source3);
                DRM = type8;
                Type type9 = new Type("Unknown", 8, source3);
                Unknown = type9;
                $VALUES = new Type[]{type, type2, type3, type4, type5, type6, type7, type8, type9};
            }

            private Type(String str, int i, Source source) {
                this.mSource = source;
            }

            public Source getSource() {
                return this.mSource;
            }
        }

        @VisibleForTesting
        static class Context {
            private final Source mSource;
            private final Type mType;

            private Context(Type type) {
                this.mSource = type.getSource();
                this.mType = type;
            }

            @Nonnull
            public Source getSource() {
                return this.mSource;
            }

            @Nonnull
            public Type getType() {
                return this.mType;
            }
        }

        @VisibleForTesting
        static class Details {
            private final Message mMessage;
            private final List<String> mStackTrace;

            private Details(@Nonnull Message message, @Nullable StackTraceElement[] stackTraceElementArr) {
                this.mMessage = (Message) Preconditions.checkNotNull(message, "message");
                this.mStackTrace = toStringList(stackTraceElementArr);
            }

            private List<String> toStringList(StackTraceElement[] stackTraceElementArr) {
                if (stackTraceElementArr == null) {
                    return null;
                }
                ArrayList arrayListNewArrayList = Lists.newArrayList();
                for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                    arrayListNewArrayList.add(stackTraceElement.toString());
                }
                return ImmutableList.copyOf((Collection) arrayListNewArrayList);
            }

            @Nonnull
            public Message getMessage() {
                return this.mMessage;
            }

            @Nullable
            public List<String> getStackTrace() {
                return this.mStackTrace;
            }

            @VisibleForTesting
            static class Message {
                private final String mGeneric;
                private final String mSpecific;

                private Message(@Nonnull String str, @Nullable String str2) {
                    this.mGeneric = (String) Preconditions.checkNotNull(str);
                    this.mSpecific = str2;
                }

                @Nonnull
                public String getGeneric() {
                    return this.mGeneric;
                }

                @Nullable
                public String getSpecific() {
                    return this.mSpecific;
                }
            }
        }
    }
}
