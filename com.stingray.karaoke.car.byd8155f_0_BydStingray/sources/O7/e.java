package O7;

import N7.o;
import io.sentry.hints.i;
import o8.C2058c;
import o8.C2061f;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'q' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final i f5181n;
    public static final e o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final e f5182p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final e f5183q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final e f5184r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final /* synthetic */ e[] f5185s;
    public final C2058c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f5186m;

    static {
        e eVar = new e("Function", 0, o.f4875j, "Function");
        o = eVar;
        e eVar2 = new e("SuspendFunction", 1, o.f4870e, "SuspendFunction");
        f5182p = eVar2;
        C2058c c2058c = o.f4873h;
        e eVar3 = new e("KFunction", 2, c2058c, "KFunction");
        f5183q = eVar3;
        e eVar4 = new e("KSuspendFunction", 3, c2058c, "KSuspendFunction");
        f5184r = eVar4;
        f5185s = new e[]{eVar, eVar2, eVar3, eVar4};
        f5181n = new i(7);
    }

    public e(String str, int i7, C2058c c2058c, String str2) {
        this.l = c2058c;
        this.f5186m = str2;
    }

    public static e valueOf(String str) {
        return (e) Enum.valueOf(e.class, str);
    }

    public static e[] values() {
        return (e[]) f5185s.clone();
    }

    public final C2061f a(int i7) {
        return C2061f.e(this.f5186m + i7);
    }
}
