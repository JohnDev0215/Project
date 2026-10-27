package com.amazon.music.voice.playbackstate.directives;

import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.DirectiveBaseFactory;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class GUIPlaybackNextDirective extends SuperbowlDirective {
    GUIPlaybackNextDirective(Builder builder) {
        super(builder);
    }

    public static final class Builder extends SuperbowlDirective.Builder<GUIPlaybackNextDirective> {
        public Builder() {
            super("Alexa.PlaybackController", "Next");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public GUIPlaybackNextDirective build() {
            return new GUIPlaybackNextDirective(this);
        }
    }

    public static final class Factory extends DirectiveBaseFactory<GUIPlaybackNextDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public GUIPlaybackNextDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            return (GUIPlaybackNextDirective) new Builder().dialogRequestId(getDialogRequestId(jSONObject)).messageId(getMessageId(jSONObject)).build();
        }
    }
}
