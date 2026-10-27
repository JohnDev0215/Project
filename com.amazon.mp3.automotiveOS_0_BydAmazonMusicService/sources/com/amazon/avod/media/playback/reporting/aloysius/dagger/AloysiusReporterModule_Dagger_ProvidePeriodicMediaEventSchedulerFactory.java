package com.amazon.avod.media.playback.reporting.aloysius.dagger;

import com.amazon.avod.media.events.PeriodicMediaEventScheduler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class AloysiusReporterModule_Dagger_ProvidePeriodicMediaEventSchedulerFactory implements Factory<PeriodicMediaEventScheduler> {
    private final AloysiusReporterModule_Dagger module;

    public AloysiusReporterModule_Dagger_ProvidePeriodicMediaEventSchedulerFactory(AloysiusReporterModule_Dagger aloysiusReporterModule_Dagger) {
        this.module = aloysiusReporterModule_Dagger;
    }

    @Override // javax.inject.Provider
    public PeriodicMediaEventScheduler get() {
        return (PeriodicMediaEventScheduler) Preconditions.checkNotNull(this.module.providePeriodicMediaEventScheduler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PeriodicMediaEventScheduler> create(AloysiusReporterModule_Dagger aloysiusReporterModule_Dagger) {
        return new AloysiusReporterModule_Dagger_ProvidePeriodicMediaEventSchedulerFactory(aloysiusReporterModule_Dagger);
    }
}
