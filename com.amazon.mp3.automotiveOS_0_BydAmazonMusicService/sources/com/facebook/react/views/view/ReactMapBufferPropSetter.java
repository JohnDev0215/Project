package com.facebook.react.views.view;

import android.graphics.Rect;
import androidx.core.view.ViewCompat;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: ReactMapBufferPropSetter.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b#\u0010$J\u001c\u0010\b\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0014\u0010\u000b\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0002J\u001c\u0010\f\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H\u0002J\u001c\u0010\u000e\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\rH\u0002J\u001c\u0010\u000f\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H\u0002J\u001c\u0010\u0010\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0002J\u001c\u0010\u0011\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0002J\u001c\u0010\u0012\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H\u0002J\u001c\u0010\u0013\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H\u0002J\u001c\u0010\u0014\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0002J\u0014\u0010\u0015\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H\u0002J\u0014\u0010\u0016\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0002J\u0014\u0010\u0017\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0002J\u001c\u0010\u0018\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H\u0002J\u001c\u0010\u0019\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H\u0002J\u001c\u0010\u001a\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0002J\u001c\u0010\u001b\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0002J\u001c\u0010\u001c\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H\u0002J\u001c\u0010\u001d\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H\u0002J\u000e\u0010\u001f\u001a\u0004\u0018\u00010\u001e*\u00020\u0005H\u0002J\u001e\u0010\"\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u0005¨\u0006%"}, d2 = {"Lcom/facebook/react/views/view/ReactMapBufferPropSetter;", "", "Lcom/facebook/react/views/view/ReactViewManager;", "Lcom/facebook/react/views/view/ReactViewGroup;", "view", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "mapBuffer", "", "accessibilityActions", "", "value", "accessibilityLiveRegion", "accessibilityState", "", "accessibilityValue", "accessibilityLabelledBy", "backfaceVisibility", "backgroundColor", "borderColor", "borderRadius", "borderStyle", "hitSlop", "importantForAccessibility", "pointerEvents", "transform", "borderWidth", "overflow", "shadowColor", "nativeBackground", "nativeForeground", "Lcom/facebook/react/bridge/ReadableMap;", "toJsDrawableDescription", "viewManager", "props", "setProps", "<init>", "()V", "ReactAndroid_release"}, k = 1, mv = {1, 6, 0})
public final class ReactMapBufferPropSetter {
    public static final ReactMapBufferPropSetter INSTANCE = new ReactMapBufferPropSetter();

    private ReactMapBufferPropSetter() {
    }

    public final void setProps(ReactViewGroup view, ReactViewManager viewManager, MapBuffer props) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        Intrinsics.checkNotNullParameter(props, "props");
        for (MapBuffer.Entry entry : props) {
            int key = entry.getKey();
            if (key != 46) {
                if (key == 100) {
                    borderWidth(viewManager, view, entry.getMapBufferValue());
                } else if (key != 101) {
                    switch (key) {
                        case 0:
                            accessibilityActions(viewManager, view, entry.getMapBufferValue());
                            break;
                        case 1:
                            String stringValue = entry.getStringValue();
                            viewManager.setAccessibilityHint(view, stringValue.length() > 0 ? stringValue : null);
                            break;
                        case 2:
                            String stringValue2 = entry.getStringValue();
                            viewManager.setAccessibilityLabel(view, stringValue2.length() > 0 ? stringValue2 : null);
                            break;
                        case 3:
                            accessibilityLabelledBy(viewManager, view, entry.getMapBufferValue());
                            break;
                        case 4:
                            accessibilityLiveRegion(view, entry.getIntValue());
                            break;
                        case 5:
                            String stringValue3 = entry.getStringValue();
                            viewManager.setAccessibilityRole(view, stringValue3.length() > 0 ? stringValue3 : null);
                            break;
                        case 6:
                            accessibilityState(viewManager, view, entry.getMapBufferValue());
                            break;
                        case 7:
                            accessibilityValue(viewManager, view, entry.getStringValue());
                            break;
                        case 8:
                            viewManager.setAccessible(view, entry.getBooleanValue());
                            break;
                        case 9:
                            backfaceVisibility(viewManager, view, entry.getIntValue());
                            break;
                        case 10:
                            backgroundColor(viewManager, view, entry.getIntValue());
                            break;
                        case 11:
                            borderColor(viewManager, view, entry.getMapBufferValue());
                            break;
                        case 12:
                            borderRadius(viewManager, view, entry.getMapBufferValue());
                            break;
                        case 13:
                            MapBuffer mapBufferValue = entry.getMapBufferValue();
                            if (mapBufferValue.contains(8)) {
                                borderStyle(viewManager, view, (int) mapBufferValue.getDouble(8));
                            }
                            break;
                        default:
                            switch (key) {
                                case 15:
                                    viewManager.setElevation(view, (float) entry.getDoubleValue());
                                    break;
                                case 16:
                                    viewManager.setFocusable(view, entry.getBooleanValue());
                                    break;
                                case 17:
                                    viewManager.setTVPreferredFocus(view, entry.getBooleanValue());
                                    break;
                                case 18:
                                    hitSlop(view, entry.getMapBufferValue());
                                    break;
                                case 19:
                                    importantForAccessibility(view, entry.getIntValue());
                                    break;
                                case 20:
                                    nativeBackground(viewManager, view, entry.getMapBufferValue());
                                    break;
                                case 21:
                                    nativeForeground(viewManager, view, entry.getMapBufferValue());
                                    break;
                                case 22:
                                    String stringValue4 = entry.getStringValue();
                                    viewManager.setNativeId(view, stringValue4.length() > 0 ? stringValue4 : null);
                                    break;
                                case 23:
                                    viewManager.setNeedsOffscreenAlphaCompositing(view, entry.getBooleanValue());
                                    break;
                                case 24:
                                    viewManager.setOpacity(view, (float) entry.getDoubleValue());
                                    break;
                                case 25:
                                    pointerEvents(view, entry.getIntValue());
                                    break;
                                case 26:
                                    viewManager.setPointerEnter(view, entry.getBooleanValue());
                                    break;
                                case 27:
                                    viewManager.setPointerLeave(view, entry.getBooleanValue());
                                    break;
                                case 28:
                                    viewManager.setPointerMove(view, entry.getBooleanValue());
                                    break;
                                case 29:
                                    viewManager.setRemoveClippedSubviews(view, entry.getBooleanValue());
                                    break;
                                case 30:
                                    viewManager.setRenderToHardwareTexture(view, entry.getBooleanValue());
                                    break;
                                case 31:
                                    shadowColor(viewManager, view, entry.getIntValue());
                                    break;
                                case 32:
                                    String stringValue5 = entry.getStringValue();
                                    viewManager.setTestId(view, stringValue5.length() > 0 ? stringValue5 : null);
                                    break;
                                case 33:
                                    transform(viewManager, view, entry.getMapBufferValue());
                                    break;
                                case 34:
                                    viewManager.setZIndex(view, entry.getIntValue());
                                    break;
                                default:
                                    switch (key) {
                                        case 38:
                                            viewManager.setPointerEnterCapture(view, entry.getBooleanValue());
                                            break;
                                        case 39:
                                            viewManager.setPointerLeaveCapture(view, entry.getBooleanValue());
                                            break;
                                        case 40:
                                            viewManager.setPointerMoveCapture(view, entry.getBooleanValue());
                                            break;
                                        case 41:
                                            viewManager.setPointerOut(view, entry.getBooleanValue());
                                            break;
                                        case 42:
                                            viewManager.setPointerOutCapture(view, entry.getBooleanValue());
                                            break;
                                        case 43:
                                            viewManager.setPointerOver(view, entry.getBooleanValue());
                                            break;
                                        case 44:
                                            viewManager.setPointerOverCapture(view, entry.getBooleanValue());
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    overflow(viewManager, view, entry.getIntValue());
                }
            }
        }
    }

    private final void accessibilityActions(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, MapBuffer mapBuffer) {
        ArrayList arrayList = new ArrayList();
        for (MapBuffer.Entry entry : mapBuffer) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            MapBuffer mapBufferValue = entry.getMapBufferValue();
            if (mapBufferValue != null) {
                javaOnlyMap.putString("name", mapBufferValue.getString(0));
                if (mapBufferValue.contains(1)) {
                    javaOnlyMap.putString(ContextMappingConstants.LABEL, mapBufferValue.getString(1));
                }
            }
            arrayList.add(javaOnlyMap);
        }
        reactViewManager.setAccessibilityActions(reactViewGroup, JavaOnlyArray.from(arrayList));
    }

    private final void accessibilityLiveRegion(ReactViewGroup reactViewGroup, int i) {
        int i2 = 0;
        if (i != 0) {
            if (i == 1) {
                i2 = 1;
            } else if (i == 2) {
                i2 = 2;
            }
        }
        ViewCompat.setAccessibilityLiveRegion(reactViewGroup, i2);
    }

    private final void accessibilityState(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, MapBuffer mapBuffer) {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        javaOnlyMap.putBoolean("selected", mapBuffer.getBoolean(3));
        javaOnlyMap.putBoolean("busy", mapBuffer.getBoolean(0));
        javaOnlyMap.putBoolean("expanded", mapBuffer.getBoolean(2));
        javaOnlyMap.putBoolean("disabled", mapBuffer.getBoolean(1));
        int i = mapBuffer.getInt(4);
        if (i == 0) {
            javaOnlyMap.putBoolean("checked", false);
        } else if (i == 1) {
            javaOnlyMap.putBoolean("checked", true);
        } else if (i == 2) {
            javaOnlyMap.putString("checked", "mixed");
        }
        reactViewManager.setViewState(reactViewGroup, javaOnlyMap);
    }

    private final void accessibilityValue(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, String str) {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        if (str.length() > 0) {
            javaOnlyMap.putString("text", str);
        }
        reactViewManager.setAccessibilityValue(reactViewGroup, javaOnlyMap);
    }

    private final void accessibilityLabelledBy(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, MapBuffer mapBuffer) {
        DynamicFromObject dynamicFromObject;
        if (mapBuffer.getCount() == 0) {
            dynamicFromObject = new DynamicFromObject(null);
        } else {
            JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
            Iterator<MapBuffer.Entry> it = mapBuffer.iterator();
            while (it.hasNext()) {
                javaOnlyArray.pushString(it.next().getStringValue());
            }
            dynamicFromObject = new DynamicFromObject(javaOnlyArray);
        }
        reactViewManager.setAccessibilityLabelledBy(reactViewGroup, dynamicFromObject);
    }

    private final void backfaceVisibility(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, int i) {
        reactViewManager.setBackfaceVisibility(reactViewGroup, i != 1 ? i != 2 ? DebugKt.DEBUG_PROPERTY_VALUE_AUTO : "hidden" : "visible");
    }

    private final void backgroundColor(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, int i) {
        Integer numValueOf = Integer.valueOf(i);
        if (!(numValueOf.intValue() != Integer.MAX_VALUE)) {
            numValueOf = null;
        }
        reactViewManager.setBackgroundColor(reactViewGroup, numValueOf != null ? numValueOf.intValue() : 0);
    }

    private final void borderColor(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, MapBuffer mapBuffer) {
        int i;
        for (MapBuffer.Entry entry : mapBuffer) {
            int key = entry.getKey();
            switch (key) {
                case 0:
                    i = 3;
                    break;
                case 1:
                    i = 1;
                    break;
                case 2:
                    i = 2;
                    break;
                case 3:
                    i = 4;
                    break;
                case 4:
                    i = 5;
                    break;
                case 5:
                    i = 6;
                    break;
                case 6:
                    i = 0;
                    break;
                default:
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Unknown key for border color: ", Integer.valueOf(key)));
            }
            Integer numValueOf = Integer.valueOf(entry.getIntValue());
            if (!(numValueOf.intValue() != -1)) {
                numValueOf = null;
            }
            reactViewManager.setBorderColor(reactViewGroup, i, numValueOf);
        }
    }

    private final void borderRadius(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, MapBuffer mapBuffer) {
        int i;
        for (MapBuffer.Entry entry : mapBuffer) {
            int key = entry.getKey();
            switch (key) {
                case 0:
                    i = 1;
                    break;
                case 1:
                    i = 2;
                    break;
                case 2:
                    i = 3;
                    break;
                case 3:
                    i = 4;
                    break;
                case 4:
                    i = 5;
                    break;
                case 5:
                    i = 6;
                    break;
                case 6:
                    i = 8;
                    break;
                case 7:
                    i = 7;
                    break;
                case 8:
                    i = 0;
                    break;
                default:
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Unknown key for border style: ", Integer.valueOf(key)));
            }
            double doubleValue = entry.getDoubleValue();
            if (!Double.isNaN(doubleValue)) {
                reactViewManager.setBorderRadius(reactViewGroup, i, (float) doubleValue);
            }
        }
    }

    private final void borderStyle(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, int i) {
        reactViewManager.setBorderStyle(reactViewGroup, i != 0 ? i != 1 ? i != 2 ? null : "dashed" : "dotted" : "solid");
    }

    private final void hitSlop(ReactViewGroup reactViewGroup, MapBuffer mapBuffer) {
        reactViewGroup.setHitSlopRect(new Rect((int) PixelUtil.toPixelFromDIP(mapBuffer.getDouble(1)), (int) PixelUtil.toPixelFromDIP(mapBuffer.getDouble(0)), (int) PixelUtil.toPixelFromDIP(mapBuffer.getDouble(2)), (int) PixelUtil.toPixelFromDIP(mapBuffer.getDouble(3))));
    }

    private final void importantForAccessibility(ReactViewGroup reactViewGroup, int i) {
        int i2 = 0;
        if (i != 0) {
            if (i == 1) {
                i2 = 1;
            } else if (i == 2) {
                i2 = 2;
            } else if (i == 3) {
                i2 = 4;
            }
        }
        ViewCompat.setImportantForAccessibility(reactViewGroup, i2);
    }

    private final void pointerEvents(ReactViewGroup reactViewGroup, int i) {
        PointerEvents pointerEvents;
        if (i == 0) {
            pointerEvents = PointerEvents.AUTO;
        } else if (i == 1) {
            pointerEvents = PointerEvents.NONE;
        } else if (i == 2) {
            pointerEvents = PointerEvents.BOX_NONE;
        } else if (i == 3) {
            pointerEvents = PointerEvents.BOX_ONLY;
        } else {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Unknown value for pointer events: ", Integer.valueOf(i)));
        }
        reactViewGroup.setPointerEvents(pointerEvents);
    }

    private final void transform(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, MapBuffer mapBuffer) {
        JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
        Iterator<MapBuffer.Entry> it = mapBuffer.iterator();
        while (it.hasNext()) {
            javaOnlyArray.pushDouble(it.next().getDoubleValue());
        }
        reactViewManager.setTransform(reactViewGroup, (ReadableArray) javaOnlyArray);
    }

    private final void borderWidth(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, MapBuffer mapBuffer) {
        int i;
        for (MapBuffer.Entry entry : mapBuffer) {
            int key = entry.getKey();
            switch (key) {
                case 0:
                    i = 3;
                    break;
                case 1:
                    i = 1;
                    break;
                case 2:
                    i = 2;
                    break;
                case 3:
                    i = 4;
                    break;
                case 4:
                    i = 5;
                    break;
                case 5:
                    i = 6;
                    break;
                case 6:
                    i = 0;
                    break;
                default:
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Unknown key for border width: ", Integer.valueOf(key)));
            }
            double doubleValue = entry.getDoubleValue();
            if (!Double.isNaN(doubleValue)) {
                reactViewManager.setBorderWidth(reactViewGroup, i, (float) doubleValue);
            }
        }
    }

    private final void overflow(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, int i) {
        String str;
        if (i == 0) {
            str = "visible";
        } else if (i == 1) {
            str = "hidden";
        } else {
            if (i != 2) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Unknown overflow value: ", Integer.valueOf(i)));
            }
            str = "scroll";
        }
        reactViewManager.setOverflow(reactViewGroup, str);
    }

    private final void shadowColor(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, int i) {
        Integer numValueOf = Integer.valueOf(i);
        if (!(numValueOf.intValue() != Integer.MAX_VALUE)) {
            numValueOf = null;
        }
        reactViewManager.setShadowColor(reactViewGroup, numValueOf == null ? ViewCompat.MEASURED_STATE_MASK : numValueOf.intValue());
    }

    private final void nativeBackground(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, MapBuffer mapBuffer) {
        reactViewManager.setNativeBackground(reactViewGroup, toJsDrawableDescription(mapBuffer));
    }

    private final void nativeForeground(ReactViewManager reactViewManager, ReactViewGroup reactViewGroup, MapBuffer mapBuffer) {
        reactViewManager.setNativeForeground(reactViewGroup, toJsDrawableDescription(mapBuffer));
    }

    private final ReadableMap toJsDrawableDescription(MapBuffer mapBuffer) {
        if (mapBuffer.getCount() == 0) {
            return null;
        }
        int i = mapBuffer.getInt(0);
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        if (i == 0) {
            javaOnlyMap.putString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "ThemeAttrAndroid");
            javaOnlyMap.putString("attribute", mapBuffer.getString(1));
        } else if (i == 1) {
            javaOnlyMap.putString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "RippleAndroid");
            if (mapBuffer.contains(2)) {
                javaOnlyMap.putInt("color", mapBuffer.getInt(2));
            }
            javaOnlyMap.putBoolean("borderless", mapBuffer.getBoolean(3));
            if (mapBuffer.contains(4)) {
                javaOnlyMap.putDouble("rippleRadius", mapBuffer.getDouble(4));
            }
        } else {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Unknown native drawable: ", Integer.valueOf(i)));
        }
        return javaOnlyMap;
    }
}
