package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideShowRecentlyAddedSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideShowRecentlyAddedSkill();
    }

    public static CurateSkyfireModule_ProvideShowRecentlyAddedSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideShowRecentlyAddedSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideShowRecentlyAddedSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideShowRecentlyAddedSkillFactory INSTANCE = new CurateSkyfireModule_ProvideShowRecentlyAddedSkillFactory();

        private InstanceHolder() {
        }
    }
}
