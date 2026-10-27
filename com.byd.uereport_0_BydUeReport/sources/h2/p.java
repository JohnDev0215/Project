package h2;

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
public class p extends g2.e implements t0, g2.t, u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f5296a = new p();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public DatatypeFactory f5297b;

    @Override // g2.e, g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        return (T) f(aVar, type, obj, null, 0);
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        char[] charArray;
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.Z();
            return;
        }
        Calendar gregorianCalendar = obj instanceof XMLGregorianCalendar ? ((XMLGregorianCalendar) obj).toGregorianCalendar() : (Calendar) obj;
        if (!d1Var.t(SerializerFeature.UseISO8601DateFormat)) {
            i0Var.H(gregorianCalendar.getTime());
            return;
        }
        char c8 = d1Var.t(SerializerFeature.UseSingleQuotes) ? '\'' : '\"';
        d1Var.append(c8);
        int i9 = gregorianCalendar.get(1);
        int i10 = gregorianCalendar.get(2) + 1;
        int i11 = gregorianCalendar.get(5);
        int i12 = gregorianCalendar.get(11);
        int i13 = gregorianCalendar.get(12);
        int i14 = gregorianCalendar.get(13);
        int i15 = gregorianCalendar.get(14);
        if (i15 != 0) {
            charArray = "0000-00-00T00:00:00.000".toCharArray();
            l2.e.i(i15, 23, charArray);
            l2.e.i(i14, 19, charArray);
            l2.e.i(i13, 16, charArray);
            l2.e.i(i12, 13, charArray);
            l2.e.i(i11, 10, charArray);
            l2.e.i(i10, 7, charArray);
            l2.e.i(i9, 4, charArray);
        } else if (i14 == 0 && i13 == 0 && i12 == 0) {
            charArray = "0000-00-00".toCharArray();
            l2.e.i(i11, 10, charArray);
            l2.e.i(i10, 7, charArray);
            l2.e.i(i9, 4, charArray);
        } else {
            charArray = "0000-00-00T00:00:00".toCharArray();
            l2.e.i(i14, 19, charArray);
            l2.e.i(i13, 16, charArray);
            l2.e.i(i12, 13, charArray);
            l2.e.i(i11, 10, charArray);
            l2.e.i(i10, 7, charArray);
            l2.e.i(i9, 4, charArray);
        }
        d1Var.write(charArray);
        float offset = gregorianCalendar.getTimeZone().getOffset(gregorianCalendar.getTimeInMillis()) / 3600000.0f;
        int i16 = (int) offset;
        if (i16 == 0.0d) {
            d1Var.write(90);
        } else {
            if (i16 > 9) {
                d1Var.write(43);
                d1Var.V(i16);
            } else if (i16 > 0) {
                d1Var.write(43);
                d1Var.write(48);
                d1Var.V(i16);
            } else if (i16 < -9) {
                d1Var.write(45);
                d1Var.V(i16);
            } else if (i16 < 0) {
                d1Var.write(45);
                d1Var.write(48);
                d1Var.V(-i16);
            }
            d1Var.write(58);
            d1Var.append(String.format("%02d", Integer.valueOf((int) ((offset - i16) * 60.0f))));
        }
        d1Var.append(c8);
    }

    @Override // h2.u
    public void d(i0 i0Var, Object obj, j jVar) throws IOException {
        d1 d1Var = i0Var.f5241k;
        String strB = jVar.b();
        Calendar calendar = (Calendar) obj;
        if (strB.equals("unixtime")) {
            d1Var.V((int) (calendar.getTimeInMillis() / 1000));
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strB);
        simpleDateFormat.setTimeZone(i0Var.f5249s);
        d1Var.d0(simpleDateFormat.format(calendar.getTime()));
    }

    @Override // g2.t
    public int e() {
        return 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.util.Calendar] */
    @Override // g2.e
    public <T> T f(f2.a aVar, Type type, Object obj, String str, int i8) {
        T t7 = (T) w.f5309a.f(aVar, type, obj, str, i8);
        if (t7 instanceof Calendar) {
            return t7;
        }
        Date date = (Date) t7;
        if (date == null) {
            return null;
        }
        f2.b bVar = aVar.f4861j;
        ?? r7 = (T) Calendar.getInstance(bVar.o0(), bVar.h0());
        r7.setTime(date);
        return type == XMLGregorianCalendar.class ? (T) g((GregorianCalendar) r7) : r7;
    }

    public XMLGregorianCalendar g(Calendar calendar) {
        if (this.f5297b == null) {
            try {
                this.f5297b = DatatypeFactory.newInstance();
            } catch (DatatypeConfigurationException e8) {
                throw new IllegalStateException("Could not obtain an instance of DatatypeFactory.", e8);
            }
        }
        return this.f5297b.newXMLGregorianCalendar((GregorianCalendar) calendar);
    }
}
