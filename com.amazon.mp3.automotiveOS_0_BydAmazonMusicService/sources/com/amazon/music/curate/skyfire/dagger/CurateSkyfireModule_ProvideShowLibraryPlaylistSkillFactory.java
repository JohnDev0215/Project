package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideShowLibraryPlaylistSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideShowLibraryPlaylistSkill();
    }

    public static CurateSkyfireModule_ProvideShowLibraryPlaylistSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideShowLibraryPlaylistSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideShowLibraryPlaylistSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideShowLibraryPlaylistSkillFactory INSTANCE = new CurateSkyfireModule_ProvideShowLibraryPlaylistSkillFactory();

        private InstanceHolder() {
        }
    }
}
