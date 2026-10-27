package com.amazon.mp3.auto;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.prime.browse.BrowseContentSelectionProvider;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.music.browse.BrowseContentSelector;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.media.auto.AutoMediaBrowser;
import com.amazon.music.media.auto.util.CredentialStorage;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CredentialUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/auto/CredentialUtil;", "", "()V", "configureCredentials", "", "context", "Landroid/content/Context;", "mediaBrowser", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CredentialUtil {
    public static final CredentialUtil INSTANCE = new CredentialUtil();

    private CredentialUtil() {
    }

    public final void configureCredentials(Context context, AutoMediaBrowser mediaBrowser) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaBrowser, "mediaBrowser");
        if (AccountCredentialUtil.get(context).isSignedIn() && AccountCredentialUtil.get(context).isSignInComplete()) {
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
            String deviceType = accountCredentialStorage.getDeviceType();
            Intrinsics.checkNotNullExpressionValue(deviceType, "accountCredentialStorage.deviceType");
            String deviceId = accountCredentialStorage.getDeviceId();
            Intrinsics.checkNotNullExpressionValue(deviceId, "accountCredentialStorage.deviceId");
            Locale locale = new BrowseLanguageProvider().getLocale(context);
            Intrinsics.checkNotNullExpressionValue(locale, "BrowseLanguageProvider().getLocale(context)");
            String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
            Intrinsics.checkNotNullExpressionValue(musicTerritoryOfResidence, "getMusicTerritoryOfResidence()");
            Marketplace homeMarketPlace = AccountDetailUtil.get(context).getHomeMarketPlace();
            Intrinsics.checkNotNullExpressionValue(homeMarketPlace, "get(context).homeMarketPlace");
            BrowseContentSelector browseContentSelection = new BrowseContentSelectionProvider().getBrowseContentSelection(context);
            Intrinsics.checkNotNullExpressionValue(browseContentSelection, "BrowseContentSelectionPr…ContentSelection(context)");
            mediaBrowser.configureCredentialStorage(new CredentialStorage.Credentials(deviceType, deviceId, locale, musicTerritoryOfResidence, homeMarketPlace, browseContentSelection, AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()), new CredentialStorage.GoogleAssistantGatewayCredentials(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()));
        }
    }
}
