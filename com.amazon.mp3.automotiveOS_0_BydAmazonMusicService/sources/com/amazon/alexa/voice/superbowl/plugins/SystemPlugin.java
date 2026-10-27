package com.amazon.alexa.voice.superbowl.plugins;

import com.amazon.alexa.voice.core.Cancellable;
import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlProcessor;
import com.amazon.alexa.voice.core.processor.superbowl.directives.SetEndpointDirective;
import com.amazon.alexa.voice.core.processor.superbowl.events.SettingsUpdatedEvent;
import com.amazon.alexa.voice.core.processor.superbowl.events.SynchronizeStateEvent;
import com.amazon.alexa.voice.superbowl.VoicePlugin;
import com.amazon.alexa.voice.superbowl.plugins.SystemRepository;
import com.amazon.alexa.voice.superbowl.plugins.system.RuntimeRepository;

/* JADX INFO: loaded from: classes2.dex */
public final class SystemPlugin extends VoicePlugin {
    private final SystemRepository repository;
    private final SystemRepository.OnChangedListener repositoryChangedListener;

    public SystemPlugin(SystemRepository systemRepository) {
        Preconditions.notNull(systemRepository, "repository == null");
        this.repository = systemRepository;
        this.repositoryChangedListener = new SystemRepository.OnChangedListener() { // from class: com.amazon.alexa.voice.superbowl.plugins.SystemPlugin.1
            @Override // com.amazon.alexa.voice.superbowl.plugins.SystemRepository.OnChangedListener
            public void onEndpointChanged(SystemRepository systemRepository2) {
                ((SuperbowlProcessor) SystemPlugin.this.getProcessor()).setEndpoint(systemRepository2.getEndpoint());
                SuperbowlDownchannel superbowlDownchannel = (SuperbowlDownchannel) SystemPlugin.this.getDownchannel();
                if (superbowlDownchannel != null) {
                    superbowlDownchannel.setEndpoint(systemRepository2.getEndpoint());
                }
            }

            @Override // com.amazon.alexa.voice.superbowl.plugins.SystemRepository.OnChangedListener
            public void onLocaleChanged(SystemRepository systemRepository2) {
                SystemPlugin.this.postEvent(new SettingsUpdatedEvent.Builder().locale(systemRepository2.getLocale()).build());
            }
        };
    }

    public SystemPlugin() {
        this(new RuntimeRepository());
    }

    public Cancellable updateSettings() {
        return postEvent(new SettingsUpdatedEvent.Builder().locale(this.repository.getLocale()).build());
    }

    @Override // com.amazon.alexa.voice.superbowl.VoicePlugin
    protected void onInitialize() {
        this.repository.addOnChangedListener(this.repositoryChangedListener);
        addResponder(new VoiceResponder() { // from class: com.amazon.alexa.voice.superbowl.plugins.SystemPlugin.2
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public void respond(Consumable<Directive> consumable) {
                SystemPlugin.this.repository.setEndpoint(((SetEndpointDirective) consumable.get()).getEndpoint());
                consumable.accept();
            }
        }, SetEndpointDirective.class);
        getDownchannel().addListener(new SuperbowlDownchannel.SimpleListener() { // from class: com.amazon.alexa.voice.superbowl.plugins.SystemPlugin.3
            @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.SimpleListener, com.amazon.alexa.voice.core.processor.superbowl.Downchannel.Listener
            public void onDownchannelActivated() {
                super.onDownchannelActivated();
                SystemPlugin.this.sendSynchronizeState();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Cancellable sendSynchronizeState() {
        return postEventAsync(new SuperbowlEvent.Factory<SynchronizeStateEvent>() { // from class: com.amazon.alexa.voice.superbowl.plugins.SystemPlugin.4
            @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Factory
            public SynchronizeStateEvent create() {
                return (SynchronizeStateEvent) new SynchronizeStateEvent.Builder().addContext(SystemPlugin.this.resolveContext()).build();
            }
        });
    }
}
