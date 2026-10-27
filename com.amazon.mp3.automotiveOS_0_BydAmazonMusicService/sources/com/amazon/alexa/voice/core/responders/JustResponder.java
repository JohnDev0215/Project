package com.amazon.alexa.voice.core.responders;

import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.core.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class JustResponder extends ComposableResponder {
    private final Factory factory;
    private final VoiceResponder responder;

    public interface Factory {
        Consumable<Directive> create(Directive directive);
    }

    public JustResponder(VoiceResponder voiceResponder, Factory factory) {
        Preconditions.notNull(voiceResponder, "responder == null");
        Preconditions.notNull(factory, "factory == null");
        this.responder = voiceResponder;
        this.factory = factory;
    }

    public void respond(Directive directive) {
        Preconditions.notNull(directive, "directive == null");
        respond(this.factory.create(directive));
    }

    @Override // com.amazon.alexa.voice.core.VoiceResponder
    public void respond(Consumable<Directive> consumable) {
        this.responder.respond(consumable);
    }
}
