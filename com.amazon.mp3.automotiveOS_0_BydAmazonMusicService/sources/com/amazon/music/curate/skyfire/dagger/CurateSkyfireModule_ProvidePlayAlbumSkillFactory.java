package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvidePlayAlbumSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return providePlayAlbumSkill();
    }

    public static CurateSkyfireModule_ProvidePlayAlbumSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill providePlayAlbumSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.providePlayAlbumSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvidePlayAlbumSkillFactory INSTANCE = new CurateSkyfireModule_ProvidePlayAlbumSkillFactory();

        private InstanceHolder() {
        }
    }
}
