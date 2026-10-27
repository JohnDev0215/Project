package com.amazon.alexa.voice.core.responders;

import com.amazon.alexa.voice.core.Function;
import com.amazon.alexa.voice.core.VoiceResponder;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ComposableResponder implements VoiceResponder {
    public final <E extends VoiceResponder> E compose(Function<VoiceResponder, E> function) {
        try {
            return function.apply(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
