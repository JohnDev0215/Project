package u;

import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: KeyCache.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<Object, HashMap<String, float[]>> f5074a = new HashMap<>();

    public float a(Object obj, String str, int i3) {
        if (!this.f5074a.containsKey(obj)) {
            return Float.NaN;
        }
        HashMap<String, float[]> map = this.f5074a.get(obj);
        if (!map.containsKey(str)) {
            return Float.NaN;
        }
        float[] fArr = map.get(str);
        if (fArr.length > i3) {
            return fArr[i3];
        }
        return Float.NaN;
    }

    public void b(Object obj, String str, int i3, float f3) {
        if (!this.f5074a.containsKey(obj)) {
            HashMap<String, float[]> map = new HashMap<>();
            float[] fArr = new float[i3 + 1];
            fArr[i3] = f3;
            map.put(str, fArr);
            this.f5074a.put(obj, map);
            return;
        }
        HashMap<String, float[]> map2 = this.f5074a.get(obj);
        if (!map2.containsKey(str)) {
            float[] fArr2 = new float[i3 + 1];
            fArr2[i3] = f3;
            map2.put(str, fArr2);
            this.f5074a.put(obj, map2);
            return;
        }
        float[] fArrCopyOf = map2.get(str);
        if (fArrCopyOf.length <= i3) {
            fArrCopyOf = Arrays.copyOf(fArrCopyOf, i3 + 1);
        }
        fArrCopyOf[i3] = f3;
        map2.put(str, fArrCopyOf);
    }
}
