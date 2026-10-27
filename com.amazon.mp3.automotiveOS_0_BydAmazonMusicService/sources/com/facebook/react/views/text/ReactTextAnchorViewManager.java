package com.facebook.react.views.text;

import android.text.TextUtils;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.yoga.YogaConstants;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ReactTextAnchorViewManager<T extends View, C extends ReactBaseTextShadowNode> extends BaseViewManager<T, C> {
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    private static final String TAG = "ReactTextAnchorViewManager";

    @ReactProp(name = "accessible")
    public void setAccessible(ReactTextView reactTextView, boolean z) {
        reactTextView.setFocusable(z);
    }

    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(ReactTextView reactTextView, int i) {
        reactTextView.setNumberOfLines(i);
    }

    @ReactProp(name = "ellipsizeMode")
    public void setEllipsizeMode(ReactTextView reactTextView, @Nullable String str) {
        if (str == null || str.equals("tail")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.END);
            return;
        }
        if (str.equals("head")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.START);
            return;
        }
        if (str.equals("middle")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.MIDDLE);
        } else {
            if (str.equals("clip")) {
                reactTextView.setEllipsizeLocation(null);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Invalid ellipsizeMode: " + str);
        }
    }

    @ReactProp(name = "adjustsFontSizeToFit")
    public void setAdjustFontSizeToFit(ReactTextView reactTextView, boolean z) {
        reactTextView.setAdjustFontSizeToFit(z);
    }

    @ReactProp(name = "textAlignVertical")
    public void setTextAlignVertical(ReactTextView reactTextView, @Nullable String str) {
        if (str == null || DebugKt.DEBUG_PROPERTY_VALUE_AUTO.equals(str)) {
            reactTextView.setGravityVertical(0);
            return;
        }
        if ("top".equals(str)) {
            reactTextView.setGravityVertical(48);
            return;
        }
        if ("bottom".equals(str)) {
            reactTextView.setGravityVertical(80);
        } else {
            if ("center".equals(str)) {
                reactTextView.setGravityVertical(16);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Invalid textAlignVertical: " + str);
        }
    }

    @ReactProp(name = "selectable")
    public void setSelectable(ReactTextView reactTextView, boolean z) {
        reactTextView.setTextIsSelectable(z);
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactTextView reactTextView, @Nullable Integer num) {
        if (num == null) {
            reactTextView.setHighlightColor(DefaultStyleValuesUtil.getDefaultTextColorHighlight(reactTextView.getContext()));
        } else {
            reactTextView.setHighlightColor(num.intValue());
        }
    }

    @ReactProp(name = "android_hyphenationFrequency")
    public void setAndroidHyphenationFrequency(ReactTextView reactTextView, @Nullable String str) {
        if (str == null || str.equals("none")) {
            reactTextView.setHyphenationFrequency(0);
            return;
        }
        if (str.equals("full")) {
            reactTextView.setHyphenationFrequency(2);
        } else {
            if (str.equals("normal")) {
                reactTextView.setHyphenationFrequency(1);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Invalid android_hyphenationFrequency: " + str);
        }
    }

    @ReactPropGroup(defaultFloat = FloatCompanionObject.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactTextView reactTextView, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (i == 0) {
            reactTextView.setBorderRadius(f);
        } else {
            reactTextView.setBorderRadius(f, i - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactTextView reactTextView, @Nullable String str) {
        reactTextView.setBorderStyle(str);
    }

    @ReactPropGroup(defaultFloat = FloatCompanionObject.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactTextView reactTextView, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        reactTextView.setBorderWidth(SPACING_TYPES[i], f);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactTextView reactTextView, int i, Integer num) {
        reactTextView.setBorderColor(SPACING_TYPES[i], num == null ? Float.NaN : num.intValue() & ViewCompat.MEASURED_SIZE_MASK, num != null ? num.intValue() >>> 24 : Float.NaN);
    }

    @ReactProp(defaultBoolean = true, name = "includeFontPadding")
    public void setIncludeFontPadding(ReactTextView reactTextView, boolean z) {
        reactTextView.setIncludeFontPadding(z);
    }

    @ReactProp(defaultBoolean = false, name = "disabled")
    public void setDisabled(ReactTextView reactTextView, boolean z) {
        reactTextView.setEnabled(!z);
    }

    @ReactProp(name = "dataDetectorType")
    public void setDataDetectorType(ReactTextView reactTextView, @Nullable String str) {
        if (str != null) {
            switch (str) {
                case "phoneNumber":
                    reactTextView.setLinkifyMask(4);
                    break;
                case "all":
                    reactTextView.setLinkifyMask(15);
                    break;
                case "link":
                    reactTextView.setLinkifyMask(1);
                    break;
                case "email":
                    reactTextView.setLinkifyMask(2);
                    break;
            }
            return;
        }
        reactTextView.setLinkifyMask(0);
    }

    @ReactProp(name = "onInlineViewLayout")
    public void setNotifyOnInlineViewLayout(ReactTextView reactTextView, boolean z) {
        reactTextView.setNotifyOnInlineViewLayout(z);
    }
}
