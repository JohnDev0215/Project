package com.facebook.react.views.text;

import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.room.FtsOptions;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import java.util.ArrayList;
import java.util.Iterator;
import kotlinx.coroutines.DebugKt;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes6.dex */
public class TextAttributeProps {
    protected int mBackgroundColor;
    protected int mColor;
    private static final int DEFAULT_JUSTIFICATION_MODE = 0;
    private static final int DEFAULT_BREAK_STRATEGY = 1;
    private static final int DEFAULT_HYPHENATION_FREQUENCY = 0;
    protected float mLineHeight = Float.NaN;
    protected boolean mIsColorSet = false;
    protected boolean mAllowFontScaling = true;
    protected boolean mIsBackgroundColorSet = false;
    protected int mNumberOfLines = -1;
    protected int mFontSize = -1;
    protected float mFontSizeInput = -1.0f;
    protected float mLineHeightInput = -1.0f;
    protected float mLetterSpacingInput = Float.NaN;
    protected int mTextAlign = 0;
    protected int mLayoutDirection = -1;
    protected TextTransform mTextTransform = TextTransform.NONE;
    protected float mTextShadowOffsetDx = 0.0f;
    protected float mTextShadowOffsetDy = 0.0f;
    protected float mTextShadowRadius = 1.0f;
    protected int mTextShadowColor = 1426063360;
    protected boolean mIsUnderlineTextDecorationSet = false;
    protected boolean mIsLineThroughTextDecorationSet = false;
    protected boolean mIncludeFontPadding = true;

    @Nullable
    protected ReactAccessibilityDelegate.AccessibilityRole mAccessibilityRole = null;
    protected boolean mIsAccessibilityRoleSet = false;
    protected boolean mIsAccessibilityLink = false;
    protected int mFontStyle = -1;
    protected int mFontWeight = -1;

    @Nullable
    protected String mFontFamily = null;

    @Nullable
    protected String mFontFeatureSettings = null;
    protected boolean mContainsImages = false;
    protected float mHeightOfTallestInlineImage = Float.NaN;

    private TextAttributeProps() {
    }

    public static TextAttributeProps fromMapBuffer(MapBuffer mapBuffer) {
        TextAttributeProps textAttributeProps = new TextAttributeProps();
        for (MapBuffer.Entry entry : mapBuffer) {
            int key = entry.getKey();
            if (key == 0) {
                textAttributeProps.setColor(Integer.valueOf(entry.getIntValue()));
            } else if (key == 1) {
                textAttributeProps.setBackgroundColor(Integer.valueOf(entry.getIntValue()));
            } else if (key == 3) {
                textAttributeProps.setFontFamily(entry.getStringValue());
            } else if (key == 4) {
                textAttributeProps.setFontSize((float) entry.getDoubleValue());
            } else if (key == 15) {
                textAttributeProps.setTextDecorationLine(entry.getStringValue());
            } else if (key == 18) {
                textAttributeProps.setTextShadowRadius((float) entry.getDoubleValue());
            } else if (key == 19) {
                textAttributeProps.setTextShadowColor(entry.getIntValue());
            } else if (key == 21) {
                textAttributeProps.setLayoutDirection(entry.getStringValue());
            } else if (key != 22) {
                switch (key) {
                    case 6:
                        textAttributeProps.setFontWeight(entry.getStringValue());
                        break;
                    case 7:
                        textAttributeProps.setFontStyle(entry.getStringValue());
                        break;
                    case 8:
                        textAttributeProps.setFontVariant(entry.getMapBufferValue());
                        break;
                    case 9:
                        textAttributeProps.setAllowFontScaling(entry.getBooleanValue());
                        break;
                    case 10:
                        textAttributeProps.setLetterSpacing((float) entry.getDoubleValue());
                        break;
                    case 11:
                        textAttributeProps.setLineHeight((float) entry.getDoubleValue());
                        break;
                }
            } else {
                textAttributeProps.setAccessibilityRole(entry.getStringValue());
            }
        }
        return textAttributeProps;
    }

    public static TextAttributeProps fromReadableMap(ReactStylesDiffMap reactStylesDiffMap) {
        TextAttributeProps textAttributeProps = new TextAttributeProps();
        textAttributeProps.setNumberOfLines(getIntProp(reactStylesDiffMap, "numberOfLines", -1));
        textAttributeProps.setLineHeight(getFloatProp(reactStylesDiffMap, "lineHeight", -1.0f));
        textAttributeProps.setLetterSpacing(getFloatProp(reactStylesDiffMap, "letterSpacing", Float.NaN));
        textAttributeProps.setAllowFontScaling(getBooleanProp(reactStylesDiffMap, "allowFontScaling", true));
        textAttributeProps.setFontSize(getFloatProp(reactStylesDiffMap, "fontSize", -1.0f));
        textAttributeProps.setColor(reactStylesDiffMap.hasKey("color") ? Integer.valueOf(reactStylesDiffMap.getInt("color", 0)) : null);
        textAttributeProps.setColor(reactStylesDiffMap.hasKey("foregroundColor") ? Integer.valueOf(reactStylesDiffMap.getInt("foregroundColor", 0)) : null);
        textAttributeProps.setBackgroundColor(reactStylesDiffMap.hasKey("backgroundColor") ? Integer.valueOf(reactStylesDiffMap.getInt("backgroundColor", 0)) : null);
        textAttributeProps.setFontFamily(getStringProp(reactStylesDiffMap, "fontFamily"));
        textAttributeProps.setFontWeight(getStringProp(reactStylesDiffMap, "fontWeight"));
        textAttributeProps.setFontStyle(getStringProp(reactStylesDiffMap, "fontStyle"));
        textAttributeProps.setFontVariant(getArrayProp(reactStylesDiffMap, "fontVariant"));
        textAttributeProps.setIncludeFontPadding(getBooleanProp(reactStylesDiffMap, "includeFontPadding", true));
        textAttributeProps.setTextDecorationLine(getStringProp(reactStylesDiffMap, "textDecorationLine"));
        textAttributeProps.setTextShadowOffset(reactStylesDiffMap.hasKey("textShadowOffset") ? reactStylesDiffMap.getMap("textShadowOffset") : null);
        textAttributeProps.setTextShadowRadius(getFloatProp(reactStylesDiffMap, "textShadowRadius", 1.0f));
        textAttributeProps.setTextShadowColor(getIntProp(reactStylesDiffMap, "textShadowColor", 1426063360));
        textAttributeProps.setTextTransform(getStringProp(reactStylesDiffMap, "textTransform"));
        textAttributeProps.setLayoutDirection(getStringProp(reactStylesDiffMap, "layoutDirection"));
        textAttributeProps.setAccessibilityRole(getStringProp(reactStylesDiffMap, "accessibilityRole"));
        return textAttributeProps;
    }

    public static int getTextAlignment(ReactStylesDiffMap reactStylesDiffMap, boolean z) {
        String string = reactStylesDiffMap.hasKey("textAlign") ? reactStylesDiffMap.getString("textAlign") : null;
        if ("justify".equals(string)) {
            return 3;
        }
        if (string == null || DebugKt.DEBUG_PROPERTY_VALUE_AUTO.equals(string)) {
            return 0;
        }
        if ("left".equals(string)) {
            if (!z) {
                return 3;
            }
        } else {
            if (!"right".equals(string)) {
                if ("center".equals(string)) {
                    return 1;
                }
                throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + string);
            }
            if (z) {
                return 3;
            }
        }
        return 5;
    }

    public static int getJustificationMode(ReactStylesDiffMap reactStylesDiffMap) {
        if (!"justify".equals(reactStylesDiffMap.hasKey("textAlign") ? reactStylesDiffMap.getString("textAlign") : null) || Build.VERSION.SDK_INT < 26) {
            return DEFAULT_JUSTIFICATION_MODE;
        }
        return 1;
    }

    private static boolean getBooleanProp(ReactStylesDiffMap reactStylesDiffMap, String str, boolean z) {
        return reactStylesDiffMap.hasKey(str) ? reactStylesDiffMap.getBoolean(str, z) : z;
    }

    private static String getStringProp(ReactStylesDiffMap reactStylesDiffMap, String str) {
        if (reactStylesDiffMap.hasKey(str)) {
            return reactStylesDiffMap.getString(str);
        }
        return null;
    }

    private static int getIntProp(ReactStylesDiffMap reactStylesDiffMap, String str, int i) {
        return reactStylesDiffMap.hasKey(str) ? reactStylesDiffMap.getInt(str, i) : i;
    }

    private static float getFloatProp(ReactStylesDiffMap reactStylesDiffMap, String str, float f) {
        return reactStylesDiffMap.hasKey(str) ? reactStylesDiffMap.getFloat(str, f) : f;
    }

    @Nullable
    private static ReadableArray getArrayProp(ReactStylesDiffMap reactStylesDiffMap, String str) {
        if (reactStylesDiffMap.hasKey(str)) {
            return reactStylesDiffMap.getArray(str);
        }
        return null;
    }

    public float getEffectiveLineHeight() {
        return !Float.isNaN(this.mLineHeight) && !Float.isNaN(this.mHeightOfTallestInlineImage) && (this.mHeightOfTallestInlineImage > this.mLineHeight ? 1 : (this.mHeightOfTallestInlineImage == this.mLineHeight ? 0 : -1)) > 0 ? this.mHeightOfTallestInlineImage : this.mLineHeight;
    }

    private void setNumberOfLines(int i) {
        if (i == 0) {
            i = -1;
        }
        this.mNumberOfLines = i;
    }

    private void setLineHeight(float f) {
        float pixelFromDIP;
        this.mLineHeightInput = f;
        if (f == -1.0f) {
            this.mLineHeight = Float.NaN;
            return;
        }
        if (this.mAllowFontScaling) {
            pixelFromDIP = PixelUtil.toPixelFromSP(f);
        } else {
            pixelFromDIP = PixelUtil.toPixelFromDIP(f);
        }
        this.mLineHeight = pixelFromDIP;
    }

    private void setLetterSpacing(float f) {
        this.mLetterSpacingInput = f;
    }

    public float getLetterSpacing() {
        float pixelFromDIP;
        if (this.mAllowFontScaling) {
            pixelFromDIP = PixelUtil.toPixelFromSP(this.mLetterSpacingInput);
        } else {
            pixelFromDIP = PixelUtil.toPixelFromDIP(this.mLetterSpacingInput);
        }
        int i = this.mFontSize;
        if (i > 0) {
            return pixelFromDIP / i;
        }
        throw new IllegalArgumentException("FontSize should be a positive value. Current value: " + this.mFontSize);
    }

    private void setAllowFontScaling(boolean z) {
        if (z != this.mAllowFontScaling) {
            this.mAllowFontScaling = z;
            setFontSize(this.mFontSizeInput);
            setLineHeight(this.mLineHeightInput);
            setLetterSpacing(this.mLetterSpacingInput);
        }
    }

    private void setFontSize(float f) {
        double dCeil;
        this.mFontSizeInput = f;
        if (f != -1.0f) {
            if (this.mAllowFontScaling) {
                dCeil = Math.ceil(PixelUtil.toPixelFromSP(f));
            } else {
                dCeil = Math.ceil(PixelUtil.toPixelFromDIP(f));
            }
            f = (float) dCeil;
        }
        this.mFontSize = (int) f;
    }

    private void setColor(@Nullable Integer num) {
        boolean z = num != null;
        this.mIsColorSet = z;
        if (z) {
            this.mColor = num.intValue();
        }
    }

    private void setBackgroundColor(Integer num) {
        boolean z = num != null;
        this.mIsBackgroundColorSet = z;
        if (z) {
            this.mBackgroundColor = num.intValue();
        }
    }

    private void setFontFamily(@Nullable String str) {
        this.mFontFamily = str;
    }

    private void setFontVariant(@Nullable ReadableArray readableArray) {
        this.mFontFeatureSettings = ReactTypefaceUtils.parseFontVariant(readableArray);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void setFontVariant(@Nullable MapBuffer mapBuffer) {
        if (mapBuffer == null || mapBuffer.getCount() == 0) {
            this.mFontFeatureSettings = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<MapBuffer.Entry> it = mapBuffer.iterator();
        while (it.hasNext()) {
            String stringValue = it.next().getStringValue();
            if (stringValue != null) {
                byte b = -1;
                switch (stringValue.hashCode()) {
                    case -1983120972:
                        if (stringValue.equals("stylistic-thirteen")) {
                            b = 0;
                        }
                        break;
                    case -1933522176:
                        if (stringValue.equals("stylistic-fifteen")) {
                            b = 1;
                        }
                        break;
                    case -1534462052:
                        if (stringValue.equals("stylistic-eighteen")) {
                            b = 2;
                        }
                        break;
                    case -1195362251:
                        if (stringValue.equals("proportional-nums")) {
                            b = 3;
                        }
                        break;
                    case -1061392823:
                        if (stringValue.equals("lining-nums")) {
                            b = 4;
                        }
                        break;
                    case -771984547:
                        if (stringValue.equals("tabular-nums")) {
                            b = 5;
                        }
                        break;
                    case -659678800:
                        if (stringValue.equals("oldstyle-nums")) {
                            b = 6;
                        }
                        break;
                    case 296506098:
                        if (stringValue.equals("stylistic-eight")) {
                            b = 7;
                        }
                        break;
                    case 309330544:
                        if (stringValue.equals("stylistic-seven")) {
                            b = 8;
                        }
                        break;
                    case 310339585:
                        if (stringValue.equals("stylistic-three")) {
                            b = 9;
                        }
                        break;
                    case 604478526:
                        if (stringValue.equals("stylistic-eleven")) {
                            b = 10;
                        }
                        break;
                    case 979426287:
                        if (stringValue.equals("stylistic-five")) {
                            b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                        }
                        break;
                    case 979432035:
                        if (stringValue.equals("stylistic-four")) {
                            b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                        }
                        break;
                    case 979664367:
                        if (stringValue.equals("stylistic-nine")) {
                            b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                        }
                        break;
                    case 1001434505:
                        if (stringValue.equals("stylistic-one")) {
                            b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                        }
                        break;
                    case 1001438213:
                        if (stringValue.equals("stylistic-six")) {
                            b = 15;
                        }
                        break;
                    case 1001439040:
                        if (stringValue.equals("stylistic-ten")) {
                            b = 16;
                        }
                        break;
                    case 1001439599:
                        if (stringValue.equals("stylistic-two")) {
                            b = 17;
                        }
                        break;
                    case 1030714463:
                        if (stringValue.equals("stylistic-sixteen")) {
                            b = 18;
                        }
                        break;
                    case 1044065430:
                        if (stringValue.equals("stylistic-twelve")) {
                            b = 19;
                        }
                        break;
                    case 1044067310:
                        if (stringValue.equals("stylistic-twenty")) {
                            b = 20;
                        }
                        break;
                    case 1183323111:
                        if (stringValue.equals("small-caps")) {
                            b = 21;
                        }
                        break;
                    case 1463562569:
                        if (stringValue.equals("stylistic-nineteen")) {
                            b = 22;
                        }
                        break;
                    case 1648446397:
                        if (stringValue.equals("stylistic-fourteen")) {
                            b = 23;
                        }
                        break;
                    case 2097122634:
                        if (stringValue.equals("stylistic-seventeen")) {
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
        this.mFontFeatureSettings = TextUtils.join(", ", arrayList);
    }

    private void setFontWeight(@Nullable String str) {
        this.mFontWeight = ReactTypefaceUtils.parseFontWeight(str);
    }

    private void setFontStyle(@Nullable String str) {
        this.mFontStyle = ReactTypefaceUtils.parseFontStyle(str);
    }

    private void setIncludeFontPadding(boolean z) {
        this.mIncludeFontPadding = z;
    }

    private void setTextDecorationLine(@Nullable String str) {
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        if (str != null) {
            for (String str2 : str.split("-")) {
                if ("underline".equals(str2)) {
                    this.mIsUnderlineTextDecorationSet = true;
                } else if ("strikethrough".equals(str2)) {
                    this.mIsLineThroughTextDecorationSet = true;
                }
            }
        }
    }

    private void setTextShadowOffset(ReadableMap readableMap) {
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        if (readableMap != null) {
            if (readableMap.hasKey("width") && !readableMap.isNull("width")) {
                this.mTextShadowOffsetDx = PixelUtil.toPixelFromDIP(readableMap.getDouble("width"));
            }
            if (!readableMap.hasKey("height") || readableMap.isNull("height")) {
                return;
            }
            this.mTextShadowOffsetDy = PixelUtil.toPixelFromDIP(readableMap.getDouble("height"));
        }
    }

    public static int getLayoutDirection(@Nullable String str) {
        if (str == null || SQLiteCommandFactory.UNDEFINED.equals(str)) {
            return -1;
        }
        if ("rtl".equals(str)) {
            return 1;
        }
        if ("ltr".equals(str)) {
            return 0;
        }
        throw new JSApplicationIllegalArgumentException("Invalid layoutDirection: " + str);
    }

    private void setLayoutDirection(@Nullable String str) {
        this.mLayoutDirection = getLayoutDirection(str);
    }

    private void setTextShadowRadius(float f) {
        if (f != this.mTextShadowRadius) {
            this.mTextShadowRadius = f;
        }
    }

    private void setTextShadowColor(int i) {
        if (i != this.mTextShadowColor) {
            this.mTextShadowColor = i;
        }
    }

    private void setTextTransform(@Nullable String str) {
        if (str == null || "none".equals(str)) {
            this.mTextTransform = TextTransform.NONE;
            return;
        }
        if ("uppercase".equals(str)) {
            this.mTextTransform = TextTransform.UPPERCASE;
            return;
        }
        if ("lowercase".equals(str)) {
            this.mTextTransform = TextTransform.LOWERCASE;
        } else {
            if ("capitalize".equals(str)) {
                this.mTextTransform = TextTransform.CAPITALIZE;
                return;
            }
            throw new JSApplicationIllegalArgumentException("Invalid textTransform: " + str);
        }
    }

    private void setAccessibilityRole(@Nullable String str) {
        if (str != null) {
            this.mIsAccessibilityRoleSet = true;
            ReactAccessibilityDelegate.AccessibilityRole accessibilityRoleFromValue = ReactAccessibilityDelegate.AccessibilityRole.fromValue(str);
            this.mAccessibilityRole = accessibilityRoleFromValue;
            this.mIsAccessibilityLink = accessibilityRoleFromValue.equals(ReactAccessibilityDelegate.AccessibilityRole.LINK);
        }
    }

    public static int getTextBreakStrategy(@Nullable String str) {
        int i = DEFAULT_BREAK_STRATEGY;
        if (str == null) {
            return i;
        }
        if (str.equals("balanced")) {
            return 2;
        }
        return !str.equals(FtsOptions.TOKENIZER_SIMPLE) ? 1 : 0;
    }

    public static int getHyphenationFrequency(@Nullable String str) {
        int i = DEFAULT_HYPHENATION_FREQUENCY;
        if (str == null) {
            return i;
        }
        if (str.equals("normal")) {
            return 1;
        }
        return !str.equals("none") ? 2 : 0;
    }
}
