package r1;

import android.graphics.Bitmap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* JADX INFO: compiled from: SizeConfigStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class m implements k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Bitmap.Config[] f9359d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Bitmap.Config[] f9360e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final Bitmap.Config[] f9361f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final Bitmap.Config[] f9362g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final Bitmap.Config[] f9363h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f9364a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g<b, Bitmap> f9365b = new g<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<Bitmap.Config, NavigableMap<Integer, Integer>> f9366c = new HashMap();

    /* JADX INFO: compiled from: SizeConfigStrategy.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9367a;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            f9367a = iArr;
            try {
                iArr[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9367a[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9367a[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9367a[Bitmap.Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: compiled from: SizeConfigStrategy.java */
    static final class b implements l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c f9368a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f9369b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Bitmap.Config f9370c;

        public b(c cVar) {
            this.f9368a = cVar;
        }

        @Override // r1.l
        public void a() {
            this.f9368a.c(this);
        }

        public void b(int i7, Bitmap.Config config) {
            this.f9369b = i7;
            this.f9370c = config;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f9369b == bVar.f9369b && k2.k.c(this.f9370c, bVar.f9370c);
        }

        public int hashCode() {
            int i7 = this.f9369b * 31;
            Bitmap.Config config = this.f9370c;
            return i7 + (config != null ? config.hashCode() : 0);
        }

        public String toString() {
            return m.i(this.f9369b, this.f9370c);
        }
    }

    /* JADX INFO: compiled from: SizeConfigStrategy.java */
    static class c extends r1.c<b> {
        c() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // r1.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public b a() {
            return new b(this);
        }

        public b e(int i7, Bitmap.Config config) {
            b bVarB = b();
            bVarB.b(i7, config);
            return bVarB;
        }
    }

    static {
        Bitmap.Config[] configArr = (Bitmap.Config[]) Arrays.copyOf(new Bitmap.Config[]{Bitmap.Config.ARGB_8888, null}, 3);
        configArr[configArr.length - 1] = Bitmap.Config.RGBA_F16;
        f9359d = configArr;
        f9360e = configArr;
        f9361f = new Bitmap.Config[]{Bitmap.Config.RGB_565};
        f9362g = new Bitmap.Config[]{Bitmap.Config.ARGB_4444};
        f9363h = new Bitmap.Config[]{Bitmap.Config.ALPHA_8};
    }

    private void g(Integer num, Bitmap bitmap) {
        NavigableMap<Integer, Integer> navigableMapK = k(bitmap.getConfig());
        Integer num2 = (Integer) navigableMapK.get(num);
        if (num2 != null) {
            if (num2.intValue() == 1) {
                navigableMapK.remove(num);
                return;
            } else {
                navigableMapK.put(num, Integer.valueOf(num2.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + num + ", removed: " + f(bitmap) + ", this: " + this);
    }

    private b h(int i7, Bitmap.Config config) {
        b bVarE = this.f9364a.e(i7, config);
        for (Bitmap.Config config2 : j(config)) {
            Integer numCeilingKey = k(config2).ceilingKey(Integer.valueOf(i7));
            if (numCeilingKey != null && numCeilingKey.intValue() <= i7 * 8) {
                if (numCeilingKey.intValue() == i7) {
                    if (config2 == null) {
                        if (config == null) {
                            return bVarE;
                        }
                    } else if (config2.equals(config)) {
                        return bVarE;
                    }
                }
                this.f9364a.c(bVarE);
                return this.f9364a.e(numCeilingKey.intValue(), config2);
            }
        }
        return bVarE;
    }

    static String i(int i7, Bitmap.Config config) {
        return "[" + i7 + "](" + config + ")";
    }

    private static Bitmap.Config[] j(Bitmap.Config config) {
        if (Bitmap.Config.RGBA_F16.equals(config)) {
            return f9360e;
        }
        int i7 = a.f9367a[config.ordinal()];
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? new Bitmap.Config[]{config} : f9363h : f9362g : f9361f : f9359d;
    }

    private NavigableMap<Integer, Integer> k(Bitmap.Config config) {
        NavigableMap<Integer, Integer> navigableMap = this.f9366c.get(config);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.f9366c.put(config, treeMap);
        return treeMap;
    }

    @Override // r1.k
    public int a(Bitmap bitmap) {
        return k2.k.g(bitmap);
    }

    @Override // r1.k
    public Bitmap b() {
        Bitmap bitmapF = this.f9365b.f();
        if (bitmapF != null) {
            g(Integer.valueOf(k2.k.g(bitmapF)), bitmapF);
        }
        return bitmapF;
    }

    @Override // r1.k
    public Bitmap c(int i7, int i8, Bitmap.Config config) {
        b bVarH = h(k2.k.f(i7, i8, config), config);
        Bitmap bitmapA = this.f9365b.a(bVarH);
        if (bitmapA != null) {
            g(Integer.valueOf(bVarH.f9369b), bitmapA);
            bitmapA.reconfigure(i7, i8, config);
        }
        return bitmapA;
    }

    @Override // r1.k
    public void d(Bitmap bitmap) {
        b bVarE = this.f9364a.e(k2.k.g(bitmap), bitmap.getConfig());
        this.f9365b.d(bVarE, bitmap);
        NavigableMap<Integer, Integer> navigableMapK = k(bitmap.getConfig());
        Integer num = (Integer) navigableMapK.get(Integer.valueOf(bVarE.f9369b));
        navigableMapK.put(Integer.valueOf(bVarE.f9369b), Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    @Override // r1.k
    public String e(int i7, int i8, Bitmap.Config config) {
        return i(k2.k.f(i7, i8, config), config);
    }

    @Override // r1.k
    public String f(Bitmap bitmap) {
        return i(k2.k.g(bitmap), bitmap.getConfig());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SizeConfigStrategy{groupedMap=");
        sb.append(this.f9365b);
        sb.append(", sortedSizes=(");
        for (Map.Entry<Bitmap.Config, NavigableMap<Integer, Integer>> entry : this.f9366c.entrySet()) {
            sb.append(entry.getKey());
            sb.append('[');
            sb.append(entry.getValue());
            sb.append("], ");
        }
        if (!this.f9366c.isEmpty()) {
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        sb.append(")}");
        return sb.toString();
    }
}
