package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideShowArtistsSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideShowArtistsSkill();
    }

    public static CurateSkyfireModule_ProvideShowArtistsSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideShowArtistsSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideShowArtistsSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideShowArtistsSkillFactory INSTANCE = new CurateSkyfireModule_ProvideShowArtistsSkillFactory();

        private InstanceHolder() {
        }
    }
}
