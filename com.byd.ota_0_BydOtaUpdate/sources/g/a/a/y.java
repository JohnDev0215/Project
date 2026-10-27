package g.a.a;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/* JADX INFO: loaded from: classes.dex */
public class y extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4195c;

    public y(byte[] bArr) {
        this.f4195c = bArr;
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof y) {
            return g.a.f.a.a(this.f4195c, ((y) qVar).f4195c);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.c(23);
        int length = this.f4195c.length;
        pVar.i(length);
        for (int i2 = 0; i2 != length; i2++) {
            pVar.c(this.f4195c[i2]);
        }
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(this.f4195c);
    }

    @Override // g.a.a.q
    public int i() {
        int length = this.f4195c.length;
        return y1.a(length) + 1 + length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public Date n() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        return simpleDateFormat.parse(o());
    }

    public String o() {
        StringBuilder sb;
        String str;
        String strP = p();
        if (strP.charAt(0) < '5') {
            sb = new StringBuilder();
            str = "20";
        } else {
            sb = new StringBuilder();
            str = "19";
        }
        sb.append(str);
        sb.append(strP);
        return sb.toString();
    }

    public String p() {
        StringBuilder sb;
        String strSubstring;
        String strB = g.a.f.i.b(this.f4195c);
        if (strB.indexOf(45) >= 0 || strB.indexOf(43) >= 0) {
            int iIndexOf = strB.indexOf(45);
            if (iIndexOf < 0) {
                iIndexOf = strB.indexOf(43);
            }
            if (iIndexOf == strB.length() - 3) {
                strB = strB + "00";
            }
            if (iIndexOf == 10) {
                sb = new StringBuilder();
                sb.append(strB.substring(0, 10));
                sb.append("00GMT");
                sb.append(strB.substring(10, 13));
                sb.append(":");
                strSubstring = strB.substring(13, 15);
            } else {
                sb = new StringBuilder();
                sb.append(strB.substring(0, 12));
                sb.append("GMT");
                sb.append(strB.substring(12, 15));
                sb.append(":");
                strSubstring = strB.substring(15, 17);
            }
        } else if (strB.length() == 11) {
            sb = new StringBuilder();
            sb.append(strB.substring(0, 10));
            strSubstring = "00GMT+00:00";
        } else {
            sb = new StringBuilder();
            sb.append(strB.substring(0, 12));
            strSubstring = "GMT+00:00";
        }
        sb.append(strSubstring);
        return sb.toString();
    }

    public String toString() {
        return g.a.f.i.b(this.f4195c);
    }
}
