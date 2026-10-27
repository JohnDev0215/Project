package com.amazon.mp3.auto;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.auto.googleAssistant.ThirdPartyVoiceAssistantName;
import com.amazon.mp3.auto.playback.ContentMetadataPlaybackHandler;
import com.amazon.mp3.capability.PlatformNameProvider;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.platform.providers.AuthenticationProviderImpl;
import com.amazon.mp3.platform.providers.CustomerMetadataProviderImpl;
import com.amazon.mp3.platform.providers.DeviceInfoProviderImpl;
import com.amazon.mp3.platform.providers.LoggingProviderImpl;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.media.auto.feature.AutomotiveFeature;
import com.amazon.music.media.auto.googleAssistant.Music3PMediaGatewayConfig;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.config.OnMediaSearchHandler;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.mediagateway.MediaContainer;
import com.amazon.music.platform.data.mediagateway.RecommendForAssistant$MediaContent;
import com.amazon.music.platform.graphql.mediagateway.generated.type.MediaType;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MediaGatewayServiceProvider;
import com.amazon.music.platform.providers.impl.mediagatewayservice.MediaGatewayServiceProviderImpl;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.voice.googleAssistant.GoogleAssistantError;
import com.amazon.music.voice.googleAssistant.GoogleAssistantListener;
import com.amazon.music.voice.googleAssistant.GoogleAssistantMetricsUtil;
import com.amazon.music.voice.googleAssistant.GoogleAssistantVoiceSearchQuery;
import com.amazon.music.voice.googleAssistant.VoiceGatewayContentMetadataConverter;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.android.gms.actions.SearchIntents;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt__JobKt;

/* JADX INFO: compiled from: GoogleAssistantSearchHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\u0006\u0010\"\u001a\u00020!\u0012\u0006\u0010%\u001a\u00020$¢\u0006\u0004\b9\u0010:J\b\u0010\u0004\u001a\u00020\u0003H\u0002J$\u0010\f\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u001a\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010\u0014\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J\u000e\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0015J\u0018\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u0012H\u0007J\b\u0010\u001a\u001a\u00020\u0005H\u0007J\u0012\u0010\u001c\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u0005H\u0007J\b\u0010\u001d\u001a\u00020\u0005H\u0007R\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u001c\u0010(\u001a\n '*\u0004\u0018\u00010\u00050\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R*\u0010+\u001a\u0004\u0018\u00010*8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b+\u0010,\u0012\u0004\b1\u00102\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001b\u00108\u001a\u0002038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107¨\u0006;"}, d2 = {"Lcom/amazon/mp3/auto/GoogleAssistantSearchHandler;", "Lcom/amazon/music/media/playback/config/OnMediaSearchHandler;", "Lcom/amazon/music/voice/googleAssistant/GoogleAssistantListener;", "Lcom/amazon/music/platform/providers/MediaGatewayServiceProvider;", "getMediaGatewayServiceProvider", "", SearchIntents.EXTRA_QUERY, "Landroid/os/Bundle;", "bundle", "", "startPlayback", "", "onMediaSearch", "Lcom/amazon/music/voice/googleAssistant/GoogleAssistantError;", "error", "errorMessage", "onError", "", "Lcom/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaContent;", "recommendForGoogleAssistant", "onMediaGatewayResponse", "Lcom/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaContainer;", "mediaItem", "isQueueBasedPlayback", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getContentMetadata", "getPlaybackInstanceId", TetheredMessageKey.requestId, "generateUtteranceId", "getDateTime", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "featureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Landroid/support/v4/media/session/MediaSessionCompat;", "mediaSession", "Landroid/support/v4/media/session/MediaSessionCompat;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/music/voice/googleAssistant/GoogleAssistantVoiceSearchQuery;", "voiceSearchQuery", "Lcom/amazon/music/voice/googleAssistant/GoogleAssistantVoiceSearchQuery;", "getVoiceSearchQuery", "()Lcom/amazon/music/voice/googleAssistant/GoogleAssistantVoiceSearchQuery;", "setVoiceSearchQuery", "(Lcom/amazon/music/voice/googleAssistant/GoogleAssistantVoiceSearchQuery;)V", "getVoiceSearchQuery$annotations", "()V", "Lkotlinx/coroutines/CoroutineScope;", "searchScope$delegate", "Lkotlin/Lazy;", "getSearchScope", "()Lkotlinx/coroutines/CoroutineScope;", "searchScope", "<init>", "(Lcom/amazon/music/platform/providers/FeatureGateProvider;Landroid/content/Context;Landroid/support/v4/media/session/MediaSessionCompat;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class GoogleAssistantSearchHandler implements OnMediaSearchHandler, GoogleAssistantListener {
    private final String TAG;
    private final Context context;
    private final FeatureGateProvider featureGateProvider;
    private final MediaSessionCompat mediaSession;

    /* JADX INFO: renamed from: searchScope$delegate, reason: from kotlin metadata */
    private final Lazy searchScope;
    private GoogleAssistantVoiceSearchQuery voiceSearchQuery;

    public GoogleAssistantSearchHandler(FeatureGateProvider featureGateProvider, Context context, MediaSessionCompat mediaSession) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaSession, "mediaSession");
        this.featureGateProvider = featureGateProvider;
        this.context = context;
        this.mediaSession = mediaSession;
        this.TAG = GoogleAssistantSearchHandler.class.getSimpleName();
        this.searchScope = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineScope>() { // from class: com.amazon.mp3.auto.GoogleAssistantSearchHandler$searchScope$2
            @Override // kotlin.jvm.functions.Function0
            public final CoroutineScope invoke() {
                return CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
            }
        });
    }

    private final CoroutineScope getSearchScope() {
        return (CoroutineScope) this.searchScope.getValue();
    }

    @Override // com.amazon.music.media.playback.config.OnMediaSearchHandler
    public void onMediaSearch(String query, Bundle bundle, boolean startPlayback) {
        GoogleAssistantVoiceSearchQuery googleAssistantVoiceSearchQuery = new GoogleAssistantVoiceSearchQuery(bundle, query);
        this.voiceSearchQuery = googleAssistantVoiceSearchQuery;
        FeatureGateProvider featureGateProvider = this.featureGateProvider;
        if (featureGateProvider == null) {
            featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        }
        Log.debug(this.TAG, "Log Feature gate provider to confirm it's bootstrapped", featureGateProvider);
        Log.debug(this.TAG, Intrinsics.stringPlus("Parsed google assistant search query ", googleAssistantVoiceSearchQuery));
        if (AutomotiveFeature.GOOGLE_ASSISTANT_VOICE.isEnabled(featureGateProvider)) {
            Log.debug(this.TAG, "Google Assistant 3P voice gateway feature gate is ON. Calling the 3p voice gateway");
            JobKt__JobKt.cancelChildren$default(getSearchScope().getCoroutineContext(), (CancellationException) null, 1, (Object) null);
            BuildersKt__Builders_commonKt.launch$default(getSearchScope(), null, null, new GoogleAssistantSearchHandler$onMediaSearch$1$1(this, googleAssistantVoiceSearchQuery, null), 3, null);
            return;
        }
        Log.debug(this.TAG, "Google Assistant 3P voice gateway feature gate is OFF");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaGatewayServiceProvider getMediaGatewayServiceProvider() {
        Music3PMediaGatewayConfig music3PMediaGatewayConfig = Music3PMediaGatewayConfig.INSTANCE;
        Providers providers = Providers.INSTANCE;
        AtomicMap<String, InterfaceProvider> providerInstances = providers.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof ConfigurationProvider)) {
            interfaceProvider = null;
        }
        ConfigurationProvider configurationProvider = (ConfigurationProvider) interfaceProvider;
        String graphQLEndpoint = music3PMediaGatewayConfig.getGraphQLEndpoint(configurationProvider instanceof Configuration ? (Configuration) configurationProvider : null);
        String platformNameForUserAgent = PlatformNameProvider.getPlatformNameForUserAgent();
        Intrinsics.checkNotNullExpressionValue(platformNameForUserAgent, "getPlatformNameForUserAgent()");
        return new MediaGatewayServiceProviderImpl(graphQLEndpoint, platformNameForUserAgent, String.valueOf(Build.VERSION.SDK_INT), new AuthenticationProviderImpl(this.context, null, 2, null), new DeviceInfoProviderImpl(this.context), (BuildInfoProvider) providers.get(BuildInfoProvider.class), new Function0<String>() { // from class: com.amazon.mp3.auto.GoogleAssistantSearchHandler.getMediaGatewayServiceProvider.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return GoogleAssistantSearchHandler.this.getPlaybackInstanceId();
            }
        }, new CustomerMetadataProviderImpl(this.context, null, 2, null), new LoggingProviderImpl(), null);
    }

    @Override // com.amazon.music.voice.googleAssistant.GoogleAssistantListener
    public void onError(GoogleAssistantError error, String errorMessage) {
        Intrinsics.checkNotNullParameter(error, "error");
        Log.error(this.TAG, "onError: Setting error playback state for error {} message {}", error, errorMessage);
        JobKt__JobKt.cancelChildren$default(getSearchScope().getCoroutineContext(), (CancellationException) null, 1, (Object) null);
        GoogleAssistantMetricsUtil googleAssistantMetricsUtil = GoogleAssistantMetricsUtil.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
        googleAssistantMetricsUtil.emitGoogleAssistantVoiceFailedMetric(error, errorMessage, TAG);
    }

    @Override // com.amazon.music.voice.googleAssistant.GoogleAssistantListener
    public void onMediaGatewayResponse(List<? extends RecommendForAssistant$MediaContent> recommendForGoogleAssistant) {
        Intrinsics.checkNotNullParameter(recommendForGoogleAssistant, "recommendForGoogleAssistant");
        Log.debug(this.TAG, Intrinsics.stringPlus("Handling response from Music3PMediaGateway ", recommendForGoogleAssistant));
        if (recommendForGoogleAssistant.isEmpty()) {
            onError(GoogleAssistantError.GATEWAY_NULL_RESPONSE, "Gateway response was null or empty");
        }
        RecommendForAssistant$MediaContent recommendForAssistant$MediaContent = (RecommendForAssistant$MediaContent) CollectionsKt___CollectionsKt.first((List) recommendForGoogleAssistant);
        MediaContainer mediaContainer = recommendForAssistant$MediaContent instanceof MediaContainer ? (MediaContainer) recommendForAssistant$MediaContent : null;
        if (mediaContainer != null && isQueueBasedPlayback(mediaContainer)) {
            Log.debug(this.TAG, Intrinsics.stringPlus("Starting playback based on CQ queueId: ", mediaContainer.getIdentifier()));
            try {
                StartPlaybackUtil startPlaybackUtil = StartPlaybackUtil.INSTANCE;
                Context context = this.context;
                String identifier = mediaContainer.getIdentifier();
                ControlSource GA = ControlSource.GA;
                Intrinsics.checkNotNullExpressionValue(GA, "GA");
                startPlaybackUtil.startCQPlayback(context, identifier, GA, null, false, true);
            } catch (Exception e) {
                Log.error(this.TAG, "Error starting playback based on CQ queueId " + mediaContainer.getIdentifier() + ": " + ((Object) e.getMessage()));
                onError(GoogleAssistantError.PLAYBACK_ERROR, "Error starting playback based on CQ queueId " + mediaContainer.getIdentifier() + ": " + ((Object) e.getMessage()));
            }
        } else {
            List<ContentMetadata> contentMetadata = getContentMetadata(recommendForAssistant$MediaContent);
            if (!(contentMetadata == null || contentMetadata.isEmpty())) {
                try {
                    Log.debug(this.TAG, Intrinsics.stringPlus("Entity for playback: ", recommendForAssistant$MediaContent));
                    ContentMetadata contentMetadata2 = (ContentMetadata) CollectionsKt___CollectionsKt.first((List) contentMetadata);
                    if (contentMetadata2 instanceof TrackMetadata) {
                        ContentMetadataPlaybackHandler.handleTrackList$default(new ContentMetadataPlaybackHandler(this.context, null, null, null, null, getSearchScope(), 30, null), contentMetadata, true, true, 0, null, 24, null);
                    } else if (contentMetadata.size() == 1) {
                        ContentMetadataPlaybackHandler.handle$default(new ContentMetadataPlaybackHandler(this.context, null, null, null, null, getSearchScope(), 30, null), contentMetadata2, true, true, null, 8, null);
                    } else {
                        Log.error(this.TAG, "Unsupported metadata type received from converter ", contentMetadata2);
                        onError(GoogleAssistantError.PLAYBACK_ERROR, Intrinsics.stringPlus("Unsupported metadata ", contentMetadata2));
                    }
                } catch (Exception e2) {
                    Log.error(this.TAG, "Error starting playback {}", contentMetadata, e2);
                    onError(GoogleAssistantError.PLAYBACK_ERROR, Intrinsics.stringPlus("Unknown runtime playback error ", e2.getMessage()));
                }
            } else {
                Log.error(this.TAG, "Cannot start playback for null metadata for response {}", recommendForAssistant$MediaContent);
                onError(GoogleAssistantError.PLAYBACK_ERROR, Intrinsics.stringPlus("Metadata was null or empty for ", recommendForAssistant$MediaContent));
            }
        }
        GoogleAssistantVoiceSearchQuery googleAssistantVoiceSearchQuery = this.voiceSearchQuery;
        if (googleAssistantVoiceSearchQuery == null) {
            return;
        }
        MetricsLogger.sendThirdPartyVoiceSearchEvent(ThirdPartyVoiceAssistantName.GOOGLE_ASSISTANT.name(), googleAssistantVoiceSearchQuery.getQuery(), googleAssistantVoiceSearchQuery.getAlbum(), googleAssistantVoiceSearchQuery.getArtist(), googleAssistantVoiceSearchQuery.getGenre(), googleAssistantVoiceSearchQuery.getPlaylist(), googleAssistantVoiceSearchQuery.getTitle(), googleAssistantVoiceSearchQuery.getFocus(), getPlaybackInstanceId(), generateUtteranceId(mediaContainer != null ? mediaContainer.getRequestId() : null));
    }

    public final boolean isQueueBasedPlayback(MediaContainer mediaItem) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        return mediaItem.getMediaType() == MediaType.QUEUE;
    }

    @VisibleForTesting
    public final List<ContentMetadata> getContentMetadata(RecommendForAssistant$MediaContent recommendForGoogleAssistant) {
        Intrinsics.checkNotNullParameter(recommendForGoogleAssistant, "recommendForGoogleAssistant");
        return new VoiceGatewayContentMetadataConverter(this.context, ContentPlaybackUtils.INSTANCE).convert(recommendForGoogleAssistant);
    }

    @VisibleForTesting
    public final String getPlaybackInstanceId() {
        String string = Playback.getInstance().getPlaybackConfig().getPlaybackInstanceId().toString();
        Intrinsics.checkNotNullExpressionValue(string, "getInstance().playbackCo…backInstanceId.toString()");
        return string;
    }

    @VisibleForTesting
    public final String generateUtteranceId(String requestId) {
        String deviceType = AccountCredentialStorage.get(this.context).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "get(context).deviceType");
        String version = ApplicationAttributes.getInstance(this.context).getVersion();
        Intrinsics.checkNotNullExpressionValue(version, "getInstance(context).version");
        String str = deviceType + ':' + version + JsonPointer.SEPARATOR + getDateTime() + "::google." + ((Object) requestId) + "/0";
        Log.debug(this.TAG, Intrinsics.stringPlus("Google Assistant thirdPartyVoiceSearch utteranceId is ", str));
        return str;
    }

    @VisibleForTesting
    public final String getDateTime() {
        String str = new SimpleDateFormat("yyyy/MM/dd/HH/'DEVICE_UNSPECIFIED'/mm:ss").format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "SimpleDateFormat(\"yyyy/M…D'/mm:ss\").format(Date())");
        return str;
    }
}
