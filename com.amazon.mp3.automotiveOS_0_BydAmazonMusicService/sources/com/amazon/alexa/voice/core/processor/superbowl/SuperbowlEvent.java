package com.amazon.alexa.voice.core.processor.superbowl;

import com.amazon.alexa.voice.core.AudioFormat;
import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.Event;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.internal.util.RandomString;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SuperbowlEvent implements Event {
    private final AudioFormat audioFormat;
    private final AudioSource audioSource;
    private final List<Context> contextList;
    private final String dialogRequestId;
    private final String messageId;
    private final String name;
    private final String namespace;

    public static abstract class Factory<T extends SuperbowlEvent> {
        public abstract T create();
    }

    protected SuperbowlEvent(Builder<? extends SuperbowlEvent> builder) {
        Preconditions.notNull(builder.name, "name == null");
        Preconditions.notNull(builder.namespace, "namespace == null");
        Preconditions.notNull(builder.messageId, "messageId == null");
        this.name = builder.name;
        this.namespace = builder.namespace;
        this.messageId = builder.messageId;
        this.dialogRequestId = builder.dialogRequestId;
        this.audioFormat = builder.audioFormat;
        this.audioSource = builder.audioSource;
        this.contextList = builder.contextList;
    }

    public final String getMessageId() {
        return this.messageId;
    }

    public final String getDialogRequestId() {
        return this.dialogRequestId;
    }

    public final AudioFormat getAudioFormat() {
        return this.audioFormat;
    }

    public final AudioSource getAudioSource() {
        return this.audioSource;
    }

    public final String getNamespace() {
        return this.namespace;
    }

    public final String getName() {
        return this.name;
    }

    protected JSONObject getPayload() throws JSONException {
        return new JSONObject();
    }

    protected JSONObject getHeader() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("namespace", this.namespace);
        jSONObject.put("name", this.name);
        jSONObject.put("messageId", this.messageId);
        jSONObject.put("dialogRequestId", this.dialogRequestId);
        return jSONObject;
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", getHeader());
        jSONObject.put("payload", getPayload());
        return jSONObject;
    }

    public final Context[] getContext() {
        List<Context> list = this.contextList;
        return (Context[]) list.toArray(new Context[list.size()]);
    }

    protected final String sanitizeAudioFormat() {
        if ("audio/L16".equalsIgnoreCase(this.audioFormat.getMime()) && this.audioFormat.getChannelCount() == 1 && this.audioFormat.getSampleRate() == 16000 && this.audioFormat.getBitRate() == 256000) {
            return "AUDIO_L16_RATE_16000_CHANNELS_1";
        }
        if (voMimeTypes.VOAUDIO_OPUS.equalsIgnoreCase(this.audioFormat.getMime()) && this.audioFormat.getChannelCount() == 1 && this.audioFormat.getSampleRate() == 16000 && this.audioFormat.getBitRate() == 32000) {
            return "AUDIO_X_CBR_OPUS_WITH_PREAMBLE_SIZE_0_BIT_RATE_32000_FRAME_SIZE_MILLISECONDS_20";
        }
        if (voMimeTypes.VOAUDIO_OPUS.equalsIgnoreCase(this.audioFormat.getMime()) && this.audioFormat.getChannelCount() == 1 && this.audioFormat.getSampleRate() == 16000 && this.audioFormat.getBitRate() == 16000) {
            return "AUDIO_X_CBR_OPUS_WITH_PREAMBLE_SIZE_0_BIT_RATE_16000_FRAME_SIZE_MILLISECONDS_20";
        }
        throw new IllegalArgumentException("Audio format is not supported " + this.audioFormat);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SuperbowlEvent superbowlEvent = (SuperbowlEvent) obj;
        if (!this.namespace.equals(superbowlEvent.namespace) || !this.name.equals(superbowlEvent.name) || !this.messageId.equals(superbowlEvent.messageId)) {
            return false;
        }
        String str = this.dialogRequestId;
        if (str == null ? superbowlEvent.dialogRequestId != null : !str.equals(superbowlEvent.dialogRequestId)) {
            return false;
        }
        AudioFormat audioFormat = this.audioFormat;
        if (audioFormat == null ? superbowlEvent.audioFormat != null : !audioFormat.equals(superbowlEvent.audioFormat)) {
            return false;
        }
        AudioSource audioSource = this.audioSource;
        if (audioSource == null ? superbowlEvent.audioSource == null : audioSource.equals(superbowlEvent.audioSource)) {
            return this.contextList.equals(superbowlEvent.contextList);
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = ((((this.namespace.hashCode() * 31) + this.name.hashCode()) * 31) + this.messageId.hashCode()) * 31;
        String str = this.dialogRequestId;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        AudioFormat audioFormat = this.audioFormat;
        int iHashCode3 = (iHashCode2 + (audioFormat != null ? audioFormat.hashCode() : 0)) * 31;
        AudioSource audioSource = this.audioSource;
        return ((iHashCode3 + (audioSource != null ? audioSource.hashCode() : 0)) * 31) + this.contextList.hashCode();
    }

    public static abstract class Builder<T extends SuperbowlEvent> {
        protected AudioFormat audioFormat;
        protected AudioSource audioSource;
        String dialogRequestId;
        String name;
        String namespace;
        List<Context> contextList = new ArrayList();
        String messageId = RandomString.string();

        public abstract T build();

        protected Builder(String str, String str2) {
            this.namespace = str;
            this.name = str2;
        }

        public final Builder<T> messageId(String str) {
            this.messageId = str;
            return this;
        }

        public final Builder<T> dialogRequestId(String str) {
            this.dialogRequestId = str;
            return this;
        }

        public final Builder<T> audio(AudioSource audioSource, AudioFormat audioFormat) {
            this.audioSource = audioSource;
            this.audioFormat = audioFormat;
            return this;
        }

        public final Builder<T> addContext(Context context) {
            Preconditions.notNull(context, "context == null");
            this.contextList.add(context);
            return this;
        }

        public final Builder<T> addContext(Collection<? extends Context> collection) {
            Preconditions.notNull(collection, "context == null");
            this.contextList.addAll(collection);
            return this;
        }
    }
}
