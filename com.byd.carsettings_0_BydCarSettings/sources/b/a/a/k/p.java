package b.a.a.k;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/* JADX INFO: compiled from: CalendarCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class p extends b.a.a.j.j.e implements t0, b.a.a.j.j.t, u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f395a = new p();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public DatatypeFactory f396b;

    @Override // b.a.a.j.j.e, b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        return (T) f(aVar, type, obj, null, 0);
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        char[] charArray;
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.J();
            return;
        }
        Calendar gregorianCalendar = obj instanceof XMLGregorianCalendar ? ((XMLGregorianCalendar) obj).toGregorianCalendar() : (Calendar) obj;
        if (!d1Var.l(SerializerFeature.UseISO8601DateFormat)) {
            i0Var.H(gregorianCalendar.getTime());
            return;
        }
        char c2 = d1Var.l(SerializerFeature.UseSingleQuotes) ? '\'' : '\"';
        d1Var.append(c2);
        int i2 = gregorianCalendar.get(1);
        int i3 = gregorianCalendar.get(2) + 1;
        int i4 = gregorianCalendar.get(5);
        int i5 = gregorianCalendar.get(11);
        int i6 = gregorianCalendar.get(12);
        int i7 = gregorianCalendar.get(13);
        int i8 = gregorianCalendar.get(14);
        if (i8 != 0) {
            charArray = "0000-00-00T00:00:00.000".toCharArray();
            b.a.a.n.e.i(i8, 23, charArray);
            b.a.a.n.e.i(i7, 19, charArray);
            b.a.a.n.e.i(i6, 16, charArray);
            b.a.a.n.e.i(i5, 13, charArray);
            b.a.a.n.e.i(i4, 10, charArray);
            b.a.a.n.e.i(i3, 7, charArray);
            b.a.a.n.e.i(i2, 4, charArray);
        } else if (i7 == 0 && i6 == 0 && i5 == 0) {
            charArray = "0000-00-00".toCharArray();
            b.a.a.n.e.i(i4, 10, charArray);
            b.a.a.n.e.i(i3, 7, charArray);
            b.a.a.n.e.i(i2, 4, charArray);
        } else {
            charArray = "0000-00-00T00:00:00".toCharArray();
            b.a.a.n.e.i(i7, 19, charArray);
            b.a.a.n.e.i(i6, 16, charArray);
            b.a.a.n.e.i(i5, 13, charArray);
            b.a.a.n.e.i(i4, 10, charArray);
            b.a.a.n.e.i(i3, 7, charArray);
            b.a.a.n.e.i(i2, 4, charArray);
        }
        d1Var.write(charArray);
        float offset = gregorianCalendar.getTimeZone().getOffset(gregorianCalendar.getTimeInMillis()) / 3600000.0f;
        int i9 = (int) offset;
        if (i9 == 0.0d) {
            d1Var.write(90);
        } else {
            if (i9 > 9) {
                d1Var.write(43);
                d1Var.F(i9);
            } else if (i9 > 0) {
                d1Var.write(43);
                d1Var.write(48);
                d1Var.F(i9);
            } else if (i9 < -9) {
                d1Var.write(45);
                d1Var.F(i9);
            } else if (i9 < 0) {
                d1Var.write(45);
                d1Var.write(48);
                d1Var.F(-i9);
            }
            d1Var.write(58);
            d1Var.append(String.format("%02d", Integer.valueOf((int) ((offset - i9) * 60.0f))));
        }
        d1Var.append(c2);
    }

    @Override // b.a.a.k.u
    public void d(i0 i0Var, Object obj, j jVar) throws IOException {
        d1 d1Var = i0Var.k;
        String strB = jVar.b();
        Calendar calendar = (Calendar) obj;
        if (strB.equals("unixtime")) {
            d1Var.F((int) (calendar.getTimeInMillis() / 1000));
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strB);
        simpleDateFormat.setTimeZone(i0Var.s);
        d1Var.N(simpleDateFormat.format(calendar.getTime()));
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.util.Calendar] */
    @Override // b.a.a.j.j.e
    public <T> T f(b.a.a.j.a aVar, Type type, Object obj, String str, int i) {
        T t = (T) w.f406a.f(aVar, type, obj, str, i);
        if (t instanceof Calendar) {
            return t;
        }
        Date date = (Date) t;
        if (date == null) {
            return null;
        }
        b.a.a.j.b bVar = aVar.g;
        ?? r7 = (T) Calendar.getInstance(bVar.W(), bVar.R());
        r7.setTime(date);
        return type == XMLGregorianCalendar.class ? (T) g((GregorianCalendar) r7) : r7;
    }

    public XMLGregorianCalendar g(Calendar calendar) {
        if (this.f396b == null) {
            try {
                this.f396b = DatatypeFactory.newInstance();
            } catch (DatatypeConfigurationException e2) {
                throw new IllegalStateException("Could not obtain an instance of DatatypeFactory.", e2);
            }
        }
        return this.f396b.newXMLGregorianCalendar((GregorianCalendar) calendar);
    }
}
