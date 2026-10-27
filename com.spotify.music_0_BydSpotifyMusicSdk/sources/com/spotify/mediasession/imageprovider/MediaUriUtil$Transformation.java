package com.spotify.mediasession.imageprovider;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class MediaUriUtil$Transformation {
    public static final MediaUriUtil$Transformation c;
    public static final MediaUriUtil$Transformation e;
    public static final MediaUriUtil$Transformation k;
    public static final /* synthetic */ MediaUriUtil$Transformation[] l;

    static {
        MediaUriUtil$Transformation mediaUriUtil$Transformation = new MediaUriUtil$Transformation("CIRCULAR", 0);
        c = mediaUriUtil$Transformation;
        MediaUriUtil$Transformation mediaUriUtil$Transformation2 = new MediaUriUtil$Transformation("ROUNDED_CORNER", 1);
        e = mediaUriUtil$Transformation2;
        MediaUriUtil$Transformation mediaUriUtil$Transformation3 = new MediaUriUtil$Transformation("NONE", 2);
        k = mediaUriUtil$Transformation3;
        l = new MediaUriUtil$Transformation[]{mediaUriUtil$Transformation, mediaUriUtil$Transformation2, mediaUriUtil$Transformation3};
    }

    public static MediaUriUtil$Transformation valueOf(String str) {
        return (MediaUriUtil$Transformation) Enum.valueOf(MediaUriUtil$Transformation.class, str);
    }

    public static MediaUriUtil$Transformation[] values() {
        return (MediaUriUtil$Transformation[]) l.clone();
    }
}
