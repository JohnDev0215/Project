package b.a.a.k;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: compiled from: JSONSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class i0 extends c1 {
    public final a1 j;
    public final d1 k;
    public int l;
    public String m;
    public String n;
    public DateFormat o;
    public String p;
    public IdentityHashMap<Object, y0> q;
    public y0 r;
    public TimeZone s;
    public Locale t;

    public i0() {
        this(new d1(), a1.g());
    }

    public boolean A(SerializerFeature serializerFeature) {
        return this.k.l(serializerFeature);
    }

    public final boolean B(Type type, Object obj) {
        y0 y0Var;
        return this.k.l(SerializerFeature.WriteClassName) && !(type == null && this.k.l(SerializerFeature.NotWriteRootClassName) && ((y0Var = this.r) == null || y0Var.f412a == null));
    }

    public void C() {
        this.k.write(10);
        for (int i = 0; i < this.l; i++) {
            this.k.write(this.m);
        }
    }

    public void D(y0 y0Var, Object obj, Object obj2, int i) {
        E(y0Var, obj, obj2, i, 0);
    }

    public void E(y0 y0Var, Object obj, Object obj2, int i, int i2) {
        if (this.k.n) {
            return;
        }
        this.r = new y0(y0Var, obj, obj2, i, i2);
        if (this.q == null) {
            this.q = new IdentityHashMap<>();
        }
        this.q.put(obj, this.r);
    }

    public void F(String str) {
        this.n = str;
        if (this.o != null) {
            this.o = null;
        }
    }

    public void G(String str) {
        this.p = str;
    }

    public final void H(Object obj) {
        if (obj == null) {
            this.k.J();
            return;
        }
        try {
            x(obj.getClass()).c(this, obj, null, null, 0);
        } catch (IOException e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    public final void I(String str) {
        e1.f362a.g(this, str);
    }

    public void J() {
        this.k.J();
    }

    public void K(Object obj) {
        y0 y0Var = this.r;
        if (obj == y0Var.f413b) {
            this.k.write("{\"$ref\":\"@\"}");
            return;
        }
        y0 y0Var2 = y0Var.f412a;
        if (y0Var2 != null && obj == y0Var2.f413b) {
            this.k.write("{\"$ref\":\"..\"}");
            return;
        }
        while (true) {
            y0 y0Var3 = y0Var.f412a;
            if (y0Var3 == null) {
                break;
            } else {
                y0Var = y0Var3;
            }
        }
        if (obj == y0Var.f413b) {
            this.k.write("{\"$ref\":\"$\"}");
            return;
        }
        this.k.write("{\"$ref\":\"");
        this.k.write(this.q.get(obj).toString());
        this.k.write("\"}");
    }

    public final void L(Object obj, Object obj2) {
        M(obj, obj2, null, 0);
    }

    public final void M(Object obj, Object obj2, Type type, int i) {
        try {
            if (obj == null) {
                this.k.J();
            } else {
                x(obj.getClass()).c(this, obj, obj2, type, i);
            }
        } catch (IOException e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    public final void N(Object obj, String str) {
        if (obj instanceof Date) {
            if ("unixtime".equals(str)) {
                this.k.F((int) (((Date) obj).getTime() / 1000));
                return;
            }
            if ("millis".equals(str)) {
                this.k.H(((Date) obj).getTime());
                return;
            }
            DateFormat dateFormatU = u();
            if (dateFormatU == null) {
                if (str != null) {
                    try {
                        dateFormatU = t(str);
                    } catch (IllegalArgumentException unused) {
                        dateFormatU = t(str.replaceAll(ExifInterface.GPS_DIRECTION_TRUE, "'T'"));
                    }
                } else {
                    String str2 = this.p;
                    dateFormatU = str2 != null ? t(str2) : t(b.a.a.a.DEFFAULT_DATE_FORMAT);
                }
            }
            this.k.N(dateFormatU.format((Date) obj));
            return;
        }
        if (!(obj instanceof byte[])) {
            if (!(obj instanceof Collection)) {
                H(obj);
                return;
            }
            Collection collection = (Collection) obj;
            Iterator it = collection.iterator();
            this.k.write(91);
            for (int i = 0; i < collection.size(); i++) {
                Object next = it.next();
                if (i != 0) {
                    this.k.write(44);
                }
                N(next, str);
            }
            this.k.write(93);
            return;
        }
        byte[] bArr = (byte[]) obj;
        if (!"gzip".equals(str) && !"gzip,base64".equals(str)) {
            if ("hex".equals(str)) {
                this.k.E(bArr);
                return;
            } else {
                this.k.q(bArr);
                return;
            }
        }
        GZIPOutputStream gZIPOutputStream = null;
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                gZIPOutputStream = bArr.length < 512 ? new GZIPOutputStream(byteArrayOutputStream, bArr.length) : new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.finish();
                this.k.q(byteArrayOutputStream.toByteArray());
            } finally {
                b.a.a.n.e.a(gZIPOutputStream);
            }
        } catch (IOException e2) {
            throw new JSONException("write gzipBytes error", e2);
        }
    }

    public void q(SerializerFeature serializerFeature, boolean z) {
        this.k.g(serializerFeature, z);
    }

    public boolean r(Object obj) {
        y0 y0Var;
        IdentityHashMap<Object, y0> identityHashMap = this.q;
        if (identityHashMap == null || (y0Var = identityHashMap.get(obj)) == null || obj == Collections.emptyMap()) {
            return false;
        }
        Object obj2 = y0Var.f414c;
        return obj2 == null || (obj2 instanceof Integer) || (obj2 instanceof String);
    }

    public void s() {
        this.l--;
    }

    public final DateFormat t(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, this.t);
        simpleDateFormat.setTimeZone(this.s);
        return simpleDateFormat;
    }

    public String toString() {
        return this.k.toString();
    }

    public DateFormat u() {
        String str;
        if (this.o == null && (str = this.n) != null) {
            this.o = t(str);
        }
        return this.o;
    }

    public String v() {
        DateFormat dateFormat = this.o;
        return dateFormat instanceof SimpleDateFormat ? ((SimpleDateFormat) dateFormat).toPattern() : this.n;
    }

    public String w() {
        return this.p;
    }

    public t0 x(Class<?> cls) {
        return this.j.h(cls);
    }

    public d1 y() {
        return this.k;
    }

    public void z() {
        this.l++;
    }

    public i0(d1 d1Var) {
        this(d1Var, a1.g());
    }

    public i0(d1 d1Var, a1 a1Var) {
        this.l = 0;
        this.m = "\t";
        this.q = null;
        this.s = b.a.a.a.defaultTimeZone;
        this.t = b.a.a.a.defaultLocale;
        this.k = d1Var;
        this.j = a1Var;
    }
}
