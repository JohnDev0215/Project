package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvidePlaySationSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return providePlaySationSkill();
    }

    public static CurateSkyfireModule_ProvidePlaySationSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill providePlaySationSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.providePlaySationSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvidePlaySationSkillFactory INSTANCE = new CurateSkyfireModule_ProvidePlaySationSkillFactory();

        private InstanceHolder() {
        }
    }
}
