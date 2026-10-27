package f2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.Feature;
import g2.j;
import g2.k;
import g2.l;
import g2.m;
import g2.o;
import g2.t;
import g2.y;
import h2.d0;
import h2.e1;
import h2.o0;
import java.io.Closeable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: compiled from: DefaultJSONParser.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements Closeable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Set<Class<?>> f4855d = new HashSet();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f4856e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i f4857f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public h f4858g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f4859h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public DateFormat f4860i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final b f4861j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public g f4862k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public g[] f4863l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4864m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List<C0039a> f4865n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f4866o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List<k> f4867p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List<j> f4868q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public m f4869r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f4870s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String[] f4871t;

    /* JADX INFO: renamed from: f2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DefaultJSONParser.java */
    public static class C0039a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final g f4872a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f4873b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public l f4874c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public g f4875d;

        public C0039a(g gVar, String str) {
            this.f4872a = gVar;
            this.f4873b = str;
        }
    }

    static {
        Class<?>[] clsArr = {Boolean.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class, String.class};
        for (int i8 = 0; i8 < 17; i8++) {
            f4855d.add(clsArr[i8]);
        }
    }

    public a(String str) {
        this(str, h.s(), c2.a.DEFAULT_PARSER_FEATURE);
    }

    public C0039a B() {
        return this.f4865n.get(r0.size() - 1);
    }

    public b C() {
        return this.f4861j;
    }

    public Object E(String str) {
        for (int i8 = 0; i8 < this.f4864m; i8++) {
            if (str.equals(this.f4863l[i8].toString())) {
                return this.f4863l[i8].f4901a;
            }
        }
        return null;
    }

    public int F() {
        return this.f4866o;
    }

    public i I() {
        return this.f4857f;
    }

    public void L(Object obj) {
        Object objE;
        l2.c cVar;
        List<C0039a> list = this.f4865n;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i8 = 0; i8 < size; i8++) {
            C0039a c0039a = this.f4865n.get(i8);
            String str = c0039a.f4873b;
            g gVar = c0039a.f4875d;
            Object obj2 = gVar != null ? gVar.f4901a : null;
            if (str.startsWith("$")) {
                objE = E(str);
                if (objE == null) {
                    try {
                        JSONPath jSONPathB = JSONPath.b(str);
                        if (jSONPathB.n()) {
                            objE = jSONPathB.e(obj);
                        }
                    } catch (JSONPathException unused) {
                    }
                }
            } else {
                objE = c0039a.f4872a.f4901a;
            }
            l lVar = c0039a.f4874c;
            if (lVar != null) {
                if (objE != null && objE.getClass() == JSONObject.class && (cVar = lVar.f5036a) != null && !Map.class.isAssignableFrom(cVar.f6173h)) {
                    Object obj3 = this.f4863l[0].f4901a;
                    JSONPath jSONPathB2 = JSONPath.b(str);
                    if (jSONPathB2.n()) {
                        objE = jSONPathB2.e(obj3);
                    }
                }
                lVar.g(obj2, objE);
            }
        }
    }

    public boolean M(Feature feature) {
        return this.f4861j.M(feature);
    }

    public Object N() {
        return P(null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x0236, code lost:
    
        return r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object O(g2.w r11, java.lang.Object r12) {
        /*
            Method dump skipped, instruction units count: 613
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.a.O(g2.w, java.lang.Object):java.lang.Object");
    }

    public Object P(Object obj) {
        b bVar = this.f4861j;
        int iQ = bVar.q();
        if (iQ == 2) {
            Number numberJ = bVar.j();
            bVar.E();
            return numberJ;
        }
        if (iQ == 3) {
            Number numberD0 = bVar.d0(bVar.M(Feature.UseBigDecimal));
            bVar.E();
            return numberD0;
        }
        if (iQ == 4) {
            String strN0 = bVar.n0();
            bVar.V(16);
            if (bVar.M(Feature.AllowISO8601DateFormat)) {
                e eVar = new e(strN0);
                try {
                    if (eVar.j1()) {
                        return eVar.w0().getTime();
                    }
                } finally {
                    eVar.close();
                }
            }
            return strN0;
        }
        if (iQ == 12) {
            return k0(new JSONObject(bVar.M(Feature.OrderedField)), obj);
        }
        if (iQ == 14) {
            JSONArray jSONArray = new JSONArray();
            Y(jSONArray, obj);
            return bVar.M(Feature.UseObjectArray) ? jSONArray.toArray() : jSONArray;
        }
        if (iQ == 18) {
            if ("NaN".equals(bVar.n0())) {
                bVar.E();
                return null;
            }
            throw new JSONException("syntax error, " + bVar.g());
        }
        if (iQ == 26) {
            byte[] bArrE0 = bVar.e0();
            bVar.E();
            return bArrE0;
        }
        switch (iQ) {
            case 6:
                bVar.E();
                return Boolean.TRUE;
            case 7:
                bVar.E();
                return Boolean.FALSE;
            case 8:
                bVar.E();
                return null;
            case 9:
                bVar.V(18);
                if (bVar.q() != 18) {
                    throw new JSONException("syntax error");
                }
                bVar.V(10);
                e(10);
                long jLongValue = bVar.j().longValue();
                e(2);
                e(11);
                return new Date(jLongValue);
            default:
                switch (iQ) {
                    case 20:
                        if (bVar.o()) {
                            return null;
                        }
                        throw new JSONException("unterminated json string, " + bVar.g());
                    case 21:
                        bVar.E();
                        HashSet hashSet = new HashSet();
                        Y(hashSet, obj);
                        return hashSet;
                    case 22:
                        bVar.E();
                        TreeSet treeSet = new TreeSet();
                        Y(treeSet, obj);
                        return treeSet;
                    case 23:
                        bVar.E();
                        return null;
                    default:
                        throw new JSONException("syntax error, " + bVar.g());
                }
        }
    }

    public void R(Class<?> cls, Collection collection) {
        T(cls, collection);
    }

    public void T(Type type, Collection collection) {
        V(type, collection, null);
    }

    public void V(Type type, Collection collection, Object obj) {
        t tVarO;
        int iQ = this.f4861j.q();
        if (iQ == 21 || iQ == 22) {
            this.f4861j.E();
            iQ = this.f4861j.q();
        }
        if (iQ != 14) {
            throw new JSONException("expect '[', but " + f.a(iQ) + ", " + this.f4861j.g());
        }
        if (Integer.TYPE == type) {
            tVarO = d0.f5206a;
            this.f4861j.V(2);
        } else if (String.class == type) {
            tVarO = e1.f5232a;
            this.f4861j.V(4);
        } else {
            tVarO = this.f4858g.o(type);
            this.f4861j.V(tVarO.e());
        }
        g gVar = this.f4862k;
        q0(collection, obj);
        int i8 = 0;
        while (true) {
            try {
                if (this.f4861j.M(Feature.AllowArbitraryCommas)) {
                    while (this.f4861j.q() == 16) {
                        this.f4861j.E();
                    }
                }
                if (this.f4861j.q() == 15) {
                    r0(gVar);
                    this.f4861j.V(16);
                    return;
                }
                Object objB = null;
                if (Integer.TYPE == type) {
                    collection.add(d0.f5206a.b(this, null, null));
                } else if (String.class == type) {
                    if (this.f4861j.q() == 4) {
                        objB = this.f4861j.n0();
                        this.f4861j.V(16);
                    } else {
                        Object objN = N();
                        if (objN != null) {
                            objB = objN.toString();
                        }
                    }
                    collection.add(objB);
                } else {
                    if (this.f4861j.q() == 8) {
                        this.f4861j.E();
                    } else {
                        objB = tVarO.b(this, type, Integer.valueOf(i8));
                    }
                    collection.add(objB);
                    m(collection);
                }
                if (this.f4861j.q() == 16) {
                    this.f4861j.V(tVarO.e());
                }
                i8++;
            } catch (Throwable th) {
                r0(gVar);
                throw th;
            }
        }
    }

    public final void X(Collection collection) {
        Y(collection, null);
    }

    public final void Y(Collection collection, Object obj) {
        b bVar = this.f4861j;
        if (bVar.q() == 21 || bVar.q() == 22) {
            bVar.E();
        }
        if (bVar.q() != 14) {
            throw new JSONException("syntax error, expect [, actual " + f.a(bVar.q()) + ", pos " + bVar.e() + ", fieldName " + obj);
        }
        bVar.V(4);
        g gVar = this.f4862k;
        if (gVar != null && gVar.f4904d > 512) {
            throw new JSONException("array level > 512");
        }
        q0(collection, obj);
        int i8 = 0;
        while (true) {
            try {
                if (bVar.M(Feature.AllowArbitraryCommas)) {
                    while (bVar.q() == 16) {
                        bVar.E();
                    }
                }
                int iQ = bVar.q();
                Object objD0 = null;
                objD0 = null;
                if (iQ == 2) {
                    Number numberJ = bVar.j();
                    bVar.V(16);
                    objD0 = numberJ;
                } else if (iQ == 3) {
                    objD0 = bVar.M(Feature.UseBigDecimal) ? bVar.d0(true) : bVar.d0(false);
                    bVar.V(16);
                } else if (iQ == 4) {
                    String strN0 = bVar.n0();
                    bVar.V(16);
                    objD0 = strN0;
                    if (bVar.M(Feature.AllowISO8601DateFormat)) {
                        e eVar = new e(strN0);
                        Object time = strN0;
                        if (eVar.j1()) {
                            time = eVar.w0().getTime();
                        }
                        eVar.close();
                        objD0 = time;
                    }
                } else if (iQ == 6) {
                    Boolean bool = Boolean.TRUE;
                    bVar.V(16);
                    objD0 = bool;
                } else if (iQ == 7) {
                    Boolean bool2 = Boolean.FALSE;
                    bVar.V(16);
                    objD0 = bool2;
                } else if (iQ == 8) {
                    bVar.V(4);
                } else if (iQ == 12) {
                    objD0 = k0(new JSONObject(bVar.M(Feature.OrderedField)), Integer.valueOf(i8));
                } else {
                    if (iQ == 20) {
                        throw new JSONException("unclosed jsonArray");
                    }
                    if (iQ == 23) {
                        bVar.V(4);
                    } else if (iQ == 14) {
                        JSONArray jSONArray = new JSONArray();
                        Y(jSONArray, Integer.valueOf(i8));
                        objD0 = jSONArray;
                        if (bVar.M(Feature.UseObjectArray)) {
                            objD0 = jSONArray.toArray();
                        }
                    } else {
                        if (iQ == 15) {
                            bVar.V(16);
                            return;
                        }
                        objD0 = N();
                    }
                }
                collection.add(objD0);
                m(collection);
                if (bVar.q() == 16) {
                    bVar.V(4);
                }
                i8++;
            } finally {
                r0(gVar);
            }
        }
    }

    public Object[] Z(Type[] typeArr) {
        Object objH;
        Class<?> componentType;
        boolean zIsArray;
        Class cls;
        int i8 = 8;
        if (this.f4861j.q() == 8) {
            this.f4861j.V(16);
            return null;
        }
        int i9 = 14;
        if (this.f4861j.q() != 14) {
            throw new JSONException("syntax error : " + this.f4861j.c0());
        }
        Object[] objArr = new Object[typeArr.length];
        if (typeArr.length == 0) {
            this.f4861j.V(15);
            if (this.f4861j.q() != 15) {
                throw new JSONException("syntax error");
            }
            this.f4861j.V(16);
            return new Object[0];
        }
        this.f4861j.V(2);
        int i10 = 0;
        while (i10 < typeArr.length) {
            if (this.f4861j.q() == i8) {
                this.f4861j.V(16);
                objH = null;
            } else {
                Type type = typeArr[i10];
                if (type == Integer.TYPE || type == Integer.class) {
                    if (this.f4861j.q() == 2) {
                        objH = Integer.valueOf(this.f4861j.N());
                        this.f4861j.V(16);
                    } else {
                        objH = l2.l.h(N(), type, this.f4858g);
                    }
                } else if (type != String.class) {
                    if (i10 == typeArr.length - 1 && (type instanceof Class) && (((cls = (Class) type) != byte[].class && cls != char[].class) || this.f4861j.q() != 4)) {
                        zIsArray = cls.isArray();
                        componentType = cls.getComponentType();
                    } else {
                        componentType = null;
                        zIsArray = false;
                    }
                    if (!zIsArray || this.f4861j.q() == i9) {
                        objH = this.f4858g.o(type).b(this, type, Integer.valueOf(i10));
                    } else {
                        ArrayList arrayList = new ArrayList();
                        t tVarO = this.f4858g.o(componentType);
                        int iE = tVarO.e();
                        if (this.f4861j.q() != 15) {
                            while (true) {
                                arrayList.add(tVarO.b(this, type, null));
                                if (this.f4861j.q() != 16) {
                                    break;
                                }
                                this.f4861j.V(iE);
                            }
                            if (this.f4861j.q() != 15) {
                                throw new JSONException("syntax error :" + f.a(this.f4861j.q()));
                            }
                        }
                        objH = l2.l.h(arrayList, type, this.f4858g);
                    }
                } else if (this.f4861j.q() == 4) {
                    objH = this.f4861j.n0();
                    this.f4861j.V(16);
                } else {
                    objH = l2.l.h(N(), type, this.f4858g);
                }
            }
            objArr[i10] = objH;
            if (this.f4861j.q() == 15) {
                break;
            }
            if (this.f4861j.q() != 16) {
                throw new JSONException("syntax error :" + f.a(this.f4861j.q()));
            }
            if (i10 == typeArr.length - 1) {
                this.f4861j.V(15);
            } else {
                this.f4861j.V(2);
            }
            i10++;
            i8 = 8;
            i9 = 14;
        }
        if (this.f4861j.q() != 15) {
            throw new JSONException("syntax error");
        }
        this.f4861j.V(16);
        return objArr;
    }

    public void a0(Object obj, String str) {
        this.f4861j.Y();
        List<k> list = this.f4867p;
        Type typeA = null;
        if (list != null) {
            Iterator<k> it = list.iterator();
            while (it.hasNext()) {
                typeA = it.next().a(obj, str);
            }
        }
        Object objN = typeA == null ? N() : e0(typeA);
        if (obj instanceof g2.i) {
            ((g2.i) obj).a(str, objN);
            return;
        }
        List<j> list2 = this.f4868q;
        if (list2 != null) {
            Iterator<j> it2 = list2.iterator();
            while (it2.hasNext()) {
                it2.next().b(obj, str, objN);
            }
        }
        if (this.f4866o == 1) {
            this.f4866o = 0;
        }
    }

    public JSONObject c0() {
        Object objH0 = h0(new JSONObject(this.f4861j.M(Feature.OrderedField)));
        if (objH0 instanceof JSONObject) {
            return (JSONObject) objH0;
        }
        if (objH0 == null) {
            return null;
        }
        return new JSONObject((Map<String, Object>) objH0);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        b bVar = this.f4861j;
        try {
            if (bVar.M(Feature.AutoCloseSource) && bVar.q() != 20) {
                throw new JSONException("not close json text, token : " + f.a(bVar.q()));
            }
        } finally {
            bVar.close();
        }
    }

    public <T> T d0(Class<T> cls) {
        return (T) g0(cls, null);
    }

    public final void e(int i8) {
        b bVar = this.f4861j;
        if (bVar.q() == i8) {
            bVar.E();
            return;
        }
        throw new JSONException("syntax error, expect " + f.a(i8) + ", actual " + f.a(bVar.q()));
    }

    public <T> T e0(Type type) {
        return (T) g0(type, null);
    }

    public void g(String str) {
        b bVar = this.f4861j;
        bVar.Y();
        if (bVar.q() != 4) {
            throw new JSONException("type not match error");
        }
        if (!str.equals(bVar.n0())) {
            throw new JSONException("type not match error");
        }
        bVar.E();
        if (bVar.q() == 16) {
            bVar.E();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T g0(Type type, Object obj) {
        int iQ = this.f4861j.q();
        if (iQ == 8) {
            this.f4861j.E();
            return null;
        }
        if (iQ == 4) {
            if (type == byte[].class) {
                T t7 = (T) this.f4861j.e0();
                this.f4861j.E();
                return t7;
            }
            if (type == char[].class) {
                String strN0 = this.f4861j.n0();
                this.f4861j.E();
                return (T) strN0.toCharArray();
            }
        }
        t tVarO = this.f4858g.o(type);
        try {
            if (tVarO.getClass() != o.class) {
                return (T) tVarO.b(this, type, obj);
            }
            if (this.f4861j.q() != 12 && this.f4861j.q() != 14) {
                throw new JSONException("syntax error,except start with { or [,but actually start with " + this.f4861j.c0());
            }
            return (T) ((o) tVarO).h(this, type, obj, 0);
        } catch (JSONException e8) {
            throw e8;
        } catch (Throwable th) {
            throw new JSONException(th.getMessage(), th);
        }
    }

    public Object h0(Map map) {
        return k0(map, null);
    }

    public final void i(g gVar) {
        int i8 = this.f4864m;
        this.f4864m = i8 + 1;
        g[] gVarArr = this.f4863l;
        if (gVarArr == null) {
            this.f4863l = new g[8];
        } else if (i8 >= gVarArr.length) {
            g[] gVarArr2 = new g[(gVarArr.length * 3) / 2];
            System.arraycopy(gVarArr, 0, gVarArr2, 0, gVarArr.length);
            this.f4863l = gVarArr2;
        }
        this.f4863l[i8] = gVar;
    }

    public void j(C0039a c0039a) {
        if (this.f4865n == null) {
            this.f4865n = new ArrayList(2);
        }
        this.f4865n.add(c0039a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:142:0x0284, code lost:
    
        r4.V(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x028f, code lost:
    
        if (r4.q() != 13) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0291, code lost:
    
        r4.V(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x029c, code lost:
    
        if ((r17.f4858g.o(r7) instanceof g2.o) == false) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x029e, code lost:
    
        r0 = l2.l.f(r18, r7, r17.f4858g);
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x02a5, code lost:
    
        r0 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x02a6, code lost:
    
        if (r0 != null) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x02aa, code lost:
    
        if (r7 != java.lang.Cloneable.class) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x02ac, code lost:
    
        r0 = new java.util.HashMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x02b8, code lost:
    
        if ("java.util.Collections$EmptyMap".equals(r6) == false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x02ba, code lost:
    
        r0 = java.util.Collections.emptyMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x02c5, code lost:
    
        if ("java.util.Collections$UnmodifiableMap".equals(r6) == false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x02c7, code lost:
    
        r0 = java.util.Collections.unmodifiableMap(new java.util.HashMap());
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x02d1, code lost:
    
        r0 = r7.newInstance();
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x02d8, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x02d9, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x02e1, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create instance error", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x02e2, code lost:
    
        u0(2);
        r3 = r17.f4862k;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x02e8, code lost:
    
        if (r3 == null) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x02ea, code lost:
    
        if (r19 == null) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x02ee, code lost:
    
        if ((r19 instanceof java.lang.Integer) != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x02f4, code lost:
    
        if ((r3.f4903c instanceof java.lang.Integer) != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x02f6, code lost:
    
        m0();
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x02fd, code lost:
    
        if (r18.size() <= 0) goto L178;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x02ff, code lost:
    
        r0 = l2.l.f(r18, r7, r17.f4858g);
        u0(0);
        l0(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x030f, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0310, code lost:
    
        r0 = r17.f4858g.o(r7);
        r3 = r0.getClass();
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x0320, code lost:
    
        if (g2.o.class.isAssignableFrom(r3) == false) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0324, code lost:
    
        if (r3 == g2.o.class) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x0328, code lost:
    
        if (r3 == g2.b0.class) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x032a, code lost:
    
        u0(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0331, code lost:
    
        if ((r0 instanceof g2.r) == false) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0333, code lost:
    
        u0(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x033e, code lost:
    
        return r0.b(r17, r7, r19);
     */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0212 A[Catch: all -> 0x0684, TryCatch #0 {all -> 0x0684, blocks: (B:24:0x0072, B:26:0x0076, B:29:0x0080, B:32:0x0093, B:36:0x00ab, B:115:0x0212, B:116:0x0218, B:118:0x0223, B:120:0x022b, B:124:0x0240, B:126:0x024e, B:141:0x027e, B:142:0x0284, B:144:0x0291, B:145:0x0294, B:147:0x029e, B:152:0x02ac, B:153:0x02b2, B:155:0x02ba, B:156:0x02bf, B:158:0x02c7, B:159:0x02d1, B:163:0x02da, B:164:0x02e1, B:165:0x02e2, B:168:0x02ec, B:170:0x02f0, B:172:0x02f6, B:173:0x02f9, B:175:0x02ff, B:178:0x0310, B:184:0x032a, B:188:0x0337, B:185:0x032f, B:187:0x0333, B:128:0x0255, B:130:0x025b, B:135:0x0268, B:138:0x026e, B:195:0x0349, B:197:0x034f, B:199:0x0357, B:201:0x0361, B:203:0x0372, B:205:0x037d, B:207:0x0385, B:209:0x0389, B:211:0x038f, B:214:0x0394, B:216:0x0398, B:239:0x03f7, B:241:0x03ff, B:244:0x0408, B:245:0x0422, B:218:0x039d, B:220:0x03a5, B:223:0x03ab, B:224:0x03b7, B:227:0x03c0, B:230:0x03c6, B:233:0x03cb, B:234:0x03d7, B:236:0x03e1, B:238:0x03ee, B:246:0x0423, B:247:0x0441, B:250:0x0445, B:252:0x0449, B:254:0x044d, B:257:0x0453, B:261:0x045b, B:267:0x046b, B:269:0x047a, B:271:0x0485, B:272:0x048d, B:273:0x0490, B:285:0x04bc, B:287:0x04c7, B:291:0x04d4, B:294:0x04e4, B:295:0x0504, B:280:0x04a0, B:282:0x04aa, B:284:0x04b9, B:283:0x04af, B:298:0x0509, B:300:0x0513, B:302:0x051b, B:303:0x051e, B:305:0x0529, B:306:0x052d, B:308:0x0538, B:311:0x053f, B:314:0x054c, B:315:0x0551, B:318:0x0556, B:320:0x055b, B:324:0x0566, B:326:0x056e, B:328:0x0583, B:332:0x05a2, B:334:0x05a8, B:337:0x05ae, B:339:0x05b4, B:341:0x05bc, B:344:0x05cd, B:347:0x05d5, B:349:0x05d9, B:350:0x05e0, B:352:0x05e5, B:353:0x05e8, B:355:0x05f0, B:358:0x05fa, B:361:0x0604, B:362:0x0609, B:363:0x060e, B:364:0x0628, B:329:0x058e, B:330:0x0595, B:365:0x0629, B:367:0x063b, B:370:0x0642, B:373:0x064f, B:374:0x066f, B:39:0x00bd, B:40:0x00db, B:43:0x00e0, B:45:0x00eb, B:47:0x00ef, B:49:0x00f3, B:52:0x00f9, B:59:0x0108, B:61:0x0110, B:64:0x0120, B:65:0x0138, B:66:0x0139, B:67:0x013e, B:78:0x0153, B:79:0x0159, B:81:0x0160, B:83:0x0169, B:90:0x017b, B:93:0x0183, B:94:0x019b, B:88:0x0176, B:82:0x0165, B:95:0x019c, B:96:0x01b4, B:102:0x01be, B:104:0x01c6, B:107:0x01d7, B:108:0x01f7, B:109:0x01f8, B:110:0x01fd, B:111:0x01fe, B:113:0x0208, B:375:0x0670, B:376:0x0677, B:377:0x0678, B:378:0x067d, B:379:0x067e, B:380:0x0683), top: B:384:0x0072, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0445 A[Catch: all -> 0x0684, TryCatch #0 {all -> 0x0684, blocks: (B:24:0x0072, B:26:0x0076, B:29:0x0080, B:32:0x0093, B:36:0x00ab, B:115:0x0212, B:116:0x0218, B:118:0x0223, B:120:0x022b, B:124:0x0240, B:126:0x024e, B:141:0x027e, B:142:0x0284, B:144:0x0291, B:145:0x0294, B:147:0x029e, B:152:0x02ac, B:153:0x02b2, B:155:0x02ba, B:156:0x02bf, B:158:0x02c7, B:159:0x02d1, B:163:0x02da, B:164:0x02e1, B:165:0x02e2, B:168:0x02ec, B:170:0x02f0, B:172:0x02f6, B:173:0x02f9, B:175:0x02ff, B:178:0x0310, B:184:0x032a, B:188:0x0337, B:185:0x032f, B:187:0x0333, B:128:0x0255, B:130:0x025b, B:135:0x0268, B:138:0x026e, B:195:0x0349, B:197:0x034f, B:199:0x0357, B:201:0x0361, B:203:0x0372, B:205:0x037d, B:207:0x0385, B:209:0x0389, B:211:0x038f, B:214:0x0394, B:216:0x0398, B:239:0x03f7, B:241:0x03ff, B:244:0x0408, B:245:0x0422, B:218:0x039d, B:220:0x03a5, B:223:0x03ab, B:224:0x03b7, B:227:0x03c0, B:230:0x03c6, B:233:0x03cb, B:234:0x03d7, B:236:0x03e1, B:238:0x03ee, B:246:0x0423, B:247:0x0441, B:250:0x0445, B:252:0x0449, B:254:0x044d, B:257:0x0453, B:261:0x045b, B:267:0x046b, B:269:0x047a, B:271:0x0485, B:272:0x048d, B:273:0x0490, B:285:0x04bc, B:287:0x04c7, B:291:0x04d4, B:294:0x04e4, B:295:0x0504, B:280:0x04a0, B:282:0x04aa, B:284:0x04b9, B:283:0x04af, B:298:0x0509, B:300:0x0513, B:302:0x051b, B:303:0x051e, B:305:0x0529, B:306:0x052d, B:308:0x0538, B:311:0x053f, B:314:0x054c, B:315:0x0551, B:318:0x0556, B:320:0x055b, B:324:0x0566, B:326:0x056e, B:328:0x0583, B:332:0x05a2, B:334:0x05a8, B:337:0x05ae, B:339:0x05b4, B:341:0x05bc, B:344:0x05cd, B:347:0x05d5, B:349:0x05d9, B:350:0x05e0, B:352:0x05e5, B:353:0x05e8, B:355:0x05f0, B:358:0x05fa, B:361:0x0604, B:362:0x0609, B:363:0x060e, B:364:0x0628, B:329:0x058e, B:330:0x0595, B:365:0x0629, B:367:0x063b, B:370:0x0642, B:373:0x064f, B:374:0x066f, B:39:0x00bd, B:40:0x00db, B:43:0x00e0, B:45:0x00eb, B:47:0x00ef, B:49:0x00f3, B:52:0x00f9, B:59:0x0108, B:61:0x0110, B:64:0x0120, B:65:0x0138, B:66:0x0139, B:67:0x013e, B:78:0x0153, B:79:0x0159, B:81:0x0160, B:83:0x0169, B:90:0x017b, B:93:0x0183, B:94:0x019b, B:88:0x0176, B:82:0x0165, B:95:0x019c, B:96:0x01b4, B:102:0x01be, B:104:0x01c6, B:107:0x01d7, B:108:0x01f7, B:109:0x01f8, B:110:0x01fd, B:111:0x01fe, B:113:0x0208, B:375:0x0670, B:376:0x0677, B:377:0x0678, B:378:0x067d, B:379:0x067e, B:380:0x0683), top: B:384:0x0072, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:267:0x046b A[Catch: all -> 0x0684, TryCatch #0 {all -> 0x0684, blocks: (B:24:0x0072, B:26:0x0076, B:29:0x0080, B:32:0x0093, B:36:0x00ab, B:115:0x0212, B:116:0x0218, B:118:0x0223, B:120:0x022b, B:124:0x0240, B:126:0x024e, B:141:0x027e, B:142:0x0284, B:144:0x0291, B:145:0x0294, B:147:0x029e, B:152:0x02ac, B:153:0x02b2, B:155:0x02ba, B:156:0x02bf, B:158:0x02c7, B:159:0x02d1, B:163:0x02da, B:164:0x02e1, B:165:0x02e2, B:168:0x02ec, B:170:0x02f0, B:172:0x02f6, B:173:0x02f9, B:175:0x02ff, B:178:0x0310, B:184:0x032a, B:188:0x0337, B:185:0x032f, B:187:0x0333, B:128:0x0255, B:130:0x025b, B:135:0x0268, B:138:0x026e, B:195:0x0349, B:197:0x034f, B:199:0x0357, B:201:0x0361, B:203:0x0372, B:205:0x037d, B:207:0x0385, B:209:0x0389, B:211:0x038f, B:214:0x0394, B:216:0x0398, B:239:0x03f7, B:241:0x03ff, B:244:0x0408, B:245:0x0422, B:218:0x039d, B:220:0x03a5, B:223:0x03ab, B:224:0x03b7, B:227:0x03c0, B:230:0x03c6, B:233:0x03cb, B:234:0x03d7, B:236:0x03e1, B:238:0x03ee, B:246:0x0423, B:247:0x0441, B:250:0x0445, B:252:0x0449, B:254:0x044d, B:257:0x0453, B:261:0x045b, B:267:0x046b, B:269:0x047a, B:271:0x0485, B:272:0x048d, B:273:0x0490, B:285:0x04bc, B:287:0x04c7, B:291:0x04d4, B:294:0x04e4, B:295:0x0504, B:280:0x04a0, B:282:0x04aa, B:284:0x04b9, B:283:0x04af, B:298:0x0509, B:300:0x0513, B:302:0x051b, B:303:0x051e, B:305:0x0529, B:306:0x052d, B:308:0x0538, B:311:0x053f, B:314:0x054c, B:315:0x0551, B:318:0x0556, B:320:0x055b, B:324:0x0566, B:326:0x056e, B:328:0x0583, B:332:0x05a2, B:334:0x05a8, B:337:0x05ae, B:339:0x05b4, B:341:0x05bc, B:344:0x05cd, B:347:0x05d5, B:349:0x05d9, B:350:0x05e0, B:352:0x05e5, B:353:0x05e8, B:355:0x05f0, B:358:0x05fa, B:361:0x0604, B:362:0x0609, B:363:0x060e, B:364:0x0628, B:329:0x058e, B:330:0x0595, B:365:0x0629, B:367:0x063b, B:370:0x0642, B:373:0x064f, B:374:0x066f, B:39:0x00bd, B:40:0x00db, B:43:0x00e0, B:45:0x00eb, B:47:0x00ef, B:49:0x00f3, B:52:0x00f9, B:59:0x0108, B:61:0x0110, B:64:0x0120, B:65:0x0138, B:66:0x0139, B:67:0x013e, B:78:0x0153, B:79:0x0159, B:81:0x0160, B:83:0x0169, B:90:0x017b, B:93:0x0183, B:94:0x019b, B:88:0x0176, B:82:0x0165, B:95:0x019c, B:96:0x01b4, B:102:0x01be, B:104:0x01c6, B:107:0x01d7, B:108:0x01f7, B:109:0x01f8, B:110:0x01fd, B:111:0x01fe, B:113:0x0208, B:375:0x0670, B:376:0x0677, B:377:0x0678, B:378:0x067d, B:379:0x067e, B:380:0x0683), top: B:384:0x0072, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0494  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x04c7 A[Catch: all -> 0x0684, TryCatch #0 {all -> 0x0684, blocks: (B:24:0x0072, B:26:0x0076, B:29:0x0080, B:32:0x0093, B:36:0x00ab, B:115:0x0212, B:116:0x0218, B:118:0x0223, B:120:0x022b, B:124:0x0240, B:126:0x024e, B:141:0x027e, B:142:0x0284, B:144:0x0291, B:145:0x0294, B:147:0x029e, B:152:0x02ac, B:153:0x02b2, B:155:0x02ba, B:156:0x02bf, B:158:0x02c7, B:159:0x02d1, B:163:0x02da, B:164:0x02e1, B:165:0x02e2, B:168:0x02ec, B:170:0x02f0, B:172:0x02f6, B:173:0x02f9, B:175:0x02ff, B:178:0x0310, B:184:0x032a, B:188:0x0337, B:185:0x032f, B:187:0x0333, B:128:0x0255, B:130:0x025b, B:135:0x0268, B:138:0x026e, B:195:0x0349, B:197:0x034f, B:199:0x0357, B:201:0x0361, B:203:0x0372, B:205:0x037d, B:207:0x0385, B:209:0x0389, B:211:0x038f, B:214:0x0394, B:216:0x0398, B:239:0x03f7, B:241:0x03ff, B:244:0x0408, B:245:0x0422, B:218:0x039d, B:220:0x03a5, B:223:0x03ab, B:224:0x03b7, B:227:0x03c0, B:230:0x03c6, B:233:0x03cb, B:234:0x03d7, B:236:0x03e1, B:238:0x03ee, B:246:0x0423, B:247:0x0441, B:250:0x0445, B:252:0x0449, B:254:0x044d, B:257:0x0453, B:261:0x045b, B:267:0x046b, B:269:0x047a, B:271:0x0485, B:272:0x048d, B:273:0x0490, B:285:0x04bc, B:287:0x04c7, B:291:0x04d4, B:294:0x04e4, B:295:0x0504, B:280:0x04a0, B:282:0x04aa, B:284:0x04b9, B:283:0x04af, B:298:0x0509, B:300:0x0513, B:302:0x051b, B:303:0x051e, B:305:0x0529, B:306:0x052d, B:308:0x0538, B:311:0x053f, B:314:0x054c, B:315:0x0551, B:318:0x0556, B:320:0x055b, B:324:0x0566, B:326:0x056e, B:328:0x0583, B:332:0x05a2, B:334:0x05a8, B:337:0x05ae, B:339:0x05b4, B:341:0x05bc, B:344:0x05cd, B:347:0x05d5, B:349:0x05d9, B:350:0x05e0, B:352:0x05e5, B:353:0x05e8, B:355:0x05f0, B:358:0x05fa, B:361:0x0604, B:362:0x0609, B:363:0x060e, B:364:0x0628, B:329:0x058e, B:330:0x0595, B:365:0x0629, B:367:0x063b, B:370:0x0642, B:373:0x064f, B:374:0x066f, B:39:0x00bd, B:40:0x00db, B:43:0x00e0, B:45:0x00eb, B:47:0x00ef, B:49:0x00f3, B:52:0x00f9, B:59:0x0108, B:61:0x0110, B:64:0x0120, B:65:0x0138, B:66:0x0139, B:67:0x013e, B:78:0x0153, B:79:0x0159, B:81:0x0160, B:83:0x0169, B:90:0x017b, B:93:0x0183, B:94:0x019b, B:88:0x0176, B:82:0x0165, B:95:0x019c, B:96:0x01b4, B:102:0x01be, B:104:0x01c6, B:107:0x01d7, B:108:0x01f7, B:109:0x01f8, B:110:0x01fd, B:111:0x01fe, B:113:0x0208, B:375:0x0670, B:376:0x0677, B:377:0x0678, B:378:0x067d, B:379:0x067e, B:380:0x0683), top: B:384:0x0072, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:342:0x05c9  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x05cd A[Catch: all -> 0x0684, TryCatch #0 {all -> 0x0684, blocks: (B:24:0x0072, B:26:0x0076, B:29:0x0080, B:32:0x0093, B:36:0x00ab, B:115:0x0212, B:116:0x0218, B:118:0x0223, B:120:0x022b, B:124:0x0240, B:126:0x024e, B:141:0x027e, B:142:0x0284, B:144:0x0291, B:145:0x0294, B:147:0x029e, B:152:0x02ac, B:153:0x02b2, B:155:0x02ba, B:156:0x02bf, B:158:0x02c7, B:159:0x02d1, B:163:0x02da, B:164:0x02e1, B:165:0x02e2, B:168:0x02ec, B:170:0x02f0, B:172:0x02f6, B:173:0x02f9, B:175:0x02ff, B:178:0x0310, B:184:0x032a, B:188:0x0337, B:185:0x032f, B:187:0x0333, B:128:0x0255, B:130:0x025b, B:135:0x0268, B:138:0x026e, B:195:0x0349, B:197:0x034f, B:199:0x0357, B:201:0x0361, B:203:0x0372, B:205:0x037d, B:207:0x0385, B:209:0x0389, B:211:0x038f, B:214:0x0394, B:216:0x0398, B:239:0x03f7, B:241:0x03ff, B:244:0x0408, B:245:0x0422, B:218:0x039d, B:220:0x03a5, B:223:0x03ab, B:224:0x03b7, B:227:0x03c0, B:230:0x03c6, B:233:0x03cb, B:234:0x03d7, B:236:0x03e1, B:238:0x03ee, B:246:0x0423, B:247:0x0441, B:250:0x0445, B:252:0x0449, B:254:0x044d, B:257:0x0453, B:261:0x045b, B:267:0x046b, B:269:0x047a, B:271:0x0485, B:272:0x048d, B:273:0x0490, B:285:0x04bc, B:287:0x04c7, B:291:0x04d4, B:294:0x04e4, B:295:0x0504, B:280:0x04a0, B:282:0x04aa, B:284:0x04b9, B:283:0x04af, B:298:0x0509, B:300:0x0513, B:302:0x051b, B:303:0x051e, B:305:0x0529, B:306:0x052d, B:308:0x0538, B:311:0x053f, B:314:0x054c, B:315:0x0551, B:318:0x0556, B:320:0x055b, B:324:0x0566, B:326:0x056e, B:328:0x0583, B:332:0x05a2, B:334:0x05a8, B:337:0x05ae, B:339:0x05b4, B:341:0x05bc, B:344:0x05cd, B:347:0x05d5, B:349:0x05d9, B:350:0x05e0, B:352:0x05e5, B:353:0x05e8, B:355:0x05f0, B:358:0x05fa, B:361:0x0604, B:362:0x0609, B:363:0x060e, B:364:0x0628, B:329:0x058e, B:330:0x0595, B:365:0x0629, B:367:0x063b, B:370:0x0642, B:373:0x064f, B:374:0x066f, B:39:0x00bd, B:40:0x00db, B:43:0x00e0, B:45:0x00eb, B:47:0x00ef, B:49:0x00f3, B:52:0x00f9, B:59:0x0108, B:61:0x0110, B:64:0x0120, B:65:0x0138, B:66:0x0139, B:67:0x013e, B:78:0x0153, B:79:0x0159, B:81:0x0160, B:83:0x0169, B:90:0x017b, B:93:0x0183, B:94:0x019b, B:88:0x0176, B:82:0x0165, B:95:0x019c, B:96:0x01b4, B:102:0x01be, B:104:0x01c6, B:107:0x01d7, B:108:0x01f7, B:109:0x01f8, B:110:0x01fd, B:111:0x01fe, B:113:0x0208, B:375:0x0670, B:376:0x0677, B:377:0x0678, B:378:0x067d, B:379:0x067e, B:380:0x0683), top: B:384:0x0072, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:349:0x05d9 A[Catch: all -> 0x0684, TryCatch #0 {all -> 0x0684, blocks: (B:24:0x0072, B:26:0x0076, B:29:0x0080, B:32:0x0093, B:36:0x00ab, B:115:0x0212, B:116:0x0218, B:118:0x0223, B:120:0x022b, B:124:0x0240, B:126:0x024e, B:141:0x027e, B:142:0x0284, B:144:0x0291, B:145:0x0294, B:147:0x029e, B:152:0x02ac, B:153:0x02b2, B:155:0x02ba, B:156:0x02bf, B:158:0x02c7, B:159:0x02d1, B:163:0x02da, B:164:0x02e1, B:165:0x02e2, B:168:0x02ec, B:170:0x02f0, B:172:0x02f6, B:173:0x02f9, B:175:0x02ff, B:178:0x0310, B:184:0x032a, B:188:0x0337, B:185:0x032f, B:187:0x0333, B:128:0x0255, B:130:0x025b, B:135:0x0268, B:138:0x026e, B:195:0x0349, B:197:0x034f, B:199:0x0357, B:201:0x0361, B:203:0x0372, B:205:0x037d, B:207:0x0385, B:209:0x0389, B:211:0x038f, B:214:0x0394, B:216:0x0398, B:239:0x03f7, B:241:0x03ff, B:244:0x0408, B:245:0x0422, B:218:0x039d, B:220:0x03a5, B:223:0x03ab, B:224:0x03b7, B:227:0x03c0, B:230:0x03c6, B:233:0x03cb, B:234:0x03d7, B:236:0x03e1, B:238:0x03ee, B:246:0x0423, B:247:0x0441, B:250:0x0445, B:252:0x0449, B:254:0x044d, B:257:0x0453, B:261:0x045b, B:267:0x046b, B:269:0x047a, B:271:0x0485, B:272:0x048d, B:273:0x0490, B:285:0x04bc, B:287:0x04c7, B:291:0x04d4, B:294:0x04e4, B:295:0x0504, B:280:0x04a0, B:282:0x04aa, B:284:0x04b9, B:283:0x04af, B:298:0x0509, B:300:0x0513, B:302:0x051b, B:303:0x051e, B:305:0x0529, B:306:0x052d, B:308:0x0538, B:311:0x053f, B:314:0x054c, B:315:0x0551, B:318:0x0556, B:320:0x055b, B:324:0x0566, B:326:0x056e, B:328:0x0583, B:332:0x05a2, B:334:0x05a8, B:337:0x05ae, B:339:0x05b4, B:341:0x05bc, B:344:0x05cd, B:347:0x05d5, B:349:0x05d9, B:350:0x05e0, B:352:0x05e5, B:353:0x05e8, B:355:0x05f0, B:358:0x05fa, B:361:0x0604, B:362:0x0609, B:363:0x060e, B:364:0x0628, B:329:0x058e, B:330:0x0595, B:365:0x0629, B:367:0x063b, B:370:0x0642, B:373:0x064f, B:374:0x066f, B:39:0x00bd, B:40:0x00db, B:43:0x00e0, B:45:0x00eb, B:47:0x00ef, B:49:0x00f3, B:52:0x00f9, B:59:0x0108, B:61:0x0110, B:64:0x0120, B:65:0x0138, B:66:0x0139, B:67:0x013e, B:78:0x0153, B:79:0x0159, B:81:0x0160, B:83:0x0169, B:90:0x017b, B:93:0x0183, B:94:0x019b, B:88:0x0176, B:82:0x0165, B:95:0x019c, B:96:0x01b4, B:102:0x01be, B:104:0x01c6, B:107:0x01d7, B:108:0x01f7, B:109:0x01f8, B:110:0x01fd, B:111:0x01fe, B:113:0x0208, B:375:0x0670, B:376:0x0677, B:377:0x0678, B:378:0x067d, B:379:0x067e, B:380:0x0683), top: B:384:0x0072, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:352:0x05e5 A[Catch: all -> 0x0684, TryCatch #0 {all -> 0x0684, blocks: (B:24:0x0072, B:26:0x0076, B:29:0x0080, B:32:0x0093, B:36:0x00ab, B:115:0x0212, B:116:0x0218, B:118:0x0223, B:120:0x022b, B:124:0x0240, B:126:0x024e, B:141:0x027e, B:142:0x0284, B:144:0x0291, B:145:0x0294, B:147:0x029e, B:152:0x02ac, B:153:0x02b2, B:155:0x02ba, B:156:0x02bf, B:158:0x02c7, B:159:0x02d1, B:163:0x02da, B:164:0x02e1, B:165:0x02e2, B:168:0x02ec, B:170:0x02f0, B:172:0x02f6, B:173:0x02f9, B:175:0x02ff, B:178:0x0310, B:184:0x032a, B:188:0x0337, B:185:0x032f, B:187:0x0333, B:128:0x0255, B:130:0x025b, B:135:0x0268, B:138:0x026e, B:195:0x0349, B:197:0x034f, B:199:0x0357, B:201:0x0361, B:203:0x0372, B:205:0x037d, B:207:0x0385, B:209:0x0389, B:211:0x038f, B:214:0x0394, B:216:0x0398, B:239:0x03f7, B:241:0x03ff, B:244:0x0408, B:245:0x0422, B:218:0x039d, B:220:0x03a5, B:223:0x03ab, B:224:0x03b7, B:227:0x03c0, B:230:0x03c6, B:233:0x03cb, B:234:0x03d7, B:236:0x03e1, B:238:0x03ee, B:246:0x0423, B:247:0x0441, B:250:0x0445, B:252:0x0449, B:254:0x044d, B:257:0x0453, B:261:0x045b, B:267:0x046b, B:269:0x047a, B:271:0x0485, B:272:0x048d, B:273:0x0490, B:285:0x04bc, B:287:0x04c7, B:291:0x04d4, B:294:0x04e4, B:295:0x0504, B:280:0x04a0, B:282:0x04aa, B:284:0x04b9, B:283:0x04af, B:298:0x0509, B:300:0x0513, B:302:0x051b, B:303:0x051e, B:305:0x0529, B:306:0x052d, B:308:0x0538, B:311:0x053f, B:314:0x054c, B:315:0x0551, B:318:0x0556, B:320:0x055b, B:324:0x0566, B:326:0x056e, B:328:0x0583, B:332:0x05a2, B:334:0x05a8, B:337:0x05ae, B:339:0x05b4, B:341:0x05bc, B:344:0x05cd, B:347:0x05d5, B:349:0x05d9, B:350:0x05e0, B:352:0x05e5, B:353:0x05e8, B:355:0x05f0, B:358:0x05fa, B:361:0x0604, B:362:0x0609, B:363:0x060e, B:364:0x0628, B:329:0x058e, B:330:0x0595, B:365:0x0629, B:367:0x063b, B:370:0x0642, B:373:0x064f, B:374:0x066f, B:39:0x00bd, B:40:0x00db, B:43:0x00e0, B:45:0x00eb, B:47:0x00ef, B:49:0x00f3, B:52:0x00f9, B:59:0x0108, B:61:0x0110, B:64:0x0120, B:65:0x0138, B:66:0x0139, B:67:0x013e, B:78:0x0153, B:79:0x0159, B:81:0x0160, B:83:0x0169, B:90:0x017b, B:93:0x0183, B:94:0x019b, B:88:0x0176, B:82:0x0165, B:95:0x019c, B:96:0x01b4, B:102:0x01be, B:104:0x01c6, B:107:0x01d7, B:108:0x01f7, B:109:0x01f8, B:110:0x01fd, B:111:0x01fe, B:113:0x0208, B:375:0x0670, B:376:0x0677, B:377:0x0678, B:378:0x067d, B:379:0x067e, B:380:0x0683), top: B:384:0x0072, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:358:0x05fa A[Catch: all -> 0x0684, TRY_ENTER, TryCatch #0 {all -> 0x0684, blocks: (B:24:0x0072, B:26:0x0076, B:29:0x0080, B:32:0x0093, B:36:0x00ab, B:115:0x0212, B:116:0x0218, B:118:0x0223, B:120:0x022b, B:124:0x0240, B:126:0x024e, B:141:0x027e, B:142:0x0284, B:144:0x0291, B:145:0x0294, B:147:0x029e, B:152:0x02ac, B:153:0x02b2, B:155:0x02ba, B:156:0x02bf, B:158:0x02c7, B:159:0x02d1, B:163:0x02da, B:164:0x02e1, B:165:0x02e2, B:168:0x02ec, B:170:0x02f0, B:172:0x02f6, B:173:0x02f9, B:175:0x02ff, B:178:0x0310, B:184:0x032a, B:188:0x0337, B:185:0x032f, B:187:0x0333, B:128:0x0255, B:130:0x025b, B:135:0x0268, B:138:0x026e, B:195:0x0349, B:197:0x034f, B:199:0x0357, B:201:0x0361, B:203:0x0372, B:205:0x037d, B:207:0x0385, B:209:0x0389, B:211:0x038f, B:214:0x0394, B:216:0x0398, B:239:0x03f7, B:241:0x03ff, B:244:0x0408, B:245:0x0422, B:218:0x039d, B:220:0x03a5, B:223:0x03ab, B:224:0x03b7, B:227:0x03c0, B:230:0x03c6, B:233:0x03cb, B:234:0x03d7, B:236:0x03e1, B:238:0x03ee, B:246:0x0423, B:247:0x0441, B:250:0x0445, B:252:0x0449, B:254:0x044d, B:257:0x0453, B:261:0x045b, B:267:0x046b, B:269:0x047a, B:271:0x0485, B:272:0x048d, B:273:0x0490, B:285:0x04bc, B:287:0x04c7, B:291:0x04d4, B:294:0x04e4, B:295:0x0504, B:280:0x04a0, B:282:0x04aa, B:284:0x04b9, B:283:0x04af, B:298:0x0509, B:300:0x0513, B:302:0x051b, B:303:0x051e, B:305:0x0529, B:306:0x052d, B:308:0x0538, B:311:0x053f, B:314:0x054c, B:315:0x0551, B:318:0x0556, B:320:0x055b, B:324:0x0566, B:326:0x056e, B:328:0x0583, B:332:0x05a2, B:334:0x05a8, B:337:0x05ae, B:339:0x05b4, B:341:0x05bc, B:344:0x05cd, B:347:0x05d5, B:349:0x05d9, B:350:0x05e0, B:352:0x05e5, B:353:0x05e8, B:355:0x05f0, B:358:0x05fa, B:361:0x0604, B:362:0x0609, B:363:0x060e, B:364:0x0628, B:329:0x058e, B:330:0x0595, B:365:0x0629, B:367:0x063b, B:370:0x0642, B:373:0x064f, B:374:0x066f, B:39:0x00bd, B:40:0x00db, B:43:0x00e0, B:45:0x00eb, B:47:0x00ef, B:49:0x00f3, B:52:0x00f9, B:59:0x0108, B:61:0x0110, B:64:0x0120, B:65:0x0138, B:66:0x0139, B:67:0x013e, B:78:0x0153, B:79:0x0159, B:81:0x0160, B:83:0x0169, B:90:0x017b, B:93:0x0183, B:94:0x019b, B:88:0x0176, B:82:0x0165, B:95:0x019c, B:96:0x01b4, B:102:0x01be, B:104:0x01c6, B:107:0x01d7, B:108:0x01f7, B:109:0x01f8, B:110:0x01fd, B:111:0x01fe, B:113:0x0208, B:375:0x0670, B:376:0x0677, B:377:0x0678, B:378:0x067d, B:379:0x067e, B:380:0x0683), top: B:384:0x0072, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:405:0x04d0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:408:0x05f0 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object k0(java.util.Map r18, java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 1673
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.a.k0(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public void l0(Object obj) {
        Object objB;
        Class<?> cls = obj.getClass();
        t tVarO = this.f4858g.o(cls);
        o oVar = tVarO instanceof o ? (o) tVarO : null;
        if (this.f4861j.q() != 12 && this.f4861j.q() != 16) {
            throw new JSONException("syntax error, expect {, actual " + this.f4861j.c0());
        }
        while (true) {
            String strW = this.f4861j.w(this.f4857f);
            if (strW == null) {
                if (this.f4861j.q() == 13) {
                    this.f4861j.V(16);
                    return;
                } else if (this.f4861j.q() != 16 || !this.f4861j.M(Feature.AllowArbitraryCommas)) {
                }
            }
            l lVarK = oVar != null ? oVar.k(strW) : null;
            if (lVarK != null) {
                l2.c cVar = lVarK.f5036a;
                Class<?> cls2 = cVar.f6173h;
                Type type = cVar.f6174i;
                if (cls2 == Integer.TYPE) {
                    this.f4861j.m0(2);
                    objB = d0.f5206a.b(this, type, null);
                } else if (cls2 == String.class) {
                    this.f4861j.m0(4);
                    objB = e1.f(this);
                } else if (cls2 == Long.TYPE) {
                    this.f4861j.m0(2);
                    objB = o0.f5295a.b(this, type, null);
                } else {
                    t tVarN = this.f4858g.n(cls2, type);
                    this.f4861j.m0(tVarN.e());
                    objB = tVarN.b(this, type, null);
                }
                lVarK.g(obj, objB);
                if (this.f4861j.q() != 16 && this.f4861j.q() == 13) {
                    this.f4861j.V(16);
                    return;
                }
            } else {
                if (!this.f4861j.M(Feature.IgnoreNotMatch)) {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + strW);
                }
                this.f4861j.Y();
                N();
                if (this.f4861j.q() == 13) {
                    this.f4861j.E();
                    return;
                }
            }
        }
    }

    public void m(Collection collection) {
        if (this.f4866o == 1) {
            if (!(collection instanceof List)) {
                C0039a c0039aB = B();
                c0039aB.f4874c = new y(collection);
                c0039aB.f4875d = this.f4862k;
                u0(0);
                return;
            }
            int size = collection.size() - 1;
            C0039a c0039aB2 = B();
            c0039aB2.f4874c = new y(this, (List) collection, size);
            c0039aB2.f4875d = this.f4862k;
            u0(0);
        }
    }

    public void m0() {
        if (this.f4861j.M(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.f4862k = this.f4862k.f4902b;
        int i8 = this.f4864m;
        if (i8 <= 0) {
            return;
        }
        int i9 = i8 - 1;
        this.f4864m = i9;
        this.f4863l[i9] = null;
    }

    public void n(Map map, Object obj) {
        if (this.f4866o == 1) {
            y yVar = new y(map, obj);
            C0039a c0039aB = B();
            c0039aB.f4874c = yVar;
            c0039aB.f4875d = this.f4862k;
            u0(0);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0022, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object n0(java.lang.String r5) {
        /*
            r4 = this;
            f2.g[] r0 = r4.f4863l
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            r0 = 0
        L7:
            f2.g[] r2 = r4.f4863l
            int r3 = r2.length
            if (r0 >= r3) goto L22
            int r3 = r4.f4864m
            if (r0 >= r3) goto L22
            r2 = r2[r0]
            java.lang.String r3 = r2.toString()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L1f
            java.lang.Object r5 = r2.f4901a
            return r5
        L1f:
            int r0 = r0 + 1
            goto L7
        L22:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.a.n0(java.lang.String):java.lang.Object");
    }

    public h o() {
        return this.f4858g;
    }

    public g o0(g gVar, Object obj, Object obj2) {
        if (this.f4861j.M(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        g gVar2 = new g(gVar, obj, obj2);
        this.f4862k = gVar2;
        i(gVar2);
        return this.f4862k;
    }

    public g q() {
        return this.f4862k;
    }

    public g q0(Object obj, Object obj2) {
        if (this.f4861j.M(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return o0(this.f4862k, obj, obj2);
    }

    public String r() {
        return this.f4859h;
    }

    public void r0(g gVar) {
        if (this.f4861j.M(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.f4862k = gVar;
    }

    public void s0(String str) {
        this.f4859h = str;
        this.f4860i = null;
    }

    public DateFormat t() {
        if (this.f4860i == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.f4859h, this.f4861j.h0());
            this.f4860i = simpleDateFormat;
            simpleDateFormat.setTimeZone(this.f4861j.o0());
        }
        return this.f4860i;
    }

    public void t0(m mVar) {
        this.f4869r = mVar;
    }

    public void u0(int i8) {
        this.f4866o = i8;
    }

    public List<j> v() {
        if (this.f4868q == null) {
            this.f4868q = new ArrayList(2);
        }
        return this.f4868q;
    }

    public List<k> w() {
        if (this.f4867p == null) {
            this.f4867p = new ArrayList(2);
        }
        return this.f4867p;
    }

    public m x() {
        return this.f4869r;
    }

    public a(String str, h hVar) {
        this(str, new e(str, c2.a.DEFAULT_PARSER_FEATURE), hVar);
    }

    public a(String str, h hVar, int i8) {
        this(str, new e(str, i8), hVar);
    }

    public a(char[] cArr, int i8, h hVar, int i9) {
        this(cArr, new e(cArr, i8, i9), hVar);
    }

    public a(Object obj, b bVar, h hVar) {
        this.f4859h = c2.a.DEFFAULT_DATE_FORMAT;
        this.f4864m = 0;
        this.f4866o = 0;
        this.f4867p = null;
        this.f4868q = null;
        this.f4869r = null;
        this.f4870s = 0;
        this.f4871t = null;
        this.f4861j = bVar;
        this.f4856e = obj;
        this.f4858g = hVar;
        this.f4857f = hVar.f4922p;
        char cF = bVar.F();
        if (cF == '{') {
            bVar.next();
            ((c) bVar).f4879g = 12;
        } else if (cF == '[') {
            bVar.next();
            ((c) bVar).f4879g = 14;
        } else {
            bVar.E();
        }
    }
}
