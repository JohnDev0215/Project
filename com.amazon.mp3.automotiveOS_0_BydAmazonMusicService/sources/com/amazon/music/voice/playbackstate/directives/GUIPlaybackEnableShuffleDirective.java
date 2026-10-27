package com.amazon.music.voice.playbackstate.directives;

import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.DirectiveBaseFactory;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class GUIPlaybackEnableShuffleDirective extends SuperbowlDirective {
    GUIPlaybackEnableShuffleDirective(Builder builder) {
        super(builder);
    }

    public static final class Builder extends SuperbowlDirective.Builder<GUIPlaybackEnableShuffleDirective> {
        public Builder() {
            super("Alexa.PlaylistController", "EnableShuffle");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public GUIPlaybackEnableShuffleDirective build() {
            return new GUIPlaybackEnableShuffleDirective(this);
        }
    }

    public static final class Factory extends DirectiveBaseFactory<GUIPlaybackEnableShuffleDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public GUIPlaybackEnableShuffleDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            return (GUIPlaybackEnableShuffleDirective) new Builder().dialogRequestId(getDialogRequestId(jSONObject)).messageId(getMessageId(jSONObject)).build();
        }
    }
}
