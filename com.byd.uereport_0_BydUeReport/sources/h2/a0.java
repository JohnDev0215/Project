package h2;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/* JADX INFO: compiled from: FieldSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class a0 implements Comparable<a0> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l2.c f5150d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f5151e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5152f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f5153g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f5154h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f5155i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public j f5156j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f5157k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f5158l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5159m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f5160n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f5161o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f5162p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f5163q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public a f5164r;

    /* JADX INFO: compiled from: FieldSerializer.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final t0 f5165a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Class<?> f5166b;

        public a(t0 t0Var, Class<?> cls) {
            this.f5165a = t0Var;
            this.f5166b = cls;
        }
    }

    public a0(Class<?> cls, l2.c cVar) {
        boolean z7;
        d2.d dVar;
        this.f5158l = false;
        this.f5159m = false;
        this.f5160n = false;
        this.f5162p = false;
        this.f5150d = cVar;
        this.f5156j = new j(cls, cVar);
        if (cls != null && (dVar = (d2.d) l2.l.M(cls, d2.d.class)) != null) {
            for (SerializerFeature serializerFeature : dVar.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.WriteEnumUsingToString) {
                    this.f5158l = true;
                } else if (serializerFeature == SerializerFeature.WriteEnumUsingName) {
                    this.f5159m = true;
                } else if (serializerFeature == SerializerFeature.DisableCircularReferenceDetect) {
                    this.f5160n = true;
                } else {
                    SerializerFeature serializerFeature2 = SerializerFeature.BrowserCompatible;
                    if (serializerFeature == serializerFeature2) {
                        this.f5152f |= serializerFeature2.mask;
                        this.f5163q = true;
                    } else {
                        SerializerFeature serializerFeature3 = SerializerFeature.WriteMapNullValue;
                        if (serializerFeature == serializerFeature3) {
                            this.f5152f |= serializerFeature3.mask;
                        }
                    }
                }
            }
        }
        cVar.o();
        this.f5153g = '\"' + cVar.f6169d + "\":";
        d2.b bVarD = cVar.d();
        if (bVarD != null) {
            SerializerFeature[] serializerFeatureArrSerialzeFeatures = bVarD.serialzeFeatures();
            int length = serializerFeatureArrSerialzeFeatures.length;
            int i8 = 0;
            while (true) {
                if (i8 >= length) {
                    z7 = false;
                    break;
                } else {
                    if ((serializerFeatureArrSerialzeFeatures[i8].getMask() & SerializerFeature.WRITE_MAP_NULL_FEATURES) != 0) {
                        z7 = true;
                        break;
                    }
                    i8++;
                }
            }
            String str = bVarD.format();
            this.f5157k = str;
            if (str.trim().length() == 0) {
                this.f5157k = null;
            }
            for (SerializerFeature serializerFeature4 : bVarD.serialzeFeatures()) {
                if (serializerFeature4 == SerializerFeature.WriteEnumUsingToString) {
                    this.f5158l = true;
                } else if (serializerFeature4 == SerializerFeature.WriteEnumUsingName) {
                    this.f5159m = true;
                } else if (serializerFeature4 == SerializerFeature.DisableCircularReferenceDetect) {
                    this.f5160n = true;
                } else if (serializerFeature4 == SerializerFeature.BrowserCompatible) {
                    this.f5163q = true;
                }
            }
            this.f5152f = SerializerFeature.of(bVarD.serialzeFeatures()) | this.f5152f;
        } else {
            z7 = false;
        }
        this.f5151e = z7;
        this.f5162p = l2.l.m0(cVar.f6170e) || l2.l.l0(cVar.f6170e);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(a0 a0Var) {
        return this.f5150d.compareTo(a0Var.f5150d);
    }

    public Object b(Object obj) throws IllegalAccessException, InvocationTargetException {
        Object objC = this.f5150d.c(obj);
        if (this.f5157k == null || objC == null) {
            return objC;
        }
        Class<?> cls = this.f5150d.f6173h;
        if (cls != Date.class && cls != java.sql.Date.class) {
            return objC;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.f5157k, c2.a.defaultLocale);
        simpleDateFormat.setTimeZone(c2.a.defaultTimeZone);
        return simpleDateFormat.format(objC);
    }

    public Object c(Object obj) throws IllegalAccessException, InvocationTargetException {
        Object objC = this.f5150d.c(obj);
        if (!this.f5162p || l2.l.p0(objC)) {
            return objC;
        }
        return null;
    }

    public void d(i0 i0Var) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (!d1Var.f5218o) {
            if (this.f5155i == null) {
                this.f5155i = this.f5150d.f6169d + ":";
            }
            d1Var.write(this.f5155i);
            return;
        }
        if (!SerializerFeature.isEnabled(d1Var.f5215l, this.f5150d.f6177l, SerializerFeature.UseSingleQuotes)) {
            d1Var.write(this.f5153g);
            return;
        }
        if (this.f5154h == null) {
            this.f5154h = '\'' + this.f5150d.f6169d + "':";
        }
        d1Var.write(this.f5154h);
    }

    public void e(i0 i0Var, Object obj) throws Exception {
        Class<?> cls;
        Class<?> cls2;
        t0 t0VarX;
        if (this.f5164r == null) {
            if (obj == null) {
                cls2 = this.f5150d.f6173h;
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
            d2.b bVarD = this.f5150d.d();
            if (bVarD == null || bVarD.serializeUsing() == Void.class) {
                if (this.f5157k != null) {
                    if (cls2 == Double.TYPE || cls2 == Double.class) {
                        xVar = new x(this.f5157k);
                    } else if (cls2 == Float.TYPE || cls2 == Float.class) {
                        xVar = new b0(this.f5157k);
                    }
                }
                t0VarX = xVar == null ? i0Var.x(cls2) : xVar;
            } else {
                t0VarX = (t0) bVarD.serializeUsing().newInstance();
                this.f5161o = true;
            }
            this.f5164r = new a(t0VarX, cls2);
        }
        a aVar = this.f5164r;
        int i8 = (this.f5160n ? this.f5150d.f6177l | SerializerFeature.DisableCircularReferenceDetect.mask : this.f5150d.f6177l) | this.f5152f;
        if (obj == null) {
            d1 d1Var = i0Var.f5241k;
            if (this.f5150d.f6173h == Object.class && d1Var.r(SerializerFeature.WRITE_MAP_NULL_FEATURES)) {
                d1Var.Z();
                return;
            }
            Class<?> cls3 = aVar.f5166b;
            if (Number.class.isAssignableFrom(cls3)) {
                d1Var.a0(this.f5152f, SerializerFeature.WriteNullNumberAsZero.mask);
                return;
            }
            if (String.class == cls3) {
                d1Var.a0(this.f5152f, SerializerFeature.WriteNullStringAsEmpty.mask);
                return;
            }
            if (Boolean.class == cls3) {
                d1Var.a0(this.f5152f, SerializerFeature.WriteNullBooleanAsFalse.mask);
                return;
            }
            if (Collection.class.isAssignableFrom(cls3) || cls3.isArray()) {
                d1Var.a0(this.f5152f, SerializerFeature.WriteNullListAsEmpty.mask);
                return;
            }
            t0 t0Var = aVar.f5165a;
            if (d1Var.r(SerializerFeature.WRITE_MAP_NULL_FEATURES) && (t0Var instanceof j0)) {
                d1Var.Z();
                return;
            } else {
                l2.c cVar = this.f5150d;
                t0Var.c(i0Var, null, cVar.f6169d, cVar.f6174i, i8);
                return;
            }
        }
        if (this.f5150d.f6185t) {
            if (this.f5159m) {
                i0Var.f5241k.d0(((Enum) obj).name());
                return;
            } else if (this.f5158l) {
                i0Var.f5241k.d0(((Enum) obj).toString());
                return;
            }
        }
        Class<?> cls4 = obj.getClass();
        t0 t0VarX2 = (cls4 == aVar.f5166b || this.f5161o) ? aVar.f5165a : i0Var.x(cls4);
        String str = this.f5157k;
        if (str != null && !(t0VarX2 instanceof x) && !(t0VarX2 instanceof b0)) {
            if (t0VarX2 instanceof u) {
                ((u) t0VarX2).d(i0Var, obj, this.f5156j);
                return;
            } else {
                i0Var.N(obj, str);
                return;
            }
        }
        l2.c cVar2 = this.f5150d;
        if (cVar2.f6187v) {
            if (t0VarX2 instanceof j0) {
                ((j0) t0VarX2).z(i0Var, obj, cVar2.f6169d, cVar2.f6174i, i8, true);
                return;
            } else if (t0VarX2 instanceof p0) {
                ((p0) t0VarX2).q(i0Var, obj, cVar2.f6169d, cVar2.f6174i, i8, true);
                return;
            }
        }
        if ((this.f5152f & SerializerFeature.WriteClassName.mask) != 0 && cls4 != cVar2.f6173h && (t0VarX2 instanceof j0)) {
            ((j0) t0VarX2).z(i0Var, obj, cVar2.f6169d, cVar2.f6174i, i8, false);
            return;
        }
        if (this.f5163q && ((cls = cVar2.f6173h) == Long.TYPE || cls == Long.class)) {
            long jLongValue = ((Long) obj).longValue();
            if (jLongValue > 9007199254740991L || jLongValue < -9007199254740991L) {
                i0Var.y().d0(Long.toString(jLongValue));
                return;
            }
        }
        l2.c cVar3 = this.f5150d;
        t0VarX2.c(i0Var, obj, cVar3.f6169d, cVar3.f6174i, i8);
    }
}
