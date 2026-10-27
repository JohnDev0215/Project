package a.a.d.b;

import com.loostone.libtuning.data.config.HardEffectDglItem;
import com.loostone.libtuning.data.config.HardEffectMicItem;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f566c;
    public static byte[] e;
    public static byte[] f;
    public static List<HardEffectMicItem> g;
    public static List<HardEffectDglItem> h;
    public static List<HardEffectDglItem> i;
    public static boolean j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f564a = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static HashMap<String, Integer> f567d = new HashMap<>();

    public final boolean a() {
        return j;
    }

    public final int b() {
        return f566c;
    }

    public final void c(int i2) {
        f566c = i2;
    }

    public final void d(List<HardEffectDglItem> list) {
        h = list;
    }

    public final void e(boolean z) {
        j = z;
    }

    public final void f(byte[] bArr) {
        e = bArr;
    }

    public final void g(int i2) {
        f565b = i2;
    }

    public final void h(List<HardEffectMicItem> list) {
        g = list;
    }

    public final void i(byte[] bArr) {
        f = bArr;
    }

    public final byte[] j() {
        return e;
    }

    public final List<HardEffectDglItem> k() {
        return h;
    }

    public final void l(List<HardEffectDglItem> list) {
        i = list;
    }

    public final List<HardEffectMicItem> m() {
        return g;
    }

    public final byte[] n() {
        return f;
    }

    public final List<HardEffectDglItem> o() {
        return i;
    }

    public final HashMap<String, Integer> p() {
        return f567d;
    }

    public final int q() {
        return f565b;
    }
}
