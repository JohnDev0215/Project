package com.amazon.music.media.auto.tab.home;

import android.content.Context;
import com.amazon.music.browse.BrowseContentSelector;
import com.amazon.music.browse.BrowseService;
import com.amazon.music.browse.Configuration;
import com.amazon.music.browse.DefaultBrowseService;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.browse.page.PageRequest;
import com.amazon.music.browse.page.UiPage;
import com.amazon.music.endpoint.EndpointProvider;
import com.amazon.music.endpoint.EndpointURLRequest;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.media.auto.tab.home.brush.BrushRequestInterceptor;
import com.amazon.music.media.auto.util.LatencyTrackUtil;
import com.amazon.music.media.auto.util.PageUri;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.musicensembleservice.brush.Content;
import java.net.URL;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;

/* JADX INFO: compiled from: RxHomePageApiRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 \"2\u00020\u0001:\u0001\"B?\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001b\u001a\u00020\u0004\u0012\u0006\u0010\u001c\u001a\u00020\u0004\u0012\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b \u0010!J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u001a\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\n\u001a\u00020\tH\u0002J \u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0016J\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0005\u001a\u00020\u0004R\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u0014R\u0014\u0010\u001c\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u0014R\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006#"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/RxHomePageApiRepository;", "", "Lcom/amazon/music/browse/BrowseService;", "createBrowseService", "", "uri", "nextToken", "Lcom/amazon/music/browse/page/PageRequest;", "createPageRequest", "Ljava/net/URL;", "getMuseBrushEndpoint", "Lrx/Observable;", "Lcom/amazon/musicensembleservice/brush/Content;", "getMusePage", "getInitialHomePage", "getInitialPageContent", "Landroid/content/Context;", "context", "Landroid/content/Context;", Splash.PARAMS_MUSIC_TERRITORY, "Ljava/lang/String;", "Ljava/util/Locale;", Splash.PARAMS_LOCALE, "Ljava/util/Locale;", "Lcom/amazon/music/marketplace/Marketplace;", "marketPlace", "Lcom/amazon/music/marketplace/Marketplace;", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "Lcom/amazon/music/browse/BrowseContentSelector;", "mode", "Lcom/amazon/music/browse/BrowseContentSelector;", "<init>", "(Landroid/content/Context;Ljava/lang/String;Ljava/util/Locale;Lcom/amazon/music/marketplace/Marketplace;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/browse/BrowseContentSelector;)V", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public class RxHomePageApiRepository {
    private static final String TAG = RxHomePageApiRepository.class.getSimpleName();
    private final Context context;
    private final String deviceId;
    private final String deviceType;
    private final Locale locale;
    private final Marketplace marketPlace;
    private final BrowseContentSelector mode;
    private final String musicTerritory;

    public RxHomePageApiRepository(Context context, String musicTerritory, Locale locale, Marketplace marketPlace, String deviceId, String deviceType, BrowseContentSelector mode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(musicTerritory, "musicTerritory");
        Intrinsics.checkNotNullParameter(locale, "locale");
        Intrinsics.checkNotNullParameter(marketPlace, "marketPlace");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.context = context;
        this.musicTerritory = musicTerritory;
        this.locale = locale;
        this.marketPlace = marketPlace;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.mode = mode;
    }

    private final BrowseService createBrowseService() throws Exception {
        URL museBrushEndpoint = getMuseBrushEndpoint();
        return new DefaultBrowseService(new Configuration.Builder().withRequestInterceptor(new BrushRequestInterceptor(this.context)).withTrackLookupURL(museBrushEndpoint).withGetHomeURL(museBrushEndpoint).withMuseURL(museBrushEndpoint).build());
    }

    private final PageRequest createPageRequest(String uri, String nextToken) {
        PageRequest pageRequestBuild = new PageRequest.Builder(this.mode, this.deviceId, this.deviceType, this.musicTerritory, uri).withLocale(this.locale).withFeaturedPlay(false, false, false).withRecentlyPlayed(true).withVerticalItemBarkers(true).withMusicShowContent(false).withBundesligaContent(false).withVideo(false).withNextToken(nextToken).withCount(10).withPodcastContent(false).withPodcastCuratedContent(false).withPodcastUserContent(false).isStub(false).build();
        Intrinsics.checkNotNullExpressionValue(pageRequestBuild, "builder.build()");
        return pageRequestBuild;
    }

    private final URL getMuseBrushEndpoint() throws Exception {
        URL museBrushEndpoint = new EndpointProvider().getMuseBrushEndpoint(new EndpointURLRequest.Builder(this.marketPlace).build());
        Intrinsics.checkNotNullExpressionValue(museBrushEndpoint, "EndpointProvider().getMuseBrushEndpoint(request)");
        return museBrushEndpoint;
    }

    public Observable<Content> getMusePage(String uri, String nextToken) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        BrushLatencyReportingUtil.INSTANCE.trackLatency(LatencyTrackUtil.INSTANCE.convertlatencyCodePageUri(uri), LatencyTrackingLeg.REQUEST);
        return new RxUiPage(new UiPage(createBrowseService())).get(createPageRequest(uri, nextToken), false);
    }

    public final Observable<Content> getInitialHomePage() {
        return getMusePage(PageUri.HOME.getURI(), null);
    }

    public final Observable<Content> getInitialPageContent(String uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return getMusePage(uri, null);
    }
}
