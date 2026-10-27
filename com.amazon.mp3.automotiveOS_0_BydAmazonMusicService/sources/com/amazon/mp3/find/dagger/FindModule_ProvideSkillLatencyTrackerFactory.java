package com.amazon.mp3.find.dagger;

import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSkillLatencyTrackerFactory implements Factory<SkillLatencyTracker> {
    private final FindModule module;

    public FindModule_ProvideSkillLatencyTrackerFactory(FindModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public SkillLatencyTracker get() {
        return provideSkillLatencyTracker(this.module);
    }

    public static FindModule_ProvideSkillLatencyTrackerFactory create(FindModule module) {
        return new FindModule_ProvideSkillLatencyTrackerFactory(module);
    }

    public static SkillLatencyTracker provideSkillLatencyTracker(FindModule instance) {
        return (SkillLatencyTracker) Preconditions.checkNotNullFromProvides(instance.provideSkillLatencyTracker());
    }
}
