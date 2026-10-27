package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideShowPlaylistsSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideShowPlaylistsSkill();
    }

    public static CurateSkyfireModule_ProvideShowPlaylistsSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideShowPlaylistsSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideShowPlaylistsSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideShowPlaylistsSkillFactory INSTANCE = new CurateSkyfireModule_ProvideShowPlaylistsSkillFactory();

        private InstanceHolder() {
        }
    }
}
