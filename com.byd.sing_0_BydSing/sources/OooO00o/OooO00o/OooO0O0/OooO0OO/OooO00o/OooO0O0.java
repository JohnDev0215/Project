package OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o;

import com.android.volley.BuildConfig;
import com.loostone.libtuning.component.provider.TuningProvider;
import java.util.Arrays;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0O0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f7a = "060";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f8b = BuildConfig.FLAVOR;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f9c = BuildConfig.FLAVOR;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f10d = OooO0o.READ.OooO0O0();
    public int e = OooO0OO.NONE.OooO0O0();
    public String f = BuildConfig.FLAVOR;
    public String g = BuildConfig.FLAVOR;

    public enum OooO {
        DEFAULT(60);

        public final int OooO0OO;

        OooO(int i) {
            this.OooO0OO = i;
        }

        public final int OooO0O0() {
            return this.OooO0OO;
        }
    }

    public enum OooO0OO {
        NONE(0),
        CAPTURE(1),
        PLAYBACK(2),
        MUSIC(3),
        ALL(4);

        public final int OooO0oO;

        OooO0OO(int i) {
            this.OooO0oO = i;
        }

        public final int OooO0O0() {
            return this.OooO0oO;
        }
    }

    public enum OooO0o {
        READ(1),
        WRITE(2);

        public final int OooO0Oo;

        OooO0o(int i) {
            this.OooO0Oo = i;
        }

        public final int OooO0O0() {
            return this.OooO0Oo;
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f14a = "060";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f15b = BuildConfig.FLAVOR;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f16c = BuildConfig.FLAVOR;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f17d = OooO0o.READ.OooO0O0();
        public int e = OooO0OO.NONE.OooO0O0();
        public String f = "000";
        public String g = BuildConfig.FLAVOR;

        public static /* synthetic */ a c(a aVar, int i, int i2, int i3) {
            if ((i3 & 2) != 0) {
                i2 = 1;
            }
            return aVar.b(i, i2);
        }

        public static /* synthetic */ a d(a aVar, OooO oooO, int i) {
            if ((i & 1) != 0) {
                oooO = OooO.DEFAULT;
            }
            return aVar.g(oooO);
        }

        public final a a(int i) {
            String str = String.format("%03d", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            h.c(str, "java.lang.String.format(format, *args)");
            this.f15b = str;
            return this;
        }

        public final a b(int i, int i2) {
            String str = String.format("%0" + i2 + 'd', Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            h.c(str, "java.lang.String.format(format, *args)");
            this.g = str;
            String str2 = String.format("%03d", Arrays.copyOf(new Object[]{Integer.valueOf(str.length())}, 1));
            h.c(str2, "java.lang.String.format(format, *args)");
            this.f = str2;
            return this;
        }

        public final a e(OooO0OO oooO0OO) {
            h.d(oooO0OO, TuningProvider.TYPE);
            this.e = oooO0OO.OooO0O0();
            return this;
        }

        public final a f(OooO0o oooO0o) {
            h.d(oooO0o, "sign");
            this.f17d = oooO0o.OooO0O0();
            return this;
        }

        public final a g(OooO oooO) {
            h.d(oooO, "version");
            String str = String.format("%03d", Arrays.copyOf(new Object[]{Integer.valueOf(oooO.OooO0O0())}, 1));
            h.c(str, "java.lang.String.format(format, *args)");
            this.f14a = str;
            return this;
        }

        public final a h(String str) {
            h.d(str, "params");
            this.g = str;
            String str2 = String.format("%03d", Arrays.copyOf(new Object[]{Integer.valueOf(str.length())}, 1));
            h.c(str2, "java.lang.String.format(format, *args)");
            this.f = str2;
            return this;
        }

        public final OooO0O0 i() {
            OooO0O0 oooO0O0 = new OooO0O0();
            oooO0O0.f7a = this.f14a;
            oooO0O0.f8b = this.f15b;
            oooO0O0.f9c = this.f16c;
            oooO0O0.f10d = this.f17d;
            oooO0O0.e = this.e;
            oooO0O0.f = this.f;
            oooO0O0.g = this.g;
            return oooO0O0;
        }

        public final a j(int i) {
            String str = String.format("%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            h.c(str, "java.lang.String.format(format, *args)");
            this.f16c = str;
            return this;
        }
    }

    public static final class b {
        public b() {
        }

        public /* synthetic */ b(f fVar) {
            this();
        }
    }

    static {
        new b(null);
    }

    public final String a() {
        return this.f7a + this.f8b + this.f9c + this.f10d + this.e + this.f + this.g;
    }
}
