package b.a.a.k;

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
    public final a0[] j;
    public final a0[] k;
    public z0 l;
    public volatile transient long[] m;
    public volatile transient short[] n;

    public j0(z0 z0Var) {
        a0[] a0VarArr;
        boolean z;
        this.l = z0Var;
        this.k = new a0[z0Var.f422f.length];
        int i = 0;
        while (true) {
            a0VarArr = this.k;
            if (i >= a0VarArr.length) {
                break;
            }
            a0VarArr[i] = new a0(z0Var.f417a, z0Var.f422f[i]);
            i++;
        }
        b.a.a.n.c[] cVarArr = z0Var.f421e;
        if (cVarArr == z0Var.f422f) {
            this.j = a0VarArr;
        } else {
            this.j = new a0[cVarArr.length];
            int i2 = 0;
            while (true) {
                if (i2 >= this.j.length) {
                    z = false;
                    break;
                }
                a0 a0VarT = t(z0Var.f421e[i2].f429a);
                if (a0VarT == null) {
                    z = true;
                    break;
                } else {
                    this.j[i2] = a0VarT;
                    i2++;
                }
            }
            if (z) {
                a0[] a0VarArr2 = this.k;
                System.arraycopy(a0VarArr2, 0, this.j, 0, a0VarArr2.length);
            }
        }
        b.a.a.h.d dVar = z0Var.f420d;
        if (dVar != null) {
            for (Class<? extends b1> cls : dVar.serialzeFilters()) {
                try {
                    b(cls.getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception unused) {
                }
            }
        }
    }

    public char A(i0 i0Var, Object obj, char c2) {
        List<c> list = i0Var.f343b;
        if (list != null) {
            Iterator<c> it = list.iterator();
            while (it.hasNext()) {
                c2 = it.next().f(i0Var, obj, c2);
            }
        }
        List<c> list2 = this.f343b;
        if (list2 != null) {
            Iterator<c> it2 = list2.iterator();
            while (it2.hasNext()) {
                c2 = it2.next().f(i0Var, obj, c2);
            }
        }
        return c2;
    }

    public char B(i0 i0Var, Object obj, char c2) {
        List<k> list = i0Var.f342a;
        if (list != null) {
            Iterator<k> it = list.iterator();
            while (it.hasNext()) {
                c2 = it.next().f(i0Var, obj, c2);
            }
        }
        List<k> list2 = this.f342a;
        if (list2 != null) {
            Iterator<k> it2 = list2.iterator();
            while (it2.hasNext()) {
                c2 = it2.next().f(i0Var, obj, c2);
            }
        }
        return c2;
    }

    public void C(i0 i0Var, String str, Object obj) {
        if (str == null) {
            str = i0Var.j.l;
        }
        i0Var.k.v(str, false);
        String name = this.l.f418b;
        if (name == null) {
            Class<?> superclass = obj.getClass();
            if (b.a.a.n.l.w0(superclass)) {
                superclass = superclass.getSuperclass();
            }
            name = superclass.getName();
        }
        i0Var.I(name);
    }

    public void D(i0 i0Var, Object obj, Object obj2, Type type, int i) throws Throwable {
        z(i0Var, obj, obj2, type, i, false);
    }

    public boolean E(i0 i0Var, Object obj, int i) {
        IdentityHashMap<Object, y0> identityHashMap;
        y0 y0Var = i0Var.r;
        int i2 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (y0Var == null || (y0Var.f415d & i2) != 0 || (i & i2) != 0 || (identityHashMap = i0Var.q) == null || !identityHashMap.containsKey(obj)) {
            return false;
        }
        i0Var.K(obj);
        return true;
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws Throwable {
        z(i0Var, obj, obj2, type, i, false);
    }

    public boolean q(i0 i0Var, String str) {
        List<l0> list = i0Var.g;
        if (list != null) {
            Iterator<l0> it = list.iterator();
            while (it.hasNext()) {
                if (!it.next().c(str)) {
                    return false;
                }
            }
        }
        List<l0> list2 = this.g;
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
        for (a0 a0Var : this.k) {
            if (a0Var.c(obj) != null) {
                hashSet.add(a0Var.f321a.f429a);
            }
        }
        return hashSet;
    }

    public a0 s(long j) {
        PropertyNamingStrategy[] propertyNamingStrategyArrValues;
        int iBinarySearch;
        if (this.m == null) {
            propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            long[] jArr = new long[this.k.length * propertyNamingStrategyArrValues.length];
            int i = 0;
            int i2 = 0;
            while (true) {
                a0[] a0VarArr = this.k;
                if (i >= a0VarArr.length) {
                    break;
                }
                String str = a0VarArr[i].f321a.f429a;
                jArr[i2] = b.a.a.n.l.I(str);
                i2++;
                for (PropertyNamingStrategy propertyNamingStrategy : propertyNamingStrategyArrValues) {
                    String strTranslate = propertyNamingStrategy.translate(str);
                    if (!str.equals(strTranslate)) {
                        jArr[i2] = b.a.a.n.l.I(strTranslate);
                        i2++;
                    }
                }
                i++;
            }
            Arrays.sort(jArr, 0, i2);
            this.m = new long[i2];
            System.arraycopy(jArr, 0, this.m, 0, i2);
        } else {
            propertyNamingStrategyArrValues = null;
        }
        int iBinarySearch2 = Arrays.binarySearch(this.m, j);
        if (iBinarySearch2 < 0) {
            return null;
        }
        if (this.n == null) {
            if (propertyNamingStrategyArrValues == null) {
                propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            }
            short[] sArr = new short[this.m.length];
            Arrays.fill(sArr, (short) -1);
            int i3 = 0;
            while (true) {
                a0[] a0VarArr2 = this.k;
                if (i3 >= a0VarArr2.length) {
                    break;
                }
                String str2 = a0VarArr2[i3].f321a.f429a;
                int iBinarySearch3 = Arrays.binarySearch(this.m, b.a.a.n.l.I(str2));
                if (iBinarySearch3 >= 0) {
                    sArr[iBinarySearch3] = (short) i3;
                }
                for (PropertyNamingStrategy propertyNamingStrategy2 : propertyNamingStrategyArrValues) {
                    String strTranslate2 = propertyNamingStrategy2.translate(str2);
                    if (!str2.equals(strTranslate2) && (iBinarySearch = Arrays.binarySearch(this.m, b.a.a.n.l.I(strTranslate2))) >= 0) {
                        sArr[iBinarySearch] = (short) i3;
                    }
                }
                i3++;
            }
            this.n = sArr;
        }
        short s = this.n[iBinarySearch2];
        if (s != -1) {
            return this.k[s];
        }
        return null;
    }

    public a0 t(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = this.k.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int iCompareTo = this.k[i2].f321a.f429a.compareTo(str);
            if (iCompareTo < 0) {
                i = i2 + 1;
            } else {
                if (iCompareTo <= 0) {
                    return this.k[i2];
                }
                length = i2 - 1;
            }
        }
        return null;
    }

    public Object u(Object obj, String str, long j, boolean z) {
        a0 a0VarS = s(j);
        if (a0VarS == null) {
            if (!z) {
                return null;
            }
            throw new JSONException("field not found. " + str);
        }
        try {
            return a0VarS.b(obj);
        } catch (IllegalAccessException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        } catch (InvocationTargetException e3) {
            throw new JSONException("getFieldValue error." + str, e3);
        }
    }

    public List<Object> v(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.k.length);
        for (a0 a0Var : this.k) {
            arrayList.add(a0Var.b(obj));
        }
        return arrayList;
    }

    public Map<String, Object> w(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.k.length);
        for (a0 a0Var : this.k) {
            boolean zIsEnabled = SerializerFeature.isEnabled(a0Var.f323c, SerializerFeature.SkipTransientField);
            b.a.a.n.c cVar = a0Var.f321a;
            if (!zIsEnabled || cVar == null || !cVar.o) {
                if (cVar.s) {
                    Object json = b.a.a.a.toJSON(a0Var.b(obj));
                    if (json instanceof Map) {
                        linkedHashMap.putAll((Map) json);
                    } else {
                        linkedHashMap.put(a0Var.f321a.f429a, a0Var.b(obj));
                    }
                } else {
                    linkedHashMap.put(cVar.f429a, a0Var.b(obj));
                }
            }
        }
        return linkedHashMap;
    }

    public int x(Object obj) throws Exception {
        int i = 0;
        for (a0 a0Var : this.k) {
            if (a0Var.c(obj) != null) {
                i++;
            }
        }
        return i;
    }

    public boolean y(i0 i0Var, int i) {
        int i2 = SerializerFeature.BeanToArray.mask;
        return ((this.l.g & i2) == 0 && !i0Var.k.o && (i & i2) == 0) ? false : true;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(3:(4:428|59|60|(4:432|61|(1:65)(1:64)|(4:416|67|(2:74|438)(1:(4:75|(3:(1:82)|74|438)(1:80)|83|(7:(1:91)(8:412|93|94|418|100|(1:102)(4:(3:424|104|(1:107))|111|112|(4:114|(1:116)|(2:121|(2:123|(2:130|(1:132)(2:133|(1:137)))(2:129|259))(1:(2:139|(2:146|(1:148))(2:145|259))(2:154|(2:156|(2:163|(1:165)(2:166|(1:170)))(2:162|259))(2:171|(2:173|(2:180|(1:182)(2:183|(1:187)))(2:179|259))(1:(0)(3:191|(0)(1:195)|259))))))(1:120)|(6:(3:253|(2:260|(1:262))|259)(1:263)|264|(2:(1:267)(1:268)|269)(2:271|(2:(1:274)|275)(1:(6:277|(1:284)(1:283)|(1:(1:291)(1:292))(1:293)|(2:295|(2:317|(1:324)(1:323))(1:(4:301|(1:303)|304|(1:312)(1:311))(2:313|(1:315)(1:316))))(1:325)|326|(2:344|(1:346))(4:330|(4:333|(4:335|(2:336|(2:338|(2:441|340)(1:442))(2:440|341))|(0)|(0))|344|(0))|332|(0)))(0)))|270|326|(3:328|344|(0))(0))(2:197|(2:199|(3:203|(0)(1:251)|259)(0))(0)))(1:(0)(0)))|347|439)|92|418|100|(0)(0)|347|439)(0))(0))|354)(0)))|420|57) */
    /* JADX WARN: Code restructure failed: missing block: B:377:0x04d5, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:378:0x04d6, code lost:
    
        r0 = r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02a0 A[Catch: Exception -> 0x0475, all -> 0x0494, TryCatch #3 {Exception -> 0x0475, blocks: (B:100:0x014c, B:111:0x0177, B:114:0x019e, B:116:0x01aa, B:118:0x01b5, B:120:0x01bf, B:123:0x01c9, B:125:0x01d5, B:127:0x01d9, B:130:0x01e0, B:132:0x01e4, B:133:0x01e8, B:135:0x01ed, B:137:0x01f0, B:139:0x01f6, B:141:0x0202, B:143:0x0206, B:146:0x020d, B:149:0x0214, B:151:0x0219, B:154:0x021d, B:156:0x0225, B:158:0x0231, B:160:0x0235, B:163:0x023c, B:165:0x0240, B:166:0x0245, B:168:0x024a, B:170:0x024d, B:171:0x0252, B:173:0x025a, B:175:0x0266, B:177:0x026a, B:180:0x0271, B:182:0x0275, B:183:0x027a, B:185:0x027f, B:187:0x0282, B:189:0x0289, B:191:0x028d, B:193:0x0297, B:197:0x02a0, B:199:0x02a4, B:201:0x02ad, B:203:0x02b4, B:205:0x02ba, B:207:0x02be, B:210:0x02c9, B:212:0x02cd, B:214:0x02d1, B:217:0x02dc, B:219:0x02e0, B:221:0x02e4, B:224:0x02ef, B:226:0x02f3, B:228:0x02f7, B:231:0x0305, B:233:0x0309, B:235:0x030d, B:238:0x031a, B:240:0x031e, B:242:0x0322, B:245:0x0330, B:247:0x0334, B:249:0x0338, B:253:0x0344, B:255:0x0348, B:257:0x034c, B:260:0x035c, B:262:0x0369, B:267:0x0375, B:269:0x037b, B:326:0x0439, B:328:0x043d, B:330:0x0441, B:333:0x044b, B:335:0x0453, B:336:0x045b, B:338:0x0461, B:274:0x0387, B:275:0x038a, B:277:0x0390, B:279:0x039c, B:286:0x03b2, B:291:0x03bc, B:295:0x03cf, B:298:0x03d7, B:301:0x03e1, B:303:0x03e9, B:304:0x03f2, B:306:0x03fb, B:308:0x0402, B:309:0x0406, B:311:0x0409, B:312:0x040d, B:313:0x0411, B:315:0x0416, B:316:0x041a, B:317:0x041e, B:319:0x0422, B:321:0x0426, B:324:0x0432, B:325:0x0436, B:292:0x03c5), top: B:418:0x014c }] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02b4 A[Catch: Exception -> 0x0475, all -> 0x0494, TryCatch #3 {Exception -> 0x0475, blocks: (B:100:0x014c, B:111:0x0177, B:114:0x019e, B:116:0x01aa, B:118:0x01b5, B:120:0x01bf, B:123:0x01c9, B:125:0x01d5, B:127:0x01d9, B:130:0x01e0, B:132:0x01e4, B:133:0x01e8, B:135:0x01ed, B:137:0x01f0, B:139:0x01f6, B:141:0x0202, B:143:0x0206, B:146:0x020d, B:149:0x0214, B:151:0x0219, B:154:0x021d, B:156:0x0225, B:158:0x0231, B:160:0x0235, B:163:0x023c, B:165:0x0240, B:166:0x0245, B:168:0x024a, B:170:0x024d, B:171:0x0252, B:173:0x025a, B:175:0x0266, B:177:0x026a, B:180:0x0271, B:182:0x0275, B:183:0x027a, B:185:0x027f, B:187:0x0282, B:189:0x0289, B:191:0x028d, B:193:0x0297, B:197:0x02a0, B:199:0x02a4, B:201:0x02ad, B:203:0x02b4, B:205:0x02ba, B:207:0x02be, B:210:0x02c9, B:212:0x02cd, B:214:0x02d1, B:217:0x02dc, B:219:0x02e0, B:221:0x02e4, B:224:0x02ef, B:226:0x02f3, B:228:0x02f7, B:231:0x0305, B:233:0x0309, B:235:0x030d, B:238:0x031a, B:240:0x031e, B:242:0x0322, B:245:0x0330, B:247:0x0334, B:249:0x0338, B:253:0x0344, B:255:0x0348, B:257:0x034c, B:260:0x035c, B:262:0x0369, B:267:0x0375, B:269:0x037b, B:326:0x0439, B:328:0x043d, B:330:0x0441, B:333:0x044b, B:335:0x0453, B:336:0x045b, B:338:0x0461, B:274:0x0387, B:275:0x038a, B:277:0x0390, B:279:0x039c, B:286:0x03b2, B:291:0x03bc, B:295:0x03cf, B:298:0x03d7, B:301:0x03e1, B:303:0x03e9, B:304:0x03f2, B:306:0x03fb, B:308:0x0402, B:309:0x0406, B:311:0x0409, B:312:0x040d, B:313:0x0411, B:315:0x0416, B:316:0x041a, B:317:0x041e, B:319:0x0422, B:321:0x0426, B:324:0x0432, B:325:0x0436, B:292:0x03c5), top: B:418:0x014c }] */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x046d  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0508 A[Catch: all -> 0x051b, TRY_ENTER, TryCatch #13 {all -> 0x051b, blocks: (B:387:0x0508, B:397:0x0558, B:399:0x055e, B:400:0x0576, B:402:0x057a, B:406:0x0583, B:407:0x0588, B:391:0x051f, B:393:0x0523, B:395:0x0527, B:396:0x0542), top: B:434:0x0506 }] */
    /* JADX WARN: Removed duplicated region for block: B:390:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x055e A[Catch: all -> 0x051b, TryCatch #13 {all -> 0x051b, blocks: (B:387:0x0508, B:397:0x0558, B:399:0x055e, B:400:0x0576, B:402:0x057a, B:406:0x0583, B:407:0x0588, B:391:0x051f, B:393:0x0523, B:395:0x0527, B:396:0x0542), top: B:434:0x0506 }] */
    /* JADX WARN: Removed duplicated region for block: B:402:0x057a A[Catch: all -> 0x051b, TryCatch #13 {all -> 0x051b, blocks: (B:387:0x0508, B:397:0x0558, B:399:0x055e, B:400:0x0576, B:402:0x057a, B:406:0x0583, B:407:0x0588, B:391:0x051f, B:393:0x0523, B:395:0x0527, B:396:0x0542), top: B:434:0x0506 }] */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0580  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0581  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00fe  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void z(b.a.a.k.i0 r34, java.lang.Object r35, java.lang.Object r36, java.lang.reflect.Type r37, int r38, boolean r39) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1422
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.k.j0.z(b.a.a.k.i0, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int, boolean):void");
    }
}
