package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.events.Event;
import com.facebook.soloader.SoLoader;
import com.swmansion.gesturehandler.ReactContextExtensionsKt;
import com.swmansion.gesturehandler.ReanimatedEventDispatcher;
import com.swmansion.gesturehandler.core.FlingGestureHandler;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.core.LongPressGestureHandler;
import com.swmansion.gesturehandler.core.ManualGestureHandler;
import com.swmansion.gesturehandler.core.NativeViewGestureHandler;
import com.swmansion.gesturehandler.core.OnTouchEventListener;
import com.swmansion.gesturehandler.core.PanGestureHandler;
import com.swmansion.gesturehandler.core.PinchGestureHandler;
import com.swmansion.gesturehandler.core.RotationGestureHandler;
import com.swmansion.gesturehandler.core.TapGestureHandler;
import com.swmansion.gesturehandler.react.RNGestureHandlerEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RNGestureHandlerModule.kt */
/* JADX INFO: loaded from: classes7.dex */
@ReactModule(name = RNGestureHandlerModule.MODULE_NAME)
@Metadata(bv = {}, d1 = {"\u0000¡\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e*\u00019\b\u0007\u0018\u0000 Q2\u00020\u00012\u00020\u0002:\nQRSTUVWXYZB\u0011\u0012\b\u0010N\u001a\u0004\u0018\u00010M¢\u0006\u0004\bO\u0010PJ\u0011\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0082 J\u0012\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002J.\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000e\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0002J'\u0010\u0010\u001a\u00020\u0005\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0006\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J7\u0010\u0014\u001a\u00020\u0005\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u0016\u001a\u00020\u0005\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0006\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0016\u0010\u0011J'\u0010\u0019\u001a\u00020\u0005\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u00172\u0006\u0010\u0018\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u001bH\u0002J'\u0010\u001d\u001a\u00020\u0005\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u00172\u0006\u0010\u0018\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u001d\u0010\u001aJ\u0018\u0010\"\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 H\u0002J\b\u0010#\u001a\u00020\u001eH\u0016J0\u0010(\u001a\u00020\u0005\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u00072\u0006\u0010'\u001a\u00020&H\u0007J \u0010*\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\u0007H\u0007J(\u0010+\u001a\u00020\u0005\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0006\u0010%\u001a\u00020\u00072\u0006\u0010'\u001a\u00020&H\u0007J\u0010\u0010,\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u0007H\u0007J\u0018\u0010/\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010.\u001a\u00020-H\u0007J\b\u00100\u001a\u00020\u0005H\u0007J\u0018\u00101\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\b\u00102\u001a\u00020-H\u0007J\u0014\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u000203H\u0016J\b\u00105\u001a\u00020\u0005H\u0016J\u000e\u00107\u001a\u00020\u00052\u0006\u00106\u001a\u00020\tJ\u000e\u00108\u001a\u00020\u00052\u0006\u00106\u001a\u00020\tR\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u001e\u0010=\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u0017\u0010@\u001a\u00020?8\u0006¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u0014\u0010E\u001a\u00020D8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010FR\u001a\u0010H\u001a\b\u0012\u0004\u0012\u00020\t0G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010L¨\u0006["}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "", "", "jsiPtr", "", "decorateRuntime", "", "viewTag", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootHelper;", "findRootHelperForViewAncestor", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "T", "handler", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "findFactoryForHandler", "onHandlerUpdate", "(Lcom/swmansion/gesturehandler/core/GestureHandler;)V", "newState", "oldState", "onStateChange", "(Lcom/swmansion/gesturehandler/core/GestureHandler;II)V", "onTouchEvent", "Lcom/facebook/react/uimanager/events/Event;", NotificationCompat.CATEGORY_EVENT, "sendEventForReanimated", "(Lcom/facebook/react/uimanager/events/Event;)V", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerEvent;", "sendEventForNativeAnimatedEvent", "sendEventForDirectEvent", "", "eventName", "Lcom/facebook/react/bridge/WritableMap;", "data", "sendEventForDeviceEvent", "getName", "handlerName", "handlerTag", "Lcom/facebook/react/bridge/ReadableMap;", "config", "createGestureHandler", "actionType", "attachGestureHandler", "updateGestureHandler", "dropGestureHandler", "", "blockNativeResponder", "handleSetJSResponder", "handleClearJSResponder", "setGestureHandlerState", "install", "", "getConstants", "onCatalystInstanceDestroy", "root", "registerRootHelper", "unregisterRootHelper", "com/swmansion/gesturehandler/react/RNGestureHandlerModule$eventListener$1", "eventListener", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$eventListener$1;", "", "handlerFactories", "[Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerRegistry;", "registry", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerRegistry;", "getRegistry", "()Lcom/swmansion/gesturehandler/react/RNGestureHandlerRegistry;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerInteractionManager;", "interactionManager", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerInteractionManager;", "", "roots", "Ljava/util/List;", "Lcom/swmansion/gesturehandler/ReanimatedEventDispatcher;", "reanimatedEventDispatcher", "Lcom/swmansion/gesturehandler/ReanimatedEventDispatcher;", "Lcom/facebook/react/bridge/ReactApplicationContext;", "reactContext", "<init>", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "Companion", "FlingGestureHandlerFactory", "HandlerFactory", "LongPressGestureHandlerFactory", "ManualGestureHandlerFactory", "NativeViewGestureHandlerFactory", "PanGestureHandlerFactory", "PinchGestureHandlerFactory", "RotationGestureHandlerFactory", "TapGestureHandlerFactory", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0})
public final class RNGestureHandlerModule extends ReactContextBaseJavaModule {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_DIRECTION = "direction";
    private static final String KEY_ENABLED = "enabled";
    private static final String KEY_HIT_SLOP = "hitSlop";
    private static final String KEY_HIT_SLOP_BOTTOM = "bottom";
    private static final String KEY_HIT_SLOP_HEIGHT = "height";
    private static final String KEY_HIT_SLOP_HORIZONTAL = "horizontal";
    private static final String KEY_HIT_SLOP_LEFT = "left";
    private static final String KEY_HIT_SLOP_RIGHT = "right";
    private static final String KEY_HIT_SLOP_TOP = "top";
    private static final String KEY_HIT_SLOP_VERTICAL = "vertical";
    private static final String KEY_HIT_SLOP_WIDTH = "width";
    private static final String KEY_LONG_PRESS_MAX_DIST = "maxDist";
    private static final String KEY_LONG_PRESS_MIN_DURATION_MS = "minDurationMs";
    private static final String KEY_MANUAL_ACTIVATION = "manualActivation";
    private static final String KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION = "disallowInterruption";
    private static final String KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START = "shouldActivateOnStart";
    private static final String KEY_NEEDS_POINTER_DATA = "needsPointerData";
    private static final String KEY_NUMBER_OF_POINTERS = "numberOfPointers";
    private static final String KEY_PAN_ACTIVATE_AFTER_LONG_PRESS = "activateAfterLongPress";
    private static final String KEY_PAN_ACTIVE_OFFSET_X_END = "activeOffsetXEnd";
    private static final String KEY_PAN_ACTIVE_OFFSET_X_START = "activeOffsetXStart";
    private static final String KEY_PAN_ACTIVE_OFFSET_Y_END = "activeOffsetYEnd";
    private static final String KEY_PAN_ACTIVE_OFFSET_Y_START = "activeOffsetYStart";
    private static final String KEY_PAN_AVG_TOUCHES = "avgTouches";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_X_END = "failOffsetXEnd";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_X_START = "failOffsetXStart";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_Y_END = "failOffsetYEnd";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_Y_START = "failOffsetYStart";
    private static final String KEY_PAN_MAX_POINTERS = "maxPointers";
    private static final String KEY_PAN_MIN_DIST = "minDist";
    private static final String KEY_PAN_MIN_POINTERS = "minPointers";
    private static final String KEY_PAN_MIN_VELOCITY = "minVelocity";
    private static final String KEY_PAN_MIN_VELOCITY_X = "minVelocityX";
    private static final String KEY_PAN_MIN_VELOCITY_Y = "minVelocityY";
    private static final String KEY_SHOULD_CANCEL_WHEN_OUTSIDE = "shouldCancelWhenOutside";
    private static final String KEY_TAP_MAX_DELAY_MS = "maxDelayMs";
    private static final String KEY_TAP_MAX_DELTA_X = "maxDeltaX";
    private static final String KEY_TAP_MAX_DELTA_Y = "maxDeltaY";
    private static final String KEY_TAP_MAX_DIST = "maxDist";
    private static final String KEY_TAP_MAX_DURATION_MS = "maxDurationMs";
    private static final String KEY_TAP_MIN_POINTERS = "minPointers";
    private static final String KEY_TAP_NUMBER_OF_TAPS = "numberOfTaps";
    public static final String MODULE_NAME = "RNGestureHandlerModule";
    private final RNGestureHandlerModule$eventListener$1 eventListener;
    private final HandlerFactory<?>[] handlerFactories;
    private final RNGestureHandlerInteractionManager interactionManager;
    private final ReanimatedEventDispatcher reanimatedEventDispatcher;
    private final RNGestureHandlerRegistry registry;
    private final List<RNGestureHandlerRootHelper> roots;

    private final native void decorateRuntime(long jsiPtr);

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public final void handleClearJSResponder() {
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.swmansion.gesturehandler.react.RNGestureHandlerModule$eventListener$1] */
    public RNGestureHandlerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.eventListener = new OnTouchEventListener() { // from class: com.swmansion.gesturehandler.react.RNGestureHandlerModule$eventListener$1
            @Override // com.swmansion.gesturehandler.core.OnTouchEventListener
            public <T extends GestureHandler<T>> void onHandlerUpdate(T handler, MotionEvent event) throws Exception {
                Intrinsics.checkNotNullParameter(handler, "handler");
                Intrinsics.checkNotNullParameter(event, "event");
                this.this$0.onHandlerUpdate(handler);
            }

            @Override // com.swmansion.gesturehandler.core.OnTouchEventListener
            public <T extends GestureHandler<T>> void onStateChange(T handler, int newState, int oldState) throws Exception {
                Intrinsics.checkNotNullParameter(handler, "handler");
                this.this$0.onStateChange(handler, newState, oldState);
            }

            @Override // com.swmansion.gesturehandler.core.OnTouchEventListener
            public <T extends GestureHandler<T>> void onTouchEvent(T handler) throws Exception {
                Intrinsics.checkNotNullParameter(handler, "handler");
                this.this$0.onTouchEvent(handler);
            }
        };
        this.handlerFactories = new HandlerFactory[]{new NativeViewGestureHandlerFactory(), new TapGestureHandlerFactory(), new LongPressGestureHandlerFactory(), new PanGestureHandlerFactory(), new PinchGestureHandlerFactory(), new RotationGestureHandlerFactory(), new FlingGestureHandlerFactory(), new ManualGestureHandlerFactory()};
        this.registry = new RNGestureHandlerRegistry();
        this.interactionManager = new RNGestureHandlerInteractionManager();
        this.roots = new ArrayList();
        this.reanimatedEventDispatcher = new ReanimatedEventDispatcher();
    }

    /* JADX INFO: compiled from: RNGestureHandlerModule.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\"\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0012J\u0017\u0010\u0013\u001a\u00028\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&¢\u0006\u0002\u0010\u0016J\u001d\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0016¢\u0006\u0002\u0010\u001aR\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "T", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerEventDataExtractor;", "()V", "name", "", "getName", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "configure", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/swmansion/gesturehandler/core/GestureHandler;Lcom/facebook/react/bridge/ReadableMap;)V", "create", "context", "Landroid/content/Context;", "(Landroid/content/Context;)Lcom/swmansion/gesturehandler/core/GestureHandler;", "extractEventData", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "(Lcom/swmansion/gesturehandler/core/GestureHandler;Lcom/facebook/react/bridge/WritableMap;)V", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static abstract class HandlerFactory<T extends GestureHandler<T>> implements RNGestureHandlerEventDataExtractor<T> {
        public abstract T create(Context context);

        public abstract String getName();

        public abstract Class<T> getType();

        public void configure(T handler, ReadableMap config) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(config, "config");
            handler.resetConfig();
            if (config.hasKey(RNGestureHandlerModule.KEY_SHOULD_CANCEL_WHEN_OUTSIDE)) {
                handler.setShouldCancelWhenOutside(config.getBoolean(RNGestureHandlerModule.KEY_SHOULD_CANCEL_WHEN_OUTSIDE));
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_ENABLED)) {
                handler.setEnabled(config.getBoolean(RNGestureHandlerModule.KEY_ENABLED));
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP)) {
                RNGestureHandlerModule.INSTANCE.handleHitSlopProperty(handler, config);
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_NEEDS_POINTER_DATA)) {
                handler.setNeedsPointerData(config.getBoolean(RNGestureHandlerModule.KEY_NEEDS_POINTER_DATA));
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_MANUAL_ACTIVATION)) {
                handler.setManualActivation(config.getBoolean(RNGestureHandlerModule.KEY_MANUAL_ACTIVATION));
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(T handler, WritableMap eventData) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(eventData, "eventData");
            eventData.putDouble(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS, handler.getNumberOfPointers());
        }
    }

    /* JADX INFO: compiled from: RNGestureHandlerModule.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$NativeViewGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/NativeViewGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "configure", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "create", "context", "Landroid/content/Context;", "extractEventData", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class NativeViewGestureHandlerFactory extends HandlerFactory<NativeViewGestureHandler> {
        private final Class<NativeViewGestureHandler> type = NativeViewGestureHandler.class;
        private final String name = "NativeViewGestureHandler";

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<NativeViewGestureHandler> getType() {
            return this.type;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return this.name;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public NativeViewGestureHandler create(Context context) {
            return new NativeViewGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(NativeViewGestureHandler handler, ReadableMap config) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(config, "config");
            super.configure(handler, config);
            if (config.hasKey(RNGestureHandlerModule.KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START)) {
                handler.setShouldActivateOnStart(config.getBoolean(RNGestureHandlerModule.KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START));
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION)) {
                handler.setDisallowInterruption(config.getBoolean(RNGestureHandlerModule.KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION));
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(NativeViewGestureHandler handler, WritableMap eventData) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(eventData, "eventData");
            super.extractEventData(handler, eventData);
            eventData.putBoolean("pointerInside", handler.getIsWithinBounds());
        }
    }

    /* JADX INFO: compiled from: RNGestureHandlerModule.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$TapGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/TapGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "configure", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "create", "context", "Landroid/content/Context;", "extractEventData", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class TapGestureHandlerFactory extends HandlerFactory<TapGestureHandler> {
        private final Class<TapGestureHandler> type = TapGestureHandler.class;
        private final String name = "TapGestureHandler";

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<TapGestureHandler> getType() {
            return this.type;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return this.name;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public TapGestureHandler create(Context context) {
            return new TapGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(TapGestureHandler handler, ReadableMap config) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(config, "config");
            super.configure(handler, config);
            if (config.hasKey(RNGestureHandlerModule.KEY_TAP_NUMBER_OF_TAPS)) {
                handler.setNumberOfTaps(config.getInt(RNGestureHandlerModule.KEY_TAP_NUMBER_OF_TAPS));
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DURATION_MS)) {
                handler.setMaxDurationMs(config.getInt(RNGestureHandlerModule.KEY_TAP_MAX_DURATION_MS));
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELAY_MS)) {
                handler.setMaxDelayMs(config.getInt(RNGestureHandlerModule.KEY_TAP_MAX_DELAY_MS));
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_X)) {
                handler.setMaxDx(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_X)));
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_Y)) {
                handler.setMaxDy(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_Y)));
            }
            if (config.hasKey("maxDist")) {
                handler.setMaxDist(PixelUtil.toPixelFromDIP(config.getDouble("maxDist")));
            }
            if (config.hasKey("minPointers")) {
                handler.setMinNumberOfPointers(config.getInt("minPointers"));
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(TapGestureHandler handler, WritableMap eventData) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(eventData, "eventData");
            super.extractEventData(handler, eventData);
            eventData.putDouble("x", PixelUtil.toDIPFromPixel(handler.getLastRelativePositionX()));
            eventData.putDouble("y", PixelUtil.toDIPFromPixel(handler.getLastRelativePositionY()));
            eventData.putDouble("absoluteX", PixelUtil.toDIPFromPixel(handler.getLastPositionInWindowX()));
            eventData.putDouble("absoluteY", PixelUtil.toDIPFromPixel(handler.getLastPositionInWindowY()));
        }
    }

    /* JADX INFO: compiled from: RNGestureHandlerModule.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$LongPressGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/LongPressGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "configure", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "create", "context", "Landroid/content/Context;", "extractEventData", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class LongPressGestureHandlerFactory extends HandlerFactory<LongPressGestureHandler> {
        private final Class<LongPressGestureHandler> type = LongPressGestureHandler.class;
        private final String name = "LongPressGestureHandler";

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<LongPressGestureHandler> getType() {
            return this.type;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return this.name;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public LongPressGestureHandler create(Context context) {
            Intrinsics.checkNotNull(context);
            return new LongPressGestureHandler(context);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(LongPressGestureHandler handler, ReadableMap config) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(config, "config");
            super.configure(handler, config);
            if (config.hasKey(RNGestureHandlerModule.KEY_LONG_PRESS_MIN_DURATION_MS)) {
                handler.setMinDurationMs(config.getInt(RNGestureHandlerModule.KEY_LONG_PRESS_MIN_DURATION_MS));
            }
            if (config.hasKey("maxDist")) {
                handler.setMaxDist(PixelUtil.toPixelFromDIP(config.getDouble("maxDist")));
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(LongPressGestureHandler handler, WritableMap eventData) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(eventData, "eventData");
            super.extractEventData(handler, eventData);
            eventData.putDouble("x", PixelUtil.toDIPFromPixel(handler.getLastRelativePositionX()));
            eventData.putDouble("y", PixelUtil.toDIPFromPixel(handler.getLastRelativePositionY()));
            eventData.putDouble("absoluteX", PixelUtil.toDIPFromPixel(handler.getLastPositionInWindowX()));
            eventData.putDouble("absoluteY", PixelUtil.toDIPFromPixel(handler.getLastPositionInWindowY()));
            eventData.putInt("duration", handler.getDuration());
        }
    }

    /* JADX INFO: compiled from: RNGestureHandlerModule.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$PanGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/PanGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "configure", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "create", "context", "Landroid/content/Context;", "extractEventData", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class PanGestureHandlerFactory extends HandlerFactory<PanGestureHandler> {
        private final Class<PanGestureHandler> type = PanGestureHandler.class;
        private final String name = "PanGestureHandler";

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<PanGestureHandler> getType() {
            return this.type;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return this.name;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public PanGestureHandler create(Context context) {
            return new PanGestureHandler(context);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(PanGestureHandler handler, ReadableMap config) {
            boolean z;
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(config, "config");
            super.configure(handler, config);
            boolean z2 = true;
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_START)) {
                handler.setActiveOffsetXStart(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_START)));
                z = true;
            } else {
                z = false;
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_END)) {
                handler.setActiveOffsetXEnd(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_END)));
                z = true;
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_START)) {
                handler.setFailOffsetXStart(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_START)));
                z = true;
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_END)) {
                handler.setFailOffsetXEnd(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_END)));
                z = true;
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_START)) {
                handler.setActiveOffsetYStart(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_START)));
                z = true;
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_END)) {
                handler.setActiveOffsetYEnd(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_END)));
                z = true;
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_START)) {
                handler.setFailOffsetYStart(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_START)));
                z = true;
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_END)) {
                handler.setFailOffsetYEnd(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_END)));
                z = true;
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY)) {
                handler.setMinVelocity(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY)));
                z = true;
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_X)) {
                handler.setMinVelocityX(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_X)));
                z = true;
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_Y)) {
                handler.setMinVelocityY(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_Y)));
            } else {
                z2 = z;
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_DIST)) {
                handler.setMinDist(PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_DIST)));
            } else if (z2) {
                handler.setMinDist(Float.MAX_VALUE);
            }
            if (config.hasKey("minPointers")) {
                handler.setMinPointers(config.getInt("minPointers"));
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_MAX_POINTERS)) {
                handler.setMaxPointers(config.getInt(RNGestureHandlerModule.KEY_PAN_MAX_POINTERS));
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_AVG_TOUCHES)) {
                handler.setAverageTouches(config.getBoolean(RNGestureHandlerModule.KEY_PAN_AVG_TOUCHES));
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVATE_AFTER_LONG_PRESS)) {
                handler.setActivateAfterLongPress(config.getInt(RNGestureHandlerModule.KEY_PAN_ACTIVATE_AFTER_LONG_PRESS));
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(PanGestureHandler handler, WritableMap eventData) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(eventData, "eventData");
            super.extractEventData(handler, eventData);
            eventData.putDouble("x", PixelUtil.toDIPFromPixel(handler.getLastRelativePositionX()));
            eventData.putDouble("y", PixelUtil.toDIPFromPixel(handler.getLastRelativePositionY()));
            eventData.putDouble("absoluteX", PixelUtil.toDIPFromPixel(handler.getLastPositionInWindowX()));
            eventData.putDouble("absoluteY", PixelUtil.toDIPFromPixel(handler.getLastPositionInWindowY()));
            eventData.putDouble("translationX", PixelUtil.toDIPFromPixel(handler.getTranslationX()));
            eventData.putDouble("translationY", PixelUtil.toDIPFromPixel(handler.getTranslationY()));
            eventData.putDouble("velocityX", PixelUtil.toDIPFromPixel(handler.getVelocityX()));
            eventData.putDouble("velocityY", PixelUtil.toDIPFromPixel(handler.getVelocityY()));
        }
    }

    /* JADX INFO: compiled from: RNGestureHandlerModule.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$PinchGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/PinchGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "create", "context", "Landroid/content/Context;", "extractEventData", "", "handler", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class PinchGestureHandlerFactory extends HandlerFactory<PinchGestureHandler> {
        private final Class<PinchGestureHandler> type = PinchGestureHandler.class;
        private final String name = "PinchGestureHandler";

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<PinchGestureHandler> getType() {
            return this.type;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return this.name;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public PinchGestureHandler create(Context context) {
            return new PinchGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(PinchGestureHandler handler, WritableMap eventData) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(eventData, "eventData");
            super.extractEventData(handler, eventData);
            eventData.putDouble("scale", handler.getScale());
            eventData.putDouble("focalX", PixelUtil.toDIPFromPixel(handler.getFocalPointX()));
            eventData.putDouble("focalY", PixelUtil.toDIPFromPixel(handler.getFocalPointY()));
            eventData.putDouble("velocity", handler.getVelocity());
        }
    }

    /* JADX INFO: compiled from: RNGestureHandlerModule.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$FlingGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/FlingGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "configure", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "create", "context", "Landroid/content/Context;", "extractEventData", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class FlingGestureHandlerFactory extends HandlerFactory<FlingGestureHandler> {
        private final Class<FlingGestureHandler> type = FlingGestureHandler.class;
        private final String name = "FlingGestureHandler";

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<FlingGestureHandler> getType() {
            return this.type;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return this.name;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public FlingGestureHandler create(Context context) {
            return new FlingGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(FlingGestureHandler handler, ReadableMap config) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(config, "config");
            super.configure(handler, config);
            if (config.hasKey(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS)) {
                handler.setNumberOfPointersRequired(config.getInt(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS));
            }
            if (config.hasKey(RNGestureHandlerModule.KEY_DIRECTION)) {
                handler.setDirection(config.getInt(RNGestureHandlerModule.KEY_DIRECTION));
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(FlingGestureHandler handler, WritableMap eventData) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(eventData, "eventData");
            super.extractEventData(handler, eventData);
            eventData.putDouble("x", PixelUtil.toDIPFromPixel(handler.getLastRelativePositionX()));
            eventData.putDouble("y", PixelUtil.toDIPFromPixel(handler.getLastRelativePositionY()));
            eventData.putDouble("absoluteX", PixelUtil.toDIPFromPixel(handler.getLastPositionInWindowX()));
            eventData.putDouble("absoluteY", PixelUtil.toDIPFromPixel(handler.getLastPositionInWindowY()));
        }
    }

    /* JADX INFO: compiled from: RNGestureHandlerModule.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$RotationGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/RotationGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "create", "context", "Landroid/content/Context;", "extractEventData", "", "handler", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class RotationGestureHandlerFactory extends HandlerFactory<RotationGestureHandler> {
        private final Class<RotationGestureHandler> type = RotationGestureHandler.class;
        private final String name = "RotationGestureHandler";

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<RotationGestureHandler> getType() {
            return this.type;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return this.name;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public RotationGestureHandler create(Context context) {
            return new RotationGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory, com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(RotationGestureHandler handler, WritableMap eventData) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(eventData, "eventData");
            super.extractEventData(handler, eventData);
            eventData.putDouble("rotation", handler.getRotation());
            eventData.putDouble("anchorX", PixelUtil.toDIPFromPixel(handler.getAnchorX()));
            eventData.putDouble("anchorY", PixelUtil.toDIPFromPixel(handler.getAnchorY()));
            eventData.putDouble("velocity", handler.getVelocity());
        }
    }

    /* JADX INFO: compiled from: RNGestureHandlerModule.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$ManualGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/ManualGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "create", "context", "Landroid/content/Context;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class ManualGestureHandlerFactory extends HandlerFactory<ManualGestureHandler> {
        private final Class<ManualGestureHandler> type = ManualGestureHandler.class;
        private final String name = "ManualGestureHandler";

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<ManualGestureHandler> getType() {
            return this.type;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return this.name;
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public ManualGestureHandler create(Context context) {
            return new ManualGestureHandler();
        }
    }

    public final RNGestureHandlerRegistry getRegistry() {
        return this.registry;
    }

    @ReactMethod
    public final <T extends GestureHandler<T>> void createGestureHandler(String handlerName, int handlerTag, ReadableMap config) {
        Intrinsics.checkNotNullParameter(handlerName, "handlerName");
        Intrinsics.checkNotNullParameter(config, "config");
        HandlerFactory<?>[] handlerFactoryArr = this.handlerFactories;
        int length = handlerFactoryArr.length;
        int i = 0;
        while (i < length) {
            HandlerFactory<?> handlerFactory = handlerFactoryArr[i];
            i++;
            if (Intrinsics.areEqual(handlerFactory.getName(), handlerName)) {
                GestureHandler<?> gestureHandlerCreate = handlerFactory.create(getReactApplicationContext());
                gestureHandlerCreate.setTag(handlerTag);
                gestureHandlerCreate.setOnTouchEventListener(this.eventListener);
                this.registry.registerHandler(gestureHandlerCreate);
                this.interactionManager.configureInteractions(gestureHandlerCreate, config);
                handlerFactory.configure(gestureHandlerCreate, config);
                return;
            }
        }
        throw new JSApplicationIllegalArgumentException(Intrinsics.stringPlus("Invalid handler name ", handlerName));
    }

    @ReactMethod
    public final void attachGestureHandler(int handlerTag, int viewTag, int actionType) {
        if (this.registry.attachHandlerToView(handlerTag, viewTag, actionType)) {
            return;
        }
        throw new JSApplicationIllegalArgumentException("Handler with tag " + handlerTag + " does not exists");
    }

    @ReactMethod
    public final <T extends GestureHandler<T>> void updateGestureHandler(int handlerTag, ReadableMap config) {
        HandlerFactory<T> handlerFactoryFindFactoryForHandler;
        Intrinsics.checkNotNullParameter(config, "config");
        GestureHandler<?> handler = this.registry.getHandler(handlerTag);
        if (handler == null || (handlerFactoryFindFactoryForHandler = findFactoryForHandler(handler)) == null) {
            return;
        }
        this.interactionManager.dropRelationsForHandlerWithTag(handlerTag);
        this.interactionManager.configureInteractions(handler, config);
        handlerFactoryFindFactoryForHandler.configure(handler, config);
    }

    @ReactMethod
    public final void dropGestureHandler(int handlerTag) {
        this.interactionManager.dropRelationsForHandlerWithTag(handlerTag);
        this.registry.dropHandler(handlerTag);
    }

    @ReactMethod
    public final void handleSetJSResponder(int viewTag, boolean blockNativeResponder) {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelperFindRootHelperForViewAncestor = findRootHelperForViewAncestor(viewTag);
        if (rNGestureHandlerRootHelperFindRootHelperForViewAncestor == null) {
            return;
        }
        rNGestureHandlerRootHelperFindRootHelperForViewAncestor.handleSetJSResponder(viewTag, blockNativeResponder);
    }

    public void setGestureHandlerState(int handlerTag, int newState) {
        GestureHandler<?> handler = this.registry.getHandler(handlerTag);
        if (handler == null) {
            return;
        }
        if (newState == 1) {
            handler.fail();
            return;
        }
        if (newState == 2) {
            handler.begin();
            return;
        }
        if (newState == 3) {
            handler.cancel();
        } else if (newState == 4) {
            handler.activate(true);
        } else {
            if (newState != 5) {
                return;
            }
            handler.end();
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public final boolean install() {
        try {
            SoLoader.loadLibrary("gesturehandler");
            decorateRuntime(getReactApplicationContext().getJavaScriptContextHolder().get());
            return true;
        } catch (Exception unused) {
            Log.w("[RNGestureHandler]", "Could not install JSI bindings.");
            return false;
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return MapsKt__MapsKt.mapOf(TuplesKt.to("State", MapsKt__MapsKt.mapOf(TuplesKt.to("UNDETERMINED", 0), TuplesKt.to("BEGAN", 2), TuplesKt.to("ACTIVE", 4), TuplesKt.to("CANCELLED", 3), TuplesKt.to("FAILED", 1), TuplesKt.to("END", 5))), TuplesKt.to("Direction", MapsKt__MapsKt.mapOf(TuplesKt.to("RIGHT", 1), TuplesKt.to("LEFT", 2), TuplesKt.to("UP", 4), TuplesKt.to("DOWN", 8))));
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.registry.dropAllHandlers();
        this.interactionManager.reset();
        synchronized (this.roots) {
            while (!this.roots.isEmpty()) {
                int size = this.roots.size();
                this.roots.get(0).tearDown();
                if (this.roots.size() >= size) {
                    throw new IllegalStateException("Expected root helper to get unregistered while tearing down");
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        super.onCatalystInstanceDestroy();
    }

    public final void registerRootHelper(RNGestureHandlerRootHelper root) {
        Intrinsics.checkNotNullParameter(root, "root");
        synchronized (this.roots) {
            if (this.roots.contains(root)) {
                throw new IllegalStateException("Root helper" + root + " already registered");
            }
            this.roots.add(root);
        }
    }

    public final void unregisterRootHelper(RNGestureHandlerRootHelper root) {
        Intrinsics.checkNotNullParameter(root, "root");
        synchronized (this.roots) {
            this.roots.remove(root);
        }
    }

    private final RNGestureHandlerRootHelper findRootHelperForViewAncestor(int viewTag) {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "reactApplicationContext");
        int iResolveRootTagFromReactTag = ExtensionsKt.getUIManager(reactApplicationContext).resolveRootTagFromReactTag(viewTag);
        Object obj = null;
        if (iResolveRootTagFromReactTag < 1) {
            return null;
        }
        synchronized (this.roots) {
            Iterator<T> it = this.roots.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                RNGestureHandlerRootHelper rNGestureHandlerRootHelper2 = (RNGestureHandlerRootHelper) next;
                if ((rNGestureHandlerRootHelper2.getRootView() instanceof ReactRootView) && ((ReactRootView) rNGestureHandlerRootHelper2.getRootView()).getRootViewTag() == iResolveRootTagFromReactTag) {
                    obj = next;
                    break;
                }
            }
            rNGestureHandlerRootHelper = (RNGestureHandlerRootHelper) obj;
        }
        return rNGestureHandlerRootHelper;
    }

    private final <T extends GestureHandler<T>> HandlerFactory<T> findFactoryForHandler(GestureHandler<T> handler) {
        Object[] objArr = this.handlerFactories;
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            HandlerFactory<T> handlerFactory = (HandlerFactory<T>) objArr[i];
            i++;
            if (Intrinsics.areEqual(handlerFactory.getType(), handler.getClass())) {
                return handlerFactory;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T extends GestureHandler<T>> void onHandlerUpdate(T handler) throws Exception {
        if (handler.getTag() >= 0 && handler.getState() == 4) {
            HandlerFactory<T> handlerFactoryFindFactoryForHandler = findFactoryForHandler(handler);
            if (handler.getActionType() == 1) {
                sendEventForReanimated(RNGestureHandlerEvent.Companion.obtain$default(RNGestureHandlerEvent.INSTANCE, handler, handlerFactoryFindFactoryForHandler, false, 4, null));
                return;
            }
            if (handler.getActionType() == 2) {
                sendEventForNativeAnimatedEvent(RNGestureHandlerEvent.INSTANCE.obtain(handler, handlerFactoryFindFactoryForHandler, true));
            } else if (handler.getActionType() == 3) {
                sendEventForDirectEvent(RNGestureHandlerEvent.Companion.obtain$default(RNGestureHandlerEvent.INSTANCE, handler, handlerFactoryFindFactoryForHandler, false, 4, null));
            } else if (handler.getActionType() == 4) {
                sendEventForDeviceEvent("onGestureHandlerEvent", RNGestureHandlerEvent.INSTANCE.createEventData(handler, handlerFactoryFindFactoryForHandler));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T extends GestureHandler<T>> void onStateChange(T handler, int newState, int oldState) throws Exception {
        if (handler.getTag() < 0) {
            return;
        }
        HandlerFactory<T> handlerFactoryFindFactoryForHandler = findFactoryForHandler(handler);
        if (handler.getActionType() == 1) {
            sendEventForReanimated(RNGestureHandlerStateChangeEvent.INSTANCE.obtain(handler, newState, oldState, handlerFactoryFindFactoryForHandler));
            return;
        }
        if (handler.getActionType() == 2 || handler.getActionType() == 3) {
            sendEventForDirectEvent(RNGestureHandlerStateChangeEvent.INSTANCE.obtain(handler, newState, oldState, handlerFactoryFindFactoryForHandler));
        } else if (handler.getActionType() == 4) {
            sendEventForDeviceEvent("onGestureHandlerStateChange", RNGestureHandlerStateChangeEvent.INSTANCE.createEventData(handler, handlerFactoryFindFactoryForHandler, newState, oldState));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T extends GestureHandler<T>> void onTouchEvent(T handler) throws Exception {
        if (handler.getTag() < 0) {
            return;
        }
        if (handler.getState() == 2 || handler.getState() == 4 || handler.getState() == 0 || handler.getView() != null) {
            if (handler.getActionType() == 1) {
                sendEventForReanimated(RNGestureHandlerTouchEvent.INSTANCE.obtain(handler));
            } else if (handler.getActionType() == 4) {
                sendEventForDeviceEvent("onGestureHandlerEvent", RNGestureHandlerTouchEvent.INSTANCE.createEventData(handler));
            }
        }
    }

    private final <T extends Event<T>> void sendEventForReanimated(T event) throws Exception {
        sendEventForDirectEvent(event);
    }

    private final void sendEventForNativeAnimatedEvent(RNGestureHandlerEvent event) throws Exception {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "reactApplicationContext");
        ReactContextExtensionsKt.dispatchEvent(reactApplicationContext, event);
    }

    private final <T extends Event<T>> void sendEventForDirectEvent(T event) throws Exception {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "reactApplicationContext");
        ReactContextExtensionsKt.dispatchEvent(reactApplicationContext, event);
    }

    private final void sendEventForDeviceEvent(String eventName, WritableMap data) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "reactApplicationContext");
        ExtensionsKt.getDeviceEventEmitter(reactApplicationContext).emit(eventName, data);
    }

    /* JADX INFO: compiled from: RNGestureHandlerModule.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b+\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010/\u001a\u0002002\n\u00101\u001a\u0006\u0012\u0002\b\u0003022\u0006\u00103\u001a\u000204H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$Companion;", "", "()V", "KEY_DIRECTION", "", "KEY_ENABLED", "KEY_HIT_SLOP", "KEY_HIT_SLOP_BOTTOM", "KEY_HIT_SLOP_HEIGHT", "KEY_HIT_SLOP_HORIZONTAL", "KEY_HIT_SLOP_LEFT", "KEY_HIT_SLOP_RIGHT", "KEY_HIT_SLOP_TOP", "KEY_HIT_SLOP_VERTICAL", "KEY_HIT_SLOP_WIDTH", "KEY_LONG_PRESS_MAX_DIST", "KEY_LONG_PRESS_MIN_DURATION_MS", "KEY_MANUAL_ACTIVATION", "KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION", "KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START", "KEY_NEEDS_POINTER_DATA", "KEY_NUMBER_OF_POINTERS", "KEY_PAN_ACTIVATE_AFTER_LONG_PRESS", "KEY_PAN_ACTIVE_OFFSET_X_END", "KEY_PAN_ACTIVE_OFFSET_X_START", "KEY_PAN_ACTIVE_OFFSET_Y_END", "KEY_PAN_ACTIVE_OFFSET_Y_START", "KEY_PAN_AVG_TOUCHES", "KEY_PAN_FAIL_OFFSET_RANGE_X_END", "KEY_PAN_FAIL_OFFSET_RANGE_X_START", "KEY_PAN_FAIL_OFFSET_RANGE_Y_END", "KEY_PAN_FAIL_OFFSET_RANGE_Y_START", "KEY_PAN_MAX_POINTERS", "KEY_PAN_MIN_DIST", "KEY_PAN_MIN_POINTERS", "KEY_PAN_MIN_VELOCITY", "KEY_PAN_MIN_VELOCITY_X", "KEY_PAN_MIN_VELOCITY_Y", "KEY_SHOULD_CANCEL_WHEN_OUTSIDE", "KEY_TAP_MAX_DELAY_MS", "KEY_TAP_MAX_DELTA_X", "KEY_TAP_MAX_DELTA_Y", "KEY_TAP_MAX_DIST", "KEY_TAP_MAX_DURATION_MS", "KEY_TAP_MIN_POINTERS", "KEY_TAP_NUMBER_OF_TAPS", "MODULE_NAME", "handleHitSlopProperty", "", "handler", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "config", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void handleHitSlopProperty(GestureHandler<?> handler, ReadableMap config) {
            if (config.getType(RNGestureHandlerModule.KEY_HIT_SLOP) == ReadableType.Number) {
                float pixelFromDIP = PixelUtil.toPixelFromDIP(config.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP));
                handler.setHitSlop(pixelFromDIP, pixelFromDIP, pixelFromDIP, pixelFromDIP, Float.NaN, Float.NaN);
                return;
            }
            ReadableMap map = config.getMap(RNGestureHandlerModule.KEY_HIT_SLOP);
            Intrinsics.checkNotNull(map);
            Intrinsics.checkNotNullExpressionValue(map, "config.getMap(KEY_HIT_SLOP)!!");
            float pixelFromDIP2 = map.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP_HORIZONTAL) ? PixelUtil.toPixelFromDIP(map.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP_HORIZONTAL)) : Float.NaN;
            float pixelFromDIP3 = pixelFromDIP2;
            float pixelFromDIP4 = map.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP_VERTICAL) ? PixelUtil.toPixelFromDIP(map.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP_VERTICAL)) : Float.NaN;
            float pixelFromDIP5 = pixelFromDIP4;
            if (map.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP_LEFT)) {
                pixelFromDIP2 = PixelUtil.toPixelFromDIP(map.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP_LEFT));
            }
            float f = pixelFromDIP2;
            if (map.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP_TOP)) {
                pixelFromDIP4 = PixelUtil.toPixelFromDIP(map.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP_TOP));
            }
            float f2 = pixelFromDIP4;
            if (map.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP_RIGHT)) {
                pixelFromDIP3 = PixelUtil.toPixelFromDIP(map.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP_RIGHT));
            }
            float f3 = pixelFromDIP3;
            if (map.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP_BOTTOM)) {
                pixelFromDIP5 = PixelUtil.toPixelFromDIP(map.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP_BOTTOM));
            }
            handler.setHitSlop(f, f2, f3, pixelFromDIP5, map.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP_WIDTH) ? PixelUtil.toPixelFromDIP(map.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP_WIDTH)) : Float.NaN, map.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP_HEIGHT) ? PixelUtil.toPixelFromDIP(map.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP_HEIGHT)) : Float.NaN);
        }
    }
}
