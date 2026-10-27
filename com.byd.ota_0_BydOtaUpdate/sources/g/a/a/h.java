package g.a.a;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class h extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f3855c;

    public h(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.f3855c = g.a.f.i.e(simpleDateFormat.format(date));
    }

    public h(byte[] bArr) {
        this.f3855c = bArr;
    }

    public static h q(Object obj) {
        if (obj == null || (obj instanceof h)) {
            return (h) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (h) q.j((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof h) {
            return g.a.f.a.a(this.f3855c, ((h) qVar).f3855c);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(24, this.f3855c);
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(this.f3855c);
    }

    @Override // g.a.a.q
    public int i() {
        int length = this.f3855c.length;
        return y1.a(length) + 1 + length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    @Override // g.a.a.q
    public q l() {
        return new t0(this.f3855c);
    }

    public final String n() {
        String str;
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            rawOffset = -rawOffset;
            str = "-";
        } else {
            str = "+";
        }
        int i2 = rawOffset / 3600000;
        int i3 = (rawOffset - (((i2 * 60) * 60) * 1000)) / 60000;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(p())) {
                i2 += str.equals("+") ? 1 : -1;
            }
        } catch (ParseException unused) {
        }
        return "GMT" + str + o(i2) + ":" + o(i3);
    }

    public final String o(int i2) {
        if (i2 >= 10) {
            return Integer.toString(i2);
        }
        return "0" + i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Date p() throws java.text.ParseException {
        /*
            Method dump skipped, instruction units count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g.a.a.h.p():java.util.Date");
    }

    public String r() {
        String strB = g.a.f.i.b(this.f3855c);
        if (strB.charAt(strB.length() - 1) == 'Z') {
            return strB.substring(0, strB.length() - 1) + "GMT+00:00";
        }
        int length = strB.length() - 5;
        char cCharAt = strB.charAt(length);
        if (cCharAt == '-' || cCharAt == '+') {
            StringBuilder sb = new StringBuilder();
            sb.append(strB.substring(0, length));
            sb.append("GMT");
            int i2 = length + 3;
            sb.append(strB.substring(length, i2));
            sb.append(":");
            sb.append(strB.substring(i2));
            return sb.toString();
        }
        int length2 = strB.length() - 3;
        char cCharAt2 = strB.charAt(length2);
        if (cCharAt2 != '-' && cCharAt2 != '+') {
            return strB + n();
        }
        return strB.substring(0, length2) + "GMT" + strB.substring(length2) + ":00";
    }

    public boolean s() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f3855c;
            if (i2 == bArr.length) {
                return false;
            }
            if (bArr[i2] == 46 && i2 == 14) {
                return true;
            }
            i2++;
        }
    }

    public boolean t() {
        return v(10) && v(11);
    }

    public boolean u() {
        return v(12) && v(13);
    }

    public final boolean v(int i2) {
        byte[] bArr = this.f3855c;
        return bArr.length > i2 && bArr[i2] >= 48 && bArr[i2] <= 57;
    }
}
