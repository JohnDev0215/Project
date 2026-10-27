package l5;

import j5.y;
import j5.z;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import o5.AbstractC2047c;
import q5.C2185a;

/* JADX INFO: renamed from: l5.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1780h implements z, Cloneable {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1780h f18644n = new C1780h();
    public final List l = Collections.emptyList();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List f18645m = Collections.emptyList();

    @Override // j5.z
    public final y a(j5.m mVar, C2185a c2185a) {
        Class cls = c2185a.f21253a;
        boolean zB = b(cls, true);
        boolean zB2 = b(cls, false);
        if (zB || zB2) {
            return new C1779g(this, zB2, zB, mVar, c2185a);
        }
        return null;
    }

    public final boolean b(Class cls, boolean z6) {
        if (!z6 && !Enum.class.isAssignableFrom(cls)) {
            h6.r rVar = AbstractC2047c.f20529a;
            if (!Modifier.isStatic(cls.getModifiers()) && (cls.isAnonymousClass() || cls.isLocalClass())) {
                return true;
            }
        }
        Iterator it = (z6 ? this.l : this.f18645m).iterator();
        if (!it.hasNext()) {
            return false;
        }
        it.next().getClass();
        throw new ClassCastException();
    }

    public final Object clone() {
        try {
            return (C1780h) super.clone();
        } catch (CloneNotSupportedException e10) {
            throw new AssertionError(e10);
        }
    }
}
