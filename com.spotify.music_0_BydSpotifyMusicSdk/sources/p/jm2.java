package p;

import com.google.firebase.components.DependencyException;
import com.google.firebase.installations.local.PersistedInstallation$RegistrationStatus;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class jm2 implements xv {
    public Object a;
    public Object b;
    public Object c;
    public Object d;
    public Object e;
    public Object f;
    public Object g;

    public jm2(int i) {
        if (i != 2 && i != 3 && i != 5 && i != 6) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
        }
    }

    @Override // p.xv
    public final Object a(Class cls) {
        if (!((Set) this.a).contains(dz1.a(cls))) {
            throw new DependencyException(String.format("Attempting to request an undeclared dependency %s.", cls));
        }
        Object objA = ((xv) this.g).a(cls);
        if (!cls.equals(bz1.class)) {
            return objA;
        }
        return new v42();
    }

    @Override // p.xv
    public final j80 b(dz1 dz1Var) {
        if (((Set) this.c).contains(dz1Var)) {
            return ((xv) this.g).b(dz1Var);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Deferred<%s>.", dz1Var));
    }

    @Override // p.xv
    public final yy1 c(dz1 dz1Var) {
        if (((Set) this.e).contains(dz1Var)) {
            return ((xv) this.g).c(dz1Var);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Provider<Set<%s>>.", dz1Var));
    }

    @Override // p.xv
    public final yy1 d(Class cls) {
        return g(dz1.a(cls));
    }

    @Override // p.xv
    public final Set e(dz1 dz1Var) {
        if (((Set) this.d).contains(dz1Var)) {
            return ((xv) this.g).e(dz1Var);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Set<%s>.", dz1Var));
    }

    @Override // p.xv
    public final Object f(dz1 dz1Var) {
        if (((Set) this.a).contains(dz1Var)) {
            return ((xv) this.g).f(dz1Var);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency %s.", dz1Var));
    }

    @Override // p.xv
    public final yy1 g(dz1 dz1Var) {
        if (((Set) this.b).contains(dz1Var)) {
            return ((xv) this.g).g(dz1Var);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Provider<%s>.", dz1Var));
    }

    public final ki h() {
        String strH = ((PersistedInstallation$RegistrationStatus) this.b) == null ? " registrationStatus" : "";
        if (((Long) this.e) == null) {
            strH = strH.concat(" expiresInSecs");
        }
        if (((Long) this.f) == null) {
            strH = u4.h(strH, " tokenCreationEpochInSecs");
        }
        if (strH.isEmpty()) {
            return new ki((String) this.a, (PersistedInstallation$RegistrationStatus) this.b, (String) this.c, (String) this.d, ((Long) this.e).longValue(), ((Long) this.f).longValue(), (String) this.g);
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final j80 i(Class cls) {
        return b(dz1.a(cls));
    }

    public final void j(PersistedInstallation$RegistrationStatus persistedInstallation$RegistrationStatus) {
        if (persistedInstallation$RegistrationStatus == null) {
            throw new NullPointerException("Null registrationStatus");
        }
        this.b = persistedInstallation$RegistrationStatus;
    }
}
