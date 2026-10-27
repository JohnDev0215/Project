package org.chromium.chrome.browser.cookies;

import defpackage.gq2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class CanonicalCookie {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final long e;
    public final long f;
    public final long g;
    public final long h;
    public final boolean i;
    public final boolean j;
    public final int k;
    public final int l;
    public final boolean m;
    public final String n;
    public final int o;
    public final int p;

    public static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    public CanonicalCookie(String str, String str2, String str3, String str4, long j, long j2, long j3, long j4, boolean z, boolean z2, int i, int i2, boolean z3, String str5, int i3, int i4) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = j;
        this.f = j2;
        this.g = j3;
        this.h = j4;
        this.i = z;
        this.j = z2;
        this.k = i;
        this.l = i2;
        this.m = z3;
        this.n = str5;
        this.o = i3;
        this.p = i4;
    }

    public static ArrayList a(DataInputStream dataInputStream) throws IOException, a {
        if (dataInputStream.readInt() != 20210712) {
            throw new a("Unexpected version");
        }
        int i = dataInputStream.readInt();
        if (i < 0) {
            throw new a(gq2.e("Negative length: ", i));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        while (i2 < i) {
            arrayList.add(new CanonicalCookie(dataInputStream.readUTF(), dataInputStream.readUTF(), dataInputStream.readUTF(), dataInputStream.readUTF(), dataInputStream.readLong(), dataInputStream.readLong(), dataInputStream.readLong(), dataInputStream.readLong(), dataInputStream.readBoolean(), dataInputStream.readBoolean(), dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readBoolean(), dataInputStream.readUTF(), dataInputStream.readInt(), dataInputStream.readInt()));
            i2++;
            i = i;
        }
        return arrayList;
    }

    public static void b(DataOutputStream dataOutputStream, CanonicalCookie[] canonicalCookieArr) throws IOException {
        if (canonicalCookieArr == null) {
            throw new IllegalArgumentException("cookies arg is null");
        }
        for (CanonicalCookie canonicalCookie : canonicalCookieArr) {
            if (canonicalCookie == null) {
                throw new IllegalArgumentException("cookies arg contains null value");
            }
        }
        int length = canonicalCookieArr.length;
        dataOutputStream.writeInt(20210712);
        dataOutputStream.writeInt(length);
        for (CanonicalCookie canonicalCookie2 : canonicalCookieArr) {
            dataOutputStream.writeUTF(canonicalCookie2.a);
            dataOutputStream.writeUTF(canonicalCookie2.b);
            dataOutputStream.writeUTF(canonicalCookie2.c);
            dataOutputStream.writeUTF(canonicalCookie2.d);
            dataOutputStream.writeLong(canonicalCookie2.e);
            dataOutputStream.writeLong(canonicalCookie2.f);
            dataOutputStream.writeLong(canonicalCookie2.g);
            dataOutputStream.writeLong(canonicalCookie2.h);
            dataOutputStream.writeBoolean(canonicalCookie2.i);
            dataOutputStream.writeBoolean(canonicalCookie2.j);
            dataOutputStream.writeInt(canonicalCookie2.k);
            dataOutputStream.writeInt(canonicalCookie2.l);
            dataOutputStream.writeBoolean(canonicalCookie2.m);
            dataOutputStream.writeUTF(canonicalCookie2.n);
            dataOutputStream.writeInt(canonicalCookie2.o);
            dataOutputStream.writeInt(canonicalCookie2.p);
        }
    }
}
