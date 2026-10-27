package defpackage;

import android.view.ViewGroup;
import java.util.HashMap;

/* JADX INFO: compiled from: KeyCache.java */
/* JADX INFO: loaded from: classes.dex */
public final class a41 {
    public final /* synthetic */ int a;
    public final Object b;

    public /* synthetic */ a41(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public final float a(Object obj, String str) {
        HashMap map;
        float[] fArr;
        HashMap map2 = (HashMap) this.b;
        if (map2.containsKey(obj) && (map = (HashMap) map2.get(obj)) != null && map.containsKey(str) && (fArr = (float[]) map.get(str)) != null && fArr.length > 0) {
            return fArr[0];
        }
        return Float.NaN;
    }

    public final String toString() {
        switch (this.a) {
            case 2:
                return "<" + ((String) this.b) + '>';
            default:
                return super.toString();
        }
    }

    public a41() {
        this.a = 0;
        this.b = new HashMap();
    }

    public a41(ViewGroup viewGroup) {
        this.a = 1;
        this.b = viewGroup.getOverlay();
    }
}
