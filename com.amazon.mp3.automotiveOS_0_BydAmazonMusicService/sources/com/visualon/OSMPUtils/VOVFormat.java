package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public class VOVFormat {
    public final String codec;
    public final double frameRate;
    public final int height;
    public final int width;

    public VOVFormat(String str, int i, int i2, double d) {
        this.codec = str;
        this.width = i;
        this.height = i2;
        this.frameRate = d;
    }

    public static VOVFormat parse(Parcel parcel) {
        String str;
        if (parcel == null) {
            return null;
        }
        parcel.setDataPosition(0);
        String string = parcel.readString();
        if (string == null || !string.contains(",")) {
            str = string;
        } else {
            for (String str2 : string.split(",")) {
                String mediaMimeType = voMimeTypes.getMediaMimeType(str2);
                if (mediaMimeType != null && voMimeTypes.isVideo(mediaMimeType)) {
                    str = str2;
                    break;
                }
            }
            str = string;
        }
        VOVFormat vOVFormat = new VOVFormat(str, parcel.readInt(), parcel.readInt(), parcel.readDouble());
        parcel.recycle();
        return vOVFormat;
    }
}
