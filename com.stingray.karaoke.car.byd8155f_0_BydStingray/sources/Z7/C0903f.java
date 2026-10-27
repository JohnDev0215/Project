package Z7;

import Q7.InterfaceC0666t;
import T7.AbstractC0794m;
import o8.C2061f;
import v8.AbstractC2564d;

/* JADX INFO: renamed from: Z7.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0903f extends F {
    public static final /* synthetic */ int l = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public static final InterfaceC0666t a(InterfaceC0666t interfaceC0666t) {
        A7.m.f(interfaceC0666t, "functionDescriptor");
        C2061f name = ((AbstractC0794m) interfaceC0666t).getName();
        A7.m.e(name, "functionDescriptor.name");
        if (b(name)) {
            return (InterfaceC0666t) AbstractC2564d.b(interfaceC0666t, C0902e.f10934n);
        }
        return null;
    }

    public static boolean b(C2061f c2061f) {
        A7.m.f(c2061f, "<this>");
        return F.f10915e.contains(c2061f);
    }
}
