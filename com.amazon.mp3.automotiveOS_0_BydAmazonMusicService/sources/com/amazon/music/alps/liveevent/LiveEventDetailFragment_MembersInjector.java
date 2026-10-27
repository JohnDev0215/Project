package com.amazon.music.alps.liveevent;

import com.amazon.music.alps.providers.ChromeProvider;
import com.amazon.music.alps.providers.SharingProvider;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.styles.StyleSheet;
import dagger.MembersInjector;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public final class LiveEventDetailFragment_MembersInjector implements MembersInjector<LiveEventDetailFragment> {
    public static void injectUserSubscription(LiveEventDetailFragment liveEventDetailFragment, UserSubscription userSubscription) {
        liveEventDetailFragment.userSubscription = userSubscription;
    }

    public static void injectSharingProvider(LiveEventDetailFragment liveEventDetailFragment, SharingProvider sharingProvider) {
        liveEventDetailFragment.sharingProvider = sharingProvider;
    }

    public static void injectChromeProvider(LiveEventDetailFragment liveEventDetailFragment, ChromeProvider chromeProvider) {
        liveEventDetailFragment.chromeProvider = chromeProvider;
    }

    public static void injectStyleSheetObservable(LiveEventDetailFragment liveEventDetailFragment, Observable<StyleSheet> observable) {
        liveEventDetailFragment.styleSheetObservable = observable;
    }
}
