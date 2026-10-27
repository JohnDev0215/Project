package com.amazon.music.voice.playbackstate.directives;

import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.DirectiveBaseFactory;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class GUIPlaybackEnableRepeatOneDirective extends SuperbowlDirective {
    GUIPlaybackEnableRepeatOneDirective(Builder builder) {
        super(builder);
    }

    public static final class Builder extends SuperbowlDirective.Builder<GUIPlaybackEnableRepeatOneDirective> {
        public Builder() {
            super("Alexa.PlaylistController", "EnableRepeatOne");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public GUIPlaybackEnableRepeatOneDirective build() {
            return new GUIPlaybackEnableRepeatOneDirective(this);
        }
    }

    public static final class Factory extends DirectiveBaseFactory<GUIPlaybackEnableRepeatOneDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public GUIPlaybackEnableRepeatOneDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            return (GUIPlaybackEnableRepeatOneDirective) new Builder().dialogRequestId(getDialogRequestId(jSONObject)).messageId(getMessageId(jSONObject)).build();
        }
    }
}
