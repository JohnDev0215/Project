package com.amazon.mp3.web;

/* JADX INFO: loaded from: classes4.dex */
public class ATCUrls {
    public static String getProdUrl() {
        return "https://artist.prod.taste.music.a2z.com/";
    }

    public static boolean isATCUrl(final String url) {
        return "https://artist.beta.taste.music.a2z.com/".equals(url) || "https://artist.prod.taste.music.a2z.com/".equals(url);
    }
}
