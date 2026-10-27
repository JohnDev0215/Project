package N5;

import S8.AbstractC0727y;
import V8.AbstractC0828q;
import com.stingray.companion.CompanionManager;
import com.stingray.companion.model.Song;
import com.stingray.companion.model.SongQueueInfo;
import com.stingray.karaoke.car.player.SongQueuePosition;
import com.stingray.kca.model.KaraokeSongResponseV2;
import com.stingray.shared.logs.segment.ActionOwner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;

/* JADX INFO: loaded from: classes.dex */
public final class q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CompanionManager f4735a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final K5.F f4736b;
    public final F6.a c;
    public final k6.r d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final K5.t f4737e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final X8.c f4738f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final X8.c f4739g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f4740h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f4741i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public f0 f4742j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4743k;
    public final V8.f0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final androidx.lifecycle.J f4744m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final V8.f0 f4745n;
    public final V8.f0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final V8.f0 f4746p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final V8.S f4747q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final V8.N f4748r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final V8.N f4749s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final V8.N f4750t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final androidx.lifecycle.J f4751u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final V8.N f4752v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final V8.M f4753w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f4754x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final V8.N f4755y;

    public q0(CompanionManager companionManager, K5.F f10, F6.a aVar, k6.r rVar, K5.t tVar) {
        A7.m.f(companionManager, "companionManager");
        A7.m.f(f10, "songRepository");
        A7.m.f(aVar, "songQueueLogger");
        A7.m.f(rVar, "parentalControlsManager");
        A7.m.f(tVar, "dataStoreManager");
        this.f4735a = companionManager;
        this.f4736b = f10;
        this.c = aVar;
        this.d = rVar;
        this.f4737e = tVar;
        Z8.e eVar = S8.E.f8084a;
        X8.c cVarA = AbstractC0727y.a(X8.m.f10573a);
        this.f4738f = cVarA;
        this.f4739g = AbstractC0727y.a(Z8.d.f11010n);
        this.f4740h = new ArrayList();
        this.f4741i = new ArrayList();
        this.f4743k = -1;
        V8.f0 f0VarC = AbstractC0828q.c(-1);
        this.l = f0VarC;
        androidx.lifecycle.J j3 = new androidx.lifecycle.J(0);
        this.f4744m = j3;
        V8.f0 f0VarC2 = AbstractC0828q.c(n7.u.l);
        this.f4745n = f0VarC2;
        V8.f0 f0VarC3 = AbstractC0828q.c(0);
        this.o = f0VarC3;
        V8.f0 f0VarC4 = AbstractC0828q.c(f0VarC3.getValue());
        this.f4746p = f0VarC4;
        V8.S sB = AbstractC0828q.b(0, 0, null, 7);
        this.f4747q = sB;
        this.f4748r = new V8.N(f0VarC3);
        this.f4749s = new V8.N(f0VarC4);
        this.f4750t = new V8.N(f0VarC);
        this.f4751u = j3;
        this.f4752v = new V8.N(f0VarC2);
        this.f4753w = new V8.M(sB);
        this.f4755y = AbstractC0828q.m(new V8.J(f0VarC, f0VarC2, new N1.d0(3, null, 1)), cVarA, V8.V.f9597a, Boolean.FALSE);
        AbstractC0727y.w(cVarA, null, null, new h0(this, null), 3);
    }

    public final boolean a(ArrayList arrayList, SongQueuePosition songQueuePosition, String str, String str2, boolean z6, F6.b bVar) {
        A7.m.f(songQueuePosition, "position");
        synchronized (this) {
            if (z6) {
                try {
                    this.f4740h.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (arrayList.isEmpty()) {
                return false;
            }
            int i7 = j0.f4702a[songQueuePosition.ordinal()];
            if (i7 == 1) {
                return b(arrayList, str, str2, this.f4743k + 1, true, z6, ActionOwner.HOST, Integer.valueOf(arrayList.size()), bVar);
            }
            if (i7 == 2) {
                return b(arrayList, str, str2, this.f4743k + 1, false, z6, ActionOwner.HOST, Integer.valueOf(arrayList.size()), bVar);
            }
            if (i7 == 3) {
                return b(arrayList, str, str2, this.f4740h.size(), false, z6, ActionOwner.HOST, Integer.valueOf(arrayList.size()), bVar);
            }
            if (i7 == 4) {
                return false;
            }
            throw new A9.k();
        }
    }

    public final boolean b(List list, String str, String str2, int i7, boolean z6, boolean z10, ActionOwner actionOwner, Integer num, F6.b bVar) {
        A7.m.f(actionOwner, "actionOwner");
        if (list.isEmpty()) {
            return false;
        }
        AbstractC0727y.w(this.f4738f, null, null, new k0(z10, this, actionOwner, i7, list, num, z6, str, bVar, str2, null), 3);
        return true;
    }

    public final void c(f0 f0Var, int i7, boolean z6, boolean z10, boolean z11, boolean z12, SongQueuePosition songQueuePosition, ActionOwner actionOwner, F6.b bVar) {
        Integer num;
        A7.m.f(songQueuePosition, "position");
        A7.m.f(actionOwner, "actionOwner");
        synchronized (this) {
            if (z11) {
                try {
                    this.f4740h.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
            int iMin = Math.min(i7, this.f4740h.size());
            i0 i0Var = new i0(this.f4743k, this.f4742j, true, z10, actionOwner);
            if (z6) {
                if (this.f4743k >= 0) {
                    i0Var.f4699a = iMin;
                    i0Var.f4700b = f0Var;
                    this.f4740h.add(iMin, f0Var);
                } else {
                    this.f4740h.add(f0Var);
                    i0Var.f4699a = 0;
                    i0Var.f4700b = f0Var;
                }
            } else if (this.f4743k >= 0) {
                this.f4740h.add(iMin, f0Var);
            } else {
                this.f4740h.add(f0Var);
                i0Var.f4699a = 0;
                i0Var.f4700b = f0Var;
            }
            if (z12) {
                this.f4740h.remove(0);
                int i10 = i0Var.f4699a;
                if (i10 == 0) {
                    i0Var.f4700b = (f0) this.f4740h.get(0);
                    i0Var.d = true;
                } else {
                    i0Var.f4699a = i10 - 1;
                }
                num = 1;
            } else {
                num = null;
            }
            F6.a aVar = this.c;
            String str = f0Var.f4691a.f13136a;
            A7.m.c(str);
            aVar.d(str, z11, actionOwner, num, songQueuePosition.name(), bVar != null ? bVar.f2425a : null, bVar != null ? bVar.f2426b : null);
            m(i0Var, false);
        }
    }

    public final void d(f0 f0Var, SongQueuePosition songQueuePosition, boolean z6, boolean z10, F6.b bVar) {
        int i7;
        A7.m.f(songQueuePosition, "position");
        synchronized (this) {
            try {
                boolean z11 = 100 == ((List) ((V8.f0) this.f4752v.l).getValue()).size();
                if (z10 || (i7 = j0.f4702a[songQueuePosition.ordinal()]) == 1) {
                    c(f0Var, this.f4743k + 1, true, true, z6, z11, songQueuePosition, ActionOwner.HOST, bVar);
                } else if (i7 == 2) {
                    c(f0Var, this.f4743k + 1, false, false, z6, z11, songQueuePosition, ActionOwner.HOST, bVar);
                } else if (i7 == 3) {
                    c(f0Var, this.f4740h.size(), false, false, z6, z11, songQueuePosition, ActionOwner.HOST, bVar);
                } else {
                    if (i7 != 4) {
                        throw new A9.k();
                    }
                    c(f0Var, this.f4743k + 1, true, true, z6, z11, songQueuePosition, ActionOwner.HOST, bVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void e(int i7) {
        ArrayList arrayList = this.f4740h;
        f0 f0Var = (f0) AbstractC1998m.b0(i7, arrayList);
        if (f0Var != null) {
            i0 i0Var = new i0(i7, f0Var, false, true, null, 16);
            V8.N n10 = this.f4748r;
            boolean z6 = true;
            if (((Number) ((V8.f0) n10.l).getValue()).intValue() != 1 && (((Number) ((V8.f0) n10.l).getValue()).intValue() != 2 || arrayList.size() != 1)) {
                z6 = false;
            }
            m(i0Var, z6);
        }
    }

    public final void f(ActionOwner actionOwner) {
        A7.m.f(actionOwner, "actionOwner");
        synchronized (this) {
            i0 i0Var = new i0(-1, null, !this.f4740h.isEmpty(), false, actionOwner, 8);
            this.f4740h.clear();
            F6.a aVar = this.c;
            aVar.getClass();
            aVar.a("Karaoke Queue Clear", new B5.z(), actionOwner);
            m(i0Var, false);
        }
    }

    public final boolean g() {
        int i7 = this.f4743k;
        ArrayList arrayList = this.f4740h;
        if (i7 >= arrayList.size() - 1) {
            return (((Number) ((V8.f0) this.f4748r.l).getValue()).intValue() == 0 || arrayList.isEmpty()) ? false : true;
        }
        return true;
    }

    public final boolean h(String str, int i7, ActionOwner actionOwner) {
        int i10;
        A7.m.f(str, "uniqueId");
        A7.m.f(actionOwner, "actionOwner");
        synchronized (this) {
            try {
                Iterator it = this.f4740h.iterator();
                int i11 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i10 = -1;
                        break;
                    }
                    if (A7.m.a(((f0) it.next()).d, str)) {
                        i10 = i11;
                        break;
                    }
                    i11++;
                }
                int iMin = Math.min(i7, this.f4740h.size() - 1);
                if (i10 < 0) {
                    return false;
                }
                f0 f0Var = (f0) this.f4740h.get(i10);
                if (i10 == iMin) {
                    return true;
                }
                this.f4740h.add(iMin, (f0) this.f4740h.remove(i10));
                int i12 = this.f4743k;
                i0 i0Var = new i0(i12, this.f4742j, true, false, actionOwner, 8);
                if (i10 == this.f4743k) {
                    i0Var.f4699a = iMin;
                } else {
                    if (i10 < i12) {
                        i0Var.f4699a = i12 - 1;
                    }
                    int i13 = i0Var.f4699a;
                    if (iMin <= i13) {
                        i0Var.f4699a = i13 + 1;
                    }
                }
                F6.a aVar = this.c;
                String str2 = f0Var.f4691a.f13136a;
                A7.m.c(str2);
                aVar.e(str2, i10, iMin, actionOwner);
                m(i0Var, false);
                this.f4741i.clear();
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean i(String str, ActionOwner actionOwner) {
        A7.m.f(str, "uniqueId");
        A7.m.f(actionOwner, "actionOwner");
        synchronized (this) {
            try {
                Iterator it = this.f4740h.iterator();
                int i7 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i7 = -1;
                        break;
                    }
                    if (A7.m.a(((f0) it.next()).d, str)) {
                        break;
                    }
                    i7++;
                }
                if (i7 < 0) {
                    return false;
                }
                i0 i0Var = new i0(this.f4743k, this.f4742j, true, false, actionOwner, 8);
                f0 f0Var = (f0) this.f4740h.get(i7);
                this.f4740h.remove(i7);
                int i10 = this.f4743k;
                if (i7 < i10) {
                    i0Var.f4699a = i10 - 1;
                } else if (i7 == i10) {
                    if (i7 >= this.f4740h.size()) {
                        i0Var.f4699a = this.f4740h.size() - 1;
                        i0Var.f4700b = (f0) AbstractC1998m.i0(this.f4740h);
                    } else {
                        i0Var.f4699a = i7;
                        i0Var.f4700b = (f0) this.f4740h.get(i7);
                    }
                }
                F6.a aVar = this.c;
                String str2 = f0Var.f4691a.f13136a;
                A7.m.c(str2);
                aVar.getClass();
                B5.z zVar = new B5.z();
                zVar.k(str2, "asset_id");
                zVar.k(Integer.valueOf(i7), "queue_index");
                aVar.a("Karaoke Queue Remove", zVar, actionOwner);
                m(i0Var, false);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void j(ArrayList arrayList) {
        ArrayList arrayList2;
        int i7 = this.f4743k;
        Iterator it = arrayList.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            arrayList2 = this.f4740h;
            int i10 = 0;
            if (!zHasNext) {
                break;
            }
            f0 f0Var = (f0) it.next();
            Iterator it2 = arrayList2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    i10 = -1;
                    break;
                } else if (A7.m.a(((f0) it2.next()).d, f0Var.d)) {
                    break;
                } else {
                    i10++;
                }
            }
            if (i10 < i7) {
                i7--;
            }
            arrayList2.remove(i10);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        i0 i0Var = new i0(this.f4743k, this.f4742j, true, false, null, 24);
        if (i7 >= arrayList2.size()) {
            i0Var.f4699a = arrayList2.size() - 1;
            i0Var.f4700b = (f0) AbstractC1998m.i0(arrayList2);
            i0Var.d = false;
        } else {
            i0Var.f4699a = i7;
            i0Var.f4700b = (f0) arrayList2.get(i7);
        }
        m(i0Var, false);
    }

    public final void k() {
        ArrayList arrayList = new ArrayList();
        for (f0 f0Var : this.f4740h) {
            String str = f0Var.f4691a.f13136a;
            A7.m.c(str);
            KaraokeSongResponseV2 karaokeSongResponseV2 = f0Var.f4691a;
            arrayList.add(new Song(str, karaokeSongResponseV2.f13137b, karaokeSongResponseV2.c, karaokeSongResponseV2.l, karaokeSongResponseV2.f13146n));
        }
        this.f4735a.updateSongQueue(new SongQueueInfo(null, (Song[]) arrayList.toArray(new Song[0]), this.f4743k, 1, null));
    }

    public final void l(String str, boolean z6, ActionOwner actionOwner) {
        A7.m.f(str, "uniqueId");
        A7.m.f(actionOwner, "actionOwner");
        synchronized (this) {
            try {
                Iterator it = this.f4740h.iterator();
                boolean z10 = false;
                int i7 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i7 = -1;
                        break;
                    } else if (A7.m.a(((f0) it.next()).d, str)) {
                        break;
                    } else {
                        i7++;
                    }
                }
                int i10 = i7;
                if (i10 >= 0) {
                    if (this.f4743k == i10 && A7.m.a(this.f4740h.get(i10), this.f4742j) && z6) {
                        z10 = true;
                    }
                    m(new i0(i10, (f0) this.f4740h.get(i10), false, z6, actionOwner), z10);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void m(i0 i0Var, boolean z6) {
        AbstractC0727y.w(this.f4738f, null, null, new p0(this, i0Var, z6, null), 3);
    }
}
