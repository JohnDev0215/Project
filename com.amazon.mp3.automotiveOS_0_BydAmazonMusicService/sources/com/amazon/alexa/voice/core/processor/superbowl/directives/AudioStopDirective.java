package com.amazon.alexa.voice.core.processor.superbowl.directives;

import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioStopDirective extends SuperbowlDirective {
    public String toString() {
        return "AudioStopDirective{}";
    }

    AudioStopDirective(Builder builder) {
        super(builder);
    }

    public static final class Builder extends SuperbowlDirective.Builder<AudioStopDirective> {
        public Builder() {
            super("AudioPlayer", "Stop");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public AudioStopDirective build() {
            return new AudioStopDirective(this);
        }
    }

    public static final class Factory extends DirectiveBaseFactory<AudioStopDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public AudioStopDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            return (AudioStopDirective) new Builder().dialogRequestId(getDialogRequestId(jSONObject)).messageId(getMessageId(jSONObject)).build();
        }
    }
}
