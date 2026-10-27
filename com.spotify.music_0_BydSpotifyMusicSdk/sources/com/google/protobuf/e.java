package com.google.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import java.nio.charset.Charset;
import java.util.List;
import p.au;
import p.b91;
import p.cv0;
import p.e51;
import p.k72;
import p.km;
import p.rl0;
import p.ry1;
import p.t02;
import p.ta0;
import p.vd1;
import p.w51;

/* JADX INFO: loaded from: classes.dex */
public final class e implements t02 {
    public final au a;
    public int b;
    public int c;
    public int d = 0;

    public e(au auVar) {
        Charset charset = w51.a;
        if (auVar == null) {
            throw new NullPointerException("input");
        }
        this.a = auVar;
        auVar.d = this;
    }

    public static void A(int i) throws InvalidProtocolBufferException {
        if ((i & 7) != 0) {
            throw InvalidProtocolBufferException.g();
        }
    }

    public static void z(int i) throws InvalidProtocolBufferException {
        if ((i & 3) != 0) {
            throw InvalidProtocolBufferException.g();
        }
    }

    public final int a() {
        int i = this.d;
        if (i != 0) {
            this.b = i;
            this.d = 0;
        } else {
            this.b = this.a.A();
        }
        int i2 = this.b;
        if (i2 == 0 || i2 == this.c) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    public final void b(Object obj, k72 k72Var, rl0 rl0Var) {
        int i = this.c;
        this.c = ((this.b >>> 3) << 3) | 4;
        try {
            k72Var.g(obj, this, rl0Var);
            if (this.b == this.c) {
            } else {
                throw InvalidProtocolBufferException.g();
            }
        } finally {
            this.c = i;
        }
    }

    public final void c(Object obj, k72 k72Var, rl0 rl0Var) throws InvalidProtocolBufferException {
        au auVar = this.a;
        int iB = auVar.B();
        if (auVar.a >= auVar.b) {
            throw new InvalidProtocolBufferException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iJ = auVar.j(iB);
        auVar.a++;
        k72Var.g(obj, this, rl0Var);
        auVar.a(0);
        auVar.a--;
        auVar.i(iJ);
    }

    public final void d(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof km;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Boolean.valueOf(auVar.k()));
                    if (auVar.e()) {
                        return;
                    } else {
                        iA = auVar.A();
                    }
                } while (iA == this.b);
                this.d = iA;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.d();
            }
            int iD = auVar.d() + auVar.B();
            do {
                list.add(Boolean.valueOf(auVar.k()));
            } while (auVar.d() < iD);
            w(iD);
            return;
        }
        km kmVar = (km) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                kmVar.c(auVar.k());
                if (auVar.e()) {
                    return;
                } else {
                    iA2 = auVar.A();
                }
            } while (iA2 == this.b);
            this.d = iA2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.d();
        }
        int iD2 = auVar.d() + auVar.B();
        do {
            kmVar.c(auVar.k());
        } while (auVar.d() < iD2);
        w(iD2);
    }

    public final ByteString e() throws InvalidProtocolBufferException.InvalidWireTypeException {
        x(2);
        return this.a.l();
    }

    public final void f(List list) throws InvalidProtocolBufferException.InvalidWireTypeException {
        int iA;
        if ((this.b & 7) != 2) {
            throw InvalidProtocolBufferException.d();
        }
        do {
            list.add(e());
            au auVar = this.a;
            if (auVar.e()) {
                return;
            } else {
                iA = auVar.A();
            }
        } while (iA == this.b);
        this.d = iA;
    }

    public final void g(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof ta0;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 1) {
                do {
                    list.add(Double.valueOf(auVar.m()));
                    if (auVar.e()) {
                        return;
                    } else {
                        iA = auVar.A();
                    }
                } while (iA == this.b);
                this.d = iA;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.d();
            }
            int iB = auVar.B();
            A(iB);
            int iD = auVar.d() + iB;
            do {
                list.add(Double.valueOf(auVar.m()));
            } while (auVar.d() < iD);
            return;
        }
        ta0 ta0Var = (ta0) list;
        int i2 = this.b & 7;
        if (i2 == 1) {
            do {
                ta0Var.c(auVar.m());
                if (auVar.e()) {
                    return;
                } else {
                    iA2 = auVar.A();
                }
            } while (iA2 == this.b);
            this.d = iA2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.d();
        }
        int iB2 = auVar.B();
        A(iB2);
        int iD2 = auVar.d() + iB2;
        do {
            ta0Var.c(auVar.m());
        } while (auVar.d() < iD2);
    }

    public final void h(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof e51;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(auVar.n()));
                    if (auVar.e()) {
                        return;
                    } else {
                        iA = auVar.A();
                    }
                } while (iA == this.b);
                this.d = iA;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.d();
            }
            int iD = auVar.d() + auVar.B();
            do {
                list.add(Integer.valueOf(auVar.n()));
            } while (auVar.d() < iD);
            w(iD);
            return;
        }
        e51 e51Var = (e51) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                e51Var.c(auVar.n());
                if (auVar.e()) {
                    return;
                } else {
                    iA2 = auVar.A();
                }
            } while (iA2 == this.b);
            this.d = iA2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.d();
        }
        int iD2 = auVar.d() + auVar.B();
        do {
            e51Var.c(auVar.n());
        } while (auVar.d() < iD2);
        w(iD2);
    }

    public final Object i(WireFormat$FieldType wireFormat$FieldType, Class cls, rl0 rl0Var) throws InvalidProtocolBufferException.InvalidWireTypeException {
        int iOrdinal = wireFormat$FieldType.ordinal();
        au auVar = this.a;
        switch (iOrdinal) {
            case 0:
                x(1);
                return Double.valueOf(auVar.m());
            case 1:
                x(5);
                return Float.valueOf(auVar.q());
            case 2:
                x(0);
                return Long.valueOf(auVar.s());
            case 3:
                x(0);
                return Long.valueOf(auVar.C());
            case 4:
                x(0);
                return Integer.valueOf(auVar.r());
            case 5:
                x(1);
                return Long.valueOf(auVar.p());
            case 6:
                x(5);
                return Integer.valueOf(auVar.o());
            case 7:
                x(0);
                return Boolean.valueOf(auVar.k());
            case 8:
                x(2);
                return auVar.z();
            case 9:
            default:
                throw new IllegalArgumentException("unsupported field type.");
            case 10:
                return o(cls, rl0Var);
            case 11:
                return e();
            case 12:
                x(0);
                return Integer.valueOf(auVar.B());
            case 13:
                x(0);
                return Integer.valueOf(auVar.n());
            case 14:
                x(5);
                return Integer.valueOf(auVar.u());
            case 15:
                x(1);
                return Long.valueOf(auVar.v());
            case 16:
                x(0);
                return Integer.valueOf(auVar.w());
            case 17:
                x(0);
                return Long.valueOf(auVar.x());
        }
    }

    public final void j(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof e51;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 2) {
                int iB = auVar.B();
                z(iB);
                int iD = auVar.d() + iB;
                do {
                    list.add(Integer.valueOf(auVar.o()));
                } while (auVar.d() < iD);
                return;
            }
            if (i != 5) {
                throw InvalidProtocolBufferException.d();
            }
            do {
                list.add(Integer.valueOf(auVar.o()));
                if (auVar.e()) {
                    return;
                } else {
                    iA = auVar.A();
                }
            } while (iA == this.b);
            this.d = iA;
            return;
        }
        e51 e51Var = (e51) list;
        int i2 = this.b & 7;
        if (i2 == 2) {
            int iB2 = auVar.B();
            z(iB2);
            int iD2 = auVar.d() + iB2;
            do {
                e51Var.c(auVar.o());
            } while (auVar.d() < iD2);
            return;
        }
        if (i2 != 5) {
            throw InvalidProtocolBufferException.d();
        }
        do {
            e51Var.c(auVar.o());
            if (auVar.e()) {
                return;
            } else {
                iA2 = auVar.A();
            }
        } while (iA2 == this.b);
        this.d = iA2;
    }

    public final void k(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof vd1;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 1) {
                do {
                    list.add(Long.valueOf(auVar.p()));
                    if (auVar.e()) {
                        return;
                    } else {
                        iA = auVar.A();
                    }
                } while (iA == this.b);
                this.d = iA;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.d();
            }
            int iB = auVar.B();
            A(iB);
            int iD = auVar.d() + iB;
            do {
                list.add(Long.valueOf(auVar.p()));
            } while (auVar.d() < iD);
            return;
        }
        vd1 vd1Var = (vd1) list;
        int i2 = this.b & 7;
        if (i2 == 1) {
            do {
                vd1Var.c(auVar.p());
                if (auVar.e()) {
                    return;
                } else {
                    iA2 = auVar.A();
                }
            } while (iA2 == this.b);
            this.d = iA2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.d();
        }
        int iB2 = auVar.B();
        A(iB2);
        int iD2 = auVar.d() + iB2;
        do {
            vd1Var.c(auVar.p());
        } while (auVar.d() < iD2);
    }

    public final void l(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof cv0;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 2) {
                int iB = auVar.B();
                z(iB);
                int iD = auVar.d() + iB;
                do {
                    list.add(Float.valueOf(auVar.q()));
                } while (auVar.d() < iD);
                return;
            }
            if (i != 5) {
                throw InvalidProtocolBufferException.d();
            }
            do {
                list.add(Float.valueOf(auVar.q()));
                if (auVar.e()) {
                    return;
                } else {
                    iA = auVar.A();
                }
            } while (iA == this.b);
            this.d = iA;
            return;
        }
        cv0 cv0Var = (cv0) list;
        int i2 = this.b & 7;
        if (i2 == 2) {
            int iB2 = auVar.B();
            z(iB2);
            int iD2 = auVar.d() + iB2;
            do {
                cv0Var.c(auVar.q());
            } while (auVar.d() < iD2);
            return;
        }
        if (i2 != 5) {
            throw InvalidProtocolBufferException.d();
        }
        do {
            cv0Var.c(auVar.q());
            if (auVar.e()) {
                return;
            } else {
                iA2 = auVar.A();
            }
        } while (iA2 == this.b);
        this.d = iA2;
    }

    public final void m(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof e51;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(auVar.r()));
                    if (auVar.e()) {
                        return;
                    } else {
                        iA = auVar.A();
                    }
                } while (iA == this.b);
                this.d = iA;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.d();
            }
            int iD = auVar.d() + auVar.B();
            do {
                list.add(Integer.valueOf(auVar.r()));
            } while (auVar.d() < iD);
            w(iD);
            return;
        }
        e51 e51Var = (e51) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                e51Var.c(auVar.r());
                if (auVar.e()) {
                    return;
                } else {
                    iA2 = auVar.A();
                }
            } while (iA2 == this.b);
            this.d = iA2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.d();
        }
        int iD2 = auVar.d() + auVar.B();
        do {
            e51Var.c(auVar.r());
        } while (auVar.d() < iD2);
        w(iD2);
    }

    public final void n(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof vd1;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Long.valueOf(auVar.s()));
                    if (auVar.e()) {
                        return;
                    } else {
                        iA = auVar.A();
                    }
                } while (iA == this.b);
                this.d = iA;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.d();
            }
            int iD = auVar.d() + auVar.B();
            do {
                list.add(Long.valueOf(auVar.s()));
            } while (auVar.d() < iD);
            w(iD);
            return;
        }
        vd1 vd1Var = (vd1) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                vd1Var.c(auVar.s());
                if (auVar.e()) {
                    return;
                } else {
                    iA2 = auVar.A();
                }
            } while (iA2 == this.b);
            this.d = iA2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.d();
        }
        int iD2 = auVar.d() + auVar.B();
        do {
            vd1Var.c(auVar.s());
        } while (auVar.d() < iD2);
        w(iD2);
    }

    public final Object o(Class cls, rl0 rl0Var) throws InvalidProtocolBufferException {
        x(2);
        k72 k72VarA = ry1.c.a(cls);
        Object objI = k72VarA.i();
        c(objI, k72VarA, rl0Var);
        k72VarA.c(objI);
        return objI;
    }

    public final void p(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof e51;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 2) {
                int iB = auVar.B();
                z(iB);
                int iD = auVar.d() + iB;
                do {
                    list.add(Integer.valueOf(auVar.u()));
                } while (auVar.d() < iD);
                return;
            }
            if (i != 5) {
                throw InvalidProtocolBufferException.d();
            }
            do {
                list.add(Integer.valueOf(auVar.u()));
                if (auVar.e()) {
                    return;
                } else {
                    iA = auVar.A();
                }
            } while (iA == this.b);
            this.d = iA;
            return;
        }
        e51 e51Var = (e51) list;
        int i2 = this.b & 7;
        if (i2 == 2) {
            int iB2 = auVar.B();
            z(iB2);
            int iD2 = auVar.d() + iB2;
            do {
                e51Var.c(auVar.u());
            } while (auVar.d() < iD2);
            return;
        }
        if (i2 != 5) {
            throw InvalidProtocolBufferException.d();
        }
        do {
            e51Var.c(auVar.u());
            if (auVar.e()) {
                return;
            } else {
                iA2 = auVar.A();
            }
        } while (iA2 == this.b);
        this.d = iA2;
    }

    public final void q(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof vd1;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 1) {
                do {
                    list.add(Long.valueOf(auVar.v()));
                    if (auVar.e()) {
                        return;
                    } else {
                        iA = auVar.A();
                    }
                } while (iA == this.b);
                this.d = iA;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.d();
            }
            int iB = auVar.B();
            A(iB);
            int iD = auVar.d() + iB;
            do {
                list.add(Long.valueOf(auVar.v()));
            } while (auVar.d() < iD);
            return;
        }
        vd1 vd1Var = (vd1) list;
        int i2 = this.b & 7;
        if (i2 == 1) {
            do {
                vd1Var.c(auVar.v());
                if (auVar.e()) {
                    return;
                } else {
                    iA2 = auVar.A();
                }
            } while (iA2 == this.b);
            this.d = iA2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.d();
        }
        int iB2 = auVar.B();
        A(iB2);
        int iD2 = auVar.d() + iB2;
        do {
            vd1Var.c(auVar.v());
        } while (auVar.d() < iD2);
    }

    public final void r(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof e51;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(auVar.w()));
                    if (auVar.e()) {
                        return;
                    } else {
                        iA = auVar.A();
                    }
                } while (iA == this.b);
                this.d = iA;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.d();
            }
            int iD = auVar.d() + auVar.B();
            do {
                list.add(Integer.valueOf(auVar.w()));
            } while (auVar.d() < iD);
            w(iD);
            return;
        }
        e51 e51Var = (e51) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                e51Var.c(auVar.w());
                if (auVar.e()) {
                    return;
                } else {
                    iA2 = auVar.A();
                }
            } while (iA2 == this.b);
            this.d = iA2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.d();
        }
        int iD2 = auVar.d() + auVar.B();
        do {
            e51Var.c(auVar.w());
        } while (auVar.d() < iD2);
        w(iD2);
    }

    public final void s(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof vd1;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Long.valueOf(auVar.x()));
                    if (auVar.e()) {
                        return;
                    } else {
                        iA = auVar.A();
                    }
                } while (iA == this.b);
                this.d = iA;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.d();
            }
            int iD = auVar.d() + auVar.B();
            do {
                list.add(Long.valueOf(auVar.x()));
            } while (auVar.d() < iD);
            w(iD);
            return;
        }
        vd1 vd1Var = (vd1) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                vd1Var.c(auVar.x());
                if (auVar.e()) {
                    return;
                } else {
                    iA2 = auVar.A();
                }
            } while (iA2 == this.b);
            this.d = iA2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.d();
        }
        int iD2 = auVar.d() + auVar.B();
        do {
            vd1Var.c(auVar.x());
        } while (auVar.d() < iD2);
        w(iD2);
    }

    public final void t(List list, boolean z) throws InvalidProtocolBufferException.InvalidWireTypeException {
        String strY;
        int iA;
        int iA2;
        if ((this.b & 7) != 2) {
            throw InvalidProtocolBufferException.d();
        }
        boolean z2 = list instanceof b91;
        au auVar = this.a;
        if (z2 && !z) {
            b91 b91Var = (b91) list;
            do {
                b91Var.g(e());
                if (auVar.e()) {
                    return;
                } else {
                    iA2 = auVar.A();
                }
            } while (iA2 == this.b);
            this.d = iA2;
            return;
        }
        do {
            if (z) {
                x(2);
                strY = auVar.z();
            } else {
                x(2);
                strY = auVar.y();
            }
            list.add(strY);
            if (auVar.e()) {
                return;
            } else {
                iA = auVar.A();
            }
        } while (iA == this.b);
        this.d = iA;
    }

    public final void u(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof e51;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(auVar.B()));
                    if (auVar.e()) {
                        return;
                    } else {
                        iA = auVar.A();
                    }
                } while (iA == this.b);
                this.d = iA;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.d();
            }
            int iD = auVar.d() + auVar.B();
            do {
                list.add(Integer.valueOf(auVar.B()));
            } while (auVar.d() < iD);
            w(iD);
            return;
        }
        e51 e51Var = (e51) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                e51Var.c(auVar.B());
                if (auVar.e()) {
                    return;
                } else {
                    iA2 = auVar.A();
                }
            } while (iA2 == this.b);
            this.d = iA2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.d();
        }
        int iD2 = auVar.d() + auVar.B();
        do {
            e51Var.c(auVar.B());
        } while (auVar.d() < iD2);
        w(iD2);
    }

    public final void v(List list) throws InvalidProtocolBufferException {
        int iA;
        int iA2;
        boolean z = list instanceof vd1;
        au auVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Long.valueOf(auVar.C()));
                    if (auVar.e()) {
                        return;
                    } else {
                        iA = auVar.A();
                    }
                } while (iA == this.b);
                this.d = iA;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.d();
            }
            int iD = auVar.d() + auVar.B();
            do {
                list.add(Long.valueOf(auVar.C()));
            } while (auVar.d() < iD);
            w(iD);
            return;
        }
        vd1 vd1Var = (vd1) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                vd1Var.c(auVar.C());
                if (auVar.e()) {
                    return;
                } else {
                    iA2 = auVar.A();
                }
            } while (iA2 == this.b);
            this.d = iA2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.d();
        }
        int iD2 = auVar.d() + auVar.B();
        do {
            vd1Var.c(auVar.C());
        } while (auVar.d() < iD2);
        w(iD2);
    }

    public final void w(int i) throws InvalidProtocolBufferException {
        if (this.a.d() != i) {
            throw InvalidProtocolBufferException.j();
        }
    }

    public final void x(int i) throws InvalidProtocolBufferException.InvalidWireTypeException {
        if ((this.b & 7) != i) {
            throw InvalidProtocolBufferException.d();
        }
    }

    public final boolean y() {
        int i;
        au auVar = this.a;
        if (auVar.e() || (i = this.b) == this.c) {
            return false;
        }
        return auVar.D(i);
    }
}
