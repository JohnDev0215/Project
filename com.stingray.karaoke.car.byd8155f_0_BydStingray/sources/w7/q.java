package W7;

import f8.InterfaceC1269e;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class q extends v implements InterfaceC1269e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Constructor f9895a;

    public q(Constructor constructor) {
        A7.m.f(constructor, "member");
        this.f9895a = constructor;
    }

    @Override // W7.v
    public final Member b() {
        return this.f9895a;
    }

    @Override // f8.InterfaceC1269e
    public final ArrayList t() {
        TypeVariable[] typeParameters = this.f9895a.getTypeParameters();
        A7.m.e(typeParameters, "member.typeParameters");
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable typeVariable : typeParameters) {
            arrayList.add(new B(typeVariable));
        }
        return arrayList;
    }
}
