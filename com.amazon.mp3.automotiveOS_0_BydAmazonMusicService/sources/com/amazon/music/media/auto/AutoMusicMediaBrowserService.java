package com.amazon.music.media.auto;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Base64;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media.utils.MediaConstants;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.music.media.auto.feature.AutomotiveFeature;
import com.amazon.music.media.auto.provider.LocalImageUriProvider;
import com.amazon.music.media.auto.provider.PlaybackLocalImageUriProvider;
import com.amazon.music.media.auto.provider.SubDeviceType;
import com.amazon.music.media.auto.provider.SubDeviceTypeProvider;
import com.amazon.music.media.auto.tab.home.brush.BrushPageConverter;
import com.amazon.music.media.auto.tab.home.mesk.converters.EntityConverterFactory;
import com.amazon.music.media.auto.tab.home.mesk.converters.MESKHomePageConverter;
import com.amazon.music.media.auto.tab.home.mesk.converters.WidgetConverterFactory;
import com.amazon.music.media.auto.tab.library.skyfire.SkyfireContentMetadataConverter;
import com.amazon.music.media.auto.tab.search.SearchContentConverter;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.AutomotiveServiceUtil;
import com.amazon.music.media.auto.util.BrushConverterUtil;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.media.auto.util.CredentialStorage;
import com.amazon.music.media.auto.util.MediaBrowserMetricsUtil;
import com.amazon.music.media.auto.util.PlaybackStateUtility;
import com.amazon.music.media.auto.util.SubDeviceConnectionUtil;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: AutoMusicMediaBrowserService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 i2\u00020\u0001:\u0002jiB3\u0012\b\u0010/\u001a\u0004\u0018\u00010.\u0012\u0006\u00104\u001a\u000203\u0012\u0006\u00107\u001a\u000206\u0012\u0006\u0010:\u001a\u000209\u0012\b\u0010=\u001a\u0004\u0018\u00010<¢\u0006\u0004\bg\u0010hJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\u0012\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0002H\u0002J\b\u0010\n\u001a\u00020\u0005H\u0002J\b\u0010\u000b\u001a\u00020\u0005H\u0002J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J\b\u0010\u0015\u001a\u00020\u0005H\u0016J\"\u0010\u001b\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u0005H\u0016J$\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00182\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0016J$\u0010(\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u000f2\u0012\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0%0$H\u0016J,\u0010(\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u000f2\u0012\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0%0$2\u0006\u0010)\u001a\u00020\u001fH\u0016J\u0010\u0010*\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u000fH\u0016J.\u0010-\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u000f2\b\u0010,\u001a\u0004\u0018\u00010\u001f2\u0012\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0%0$H\u0016R\u001c\u0010/\u001a\u0004\u0018\u00010.8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0019\u0010=\u001a\u0004\u0018\u00010<8\u0006¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\"\u0010E\u001a\u00020D8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\bE\u0010F\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u0016\u0010L\u001a\u00020K8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bL\u0010MR\"\u0010O\u001a\u00020N8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u0018\u0010V\u001a\u00060UR\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010WR\u001a\u0010Y\u001a\u00020X8\u0004X\u0084\u0004¢\u0006\f\n\u0004\bY\u0010Z\u001a\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR \u0010e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020d0c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010f¨\u0006k"}, d2 = {"Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService;", "Landroidx/media/MediaBrowserServiceCompat;", "", "isConnectivityReloadEnabled", "isTextSearchEnabled", "", "reInitRootNodeForLocaleChange", "invalidateServiceOnLogout", "forceFetch", "loadMetadata", "unloadMetadata", "initializeMediaSession", "", "startTimeMillis", "logOnGetRootDuration", "", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "emitGetRootFailedMetric", "callerPackageName", "Landroid/content/pm/PackageInfo;", "getPackageInfo", "onCreate", "Landroid/content/Intent;", MAPAccountManager.KEY_INTENT, "", "flags", "startId", "onStartCommand", "onDestroy", "clientPackageName", "clientUid", "Landroid/os/Bundle;", "bundle", "Landroidx/media/MediaBrowserServiceCompat$BrowserRoot;", "onGetRoot", "parentId", "Landroidx/media/MediaBrowserServiceCompat$Result;", "", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "result", "onLoadChildren", "options", "notifyChildrenChanged", SearchIntents.EXTRA_QUERY, "extras", "onSearch", "Lcom/amazon/music/media/auto/AutoMusicMediaBrowserCallback;", "autoMediaBrowserCallback", "Lcom/amazon/music/media/auto/AutoMusicMediaBrowserCallback;", "getAutoMediaBrowserCallback", "()Lcom/amazon/music/media/auto/AutoMusicMediaBrowserCallback;", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "contentPlaybackUtil", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "Lcom/amazon/music/media/auto/util/BrushConverterUtil;", "brushConverterUtil", "Lcom/amazon/music/media/auto/util/BrushConverterUtil;", "Lcom/amazon/music/media/auto/util/SubDeviceConnectionUtil;", "subDeviceConnectionUtil", "Lcom/amazon/music/media/auto/util/SubDeviceConnectionUtil;", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "featureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "getFeatureGateProvider", "()Lcom/amazon/music/platform/providers/FeatureGateProvider;", "Lcom/amazon/music/media/auto/PackageValidator;", "packageValidator", "Lcom/amazon/music/media/auto/PackageValidator;", "Landroid/support/v4/media/session/MediaSessionCompat;", "mediaSession", "Landroid/support/v4/media/session/MediaSessionCompat;", "getMediaSession", "()Landroid/support/v4/media/session/MediaSessionCompat;", "setMediaSession", "(Landroid/support/v4/media/session/MediaSessionCompat;)V", "Lcom/amazon/music/media/playback/config/PlaybackConfig;", "playbackConfig", "Lcom/amazon/music/media/playback/config/PlaybackConfig;", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "mediaBrowser", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "getMediaBrowser", "()Lcom/amazon/music/media/auto/AutoMediaBrowser;", "setMediaBrowser", "(Lcom/amazon/music/media/auto/AutoMediaBrowser;)V", "Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService$AutoMusicBroadcastReceiver;", "autoMusicBroadcastReceiver", "Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService$AutoMusicBroadcastReceiver;", "Lcom/amazon/music/media/auto/util/CredentialStorage;", "credentialStorage", "Lcom/amazon/music/media/auto/util/CredentialStorage;", "getCredentialStorage", "()Lcom/amazon/music/media/auto/util/CredentialStorage;", "Lio/reactivex/disposables/CompositeDisposable;", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "Lkotlinx/coroutines/CoroutineScope;", "mediaBrowserScope", "Lkotlinx/coroutines/CoroutineScope;", "", "Lkotlinx/coroutines/Job;", "notificationJobs", "Ljava/util/Map;", "<init>", "(Lcom/amazon/music/media/auto/AutoMusicMediaBrowserCallback;Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;Lcom/amazon/music/media/auto/util/BrushConverterUtil;Lcom/amazon/music/media/auto/util/SubDeviceConnectionUtil;Lcom/amazon/music/platform/providers/FeatureGateProvider;)V", "Companion", "AutoMusicBroadcastReceiver", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public class AutoMusicMediaBrowserService extends MediaBrowserServiceCompat {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long NOTIFICATION_THROTTLE_DURATION;
    private static final String TAG;
    private static boolean metadataLoaded;
    private final AutoMusicMediaBrowserCallback autoMediaBrowserCallback;
    private final AutoMusicBroadcastReceiver autoMusicBroadcastReceiver;
    private final BrushConverterUtil brushConverterUtil;
    private final CompositeDisposable compositeDisposable;
    private final ContentPlaybackUtil contentPlaybackUtil;
    private final CredentialStorage credentialStorage;
    private final FeatureGateProvider featureGateProvider;
    public AutoMediaBrowser mediaBrowser;
    private final CoroutineScope mediaBrowserScope;
    protected MediaSessionCompat mediaSession;
    private final Map<String, Job> notificationJobs;
    private final PackageValidator packageValidator;
    private PlaybackConfig playbackConfig;
    private final SubDeviceConnectionUtil subDeviceConnectionUtil;

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    protected final AutoMusicMediaBrowserCallback getAutoMediaBrowserCallback() {
        return this.autoMediaBrowserCallback;
    }

    public final FeatureGateProvider getFeatureGateProvider() {
        return this.featureGateProvider;
    }

    public AutoMusicMediaBrowserService(AutoMusicMediaBrowserCallback autoMusicMediaBrowserCallback, ContentPlaybackUtil contentPlaybackUtil, BrushConverterUtil brushConverterUtil, SubDeviceConnectionUtil subDeviceConnectionUtil, FeatureGateProvider featureGateProvider) {
        Intrinsics.checkNotNullParameter(contentPlaybackUtil, "contentPlaybackUtil");
        Intrinsics.checkNotNullParameter(brushConverterUtil, "brushConverterUtil");
        Intrinsics.checkNotNullParameter(subDeviceConnectionUtil, "subDeviceConnectionUtil");
        this.autoMediaBrowserCallback = autoMusicMediaBrowserCallback;
        this.contentPlaybackUtil = contentPlaybackUtil;
        this.brushConverterUtil = brushConverterUtil;
        this.subDeviceConnectionUtil = subDeviceConnectionUtil;
        this.featureGateProvider = featureGateProvider;
        this.packageValidator = new PackageValidator();
        this.autoMusicBroadcastReceiver = new AutoMusicBroadcastReceiver(this);
        this.credentialStorage = new CredentialStorage();
        this.compositeDisposable = new CompositeDisposable();
        this.mediaBrowserScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        this.notificationJobs = new LinkedHashMap();
    }

    /* JADX INFO: compiled from: AutoMusicMediaBrowserService.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b%\u0010&R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\r8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0010\u0010\u000bR\u0014\u0010\u0011\u001a\u00020\r8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0012\u0010\u000bR\u0014\u0010\u0013\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0013\u0010\u000bR\u0014\u0010\u0014\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0014\u0010\u000bR\u0014\u0010\u0015\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0015\u0010\u000bR\u0014\u0010\u0016\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0016\u0010\u000bR\u0014\u0010\u0017\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0017\u0010\u000bR\u0014\u0010\u0018\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0018\u0010\u000bR\u001d\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001c\u0010\u000bR\u0014\u0010\u001d\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001d\u0010\u000bR\u0014\u0010\u001e\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001e\u0010\u000bR\u0014\u0010\u001f\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001f\u0010\u000bR\u0014\u0010 \u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b \u0010\u000bR\u0014\u0010!\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b!\u0010\u000bR\u0014\u0010\"\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\"\u0010\u000bR\u001c\u0010$\u001a\n #*\u0004\u0018\u00010\t0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010\u000b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006'"}, d2 = {"Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService$Companion;", "", "", "metadataLoaded", "Z", "getMetadataLoaded", "()Z", "setMetadataLoaded", "(Z)V", "", "AUTOMOTIVE_PACKAGE_NAME", "Ljava/lang/String;", "CONTENT_STYLE_BROWSABLE_HINT", "", "CONTENT_STYLE_GRID_ITEM_HINT_VALUE", "I", "CONTENT_STYLE_GROUP_TITLE", "CONTENT_STYLE_LIST_ITEM_HINT_VALUE", "CONTENT_STYLE_PLAYABLE_HINT", "CONTENT_STYLE_SINGLE_ITEM_HINT", "ERROR_RESOLUTION_ACTION_INTENT", "ERROR_RESOLUTION_ACTION_LABEL", "FLEX_EVENT_ON_GET_ROOT_FAILED", "MEDIA_STORE_EXTRA_MEDIA_CAPABILITIES", "MEDIA_STORE_EXTRA_MEDIA_CAPABILITIES_UID", "Lkotlin/time/Duration;", "NOTIFICATION_THROTTLE_DURATION", "J", "SEARCH_SUPPORTED", "SIGN_IN_FLOW_CANCEL_ACTION", "SIGN_IN_FLOW_COMPLETE_ACTION", "SIGN_IN_REQUIRED_BUNDLE_EXTRA", "SIGN_OUT_COMPLETE_ACTION", "SLOT_RESERVATION_SKIP_TO_NEXT", "SLOT_RESERVATION_SKIP_TO_PREV", "kotlin.jvm.PlatformType", "TAG", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getMetadataLoaded() {
            return AutoMusicMediaBrowserService.metadataLoaded;
        }
    }

    static {
        Duration.Companion companion = Duration.INSTANCE;
        NOTIFICATION_THROTTLE_DURATION = DurationKt.toDuration(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, DurationUnit.MILLISECONDS);
        TAG = AutoMusicMediaBrowserService.class.getSimpleName();
    }

    protected final MediaSessionCompat getMediaSession() {
        MediaSessionCompat mediaSessionCompat = this.mediaSession;
        if (mediaSessionCompat != null) {
            return mediaSessionCompat;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mediaSession");
        return null;
    }

    protected final void setMediaSession(MediaSessionCompat mediaSessionCompat) {
        Intrinsics.checkNotNullParameter(mediaSessionCompat, "<set-?>");
        this.mediaSession = mediaSessionCompat;
    }

    public final AutoMediaBrowser getMediaBrowser() {
        AutoMediaBrowser autoMediaBrowser = this.mediaBrowser;
        if (autoMediaBrowser != null) {
            return autoMediaBrowser;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mediaBrowser");
        return null;
    }

    public final void setMediaBrowser(AutoMediaBrowser autoMediaBrowser) {
        Intrinsics.checkNotNullParameter(autoMediaBrowser, "<set-?>");
        this.mediaBrowser = autoMediaBrowser;
    }

    protected final CredentialStorage getCredentialStorage() {
        return this.credentialStorage;
    }

    /* JADX INFO: compiled from: AutoMusicMediaBrowserService.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService$AutoMusicBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService;)V", "onReceive", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class AutoMusicBroadcastReceiver extends BroadcastReceiver {
        final /* synthetic */ AutoMusicMediaBrowserService this$0;

        public AutoMusicBroadcastReceiver(AutoMusicMediaBrowserService this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AutoMusicMediaBrowserCallback autoMediaBrowserCallback;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG = AutoMusicMediaBrowserService.TAG;
            Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
            autoLog.debug(TAG, "AutoMusicBroadcastReceiver: Received broadcast from " + ((Object) context.getPackageName()) + " for intent " + intent);
            if (Intrinsics.areEqual(intent.getAction(), "com.amazon.mp3.MAP_SignIn_Complete")) {
                PlaybackStateUtility.INSTANCE.setNonePlayBackState(this.this$0.getMediaSession());
                AutoMusicMediaBrowserCallback autoMediaBrowserCallback2 = this.this$0.getAutoMediaBrowserCallback();
                if (autoMediaBrowserCallback2 == null) {
                    return;
                }
                Context applicationContext = this.this$0.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                autoMediaBrowserCallback2.onAuthenticationCompleted(applicationContext, this.this$0.getMediaBrowser());
                return;
            }
            if (Intrinsics.areEqual(intent.getAction(), "android.intent.action.LOCALE_CHANGED")) {
                this.this$0.reInitRootNodeForLocaleChange();
                AutoMusicMediaBrowserCallback autoMediaBrowserCallback3 = this.this$0.getAutoMediaBrowserCallback();
                if (autoMediaBrowserCallback3 == null) {
                    return;
                }
                Context applicationContext2 = this.this$0.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
                autoMediaBrowserCallback3.onLocalChange(applicationContext2, this.this$0.getCredentialStorage(), this.this$0.getMediaBrowser());
                return;
            }
            if (Intrinsics.areEqual(intent.getAction(), "com.amazon.mp3.MAP_SignOut_Complete") || Intrinsics.areEqual(intent.getAction(), "com.amazon.mp3.MAP_SignIn_Cancel")) {
                this.this$0.invalidateServiceOnLogout();
                PlaybackStateUtility.INSTANCE.setNonePlayBackState(this.this$0.getMediaSession());
                if (!Intrinsics.areEqual(intent.getAction(), "com.amazon.mp3.MAP_SignOut_Complete") || (autoMediaBrowserCallback = this.this$0.getAutoMediaBrowserCallback()) == null) {
                    return;
                }
                Context applicationContext3 = this.this$0.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
                autoMediaBrowserCallback.onSignOut(applicationContext3, this.this$0.getMediaSession());
                return;
            }
            if (Intrinsics.areEqual(intent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE")) {
                String TAG2 = AutoMusicMediaBrowserService.TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog.debug(TAG2, "Connectivity change triggered");
                if (this.this$0.isConnectivityReloadEnabled()) {
                    AutoMusicMediaBrowserCallback autoMediaBrowserCallback4 = this.this$0.getAutoMediaBrowserCallback();
                    boolean z = false;
                    if (autoMediaBrowserCallback4 != null && autoMediaBrowserCallback4.onConnectivityChange(context)) {
                        z = true;
                    }
                    if (z && (!SubDeviceTypeProvider.INSTANCE.getSubDeviceTypes().isEmpty()) && !this.this$0.getMediaBrowser().areAllPagesReady()) {
                        String TAG3 = AutoMusicMediaBrowserService.TAG;
                        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                        autoLog.debug(TAG3, "Network connected, try again to load data.");
                        this.this$0.unloadMetadata();
                        this.this$0.loadMetadata(true);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isConnectivityReloadEnabled() {
        FeatureGateProvider featureGateProvider = this.featureGateProvider;
        return featureGateProvider != null && AutomotiveFeature.MEDIA_BROWSER_CONNECTIVITY_RELOAD.isEnabled(featureGateProvider);
    }

    private final boolean isTextSearchEnabled() {
        return Intrinsics.areEqual(getApplicationContext().getPackageName(), "com.amazon.mp3.automotiveOS") && AutomotiveFeature.MEDIA_BROWSER_TEXT_SEARCH.isEnabled(this.featureGateProvider);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reInitRootNodeForLocaleChange() {
        getMediaBrowser().clear();
        getMediaBrowser().initFirstLayerDrawer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invalidateServiceOnLogout() {
        getMediaBrowser().clear();
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.amazon.mp3.media.auto.SignIn_Required", true);
        Unit unit = Unit.INSTANCE;
        notifyChildrenChanged("BROWSER_ROOT", bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("com.amazon.mp3.media.auto.SignIn_Required", true);
        notifyChildrenChanged("SEARCH_RESULTS", bundle2);
    }

    @Override // androidx.media.MediaBrowserServiceCompat, android.app.Service
    public void onCreate() throws Exception {
        try {
            super.onCreate();
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "onCreate called");
            initializeMediaSession();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.amazon.mp3.MAP_SignIn_Complete");
            intentFilter.addAction("com.amazon.mp3.MAP_SignOut_Complete");
            intentFilter.addAction("com.amazon.mp3.MAP_SignIn_Cancel");
            intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            registerReceiver(this.autoMusicBroadcastReceiver, intentFilter);
            AutomotiveServiceUtil.INSTANCE.setAutomotiveServiceRunning(true);
        } catch (Exception e) {
            MediaBrowserMetricsUtil mediaBrowserMetricsUtil = MediaBrowserMetricsUtil.INSTANCE;
            String TAG3 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
            mediaBrowserMetricsUtil.sendEvent("autoMediaBrowserOnCreateFailure", TAG3, e.getMessage());
            throw e;
        }
    }

    static /* synthetic */ void loadMetadata$default(AutoMusicMediaBrowserService autoMusicMediaBrowserService, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: loadMetadata");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        autoMusicMediaBrowserService.loadMetadata(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadMetadata(boolean forceFetch) {
        if (metadataLoaded) {
            return;
        }
        if (Intrinsics.areEqual(getApplicationContext().getPackageName(), "com.amazon.mp3.automotiveOS")) {
            PlaybackConfig playbackConfig = this.playbackConfig;
            if (playbackConfig == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playbackConfig");
                playbackConfig = null;
            }
            playbackConfig.setUseLocalImageUri(true);
        }
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, "loadMetadata");
        SubDeviceTypeProvider subDeviceTypeProvider = SubDeviceTypeProvider.INSTANCE;
        if (subDeviceTypeProvider.isMultiLevelMediaBrowserHierarchy()) {
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "initFirstLayerDrawer for subDeiceType with multiLevelMediaBrowserHierarchy");
            getMediaBrowser().initFirstLayerDrawer();
        }
        if (!getMediaBrowser().getIsHomePageReady() && subDeviceTypeProvider.getLatestSubDeviceType() != SubDeviceType.ANDROID_AUTOMOTIVE_OS) {
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "Home page not ready while loading metadata- fetching it");
            getMediaBrowser().fetchHomePage(forceFetch);
        }
        SubDeviceType latestSubDeviceType = subDeviceTypeProvider.getLatestSubDeviceType();
        if (!ArraysKt___ArraysKt.contains(subDeviceTypeProvider.getUnknownSubDeviceTypes(), latestSubDeviceType)) {
            this.subDeviceConnectionUtil.sendSubDeviceConnectedEvent(String.valueOf(latestSubDeviceType), CollectionsKt___CollectionsKt.contains(subDeviceTypeProvider.getStopCastingSubDeviceList(), latestSubDeviceType));
        }
        AutoBrowserConstants autoBrowserConstants = AutoBrowserConstants.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        autoBrowserConstants.resetWidgetAndEntitiesCount(applicationContext);
        metadataLoaded = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unloadMetadata() {
        if (metadataLoaded) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "unloadMetadata");
            getMediaBrowser().clear();
            metadataLoaded = false;
        }
    }

    private final void initializeMediaSession() {
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        LocalImageUriProvider localImageUriProvider = new LocalImageUriProvider(applicationContext, this.mediaBrowserScope);
        MediaSessionCompat mediaSession = Playback.getInstance().getMediaSession();
        Intrinsics.checkNotNullExpressionValue(mediaSession, "getInstance().mediaSession");
        setMediaSession(mediaSession);
        PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
        playbackConfig.setUseLocalImageUri(false);
        playbackConfig.setLocalImageUriProvider(new PlaybackLocalImageUriProvider(localImageUriProvider));
        playbackConfig.setShowAddToLibraryIcon(false);
        playbackConfig.setResetPositionOnRepeat(true);
        Intrinsics.checkNotNullExpressionValue(playbackConfig, "getInstance().playbackCo…OnRepeat = true\n        }");
        this.playbackConfig = playbackConfig;
        BrushPageConverter brushPageConverter = new BrushPageConverter(this.contentPlaybackUtil, this.brushConverterUtil);
        ContentPlaybackUtil contentPlaybackUtil = this.contentPlaybackUtil;
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
        SkyfireContentMetadataConverter skyfireContentMetadataConverter = new SkyfireContentMetadataConverter(contentPlaybackUtil, applicationContext2);
        MESKHomePageConverter mESKHomePageConverter = new MESKHomePageConverter(this.contentPlaybackUtil, EntityConverterFactory.INSTANCE, WidgetConverterFactory.INSTANCE);
        Context applicationContext3 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
        SearchContentConverter searchContentConverter = new SearchContentConverter(applicationContext3, this.contentPlaybackUtil, this.brushConverterUtil, 0, 0, 0, 56, null);
        Context applicationContext4 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
        setMediaBrowser(new AutoMediaBrowser(applicationContext4, this, brushPageConverter, skyfireContentMetadataConverter, mESKHomePageConverter, this.credentialStorage, this.featureGateProvider, this.mediaBrowserScope, localImageUriProvider, searchContentConverter));
        setSessionToken(getMediaSession().getSessionToken());
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, Intrinsics.stringPlus("Bound to media session with token ", getSessionToken()));
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.car.media.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", true);
        bundle.putBoolean("com.google.android.gms.car.media.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", true);
        getMediaSession().setExtras(bundle);
    }

    @Override // android.app.Service
    public void onDestroy() throws Exception {
        try {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.warning(TAG2, "onDestroy");
            unregisterReceiver(this.autoMusicBroadcastReceiver);
            unloadMetadata();
            PlaybackConfig playbackConfig = this.playbackConfig;
            if (playbackConfig == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playbackConfig");
                playbackConfig = null;
            }
            playbackConfig.setOnMediaBrowseHandler(null);
            SubDeviceTypeProvider subDeviceTypeProvider = SubDeviceTypeProvider.INSTANCE;
            if (subDeviceTypeProvider.getLatestSubDeviceType() != null) {
                this.subDeviceConnectionUtil.sendSubDeviceDisconnectedEvent(String.valueOf(subDeviceTypeProvider.getLatestSubDeviceType()));
            }
            PlaybackConfig playbackConfig2 = this.playbackConfig;
            if (playbackConfig2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playbackConfig");
                playbackConfig2 = null;
            }
            playbackConfig2.setUseLocalImageUri(false);
            PlaybackConfig playbackConfig3 = this.playbackConfig;
            if (playbackConfig3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playbackConfig");
                playbackConfig3 = null;
            }
            playbackConfig3.setLocalImageUriProvider(null);
            AutomotiveServiceUtil.INSTANCE.setAutomotiveServiceRunning(false);
            subDeviceTypeProvider.clear();
            this.compositeDisposable.clear();
            CoroutineScopeKt.cancel$default(this.mediaBrowserScope, null, 1, null);
            super.onDestroy();
        } catch (Exception e) {
            MediaBrowserMetricsUtil mediaBrowserMetricsUtil = MediaBrowserMetricsUtil.INSTANCE;
            String TAG3 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
            mediaBrowserMetricsUtil.sendEvent("autoMediaBrowserOnDestroyFailure", TAG3, e.getMessage());
            throw e;
        }
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String clientPackageName, int clientUid, Bundle bundle) throws Exception {
        Intrinsics.checkNotNullParameter(clientPackageName, "clientPackageName");
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, Intrinsics.stringPlus("onGetRoot called for client ", clientPackageName));
            if (!this.packageValidator.isCallerAllowed(this, clientPackageName, clientUid)) {
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog.warning(TAG2, Intrinsics.stringPlus("onGetRoot: IGNORING request from untrusted package ", clientPackageName));
                logOnGetRootDuration(jCurrentTimeMillis);
                emitGetRootFailedMetric(clientPackageName);
                return null;
            }
            logOnGetRootDuration(jCurrentTimeMillis);
            BuildersKt__Builders_commonKt.launch$default(this.mediaBrowserScope, null, null, new AnonymousClass1(null), 3, null);
            logOnGetRootDuration(jCurrentTimeMillis);
            boolean z = false;
            if (bundle != null && bundle.containsKey(MediaBrowserServiceCompat.BrowserRoot.EXTRA_SUGGESTED)) {
                z = true;
            }
            if (z) {
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog.debug(TAG2, "onGetRoot: OS is requesting recommendations, returning flat root");
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean(MediaBrowserServiceCompat.BrowserRoot.EXTRA_SUGGESTED, true);
                logOnGetRootDuration(jCurrentTimeMillis);
                return new MediaBrowserServiceCompat.BrowserRoot("FLAT_ROOT", bundle2);
            }
            Bundle bundle3 = new Bundle();
            bundle3.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_BROWSABLE, 2);
            bundle3.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE, 2);
            bundle3.putBoolean(MediaConstants.BROWSER_SERVICE_EXTRAS_KEY_SEARCH_SUPPORTED, isTextSearchEnabled());
            logOnGetRootDuration(jCurrentTimeMillis);
            return new MediaBrowserServiceCompat.BrowserRoot("BROWSER_ROOT", bundle3);
        } catch (Exception e) {
            MediaBrowserMetricsUtil mediaBrowserMetricsUtil = MediaBrowserMetricsUtil.INSTANCE;
            String TAG3 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
            mediaBrowserMetricsUtil.sendEvent("autoMediaBrowserOnGetRootFailure", TAG3, clientPackageName, e.getMessage());
            throw e;
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.AutoMusicMediaBrowserService$onGetRoot$1, reason: invalid class name */
    /* JADX INFO: compiled from: AutoMusicMediaBrowserService.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.media.auto.AutoMusicMediaBrowserService$onGetRoot$1", f = "AutoMusicMediaBrowserService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AutoMusicMediaBrowserService.this.new AnonymousClass1(continuation);
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
            AutoMusicMediaBrowserService.loadMetadata$default(AutoMusicMediaBrowserService.this, false, 1, null);
            return Unit.INSTANCE;
        }
    }

    private final void logOnGetRootDuration(long startTimeMillis) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, "logOnGetRootDuration: total time taken to run onGetRoot = " + (jCurrentTimeMillis - startTimeMillis) + " ms");
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    public void onLoadChildren(String parentId, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) throws Exception {
        List<MediaBrowserCompat.MediaItem> mutableList;
        Intrinsics.checkNotNullParameter(parentId, "parentId");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, Intrinsics.stringPlus("onLoadChildren is called by ", parentId));
            if (getMediaBrowser().getIsHomePageReady()) {
                if (Intrinsics.areEqual("FLAT_ROOT", parentId)) {
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    autoLog.debug(TAG2, "onLoadChildren: OS is requesting recommendations, getting flat list");
                    mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) getMediaBrowser().getFlatRootChildren());
                } else {
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    autoLog.debug(TAG2, Intrinsics.stringPlus("onLoadChildren: OS is requesting full map, getting children of parent ", parentId));
                    mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) getMediaBrowser().getNodeManager().getChildren(parentId));
                }
                if (mutableList.isEmpty()) {
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    autoLog.warning(TAG2, "onLoadChildren: items is empty, detaching from result");
                    getMediaBrowser().setHomePageResult(result);
                    result.detach();
                    return;
                }
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog.debug(TAG2, "onLoadChildren: Home page is loaded with " + mutableList.size() + " items, sending result");
                result.sendResult(mutableList);
                return;
            }
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.warning(TAG2, "onLoadChildren: Home page not loaded, detaching from result");
            getMediaBrowser().setHomePageResult(result);
            result.detach();
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "onLoadChildren: Home page not loaded, making Muse home page call");
            BuildersKt__Builders_commonKt.launch$default(this.mediaBrowserScope, null, null, new C04311(null), 3, null);
        } catch (Exception e) {
            MediaBrowserMetricsUtil mediaBrowserMetricsUtil = MediaBrowserMetricsUtil.INSTANCE;
            String TAG3 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
            mediaBrowserMetricsUtil.sendEvent("autoMediaBrowserOnLoadChildrenFailure", TAG3, parentId, e.getMessage());
            throw e;
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.AutoMusicMediaBrowserService$onLoadChildren$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AutoMusicMediaBrowserService.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.media.auto.AutoMusicMediaBrowserService$onLoadChildren$1", f = "AutoMusicMediaBrowserService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C04311(Continuation<? super C04311> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AutoMusicMediaBrowserService.this.new C04311(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04311) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AutoMediaBrowser.fetchHomePage$default(AutoMusicMediaBrowserService.this.getMediaBrowser(), false, 1, null);
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    public void onLoadChildren(String parentId, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result, Bundle options) throws Exception {
        Intrinsics.checkNotNullParameter(parentId, "parentId");
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(options, "options");
        try {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "onLoadChildren is called by " + parentId + " with options " + options);
            if (options.getBoolean("com.amazon.mp3.media.auto.SignIn_Required")) {
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog.debug(TAG2, "Returning null result to force user to sign-in");
                result.sendResult(null);
                return;
            }
            super.onLoadChildren(parentId, result, options);
        } catch (Exception e) {
            MediaBrowserMetricsUtil mediaBrowserMetricsUtil = MediaBrowserMetricsUtil.INSTANCE;
            String TAG3 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
            mediaBrowserMetricsUtil.sendEvent("autoMediaBrowserOnLoadChildrenFailure", TAG3, parentId, e.getMessage());
            throw e;
        }
    }

    private final void emitGetRootFailedMetric(final String packageName) {
        this.compositeDisposable.add(Completable.fromAction(new Action() { // from class: com.amazon.music.media.auto.AutoMusicMediaBrowserService$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Action
            public final void run() {
                AutoMusicMediaBrowserService.m2159emitGetRootFailedMetric$lambda8(this.f$0, packageName);
            }
        }).subscribeOn(Schedulers.io()).doOnError(new Consumer() { // from class: com.amazon.music.media.auto.AutoMusicMediaBrowserService$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AutoMusicMediaBrowserService.m2160emitGetRootFailedMetric$lambda9((Throwable) obj);
            }
        }).subscribe());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: emitGetRootFailedMetric$lambda-8, reason: not valid java name */
    public static final void m2159emitGetRootFailedMetric$lambda8(AutoMusicMediaBrowserService this$0, String packageName) {
        float length;
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(packageName, "$packageName");
        PackageInfo packageInfo = this$0.getPackageInfo(packageName);
        if (packageInfo == null) {
            str = "null";
            length = 0.0f;
        } else {
            String strEncodeToString = Base64.encodeToString(packageInfo.signatures[0].toByteArray(), 2);
            Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(\n        …O_WRAP,\n                )");
            length = packageInfo.signatures.length;
            str = strEncodeToString;
        }
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("onGetRootFailed").withHcFlexStr1(packageName).withHcFlexStr2(str).withFlexNum1(Float.valueOf(length)).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: emitGetRootFailedMetric$lambda-9, reason: not valid java name */
    public static final void m2160emitGetRootFailedMetric$lambda9(Throwable th) {
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.error(TAG2, "Error occurred while emitting onGetRootFailed flex metric", th);
    }

    private final PackageInfo getPackageInfo(String callerPackageName) {
        try {
            return getPackageManager().getPackageInfo(callerPackageName, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    public synchronized void notifyChildrenChanged(String parentId) {
        Intrinsics.checkNotNullParameter(parentId, "parentId");
        Job job = this.notificationJobs.get(parentId);
        if (job != null && job.isActive()) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.notificationJobs.put(parentId, BuildersKt__Builders_commonKt.launch$default(this.mediaBrowserScope, null, null, new AnonymousClass2(parentId, this, null), 3, null));
        CollectionsKt__MutableCollectionsKt.removeAll(this.notificationJobs.values(), new Function1<Job, Boolean>() { // from class: com.amazon.music.media.auto.AutoMusicMediaBrowserService.notifyChildrenChanged.3
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Job it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(it.isCompleted());
            }
        });
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.AutoMusicMediaBrowserService$notifyChildrenChanged$2, reason: invalid class name */
    /* JADX INFO: compiled from: AutoMusicMediaBrowserService.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.media.auto.AutoMusicMediaBrowserService$notifyChildrenChanged$2", f = "AutoMusicMediaBrowserService.kt", i = {}, l = {525}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $parentId;
        int label;
        final /* synthetic */ AutoMusicMediaBrowserService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, AutoMusicMediaBrowserService autoMusicMediaBrowserService, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$parentId = str;
            this.this$0 = autoMusicMediaBrowserService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$parentId, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                long j = AutoMusicMediaBrowserService.NOTIFICATION_THROTTLE_DURATION;
                this.label = 1;
                if (DelayKt.m4266delayVtjQ1oo(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG = AutoMusicMediaBrowserService.TAG;
            Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
            autoLog.debug(TAG, "notifyChildrenChanged on MediaItem node '" + this.$parentId + '\'');
            AutoMusicMediaBrowserService.super.notifyChildrenChanged(this.$parentId);
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    public void onSearch(String query, Bundle extras, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(result, "result");
        if (!isTextSearchEnabled()) {
            result.sendResult(null);
            return;
        }
        AutoTextSearchQuery autoTextSearchQuery = new AutoTextSearchQuery(StringsKt__StringsKt.trim((CharSequence) query).toString(), extras == null ? null : extras.getString("android.intent.extra.album"), extras == null ? null : extras.getString("android.intent.extra.artist"), extras == null ? null : extras.getString("android.intent.extra.genre"), extras == null ? null : extras.getString("android.intent.extra.playlist"), extras != null ? extras.getString("android.intent.extra.title") : null);
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, Intrinsics.stringPlus("onSearch: ", autoTextSearchQuery));
        getMediaBrowser().setTextSearchResult(result);
        result.detach();
        getMediaBrowser().fetchTextSearchResults(autoTextSearchQuery);
    }
}
