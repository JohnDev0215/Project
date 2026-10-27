package com.facebook.react.uimanager;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes6.dex */
public abstract class BaseViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T>> implements ViewManagerDelegate<T> {
    protected final U mViewManager;

    @Override // com.facebook.react.uimanager.ViewManagerDelegate
    public void receiveCommand(T t, String str, ReadableArray readableArray) {
    }

    public BaseViewManagerDelegate(U u) {
        this.mViewManager = u;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -1721943862:
                if (str.equals("translateX")) {
                    b = 0;
                }
                break;
            case -1721943861:
                if (str.equals("translateY")) {
                    b = 1;
                }
                break;
            case -1589741021:
                if (str.equals("shadowColor")) {
                    b = 2;
                }
                break;
            case -1267206133:
                if (str.equals("opacity")) {
                    b = 3;
                }
                break;
            case -1228066334:
                if (str.equals("borderTopLeftRadius")) {
                    b = 4;
                }
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    b = 5;
                }
                break;
            case -908189617:
                if (str.equals("scaleY")) {
                    b = 6;
                }
                break;
            case -877170387:
                if (str.equals("testID")) {
                    b = 7;
                }
                break;
            case -731417480:
                if (str.equals("zIndex")) {
                    b = 8;
                }
                break;
            case -101663499:
                if (str.equals("accessibilityHint")) {
                    b = 9;
                }
                break;
            case -101359900:
                if (str.equals("accessibilityRole")) {
                    b = 10;
                }
                break;
            case -80891667:
                if (str.equals("renderToHardwareTextureAndroid")) {
                    b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                }
                break;
            case -40300674:
                if (str.equals("rotation")) {
                    b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                }
                break;
            case -4379043:
                if (str.equals("elevation")) {
                    b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                }
                break;
            case 36255470:
                if (str.equals("accessibilityLiveRegion")) {
                    b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                }
                break;
            case 333432965:
                if (str.equals("borderTopRightRadius")) {
                    b = 15;
                }
                break;
            case 581268560:
                if (str.equals("borderBottomLeftRadius")) {
                    b = 16;
                }
                break;
            case 588239831:
                if (str.equals("borderBottomRightRadius")) {
                    b = 17;
                }
                break;
            case 746986311:
                if (str.equals("importantForAccessibility")) {
                    b = 18;
                }
                break;
            case 1052666732:
                if (str.equals("transform")) {
                    b = 19;
                }
                break;
            case 1146842694:
                if (str.equals("accessibilityLabel")) {
                    b = 20;
                }
                break;
            case 1153872867:
                if (str.equals("accessibilityState")) {
                    b = 21;
                }
                break;
            case 1287124693:
                if (str.equals("backgroundColor")) {
                    b = 22;
                }
                break;
            case 1349188574:
                if (str.equals("borderRadius")) {
                    b = 23;
                }
                break;
            case 1505602511:
                if (str.equals("accessibilityActions")) {
                    b = 24;
                }
                break;
            case 1761903244:
                if (str.equals("accessibilityCollection")) {
                    b = 25;
                }
                break;
            case 1865277756:
                if (str.equals("accessibilityLabelledBy")) {
                    b = 26;
                }
                break;
            case 1993034687:
                if (str.equals("accessibilityCollectionItem")) {
                    b = 27;
                }
                break;
            case 2045685618:
                if (str.equals("nativeID")) {
                    b = 28;
                }
                break;
        }
        switch (b) {
            case 0:
                this.mViewManager.setTranslateX(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case 1:
                this.mViewManager.setTranslateY(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case 2:
                this.mViewManager.setShadowColor(t, obj != null ? ColorPropConverter.getColor(obj, t.getContext()).intValue() : 0);
                break;
            case 3:
                this.mViewManager.setOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                break;
            case 4:
                this.mViewManager.setBorderTopLeftRadius(t, obj != null ? ((Double) obj).floatValue() : Float.NaN);
                break;
            case 5:
                this.mViewManager.setScaleX(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                break;
            case 6:
                this.mViewManager.setScaleY(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                break;
            case 7:
                this.mViewManager.setTestId(t, (String) obj);
                break;
            case 8:
                this.mViewManager.setZIndex(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case 9:
                this.mViewManager.setAccessibilityHint(t, (String) obj);
                break;
            case 10:
                this.mViewManager.setAccessibilityRole(t, (String) obj);
                break;
            case 11:
                this.mViewManager.setRenderToHardwareTexture(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 12:
                this.mViewManager.setRotation(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case 13:
                this.mViewManager.setElevation(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case 14:
                this.mViewManager.setAccessibilityLiveRegion(t, (String) obj);
                break;
            case 15:
                this.mViewManager.setBorderTopRightRadius(t, obj != null ? ((Double) obj).floatValue() : Float.NaN);
                break;
            case 16:
                this.mViewManager.setBorderBottomLeftRadius(t, obj != null ? ((Double) obj).floatValue() : Float.NaN);
                break;
            case 17:
                this.mViewManager.setBorderBottomRightRadius(t, obj != null ? ((Double) obj).floatValue() : Float.NaN);
                break;
            case 18:
                this.mViewManager.setImportantForAccessibility(t, (String) obj);
                break;
            case 19:
                this.mViewManager.setTransform(t, (ReadableArray) obj);
                break;
            case 20:
                this.mViewManager.setAccessibilityLabel(t, (String) obj);
                break;
            case 21:
                this.mViewManager.setViewState(t, (ReadableMap) obj);
                break;
            case 22:
                this.mViewManager.setBackgroundColor(t, obj != null ? ColorPropConverter.getColor(obj, t.getContext()).intValue() : 0);
                break;
            case 23:
                this.mViewManager.setBorderRadius(t, obj != null ? ((Double) obj).floatValue() : Float.NaN);
                break;
            case 24:
                this.mViewManager.setAccessibilityActions(t, (ReadableArray) obj);
                break;
            case 25:
                this.mViewManager.setAccessibilityCollection(t, (ReadableMap) obj);
                break;
            case 26:
                this.mViewManager.setAccessibilityLabelledBy(t, new DynamicFromObject(obj));
                break;
            case 27:
                this.mViewManager.setAccessibilityCollectionItem(t, (ReadableMap) obj);
                break;
            case 28:
                this.mViewManager.setNativeId(t, (String) obj);
                break;
        }
    }
}
