package com.amazon.alexa.voice.core.responders;

import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes2.dex */
public final class FilterResponder extends ComposableResponder {
    private final Filter filter;
    private final VoiceResponder responder;

    public interface Filter {
        boolean shouldRespond(Directive directive);
    }

    public FilterResponder(VoiceResponder voiceResponder, Filter filter) {
        Preconditions.notNull(voiceResponder, "responder == null");
        Preconditions.notNull(filter, "filter == null");
        this.responder = voiceResponder;
        this.filter = filter;
    }

    @Override // com.amazon.alexa.voice.core.VoiceResponder
    public void respond(Consumable<Directive> consumable) {
        if (this.filter.shouldRespond(consumable.get())) {
            this.responder.respond(consumable);
        } else {
            consumable.reject(new CancellationException("Directive was not responded"));
        }
    }
}
