package com.amazon.mp3.alps.dagger;

import com.amazon.mp3.alps.adapters.ALPSChromeProviderAdapter;
import com.amazon.mp3.alps.adapters.ALPSSharingProviderAdapter;
import com.amazon.mp3.dmsfcore.providers.DMSFChromeProvider;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.skyfire.ui.providers.ChromeProvider;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultSharingProvider;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;

/* JADX INFO: compiled from: ALPSModule.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u000b\u001a\u00020\u0006H\u0007J\b\u0010\f\u001a\u00020\nH\u0007J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0007¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/alps/dagger/ALPSModule;", "", "()V", "provideAlpsChromeProvider", "Lcom/amazon/music/alps/providers/ChromeProvider;", "chromeProvider", "Lcom/amazon/music/skyfire/ui/providers/ChromeProvider;", "provideAlpsSharingProvider", "Lcom/amazon/music/alps/providers/SharingProvider;", "sharingProvider", "Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "provideChromeProvider", "provideSharingProvider", "provideStyleSheet", "Lrx/Observable;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "provideUserSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ALPSModule {
    public final UserSubscription provideUserSubscription() {
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        return userSubscription;
    }

    public final Observable<StyleSheet> provideStyleSheet() {
        return StyleSheetProvider.getStyleSheetObservable();
    }

    public final ChromeProvider provideChromeProvider() {
        return new DMSFChromeProvider();
    }

    public final SharingProvider provideSharingProvider() {
        return new DefaultSharingProvider();
    }

    public final com.amazon.music.alps.providers.ChromeProvider provideAlpsChromeProvider(ChromeProvider chromeProvider) {
        Intrinsics.checkNotNullParameter(chromeProvider, "chromeProvider");
        return new ALPSChromeProviderAdapter(chromeProvider);
    }

    public final com.amazon.music.alps.providers.SharingProvider provideAlpsSharingProvider(SharingProvider sharingProvider) {
        Intrinsics.checkNotNullParameter(sharingProvider, "sharingProvider");
        return new ALPSSharingProviderAdapter(sharingProvider);
    }
}
