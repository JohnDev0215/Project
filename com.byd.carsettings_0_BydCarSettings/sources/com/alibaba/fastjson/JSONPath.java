package com.alibaba.fastjson;

import androidx.core.app.NotificationCompat;
import b.a.a.k.a1;
import b.a.a.k.j0;
import b.a.a.k.t0;
import com.bydux.api.common.tools.util.ListUtils;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class JSONPath implements b.a.a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ConcurrentMap<String, JSONPath> f2361a = new ConcurrentHashMap(128, 0.75f, 1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2362b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a0[] f2363c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2364d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a1 f2365e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.a.a.j.h f2366f;

    public enum Operator {
        EQ,
        NE,
        GT,
        GE,
        LT,
        LE,
        LIKE,
        NOT_LIKE,
        RLIKE,
        NOT_RLIKE,
        IN,
        NOT_IN,
        BETWEEN,
        NOT_BETWEEN,
        And,
        Or,
        REG_MATCH
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2368a;

        static {
            int[] iArr = new int[Operator.values().length];
            f2368a = iArr;
            try {
                iArr[Operator.EQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2368a[Operator.NE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2368a[Operator.GE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2368a[Operator.GT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2368a[Operator.LE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2368a[Operator.LT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public interface a0 {
        Object a(JSONPath jSONPath, Object obj, Object obj2);
    }

    public static class b implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2369a;

        public b(int i) {
            this.f2369a = i;
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.h(obj2, this.f2369a);
        }
    }

    public static class b0 implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b0 f2370a = new b0();

        @Override // com.alibaba.fastjson.JSONPath.a0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Integer a(JSONPath jSONPath, Object obj, Object obj2) {
            return Integer.valueOf(jSONPath.g(obj2));
        }
    }

    public static class c extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final double f2371f;
        public final Operator g;

        public c(String str, boolean z, double d2, Operator operator) {
            super(str, z);
            this.f2371f = d2;
            this.g = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null || !(objB instanceof Number)) {
                return false;
            }
            double dDoubleValue = ((Number) objB).doubleValue();
            switch (a.f2368a[this.g.ordinal()]) {
                case 1:
                    if (dDoubleValue == this.f2371f) {
                    }
                    break;
                case 2:
                    if (dDoubleValue != this.f2371f) {
                    }
                    break;
                case 3:
                    if (dDoubleValue >= this.f2371f) {
                    }
                    break;
                case 4:
                    if (dDoubleValue > this.f2371f) {
                    }
                    break;
                case 5:
                    if (dDoubleValue <= this.f2371f) {
                    }
                    break;
                case 6:
                    if (dDoubleValue < this.f2371f) {
                    }
                    break;
            }
            return false;
        }
    }

    public static class c0 extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final String[] f2372f;
        public final boolean g;

        public c0(String str, boolean z, String[] strArr, boolean z2) {
            super(str, z);
            this.f2372f = strArr;
            this.g = z2;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            for (String str : this.f2372f) {
                if (str == objB) {
                    return !this.g;
                }
                if (str != null && str.equals(objB)) {
                    return !this.g;
                }
            }
            return this.g;
        }
    }

    public interface d {
        boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3);
    }

    public static class d0 extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final String f2373f;
        public final Operator g;

        public d0(String str, boolean z, String str2, Operator operator) {
            super(str, z);
            this.f2373f = str2;
            this.g = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            Operator operator = this.g;
            if (operator == Operator.EQ) {
                return this.f2373f.equals(objB);
            }
            if (operator == Operator.NE) {
                return !this.f2373f.equals(objB);
            }
            if (objB == null) {
                return false;
            }
            int iCompareTo = this.f2373f.compareTo(objB.toString());
            Operator operator2 = this.g;
            return operator2 == Operator.GE ? iCompareTo <= 0 : operator2 == Operator.GT ? iCompareTo < 0 : operator2 == Operator.LE ? iCompareTo >= 0 : operator2 == Operator.LT && iCompareTo > 0;
        }
    }

    public static class e implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2374a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List<d> f2375b;

        public e(d dVar, d dVar2, boolean z) {
            ArrayList arrayList = new ArrayList(2);
            this.f2375b = arrayList;
            arrayList.add(dVar);
            this.f2375b.add(dVar2);
            this.f2374a = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            if (this.f2374a) {
                Iterator<d> it = this.f2375b.iterator();
                while (it.hasNext()) {
                    if (!it.next().a(jSONPath, obj, obj2, obj3)) {
                        return false;
                    }
                }
                return true;
            }
            Iterator<d> it2 = this.f2375b.iterator();
            while (it2.hasNext()) {
                if (it2.next().a(jSONPath, obj, obj2, obj3)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class e0 implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e0 f2376a = new e0();

        @Override // com.alibaba.fastjson.JSONPath.a0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public String a(JSONPath jSONPath, Object obj, Object obj2) {
            return obj2 == null ? "null" : obj2 instanceof Collection ? "array" : obj2 instanceof Number ? "number" : obj2 instanceof Boolean ? "boolean" : ((obj2 instanceof String) || (obj2 instanceof UUID) || (obj2 instanceof Enum)) ? "string" : "object";
        }
    }

    public static class f implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d f2377a;

        public f(d dVar) {
            this.f2377a = dVar;
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            if (obj2 == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            if (!(obj2 instanceof Iterable)) {
                if (this.f2377a.a(jSONPath, obj, obj2, obj2)) {
                    return obj2;
                }
                return null;
            }
            for (Object obj3 : (Iterable) obj2) {
                if (this.f2377a.a(jSONPath, obj, obj2, obj3)) {
                    jSONArray.add(obj3);
                }
            }
            return jSONArray;
        }
    }

    public static class f0 extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Object f2378f;
        public boolean g;

        public f0(String str, boolean z, Object obj, boolean z2) {
            super(str, z);
            this.g = true;
            if (obj == null) {
                throw new IllegalArgumentException("value is null");
            }
            this.f2378f = obj;
            this.g = z2;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            boolean zEquals = this.f2378f.equals(b(jSONPath, obj, obj3));
            return !this.g ? !zEquals : zEquals;
        }
    }

    public static class g implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final g f2379a = new g();

        public static Object b(Object obj) {
            if (obj == null) {
                return null;
            }
            if (obj instanceof Float) {
                return Double.valueOf(Math.floor(((Float) obj).floatValue()));
            }
            if (obj instanceof Double) {
                return Double.valueOf(Math.floor(((Double) obj).doubleValue()));
            }
            if (obj instanceof BigDecimal) {
                return ((BigDecimal) obj).setScale(0, RoundingMode.FLOOR);
            }
            if ((obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof BigInteger)) {
                return obj;
            }
            throw new UnsupportedOperationException();
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            if (!(obj2 instanceof JSONArray)) {
                return b(obj2);
            }
            JSONArray jSONArray = (JSONArray) ((JSONArray) obj2).clone();
            for (int i = 0; i < jSONArray.size(); i++) {
                Object obj3 = jSONArray.get(i);
                Object objB = b(obj3);
                if (objB != obj3) {
                    jSONArray.set(i, objB);
                }
            }
            return jSONArray;
        }
    }

    public static class g0 implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final g0 f2380a = new g0(false);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final g0 f2381b = new g0(true);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2382c;

        public g0(boolean z) {
            this.f2382c = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            if (!this.f2382c) {
                return jSONPath.k(obj2);
            }
            ArrayList arrayList = new ArrayList();
            jSONPath.c(obj2, arrayList);
            return arrayList;
        }
    }

    public static class h extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final long f2383f;
        public final long g;
        public final boolean h;

        public h(String str, boolean z, long j, long j2, boolean z2) {
            super(str, z);
            this.f2383f = j;
            this.g = j2;
            this.h = z2;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null) {
                return false;
            }
            if (objB instanceof Number) {
                long jC0 = b.a.a.n.l.C0((Number) objB);
                if (jC0 >= this.f2383f && jC0 <= this.g) {
                    return !this.h;
                }
            }
            return this.h;
        }
    }

    public static class i extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final long[] f2384f;
        public final boolean g;

        public i(String str, boolean z, long[] jArr, boolean z2) {
            super(str, z);
            this.f2384f = jArr;
            this.g = z2;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null) {
                return false;
            }
            if (objB instanceof Number) {
                long jC0 = b.a.a.n.l.C0((Number) objB);
                for (long j : this.f2384f) {
                    if (j == jC0) {
                        return !this.g;
                    }
                }
            }
            return this.g;
        }
    }

    public static class j extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Long[] f2385f;
        public final boolean g;

        public j(String str, boolean z, Long[] lArr, boolean z2) {
            super(str, z);
            this.f2385f = lArr;
            this.g = z2;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            int i = 0;
            if (objB == null) {
                Long[] lArr = this.f2385f;
                int length = lArr.length;
                while (i < length) {
                    if (lArr[i] == null) {
                        return !this.g;
                    }
                    i++;
                }
                return this.g;
            }
            if (objB instanceof Number) {
                long jC0 = b.a.a.n.l.C0((Number) objB);
                Long[] lArr2 = this.f2385f;
                int length2 = lArr2.length;
                while (i < length2) {
                    Long l = lArr2[i];
                    if (l != null && l.longValue() == jC0) {
                        return !this.g;
                    }
                    i++;
                }
            }
            return this.g;
        }
    }

    public static class k extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final long f2386f;
        public final Operator g;
        public BigDecimal h;
        public Float i;
        public Double j;

        public k(String str, boolean z, long j, Operator operator) {
            super(str, z);
            this.f2386f = j;
            this.g = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null || !(objB instanceof Number)) {
                return false;
            }
            if (objB instanceof BigDecimal) {
                if (this.h == null) {
                    this.h = BigDecimal.valueOf(this.f2386f);
                }
                int iCompareTo = this.h.compareTo((BigDecimal) objB);
                switch (a.f2368a[this.g.ordinal()]) {
                    case 1:
                        if (iCompareTo == 0) {
                        }
                        break;
                    case 2:
                        if (iCompareTo != 0) {
                        }
                        break;
                    case 3:
                        if (iCompareTo <= 0) {
                        }
                        break;
                    case 4:
                        if (iCompareTo < 0) {
                        }
                        break;
                    case 5:
                        if (iCompareTo >= 0) {
                        }
                        break;
                    case 6:
                        if (iCompareTo > 0) {
                        }
                        break;
                }
                return false;
            }
            if (objB instanceof Float) {
                if (this.i == null) {
                    this.i = Float.valueOf(this.f2386f);
                }
                int iCompareTo2 = this.i.compareTo((Float) objB);
                switch (a.f2368a[this.g.ordinal()]) {
                    case 1:
                        if (iCompareTo2 == 0) {
                        }
                        break;
                    case 2:
                        if (iCompareTo2 != 0) {
                        }
                        break;
                    case 3:
                        if (iCompareTo2 <= 0) {
                        }
                        break;
                    case 4:
                        if (iCompareTo2 < 0) {
                        }
                        break;
                    case 5:
                        if (iCompareTo2 >= 0) {
                        }
                        break;
                    case 6:
                        if (iCompareTo2 > 0) {
                        }
                        break;
                }
                return false;
            }
            if (!(objB instanceof Double)) {
                long jC0 = b.a.a.n.l.C0((Number) objB);
                switch (a.f2368a[this.g.ordinal()]) {
                    case 1:
                        if (jC0 == this.f2386f) {
                        }
                        break;
                    case 2:
                        if (jC0 != this.f2386f) {
                        }
                        break;
                    case 3:
                        if (jC0 >= this.f2386f) {
                        }
                        break;
                    case 4:
                        if (jC0 > this.f2386f) {
                        }
                        break;
                    case 5:
                        if (jC0 <= this.f2386f) {
                        }
                        break;
                    case 6:
                        if (jC0 < this.f2386f) {
                        }
                        break;
                }
                return false;
            }
            if (this.j == null) {
                this.j = Double.valueOf(this.f2386f);
            }
            int iCompareTo3 = this.j.compareTo((Double) objB);
            switch (a.f2368a[this.g.ordinal()]) {
                case 1:
                    if (iCompareTo3 == 0) {
                    }
                    break;
                case 2:
                    if (iCompareTo3 != 0) {
                    }
                    break;
                case 3:
                    if (iCompareTo3 <= 0) {
                    }
                    break;
                case 4:
                    if (iCompareTo3 < 0) {
                    }
                    break;
                case 5:
                    if (iCompareTo3 >= 0) {
                    }
                    break;
                case 6:
                    if (iCompareTo3 > 0) {
                    }
                    break;
            }
            return false;
        }
    }

    public static class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Pattern f2387a = Pattern.compile("'\\s*,\\s*'");

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f2388b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2389c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public char f2390d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2391e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f2392f;

        public l(String str) {
            this.f2388b = str;
            i();
        }

        public static boolean g(char c2) {
            return c2 == '-' || c2 == '+' || (c2 >= '0' && c2 <= '9');
        }

        public void a(char c2) {
            if (this.f2390d == ' ') {
                i();
            }
            if (this.f2390d == c2) {
                if (h()) {
                    return;
                }
                i();
            } else {
                throw new JSONPathException("expect '" + c2 + ", but '" + this.f2390d + "'");
            }
        }

        public a0 c(String str) {
            int length = str.length();
            char cCharAt = str.charAt(0);
            int i = length - 1;
            char cCharAt2 = str.charAt(i);
            int iIndexOf = str.indexOf(44);
            if (str.length() > 2 && cCharAt == '\'' && cCharAt2 == '\'') {
                String strSubstring = str.substring(1, i);
                return (iIndexOf == -1 || !f2387a.matcher(str).find()) ? new v(strSubstring, false) : new r(strSubstring.split("'\\s*,\\s*'"));
            }
            int iIndexOf2 = str.indexOf(58);
            if (iIndexOf == -1 && iIndexOf2 == -1) {
                if (b.a.a.n.l.u0(str)) {
                    try {
                        return new b(Integer.parseInt(str));
                    } catch (NumberFormatException unused) {
                        return new v(str, false);
                    }
                }
                if (str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') {
                    str = str.substring(1, str.length() - 1);
                }
                return new v(str, false);
            }
            if (iIndexOf != -1) {
                String[] strArrSplit = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                int[] iArr = new int[strArrSplit.length];
                for (int i2 = 0; i2 < strArrSplit.length; i2++) {
                    iArr[i2] = Integer.parseInt(strArrSplit[i2]);
                }
                return new q(iArr);
            }
            if (iIndexOf2 == -1) {
                throw new UnsupportedOperationException();
            }
            String[] strArrSplit2 = str.split(":");
            int length2 = strArrSplit2.length;
            int[] iArr2 = new int[length2];
            for (int i3 = 0; i3 < strArrSplit2.length; i3++) {
                String str2 = strArrSplit2[i3];
                if (str2.length() != 0) {
                    iArr2[i3] = Integer.parseInt(str2);
                } else {
                    if (i3 != 0) {
                        throw new UnsupportedOperationException();
                    }
                    iArr2[i3] = 0;
                }
            }
            int i4 = iArr2[0];
            int i5 = length2 > 1 ? iArr2[1] : -1;
            int i6 = length2 == 3 ? iArr2[2] : 1;
            if (i5 < 0 || i5 >= i4) {
                if (i6 > 0) {
                    return new w(i4, i5, i6);
                }
                throw new UnsupportedOperationException("step must greater than zero : " + i6);
            }
            throw new UnsupportedOperationException("end must greater than or equals start. start " + i4 + ",  end " + i5);
        }

        public a0[] d() {
            String str = this.f2388b;
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException();
            }
            a0[] a0VarArr = new a0[8];
            while (true) {
                a0 a0VarP = p();
                if (a0VarP == null) {
                    break;
                }
                if (a0VarP instanceof v) {
                    v vVar = (v) a0VarP;
                    if (vVar.f2407c || !vVar.f2405a.equals("*")) {
                    }
                }
                int i = this.f2391e;
                if (i == a0VarArr.length) {
                    a0[] a0VarArr2 = new a0[(i * 3) / 2];
                    System.arraycopy(a0VarArr, 0, a0VarArr2, 0, i);
                    a0VarArr = a0VarArr2;
                }
                int i2 = this.f2391e;
                this.f2391e = i2 + 1;
                a0VarArr[i2] = a0VarP;
            }
            int i3 = this.f2391e;
            if (i3 == a0VarArr.length) {
                return a0VarArr;
            }
            a0[] a0VarArr3 = new a0[i3];
            System.arraycopy(a0VarArr, 0, a0VarArr3, 0, i3);
            return a0VarArr3;
        }

        public d e(d dVar) {
            char c2 = this.f2390d;
            boolean z = true;
            boolean z2 = c2 == '&';
            if ((c2 != '&' || f() != '&') && (this.f2390d != '|' || f() != '|')) {
                return dVar;
            }
            i();
            i();
            if (this.f2390d == '(') {
                i();
            } else {
                z = false;
            }
            while (this.f2390d == ' ') {
                i();
            }
            e eVar = new e(dVar, (d) k(false), z2);
            if (z && this.f2390d == ')') {
                i();
            }
            return eVar;
        }

        public char f() {
            return this.f2388b.charAt(this.f2389c);
        }

        public boolean h() {
            return this.f2389c >= this.f2388b.length();
        }

        public void i() {
            String str = this.f2388b;
            int i = this.f2389c;
            this.f2389c = i + 1;
            this.f2390d = str.charAt(i);
        }

        public a0 j(boolean z) {
            Object objK = k(z);
            return objK instanceof a0 ? (a0) objK : new f((d) objK);
        }

        /* JADX WARN: Removed duplicated region for block: B:49:0x0096  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x00d8  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x0119  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object k(boolean r27) {
            /*
                Method dump skipped, instruction units count: 1787
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.JSONPath.l.k(boolean):java.lang.Object");
        }

        public double l(long j) {
            int i = this.f2389c - 1;
            i();
            while (true) {
                char c2 = this.f2390d;
                if (c2 < '0' || c2 > '9') {
                    break;
                }
                i();
            }
            return Double.parseDouble(this.f2388b.substring(i, this.f2389c - 1)) + j;
        }

        public long m() {
            int i = this.f2389c - 1;
            char c2 = this.f2390d;
            if (c2 == '+' || c2 == '-') {
                i();
            }
            while (true) {
                char c3 = this.f2390d;
                if (c3 < '0' || c3 > '9') {
                    break;
                }
                i();
            }
            return Long.parseLong(this.f2388b.substring(i, this.f2389c - 1));
        }

        public String n() {
            s();
            char c2 = this.f2390d;
            if (c2 != '\\' && !Character.isJavaIdentifierStart(c2)) {
                throw new JSONPathException("illeal jsonpath syntax. " + this.f2388b);
            }
            StringBuilder sb = new StringBuilder();
            while (!h()) {
                char c3 = this.f2390d;
                if (c3 == '\\') {
                    i();
                    sb.append(this.f2390d);
                    if (h()) {
                        return sb.toString();
                    }
                    i();
                } else {
                    if (!Character.isJavaIdentifierPart(c3)) {
                        break;
                    }
                    sb.append(this.f2390d);
                    i();
                }
            }
            if (h() && Character.isJavaIdentifierPart(this.f2390d)) {
                sb.append(this.f2390d);
            }
            return sb.toString();
        }

        public Operator o() {
            Operator operator;
            char c2 = this.f2390d;
            if (c2 == '=') {
                i();
                char c3 = this.f2390d;
                if (c3 == '~') {
                    i();
                    operator = Operator.REG_MATCH;
                } else if (c3 == '=') {
                    i();
                    operator = Operator.EQ;
                } else {
                    operator = Operator.EQ;
                }
            } else if (c2 == '!') {
                i();
                a('=');
                operator = Operator.NE;
            } else if (c2 == '<') {
                i();
                if (this.f2390d == '=') {
                    i();
                    operator = Operator.LE;
                } else {
                    operator = Operator.LT;
                }
            } else if (c2 == '>') {
                i();
                if (this.f2390d == '=') {
                    i();
                    operator = Operator.GE;
                } else {
                    operator = Operator.GT;
                }
            } else {
                operator = null;
            }
            if (operator != null) {
                return operator;
            }
            String strN = n();
            if ("not".equalsIgnoreCase(strN)) {
                s();
                String strN2 = n();
                if ("like".equalsIgnoreCase(strN2)) {
                    return Operator.NOT_LIKE;
                }
                if ("rlike".equalsIgnoreCase(strN2)) {
                    return Operator.NOT_RLIKE;
                }
                if ("in".equalsIgnoreCase(strN2)) {
                    return Operator.NOT_IN;
                }
                if ("between".equalsIgnoreCase(strN2)) {
                    return Operator.NOT_BETWEEN;
                }
                throw new UnsupportedOperationException();
            }
            if ("nin".equalsIgnoreCase(strN)) {
                return Operator.NOT_IN;
            }
            if ("like".equalsIgnoreCase(strN)) {
                return Operator.LIKE;
            }
            if ("rlike".equalsIgnoreCase(strN)) {
                return Operator.RLIKE;
            }
            if ("in".equalsIgnoreCase(strN)) {
                return Operator.IN;
            }
            if ("between".equalsIgnoreCase(strN)) {
                return Operator.BETWEEN;
            }
            throw new UnsupportedOperationException();
        }

        public a0 p() {
            boolean z = true;
            if (this.f2391e == 0 && this.f2388b.length() == 1) {
                if (g(this.f2390d)) {
                    return new b(this.f2390d - '0');
                }
                char c2 = this.f2390d;
                if ((c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z')) {
                    return new v(Character.toString(c2), false);
                }
            }
            while (!h()) {
                s();
                char c3 = this.f2390d;
                if (c3 != '$') {
                    if (c3 != '.' && c3 != '/') {
                        if (c3 == '[') {
                            return j(true);
                        }
                        if (this.f2391e == 0) {
                            return new v(n(), false);
                        }
                        if (c3 == '?') {
                            return new f((d) k(false));
                        }
                        throw new JSONPathException("not support jsonpath : " + this.f2388b);
                    }
                    i();
                    if (c3 == '.' && this.f2390d == '.') {
                        i();
                        int length = this.f2388b.length();
                        int i = this.f2389c;
                        if (length > i + 3 && this.f2390d == '[' && this.f2388b.charAt(i) == '*' && this.f2388b.charAt(this.f2389c + 1) == ']' && this.f2388b.charAt(this.f2389c + 2) == '.') {
                            i();
                            i();
                            i();
                            i();
                        }
                    } else {
                        z = false;
                    }
                    char c4 = this.f2390d;
                    if (c4 == '*') {
                        if (!h()) {
                            i();
                        }
                        return z ? g0.f2381b : g0.f2380a;
                    }
                    if (g(c4)) {
                        return j(false);
                    }
                    String strN = n();
                    if (this.f2390d != '(') {
                        return new v(strN, z);
                    }
                    i();
                    if (this.f2390d != ')') {
                        throw new JSONPathException("not support jsonpath : " + this.f2388b);
                    }
                    if (!h()) {
                        i();
                    }
                    if ("size".equals(strN) || "length".equals(strN)) {
                        return b0.f2370a;
                    }
                    if ("max".equals(strN)) {
                        return o.f2395a;
                    }
                    if ("min".equals(strN)) {
                        return p.f2396a;
                    }
                    if ("keySet".equals(strN)) {
                        return m.f2393a;
                    }
                    if (NotificationCompat.MessagingStyle.Message.KEY_DATA_MIME_TYPE.equals(strN)) {
                        return e0.f2376a;
                    }
                    if ("floor".equals(strN)) {
                        return g.f2379a;
                    }
                    throw new JSONPathException("not support jsonpath : " + this.f2388b);
                }
                i();
                s();
                if (this.f2390d == '?') {
                    return new f((d) k(false));
                }
            }
            return null;
        }

        public String q() {
            char c2 = this.f2390d;
            i();
            int i = this.f2389c - 1;
            while (this.f2390d != c2 && !h()) {
                i();
            }
            String strSubstring = this.f2388b.substring(i, h() ? this.f2389c : this.f2389c - 1);
            a(c2);
            return strSubstring;
        }

        public Object r() {
            s();
            if (g(this.f2390d)) {
                return Long.valueOf(m());
            }
            char c2 = this.f2390d;
            if (c2 == '\"' || c2 == '\'') {
                return q();
            }
            if (c2 != 'n') {
                throw new UnsupportedOperationException();
            }
            if ("null".equals(n())) {
                return null;
            }
            throw new JSONPathException(this.f2388b);
        }

        public final void s() {
            while (true) {
                char c2 = this.f2390d;
                if (c2 > ' ') {
                    return;
                }
                if (c2 != ' ' && c2 != '\r' && c2 != '\n' && c2 != '\t' && c2 != '\f' && c2 != '\b') {
                    return;
                } else {
                    i();
                }
            }
        }
    }

    public static class m implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final m f2393a = new m();

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.f(obj2);
        }
    }

    public static class n extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final String f2394f;
        public final String g;
        public final String[] h;
        public final int i;
        public final boolean j;

        public n(String str, boolean z, String str2, String str3, String[] strArr, boolean z2) {
            super(str, z);
            this.f2394f = str2;
            this.g = str3;
            this.h = strArr;
            this.j = z2;
            int length = str2 != null ? str2.length() + 0 : 0;
            length = str3 != null ? length + str3.length() : length;
            if (strArr != null) {
                for (String str4 : strArr) {
                    length += str4.length();
                }
            }
            this.i = length;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            int length;
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null) {
                return false;
            }
            String string = objB.toString();
            if (string.length() < this.i) {
                return this.j;
            }
            String str = this.f2394f;
            if (str == null) {
                length = 0;
            } else {
                if (!string.startsWith(str)) {
                    return this.j;
                }
                length = this.f2394f.length() + 0;
            }
            String[] strArr = this.h;
            if (strArr != null) {
                for (String str2 : strArr) {
                    int iIndexOf = string.indexOf(str2, length);
                    if (iIndexOf == -1) {
                        return this.j;
                    }
                    length = iIndexOf + str2.length();
                }
            }
            String str3 = this.g;
            return (str3 == null || string.endsWith(str3)) ? !this.j : this.j;
        }
    }

    public static class o implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final o f2395a = new o();

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            if (!(obj2 instanceof Collection)) {
                throw new UnsupportedOperationException();
            }
            Object obj3 = null;
            for (Object obj4 : (Collection) obj2) {
                if (obj4 != null && (obj3 == null || JSONPath.a(obj3, obj4) < 0)) {
                    obj3 = obj4;
                }
            }
            return obj3;
        }
    }

    public static class p implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final p f2396a = new p();

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            if (!(obj2 instanceof Collection)) {
                throw new UnsupportedOperationException();
            }
            Object obj3 = null;
            for (Object obj4 : (Collection) obj2) {
                if (obj4 != null && (obj3 == null || JSONPath.a(obj3, obj4) > 0)) {
                    obj3 = obj4;
                }
            }
            return obj3;
        }
    }

    public static class q implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f2397a;

        public q(int[] iArr) {
            this.f2397a = iArr;
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            JSONArray jSONArray = new JSONArray(this.f2397a.length);
            int i = 0;
            while (true) {
                int[] iArr = this.f2397a;
                if (i >= iArr.length) {
                    return jSONArray;
                }
                jSONArray.add(jSONPath.h(obj2, iArr[i]));
                i++;
            }
        }
    }

    public static class r implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String[] f2398a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long[] f2399b;

        public r(String[] strArr) {
            this.f2398a = strArr;
            this.f2399b = new long[strArr.length];
            int i = 0;
            while (true) {
                long[] jArr = this.f2399b;
                if (i >= jArr.length) {
                    return;
                }
                jArr[i] = b.a.a.n.l.I(strArr[i]);
                i++;
            }
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            ArrayList arrayList = new ArrayList(this.f2398a.length);
            int i = 0;
            while (true) {
                String[] strArr = this.f2398a;
                if (i >= strArr.length) {
                    return arrayList;
                }
                arrayList.add(jSONPath.j(obj2, strArr[i], this.f2399b[i]));
                i++;
            }
        }
    }

    public static class s extends u {
        public s(String str, boolean z) {
            super(str, z);
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            return jSONPath.j(obj3, this.f2401b, this.f2402c) != null;
        }
    }

    public static class t extends u {
        public t(String str, boolean z) {
            super(str, z);
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            return b(jSONPath, obj, obj3) == null;
        }
    }

    public static abstract class u implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static long f2400a = b.a.a.n.l.I(NotificationCompat.MessagingStyle.Message.KEY_DATA_MIME_TYPE);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f2401b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final long f2402c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f2403d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public a0 f2404e;

        public u(String str, boolean z) {
            this.f2401b = str;
            long jI = b.a.a.n.l.I(str);
            this.f2402c = jI;
            this.f2403d = z;
            if (z) {
                if (jI == f2400a) {
                    this.f2404e = e0.f2376a;
                } else {
                    if (jI == 5614464919154503228L) {
                        this.f2404e = b0.f2370a;
                        return;
                    }
                    throw new JSONPathException("unsupported funciton : " + str);
                }
            }
        }

        public Object b(JSONPath jSONPath, Object obj, Object obj2) {
            a0 a0Var = this.f2404e;
            return a0Var != null ? a0Var.a(jSONPath, obj, obj2) : jSONPath.j(obj2, this.f2401b, this.f2402c);
        }
    }

    public static class v implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f2405a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f2406b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f2407c;

        public v(String str, boolean z) {
            this.f2405a = str;
            this.f2406b = b.a.a.n.l.I(str);
            this.f2407c = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            if (!this.f2407c) {
                return jSONPath.j(obj2, this.f2405a, this.f2406b);
            }
            ArrayList arrayList = new ArrayList();
            jSONPath.d(obj2, this.f2405a, arrayList);
            return arrayList;
        }
    }

    public static class w implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2408a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f2409b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f2410c;

        public w(int i, int i2, int i3) {
            this.f2408a = i;
            this.f2409b = i2;
            this.f2410c = i3;
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            int iIntValue = b0.f2370a.a(jSONPath, obj, obj2).intValue();
            int i = this.f2408a;
            if (i < 0) {
                i += iIntValue;
            }
            int i2 = this.f2409b;
            if (i2 < 0) {
                i2 += iIntValue;
            }
            int i3 = ((i2 - i) / this.f2410c) + 1;
            if (i3 == -1) {
                return null;
            }
            ArrayList arrayList = new ArrayList(i3);
            while (i <= i2 && i < iIntValue) {
                arrayList.add(jSONPath.h(obj2, i));
                i += this.f2410c;
            }
            return arrayList;
        }
    }

    public static class x extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final a0 f2411f;
        public final Operator g;

        public x(String str, boolean z, a0 a0Var, Operator operator) {
            super(str, z);
            this.f2411f = a0Var;
            this.g = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null || !(objB instanceof Number)) {
                return false;
            }
            Object objA = this.f2411f.a(jSONPath, obj, obj);
            if ((objA instanceof Integer) || (objA instanceof Long) || (objA instanceof Short) || (objA instanceof Byte)) {
                long jC0 = b.a.a.n.l.C0((Number) objA);
                if ((objB instanceof Integer) || (objB instanceof Long) || (objB instanceof Short) || (objB instanceof Byte)) {
                    long jC02 = b.a.a.n.l.C0((Number) objB);
                    switch (a.f2368a[this.g.ordinal()]) {
                        case 1:
                            return jC02 == jC0;
                        case 2:
                            return jC02 != jC0;
                        case 3:
                            return jC02 >= jC0;
                        case 4:
                            return jC02 > jC0;
                        case 5:
                            return jC02 <= jC0;
                        case 6:
                            return jC02 < jC0;
                    }
                }
                if (objB instanceof BigDecimal) {
                    int iCompareTo = BigDecimal.valueOf(jC0).compareTo((BigDecimal) objB);
                    switch (a.f2368a[this.g.ordinal()]) {
                        case 1:
                            return iCompareTo == 0;
                        case 2:
                            return iCompareTo != 0;
                        case 3:
                            return iCompareTo <= 0;
                        case 4:
                            return iCompareTo < 0;
                        case 5:
                            return iCompareTo >= 0;
                        case 6:
                            return iCompareTo > 0;
                        default:
                            return false;
                    }
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    public static class y extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Pattern f2412f;
        public final Operator g;

        public y(String str, boolean z, Pattern pattern, Operator operator) {
            super(str, z);
            this.f2412f = pattern;
            this.g = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null) {
                return false;
            }
            return this.f2412f.matcher(objB.toString()).matches();
        }
    }

    public static class z extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Pattern f2413f;
        public final boolean g;

        public z(String str, boolean z, String str2, boolean z2) {
            super(str, z);
            this.f2413f = Pattern.compile(str2);
            this.g = z2;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null) {
                return false;
            }
            boolean zMatches = this.f2413f.matcher(objB.toString()).matches();
            return this.g ? !zMatches : zMatches;
        }
    }

    public JSONPath(String str) {
        this(str, a1.g(), b.a.a.j.h.s());
    }

    public static int a(Object obj, Object obj2) {
        Object d2;
        Object f2;
        if (obj.getClass() == obj2.getClass()) {
            return ((Comparable) obj).compareTo(obj2);
        }
        Class<?> cls = obj.getClass();
        Class<?> cls2 = obj2.getClass();
        if (cls == BigDecimal.class) {
            if (cls2 == Integer.class) {
                f2 = new BigDecimal(((Integer) obj2).intValue());
            } else if (cls2 == Long.class) {
                f2 = new BigDecimal(((Long) obj2).longValue());
            } else if (cls2 == Float.class) {
                f2 = new BigDecimal(((Float) obj2).floatValue());
            } else if (cls2 == Double.class) {
                f2 = new BigDecimal(((Double) obj2).doubleValue());
            }
            obj2 = f2;
        } else if (cls == Long.class) {
            if (cls2 == Integer.class) {
                f2 = new Long(((Integer) obj2).intValue());
                obj2 = f2;
            } else {
                if (cls2 == BigDecimal.class) {
                    d2 = new BigDecimal(((Long) obj).longValue());
                } else if (cls2 == Float.class) {
                    d2 = new Float(((Long) obj).longValue());
                } else if (cls2 == Double.class) {
                    d2 = new Double(((Long) obj).longValue());
                }
                obj = d2;
            }
        } else if (cls == Integer.class) {
            if (cls2 == Long.class) {
                d2 = new Long(((Integer) obj).intValue());
            } else if (cls2 == BigDecimal.class) {
                d2 = new BigDecimal(((Integer) obj).intValue());
            } else if (cls2 == Float.class) {
                d2 = new Float(((Integer) obj).intValue());
            } else if (cls2 == Double.class) {
                d2 = new Double(((Integer) obj).intValue());
            }
            obj = d2;
        } else if (cls == Double.class) {
            if (cls2 == Integer.class) {
                f2 = new Double(((Integer) obj2).intValue());
            } else if (cls2 == Long.class) {
                f2 = new Double(((Long) obj2).longValue());
            } else if (cls2 == Float.class) {
                f2 = new Double(((Float) obj2).floatValue());
            }
            obj2 = f2;
        } else if (cls == Float.class) {
            if (cls2 == Integer.class) {
                f2 = new Float(((Integer) obj2).intValue());
            } else if (cls2 == Long.class) {
                f2 = new Float(((Long) obj2).longValue());
            } else if (cls2 == Double.class) {
                d2 = new Double(((Float) obj).floatValue());
                obj = d2;
            }
            obj2 = f2;
        }
        return ((Comparable) obj).compareTo(obj2);
    }

    public static JSONPath b(String str) {
        if (str == null) {
            throw new JSONPathException("jsonpath can not be null");
        }
        JSONPath jSONPath = f2361a.get(str);
        if (jSONPath != null) {
            return jSONPath;
        }
        JSONPath jSONPath2 = new JSONPath(str);
        if (f2361a.size() >= 1024) {
            return jSONPath2;
        }
        f2361a.putIfAbsent(str, jSONPath2);
        return f2361a.get(str);
    }

    public static boolean m(Class<?> cls) {
        return cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class;
    }

    public void c(Object obj, List<Object> list) {
        Collection collectionV;
        Class<?> cls = obj.getClass();
        j0 j0VarI = i(cls);
        if (j0VarI != null) {
            try {
                collectionV = j0VarI.v(obj);
            } catch (Exception e2) {
                throw new JSONPathException("jsonpath error, path " + this.f2362b, e2);
            }
        } else {
            collectionV = obj instanceof Map ? ((Map) obj).values() : obj instanceof Collection ? (Collection) obj : null;
        }
        if (collectionV == null) {
            throw new UnsupportedOperationException(cls.getName());
        }
        for (Object obj2 : collectionV) {
            if (obj2 == null || b.a.a.j.h.v(obj2.getClass())) {
                list.add(obj2);
            } else {
                c(obj2, list);
            }
        }
    }

    public void d(Object obj, String str, List<Object> list) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object value = entry.getValue();
                if (str.equals(entry.getKey())) {
                    if (value instanceof Collection) {
                        list.addAll((Collection) value);
                    } else {
                        list.add(value);
                    }
                } else if (value != null && !b.a.a.j.h.v(value.getClass())) {
                    d(value, str, list);
                }
            }
            return;
        }
        if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                if (!b.a.a.j.h.v(obj2.getClass())) {
                    d(obj2, str, list);
                }
            }
            return;
        }
        j0 j0VarI = i(obj.getClass());
        if (j0VarI == null) {
            if (obj instanceof List) {
                List list2 = (List) obj;
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    d(list2.get(i2), str, list);
                }
                return;
            }
            return;
        }
        try {
            b.a.a.k.a0 a0VarT = j0VarI.t(str);
            if (a0VarT == null) {
                Iterator<Object> it = j0VarI.v(obj).iterator();
                while (it.hasNext()) {
                    d(it.next(), str, list);
                }
                return;
            }
            try {
                list.add(a0VarT.c(obj));
            } catch (IllegalAccessException e2) {
                throw new JSONException("getFieldValue error." + str, e2);
            } catch (InvocationTargetException e3) {
                throw new JSONException("getFieldValue error." + str, e3);
            }
        } catch (Exception e4) {
            throw new JSONPathException("jsonpath error, path " + this.f2362b + ", segement " + str, e4);
        }
    }

    public Object e(Object obj) {
        if (obj == null) {
            return null;
        }
        l();
        int i2 = 0;
        Object objA = obj;
        while (true) {
            a0[] a0VarArr = this.f2363c;
            if (i2 >= a0VarArr.length) {
                return objA;
            }
            objA = a0VarArr[i2].a(this, obj, objA);
            i2++;
        }
    }

    public Set<?> f(Object obj) {
        j0 j0VarI;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return ((Map) obj).keySet();
        }
        if ((obj instanceof Collection) || (obj instanceof Object[]) || obj.getClass().isArray() || (j0VarI = i(obj.getClass())) == null) {
            return null;
        }
        try {
            return j0VarI.r(obj);
        } catch (Exception e2) {
            throw new JSONPathException("evalKeySet error : " + this.f2362b, e2);
        }
    }

    public int g(Object obj) {
        if (obj == null) {
            return -1;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).size();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj);
        }
        if (obj instanceof Map) {
            int i2 = 0;
            Iterator it = ((Map) obj).values().iterator();
            while (it.hasNext()) {
                if (it.next() != null) {
                    i2++;
                }
            }
            return i2;
        }
        j0 j0VarI = i(obj.getClass());
        if (j0VarI == null) {
            return -1;
        }
        try {
            return j0VarI.x(obj);
        } catch (Exception e2) {
            throw new JSONPathException("evalSize error : " + this.f2362b, e2);
        }
    }

    public Object h(Object obj, int i2) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (i2 >= 0) {
                if (i2 < list.size()) {
                    return list.get(i2);
                }
                return null;
            }
            if (Math.abs(i2) <= list.size()) {
                return list.get(list.size() + i2);
            }
            return null;
        }
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            if (i2 >= 0) {
                if (i2 < length) {
                    return Array.get(obj, i2);
                }
                return null;
            }
            if (Math.abs(i2) <= length) {
                return Array.get(obj, length + i2);
            }
            return null;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Object obj2 = map.get(Integer.valueOf(i2));
            return obj2 == null ? map.get(Integer.toString(i2)) : obj2;
        }
        if (!(obj instanceof Collection)) {
            if (i2 == 0) {
                return obj;
            }
            throw new UnsupportedOperationException();
        }
        int i3 = 0;
        for (Object obj3 : (Collection) obj) {
            if (i3 == i2) {
                return obj3;
            }
            i3++;
        }
        return null;
    }

    public j0 i(Class<?> cls) {
        t0 t0VarH = this.f2365e.h(cls);
        if (t0VarH instanceof j0) {
            return (j0) t0VarH;
        }
        return null;
    }

    public Object j(Object obj, String str, long j2) {
        JSONArray jSONArray = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            try {
                obj = b.a.a.a.parseObject((String) obj);
            } catch (Exception unused) {
            }
        }
        Object obj2 = obj;
        if (obj2 instanceof Map) {
            Map map = (Map) obj2;
            Object obj3 = map.get(str);
            return obj3 == null ? (5614464919154503228L == j2 || -1580386065683472715L == j2) ? Integer.valueOf(map.size()) : obj3 : obj3;
        }
        j0 j0VarI = i(obj2.getClass());
        if (j0VarI != null) {
            try {
                return j0VarI.u(obj2, str, j2, false);
            } catch (Exception e2) {
                throw new JSONPathException("jsonpath error, path " + this.f2362b + ", segement " + str, e2);
            }
        }
        int i2 = 0;
        if (obj2 instanceof List) {
            List list = (List) obj2;
            if (5614464919154503228L == j2 || -1580386065683472715L == j2) {
                return Integer.valueOf(list.size());
            }
            while (i2 < list.size()) {
                Object obj4 = list.get(i2);
                if (obj4 == list) {
                    if (jSONArray == null) {
                        jSONArray = new JSONArray(list.size());
                    }
                    jSONArray.add(obj4);
                } else {
                    Object objJ = j(obj4, str, j2);
                    if (objJ instanceof Collection) {
                        Collection collection = (Collection) objJ;
                        if (jSONArray == null) {
                            jSONArray = new JSONArray(list.size());
                        }
                        jSONArray.addAll(collection);
                    } else if (objJ != null) {
                        if (jSONArray == null) {
                            jSONArray = new JSONArray(list.size());
                        }
                        jSONArray.add(objJ);
                    }
                }
                i2++;
            }
            return jSONArray == null ? Collections.emptyList() : jSONArray;
        }
        if (obj2 instanceof Object[]) {
            Object[] objArr = (Object[]) obj2;
            if (5614464919154503228L == j2 || -1580386065683472715L == j2) {
                return Integer.valueOf(objArr.length);
            }
            JSONArray jSONArray2 = new JSONArray(objArr.length);
            while (i2 < objArr.length) {
                Object[] objArr2 = objArr[i2];
                if (objArr2 == objArr) {
                    jSONArray2.add(objArr2);
                } else {
                    Object objJ2 = j(objArr2, str, j2);
                    if (objJ2 instanceof Collection) {
                        jSONArray2.addAll((Collection) objJ2);
                    } else if (objJ2 != null) {
                        jSONArray2.add(objJ2);
                    }
                }
                i2++;
            }
            return jSONArray2;
        }
        if (obj2 instanceof Enum) {
            Enum r8 = (Enum) obj2;
            if (-4270347329889690746L == j2) {
                return r8.name();
            }
            if (-1014497654951707614L == j2) {
                return Integer.valueOf(r8.ordinal());
            }
        }
        if (obj2 instanceof Calendar) {
            Calendar calendar = (Calendar) obj2;
            if (8963398325558730460L == j2) {
                return Integer.valueOf(calendar.get(1));
            }
            if (-811277319855450459L == j2) {
                return Integer.valueOf(calendar.get(2));
            }
            if (-3851359326990528739L == j2) {
                return Integer.valueOf(calendar.get(5));
            }
            if (4647432019745535567L == j2) {
                return Integer.valueOf(calendar.get(11));
            }
            if (6607618197526598121L == j2) {
                return Integer.valueOf(calendar.get(12));
            }
            if (-6586085717218287427L == j2) {
                return Integer.valueOf(calendar.get(13));
            }
        }
        return null;
    }

    public Collection<Object> k(Object obj) {
        if (obj == null) {
            return null;
        }
        j0 j0VarI = i(obj.getClass());
        if (j0VarI == null) {
            if (obj instanceof Map) {
                return ((Map) obj).values();
            }
            if (obj instanceof Collection) {
                return (Collection) obj;
            }
            throw new UnsupportedOperationException();
        }
        try {
            return j0VarI.v(obj);
        } catch (Exception e2) {
            throw new JSONPathException("jsonpath error, path " + this.f2362b, e2);
        }
    }

    public void l() {
        if (this.f2363c != null) {
            return;
        }
        if ("*".equals(this.f2362b)) {
            this.f2363c = new a0[]{g0.f2380a};
            return;
        }
        l lVar = new l(this.f2362b);
        this.f2363c = lVar.d();
        this.f2364d = lVar.f2392f;
    }

    public boolean n() {
        try {
            l();
            int i2 = 0;
            while (true) {
                a0[] a0VarArr = this.f2363c;
                if (i2 >= a0VarArr.length) {
                    return true;
                }
                Class<?> cls = a0VarArr[i2].getClass();
                if (cls != b.class && cls != v.class) {
                    return false;
                }
                i2++;
            }
        } catch (JSONPathException unused) {
            return false;
        }
    }

    @Override // b.a.a.b
    public String toJSONString() {
        return b.a.a.a.toJSONString(this.f2362b);
    }

    public JSONPath(String str, a1 a1Var, b.a.a.j.h hVar) {
        if (str == null || str.length() == 0) {
            throw new JSONPathException("json-path can not be null or empty");
        }
        this.f2362b = str;
        this.f2365e = a1Var;
        this.f2366f = hVar;
    }
}
