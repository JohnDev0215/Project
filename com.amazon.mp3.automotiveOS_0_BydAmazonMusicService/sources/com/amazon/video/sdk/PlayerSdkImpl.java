package com.amazon.video.sdk;

import androidx.core.os.EnvironmentCompat;
import com.amazon.avod.media.MediaSystem;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.content.LocalContentManager;
import com.amazon.video.sdk.content.LocalContentManagerConfig;
import com.amazon.video.sdk.content.LocalContentManagerImpl;
import com.amazon.video.sdk.download.DownloadFeatureConfig;
import com.amazon.video.sdk.download.DownloadFeatureImpl;
import com.amazon.video.sdk.player.Player;
import com.amazon.video.sdk.player.PlayerConfig;
import com.amazon.video.sdk.player.PlayerImpl;
import com.google.common.base.Stopwatch;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerLoader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b.\u0010/J\b\u0010\u0003\u001a\u00020\u0002H\u0003J\u0006\u0010\u0004\u001a\u00020\u0001J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\rR\u001a\u0010\u0011\u001a\u00020\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0019\u001a\n \u0018*\u0004\u0018\u00010\u00170\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001b\u0010\u0016R\u0016\u0010\u001c\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u0016R\u0016\u0010\u001d\u001a\u00020\t8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\"\u0010 \u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\"\u0010\"\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010!R\"\u0010#\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010!R.\u0010%\u001a\u001c\u0012\u0004\u0012\u00020\u000b\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\u001f0$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010)\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000b0*8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,¨\u00060"}, d2 = {"Lcom/amazon/video/sdk/PlayerSdkImpl;", "Lcom/amazon/video/sdk/PlayerSdk;", "", "getPlayerSdkVersionId", "initialize", "Lcom/amazon/video/sdk/player/PlayerConfig;", "config", "Lcom/amazon/video/sdk/player/Player;", "getPlayerFeature", "Lcom/amazon/video/sdk/content/LocalContentManager;", "getLocalContentManagerFeature", "Lcom/amazon/video/sdk/PlayerSdkFeature;", "feature", "", "Lcom/amazon/video/sdk/Feature;", "getFeature", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "sdkConfig", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "getSdkConfig", "()Lcom/amazon/video/sdk/PlayerSdkConfig;", "RELEASE_NUMBER_FILE", "Ljava/lang/String;", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "UTF_8_CHARSET", "Ljava/nio/charset/Charset;", "UNKNOWN_PLAYER_SDK_VERSION_ID", "playerSdkVersionId", "currentLocalContentManagerFeature", "Lcom/amazon/video/sdk/content/LocalContentManager;", "Lkotlin/Function1;", "playerFeatureFunction", "Lkotlin/jvm/functions/Function1;", "localContentFeatureFunction", "downloadFeatureFunction", "", "featureCallableFunctionMap", "Ljava/util/Map;", "getVersion", "()Ljava/lang/String;", "version", "", "getFeatures", "()Ljava/util/List;", "features", "<init>", "(Lcom/amazon/video/sdk/PlayerSdkConfig;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class PlayerSdkImpl implements PlayerSdk {
    private final String RELEASE_NUMBER_FILE;
    private final String UNKNOWN_PLAYER_SDK_VERSION_ID;
    private final Charset UTF_8_CHARSET;
    private LocalContentManager currentLocalContentManagerFeature;
    private final Function1<Object, Feature> downloadFeatureFunction;
    private final Map<PlayerSdkFeature, Function1<Object, Feature>> featureCallableFunctionMap;
    private final Function1<Object, Feature> localContentFeatureFunction;
    private final Function1<Object, Feature> playerFeatureFunction;
    private String playerSdkVersionId;
    private final PlayerSdkConfig sdkConfig;

    public PlayerSdkImpl(PlayerSdkConfig sdkConfig) {
        Intrinsics.checkNotNullParameter(sdkConfig, "sdkConfig");
        this.sdkConfig = sdkConfig;
        this.RELEASE_NUMBER_FILE = "release_number";
        this.UTF_8_CHARSET = Charset.forName("UTF8");
        this.UNKNOWN_PLAYER_SDK_VERSION_ID = EnvironmentCompat.MEDIA_UNKNOWN;
        this.playerSdkVersionId = EnvironmentCompat.MEDIA_UNKNOWN;
        Function1<Object, PlayerImpl> function1 = new Function1<Object, PlayerImpl>() { // from class: com.amazon.video.sdk.PlayerSdkImpl$playerFeatureFunction$1
            {
                super(1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final PlayerImpl invoke(Object obj) {
                if (!(obj instanceof PlayerConfig)) {
                    throw new IllegalArgumentException(obj + " is wrong type for Player feature");
                }
                return new PlayerImpl(this.this$0.getSdkConfig(), (PlayerConfig) obj, this.this$0.getLocalContentManagerFeature(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, 1048568, null);
            }
        };
        this.playerFeatureFunction = function1;
        Function1<Object, LocalContentManagerImpl> function12 = new Function1<Object, LocalContentManagerImpl>() { // from class: com.amazon.video.sdk.PlayerSdkImpl$localContentFeatureFunction$1
            {
                super(1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final LocalContentManagerImpl invoke(Object obj) {
                if (!(obj instanceof LocalContentManagerConfig)) {
                    throw new IllegalArgumentException(obj + " is wrong type for LocalContentManager feature");
                }
                LocalContentManagerConfig localContentManagerConfig = (LocalContentManagerConfig) obj;
                MediaSystem.getInstance().getClientCacheConfig().setMaxCacheSizeInKb(localContentManagerConfig.getAllocationBytes());
                PlayerSdkConfig sdkConfig2 = this.this$0.getSdkConfig();
                VideoCacheManager videoCacheManager = MediaSystem.getInstance().getVideoCacheManager();
                Intrinsics.checkNotNullExpressionValue(videoCacheManager, "getInstance().videoCacheManager");
                return new LocalContentManagerImpl(sdkConfig2, localContentManagerConfig, videoCacheManager, null, null, 24, null);
            }
        };
        this.localContentFeatureFunction = function12;
        Function1<Object, DownloadFeatureImpl> function13 = new Function1<Object, DownloadFeatureImpl>() { // from class: com.amazon.video.sdk.PlayerSdkImpl$downloadFeatureFunction$1
            {
                super(1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final DownloadFeatureImpl invoke(Object obj) {
                if (!(obj instanceof DownloadFeatureConfig)) {
                    throw new IllegalArgumentException(obj + " is wrong type for Download feature");
                }
                return new DownloadFeatureImpl((DownloadFeatureConfig) obj, this.this$0.getSdkConfig().getContext());
            }
        };
        this.downloadFeatureFunction = function13;
        this.featureCallableFunctionMap = MapsKt__MapsKt.mapOf(TuplesKt.to(PlayerSdkFeature.Player, function1), TuplesKt.to(PlayerSdkFeature.LocalContentManager, function12), TuplesKt.to(PlayerSdkFeature.Download, function13));
    }

    @Override // com.amazon.video.sdk.PlayerSdk
    public PlayerSdkConfig getSdkConfig() {
        return this.sdkConfig;
    }

    public final PlayerSdk initialize() {
        Stopwatch stopwatchCreateStarted = Stopwatch.createStarted();
        Intrinsics.checkNotNullExpressionValue(stopwatchCreateStarted, "createStarted()");
        PlayerApplicationInitializer.init(getSdkConfig(), getVersion());
        stopwatchCreateStarted.stop();
        DLog.logf("PlayerApplicationInitializer.init() version %s call completed in Ms : %s", getVersion(), Long.valueOf(stopwatchCreateStarted.elapsed(TimeUnit.MILLISECONDS)));
        return this;
    }

    @Override // com.amazon.video.sdk.PlayerSdk
    public String getVersion() {
        return getPlayerSdkVersionId();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.Reader] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    @Nonnull
    private final String getPlayerSdkVersionId() throws Throwable {
        if (Intrinsics.areEqual(this.playerSdkVersionId, this.UNKNOWN_PLAYER_SDK_VERSION_ID)) {
            ?? r1 = 0;
            InputStreamReader inputStreamReader = null;
            try {
                try {
                    InputStreamReader inputStreamReader2 = new InputStreamReader(getSdkConfig().getContext().getAssets().open(this.RELEASE_NUMBER_FILE), this.UTF_8_CHARSET);
                    try {
                        String string = CharStreams.toString(inputStreamReader2);
                        String str = "toString(inputStreamReader)";
                        Intrinsics.checkNotNullExpressionValue(string, "toString(inputStreamReader)");
                        this.playerSdkVersionId = string;
                        Closeables.closeQuietly(inputStreamReader2);
                        r1 = str;
                    } catch (IOException e) {
                        e = e;
                        inputStreamReader = inputStreamReader2;
                        DLog.errorf("Failed to read the version id from assets: %s", e);
                        Closeables.closeQuietly(inputStreamReader);
                        r1 = inputStreamReader;
                    } catch (Throwable th) {
                        th = th;
                        r1 = inputStreamReader2;
                        Closeables.closeQuietly((Reader) r1);
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return this.playerSdkVersionId;
    }

    public List<PlayerSdkFeature> getFeatures() {
        return CollectionsKt__CollectionsKt.listOf((Object[]) new PlayerSdkFeature[]{PlayerSdkFeature.Player, PlayerSdkFeature.LocalContentManager, PlayerSdkFeature.Download});
    }

    @Override // com.amazon.video.sdk.PlayerSdk
    public synchronized Player getPlayerFeature(PlayerConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        DLog.logf("PlayerSDK.getPlayerFeature(%s)", config);
        return (PlayerImpl) getFeature(PlayerSdkFeature.Player, config);
    }

    @Override // com.amazon.video.sdk.PlayerSdk
    public synchronized LocalContentManager getLocalContentManagerFeature() {
        LocalContentManager localContentManager;
        DLog.logf("PlayerSDK.getLocalContentManagerFeature()");
        if (this.currentLocalContentManagerFeature == null) {
            this.currentLocalContentManagerFeature = (LocalContentManager) getFeature(PlayerSdkFeature.LocalContentManager, getSdkConfig().getLocalContentManagerConfig());
        }
        localContentManager = this.currentLocalContentManagerFeature;
        if (localContentManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentLocalContentManagerFeature");
            localContentManager = null;
        }
        return localContentManager;
    }

    public final Feature getFeature(PlayerSdkFeature feature, Object config) {
        Intrinsics.checkNotNullParameter(feature, "feature");
        if (getFeatures().contains(feature)) {
            return (Feature) ((Function1) MapsKt__MapsKt.getValue(this.featureCallableFunctionMap, feature)).invoke(config);
        }
        throw new NotImplementedError(feature + " is not implemented");
    }
}
