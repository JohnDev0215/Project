package okhttp3.internal;

import java.net.IDN;
import java.net.InetAddress;
import java.util.Locale;
import kotlin.text.b;
import p.fo;
import p.ng2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class HostnamesKt {
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00d5, code lost:
    
        if (r7 == r0) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00d7, code lost:
    
        if (r8 != (-1)) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00d9, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00da, code lost:
    
        r1 = r7 - r8;
        java.lang.System.arraycopy(r3, r8, r3, 16 - r1, r1);
        java.util.Arrays.fill(r3, r8, (16 - r7) + r8, (byte) 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00ec, code lost:
    
        return java.net.InetAddress.getByAddress(r3);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008e A[LOOP:2: B:25:0x004c->B:50:0x008e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00a2 A[EDGE_INSN: B:85:0x00a2->B:54:0x00a2 BREAK  A[LOOP:2: B:25:0x004c->B:50:0x008e], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [int] */
    /* JADX WARN: Type inference failed for: r14v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.net.InetAddress a(java.lang.String r17, int r18, int r19) {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.HostnamesKt.a(java.lang.String, int, int):java.net.InetAddress");
    }

    public static final String b(String str) {
        v03.j(str, "<this>");
        int i = 0;
        int i2 = -1;
        if (!b.i0(str, ":")) {
            try {
                String ascii = IDN.toASCII(str);
                v03.i(ascii, "toASCII(host)");
                Locale locale = Locale.US;
                v03.i(locale, "US");
                String lowerCase = ascii.toLowerCase(locale);
                v03.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (lowerCase.length() == 0) {
                    return null;
                }
                int length = lowerCase.length();
                for (int i3 = 0; i3 < length; i3++) {
                    char cCharAt = lowerCase.charAt(i3);
                    if (v03.n(cCharAt, 31) <= 0 || v03.n(cCharAt, 127) >= 0 || b.n0(" #%/:?@[\\]", cCharAt, 0, false, 6) != -1) {
                        return null;
                    }
                }
                return lowerCase;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        InetAddress inetAddressA = (ng2.g0(str, "[", false) && ng2.a0(str, "]", false)) ? a(str, 1, str.length() - 1) : a(str, 0, str.length());
        if (inetAddressA == null) {
            return null;
        }
        byte[] address = inetAddressA.getAddress();
        if (address.length != 16) {
            if (address.length == 4) {
                return inetAddressA.getHostAddress();
            }
            throw new AssertionError("Invalid IPv6 address: '" + str + '\'');
        }
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
        fo foVar = new fo();
        while (i < address.length) {
            if (i == i2) {
                foVar.W0(58);
                i += i5;
                if (i == 16) {
                    foVar.W0(58);
                }
            } else {
                if (i > 0) {
                    foVar.W0(58);
                }
                byte b = address[i];
                byte[] bArr = Util.a;
                foVar.Y0(((b & 255) << 8) | (address[i + 1] & 255));
                i += 2;
            }
        }
        return foVar.Q0();
    }
}
