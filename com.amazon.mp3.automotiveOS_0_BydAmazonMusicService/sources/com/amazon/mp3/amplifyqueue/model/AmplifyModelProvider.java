package com.amazon.mp3.amplifyqueue.model;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.util.Immutable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class AmplifyModelProvider implements ModelProvider {
    private static AmplifyModelProvider amplifyGeneratedModelInstance;

    @Override // com.amplifyframework.core.model.ModelProvider
    public String version() {
        return "5f1061452228d89fc3910b2865bcb06a";
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
        return Immutable.of(new HashSet(Arrays.asList(CustomerDeviceSettings.class, DevicePlaybackState.class, MetricsContext.class, ProposedDevicePlaybackState.class, Queue.class, QueueCapabilitySet.class, QueueCapabilitySetV2.class, QueueEntityCapabilitySet.class, QueueEntityMetadata.class, QueueSequenceSlice.class, Setting.class)));
    }
}
