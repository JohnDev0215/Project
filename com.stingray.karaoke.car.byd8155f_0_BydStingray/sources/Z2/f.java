package Z2;

import B2.N;
import T.C0746i0;
import android.text.SpannableStringBuilder;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import h2.AbstractC1348e;
import h2.t;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class f extends h {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final t f10871h = new t();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final N f10872i = new N();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f10873j = -1;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f10874k;
    public final e[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public e f10875m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f10876n;
    public List o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public N f10877p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f10878q;

    public f(int i7, List list) {
        this.f10874k = i7 == -1 ? 1 : i7;
        if (list != null) {
            byte[] bArr = AbstractC1348e.f15208a;
            if (list.size() == 1 && ((byte[]) list.get(0)).length == 1) {
                byte b10 = ((byte[]) list.get(0))[0];
            }
        }
        this.l = new e[8];
        for (int i10 = 0; i10 < 8; i10++) {
            this.l[i10] = new e();
        }
        this.f10875m = this.l[0];
    }

    @Override // Z2.h, m2.d
    public final void flush() {
        super.flush();
        this.f10876n = null;
        this.o = null;
        this.f10878q = 0;
        this.f10875m = this.l[0];
        m();
        this.f10877p = null;
    }

    @Override // Z2.h
    public final C0746i0 g() {
        List list = this.f10876n;
        this.o = list;
        list.getClass();
        return new C0746i0(list);
    }

    @Override // Z2.h
    public final void h(g gVar) {
        ByteBuffer byteBuffer = gVar.f18955p;
        byteBuffer.getClass();
        byte[] bArrArray = byteBuffer.array();
        int iLimit = byteBuffer.limit();
        t tVar = this.f10871h;
        tVar.F(iLimit, bArrArray);
        while (tVar.a() >= 3) {
            int iV = tVar.v();
            int i7 = iV & 3;
            boolean z6 = (iV & 4) == 4;
            byte bV = (byte) tVar.v();
            byte bV2 = (byte) tVar.v();
            if (i7 == 2 || i7 == 3) {
                if (z6) {
                    if (i7 == 3) {
                        k();
                        int i10 = (bV & 192) >> 6;
                        int i11 = this.f10873j;
                        if (i11 != -1 && i10 != (i11 + 1) % 4) {
                            m();
                            AbstractC1346c.z("Cea708Decoder", "Sequence number discontinuity. previous=" + this.f10873j + " current=" + i10);
                        }
                        this.f10873j = i10;
                        int i12 = bV & 63;
                        if (i12 == 0) {
                            i12 = 64;
                        }
                        N n10 = new N(i10, i12);
                        this.f10877p = n10;
                        n10.f526e = 1;
                        n10.d[0] = bV2;
                    } else {
                        AbstractC1346c.c(i7 == 2);
                        N n11 = this.f10877p;
                        if (n11 == null) {
                            AbstractC1346c.p("Cea708Decoder", "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
                        } else {
                            byte[] bArr = n11.d;
                            int i13 = n11.f526e;
                            int i14 = i13 + 1;
                            n11.f526e = i14;
                            bArr[i13] = bV;
                            n11.f526e = i13 + 2;
                            bArr[i14] = bV2;
                        }
                    }
                    N n12 = this.f10877p;
                    if (n12.f526e == (n12.c * 2) - 1) {
                        k();
                    }
                }
            }
        }
    }

    @Override // Z2.h
    public final boolean j() {
        return this.f10876n != this.o;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void k() {
        int i7;
        int i10;
        boolean z6;
        char c;
        int i11;
        N n10 = this.f10877p;
        if (n10 == null) {
            return;
        }
        int i12 = 2;
        if (n10.f526e != (n10.c * 2) - 1) {
            AbstractC1346c.n("Cea708Decoder", "DtvCcPacket ended prematurely; size is " + ((this.f10877p.c * 2) - 1) + ", but current index is " + this.f10877p.f526e + " (sequence number " + this.f10877p.f525b + ");");
        }
        N n11 = this.f10877p;
        byte[] bArr = n11.d;
        int i13 = n11.f526e;
        N n12 = this.f10872i;
        n12.p(i13, bArr);
        boolean z10 = false;
        while (true) {
            if (n12.b() > 0) {
                int i14 = 3;
                int i15 = n12.i(3);
                int i16 = n12.i(5);
                if (i15 == 7) {
                    n12.u(i12);
                    i15 = n12.i(6);
                    if (i15 < 7) {
                        AbstractC1347d.p("Invalid extended service number: ", i15, "Cea708Decoder");
                    }
                }
                if (i16 == 0) {
                    if (i15 != 0) {
                        AbstractC1346c.z("Cea708Decoder", "serviceNumber is non-zero (" + i15 + ") when blockSize is 0");
                    }
                } else if (i15 != this.f10874k) {
                    n12.v(i16);
                } else {
                    int iG = (i16 * 8) + n12.g();
                    while (n12.g() < iG) {
                        int i17 = n12.i(8);
                        if (i17 != 16) {
                            if (i17 <= 31) {
                                if (i17 != 0) {
                                    if (i17 == i14) {
                                        this.f10876n = l();
                                    } else if (i17 != 8) {
                                        switch (i17) {
                                            case 12:
                                                m();
                                                break;
                                            case 13:
                                                this.f10875m.a('\n');
                                                break;
                                            case 14:
                                                break;
                                            default:
                                                if (i17 >= 17 && i17 <= 23) {
                                                    AbstractC1346c.z("Cea708Decoder", "Currently unsupported COMMAND_EXT1 Command: " + i17);
                                                    n12.u(8);
                                                } else if (i17 < 24 || i17 > 31) {
                                                    AbstractC1347d.p("Invalid C0 command: ", i17, "Cea708Decoder");
                                                } else {
                                                    AbstractC1346c.z("Cea708Decoder", "Currently unsupported COMMAND_P16 Command: " + i17);
                                                    n12.u(16);
                                                }
                                                break;
                                        }
                                    } else {
                                        SpannableStringBuilder spannableStringBuilder = this.f10875m.f10855b;
                                        int length = spannableStringBuilder.length();
                                        if (length > 0) {
                                            spannableStringBuilder.delete(length - 1, length);
                                        }
                                    }
                                }
                                i11 = i12;
                                i7 = i14;
                                i10 = iG;
                            } else if (i17 <= 127) {
                                if (i17 == 127) {
                                    this.f10875m.a((char) 9835);
                                } else {
                                    this.f10875m.a((char) (i17 & 255));
                                }
                                i11 = i12;
                                i7 = i14;
                                i10 = iG;
                                z10 = true;
                            } else {
                                if (i17 <= 159) {
                                    e[] eVarArr = this.l;
                                    switch (i17) {
                                        case 128:
                                        case 129:
                                        case 130:
                                        case 131:
                                        case 132:
                                        case 133:
                                        case 134:
                                        case 135:
                                            i7 = i14;
                                            i10 = iG;
                                            z6 = true;
                                            int i18 = i17 - 128;
                                            if (this.f10878q != i18) {
                                                this.f10878q = i18;
                                                this.f10875m = eVarArr[i18];
                                            }
                                            break;
                                        case 136:
                                            i7 = i14;
                                            i10 = iG;
                                            z6 = true;
                                            for (int i19 = 1; i19 <= 8; i19++) {
                                                if (n12.h()) {
                                                    e eVar = eVarArr[8 - i19];
                                                    eVar.f10854a.clear();
                                                    eVar.f10855b.clear();
                                                    eVar.o = -1;
                                                    eVar.f10865p = -1;
                                                    eVar.f10866q = -1;
                                                    eVar.f10868s = -1;
                                                    eVar.f10870u = 0;
                                                }
                                            }
                                            break;
                                        case 137:
                                            i7 = i14;
                                            i10 = iG;
                                            for (int i20 = 1; i20 <= 8; i20++) {
                                                if (n12.h()) {
                                                    eVarArr[8 - i20].d = true;
                                                }
                                            }
                                            z6 = true;
                                            break;
                                        case 138:
                                            i7 = i14;
                                            i10 = iG;
                                            for (int i21 = 1; i21 <= 8; i21++) {
                                                if (n12.h()) {
                                                    eVarArr[8 - i21].d = false;
                                                }
                                            }
                                            z6 = true;
                                            break;
                                        case 139:
                                            i7 = i14;
                                            i10 = iG;
                                            for (int i22 = 1; i22 <= 8; i22++) {
                                                if (n12.h()) {
                                                    eVarArr[8 - i22].d = !r1.d;
                                                }
                                            }
                                            z6 = true;
                                            break;
                                        case 140:
                                            i7 = i14;
                                            i10 = iG;
                                            for (int i23 = 1; i23 <= 8; i23++) {
                                                if (n12.h()) {
                                                    eVarArr[8 - i23].d();
                                                }
                                            }
                                            z6 = true;
                                            break;
                                        case 141:
                                            i7 = i14;
                                            i10 = iG;
                                            n12.u(8);
                                            z6 = true;
                                            break;
                                        case 142:
                                            i7 = i14;
                                            i10 = iG;
                                            z6 = true;
                                            break;
                                        case 143:
                                            i7 = i14;
                                            i10 = iG;
                                            m();
                                            z6 = true;
                                            break;
                                        case 144:
                                            i10 = iG;
                                            if (this.f10875m.c) {
                                                n12.i(4);
                                                n12.i(2);
                                                n12.i(2);
                                                boolean zH = n12.h();
                                                boolean zH2 = n12.h();
                                                i7 = 3;
                                                n12.i(3);
                                                n12.i(3);
                                                this.f10875m.e(zH, zH2);
                                                z6 = true;
                                            } else {
                                                n12.u(16);
                                                i7 = 3;
                                                z6 = true;
                                            }
                                            break;
                                        case 145:
                                            i10 = iG;
                                            if (this.f10875m.c) {
                                                int iC = e.c(n12.i(2), n12.i(2), n12.i(2), n12.i(2));
                                                int iC2 = e.c(n12.i(2), n12.i(2), n12.i(2), n12.i(2));
                                                n12.u(2);
                                                e.c(n12.i(2), n12.i(2), n12.i(2), 0);
                                                this.f10875m.f(iC, iC2);
                                            } else {
                                                n12.u(24);
                                            }
                                            i7 = 3;
                                            z6 = true;
                                            break;
                                        case 146:
                                            i10 = iG;
                                            if (this.f10875m.c) {
                                                n12.u(4);
                                                int i24 = n12.i(4);
                                                n12.u(2);
                                                n12.i(6);
                                                e eVar2 = this.f10875m;
                                                if (eVar2.f10870u != i24) {
                                                    eVar2.a('\n');
                                                }
                                                eVar2.f10870u = i24;
                                            } else {
                                                n12.u(16);
                                            }
                                            i7 = 3;
                                            z6 = true;
                                            break;
                                        case 147:
                                        case 148:
                                        case 149:
                                        case 150:
                                        default:
                                            AbstractC1347d.p("Invalid C1 command: ", i17, "Cea708Decoder");
                                            i7 = i14;
                                            i10 = iG;
                                            z6 = true;
                                            break;
                                        case 151:
                                            i10 = iG;
                                            if (this.f10875m.c) {
                                                int iC3 = e.c(n12.i(2), n12.i(2), n12.i(2), n12.i(2));
                                                n12.i(2);
                                                e.c(n12.i(2), n12.i(2), n12.i(2), 0);
                                                n12.h();
                                                n12.h();
                                                n12.i(2);
                                                n12.i(2);
                                                int i25 = n12.i(2);
                                                n12.u(8);
                                                e eVar3 = this.f10875m;
                                                eVar3.f10864n = iC3;
                                                eVar3.f10862k = i25;
                                            } else {
                                                n12.u(32);
                                            }
                                            i7 = 3;
                                            z6 = true;
                                            break;
                                        case 152:
                                        case 153:
                                        case 154:
                                        case 155:
                                        case 156:
                                        case 157:
                                        case 158:
                                        case 159:
                                            int i26 = i17 - 152;
                                            e eVar4 = eVarArr[i26];
                                            n12.u(i12);
                                            boolean zH3 = n12.h();
                                            n12.u(i12);
                                            int i27 = n12.i(i14);
                                            boolean zH4 = n12.h();
                                            int i28 = n12.i(7);
                                            int i29 = n12.i(8);
                                            int i30 = n12.i(4);
                                            int i31 = n12.i(4);
                                            n12.u(i12);
                                            n12.u(6);
                                            n12.u(i12);
                                            int i32 = n12.i(3);
                                            i10 = iG;
                                            int i33 = n12.i(3);
                                            eVar4.c = true;
                                            eVar4.d = zH3;
                                            eVar4.f10856e = i27;
                                            eVar4.f10857f = zH4;
                                            eVar4.f10858g = i28;
                                            eVar4.f10859h = i29;
                                            eVar4.f10860i = i30;
                                            int i34 = i31 + 1;
                                            if (eVar4.f10861j != i34) {
                                                eVar4.f10861j = i34;
                                                while (true) {
                                                    ArrayList arrayList = eVar4.f10854a;
                                                    if (arrayList.size() >= eVar4.f10861j || arrayList.size() >= 15) {
                                                        arrayList.remove(0);
                                                    }
                                                }
                                            }
                                            if (i32 != 0 && eVar4.l != i32) {
                                                eVar4.l = i32;
                                                int i35 = i32 - 1;
                                                int i36 = e.B[i35];
                                                boolean z11 = e.f10845A[i35];
                                                int i37 = e.f10852y[i35];
                                                int i38 = e.f10853z[i35];
                                                int i39 = e.f10851x[i35];
                                                eVar4.f10864n = i36;
                                                eVar4.f10862k = i39;
                                            }
                                            if (i33 != 0 && eVar4.f10863m != i33) {
                                                eVar4.f10863m = i33;
                                                int i40 = i33 - 1;
                                                int i41 = e.f10847D[i40];
                                                int i42 = e.f10846C[i40];
                                                eVar4.e(false, false);
                                                eVar4.f(e.f10849v, e.f10848E[i40]);
                                            }
                                            if (this.f10878q != i26) {
                                                this.f10878q = i26;
                                                this.f10875m = eVarArr[i26];
                                            }
                                            i7 = 3;
                                            z6 = true;
                                            break;
                                    }
                                } else {
                                    i7 = i14;
                                    i10 = iG;
                                    z6 = true;
                                    if (i17 <= 255) {
                                        this.f10875m.a((char) (i17 & 255));
                                    } else {
                                        AbstractC1347d.p("Invalid base command: ", i17, "Cea708Decoder");
                                        i11 = 2;
                                        c = 7;
                                    }
                                }
                                z10 = z6;
                                i11 = 2;
                                c = 7;
                            }
                            z6 = true;
                            c = 7;
                        } else {
                            i7 = i14;
                            i10 = iG;
                            z6 = true;
                            int i43 = n12.i(8);
                            if (i43 <= 31) {
                                c = 7;
                                if (i43 > 7) {
                                    if (i43 <= 15) {
                                        n12.u(8);
                                    } else if (i43 <= 23) {
                                        n12.u(16);
                                    } else if (i43 <= 31) {
                                        n12.u(24);
                                    }
                                }
                            } else {
                                c = 7;
                                if (i43 <= 127) {
                                    if (i43 == 32) {
                                        this.f10875m.a(' ');
                                    } else if (i43 == 33) {
                                        this.f10875m.a((char) 160);
                                    } else if (i43 == 37) {
                                        this.f10875m.a((char) 8230);
                                    } else if (i43 == 42) {
                                        this.f10875m.a((char) 352);
                                    } else if (i43 == 44) {
                                        this.f10875m.a((char) 338);
                                    } else if (i43 == 63) {
                                        this.f10875m.a((char) 376);
                                    } else if (i43 == 57) {
                                        this.f10875m.a((char) 8482);
                                    } else if (i43 == 58) {
                                        this.f10875m.a((char) 353);
                                    } else if (i43 == 60) {
                                        this.f10875m.a((char) 339);
                                    } else if (i43 != 61) {
                                        switch (i43) {
                                            case 48:
                                                this.f10875m.a((char) 9608);
                                                break;
                                            case 49:
                                                this.f10875m.a((char) 8216);
                                                break;
                                            case 50:
                                                this.f10875m.a((char) 8217);
                                                break;
                                            case 51:
                                                this.f10875m.a((char) 8220);
                                                break;
                                            case 52:
                                                this.f10875m.a((char) 8221);
                                                break;
                                            case 53:
                                                this.f10875m.a((char) 8226);
                                                break;
                                            default:
                                                switch (i43) {
                                                    case 118:
                                                        this.f10875m.a((char) 8539);
                                                        break;
                                                    case 119:
                                                        this.f10875m.a((char) 8540);
                                                        break;
                                                    case 120:
                                                        this.f10875m.a((char) 8541);
                                                        break;
                                                    case 121:
                                                        this.f10875m.a((char) 8542);
                                                        break;
                                                    case 122:
                                                        this.f10875m.a((char) 9474);
                                                        break;
                                                    case 123:
                                                        this.f10875m.a((char) 9488);
                                                        break;
                                                    case 124:
                                                        this.f10875m.a((char) 9492);
                                                        break;
                                                    case 125:
                                                        this.f10875m.a((char) 9472);
                                                        break;
                                                    case 126:
                                                        this.f10875m.a((char) 9496);
                                                        break;
                                                    case 127:
                                                        this.f10875m.a((char) 9484);
                                                        break;
                                                    default:
                                                        AbstractC1347d.p("Invalid G2 character: ", i43, "Cea708Decoder");
                                                        break;
                                                }
                                                break;
                                        }
                                    } else {
                                        this.f10875m.a((char) 8480);
                                    }
                                    z10 = true;
                                } else if (i43 > 159) {
                                    i11 = 2;
                                    if (i43 <= 255) {
                                        if (i43 == 160) {
                                            this.f10875m.a((char) 13252);
                                        } else {
                                            AbstractC1347d.p("Invalid G3 character: ", i43, "Cea708Decoder");
                                            this.f10875m.a('_');
                                        }
                                        z10 = true;
                                    } else {
                                        AbstractC1347d.p("Invalid extended command: ", i43, "Cea708Decoder");
                                    }
                                } else if (i43 <= 135) {
                                    n12.u(32);
                                } else if (i43 <= 143) {
                                    n12.u(40);
                                } else if (i43 <= 159) {
                                    i11 = 2;
                                    n12.u(2);
                                    n12.u(n12.i(6) * 8);
                                }
                            }
                            i11 = 2;
                        }
                        i14 = i7;
                        iG = i10;
                        i12 = i11;
                    }
                }
            }
        }
        if (z10) {
            this.f10876n = l();
        }
        this.f10877p = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List l() {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Z2.f.l():java.util.List");
    }

    public final void m() {
        for (int i7 = 0; i7 < 8; i7++) {
            this.l[i7].d();
        }
    }
}
