package com.amazon.alexa.voice.core.responders;

import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.core.channel.Channel;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;

/* JADX INFO: loaded from: classes2.dex */
public final class ChannelResponder extends ComposableResponder {
    private final Channel channel;
    private final Deque<Consumable<Directive>> responded;
    private final VoiceResponder responder;

    public ChannelResponder(VoiceResponder voiceResponder, Channel channel) {
        Preconditions.notNull(voiceResponder, "responder == null");
        Preconditions.notNull(channel, "channel == null");
        this.responder = voiceResponder;
        this.channel = channel;
        this.responded = new ArrayDeque();
    }

    @Override // com.amazon.alexa.voice.core.VoiceResponder
    public void respond(Consumable<Directive> consumable) {
        synchronized (this.responded) {
            this.responded.add(consumable);
        }
        this.channel.acquireActivation();
        this.responder.respond(new ChannelConsumable(consumable));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void consumed(Consumable<Directive> consumable) {
        synchronized (this.responded) {
            if (this.responded.remove(consumable)) {
                this.channel.releaseActivation();
            }
        }
    }

    class ChannelConsumable implements Consumable<Directive> {
        private final Consumable<Directive> consumable;

        public ChannelConsumable(Consumable<Directive> consumable) {
            this.consumable = consumable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.voice.core.Consumable
        public Directive get() {
            return this.consumable.get();
        }

        @Override // com.amazon.alexa.voice.core.Consumable
        public void accept() {
            ChannelResponder.this.consumed(this.consumable);
            this.consumable.accept();
        }

        @Override // com.amazon.alexa.voice.core.Consumable
        public void reject(Throwable th) {
            ChannelResponder.this.consumed(this.consumable);
            this.consumable.reject(th);
        }
    }
}
