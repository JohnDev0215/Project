package com.visualon.OSMPSubTitle;

import android.graphics.Typeface;

/* JADX INFO: loaded from: classes7.dex */
public interface voSubTitleFormatSetting {
    public static final int VOOSMP_FontStyle_Arial = 16;
    public static final int VOOSMP_FontStyle_Coronet = 18;
    public static final int VOOSMP_FontStyle_Courier = 14;
    public static final int VOOSMP_FontStyle_Customer = -1;
    public static final int VOOSMP_FontStyle_Default = 0;
    public static final int VOOSMP_FontStyle_Default_Casual = 5;
    public static final int VOOSMP_FontStyle_Default_Cursive = 6;
    public static final int VOOSMP_FontStyle_Default_Monospaced_without_serifs = 3;
    public static final int VOOSMP_FontStyle_Default_Proportionally_spaced_with_serifs = 2;
    public static final int VOOSMP_FontStyle_Default_Proportionally_spaced_without_serifs = 4;
    public static final int VOOSMP_FontStyle_Default_Small_capitals = 7;
    public static final int VOOSMP_FontStyle_Dom = 17;
    public static final int VOOSMP_FontStyle_Gothic = 19;
    public static final int VOOSMP_FontStyle_Helvetica = 15;
    public static final int VOOSMP_FontStyle_MonospaceSansSerif = 20;
    public static final int VOOSMP_FontStyle_Monospaced = 8;
    public static final int VOOSMP_FontStyle_ProportionalSansSerif = 11;
    public static final int VOOSMP_FontStyle_ProportionalSerif = 12;
    public static final int VOOSMP_FontStyle_SansSerif = 9;
    public static final int VOOSMP_FontStyle_Serif = 10;
    public static final int VOOSMP_FontStyle_Times_New_Roman = 13;
    public static final int VOOSMP_Monospaced_with_serifs = 1;

    void reset();

    void setBackgroundColor(int i);

    void setBackgroundOpacity(int i);

    void setEdgeColor(int i);

    void setEdgeOpacity(int i);

    void setEdgeType(int i);

    void setFontBold(boolean z);

    void setFontColor(int i);

    void setFontItalic(boolean z);

    void setFontName(String str);

    void setFontOpacity(int i);

    void setFontSizeScale(int i);

    void setFontStyle(int i);

    void setFontUnderline(boolean z);

    void setSubtitleBoundingBox(int i, int i2, int i3, int i4, boolean z);

    void setSubtitleGravity(int i, int i2);

    void setSubtitleTrim(String str);

    void setTypeface(Typeface typeface);

    void setWindowColor(int i);

    void setWindowOpacity(int i);
}
