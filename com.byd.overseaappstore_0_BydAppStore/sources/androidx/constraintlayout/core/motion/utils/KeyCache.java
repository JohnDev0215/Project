package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class KeyCache {
    public HashMap<Object, HashMap<String, float[]>> map = new HashMap<>();

    public float getFloatValue(Object obj, String str, int i) {
        HashMap<String, float[]> map;
        float[] fArr;
        if (this.map.containsKey(obj) && (map = this.map.get(obj)) != null && map.containsKey(str) && (fArr = map.get(str)) != null && fArr.length > i) {
            return fArr[i];
        }
        return Float.NaN;
    }

    public void setFloatValue(Object obj, String str, int i, float f) {
        HashMap<String, float[]> map;
        if (this.map.containsKey(obj)) {
            map = this.map.get(obj);
            if (map == null) {
                map = new HashMap<>();
            }
            if (map.containsKey(str)) {
                float[] fArrCopyOf = map.get(str);
                if (fArrCopyOf == null) {
                    fArrCopyOf = new float[0];
                }
                if (fArrCopyOf.length <= i) {
                    fArrCopyOf = Arrays.copyOf(fArrCopyOf, i + 1);
                }
                fArrCopyOf[i] = f;
                map.put(str, fArrCopyOf);
                return;
            }
            float[] fArr = new float[i + 1];
            fArr[i] = f;
            map.put(str, fArr);
        } else {
            map = new HashMap<>();
            float[] fArr2 = new float[i + 1];
            fArr2[i] = f;
            map.put(str, fArr2);
        }
        this.map.put(obj, map);
    }
}
