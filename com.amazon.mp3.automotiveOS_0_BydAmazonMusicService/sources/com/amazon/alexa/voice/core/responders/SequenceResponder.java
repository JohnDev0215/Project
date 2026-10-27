package com.amazon.alexa.voice.core.responders;

import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes2.dex */
public final class SequenceResponder extends ComposableResponder {
    private Consumable<Directive> currentConsumable;
    private final Deque<Consumable<Directive>> queue;
    private final VoiceResponder responder;

    public SequenceResponder(VoiceResponder voiceResponder) {
        Preconditions.notNull(voiceResponder, "responder == null");
        this.responder = voiceResponder;
        this.queue = new ArrayDeque();
    }

    @Override // com.amazon.alexa.voice.core.VoiceResponder
    public void respond(Consumable<Directive> consumable) {
        boolean z;
        synchronized (this.queue) {
            if (this.currentConsumable == null) {
                z = true;
                this.currentConsumable = consumable;
            } else {
                z = false;
                this.queue.add(consumable);
            }
        }
        if (z) {
            this.responder.respond(new SequenceConsumable(consumable));
        }
    }

    public int size() {
        int size;
        synchronized (this.queue) {
            size = this.queue.size();
        }
        return size;
    }

    public void clear() {
        CancellationException cancellationException = new CancellationException("Directives sequence was dropped!");
        synchronized (this.queue) {
            while (true) {
                Consumable<Directive> consumablePoll = this.queue.poll();
                if (consumablePoll != null) {
                    consumablePoll.reject(cancellationException);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void respondNext() {
        Consumable<Directive> consumablePoll;
        synchronized (this.queue) {
            consumablePoll = this.queue.poll();
            this.currentConsumable = consumablePoll;
        }
        if (consumablePoll != null) {
            this.responder.respond(new SequenceConsumable(consumablePoll));
        }
    }

    final class SequenceConsumable implements Consumable<Directive> {
        private final Consumable<Directive> consumable;

        public SequenceConsumable(Consumable<Directive> consumable) {
            this.consumable = consumable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.voice.core.Consumable
        public Directive get() {
            return this.consumable.get();
        }

        @Override // com.amazon.alexa.voice.core.Consumable
        public void accept() {
            this.consumable.accept();
            SequenceResponder.this.respondNext();
        }

        @Override // com.amazon.alexa.voice.core.Consumable
        public void reject(Throwable th) {
            this.consumable.reject(th);
            SequenceResponder.this.respondNext();
        }
    }
}
