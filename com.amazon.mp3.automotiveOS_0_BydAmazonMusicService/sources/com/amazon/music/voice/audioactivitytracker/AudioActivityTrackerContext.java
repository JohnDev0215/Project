package com.amazon.music.voice.audioactivitytracker;

import com.amazon.alexa.voice.core.processor.superbowl.Context;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: AudioActivityTrackerContext.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \r2\u00020\u0001:\u0003\u000e\r\u000fB\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0014R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext;", "Lcom/amazon/alexa/voice/core/processor/superbowl/Context;", "Lorg/json/JSONObject;", "getContentChannelObject", "getDialogChannelObject", "getPayload", "Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$Builder;", "builder", "Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$Builder;", "getBuilder", "()Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$Builder;", "<init>", "(Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$Builder;)V", "Companion", "Builder", "ContentChannel", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final class AudioActivityTrackerContext extends Context {
    private final Builder builder;

    /* JADX INFO: compiled from: AudioActivityTrackerContext.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$ContentChannel;", "", "(Ljava/lang/String;I)V", "ExternalMediaPlayer", "AudioPlayer", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ContentChannel {
        ExternalMediaPlayer,
        AudioPlayer
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioActivityTrackerContext(Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Context
    protected JSONObject getPayload() {
        JSONObject jSONObject = new JSONObject();
        JSONObject contentChannelObject = getContentChannelObject();
        if (contentChannelObject != null) {
            jSONObject.put("content", contentChannelObject);
        }
        JSONObject dialogChannelObject = getDialogChannelObject();
        if (dialogChannelObject != null) {
            jSONObject.put("dialog", dialogChannelObject);
        }
        return jSONObject;
    }

    private final JSONObject getContentChannelObject() {
        JSONObject jSONObject = new JSONObject();
        ContentChannel contentChannel = this.builder.getContentChannel();
        if (contentChannel != null) {
            jSONObject.put(ContextMappingConstants.INTERFACE, contentChannel.name());
        }
        Long contentChannelIdleTime = this.builder.getContentChannelIdleTime();
        if ((contentChannelIdleTime == null ? null : jSONObject.put("idleTimeInMilliseconds", contentChannelIdleTime.longValue())) == null) {
            return null;
        }
        return jSONObject;
    }

    private final JSONObject getDialogChannelObject() {
        JSONObject jSONObject = new JSONObject();
        Long dialogChannelIdleTime = this.builder.getDialogChannelIdleTime();
        if ((dialogChannelIdleTime == null ? null : jSONObject.put("idleTimeInMilliseconds", dialogChannelIdleTime.longValue())) == null) {
            return null;
        }
        jSONObject.put(ContextMappingConstants.INTERFACE, "SpeechSynthesizer");
        return jSONObject;
    }

    /* JADX INFO: compiled from: AudioActivityTrackerContext.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J!\u0010\b\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\n\u0010\u000bR$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$Builder;", "Lcom/amazon/alexa/voice/core/processor/superbowl/Context$Builder;", "Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$ContentChannel;", "contentChannel", "", "idleTime", "withContentChannel", "(Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$ContentChannel;Ljava/lang/Long;)Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$Builder;", "withDialogChannelIdleTime", "(Ljava/lang/Long;)Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$Builder;", "Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$ContentChannel;", "getContentChannel", "()Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$ContentChannel;", "setContentChannel", "(Lcom/amazon/music/voice/audioactivitytracker/AudioActivityTrackerContext$ContentChannel;)V", "contentChannelIdleTime", "Ljava/lang/Long;", "getContentChannelIdleTime", "()Ljava/lang/Long;", "setContentChannelIdleTime", "(Ljava/lang/Long;)V", "dialogChannelIdleTime", "getDialogChannelIdleTime", "setDialogChannelIdleTime", "<init>", "()V", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
    public static final class Builder extends Context.Builder<AudioActivityTrackerContext> {
        private ContentChannel contentChannel;
        private Long contentChannelIdleTime;
        private Long dialogChannelIdleTime;

        public Builder() {
            super("AudioActivityTracker", "ActivityState");
        }

        public AudioActivityTrackerContext build() {
            return new AudioActivityTrackerContext(this);
        }

        public final ContentChannel getContentChannel() {
            return this.contentChannel;
        }

        public final void setContentChannel(ContentChannel contentChannel) {
            this.contentChannel = contentChannel;
        }

        public final Long getContentChannelIdleTime() {
            return this.contentChannelIdleTime;
        }

        public final void setContentChannelIdleTime(Long l) {
            this.contentChannelIdleTime = l;
        }

        public final Long getDialogChannelIdleTime() {
            return this.dialogChannelIdleTime;
        }

        public final Builder withContentChannel(ContentChannel contentChannel, Long idleTime) {
            if (idleTime != null) {
                setContentChannelIdleTime(Long.valueOf(idleTime.longValue()));
                setContentChannel(contentChannel);
            }
            return this;
        }

        public final Builder withDialogChannelIdleTime(Long idleTime) {
            this.dialogChannelIdleTime = idleTime;
            return this;
        }
    }
}
