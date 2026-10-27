package com.th3rdwave.safeareacontext;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.viewmanagers.RNCSafeAreaProviderManagerDelegate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeAreaProviderManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@ReactModule(name = SafeAreaProviderManager.REACT_CLASS)
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u0001\u0016B\u0007¢\u0006\u0004\b\u0014\u0010\u0015J$\u0010\u0006\u001a\u001e\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00000\u00000\u0004H\u0014J\b\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0016J \u0010\r\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\f0\fH\u0016J\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0002H\u0014R0\u0010\u0012\u001a\u001e\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00000\u00000\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcom/th3rdwave/safeareacontext/SafeAreaProviderManager;", "Lcom/facebook/react/uimanager/ViewGroupManager;", "Lcom/th3rdwave/safeareacontext/SafeAreaProvider;", "", "Lcom/facebook/react/viewmanagers/RNCSafeAreaProviderManagerDelegate;", "kotlin.jvm.PlatformType", "getDelegate", "", "getName", "Lcom/facebook/react/uimanager/ThemedReactContext;", "context", "createViewInstance", "", "getExportedCustomDirectEventTypeConstants", "reactContext", "view", "", "addEventEmitters", "mDelegate", "Lcom/facebook/react/viewmanagers/RNCSafeAreaProviderManagerDelegate;", "<init>", "()V", "Companion", "react-native-safe-area-context_release"}, k = 1, mv = {1, 6, 0})
public final class SafeAreaProviderManager extends ViewGroupManager<SafeAreaProvider> {
    public static final String REACT_CLASS = "RNCSafeAreaProvider";
    private final RNCSafeAreaProviderManagerDelegate<SafeAreaProvider, SafeAreaProviderManager> mDelegate = new RNCSafeAreaProviderManagerDelegate<>(this);

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RNCSafeAreaProviderManagerDelegate<SafeAreaProvider, SafeAreaProviderManager> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public SafeAreaProvider createViewInstance(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new SafeAreaProvider(context);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Map<String, String>> getExportedCustomDirectEventTypeConstants() {
        return MapsKt__MapsKt.mutableMapOf(TuplesKt.to("topInsetsChange", MapsKt__MapsKt.mutableMapOf(TuplesKt.to("registrationName", "onInsetsChange"))));
    }

    /* JADX INFO: renamed from: com.th3rdwave.safeareacontext.SafeAreaProviderManager$addEventEmitters$1, reason: invalid class name */
    /* JADX INFO: compiled from: SafeAreaProviderManager.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<SafeAreaProvider, EdgeInsets, Rect, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, SafeAreaProviderManagerKt.class, "handleOnInsetsChange", "handleOnInsetsChange(Lcom/th3rdwave/safeareacontext/SafeAreaProvider;Lcom/th3rdwave/safeareacontext/EdgeInsets;Lcom/th3rdwave/safeareacontext/Rect;)V", 1);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(SafeAreaProvider safeAreaProvider, EdgeInsets edgeInsets, Rect rect) {
            invoke2(safeAreaProvider, edgeInsets, rect);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(SafeAreaProvider p0, EdgeInsets p1, Rect p2) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            Intrinsics.checkNotNullParameter(p1, "p1");
            Intrinsics.checkNotNullParameter(p2, "p2");
            SafeAreaProviderManagerKt.handleOnInsetsChange(p0, p1, p2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext reactContext, SafeAreaProvider view) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(view, "view");
        super.addEventEmitters(reactContext, view);
        view.setOnInsetsChangeHandler(AnonymousClass1.INSTANCE);
    }
}
