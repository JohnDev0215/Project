package com.amazon.mp3.casting;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.ImmutableInitializeTransferPlaybackMethod;
import ExternalActionInterface.v1_0.ImmutableSubscribeIotMethod;
import ExternalActionInterface.v1_0.InitializeTransferPlaybackMethod;
import ExternalActionInterface.v1_0.SubscribeIotMethod;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.casting.CastingQueueHandler;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionType;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.explore.skyfire.SkyFireUtils;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.core.template.OwnerIdGenerator;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.skyfire.MethodListener;
import com.amazon.musicplayqueueservice.client.common.CastingDevice;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.media.playback.Playback;
import dagger.android.HasAndroidInjector;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CastingSkillDelegate.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 12\u00020\u00012\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u00020\u00050\u0004:\u00011B\t\b\u0016¢\u0006\u0004\b/\u00100J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0014\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0005H\u0016J\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0010J\u000e\u0010\u0013\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0012J&\u0010\u0018\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0016H\u0016J\"\u0010\u001c\u001a\u00020\u00192\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0016R\"\u0010\u001e\u001a\u00020\u001d8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u00148\u0016X\u0096D¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010.\u001a\u00020+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u00062"}, d2 = {"Lcom/amazon/mp3/casting/CastingSkillService;", "Landroid/app/Service;", "Lcom/amazon/music/skyfire/ui/skyfire/MethodListener;", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "Lcom/amazon/music/skyfire/platform/MethodCallback;", "LCoreInterface/v1_0/Method;", "Landroid/content/Intent;", MAPAccountManager.KEY_INTENT, "", "invokeSkill", "p0", "Landroid/os/IBinder;", "onBind", "onCreate", "method", "handleMethod", "LExternalActionInterface/v1_0/InitializeTransferPlaybackMethod;", "handleInitializeTransferPlayback", "LExternalActionInterface/v1_0/SubscribeIotMethod;", "handleSubscribeIoT", "", "ownerId", "Lcom/amazon/music/skyfire/platform/OnMethodExecutedListener;", "onMethodExecutedListener", "executeMethod", "", "flags", "startId", "onStartCommand", "Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "skyFireProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "getSkyFireProvider", "()Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "setSkyFireProvider", "(Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;)V", "listenerId", "Ljava/lang/String;", "getListenerId", "()Ljava/lang/String;", "Lorg/slf4j/Logger;", "logger", "Lorg/slf4j/Logger;", "Lcom/amazon/music/skyfire/SkyFireApplication;", "getSkyFireApplication", "()Lcom/amazon/music/skyfire/SkyFireApplication;", "skyFireApplication", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class CastingSkillService extends Service implements MethodListener, DispatcherContext, MethodCallback<Method> {
    private static final String singleActiveStreamSkillPath = "/amconnect/getSingleActiveStream";
    private static final String transferSkillPath = "/amconnect/transferPlayback";
    private final String listenerId = OwnerIdGenerator.amconnect;
    private final Logger logger;
    public SkyFireProvider skyFireProvider;

    public final void handleInitializeTransferPlayback(InitializeTransferPlaybackMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent p0) {
        return null;
    }

    public CastingSkillService() {
        Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(javaClass.simpleName)");
        this.logger = logger;
    }

    public final SkyFireProvider getSkyFireProvider() {
        SkyFireProvider skyFireProvider = this.skyFireProvider;
        if (skyFireProvider != null) {
            return skyFireProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("skyFireProvider");
        return null;
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.MethodListener, com.amazon.music.skyfire.ui.skyfire.DispatcherContext
    public String getListenerId() {
        return this.listenerId;
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.DispatcherContext
    public SkyFireApplication getSkyFireApplication() {
        return getSkyFireProvider().getSkyFireApplication();
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
        String canonicalName = ImmutableInitializeTransferPlaybackMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName);
        hashSet.add(canonicalName);
        String canonicalName2 = ImmutableSubscribeIotMethod.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName2);
        hashSet.add(canonicalName2);
        getSkyFireApplication().registerMethods(SetsKt__SetsKt.emptySet(), hashSet, this);
        getSkyFireProvider().getRouter().plusAssign(this);
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.MethodListener
    public void handleMethod(Method method) throws CastingException {
        Intrinsics.checkNotNullParameter(method, "method");
        if (method instanceof SubscribeIotMethod) {
            handleSubscribeIoT((SubscribeIotMethod) method);
        } else if (method instanceof InitializeTransferPlaybackMethod) {
            handleInitializeTransferPlayback((InitializeTransferPlaybackMethod) method);
        }
    }

    public final void handleSubscribeIoT(SubscribeIotMethod method) throws CastingException {
        Intrinsics.checkNotNullParameter(method, "method");
        if (method.targetId() == null || method.queueId() == null) {
            return;
        }
        if (CastingUtil.isCastingToAlexa() && Intrinsics.areEqual(CastingUtil.getLastCastingDeviceTargetId(), method.targetId())) {
            return;
        }
        CastingSessionManager castingSessionManager = CastingSessionManager.getInstance();
        List<CastingDevice> amazonDeviceList = castingSessionManager.getCasting().getAmazonDeviceList();
        Playback playback = Podcast.getPlayback();
        for (CastingDevice castingDevice : amazonDeviceList) {
            if (castingDevice.getTargetId().equals(method.targetId())) {
                com.amazon.music.casting.CastingDevice castingDeviceBuild = new CastingDevice.Builder(CastingCategory.fromString(castingDevice.getCategory()), method.targetId()).withName(castingDevice.getName()).withOverridePlayback(false).withIsPlaying(castingDevice.isIsPlaying()).withDeviceTypeId("UNKNOWN").build();
                if (playback.isPodcastLastPlayed()) {
                    playback.clear();
                    NowPlayingUtil.clear();
                }
                castingSessionManager.connect(castingDeviceBuild, CastingConnectionType.AUTOMATIC);
                CastingQueueHandler.saveQueueId(getApplicationContext(), method.queueId());
                return;
            }
        }
    }

    @Override // com.amazon.music.skyfire.platform.MethodCallback
    public void executeMethod(Method method, String ownerId, OnMethodExecutedListener<Method> onMethodExecutedListener) throws CastingException {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(onMethodExecutedListener, "onMethodExecutedListener");
        handleMethod(method);
        onMethodExecutedListener.onMethodExecuted(method);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return 1;
        }
        invokeSkill(intent);
        return 1;
    }

    private final void invokeSkill(Intent intent) {
        String stringExtra = intent.getStringExtra(CastingUtil.SKILL_TYPE);
        if (StringsKt__StringsJVMKt.equals$default(stringExtra, CastingUtil.SINGLE_ACTIVE_STREAM, false, 2, null)) {
            getSkyFireApplication().dispatchMethods(OwnerIdGenerator.amconnect, CollectionsKt__CollectionsJVMKt.listOf(SkyFireUtils.getInvokeHttpSkillMethod(singleActiveStreamSkillPath)));
        } else if (StringsKt__StringsJVMKt.equals$default(stringExtra, CastingUtil.TRANSFER_PLAYBACK, false, 2, null) && intent.getStringExtra(CastingUtil.QUEUE_ID) == null) {
            this.logger.error("queueId was null, could not transfer");
        }
    }
}
