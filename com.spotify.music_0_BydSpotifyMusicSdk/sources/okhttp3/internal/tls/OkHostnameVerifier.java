package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import kotlin.collections.EmptyList;
import kotlin.text.b;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import p.ng2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class OkHostnameVerifier implements HostnameVerifier {
    public static final OkHostnameVerifier a = new OkHostnameVerifier();

    private OkHostnameVerifier() {
    }

    public static List a(X509Certificate x509Certificate, int i) {
        Object obj;
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return EmptyList.c;
            }
            ArrayList arrayList = new ArrayList();
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && v03.b(list.get(0), Integer.valueOf(i)) && (obj = list.get(1)) != null) {
                    arrayList.add((String) obj);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return EmptyList.c;
        }
    }

    public static boolean b(String str) {
        int i;
        int length = str.length();
        int length2 = str.length();
        if (length2 < 0) {
            throw new IllegalArgumentException(u4.f("endIndex < beginIndex: ", length2, " < 0").toString());
        }
        if (length2 > str.length()) {
            StringBuilder sbO = u4.o("endIndex > string.length: ", length2, " > ");
            sbO.append(str.length());
            throw new IllegalArgumentException(sbO.toString().toString());
        }
        long j = 0;
        int i2 = 0;
        while (i2 < length2) {
            char cCharAt = str.charAt(i2);
            if (cCharAt < 128) {
                j++;
            } else {
                if (cCharAt < 2048) {
                    i = 2;
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    i = 3;
                } else {
                    int i3 = i2 + 1;
                    char cCharAt2 = i3 < length2 ? str.charAt(i3) : (char) 0;
                    if (cCharAt > 56319 || cCharAt2 < 56320 || cCharAt2 > 57343) {
                        j++;
                        i2 = i3;
                    } else {
                        j += (long) 4;
                        i2 += 2;
                    }
                }
                j += (long) i;
            }
            i2++;
        }
        return length == ((int) j);
    }

    public static boolean c(String str, X509Certificate x509Certificate) {
        int length;
        v03.j(str, "host");
        byte[] bArr = Util.a;
        if (Util.f.a(str)) {
            String strB = HostnamesKt.b(str);
            List listA = a(x509Certificate, 7);
            if (!(listA instanceof Collection) || !listA.isEmpty()) {
                Iterator it = listA.iterator();
                while (it.hasNext()) {
                    if (v03.b(strB, HostnamesKt.b((String) it.next()))) {
                        return true;
                    }
                }
            }
        } else {
            if (b(str)) {
                Locale locale = Locale.US;
                v03.i(locale, "US");
                str = str.toLowerCase(locale);
                v03.i(str, "this as java.lang.String).toLowerCase(locale)");
            }
            List<String> listA2 = a(x509Certificate, 2);
            if (!(listA2 instanceof Collection) || !listA2.isEmpty()) {
                for (String lowerCase : listA2) {
                    a.getClass();
                    if (str.length() != 0 && !ng2.g0(str, ".", false) && !ng2.a0(str, "..", false) && lowerCase != null && lowerCase.length() != 0 && !ng2.g0(lowerCase, ".", false) && !ng2.a0(lowerCase, "..", false)) {
                        String strConcat = !ng2.a0(str, ".", false) ? str.concat(".") : str;
                        if (!ng2.a0(lowerCase, ".", false)) {
                            lowerCase = lowerCase.concat(".");
                        }
                        if (b(lowerCase)) {
                            Locale locale2 = Locale.US;
                            v03.i(locale2, "US");
                            lowerCase = lowerCase.toLowerCase(locale2);
                            v03.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                        }
                        if (!b.i0(lowerCase, "*")) {
                            if (v03.b(strConcat, lowerCase)) {
                                return true;
                            }
                        } else if (ng2.g0(lowerCase, "*.", false) && b.n0(lowerCase, '*', 1, false, 4) == -1 && strConcat.length() >= lowerCase.length() && !v03.b("*.", lowerCase)) {
                            String strSubstring = lowerCase.substring(1);
                            v03.i(strSubstring, "this as java.lang.String).substring(startIndex)");
                            if (ng2.a0(strConcat, strSubstring, false) && ((length = strConcat.length() - strSubstring.length()) <= 0 || b.q0(strConcat, '.', length - 1, 4) == -1)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        v03.j(str, "host");
        v03.j(sSLSession, "session");
        if (!b(str)) {
            return false;
        }
        try {
            Certificate certificate = sSLSession.getPeerCertificates()[0];
            v03.g(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            return c(str, (X509Certificate) certificate);
        } catch (SSLException unused) {
            return false;
        }
    }
}
