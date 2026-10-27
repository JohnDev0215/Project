package com.amazon.avod.media;

import com.visualon.OSMPUtils.voMimeTypes;

/* JADX INFO: loaded from: classes2.dex */
public class MimeTypes {
    private MimeTypes() {
    }

    public static boolean isImageType(String str) {
        return str.equals("image/jpeg") || str.equals("image/png");
    }

    public static boolean isVideoType(String str) {
        return str.equals(voMimeTypes.VOVIDEO_MP4) || str.equals(voMimeTypes.VOVIDEO_MPEG) || str.equals("video/aiv-asin");
    }
}
