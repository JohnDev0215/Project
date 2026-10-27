package com.amazon.music.connect.feed;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.amazon.mp3.reactnativemodule.provider.IdentityProvider;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultReactIdentityProvider implements IdentityProvider {
    private final com.amazon.music.connect.IdentityProvider identityProvider;

    public DefaultReactIdentityProvider(FragmentActivity fragmentActivity) {
        this.identityProvider = ((FeedViewModel) ViewModelProviders.of(fragmentActivity).get(FeedViewModel.class)).identityProvider;
    }
}
