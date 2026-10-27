package b.a.a.k;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/* JADX INFO: compiled from: FieldSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class a0 implements Comparable<a0> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.a.a.n.c f321a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f322b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f323c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f324d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f325e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f326f;
    public j g;
    public String h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l = false;
    public boolean m;
    public boolean n;
    public a o;

    /* JADX INFO: compiled from: FieldSerializer.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final t0 f327a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Class<?> f328b;

        public a(t0 t0Var, Class<?> cls) {
            this.f327a = t0Var;
            this.f328b = cls;
        }
    }

    public a0(Class<?> cls, b.a.a.n.c cVar) {
        boolean z;
        b.a.a.h.d dVar;
        this.i = false;
        this.j = false;
        this.k = false;
        this.m = false;
        this.f321a = cVar;
        this.g = new j(cls, cVar);
        if (cls != null && (dVar = (b.a.a.h.d) b.a.a.n.l.M(cls, b.a.a.h.d.class)) != null) {
            for (SerializerFeature serializerFeature : dVar.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.WriteEnumUsingToString) {
                    this.i = true;
                } else if (serializerFeature == SerializerFeature.WriteEnumUsingName) {
                    this.j = true;
                } else if (serializerFeature == SerializerFeature.DisableCircularReferenceDetect) {
                    this.k = true;
                } else {
                    SerializerFeature serializerFeature2 = SerializerFeature.BrowserCompatible;
                    if (serializerFeature == serializerFeature2) {
                        this.f323c |= serializerFeature2.mask;
                        this.n = true;
                    } else {
                        SerializerFeature serializerFeature3 = SerializerFeature.WriteMapNullValue;
                        if (serializerFeature == serializerFeature3) {
                            this.f323c |= serializerFeature3.mask;
                        }
                    }
                }
            }
        }
        cVar.o();
        this.f324d = '\"' + cVar.f429a + "\":";
        b.a.a.h.b bVarD = cVar.d();
        if (bVarD != null) {
            SerializerFeature[] serializerFeatureArrSerialzeFeatures = bVarD.serialzeFeatures();
            int length = serializerFeatureArrSerialzeFeatures.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                } else {
                    if ((serializerFeatureArrSerialzeFeatures[i].getMask() & SerializerFeature.WRITE_MAP_NULL_FEATURES) != 0) {
                        z = true;
                        break;
                    }
                    i++;
                }
            }
            String str = bVarD.format();
            this.h = str;
            if (str.trim().length() == 0) {
                this.h = null;
            }
            for (SerializerFeature serializerFeature4 : bVarD.serialzeFeatures()) {
                if (serializerFeature4 == SerializerFeature.WriteEnumUsingToString) {
                    this.i = true;
                } else if (serializerFeature4 == SerializerFeature.WriteEnumUsingName) {
                    this.j = true;
                } else if (serializerFeature4 == SerializerFeature.DisableCircularReferenceDetect) {
                    this.k = true;
                } else if (serializerFeature4 == SerializerFeature.BrowserCompatible) {
                    this.n = true;
                }
            }
            this.f323c = SerializerFeature.of(bVarD.serialzeFeatures()) | this.f323c;
        } else {
            z = false;
        }
        this.f322b = z;
        this.m = b.a.a.n.l.m0(cVar.f430b) || b.a.a.n.l.l0(cVar.f430b);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(a0 a0Var) {
        return this.f321a.compareTo(a0Var.f321a);
    }

    public Object b(Object obj) throws IllegalAccessException, InvocationTargetException {
        Object objC = this.f321a.c(obj);
        if (this.h == null || objC == null) {
            return objC;
        }
        Class<?> cls = this.f321a.f433e;
        if (cls != Date.class && cls != java.sql.Date.class) {
            return objC;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.h, b.a.a.a.defaultLocale);
        simpleDateFormat.setTimeZone(b.a.a.a.defaultTimeZone);
        return simpleDateFormat.format(objC);
    }

    public Object c(Object obj) throws IllegalAccessException, InvocationTargetException {
        Object objC = this.f321a.c(obj);
        if (!this.m || b.a.a.n.l.p0(objC)) {
            return objC;
        }
        return null;
    }

    public void d(i0 i0Var) throws IOException {
        d1 d1Var = i0Var.k;
        if (!d1Var.l) {
            if (this.f326f == null) {
                this.f326f = this.f321a.f429a + ":";
            }
            d1Var.write(this.f326f);
            return;
        }
        if (!SerializerFeature.isEnabled(d1Var.i, this.f321a.i, SerializerFeature.UseSingleQuotes)) {
            d1Var.write(this.f324d);
            return;
        }
        if (this.f325e == null) {
            this.f325e = '\'' + this.f321a.f429a + "':";
        }
        d1Var.write(this.f325e);
    }

    public void e(i0 i0Var, Object obj) throws Exception {
        Class<?> cls;
        Class<?> cls2;
        t0 t0VarX;
        if (this.o == null) {
            if (obj == null) {
                cls2 = this.f321a.f433e;
                if (cls2 == Byte.TYPE) {
                    cls2 = Byte.class;
                } else if (cls2 == Short.TYPE) {
                    cls2 = Short.class;
                } else if (cls2 == Integer.TYPE) {
                    cls2 = Integer.class;
                } else if (cls2 == Long.TYPE) {
                    cls2 = Long.class;
                } else if (cls2 == Float.TYPE) {
                    cls2 = Float.class;
                } else if (cls2 == Double.TYPE) {
                    cls2 = Double.class;
                } else if (cls2 == Boolean.TYPE) {
                    cls2 = Boolean.class;
                }
            } else {
                cls2 = obj.getClass();
            }
            t0 xVar = null;
            b.a.a.h.b bVarD = this.f321a.d();
            if (bVarD == null || bVarD.serializeUsing() == Void.class) {
                if (this.h != null) {
                    if (cls2 == Double.TYPE || cls2 == Double.class) {
                        xVar = new x(this.h);
                    } else if (cls2 == Float.TYPE || cls2 == Float.class) {
                        xVar = new b0(this.h);
                    }
                }
                t0VarX = xVar == null ? i0Var.x(cls2) : xVar;
            } else {
                t0VarX = (t0) bVarD.serializeUsing().newInstance();
                this.l = true;
            }
            this.o = new a(t0VarX, cls2);
        }
        a aVar = this.o;
        int i = (this.k ? this.f321a.i | SerializerFeature.DisableCircularReferenceDetect.mask : this.f321a.i) | this.f323c;
        if (obj == null) {
            d1 d1Var = i0Var.k;
            if (this.f321a.f433e == Object.class && d1Var.k(SerializerFeature.WRITE_MAP_NULL_FEATURES)) {
                d1Var.J();
                return;
            }
            Class<?> cls3 = aVar.f328b;
            if (Number.class.isAssignableFrom(cls3)) {
                d1Var.L(this.f323c, SerializerFeature.WriteNullNumberAsZero.mask);
                return;
            }
            if (String.class == cls3) {
                d1Var.L(this.f323c, SerializerFeature.WriteNullStringAsEmpty.mask);
                return;
            }
            if (Boolean.class == cls3) {
                d1Var.L(this.f323c, SerializerFeature.WriteNullBooleanAsFalse.mask);
                return;
            }
            if (Collection.class.isAssignableFrom(cls3) || cls3.isArray()) {
                d1Var.L(this.f323c, SerializerFeature.WriteNullListAsEmpty.mask);
                return;
            }
            t0 t0Var = aVar.f327a;
            if (d1Var.k(SerializerFeature.WRITE_MAP_NULL_FEATURES) && (t0Var instanceof j0)) {
                d1Var.J();
                return;
            } else {
                b.a.a.n.c cVar = this.f321a;
                t0Var.c(i0Var, null, cVar.f429a, cVar.f434f, i);
                return;
            }
        }
        if (this.f321a.q) {
            if (this.j) {
                i0Var.k.N(((Enum) obj).name());
                return;
            } else if (this.i) {
                i0Var.k.N(((Enum) obj).toString());
                return;
            }
        }
        Class<?> cls4 = obj.getClass();
        t0 t0VarX2 = (cls4 == aVar.f328b || this.l) ? aVar.f327a : i0Var.x(cls4);
        String str = this.h;
        if (str != null && !(t0VarX2 instanceof x) && !(t0VarX2 instanceof b0)) {
            if (t0VarX2 instanceof u) {
                ((u) t0VarX2).d(i0Var, obj, this.g);
                return;
            } else {
                i0Var.N(obj, str);
                return;
            }
        }
        b.a.a.n.c cVar2 = this.f321a;
        if (cVar2.s) {
            if (t0VarX2 instanceof j0) {
                ((j0) t0VarX2).z(i0Var, obj, cVar2.f429a, cVar2.f434f, i, true);
                return;
            } else if (t0VarX2 instanceof p0) {
                ((p0) t0VarX2).q(i0Var, obj, cVar2.f429a, cVar2.f434f, i, true);
                return;
            }
        }
        if ((this.f323c & SerializerFeature.WriteClassName.mask) != 0 && cls4 != cVar2.f433e && (t0VarX2 instanceof j0)) {
            ((j0) t0VarX2).z(i0Var, obj, cVar2.f429a, cVar2.f434f, i, false);
            return;
        }
        if (this.n && ((cls = cVar2.f433e) == Long.TYPE || cls == Long.class)) {
            long jLongValue = ((Long) obj).longValue();
            if (jLongValue > 9007199254740991L || jLongValue < -9007199254740991L) {
                i0Var.y().N(Long.toString(jLongValue));
                return;
            }
        }
        b.a.a.n.c cVar3 = this.f321a;
        t0VarX2.c(i0Var, obj, cVar3.f429a, cVar3.f434f, i);
    }
}
