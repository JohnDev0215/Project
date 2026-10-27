package com.amazon.mp3.watch;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Response;
import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.Track;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import Watch.TetheredInterface.v1_0.ImmutableStartSyncMediaMetadataMethod;
import Watch.TetheredInterface.v1_0.ImmutableStopSyncMediaMetadataMethod;
import Watch.TetheredInterface.v1_0.ImmutableSyncMediaMetadataMethod;
import Watch.TetheredInterface.v1_0.StartSyncMediaMetadataMethod;
import Watch.TetheredInterface.v1_0.StopSyncMediaMetadataMethod;
import Watch.TetheredInterface.v1_0.SyncMediaMetadataMethod;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.explore.providers.ExplorePlaybackProvider;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.core.network.Mappers;
import com.amazon.music.skyfire.core.template.OwnerIdGenerator;
import com.amazon.music.skyfire.models.RepeatControlState;
import com.amazon.music.skyfire.models.ShuffleControlState;
import com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel;
import com.amazon.music.skyfire.models.SkyfirePlaybackStateModel;
import com.amazon.music.skyfire.models.TetheredError;
import com.amazon.music.skyfire.models.TetheredErrorMessage;
import com.amazon.music.skyfire.models.TetheredMessage;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import com.amazon.music.skyfire.ui.skyfire.AndroidClientInformationCache;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.skyfire.MethodListener;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.media.playback.PlaybackSpeed;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import dagger.android.HasAndroidInjector;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: WatchSessionDelegate.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0085\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001d\b\u0016\u0018\u0000 ~2\u00020\u00012\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u00020\u00050\u00042\u00020\u00062\u00020\u00072\u00020\b:\u0001~B\t\b\u0016¢\u0006\u0004\b|\u0010}J\b\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u000b\u001a\u00020\tH\u0002J$\u0010\u0010\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000eH\u0002J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0014\u001a\u00020\tH\u0002J\b\u0010\u0015\u001a\u00020\tH\u0002J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J+\u0010\u001e\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010 \u001a\u00020\tH\u0002J\b\u0010!\u001a\u00020\tH\u0002J\b\u0010\"\u001a\u00020\tH\u0016J\u0010\u0010%\u001a\u00020\t2\u0006\u0010$\u001a\u00020#H\u0016J\"\u0010(\u001a\u00020\u001c2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u001cH\u0016J\b\u0010)\u001a\u00020\tH\u0016J\u0010\u0010+\u001a\u00020\t2\u0006\u0010*\u001a\u00020\u0016H\u0016J\u0014\u0010-\u001a\u0004\u0018\u00010,2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0016J&\u00101\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\f2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00050/H\u0016J\u0010\u00102\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\b\u00103\u001a\u00020\tH\u0016J\u001a\u00107\u001a\u00020\t2\u0006\u00104\u001a\u00020\u001c2\b\u00106\u001a\u0004\u0018\u000105H\u0016R\u001a\u0010:\u001a\b\u0012\u0004\u0012\u000209088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u001a\u0010=\u001a\b\u0012\u0004\u0012\u00020<088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010;R\u001a\u0010?\u001a\b\u0012\u0004\u0012\u00020>088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010;R\u001a\u0010A\u001a\b\u0012\u0004\u0012\u00020@088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010;R\"\u0010C\u001a\u00020B8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\"\u0010J\u001a\u00020I8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\"\u0010Q\u001a\u00020P8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR(\u0010[\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010Y\u0012\u0004\u0012\u00020Z0X0W8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010_R\u001c\u0010b\u001a\n a*\u0004\u0018\u00010`0`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bb\u0010cR\u0014\u0010e\u001a\u00020d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010fR\u0018\u0010h\u001a\u0004\u0018\u00010g8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bh\u0010iR\u0014\u0010k\u001a\u00020j8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bk\u0010lR\u0014\u0010n\u001a\u00020m8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bn\u0010oR\u001c\u0010r\u001a\b\u0012\u0004\u0012\u00020q0p8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\br\u0010sR\u001a\u0010t\u001a\u00020\f8\u0016X\u0096D¢\u0006\f\n\u0004\bt\u0010u\u001a\u0004\bv\u0010wR\u0014\u0010{\u001a\u00020x8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\by\u0010z¨\u0006\u007f"}, d2 = {"Lcom/amazon/mp3/watch/WatchSessionService;", "Landroid/app/Service;", "Lcom/amazon/music/skyfire/ui/skyfire/MethodListener;", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "Lcom/amazon/music/skyfire/platform/MethodCallback;", "LCoreInterface/v1_0/Method;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "Lcom/google/android/gms/wearable/CapabilityClient$OnCapabilityChangedListener;", "", "registerListeners", "unregisterListeners", "", "action", "", "args", "onTriggerExternalAction", "LExternalActionInterface/v1_0/TriggerExternalGenericPlaybackMethod;", "method", "onTriggerExternalGenericPlayback", "connectedToWatch", "disconnectedFromWatch", "Landroid/content/Intent;", MAPAccountManager.KEY_INTENT, "didReceiveMessage", TetheredMessageKey.nodeId, "Lcom/amazon/music/skyfire/models/TetheredError;", "error", "", TetheredMessageKey.requestId, "replyToWatch", "(Ljava/lang/String;Lcom/amazon/music/skyfire/models/TetheredError;Ljava/lang/Integer;)V", "musicPlaybackStateChanged", "podcastPlaybackStateChanged", "onCreate", "Lcom/google/android/gms/wearable/CapabilityInfo;", "capabilityInfo", "onCapabilityChanged", "flags", "startId", "onStartCommand", "onDestroy", "rootIntent", "onTaskRemoved", "Landroid/os/IBinder;", "onBind", "ownerId", "Lcom/amazon/music/skyfire/platform/OnMethodExecutedListener;", "onMethodExecutedListener", "executeMethod", "handleMethod", "onPlayStateChanged", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/music/media/playback/ControlSource;", "controlSource", "onPlaybackEvent", "Lkotlin/Function0;", "", "isSignedOut", "Lkotlin/jvm/functions/Function0;", "Lcom/google/android/gms/wearable/DataClient;", "dataClient", "Lcom/google/android/gms/wearable/MessageClient;", "messageClient", "Lcom/google/android/gms/wearable/CapabilityClient;", "capabilityClient", "Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "skyFireProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "getSkyFireProvider", "()Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "setSkyFireProvider", "(Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;)V", "Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "externalActionProvider", "Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "getExternalActionProvider", "()Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "setExternalActionProvider", "(Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;)V", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "playbackProvider", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "getPlaybackProvider", "()Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "setPlaybackProvider", "(Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;)V", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lkotlin/Pair;", "Lcom/amazon/music/skyfire/models/SkyfirePlaybackStateModel;", "", "playbackStateSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lcom/amazon/podcast/media/playback/Playback;", "kotlin.jvm.PlatformType", "podcastPlayback", "Lcom/amazon/podcast/media/playback/Playback;", "com/amazon/mp3/watch/WatchSessionService$podcastPlaybackCallback$1", "podcastPlaybackCallback", "Lcom/amazon/mp3/watch/WatchSessionService$podcastPlaybackCallback$1;", "Landroid/media/AudioManager;", "audioManager", "Landroid/media/AudioManager;", "Landroid/database/ContentObserver;", "volumeObserver", "Landroid/database/ContentObserver;", "Lorg/slf4j/Logger;", "logger", "Lorg/slf4j/Logger;", "", "Lcom/google/android/gms/wearable/Node;", "connectedNodes", "Ljava/util/Set;", "listenerId", "Ljava/lang/String;", "getListenerId", "()Ljava/lang/String;", "Lcom/amazon/music/skyfire/SkyFireApplication;", "getSkyFireApplication", "()Lcom/amazon/music/skyfire/SkyFireApplication;", "skyFireApplication", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class WatchSessionService extends Service implements MethodListener, DispatcherContext, MethodCallback<Method>, OnPlayStateChangedListener, OnPlaybackEventListener, CapabilityClient.OnCapabilityChangedListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean isBound;
    private static final Uri tetheredPlaybackStateUri;
    private AudioManager audioManager;
    private final Function0<CapabilityClient> capabilityClient;
    private Set<? extends Node> connectedNodes;
    private final Function0<DataClient> dataClient;
    public ExternalActionProvider externalActionProvider;
    private final Function0<Boolean> isSignedOut;
    private final String listenerId;
    private final Logger logger;
    private final Function0<MessageClient> messageClient;
    private final ActionValidatedPlaybackController playbackController;
    public PlaybackProvider playbackProvider;
    private final PublishSubject<Pair<SkyfirePlaybackStateModel, Long>> playbackStateSubject;
    private final Playback podcastPlayback;
    private final WatchSessionService$podcastPlaybackCallback$1 podcastPlaybackCallback;
    public SkyFireProvider skyFireProvider;
    private final ContentObserver volumeObserver;

    /* JADX INFO: compiled from: WatchSessionDelegate.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RepeatMode.values().length];
            iArr[RepeatMode.REPEAT_ALL.ordinal()] = 1;
            iArr[RepeatMode.REPEAT_ONE.ordinal()] = 2;
            iArr[RepeatMode.REPEAT_NONE.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final void disconnectedFromWatch() {
    }

    public WatchSessionService() {
        PublishSubject<Pair<SkyfirePlaybackStateModel, Long>> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.playbackStateSubject = publishSubjectCreate;
        this.playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.EXTERNAL);
        this.podcastPlayback = Podcast.getPlayback();
        this.podcastPlaybackCallback = new WatchSessionService$podcastPlaybackCallback$1(this);
        final Handler handler = new Handler(Looper.getMainLooper());
        this.volumeObserver = new ContentObserver(handler) { // from class: com.amazon.mp3.watch.WatchSessionService$volumeObserver$1
            @Override // android.database.ContentObserver
            public boolean deliverSelfNotifications() {
                return false;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                this.this$0.onPlayStateChanged();
            }
        };
        Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(javaClass.simpleName)");
        this.logger = logger;
        this.connectedNodes = SetsKt__SetsKt.emptySet();
        this.listenerId = OwnerIdGenerator.watch;
        this.isSignedOut = new Function0<Boolean>() { // from class: com.amazon.mp3.watch.WatchSessionService.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(AccountCredentialUtil.get(WatchSessionService.this.getApplicationContext()).isSignedOut());
            }
        };
        this.dataClient = new Function0<DataClient>() { // from class: com.amazon.mp3.watch.WatchSessionService.2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DataClient invoke() {
                DataClient dataClient = Wearable.getDataClient(WatchSessionService.this.getApplicationContext());
                Intrinsics.checkNotNullExpressionValue(dataClient, "getDataClient(applicationContext)");
                return dataClient;
            }
        };
        this.messageClient = new Function0<MessageClient>() { // from class: com.amazon.mp3.watch.WatchSessionService.3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MessageClient invoke() {
                MessageClient messageClient = Wearable.getMessageClient(WatchSessionService.this.getApplicationContext());
                Intrinsics.checkNotNullExpressionValue(messageClient, "getMessageClient(applicationContext)");
                return messageClient;
            }
        };
        this.capabilityClient = new Function0<CapabilityClient>() { // from class: com.amazon.mp3.watch.WatchSessionService.4
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CapabilityClient invoke() {
                CapabilityClient capabilityClient = Wearable.getCapabilityClient(WatchSessionService.this.getApplicationContext());
                Intrinsics.checkNotNullExpressionValue(capabilityClient, "getCapabilityClient(applicationContext)");
                return capabilityClient;
            }
        };
    }

    public final SkyFireProvider getSkyFireProvider() {
        SkyFireProvider skyFireProvider = this.skyFireProvider;
        if (skyFireProvider != null) {
            return skyFireProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("skyFireProvider");
        return null;
    }

    public final ExternalActionProvider getExternalActionProvider() {
        ExternalActionProvider externalActionProvider = this.externalActionProvider;
        if (externalActionProvider != null) {
            return externalActionProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("externalActionProvider");
        return null;
    }

    public final PlaybackProvider getPlaybackProvider() {
        PlaybackProvider playbackProvider = this.playbackProvider;
        if (playbackProvider != null) {
            return playbackProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playbackProvider");
        return null;
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.DispatcherContext
    public SkyFireApplication getSkyFireApplication() {
        return getSkyFireProvider().getSkyFireApplication();
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.MethodListener, com.amazon.music.skyfire.ui.skyfire.DispatcherContext
    public String getListenerId() {
        return this.listenerId;
    }

    /* JADX INFO: compiled from: WatchSessionDelegate.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/watch/WatchSessionService$Companion;", "", "()V", "PLAYBACK_STATE_DEBOUNCE_INTERVAL_MS", "", "REMOTE_PLAYBACK_CONTROLLER_CAPABILITY", "", "<set-?>", "", "isBound", "()Z", "tetheredPlaybackStateUri", "Landroid/net/Uri;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isBound() {
            return WatchSessionService.isBound;
        }
    }

    static {
        Uri uri = Uri.parse("wear://*/wearos/playbackState");
        Intrinsics.checkNotNullExpressionValue(uri, "parse(\"wear://*${Tethere…heredPlaybackStatePath}\")");
        tetheredPlaybackStateUri = uri;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Object applicationContext = getApplicationContext();
        if (applicationContext == null) {
            throw new NullPointerException("null cannot be cast to non-null type dagger.android.HasAndroidInjector");
        }
        ((HasAndroidInjector) applicationContext).androidInjector().inject(this);
        HashSet hashSet = new HashSet();
        String canonicalName = ImmutableSyncMediaMetadataMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName);
        hashSet.add(canonicalName);
        String canonicalName2 = ImmutableStartSyncMediaMetadataMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName2);
        hashSet.add(canonicalName2);
        String canonicalName3 = ImmutableStopSyncMediaMetadataMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName3);
        hashSet.add(canonicalName3);
        getSkyFireApplication().registerMethods(SetsKt__SetsKt.emptySet(), hashSet, this);
        getSkyFireProvider().getRouter().plusAssign(this);
        this.playbackStateSubject.debounce(500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.single()).subscribe(new Consumer() { // from class: com.amazon.mp3.watch.WatchSessionService$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws ExecutionException, InterruptedException {
                WatchSessionService.m1835onCreate$lambda2(this.f$0, (Pair) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.watch.WatchSessionService$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                WatchSessionService.m1837onCreate$lambda3(this.f$0, (Throwable) obj);
            }
        });
        this.audioManager = (AudioManager) getApplicationContext().getSystemService(AudioManager.class);
        this.capabilityClient.invoke().getCapability("remote_playback_controller", 1).addOnSuccessListener(new OnSuccessListener() { // from class: com.amazon.mp3.watch.WatchSessionService$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                this.f$0.onCapabilityChanged((CapabilityInfo) obj);
            }
        });
        this.capabilityClient.invoke().addListener(this, "remote_playback_controller");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-2, reason: not valid java name */
    public static final void m1835onCreate$lambda2(final WatchSessionService this$0, Pair pair) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (pair.getFirst() != null) {
            PutDataMapRequest putDataMapRequestCreate = PutDataMapRequest.create(TetheredMessageKey.tetheredPlaybackStatePath);
            Json.Companion companion = Json.INSTANCE;
            Object first = pair.getFirst();
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(companion.getSerializersModule(), Reflection.nullableTypeOf(SkyfirePlaybackStateModel.class));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            putDataMapRequestCreate.getDataMap().putString(TetheredMessageKey.playbackState, companion.encodeToString(kSerializerSerializer, first));
            putDataMapRequestCreate.getDataMap().putLong(TetheredMessageKey.timestamp, ((Number) pair.getSecond()).longValue());
            PutDataRequest urgent = putDataMapRequestCreate.asPutDataRequest().setUrgent();
            Intrinsics.checkNotNullExpressionValue(urgent, "create(TetheredMessageKe…             .setUrgent()");
            this$0.dataClient.invoke().putDataItem(urgent).addOnFailureListener(new OnFailureListener() { // from class: com.amazon.mp3.watch.WatchSessionService$$ExternalSyntheticLambda6
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    WatchSessionService.m1836onCreate$lambda2$lambda1(this.f$0, exc);
                }
            });
            return;
        }
        Tasks.await(this$0.dataClient.invoke().deleteDataItems(tetheredPlaybackStateUri));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1836onCreate$lambda2$lambda1(WatchSessionService this$0, Exception exception) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(exception, "exception");
        this$0.logger.error("Could not update watch state: " + exception + ')');
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-3, reason: not valid java name */
    public static final void m1837onCreate$lambda3(WatchSessionService this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.logger.error(Intrinsics.stringPlus("PlaybackStateSubject threw an error: ", th));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0025  */
    @Override // com.google.android.gms.wearable.CapabilityClient.OnCapabilityChangedListener, com.google.android.gms.wearable.CapabilityApi.CapabilityListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onCapabilityChanged(com.google.android.gms.wearable.CapabilityInfo r4) {
        /*
            r3 = this;
            java.lang.String r0 = "capabilityInfo"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.util.Set<? extends com.google.android.gms.wearable.Node> r0 = r3.connectedNodes
            boolean r0 = r0.isEmpty()
            java.lang.String r1 = "capabilityInfo.nodes"
            if (r0 == 0) goto L25
            java.util.Set r0 = r4.getNodes()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L25
            r3.registerListeners()
            r3.onPlayStateChanged()
            goto L49
        L25:
            java.util.Set<? extends com.google.android.gms.wearable.Node> r0 = r3.connectedNodes
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L49
            java.util.Set r0 = r4.getNodes()
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L49
            r3.unregisterListeners()
            kotlin.jvm.functions.Function0<com.google.android.gms.wearable.DataClient> r0 = r3.dataClient
            java.lang.Object r0 = r0.invoke()
            com.google.android.gms.wearable.DataClient r0 = (com.google.android.gms.wearable.DataClient) r0
            android.net.Uri r2 = com.amazon.mp3.watch.WatchSessionService.tetheredPlaybackStateUri
            r0.deleteDataItems(r2)
        L49:
            java.util.Set r4 = r4.getNodes()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r1)
            r3.connectedNodes = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.watch.WatchSessionService.onCapabilityChanged(com.google.android.gms.wearable.CapabilityInfo):void");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null || !intent.hasExtra("message")) {
            return 1;
        }
        didReceiveMessage(intent);
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        isBound = false;
        unregisterListeners();
        this.capabilityClient.invoke().removeListener(this, "remote_playback_controller");
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent rootIntent) {
        Intrinsics.checkNotNullParameter(rootIntent, "rootIntent");
        super.onTaskRemoved(rootIntent);
        this.dataClient.invoke().deleteDataItems(tetheredPlaybackStateUri).addOnCompleteListener(new OnCompleteListener() { // from class: com.amazon.mp3.watch.WatchSessionService$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                WatchSessionService.m1839onTaskRemoved$lambda4(this.f$0, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onTaskRemoved$lambda-4, reason: not valid java name */
    public static final void m1839onTaskRemoved$lambda4(WatchSessionService this$0, Task it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.stopSelf();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        isBound = true;
        return null;
    }

    private final void registerListeners() {
        getApplicationContext().getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.volumeObserver);
        this.playbackController.addOnPlayStateChangedListener(this);
        this.playbackController.addOnPlaybackEventListener(this);
        this.podcastPlayback.addStateCallback(this.podcastPlaybackCallback);
        this.podcastPlayback.addSpeedCallback(this.podcastPlaybackCallback);
    }

    private final void unregisterListeners() {
        getApplicationContext().getContentResolver().unregisterContentObserver(this.volumeObserver);
        this.playbackController.removeOnPlayStateChangedListener(this);
        this.playbackController.removeOnPlaybackEventListener(this);
        this.podcastPlayback.removeStateCallback(this.podcastPlaybackCallback);
        this.podcastPlayback.removeSpeedCallback(this.podcastPlaybackCallback);
    }

    @Override // com.amazon.music.skyfire.platform.MethodCallback
    public void executeMethod(Method method, String ownerId, OnMethodExecutedListener<Method> onMethodExecutedListener) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(onMethodExecutedListener, "onMethodExecutedListener");
        handleMethod(method);
        onMethodExecutedListener.onMethodExecuted(method);
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.MethodListener
    public void handleMethod(Method method) {
        Intrinsics.checkNotNullParameter(method, "method");
        if (method instanceof SyncMediaMetadataMethod) {
            onPlayStateChanged();
            return;
        }
        if (method instanceof StartSyncMediaMetadataMethod) {
            connectedToWatch();
            return;
        }
        if (method instanceof StopSyncMediaMetadataMethod) {
            disconnectedFromWatch();
            return;
        }
        if (method instanceof TriggerExternalGenericPlaybackMethod) {
            onTriggerExternalGenericPlayback((TriggerExternalGenericPlaybackMethod) method);
            return;
        }
        if (!(method instanceof TriggerExternalActionMethod)) {
            this.logger.warn("Don't know how to handle method '" + method + '\'');
            return;
        }
        TriggerExternalActionMethod triggerExternalActionMethod = (TriggerExternalActionMethod) method;
        String strName = triggerExternalActionMethod.name();
        Intrinsics.checkNotNullExpressionValue(strName, "method.name()");
        Map<String, String> mapContext = triggerExternalActionMethod.context();
        if (mapContext == null) {
            mapContext = MapsKt__MapsKt.emptyMap();
        }
        onTriggerExternalAction(strName, mapContext);
    }

    private final void onTriggerExternalAction(String action, Map<String, String> args) {
        ExternalActionProvider externalActionProvider = getExternalActionProvider();
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        externalActionProvider.handleAction(applicationContext, action, args, null);
    }

    private final void onTriggerExternalGenericPlayback(TriggerExternalGenericPlaybackMethod method) {
        List<PlayableContent> listContent = method.content();
        if (listContent == null) {
            return;
        }
        PlayableContent playableContent = (PlayableContent) CollectionsKt___CollectionsKt.firstOrNull((List) listContent);
        if (!(playableContent instanceof Track)) {
            if (playableContent == null) {
                return;
            }
            getPlaybackProvider().play(playableContent, getApplicationContext());
            return;
        }
        PlaybackProvider playbackProvider = getPlaybackProvider();
        ExplorePlaybackProvider explorePlaybackProvider = playbackProvider instanceof ExplorePlaybackProvider ? (ExplorePlaybackProvider) playbackProvider : null;
        if (explorePlaybackProvider == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (PlayableContent playableContent2 : listContent) {
            Track track = playableContent2 instanceof Track ? (Track) playableContent2 : null;
            if (track != null) {
                arrayList.add(track);
            }
        }
        explorePlaybackProvider.playTracks(getApplicationContext(), arrayList, method.startingIndex());
    }

    private final void connectedToWatch() {
        onPlayStateChanged();
    }

    private final void didReceiveMessage(Intent intent) {
        if (this.isSignedOut.invoke().booleanValue()) {
            Integer numValueOf = Integer.valueOf(intent.getIntExtra(TetheredMessageKey.requestId, -1));
            if (numValueOf.intValue() == -1) {
                numValueOf = null;
            }
            String stringExtra = intent.getStringExtra(TetheredMessageKey.nodeId);
            if (stringExtra == null) {
                return;
            }
            replyToWatch(stringExtra, TetheredError.notLoggedIn, numValueOf);
            return;
        }
        String stringExtra2 = intent.getStringExtra("message");
        if (stringExtra2 == null) {
            return;
        }
        Json.Companion companion = Json.INSTANCE;
        KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(companion.getSerializersModule(), Reflection.typeOf(TetheredMessage.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        TetheredMessage tetheredMessage = (TetheredMessage) companion.decodeFromString(kSerializerSerializer, stringExtra2);
        Iterator<String> it = tetheredMessage.getClientInformation().values().iterator();
        while (it.hasNext()) {
            AndroidClientInformationCache.INSTANCE.set(it.next());
        }
        if (!StringsKt__StringsJVMKt.isBlank(tetheredMessage.getMethods())) {
            try {
                getSkyFireApplication().dispatchMethods(OwnerIdGenerator.watch, ((Response) Mappers.mapper().readValue(tetheredMessage.getMethods(), Response.class)).methods());
            } catch (Exception e) {
                this.logger.error(e.getMessage());
            }
        }
    }

    private final void replyToWatch(String nodeId, TetheredError error, Integer requestId) {
        TetheredErrorMessage tetheredErrorMessage = new TetheredErrorMessage(error, requestId);
        Json.Companion companion = Json.INSTANCE;
        KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(companion.getSerializersModule(), Reflection.typeOf(TetheredErrorMessage.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        byte[] bytes = companion.encodeToString(kSerializerSerializer, tetheredErrorMessage).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        this.messageClient.invoke().sendMessage(nodeId, TetheredMessageKey.tetheredErrorPath, bytes).addOnFailureListener(new OnFailureListener() { // from class: com.amazon.mp3.watch.WatchSessionService$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                WatchSessionService.m1840replyToWatch$lambda9(this.f$0, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: replyToWatch$lambda-9, reason: not valid java name */
    public static final void m1840replyToWatch$lambda9(WatchSessionService this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.logger.error(it.getMessage());
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        if (this.podcastPlayback.isPodcastLastPlayed()) {
            podcastPlaybackStateChanged();
        } else {
            musicPlaybackStateChanged();
        }
    }

    @Override // com.amazon.music.media.playback.OnPlaybackEventListener
    public void onPlaybackEvent(int event, ControlSource controlSource) {
        if (event == 12 || event == 14 || event == 18) {
            for (Node node : this.connectedNodes) {
                MessageClient messageClientInvoke = this.messageClient.invoke();
                String id = node.getId();
                byte[] bytes = TetheredMessageKey.launchApplication.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                messageClientInvoke.sendMessage(id, TetheredMessageKey.tetheredNotificationPath, bytes).addOnFailureListener(new OnFailureListener() { // from class: com.amazon.mp3.watch.WatchSessionService$$ExternalSyntheticLambda2
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        WatchSessionService.m1838onPlaybackEvent$lambda10(this.f$0, exc);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlaybackEvent$lambda-10, reason: not valid java name */
    public static final void m1838onPlaybackEvent$lambda10(WatchSessionService this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.logger.error(it.getMessage());
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x013c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void musicPlaybackStateChanged() {
        /*
            Method dump skipped, instruction units count: 384
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.watch.WatchSessionService.musicPlaybackStateChanged():void");
    }

    private final void podcastPlaybackStateChanged() {
        Unit unit;
        MediaMetadataElement mediaMetadataElement = this.podcastPlayback.getMedia().getMediaMetadataElement();
        if (mediaMetadataElement == null) {
            unit = null;
        } else {
            float value = this.podcastPlayback.getPlaybackSpeed().getValue();
            SkyfirePlaybackStateModel skyfirePlaybackStateModel = new SkyfirePlaybackStateModel(mediaMetadataElement.getTitle(), (String) null, mediaMetadataElement.getSubtitle(), (int) (this.podcastPlayback.getPlaybackDuration() / 1000.0f), this.podcastPlayback.getPlaybackPosition() / 1000.0f, this.podcastPlayback.getPlaybackState() == 3 ? value : 0.0f, this.podcastPlayback.getPlaybackState() == 6, mediaMetadataElement.getArtwork(), this.audioManager == null ? 0.0f : r3.getStreamVolume(3) / r3.getStreamMaxVolume(3), value, (ShuffleControlState) null, (RepeatControlState) null, (SkyfirePlaybackControlsModel) null, 7168, (DefaultConstructorMarker) null);
            SkyfirePlaybackControlsModel controls = skyfirePlaybackStateModel.getControls();
            SkyfirePlaybackControlsModel.ButtonState buttonState = SkyfirePlaybackControlsModel.ButtonState.ENABLED;
            controls.setPlay(buttonState);
            skyfirePlaybackStateModel.getControls().setNext(buttonState);
            skyfirePlaybackStateModel.getControls().setPrevious(buttonState);
            skyfirePlaybackStateModel.getControls().setRepeat(null);
            skyfirePlaybackStateModel.getControls().setShuffle(null);
            skyfirePlaybackStateModel.getControls().setSkipForward(buttonState);
            skyfirePlaybackStateModel.getControls().setSkipBackward(buttonState);
            skyfirePlaybackStateModel.getControls().setPlaybackSpeed(buttonState);
            SkyfirePlaybackControlsModel controls2 = skyfirePlaybackStateModel.getControls();
            PlaybackSpeed[] playbackSpeedArrValues = PlaybackSpeed.values();
            ArrayList arrayList = new ArrayList(playbackSpeedArrValues.length);
            int length = playbackSpeedArrValues.length;
            int i = 0;
            while (i < length) {
                PlaybackSpeed playbackSpeed = playbackSpeedArrValues[i];
                i++;
                String displayText = playbackSpeed.getDisplayText();
                Intrinsics.checkNotNullExpressionValue(displayText, "playbackSpeedEntry.displayText");
                arrayList.add(new SkyfirePlaybackControlsModel.PlaybackSpeedOption(displayText, playbackSpeed.getValue()));
            }
            controls2.setPlaybackSpeedOptions(arrayList);
            this.playbackStateSubject.onNext(new Pair<>(skyfirePlaybackStateModel, Long.valueOf(Instant.now().getMillis())));
            unit = Unit.INSTANCE;
        }
        if (unit != null || this.podcastPlayback.getShouldShowMiniPlayerInApp()) {
            return;
        }
        this.playbackStateSubject.onNext(new Pair<>(null, Long.valueOf(Instant.now().getMillis())));
    }
}
