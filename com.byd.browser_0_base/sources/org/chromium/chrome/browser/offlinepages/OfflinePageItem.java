package org.chromium.chrome.browser.offlinepages;

/* JADX INFO: loaded from: classes2.dex */
public final class OfflinePageItem {
    public final String a;
    public final long b;
    public final ClientId c;
    public final String d;
    public final String e;
    public final long f;
    public final long g;
    public final int h;
    public final long i;
    public final String j;

    public OfflinePageItem(String str, long j, String str2, String str3, String str4, String str5, long j2, long j3, int i, long j4, String str6) {
        this.a = str;
        this.b = j;
        this.c = new ClientId(str2, str3);
        this.d = str4;
        this.e = str5;
        this.f = j2;
        this.g = j3;
        this.h = i;
        this.i = j4;
        this.j = str6;
    }
}
