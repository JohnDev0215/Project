package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvidePlayCatalogPlaylistSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return providePlayCatalogPlaylistSkill();
    }

    public static CurateSkyfireModule_ProvidePlayCatalogPlaylistSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill providePlayCatalogPlaylistSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.providePlayCatalogPlaylistSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvidePlayCatalogPlaylistSkillFactory INSTANCE = new CurateSkyfireModule_ProvidePlayCatalogPlaylistSkillFactory();

        private InstanceHolder() {
        }
    }
}
