package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSVGGroupManagerInterface;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes6.dex */
public class RNSVGGroupManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGGroupManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSVGGroupManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -1267206133:
                if (str.equals("opacity")) {
                    b = 0;
                }
                break;
            case -1081239615:
                if (str.equals("matrix")) {
                    b = 1;
                }
                break;
            case -993894751:
                if (str.equals("propList")) {
                    b = 2;
                }
                break;
            case -933864895:
                if (str.equals("markerEnd")) {
                    b = 3;
                }
                break;
            case -933857362:
                if (str.equals("markerMid")) {
                    b = 4;
                }
                break;
            case -891980232:
                if (str.equals("stroke")) {
                    b = 5;
                }
                break;
            case -734428249:
                if (str.equals("fontWeight")) {
                    b = 6;
                }
                break;
            case -729118945:
                if (str.equals("fillRule")) {
                    b = 7;
                }
                break;
            case -416535885:
                if (str.equals("strokeOpacity")) {
                    b = 8;
                }
                break;
            case -293492298:
                if (str.equals("pointerEvents")) {
                    b = 9;
                }
                break;
            case -53677816:
                if (str.equals("fillOpacity")) {
                    b = 10;
                }
                break;
            case -44578051:
                if (str.equals("strokeDashoffset")) {
                    b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                }
                break;
            case 3143043:
                if (str.equals("fill")) {
                    b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                }
                break;
            case 3148879:
                if (str.equals("font")) {
                    b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                }
                break;
            case 3344108:
                if (str.equals("mask")) {
                    b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                }
                break;
            case 3373707:
                if (str.equals("name")) {
                    b = 15;
                }
                break;
            case 78845486:
                if (str.equals("strokeMiterlimit")) {
                    b = 16;
                }
                break;
            case 104482996:
                if (str.equals("vectorEffect")) {
                    b = 17;
                }
                break;
            case 217109576:
                if (str.equals("markerStart")) {
                    b = 18;
                }
                break;
            case 365601008:
                if (str.equals("fontSize")) {
                    b = 19;
                }
                break;
            case 401643183:
                if (str.equals("strokeDasharray")) {
                    b = 20;
                }
                break;
            case 917656469:
                if (str.equals("clipPath")) {
                    b = 21;
                }
                break;
            case 917735020:
                if (str.equals("clipRule")) {
                    b = 22;
                }
                break;
            case 1027575302:
                if (str.equals("strokeLinecap")) {
                    b = 23;
                }
                break;
            case 1671764162:
                if (str.equals("display")) {
                    b = 24;
                }
                break;
            case 1790285174:
                if (str.equals("strokeLinejoin")) {
                    b = 25;
                }
                break;
            case 1847674614:
                if (str.equals("responsible")) {
                    b = 26;
                }
                break;
            case 1924065902:
                if (str.equals("strokeWidth")) {
                    b = 27;
                }
                break;
        }
        switch (b) {
            case 0:
                this.mViewManager.setOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                break;
            case 1:
                ((RNSVGGroupManagerInterface) this.mViewManager).setMatrix(t, (ReadableArray) obj);
                break;
            case 2:
                ((RNSVGGroupManagerInterface) this.mViewManager).setPropList(t, (ReadableArray) obj);
                break;
            case 3:
                ((RNSVGGroupManagerInterface) this.mViewManager).setMarkerEnd(t, obj != null ? (String) obj : null);
                break;
            case 4:
                ((RNSVGGroupManagerInterface) this.mViewManager).setMarkerMid(t, obj != null ? (String) obj : null);
                break;
            case 5:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStroke(t, (ReadableMap) obj);
                break;
            case 6:
                if (obj instanceof String) {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setFontWeight(t, (String) obj);
                } else if (obj instanceof Double) {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setFontWeight(t, (Double) obj);
                } else {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setFontWeight(t, (Double) null);
                }
                break;
            case 7:
                ((RNSVGGroupManagerInterface) this.mViewManager).setFillRule(t, obj != null ? ((Double) obj).intValue() : 1);
                break;
            case 8:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                break;
            case 9:
                ((RNSVGGroupManagerInterface) this.mViewManager).setPointerEvents(t, obj != null ? (String) obj : null);
                break;
            case 10:
                ((RNSVGGroupManagerInterface) this.mViewManager).setFillOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                break;
            case 11:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeDashoffset(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case 12:
                ((RNSVGGroupManagerInterface) this.mViewManager).setFill(t, (ReadableMap) obj);
                break;
            case 13:
                ((RNSVGGroupManagerInterface) this.mViewManager).setFont(t, (ReadableMap) obj);
                break;
            case 14:
                ((RNSVGGroupManagerInterface) this.mViewManager).setMask(t, obj != null ? (String) obj : null);
                break;
            case 15:
                ((RNSVGGroupManagerInterface) this.mViewManager).setName(t, obj != null ? (String) obj : null);
                break;
            case 16:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeMiterlimit(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case 17:
                ((RNSVGGroupManagerInterface) this.mViewManager).setVectorEffect(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case 18:
                ((RNSVGGroupManagerInterface) this.mViewManager).setMarkerStart(t, obj != null ? (String) obj : null);
                break;
            case 19:
                if (obj instanceof String) {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setFontSize(t, (String) obj);
                } else if (obj instanceof Double) {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setFontSize(t, (Double) obj);
                } else {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setFontSize(t, (Double) null);
                }
                break;
            case 20:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeDasharray(t, (ReadableArray) obj);
                break;
            case 21:
                ((RNSVGGroupManagerInterface) this.mViewManager).setClipPath(t, obj != null ? (String) obj : null);
                break;
            case 22:
                ((RNSVGGroupManagerInterface) this.mViewManager).setClipRule(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case 23:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeLinecap(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case 24:
                ((RNSVGGroupManagerInterface) this.mViewManager).setDisplay(t, obj != null ? (String) obj : null);
                break;
            case 25:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeLinejoin(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case 26:
                ((RNSVGGroupManagerInterface) this.mViewManager).setResponsible(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 27:
                if (obj instanceof String) {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeWidth(t, (String) obj);
                } else if (obj instanceof Double) {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeWidth(t, (Double) obj);
                } else {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeWidth(t, "1");
                }
                break;
            default:
                super.setProperty(t, str, obj);
                break;
        }
    }
}
