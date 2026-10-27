package com.alibaba.fastjson;

import com.bydux.api.common.tools.util.ListUtils;
import h2.a1;
import h2.j0;
import h2.t0;
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
public class JSONPath implements c2.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static ConcurrentMap<String, JSONPath> f2525d = new ConcurrentHashMap(128, 0.75f, 1);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f2526e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a0[] f2527f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2528g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a1 f2529h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f2.h f2530i;

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
        public static final /* synthetic */ int[] f2531a;

        static {
            int[] iArr = new int[Operator.values().length];
            f2531a = iArr;
            try {
                iArr[Operator.EQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2531a[Operator.NE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2531a[Operator.GE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2531a[Operator.GT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2531a[Operator.LE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2531a[Operator.LT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public interface a0 {
        Object a(JSONPath jSONPath, Object obj, Object obj2);
    }

    public static class b implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2532a;

        public b(int i8) {
            this.f2532a = i8;
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.h(obj2, this.f2532a);
        }
    }

    public static class b0 implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b0 f2533a = new b0();

        @Override // com.alibaba.fastjson.JSONPath.a0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Integer a(JSONPath jSONPath, Object obj, Object obj2) {
            return Integer.valueOf(jSONPath.g(obj2));
        }
    }

    public static class c extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final double f2534f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final Operator f2535g;

        public c(String str, boolean z7, double d8, Operator operator) {
            super(str, z7);
            this.f2534f = d8;
            this.f2535g = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null || !(objB instanceof Number)) {
                return false;
            }
            double dDoubleValue = ((Number) objB).doubleValue();
            switch (a.f2531a[this.f2535g.ordinal()]) {
                case 1:
                    if (dDoubleValue == this.f2534f) {
                    }
                    break;
                case 2:
                    if (dDoubleValue != this.f2534f) {
                    }
                    break;
                case 3:
                    if (dDoubleValue >= this.f2534f) {
                    }
                    break;
                case 4:
                    if (dDoubleValue > this.f2534f) {
                    }
                    break;
                case 5:
                    if (dDoubleValue <= this.f2534f) {
                    }
                    break;
                case 6:
                    if (dDoubleValue < this.f2534f) {
                    }
                    break;
            }
            return false;
        }
    }

    public static class c0 extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final String[] f2536f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final boolean f2537g;

        public c0(String str, boolean z7, String[] strArr, boolean z8) {
            super(str, z7);
            this.f2536f = strArr;
            this.f2537g = z8;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            for (String str : this.f2536f) {
                if (str == objB) {
                    return !this.f2537g;
                }
                if (str != null && str.equals(objB)) {
                    return !this.f2537g;
                }
            }
            return this.f2537g;
        }
    }

    public interface d {
        boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3);
    }

    public static class d0 extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final String f2538f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final Operator f2539g;

        public d0(String str, boolean z7, String str2, Operator operator) {
            super(str, z7);
            this.f2538f = str2;
            this.f2539g = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            Operator operator = this.f2539g;
            if (operator == Operator.EQ) {
                return this.f2538f.equals(objB);
            }
            if (operator == Operator.NE) {
                return !this.f2538f.equals(objB);
            }
            if (objB == null) {
                return false;
            }
            int iCompareTo = this.f2538f.compareTo(objB.toString());
            Operator operator2 = this.f2539g;
            return operator2 == Operator.GE ? iCompareTo <= 0 : operator2 == Operator.GT ? iCompareTo < 0 : operator2 == Operator.LE ? iCompareTo >= 0 : operator2 == Operator.LT && iCompareTo > 0;
        }
    }

    public static class e implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2540a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List<d> f2541b;

        public e(d dVar, d dVar2, boolean z7) {
            ArrayList arrayList = new ArrayList(2);
            this.f2541b = arrayList;
            arrayList.add(dVar);
            this.f2541b.add(dVar2);
            this.f2540a = z7;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            if (this.f2540a) {
                Iterator<d> it = this.f2541b.iterator();
                while (it.hasNext()) {
                    if (!it.next().a(jSONPath, obj, obj2, obj3)) {
                        return false;
                    }
                }
                return true;
            }
            Iterator<d> it2 = this.f2541b.iterator();
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
        public static final e0 f2542a = new e0();

        @Override // com.alibaba.fastjson.JSONPath.a0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public String a(JSONPath jSONPath, Object obj, Object obj2) {
            return obj2 == null ? "null" : obj2 instanceof Collection ? "array" : obj2 instanceof Number ? "number" : obj2 instanceof Boolean ? "boolean" : ((obj2 instanceof String) || (obj2 instanceof UUID) || (obj2 instanceof Enum)) ? "string" : "object";
        }
    }

    public static class f implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d f2543a;

        public f(d dVar) {
            this.f2543a = dVar;
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            if (obj2 == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            if (!(obj2 instanceof Iterable)) {
                if (this.f2543a.a(jSONPath, obj, obj2, obj2)) {
                    return obj2;
                }
                return null;
            }
            for (Object obj3 : (Iterable) obj2) {
                if (this.f2543a.a(jSONPath, obj, obj2, obj3)) {
                    jSONArray.add(obj3);
                }
            }
            return jSONArray;
        }
    }

    public static class f0 extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Object f2544f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f2545g;

        public f0(String str, boolean z7, Object obj, boolean z8) {
            super(str, z7);
            this.f2545g = true;
            if (obj == null) {
                throw new IllegalArgumentException("value is null");
            }
            this.f2544f = obj;
            this.f2545g = z8;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            boolean zEquals = this.f2544f.equals(b(jSONPath, obj, obj3));
            return !this.f2545g ? !zEquals : zEquals;
        }
    }

    public static class g implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final g f2546a = new g();

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
            for (int i8 = 0; i8 < jSONArray.size(); i8++) {
                Object obj3 = jSONArray.get(i8);
                Object objB = b(obj3);
                if (objB != obj3) {
                    jSONArray.set(i8, objB);
                }
            }
            return jSONArray;
        }
    }

    public static class g0 implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final g0 f2547a = new g0(false);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final g0 f2548b = new g0(true);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2549c;

        public g0(boolean z7) {
            this.f2549c = z7;
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            if (!this.f2549c) {
                return jSONPath.k(obj2);
            }
            ArrayList arrayList = new ArrayList();
            jSONPath.c(obj2, arrayList);
            return arrayList;
        }
    }

    public static class h extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final long f2550f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final long f2551g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final boolean f2552h;

        public h(String str, boolean z7, long j8, long j9, boolean z8) {
            super(str, z7);
            this.f2550f = j8;
            this.f2551g = j9;
            this.f2552h = z8;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null) {
                return false;
            }
            if (objB instanceof Number) {
                long jC0 = l2.l.C0((Number) objB);
                if (jC0 >= this.f2550f && jC0 <= this.f2551g) {
                    return !this.f2552h;
                }
            }
            return this.f2552h;
        }
    }

    public static class i extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final long[] f2553f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final boolean f2554g;

        public i(String str, boolean z7, long[] jArr, boolean z8) {
            super(str, z7);
            this.f2553f = jArr;
            this.f2554g = z8;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null) {
                return false;
            }
            if (objB instanceof Number) {
                long jC0 = l2.l.C0((Number) objB);
                for (long j8 : this.f2553f) {
                    if (j8 == jC0) {
                        return !this.f2554g;
                    }
                }
            }
            return this.f2554g;
        }
    }

    public static class j extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Long[] f2555f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final boolean f2556g;

        public j(String str, boolean z7, Long[] lArr, boolean z8) {
            super(str, z7);
            this.f2555f = lArr;
            this.f2556g = z8;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            int i8 = 0;
            if (objB == null) {
                Long[] lArr = this.f2555f;
                int length = lArr.length;
                while (i8 < length) {
                    if (lArr[i8] == null) {
                        return !this.f2556g;
                    }
                    i8++;
                }
                return this.f2556g;
            }
            if (objB instanceof Number) {
                long jC0 = l2.l.C0((Number) objB);
                Long[] lArr2 = this.f2555f;
                int length2 = lArr2.length;
                while (i8 < length2) {
                    Long l8 = lArr2[i8];
                    if (l8 != null && l8.longValue() == jC0) {
                        return !this.f2556g;
                    }
                    i8++;
                }
            }
            return this.f2556g;
        }
    }

    public static class k extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final long f2557f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final Operator f2558g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public BigDecimal f2559h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Float f2560i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public Double f2561j;

        public k(String str, boolean z7, long j8, Operator operator) {
            super(str, z7);
            this.f2557f = j8;
            this.f2558g = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null || !(objB instanceof Number)) {
                return false;
            }
            if (objB instanceof BigDecimal) {
                if (this.f2559h == null) {
                    this.f2559h = BigDecimal.valueOf(this.f2557f);
                }
                int iCompareTo = this.f2559h.compareTo((BigDecimal) objB);
                switch (a.f2531a[this.f2558g.ordinal()]) {
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
                if (this.f2560i == null) {
                    this.f2560i = Float.valueOf(this.f2557f);
                }
                int iCompareTo2 = this.f2560i.compareTo((Float) objB);
                switch (a.f2531a[this.f2558g.ordinal()]) {
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
                long jC0 = l2.l.C0((Number) objB);
                switch (a.f2531a[this.f2558g.ordinal()]) {
                    case 1:
                        if (jC0 == this.f2557f) {
                        }
                        break;
                    case 2:
                        if (jC0 != this.f2557f) {
                        }
                        break;
                    case 3:
                        if (jC0 >= this.f2557f) {
                        }
                        break;
                    case 4:
                        if (jC0 > this.f2557f) {
                        }
                        break;
                    case 5:
                        if (jC0 <= this.f2557f) {
                        }
                        break;
                    case 6:
                        if (jC0 < this.f2557f) {
                        }
                        break;
                }
                return false;
            }
            if (this.f2561j == null) {
                this.f2561j = Double.valueOf(this.f2557f);
            }
            int iCompareTo3 = this.f2561j.compareTo((Double) objB);
            switch (a.f2531a[this.f2558g.ordinal()]) {
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
        public static final Pattern f2562a = Pattern.compile("'\\s*,\\s*'");

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f2563b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2564c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public char f2565d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2566e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f2567f;

        public l(String str) {
            this.f2563b = str;
            i();
        }

        public static boolean g(char c8) {
            return c8 == '-' || c8 == '+' || (c8 >= '0' && c8 <= '9');
        }

        public void a(char c8) {
            if (this.f2565d == ' ') {
                i();
            }
            if (this.f2565d == c8) {
                if (h()) {
                    return;
                }
                i();
            } else {
                throw new JSONPathException("expect '" + c8 + ", but '" + this.f2565d + "'");
            }
        }

        public a0 c(String str) {
            int length = str.length();
            char cCharAt = str.charAt(0);
            int i8 = length - 1;
            char cCharAt2 = str.charAt(i8);
            int iIndexOf = str.indexOf(44);
            if (str.length() > 2 && cCharAt == '\'' && cCharAt2 == '\'') {
                String strSubstring = str.substring(1, i8);
                return (iIndexOf == -1 || !f2562a.matcher(str).find()) ? new v(strSubstring, false) : new r(strSubstring.split("'\\s*,\\s*'"));
            }
            int iIndexOf2 = str.indexOf(58);
            if (iIndexOf == -1 && iIndexOf2 == -1) {
                if (l2.l.u0(str)) {
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
                for (int i9 = 0; i9 < strArrSplit.length; i9++) {
                    iArr[i9] = Integer.parseInt(strArrSplit[i9]);
                }
                return new q(iArr);
            }
            if (iIndexOf2 == -1) {
                throw new UnsupportedOperationException();
            }
            String[] strArrSplit2 = str.split(":");
            int length2 = strArrSplit2.length;
            int[] iArr2 = new int[length2];
            for (int i10 = 0; i10 < strArrSplit2.length; i10++) {
                String str2 = strArrSplit2[i10];
                if (str2.length() != 0) {
                    iArr2[i10] = Integer.parseInt(str2);
                } else {
                    if (i10 != 0) {
                        throw new UnsupportedOperationException();
                    }
                    iArr2[i10] = 0;
                }
            }
            int i11 = iArr2[0];
            int i12 = length2 > 1 ? iArr2[1] : -1;
            int i13 = length2 == 3 ? iArr2[2] : 1;
            if (i12 < 0 || i12 >= i11) {
                if (i13 > 0) {
                    return new w(i11, i12, i13);
                }
                throw new UnsupportedOperationException("step must greater than zero : " + i13);
            }
            throw new UnsupportedOperationException("end must greater than or equals start. start " + i11 + ",  end " + i12);
        }

        public a0[] d() {
            String str = this.f2563b;
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
                    if (vVar.f2586c || !vVar.f2584a.equals("*")) {
                    }
                }
                int i8 = this.f2566e;
                if (i8 == a0VarArr.length) {
                    a0[] a0VarArr2 = new a0[(i8 * 3) / 2];
                    System.arraycopy(a0VarArr, 0, a0VarArr2, 0, i8);
                    a0VarArr = a0VarArr2;
                }
                int i9 = this.f2566e;
                this.f2566e = i9 + 1;
                a0VarArr[i9] = a0VarP;
            }
            int i10 = this.f2566e;
            if (i10 == a0VarArr.length) {
                return a0VarArr;
            }
            a0[] a0VarArr3 = new a0[i10];
            System.arraycopy(a0VarArr, 0, a0VarArr3, 0, i10);
            return a0VarArr3;
        }

        public d e(d dVar) {
            char c8 = this.f2565d;
            boolean z7 = true;
            boolean z8 = c8 == '&';
            if ((c8 != '&' || f() != '&') && (this.f2565d != '|' || f() != '|')) {
                return dVar;
            }
            i();
            i();
            if (this.f2565d == '(') {
                i();
            } else {
                z7 = false;
            }
            while (this.f2565d == ' ') {
                i();
            }
            e eVar = new e(dVar, (d) k(false), z8);
            if (z7 && this.f2565d == ')') {
                i();
            }
            return eVar;
        }

        public char f() {
            return this.f2563b.charAt(this.f2564c);
        }

        public boolean h() {
            return this.f2564c >= this.f2563b.length();
        }

        public void i() {
            String str = this.f2563b;
            int i8 = this.f2564c;
            this.f2564c = i8 + 1;
            this.f2565d = str.charAt(i8);
        }

        public a0 j(boolean z7) {
            Object objK = k(z7);
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

        public double l(long j8) {
            int i8 = this.f2564c - 1;
            i();
            while (true) {
                char c8 = this.f2565d;
                if (c8 < '0' || c8 > '9') {
                    break;
                }
                i();
            }
            return Double.parseDouble(this.f2563b.substring(i8, this.f2564c - 1)) + j8;
        }

        public long m() {
            int i8 = this.f2564c - 1;
            char c8 = this.f2565d;
            if (c8 == '+' || c8 == '-') {
                i();
            }
            while (true) {
                char c9 = this.f2565d;
                if (c9 < '0' || c9 > '9') {
                    break;
                }
                i();
            }
            return Long.parseLong(this.f2563b.substring(i8, this.f2564c - 1));
        }

        public String n() {
            s();
            char c8 = this.f2565d;
            if (c8 != '\\' && !Character.isJavaIdentifierStart(c8)) {
                throw new JSONPathException("illeal jsonpath syntax. " + this.f2563b);
            }
            StringBuilder sb = new StringBuilder();
            while (!h()) {
                char c9 = this.f2565d;
                if (c9 == '\\') {
                    i();
                    sb.append(this.f2565d);
                    if (h()) {
                        return sb.toString();
                    }
                    i();
                } else {
                    if (!Character.isJavaIdentifierPart(c9)) {
                        break;
                    }
                    sb.append(this.f2565d);
                    i();
                }
            }
            if (h() && Character.isJavaIdentifierPart(this.f2565d)) {
                sb.append(this.f2565d);
            }
            return sb.toString();
        }

        public Operator o() {
            Operator operator;
            char c8 = this.f2565d;
            if (c8 == '=') {
                i();
                char c9 = this.f2565d;
                if (c9 == '~') {
                    i();
                    operator = Operator.REG_MATCH;
                } else if (c9 == '=') {
                    i();
                    operator = Operator.EQ;
                } else {
                    operator = Operator.EQ;
                }
            } else if (c8 == '!') {
                i();
                a('=');
                operator = Operator.NE;
            } else if (c8 == '<') {
                i();
                if (this.f2565d == '=') {
                    i();
                    operator = Operator.LE;
                } else {
                    operator = Operator.LT;
                }
            } else if (c8 == '>') {
                i();
                if (this.f2565d == '=') {
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
            boolean z7 = true;
            if (this.f2566e == 0 && this.f2563b.length() == 1) {
                if (g(this.f2565d)) {
                    return new b(this.f2565d - '0');
                }
                char c8 = this.f2565d;
                if ((c8 >= 'a' && c8 <= 'z') || (c8 >= 'A' && c8 <= 'Z')) {
                    return new v(Character.toString(c8), false);
                }
            }
            while (!h()) {
                s();
                char c9 = this.f2565d;
                if (c9 != '$') {
                    if (c9 != '.' && c9 != '/') {
                        if (c9 == '[') {
                            return j(true);
                        }
                        if (this.f2566e == 0) {
                            return new v(n(), false);
                        }
                        if (c9 == '?') {
                            return new f((d) k(false));
                        }
                        throw new JSONPathException("not support jsonpath : " + this.f2563b);
                    }
                    i();
                    if (c9 == '.' && this.f2565d == '.') {
                        i();
                        int length = this.f2563b.length();
                        int i8 = this.f2564c;
                        if (length > i8 + 3 && this.f2565d == '[' && this.f2563b.charAt(i8) == '*' && this.f2563b.charAt(this.f2564c + 1) == ']' && this.f2563b.charAt(this.f2564c + 2) == '.') {
                            i();
                            i();
                            i();
                            i();
                        }
                    } else {
                        z7 = false;
                    }
                    char c10 = this.f2565d;
                    if (c10 == '*') {
                        if (!h()) {
                            i();
                        }
                        return z7 ? g0.f2548b : g0.f2547a;
                    }
                    if (g(c10)) {
                        return j(false);
                    }
                    String strN = n();
                    if (this.f2565d != '(') {
                        return new v(strN, z7);
                    }
                    i();
                    if (this.f2565d != ')') {
                        throw new JSONPathException("not support jsonpath : " + this.f2563b);
                    }
                    if (!h()) {
                        i();
                    }
                    if ("size".equals(strN) || "length".equals(strN)) {
                        return b0.f2533a;
                    }
                    if ("max".equals(strN)) {
                        return o.f2574a;
                    }
                    if ("min".equals(strN)) {
                        return p.f2575a;
                    }
                    if ("keySet".equals(strN)) {
                        return m.f2568a;
                    }
                    if ("type".equals(strN)) {
                        return e0.f2542a;
                    }
                    if ("floor".equals(strN)) {
                        return g.f2546a;
                    }
                    throw new JSONPathException("not support jsonpath : " + this.f2563b);
                }
                i();
                s();
                if (this.f2565d == '?') {
                    return new f((d) k(false));
                }
            }
            return null;
        }

        public String q() {
            char c8 = this.f2565d;
            i();
            int i8 = this.f2564c - 1;
            while (this.f2565d != c8 && !h()) {
                i();
            }
            String strSubstring = this.f2563b.substring(i8, h() ? this.f2564c : this.f2564c - 1);
            a(c8);
            return strSubstring;
        }

        public Object r() {
            s();
            if (g(this.f2565d)) {
                return Long.valueOf(m());
            }
            char c8 = this.f2565d;
            if (c8 == '\"' || c8 == '\'') {
                return q();
            }
            if (c8 != 'n') {
                throw new UnsupportedOperationException();
            }
            if ("null".equals(n())) {
                return null;
            }
            throw new JSONPathException(this.f2563b);
        }

        public final void s() {
            while (true) {
                char c8 = this.f2565d;
                if (c8 > ' ') {
                    return;
                }
                if (c8 != ' ' && c8 != '\r' && c8 != '\n' && c8 != '\t' && c8 != '\f' && c8 != '\b') {
                    return;
                } else {
                    i();
                }
            }
        }
    }

    public static class m implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final m f2568a = new m();

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.f(obj2);
        }
    }

    public static class n extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final String f2569f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final String f2570g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final String[] f2571h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final int f2572i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final boolean f2573j;

        public n(String str, boolean z7, String str2, String str3, String[] strArr, boolean z8) {
            super(str, z7);
            this.f2569f = str2;
            this.f2570g = str3;
            this.f2571h = strArr;
            this.f2573j = z8;
            int length = str2 != null ? str2.length() + 0 : 0;
            length = str3 != null ? length + str3.length() : length;
            if (strArr != null) {
                for (String str4 : strArr) {
                    length += str4.length();
                }
            }
            this.f2572i = length;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            int length;
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null) {
                return false;
            }
            String string = objB.toString();
            if (string.length() < this.f2572i) {
                return this.f2573j;
            }
            String str = this.f2569f;
            if (str == null) {
                length = 0;
            } else {
                if (!string.startsWith(str)) {
                    return this.f2573j;
                }
                length = this.f2569f.length() + 0;
            }
            String[] strArr = this.f2571h;
            if (strArr != null) {
                for (String str2 : strArr) {
                    int iIndexOf = string.indexOf(str2, length);
                    if (iIndexOf == -1) {
                        return this.f2573j;
                    }
                    length = iIndexOf + str2.length();
                }
            }
            String str3 = this.f2570g;
            return (str3 == null || string.endsWith(str3)) ? !this.f2573j : this.f2573j;
        }
    }

    public static class o implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final o f2574a = new o();

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
        public static final p f2575a = new p();

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
        public final int[] f2576a;

        public q(int[] iArr) {
            this.f2576a = iArr;
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            JSONArray jSONArray = new JSONArray(this.f2576a.length);
            int i8 = 0;
            while (true) {
                int[] iArr = this.f2576a;
                if (i8 >= iArr.length) {
                    return jSONArray;
                }
                jSONArray.add(jSONPath.h(obj2, iArr[i8]));
                i8++;
            }
        }
    }

    public static class r implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String[] f2577a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long[] f2578b;

        public r(String[] strArr) {
            this.f2577a = strArr;
            this.f2578b = new long[strArr.length];
            int i8 = 0;
            while (true) {
                long[] jArr = this.f2578b;
                if (i8 >= jArr.length) {
                    return;
                }
                jArr[i8] = l2.l.I(strArr[i8]);
                i8++;
            }
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            ArrayList arrayList = new ArrayList(this.f2577a.length);
            int i8 = 0;
            while (true) {
                String[] strArr = this.f2577a;
                if (i8 >= strArr.length) {
                    return arrayList;
                }
                arrayList.add(jSONPath.j(obj2, strArr[i8], this.f2578b[i8]));
                i8++;
            }
        }
    }

    public static class s extends u {
        public s(String str, boolean z7) {
            super(str, z7);
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            return jSONPath.j(obj3, this.f2580b, this.f2581c) != null;
        }
    }

    public static class t extends u {
        public t(String str, boolean z7) {
            super(str, z7);
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            return b(jSONPath, obj, obj3) == null;
        }
    }

    public static abstract class u implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static long f2579a = l2.l.I("type");

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f2580b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final long f2581c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f2582d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public a0 f2583e;

        public u(String str, boolean z7) {
            this.f2580b = str;
            long jI = l2.l.I(str);
            this.f2581c = jI;
            this.f2582d = z7;
            if (z7) {
                if (jI == f2579a) {
                    this.f2583e = e0.f2542a;
                } else {
                    if (jI == 5614464919154503228L) {
                        this.f2583e = b0.f2533a;
                        return;
                    }
                    throw new JSONPathException("unsupported funciton : " + str);
                }
            }
        }

        public Object b(JSONPath jSONPath, Object obj, Object obj2) {
            a0 a0Var = this.f2583e;
            return a0Var != null ? a0Var.a(jSONPath, obj, obj2) : jSONPath.j(obj2, this.f2580b, this.f2581c);
        }
    }

    public static class v implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f2584a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f2585b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f2586c;

        public v(String str, boolean z7) {
            this.f2584a = str;
            this.f2585b = l2.l.I(str);
            this.f2586c = z7;
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            if (!this.f2586c) {
                return jSONPath.j(obj2, this.f2584a, this.f2585b);
            }
            ArrayList arrayList = new ArrayList();
            jSONPath.d(obj2, this.f2584a, arrayList);
            return arrayList;
        }
    }

    public static class w implements a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2587a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f2588b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f2589c;

        public w(int i8, int i9, int i10) {
            this.f2587a = i8;
            this.f2588b = i9;
            this.f2589c = i10;
        }

        @Override // com.alibaba.fastjson.JSONPath.a0
        public Object a(JSONPath jSONPath, Object obj, Object obj2) {
            int iIntValue = b0.f2533a.a(jSONPath, obj, obj2).intValue();
            int i8 = this.f2587a;
            if (i8 < 0) {
                i8 += iIntValue;
            }
            int i9 = this.f2588b;
            if (i9 < 0) {
                i9 += iIntValue;
            }
            int i10 = ((i9 - i8) / this.f2589c) + 1;
            if (i10 == -1) {
                return null;
            }
            ArrayList arrayList = new ArrayList(i10);
            while (i8 <= i9 && i8 < iIntValue) {
                arrayList.add(jSONPath.h(obj2, i8));
                i8 += this.f2589c;
            }
            return arrayList;
        }
    }

    public static class x extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final a0 f2590f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final Operator f2591g;

        public x(String str, boolean z7, a0 a0Var, Operator operator) {
            super(str, z7);
            this.f2590f = a0Var;
            this.f2591g = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null || !(objB instanceof Number)) {
                return false;
            }
            Object objA = this.f2590f.a(jSONPath, obj, obj);
            if ((objA instanceof Integer) || (objA instanceof Long) || (objA instanceof Short) || (objA instanceof Byte)) {
                long jC0 = l2.l.C0((Number) objA);
                if ((objB instanceof Integer) || (objB instanceof Long) || (objB instanceof Short) || (objB instanceof Byte)) {
                    long jC02 = l2.l.C0((Number) objB);
                    switch (a.f2531a[this.f2591g.ordinal()]) {
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
                    switch (a.f2531a[this.f2591g.ordinal()]) {
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
        public final Pattern f2592f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final Operator f2593g;

        public y(String str, boolean z7, Pattern pattern, Operator operator) {
            super(str, z7);
            this.f2592f = pattern;
            this.f2593g = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null) {
                return false;
            }
            return this.f2592f.matcher(objB.toString()).matches();
        }
    }

    public static class z extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Pattern f2594f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final boolean f2595g;

        public z(String str, boolean z7, String str2, boolean z8) {
            super(str, z7);
            this.f2594f = Pattern.compile(str2);
            this.f2595g = z8;
        }

        @Override // com.alibaba.fastjson.JSONPath.d
        public boolean a(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object objB = b(jSONPath, obj, obj3);
            if (objB == null) {
                return false;
            }
            boolean zMatches = this.f2594f.matcher(objB.toString()).matches();
            return this.f2595g ? !zMatches : zMatches;
        }
    }

    public JSONPath(String str) {
        this(str, a1.g(), f2.h.s());
    }

    public static int a(Object obj, Object obj2) {
        Object d8;
        Object f8;
        if (obj.getClass() == obj2.getClass()) {
            return ((Comparable) obj).compareTo(obj2);
        }
        Class<?> cls = obj.getClass();
        Class<?> cls2 = obj2.getClass();
        if (cls == BigDecimal.class) {
            if (cls2 == Integer.class) {
                f8 = new BigDecimal(((Integer) obj2).intValue());
            } else if (cls2 == Long.class) {
                f8 = new BigDecimal(((Long) obj2).longValue());
            } else if (cls2 == Float.class) {
                f8 = new BigDecimal(((Float) obj2).floatValue());
            } else if (cls2 == Double.class) {
                f8 = new BigDecimal(((Double) obj2).doubleValue());
            }
            obj2 = f8;
        } else if (cls == Long.class) {
            if (cls2 == Integer.class) {
                f8 = new Long(((Integer) obj2).intValue());
                obj2 = f8;
            } else {
                if (cls2 == BigDecimal.class) {
                    d8 = new BigDecimal(((Long) obj).longValue());
                } else if (cls2 == Float.class) {
                    d8 = new Float(((Long) obj).longValue());
                } else if (cls2 == Double.class) {
                    d8 = new Double(((Long) obj).longValue());
                }
                obj = d8;
            }
        } else if (cls == Integer.class) {
            if (cls2 == Long.class) {
                d8 = new Long(((Integer) obj).intValue());
            } else if (cls2 == BigDecimal.class) {
                d8 = new BigDecimal(((Integer) obj).intValue());
            } else if (cls2 == Float.class) {
                d8 = new Float(((Integer) obj).intValue());
            } else if (cls2 == Double.class) {
                d8 = new Double(((Integer) obj).intValue());
            }
            obj = d8;
        } else if (cls == Double.class) {
            if (cls2 == Integer.class) {
                f8 = new Double(((Integer) obj2).intValue());
            } else if (cls2 == Long.class) {
                f8 = new Double(((Long) obj2).longValue());
            } else if (cls2 == Float.class) {
                f8 = new Double(((Float) obj2).floatValue());
            }
            obj2 = f8;
        } else if (cls == Float.class) {
            if (cls2 == Integer.class) {
                f8 = new Float(((Integer) obj2).intValue());
            } else if (cls2 == Long.class) {
                f8 = new Float(((Long) obj2).longValue());
            } else if (cls2 == Double.class) {
                d8 = new Double(((Float) obj).floatValue());
                obj = d8;
            }
            obj2 = f8;
        }
        return ((Comparable) obj).compareTo(obj2);
    }

    public static JSONPath b(String str) {
        if (str == null) {
            throw new JSONPathException("jsonpath can not be null");
        }
        JSONPath jSONPath = f2525d.get(str);
        if (jSONPath != null) {
            return jSONPath;
        }
        JSONPath jSONPath2 = new JSONPath(str);
        if (f2525d.size() >= 1024) {
            return jSONPath2;
        }
        f2525d.putIfAbsent(str, jSONPath2);
        return f2525d.get(str);
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
            } catch (Exception e8) {
                throw new JSONPathException("jsonpath error, path " + this.f2526e, e8);
            }
        } else {
            collectionV = obj instanceof Map ? ((Map) obj).values() : obj instanceof Collection ? (Collection) obj : null;
        }
        if (collectionV == null) {
            throw new UnsupportedOperationException(cls.getName());
        }
        for (Object obj2 : collectionV) {
            if (obj2 == null || f2.h.v(obj2.getClass())) {
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
                } else if (value != null && !f2.h.v(value.getClass())) {
                    d(value, str, list);
                }
            }
            return;
        }
        if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                if (!f2.h.v(obj2.getClass())) {
                    d(obj2, str, list);
                }
            }
            return;
        }
        j0 j0VarI = i(obj.getClass());
        if (j0VarI == null) {
            if (obj instanceof List) {
                List list2 = (List) obj;
                for (int i8 = 0; i8 < list2.size(); i8++) {
                    d(list2.get(i8), str, list);
                }
                return;
            }
            return;
        }
        try {
            h2.a0 a0VarT = j0VarI.t(str);
            if (a0VarT == null) {
                Iterator<Object> it = j0VarI.v(obj).iterator();
                while (it.hasNext()) {
                    d(it.next(), str, list);
                }
                return;
            }
            try {
                list.add(a0VarT.c(obj));
            } catch (IllegalAccessException e8) {
                throw new JSONException("getFieldValue error." + str, e8);
            } catch (InvocationTargetException e9) {
                throw new JSONException("getFieldValue error." + str, e9);
            }
        } catch (Exception e10) {
            throw new JSONPathException("jsonpath error, path " + this.f2526e + ", segement " + str, e10);
        }
    }

    public Object e(Object obj) {
        if (obj == null) {
            return null;
        }
        l();
        int i8 = 0;
        Object objA = obj;
        while (true) {
            a0[] a0VarArr = this.f2527f;
            if (i8 >= a0VarArr.length) {
                return objA;
            }
            objA = a0VarArr[i8].a(this, obj, objA);
            i8++;
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
        } catch (Exception e8) {
            throw new JSONPathException("evalKeySet error : " + this.f2526e, e8);
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
            int i8 = 0;
            Iterator it = ((Map) obj).values().iterator();
            while (it.hasNext()) {
                if (it.next() != null) {
                    i8++;
                }
            }
            return i8;
        }
        j0 j0VarI = i(obj.getClass());
        if (j0VarI == null) {
            return -1;
        }
        try {
            return j0VarI.x(obj);
        } catch (Exception e8) {
            throw new JSONPathException("evalSize error : " + this.f2526e, e8);
        }
    }

    public Object h(Object obj, int i8) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (i8 >= 0) {
                if (i8 < list.size()) {
                    return list.get(i8);
                }
                return null;
            }
            if (Math.abs(i8) <= list.size()) {
                return list.get(list.size() + i8);
            }
            return null;
        }
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            if (i8 >= 0) {
                if (i8 < length) {
                    return Array.get(obj, i8);
                }
                return null;
            }
            if (Math.abs(i8) <= length) {
                return Array.get(obj, length + i8);
            }
            return null;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Object obj2 = map.get(Integer.valueOf(i8));
            return obj2 == null ? map.get(Integer.toString(i8)) : obj2;
        }
        if (!(obj instanceof Collection)) {
            if (i8 == 0) {
                return obj;
            }
            throw new UnsupportedOperationException();
        }
        int i9 = 0;
        for (Object obj3 : (Collection) obj) {
            if (i9 == i8) {
                return obj3;
            }
            i9++;
        }
        return null;
    }

    public j0 i(Class<?> cls) {
        t0 t0VarH = this.f2529h.h(cls);
        if (t0VarH instanceof j0) {
            return (j0) t0VarH;
        }
        return null;
    }

    public Object j(Object obj, String str, long j8) {
        JSONArray jSONArray = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            try {
                obj = c2.a.parseObject((String) obj);
            } catch (Exception unused) {
            }
        }
        Object obj2 = obj;
        if (obj2 instanceof Map) {
            Map map = (Map) obj2;
            Object obj3 = map.get(str);
            return obj3 == null ? (5614464919154503228L == j8 || -1580386065683472715L == j8) ? Integer.valueOf(map.size()) : obj3 : obj3;
        }
        j0 j0VarI = i(obj2.getClass());
        if (j0VarI != null) {
            try {
                return j0VarI.u(obj2, str, j8, false);
            } catch (Exception e8) {
                throw new JSONPathException("jsonpath error, path " + this.f2526e + ", segement " + str, e8);
            }
        }
        int i8 = 0;
        if (obj2 instanceof List) {
            List list = (List) obj2;
            if (5614464919154503228L == j8 || -1580386065683472715L == j8) {
                return Integer.valueOf(list.size());
            }
            while (i8 < list.size()) {
                Object obj4 = list.get(i8);
                if (obj4 == list) {
                    if (jSONArray == null) {
                        jSONArray = new JSONArray(list.size());
                    }
                    jSONArray.add(obj4);
                } else {
                    Object objJ = j(obj4, str, j8);
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
                i8++;
            }
            return jSONArray == null ? Collections.emptyList() : jSONArray;
        }
        if (obj2 instanceof Object[]) {
            Object[] objArr = (Object[]) obj2;
            if (5614464919154503228L == j8 || -1580386065683472715L == j8) {
                return Integer.valueOf(objArr.length);
            }
            JSONArray jSONArray2 = new JSONArray(objArr.length);
            while (i8 < objArr.length) {
                Object[] objArr2 = objArr[i8];
                if (objArr2 == objArr) {
                    jSONArray2.add(objArr2);
                } else {
                    Object objJ2 = j(objArr2, str, j8);
                    if (objJ2 instanceof Collection) {
                        jSONArray2.addAll((Collection) objJ2);
                    } else if (objJ2 != null) {
                        jSONArray2.add(objJ2);
                    }
                }
                i8++;
            }
            return jSONArray2;
        }
        if (obj2 instanceof Enum) {
            Enum r8 = (Enum) obj2;
            if (-4270347329889690746L == j8) {
                return r8.name();
            }
            if (-1014497654951707614L == j8) {
                return Integer.valueOf(r8.ordinal());
            }
        }
        if (obj2 instanceof Calendar) {
            Calendar calendar = (Calendar) obj2;
            if (8963398325558730460L == j8) {
                return Integer.valueOf(calendar.get(1));
            }
            if (-811277319855450459L == j8) {
                return Integer.valueOf(calendar.get(2));
            }
            if (-3851359326990528739L == j8) {
                return Integer.valueOf(calendar.get(5));
            }
            if (4647432019745535567L == j8) {
                return Integer.valueOf(calendar.get(11));
            }
            if (6607618197526598121L == j8) {
                return Integer.valueOf(calendar.get(12));
            }
            if (-6586085717218287427L == j8) {
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
        } catch (Exception e8) {
            throw new JSONPathException("jsonpath error, path " + this.f2526e, e8);
        }
    }

    public void l() {
        if (this.f2527f != null) {
            return;
        }
        if ("*".equals(this.f2526e)) {
            this.f2527f = new a0[]{g0.f2547a};
            return;
        }
        l lVar = new l(this.f2526e);
        this.f2527f = lVar.d();
        this.f2528g = lVar.f2567f;
    }

    public boolean n() {
        try {
            l();
            int i8 = 0;
            while (true) {
                a0[] a0VarArr = this.f2527f;
                if (i8 >= a0VarArr.length) {
                    return true;
                }
                Class<?> cls = a0VarArr[i8].getClass();
                if (cls != b.class && cls != v.class) {
                    return false;
                }
                i8++;
            }
        } catch (JSONPathException unused) {
            return false;
        }
    }

    @Override // c2.b
    public String toJSONString() {
        return c2.a.toJSONString(this.f2526e);
    }

    public JSONPath(String str, a1 a1Var, f2.h hVar) {
        if (str == null || str.length() == 0) {
            throw new JSONPathException("json-path can not be null or empty");
        }
        this.f2526e = str;
        this.f2529h = a1Var;
        this.f2530i = hVar;
    }
}
