package OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0Oo;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO0O0;
import android.media.AudioManager;
import com.android.volley.BuildConfig;
import java.util.Objects;
import kotlin.LazyThreadSafetyMode;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.j;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.o;

/* JADX INFO: loaded from: classes.dex */
public final class OooO00o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f542a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final kotlin.a<OooO00o> f543b = d.a(LazyThreadSafetyMode.SYNCHRONIZED, C0021OooO00o.f545OooO00o);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final kotlin.a f544c;

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0Oo.OooO00o$OooO00o, reason: collision with other inner class name */
    public static final class C0021OooO00o extends Lambda implements kotlin.jvm.b.a<OooO00o> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final C0021OooO00o f545OooO00o = new C0021OooO00o();

        public C0021OooO00o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o invoke() {
            return new OooO00o(null);
        }
    }

    public static final class OooO0OO extends Lambda implements kotlin.jvm.b.a<AudioManager> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0OO f546OooO00o = new OooO0OO();

        public OooO0OO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final AudioManager invoke() {
            Object systemService = OooO00o.OooO00o.OooO0O0.a.f39a.a().getSystemService("audio");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
            return (AudioManager) systemService;
        }
    }

    public static final class a {
        static {
            j.d(new PropertyReference1Impl(j.a(a.class), "instance", "getInstance()Lcom/loostone/libtuning/process/full_scene/bottom/AudioManagerCommunication;"));
        }

        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }

        public final OooO00o a() {
            return (OooO00o) OooO00o.f543b.getValue();
        }
    }

    public OooO00o() {
        this.f544c = d.b(OooO0OO.f546OooO00o);
    }

    public /* synthetic */ OooO00o(f fVar) {
        this();
    }

    public static /* synthetic */ String c(OooO00o oooO00o, OooO0O0 oooO0O0, boolean z, int i) {
        if ((i & 2) != 0) {
            z = true;
        }
        return oooO00o.b(oooO0O0, z);
    }

    public final String a(OooO0O0 oooO0O0) {
        h.d(oooO0O0, "command");
        return c(this, oooO0O0, false, 2);
    }

    public final String b(OooO0O0 oooO0O0, boolean z) {
        String strA = oooO0O0.a();
        String strD = OooO00o.OooO00o.OooO0O0.OooO0o0.b.d(f().getParameters("pm_kara_code"));
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.b("AMCommunication", h.i(" readByte code: ", strD));
        h.c(strD, "seed");
        if (StringsKt__StringsKt.o(strD, "pm_kara_code=", false, 2, null)) {
            h.c(strD, "seed");
            String strJ = o.j(strD, "pm_kara_code=", BuildConfig.FLAVOR, false, 4, null);
            if (h.a(strJ, BuildConfig.FLAVOR)) {
                bVar.d("AMCommunication", h.i("wrong with readByte: ", oooO0O0));
                h.c(strJ, "seed");
                return strJ;
            }
            AudioManager audioManagerF = f();
            OooO00o.OooO00o.OooO0O0.OooO0o0.g.a aVar = OooO00o.OooO00o.OooO0O0.OooO0o0.g.a.f38a;
            h.c(strJ, "seed");
            audioManagerF.setParameters(h.i("pm_kara=", aVar.b(strA, strJ)));
            String strD2 = OooO00o.OooO00o.OooO0O0.OooO0o0.b.d(f().getParameters("pm_kara"));
            bVar.b("AMCommunication", h.i("readByte result: ", strD2));
            h.c(strD2, "result");
            if (StringsKt__StringsKt.o(strD2, "pm_kara=", false, 2, null)) {
                h.c(strD2, "result");
                String strJ2 = o.j(strD2, "pm_kara=", BuildConfig.FLAVOR, false, 4, null);
                if (!z) {
                    h.c(strJ2, "{\n                    result\n                }");
                    return strJ2;
                }
                h.c(strJ2, "result");
                h.c(strJ, "seed");
                return aVar.a(strJ2, strJ);
            }
            bVar.d("AMCommunication", h.i("wrong with readByte result: ", strA));
        } else {
            bVar.d("AMCommunication", h.i("wrong with readByte code: ", strA));
        }
        return BuildConfig.FLAVOR;
    }

    public final void e(String str) {
        h.d(str, "params");
        f().setParameters(str);
    }

    public final AudioManager f() {
        return (AudioManager) this.f544c.getValue();
    }

    public final void g(OooO0O0 oooO0O0) {
        h.d(oooO0O0, "command");
        h(oooO0O0);
    }

    public final void h(OooO0O0 oooO0O0) {
        String strD = OooO00o.OooO00o.OooO0O0.OooO0o0.b.d(f().getParameters("pm_kara_code"));
        h.c(strD, "code");
        if (!StringsKt__StringsKt.o(strD, "pm_kara_code=", false, 2, null)) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("AMCommunication", h.i("wrong with write code: ", oooO0O0.a()));
            return;
        }
        h.c(strD, "code");
        String strJ = o.j(strD, "pm_kara_code=", BuildConfig.FLAVOR, false, 4, null);
        if (h.a(strJ, BuildConfig.FLAVOR)) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("AMCommunication", h.i("wrong with write: ", oooO0O0.a()));
            return;
        }
        AudioManager audioManagerF = f();
        OooO00o.OooO00o.OooO0O0.OooO0o0.g.a aVar = OooO00o.OooO00o.OooO0O0.OooO0o0.g.a.f38a;
        String strA = oooO0O0.a();
        h.c(strJ, "code");
        audioManagerF.setParameters(h.i("pm_kara=", aVar.b(strA, strJ)));
    }
}
