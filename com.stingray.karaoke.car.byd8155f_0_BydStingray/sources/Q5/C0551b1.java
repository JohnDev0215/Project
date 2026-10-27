package Q5;

import T.C0732b0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC2000o;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: Q5.b1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0551b1 implements C0.K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ float f6708a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ float f6709b;
    public final /* synthetic */ C0732b0 c;
    public final /* synthetic */ C0732b0 d;

    public C0551b1(float f10, float f11, C0732b0 c0732b0, C0732b0 c0732b02) {
        this.f6708a = f10;
        this.f6709b = f11;
        this.c = c0732b0;
        this.d = c0732b02;
    }

    @Override // C0.K
    public final C0.L h(C0.M m10, List list, long j3) {
        Integer num;
        A7.m.f(m10, "$this$Layout");
        A7.m.f(list, "measurables");
        final ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((C0.J) it.next()).b(j3));
        }
        Iterator it2 = arrayList.iterator();
        int i7 = 0;
        while (it2.hasNext()) {
            i7 += ((C0.V) it2.next()).l;
        }
        C0732b0 c0732b0 = this.c;
        c0732b0.j(i7);
        int iH = a1.a.h(j3);
        C0732b0 c0732b02 = this.d;
        c0732b02.j(iH);
        Iterator it3 = arrayList.iterator();
        if (it3.hasNext()) {
            Integer numValueOf = Integer.valueOf(((C0.V) it3.next()).f871m);
            while (it3.hasNext()) {
                Integer numValueOf2 = Integer.valueOf(((C0.V) it3.next()).f871m);
                if (numValueOf.compareTo(numValueOf2) < 0) {
                    numValueOf = numValueOf2;
                }
            }
            num = numValueOf;
        } else {
            num = null;
        }
        int iIntValue = num != null ? num.intValue() : 0;
        float fI = c0732b0.i();
        float f10 = this.f6708a * this.f6709b;
        int i10 = (((float) (arrayList.size() + (-1))) * f10) + fI <= ((float) c0732b02.i()) ? c0732b0.i() + ((int) (f10 * (arrayList.size() - 1))) : c0732b02.i();
        final C0732b0 c0732b03 = this.c;
        final C0732b0 c0732b04 = this.d;
        final float f11 = this.f6708a;
        final float f12 = this.f6709b;
        final int i11 = iIntValue;
        return m10.m0(i10, iIntValue, n7.v.l, new InterfaceC2782k() { // from class: Q5.a1
            /* JADX WARN: Removed duplicated region for block: B:26:0x0099  */
            /* JADX WARN: Removed duplicated region for block: B:9:0x0049  */
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // z7.InterfaceC2782k
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object b(java.lang.Object r11) {
                /*
                    r10 = this;
                    C0.U r11 = (C0.U) r11
                    java.lang.String r0 = "$this$layout"
                    A7.m.f(r11, r0)
                    T.b0 r0 = r4
                    int r1 = r0.i()
                    float r1 = (float) r1
                    float r2 = r1
                    float r3 = r2
                    float r2 = r2 * r3
                    java.util.ArrayList r3 = r3
                    int r4 = r3.size()
                    r5 = 1
                    int r4 = r4 - r5
                    float r4 = (float) r4
                    float r4 = r4 * r2
                    float r4 = r4 + r1
                    T.b0 r1 = r5
                    int r6 = r1.i()
                    float r6 = (float) r6
                    int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                    g0.f r6 = g0.b.f14671w
                    g0.f r7 = g0.b.f14670v
                    g0.f r8 = g0.b.f14669u
                    int r10 = r6
                    r9 = 0
                    if (r4 > 0) goto L6b
                    java.util.Iterator r0 = r3.iterator()
                    r1 = r9
                L37:
                    boolean r3 = r0.hasNext()
                    if (r3 == 0) goto Lba
                    java.lang.Object r3 = r0.next()
                    C0.V r3 = (C0.V) r3
                    boolean r4 = r7.equals(r8)
                    if (r4 == 0) goto L4b
                L49:
                    r4 = r9
                    goto L62
                L4b:
                    boolean r4 = r7.equals(r7)
                    if (r4 == 0) goto L58
                    int r4 = r3.f871m
                    int r4 = r10 - r4
                    int r4 = r4 / 2
                    goto L62
                L58:
                    boolean r4 = r7.equals(r6)
                    if (r4 == 0) goto L49
                    int r4 = r3.f871m
                    int r4 = r10 - r4
                L62:
                    C0.U.d(r11, r3, r1, r4)
                    int r3 = r3.l
                    int r4 = (int) r2
                    int r3 = r3 + r4
                    int r1 = r1 + r3
                    goto L37
                L6b:
                    int r2 = r3.size()
                    if (r2 <= r5) goto L81
                    int r1 = r1.i()
                    int r0 = r0.i()
                    int r1 = r1 - r0
                    int r0 = r3.size()
                    int r0 = r0 - r5
                    int r1 = r1 / r0
                    goto L82
                L81:
                    r1 = r9
                L82:
                    java.util.Iterator r0 = r3.iterator()
                    r2 = r9
                L87:
                    boolean r3 = r0.hasNext()
                    if (r3 == 0) goto Lba
                    java.lang.Object r3 = r0.next()
                    C0.V r3 = (C0.V) r3
                    boolean r4 = r7.equals(r8)
                    if (r4 == 0) goto L9b
                L99:
                    r4 = r9
                    goto Lb2
                L9b:
                    boolean r4 = r7.equals(r7)
                    if (r4 == 0) goto La8
                    int r4 = r3.f871m
                    int r4 = r10 - r4
                    int r4 = r4 / 2
                    goto Lb2
                La8:
                    boolean r4 = r7.equals(r6)
                    if (r4 == 0) goto L99
                    int r4 = r3.f871m
                    int r4 = r10 - r4
                Lb2:
                    C0.U.d(r11, r3, r2, r4)
                    int r3 = r3.l
                    int r3 = r3 + r1
                    int r2 = r2 + r3
                    goto L87
                Lba:
                    m7.q r10 = m7.q.f19267a
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: Q5.C0547a1.b(java.lang.Object):java.lang.Object");
            }
        });
    }
}
