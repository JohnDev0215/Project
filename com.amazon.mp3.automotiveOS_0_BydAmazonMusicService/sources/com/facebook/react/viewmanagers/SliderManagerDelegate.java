package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.SliderManagerInterface;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes6.dex */
public class SliderManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & SliderManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public SliderManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -1900655011:
                if (str.equals("maximumTrackTintColor")) {
                    b = 0;
                }
                break;
            case -1736983259:
                if (str.equals("thumbImage")) {
                    b = 1;
                }
                break;
            case -1609594047:
                if (str.equals("enabled")) {
                    b = 2;
                }
                break;
            case -1021497397:
                if (str.equals("minimumTrackTintColor")) {
                    b = 3;
                }
                break;
            case -981448432:
                if (str.equals("maximumTrackImage")) {
                    b = 4;
                }
                break;
            case -877170387:
                if (str.equals("testID")) {
                    b = 5;
                }
                break;
            case 3540684:
                if (str.equals("step")) {
                    b = 6;
                }
                break;
            case 111972721:
                if (str.equals("value")) {
                    b = 7;
                }
                break;
            case 270940796:
                if (str.equals("disabled")) {
                    b = 8;
                }
                break;
            case 718061361:
                if (str.equals("maximumValue")) {
                    b = 9;
                }
                break;
            case 1139400400:
                if (str.equals("trackImage")) {
                    b = 10;
                }
                break;
            case 1192487427:
                if (str.equals("minimumValue")) {
                    b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                }
                break;
            case 1333596542:
                if (str.equals("minimumTrackImage")) {
                    b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                }
                break;
            case 1912319986:
                if (str.equals("thumbTintColor")) {
                    b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                }
                break;
        }
        switch (b) {
            case 0:
                ((SliderManagerInterface) this.mViewManager).setMaximumTrackTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 1:
                ((SliderManagerInterface) this.mViewManager).setThumbImage(t, (ReadableMap) obj);
                break;
            case 2:
                ((SliderManagerInterface) this.mViewManager).setEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                break;
            case 3:
                ((SliderManagerInterface) this.mViewManager).setMinimumTrackTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 4:
                ((SliderManagerInterface) this.mViewManager).setMaximumTrackImage(t, (ReadableMap) obj);
                break;
            case 5:
                ((SliderManagerInterface) this.mViewManager).setTestID(t, obj == null ? "" : (String) obj);
                break;
            case 6:
                ((SliderManagerInterface) this.mViewManager).setStep(t, obj != null ? ((Double) obj).doubleValue() : 0.0d);
                break;
            case 7:
                ((SliderManagerInterface) this.mViewManager).setValue(t, obj != null ? ((Double) obj).doubleValue() : 0.0d);
                break;
            case 8:
                ((SliderManagerInterface) this.mViewManager).setDisabled(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 9:
                ((SliderManagerInterface) this.mViewManager).setMaximumValue(t, obj == null ? 1.0d : ((Double) obj).doubleValue());
                break;
            case 10:
                ((SliderManagerInterface) this.mViewManager).setTrackImage(t, (ReadableMap) obj);
                break;
            case 11:
                ((SliderManagerInterface) this.mViewManager).setMinimumValue(t, obj != null ? ((Double) obj).doubleValue() : 0.0d);
                break;
            case 12:
                ((SliderManagerInterface) this.mViewManager).setMinimumTrackImage(t, (ReadableMap) obj);
                break;
            case 13:
                ((SliderManagerInterface) this.mViewManager).setThumbTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            default:
                super.setProperty(t, str, obj);
                break;
        }
    }
}
