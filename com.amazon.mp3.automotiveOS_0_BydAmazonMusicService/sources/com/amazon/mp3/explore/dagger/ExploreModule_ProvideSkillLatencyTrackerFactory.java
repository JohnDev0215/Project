package com.amazon.mp3.explore.dagger;

import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideSkillLatencyTrackerFactory implements Factory<SkillLatencyTracker> {
    private final ExploreModule module;

    public ExploreModule_ProvideSkillLatencyTrackerFactory(ExploreModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public SkillLatencyTracker get() {
        return provideSkillLatencyTracker(this.module);
    }

    public static ExploreModule_ProvideSkillLatencyTrackerFactory create(ExploreModule module) {
        return new ExploreModule_ProvideSkillLatencyTrackerFactory(module);
    }

    public static SkillLatencyTracker provideSkillLatencyTracker(ExploreModule instance) {
        return (SkillLatencyTracker) Preconditions.checkNotNullFromProvides(instance.provideSkillLatencyTracker());
    }
}
