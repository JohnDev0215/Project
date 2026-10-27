package p;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import java.util.ArrayList;
import kotlin.NoWhenBranchMatchedException;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class qt implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final xy1 c;
    public final xy1 d;
    public final Object e;

    public /* synthetic */ qt(wy1 wy1Var, wy1 wy1Var2, wy1 wy1Var3, wy1 wy1Var4, int i) {
        this.a = i;
        this.b = wy1Var;
        this.c = wy1Var2;
        this.d = wy1Var3;
        this.e = wy1Var4;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.d;
        xy1 xy1Var2 = this.c;
        xy1 xy1Var3 = this.b;
        Object obj = this.e;
        switch (i) {
            case 0:
                return new pt((Context) xy1Var3.get(), (ut) xy1Var2.get(), (vx1) xy1Var.get(), (ad1) ((xy1) obj).get());
            case 1:
                return new j0((st1) xy1Var3.get(), (ki1) xy1Var2.get(), (jf0) xy1Var.get(), (up2) ((xy1) obj).get());
            case 2:
                OkHttpClient okHttpClient = (OkHttpClient) xy1Var3.get();
                cg2 cg2Var = (cg2) xy1Var2.get();
                ui0 ui0Var = (ui0) xy1Var.get();
                bc2 bc2Var = (bc2) ((xy1) obj).get();
                v03.j(ui0Var, "eventPublisher");
                v03.j(okHttpClient, "callFactory");
                v03.j(bc2Var, "settings");
                v03.j(cg2Var, "storage");
                xk1 xk1Var = bc2Var.a;
                if (xk1Var instanceof vk1) {
                    return new com.spotify.remoteconfig.fetcherimpl.a(ui0Var, okHttpClient, bc2Var, cg2Var);
                }
                if (xk1Var instanceof wk1) {
                    return new hq2(bc2Var, ui0Var, cg2Var, okHttpClient);
                }
                throw new NoWhenBranchMatchedException();
            case 3:
                ui0 ui0Var2 = (ui0) xy1Var3.get();
                bc2 bc2Var2 = (bc2) xy1Var2.get();
                cg2 cg2Var2 = (cg2) xy1Var.get();
                b42 b42Var = (b42) ((xy1) obj).get();
                v03.j(ui0Var2, "eventPublisher");
                v03.j(bc2Var2, "settings");
                v03.j(cg2Var2, "storage");
                v03.j(b42Var, "resolverDebugFactory");
                xk1 xk1Var2 = bc2Var2.a;
                if (xk1Var2 instanceof vk1) {
                    return new ze(bc2Var2.b, ui0Var2, bc2Var2.c, cg2Var2, b42Var);
                }
                if (xk1Var2 instanceof wk1) {
                    return new iq2(bc2Var2.b, ui0Var2, bc2Var2.c, cg2Var2, b42Var);
                }
                throw new NoWhenBranchMatchedException();
            case 4:
                OkHttpClient okHttpClient2 = (OkHttpClient) xy1Var3.get();
                bw2 bw2Var = (bw2) xy1Var2.get();
                ep1 ep1Var = (ep1) xy1Var.get();
                j2 j2Var = (j2) ((xy1) obj).get();
                OkHttpClient.Builder builderE = okHttpClient2.e();
                v03.j(ep1Var, "interceptor");
                ArrayList arrayList = builderE.c;
                arrayList.add(ep1Var);
                v03.j(bw2Var, "interceptor");
                arrayList.add(bw2Var);
                v03.j(j2Var, "interceptor");
                arrayList.add(j2Var);
                builderE.g = bw2Var;
                return new OkHttpClient(builderE);
            case 5:
                return new Cdo((vv2) xy1Var3.get(), (ki1) xy1Var2.get(), (mh1) xy1Var.get(), (w40) ((xy1) obj).get());
            case 6:
                return new iz2((Resources) xy1Var3.get(), (ps1) xy1Var2.get(), (ki1) xy1Var.get(), (mh1) ((xy1) obj).get());
            case 7:
                return new jz2((Resources) xy1Var3.get(), (xb1) xy1Var2.get(), (ki1) xy1Var.get(), (m) ((xy1) obj).get());
            case 8:
                return new jc2((Context) xy1Var3.get(), (x00) xy1Var2.get(), (fh0) xy1Var.get(), (m) ((xy1) obj).get());
            default:
                Handler handler = (Handler) xy1Var3.get();
                ad adVar = (ad) xy1Var2.get();
                md mdVar = (md) xy1Var.get();
                ((yn) obj).getClass();
                v03.j(handler, "handler");
                v03.j(adVar, "audioDuckingController");
                v03.j(mdVar, "audioManagerProxy");
                return new com.spotify.audiofocus.coreinternal.a(handler, adVar, mdVar);
        }
    }

    public qt(yn ynVar, wy1 wy1Var, wy1 wy1Var2) {
        ke2 ke2Var = ln1.d;
        this.a = 9;
        this.e = ynVar;
        this.b = ke2Var;
        this.c = wy1Var;
        this.d = wy1Var2;
    }
}
