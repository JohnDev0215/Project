package g2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: EnumDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class<?> f5032a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Enum[] f5033b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Enum[] f5034c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long[] f5035d;

    public h(Class<?> cls) {
        d2.b bVar;
        this.f5032a = cls;
        this.f5034c = (Enum[]) cls.getEnumConstants();
        HashMap map = new HashMap();
        int i8 = 0;
        while (true) {
            Enum[] enumArr = this.f5034c;
            if (i8 >= enumArr.length) {
                break;
            }
            Enum r52 = enumArr[i8];
            String strName = r52.name();
            d2.b bVar2 = null;
            try {
                bVar = (d2.b) l2.l.N(cls.getField(strName), d2.b.class);
                if (bVar != null) {
                    try {
                        String strName2 = bVar.name();
                        if (strName2 != null && strName2.length() > 0) {
                            strName = strName2;
                        }
                    } catch (Exception unused) {
                        bVar2 = bVar;
                        bVar = bVar2;
                    }
                }
            } catch (Exception unused2) {
            }
            int i9 = 0;
            long j8 = -3750763034362895579L;
            long j9 = -3750763034362895579L;
            while (i9 < strName.length()) {
                int iCharAt = strName.charAt(i9);
                long j10 = ((long) iCharAt) ^ j8;
                if (iCharAt >= 65 && iCharAt <= 90) {
                    iCharAt += 32;
                }
                j9 = (((long) iCharAt) ^ j9) * 1099511628211L;
                i9++;
                j8 = j10 * 1099511628211L;
            }
            map.put(Long.valueOf(j8), r52);
            if (j8 != j9) {
                map.put(Long.valueOf(j9), r52);
            }
            if (bVar != null) {
                String[] strArrAlternateNames = bVar.alternateNames();
                int length = strArrAlternateNames.length;
                int i10 = 0;
                while (i10 < length) {
                    String str = strArrAlternateNames[i10];
                    int i11 = 0;
                    long jCharAt = -3750763034362895579L;
                    while (i11 < str.length()) {
                        jCharAt = (jCharAt ^ ((long) str.charAt(i11))) * 1099511628211L;
                        i11++;
                        i8 = i8;
                    }
                    int i12 = i8;
                    if (jCharAt != j8 && jCharAt != j9) {
                        map.put(Long.valueOf(jCharAt), r52);
                    }
                    i10++;
                    i8 = i12;
                }
            }
            i8++;
        }
        this.f5035d = new long[map.size()];
        Iterator it = map.keySet().iterator();
        int i13 = 0;
        while (it.hasNext()) {
            this.f5035d[i13] = ((Long) it.next()).longValue();
            i13++;
        }
        Arrays.sort(this.f5035d);
        this.f5033b = new Enum[this.f5035d.length];
        int i14 = 0;
        while (true) {
            long[] jArr = this.f5035d;
            if (i14 >= jArr.length) {
                return;
            }
            this.f5033b[i14] = (Enum) map.get(Long.valueOf(jArr[i14]));
            i14++;
        }
    }

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        try {
            f2.b bVar = aVar.f4861j;
            int iQ = bVar.q();
            if (iQ == 2) {
                int iN = bVar.N();
                bVar.V(16);
                if (iN >= 0) {
                    Object[] objArr = this.f5034c;
                    if (iN < objArr.length) {
                        return (T) objArr[iN];
                    }
                }
                throw new JSONException("parse enum " + this.f5032a.getName() + " error, value : " + iN);
            }
            if (iQ != 4) {
                if (iQ == 8) {
                    bVar.V(16);
                    return null;
                }
                throw new JSONException("parse enum " + this.f5032a.getName() + " error, value : " + aVar.N());
            }
            String strN0 = bVar.n0();
            bVar.V(16);
            if (strN0.length() == 0) {
                return null;
            }
            long j8 = -3750763034362895579L;
            long j9 = -3750763034362895579L;
            for (int i8 = 0; i8 < strN0.length(); i8++) {
                int iCharAt = strN0.charAt(i8);
                long j10 = j8 ^ ((long) iCharAt);
                if (iCharAt >= 65 && iCharAt <= 90) {
                    iCharAt += 32;
                }
                j8 = j10 * 1099511628211L;
                j9 = (j9 ^ ((long) iCharAt)) * 1099511628211L;
            }
            T t7 = (T) c(j8);
            if (t7 == null && j9 != j8) {
                t7 = (T) c(j9);
            }
            if (t7 == null && bVar.M(Feature.ErrorOnEnumNotMatch)) {
                throw new JSONException("not match enum value, " + this.f5032a.getName() + " : " + strN0);
            }
            return t7;
        } catch (JSONException e8) {
            throw e8;
        } catch (Exception e9) {
            throw new JSONException(e9.getMessage(), e9);
        }
    }

    public Enum c(long j8) {
        int iBinarySearch;
        if (this.f5033b != null && (iBinarySearch = Arrays.binarySearch(this.f5035d, j8)) >= 0) {
            return this.f5033b[iBinarySearch];
        }
        return null;
    }

    public Enum<?> d(int i8) {
        return this.f5034c[i8];
    }

    @Override // g2.t
    public int e() {
        return 2;
    }
}
