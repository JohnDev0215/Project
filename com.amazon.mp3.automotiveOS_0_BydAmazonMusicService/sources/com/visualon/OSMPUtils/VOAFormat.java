package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public class VOAFormat {
    public final int channelCount;
    public final String codec;
    public final int sampleRate;

    public VOAFormat(String str, int i, int i2) {
        this.codec = str;
        this.sampleRate = i;
        this.channelCount = i2;
    }

    public static VOAFormat parse(Parcel parcel) {
        if (parcel == null) {
            return null;
        }
        int i = 0;
        parcel.setDataPosition(0);
        String string = parcel.readString();
        if (string != null && string.contains(",")) {
            String[] strArrSplit = string.split(",");
            int length = strArrSplit.length;
            while (true) {
                if (i < length) {
                    String str = strArrSplit[i];
                    String mediaMimeType = voMimeTypes.getMediaMimeType(str);
                    if (mediaMimeType != null && voMimeTypes.isAudio(mediaMimeType)) {
                        string = str;
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
        VOAFormat vOAFormat = new VOAFormat(string, parcel.readInt(), parcel.readInt());
        parcel.recycle();
        return vOAFormat;
    }
}
