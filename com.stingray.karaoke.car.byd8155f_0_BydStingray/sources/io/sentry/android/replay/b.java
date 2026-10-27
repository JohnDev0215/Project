package io.sentry.android.replay;

import java.util.Locale;
import java.util.Map;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class b extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final b f16340n = new b(1, 0);
    public static final b o = new b(1, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f16341m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(int i7, int i10) {
        super(i7);
        this.f16341m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f16341m) {
            case 0:
                Q8.f fVar = (Q8.f) obj;
                A7.m.f(fVar, "it");
                String strGroup = fVar.f7223a.group();
                A7.m.e(strGroup, "group(...)");
                String strValueOf = String.valueOf(Q8.h.N0(strGroup));
                A7.m.d(strValueOf, "null cannot be cast to non-null type java.lang.String");
                String upperCase = strValueOf.toUpperCase(Locale.ROOT);
                A7.m.e(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                return upperCase;
            default:
                Map.Entry entry = (Map.Entry) obj;
                A7.m.f(entry, "<name for destructuring parameter 0>");
                return ((String) entry.getKey()) + '=' + ((String) entry.getValue());
        }
    }
}
