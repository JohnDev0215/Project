package com.amazon.alexa.voice.superbowl;

import com.amazon.alexa.voice.core.Action;
import com.amazon.alexa.voice.core.Cancellable;
import com.amazon.alexa.voice.core.Consumer;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.Event;
import com.amazon.alexa.voice.core.Executable;
import com.amazon.alexa.voice.core.Scheduler;
import com.amazon.alexa.voice.core.VoiceProcessor;
import com.amazon.alexa.voice.core.VoiceProcessorException;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.Context;
import com.amazon.alexa.voice.core.processor.superbowl.ContextResolver;
import com.amazon.alexa.voice.core.processor.superbowl.Downchannel;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import com.amazon.alexa.voice.core.responders.CompositeResponder;
import com.amazon.alexa.voice.core.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VoicePlugin {
    private Delegate delegate;
    private volatile boolean initialized;
    private final List<VoiceResponder> addedResponders = new ArrayList();
    private final List<ContextResolver<? extends Context>> addedContextResolvers = new ArrayList();

    interface Delegate {
        void addContextResolver(ContextResolver<? extends Context> contextResolver);

        void addResponder(VoiceResponder voiceResponder, CompositeResponder.Filter filter);

        void addResponderInSequence(VoiceResponder voiceResponder, CompositeResponder.Filter filter);

        Dialog getDialogRequest();

        Downchannel getDownchannel();

        Scheduler getEventScheduler();

        VoiceProcessor getProcessor();

        void handleException(VoiceProcessorException voiceProcessorException);

        Cancellable postEvent(Event event);

        Collection<? extends Context> resolveContext();

        void respondToDirectives(Directive[] directiveArr);
    }

    protected void onInitialize() {
    }

    protected VoicePlugin() {
    }

    final void initialize(Delegate delegate) {
        if (this.initialized) {
            return;
        }
        this.initialized = true;
        this.delegate = delegate;
        onInitialize();
    }

    public final boolean isInitialized() {
        return this.initialized;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Dialog getDialogRequest() {
        Preconditions.notNull(this.delegate, "Voice plugin is not initialized!");
        return this.delegate.getDialogRequest();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Scheduler getEventScheduler() {
        Preconditions.notNull(this.delegate, "Voice plugin is not initialized!");
        return this.delegate.getEventScheduler();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final VoiceProcessor getProcessor() {
        return this.delegate.getProcessor();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Downchannel getDownchannel() {
        return this.delegate.getDownchannel();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleException(VoiceProcessorException voiceProcessorException) {
        this.delegate.handleException(voiceProcessorException);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void respondToDirectives(Directive[] directiveArr) {
        Preconditions.mainThread("respondToDirectives() has to be called from the main thread");
        Preconditions.notNull(this.delegate, "Voice plugin is not initialized!");
        this.delegate.respondToDirectives(directiveArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Cancellable postEvent(Event event) {
        Preconditions.notNull(event, "event == null");
        return this.delegate.postEvent(event);
    }

    protected final Cancellable postEventAsync(SuperbowlEvent.Factory factory) {
        return postEventAsync(factory, null);
    }

    protected final Cancellable postEventAsync(final SuperbowlEvent.Factory factory, final Consumer<Throwable> consumer) {
        return new Executable(new Action() { // from class: com.amazon.alexa.voice.superbowl.VoicePlugin.1
            @Override // com.amazon.alexa.voice.core.Action
            public void call() throws Exception {
                VoicePlugin.this.delegate.postEvent(factory.create());
            }
        }).executeOn(Schedulers.io()).execute(new Consumer<Throwable>() { // from class: com.amazon.alexa.voice.superbowl.VoicePlugin.2
            @Override // com.amazon.alexa.voice.core.Consumer
            public void accept(Throwable th) throws Exception {
                Consumer consumer2 = consumer;
                if (consumer2 != null) {
                    consumer2.accept(th);
                }
            }
        });
    }

    protected final void addResponder(VoiceResponder voiceResponder, CompositeResponder.Filter filter) {
        Preconditions.notNull(voiceResponder, "responder == null");
        Preconditions.notNull(this.delegate, "Voice plugin is not initialized!");
        synchronized (this.addedResponders) {
            this.addedResponders.add(voiceResponder);
        }
        this.delegate.addResponder(voiceResponder, filter);
    }

    protected final void addResponder(VoiceResponder voiceResponder, Class<? extends Directive> cls) {
        Preconditions.notNull(cls, "aClass == null");
        Preconditions.notNull(this.delegate, "Voice plugin is not initialized!");
        addResponder(voiceResponder, new CompositeResponder.InstanceFilter(cls));
    }

    protected final void addResponderInSequence(VoiceResponder voiceResponder, CompositeResponder.Filter filter) {
        Preconditions.notNull(voiceResponder, "responder == null");
        Preconditions.notNull(this.delegate, "Voice plugin is not initialized!");
        synchronized (this.addedResponders) {
            this.addedResponders.add(voiceResponder);
        }
        this.delegate.addResponderInSequence(voiceResponder, filter);
    }

    protected final void addResponderInSequence(VoiceResponder voiceResponder, Class<? extends Directive> cls) {
        Preconditions.notNull(cls, "aClass == null");
        Preconditions.notNull(this.delegate, "Voice plugin is not initialized!");
        addResponderInSequence(voiceResponder, new CompositeResponder.InstanceFilter(cls));
    }

    protected final void addContextResolver(ContextResolver<? extends Context> contextResolver) {
        Preconditions.notNull(contextResolver, "contextResolver == null");
        Preconditions.notNull(this.delegate, "Voice plugin is not initialized!");
        synchronized (this.addedContextResolvers) {
            this.addedContextResolvers.add(contextResolver);
        }
        this.delegate.addContextResolver(contextResolver);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Collection<? extends Context> resolveContext() {
        return this.delegate.resolveContext();
    }
}
