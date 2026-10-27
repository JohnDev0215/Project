package org.chromium.chrome.browser.download;

import android.graphics.Bitmap;
import defpackage.n3;
import defpackage.ny;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public final class DownloadInfo {
    public final boolean A;
    public final boolean B;
    public final boolean C;
    public final Bitmap D;
    public final int E;
    public final int F;
    public final boolean G;
    public final GURL a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final GURL h;
    public final GURL i;
    public final long j;
    public final long k;
    public final String l;
    public final boolean m;
    public final String n;
    public final boolean o;
    public final OfflineItem.a p;
    public final long q;
    public final boolean r;
    public final boolean s;
    public final boolean t;
    public final OTRProfileID u;
    public final boolean v;
    public final int w;
    public final long x;
    public final boolean y;
    public final ny z;

    public static class a {
        public boolean B;
        public boolean C;
        public Bitmap D;
        public int E;
        public int F;
        public boolean G;
        public GURL a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public GURL h;
        public GURL i;
        public long j;
        public long k;
        public boolean l;
        public String m;
        public boolean n;
        public String o;
        public long q;
        public boolean s;
        public boolean t;
        public OTRProfileID u;
        public boolean v;
        public long x;
        public boolean y;
        public ny z;
        public OfflineItem.a p = new OfflineItem.a(0, null, 2);
        public boolean r = true;
        public int w = 0;
        public boolean A = true;

        public static a b(DownloadInfo downloadInfo) {
            a aVar = new a();
            aVar.a = downloadInfo.a;
            aVar.b = downloadInfo.b;
            aVar.c = downloadInfo.c;
            aVar.d = downloadInfo.d;
            aVar.e = downloadInfo.e;
            aVar.f = downloadInfo.f;
            aVar.g = downloadInfo.g;
            aVar.h = downloadInfo.h;
            aVar.i = downloadInfo.i;
            aVar.j = downloadInfo.j;
            aVar.k = downloadInfo.k;
            aVar.m = downloadInfo.l;
            aVar.n = downloadInfo.m;
            aVar.o = downloadInfo.n;
            aVar.l = downloadInfo.o;
            aVar.p = downloadInfo.p;
            aVar.q = downloadInfo.q;
            aVar.y = downloadInfo.y;
            aVar.r = downloadInfo.r;
            aVar.s = downloadInfo.s;
            aVar.c(downloadInfo.u);
            aVar.v = downloadInfo.v;
            aVar.w = downloadInfo.w;
            aVar.x = downloadInfo.x;
            aVar.B = downloadInfo.B;
            aVar.C = downloadInfo.C;
            aVar.D = downloadInfo.D;
            aVar.E = downloadInfo.E;
            aVar.F = downloadInfo.F;
            aVar.G = downloadInfo.G;
            return aVar;
        }

        public final DownloadInfo a() {
            return new DownloadInfo(this);
        }

        public final void c(OTRProfileID oTRProfileID) {
            this.u = oTRProfileID;
            OTRProfileID oTRProfileID2 = OTRProfileID.b;
            this.t = oTRProfileID != null;
        }
    }

    public DownloadInfo(a aVar) {
        GURL gurl = aVar.a;
        this.a = gurl == null ? GURL.emptyGURL() : gurl;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        GURL gurl2 = aVar.h;
        this.h = gurl2 == null ? GURL.emptyGURL() : gurl2;
        GURL gurl3 = aVar.i;
        this.i = gurl3 == null ? GURL.emptyGURL() : gurl3;
        this.j = aVar.j;
        this.k = aVar.k;
        String str = aVar.m;
        this.l = str;
        this.m = aVar.n;
        this.o = aVar.l;
        this.n = aVar.o;
        this.p = aVar.p;
        this.q = aVar.q;
        this.r = aVar.r;
        this.s = aVar.s;
        this.t = aVar.t;
        this.u = aVar.u;
        boolean z = aVar.v;
        this.v = z;
        this.w = aVar.w;
        this.x = aVar.x;
        this.y = aVar.y;
        ny nyVar = aVar.z;
        if (nyVar != null) {
            this.z = nyVar;
        } else {
            this.z = n3.p(str, z);
        }
        this.A = aVar.A;
        this.B = aVar.B;
        this.C = aVar.C;
        this.D = aVar.D;
        this.E = aVar.E;
        this.F = aVar.F;
        this.G = aVar.G;
    }

    @CalledByNative
    public static DownloadInfo createDownloadInfo(String str, String str2, String str3, GURL gurl, String str4, long j, long j2, OTRProfileID oTRProfileID, int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, GURL gurl2, GURL gurl3, long j3, long j4, boolean z5, int i3) {
        String strRemapGenericMimeType = MimeUtils.remapGenericMimeType(str4, gurl.j(), str2);
        OfflineItem.a aVar = new OfflineItem.a(j, i2 == -1 ? null : Long.valueOf(j2), 0);
        a aVar2 = new a();
        aVar2.j = j;
        aVar2.k = j2;
        aVar2.f = str2;
        aVar2.m = str;
        aVar2.e = str2;
        aVar2.g = str3;
        aVar2.n = z2;
        aVar2.c(oTRProfileID);
        aVar2.s = z;
        aVar2.r = z3;
        aVar2.C = z4;
        aVar2.c = strRemapGenericMimeType;
        aVar2.i = gurl2;
        aVar2.p = aVar;
        aVar2.h = gurl3;
        aVar2.w = i;
        aVar2.q = j3;
        aVar2.x = j4;
        aVar2.y = z5;
        aVar2.a = gurl;
        aVar2.F = i3;
        return new DownloadInfo(aVar2);
    }
}
