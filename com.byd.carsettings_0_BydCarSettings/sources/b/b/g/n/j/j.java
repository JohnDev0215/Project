package b.b.g.n.j;

import b.b.g.o.b;
import b.b.g.o.c;
import b.b.g.q.f.b.n;
import java.util.Arrays;

/* JADX INFO: compiled from: GeneralSoundFieldPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class j extends b.b.m.e.g<n> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.g.o.c f1093a = c.d.a();

    public int[] c() {
        int[] iArrN = this.f1093a.n(0);
        b.b.m.c.a.a("GeneralSoundFieldPresenter", "getPosition", "before position: " + Arrays.toString(iArrN));
        iArrN[0] = iArrN[0] + 10;
        iArrN[1] = -iArrN[1];
        iArrN[1] = iArrN[1] + 10;
        b.b.m.c.a.a("GeneralSoundFieldPresenter", "getPosition", "after position: " + Arrays.toString(iArrN));
        return iArrN;
    }

    public int[] d() {
        b.b.m.c.a.a("GeneralSoundFieldPresenter", "resetPosition");
        this.f1093a.S(0, 0, 0);
        this.f1093a.c(0, 2);
        b.C0059b.a().s(getV().getContext(), 0, 0);
        return c();
    }

    public int e(int i, int i2) {
        int i3 = i - 10;
        int i4 = i2 - 10;
        int i5 = -i4;
        b.b.m.c.a.a("GeneralSoundFieldPresenter", "setPosition", "posX: " + i3 + ", posY: " + i4 + ", transPosY: " + i5);
        b.C0059b.a().s(getV().getContext(), i3, i5);
        return this.f1093a.S(0, i3, i5);
    }
}
