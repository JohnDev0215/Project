package com.amazon.mp3.reactnative.modules;

import com.amazon.mp3.util.Log;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.playback.queueseeds.MediaResourceNameFactory;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.PlayQueuePosition;
import com.amazon.music.platform.providers.PlayQueueProvider;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
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
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: PlayQueueModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0011\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0016J\u0018\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0018\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0007R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/PlayQueueModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "playQueueProvider", "Lcom/amazon/music/platform/providers/PlayQueueProvider;", "getPlayQueueProvider", "()Lcom/amazon/music/platform/providers/PlayQueueProvider;", "playQueueProvider$delegate", "Lkotlin/Lazy;", "add", "", "entityItem", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "position", "Lcom/amazon/music/platform/providers/PlayQueuePosition;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "mrn", "", "getEntityId", "getName", "insertTrackAtBeginning", "insertTrackAtEnd", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayQueueModule extends ReactContextBaseJavaModule {
    private static final String TAG = PlayQueueModule.class.getSimpleName();

    /* JADX INFO: renamed from: playQueueProvider$delegate, reason: from kotlin metadata */
    private final Lazy playQueueProvider;

    public PlayQueueModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.playQueueProvider = LazyKt__LazyJVMKt.lazy(new Function0<PlayQueueProvider>() { // from class: com.amazon.mp3.reactnative.modules.PlayQueueModule$playQueueProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlayQueueProvider invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(PlayQueueProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof PlayQueueProvider)) {
                    interfaceProvider = null;
                }
                return (PlayQueueProvider) interfaceProvider;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlayQueueProvider getPlayQueueProvider() {
        return (PlayQueueProvider) this.playQueueProvider.getValue();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    @ReactMethod
    public final void insertTrackAtBeginning(String mrn, Promise promise) {
        Intrinsics.checkNotNullParameter(mrn, "mrn");
        Intrinsics.checkNotNullParameter(promise, "promise");
        add(mrn, PlayQueuePosition.NEXT, promise);
    }

    @ReactMethod
    public final void insertTrackAtEnd(String mrn, Promise promise) {
        Intrinsics.checkNotNullParameter(mrn, "mrn");
        Intrinsics.checkNotNullParameter(promise, "promise");
        add(mrn, PlayQueuePosition.END, promise);
    }

    private final void add(String mrn, PlayQueuePosition position, Promise promise) {
        Unit unit;
        EntityItemIdentifier entityId = getEntityId(mrn);
        if (entityId == null) {
            unit = null;
        } else {
            add(entityId, PlayQueuePosition.END, promise);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Log.error(TAG, "MRN conversion to EntityItemIdentifier failed");
            promise.reject("typeError", "MRN conversion failed");
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.reactnative.modules.PlayQueueModule$add$3, reason: invalid class name */
    /* JADX INFO: compiled from: PlayQueueModule.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.reactnative.modules.PlayQueueModule$add$3", f = "PlayQueueModule.kt", i = {0}, l = {77}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ EntityItemIdentifier $entityItem;
        final /* synthetic */ PlayQueuePosition $position;
        final /* synthetic */ Promise $promise;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(EntityItemIdentifier entityItemIdentifier, PlayQueuePosition playQueuePosition, Promise promise, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$entityItem = entityItemIdentifier;
            this.$position = playQueuePosition;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = PlayQueueModule.this.new AnonymousClass3(this.$entityItem, this.$position, this.$promise, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x005f  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 1
                if (r1 == 0) goto L1f
                if (r1 != r2) goto L17
                java.lang.Object r0 = r6.L$1
                com.facebook.react.bridge.Promise r0 = (com.facebook.react.bridge.Promise) r0
                java.lang.Object r1 = r6.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r7)
                goto L44
            L17:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L1f:
                kotlin.ResultKt.throwOnFailure(r7)
                java.lang.Object r7 = r6.L$0
                kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
                com.amazon.mp3.reactnative.modules.PlayQueueModule r1 = com.amazon.mp3.reactnative.modules.PlayQueueModule.this
                com.amazon.music.platform.providers.PlayQueueProvider r1 = com.amazon.mp3.reactnative.modules.PlayQueueModule.access$getPlayQueueProvider(r1)
                if (r1 != 0) goto L30
                r7 = 0
                goto L5d
            L30:
                com.amazon.music.platform.data.entity.EntityItemIdentifier r3 = r6.$entityItem
                com.amazon.music.platform.providers.PlayQueuePosition r4 = r6.$position
                com.facebook.react.bridge.Promise r5 = r6.$promise
                r6.L$0 = r7
                r6.L$1 = r5
                r6.label = r2
                java.lang.Object r7 = r1.add(r3, r4, r6)
                if (r7 != r0) goto L43
                return r0
            L43:
                r0 = r5
            L44:
                java.lang.Boolean r7 = (java.lang.Boolean) r7
                boolean r7 = r7.booleanValue()
                if (r7 == 0) goto L54
                java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
                r0.resolve(r7)
                goto L5b
            L54:
                java.lang.String r7 = "actionFailed"
                java.lang.String r1 = "Unable to add the item to play queue"
                r0.reject(r7, r1)
            L5b:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
            L5d:
                if (r7 != 0) goto L6f
                com.facebook.react.bridge.Promise r7 = r6.$promise
                java.lang.String r0 = com.amazon.mp3.reactnative.modules.PlayQueueModule.access$getTAG$cp()
                java.lang.String r1 = "PlayQueueProvider not found"
                com.amazon.mp3.util.Log.error(r0, r1)
                java.lang.String r0 = "PlayQueueProvider"
                com.amazon.mp3.reactnative.modules.PromiseExtensionKt.rejectWithProviderNotFound(r7, r0)
            L6f:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.reactnative.modules.PlayQueueModule.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final void add(EntityItemIdentifier entityItem, PlayQueuePosition position, Promise promise) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass3(entityItem, position, promise, null), 3, null);
    }

    private final EntityItemIdentifier getEntityId(String mrn) {
        return MediaResourceNameFactory.INSTANCE.from(mrn);
    }
}
