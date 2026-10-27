package com.amazon.music.voice.text;

import com.amazon.alexa.voice.superbowl.VoicePlugin;
import com.amazon.music.voice.text.TextMessageEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextPlugin.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/voice/text/TextPlugin;", "Lcom/amazon/alexa/voice/superbowl/VoicePlugin;", "()V", "cancel", "", "sendTextMessageEvent", "textMessage", "", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TextPlugin extends VoicePlugin {
    public final void sendTextMessageEvent(String textMessage) {
        Intrinsics.checkNotNullParameter(textMessage, "textMessage");
        postEvent((TextMessageEvent) new TextMessageEvent.Builder(textMessage).addContext(resolveContext()).dialogRequestId(getDialogRequest().newRequestId()).build());
    }

    public final void cancel() {
        getDialogRequest().newRequestId();
    }
}
