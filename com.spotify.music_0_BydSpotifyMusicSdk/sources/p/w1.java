package p;

import com.google.gson.internal.LinkedTreeMap;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.connectivity.ConnectionStateController$ConnectionState;
import com.spotify.base.java.logging.Logger;
import com.spotify.player.model.PlayerError;
import java.util.ArrayDeque;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class w1 implements io.reactivex.rxjava3.functions.o, io.reactivex.rxjava3.functions.g, io.reactivex.rxjava3.functions.p, y4, xo1 {
    public static final w1 e = new w1(0);
    public static final w1 k = new w1(1);
    public static final w1 l = new w1(0);
    public static final w1 m = new w1(1);
    public static final w1 n = new w1(2);
    public static final w1 o = new w1(3);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final w1 f203p = new w1(0);
    public static final w1 q = new w1(1);
    public final int c;

    public /* synthetic */ w1(int i) {
        this.c = i;
    }

    @Override // p.y4
    public Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        switch (this.c) {
            case 0:
                v03.j(u71Var, "reader");
                v03.j(cVar, "customScalarAdapters");
                Object objJ = he1.J(u71Var);
                v03.h(objJ);
                return objJ;
            case 1:
                v03.j(u71Var, "reader");
                v03.j(cVar, "customScalarAdapters");
                return Boolean.valueOf(u71Var.w0());
            case 2:
                v03.j(u71Var, "reader");
                v03.j(cVar, "customScalarAdapters");
                return Double.valueOf(u71Var.T());
            case 3:
            default:
                v03.j(u71Var, "reader");
                v03.j(cVar, "customScalarAdapters");
                String strU = u71Var.u();
                v03.h(strU);
                return strU;
            case 4:
                v03.j(u71Var, "reader");
                v03.j(cVar, "customScalarAdapters");
                return Integer.valueOf(u71Var.D0());
        }
    }

    @Override // io.reactivex.rxjava3.functions.g
    public void accept(Object obj) {
        int i = this.c;
        switch (i) {
            case 0:
                Throwable th = (Throwable) obj;
                switch (i) {
                    case 0:
                        Logger.c(th, "Error observing player errors", new Object[0]);
                        break;
                    default:
                        Logger.c(th, "Exception while calling resume.", new Object[0]);
                        break;
                }
                break;
            case 1:
                PlayerError playerError = (PlayerError) obj;
                v03.j(playerError, "error");
                Logger.b("Pick and shuffle cap prevented playback of track %s", playerError.e());
                break;
            case 2:
                Throwable th2 = (Throwable) obj;
                switch (i) {
                    case 0:
                        Logger.c(th2, "Error observing player errors", new Object[0]);
                        break;
                    default:
                        Logger.c(th2, "Exception while calling resume.", new Object[0]);
                        break;
                }
                break;
            default:
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        switch (this.c) {
            case 0:
                return new tx1();
            default:
                return Integer.valueOf((int) (((Float) obj).floatValue() * 100));
        }
    }

    public void b(a81 a81Var, com.apollographql.apollo3.api.c cVar, Object obj) {
        switch (this.c) {
            case 0:
                v03.j(a81Var, "writer");
                v03.j(cVar, "customScalarAdapters");
                v03.j(obj, "value");
                f60.U(a81Var, obj);
                break;
            case 1:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                v03.j(a81Var, "writer");
                v03.j(cVar, "customScalarAdapters");
                a81Var.n0(zBooleanValue);
                break;
            case 2:
                double dDoubleValue = ((Number) obj).doubleValue();
                v03.j(a81Var, "writer");
                v03.j(cVar, "customScalarAdapters");
                a81Var.G(dDoubleValue);
                break;
            case 3:
            default:
                String str = (String) obj;
                v03.j(a81Var, "writer");
                v03.j(cVar, "customScalarAdapters");
                v03.j(str, "value");
                a81Var.N(str);
                break;
            case 4:
                int iIntValue = ((Number) obj).intValue();
                v03.j(a81Var, "writer");
                v03.j(cVar, "customScalarAdapters");
                a81Var.A(iIntValue);
                break;
        }
    }

    @Override // p.xo1
    public Object k() {
        switch (this.c) {
            case 0:
                return new ConcurrentHashMap();
            case 1:
                return new LinkedTreeMap();
            default:
                return new ArrayDeque();
        }
    }

    @Override // io.reactivex.rxjava3.functions.p
    public boolean test(Object obj) {
        switch (this.c) {
            case 0:
                if (((Pair) obj).c() == ConnectionStateController$ConnectionState.k) {
                }
                break;
            default:
                if (((LoginState) obj) != LoginState.c) {
                }
                break;
        }
        return true;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w1(int i, int i2) {
        this(0);
        this.c = i;
        int i3 = 1;
        if (i != 1) {
        } else {
            this(i3);
        }
    }
}
