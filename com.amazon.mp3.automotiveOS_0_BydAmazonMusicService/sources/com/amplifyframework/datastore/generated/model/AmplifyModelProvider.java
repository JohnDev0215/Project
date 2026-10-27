package com.amplifyframework.datastore.generated.model;

import com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings;
import com.amazon.mp3.amplifyqueue.model.DevicePlaybackState;
import com.amazon.mp3.amplifyqueue.model.MetricsContext;
import com.amazon.mp3.amplifyqueue.model.ProposedDevicePlaybackState;
import com.amazon.mp3.amplifyqueue.model.Queue;
import com.amazon.mp3.amplifyqueue.model.QueueCapabilitySet;
import com.amazon.mp3.amplifyqueue.model.QueueEntityCapabilitySet;
import com.amazon.mp3.amplifyqueue.model.QueueEntityMetadata;
import com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice;
import com.amazon.mp3.amplifyqueue.model.Setting;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.util.Immutable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class AmplifyModelProvider implements ModelProvider {
    private static final String AMPLIFY_MODEL_VERSION = "5eafa9426645e663b2b11276e104300e";
    private static AmplifyModelProvider amplifyGeneratedModelInstance;

    @Override // com.amplifyframework.core.model.ModelProvider
    public String version() {
        return AMPLIFY_MODEL_VERSION;
    }

    private AmplifyModelProvider() {
    }

    public static AmplifyModelProvider getInstance() {
        if (amplifyGeneratedModelInstance == null) {
            amplifyGeneratedModelInstance = new AmplifyModelProvider();
        }
        return amplifyGeneratedModelInstance;
    }

    @Override // com.amplifyframework.core.model.ModelProvider
    public Set<Class<? extends Model>> models() {
        return Immutable.of(new HashSet(Arrays.asList(CustomerDeviceSettings.class, DevicePlaybackState.class, MetricsContext.class, ProposedDevicePlaybackState.class, Queue.class, QueueCapabilitySet.class, QueueEntityCapabilitySet.class, QueueEntityMetadata.class, QueueSequenceSlice.class, Setting.class)));
    }
}
