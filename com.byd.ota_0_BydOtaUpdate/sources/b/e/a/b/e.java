package b.e.a.b;

import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: KeyCache.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<Object, HashMap<String, float[]>> f1250a = new HashMap<>();

    public float a(Object obj, String str, int i2) {
        if (!this.f1250a.containsKey(obj)) {
            return Float.NaN;
        }
        HashMap<String, float[]> map = this.f1250a.get(obj);
        if (!map.containsKey(str)) {
            return Float.NaN;
        }
        float[] fArr = map.get(str);
        if (fArr.length > i2) {
            return fArr[i2];
        }
        return Float.NaN;
    }

    public void b(Object obj, String str, int i2, float f2) {
        if (!this.f1250a.containsKey(obj)) {
            HashMap<String, float[]> map = new HashMap<>();
            float[] fArr = new float[i2 + 1];
            fArr[i2] = f2;
            map.put(str, fArr);
            this.f1250a.put(obj, map);
            return;
        }
        HashMap<String, float[]> map2 = this.f1250a.get(obj);
        if (!map2.containsKey(str)) {
            float[] fArr2 = new float[i2 + 1];
            fArr2[i2] = f2;
            map2.put(str, fArr2);
            this.f1250a.put(obj, map2);
            return;
        }
        float[] fArrCopyOf = map2.get(str);
        if (fArrCopyOf.length <= i2) {
            fArrCopyOf = Arrays.copyOf(fArrCopyOf, i2 + 1);
        }
        fArrCopyOf[i2] = f2;
        map2.put(str, fArrCopyOf);
    }
}
