package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideShowSongsSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideShowSongsSkill();
    }

    public static CurateSkyfireModule_ProvideShowSongsSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideShowSongsSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideShowSongsSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideShowSongsSkillFactory INSTANCE = new CurateSkyfireModule_ProvideShowSongsSkillFactory();

        private InstanceHolder() {
        }
    }
}
