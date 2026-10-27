package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class go implements qv2 {
    public Object c = ho.f129p;
    public zp e;
    public final /* synthetic */ kotlinx.coroutines.channels.a k;

    public go(kotlinx.coroutines.channels.a aVar) {
        this.k = aVar;
    }

    @Override // p.qv2
    public final void a(cb2 cb2Var, int i) {
        zp zpVar = this.e;
        if (zpVar != null) {
            zpVar.a(cb2Var, i);
        }
    }

    public final Object b(c00 c00Var) throws Throwable {
        er erVar;
        Boolean bool;
        er erVar2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = kotlinx.coroutines.channels.a.f88p;
        kotlinx.coroutines.channels.a aVar = this.k;
        er erVar3 = (er) atomicReferenceFieldUpdater.get(aVar);
        while (!aVar.y()) {
            long andIncrement = kotlinx.coroutines.channels.a.l.getAndIncrement(aVar);
            long j = ho.b;
            long j2 = andIncrement / j;
            int i = (int) (andIncrement % j);
            if (erVar3.k != j2) {
                er erVarR = aVar.r(j2, erVar3);
                if (erVarR == null) {
                    continue;
                } else {
                    erVar = erVarR;
                }
            } else {
                erVar = erVar3;
            }
            Object objI = aVar.I(erVar, i, andIncrement, null);
            tz2 tz2Var = ho.m;
            if (objI == tz2Var) {
                throw new IllegalStateException("unreachable".toString());
            }
            tz2 tz2Var2 = ho.o;
            if (objI != tz2Var2) {
                if (objI != ho.n) {
                    erVar.a();
                    this.c = objI;
                    return Boolean.TRUE;
                }
                kotlinx.coroutines.channels.a aVar2 = this.k;
                zp zpVarS = v03.s(vr2.k(c00Var));
                try {
                    this.e = zpVarS;
                    Object objI2 = aVar2.I(erVar, i, andIncrement, this);
                    if (objI2 == tz2Var) {
                        a(erVar, i);
                    } else {
                        ry0 ry0VarA = null;
                        y10 y10Var = zpVarS.m;
                        ry0 ry0Var = aVar2.e;
                        if (objI2 == tz2Var2) {
                            if (andIncrement < aVar2.v()) {
                                erVar.a();
                            }
                            er erVar4 = (er) kotlinx.coroutines.channels.a.f88p.get(aVar2);
                            while (true) {
                                if (aVar2.y()) {
                                    zp zpVar = this.e;
                                    v03.h(zpVar);
                                    this.e = null;
                                    this.c = ho.l;
                                    Throwable thS = aVar.s();
                                    if (thS == null) {
                                        zpVar.k(Boolean.FALSE);
                                    } else {
                                        zpVar.k(kotlin.a.b(thS));
                                    }
                                } else {
                                    long andIncrement2 = kotlinx.coroutines.channels.a.l.getAndIncrement(aVar2);
                                    long j3 = ho.b;
                                    long j4 = andIncrement2 / j3;
                                    int i2 = (int) (andIncrement2 % j3);
                                    if (erVar4.k != j4) {
                                        er erVarR2 = aVar2.r(j4, erVar4);
                                        if (erVarR2 != null) {
                                            erVar2 = erVarR2;
                                        }
                                    } else {
                                        erVar2 = erVar4;
                                    }
                                    ry0 ry0Var2 = ry0Var;
                                    Object objI3 = aVar2.I(erVar2, i2, andIncrement2, this);
                                    if (objI3 == ho.m) {
                                        a(erVar2, i2);
                                        break;
                                    }
                                    if (objI3 == ho.o) {
                                        if (andIncrement2 < aVar2.v()) {
                                            erVar2.a();
                                        }
                                        erVar4 = erVar2;
                                        ry0Var = ry0Var2;
                                    } else {
                                        if (objI3 == ho.n) {
                                            throw new IllegalStateException("unexpected".toString());
                                        }
                                        erVar2.a();
                                        this.c = objI3;
                                        this.e = null;
                                        bool = Boolean.TRUE;
                                        if (ry0Var2 != null) {
                                            ry0VarA = kotlinx.coroutines.internal.b.a(ry0Var2, objI3, y10Var);
                                        }
                                    }
                                }
                            }
                        } else {
                            erVar.a();
                            this.c = objI2;
                            this.e = null;
                            bool = Boolean.TRUE;
                            if (ry0Var != null) {
                                ry0VarA = kotlinx.coroutines.internal.b.a(ry0Var, objI2, y10Var);
                            }
                        }
                        zpVarS.z(bool, ry0VarA);
                    }
                    return zpVarS.q();
                } catch (Throwable th) {
                    zpVarS.y();
                    throw th;
                }
            }
            if (andIncrement < aVar.v()) {
                erVar.a();
            }
            erVar3 = erVar;
        }
        this.c = ho.l;
        Throwable thS2 = aVar.s();
        if (thS2 == null) {
            return Boolean.FALSE;
        }
        int i3 = mf2.a;
        throw thS2;
    }
}
