package com.amazon.music.voice.playbackstate.directives;

import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.DirectiveBaseFactory;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class GUIPlaybackDisableRepeatDirective extends SuperbowlDirective {
    GUIPlaybackDisableRepeatDirective(Builder builder) {
        super(builder);
    }

    public static final class Builder extends SuperbowlDirective.Builder<GUIPlaybackDisableRepeatDirective> {
        public Builder() {
            super("Alexa.PlaylistController", "DisableRepeat");
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public GUIPlaybackDisableRepeatDirective build() {
            return new GUIPlaybackDisableRepeatDirective(this);
        }
    }

    public static final class Factory extends DirectiveBaseFactory<GUIPlaybackDisableRepeatDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public GUIPlaybackDisableRepeatDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            return (GUIPlaybackDisableRepeatDirective) new Builder().dialogRequestId(getDialogRequestId(jSONObject)).messageId(getMessageId(jSONObject)).build();
        }
    }
}
