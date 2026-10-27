package com.stingray.kca.model;

import E5.o;
import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u0010"}, d2 = {"Lcom/stingray/kca/model/ImageType;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "NONE", "PROMOSMALL", "PROMOLARGE", "THUMBNAILSQUARE", "THUMBNAILSQUAREFOREGROUND", "UnknownDefaultOpenApi", "toString", "Companion", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ImageType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ ImageType[] $VALUES;
    private final String value;

    @o(name = "NONE")
    public static final ImageType NONE = new ImageType("NONE", 0, "NONE");

    @o(name = "PROMO_SMALL")
    public static final ImageType PROMOSMALL = new ImageType("PROMOSMALL", 1, "PROMO_SMALL");

    @o(name = "PROMO_LARGE")
    public static final ImageType PROMOLARGE = new ImageType("PROMOLARGE", 2, "PROMO_LARGE");

    @o(name = "THUMBNAIL_SQUARE")
    public static final ImageType THUMBNAILSQUARE = new ImageType("THUMBNAILSQUARE", 3, "THUMBNAIL_SQUARE");

    @o(name = "THUMBNAIL_SQUARE_FOREGROUND")
    public static final ImageType THUMBNAILSQUAREFOREGROUND = new ImageType("THUMBNAILSQUAREFOREGROUND", 4, "THUMBNAIL_SQUARE_FOREGROUND");

    @o(name = "unknown_default_open_api")
    public static final ImageType UnknownDefaultOpenApi = new ImageType("UnknownDefaultOpenApi", 5, "unknown_default_open_api");

    private static final /* synthetic */ ImageType[] $values() {
        return new ImageType[]{NONE, PROMOSMALL, PROMOLARGE, THUMBNAILSQUARE, THUMBNAILSQUAREFOREGROUND, UnknownDefaultOpenApi};
    }

    static {
        ImageType[] imageTypeArr$values = $values();
        $VALUES = imageTypeArr$values;
        $ENTRIES = r.e(imageTypeArr$values);
        INSTANCE = new Companion(0);
    }

    private ImageType(String str, int i7, String str2) {
        this.value = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static ImageType valueOf(String str) {
        return (ImageType) Enum.valueOf(ImageType.class, str);
    }

    public static ImageType[] values() {
        return (ImageType[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
