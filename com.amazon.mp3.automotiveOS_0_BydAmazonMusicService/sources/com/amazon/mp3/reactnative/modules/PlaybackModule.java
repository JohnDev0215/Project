package com.amazon.mp3.reactnative.modules;

import com.amazon.mp3.reactnative.ReactJsonConverter;
import com.amazon.mp3.util.Log;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.events.PlaybackStateEvent;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.playback.PlaybackRequestFactory;
import com.amazon.music.platform.playback.PlaybackStateListener;
import com.amazon.music.platform.playback.data.PlaybackRequest;
import com.amazon.music.platform.playback.state.DevicePlaybackState;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.PlaybackProvider;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: PlaybackModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 .2\u00020\u00012\u00020\u0002:\u0001.B\u0011\b\u0000\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0014\u001a\u00020\u0015H\u0007J\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0011H\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001f\u001a\u00020 H\u0002J(\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0010\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\u001cH\u0016J\b\u0010(\u001a\u00020\u0015H\u0007J\"\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00112\b\u0010-\u001a\u0004\u0018\u00010\u001aH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u0004\u0018\u00010\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006/"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/PlaybackModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "Lcom/amazon/music/platform/playback/PlaybackStateListener;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "playbackProvider", "Lcom/amazon/music/platform/providers/PlaybackProvider;", "getPlaybackProvider", "()Lcom/amazon/music/platform/providers/PlaybackProvider;", "playbackProvider$delegate", "Lkotlin/Lazy;", "playbackStateChangeListenerCount", "", "uniqueIdentity", "", "getUniqueIdentity", "()Ljava/lang/String;", "addPlaybackListener", "", "currentPlaybackState", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "getMap", "Lcom/facebook/react/bridge/WritableMap;", "playbackStateEvent", "Lcom/amazon/music/platform/events/PlaybackStateEvent;", "getName", "getPlaybackStateEvent", "devicePlaybackState", "Lcom/amazon/music/platform/playback/state/DevicePlaybackState;", "initiatePlayback", "playbackRequest", "Lcom/facebook/react/bridge/ReadableMap;", "playbackOptions", "metricsContext", "onEvent", "value", "removePlaybackListener", "sendEvent", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "eventName", "params", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaybackModule extends ReactContextBaseJavaModule implements PlaybackStateListener {
    private static final String TAG = PlaybackModule.class.getSimpleName();
    private final ReactApplicationContext context;
    private final CoroutineScope coroutineScope;

    /* JADX INFO: renamed from: playbackProvider$delegate, reason: from kotlin metadata */
    private final Lazy playbackProvider;
    private volatile int playbackStateChangeListenerCount;

    public PlaybackModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.context = reactApplicationContext;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        this.playbackProvider = LazyKt__LazyJVMKt.lazy(new Function0<PlaybackProvider>() { // from class: com.amazon.mp3.reactnative.modules.PlaybackModule$playbackProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlaybackProvider invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(PlaybackProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof PlaybackProvider)) {
                    interfaceProvider = null;
                }
                return (PlaybackProvider) interfaceProvider;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlaybackProvider getPlaybackProvider() {
        return (PlaybackProvider) this.playbackProvider.getValue();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    @ReactMethod
    public final void initiatePlayback(ReadableMap playbackRequest, ReadableMap playbackOptions, ReadableMap metricsContext, Promise promise) throws Exception {
        Intrinsics.checkNotNullParameter(playbackRequest, "playbackRequest");
        Intrinsics.checkNotNullParameter(playbackOptions, "playbackOptions");
        Intrinsics.checkNotNullParameter(metricsContext, "metricsContext");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(PlaybackRequestFactory.INSTANCE.from(playbackRequest.toHashMap(), playbackOptions.toHashMap(), metricsContext.toHashMap()), promise, null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.reactnative.modules.PlaybackModule$initiatePlayback$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlaybackModule.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.reactnative.modules.PlaybackModule$initiatePlayback$1", f = "PlaybackModule.kt", i = {}, l = {61}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PlaybackRequest $initiatePlaybackRequest;
        final /* synthetic */ Promise $promise;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PlaybackRequest playbackRequest, Promise promise, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$initiatePlaybackRequest = playbackRequest;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlaybackModule.this.new AnonymousClass1(this.$initiatePlaybackRequest, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r4) {
            /*
                r3 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r3.label
                r2 = 1
                if (r1 == 0) goto L17
                if (r1 != r2) goto Lf
                kotlin.ResultKt.throwOnFailure(r4)
                goto L2f
            Lf:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r0)
                throw r4
            L17:
                kotlin.ResultKt.throwOnFailure(r4)
                com.amazon.mp3.reactnative.modules.PlaybackModule r4 = com.amazon.mp3.reactnative.modules.PlaybackModule.this
                com.amazon.music.platform.providers.PlaybackProvider r4 = com.amazon.mp3.reactnative.modules.PlaybackModule.access$getPlaybackProvider(r4)
                if (r4 != 0) goto L24
                r4 = 0
                goto L31
            L24:
                com.amazon.music.platform.playback.data.PlaybackRequest r1 = r3.$initiatePlaybackRequest
                r3.label = r2
                java.lang.Object r4 = r4.initiatePlayback(r1, r3)
                if (r4 != r0) goto L2f
                return r0
            L2f:
                com.amazon.music.platform.Outcome r4 = (com.amazon.music.platform.Outcome) r4
            L31:
                boolean r0 = r4 instanceof com.amazon.music.platform.Outcome.Success
                if (r0 == 0) goto L3f
                com.facebook.react.bridge.Promise r4 = r3.$promise
                java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
                r4.resolve(r0)
                goto L4f
            L3f:
                boolean r4 = r4 instanceof com.amazon.music.platform.Outcome.Failure
                if (r4 == 0) goto L4f
                com.facebook.react.bridge.Promise r4 = r3.$promise
                java.lang.Error r0 = new java.lang.Error
                java.lang.String r1 = "Unable to start playback."
                r0.<init>(r1)
                r4.reject(r0)
            L4f:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.reactnative.modules.PlaybackModule.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @ReactMethod
    public final void currentPlaybackState(Promise promise) {
        DevicePlaybackState devicePlaybackStateState;
        Intrinsics.checkNotNullParameter(promise, "promise");
        PlaybackProvider playbackProvider = getPlaybackProvider();
        Unit unit = null;
        if (playbackProvider != null && (devicePlaybackStateState = playbackProvider.state()) != null) {
            PlaybackStateEvent playbackStateEvent = getPlaybackStateEvent(devicePlaybackStateState);
            if (playbackStateEvent != null) {
                promise.resolve(getMap(playbackStateEvent));
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                promise.reject("error", "No item playing");
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PromiseExtensionKt.rejectWithProviderNotFound(promise, "PlaybackProvider");
        }
    }

    @ReactMethod
    public final void addPlaybackListener() {
        PlaybackProvider playbackProvider;
        if (this.playbackStateChangeListenerCount == 0 && (playbackProvider = getPlaybackProvider()) != null) {
            playbackProvider.addPlaybackStateListener(this);
        }
        this.playbackStateChangeListenerCount++;
    }

    @ReactMethod
    public final void removePlaybackListener() {
        if (this.playbackStateChangeListenerCount > 1) {
            this.playbackStateChangeListenerCount--;
            return;
        }
        if (this.playbackStateChangeListenerCount == 1) {
            this.playbackStateChangeListenerCount = 0;
            PlaybackProvider playbackProvider = getPlaybackProvider();
            if (playbackProvider == null) {
                return;
            }
            playbackProvider.removePlaybackStateListener(this);
        }
    }

    @Override // com.amazon.music.platform.data.Listener
    public void onEvent(PlaybackStateEvent value) {
        Intrinsics.checkNotNullParameter(value, "value");
        ReactApplicationContext reactApplicationContext = this.context;
        if (reactApplicationContext == null) {
            return;
        }
        sendEvent(reactApplicationContext, value.getName(), getMap(value));
    }

    public String getUniqueIdentity() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    private final void sendEvent(ReactContext reactContext, String eventName, WritableMap params) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(eventName, params);
    }

    private final WritableMap getMap(PlaybackStateEvent playbackStateEvent) {
        try {
            return ReactJsonConverter.INSTANCE.convertJsonToMap(new JSONObject(playbackStateEvent.getJson()));
        } catch (JSONException e) {
            String localizedMessage = e.getLocalizedMessage();
            Log.error(TAG, localizedMessage);
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("error", localizedMessage);
            return writableMapCreateMap;
        }
    }

    private final PlaybackStateEvent getPlaybackStateEvent(DevicePlaybackState devicePlaybackState) {
        EntityItemIdentifier currentEntityItemId = devicePlaybackState.getCurrentEntityItemId();
        if (currentEntityItemId == null) {
            return null;
        }
        return new PlaybackStateEvent(devicePlaybackState.getPlaybackState(), currentEntityItemId, devicePlaybackState.getParentEntityItemId());
    }
}
