package com.amazon.alexa.voice.core.responders;

import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class CompositeResponder extends ComposableResponder {
    private final List<ResponderHolder> responders = new ArrayList();

    public interface Filter {
        boolean isSupported(Directive directive);
    }

    public void add(VoiceResponder voiceResponder, Filter filter) {
        Preconditions.notNull(voiceResponder, "responder == null");
        Preconditions.notNull(filter, "filter == null");
        synchronized (this.responders) {
            this.responders.add(new ResponderHolder(voiceResponder, filter));
        }
    }

    @Override // com.amazon.alexa.voice.core.VoiceResponder
    public void respond(Consumable<Directive> consumable) {
        VoiceResponder voiceResponderFindResponder = findResponder(consumable.get());
        if (voiceResponderFindResponder == null) {
            consumable.reject(new IllegalArgumentException("No suitable responder was found for directive " + consumable.get()));
            return;
        }
        voiceResponderFindResponder.respond(consumable);
    }

    private VoiceResponder findResponder(Directive directive) {
        synchronized (this.responders) {
            int size = this.responders.size();
            for (int i = 0; i < size; i++) {
                ResponderHolder responderHolder = this.responders.get(i);
                if (responderHolder.filter.isSupported(directive)) {
                    return responderHolder.responder;
                }
            }
            return null;
        }
    }

    static final class ResponderHolder {
        private final Filter filter;
        private final VoiceResponder responder;

        public ResponderHolder(VoiceResponder voiceResponder, Filter filter) {
            this.responder = voiceResponder;
            this.filter = filter;
        }
    }

    public static final class InstanceFilter implements Filter {
        private final Class<? extends Directive> aClass;

        public InstanceFilter(Class<? extends Directive> cls) {
            Preconditions.notNull(cls, "aClass == null");
            this.aClass = cls;
        }

        @Override // com.amazon.alexa.voice.core.responders.CompositeResponder.Filter
        public boolean isSupported(Directive directive) {
            return this.aClass.isInstance(directive);
        }
    }
}
