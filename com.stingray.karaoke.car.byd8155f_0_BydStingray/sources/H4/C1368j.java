package h4;

import A4.q;
import android.graphics.Bitmap;
import io.sentry.C1489n1;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* JADX INFO: renamed from: h4.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1368j {
    public static final Bitmap.Config[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Bitmap.Config[] f15527e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Bitmap.Config[] f15528f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Bitmap.Config[] f15529g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Bitmap.Config[] f15530h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1363e f15531a = new C1363e(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1489n1 f15532b = new C1489n1(20);
    public final HashMap c = new HashMap();

    static {
        Bitmap.Config[] configArr = (Bitmap.Config[]) Arrays.copyOf(new Bitmap.Config[]{Bitmap.Config.ARGB_8888, null}, 3);
        configArr[configArr.length - 1] = Bitmap.Config.RGBA_F16;
        d = configArr;
        f15527e = configArr;
        f15528f = new Bitmap.Config[]{Bitmap.Config.RGB_565};
        f15529g = new Bitmap.Config[]{Bitmap.Config.ARGB_4444};
        f15530h = new Bitmap.Config[]{Bitmap.Config.ALPHA_8};
    }

    public static String c(int i7, Bitmap.Config config) {
        return "[" + i7 + "](" + config + ")";
    }

    public final void a(Integer num, Bitmap bitmap) {
        NavigableMap navigableMapD = d(bitmap.getConfig());
        Integer num2 = (Integer) navigableMapD.get(num);
        if (num2 != null) {
            if (num2.intValue() == 1) {
                navigableMapD.remove(num);
                return;
            } else {
                navigableMapD.put(num, Integer.valueOf(num2.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + num + ", removed: " + c(q.c(bitmap), bitmap.getConfig()) + ", this: " + this);
    }

    public final Bitmap b(int i7, int i10, Bitmap.Config config) {
        Bitmap.Config[] configArr;
        int iD = q.d(config) * i7 * i10;
        C1363e c1363e = this.f15531a;
        InterfaceC1365g interfaceC1365gA1 = (InterfaceC1365g) ((ArrayDeque) c1363e.f476m).poll();
        if (interfaceC1365gA1 == null) {
            interfaceC1365gA1 = c1363e.a1();
        }
        C1367i c1367i = (C1367i) interfaceC1365gA1;
        c1367i.f15526b = iD;
        c1367i.c = config;
        if (Bitmap.Config.RGBA_F16.equals(config)) {
            configArr = f15527e;
        } else {
            int i11 = AbstractC1366h.f15524a[config.ordinal()];
            configArr = i11 != 1 ? i11 != 2 ? i11 != 3 ? i11 != 4 ? new Bitmap.Config[]{config} : f15530h : f15529g : f15528f : d;
        }
        int length = configArr.length;
        int i12 = 0;
        while (true) {
            if (i12 >= length) {
                break;
            }
            Bitmap.Config config2 = configArr[i12];
            Integer num = (Integer) d(config2).ceilingKey(Integer.valueOf(iD));
            if (num == null || num.intValue() > iD * 8) {
                i12++;
            } else if (num.intValue() != iD || (config2 != null ? !config2.equals(config) : config != null)) {
                c1363e.S0(c1367i);
                int iIntValue = num.intValue();
                InterfaceC1365g interfaceC1365gA12 = (InterfaceC1365g) ((ArrayDeque) c1363e.f476m).poll();
                if (interfaceC1365gA12 == null) {
                    interfaceC1365gA12 = c1363e.a1();
                }
                c1367i = (C1367i) interfaceC1365gA12;
                c1367i.f15526b = iIntValue;
                c1367i.c = config2;
            }
        }
        Bitmap bitmap = (Bitmap) this.f15532b.p(c1367i);
        if (bitmap != null) {
            a(Integer.valueOf(c1367i.f15526b), bitmap);
            bitmap.reconfigure(i7, i10, config);
        }
        return bitmap;
    }

    public final NavigableMap d(Bitmap.Config config) {
        HashMap map = this.c;
        NavigableMap navigableMap = (NavigableMap) map.get(config);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        map.put(config, treeMap);
        return treeMap;
    }

    public final void e(Bitmap bitmap) {
        int iC = q.c(bitmap);
        Bitmap.Config config = bitmap.getConfig();
        C1363e c1363e = this.f15531a;
        InterfaceC1365g interfaceC1365gA1 = (InterfaceC1365g) ((ArrayDeque) c1363e.f476m).poll();
        if (interfaceC1365gA1 == null) {
            interfaceC1365gA1 = c1363e.a1();
        }
        C1367i c1367i = (C1367i) interfaceC1365gA1;
        c1367i.f15526b = iC;
        c1367i.c = config;
        this.f15532b.D(c1367i, bitmap);
        NavigableMap navigableMapD = d(bitmap.getConfig());
        Integer num = (Integer) navigableMapD.get(Integer.valueOf(c1367i.f15526b));
        navigableMapD.put(Integer.valueOf(c1367i.f15526b), Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    public final String toString() {
        StringBuilder sbI = M5.b.i("SizeConfigStrategy{groupedMap=");
        sbI.append(this.f15532b);
        sbI.append(", sortedSizes=(");
        HashMap map = this.c;
        for (Map.Entry entry : map.entrySet()) {
            sbI.append(entry.getKey());
            sbI.append('[');
            sbI.append(entry.getValue());
            sbI.append("], ");
        }
        if (!map.isEmpty()) {
            sbI.replace(sbI.length() - 2, sbI.length(), "");
        }
        sbI.append(")}");
        return sbI.toString();
    }
}
