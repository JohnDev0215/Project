package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideCreateNewPlaylistFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideCreateNewPlaylist();
    }

    public static CurateSkyfireModule_ProvideCreateNewPlaylistFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideCreateNewPlaylist() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideCreateNewPlaylist());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideCreateNewPlaylistFactory INSTANCE = new CurateSkyfireModule_ProvideCreateNewPlaylistFactory();

        private InstanceHolder() {
        }
    }
}
