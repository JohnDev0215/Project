package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideShowPodcastsSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideShowPodcastsSkill();
    }

    public static CurateSkyfireModule_ProvideShowPodcastsSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideShowPodcastsSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideShowPodcastsSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideShowPodcastsSkillFactory INSTANCE = new CurateSkyfireModule_ProvideShowPodcastsSkillFactory();

        private InstanceHolder() {
        }
    }
}
