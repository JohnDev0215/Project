package com.amazon.alexa.voice.core.processor.superbowl.context;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.Context;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class SpeakerVolumeContext extends Context {
    private final boolean muted;
    private final int volume;

    private SpeakerVolumeContext(Builder builder) {
        super(builder);
        this.muted = builder.muted;
        this.volume = builder.volume;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Context
    protected JSONObject getPayload() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, this.volume);
        jSONObject.put("muted", this.muted);
        return jSONObject;
    }

    public String toString() {
        return "SpeakerVolumeContext{namespace='" + getNamespace() + "', name='" + getName() + "', muted=" + this.muted + ", volume=" + this.volume + '}';
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Context
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SpeakerVolumeContext.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        SpeakerVolumeContext speakerVolumeContext = (SpeakerVolumeContext) obj;
        return this.muted == speakerVolumeContext.muted && this.volume == speakerVolumeContext.volume;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Context
    public int hashCode() {
        return (((super.hashCode() * 31) + (this.muted ? 1 : 0)) * 31) + this.volume;
    }

    public static final class Builder extends Context.Builder<SpeakerVolumeContext> {
        private boolean muted;
        private int volume;

        public Builder() {
            super("Speaker", "VolumeState");
        }

        public Builder muted(boolean z) {
            this.muted = z;
            return this;
        }

        public Builder volume(int i) {
            this.volume = i;
            return this;
        }

        public SpeakerVolumeContext build() {
            Preconditions.range(this.volume, 0, 100, "Volume has to be in range 0..100");
            return new SpeakerVolumeContext(this);
        }
    }
}
