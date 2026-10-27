package spi;

import java.net.IDN;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.time.DurationKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@JvmName(name = "-Util")
public final class c {

    @NotNull
    public static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static final int a(char c) {
        if ('0' <= c && '9' >= c) {
            return c - '0';
        }
        char c2 = 'a';
        if ('a' > c || 'f' < c) {
            c2 = 'A';
            if ('A' > c || 'F' < c) {
                throw new IllegalArgumentException("Unexpected hex digit: " + c);
            }
        }
        return (c - c2) + 10;
    }

    public static final void b(ts tsVar, vs vsVar, String str) {
        Objects.requireNonNull(ws.j);
        Logger logger = ws.i;
        StringBuilder sb = new StringBuilder();
        sb.append(vsVar.f);
        sb.append(' ');
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
        sb.append(str2);
        sb.append(": ");
        sb.append(tsVar.c);
        logger.fine(sb.toString());
    }

    public static final boolean c(@NotNull byte[] a2, int i, @NotNull byte[] b, int i2, int i3) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        for (int i4 = 0; i4 < i3; i4++) {
            if (a2[i4 + i] != b[i4 + i2]) {
                return false;
            }
        }
        return true;
    }

    public static final void d(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException("size=" + j + " offset=" + j2 + " byteCount=" + j3);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x00d9, code lost:
    
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008d A[LOOP:2: B:24:0x004b->B:49:0x008d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x009d A[EDGE_INSN: B:90:0x009d->B:53:0x009d BREAK  A[LOOP:2: B:24:0x004b->B:49:0x008d], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.net.InetAddress e(java.lang.String r17, int r18, int r19) {
        /*
            Method dump skipped, instruction units count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.c.e(java.lang.String, int, int):java.net.InetAddress");
    }

    @NotNull
    public static final String f(long j) {
        StringBuilder sb;
        long j2;
        long j3;
        long j4;
        if (j > -999500000) {
            if (j > -999500) {
                if (j <= 0) {
                    sb = new StringBuilder();
                    j4 = j - ((long) 500);
                } else if (j < 999500) {
                    sb = new StringBuilder();
                    j4 = j + ((long) 500);
                } else if (j < 999500000) {
                    sb = new StringBuilder();
                    j3 = j + ((long) 500000);
                } else {
                    sb = new StringBuilder();
                    j2 = j + ((long) 500000000);
                }
                sb.append(j4 / ((long) 1000));
                sb.append(" µs");
                String string = sb.toString();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("%6s", Arrays.copyOf(new Object[]{string}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                return str;
            }
            sb = new StringBuilder();
            j3 = j - ((long) 500000);
            sb.append(j3 / ((long) DurationKt.NANOS_IN_MILLIS));
            sb.append(" ms");
            String string2 = sb.toString();
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String str2 = String.format("%6s", Arrays.copyOf(new Object[]{string2}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
            return str2;
        }
        sb = new StringBuilder();
        j2 = j - ((long) 500000000);
        sb.append(j2 / ((long) 1000000000));
        sb.append(" s ");
        String string22 = sb.toString();
        StringCompanionObject stringCompanionObject22 = StringCompanionObject.INSTANCE;
        String str22 = String.format("%6s", Arrays.copyOf(new Object[]{string22}, 1));
        Intrinsics.checkNotNullExpressionValue(str22, "java.lang.String.format(format, *args)");
        return str22;
    }

    @Nullable
    public static final String g(@NotNull String toCanonicalHost) {
        Intrinsics.checkNotNullParameter(toCanonicalHost, "$this$toCanonicalHost");
        int i = 0;
        int i2 = -1;
        if (!StringsKt__StringsKt.contains$default(toCanonicalHost, ":", false, 2, (Object) null)) {
            try {
                String ascii = IDN.toASCII(toCanonicalHost);
                Intrinsics.checkNotNullExpressionValue(ascii, "IDN.toASCII(host)");
                Locale locale = Locale.US;
                Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
                if (ascii == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase = ascii.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                if (lowerCase.length() == 0) {
                    return null;
                }
                int length = lowerCase.length();
                for (int i3 = 0; i3 < length; i3++) {
                    char cCharAt = lowerCase.charAt(i3);
                    if (Intrinsics.compare((int) cCharAt, 31) > 0 && Intrinsics.compare((int) cCharAt, 127) < 0 && StringsKt.j(" #%/:?@[\\]", cCharAt, 0, 6) == -1) {
                    }
                    i = 1;
                    break;
                }
                if (i != 0) {
                    return null;
                }
                return lowerCase;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        InetAddress inetAddressE = (StringsKt.x(toCanonicalHost, "[") && StringsKt.g(toCanonicalHost, "]")) ? e(toCanonicalHost, 1, toCanonicalHost.length() - 1) : e(toCanonicalHost, 0, toCanonicalHost.length());
        if (inetAddressE == null) {
            return null;
        }
        byte[] address = inetAddressE.getAddress();
        if (address.length != 16) {
            if (address.length == 4) {
                return inetAddressE.getHostAddress();
            }
            throw new AssertionError("Invalid IPv6 address: '" + toCanonicalHost + '\'');
        }
        Intrinsics.checkNotNullExpressionValue(address, "address");
        int i4 = 0;
        int i5 = 0;
        while (i4 < address.length) {
            int i6 = i4;
            while (i6 < 16 && address[i6] == 0 && address[i6 + 1] == 0) {
                i6 += 2;
            }
            int i7 = i6 - i4;
            if (i7 > i5 && i7 >= 4) {
                i2 = i4;
                i5 = i7;
            }
            i4 = i6 + 2;
        }
        g2 g2Var = new g2();
        while (i < address.length) {
            if (i == i2) {
                g2Var.S(58);
                i += i5;
                if (i == 16) {
                    g2Var.S(58);
                }
            } else {
                if (i > 0) {
                    g2Var.S(58);
                }
                byte b = address[i];
                byte[] bArr = uu.a;
                g2Var.o(((b & 255) << 8) | (address[i + 1] & 255));
                i += 2;
            }
        }
        return g2Var.L();
    }

    @NotNull
    public static final String h(byte b) {
        char[] cArr = a;
        return new String(new char[]{cArr[(b >> 4) & 15], cArr[b & 15]});
    }
}
