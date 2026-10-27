package c3;

import android.graphics.Color;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: c3.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1022e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f12370a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f12371b;
    public final Integer c;
    public final Integer d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f12372e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f12373f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f12374g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f12375h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f12376i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f12377j;

    public C1022e(String str, int i7, Integer num, Integer num2, float f10, boolean z6, boolean z10, boolean z11, boolean z12, int i10) {
        this.f12370a = str;
        this.f12371b = i7;
        this.c = num;
        this.d = num2;
        this.f12372e = f10;
        this.f12373f = z6;
        this.f12374g = z10;
        this.f12375h = z11;
        this.f12376i = z12;
        this.f12377j = i10;
    }

    public static int a(String str) {
        boolean z6;
        try {
            int i7 = Integer.parseInt(str.trim());
            switch (i7) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    z6 = true;
                    break;
                default:
                    z6 = false;
                    break;
            }
            if (z6) {
                return i7;
            }
        } catch (NumberFormatException unused) {
        }
        AbstractC1347d.q("Ignoring unknown alignment: ", str, "SsaStyle");
        return -1;
    }

    public static boolean b(String str) {
        try {
            int i7 = Integer.parseInt(str);
            return i7 == 1 || i7 == -1;
        } catch (NumberFormatException e10) {
            AbstractC1346c.A("SsaStyle", "Failed to parse boolean value: '" + str + "'", e10);
            return false;
        }
    }

    public static Integer c(String str) {
        try {
            long j3 = str.startsWith("&H") ? Long.parseLong(str.substring(2), 16) : Long.parseLong(str);
            AbstractC1346c.c(j3 <= 4294967295L);
            return Integer.valueOf(Color.argb(AbstractC2673a.q(((j3 >> 24) & 255) ^ 255), AbstractC2673a.q(j3 & 255), AbstractC2673a.q((j3 >> 8) & 255), AbstractC2673a.q((j3 >> 16) & 255)));
        } catch (IllegalArgumentException e10) {
            AbstractC1346c.A("SsaStyle", "Failed to parse color expression: '" + str + "'", e10);
            return null;
        }
    }
}
