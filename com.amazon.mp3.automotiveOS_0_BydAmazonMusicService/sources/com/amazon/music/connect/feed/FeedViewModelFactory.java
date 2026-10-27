package com.amazon.music.connect.feed;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.music.connect.IdentityProvider;

/* JADX INFO: loaded from: classes4.dex */
public class FeedViewModelFactory implements ViewModelProvider.Factory {
    private final boolean artistFollowSupported;
    private final String feedApiEndpoint;
    private final String feedVersion;
    private final IdentityProvider identityProvider;

    public FeedViewModelFactory(IdentityProvider identityProvider, String str, String str2, boolean z) {
        this.identityProvider = identityProvider;
        this.feedVersion = str;
        this.feedApiEndpoint = str2;
        this.artistFollowSupported = z;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> cls) {
        return new FeedViewModel(this.identityProvider, this.feedVersion, this.feedApiEndpoint, this.artistFollowSupported);
    }
}
