package com.amazon.identity.auth.device;

import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class cd {
    public static final HashMap<String, bd> g = new HashMap<>();
    public static final String h = cd.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Random f272a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public cd(int i, int i2) {
        this(i, i2, 30, 0);
    }

    public static bd b(URL url) {
        bd bdVar;
        HashMap<String, bd> map = g;
        synchronized (map) {
            bdVar = map.get(c(url));
        }
        return bdVar;
    }

    public static String c(URL url) {
        return url.getHost() + url.getPath();
    }

    public final int a(int i) {
        int i2 = 1;
        for (int i3 = 0; i3 < i && this.b * i2 * 2 <= this.c; i3++) {
            i2 *= 2;
        }
        return i2;
    }

    public cd(int i, int i2, int i3, int i4) {
        this.f272a = new SecureRandom();
        this.b = i;
        this.c = i2;
        this.d = i4;
        this.e = a(i4);
        this.f = i3;
        if (this.b <= 0) {
            this.b = 10;
            ga.d(h, "ExponentialBackoffHelper: was constructed with a first retry interval value less than or equal to zero. It has been set to a default value (%d ms)", 10);
        }
        if (this.f <= 0) {
            this.f = 10;
            ga.d(h, "ExponentialBackoffHelper: was constructed with a jitter percent range less than or equal to zero. It has been set to a default value (%d percent)", 10);
        }
    }

    public static int a(long j, int i, Random random) {
        int iMin = (int) Math.min(2147483647L, ((((long) i) * 2) * j) / 100);
        if (iMin == 0) {
            return (int) j;
        }
        return (int) ((j - ((long) (iMin / 2))) + ((long) random.nextInt(iMin)));
    }

    public static bd a(URL url) {
        bd bdVarA;
        String strC = c(url);
        HashMap<String, bd> map = g;
        synchronized (map) {
            bd bdVar = map.get(strC);
            if (bdVar == null) {
                bdVarA = new bd(url, bd.f);
            } else {
                bdVarA = bdVar.a(url);
            }
            map.put(strC, bdVarA);
        }
        return bdVarA;
    }
}
