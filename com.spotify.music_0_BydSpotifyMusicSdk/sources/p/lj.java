package p;

import com.google.common.base.Optional;
import com.google.firebase.concurrent.ExecutorsRegistrar;
import com.google.firebase.concurrent.UiExecutor;
import com.google.firebase.installations.FirebaseInstallationsRegistrar;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.auth.model.LoginResponse;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.connectiontype.OfflineState;
import com.spotify.connectivity.platformconnectiontype.ConnectionStateObservable;
import com.spotify.connectivity.platformconnectiontype.OfflineStateControllerCosmos;
import com.spotify.connectivity.sessionstate.SessionState;
import com.spotify.contexts.DeviceAndroid;
import com.spotify.eventsender.Fragment;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.PlayerQueue;
import com.spotify.player.model.PlayerState;
import com.spotify.webapi.service.models.ArtistSimple;
import com.spotify.webapi.service.models.Entity;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class lj implements oz0, io.reactivex.rxjava3.functions.c, aw, io.reactivex.rxjava3.functions.g, uw1, io.reactivex.rxjava3.functions.p {
    public final /* synthetic */ int c;

    public /* synthetic */ lj(int i) {
        this.c = i;
    }

    @Override // p.aw
    public Object a(jm2 jm2Var) {
        switch (this.c) {
            case 0:
                return (ScheduledExecutorService) ExecutorsRegistrar.a.get();
            case 1:
                return (ScheduledExecutorService) ExecutorsRegistrar.c.get();
            case 2:
                return (ScheduledExecutorService) ExecutorsRegistrar.b.get();
            case 3:
                w81 w81Var = ExecutorsRegistrar.a;
                return UiExecutor.c;
            case 4:
                return FirebaseInstallationsRegistrar.lambda$getComponents$0(jm2Var);
            default:
                Set setE = jm2Var.e(dz1.a(ei.class));
                p01 p01Var = p01.k;
                if (p01Var == null) {
                    synchronized (p01.class) {
                        try {
                            p01Var = p01.k;
                            if (p01Var == null) {
                                p01Var = new p01(0);
                                p01.k = p01Var;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return new d80(setE, p01Var);
        }
    }

    @Override // io.reactivex.rxjava3.functions.g
    public void accept(Object obj) {
        switch (this.c) {
            case 0:
                Logger.c((Throwable) obj, "Exception while calling skipToPrevious.", new Object[0]);
                break;
            case 1:
                Logger.c((Throwable) obj, "Exception while calling setPlaybackSpeed.", new Object[0]);
                break;
            case 2:
                Logger.c((Throwable) obj, "Error subscribing to player state.", new Object[0]);
                break;
            case 3:
                Logger.c((Throwable) obj, "Exception while calling seekToPosition.", new Object[0]);
                break;
            case 4:
                Logger.c((Throwable) obj, "Exception while calling setShuffle.", new Object[0]);
                break;
            case 5:
                Logger.c((Throwable) obj, "Exception while calling setPlayerQueue.", new Object[0]);
                break;
            case 6:
                ((OfflineState) obj).offline();
                break;
            default:
                OfflineStateControllerCosmos.lambda$new$1((Throwable) obj);
                break;
        }
    }

    @Override // p.oz0
    public Object apply(Object obj) {
        switch (this.c) {
            case 0:
                return ((if2) obj).toString();
            case 1:
                ArtistSimple artistSimple = (ArtistSimple) obj;
                if (artistSimple != null) {
                    return artistSimple.name;
                }
                return null;
            case 2:
                return new if2((String) obj);
            case 3:
                return Long.valueOf(((fi0) obj).a);
            case 4:
                Fragment fragment = (Fragment) obj;
                return new pr1(fragment.getName(), fragment.getData());
            case 5:
                try {
                    return DeviceAndroid.l((ByteString) ((pr1) obj).b).j();
                } catch (InvalidProtocolBufferException unused) {
                    return "";
                }
            case 6:
                Fragment fragment2 = (Fragment) obj;
                return new pr1(fragment2.getName(), fragment2.getData());
            default:
                return Long.valueOf(((fi0) obj).a);
        }
    }

    @Override // io.reactivex.rxjava3.functions.c
    public Object k(Object obj, Object obj2) {
        switch (this.c) {
            case 0:
                return (LoginState) obj;
            case 1:
                PlayerQueue playerQueue = (PlayerQueue) obj;
                ju1 ju1Var = (ju1) obj2;
                boolean zIsEmpty = ju1Var.a.w().g().isEmpty();
                PlayerState playerState = ju1Var.a;
                return new ku1(zIsEmpty, playerState.n().c(), (String) playerState.c().get("context_description"), ju1Var.f() != null ? ju1Var.f().f() : null, playerQueue);
            case 2:
                return new pr1((Throwable) obj, (Integer) obj2);
            case 3:
                return ConnectionStateObservable.toConnectionState((OfflineState.State) obj, ((Boolean) obj2).booleanValue());
            default:
                return (Boolean) obj2;
        }
    }

    @Override // io.reactivex.rxjava3.functions.p
    public boolean test(Object obj) {
        LoginState loginState = LoginState.c;
        LoginState loginState2 = LoginState.e;
        switch (this.c) {
            case 0:
                return ((LoginState) obj) != loginState;
            case 1:
                return ((Integer) obj).intValue() == 401;
            case 2:
                return Objects.equals(((LoginResponse) obj).a, "sessionInfo");
            case 3:
                return ((tx1) obj).b;
            case 4:
                Logger.c((Throwable) obj, "Play from search failed because of an exception.", new Object[0]);
                return true;
            case 5:
                return ((LoginState) obj) != loginState;
            case 6:
                ju1 ju1Var = (ju1) obj;
                return ju1Var.g().d() || ju1Var.d() || !ju1Var.a.k();
            case 7:
                ju1 ju1Var2 = (ju1) obj;
                return ju1Var2.g().d() || ju1Var2.d() || !ju1Var2.a.k();
            case 8:
                return ((ju1) obj).g().d();
            case 9:
                ju1 ju1Var3 = (ju1) obj;
                return (ju1Var3.a.k() && ju1Var3.f() != null) || !ju1Var3.a.k() || ju1Var3.d();
            case 10:
                ku1 ku1Var = (ku1) obj;
                Optional optionalF = ku1Var.e.f();
                boolean zD = optionalF.d();
                String str = ku1Var.d;
                if (zD) {
                    return v03.b(str, ((ContextTrack) optionalF.c()).f());
                }
                return str == null;
            case 11:
                return ((LoginState) obj) == loginState2;
            case 12:
                return ((LoginState) obj) == loginState2;
            case 13:
                LoginState loginState3 = (LoginState) obj;
                return loginState3 == LoginState.k || loginState3 == LoginState.l;
            case 14:
                return ((OfflineState) obj).offlineState() == OfflineState.State.ONLINE;
            case 15:
                return ((Map) obj).containsKey("error");
            case 16:
                return ((Map) obj).get("error") instanceof String;
            case 17:
                return ((Optional) obj).d();
            case 18:
                return ((Boolean) obj).booleanValue();
            case 19:
                return ((SessionState) obj).connected();
            default:
                return ((Boolean) obj).booleanValue();
        }
    }

    @Override // p.uw1
    /* JADX INFO: renamed from: apply, reason: collision with other method in class */
    public boolean mo348apply(Object obj) {
        switch (this.c) {
            case 0:
                return ((String) obj) != null;
            case 1:
                return ((Entity) obj).isPlayable();
            default:
                return ((String) ((pr1) obj).a).equals("context_device_android");
        }
    }
}
