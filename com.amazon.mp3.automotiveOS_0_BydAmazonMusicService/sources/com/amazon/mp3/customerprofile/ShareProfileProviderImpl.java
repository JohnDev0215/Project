package com.amazon.mp3.customerprofile;

import android.content.Context;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.share.ProfileShareProvider;
import com.amazon.music.views.library.providers.ShareProfileProvider;

/* JADX INFO: loaded from: classes3.dex */
public class ShareProfileProviderImpl implements ShareProfileProvider {
    private final Context context;

    public ShareProfileProviderImpl(Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.views.library.providers.ShareProfileProvider
    public void onShare(String profileKey, String profileName, String profileImage, String profileUrl) {
        ProfileShareProvider profileShareProvider = new ProfileShareProvider(this.context, EndpointsProvider.get().getMusicDomain(), profileKey, profileName, profileImage, profileUrl);
        CustomerProfileMetrics.emitUiClickMetric(ActionType.SHARE);
        profileShareProvider.startShare();
    }
}
