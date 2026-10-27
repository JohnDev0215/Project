package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.google.android.gms.cast.MediaError;
import com.google.firebase.perf.util.Constants;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes6.dex */
public class ReactTypefaceUtils {
    public static int parseFontWeight(@Nullable String str) {
        if (str != null) {
            switch (str) {
                case "normal":
                case "400":
                    return 400;
                case "100":
                    return 100;
                case "200":
                    return 200;
                case "300":
                    return 300;
                case "500":
                    return 500;
                case "600":
                    return MediaError.DetailedErrorCode.TEXT_UNKNOWN;
                case "700":
                case "bold":
                    return Constants.FROZEN_FRAME_TIME;
                case "800":
                    return 800;
                case "900":
                    return 900;
            }
        }
        return -1;
    }

    public static int parseFontStyle(@Nullable String str) {
        if (str == null) {
            return -1;
        }
        if ("italic".equals(str)) {
            return 2;
        }
        return "normal".equals(str) ? 0 : -1;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Nullable
    public static String parseFontVariant(@Nullable ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            String string = readableArray.getString(i);
            if (string != null) {
                byte b = -1;
                switch (string.hashCode()) {
                    case -1983120972:
                        if (string.equals("stylistic-thirteen")) {
                            b = 0;
                        }
                        break;
                    case -1933522176:
                        if (string.equals("stylistic-fifteen")) {
                            b = 1;
                        }
                        break;
                    case -1534462052:
                        if (string.equals("stylistic-eighteen")) {
                            b = 2;
                        }
                        break;
                    case -1195362251:
                        if (string.equals("proportional-nums")) {
                            b = 3;
                        }
                        break;
                    case -1061392823:
                        if (string.equals("lining-nums")) {
                            b = 4;
                        }
                        break;
                    case -771984547:
                        if (string.equals("tabular-nums")) {
                            b = 5;
                        }
                        break;
                    case -659678800:
                        if (string.equals("oldstyle-nums")) {
                            b = 6;
                        }
                        break;
                    case 296506098:
                        if (string.equals("stylistic-eight")) {
                            b = 7;
                        }
                        break;
                    case 309330544:
                        if (string.equals("stylistic-seven")) {
                            b = 8;
                        }
                        break;
                    case 310339585:
                        if (string.equals("stylistic-three")) {
                            b = 9;
                        }
                        break;
                    case 604478526:
                        if (string.equals("stylistic-eleven")) {
                            b = 10;
                        }
                        break;
                    case 979426287:
                        if (string.equals("stylistic-five")) {
                            b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                        }
                        break;
                    case 979432035:
                        if (string.equals("stylistic-four")) {
                            b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                        }
                        break;
                    case 979664367:
                        if (string.equals("stylistic-nine")) {
                            b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                        }
                        break;
                    case 1001434505:
                        if (string.equals("stylistic-one")) {
                            b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                        }
                        break;
                    case 1001438213:
                        if (string.equals("stylistic-six")) {
                            b = 15;
                        }
                        break;
                    case 1001439040:
                        if (string.equals("stylistic-ten")) {
                            b = 16;
                        }
                        break;
                    case 1001439599:
                        if (string.equals("stylistic-two")) {
                            b = 17;
                        }
                        break;
                    case 1030714463:
                        if (string.equals("stylistic-sixteen")) {
                            b = 18;
                        }
                        break;
                    case 1044065430:
                        if (string.equals("stylistic-twelve")) {
                            b = 19;
                        }
                        break;
                    case 1044067310:
                        if (string.equals("stylistic-twenty")) {
                            b = 20;
                        }
                        break;
                    case 1183323111:
                        if (string.equals("small-caps")) {
                            b = 21;
                        }
                        break;
                    case 1463562569:
                        if (string.equals("stylistic-nineteen")) {
                            b = 22;
                        }
                        break;
                    case 1648446397:
                        if (string.equals("stylistic-fourteen")) {
                            b = 23;
                        }
                        break;
                    case 2097122634:
                        if (string.equals("stylistic-seventeen")) {
                            b = 24;
                        }
                        break;
                }
                switch (b) {
                    case 0:
                        arrayList.add("'ss13'");
                        break;
                    case 1:
                        arrayList.add("'ss15'");
                        break;
                    case 2:
                        arrayList.add("'ss18'");
                        break;
                    case 3:
                        arrayList.add("'pnum'");
                        break;
                    case 4:
                        arrayList.add("'lnum'");
                        break;
                    case 5:
                        arrayList.add("'tnum'");
                        break;
                    case 6:
                        arrayList.add("'onum'");
                        break;
                    case 7:
                        arrayList.add("'ss08'");
                        break;
                    case 8:
                        arrayList.add("'ss07'");
                        break;
                    case 9:
                        arrayList.add("'ss03'");
                        break;
                    case 10:
                        arrayList.add("'ss11'");
                        break;
                    case 11:
                        arrayList.add("'ss05'");
                        break;
                    case 12:
                        arrayList.add("'ss04'");
                        break;
                    case 13:
                        arrayList.add("'ss09'");
                        break;
                    case 14:
                        arrayList.add("'ss01'");
                        break;
                    case 15:
                        arrayList.add("'ss06'");
                        break;
                    case 16:
                        arrayList.add("'ss10'");
                        break;
                    case 17:
                        arrayList.add("'ss02'");
                        break;
                    case 18:
                        arrayList.add("'ss16'");
                        break;
                    case 19:
                        arrayList.add("'ss12'");
                        break;
                    case 20:
                        arrayList.add("'ss20'");
                        break;
                    case 21:
                        arrayList.add("'smcp'");
                        break;
                    case 22:
                        arrayList.add("'ss19'");
                        break;
                    case 23:
                        arrayList.add("'ss14'");
                        break;
                    case 24:
                        arrayList.add("'ss17'");
                        break;
                }
            }
        }
        return TextUtils.join(", ", arrayList);
    }

    public static Typeface applyStyles(@Nullable Typeface typeface, int i, int i2, @Nullable String str, AssetManager assetManager) {
        TypefaceStyle typefaceStyle = new TypefaceStyle(i, i2);
        if (str != null) {
            return ReactFontManager.getInstance().getTypeface(str, typefaceStyle, assetManager);
        }
        if (typeface == null) {
            typeface = Typeface.DEFAULT;
        }
        return typefaceStyle.apply(typeface);
    }
}
