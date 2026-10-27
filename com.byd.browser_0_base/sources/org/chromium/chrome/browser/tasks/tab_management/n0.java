package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.g72;
import defpackage.xa1;
import defpackage.ya1;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabListModel.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n0 extends ya1 {

    /* JADX INFO: compiled from: TabListModel.java */
    public static class a {
        public static final PropertyModel.j a = new PropertyModel.j();
        public static final PropertyModel.o b = new PropertyModel.o();
    }

    public final void B(int i, xa1 xa1Var) {
        super.u(i, xa1Var);
    }

    public final int C(int i) {
        long jH = g72.b(i).h();
        int i2 = 0;
        while (i2 < size()) {
            PropertyModel propertyModel = get(i2).b;
            if (propertyModel.h(a.a) == 0 && g72.b(propertyModel.h(u0.a)).h() - jH < 0) {
                break;
            }
            i2++;
        }
        return i2;
    }

    public final int D(int i) {
        if (i < 0) {
            return -1;
        }
        if (i > size()) {
            i = size();
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (get(i3).b.h(a.a) == 0) {
                i2++;
            }
        }
        return i2;
    }

    public final int E(int i) {
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (get(i2).b.h(a.a) == 0) {
                return i2;
            }
        }
        return -1;
    }

    public final int F(int i) {
        for (int i2 = 0; i2 < size(); i2++) {
            PropertyModel propertyModel = get(i2).b;
            if (propertyModel.h(a.a) == 0 && propertyModel.h(u0.a) == i) {
                return i2;
            }
        }
        return -1;
    }

    public final int G(int i) {
        int i2 = -1;
        if (i < 0) {
            return -1;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size(); i4++) {
            if (get(i4).b.h(a.a) == 0) {
                int i5 = i3 + 1;
                if (i3 == i) {
                    return i4;
                }
                i3 = i5;
                i2 = i4;
            }
        }
        return i2 + 1;
    }

    public final int H() {
        for (int size = size() - 1; size >= 0; size--) {
            if (get(size).b.h(a.a) == 1) {
                return size;
            }
        }
        return -1;
    }

    public final int I(int i) {
        for (int size = size() - 1; size >= 0; size--) {
            PropertyModel propertyModel = get(size).b;
            if (propertyModel.h(a.a) == 1 && propertyModel.h(f.a) == i) {
                return size;
            }
        }
        return -1;
    }

    public final void J(int i, boolean z) {
        if (i < 0 || i >= size()) {
            return;
        }
        int i2 = z ? 4 : 3;
        PropertyModel propertyModel = get(i).b;
        PropertyModel.p pVar = u0.n;
        if (propertyModel.h(pVar) == i2) {
            return;
        }
        get(i).b.n(pVar, i2);
    }

    public final void K(int i, boolean z) {
        if (i < 0 || i >= size()) {
            return;
        }
        int i2 = z ? 2 : 1;
        PropertyModel propertyModel = get(i).b;
        PropertyModel.p pVar = u0.n;
        if (propertyModel.h(pVar) == i2) {
            return;
        }
        get(i).b.n(pVar, i2);
        get(i).b.m(a.b, z ? 0.8f : 1.0f);
    }

    public final void L(int i, Tab tab) {
        if (get(i).b.h(a.a) != 0) {
            return;
        }
        get(i).b.n(u0.a, tab.getId());
    }

    @Override // defpackage.q81
    public final void u(int i, xa1 xa1Var) {
        super.u(i, xa1Var);
    }
}
