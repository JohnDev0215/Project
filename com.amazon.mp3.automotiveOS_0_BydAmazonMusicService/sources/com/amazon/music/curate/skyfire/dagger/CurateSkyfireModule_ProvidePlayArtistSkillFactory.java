package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvidePlayArtistSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return providePlayArtistSkill();
    }

    public static CurateSkyfireModule_ProvidePlayArtistSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill providePlayArtistSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.providePlayArtistSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvidePlayArtistSkillFactory INSTANCE = new CurateSkyfireModule_ProvidePlayArtistSkillFactory();

        private InstanceHolder() {
        }
    }
}
