package defpackage;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'u' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: xl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC1781xl {
    public static final EnumC1781xl A;
    public static final EnumC1781xl B;
    public static final EnumC1781xl C;
    public static final EnumC1781xl D;
    public static final EnumC1781xl E;
    public static final EnumC1781xl F;
    public static final EnumC1781xl G;
    public static final EnumC1781xl H;
    public static final EnumC1781xl I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final EnumC1781xl f35J;
    public static final EnumC1781xl K;
    public static final EnumC1781xl L;
    public static final EnumC1781xl M;
    public static final EnumC1781xl N;
    public static final EnumC1781xl O;
    public static final EnumC1781xl P;
    public static final EnumC1781xl Q;
    public static final EnumC1781xl R;
    public static final EnumC1781xl S;
    public static final EnumC1781xl T;
    public static final EnumC1781xl U;
    public static final EnumC1781xl V;
    public static final EnumC1781xl W;
    public static final EnumC1781xl X;
    public static final EnumC1781xl Y;
    public static final EnumC1781xl Z;
    public static final EnumC1781xl a0;
    public static final EnumC1781xl b0;
    public static final EnumC1781xl c0;
    public static final EnumC1781xl d0;
    public static final EnumC1781xl e0;
    public static final EnumC1781xl f0;
    public static final EnumC1781xl g0;
    public static final EnumC1781xl h0;
    public static final EnumC1781xl i0;
    public static final EnumC1781xl j0;
    public static final EnumC1781xl k0;
    public static final EnumC1781xl l0;
    public static final EnumC1781xl m0;
    public static final EnumC1781xl n0;
    public static final EnumC1781xl o0;
    public static final EnumC1781xl p0;
    public static final EnumC1781xl q0;
    public static final EnumC1781xl r0;
    public static final EnumC1781xl s0;
    public static final EnumC1781xl[] t0;
    public static final EnumC1781xl u;
    public static final /* synthetic */ EnumC1781xl[] u0;
    public static final EnumC1781xl v;
    public static final EnumC1781xl w;
    public static final EnumC1781xl x;
    public static final EnumC1781xl y;
    public static final EnumC1781xl z;
    public final int s;
    public final EnumC1726wl t;

    static {
        EnumC1726wl enumC1726wl = EnumC1726wl.PACKED_VECTOR;
        EnumC1726wl enumC1726wl2 = EnumC1726wl.VECTOR;
        EnumC1726wl enumC1726wl3 = EnumC1726wl.SCALAR;
        u = new EnumC1781xl("DOUBLE", 0, 0, enumC1726wl3, EnumC0173Jl.DOUBLE);
        v = new EnumC1781xl("FLOAT", 1, 1, enumC1726wl3, EnumC0173Jl.FLOAT);
        w = new EnumC1781xl("INT64", 2, 2, enumC1726wl3, EnumC0173Jl.LONG);
        x = new EnumC1781xl("UINT64", 3, 3, enumC1726wl3, EnumC0173Jl.LONG);
        y = new EnumC1781xl("INT32", 4, 4, enumC1726wl3, EnumC0173Jl.INT);
        z = new EnumC1781xl("FIXED64", 5, 5, enumC1726wl3, EnumC0173Jl.LONG);
        A = new EnumC1781xl("FIXED32", 6, 6, enumC1726wl3, EnumC0173Jl.INT);
        B = new EnumC1781xl("BOOL", 7, 7, enumC1726wl3, EnumC0173Jl.BOOLEAN);
        C = new EnumC1781xl("STRING", 8, 8, enumC1726wl3, EnumC0173Jl.STRING);
        D = new EnumC1781xl("MESSAGE", 9, 9, enumC1726wl3, EnumC0173Jl.MESSAGE);
        E = new EnumC1781xl("BYTES", 10, 10, enumC1726wl3, EnumC0173Jl.BYTE_STRING);
        F = new EnumC1781xl("UINT32", 11, 11, enumC1726wl3, EnumC0173Jl.INT);
        G = new EnumC1781xl("ENUM", 12, 12, enumC1726wl3, EnumC0173Jl.ENUM);
        H = new EnumC1781xl("SFIXED32", 13, 13, enumC1726wl3, EnumC0173Jl.INT);
        I = new EnumC1781xl("SFIXED64", 14, 14, enumC1726wl3, EnumC0173Jl.LONG);
        f35J = new EnumC1781xl("SINT32", 15, 15, enumC1726wl3, EnumC0173Jl.INT);
        K = new EnumC1781xl("SINT64", 16, 16, enumC1726wl3, EnumC0173Jl.LONG);
        L = new EnumC1781xl("GROUP", 17, 17, enumC1726wl3, EnumC0173Jl.MESSAGE);
        M = new EnumC1781xl("DOUBLE_LIST", 18, 18, enumC1726wl2, EnumC0173Jl.DOUBLE);
        N = new EnumC1781xl("FLOAT_LIST", 19, 19, enumC1726wl2, EnumC0173Jl.FLOAT);
        O = new EnumC1781xl("INT64_LIST", 20, 20, enumC1726wl2, EnumC0173Jl.LONG);
        P = new EnumC1781xl("UINT64_LIST", 21, 21, enumC1726wl2, EnumC0173Jl.LONG);
        Q = new EnumC1781xl("INT32_LIST", 22, 22, enumC1726wl2, EnumC0173Jl.INT);
        R = new EnumC1781xl("FIXED64_LIST", 23, 23, enumC1726wl2, EnumC0173Jl.LONG);
        S = new EnumC1781xl("FIXED32_LIST", 24, 24, enumC1726wl2, EnumC0173Jl.INT);
        T = new EnumC1781xl("BOOL_LIST", 25, 25, enumC1726wl2, EnumC0173Jl.BOOLEAN);
        U = new EnumC1781xl("STRING_LIST", 26, 26, enumC1726wl2, EnumC0173Jl.STRING);
        V = new EnumC1781xl("MESSAGE_LIST", 27, 27, enumC1726wl2, EnumC0173Jl.MESSAGE);
        W = new EnumC1781xl("BYTES_LIST", 28, 28, enumC1726wl2, EnumC0173Jl.BYTE_STRING);
        X = new EnumC1781xl("UINT32_LIST", 29, 29, enumC1726wl2, EnumC0173Jl.INT);
        Y = new EnumC1781xl("ENUM_LIST", 30, 30, enumC1726wl2, EnumC0173Jl.ENUM);
        Z = new EnumC1781xl("SFIXED32_LIST", 31, 31, enumC1726wl2, EnumC0173Jl.INT);
        a0 = new EnumC1781xl("SFIXED64_LIST", 32, 32, enumC1726wl2, EnumC0173Jl.LONG);
        b0 = new EnumC1781xl("SINT32_LIST", 33, 33, enumC1726wl2, EnumC0173Jl.INT);
        c0 = new EnumC1781xl("SINT64_LIST", 34, 34, enumC1726wl2, EnumC0173Jl.LONG);
        d0 = new EnumC1781xl("DOUBLE_LIST_PACKED", 35, 35, enumC1726wl, EnumC0173Jl.DOUBLE);
        e0 = new EnumC1781xl("FLOAT_LIST_PACKED", 36, 36, enumC1726wl, EnumC0173Jl.FLOAT);
        f0 = new EnumC1781xl("INT64_LIST_PACKED", 37, 37, enumC1726wl, EnumC0173Jl.LONG);
        g0 = new EnumC1781xl("UINT64_LIST_PACKED", 38, 38, enumC1726wl, EnumC0173Jl.LONG);
        h0 = new EnumC1781xl("INT32_LIST_PACKED", 39, 39, enumC1726wl, EnumC0173Jl.INT);
        i0 = new EnumC1781xl("FIXED64_LIST_PACKED", 40, 40, enumC1726wl, EnumC0173Jl.LONG);
        j0 = new EnumC1781xl("FIXED32_LIST_PACKED", 41, 41, enumC1726wl, EnumC0173Jl.INT);
        k0 = new EnumC1781xl("BOOL_LIST_PACKED", 42, 42, enumC1726wl, EnumC0173Jl.BOOLEAN);
        l0 = new EnumC1781xl("UINT32_LIST_PACKED", 43, 43, enumC1726wl, EnumC0173Jl.INT);
        m0 = new EnumC1781xl("ENUM_LIST_PACKED", 44, 44, enumC1726wl, EnumC0173Jl.ENUM);
        n0 = new EnumC1781xl("SFIXED32_LIST_PACKED", 45, 45, enumC1726wl, EnumC0173Jl.INT);
        o0 = new EnumC1781xl("SFIXED64_LIST_PACKED", 46, 46, enumC1726wl, EnumC0173Jl.LONG);
        p0 = new EnumC1781xl("SINT32_LIST_PACKED", 47, 47, enumC1726wl, EnumC0173Jl.INT);
        q0 = new EnumC1781xl("SINT64_LIST_PACKED", 48, 48, enumC1726wl, EnumC0173Jl.LONG);
        r0 = new EnumC1781xl("GROUP_LIST", 49, 49, enumC1726wl2, EnumC0173Jl.MESSAGE);
        EnumC1781xl enumC1781xl = new EnumC1781xl("MAP", 50, 50, EnumC1726wl.MAP, EnumC0173Jl.VOID);
        s0 = enumC1781xl;
        u0 = new EnumC1781xl[]{u, v, w, x, y, z, A, B, C, D, E, F, G, H, I, f35J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a0, b0, c0, d0, e0, f0, g0, h0, i0, j0, k0, l0, m0, n0, o0, p0, q0, r0, enumC1781xl};
        EnumC1781xl[] enumC1781xlArrValues = values();
        t0 = new EnumC1781xl[enumC1781xlArrValues.length];
        for (EnumC1781xl enumC1781xl2 : enumC1781xlArrValues) {
            t0[enumC1781xl2.s] = enumC1781xl2;
        }
    }

    public EnumC1781xl(String str, int i, int i2, EnumC1726wl enumC1726wl, EnumC0173Jl enumC0173Jl) {
        this.s = i2;
        this.t = enumC1726wl;
        int iOrdinal = enumC1726wl.ordinal();
        if (iOrdinal == 1 || iOrdinal == 3) {
            Class cls = enumC0173Jl.s;
        }
        if (enumC1726wl == EnumC1726wl.SCALAR && enumC0173Jl.ordinal() == 6) {
        }
    }

    public static EnumC1781xl[] values() {
        return (EnumC1781xl[]) u0.clone();
    }
}
