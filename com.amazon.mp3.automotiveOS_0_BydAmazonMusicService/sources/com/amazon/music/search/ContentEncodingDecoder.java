package com.amazon.music.search;

import com.amazon.music.contentEncoding.ContentEncoding;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ContentEncodingDecoder {
    public static List<ContentEncoding> loadFrom(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        if (list.contains("hdAvailable")) {
            arrayList.add(ContentEncoding.HD);
        }
        if (list.contains("uhdAvailable")) {
            arrayList.add(ContentEncoding.UHD);
        }
        if (list.contains("immersive")) {
            arrayList.add(ContentEncoding.IMMERSIVE);
        }
        if (list.contains("ra360Available")) {
            arrayList.add(ContentEncoding.SONY_360);
        }
        if (list.contains("atmosAvailable")) {
            arrayList.add(ContentEncoding.DOLBY_ATMOS);
        }
        return arrayList;
    }
}
