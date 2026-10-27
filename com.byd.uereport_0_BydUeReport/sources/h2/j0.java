package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: JavaBeanSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class j0 extends c1 implements t0 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final a0[] f5254j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final a0[] f5255k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public z0 f5256l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile transient long[] f5257m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile transient short[] f5258n;

    public j0(z0 z0Var) {
        a0[] a0VarArr;
        boolean z7;
        this.f5256l = z0Var;
        this.f5255k = new a0[z0Var.f5325f.length];
        int i8 = 0;
        while (true) {
            a0VarArr = this.f5255k;
            if (i8 >= a0VarArr.length) {
                break;
            }
            a0VarArr[i8] = new a0(z0Var.f5320a, z0Var.f5325f[i8]);
            i8++;
        }
        l2.c[] cVarArr = z0Var.f5324e;
        if (cVarArr == z0Var.f5325f) {
            this.f5254j = a0VarArr;
        } else {
            this.f5254j = new a0[cVarArr.length];
            int i9 = 0;
            while (true) {
                if (i9 >= this.f5254j.length) {
                    z7 = false;
                    break;
                }
                a0 a0VarT = t(z0Var.f5324e[i9].f6169d);
                if (a0VarT == null) {
                    z7 = true;
                    break;
                } else {
                    this.f5254j[i9] = a0VarT;
                    i9++;
                }
            }
            if (z7) {
                a0[] a0VarArr2 = this.f5255k;
                System.arraycopy(a0VarArr2, 0, this.f5254j, 0, a0VarArr2.length);
            }
        }
        d2.d dVar = z0Var.f5323d;
        if (dVar != null) {
            for (Class<? extends b1> cls : dVar.serialzeFilters()) {
                try {
                    b(cls.getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception unused) {
                }
            }
        }
    }

    public char A(i0 i0Var, Object obj, char c8) {
        List<c> list = i0Var.f5193b;
        if (list != null) {
            Iterator<c> it = list.iterator();
            while (it.hasNext()) {
                c8 = it.next().f(i0Var, obj, c8);
            }
        }
        List<c> list2 = this.f5193b;
        if (list2 != null) {
            Iterator<c> it2 = list2.iterator();
            while (it2.hasNext()) {
                c8 = it2.next().f(i0Var, obj, c8);
            }
        }
        return c8;
    }

    public char B(i0 i0Var, Object obj, char c8) {
        List<k> list = i0Var.f5192a;
        if (list != null) {
            Iterator<k> it = list.iterator();
            while (it.hasNext()) {
                c8 = it.next().f(i0Var, obj, c8);
            }
        }
        List<k> list2 = this.f5192a;
        if (list2 != null) {
            Iterator<k> it2 = list2.iterator();
            while (it2.hasNext()) {
                c8 = it2.next().f(i0Var, obj, c8);
            }
        }
        return c8;
    }

    public void C(i0 i0Var, String str, Object obj) {
        if (str == null) {
            str = i0Var.f5240j.f5178l;
        }
        i0Var.f5241k.F(str, false);
        String name = this.f5256l.f5321b;
        if (name == null) {
            Class<?> superclass = obj.getClass();
            if (l2.l.w0(superclass)) {
                superclass = superclass.getSuperclass();
            }
            name = superclass.getName();
        }
        i0Var.I(name);
    }

    public void D(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws Throwable {
        z(i0Var, obj, obj2, type, i8, false);
    }

    public boolean E(i0 i0Var, Object obj, int i8) {
        IdentityHashMap<Object, y0> identityHashMap;
        y0 y0Var = i0Var.f5248r;
        int i9 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (y0Var == null || (y0Var.f5318d & i9) != 0 || (i8 & i9) != 0 || (identityHashMap = i0Var.f5247q) == null || !identityHashMap.containsKey(obj)) {
            return false;
        }
        i0Var.K(obj);
        return true;
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws Throwable {
        z(i0Var, obj, obj2, type, i8, false);
    }

    public boolean q(i0 i0Var, String str) {
        List<l0> list = i0Var.f5198g;
        if (list != null) {
            Iterator<l0> it = list.iterator();
            while (it.hasNext()) {
                if (!it.next().c(str)) {
                    return false;
                }
            }
        }
        List<l0> list2 = this.f5198g;
        if (list2 == null) {
            return true;
        }
        Iterator<l0> it2 = list2.iterator();
        while (it2.hasNext()) {
            if (!it2.next().c(str)) {
                return false;
            }
        }
        return true;
    }

    public Set<String> r(Object obj) throws Exception {
        HashSet hashSet = new HashSet();
        for (a0 a0Var : this.f5255k) {
            if (a0Var.c(obj) != null) {
                hashSet.add(a0Var.f5150d.f6169d);
            }
        }
        return hashSet;
    }

    public a0 s(long j8) {
        PropertyNamingStrategy[] propertyNamingStrategyArrValues;
        int iBinarySearch;
        if (this.f5257m == null) {
            propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            long[] jArr = new long[this.f5255k.length * propertyNamingStrategyArrValues.length];
            int i8 = 0;
            int i9 = 0;
            while (true) {
                a0[] a0VarArr = this.f5255k;
                if (i8 >= a0VarArr.length) {
                    break;
                }
                String str = a0VarArr[i8].f5150d.f6169d;
                jArr[i9] = l2.l.I(str);
                i9++;
                for (PropertyNamingStrategy propertyNamingStrategy : propertyNamingStrategyArrValues) {
                    String strTranslate = propertyNamingStrategy.translate(str);
                    if (!str.equals(strTranslate)) {
                        jArr[i9] = l2.l.I(strTranslate);
                        i9++;
                    }
                }
                i8++;
            }
            Arrays.sort(jArr, 0, i9);
            this.f5257m = new long[i9];
            System.arraycopy(jArr, 0, this.f5257m, 0, i9);
        } else {
            propertyNamingStrategyArrValues = null;
        }
        int iBinarySearch2 = Arrays.binarySearch(this.f5257m, j8);
        if (iBinarySearch2 < 0) {
            return null;
        }
        if (this.f5258n == null) {
            if (propertyNamingStrategyArrValues == null) {
                propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            }
            short[] sArr = new short[this.f5257m.length];
            Arrays.fill(sArr, (short) -1);
            int i10 = 0;
            while (true) {
                a0[] a0VarArr2 = this.f5255k;
                if (i10 >= a0VarArr2.length) {
                    break;
                }
                String str2 = a0VarArr2[i10].f5150d.f6169d;
                int iBinarySearch3 = Arrays.binarySearch(this.f5257m, l2.l.I(str2));
                if (iBinarySearch3 >= 0) {
                    sArr[iBinarySearch3] = (short) i10;
                }
                for (PropertyNamingStrategy propertyNamingStrategy2 : propertyNamingStrategyArrValues) {
                    String strTranslate2 = propertyNamingStrategy2.translate(str2);
                    if (!str2.equals(strTranslate2) && (iBinarySearch = Arrays.binarySearch(this.f5257m, l2.l.I(strTranslate2))) >= 0) {
                        sArr[iBinarySearch] = (short) i10;
                    }
                }
                i10++;
            }
            this.f5258n = sArr;
        }
        short s7 = this.f5258n[iBinarySearch2];
        if (s7 != -1) {
            return this.f5255k[s7];
        }
        return null;
    }

    public a0 t(String str) {
        if (str == null) {
            return null;
        }
        int i8 = 0;
        int length = this.f5255k.length - 1;
        while (i8 <= length) {
            int i9 = (i8 + length) >>> 1;
            int iCompareTo = this.f5255k[i9].f5150d.f6169d.compareTo(str);
            if (iCompareTo < 0) {
                i8 = i9 + 1;
            } else {
                if (iCompareTo <= 0) {
                    return this.f5255k[i9];
                }
                length = i9 - 1;
            }
        }
        return null;
    }

    public Object u(Object obj, String str, long j8, boolean z7) {
        a0 a0VarS = s(j8);
        if (a0VarS == null) {
            if (!z7) {
                return null;
            }
            throw new JSONException("field not found. " + str);
        }
        try {
            return a0VarS.b(obj);
        } catch (IllegalAccessException e8) {
            throw new JSONException("getFieldValue error." + str, e8);
        } catch (InvocationTargetException e9) {
            throw new JSONException("getFieldValue error." + str, e9);
        }
    }

    public List<Object> v(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.f5255k.length);
        for (a0 a0Var : this.f5255k) {
            arrayList.add(a0Var.b(obj));
        }
        return arrayList;
    }

    public Map<String, Object> w(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.f5255k.length);
        for (a0 a0Var : this.f5255k) {
            boolean zIsEnabled = SerializerFeature.isEnabled(a0Var.f5152f, SerializerFeature.SkipTransientField);
            l2.c cVar = a0Var.f5150d;
            if (!zIsEnabled || cVar == null || !cVar.f6183r) {
                if (cVar.f6187v) {
                    Object json = c2.a.toJSON(a0Var.b(obj));
                    if (json instanceof Map) {
                        linkedHashMap.putAll((Map) json);
                    } else {
                        linkedHashMap.put(a0Var.f5150d.f6169d, a0Var.b(obj));
                    }
                } else {
                    linkedHashMap.put(cVar.f6169d, a0Var.b(obj));
                }
            }
        }
        return linkedHashMap;
    }

    public int x(Object obj) throws Exception {
        int i8 = 0;
        for (a0 a0Var : this.f5255k) {
            if (a0Var.c(obj) != null) {
                i8++;
            }
        }
        return i8;
    }

    public boolean y(i0 i0Var, int i8) {
        int i9 = SerializerFeature.BeanToArray.mask;
        return ((this.f5256l.f5326g & i9) == 0 && !i0Var.f5241k.f5221r && (i8 & i9) == 0) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02a0 A[Catch: Exception -> 0x0475, all -> 0x0494, TryCatch #3 {Exception -> 0x0475, blocks: (B:100:0x014c, B:111:0x0177, B:114:0x019e, B:116:0x01aa, B:118:0x01b5, B:120:0x01bf, B:123:0x01c9, B:125:0x01d5, B:127:0x01d9, B:130:0x01e0, B:132:0x01e4, B:133:0x01e8, B:135:0x01ed, B:137:0x01f0, B:139:0x01f6, B:141:0x0202, B:143:0x0206, B:146:0x020d, B:149:0x0214, B:151:0x0219, B:154:0x021d, B:156:0x0225, B:158:0x0231, B:160:0x0235, B:163:0x023c, B:165:0x0240, B:166:0x0245, B:168:0x024a, B:170:0x024d, B:171:0x0252, B:173:0x025a, B:175:0x0266, B:177:0x026a, B:180:0x0271, B:182:0x0275, B:183:0x027a, B:185:0x027f, B:187:0x0282, B:189:0x0289, B:191:0x028d, B:193:0x0297, B:197:0x02a0, B:199:0x02a4, B:201:0x02ad, B:203:0x02b4, B:205:0x02ba, B:207:0x02be, B:210:0x02c9, B:212:0x02cd, B:214:0x02d1, B:217:0x02dc, B:219:0x02e0, B:221:0x02e4, B:224:0x02ef, B:226:0x02f3, B:228:0x02f7, B:231:0x0305, B:233:0x0309, B:235:0x030d, B:238:0x031a, B:240:0x031e, B:242:0x0322, B:245:0x0330, B:247:0x0334, B:249:0x0338, B:253:0x0344, B:255:0x0348, B:257:0x034c, B:260:0x035c, B:262:0x0369, B:267:0x0375, B:269:0x037b, B:326:0x0439, B:328:0x043d, B:330:0x0441, B:333:0x044b, B:335:0x0453, B:336:0x045b, B:338:0x0461, B:274:0x0387, B:275:0x038a, B:277:0x0390, B:279:0x039c, B:286:0x03b2, B:291:0x03bc, B:295:0x03cf, B:298:0x03d7, B:301:0x03e1, B:303:0x03e9, B:304:0x03f2, B:306:0x03fb, B:308:0x0402, B:309:0x0406, B:311:0x0409, B:312:0x040d, B:313:0x0411, B:315:0x0416, B:316:0x041a, B:317:0x041e, B:319:0x0422, B:321:0x0426, B:324:0x0432, B:325:0x0436, B:292:0x03c5), top: B:418:0x014c }] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02b4 A[Catch: Exception -> 0x0475, all -> 0x0494, TryCatch #3 {Exception -> 0x0475, blocks: (B:100:0x014c, B:111:0x0177, B:114:0x019e, B:116:0x01aa, B:118:0x01b5, B:120:0x01bf, B:123:0x01c9, B:125:0x01d5, B:127:0x01d9, B:130:0x01e0, B:132:0x01e4, B:133:0x01e8, B:135:0x01ed, B:137:0x01f0, B:139:0x01f6, B:141:0x0202, B:143:0x0206, B:146:0x020d, B:149:0x0214, B:151:0x0219, B:154:0x021d, B:156:0x0225, B:158:0x0231, B:160:0x0235, B:163:0x023c, B:165:0x0240, B:166:0x0245, B:168:0x024a, B:170:0x024d, B:171:0x0252, B:173:0x025a, B:175:0x0266, B:177:0x026a, B:180:0x0271, B:182:0x0275, B:183:0x027a, B:185:0x027f, B:187:0x0282, B:189:0x0289, B:191:0x028d, B:193:0x0297, B:197:0x02a0, B:199:0x02a4, B:201:0x02ad, B:203:0x02b4, B:205:0x02ba, B:207:0x02be, B:210:0x02c9, B:212:0x02cd, B:214:0x02d1, B:217:0x02dc, B:219:0x02e0, B:221:0x02e4, B:224:0x02ef, B:226:0x02f3, B:228:0x02f7, B:231:0x0305, B:233:0x0309, B:235:0x030d, B:238:0x031a, B:240:0x031e, B:242:0x0322, B:245:0x0330, B:247:0x0334, B:249:0x0338, B:253:0x0344, B:255:0x0348, B:257:0x034c, B:260:0x035c, B:262:0x0369, B:267:0x0375, B:269:0x037b, B:326:0x0439, B:328:0x043d, B:330:0x0441, B:333:0x044b, B:335:0x0453, B:336:0x045b, B:338:0x0461, B:274:0x0387, B:275:0x038a, B:277:0x0390, B:279:0x039c, B:286:0x03b2, B:291:0x03bc, B:295:0x03cf, B:298:0x03d7, B:301:0x03e1, B:303:0x03e9, B:304:0x03f2, B:306:0x03fb, B:308:0x0402, B:309:0x0406, B:311:0x0409, B:312:0x040d, B:313:0x0411, B:315:0x0416, B:316:0x041a, B:317:0x041e, B:319:0x0422, B:321:0x0426, B:324:0x0432, B:325:0x0436, B:292:0x03c5), top: B:418:0x014c }] */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x046d  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0507 A[Catch: all -> 0x051a, TRY_ENTER, TryCatch #13 {all -> 0x051a, blocks: (B:387:0x0507, B:397:0x0557, B:399:0x055d, B:400:0x0575, B:402:0x0579, B:406:0x0582, B:407:0x0587, B:391:0x051e, B:393:0x0522, B:395:0x0526, B:396:0x0541), top: B:434:0x0505 }] */
    /* JADX WARN: Removed duplicated region for block: B:390:0x051c  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x055d A[Catch: all -> 0x051a, TryCatch #13 {all -> 0x051a, blocks: (B:387:0x0507, B:397:0x0557, B:399:0x055d, B:400:0x0575, B:402:0x0579, B:406:0x0582, B:407:0x0587, B:391:0x051e, B:393:0x0522, B:395:0x0526, B:396:0x0541), top: B:434:0x0505 }] */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0579 A[Catch: all -> 0x051a, TryCatch #13 {all -> 0x051a, blocks: (B:387:0x0507, B:397:0x0557, B:399:0x055d, B:400:0x0575, B:402:0x0579, B:406:0x0582, B:407:0x0587, B:391:0x051e, B:393:0x0522, B:395:0x0526, B:396:0x0541), top: B:434:0x0505 }] */
    /* JADX WARN: Removed duplicated region for block: B:404:0x057f  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0580  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00fe  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void z(h2.i0 r34, java.lang.Object r35, java.lang.Object r36, java.lang.reflect.Type r37, int r38, boolean r39) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1421
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h2.j0.z(h2.i0, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int, boolean):void");
    }
}
