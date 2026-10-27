package com.amazon.music.voice.playbackstate.directives;

import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.DirectiveBaseFactory;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class GUIPlaybackPreviousDirective extends SuperbowlDirective {
    GUIPlaybackPreviousDirective(Builder builder) {
        super(builder);
    }

    public static final class Builder extends SuperbowlDirective.Builder<GUIPlaybackPreviousDirective> {
        public Builder() {
            super("Alexa.PlaybackController", "Previous");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public GUIPlaybackPreviousDirective build() {
            return new GUIPlaybackPreviousDirective(this);
        }
    }

    public static final class Factory extends DirectiveBaseFactory<GUIPlaybackPreviousDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public GUIPlaybackPreviousDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            return (GUIPlaybackPreviousDirective) new Builder().dialogRequestId(getDialogRequestId(jSONObject)).messageId(getMessageId(jSONObject)).build();
        }
    }
}
