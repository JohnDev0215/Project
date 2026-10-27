package com.amazon.mp3.casting;

import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class CastingSkillService_MembersInjector implements MembersInjector<CastingSkillService> {
    public static void injectSkyFireProvider(CastingSkillService instance, SkyFireProvider skyFireProvider) {
        instance.skyFireProvider = skyFireProvider;
    }
}
