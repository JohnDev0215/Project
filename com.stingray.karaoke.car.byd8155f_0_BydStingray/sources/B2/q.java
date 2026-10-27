package B2;

import android.text.TextUtils;
import h2.AbstractC1346c;
import io.sentry.EnumC1495p1;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public final class q implements T1.o, h5.s, l5.o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f598m;

    public /* synthetic */ q(String str, int i7) {
        this.l = i7;
        this.f598m = str;
    }

    public static q d(A2.c cVar, List list) {
        String str;
        String str2;
        io.sentry.K k10 = (io.sentry.K) cVar.f312e;
        String strB = io.sentry.util.i.b(list);
        String str3 = "UTF-8";
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        boolean z6 = true;
        if (strB != null) {
            try {
                String[] strArrSplit = strB.split(",", -1);
                int length = strArrSplit.length;
                int i7 = 0;
                while (i7 < length) {
                    String str4 = strArrSplit[i7];
                    if (str4.trim().startsWith("sentry-")) {
                        try {
                            int iIndexOf = str4.indexOf("=");
                            map.put(URLDecoder.decode(str4.substring(0, iIndexOf).trim(), str3), URLDecoder.decode(str4.substring(iIndexOf + 1).trim(), str3));
                            str2 = str3;
                            z6 = false;
                        } catch (Throwable th) {
                            str2 = str3;
                            k10.p(EnumC1495p1.ERROR, th, "Unable to decode baggage key value pair %s", str4);
                        }
                    } else {
                        str2 = str3;
                        arrayList.add(str4.trim());
                    }
                    i7++;
                    str3 = str2;
                }
            } catch (Throwable th2) {
                k10.p(EnumC1495p1.ERROR, th2, "Unable to decode baggage header %s", strB);
            }
        }
        A2.c cVar2 = new A2.c(map, arrayList.isEmpty() ? null : io.sentry.util.i.b(arrayList), z6, k10);
        StringBuilder sb = new StringBuilder();
        String str5 = (String) cVar2.d;
        int i10 = 0;
        if (str5 == null || str5.isEmpty()) {
            str = "";
        } else {
            sb.append(str5);
            int i11 = 0;
            while (i10 < str5.length()) {
                if (str5.charAt(i10) == ',') {
                    i11++;
                }
                i10++;
            }
            i10 = i11 + 1;
            str = ",";
        }
        HashMap map2 = (HashMap) cVar.c;
        int i12 = i10;
        String str6 = str;
        for (String str7 : new TreeSet(map2.keySet())) {
            String str8 = (String) map2.get(str7);
            if (str8 != null) {
                io.sentry.K k11 = (io.sentry.K) cVar.f312e;
                if (i12 >= 64) {
                    k11.k(EnumC1495p1.ERROR, "Not adding baggage value %s as the total number of list members would exceed the maximum of %s.", str7, 64);
                } else {
                    try {
                        String str9 = str6 + URLEncoder.encode(str7, "UTF-8").replaceAll("\\+", "%20") + "=" + URLEncoder.encode(str8, "UTF-8").replaceAll("\\+", "%20");
                        if (sb.length() + str9.length() > 8192) {
                            k11.k(EnumC1495p1.ERROR, "Not adding baggage value %s as the total header value length would exceed the maximum of %s.", str7, 8192);
                        } else {
                            i12++;
                            sb.append(str9);
                            str6 = ",";
                        }
                    } catch (Throwable th3) {
                        k11.p(EnumC1495p1.ERROR, th3, "Unable to encode baggage key value pair (key=%s,value=%s).", str7, str8);
                    }
                }
            }
        }
        String string = sb.toString();
        if (string.isEmpty()) {
            return null;
        }
        return new q(string, 5);
    }

    public static q e(h2.t tVar) {
        String str;
        tVar.I(2);
        int iV = tVar.v();
        int i7 = iV >> 1;
        int iV2 = ((tVar.v() >> 3) & 31) | ((iV & 1) << 5);
        if (i7 == 4 || i7 == 5 || i7 == 7) {
            str = "dvhe";
        } else if (i7 == 8) {
            str = "hev1";
        } else {
            if (i7 != 9) {
                return null;
            }
            str = "avc3";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".0");
        sb.append(i7);
        sb.append(iV2 >= 10 ? "." : ".0");
        sb.append(iV2);
        return new q(sb.toString(), 0);
    }

    @Override // l5.o
    public Object H() {
        switch (this.l) {
            case 7:
                throw new j5.p(this.f598m);
            default:
                throw new j5.p(this.f598m);
        }
    }

    @Override // T1.o
    public boolean a(CharSequence charSequence, int i7, int i10, T1.w wVar) {
        if (!TextUtils.equals(charSequence.subSequence(i7, i10), this.f598m)) {
            return true;
        }
        wVar.c = (wVar.c & 3) | 4;
        return false;
    }

    public void b(StringBuilder sb, Iterator it) {
        try {
            if (it.hasNext()) {
                Object next = it.next();
                Objects.requireNonNull(next);
                sb.append(next instanceof CharSequence ? (CharSequence) next : next.toString());
                while (it.hasNext()) {
                    sb.append((CharSequence) this.f598m);
                    Object next2 = it.next();
                    Objects.requireNonNull(next2);
                    sb.append(next2 instanceof CharSequence ? (CharSequence) next2 : next2.toString());
                }
            }
        } catch (IOException e10) {
            throw new AssertionError(e10);
        }
    }

    @Override // T1.o
    public Object c() {
        return this;
    }

    @Override // h5.s
    public void h(Throwable th) {
        AbstractC1346c.A("MediaNtfMng", "custom command " + this.f598m + " produced an error: " + th.getMessage(), th);
    }

    @Override // h5.s
    public /* bridge */ /* synthetic */ void i(Object obj) {
    }

    public String toString() {
        switch (this.l) {
            case 1:
                return this.f598m;
            case 2:
            default:
                return super.toString();
            case 3:
                return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("<"), this.f598m, '>');
        }
    }

    public q(String str) {
        this.l = 4;
        str.getClass();
        this.f598m = str;
    }
}
