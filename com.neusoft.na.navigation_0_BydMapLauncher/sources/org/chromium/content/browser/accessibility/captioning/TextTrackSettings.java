package org.chromium.content.browser.accessibility.captioning;

import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class TextTrackSettings {
    private static final String DEFAULT_VALUE = "";
    private String mTextTrackBackgroundColor;
    private String mTextTrackFontFamily;
    private String mTextTrackFontStyle;
    private String mTextTrackFontVariant;
    private String mTextTrackTextColor;
    private String mTextTrackTextShadow;
    private String mTextTrackTextSize;
    private boolean mTextTracksEnabled;

    public TextTrackSettings() {
    }

    public TextTrackSettings(boolean z, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.mTextTracksEnabled = z;
        this.mTextTrackBackgroundColor = str;
        this.mTextTrackFontFamily = str2;
        this.mTextTrackFontStyle = str3;
        this.mTextTrackFontVariant = str4;
        this.mTextTrackTextColor = str5;
        this.mTextTrackTextShadow = str6;
        this.mTextTrackTextSize = str7;
    }

    public boolean getTextTracksEnabled() {
        return this.mTextTracksEnabled;
    }

    public String getTextTrackBackgroundColor() {
        return Objects.toString(this.mTextTrackBackgroundColor, "");
    }

    public String getTextTrackFontFamily() {
        return Objects.toString(this.mTextTrackFontFamily, "");
    }

    public String getTextTrackFontStyle() {
        return Objects.toString(this.mTextTrackFontStyle, "");
    }

    public String getTextTrackFontVariant() {
        return Objects.toString(this.mTextTrackFontVariant, "");
    }

    public String getTextTrackTextColor() {
        return Objects.toString(this.mTextTrackTextColor, "");
    }

    public String getTextTrackTextShadow() {
        return Objects.toString(this.mTextTrackTextShadow, "");
    }

    public String getTextTrackTextSize() {
        return Objects.toString(this.mTextTrackTextSize, "");
    }
}
