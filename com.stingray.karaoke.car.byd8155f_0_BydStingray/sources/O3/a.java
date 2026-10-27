package O3;

import a4.AbstractC0922c;
import a4.AbstractC0927h;
import android.graphics.Bitmap;
import android.graphics.Rect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import u.C2459G;
import u.C2472l;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public HashMap c;
    public HashMap d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f5071e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public HashMap f5072f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList f5073g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public C2459G f5074h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C2472l f5075i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList f5076j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Rect f5077k;
    public float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f5078m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f5079n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Y4.e f5069a = new Y4.e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashSet f5070b = new HashSet();
    public int o = 0;

    public final void a(String str) {
        AbstractC0922c.a(str);
        this.f5070b.add(str);
    }

    public final float b() {
        return (long) (((this.f5078m - this.l) / this.f5079n) * 1000.0f);
    }

    public final Map c() {
        float fC = AbstractC0927h.c();
        if (fC != this.f5071e) {
            for (Map.Entry entry : this.d.entrySet()) {
                HashMap map = this.d;
                String str = (String) entry.getKey();
                m mVar = (m) entry.getValue();
                float f10 = this.f5071e / fC;
                int i7 = (int) (mVar.f5133a * f10);
                int i10 = (int) (mVar.f5134b * f10);
                m mVar2 = new m(mVar.c, mVar.d, i7, i10, mVar.f5135e);
                Bitmap bitmap = mVar.f5136f;
                if (bitmap != null) {
                    mVar2.f5136f = Bitmap.createScaledBitmap(bitmap, i7, i10, true);
                }
                map.put(str, mVar2);
            }
        }
        this.f5071e = fC;
        return this.d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LottieComposition:\n");
        Iterator it = this.f5076j.iterator();
        while (it.hasNext()) {
            sb.append(((X3.e) it.next()).a("\t"));
        }
        return sb.toString();
    }
}
