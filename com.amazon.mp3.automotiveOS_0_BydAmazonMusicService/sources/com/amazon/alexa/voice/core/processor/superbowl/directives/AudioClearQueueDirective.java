package com.amazon.alexa.voice.core.processor.superbowl.directives;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioClearQueueDirective extends SuperbowlDirective {
    private final ClearBehavior clearBehavior;

    AudioClearQueueDirective(Builder builder) {
        super(builder);
        this.clearBehavior = builder.clearBehavior;
    }

    public ClearBehavior getClearBehavior() {
        return this.clearBehavior;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && AudioClearQueueDirective.class == obj.getClass() && super.equals(obj) && this.clearBehavior == ((AudioClearQueueDirective) obj).clearBehavior;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective
    public int hashCode() {
        return (super.hashCode() * 31) + this.clearBehavior.hashCode();
    }

    public String toString() {
        return "AudioClearQueueDirective{clearBehavior=" + this.clearBehavior + '}';
    }

    public static final class Builder extends SuperbowlDirective.Builder<AudioClearQueueDirective> {
        ClearBehavior clearBehavior;

        public Builder() {
            super("AudioPlayer", "ClearQueue");
        }

        public Builder clearBehavior(ClearBehavior clearBehavior) {
            this.clearBehavior = clearBehavior;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public AudioClearQueueDirective build() {
            Preconditions.notNull(this.clearBehavior, "clearBehavior == null");
            return new AudioClearQueueDirective(this);
        }
    }

    public static final class Factory extends DirectiveBaseFactory<AudioClearQueueDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public AudioClearQueueDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            return (AudioClearQueueDirective) new Builder().clearBehavior(ClearBehavior.parse(jSONObject.getJSONObject("payload").getString("clearBehavior"))).dialogRequestId(getDialogRequestId(jSONObject)).messageId(getMessageId(jSONObject)).build();
        }
    }

    public enum ClearBehavior {
        CLEAR_ENQUEUED("CLEAR_ENQUEUED"),
        CLEAR_ALL("CLEAR_ALL"),
        UNKNOWN("UNKNOWN");

        private final String id;

        ClearBehavior(String str) {
            this.id = str;
        }

        public String getId() {
            return this.id;
        }

        public static ClearBehavior parse(String str) {
            for (ClearBehavior clearBehavior : values()) {
                if (clearBehavior.id.equalsIgnoreCase(str)) {
                    return clearBehavior;
                }
            }
            return UNKNOWN;
        }
    }
}
