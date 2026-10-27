package B2;

import c5.InterfaceC1027d;
import com.stingray.karaoke.car.MainApplication;
import io.sentry.N0;
import io.sentry.O0;
import java.lang.reflect.Constructor;

/* JADX INFO: renamed from: B2.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0074n implements u, O0, InterfaceC1027d {
    public final /* synthetic */ int l;

    public /* synthetic */ C0074n(int i7) {
        this.l = i7;
    }

    public Constructor a() {
        switch (this.l) {
            case 0:
                if (Boolean.TRUE.equals(Class.forName("androidx.media3.decoder.flac.FlacLibrary").getMethod("isAvailable", null).invoke(null, null))) {
                    return Class.forName("androidx.media3.decoder.flac.FlacExtractor").asSubclass(r.class).getConstructor(Integer.TYPE);
                }
                return null;
            default:
                return Class.forName("androidx.media3.decoder.midi.MidiExtractor").asSubclass(r.class).getConstructor(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:98:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0366  */
    @Override // c5.InterfaceC1027d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object apply(java.lang.Object r51) {
        /*
            Method dump skipped, instruction units count: 1358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: B2.C0074n.apply(java.lang.Object):java.lang.Object");
    }

    @Override // B2.u
    public r[] f() {
        switch (this.l) {
            case 2:
                return new r[]{new C2.a()};
            case 5:
                return new r[]{new G2.d()};
            case 6:
                return new r[]{new H2.b()};
            case 18:
                return new r[]{new V2.l(Y2.j.f10647f, 16)};
            default:
                return new r[]{new W2.d()};
        }
    }

    @Override // io.sentry.O0
    public void i(N0 n02) {
        int i7 = MainApplication.f13005p;
        A7.m.f(n02, "it");
        n02.d("manufacturer", "byd8155f");
        n02.d("buildType", "release");
    }
}
