package defpackage;

import java.util.HashMap;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r11v2 m02, still in use, count: 1, list:
  (r11v2 m02) from 0x00d8: IPUT 
  (wrap:pd:0x00d5: CONSTRUCTOR (16 int) A[MD:(int):void (m), WRAPPED] (LINE:214) call: pd.<init>(int):void type: CONSTRUCTOR)
  (r11v2 m02)
 (LINE:217) m02.f r02
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:252)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:180)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: PersistedTabDataConfiguration.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m02 {
    /* JADX INFO: Fake field, exist only in values array */
    EF0("CPTDFB"),
    /* JADX INFO: Fake field, exist only in values array */
    EF1("ECPTDFB"),
    /* JADX INFO: Fake field, exist only in values array */
    EF3("MPTD"),
    /* JADX INFO: Fake field, exist only in values array */
    EF5("EMPTD"),
    /* JADX INFO: Fake field, exist only in values array */
    EF7("COPTD"),
    /* JADX INFO: Fake field, exist only in values array */
    EF9("SPTD"),
    /* JADX INFO: Fake field, exist only in values array */
    EF11("EBBTC"),
    /* JADX INFO: Fake field, exist only in values array */
    EF13("TC");

    public static final HashMap g;
    public static final HashMap h;
    public static mm0 i;
    public static rh0 j;
    public static ai1 k;
    public static ch0 l;
    public final String e;
    public r02 f;

    static {
        m02 m02Var = EF0;
        m02 m02Var2 = EF1;
        m02 m02Var3 = EF3;
        m02 m02Var4 = EF5;
        m02 m02Var5 = EF7;
        m02 m02Var6 = EF9;
        HashMap map = new HashMap();
        g = map;
        HashMap map2 = new HashMap();
        h = map2;
        map.put(a10.class, m02Var5);
        map2.put(a10.class, m02Var5);
        map.put(CriticalPersistedTabData.class, m02Var);
        map2.put(CriticalPersistedTabData.class, m02Var2);
        map.put(zh1.class, m02Var3);
        map2.put(zh1.class, m02Var4);
        map.put(xl2.class, m02Var6);
        map2.put(xl2.class, m02Var6);
        m02Var5.f = new v61();
        m02Var.f = new wd0(19);
        m02Var2.f = new pd(15);
        m02Var3.f = new i83(17);
        m02Var4.f = new qh1(11);
        m02Var6.f = new v61();
        m02Var.f = new wd0(20);
        m02Var.f = new pd(16);
    }

    public m02(String str) {
        this.e = str;
    }

    public static m02 a(Class<? extends l02> cls, boolean z) {
        return z ? (m02) h.get(cls) : (m02) g.get(cls);
    }

    public static mm0 e() {
        if (i == null) {
            i = new mm0();
        }
        return i;
    }

    public static m02 valueOf(String str) {
        return (m02) Enum.valueOf(m02.class, str);
    }

    public static m02[] values() {
        return (m02[]) m.clone();
    }

    public final q02 f() {
        return this.f.a();
    }
}
