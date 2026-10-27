package r8;

import D8.v;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0656i;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import Q7.L;
import java.util.Comparator;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Comparator {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final i f21885m = new i(0);
    public final /* synthetic */ int l;

    public /* synthetic */ i(int i7) {
        this.l = i7;
    }

    public static int a(InterfaceC0657j interfaceC0657j) {
        if (d.m(interfaceC0657j)) {
            return 8;
        }
        if (interfaceC0657j instanceof InterfaceC0656i) {
            return 7;
        }
        if (interfaceC0657j instanceof L) {
            return ((L) interfaceC0657j).K() == null ? 6 : 5;
        }
        if (interfaceC0657j instanceof InterfaceC0666t) {
            return ((InterfaceC0666t) interfaceC0657j).K() == null ? 4 : 3;
        }
        if (interfaceC0657j instanceof InterfaceC0652e) {
            return 2;
        }
        return interfaceC0657j instanceof v ? 1 : 0;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Integer numValueOf;
        switch (this.l) {
            case 0:
                InterfaceC0657j interfaceC0657j = (InterfaceC0657j) obj;
                InterfaceC0657j interfaceC0657j2 = (InterfaceC0657j) obj2;
                int iA = a(interfaceC0657j2) - a(interfaceC0657j);
                if (iA != 0) {
                    numValueOf = Integer.valueOf(iA);
                } else if (d.n(interfaceC0657j, 4) && d.n(interfaceC0657j2, 4)) {
                    numValueOf = 0;
                } else {
                    int iCompareTo = interfaceC0657j.getName().l.compareTo(interfaceC0657j2.getName().l);
                    numValueOf = iCompareTo != 0 ? Integer.valueOf(iCompareTo) : null;
                }
                if (numValueOf != null) {
                    return numValueOf.intValue();
                }
                return 0;
            default:
                return h6.f.b(AbstractC2564d.g((InterfaceC0652e) obj).b(), AbstractC2564d.g((InterfaceC0652e) obj2).b());
        }
    }
}
