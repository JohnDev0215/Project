package x;

import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: KeyCache.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<Object, HashMap<String, float[]>> f8788a = new HashMap<>();

    public float a(Object obj, String str, int i8) {
        if (!this.f8788a.containsKey(obj)) {
            return Float.NaN;
        }
        HashMap<String, float[]> map = this.f8788a.get(obj);
        if (!map.containsKey(str)) {
            return Float.NaN;
        }
        float[] fArr = map.get(str);
        if (fArr.length > i8) {
            return fArr[i8];
        }
        return Float.NaN;
    }

    public void b(Object obj, String str, int i8, float f8) {
        if (!this.f8788a.containsKey(obj)) {
            HashMap<String, float[]> map = new HashMap<>();
            float[] fArr = new float[i8 + 1];
            fArr[i8] = f8;
            map.put(str, fArr);
            this.f8788a.put(obj, map);
            return;
        }
        HashMap<String, float[]> map2 = this.f8788a.get(obj);
        if (!map2.containsKey(str)) {
            float[] fArr2 = new float[i8 + 1];
            fArr2[i8] = f8;
            map2.put(str, fArr2);
            this.f8788a.put(obj, map2);
            return;
        }
        float[] fArrCopyOf = map2.get(str);
        if (fArrCopyOf.length <= i8) {
            fArrCopyOf = Arrays.copyOf(fArrCopyOf, i8 + 1);
        }
        fArrCopyOf[i8] = f8;
        map2.put(str, fArrCopyOf);
    }
}
