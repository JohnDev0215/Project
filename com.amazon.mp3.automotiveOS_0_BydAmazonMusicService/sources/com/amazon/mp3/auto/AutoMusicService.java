package com.amazon.mp3.auto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.media.MediaBrowserServiceCompat;
import com.amazon.mp3.account.SubscriptionType;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.auto.AutoMusicService;
import com.amazon.mp3.auto.googleAssistant.LegacyVoiceSearchHandler;
import com.amazon.mp3.auto.playback.AutomotivePlaybackHandler;
import com.amazon.mp3.auto.provider.LegacyVoiceSearchBrowserImpl;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.brush.converters.BrushConverterUtils;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.init.InitializationHandler;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.health.AppHealthUtil;
import com.amazon.mp3.voice.deeplink.AlexaDeeplinkMediaBrowseHandler;
import com.amazon.music.media.auto.AutoMusicMediaBrowserCallback;
import com.amazon.music.media.auto.AutoMusicMediaBrowserService;
import com.amazon.music.media.auto.PendingPlayback;
import com.amazon.music.media.auto.feature.AutomotiveFeature;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.util.PlaybackStateUtility;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.config.OnMediaBrowseHandler;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: AutoMusicService.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000w\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0007*\u00011\u0018\u0000 62\u00020\u0001:\u00016B\u0007¢\u0006\u0004\b4\u00105J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\b\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\b\u0010\t\u001a\u00020\u0004H\u0016J$\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016J$\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00062\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012H\u0016J.\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\r2\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012H\u0016J\b\u0010\u001a\u001a\u00020\u0004H\u0016R\u0016\u0010\u001c\u001a\u00020\u001b8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u001e8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010%\u001a\u00020$8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b%\u0010&R\u0017\u0010(\u001a\u00020'8\u0006¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u0017\u0010-\u001a\u00020,8\u0006¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103¨\u00067"}, d2 = {"Lcom/amazon/mp3/auto/AutoMusicService;", "Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService;", "", "isRegionSupported", "", "initializeMediaSearchHandler", "", "mediaId", "startPlaybackForMediaId", "onCreate", "clientPackageName", "", "clientUid", "Landroid/os/Bundle;", "bundle", "Landroidx/media/MediaBrowserServiceCompat$BrowserRoot;", "onGetRoot", "parentId", "Landroidx/media/MediaBrowserServiceCompat$Result;", "", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "result", "onLoadChildren", SearchIntents.EXTRA_QUERY, "extras", "onSearch", "onDestroy", "Lcom/amazon/mp3/auto/playback/AutomotivePlaybackHandler;", "playbackHandler", "Lcom/amazon/mp3/auto/playback/AutomotivePlaybackHandler;", "Lcom/amazon/mp3/voice/deeplink/AlexaDeeplinkMediaBrowseHandler;", "alexaDeeplinkMediaBrowseHandler", "Lcom/amazon/mp3/voice/deeplink/AlexaDeeplinkMediaBrowseHandler;", "Lcom/amazon/mp3/auto/GoogleAssistantSearchHandler;", "googleAssistantSearchHandler", "Lcom/amazon/mp3/auto/GoogleAssistantSearchHandler;", "Lcom/amazon/mp3/auto/googleAssistant/LegacyVoiceSearchHandler;", "legacyVoiceSearchHandler", "Lcom/amazon/mp3/auto/googleAssistant/LegacyVoiceSearchHandler;", "Lkotlinx/coroutines/CoroutineScope;", "mediaScope", "Lkotlinx/coroutines/CoroutineScope;", "getMediaScope", "()Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/music/media/auto/PendingPlayback;", "pendingPlayback", "Lcom/amazon/music/media/auto/PendingPlayback;", "getPendingPlayback", "()Lcom/amazon/music/media/auto/PendingPlayback;", "com/amazon/mp3/auto/AutoMusicService$mediaBrowseHandler$1", "mediaBrowseHandler", "Lcom/amazon/mp3/auto/AutoMusicService$mediaBrowseHandler$1;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AutoMusicService extends AutoMusicMediaBrowserService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = AutoMusicService.class.getSimpleName();
    private AlexaDeeplinkMediaBrowseHandler alexaDeeplinkMediaBrowseHandler;
    private GoogleAssistantSearchHandler googleAssistantSearchHandler;
    private LegacyVoiceSearchHandler legacyVoiceSearchHandler;
    private final AutoMusicService$mediaBrowseHandler$1 mediaBrowseHandler;
    private final CoroutineScope mediaScope;
    private final PendingPlayback pendingPlayback;
    private AutomotivePlaybackHandler playbackHandler;

    /* JADX WARN: Type inference failed for: r0v6, types: [com.amazon.mp3.auto.AutoMusicService$mediaBrowseHandler$1] */
    public AutoMusicService() {
        super(new AutomotiveMusicMediaBrowserCallback(), ContentPlaybackUtils.INSTANCE, BrushConverterUtils.INSTANCE, SubDeviceConnectionUtils.INSTANCE, (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
        this.mediaScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        this.pendingPlayback = new PendingPlayback() { // from class: com.amazon.mp3.auto.AutoMusicService$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.auto.PendingPlayback
            public final void readyForPlayback(String str, boolean z) {
                AutoMusicService.m247pendingPlayback$lambda0(this.f$0, str, z);
            }
        };
        this.mediaBrowseHandler = new OnMediaBrowseHandler() { // from class: com.amazon.mp3.auto.AutoMusicService$mediaBrowseHandler$1
            @Override // com.amazon.music.media.playback.config.OnMediaBrowseHandler
            public void onMediaBrowse(String mediaId, Bundle bundle, boolean startPlayback) {
                AutoMusicService.Companion companion = AutoMusicService.INSTANCE;
                Log.debug(companion.getTAG(), Intrinsics.stringPlus("onMediaBrowse: mediaId=", mediaId));
                if (!this.this$0.isRegionSupported()) {
                    Log.error(companion.getTAG(), "User's account tier isn't supported in their region");
                    PlaybackStateUtility playbackStateUtility = PlaybackStateUtility.INSTANCE;
                    playbackStateUtility.setNonePlayBackState(this.this$0.getMediaSession());
                    MediaSessionCompat mediaSession = this.this$0.getMediaSession();
                    Context applicationContext = this.this$0.getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                    playbackStateUtility.setErrorPlaybackState(mediaSession, applicationContext, null, 4, this.this$0.getString(R.string.dmusic_automotive_plan_not_supported_error));
                    return;
                }
                if (mediaId != null) {
                    Log.debug(companion.getTAG(), Intrinsics.stringPlus("Attempting to play media ", mediaId));
                    if ((this.this$0.getMediaBrowser().getContent(mediaId) == null ? null : Boolean.valueOf(this.this$0.startPlaybackForMediaId(mediaId))) == null) {
                        AutoMusicService autoMusicService = this.this$0;
                        Log.debug(companion.getTAG(), "MediaId " + ((Object) mediaId) + " not found preparing for playback when hierarchy loads instead");
                        autoMusicService.getMediaBrowser().prepareForPlayback(mediaId, true, autoMusicService.getPendingPlayback());
                    }
                }
            }

            @Override // com.amazon.music.media.playback.config.OnMediaBrowseHandler
            public void onMediaBrowse(Uri uri, Bundle bundle, boolean startPlayback) {
                Log.debug(AutoMusicService.INSTANCE.getTAG(), Intrinsics.stringPlus("onMediaBrowse: uri=", uri));
                AlexaDeeplinkMediaBrowseHandler alexaDeeplinkMediaBrowseHandler = this.this$0.alexaDeeplinkMediaBrowseHandler;
                if (alexaDeeplinkMediaBrowseHandler == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("alexaDeeplinkMediaBrowseHandler");
                    alexaDeeplinkMediaBrowseHandler = null;
                }
                alexaDeeplinkMediaBrowseHandler.onMediaBrowse(uri, bundle, startPlayback);
            }
        };
    }

    /* JADX INFO: compiled from: AutoMusicService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/auto/AutoMusicService$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return AutoMusicService.TAG;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: pendingPlayback$lambda-0, reason: not valid java name */
    public static final void m247pendingPlayback$lambda0(AutoMusicService this$0, String str, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startPlaybackForMediaId(str);
    }

    public final PendingPlayback getPendingPlayback() {
        return this.pendingPlayback;
    }

    public final boolean startPlaybackForMediaId(String mediaId) {
        if (mediaId == null) {
            return false;
        }
        AutoMediaItem content = getMediaBrowser().getContent(mediaId);
        if (content != null) {
            Log.debug(TAG, "onMediaBrowse: Found media item " + content.getTitle() + " with uri " + content.getMetadata().getUri());
            AutomotivePlaybackHandler automotivePlaybackHandler = this.playbackHandler;
            if (automotivePlaybackHandler == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playbackHandler");
                automotivePlaybackHandler = null;
            }
            automotivePlaybackHandler.handleMediaBrowseRequest(content);
            return true;
        }
        Log.error(TAG, Intrinsics.stringPlus("startPlaybackForMediaId: Could not find media item for mediaId ", mediaId));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isRegionSupported() {
        if (UserSubscriptionUtil.isPrime()) {
            return FeatureFlagsProvider.getFeatureFlagProvider().canAccessPrime();
        }
        if (UserSubscriptionUtil.isNightwingOnly() || UserSubscriptionUtil.getSubscriptions().get(0) == SubscriptionType.none) {
            return FeatureFlagsProvider.getFeatureFlagProvider().canAccessNightwing();
        }
        return true;
    }

    @Override // com.amazon.music.media.auto.AutoMusicMediaBrowserService, androidx.media.MediaBrowserServiceCompat, android.app.Service
    public void onCreate() throws Exception {
        super.onCreate();
        String str = TAG;
        Log.debug(str, "onCreate called");
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        this.playbackHandler = new AutomotivePlaybackHandler(applicationContext, PageType.AUTOMOTIVE_OS_BROWSE_HOME, PlaybackPageType.AUTOMOTIVE_OS_BROWSE_HOME);
        if (Intrinsics.areEqual(getApplicationContext().getPackageName(), "com.amazon.mp3.automotiveOS")) {
            Log.debug(str, "InitializationHandler is called");
            AppHealthUtil appHealthUtil = new AppHealthUtil(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
            new InitializationHandler(new AutomotiveLaunchInitialization(applicationContext2, appHealthUtil)).initializeOnNewThread();
        }
        Context applicationContext3 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
        this.alexaDeeplinkMediaBrowseHandler = new AlexaDeeplinkMediaBrowseHandler(applicationContext3, getMediaSession());
        Playback.getInstance().getPlaybackConfig().setOnMediaBrowseHandler(this.mediaBrowseHandler);
        AutoMusicMediaBrowserCallback autoMediaBrowserCallback = getAutoMediaBrowserCallback();
        if (autoMediaBrowserCallback == null) {
            return;
        }
        Context applicationContext4 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
        autoMediaBrowserCallback.configureMediaBrowser(applicationContext4, getMediaBrowser());
    }

    private final void initializeMediaSearchHandler() {
        GoogleAssistantSearchHandler googleAssistantSearchHandler = null;
        if (getFeatureGateProvider() == null || !AutomotiveFeature.GOOGLE_ASSISTANT_VOICE.isEnabled(getFeatureGateProvider())) {
            BuildersKt__Builders_commonKt.launch$default(this.mediaScope, null, null, new AnonymousClass1(null), 3, null);
            return;
        }
        FeatureGateProvider featureGateProvider = getFeatureGateProvider();
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        this.googleAssistantSearchHandler = new GoogleAssistantSearchHandler(featureGateProvider, applicationContext, getMediaSession());
        Log.debug(TAG, "Playback session updated with 3p voice gateway onPlayFromSearchHandler");
        PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
        GoogleAssistantSearchHandler googleAssistantSearchHandler2 = this.googleAssistantSearchHandler;
        if (googleAssistantSearchHandler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleAssistantSearchHandler");
        } else {
            googleAssistantSearchHandler = googleAssistantSearchHandler2;
        }
        playbackConfig.setOnMediaSearchHandler(googleAssistantSearchHandler);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.auto.AutoMusicService$initializeMediaSearchHandler$1, reason: invalid class name */
    /* JADX INFO: compiled from: AutoMusicService.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.auto.AutoMusicService$initializeMediaSearchHandler$1", f = "AutoMusicService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AutoMusicService.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Context applicationContext = AutoMusicService.this.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            LegacyVoiceSearchBrowserImpl legacyVoiceSearchBrowserImpl = new LegacyVoiceSearchBrowserImpl(applicationContext);
            AutoMusicService autoMusicService = AutoMusicService.this;
            Context applicationContext2 = autoMusicService.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
            autoMusicService.legacyVoiceSearchHandler = new LegacyVoiceSearchHandler(applicationContext2, legacyVoiceSearchBrowserImpl, AutoMusicService.this.getMediaSession());
            Log.debug(AutoMusicService.INSTANCE.getTAG(), "Playback session updated with legacy Tenzing search onPlayFromSearchHandler");
            PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
            LegacyVoiceSearchHandler legacyVoiceSearchHandler = AutoMusicService.this.legacyVoiceSearchHandler;
            if (legacyVoiceSearchHandler == null) {
                Intrinsics.throwUninitializedPropertyAccessException("legacyVoiceSearchHandler");
                legacyVoiceSearchHandler = null;
            }
            playbackConfig.setOnMediaSearchHandler(legacyVoiceSearchHandler);
            return Unit.INSTANCE;
        }
    }

    @Override // com.amazon.music.media.auto.AutoMusicMediaBrowserService, androidx.media.MediaBrowserServiceCompat
    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String clientPackageName, int clientUid, Bundle bundle) throws Exception {
        Intrinsics.checkNotNullParameter(clientPackageName, "clientPackageName");
        long jCurrentTimeMillis = System.currentTimeMillis();
        MediaBrowserServiceCompat.BrowserRoot browserRootOnGetRoot = super.onGetRoot(clientPackageName, clientUid, bundle);
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        if (browserRootOnGetRoot != null) {
            initializeMediaSearchHandler();
        }
        String str = TAG;
        Log.debug(str, "logOnGetRootDuration: Time taken to run onGetRoot in derived class = " + (System.currentTimeMillis() - jCurrentTimeMillis2) + " millis");
        Log.debug(str, "logOnGetRootDuration: total time taken to run onGetRoot = " + (System.currentTimeMillis() - jCurrentTimeMillis) + " millis");
        return browserRootOnGetRoot;
    }

    @Override // com.amazon.music.media.auto.AutoMusicMediaBrowserService, androidx.media.MediaBrowserServiceCompat
    public void onLoadChildren(String parentId, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) throws Exception {
        Intrinsics.checkNotNullParameter(parentId, "parentId");
        Intrinsics.checkNotNullParameter(result, "result");
        String str = TAG;
        Log.debug(str, Intrinsics.stringPlus("onLoadChildren is called by ", parentId));
        if (AccountCredentialUtil.get(getApplicationContext()).isSignedIn() && AccountCredentialUtil.get(getApplicationContext()).isSignInComplete() && AccountDetailUtil.get(getApplicationContext()).canViewAndPlayCloudContent()) {
            super.onLoadChildren(parentId, result);
            return;
        }
        Log.debug(str, "User is not signed in or this device is not authorized");
        result.sendResult(new ArrayList());
        AutoMusicMediaBrowserCallback autoMediaBrowserCallback = getAutoMediaBrowserCallback();
        if (autoMediaBrowserCallback == null) {
            return;
        }
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        autoMediaBrowserCallback.onAuthenticationRequired(applicationContext, getMediaSession());
    }

    @Override // com.amazon.music.media.auto.AutoMusicMediaBrowserService, androidx.media.MediaBrowserServiceCompat
    public void onSearch(String query, Bundle extras, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(result, "result");
        String str = TAG;
        Log.debug(str, "onSearch is called with query '" + query + '\'');
        if (AccountCredentialUtil.get(getApplicationContext()).isSignedIn() && AccountCredentialUtil.get(getApplicationContext()).isSignInComplete() && AccountDetailUtil.get(getApplicationContext()).canViewAndPlayCloudContent()) {
            super.onSearch(query, extras, result);
            return;
        }
        Log.debug(str, "User is not signed in or this device is not authorized");
        result.sendResult(null);
        AutoMusicMediaBrowserCallback autoMediaBrowserCallback = getAutoMediaBrowserCallback();
        if (autoMediaBrowserCallback == null) {
            return;
        }
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        autoMediaBrowserCallback.onAuthenticationRequired(applicationContext, getMediaSession());
    }

    @Override // com.amazon.music.media.auto.AutoMusicMediaBrowserService, android.app.Service
    public void onDestroy() throws Exception {
        Log.debug(TAG, "onDestroy called");
        super.onDestroy();
        CoroutineScopeKt.cancel$default(this.mediaScope, null, 1, null);
    }
}
