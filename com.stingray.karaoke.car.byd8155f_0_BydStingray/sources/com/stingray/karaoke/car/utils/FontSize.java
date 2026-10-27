package com.stingray.karaoke.car.utils;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0019\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001b¨\u0006\u001c"}, d2 = {"Lcom/stingray/karaoke/car/utils/FontSize;", "", "baseMultiplier", "", "<init>", "(Ljava/lang/String;IF)V", "getBaseMultiplier", "()F", "H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "Subtitle1", "Subtitle2", "Body1", "Body2", "ButtonSm", "ButtonMd", "ButtonLg", "Caption", "Overline", "NavLabel", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FontSize {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ FontSize[] $VALUES;
    private final float baseMultiplier;

    /* JADX INFO: renamed from: H1, reason: collision with root package name */
    public static final FontSize f13035H1 = new FontSize("H1", 0, 4.8f);

    /* JADX INFO: renamed from: H2, reason: collision with root package name */
    public static final FontSize f13036H2 = new FontSize("H2", 1, 3.8f);

    /* JADX INFO: renamed from: H3, reason: collision with root package name */
    public static final FontSize f13037H3 = new FontSize("H3", 2, 3.0f);

    /* JADX INFO: renamed from: H4, reason: collision with root package name */
    public static final FontSize f13038H4 = new FontSize("H4", 3, 2.7f);

    /* JADX INFO: renamed from: H5, reason: collision with root package name */
    public static final FontSize f13039H5 = new FontSize("H5", 4, 2.4f);

    /* JADX INFO: renamed from: H6, reason: collision with root package name */
    public static final FontSize f13040H6 = new FontSize("H6", 5, 2.0f);

    /* JADX INFO: renamed from: H7, reason: collision with root package name */
    public static final FontSize f13041H7 = new FontSize("H7", 6, 1.7f);

    /* JADX INFO: renamed from: H8, reason: collision with root package name */
    public static final FontSize f13042H8 = new FontSize("H8", 7, 1.5f);
    public static final FontSize H9 = new FontSize("H9", 8, 1.2f);
    public static final FontSize H10 = new FontSize("H10", 9, 1.1f);
    public static final FontSize Subtitle1 = new FontSize("Subtitle1", 10, 1.0f);
    public static final FontSize Subtitle2 = new FontSize("Subtitle2", 11, 0.9f);
    public static final FontSize Body1 = new FontSize("Body1", 12, 0.8f);
    public static final FontSize Body2 = new FontSize("Body2", 13, 0.7f);
    public static final FontSize ButtonSm = new FontSize("ButtonSm", 14, 0.8f);
    public static final FontSize ButtonMd = new FontSize("ButtonMd", 15, 1.2f);
    public static final FontSize ButtonLg = new FontSize("ButtonLg", 16, 1.5f);
    public static final FontSize Caption = new FontSize("Caption", 17, 0.6f);
    public static final FontSize Overline = new FontSize("Overline", 18, 0.5f);
    public static final FontSize NavLabel = new FontSize("NavLabel", 19, 0.5f);

    private static final /* synthetic */ FontSize[] $values() {
        return new FontSize[]{f13035H1, f13036H2, f13037H3, f13038H4, f13039H5, f13040H6, f13041H7, f13042H8, H9, H10, Subtitle1, Subtitle2, Body1, Body2, ButtonSm, ButtonMd, ButtonLg, Caption, Overline, NavLabel};
    }

    static {
        FontSize[] fontSizeArr$values = $values();
        $VALUES = fontSizeArr$values;
        $ENTRIES = r.e(fontSizeArr$values);
    }

    private FontSize(String str, int i7, float f10) {
        this.baseMultiplier = f10;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static FontSize valueOf(String str) {
        return (FontSize) Enum.valueOf(FontSize.class, str);
    }

    public static FontSize[] values() {
        return (FontSize[]) $VALUES.clone();
    }

    public final float getBaseMultiplier() {
        return this.baseMultiplier;
    }
}
