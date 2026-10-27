package b.a.a.j.j;

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
    public final Class<?> f278a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Enum[] f279b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Enum[] f280c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long[] f281d;

    public h(Class<?> cls) {
        b.a.a.h.b bVar;
        this.f278a = cls;
        this.f280c = (Enum[]) cls.getEnumConstants();
        HashMap map = new HashMap();
        int i = 0;
        while (true) {
            Enum[] enumArr = this.f280c;
            if (i >= enumArr.length) {
                break;
            }
            Enum r5 = enumArr[i];
            String strName = r5.name();
            b.a.a.h.b bVar2 = null;
            try {
                bVar = (b.a.a.h.b) b.a.a.n.l.N(cls.getField(strName), b.a.a.h.b.class);
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
            int i2 = 0;
            long j = -3750763034362895579L;
            long j2 = -3750763034362895579L;
            while (i2 < strName.length()) {
                int iCharAt = strName.charAt(i2);
                long j3 = ((long) iCharAt) ^ j;
                if (iCharAt >= 65 && iCharAt <= 90) {
                    iCharAt += 32;
                }
                j2 = (((long) iCharAt) ^ j2) * 1099511628211L;
                i2++;
                j = j3 * 1099511628211L;
            }
            map.put(Long.valueOf(j), r5);
            if (j != j2) {
                map.put(Long.valueOf(j2), r5);
            }
            if (bVar != null) {
                String[] strArrAlternateNames = bVar.alternateNames();
                int length = strArrAlternateNames.length;
                int i3 = 0;
                while (i3 < length) {
                    String str = strArrAlternateNames[i3];
                    int i4 = 0;
                    long jCharAt = -3750763034362895579L;
                    while (i4 < str.length()) {
                        jCharAt = (jCharAt ^ ((long) str.charAt(i4))) * 1099511628211L;
                        i4++;
                        i = i;
                    }
                    int i5 = i;
                    if (jCharAt != j && jCharAt != j2) {
                        map.put(Long.valueOf(jCharAt), r5);
                    }
                    i3++;
                    i = i5;
                }
            }
            i++;
        }
        this.f281d = new long[map.size()];
        Iterator it = map.keySet().iterator();
        int i6 = 0;
        while (it.hasNext()) {
            this.f281d[i6] = ((Long) it.next()).longValue();
            i6++;
        }
        Arrays.sort(this.f281d);
        this.f279b = new Enum[this.f281d.length];
        int i7 = 0;
        while (true) {
            long[] jArr = this.f281d;
            if (i7 >= jArr.length) {
                return;
            }
            this.f279b[i7] = (Enum) map.get(Long.valueOf(jArr[i7]));
            i7++;
        }
    }

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        try {
            b.a.a.j.b bVar = aVar.g;
            int iJ = bVar.j();
            if (iJ == 2) {
                int iZ = bVar.z();
                bVar.F(16);
                if (iZ >= 0) {
                    Object[] objArr = this.f280c;
                    if (iZ < objArr.length) {
                        return (T) objArr[iZ];
                    }
                }
                throw new JSONException("parse enum " + this.f278a.getName() + " error, value : " + iZ);
            }
            if (iJ != 4) {
                if (iJ == 8) {
                    bVar.F(16);
                    return null;
                }
                throw new JSONException("parse enum " + this.f278a.getName() + " error, value : " + aVar.z());
            }
            String strV = bVar.V();
            bVar.F(16);
            if (strV.length() == 0) {
                return null;
            }
            long j = -3750763034362895579L;
            long j2 = -3750763034362895579L;
            for (int i = 0; i < strV.length(); i++) {
                int iCharAt = strV.charAt(i);
                long j3 = j ^ ((long) iCharAt);
                if (iCharAt >= 65 && iCharAt <= 90) {
                    iCharAt += 32;
                }
                j = j3 * 1099511628211L;
                j2 = (j2 ^ ((long) iCharAt)) * 1099511628211L;
            }
            T t = (T) c(j);
            if (t == null && j2 != j) {
                t = (T) c(j2);
            }
            if (t == null && bVar.y(Feature.ErrorOnEnumNotMatch)) {
                throw new JSONException("not match enum value, " + this.f278a.getName() + " : " + strV);
            }
            return t;
        } catch (JSONException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new JSONException(e3.getMessage(), e3);
        }
    }

    public Enum c(long j) {
        int iBinarySearch;
        if (this.f279b != null && (iBinarySearch = Arrays.binarySearch(this.f281d, j)) >= 0) {
            return this.f279b[iBinarySearch];
        }
        return null;
    }

    public Enum<?> d(int i) {
        return this.f280c[i];
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 2;
    }
}
