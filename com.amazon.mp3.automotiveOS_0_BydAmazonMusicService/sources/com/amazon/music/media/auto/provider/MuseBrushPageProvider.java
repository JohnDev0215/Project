package com.amazon.music.media.auto.provider;

import android.content.Context;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.media.auto.tab.home.RxHomePageApiRepository;
import com.amazon.music.media.auto.tab.home.brush.BrushPageConverter;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.CredentialStorage;
import com.amazon.music.media.auto.util.LatencyCodePageUri;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: MuseBrushPageProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J6\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/media/auto/provider/MuseBrushPageProvider;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "fetchHomePageInProgress", "", "getHomePage", "", "context", "Landroid/content/Context;", "credentials", "Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;", "onMusicLoadedListener", "Lcom/amazon/music/media/auto/provider/OnMusicLoadedListener;", "converter", "Lcom/amazon/music/media/auto/tab/home/brush/BrushPageConverter;", "getSeeMoreContent", "widgetTitle", "uri", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MuseBrushPageProvider {
    public static final MuseBrushPageProvider INSTANCE = new MuseBrushPageProvider();
    private static final String TAG = MuseBrushPageProvider.class.getSimpleName();
    private static boolean fetchHomePageInProgress;

    private MuseBrushPageProvider() {
    }

    public final synchronized void getHomePage(Context context, CredentialStorage.Credentials credentials, final OnMusicLoadedListener onMusicLoadedListener, final BrushPageConverter converter) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(credentials, "credentials");
        Intrinsics.checkNotNullParameter(onMusicLoadedListener, "onMusicLoadedListener");
        Intrinsics.checkNotNullParameter(converter, "converter");
        if (!fetchHomePageInProgress) {
            fetchHomePageInProgress = true;
            RxHomePageApiRepository rxHomePageApiRepository = new RxHomePageApiRepository(context, credentials.getMusicTerritory(), credentials.getLocale(), credentials.getMarketPlace(), credentials.getDeviceId(), credentials.getDeviceType(), credentials.getContentType());
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "getHomePage: Fetching initial home page items");
            rxHomePageApiRepository.getInitialHomePage().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.media.auto.provider.MuseBrushPageProvider$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MuseBrushPageProvider.m2163getHomePage$lambda0(onMusicLoadedListener, converter, (Content) obj);
                }
            }, new Action1() { // from class: com.amazon.music.media.auto.provider.MuseBrushPageProvider$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MuseBrushPageProvider.m2164getHomePage$lambda1((Throwable) obj);
                }
            }, new Action0() { // from class: com.amazon.music.media.auto.provider.MuseBrushPageProvider$$ExternalSyntheticLambda2
                @Override // rx.functions.Action0
                public final void call() {
                    MuseBrushPageProvider.m2165getHomePage$lambda2();
                }
            });
        } else {
            AutoLog autoLog2 = AutoLog.INSTANCE;
            String TAG3 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
            autoLog2.debug(TAG3, "Waiting for response from Muse, should not make another call right now");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getHomePage$lambda-0, reason: not valid java name */
    public static final void m2163getHomePage$lambda0(OnMusicLoadedListener onMusicLoadedListener, BrushPageConverter converter, Content content) {
        Intrinsics.checkNotNullParameter(onMusicLoadedListener, "$onMusicLoadedListener");
        Intrinsics.checkNotNullParameter(converter, "$converter");
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, "getHomePage onNext: Received " + content.getBlocks().size() + " blocks");
        BrushLatencyReportingUtil.INSTANCE.trackLatency(LatencyCodePageUri.MEDIA_BROWSER_HOME.getURI(), LatencyTrackingLeg.RENDERING);
        List<Block> blocks = content.getBlocks();
        Intrinsics.checkNotNullExpressionValue(blocks, "res.blocks");
        onMusicLoadedListener.onBrushHomePageFetched(BrushPageConverter.convert$default(converter, blocks, false, 2, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getHomePage$lambda-1, reason: not valid java name */
    public static final void m2164getHomePage$lambda1(Throwable th) {
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.error(TAG2, "getHomePage onError: Failed to fetch Brush home page from Muse", th);
        fetchHomePageInProgress = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getHomePage$lambda-2, reason: not valid java name */
    public static final void m2165getHomePage$lambda2() {
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, "getHomePage onCompleted");
        BrushLatencyReportingUtil.INSTANCE.trackLatency(LatencyCodePageUri.MEDIA_BROWSER_HOME.getURI(), LatencyTrackingLeg.LOAD_COMPLETE);
        fetchHomePageInProgress = false;
    }

    public final synchronized void getSeeMoreContent(Context context, final String widgetTitle, final String uri, CredentialStorage.Credentials credentials, final BrushPageConverter converter, final OnMusicLoadedListener onMusicLoadedListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(widgetTitle, "widgetTitle");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(credentials, "credentials");
        Intrinsics.checkNotNullParameter(converter, "converter");
        Intrinsics.checkNotNullParameter(onMusicLoadedListener, "onMusicLoadedListener");
        new RxHomePageApiRepository(context, credentials.getMusicTerritory(), credentials.getLocale(), credentials.getMarketPlace(), credentials.getDeviceId(), credentials.getDeviceType(), credentials.getContentType()).getInitialPageContent(uri).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.media.auto.provider.MuseBrushPageProvider$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MuseBrushPageProvider.m2166getSeeMoreContent$lambda3(converter, onMusicLoadedListener, widgetTitle, (Content) obj);
            }
        }, new Action1() { // from class: com.amazon.music.media.auto.provider.MuseBrushPageProvider$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MuseBrushPageProvider.m2167getSeeMoreContent$lambda4(uri, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSeeMoreContent$lambda-3, reason: not valid java name */
    public static final void m2166getSeeMoreContent$lambda3(BrushPageConverter converter, OnMusicLoadedListener onMusicLoadedListener, String widgetTitle, Content content) {
        Intrinsics.checkNotNullParameter(converter, "$converter");
        Intrinsics.checkNotNullParameter(onMusicLoadedListener, "$onMusicLoadedListener");
        Intrinsics.checkNotNullParameter(widgetTitle, "$widgetTitle");
        List<Block> blocks = content.getBlocks();
        Intrinsics.checkNotNullExpressionValue(blocks, "res.blocks");
        onMusicLoadedListener.onBrushHomeSeeMorePageFetched(converter.convert(blocks, false), widgetTitle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSeeMoreContent$lambda-4, reason: not valid java name */
    public static final void m2167getSeeMoreContent$lambda4(String uri, Throwable th) {
        Intrinsics.checkNotNullParameter(uri, "$uri");
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.error(TAG2, "Failed to fetch widget content with URI " + uri + " from Muse", th);
    }
}
