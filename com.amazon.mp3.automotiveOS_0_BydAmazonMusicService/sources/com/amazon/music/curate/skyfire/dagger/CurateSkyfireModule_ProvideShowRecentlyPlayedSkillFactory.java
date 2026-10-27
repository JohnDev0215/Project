package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideShowRecentlyPlayedSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideShowRecentlyPlayedSkill();
    }

    public static CurateSkyfireModule_ProvideShowRecentlyPlayedSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideShowRecentlyPlayedSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideShowRecentlyPlayedSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideShowRecentlyPlayedSkillFactory INSTANCE = new CurateSkyfireModule_ProvideShowRecentlyPlayedSkillFactory();

        private InstanceHolder() {
        }
    }
}
