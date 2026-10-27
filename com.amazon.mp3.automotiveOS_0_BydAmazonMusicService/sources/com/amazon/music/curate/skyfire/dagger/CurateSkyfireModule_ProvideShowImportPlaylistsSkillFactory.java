package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideShowImportPlaylistsSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideShowImportPlaylistsSkill();
    }

    public static CurateSkyfireModule_ProvideShowImportPlaylistsSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideShowImportPlaylistsSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideShowImportPlaylistsSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideShowImportPlaylistsSkillFactory INSTANCE = new CurateSkyfireModule_ProvideShowImportPlaylistsSkillFactory();

        private InstanceHolder() {
        }
    }
}
