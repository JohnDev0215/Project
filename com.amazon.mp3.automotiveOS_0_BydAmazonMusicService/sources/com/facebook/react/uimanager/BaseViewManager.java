package com.facebook.react.uimanager;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.R$id;
import com.facebook.react.R$string;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.MatrixMathHelper;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.PointerEventHelper;
import com.facebook.react.uimanager.util.ReactFindViewUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes6.dex */
public abstract class BaseViewManager<T extends View, C extends LayoutShadowNode> extends ViewManager<T, C> implements BaseViewManagerInterface<T> {
    private static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    private static final String STATE_BUSY = "busy";
    private static final String STATE_CHECKED = "checked";
    private static final String STATE_EXPANDED = "expanded";
    private static final String STATE_MIXED = "mixed";
    public static final Map<String, Integer> sStateDescription;
    private static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = (float) Math.sqrt(5.0d);
    private static MatrixMathHelper.MatrixDecompositionContext sMatrixDecompositionContext = new MatrixMathHelper.MatrixDecompositionContext();
    private static double[] sTransformDecompositionArray = new double[16];

    @ReactProp(name = "onMoveShouldSetResponder")
    public void setMoveShouldSetResponder(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onMoveShouldSetResponderCapture")
    public void setMoveShouldSetResponderCapture(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onResponderEnd")
    public void setResponderEnd(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onResponderGrant")
    public void setResponderGrant(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onResponderMove")
    public void setResponderMove(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onResponderReject")
    public void setResponderReject(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onResponderRelease")
    public void setResponderRelease(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onResponderStart")
    public void setResponderStart(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onResponderTerminate")
    public void setResponderTerminate(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onResponderTerminationRequest")
    public void setResponderTerminationRequest(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onShouldBlockNativeResponder")
    public void setShouldBlockNativeResponder(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onStartShouldSetResponder")
    public void setStartShouldSetResponder(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onStartShouldSetResponderCapture")
    public void setStartShouldSetResponderCapture(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onTouchCancel")
    public void setTouchCancel(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onTouchEnd")
    public void setTouchEnd(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onTouchMove")
    public void setTouchMove(@NonNull T t, boolean z) {
    }

    @ReactProp(name = "onTouchStart")
    public void setTouchStart(@NonNull T t, boolean z) {
    }

    static {
        HashMap map = new HashMap();
        sStateDescription = map;
        map.put(STATE_BUSY, Integer.valueOf(R$string.state_busy_description));
        map.put(STATE_EXPANDED, Integer.valueOf(R$string.state_expanded_description));
        map.put("collapsed", Integer.valueOf(R$string.state_collapsed_description));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    protected T prepareToRecycleView(@NonNull ThemedReactContext themedReactContext, T t) {
        t.setTag(null);
        t.setTag(R$id.pointer_events, null);
        t.setTag(R$id.react_test_id, null);
        t.setTag(R$id.view_tag_native_id, null);
        t.setTag(R$id.labelled_by, null);
        t.setTag(R$id.accessibility_label, null);
        t.setTag(R$id.accessibility_hint, null);
        t.setTag(R$id.accessibility_role, null);
        t.setTag(R$id.accessibility_state, null);
        t.setTag(R$id.accessibility_actions, null);
        t.setTag(R$id.accessibility_value, null);
        setTransform(t, null);
        t.resetPivot();
        t.setTop(0);
        t.setBottom(0);
        t.setLeft(0);
        t.setRight(0);
        t.setElevation(0.0f);
        t.setAnimationMatrix(null);
        if (Build.VERSION.SDK_INT >= 28) {
            t.setOutlineAmbientShadowColor(ViewCompat.MEASURED_STATE_MASK);
            t.setOutlineSpotShadowColor(ViewCompat.MEASURED_STATE_MASK);
        }
        t.setNextFocusDownId(-1);
        t.setNextFocusForwardId(-1);
        t.setNextFocusRightId(-1);
        t.setNextFocusUpId(-1);
        t.setFocusable(false);
        t.setFocusableInTouchMode(false);
        t.setElevation(0.0f);
        t.setAlpha(1.0f);
        setPadding(t, 0, 0, 0, 0);
        t.setForeground(null);
        return t;
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(customType = "Color", defaultInt = 0, name = "backgroundColor")
    public void setBackgroundColor(@NonNull T t, int i) {
        t.setBackgroundColor(i);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "transform")
    public void setTransform(@NonNull T t, @Nullable ReadableArray readableArray) {
        if (readableArray == null) {
            resetTransformProperty(t);
        } else {
            setTransformProperty(t, readableArray);
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(@NonNull T t, float f) {
        t.setAlpha(f);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "elevation")
    public void setElevation(@NonNull T t, float f) {
        ViewCompat.setElevation(t, PixelUtil.toPixelFromDIP(f));
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(customType = "Color", defaultInt = ViewCompat.MEASURED_STATE_MASK, name = "shadowColor")
    public void setShadowColor(@NonNull T t, int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            t.setOutlineAmbientShadowColor(i);
            t.setOutlineSpotShadowColor(i);
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "zIndex")
    public void setZIndex(@NonNull T t, float f) {
        ViewGroupManager.setViewZIndex(t, Math.round(f));
        ViewParent parent = t.getParent();
        if (parent instanceof ReactZIndexedViewGroup) {
            ((ReactZIndexedViewGroup) parent).updateDrawingOrder();
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "renderToHardwareTextureAndroid")
    public void setRenderToHardwareTexture(@NonNull T t, boolean z) {
        t.setLayerType(z ? 2 : 0, null);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "testID")
    public void setTestId(@NonNull T t, @Nullable String str) {
        t.setTag(R$id.react_test_id, str);
        t.setTag(str);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "nativeID")
    public void setNativeId(@NonNull T t, @Nullable String str) {
        t.setTag(R$id.view_tag_native_id, str);
        ReactFindViewUtil.notifyViewRendered(t);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "accessibilityLabelledBy")
    public void setAccessibilityLabelledBy(@NonNull T t, @Nullable Dynamic dynamic) {
        if (dynamic.isNull()) {
            return;
        }
        if (dynamic.getType() == ReadableType.String) {
            t.setTag(R$id.labelled_by, dynamic.asString());
        } else if (dynamic.getType() == ReadableType.Array) {
            t.setTag(R$id.labelled_by, dynamic.asArray().getString(0));
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "accessibilityLabel")
    public void setAccessibilityLabel(@NonNull T t, @Nullable String str) {
        t.setTag(R$id.accessibility_label, str);
        updateViewContentDescription(t);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "accessibilityHint")
    public void setAccessibilityHint(@NonNull T t, @Nullable String str) {
        t.setTag(R$id.accessibility_hint, str);
        updateViewContentDescription(t);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "accessibilityRole")
    public void setAccessibilityRole(@NonNull T t, @Nullable String str) {
        if (str == null) {
            return;
        }
        t.setTag(R$id.accessibility_role, ReactAccessibilityDelegate.AccessibilityRole.fromValue(str));
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "accessibilityCollection")
    public void setAccessibilityCollection(@NonNull T t, @Nullable ReadableMap readableMap) {
        t.setTag(R$id.accessibility_collection, readableMap);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "accessibilityCollectionItem")
    public void setAccessibilityCollectionItem(@NonNull T t, @Nullable ReadableMap readableMap) {
        t.setTag(R$id.accessibility_collection_item, readableMap);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "accessibilityState")
    public void setViewState(@NonNull T t, @Nullable ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        if (readableMap.hasKey("selected")) {
            boolean zIsSelected = t.isSelected();
            boolean z = readableMap.getBoolean("selected");
            t.setSelected(z);
            if (t.isAccessibilityFocused() && zIsSelected && !z) {
                t.announceForAccessibility(t.getContext().getString(R$string.state_unselected_description));
            }
        } else {
            t.setSelected(false);
        }
        t.setTag(R$id.accessibility_state, readableMap);
        if (readableMap.hasKey("disabled") && !readableMap.getBoolean("disabled")) {
            t.setEnabled(true);
        }
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            if (strNextKey.equals(STATE_BUSY) || strNextKey.equals(STATE_EXPANDED) || (strNextKey.equals(STATE_CHECKED) && readableMap.getType(STATE_CHECKED) == ReadableType.String)) {
                updateViewContentDescription(t);
                return;
            } else if (t.isAccessibilityFocused()) {
                t.sendAccessibilityEvent(1);
            }
        }
    }

    private void updateViewContentDescription(@NonNull T t) {
        Dynamic dynamic;
        int i;
        String str = (String) t.getTag(R$id.accessibility_label);
        ReadableMap readableMap = (ReadableMap) t.getTag(R$id.accessibility_state);
        ArrayList arrayList = new ArrayList();
        ReadableMap readableMap2 = (ReadableMap) t.getTag(R$id.accessibility_value);
        if (str != null) {
            arrayList.add(str);
        }
        if (readableMap != null) {
            ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
            while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                Dynamic dynamic2 = readableMap.getDynamic(strNextKey);
                if (strNextKey.equals(STATE_CHECKED) && dynamic2.getType() == ReadableType.String && dynamic2.asString().equals(STATE_MIXED)) {
                    arrayList.add(t.getContext().getString(R$string.state_mixed_description));
                } else if (strNextKey.equals(STATE_BUSY) && dynamic2.getType() == ReadableType.Boolean && dynamic2.asBoolean()) {
                    arrayList.add(t.getContext().getString(R$string.state_busy_description));
                } else if (strNextKey.equals(STATE_EXPANDED) && dynamic2.getType() == ReadableType.Boolean) {
                    Context context = t.getContext();
                    if (dynamic2.asBoolean()) {
                        i = R$string.state_expanded_description;
                    } else {
                        i = R$string.state_collapsed_description;
                    }
                    arrayList.add(context.getString(i));
                }
            }
        }
        if (readableMap2 != null && readableMap2.hasKey("text") && (dynamic = readableMap2.getDynamic("text")) != null && dynamic.getType() == ReadableType.String) {
            arrayList.add(dynamic.asString());
        }
        if (arrayList.size() > 0) {
            t.setContentDescription(TextUtils.join(", ", arrayList));
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "accessibilityActions")
    public void setAccessibilityActions(T t, ReadableArray readableArray) {
        if (readableArray == null) {
            return;
        }
        t.setTag(R$id.accessibility_actions, readableArray);
    }

    @ReactProp(name = "accessibilityValue")
    public void setAccessibilityValue(T t, ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        t.setTag(R$id.accessibility_value, readableMap);
        if (readableMap.hasKey("text")) {
            updateViewContentDescription(t);
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "importantForAccessibility")
    public void setImportantForAccessibility(@NonNull T t, @Nullable String str) {
        if (str == null || str.equals(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
            ViewCompat.setImportantForAccessibility(t, 0);
            return;
        }
        if (str.equals("yes")) {
            ViewCompat.setImportantForAccessibility(t, 1);
        } else if (str.equals("no")) {
            ViewCompat.setImportantForAccessibility(t, 2);
        } else if (str.equals("no-hide-descendants")) {
            ViewCompat.setImportantForAccessibility(t, 4);
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "rotation")
    @Deprecated
    public void setRotation(@NonNull T t, float f) {
        t.setRotation(f);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = "scaleX")
    @Deprecated
    public void setScaleX(@NonNull T t, float f) {
        t.setScaleX(f);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = "scaleY")
    @Deprecated
    public void setScaleY(@NonNull T t, float f) {
        t.setScaleY(f);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 0.0f, name = "translateX")
    @Deprecated
    public void setTranslateX(@NonNull T t, float f) {
        t.setTranslationX(PixelUtil.toPixelFromDIP(f));
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 0.0f, name = "translateY")
    @Deprecated
    public void setTranslateY(@NonNull T t, float f) {
        t.setTranslationY(PixelUtil.toPixelFromDIP(f));
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "accessibilityLiveRegion")
    public void setAccessibilityLiveRegion(@NonNull T t, @Nullable String str) {
        if (str == null || str.equals("none")) {
            ViewCompat.setAccessibilityLiveRegion(t, 0);
        } else if (str.equals("polite")) {
            ViewCompat.setAccessibilityLiveRegion(t, 1);
        } else if (str.equals("assertive")) {
            ViewCompat.setAccessibilityLiveRegion(t, 2);
        }
    }

    private static void setTransformProperty(@NonNull View view, ReadableArray readableArray) {
        sMatrixDecompositionContext.reset();
        TransformHelper.processTransform(readableArray, sTransformDecompositionArray);
        MatrixMathHelper.decomposeMatrix(sTransformDecompositionArray, sMatrixDecompositionContext);
        view.setTranslationX(PixelUtil.toPixelFromDIP(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.translation[0])));
        view.setTranslationY(PixelUtil.toPixelFromDIP(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.translation[1])));
        view.setRotation(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.rotationDegrees[2]));
        view.setRotationX(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.rotationDegrees[0]));
        view.setRotationY(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.rotationDegrees[1]));
        view.setScaleX(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.scale[0]));
        view.setScaleY(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.scale[1]));
        double[] dArr = sMatrixDecompositionContext.perspective;
        if (dArr.length > 2) {
            float f = (float) dArr[2];
            if (f == 0.0f) {
                f = 7.8125E-4f;
            }
            float f2 = (-1.0f) / f;
            float f3 = DisplayMetricsHolder.getScreenDisplayMetrics().density;
            view.setCameraDistance(sanitizeFloatPropertyValue(f3 * f3 * f2 * CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER));
        }
    }

    private static float sanitizeFloatPropertyValue(float f) {
        if (f >= -3.4028235E38f && f <= Float.MAX_VALUE) {
            return f;
        }
        if (f < -3.4028235E38f || f == Float.NEGATIVE_INFINITY) {
            return -3.4028235E38f;
        }
        if (f > Float.MAX_VALUE || f == Float.POSITIVE_INFINITY) {
            return Float.MAX_VALUE;
        }
        if (Float.isNaN(f)) {
            return 0.0f;
        }
        throw new IllegalStateException("Invalid float property value: " + f);
    }

    private static void resetTransformProperty(@NonNull View view) {
        view.setTranslationX(PixelUtil.toPixelFromDIP(0.0f));
        view.setTranslationY(PixelUtil.toPixelFromDIP(0.0f));
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setCameraDistance(0.0f);
    }

    private void updateViewAccessibility(@NonNull T t) {
        ReactAccessibilityDelegate.setDelegate(t, t.isFocusable(), t.getImportantForAccessibility());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    protected void onAfterUpdateTransaction(@NonNull T t) {
        super.onAfterUpdateTransaction(t);
        updateViewAccessibility(t);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap<>();
        }
        MapBuilder.Builder builderPut = MapBuilder.builder().put("topPointerCancel", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPointerCancel", "captured", "onPointerCancelCapture"))).put("topPointerDown", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPointerDown", "captured", "onPointerDownCapture")));
        Boolean bool = Boolean.TRUE;
        exportedCustomDirectEventTypeConstants.putAll(builderPut.put("topPointerEnter", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPointerEnter", "captured", "onPointerEnterCapture", "skipBubbling", bool))).put("topPointerLeave", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPointerLeave", "captured", "onPointerLeaveCapture", "skipBubbling", bool))).put("topPointerMove", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPointerMove", "captured", "onPointerMoveCapture"))).put("topPointerUp", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPointerUp", "captured", "onPointerUpCapture"))).put("topPointerOut", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPointerOut", "captured", "onPointerOutCapture"))).put("topPointerOver", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPointerOver", "captured", "onPointerOverCapture"))).build());
        return exportedCustomDirectEventTypeConstants;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap<>();
        }
        exportedCustomDirectEventTypeConstants.putAll(MapBuilder.builder().put("topAccessibilityAction", MapBuilder.of("registrationName", "onAccessibilityAction")).build());
        return exportedCustomDirectEventTypeConstants;
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderRadius(T t, float f) {
        logUnsupportedPropertyWarning("borderRadius");
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderBottomLeftRadius(T t, float f) {
        logUnsupportedPropertyWarning("borderBottomLeftRadius");
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderBottomRightRadius(T t, float f) {
        logUnsupportedPropertyWarning("borderBottomRightRadius");
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderTopLeftRadius(T t, float f) {
        logUnsupportedPropertyWarning("borderTopLeftRadius");
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderTopRightRadius(T t, float f) {
        logUnsupportedPropertyWarning("borderTopRightRadius");
    }

    private void logUnsupportedPropertyWarning(String str) {
        FLog.w("ReactNative", "%s doesn't support property '%s'", getName(), str);
    }

    private static void setPointerEventsFlag(@NonNull View view, PointerEventHelper.EVENT event, boolean z) {
        int i = R$id.pointer_events;
        Integer num = (Integer) view.getTag(i);
        int iIntValue = num != null ? num.intValue() : 0;
        int iOrdinal = 1 << event.ordinal();
        view.setTag(i, Integer.valueOf(z ? iOrdinal | iIntValue : (~iOrdinal) & iIntValue));
    }

    @ReactProp(name = "onPointerEnter")
    public void setPointerEnter(@NonNull T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.ENTER, z);
    }

    @ReactProp(name = "onPointerEnterCapture")
    public void setPointerEnterCapture(@NonNull T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.ENTER_CAPTURE, z);
    }

    @ReactProp(name = "onPointerOver")
    public void setPointerOver(@NonNull T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.OVER, z);
    }

    @ReactProp(name = "onPointerOverCapture")
    public void setPointerOverCapture(@NonNull T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.OVER_CAPTURE, z);
    }

    @ReactProp(name = "onPointerOut")
    public void setPointerOut(@NonNull T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.OUT, z);
    }

    @ReactProp(name = "onPointerOutCapture")
    public void setPointerOutCapture(@NonNull T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.OUT_CAPTURE, z);
    }

    @ReactProp(name = "onPointerLeave")
    public void setPointerLeave(@NonNull T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.LEAVE, z);
    }

    @ReactProp(name = "onPointerLeaveCapture")
    public void setPointerLeaveCapture(@NonNull T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.LEAVE_CAPTURE, z);
    }

    @ReactProp(name = "onPointerMove")
    public void setPointerMove(@NonNull T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.MOVE, z);
    }

    @ReactProp(name = "onPointerMoveCapture")
    public void setPointerMoveCapture(@NonNull T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.MOVE_CAPTURE, z);
    }
}
