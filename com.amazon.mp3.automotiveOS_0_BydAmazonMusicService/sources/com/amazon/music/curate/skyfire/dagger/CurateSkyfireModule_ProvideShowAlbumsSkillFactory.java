package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideShowAlbumsSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideShowAlbumsSkill();
    }

    public static CurateSkyfireModule_ProvideShowAlbumsSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideShowAlbumsSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideShowAlbumsSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideShowAlbumsSkillFactory INSTANCE = new CurateSkyfireModule_ProvideShowAlbumsSkillFactory();

        private InstanceHolder() {
        }
    }
}
