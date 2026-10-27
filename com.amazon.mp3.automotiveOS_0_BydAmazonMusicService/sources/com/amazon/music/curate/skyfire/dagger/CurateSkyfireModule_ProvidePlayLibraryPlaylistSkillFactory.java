package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvidePlayLibraryPlaylistSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return providePlayLibraryPlaylistSkill();
    }

    public static CurateSkyfireModule_ProvidePlayLibraryPlaylistSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill providePlayLibraryPlaylistSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.providePlayLibraryPlaylistSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvidePlayLibraryPlaylistSkillFactory INSTANCE = new CurateSkyfireModule_ProvidePlayLibraryPlaylistSkillFactory();

        private InstanceHolder() {
        }
    }
}
