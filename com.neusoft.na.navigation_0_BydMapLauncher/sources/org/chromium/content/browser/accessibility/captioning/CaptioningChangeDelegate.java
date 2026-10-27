package org.chromium.content.browser.accessibility.captioning;

import android.graphics.Color;
import android.graphics.Typeface;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;
import org.chromium.content.browser.accessibility.captioning.SystemCaptioningBridge;

/* JADX INFO: loaded from: classes3.dex */
public class CaptioningChangeDelegate {
    public static final String DEFAULT_CAPTIONING_PREF_VALUE = "";
    private static final String FONT_STYLE_ITALIC = "italic";
    private final Map<SystemCaptioningBridge.SystemCaptioningBridgeListener, Boolean> mListeners = new WeakHashMap();
    private String mTextTrackBackgroundColor;
    private String mTextTrackFontFamily;
    private String mTextTrackFontStyle;
    private String mTextTrackFontVariant;
    private String mTextTrackTextColor;
    private String mTextTrackTextShadow;
    private String mTextTrackTextSize;
    private boolean mTextTracksEnabled;

    public void onLocaleChanged(Locale locale) {
    }

    public void onEnabledChanged(boolean z) {
        this.mTextTracksEnabled = z;
        notifySettingsChanged();
    }

    public void onFontScaleChanged(float f) {
        this.mTextTrackTextSize = androidFontScaleToPercentage(f);
        notifySettingsChanged();
    }

    public void onUserStyleChanged(CaptioningStyle captioningStyle) {
        this.mTextTrackTextColor = androidColorToCssColor(captioningStyle.getForegroundColor());
        this.mTextTrackBackgroundColor = androidColorToCssColor(captioningStyle.getBackgroundColor());
        this.mTextTrackTextShadow = ClosedCaptionEdgeAttribute.fromSystemEdgeAttribute(captioningStyle.getEdgeType(), androidColorToCssColor(captioningStyle.getEdgeColor())).getTextShadow();
        Typeface typeface = captioningStyle.getTypeface();
        this.mTextTrackFontFamily = ClosedCaptionFont.fromSystemFont(typeface).getFontFamily();
        if (typeface != null && typeface.isItalic()) {
            this.mTextTrackFontStyle = FONT_STYLE_ITALIC;
        } else {
            this.mTextTrackFontStyle = "";
        }
        this.mTextTrackFontVariant = "";
        notifySettingsChanged();
    }

    public enum ClosedCaptionEdgeAttribute {
        NONE(""),
        OUTLINE("%2$s %2$s 0 %1$s, -%2$s -%2$s 0 %1$s, %2$s -%2$s 0 %1$s, -%2$s %2$s 0 %1$s"),
        DROP_SHADOW("%1$s %2$s %2$s 0.1em"),
        RAISED("-%2$s -%2$s 0 %1$s"),
        DEPRESSED("%2$s %2$s 0 %1$s");

        private static String sEdgeColor;
        private final String mTextShadow;
        private static String sDefaultEdgeColor = "silver";
        private static String sShadowOffset = "0.05em";

        ClosedCaptionEdgeAttribute(String str) {
            this.mTextShadow = str;
        }

        public static ClosedCaptionEdgeAttribute fromSystemEdgeAttribute(Integer num, String str) {
            if (num == null) {
                return NONE;
            }
            if (str == null || str.isEmpty()) {
                sEdgeColor = sDefaultEdgeColor;
            } else {
                sEdgeColor = str;
            }
            int iIntValue = num.intValue();
            if (iIntValue == 1) {
                return OUTLINE;
            }
            if (iIntValue == 2) {
                return DROP_SHADOW;
            }
            if (iIntValue == 3) {
                return RAISED;
            }
            if (iIntValue == 4) {
                return DEPRESSED;
            }
            return NONE;
        }

        public static void setShadowOffset(String str) {
            sShadowOffset = str;
        }

        public static void setDefaultEdgeColor(String str) {
            sDefaultEdgeColor = str;
        }

        public String getTextShadow() {
            return String.format(this.mTextShadow, sEdgeColor, sShadowOffset);
        }
    }

    public enum ClosedCaptionFont {
        DEFAULT("", EnumSet.noneOf(Flags.class)),
        SANS_SERIF("sans-serif", EnumSet.of(Flags.SANS_SERIF)),
        SANS_SERIF_CONDENSED("sans-serif-condensed", EnumSet.of(Flags.SANS_SERIF)),
        SANS_SERIF_MONOSPACE("sans-serif-monospace", EnumSet.of(Flags.SANS_SERIF, Flags.MONOSPACE)),
        SERIF("serif", EnumSet.of(Flags.SERIF)),
        SERIF_MONOSPACE("serif-monospace", EnumSet.of(Flags.SERIF, Flags.MONOSPACE)),
        CASUAL("casual", EnumSet.noneOf(Flags.class)),
        CURSIVE("cursive", EnumSet.noneOf(Flags.class)),
        SANS_SERIF_SMALLCAPS("sans-serif-smallcaps", EnumSet.of(Flags.SANS_SERIF)),
        MONOSPACE("monospace", EnumSet.of(Flags.MONOSPACE));

        final EnumSet<Flags> mFlags;
        private final String mFontFamily;

        enum Flags {
            SANS_SERIF,
            SERIF,
            MONOSPACE
        }

        ClosedCaptionFont(String str, EnumSet enumSet) {
            this.mFontFamily = str;
            this.mFlags = enumSet;
        }

        public static ClosedCaptionFont fromSystemFont(Typeface typeface) {
            if (typeface == null) {
                return DEFAULT;
            }
            for (ClosedCaptionFont closedCaptionFont : values()) {
                if (belongsToFontFamily(typeface, closedCaptionFont)) {
                    return closedCaptionFont;
                }
            }
            return DEFAULT;
        }

        private static boolean belongsToFontFamily(Typeface typeface, ClosedCaptionFont closedCaptionFont) {
            return Typeface.create(closedCaptionFont.getFontFamily(), typeface.getStyle()).equals(typeface);
        }

        public String getFontFamily() {
            return this.mFontFamily;
        }
    }

    public static String androidColorToCssColor(Integer num) {
        return num == null ? "" : String.format("rgba(%s, %s, %s, %s)", Integer.valueOf(Color.red(num.intValue())), Integer.valueOf(Color.green(num.intValue())), Integer.valueOf(Color.blue(num.intValue())), new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US)).format(((double) Color.alpha(num.intValue())) / 255.0d));
    }

    public static String androidFontScaleToPercentage(float f) {
        return new DecimalFormat("#%", new DecimalFormatSymbols(Locale.US)).format(f);
    }

    private void notifySettingsChanged() {
        Iterator<SystemCaptioningBridge.SystemCaptioningBridgeListener> it = this.mListeners.keySet().iterator();
        while (it.hasNext()) {
            notifyListener(it.next());
        }
    }

    public void notifyListener(SystemCaptioningBridge.SystemCaptioningBridgeListener systemCaptioningBridgeListener) {
        if (this.mTextTracksEnabled) {
            systemCaptioningBridgeListener.onSystemCaptioningChanged(new TextTrackSettings(this.mTextTracksEnabled, this.mTextTrackBackgroundColor, this.mTextTrackFontFamily, this.mTextTrackFontStyle, this.mTextTrackFontVariant, this.mTextTrackTextColor, this.mTextTrackTextShadow, this.mTextTrackTextSize));
        } else {
            systemCaptioningBridgeListener.onSystemCaptioningChanged(new TextTrackSettings());
        }
    }

    public void addListener(SystemCaptioningBridge.SystemCaptioningBridgeListener systemCaptioningBridgeListener) {
        this.mListeners.put(systemCaptioningBridgeListener, null);
    }

    public void removeListener(SystemCaptioningBridge.SystemCaptioningBridgeListener systemCaptioningBridgeListener) {
        this.mListeners.remove(systemCaptioningBridgeListener);
    }

    public boolean hasActiveListener() {
        return !this.mListeners.isEmpty();
    }
}
