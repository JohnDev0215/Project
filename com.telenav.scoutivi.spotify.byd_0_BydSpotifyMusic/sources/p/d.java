package p;

import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: KeyCache.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    HashMap<Object, HashMap<String, float[]>> f8714a = new HashMap<>();

    public float a(Object obj, String str, int i7) {
        HashMap<String, float[]> map;
        float[] fArr;
        if (this.f8714a.containsKey(obj) && (map = this.f8714a.get(obj)) != null && map.containsKey(str) && (fArr = map.get(str)) != null && fArr.length > i7) {
            return fArr[i7];
        }
        return Float.NaN;
    }

    public void b(Object obj, String str, int i7, float f7) {
        if (!this.f8714a.containsKey(obj)) {
            HashMap<String, float[]> map = new HashMap<>();
            float[] fArr = new float[i7 + 1];
            fArr[i7] = f7;
            map.put(str, fArr);
            this.f8714a.put(obj, map);
            return;
        }
        HashMap<String, float[]> map2 = this.f8714a.get(obj);
        if (map2 == null) {
            map2 = new HashMap<>();
        }
        if (!map2.containsKey(str)) {
            float[] fArr2 = new float[i7 + 1];
            fArr2[i7] = f7;
            map2.put(str, fArr2);
            this.f8714a.put(obj, map2);
            return;
        }
        float[] fArrCopyOf = map2.get(str);
        if (fArrCopyOf == null) {
            fArrCopyOf = new float[0];
        }
        if (fArrCopyOf.length <= i7) {
            fArrCopyOf = Arrays.copyOf(fArrCopyOf, i7 + 1);
        }
        fArrCopyOf[i7] = f7;
        map2.put(str, fArrCopyOf);
    }
}
