package h2;

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

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final a1 f5240j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final d1 f5241k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5242l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f5243m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f5244n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public DateFormat f5245o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f5246p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public IdentityHashMap<Object, y0> f5247q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public y0 f5248r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public TimeZone f5249s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Locale f5250t;

    public i0() {
        this(new d1(), a1.g());
    }

    public boolean A(SerializerFeature serializerFeature) {
        return this.f5241k.t(serializerFeature);
    }

    public final boolean B(Type type, Object obj) {
        y0 y0Var;
        return this.f5241k.t(SerializerFeature.WriteClassName) && !(type == null && this.f5241k.t(SerializerFeature.NotWriteRootClassName) && ((y0Var = this.f5248r) == null || y0Var.f5315a == null));
    }

    public void C() {
        this.f5241k.write(10);
        for (int i8 = 0; i8 < this.f5242l; i8++) {
            this.f5241k.write(this.f5243m);
        }
    }

    public void D(y0 y0Var, Object obj, Object obj2, int i8) {
        E(y0Var, obj, obj2, i8, 0);
    }

    public void E(y0 y0Var, Object obj, Object obj2, int i8, int i9) {
        if (this.f5241k.f5220q) {
            return;
        }
        this.f5248r = new y0(y0Var, obj, obj2, i8, i9);
        if (this.f5247q == null) {
            this.f5247q = new IdentityHashMap<>();
        }
        this.f5247q.put(obj, this.f5248r);
    }

    public void F(String str) {
        this.f5244n = str;
        if (this.f5245o != null) {
            this.f5245o = null;
        }
    }

    public void G(String str) {
        this.f5246p = str;
    }

    public final void H(Object obj) {
        if (obj == null) {
            this.f5241k.Z();
            return;
        }
        try {
            x(obj.getClass()).c(this, obj, null, null, 0);
        } catch (IOException e8) {
            throw new JSONException(e8.getMessage(), e8);
        }
    }

    public final void I(String str) {
        e1.f5232a.g(this, str);
    }

    public void J() {
        this.f5241k.Z();
    }

    public void K(Object obj) {
        y0 y0Var = this.f5248r;
        if (obj == y0Var.f5316b) {
            this.f5241k.write("{\"$ref\":\"@\"}");
            return;
        }
        y0 y0Var2 = y0Var.f5315a;
        if (y0Var2 != null && obj == y0Var2.f5316b) {
            this.f5241k.write("{\"$ref\":\"..\"}");
            return;
        }
        while (true) {
            y0 y0Var3 = y0Var.f5315a;
            if (y0Var3 == null) {
                break;
            } else {
                y0Var = y0Var3;
            }
        }
        if (obj == y0Var.f5316b) {
            this.f5241k.write("{\"$ref\":\"$\"}");
            return;
        }
        this.f5241k.write("{\"$ref\":\"");
        this.f5241k.write(this.f5247q.get(obj).toString());
        this.f5241k.write("\"}");
    }

    public final void L(Object obj, Object obj2) {
        M(obj, obj2, null, 0);
    }

    public final void M(Object obj, Object obj2, Type type, int i8) {
        try {
            if (obj == null) {
                this.f5241k.Z();
            } else {
                x(obj.getClass()).c(this, obj, obj2, type, i8);
            }
        } catch (IOException e8) {
            throw new JSONException(e8.getMessage(), e8);
        }
    }

    public final void N(Object obj, String str) {
        if (obj instanceof Date) {
            if ("unixtime".equals(str)) {
                this.f5241k.V((int) (((Date) obj).getTime() / 1000));
                return;
            }
            if ("millis".equals(str)) {
                this.f5241k.Y(((Date) obj).getTime());
                return;
            }
            DateFormat dateFormatU = u();
            if (dateFormatU == null) {
                if (str != null) {
                    try {
                        dateFormatU = t(str);
                    } catch (IllegalArgumentException unused) {
                        dateFormatU = t(str.replaceAll("T", "'T'"));
                    }
                } else {
                    String str2 = this.f5246p;
                    dateFormatU = str2 != null ? t(str2) : t(c2.a.DEFFAULT_DATE_FORMAT);
                }
            }
            this.f5241k.d0(dateFormatU.format((Date) obj));
            return;
        }
        if (!(obj instanceof byte[])) {
            if (!(obj instanceof Collection)) {
                H(obj);
                return;
            }
            Collection collection = (Collection) obj;
            Iterator it = collection.iterator();
            this.f5241k.write(91);
            for (int i8 = 0; i8 < collection.size(); i8++) {
                Object next = it.next();
                if (i8 != 0) {
                    this.f5241k.write(44);
                }
                N(next, str);
            }
            this.f5241k.write(93);
            return;
        }
        byte[] bArr = (byte[]) obj;
        if (!"gzip".equals(str) && !"gzip,base64".equals(str)) {
            if ("hex".equals(str)) {
                this.f5241k.T(bArr);
                return;
            } else {
                this.f5241k.x(bArr);
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
                this.f5241k.x(byteArrayOutputStream.toByteArray());
            } catch (IOException e8) {
                throw new JSONException("write gzipBytes error", e8);
            }
        } finally {
            l2.e.a(gZIPOutputStream);
        }
    }

    public void q(SerializerFeature serializerFeature, boolean z7) {
        this.f5241k.m(serializerFeature, z7);
    }

    public boolean r(Object obj) {
        y0 y0Var;
        IdentityHashMap<Object, y0> identityHashMap = this.f5247q;
        if (identityHashMap == null || (y0Var = identityHashMap.get(obj)) == null || obj == Collections.emptyMap()) {
            return false;
        }
        Object obj2 = y0Var.f5317c;
        return obj2 == null || (obj2 instanceof Integer) || (obj2 instanceof String);
    }

    public void s() {
        this.f5242l--;
    }

    public final DateFormat t(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, this.f5250t);
        simpleDateFormat.setTimeZone(this.f5249s);
        return simpleDateFormat;
    }

    public String toString() {
        return this.f5241k.toString();
    }

    public DateFormat u() {
        String str;
        if (this.f5245o == null && (str = this.f5244n) != null) {
            this.f5245o = t(str);
        }
        return this.f5245o;
    }

    public String v() {
        DateFormat dateFormat = this.f5245o;
        return dateFormat instanceof SimpleDateFormat ? ((SimpleDateFormat) dateFormat).toPattern() : this.f5244n;
    }

    public String w() {
        return this.f5246p;
    }

    public t0 x(Class<?> cls) {
        return this.f5240j.h(cls);
    }

    public d1 y() {
        return this.f5241k;
    }

    public void z() {
        this.f5242l++;
    }

    public i0(d1 d1Var) {
        this(d1Var, a1.g());
    }

    public i0(d1 d1Var, a1 a1Var) {
        this.f5242l = 0;
        this.f5243m = "\t";
        this.f5247q = null;
        this.f5249s = c2.a.defaultTimeZone;
        this.f5250t = c2.a.defaultLocale;
        this.f5241k = d1Var;
        this.f5240j = a1Var;
    }
}
