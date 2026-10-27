package com.amazon.mp3.library.fragment;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.prime.browse.BrowseContentSelectionProvider;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.music.browse.BrowseContentSelector;
import com.amazon.music.browse.BrowseService;
import com.amazon.music.browse.PlaylistLookup;
import com.amazon.music.storeservice.model.PlaylistItem;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPlaylistDetailModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/library/fragment/VideoPlaylistDetailModel;", "", "()V", "application", "Lcom/amazon/mp3/AmazonApplication;", "kotlin.jvm.PlatformType", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/browse/BrowseService;", "getPlaylistContent", "Lcom/amazon/music/storeservice/model/PlaylistItem;", "asin", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoPlaylistDetailModel {
    public static final VideoPlaylistDetailModel INSTANCE = new VideoPlaylistDetailModel();
    private static final AmazonApplication application;
    private static final BrowseService service;

    private VideoPlaylistDetailModel() {
    }

    static {
        AmazonApplication application2 = AmazonApplication.getApplication();
        application = application2;
        BrowseService browseServiceCreateBrowseService = BrowseFactory.createBrowseService(application2.getApplicationContext());
        Intrinsics.checkNotNullExpressionValue(browseServiceCreateBrowseService, "createBrowseService(appl…ation.applicationContext)");
        service = browseServiceCreateBrowseService;
    }

    public final PlaylistItem getPlaylistContent(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        BrowseLanguageProvider browseLanguageProvider = new BrowseLanguageProvider();
        AmazonApplication amazonApplication = application;
        Locale locale = browseLanguageProvider.getLocale(amazonApplication.getApplicationContext());
        BrowseContentSelector browseContentSelectionWithOwnedContent = new BrowseContentSelectionProvider().getBrowseContentSelectionWithOwnedContent(amazonApplication.getApplicationContext());
        String deviceId = AccountCredentialStorage.get(amazonApplication.getApplicationContext()).getDeviceId();
        String deviceType = AccountCredentialStorage.get(amazonApplication.getApplicationContext()).getDeviceType();
        boolean zIsMusicVideosEnabled = AmazonApplication.getCapabilities().isMusicVideosEnabled();
        boolean zIsExplicitLanguageFilterEnabled = AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled();
        ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
        Context applicationContext = amazonApplication.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "application.applicationContext");
        PlaylistItem playlistItem = new PlaylistLookup(service).get(new PlaylistLookup.Request(asin, musicTerritoryOfResidence, browseContentSelectionWithOwnedContent, deviceId, deviceType, locale, zIsMusicVideosEnabled, zIsExplicitLanguageFilterEnabled, childUserUtil.getMusicRequestIdentityContextToken(applicationContext))).playlist;
        Intrinsics.checkNotNullExpressionValue(playlistItem, "playlistLookupResponse.playlist");
        return playlistItem;
    }
}
