package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.LocalSkill;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule_ProvideShowBrowseHomeSkillFactory implements Factory<LocalSkill> {
    @Override // javax.inject.Provider
    public LocalSkill get() {
        return provideShowBrowseHomeSkill();
    }

    public static CurateSkyfireModule_ProvideShowBrowseHomeSkillFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LocalSkill provideShowBrowseHomeSkill() {
        return (LocalSkill) Preconditions.checkNotNullFromProvides(CurateSkyfireModule.provideShowBrowseHomeSkill());
    }

    private static final class InstanceHolder {
        private static final CurateSkyfireModule_ProvideShowBrowseHomeSkillFactory INSTANCE = new CurateSkyfireModule_ProvideShowBrowseHomeSkillFactory();

        private InstanceHolder() {
        }
    }
}
