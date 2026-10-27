package q8;

import java.util.ArrayList;
import java.util.Set;
import n7.AbstractC1996k;
import n7.AbstractC1998m;

/* JADX INFO: renamed from: q8.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC2204h {
    VISIBILITY(true),
    MODALITY(true),
    OVERRIDE(true),
    ANNOTATIONS(false),
    INNER(true),
    MEMBER_KIND(true),
    DATA(true),
    INLINE(true),
    EXPECT(true),
    ACTUAL(true),
    CONST(true),
    LATEINIT(true),
    FUN(true),
    VALUE(true);


    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Set f21285m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Set f21286n;
    public final boolean l;

    static {
        EnumC2204h[] enumC2204hArrValues = values();
        ArrayList arrayList = new ArrayList();
        for (EnumC2204h enumC2204h : enumC2204hArrValues) {
            if (enumC2204h.l) {
                arrayList.add(enumC2204h);
            }
        }
        f21285m = AbstractC1998m.D0(arrayList);
        f21286n = AbstractC1996k.c0(values());
    }

    EnumC2204h(boolean z6) {
        this.l = z6;
    }
}
