package N5;

import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import V8.AbstractC0828q;
import X5.D0;
import X5.G0;
import android.content.ComponentName;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.stingray.companion.CompanionManager;
import com.stingray.companion.model.PlaybackMode;
import com.stingray.companion.model.PlayerInfo;
import com.stingray.companion.model.PlayerState;
import com.stingray.karaoke.car.player.LeadVocalState;
import com.stingray.karaoke.car.player.PlayerActionType;
import com.stingray.karaoke.car.player.PlayerService;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.kca.model.AudioResource;
import com.stingray.kca.model.AudioResponse;
import com.stingray.kca.model.Filter;
import com.stingray.kca.model.KaraokeSongResponseV2;
import com.stingray.kca.model.VideoBackground;
import com.stingray.kca.model.VideoBackgroundResource;
import com.stingray.shared.logs.segment.ActionOwner;
import com.stingray.shared.logs.segment.player.ContentCompletedReason;
import com.stingray.shared.logs.segment.player.PlaybackInterruptedReason;
import com.stingray.shared.logs.segment.player.PlaybackSetting;
import com.stingray.shared.logs.segment.player.VideoPlayerBackground;
import h2.AbstractC1343A;
import h8.AbstractC1387d;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import k3.C1672v;
import k3.C1674w;
import k3.E1;
import k3.RunnableC1666s;
import k3.y1;
import k6.C1691C;
import k6.C1704l;
import n2.C1923x;
import n6.C1972e;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import timber.log.Timber;

/* JADX INFO: loaded from: classes.dex */
public final class Z {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f4623A;
    public ContentCompletedReason B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C0738e0 f4624C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final X8.c f4625D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final X8.c f4626E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final LinkedList f4627F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final AudioManager f4628G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final V8.f0 f4629H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final V8.f0 f4630I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final V8.N f4631J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final V8.f0 f4632K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final V8.N f4633L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final V8.f0 f4634M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final V8.N f4635N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final V8.f0 f4636O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final V8.N f4637P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final V8.f0 f4638Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final V8.N f4639R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final V8.f0 f4640S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final V8.N f4641T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public final V8.f0 f4642U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public final V8.N f4643V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final V8.f0 f4644W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public final V8.N f4645X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public final V8.f0 f4646Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public final V8.N f4647Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4648a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final V8.f0 f4649a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CompanionManager f4650b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public final V8.N f4651b0;
    public final q0 c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final V8.f0 f4652c0;
    public final C1704l d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public final V8.N f4653d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final D6.e f4654e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final V8.f0 f4655e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final j6.n f4656f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final V8.f0 f4657f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final n6.u f4658g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final V8.N f4659g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C1972e f4660h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final V8.N f4661h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C1691C f4662i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final G0 f4663j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final W5.e f4664k;
    public final I6.d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final e6.k f4665m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f4666n;
    public ActionOwner o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C1672v f4667p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public P f4668q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C1674w f4669r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C0453i f4670s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public AudioFocusRequest f4671t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f4672u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f4673v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f4674w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f4675x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f4676y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final ArrayList f4677z;

    public Z(Context context, CompanionManager companionManager, q0 q0Var, C1704l c1704l, D6.e eVar, j6.n nVar, n6.u uVar, C1972e c1972e, B6.b bVar, C1691C c1691c, G0 g02, W5.e eVar2, I6.d dVar, e6.k kVar) {
        A7.m.f(context, "context");
        A7.m.f(companionManager, "companionManager");
        A7.m.f(q0Var, "songQueue");
        A7.m.f(c1704l, "driveModeManager");
        A7.m.f(eVar, "playerLogger");
        A7.m.f(nVar, "profileManager");
        A7.m.f(uVar, "microphoneManager");
        A7.m.f(c1972e, "gameLoopManager");
        A7.m.f(bVar, "featureLogger");
        A7.m.f(c1691c, "uxRestrictionManager");
        A7.m.f(g02, "scoringCountdownManager");
        A7.m.f(eVar2, "appNavigationManager");
        A7.m.f(dVar, "soundFxManager");
        A7.m.f(kVar, "searchManager");
        this.f4648a = context;
        this.f4650b = companionManager;
        this.c = q0Var;
        this.d = c1704l;
        this.f4654e = eVar;
        this.f4656f = nVar;
        this.f4658g = uVar;
        this.f4660h = c1972e;
        this.f4662i = c1691c;
        this.f4663j = g02;
        this.f4664k = eVar2;
        this.l = dVar;
        this.f4665m = kVar;
        this.o = ActionOwner.HOST;
        this.f4676y = -1L;
        this.f4677z = new ArrayList();
        this.B = ContentCompletedReason.COMPLETED;
        Boolean bool = Boolean.FALSE;
        this.f4624C = C0735d.O(bool, T.S.f8224q);
        Z8.e eVar3 = S8.E.f8084a;
        T8.d dVar2 = X8.m.f10573a;
        X8.c cVarA = AbstractC0727y.a(dVar2);
        this.f4625D = cVarA;
        X8.c cVarA2 = AbstractC0727y.a(h6.q.x(dVar2, AbstractC0727y.c()));
        this.f4626E = cVarA2;
        this.f4627F = new LinkedList();
        Object systemService = context.getSystemService("audio");
        A7.m.d(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        this.f4628G = (AudioManager) systemService;
        this.f4629H = AbstractC0828q.c(null);
        V8.f0 f0VarC = AbstractC0828q.c(0L);
        this.f4630I = f0VarC;
        this.f4631J = new V8.N(f0VarC);
        V8.f0 f0VarC2 = AbstractC0828q.c(bool);
        this.f4632K = f0VarC2;
        this.f4633L = new V8.N(f0VarC2);
        V8.f0 f0VarC3 = AbstractC0828q.c(PlayerStatus.STOPPED);
        this.f4634M = f0VarC3;
        this.f4635N = new V8.N(f0VarC3);
        V8.f0 f0VarC4 = AbstractC0828q.c(null);
        this.f4636O = f0VarC4;
        this.f4637P = new V8.N(f0VarC4);
        V8.f0 f0VarC5 = AbstractC0828q.c(bool);
        this.f4638Q = f0VarC5;
        this.f4639R = new V8.N(f0VarC5);
        V8.f0 f0VarC6 = AbstractC0828q.c(bool);
        this.f4640S = f0VarC6;
        this.f4641T = new V8.N(f0VarC6);
        V8.f0 f0VarC7 = AbstractC0828q.c(null);
        this.f4642U = f0VarC7;
        this.f4643V = new V8.N(f0VarC7);
        V8.f0 f0VarC8 = AbstractC0828q.c(null);
        this.f4644W = f0VarC8;
        this.f4645X = new V8.N(f0VarC8);
        V8.f0 f0VarC9 = AbstractC0828q.c(bool);
        this.f4646Y = f0VarC9;
        this.f4647Z = new V8.N(f0VarC9);
        V8.f0 f0VarC10 = AbstractC0828q.c(Boolean.TRUE);
        this.f4649a0 = f0VarC10;
        this.f4651b0 = new V8.N(f0VarC10);
        V8.f0 f0VarC11 = AbstractC0828q.c(bool);
        this.f4652c0 = f0VarC11;
        this.f4653d0 = new V8.N(f0VarC11);
        this.f4655e0 = AbstractC0828q.c(bool);
        V8.f0 f0VarC12 = AbstractC0828q.c(bool);
        this.f4657f0 = f0VarC12;
        this.f4659g0 = new V8.N(f0VarC12);
        this.f4661h0 = AbstractC0828q.m(new V8.J(f0VarC7, f0VarC12, new D(this, null)), cVarA2, V8.V.a(), LeadVocalState.LeadVocalOff);
        AbstractC0727y.w(cVarA, null, null, new C0455k(this, null), 3);
        A7.v vVar = new A7.v();
        vVar.l = true;
        AbstractC0727y.w(cVarA, null, null, new C0457m(vVar, this, null), 3);
    }

    public static /* synthetic */ Enum H(Z z6, PlayerActionType playerActionType, Set set, long j3, s7.i iVar, int i7) {
        Set setT = AbstractC1985B.t(PlayerStatus.ERROR);
        if ((i7 & 8) != 0) {
            j3 = 20000;
        }
        return z6.G(playerActionType, set, setT, j3, iVar);
    }

    public static AudioResource g(List list) {
        Object obj = null;
        if (list == null) {
            return null;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (A7.m.a(((AudioResource) next).f13070e, Boolean.TRUE)) {
                obj = next;
                break;
            }
        }
        return (AudioResource) obj;
    }

    public static AudioResource h(List list) {
        Object obj = null;
        if (list == null) {
            return null;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (A7.m.a(((AudioResource) next).f13070e, Boolean.FALSE)) {
                obj = next;
                break;
            }
        }
        return (AudioResource) obj;
    }

    public static boolean j(List list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (A7.m.a(((AudioResource) it.next()).f13070e, Boolean.TRUE)) {
                return true;
            }
        }
        return false;
    }

    public static boolean k(List list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (A7.m.a(((AudioResource) it.next()).f13070e, Boolean.FALSE)) {
                return true;
            }
        }
        return false;
    }

    public static void y(Z z6, boolean z10, boolean z11, ActionOwner actionOwner, boolean z12, boolean z13, int i7) {
        KaraokeSongResponseV2 karaokeSongResponseV2;
        boolean z14 = (i7 & 8) != 0 ? false : z12;
        if ((i7 & 16) != 0) {
            f0 f0Var = (f0) z6.f4636O.getValue();
            z13 = (f0Var == null || (karaokeSongResponseV2 = f0Var.f4691a) == null) ? false : A7.m.a(karaokeSongResponseV2.f13146n, Boolean.TRUE);
        }
        z6.x(z10, z11, actionOwner, z14, z13);
    }

    public final void A(long j3, int i7) {
        A7.m.f("setCurrentMediaItem: " + i7 + " " + j3, "text");
        if (i7 >= 0) {
            C1672v c1672v = this.f4667p;
            if (c1672v == null) {
                A7.m.j("mediaController");
                throw null;
            }
            c1672v.I0(j3, i7);
            f0 f0Var = (f0) AbstractC1998m.b0(i7, (List) ((V8.f0) this.c.f4752v.l).getValue());
            if ((f0Var != null ? f0Var.f4692b : null) != null) {
                this.f4642U.l(f0Var.f4692b);
                this.f4644W.l(f0Var.c);
            }
        }
    }

    public final void B(int i7) {
        A(0L, i7);
        q0 q0Var = this.c;
        if (q0Var.f4754x && this.f4636O.getValue() != null && this.f4634M.getValue() != PlayerStatus.COUNTDOWN && !((Boolean) this.f4638Q.getValue()).booleanValue()) {
            Z8.e eVar = S8.E.f8084a;
            AbstractC0727y.w(AbstractC0727y.a(X8.m.f10573a), null, null, new M(this, null), 3);
            v();
        }
        if (q0Var.f4754x) {
            return;
        }
        C(PlayerStatus.PAUSED);
    }

    public final void C(PlayerStatus playerStatus) {
        A7.m.f("PlayerManager setPlayerStatus: " + playerStatus, "text");
        this.f4634M.l(playerStatus);
    }

    public final void D(int i7, ActionOwner actionOwner) {
        C1672v c1672v = this.f4667p;
        if (c1672v == null) {
            A7.m.j("mediaController");
            throw null;
        }
        q0 q0Var = this.c;
        q0Var.getClass();
        if (i7 == 0 || i7 == 1 || i7 == 2) {
            V8.f0 f0Var = q0Var.o;
            if (i7 != ((Number) f0Var.getValue()).intValue()) {
                q0Var.f4746p.l(f0Var.getValue());
                f0Var.m(null, Integer.valueOf(i7));
            } else {
                i7 = ((Number) f0Var.getValue()).intValue();
            }
        } else {
            i7 = 0;
        }
        c1672v.h(i7);
        if (actionOwner != null) {
            p(PlaybackSetting.REPEAT_MODE, actionOwner);
        }
    }

    public final void E(boolean z6, ActionOwner actionOwner) {
        A7.m.f(actionOwner, "actionOwner");
        d(new C0451g(PlayerActionType.SkipNext, new C0462s(this, actionOwner, null, z6)));
    }

    public final void F(boolean z6) {
        if (z6) {
            C1672v c1672v = this.f4667p;
            if (c1672v == null) {
                A7.m.j("mediaController");
                throw null;
            }
            Bundle bundle = Bundle.EMPTY;
            c1672v.Y0(new y1("com.stingray.karaoke.SET_IS_FAVORITE", bundle), bundle);
            return;
        }
        C1672v c1672v2 = this.f4667p;
        if (c1672v2 == null) {
            A7.m.j("mediaController");
            throw null;
        }
        Bundle bundle2 = Bundle.EMPTY;
        c1672v2.Y0(new y1("com.stingray.karaoke.SET_IS_NOT_FAVORITE", bundle2), bundle2);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Enum G(com.stingray.karaoke.car.player.PlayerActionType r6, java.util.Set r7, java.util.Set r8, long r9, s7.AbstractC2410c r11) throws java.lang.Throwable {
        /*
            r5 = this;
            java.lang.String r0 = "PlayerManager: "
            boolean r1 = r11 instanceof N5.W
            if (r1 == 0) goto L15
            r1 = r11
            N5.W r1 = (N5.W) r1
            int r2 = r1.f4619p
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.f4619p = r2
            goto L1a
        L15:
            N5.W r1 = new N5.W
            r1.<init>(r5, r11)
        L1a:
            java.lang.Object r11 = r1.f4618n
            r7.a r2 = r7.EnumC2303a.l
            int r3 = r1.f4619p
            r4 = 1
            if (r3 == 0) goto L3b
            if (r3 != r4) goto L33
            java.util.Set r5 = r1.f4617m
            r8 = r5
            java.util.Set r8 = (java.util.Set) r8
            java.util.Set r5 = r1.l
            r7 = r5
            java.util.Set r7 = (java.util.Set) r7
            h8.AbstractC1386c.u(r11)     // Catch: S8.p0 -> La8
            goto L89
        L33:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3b:
            h8.AbstractC1386c.u(r11)
            V8.f0 r11 = r5.f4634M     // Catch: S8.p0 -> La8
            java.lang.Object r11 = r11.getValue()     // Catch: S8.p0 -> La8
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: S8.p0 -> La8
            r3.<init>(r0)     // Catch: S8.p0 -> La8
            r3.append(r6)     // Catch: S8.p0 -> La8
            java.lang.String r6 = "Action waitForStatusChange: "
            r3.append(r6)     // Catch: S8.p0 -> La8
            r3.append(r7)     // Catch: S8.p0 -> La8
            java.lang.String r6 = ", Current status: "
            r3.append(r6)     // Catch: S8.p0 -> La8
            r3.append(r11)     // Catch: S8.p0 -> La8
            java.lang.String r6 = r3.toString()     // Catch: S8.p0 -> La8
            java.lang.String r11 = "text"
            A7.m.f(r6, r11)     // Catch: S8.p0 -> La8
            N5.Y r6 = new N5.Y     // Catch: S8.p0 -> La8
            r11 = 0
            r6.<init>(r5, r7, r11)     // Catch: S8.p0 -> La8
            r5 = r7
            java.util.Set r5 = (java.util.Set) r5     // Catch: S8.p0 -> La8
            r1.l = r5     // Catch: S8.p0 -> La8
            r5 = r8
            java.util.Set r5 = (java.util.Set) r5     // Catch: S8.p0 -> La8
            r1.f4617m = r5     // Catch: S8.p0 -> La8
            r1.f4619p = r4     // Catch: S8.p0 -> La8
            r3 = 0
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 <= 0) goto La0
            S8.q0 r5 = new S8.q0     // Catch: S8.p0 -> La8
            r5.<init>(r9, r1)     // Catch: S8.p0 -> La8
            java.lang.Object r11 = S8.AbstractC0727y.B(r5, r6)     // Catch: S8.p0 -> La8
            if (r11 != r2) goto L89
            return r2
        L89:
            com.stingray.karaoke.car.player.PlayerStatus r11 = (com.stingray.karaoke.car.player.PlayerStatus) r11     // Catch: S8.p0 -> La8
            boolean r5 = r7.contains(r11)     // Catch: S8.p0 -> La8
            if (r5 == 0) goto L94
            N5.a r5 = N5.EnumC0445a.l     // Catch: S8.p0 -> La8
            goto Laa
        L94:
            boolean r5 = r8.contains(r11)     // Catch: S8.p0 -> La8
            if (r5 == 0) goto L9d
            N5.a r5 = N5.EnumC0445a.f4678m     // Catch: S8.p0 -> La8
            goto Laa
        L9d:
            N5.a r5 = N5.EnumC0445a.f4679n     // Catch: S8.p0 -> La8
            goto Laa
        La0:
            S8.p0 r5 = new S8.p0     // Catch: S8.p0 -> La8
            java.lang.String r6 = "Timed out immediately"
            r5.<init>(r6, r11)     // Catch: S8.p0 -> La8
            throw r5     // Catch: S8.p0 -> La8
        La8:
            N5.a r5 = N5.EnumC0445a.f4679n
        Laa:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: N5.Z.G(com.stingray.karaoke.car.player.PlayerActionType, java.util.Set, java.util.Set, long, s7.c):java.lang.Enum");
    }

    public final D6.a a(KaraokeSongResponseV2 karaokeSongResponseV2) {
        String str;
        VideoBackground videoBackground;
        List list;
        VideoBackgroundResource videoBackgroundResource;
        String str2;
        Filter filter;
        String str3 = null;
        if (karaokeSongResponseV2 == null) {
            f0 f0Var = (f0) this.f4636O.getValue();
            karaokeSongResponseV2 = f0Var != null ? f0Var.f4691a : null;
        }
        String str4 = "";
        if (karaokeSongResponseV2 == null || (str = karaokeSongResponseV2.f13136a) == null) {
            str = "";
        }
        if (karaokeSongResponseV2 != null && (filter = karaokeSongResponseV2.f13138e) != null) {
            str3 = filter.f13092b;
        }
        List listJ = AbstractC1387d.j(String.valueOf(str3));
        long j3 = 1000;
        int i7 = (int) (this.f4666n / j3);
        int i10 = (int) (this.f4672u / j3);
        AudioResponse audioResponse = (AudioResponse) this.f4642U.getValue();
        if (audioResponse != null && (videoBackground = audioResponse.c) != null && (list = videoBackground.f13233b) != null && (videoBackgroundResource = (VideoBackgroundResource) AbstractC1998m.a0(list)) != null && (str2 = videoBackgroundResource.f13236a) != null) {
            str4 = str2;
        }
        D6.a aVar = new D6.a();
        aVar.f1194a = str;
        aVar.f1195b = "FULL";
        aVar.c = listJ;
        aVar.d = i7;
        aVar.f1196e = i10;
        aVar.f1197f = str4;
        return aVar;
    }

    public final D6.b b(boolean z6) {
        int iIntValue;
        KaraokeSongResponseV2 karaokeSongResponseV2;
        Boolean bool;
        V8.f0 f0Var = this.f4636O;
        Object value = f0Var.getValue();
        boolean zBooleanValue = false;
        q0 q0Var = this.c;
        if (value != null) {
            Object value2 = f0Var.getValue();
            A7.m.c(value2);
            q0Var.getClass();
            String str = ((f0) value2).d;
            A7.m.f(str, "uniqueId");
            Iterator it = q0Var.f4740h.iterator();
            iIntValue = 0;
            while (true) {
                if (!it.hasNext()) {
                    iIntValue = -1;
                    break;
                }
                if (A7.m.a(((f0) it.next()).d, str)) {
                    break;
                }
                iIntValue++;
            }
            if (iIntValue == -1) {
                iIntValue = q0Var.f4743k;
            }
        } else {
            iIntValue = ((Number) ((V8.f0) q0Var.f4750t.l).getValue()).intValue();
        }
        int iIntValue2 = ((Number) ((V8.f0) q0Var.f4748r.l).getValue()).intValue();
        boolean zBooleanValue2 = true;
        String str2 = iIntValue2 != 1 ? iIntValue2 != 2 ? "NO_REPEAT" : "REPEAT" : "REPEAT_ONE";
        VideoPlayerBackground videoPlayerBackground = VideoPlayerBackground.VIDEO;
        boolean zBooleanValue3 = ((Boolean) this.f4664k.f9820e.getValue()).booleanValue();
        if (!z6) {
            zBooleanValue2 = ((Boolean) this.f4657f0.getValue()).booleanValue();
        } else if (((V8.f0) this.f4661h0.l).getValue() != LeadVocalState.LeadVocalOn) {
            zBooleanValue2 = false;
        }
        CompanionManager.Companion.getClass();
        String str3 = (String) CompanionManager.companionSessionId.getValue();
        boolean zBooleanValue4 = ((Boolean) this.f4638Q.getValue()).booleanValue();
        f0 f0Var2 = (f0) f0Var.getValue();
        if (f0Var2 != null && (karaokeSongResponseV2 = f0Var2.f4691a) != null && (bool = karaokeSongResponseV2.f13146n) != null) {
            zBooleanValue = bool.booleanValue();
        }
        A7.m.f(videoPlayerBackground, "videoPlayerBackground");
        A7.m.f(str3, "lobbyId");
        D6.b bVar = new D6.b();
        bVar.f1198a = "KML";
        bVar.f1199b = videoPlayerBackground;
        bVar.c = zBooleanValue3;
        bVar.d = zBooleanValue2;
        bVar.f1200e = null;
        bVar.f1201f = str3;
        bVar.f1202g = iIntValue;
        bVar.f1203h = str2;
        bVar.f1204i = "";
        bVar.f1205j = zBooleanValue4;
        bVar.f1206k = zBooleanValue;
        return bVar;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [N5.i] */
    public final void c() {
        if (((Boolean) this.f4655e0.getValue()).booleanValue()) {
            return;
        }
        P p10 = new P(this);
        this.f4668q = p10;
        this.f4650b.addListener(p10);
        this.f4670s = new AudioManager.OnAudioFocusChangeListener() { // from class: N5.i
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i7) {
                Z z6 = this.f4698a;
                if (i7 == -2 || i7 == -1) {
                    Object value = z6.f4634M.getValue();
                    PlayerStatus playerStatus = PlayerStatus.PLAYING;
                    C0738e0 c0738e0 = z6.f4624C;
                    if (value != playerStatus) {
                        c0738e0.setValue(Boolean.FALSE);
                        return;
                    } else {
                        c0738e0.setValue(Boolean.valueOf(i7 == -2));
                        z6.s(ActionOwner.HOST);
                        return;
                    }
                }
                if (i7 == 1 || i7 == 2) {
                    Object value2 = z6.f4634M.getValue();
                    PlayerStatus playerStatus2 = PlayerStatus.PAUSED;
                    C0738e0 c0738e02 = z6.f4624C;
                    if (value2 == playerStatus2 && ((Boolean) c0738e02.getValue()).booleanValue()) {
                        z6.t(ActionOwner.HOST);
                    }
                    c0738e02.setValue(Boolean.FALSE);
                }
            }
        };
        AudioFocusRequest.Builder audioAttributes = new AudioFocusRequest.Builder(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build());
        C0453i c0453i = this.f4670s;
        if (c0453i == null) {
            A7.m.j("audioFocusChangeListener");
            throw null;
        }
        this.f4671t = audioAttributes.setOnAudioFocusChangeListener(c0453i).build();
        PlayerService.f13010w = new C0452h(this, 0);
        PlayerService.f13011x = new Q(this);
        PlayerService.f13012y = new C0452h(this, 1);
        Context context = this.f4648a;
        E1 e12 = new E1(context, new ComponentName(context, (Class<?>) PlayerService.class));
        Bundle bundle = Bundle.EMPTY;
        Y4.e eVar = new Y4.e(25);
        Looper looperQ = AbstractC1343A.q();
        C1674w c1674w = new C1674w(looperQ);
        AbstractC1343A.K(new Handler(looperQ), new RunnableC1666s(c1674w, new C1672v(context, e12, bundle, eVar, looperQ, c1674w, e12.f17758a.n() ? new io.sentry.internal.debugmeta.c(new k2.i(context)) : null), 0));
        this.f4669r = c1674w;
        c1674w.b(new h5.t(c1674w, 0, new Q(this)), context.getMainExecutor());
        AbstractC0727y.w(this.f4626E, null, null, new C0469z(this, null), 3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0072, code lost:
    
        if ((r1 != null ? r1.f4693a : null) == com.stingray.karaoke.car.player.PlayerActionType.Pause) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(N5.C0451g r8) {
        /*
            r7 = this;
            V8.f0 r0 = r7.f4629H
            java.lang.Object r1 = r0.getValue()
            N5.g r1 = (N5.C0451g) r1
            r2 = 0
            if (r1 == 0) goto Le
            com.stingray.karaoke.car.player.PlayerActionType r1 = r1.f4693a
            goto Lf
        Le:
            r1 = r2
        Lf:
            com.stingray.karaoke.car.player.PlayerActionType r3 = r8.f4693a
            java.lang.String r4 = "text"
            java.lang.String r5 = "PlayerManager: Action "
            if (r1 != r3) goto L2d
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r5)
            r7.append(r3)
            java.lang.String r8 = " is invalid - already executing"
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            A7.m.f(r7, r4)
            goto Lb0
        L2d:
            com.stingray.karaoke.car.player.PlayerActionType r1 = com.stingray.karaoke.car.player.PlayerActionType.SkipPrevious
            if (r3 == r1) goto L35
            com.stingray.karaoke.car.player.PlayerActionType r1 = com.stingray.karaoke.car.player.PlayerActionType.SkipNext
            if (r3 != r1) goto L50
        L35:
            java.lang.Object r1 = r0.getValue()
            if (r1 == 0) goto L50
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r5)
            r7.append(r3)
            java.lang.String r8 = " is invalid - another action is executing"
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            A7.m.f(r7, r4)
            goto Lb0
        L50:
            com.stingray.karaoke.car.player.PlayerActionType r1 = com.stingray.karaoke.car.player.PlayerActionType.Play
            if (r3 == r1) goto L58
            com.stingray.karaoke.car.player.PlayerActionType r6 = com.stingray.karaoke.car.player.PlayerActionType.Pause
            if (r3 != r6) goto L75
        L58:
            java.lang.Object r6 = r0.getValue()
            N5.g r6 = (N5.C0451g) r6
            if (r6 == 0) goto L63
            com.stingray.karaoke.car.player.PlayerActionType r6 = r6.f4693a
            goto L64
        L63:
            r6 = r2
        L64:
            if (r6 == r1) goto L9c
            java.lang.Object r1 = r0.getValue()
            N5.g r1 = (N5.C0451g) r1
            if (r1 == 0) goto L70
            com.stingray.karaoke.car.player.PlayerActionType r2 = r1.f4693a
        L70:
            com.stingray.karaoke.car.player.PlayerActionType r1 = com.stingray.karaoke.car.player.PlayerActionType.Pause
            if (r2 != r1) goto L75
            goto L9c
        L75:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "PlayerManager: Enqueueing action "
            r1.<init>(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            A7.m.f(r1, r4)
            java.util.LinkedList r1 = r7.f4627F
            r1.add(r8)
            java.lang.Object r8 = r0.getValue()
            if (r8 != 0) goto Lb0
            int r8 = r1.size()
            r0 = 1
            if (r8 != r0) goto Lb0
            r7.e()
            goto Lb0
        L9c:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r5)
            r7.append(r3)
            java.lang.String r8 = " is invalid - play/pause action already executing"
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            A7.m.f(r7, r4)
        Lb0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: N5.Z.d(N5.g):void");
    }

    public final void e() {
        C0451g c0451g = (C0451g) this.f4627F.poll();
        if (c0451g != null) {
            V8.f0 f0Var = this.f4629H;
            f0Var.getClass();
            f0Var.m(null, c0451g);
            AbstractC0727y.w(this.f4625D, null, null, new B(c0451g, this, null), 3);
        }
    }

    public final AudioResource f(List list) {
        boolean zBooleanValue = ((Boolean) this.f4657f0.getValue()).booleanValue();
        if (zBooleanValue && j(list)) {
            return g(list);
        }
        if ((zBooleanValue || !k(list)) && !k(list)) {
            if (j(list)) {
                return g(list);
            }
            return null;
        }
        return h(list);
    }

    public final long i() {
        C1672v c1672v = this.f4667p;
        if (c1672v != null) {
            return c1672v.N0();
        }
        A7.m.j("mediaController");
        throw null;
    }

    public final boolean l() {
        return this.f4676y != -1;
    }

    public final void m(ContentCompletedReason contentCompletedReason, ActionOwner actionOwner) {
        f0 f0Var = (f0) this.f4636O.getValue();
        if (f0Var != null) {
            D6.b bVarB = b(true);
            D6.a aVarA = a(null);
            Z8.e eVar = S8.E.f8084a;
            AbstractC0727y.w(AbstractC0727y.a(X8.m.f10573a), null, null, new E(contentCompletedReason, aVarA, this, f0Var, bVarB, actionOwner, null), 3);
        }
    }

    public final void n(KaraokeSongResponseV2 karaokeSongResponseV2, ActionOwner actionOwner) {
        this.f4675x = false;
        Z8.e eVar = S8.E.f8084a;
        AbstractC0727y.w(AbstractC0727y.a(X8.m.f10573a), null, null, new F(this, karaokeSongResponseV2, actionOwner, null), 3);
    }

    public final void o() {
        Z8.e eVar = S8.E.f8084a;
        AbstractC0727y.w(AbstractC0727y.a(X8.m.f10573a), null, null, new G(this, null), 3);
    }

    public final void p(PlaybackSetting playbackSetting, ActionOwner actionOwner) {
        Z8.e eVar = S8.E.f8084a;
        AbstractC0727y.w(AbstractC0727y.a(X8.m.f10573a), null, null, new L(this, playbackSetting, actionOwner, null), 3);
    }

    public final void q() {
        Boolean bool = Boolean.FALSE;
        V8.f0 f0Var = this.f4646Y;
        f0Var.getClass();
        f0Var.m(null, bool);
        Boolean bool2 = Boolean.TRUE;
        V8.f0 f0Var2 = this.f4649a0;
        f0Var2.getClass();
        f0Var2.m(null, bool2);
        this.f4660h.b();
    }

    public final void r(Exception exc) {
        A7.m.f(exc, "error");
        String str = "on player error " + exc;
        A7.m.f(str, "error");
        B9.a aVar = Timber.f22577a;
        aVar.e("");
        aVar.b(str, new Object[0]);
        PlaybackInterruptedReason playbackInterruptedReason = PlaybackInterruptedReason.PLAYER_ERROR;
        String strG = M5.b.g("Player error: ", exc.getMessage());
        Z8.e eVar = S8.E.f8084a;
        AbstractC0727y.w(AbstractC0727y.a(X8.m.f10573a), null, null, new H(this, playbackInterruptedReason, strG, null), 3);
        C1672v c1672v = this.f4667p;
        if (c1672v == null) {
            A7.m.j("mediaController");
            throw null;
        }
        c1672v.b();
        this.f4663j.b();
        String string = UUID.randomUUID().toString();
        A7.m.e(string, "toString(...)");
        for (D0 d02 : this.f4677z) {
            d02.getClass();
            d02.f10158x.s(ActionOwner.HOST);
            d02.f10107E.b();
            Boolean bool = Boolean.FALSE;
            V8.f0 f0Var = d02.f10119O;
            f0Var.getClass();
            f0Var.m(null, bool);
            n7.z.b(string, true, new b0.b(634440067, new I5.y(d02, 19), true), 6);
        }
        AbstractC0727y.w(this.f4626E, null, null, new V(this, null), 3);
        C(PlayerStatus.ERROR);
    }

    public final void s(ActionOwner actionOwner) {
        A7.m.f(actionOwner, "actionOwner");
        d(new C0451g(PlayerActionType.Pause, new C0459o(this, actionOwner, null)));
    }

    public final void t(ActionOwner actionOwner) {
        A7.m.f(actionOwner, "actionOwner");
        d(new C0451g(PlayerActionType.Play, new C0460p(this, actionOwner, null), new C0452h(this, 2)));
    }

    public final void u(ActionOwner actionOwner) {
        A7.m.f(actionOwner, "actionOwner");
        d(new C0451g(PlayerActionType.Replay, new C0461q(this, actionOwner, null)));
    }

    public final void v() {
        AudioFocusRequest audioFocusRequest = this.f4671t;
        if (audioFocusRequest == null) {
            A7.m.j("audioFocusRequest");
            throw null;
        }
        if (this.f4628G.requestAudioFocus(audioFocusRequest) == 1) {
            C1672v c1672v = this.f4667p;
            if (c1672v != null) {
                c1672v.q(true);
            } else {
                A7.m.j("mediaController");
                throw null;
            }
        }
    }

    public final void w(long j3, ActionOwner actionOwner) {
        A7.m.f(actionOwner, "actionOwner");
        d(new C0451g(PlayerActionType.Seek, new r(this, j3, actionOwner, null)));
    }

    public final void x(boolean z6, boolean z10, ActionOwner actionOwner, boolean z11, boolean z12) {
        A7.m.f(actionOwner, "actionOwner");
        if (z10) {
            Boolean boolValueOf = Boolean.valueOf(z6);
            V8.f0 f0Var = this.f4640S;
            f0Var.getClass();
            f0Var.m(null, boolValueOf);
        }
        boolean z13 = z6 && z12;
        V8.f0 f0Var2 = this.f4638Q;
        if (((Boolean) f0Var2.getValue()).booleanValue() == z13) {
            if (z10 || !z11) {
                return;
            }
            t(actionOwner);
            return;
        }
        Boolean boolValueOf2 = Boolean.valueOf(z6);
        f0Var2.getClass();
        f0Var2.m(null, boolValueOf2);
        p(PlaybackSetting.GAMING_MODE, actionOwner);
        C1923x c1923x = PlayerService.f13008u;
        if (c1923x == null) {
            A7.m.j("player");
            throw null;
        }
        c1923x.A1();
        if (c1923x.f19738M != z6) {
            c1923x.f19738M = z6;
            h2.x xVar = c1923x.l.f19525t;
            xVar.getClass();
            h2.w wVarB = h2.x.b();
            wVarB.f15246a = xVar.f15248a.obtainMessage(23, z6 ? 1 : 0, 0);
            wVarB.b();
        }
        if (!z6) {
            D(((Number) ((V8.f0) this.c.f4749s.l).getValue()).intValue(), null);
            this.f4663j.b();
            if (z11) {
                t(actionOwner);
                return;
            }
            return;
        }
        C1672v c1672v = this.f4667p;
        if (c1672v == null) {
            A7.m.j("mediaController");
            throw null;
        }
        c1672v.k(0L);
        C1672v c1672v2 = this.f4667p;
        if (c1672v2 == null) {
            A7.m.j("mediaController");
            throw null;
        }
        c1672v2.b();
        D(0, null);
        if (z10 || !z11) {
            return;
        }
        t(actionOwner);
    }

    public final void z() {
        PlayerState playerState;
        KaraokeSongResponseV2 karaokeSongResponseV2;
        f0 f0Var = (f0) this.f4636O.getValue();
        String str = (f0Var == null || (karaokeSongResponseV2 = f0Var.f4691a) == null) ? null : karaokeSongResponseV2.f13136a;
        V8.f0 f0Var2 = this.f4634M;
        boolean z6 = f0Var2.getValue() == PlayerStatus.BUFFERING && i() == 0;
        PlaybackMode playbackMode = ((Boolean) this.f4638Q.getValue()).booleanValue() ? PlaybackMode.SCORING : PlaybackMode.NORMAL;
        CompanionManager companionManager = this.f4650b;
        if (str == null || f0Var2.getValue() == PlayerStatus.STOPPED) {
            PlayerState playerState2 = PlayerState.IDLE;
            String string = ZonedDateTime.now(ZoneOffset.UTC).toString();
            A7.m.e(string, "toString(...)");
            companionManager.updateCurrentPlayerInfo(new PlayerInfo(null, playerState2, 0L, string, playbackMode, false, 4700L, 0L, 0L, 416, null));
            return;
        }
        if (z6) {
            playerState = PlayerState.IDLE;
        } else {
            switch (AbstractC0458n.f4723a[((PlayerStatus) f0Var2.getValue()).ordinal()]) {
                case 1:
                    playerState = PlayerState.IDLE;
                    break;
                case 2:
                    playerState = PlayerState.PLAYING;
                    break;
                case 3:
                    playerState = PlayerState.PAUSED;
                    break;
                case 4:
                    playerState = PlayerState.IDLE;
                    break;
                case 5:
                    playerState = PlayerState.IDLE;
                    break;
                case 6:
                    playerState = PlayerState.ERROR;
                    break;
                case 7:
                    playerState = PlayerState.IDLE;
                    break;
                case 8:
                    playerState = PlayerState.IDLE;
                    break;
                default:
                    throw new A9.k();
            }
        }
        long jI = z6 ? 0L : i();
        String string2 = ZonedDateTime.now(ZoneOffset.UTC).toString();
        A7.m.e(string2, "toString(...)");
        companionManager.updateCurrentPlayerInfo(new PlayerInfo(str, playerState, jI, string2, playbackMode, false, 4700L, 0L, 0L, 416, null));
    }
}
