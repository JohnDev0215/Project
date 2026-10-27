package b.a.a.j;

import b.a.a.j.j.j;
import b.a.a.j.j.k;
import b.a.a.j.j.l;
import b.a.a.j.j.m;
import b.a.a.j.j.o;
import b.a.a.j.j.t;
import b.a.a.j.j.y;
import b.a.a.k.d0;
import b.a.a.k.e1;
import b.a.a.k.o0;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.Feature;
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

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Set<Class<?>> f229a = new HashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f230b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i f231c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public h f232d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f233e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public DateFormat f234f;
    public final b g;
    public g h;
    public g[] i;
    public int j;
    public List<C0016a> k;
    public int l;
    public List<k> m;
    public List<j> n;
    public m o;
    public int p;
    public String[] q;

    /* JADX INFO: renamed from: b.a.a.j.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DefaultJSONParser.java */
    public static class C0016a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final g f235a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f236b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public l f237c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public g f238d;

        public C0016a(g gVar, String str) {
            this.f235a = gVar;
            this.f236b = str;
        }
    }

    static {
        Class<?>[] clsArr = {Boolean.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class, String.class};
        for (int i = 0; i < 17; i++) {
            f229a.add(clsArr[i]);
        }
    }

    public a(String str) {
        this(str, h.s(), b.a.a.a.DEFAULT_PARSER_FEATURE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x0236, code lost:
    
        return r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object A(b.a.a.j.j.w r11, java.lang.Object r12) {
        /*
            Method dump skipped, instruction units count: 613
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.a.A(b.a.a.j.j.w, java.lang.Object):java.lang.Object");
    }

    public Object B(Object obj) {
        b bVar = this.g;
        int iJ = bVar.j();
        if (iJ == 2) {
            Number numberF = bVar.f();
            bVar.u();
            return numberF;
        }
        if (iJ == 3) {
            Number numberN = bVar.N(bVar.y(Feature.UseBigDecimal));
            bVar.u();
            return numberN;
        }
        if (iJ == 4) {
            String strV = bVar.V();
            bVar.F(16);
            if (bVar.y(Feature.AllowISO8601DateFormat)) {
                e eVar = new e(strV);
                try {
                    if (eVar.Q0()) {
                        return eVar.d0().getTime();
                    }
                } finally {
                    eVar.close();
                }
            }
            return strV;
        }
        if (iJ == 12) {
            return S(new JSONObject(bVar.y(Feature.OrderedField)), obj);
        }
        if (iJ == 14) {
            JSONArray jSONArray = new JSONArray();
            H(jSONArray, obj);
            return bVar.y(Feature.UseObjectArray) ? jSONArray.toArray() : jSONArray;
        }
        if (iJ == 18) {
            if ("NaN".equals(bVar.V())) {
                bVar.u();
                return null;
            }
            throw new JSONException("syntax error, " + bVar.c());
        }
        if (iJ == 26) {
            byte[] bArrO = bVar.O();
            bVar.u();
            return bArrO;
        }
        switch (iJ) {
            case 6:
                bVar.u();
                return Boolean.TRUE;
            case 7:
                bVar.u();
                return Boolean.FALSE;
            case 8:
                bVar.u();
                return null;
            case 9:
                bVar.F(18);
                if (bVar.j() != 18) {
                    throw new JSONException("syntax error");
                }
                bVar.F(10);
                a(10);
                long jLongValue = bVar.f().longValue();
                a(2);
                a(11);
                return new Date(jLongValue);
            default:
                switch (iJ) {
                    case 20:
                        if (bVar.i()) {
                            return null;
                        }
                        throw new JSONException("unterminated json string, " + bVar.c());
                    case 21:
                        bVar.u();
                        HashSet hashSet = new HashSet();
                        H(hashSet, obj);
                        return hashSet;
                    case 22:
                        bVar.u();
                        TreeSet treeSet = new TreeSet();
                        H(treeSet, obj);
                        return treeSet;
                    case 23:
                        bVar.u();
                        return null;
                    default:
                        throw new JSONException("syntax error, " + bVar.c());
                }
        }
    }

    public void C(Class<?> cls, Collection collection) {
        E(cls, collection);
    }

    public void E(Type type, Collection collection) {
        F(type, collection, null);
    }

    public void F(Type type, Collection collection, Object obj) {
        t tVarO;
        int iJ = this.g.j();
        if (iJ == 21 || iJ == 22) {
            this.g.u();
            iJ = this.g.j();
        }
        if (iJ != 14) {
            throw new JSONException("expect '[', but " + f.a(iJ) + ", " + this.g.c());
        }
        if (Integer.TYPE == type) {
            tVarO = d0.f353a;
            this.g.F(2);
        } else if (String.class == type) {
            tVarO = e1.f362a;
            this.g.F(4);
        } else {
            tVarO = this.f232d.o(type);
            this.g.F(tVarO.e());
        }
        g gVar = this.h;
        X(collection, obj);
        int i = 0;
        while (true) {
            try {
                if (this.g.y(Feature.AllowArbitraryCommas)) {
                    while (this.g.j() == 16) {
                        this.g.u();
                    }
                }
                if (this.g.j() == 15) {
                    Y(gVar);
                    this.g.F(16);
                    return;
                }
                Object objB = null;
                if (Integer.TYPE == type) {
                    collection.add(d0.f353a.b(this, null, null));
                } else if (String.class == type) {
                    if (this.g.j() == 4) {
                        objB = this.g.V();
                        this.g.F(16);
                    } else {
                        Object objZ = z();
                        if (objZ != null) {
                            objB = objZ.toString();
                        }
                    }
                    collection.add(objB);
                } else {
                    if (this.g.j() == 8) {
                        this.g.u();
                    } else {
                        objB = tVarO.b(this, type, Integer.valueOf(i));
                    }
                    collection.add(objB);
                    g(collection);
                }
                if (this.g.j() == 16) {
                    this.g.F(tVarO.e());
                }
                i++;
            } catch (Throwable th) {
                Y(gVar);
                throw th;
            }
        }
    }

    public final void G(Collection collection) {
        H(collection, null);
    }

    public final void H(Collection collection, Object obj) {
        b bVar = this.g;
        if (bVar.j() == 21 || bVar.j() == 22) {
            bVar.u();
        }
        if (bVar.j() != 14) {
            throw new JSONException("syntax error, expect [, actual " + f.a(bVar.j()) + ", pos " + bVar.a() + ", fieldName " + obj);
        }
        bVar.F(4);
        g gVar = this.h;
        if (gVar != null && gVar.f248d > 512) {
            throw new JSONException("array level > 512");
        }
        X(collection, obj);
        int i = 0;
        while (true) {
            try {
                if (bVar.y(Feature.AllowArbitraryCommas)) {
                    while (bVar.j() == 16) {
                        bVar.u();
                    }
                }
                int iJ = bVar.j();
                Object objN = null;
                objN = null;
                if (iJ == 2) {
                    Number numberF = bVar.f();
                    bVar.F(16);
                    objN = numberF;
                } else if (iJ == 3) {
                    objN = bVar.y(Feature.UseBigDecimal) ? bVar.N(true) : bVar.N(false);
                    bVar.F(16);
                } else if (iJ == 4) {
                    String strV = bVar.V();
                    bVar.F(16);
                    objN = strV;
                    if (bVar.y(Feature.AllowISO8601DateFormat)) {
                        e eVar = new e(strV);
                        Object time = strV;
                        if (eVar.Q0()) {
                            time = eVar.d0().getTime();
                        }
                        eVar.close();
                        objN = time;
                    }
                } else if (iJ == 6) {
                    Boolean bool = Boolean.TRUE;
                    bVar.F(16);
                    objN = bool;
                } else if (iJ == 7) {
                    Boolean bool2 = Boolean.FALSE;
                    bVar.F(16);
                    objN = bool2;
                } else if (iJ == 8) {
                    bVar.F(4);
                } else if (iJ == 12) {
                    objN = S(new JSONObject(bVar.y(Feature.OrderedField)), Integer.valueOf(i));
                } else {
                    if (iJ == 20) {
                        throw new JSONException("unclosed jsonArray");
                    }
                    if (iJ == 23) {
                        bVar.F(4);
                    } else if (iJ == 14) {
                        JSONArray jSONArray = new JSONArray();
                        H(jSONArray, Integer.valueOf(i));
                        objN = jSONArray;
                        if (bVar.y(Feature.UseObjectArray)) {
                            objN = jSONArray.toArray();
                        }
                    } else {
                        if (iJ == 15) {
                            bVar.F(16);
                            return;
                        }
                        objN = z();
                    }
                }
                collection.add(objN);
                g(collection);
                if (bVar.j() == 16) {
                    bVar.F(4);
                }
                i++;
            } finally {
                Y(gVar);
            }
        }
    }

    public Object[] J(Type[] typeArr) {
        Object objH;
        Class<?> componentType;
        boolean zIsArray;
        Class cls;
        int i = 8;
        if (this.g.j() == 8) {
            this.g.F(16);
            return null;
        }
        int i2 = 14;
        if (this.g.j() != 14) {
            throw new JSONException("syntax error : " + this.g.M());
        }
        Object[] objArr = new Object[typeArr.length];
        if (typeArr.length == 0) {
            this.g.F(15);
            if (this.g.j() != 15) {
                throw new JSONException("syntax error");
            }
            this.g.F(16);
            return new Object[0];
        }
        this.g.F(2);
        int i3 = 0;
        while (i3 < typeArr.length) {
            if (this.g.j() == i) {
                this.g.F(16);
                objH = null;
            } else {
                Type type = typeArr[i3];
                if (type == Integer.TYPE || type == Integer.class) {
                    if (this.g.j() == 2) {
                        objH = Integer.valueOf(this.g.z());
                        this.g.F(16);
                    } else {
                        objH = b.a.a.n.l.h(z(), type, this.f232d);
                    }
                } else if (type != String.class) {
                    if (i3 == typeArr.length - 1 && (type instanceof Class) && (((cls = (Class) type) != byte[].class && cls != char[].class) || this.g.j() != 4)) {
                        zIsArray = cls.isArray();
                        componentType = cls.getComponentType();
                    } else {
                        componentType = null;
                        zIsArray = false;
                    }
                    if (!zIsArray || this.g.j() == i2) {
                        objH = this.f232d.o(type).b(this, type, Integer.valueOf(i3));
                    } else {
                        ArrayList arrayList = new ArrayList();
                        t tVarO = this.f232d.o(componentType);
                        int iE = tVarO.e();
                        if (this.g.j() != 15) {
                            while (true) {
                                arrayList.add(tVarO.b(this, type, null));
                                if (this.g.j() != 16) {
                                    break;
                                }
                                this.g.F(iE);
                            }
                            if (this.g.j() != 15) {
                                throw new JSONException("syntax error :" + f.a(this.g.j()));
                            }
                        }
                        objH = b.a.a.n.l.h(arrayList, type, this.f232d);
                    }
                } else if (this.g.j() == 4) {
                    objH = this.g.V();
                    this.g.F(16);
                } else {
                    objH = b.a.a.n.l.h(z(), type, this.f232d);
                }
            }
            objArr[i3] = objH;
            if (this.g.j() == 15) {
                break;
            }
            if (this.g.j() != 16) {
                throw new JSONException("syntax error :" + f.a(this.g.j()));
            }
            if (i3 == typeArr.length - 1) {
                this.g.F(15);
            } else {
                this.g.F(2);
            }
            i3++;
            i = 8;
            i2 = 14;
        }
        if (this.g.j() != 15) {
            throw new JSONException("syntax error");
        }
        this.g.F(16);
        return objArr;
    }

    public void L(Object obj, String str) {
        this.g.H();
        List<k> list = this.m;
        Type typeA = null;
        if (list != null) {
            Iterator<k> it = list.iterator();
            while (it.hasNext()) {
                typeA = it.next().a(obj, str);
            }
        }
        Object objZ = typeA == null ? z() : O(typeA);
        if (obj instanceof b.a.a.j.j.i) {
            ((b.a.a.j.j.i) obj).a(str, objZ);
            return;
        }
        List<j> list2 = this.n;
        if (list2 != null) {
            Iterator<j> it2 = list2.iterator();
            while (it2.hasNext()) {
                it2.next().b(obj, str, objZ);
            }
        }
        if (this.l == 1) {
            this.l = 0;
        }
    }

    public JSONObject M() {
        Object objR = R(new JSONObject(this.g.y(Feature.OrderedField)));
        if (objR instanceof JSONObject) {
            return (JSONObject) objR;
        }
        if (objR == null) {
            return null;
        }
        return new JSONObject((Map<String, Object>) objR);
    }

    public <T> T N(Class<T> cls) {
        return (T) Q(cls, null);
    }

    public <T> T O(Type type) {
        return (T) Q(type, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T Q(Type type, Object obj) {
        int iJ = this.g.j();
        if (iJ == 8) {
            this.g.u();
            return null;
        }
        if (iJ == 4) {
            if (type == byte[].class) {
                T t = (T) this.g.O();
                this.g.u();
                return t;
            }
            if (type == char[].class) {
                String strV = this.g.V();
                this.g.u();
                return (T) strV.toCharArray();
            }
        }
        t tVarO = this.f232d.o(type);
        try {
            if (tVarO.getClass() != o.class) {
                return (T) tVarO.b(this, type, obj);
            }
            if (this.g.j() != 12 && this.g.j() != 14) {
                throw new JSONException("syntax error,except start with { or [,but actually start with " + this.g.M());
            }
            return (T) ((o) tVarO).h(this, type, obj, 0);
        } catch (JSONException e2) {
            throw e2;
        } catch (Throwable th) {
            throw new JSONException(th.getMessage(), th);
        }
    }

    public Object R(Map map) {
        return S(map, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:142:0x0284, code lost:
    
        r4.F(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x028f, code lost:
    
        if (r4.j() != 13) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0291, code lost:
    
        r4.F(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x029c, code lost:
    
        if ((r17.f232d.o(r7) instanceof b.a.a.j.j.o) == false) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x029e, code lost:
    
        r0 = b.a.a.n.l.f(r18, r7, r17.f232d);
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
    
        b0(2);
        r3 = r17.h;
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
    
        if ((r3.f247c instanceof java.lang.Integer) != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x02f6, code lost:
    
        U();
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x02fd, code lost:
    
        if (r18.size() <= 0) goto L178;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x02ff, code lost:
    
        r0 = b.a.a.n.l.f(r18, r7, r17.f232d);
        b0(0);
        T(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x030f, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0310, code lost:
    
        r0 = r17.f232d.o(r7);
        r3 = r0.getClass();
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x0320, code lost:
    
        if (b.a.a.j.j.o.class.isAssignableFrom(r3) == false) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0324, code lost:
    
        if (r3 == b.a.a.j.j.o.class) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x0328, code lost:
    
        if (r3 == b.a.a.j.j.b0.class) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x032a, code lost:
    
        b0(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0331, code lost:
    
        if ((r0 instanceof b.a.a.j.j.r) == false) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0333, code lost:
    
        b0(0);
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
    public final java.lang.Object S(java.util.Map r18, java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 1673
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.a.S(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public void T(Object obj) {
        Object objB;
        Class<?> cls = obj.getClass();
        t tVarO = this.f232d.o(cls);
        o oVar = tVarO instanceof o ? (o) tVarO : null;
        if (this.g.j() != 12 && this.g.j() != 16) {
            throw new JSONException("syntax error, expect {, actual " + this.g.M());
        }
        while (true) {
            String strO = this.g.o(this.f231c);
            if (strO == null) {
                if (this.g.j() == 13) {
                    this.g.F(16);
                    return;
                } else if (this.g.j() != 16 || !this.g.y(Feature.AllowArbitraryCommas)) {
                }
            }
            l lVarK = oVar != null ? oVar.k(strO) : null;
            if (lVarK != null) {
                b.a.a.n.c cVar = lVarK.f282a;
                Class<?> cls2 = cVar.f433e;
                Type type = cVar.f434f;
                if (cls2 == Integer.TYPE) {
                    this.g.U(2);
                    objB = d0.f353a.b(this, type, null);
                } else if (cls2 == String.class) {
                    this.g.U(4);
                    objB = e1.f(this);
                } else if (cls2 == Long.TYPE) {
                    this.g.U(2);
                    objB = o0.f394a.b(this, type, null);
                } else {
                    t tVarN = this.f232d.n(cls2, type);
                    this.g.U(tVarN.e());
                    objB = tVarN.b(this, type, null);
                }
                lVarK.g(obj, objB);
                if (this.g.j() != 16 && this.g.j() == 13) {
                    this.g.F(16);
                    return;
                }
            } else {
                if (!this.g.y(Feature.IgnoreNotMatch)) {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + strO);
                }
                this.g.H();
                z();
                if (this.g.j() == 13) {
                    this.g.u();
                    return;
                }
            }
        }
    }

    public void U() {
        if (this.g.y(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.h = this.h.f246b;
        int i = this.j;
        if (i <= 0) {
            return;
        }
        int i2 = i - 1;
        this.j = i2;
        this.i[i2] = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0022, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object V(java.lang.String r5) {
        /*
            r4 = this;
            b.a.a.j.g[] r0 = r4.i
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            r0 = 0
        L7:
            b.a.a.j.g[] r2 = r4.i
            int r3 = r2.length
            if (r0 >= r3) goto L22
            int r3 = r4.j
            if (r0 >= r3) goto L22
            r2 = r2[r0]
            java.lang.String r3 = r2.toString()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L1f
            java.lang.Object r5 = r2.f245a
            return r5
        L1f:
            int r0 = r0 + 1
            goto L7
        L22:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.a.V(java.lang.String):java.lang.Object");
    }

    public g W(g gVar, Object obj, Object obj2) {
        if (this.g.y(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        g gVar2 = new g(gVar, obj, obj2);
        this.h = gVar2;
        e(gVar2);
        return this.h;
    }

    public g X(Object obj, Object obj2) {
        if (this.g.y(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return W(this.h, obj, obj2);
    }

    public void Y(g gVar) {
        if (this.g.y(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.h = gVar;
    }

    public void Z(String str) {
        this.f233e = str;
        this.f234f = null;
    }

    public final void a(int i) {
        b bVar = this.g;
        if (bVar.j() == i) {
            bVar.u();
            return;
        }
        throw new JSONException("syntax error, expect " + f.a(i) + ", actual " + f.a(bVar.j()));
    }

    public void a0(m mVar) {
        this.o = mVar;
    }

    public void b0(int i) {
        this.l = i;
    }

    public void c(String str) {
        b bVar = this.g;
        bVar.H();
        if (bVar.j() != 4) {
            throw new JSONException("type not match error");
        }
        if (!str.equals(bVar.V())) {
            throw new JSONException("type not match error");
        }
        bVar.u();
        if (bVar.j() == 16) {
            bVar.u();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        b bVar = this.g;
        try {
            if (bVar.y(Feature.AutoCloseSource) && bVar.j() != 20) {
                throw new JSONException("not close json text, token : " + f.a(bVar.j()));
            }
        } finally {
            bVar.close();
        }
    }

    public final void e(g gVar) {
        int i = this.j;
        this.j = i + 1;
        g[] gVarArr = this.i;
        if (gVarArr == null) {
            this.i = new g[8];
        } else if (i >= gVarArr.length) {
            g[] gVarArr2 = new g[(gVarArr.length * 3) / 2];
            System.arraycopy(gVarArr, 0, gVarArr2, 0, gVarArr.length);
            this.i = gVarArr2;
        }
        this.i[i] = gVar;
    }

    public void f(C0016a c0016a) {
        if (this.k == null) {
            this.k = new ArrayList(2);
        }
        this.k.add(c0016a);
    }

    public void g(Collection collection) {
        if (this.l == 1) {
            if (!(collection instanceof List)) {
                C0016a c0016aS = s();
                c0016aS.f237c = new y(collection);
                c0016aS.f238d = this.h;
                b0(0);
                return;
            }
            int size = collection.size() - 1;
            C0016a c0016aS2 = s();
            c0016aS2.f237c = new y(this, (List) collection, size);
            c0016aS2.f238d = this.h;
            b0(0);
        }
    }

    public void h(Map map, Object obj) {
        if (this.l == 1) {
            y yVar = new y(map, obj);
            C0016a c0016aS = s();
            c0016aS.f237c = yVar;
            c0016aS.f238d = this.h;
            b0(0);
        }
    }

    public h i() {
        return this.f232d;
    }

    public g j() {
        return this.h;
    }

    public String k() {
        return this.f233e;
    }

    public DateFormat l() {
        if (this.f234f == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.f233e, this.g.R());
            this.f234f = simpleDateFormat;
            simpleDateFormat.setTimeZone(this.g.W());
        }
        return this.f234f;
    }

    public List<j> n() {
        if (this.n == null) {
            this.n = new ArrayList(2);
        }
        return this.n;
    }

    public List<k> o() {
        if (this.m == null) {
            this.m = new ArrayList(2);
        }
        return this.m;
    }

    public m q() {
        return this.o;
    }

    public C0016a s() {
        return this.k.get(r0.size() - 1);
    }

    public b t() {
        return this.g;
    }

    public Object u(String str) {
        for (int i = 0; i < this.j; i++) {
            if (str.equals(this.i[i].toString())) {
                return this.i[i].f245a;
            }
        }
        return null;
    }

    public int v() {
        return this.l;
    }

    public i w() {
        return this.f231c;
    }

    public void x(Object obj) {
        Object objE;
        b.a.a.n.c cVar;
        List<C0016a> list = this.k;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            C0016a c0016a = this.k.get(i);
            String str = c0016a.f236b;
            g gVar = c0016a.f238d;
            Object obj2 = gVar != null ? gVar.f245a : null;
            if (str.startsWith("$")) {
                objE = u(str);
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
                objE = c0016a.f235a.f245a;
            }
            l lVar = c0016a.f237c;
            if (lVar != null) {
                if (objE != null && objE.getClass() == JSONObject.class && (cVar = lVar.f282a) != null && !Map.class.isAssignableFrom(cVar.f433e)) {
                    Object obj3 = this.i[0].f245a;
                    JSONPath jSONPathB2 = JSONPath.b(str);
                    if (jSONPathB2.n()) {
                        objE = jSONPathB2.e(obj3);
                    }
                }
                lVar.g(obj2, objE);
            }
        }
    }

    public boolean y(Feature feature) {
        return this.g.y(feature);
    }

    public Object z() {
        return B(null);
    }

    public a(String str, h hVar) {
        this(str, new e(str, b.a.a.a.DEFAULT_PARSER_FEATURE), hVar);
    }

    public a(String str, h hVar, int i) {
        this(str, new e(str, i), hVar);
    }

    public a(char[] cArr, int i, h hVar, int i2) {
        this(cArr, new e(cArr, i, i2), hVar);
    }

    public a(Object obj, b bVar, h hVar) {
        this.f233e = b.a.a.a.DEFFAULT_DATE_FORMAT;
        this.j = 0;
        this.l = 0;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = 0;
        this.q = null;
        this.g = bVar;
        this.f230b = obj;
        this.f232d = hVar;
        this.f231c = hVar.p;
        char cV = bVar.v();
        if (cV == '{') {
            bVar.next();
            ((c) bVar).f242d = 12;
        } else if (cV == '[') {
            bVar.next();
            ((c) bVar).f242d = 14;
        } else {
            bVar.u();
        }
    }
}
