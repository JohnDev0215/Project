package L0;

import java.util.ArrayList;
import java.util.List;
import n7.AbstractC1998m;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class q extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final q f3923n = new q(2, 0);
    public static final q o = new q(2, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final q f3924p = new q(2, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final q f3925q = new q(2, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final q f3926r = new q(2, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final q f3927s = new q(2, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final q f3928t = new q(2, 6);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final q f3929u = new q(2, 7);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final q f3930v = new q(2, 8);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final q f3931w = new q(2, 9);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final q f3932x = new q(2, 10);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final q f3933y = new q(2, 11);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3934m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(int i7, int i10) {
        super(i7);
        this.f3934m = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        String str;
        m7.c cVar;
        switch (this.f3934m) {
            case 0:
                List list = (List) obj;
                List list2 = (List) obj2;
                if (list == null) {
                    return list2;
                }
                ArrayList arrayListA0 = AbstractC1998m.A0(list);
                arrayListA0.addAll(list2);
                return arrayListA0;
            case 1:
                return (m7.q) obj;
            case 2:
                throw new IllegalStateException("merge function called on unmergeable property IsDialog. A dialog should not be a child of a clickable/focusable node.");
            case 3:
                throw new IllegalStateException("merge function called on unmergeable property IsPopup. A popup should not be a child of a clickable/focusable node.");
            case 4:
                throw new IllegalStateException("merge function called on unmergeable property PaneTitle.");
            case 5:
                g gVar = (g) obj;
                int i7 = ((g) obj2).f3883a;
                return gVar;
            case 6:
                return (String) obj;
            case 7:
                List list3 = (List) obj;
                List list4 = (List) obj2;
                if (list3 == null) {
                    return list4;
                }
                ArrayList arrayListA02 = AbstractC1998m.A0(list3);
                arrayListA02.addAll(list4);
                return arrayListA02;
            case 8:
                Float f10 = (Float) obj;
                ((Number) obj2).floatValue();
                return f10;
            case 9:
                Boolean bool = (Boolean) obj;
                ((Boolean) obj2).booleanValue();
                return bool;
            case 10:
                a aVar = (a) obj;
                a aVar2 = (a) obj2;
                if (aVar == null || (str = aVar.f3875a) == null) {
                    str = aVar2.f3875a;
                }
                if (aVar == null || (cVar = aVar.f3876b) == null) {
                    cVar = aVar2.f3876b;
                }
                return new a(str, cVar);
            default:
                return obj == null ? obj2 : obj;
        }
    }
}
