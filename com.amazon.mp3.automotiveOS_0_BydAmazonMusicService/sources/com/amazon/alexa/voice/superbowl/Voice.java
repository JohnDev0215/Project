package com.amazon.alexa.voice.superbowl;

import com.amazon.alexa.voice.core.Action;
import com.amazon.alexa.voice.core.Cancellable;
import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.Consumables;
import com.amazon.alexa.voice.core.Consumer;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.Event;
import com.amazon.alexa.voice.core.Executable;
import com.amazon.alexa.voice.core.Function;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.Scheduler;
import com.amazon.alexa.voice.core.Task;
import com.amazon.alexa.voice.core.VoiceProcessor;
import com.amazon.alexa.voice.core.VoiceProcessorException;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.Context;
import com.amazon.alexa.voice.core.processor.superbowl.ContextResolver;
import com.amazon.alexa.voice.core.processor.superbowl.Downchannel;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlProcessor;
import com.amazon.alexa.voice.core.processor.superbowl.events.ExceptionEncounteredEvent;
import com.amazon.alexa.voice.core.responders.CompositeResponder;
import com.amazon.alexa.voice.core.responders.FilterResponder;
import com.amazon.alexa.voice.core.responders.JustResponder;
import com.amazon.alexa.voice.core.responders.SequenceResponder;
import com.amazon.alexa.voice.core.schedulers.Schedulers;
import com.amazon.alexa.voice.superbowl.VoicePlugin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public final class Voice {
    private final List<ContextResolver<? extends Context>> contextResolverList;
    private final Dialog dialog;
    private final DirectiveProcessingHelper directiveProcessingHelper;
    private final Downchannel downchannel;
    private final VoiceProcessor.ErrorListener errorListener;
    private final Scheduler eventScheduler;
    private final CompositeResponder extendedResponder;
    private final CompositeResponder extendedSequenceResponder;
    private volatile int pendingEventCount;
    private final PluginDelegate pluginDelegate;
    private final List<VoicePlugin> pluginList;
    private final SuperbowlProcessor processor;
    private final JustResponder responder;
    private final SequenceResponder sequenceResponder;

    public interface DirectiveProcessingListener {
        void onDirectiveAccepted(Directive directive);

        void onDirectiveReceived(Directive directive);

        void onDirectiveRejected(Directive directive, Throwable th);

        void onDirectivesReceived(Directive[] directiveArr);

        void onDirectivesResponded();

        void onDirectivesScheduled();
    }

    public static class SimpleDirectiveProcessingListener implements DirectiveProcessingListener {
        @Override // com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
        public void onDirectiveAccepted(Directive directive) {
        }

        @Override // com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
        public void onDirectiveReceived(Directive directive) {
        }

        @Override // com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
        public void onDirectiveRejected(Directive directive, Throwable th) {
        }

        @Override // com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
        public void onDirectivesReceived(Directive[] directiveArr) {
        }

        @Override // com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
        public void onDirectivesResponded() {
        }

        @Override // com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
        public void onDirectivesScheduled() {
        }
    }

    static /* synthetic */ int access$010(Voice voice) {
        int i = voice.pendingEventCount;
        voice.pendingEventCount = i - 1;
        return i;
    }

    Voice(Builder builder) {
        ArrayList arrayList = new ArrayList(builder.pluginList);
        this.pluginList = arrayList;
        SuperbowlProcessor superbowlProcessor = builder.processor;
        this.processor = superbowlProcessor;
        this.downchannel = builder.downchannel;
        this.errorListener = builder.errorListener;
        this.contextResolverList = builder.contextResolverList;
        this.dialog = new Dialog();
        this.eventScheduler = builder.scheduler;
        this.pluginDelegate = new PluginDelegate();
        CompositeResponder compositeResponder = new CompositeResponder();
        this.extendedResponder = compositeResponder;
        CompositeResponder compositeResponder2 = new CompositeResponder();
        this.extendedSequenceResponder = compositeResponder2;
        SequenceResponder sequenceResponder = new SequenceResponder(compositeResponder2);
        this.sequenceResponder = sequenceResponder;
        this.responder = createJustResponder(compositeResponder);
        this.directiveProcessingHelper = new DirectiveProcessingHelper();
        compositeResponder.add(sequenceResponder, builder.sequencingFilter);
        superbowlProcessor.setUnsupportedDirectiveHandler(new SuperbowlProcessor.UnsupportedDirectiveHandler() { // from class: com.amazon.alexa.voice.superbowl.Voice.1
            @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlProcessor.UnsupportedDirectiveHandler
            public boolean onUnsupportedDirective(String str, Throwable th) {
                Logger.error("An unsupported directive was found and won't be processed", th);
                Voice.this.postEvent(new ExceptionEncounteredEvent.Builder().unparsedDirective(str).error(new ExceptionEncounteredEvent.Error(ExceptionEncounteredEvent.Error.Type.UNEXPECTED_INFORMATION_RECEIVED, "An unsupported directive was found and won't be processed")).build());
                return true;
            }
        });
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((VoicePlugin) it.next()).initialize(this.pluginDelegate);
        }
    }

    public void cancel() {
        this.sequenceResponder.clear();
    }

    public int getNumberOfSequencedDirectives() {
        return this.sequenceResponder.size();
    }

    public Cancellable postEvent(final Event event) {
        Preconditions.notNull(event, "event == null");
        this.pendingEventCount++;
        this.directiveProcessingHelper.notifyDirectivesScheduled();
        return new Executable(new Function<Task<Directive[]>, Directive[]>() { // from class: com.amazon.alexa.voice.superbowl.Voice.5
            @Override // com.amazon.alexa.voice.core.Function
            public Directive[] apply(Task<Directive[]> task) throws Exception {
                return Voice.this.processor.execute(event);
            }
        }).executeOn(this.eventScheduler).consumeOn(Schedulers.main()).onCancel(new Action() { // from class: com.amazon.alexa.voice.superbowl.Voice.4
            @Override // com.amazon.alexa.voice.core.Action
            public void call() throws Exception {
                Voice.access$010(Voice.this);
                Voice.this.directiveProcessingHelper.notifyDirectivesResponded();
            }
        }).execute(new Consumer<Directive[]>() { // from class: com.amazon.alexa.voice.superbowl.Voice.2
            @Override // com.amazon.alexa.voice.core.Consumer
            public void accept(Directive[] directiveArr) throws Exception {
                Voice.access$010(Voice.this);
                Voice.this.directiveProcessingHelper.received(directiveArr);
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.alexa.voice.superbowl.Voice.3
            @Override // com.amazon.alexa.voice.core.Consumer
            public void accept(Throwable th) throws Exception {
                Logger.error("Failed to send an event", th);
                Voice.access$010(Voice.this);
                Voice.this.directiveProcessingHelper.notifyDirectivesResponded();
                if (th instanceof VoiceProcessorException) {
                    Voice.this.handleException((VoiceProcessorException) th);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleException(VoiceProcessorException voiceProcessorException) {
        VoiceProcessor.ErrorListener errorListener = this.errorListener;
        if (errorListener == null) {
            return;
        }
        errorListener.onError(voiceProcessorException);
    }

    public void addPlugin(VoicePlugin voicePlugin) {
        Preconditions.mainThread("addPlugin() has to be called from the main thread");
        Preconditions.notNull(voicePlugin, "plugin == null");
        if (voicePlugin.isInitialized()) {
            throw new IllegalStateException("Voice plugin is already initialized!");
        }
        this.pluginList.add(voicePlugin);
        voicePlugin.initialize(this.pluginDelegate);
    }

    private JustResponder createJustResponder(VoiceResponder voiceResponder) {
        return new JustResponder(new FilterResponder(voiceResponder, new FilterResponder.Filter() { // from class: com.amazon.alexa.voice.superbowl.Voice.6
            @Override // com.amazon.alexa.voice.core.responders.FilterResponder.Filter
            public boolean shouldRespond(Directive directive) {
                if (!(directive instanceof SuperbowlDirective)) {
                    return false;
                }
                String dialogRequestId = ((SuperbowlDirective) directive).getDialogRequestId();
                return dialogRequestId == null || dialogRequestId.equals(Voice.this.dialog.getRequestId());
            }
        }), new JustResponder.Factory() { // from class: com.amazon.alexa.voice.superbowl.Voice.7
            @Override // com.amazon.alexa.voice.core.responders.JustResponder.Factory
            public Consumable<Directive> create(final Directive directive) {
                return Consumables.of(directive, new Consumer<Directive>() { // from class: com.amazon.alexa.voice.superbowl.Voice.7.1
                    @Override // com.amazon.alexa.voice.core.Consumer
                    public void accept(Directive directive2) throws Exception {
                        Logger.verbose("Directive was accepted " + directive2);
                        Voice.this.directiveProcessingHelper.accepted(directive2);
                    }
                }, new Consumer<Throwable>() { // from class: com.amazon.alexa.voice.superbowl.Voice.7.2
                    @Override // com.amazon.alexa.voice.core.Consumer
                    public void accept(Throwable th) throws Exception {
                        Logger.error("Directive was rejected", th);
                        Voice.this.directiveProcessingHelper.rejected(directive, th);
                    }
                });
            }
        });
    }

    public void addDirectiveProcessingListener(DirectiveProcessingListener directiveProcessingListener) {
        this.directiveProcessingHelper.addListener(directiveProcessingListener);
    }

    final class DirectiveProcessingHelper {
        private final Set<Directive> directiveSet = new HashSet(6);
        private final List<DirectiveProcessingListener> listenerList = new ArrayList();
        private final AtomicBoolean directivesScheduled = new AtomicBoolean();

        public DirectiveProcessingHelper() {
        }

        public void addListener(DirectiveProcessingListener directiveProcessingListener) {
            synchronized (this.listenerList) {
                this.listenerList.add(directiveProcessingListener);
            }
        }

        public void accepted(Directive directive) {
            synchronized (this.directiveSet) {
                if (this.directiveSet.remove(directive)) {
                    synchronized (this.listenerList) {
                        for (int size = this.listenerList.size() - 1; size >= 0; size--) {
                            this.listenerList.get(size).onDirectiveAccepted(directive);
                        }
                    }
                    notifyDirectivesResponded();
                }
            }
        }

        public void rejected(Directive directive, Throwable th) {
            synchronized (this.directiveSet) {
                if (this.directiveSet.remove(directive)) {
                    synchronized (this.listenerList) {
                        for (int size = this.listenerList.size() - 1; size >= 0; size--) {
                            this.listenerList.get(size).onDirectiveRejected(directive, th);
                        }
                    }
                    notifyDirectivesResponded();
                }
            }
        }

        public void notifyDirectivesResponded() {
            if (Voice.this.pendingEventCount != 0) {
                return;
            }
            synchronized (this.directiveSet) {
                if (this.directiveSet.isEmpty()) {
                    if (this.directivesScheduled.compareAndSet(true, false)) {
                        synchronized (this.listenerList) {
                            for (int size = this.listenerList.size() - 1; size >= 0; size--) {
                                this.listenerList.get(size).onDirectivesResponded();
                            }
                        }
                    }
                }
            }
        }

        public void notifyDirectivesScheduled() {
            if (this.directivesScheduled.compareAndSet(false, true)) {
                synchronized (this.listenerList) {
                    for (int size = this.listenerList.size() - 1; size >= 0; size--) {
                        this.listenerList.get(size).onDirectivesScheduled();
                    }
                }
            }
        }

        public void received(Directive[] directiveArr) {
            notifyDirectivesScheduled();
            synchronized (this.directiveSet) {
                this.directiveSet.addAll(Arrays.asList(directiveArr));
            }
            synchronized (this.listenerList) {
                for (int size = this.listenerList.size() - 1; size >= 0; size--) {
                    this.listenerList.get(size).onDirectivesReceived(directiveArr);
                }
            }
            if (directiveArr.length == 0) {
                notifyDirectivesResponded();
                return;
            }
            for (Directive directive : directiveArr) {
                synchronized (this.listenerList) {
                    for (int size2 = this.listenerList.size() - 1; size2 >= 0; size2--) {
                        this.listenerList.get(size2).onDirectiveReceived(directive);
                    }
                }
                Voice.this.responder.respond(directive);
            }
        }
    }

    final class PluginDelegate implements VoicePlugin.Delegate {
        PluginDelegate() {
        }

        @Override // com.amazon.alexa.voice.superbowl.VoicePlugin.Delegate
        public Dialog getDialogRequest() {
            return Voice.this.dialog;
        }

        @Override // com.amazon.alexa.voice.superbowl.VoicePlugin.Delegate
        public void handleException(VoiceProcessorException voiceProcessorException) {
            Voice.this.handleException(voiceProcessorException);
        }

        @Override // com.amazon.alexa.voice.superbowl.VoicePlugin.Delegate
        public Scheduler getEventScheduler() {
            return Voice.this.eventScheduler;
        }

        @Override // com.amazon.alexa.voice.superbowl.VoicePlugin.Delegate
        public VoiceProcessor getProcessor() {
            if (Voice.this.processor != null) {
                return Voice.this.processor;
            }
            throw new IllegalStateException("Voice does not support an execution of events during an initialization");
        }

        @Override // com.amazon.alexa.voice.superbowl.VoicePlugin.Delegate
        public Downchannel getDownchannel() {
            return Voice.this.downchannel;
        }

        @Override // com.amazon.alexa.voice.superbowl.VoicePlugin.Delegate
        public Collection<? extends Context> resolveContext() {
            ArrayList arrayList = new ArrayList();
            synchronized (Voice.this.contextResolverList) {
                for (int size = Voice.this.contextResolverList.size() - 1; size >= 0; size--) {
                    Context contextResolve = ((ContextResolver) Voice.this.contextResolverList.get(size)).resolve();
                    if (contextResolve != null) {
                        arrayList.add(contextResolve);
                    }
                }
            }
            return arrayList;
        }

        @Override // com.amazon.alexa.voice.superbowl.VoicePlugin.Delegate
        public Cancellable postEvent(Event event) {
            return Voice.this.postEvent(event);
        }

        @Override // com.amazon.alexa.voice.superbowl.VoicePlugin.Delegate
        public void respondToDirectives(Directive[] directiveArr) {
            if (Voice.this.responder != null) {
                Voice.this.directiveProcessingHelper.received(directiveArr);
                return;
            }
            throw new IllegalStateException("Voice does not support responding to directives during an initialization");
        }

        @Override // com.amazon.alexa.voice.superbowl.VoicePlugin.Delegate
        public void addResponder(VoiceResponder voiceResponder, CompositeResponder.Filter filter) {
            Voice.this.extendedResponder.add(voiceResponder, filter);
        }

        @Override // com.amazon.alexa.voice.superbowl.VoicePlugin.Delegate
        public void addResponderInSequence(VoiceResponder voiceResponder, CompositeResponder.Filter filter) {
            Voice.this.extendedSequenceResponder.add(voiceResponder, filter);
        }

        @Override // com.amazon.alexa.voice.superbowl.VoicePlugin.Delegate
        public void addContextResolver(ContextResolver<? extends Context> contextResolver) {
            synchronized (Voice.this.contextResolverList) {
                Voice.this.contextResolverList.add(contextResolver);
            }
        }
    }

    public static final class Builder {
        Downchannel downchannel;
        VoiceProcessor.ErrorListener errorListener;
        SuperbowlProcessor processor;
        CompositeResponder.Filter sequencingFilter;
        List<VoicePlugin> pluginList = new ArrayList();
        List<ContextResolver<? extends Context>> contextResolverList = new ArrayList();
        Scheduler scheduler = Schedulers.serial();

        public Builder addPlugin(VoicePlugin voicePlugin) {
            Preconditions.notNull(voicePlugin, "plugin == null");
            this.pluginList.add(voicePlugin);
            return this;
        }

        public Builder addPlugins(VoicePlugin... voicePluginArr) {
            return addPlugins(Arrays.asList(voicePluginArr));
        }

        public Builder addPlugins(Collection<? extends VoicePlugin> collection) {
            Preconditions.notNull(collection, "pluginList == null");
            this.pluginList.addAll(collection);
            return this;
        }

        public Builder addContextResolver(ContextResolver<? extends Context> contextResolver) {
            Preconditions.notNull(contextResolver, "contextResolver == null");
            this.contextResolverList.add(contextResolver);
            return this;
        }

        public Builder processor(SuperbowlProcessor superbowlProcessor) {
            this.processor = superbowlProcessor;
            return this;
        }

        public Builder downchannel(Downchannel downchannel) {
            this.downchannel = downchannel;
            return this;
        }

        public Builder sequencingFilter(CompositeResponder.Filter filter) {
            Preconditions.notNull(filter, "filter == null");
            this.sequencingFilter = filter;
            return this;
        }

        public Builder eventScheduler(Scheduler scheduler) {
            Preconditions.notNull(scheduler, "scheduler == null");
            this.scheduler = scheduler;
            return this;
        }

        public Voice build() {
            Preconditions.mainThread("build() has to be called from the main thread");
            Preconditions.notNull(this.processor, "processor == null");
            if (this.sequencingFilter == null) {
                this.sequencingFilter = new CompositeResponder.Filter() { // from class: com.amazon.alexa.voice.superbowl.Voice.Builder.1
                    @Override // com.amazon.alexa.voice.core.responders.CompositeResponder.Filter
                    public boolean isSupported(Directive directive) {
                        String namespace = ((SuperbowlDirective) directive).getNamespace();
                        return "SpeechSynthesizer".equals(namespace) || "SpeechRecognizer".equals(namespace) || "AudioPlayer".equals(namespace);
                    }
                };
            }
            return new Voice(this);
        }
    }
}
