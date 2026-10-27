package L8;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2061f f4083a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Q8.g f4084b;
    public final Collection c;
    public final InterfaceC2782k d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e[] f4085e;

    public i(C2061f c2061f, Q8.g gVar, Collection collection, InterfaceC2782k interfaceC2782k, e... eVarArr) {
        this.f4083a = c2061f;
        this.f4084b = gVar;
        this.c = collection;
        this.d = interfaceC2782k;
        this.f4085e = eVarArr;
    }

    public /* synthetic */ i(C2061f c2061f, e[] eVarArr) {
        this(c2061f, eVarArr, h.f4074n);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public i(C2061f c2061f, e[] eVarArr, InterfaceC2782k interfaceC2782k) {
        this(c2061f, null, null, interfaceC2782k, (e[]) Arrays.copyOf(eVarArr, eVarArr.length));
        A7.m.f(c2061f, "name");
        A7.m.f(interfaceC2782k, "additionalChecks");
    }

    public /* synthetic */ i(Set set, e[] eVarArr) {
        this(set, eVarArr, h.f4075p);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public i(Collection collection, e[] eVarArr, InterfaceC2782k interfaceC2782k) {
        this(null, null, collection, interfaceC2782k, (e[]) Arrays.copyOf(eVarArr, eVarArr.length));
        A7.m.f(collection, "nameList");
        A7.m.f(interfaceC2782k, "additionalChecks");
    }
}
