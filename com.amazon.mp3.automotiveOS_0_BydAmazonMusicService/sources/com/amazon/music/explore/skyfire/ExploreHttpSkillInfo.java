package com.amazon.music.explore.skyfire;

import InteractionInterface.v1_0.SetHttpSkillUrlMethod;
import android.content.SharedPreferences;
import com.amazon.music.explore.ExploreEndpointFactory;
import com.amazon.music.skyfire.core.network.HttpSkillInfo;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreHttpSkillInfo implements HttpSkillInfo {
    private static String HTTP_SKILL_URL_KEY = "HTTP_SKILL_URL";
    private final EnvironmentProvider environmentProvider;
    private final StorageProvider storageProvider;

    public ExploreHttpSkillInfo(StorageProvider storageProvider, EnvironmentProvider environmentProvider) {
        this.storageProvider = storageProvider;
        this.environmentProvider = environmentProvider;
    }

    @Override // com.amazon.music.skyfire.core.network.HttpSkillInfo
    public String url() {
        if (this.environmentProvider.getEndpointOverride() != null) {
            return ExploreEndpointFactory.getExploreEndpoint(this.environmentProvider.getEndpointOverride());
        }
        return this.storageProvider.getSharedPreferences().getString(HTTP_SKILL_URL_KEY, null);
    }

    void handleSetHttpSkillUrlMethod(SetHttpSkillUrlMethod setHttpSkillUrlMethod) {
        String strUrl = setHttpSkillUrlMethod.url();
        SharedPreferences.Editor editorEdit = this.storageProvider.getSharedPreferences().edit();
        editorEdit.putString(HTTP_SKILL_URL_KEY, strUrl);
        editorEdit.apply();
    }
}
