package p;

import com.spotify.connect.esperanto.proto.ConnectMessages$PullRequest;
import com.spotify.playerlimited.player.models.GaiaState;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.SingleSource;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ea1 implements io.reactivex.rxjava3.functions.o, io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ ia1 e;

    public /* synthetic */ ea1(ia1 ia1Var, int i) {
        this.c = i;
        this.e = ia1Var;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public void accept(Object obj) {
        int i = this.c;
        ia1 ia1Var = this.e;
        switch (i) {
            case 0:
                ia1Var.u((ju1) obj, 15000);
                break;
            default:
                ia1Var.u((ju1) obj, -15000);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        px pxVar = px.e;
        int i = this.c;
        ia1 ia1Var = this.e;
        switch (i) {
            case 0:
                ia1Var.getClass();
                return ((a01) obj).d ? ((i10) ia1Var.h).d() : io.reactivex.rxjava3.internal.operators.completable.d.c;
            case 1:
                ia1Var.getClass();
                boolean z = ((a01) obj).d;
                x00 x00Var = ia1Var.h;
                if (z) {
                    i10 i10Var = (i10) x00Var;
                    i10Var.getClass();
                    Completable completableIgnoreElement = ((nh0) i10Var.c).a(new zt1("automotive-playbackcontroller")).map(new h10(i10Var, 2)).ignoreElement();
                    v03.i(completableIgnoreElement, "override fun resume(feat…   .ignoreElement()\n    }");
                    return completableIgnoreElement;
                }
                hh0 hh0Var = ((i10) x00Var).l;
                hh0Var.getClass();
                com.google.protobuf.i iVarBuild = ConnectMessages$PullRequest.e().build();
                v03.i(iVarBuild, "newBuilder().build()");
                SingleSource map = hh0Var.a.callSingle("spotify.connect.esperanto.proto.ConnectService", "Pull", (ConnectMessages$PullRequest) iVarBuild).map(pxVar);
                v03.i(map, "callSingle(\"spotify.conn…     }\n                })");
                return new io.reactivex.rxjava3.internal.operators.completable.e(5, map);
            default:
                ia1Var.getClass();
                if (((GaiaState) obj).a) {
                    return io.reactivex.rxjava3.internal.operators.completable.d.c;
                }
                hh0 hh0Var2 = ((i10) ia1Var.h).l;
                hh0Var2.getClass();
                com.google.protobuf.i iVarBuild2 = ConnectMessages$PullRequest.e().build();
                v03.i(iVarBuild2, "newBuilder().build()");
                SingleSource map2 = hh0Var2.a.callSingle("spotify.connect.esperanto.proto.ConnectService", "Pull", (ConnectMessages$PullRequest) iVarBuild2).map(pxVar);
                v03.i(map2, "callSingle(\"spotify.conn…     }\n                })");
                return new io.reactivex.rxjava3.internal.operators.completable.e(5, map2);
        }
    }
}
