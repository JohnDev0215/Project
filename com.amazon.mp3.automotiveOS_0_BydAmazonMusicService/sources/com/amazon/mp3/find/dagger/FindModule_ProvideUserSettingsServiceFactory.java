package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.UserSettingsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideUserSettingsServiceFactory implements Factory<UserSettingsService> {
    public static UserSettingsService provideUserSettingsService(FindModule instance, Context context, SearchFeaturesProvider searchFeaturesProvider) {
        return (UserSettingsService) Preconditions.checkNotNullFromProvides(instance.provideUserSettingsService(context, searchFeaturesProvider));
    }
}
