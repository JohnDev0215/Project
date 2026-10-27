package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideShowRecentlyDownloadedSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideShowRecentlyDownloadedSkill();
    }

    public static CurateSkyfireModule_ProvideShowRecentlyDownloadedSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideShowRecentlyDownloadedSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideShowRecentlyDownloadedSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideShowRecentlyDownloadedSkillFactory INSTANCE = new CurateSkyfireModule_ProvideShowRecentlyDownloadedSkillFactory();

        private InstanceHolder() {
        }
    }
}
