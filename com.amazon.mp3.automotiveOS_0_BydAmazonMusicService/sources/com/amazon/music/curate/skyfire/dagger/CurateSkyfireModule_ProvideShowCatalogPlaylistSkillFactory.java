package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideShowCatalogPlaylistSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideShowCatalogPlaylistSkill();
    }

    public static CurateSkyfireModule_ProvideShowCatalogPlaylistSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideShowCatalogPlaylistSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideShowCatalogPlaylistSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideShowCatalogPlaylistSkillFactory INSTANCE = new CurateSkyfireModule_ProvideShowCatalogPlaylistSkillFactory();

        private InstanceHolder() {
        }
    }
}
