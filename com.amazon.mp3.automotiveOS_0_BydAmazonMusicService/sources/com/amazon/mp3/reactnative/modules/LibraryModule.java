package com.amazon.mp3.reactnative.modules;

import com.amazon.mp3.util.Log;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.library.LibraryUseCase;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.playback.queueseeds.MediaResourceNameFactory;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.LibraryProvider;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: LibraryModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\b\u0010\u0016\u001a\u00020\u0011H\u0007J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J \u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0016J\u0018\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0018\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\u000b\u001a\u0004\u0018\u00010\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/LibraryModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "coroutineScope$delegate", "Lkotlin/Lazy;", "libraryProvider", "Lcom/amazon/music/platform/providers/LibraryProvider;", "getLibraryProvider", "()Lcom/amazon/music/platform/providers/LibraryProvider;", "libraryProvider$delegate", "addToLibrary", "", "mrn", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "cancel", "getIdentifier", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getInteractor", "Lcom/amazon/music/platform/data/library/LibraryUseCase;", "Lcom/amazon/music/platform/data/entity/EntityItem;", "identifier", "getName", "isInLibrary", "removeFromLibrary", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryModule extends ReactContextBaseJavaModule {
    private static final String PROVIDER_NAME = "LibraryModule";

    /* JADX INFO: renamed from: coroutineScope$delegate, reason: from kotlin metadata */
    private final Lazy coroutineScope;

    /* JADX INFO: renamed from: libraryProvider$delegate, reason: from kotlin metadata */
    private final Lazy libraryProvider;
    private static final String TAG = LibraryModule.class.getSimpleName();

    public LibraryModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.libraryProvider = LazyKt__LazyJVMKt.lazy(new Function0<LibraryProvider>() { // from class: com.amazon.mp3.reactnative.modules.LibraryModule$libraryProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LibraryProvider invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(LibraryProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof LibraryProvider)) {
                    interfaceProvider = null;
                }
                return (LibraryProvider) interfaceProvider;
            }
        });
        this.coroutineScope = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineScope>() { // from class: com.amazon.mp3.reactnative.modules.LibraryModule$coroutineScope$2
            @Override // kotlin.jvm.functions.Function0
            public final CoroutineScope invoke() {
                return CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
            }
        });
    }

    private final LibraryProvider getLibraryProvider() {
        return (LibraryProvider) this.libraryProvider.getValue();
    }

    private final CoroutineScope getCoroutineScope() {
        return (CoroutineScope) this.coroutineScope.getValue();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    @ReactMethod
    public final void cancel() {
        if (CoroutineScopeKt.isActive(getCoroutineScope())) {
            CoroutineScopeKt.cancel$default(getCoroutineScope(), null, 1, null);
        }
    }

    @ReactMethod
    public final void addToLibrary(String mrn, Promise promise) {
        LibraryUseCase<EntityItem> interactor;
        Intrinsics.checkNotNullParameter(mrn, "mrn");
        Intrinsics.checkNotNullParameter(promise, "promise");
        EntityItemIdentifier identifier = getIdentifier(mrn, promise);
        if (identifier == null || (interactor = getInteractor(identifier, promise)) == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new LibraryModule$addToLibrary$1$1$1(interactor, identifier, promise, null), 3, null);
    }

    @ReactMethod
    public final void removeFromLibrary(String mrn, Promise promise) {
        LibraryUseCase<EntityItem> interactor;
        Intrinsics.checkNotNullParameter(mrn, "mrn");
        Intrinsics.checkNotNullParameter(promise, "promise");
        EntityItemIdentifier identifier = getIdentifier(mrn, promise);
        if (identifier == null || (interactor = getInteractor(identifier, promise)) == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new LibraryModule$removeFromLibrary$1$1$1(interactor, identifier, promise, null), 3, null);
    }

    @ReactMethod
    public final void isInLibrary(String mrn, Promise promise) {
        LibraryUseCase<EntityItem> interactor;
        Intrinsics.checkNotNullParameter(mrn, "mrn");
        Intrinsics.checkNotNullParameter(promise, "promise");
        EntityItemIdentifier identifier = getIdentifier(mrn, promise);
        if (identifier == null || (interactor = getInteractor(identifier, promise)) == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new LibraryModule$isInLibrary$1$1$1(promise, interactor, identifier, null), 3, null);
    }

    private final EntityItemIdentifier getIdentifier(String mrn, Promise promise) {
        try {
            EntityItemIdentifier entityItemIdentifierFrom = MediaResourceNameFactory.INSTANCE.from(mrn);
            if (entityItemIdentifierFrom != null) {
                return entityItemIdentifierFrom;
            }
        } catch (Exception e) {
            Log.warning(TAG, Intrinsics.stringPlus("Exception while parsing MRN: ", mrn), e);
        }
        promise.reject("notFound", Intrinsics.stringPlus("Failed to get EntityItemIdentifier for MRN: ", mrn));
        return null;
    }

    private final LibraryUseCase<EntityItem> getInteractor(EntityItemIdentifier identifier, Promise promise) {
        EntityItemType type = identifier.getType();
        LibraryProvider libraryProvider = getLibraryProvider();
        if (libraryProvider != null) {
            LibraryUseCase<EntityItem> interactor = libraryProvider.getInteractor(type);
            if (interactor == null) {
                promise.reject("notFound", Intrinsics.stringPlus("Failed to get LibraryUseCase for identifier: ", identifier));
            }
            return interactor;
        }
        PromiseExtensionKt.rejectWithProviderNotFound(promise, PROVIDER_NAME);
        return null;
    }
}
