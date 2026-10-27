package spi;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(19)
public final class sp implements hh {
    public static final Bitmap.Config[] d;
    public static final Bitmap.Config[] e;
    public static final Bitmap.Config[] f;
    public static final Bitmap.Config[] g;
    public static final Bitmap.Config[] h;
    public final c a = new c();
    public final ae<b, Bitmap> b = new ae<>();
    public final Map<Bitmap.Config, NavigableMap<Integer, Integer>> c = new HashMap();

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            a = iArr;
            try {
                iArr[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[Bitmap.Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @VisibleForTesting
    public static final class b implements al {
        public final c a;
        public int b;
        public Bitmap.Config c;

        public b(c cVar) {
            this.a = cVar;
        }

        @VisibleForTesting
        public b(c cVar, int i, Bitmap.Config config) {
            this(cVar);
            this.b = i;
            this.c = config;
        }

        @Override // spi.al
        public final void a() {
            this.a.c(this);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.b == bVar.b && tu.b(this.c, bVar.c);
        }

        public final int hashCode() {
            int i = this.b * 31;
            Bitmap.Config config = this.c;
            return i + (config != null ? config.hashCode() : 0);
        }

        public final String toString() {
            return sp.h(this.b, this.c);
        }
    }

    @VisibleForTesting
    public static class c extends g1<b> {
        @Override // spi.g1
        public final al a() {
            return new b(this);
        }

        public final b d(int i, Bitmap.Config config) {
            b bVarB = b();
            bVarB.b = i;
            bVarB.c = config;
            return bVarB;
        }
    }

    static {
        Bitmap.Config[] configArr = (Bitmap.Config[]) Arrays.copyOf(new Bitmap.Config[]{Bitmap.Config.ARGB_8888, null}, 3);
        configArr[configArr.length - 1] = Bitmap.Config.RGBA_F16;
        d = configArr;
        e = configArr;
        f = new Bitmap.Config[]{Bitmap.Config.RGB_565};
        g = new Bitmap.Config[]{Bitmap.Config.ARGB_4444};
        h = new Bitmap.Config[]{Bitmap.Config.ALPHA_8};
    }

    public static String h(int i, Bitmap.Config config) {
        return "[" + i + "](" + config + ")";
    }

    @Override // spi.hh
    @Nullable
    public final Bitmap a() {
        Bitmap bitmapD = this.b.d();
        if (bitmapD != null) {
            g(Integer.valueOf(tu.c(bitmapD)), bitmapD);
        }
        return bitmapD;
    }

    @Override // spi.hh
    public final String b(int i, int i2, Bitmap.Config config) {
        return h(tu.d(config) * i * i2, config);
    }

    @Override // spi.hh
    public final void c(Bitmap bitmap) {
        b bVarD = this.a.d(tu.c(bitmap), bitmap.getConfig());
        this.b.b(bVarD, bitmap);
        NavigableMap<Integer, Integer> navigableMapI = i(bitmap.getConfig());
        Integer num = navigableMapI.get(Integer.valueOf(bVarD.b));
        navigableMapI.put(Integer.valueOf(bVarD.b), Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    @Override // spi.hh
    @Nullable
    public final Bitmap d(int i, int i2, Bitmap.Config config) {
        Bitmap.Config[] configArr;
        int iD = tu.d(config) * i * i2;
        b bVarD = this.a.d(iD, config);
        int i3 = 0;
        if (Bitmap.Config.RGBA_F16.equals(config)) {
            configArr = e;
        } else {
            int i4 = a.a[config.ordinal()];
            configArr = i4 != 1 ? i4 != 2 ? i4 != 3 ? i4 != 4 ? new Bitmap.Config[]{config} : h : g : f : d;
        }
        int length = configArr.length;
        while (true) {
            if (i3 >= length) {
                break;
            }
            Bitmap.Config config2 = configArr[i3];
            Integer numCeilingKey = i(config2).ceilingKey(Integer.valueOf(iD));
            if (numCeilingKey == null || numCeilingKey.intValue() > iD * 8) {
                i3++;
            } else if (numCeilingKey.intValue() != iD || (config2 != null ? !config2.equals(config) : config != null)) {
                this.a.c(bVarD);
                bVarD = this.a.d(numCeilingKey.intValue(), config2);
            }
        }
        Bitmap bitmapA = this.b.a(bVarD);
        if (bitmapA != null) {
            g(Integer.valueOf(bVarD.b), bitmapA);
            bitmapA.reconfigure(i, i2, config);
        }
        return bitmapA;
    }

    @Override // spi.hh
    public final int e(Bitmap bitmap) {
        return tu.c(bitmap);
    }

    @Override // spi.hh
    public final String f(Bitmap bitmap) {
        return h(tu.c(bitmap), bitmap.getConfig());
    }

    public final void g(Integer num, Bitmap bitmap) {
        NavigableMap<Integer, Integer> navigableMapI = i(bitmap.getConfig());
        Integer num2 = navigableMapI.get(num);
        if (num2 != null) {
            if (num2.intValue() == 1) {
                navigableMapI.remove(num);
                return;
            } else {
                navigableMapI.put(num, Integer.valueOf(num2.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + num + ", removed: " + f(bitmap) + ", this: " + this);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final NavigableMap<Integer, Integer> i(Bitmap.Config config) {
        NavigableMap<Integer, Integer> navigableMap = this.c.get(config);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.c.put(config, treeMap);
        return treeMap;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.HashMap, java.util.Map<android.graphics.Bitmap$Config, java.util.NavigableMap<java.lang.Integer, java.lang.Integer>>] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.HashMap, java.util.Map<android.graphics.Bitmap$Config, java.util.NavigableMap<java.lang.Integer, java.lang.Integer>>] */
    public final String toString() {
        StringBuilder sbK = e.k("SizeConfigStrategy{groupedMap=");
        sbK.append(this.b);
        sbK.append(", sortedSizes=(");
        for (Map.Entry entry : this.c.entrySet()) {
            sbK.append(entry.getKey());
            sbK.append('[');
            sbK.append(entry.getValue());
            sbK.append("], ");
        }
        if (!this.c.isEmpty()) {
            sbK.replace(sbK.length() - 2, sbK.length(), "");
        }
        sbK.append(")}");
        return sbK.toString();
    }
}
