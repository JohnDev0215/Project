package C0;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface K {
    default int a(InterfaceC0108o interfaceC0108o, List list, int i7) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            int i11 = 1;
            arrayList.add(new C0104k((J) list.get(i10), i11, i11, 0));
        }
        return h(new C0110q(interfaceC0108o, interfaceC0108o.getLayoutDirection()), arrayList, io.sentry.config.a.f(0, i7, 7)).e();
    }

    default int d(InterfaceC0108o interfaceC0108o, List list, int i7) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            int i11 = 2;
            arrayList.add(new C0104k((J) list.get(i10), i11, i11, 0));
        }
        return h(new C0110q(interfaceC0108o, interfaceC0108o.getLayoutDirection()), arrayList, io.sentry.config.a.f(i7, 0, 13)).c();
    }

    L h(M m10, List list, long j3);

    default int i(InterfaceC0108o interfaceC0108o, List list, int i7) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(new C0104k((J) list.get(i10), 1, 2, 0));
        }
        return h(new C0110q(interfaceC0108o, interfaceC0108o.getLayoutDirection()), arrayList, io.sentry.config.a.f(i7, 0, 13)).c();
    }

    default int j(InterfaceC0108o interfaceC0108o, List list, int i7) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(new C0104k((J) list.get(i10), 2, 1, 0));
        }
        return h(new C0110q(interfaceC0108o, interfaceC0108o.getLayoutDirection()), arrayList, io.sentry.config.a.f(0, i7, 7)).e();
    }
}
