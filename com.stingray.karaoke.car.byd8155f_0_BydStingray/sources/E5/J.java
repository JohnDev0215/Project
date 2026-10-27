package E5;

import java.io.EOFException;
import java.math.BigDecimal;
import r9.C2319i;

/* JADX INFO: loaded from: classes.dex */
public final class J extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1775a;

    public /* synthetic */ J(int i7) {
        this.f1775a = i7;
    }

    @Override // E5.q
    public final Object a(u uVar) throws s, EOFException {
        long j3;
        switch (this.f1775a) {
            case 0:
                return uVar.y();
            case 1:
                v vVar = (v) uVar;
                int iN = vVar.f1829r;
                if (iN == 0) {
                    iN = vVar.N();
                }
                boolean z6 = false;
                if (iN == 5) {
                    vVar.f1829r = 0;
                    int[] iArr = vVar.o;
                    int i7 = vVar.l - 1;
                    iArr[i7] = iArr[i7] + 1;
                    z6 = true;
                } else {
                    if (iN != 6) {
                        throw new A9.k("Expected a boolean but was " + A7.k.w(vVar.z()) + " at path " + vVar.k());
                    }
                    vVar.f1829r = 0;
                    int[] iArr2 = vVar.o;
                    int i10 = vVar.l - 1;
                    iArr2[i10] = iArr2[i10] + 1;
                }
                return Boolean.valueOf(z6);
            case 2:
                return Byte.valueOf((byte) M.h(uVar, "a byte", -128, 255));
            case 3:
                String strY = uVar.y();
                if (strY.length() <= 1) {
                    return Character.valueOf(strY.charAt(0));
                }
                throw new A9.k("Expected a char but was " + ("\"" + strY + '\"') + " at path " + uVar.k());
            case 4:
                return Double.valueOf(uVar.o());
            case 5:
                float fO = (float) uVar.o();
                if (!Float.isInfinite(fO)) {
                    return Float.valueOf(fO);
                }
                throw new A9.k("JSON forbids NaN and infinities: " + fO + " at path " + uVar.k());
            case 6:
                return Integer.valueOf(uVar.q());
            case 7:
                v vVar2 = (v) uVar;
                int iN2 = vVar2.f1829r;
                if (iN2 == 0) {
                    iN2 = vVar2.N();
                }
                if (iN2 == 16) {
                    vVar2.f1829r = 0;
                    int[] iArr3 = vVar2.o;
                    int i11 = vVar2.l - 1;
                    iArr3[i11] = iArr3[i11] + 1;
                    j3 = vVar2.f1830s;
                } else {
                    if (iN2 == 17) {
                        long j10 = vVar2.f1831t;
                        C2319i c2319i = vVar2.f1828q;
                        c2319i.getClass();
                        vVar2.f1832u = c2319i.P(j10, Q8.a.f7211a);
                    } else if (iN2 == 9 || iN2 == 8) {
                        String strT = iN2 == 9 ? vVar2.T(v.f1825w) : vVar2.T(v.f1824v);
                        vVar2.f1832u = strT;
                        try {
                            long j11 = Long.parseLong(strT);
                            vVar2.f1829r = 0;
                            int[] iArr4 = vVar2.o;
                            int i12 = vVar2.l - 1;
                            iArr4[i12] = iArr4[i12] + 1;
                            j3 = j11;
                        } catch (NumberFormatException unused) {
                            vVar2.f1829r = 11;
                            long jLongValueExact = new BigDecimal(vVar2.f1832u).longValueExact();
                            vVar2.f1832u = null;
                            vVar2.f1829r = 0;
                            int[] iArr5 = vVar2.o;
                            int i13 = vVar2.l - 1;
                            iArr5[i13] = iArr5[i13] + 1;
                            j3 = jLongValueExact;
                        }
                    } else if (iN2 != 11) {
                        throw new A9.k("Expected a long but was " + A7.k.w(vVar2.z()) + " at path " + vVar2.k());
                    }
                    vVar2.f1829r = 11;
                    try {
                        long jLongValueExact2 = new BigDecimal(vVar2.f1832u).longValueExact();
                        vVar2.f1832u = null;
                        vVar2.f1829r = 0;
                        int[] iArr52 = vVar2.o;
                        int i132 = vVar2.l - 1;
                        iArr52[i132] = iArr52[i132] + 1;
                        j3 = jLongValueExact2;
                    } catch (ArithmeticException | NumberFormatException unused2) {
                        throw new A9.k("Expected a long but was " + vVar2.f1832u + " at path " + vVar2.k());
                    }
                }
                return Long.valueOf(j3);
            default:
                return Short.valueOf((short) M.h(uVar, "a short", -32768, 32767));
        }
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        switch (this.f1775a) {
            case 0:
                xVar.v((String) obj);
                return;
            case 1:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                w wVar = (w) xVar;
                if (wVar.f1838p) {
                    throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + wVar.j());
                }
                wVar.G();
                wVar.y();
                wVar.f1834r.c0(zBooleanValue ? "true" : "false");
                int[] iArr = wVar.o;
                int i7 = wVar.l - 1;
                iArr[i7] = iArr[i7] + 1;
                return;
            case 2:
                xVar.q(((Byte) obj).intValue() & 255);
                return;
            case 3:
                xVar.v(((Character) obj).toString());
                return;
            case 4:
                double dDoubleValue = ((Double) obj).doubleValue();
                w wVar2 = (w) xVar;
                wVar2.getClass();
                if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                    throw new IllegalArgumentException("Numeric values must be finite, but was " + dDoubleValue);
                }
                if (wVar2.f1838p) {
                    wVar2.f1838p = false;
                    wVar2.k(Double.toString(dDoubleValue));
                    return;
                }
                wVar2.G();
                wVar2.y();
                wVar2.f1834r.c0(Double.toString(dDoubleValue));
                int[] iArr2 = wVar2.o;
                int i10 = wVar2.l - 1;
                iArr2[i10] = iArr2[i10] + 1;
                return;
            case 5:
                Float f10 = (Float) obj;
                f10.getClass();
                w wVar3 = (w) xVar;
                wVar3.getClass();
                String string = f10.toString();
                if (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN")) {
                    throw new IllegalArgumentException("Numeric values must be finite, but was " + f10);
                }
                if (wVar3.f1838p) {
                    wVar3.f1838p = false;
                    wVar3.k(string);
                    return;
                }
                wVar3.G();
                wVar3.y();
                wVar3.f1834r.c0(string);
                int[] iArr3 = wVar3.o;
                int i11 = wVar3.l - 1;
                iArr3[i11] = iArr3[i11] + 1;
                return;
            case 6:
                xVar.q(((Integer) obj).intValue());
                return;
            case 7:
                xVar.q(((Long) obj).longValue());
                return;
            default:
                xVar.q(((Short) obj).intValue());
                return;
        }
    }

    public final String toString() {
        switch (this.f1775a) {
            case 0:
                return "JsonAdapter(String)";
            case 1:
                return "JsonAdapter(Boolean)";
            case 2:
                return "JsonAdapter(Byte)";
            case 3:
                return "JsonAdapter(Character)";
            case 4:
                return "JsonAdapter(Double)";
            case 5:
                return "JsonAdapter(Float)";
            case 6:
                return "JsonAdapter(Integer)";
            case 7:
                return "JsonAdapter(Long)";
            default:
                return "JsonAdapter(Short)";
        }
    }
}
