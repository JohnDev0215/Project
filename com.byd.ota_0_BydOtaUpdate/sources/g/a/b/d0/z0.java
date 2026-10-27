package g.a.b.d0;

import g.a.b.j0.h1;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class z0 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int[] f4673a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int[] f4674b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int[] f4675c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int[] f4676d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4677e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4678f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long[] f4679g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long[] f4680h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long[] f4681i;
    public d j;
    public boolean k;

    public static final class a extends d {
        public a(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // g.a.b.d0.z0.d
        public void a(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f4683b;
            long[] jArr4 = this.f4682a;
            int[] iArr = z0.f4674b;
            int[] iArr2 = z0.f4676d;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            int i2 = 1;
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = jArr[8];
            long j10 = jArr[9];
            long j11 = jArr[10];
            long j12 = jArr[11];
            long j13 = jArr[12];
            long jN = jArr[13];
            long j14 = jArr[14];
            long jN2 = jArr[15];
            int i3 = 19;
            while (i3 >= i2) {
                int i4 = iArr[i3];
                int i5 = iArr2[i3];
                int i6 = i4 + 1;
                long j15 = j - jArr3[i6];
                int i7 = i4 + 2;
                long j16 = j2 - jArr3[i7];
                int i8 = i4 + 3;
                long j17 = j3 - jArr3[i8];
                int i9 = i4 + 4;
                long j18 = j4 - jArr3[i9];
                int i10 = i4 + 5;
                long j19 = j5 - jArr3[i10];
                int i11 = i4 + 6;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j20 = j6 - jArr3[i11];
                int i12 = i4 + 7;
                long j21 = j7 - jArr3[i12];
                int i13 = i4 + 8;
                long j22 = j8 - jArr3[i13];
                int i14 = i4 + 9;
                long j23 = j9 - jArr3[i14];
                int i15 = i4 + 10;
                long j24 = j10 - jArr3[i15];
                int i16 = i4 + 11;
                long j25 = j11 - jArr3[i16];
                int i17 = i4 + 12;
                long j26 = j12 - jArr3[i17];
                int i18 = i4 + 13;
                long j27 = j13 - jArr3[i18];
                int i19 = i4 + 14;
                int i20 = i5 + 1;
                long j28 = jN - (jArr3[i19] + jArr4[i20]);
                int i21 = i4 + 15;
                long j29 = j14 - (jArr3[i21] + jArr4[i5 + 2]);
                long[] jArr5 = jArr3;
                long j30 = i3;
                long jN3 = z0.n(jN2 - ((jArr3[i4 + 16] + j30) + 1), 9, j15);
                long j31 = j15 - jN3;
                long jN4 = z0.n(j26, 48, j17);
                long j32 = j17 - jN4;
                long jN5 = z0.n(j28, 35, j21);
                long j33 = j21 - jN5;
                long[] jArr6 = jArr4;
                long jN6 = z0.n(j24, 52, j19);
                long j34 = j19 - jN6;
                long jN7 = z0.n(j16, 23, j29);
                long j35 = j29 - jN7;
                long jN8 = z0.n(j20, 31, j23);
                long j36 = j23 - jN8;
                long jN9 = z0.n(j18, 37, j25);
                long j37 = j25 - jN9;
                long jN10 = z0.n(j22, 20, j27);
                long j38 = j27 - jN10;
                long jN11 = z0.n(jN10, 31, j31);
                long j39 = j31 - jN11;
                long jN12 = z0.n(jN8, 44, j32);
                long j40 = j32 - jN12;
                long jN13 = z0.n(jN9, 47, j34);
                long j41 = j34 - jN13;
                long jN14 = z0.n(jN7, 46, j33);
                long j42 = j33 - jN14;
                long jN15 = z0.n(jN3, 19, j38);
                long j43 = j38 - jN15;
                long jN16 = z0.n(jN5, 42, j35);
                long j44 = j35 - jN16;
                long jN17 = z0.n(jN4, 44, j36);
                long j45 = j36 - jN17;
                long jN18 = z0.n(jN6, 25, j37);
                long j46 = j37 - jN18;
                long jN19 = z0.n(jN18, 16, j39);
                long j47 = j39 - jN19;
                long jN20 = z0.n(jN16, 34, j40);
                long j48 = j40 - jN20;
                long jN21 = z0.n(jN17, 56, j42);
                long j49 = j42 - jN21;
                long jN22 = z0.n(jN15, 51, j41);
                long j50 = j41 - jN22;
                long jN23 = z0.n(jN11, 4, j46);
                long j51 = j46 - jN23;
                long jN24 = z0.n(jN13, 53, j43);
                long j52 = j43 - jN24;
                long jN25 = z0.n(jN12, 42, j44);
                long j53 = j44 - jN25;
                long jN26 = z0.n(jN14, 41, j45);
                long j54 = j45 - jN26;
                long jN27 = z0.n(jN26, 41, j47);
                long jN28 = z0.n(jN24, 9, j48);
                long jN29 = z0.n(jN25, 37, j50);
                long j55 = j50 - jN29;
                long jN30 = z0.n(jN23, 31, j49);
                long j56 = j49 - jN30;
                long jN31 = z0.n(jN19, 12, j54);
                long j57 = j54 - jN31;
                long jN32 = z0.n(jN21, 47, j51);
                long j58 = j51 - jN32;
                long jN33 = z0.n(jN20, 44, j52);
                long j59 = j52 - jN33;
                long jN34 = z0.n(jN22, 30, j53);
                long j60 = j53 - jN34;
                long j61 = (j47 - jN27) - jArr5[i4];
                long j62 = jN27 - jArr5[i6];
                long j63 = (j48 - jN28) - jArr5[i7];
                long j64 = jN28 - jArr5[i8];
                long j65 = j55 - jArr5[i9];
                long j66 = jN29 - jArr5[i10];
                long j67 = j56 - jArr5[i11];
                long j68 = jN30 - jArr5[i12];
                long j69 = j57 - jArr5[i13];
                long j70 = jN31 - jArr5[i14];
                long j71 = j58 - jArr5[i15];
                long j72 = jN32 - jArr5[i16];
                long j73 = j59 - jArr5[i17];
                long j74 = jN33 - (jArr5[i18] + jArr6[i5]);
                long j75 = j60 - (jArr5[i19] + jArr6[i20]);
                long jN35 = z0.n(jN34 - (jArr5[i21] + j30), 5, j61);
                long j76 = j61 - jN35;
                long jN36 = z0.n(j72, 20, j63);
                long j77 = j63 - jN36;
                long jN37 = z0.n(j74, 48, j67);
                long j78 = j67 - jN37;
                long jN38 = z0.n(j70, 41, j65);
                long j79 = j65 - jN38;
                long jN39 = z0.n(j62, 47, j75);
                long j80 = j75 - jN39;
                long jN40 = z0.n(j66, 28, j69);
                long j81 = j69 - jN40;
                long jN41 = z0.n(j64, 16, j71);
                long j82 = j71 - jN41;
                long jN42 = z0.n(j68, 25, j73);
                long j83 = j73 - jN42;
                long jN43 = z0.n(jN42, 33, j76);
                long j84 = j76 - jN43;
                long jN44 = z0.n(jN40, 4, j77);
                long j85 = j77 - jN44;
                long jN45 = z0.n(jN41, 51, j79);
                long j86 = j79 - jN45;
                long jN46 = z0.n(jN39, 13, j78);
                long j87 = j78 - jN46;
                long jN47 = z0.n(jN35, 34, j83);
                long j88 = j83 - jN47;
                long jN48 = z0.n(jN37, 41, j80);
                long j89 = j80 - jN48;
                long jN49 = z0.n(jN36, 59, j81);
                long j90 = j81 - jN49;
                long jN50 = z0.n(jN38, 17, j82);
                long j91 = j82 - jN50;
                long jN51 = z0.n(jN50, 38, j84);
                long j92 = j84 - jN51;
                long jN52 = z0.n(jN48, 19, j85);
                long j93 = j85 - jN52;
                long jN53 = z0.n(jN49, 10, j87);
                long j94 = j87 - jN53;
                long jN54 = z0.n(jN47, 55, j86);
                long j95 = j86 - jN54;
                long jN55 = z0.n(jN43, 49, j91);
                long j96 = j91 - jN55;
                long jN56 = z0.n(jN45, 18, j88);
                long j97 = j88 - jN56;
                long jN57 = z0.n(jN44, 23, j89);
                long j98 = j89 - jN57;
                long jN58 = z0.n(jN46, 52, j90);
                long j99 = j90 - jN58;
                long jN59 = z0.n(jN58, 24, j92);
                long j100 = j92 - jN59;
                long jN60 = z0.n(jN56, 13, j93);
                j3 = j93 - jN60;
                long jN61 = z0.n(jN57, 8, j95);
                long j101 = j95 - jN61;
                long jN62 = z0.n(jN55, 47, j94);
                long j102 = j94 - jN62;
                long jN63 = z0.n(jN51, 8, j99);
                long j103 = j99 - jN63;
                long jN64 = z0.n(jN53, 17, j96);
                long j104 = j96 - jN64;
                jN = z0.n(jN52, 22, j97);
                jN2 = z0.n(jN54, 37, j98);
                j14 = j98 - jN2;
                j12 = jN64;
                j13 = j97 - jN;
                iArr = iArr3;
                jArr4 = jArr6;
                jArr3 = jArr5;
                j9 = j103;
                j10 = jN63;
                i2 = 1;
                j5 = j101;
                j2 = jN59;
                i3 -= 2;
                j4 = jN60;
                iArr2 = iArr4;
                j8 = jN62;
                j11 = j104;
                j6 = jN61;
                j7 = j102;
                j = j100;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            long j105 = j - jArr7[0];
            long j106 = j2 - jArr7[1];
            long j107 = j3 - jArr7[2];
            long j108 = j4 - jArr7[3];
            long j109 = j5 - jArr7[4];
            long j110 = j6 - jArr7[5];
            long j111 = j7 - jArr7[6];
            long j112 = j8 - jArr7[7];
            long j113 = j9 - jArr7[8];
            long j114 = j10 - jArr7[9];
            long j115 = j11 - jArr7[10];
            long j116 = j12 - jArr7[11];
            long j117 = j13 - jArr7[12];
            long j118 = jN - (jArr7[13] + jArr8[0]);
            long j119 = j14 - (jArr7[14] + jArr8[1]);
            long j120 = jN2 - jArr7[15];
            jArr2[0] = j105;
            jArr2[1] = j106;
            jArr2[2] = j107;
            jArr2[3] = j108;
            jArr2[4] = j109;
            jArr2[5] = j110;
            jArr2[6] = j111;
            jArr2[7] = j112;
            jArr2[8] = j113;
            jArr2[9] = j114;
            jArr2[10] = j115;
            jArr2[11] = j116;
            jArr2[12] = j117;
            jArr2[13] = j118;
            jArr2[14] = j119;
            jArr2[15] = j120;
        }

        @Override // g.a.b.d0.z0.d
        public void b(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f4683b;
            long[] jArr4 = this.f4682a;
            int[] iArr = z0.f4674b;
            int[] iArr2 = z0.f4676d;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            int i2 = 1;
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = jArr[8];
            long j10 = jArr[9];
            long j11 = jArr[10];
            long j12 = jArr[11];
            long j13 = jArr[12];
            long j14 = jArr[13];
            long j15 = jArr[14];
            long j16 = jArr[15];
            long j17 = j + jArr3[0];
            long j18 = j2 + jArr3[1];
            long j19 = j3 + jArr3[2];
            long j20 = j4 + jArr3[3];
            long j21 = j5 + jArr3[4];
            long j22 = j6 + jArr3[5];
            long j23 = j7 + jArr3[6];
            long j24 = j8 + jArr3[7];
            long j25 = j9 + jArr3[8];
            long j26 = j10 + jArr3[9];
            long j27 = j11 + jArr3[10];
            long j28 = j12 + jArr3[11];
            long j29 = j13 + jArr3[12];
            long j30 = j14 + jArr3[13] + jArr4[0];
            long j31 = j15 + jArr3[14] + jArr4[1];
            long j32 = j20;
            long j33 = j22;
            long j34 = j24;
            long j35 = j26;
            long j36 = j28;
            long j37 = j30;
            long j38 = j16 + jArr3[15];
            while (i2 < 20) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                long j39 = j17 + j18;
                long j40 = z0.j(j18, 24, j39);
                long j41 = j19 + j32;
                long j42 = z0.j(j32, 13, j41);
                long[] jArr5 = jArr3;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j43 = j33;
                long j44 = j21 + j43;
                long j45 = z0.j(j43, 8, j44);
                int i5 = i2;
                long j46 = j34;
                long j47 = j23 + j46;
                long j48 = z0.j(j46, 47, j47);
                long[] jArr6 = jArr4;
                long j49 = j35;
                long j50 = j25 + j49;
                long j51 = z0.j(j49, 8, j50);
                long j52 = j36;
                long j53 = j27 + j52;
                long j54 = z0.j(j52, 17, j53);
                long j55 = j37;
                long j56 = j29 + j55;
                long j57 = z0.j(j55, 22, j56);
                long j58 = j38;
                long j59 = j31 + j58;
                long j60 = z0.j(j58, 37, j59);
                long j61 = j39 + j51;
                long j62 = z0.j(j51, 38, j61);
                long j63 = j41 + j57;
                long j64 = z0.j(j57, 19, j63);
                long j65 = j47 + j54;
                long j66 = z0.j(j54, 10, j65);
                long j67 = j44 + j60;
                long j68 = z0.j(j60, 55, j67);
                long j69 = j53 + j48;
                long j70 = z0.j(j48, 49, j69);
                long j71 = j56 + j42;
                long j72 = z0.j(j42, 18, j71);
                long j73 = j59 + j45;
                long j74 = z0.j(j45, 23, j73);
                long j75 = j50 + j40;
                long j76 = z0.j(j40, 52, j75);
                long j77 = j61 + j70;
                long j78 = z0.j(j70, 33, j77);
                long j79 = j63 + j74;
                long j80 = z0.j(j74, 4, j79);
                long j81 = j67 + j72;
                long j82 = z0.j(j72, 51, j81);
                long j83 = j65 + j76;
                long j84 = z0.j(j76, 13, j83);
                long j85 = j71 + j68;
                long j86 = z0.j(j68, 34, j85);
                long j87 = j73 + j64;
                long j88 = z0.j(j64, 41, j87);
                long j89 = j75 + j66;
                long j90 = z0.j(j66, 59, j89);
                long j91 = j69 + j62;
                long j92 = z0.j(j62, 17, j91);
                long j93 = j77 + j86;
                long j94 = z0.j(j86, 5, j93);
                long j95 = j79 + j90;
                long j96 = z0.j(j90, 20, j95);
                long j97 = j83 + j88;
                long j98 = z0.j(j88, 48, j97);
                long j99 = j81 + j92;
                long j100 = z0.j(j92, 41, j99);
                long j101 = j87 + j84;
                long j102 = z0.j(j84, 47, j101);
                long j103 = j89 + j80;
                long j104 = z0.j(j80, 28, j103);
                long j105 = j91 + j82;
                long j106 = z0.j(j82, 16, j105);
                long j107 = j85 + j78;
                long j108 = z0.j(j78, 25, j107);
                long j109 = j93 + jArr5[i3];
                int i6 = i3 + 1;
                long j110 = j102 + jArr5[i6];
                int i7 = i3 + 2;
                long j111 = j95 + jArr5[i7];
                int i8 = i3 + 3;
                long j112 = j106 + jArr5[i8];
                int i9 = i3 + 4;
                long j113 = j99 + jArr5[i9];
                int i10 = i3 + 5;
                long j114 = j104 + jArr5[i10];
                int i11 = i3 + 6;
                long j115 = j97 + jArr5[i11];
                int i12 = i3 + 7;
                long j116 = j108 + jArr5[i12];
                int i13 = i3 + 8;
                long j117 = j103 + jArr5[i13];
                int i14 = i3 + 9;
                long j118 = j100 + jArr5[i14];
                int i15 = i3 + 10;
                long j119 = j105 + jArr5[i15];
                int i16 = i3 + 11;
                long j120 = j96 + jArr5[i16];
                int i17 = i3 + 12;
                long j121 = j107 + jArr5[i17];
                int i18 = i3 + 13;
                long j122 = j98 + jArr5[i18] + jArr6[i4];
                int i19 = i3 + 14;
                int i20 = i4 + 1;
                long j123 = j101 + jArr5[i19] + jArr6[i20];
                int i21 = i3 + 15;
                long j124 = jArr5[i21];
                long j125 = i5;
                long j126 = j94 + j124 + j125;
                long j127 = j109 + j110;
                long j128 = z0.j(j110, 41, j127);
                long j129 = j111 + j112;
                long j130 = z0.j(j112, 9, j129);
                long j131 = j113 + j114;
                long j132 = z0.j(j114, 37, j131);
                long j133 = j115 + j116;
                long j134 = z0.j(j116, 31, j133);
                long j135 = j117 + j118;
                long j136 = z0.j(j118, 12, j135);
                long j137 = j119 + j120;
                long j138 = z0.j(j120, 47, j137);
                long j139 = j121 + j122;
                long j140 = z0.j(j122, 44, j139);
                long j141 = j123 + j126;
                long j142 = z0.j(j126, 30, j141);
                long j143 = j127 + j136;
                long j144 = z0.j(j136, 16, j143);
                long j145 = j129 + j140;
                long j146 = z0.j(j140, 34, j145);
                long j147 = j133 + j138;
                long j148 = z0.j(j138, 56, j147);
                long j149 = j131 + j142;
                long j150 = z0.j(j142, 51, j149);
                long j151 = j137 + j134;
                long j152 = z0.j(j134, 4, j151);
                long j153 = j139 + j130;
                long j154 = z0.j(j130, 53, j153);
                long j155 = j141 + j132;
                long j156 = z0.j(j132, 42, j155);
                long j157 = j135 + j128;
                long j158 = z0.j(j128, 41, j157);
                long j159 = j143 + j152;
                long j160 = z0.j(j152, 31, j159);
                long j161 = j145 + j156;
                long j162 = z0.j(j156, 44, j161);
                long j163 = j149 + j154;
                long j164 = z0.j(j154, 47, j163);
                long j165 = j147 + j158;
                long j166 = z0.j(j158, 46, j165);
                long j167 = j153 + j150;
                long j168 = z0.j(j150, 19, j167);
                long j169 = j155 + j146;
                long j170 = z0.j(j146, 42, j169);
                long j171 = j157 + j148;
                long j172 = z0.j(j148, 44, j171);
                long j173 = j151 + j144;
                long j174 = z0.j(j144, 25, j173);
                long j175 = j159 + j168;
                long j176 = z0.j(j168, 9, j175);
                long j177 = j161 + j172;
                long j178 = z0.j(j172, 48, j177);
                long j179 = j165 + j170;
                long j180 = z0.j(j170, 35, j179);
                long j181 = j163 + j174;
                long j182 = z0.j(j174, 52, j181);
                long j183 = j169 + j166;
                long j184 = z0.j(j166, 23, j183);
                long j185 = j171 + j162;
                long j186 = z0.j(j162, 31, j185);
                long j187 = j173 + j164;
                long j188 = z0.j(j164, 37, j187);
                long j189 = j167 + j160;
                long j190 = z0.j(j160, 20, j189);
                long j191 = j175 + jArr5[i6];
                long j192 = j184 + jArr5[i7];
                long j193 = j177 + jArr5[i8];
                long j194 = j188 + jArr5[i9];
                long j195 = j181 + jArr5[i10];
                long j196 = j186 + jArr5[i11];
                long j197 = j179 + jArr5[i12];
                long j198 = j190 + jArr5[i13];
                long j199 = j185 + jArr5[i14];
                j35 = j182 + jArr5[i15];
                j27 = j187 + jArr5[i16];
                j36 = j178 + jArr5[i17];
                long j200 = j189 + jArr5[i18];
                j37 = j180 + jArr5[i19] + jArr6[i20];
                long j201 = j183 + jArr5[i21] + jArr6[i4 + 2];
                j38 = j176 + jArr5[i3 + 16] + j125 + 1;
                j34 = j198;
                j32 = j194;
                j33 = j196;
                j31 = j201;
                iArr2 = iArr4;
                j25 = j199;
                j23 = j197;
                j29 = j200;
                j18 = j192;
                iArr = iArr3;
                jArr4 = jArr6;
                jArr3 = jArr5;
                i2 = i5 + 2;
                j21 = j195;
                j17 = j191;
                j19 = j193;
            }
            jArr2[0] = j17;
            jArr2[1] = j18;
            jArr2[2] = j19;
            jArr2[3] = j32;
            jArr2[4] = j21;
            jArr2[5] = j33;
            jArr2[6] = j23;
            jArr2[7] = j34;
            jArr2[8] = j25;
            jArr2[9] = j35;
            jArr2[10] = j27;
            jArr2[11] = j36;
            jArr2[12] = j29;
            jArr2[13] = j37;
            jArr2[14] = j31;
            jArr2[15] = j38;
        }
    }

    public static final class b extends d {
        public b(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // g.a.b.d0.z0.d
        public void a(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f4683b;
            long[] jArr4 = this.f4682a;
            int[] iArr = z0.f4675c;
            int[] iArr2 = z0.f4676d;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            boolean z = false;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long jN = jArr[3];
            int i2 = 17;
            for (int i3 = 1; i2 >= i3; i3 = 1) {
                int i4 = iArr[i2];
                int i5 = iArr2[i2];
                int i6 = i4 + 1;
                long j4 = j - jArr3[i6];
                int i7 = i4 + 2;
                int i8 = i5 + 1;
                long j5 = j2 - (jArr3[i7] + jArr4[i8]);
                int i9 = i4 + 3;
                long j6 = j3 - (jArr3[i9] + jArr4[i5 + 2]);
                long j7 = i2;
                long jN2 = z0.n(jN - ((jArr3[i4 + 4] + j7) + 1), 32, j4);
                long j8 = j4 - jN2;
                int[] iArr3 = iArr;
                long jN3 = z0.n(j5, 32, j6);
                long j9 = j6 - jN3;
                long jN4 = z0.n(jN3, 58, j8);
                long j10 = j8 - jN4;
                long jN5 = z0.n(jN2, 22, j9);
                long j11 = j9 - jN5;
                long jN6 = z0.n(jN5, 46, j10);
                long j12 = j10 - jN6;
                long jN7 = z0.n(jN4, 12, j11);
                long j13 = j11 - jN7;
                long jN8 = z0.n(jN7, 25, j12);
                long jN9 = z0.n(jN6, 33, j13);
                long j14 = (j12 - jN8) - jArr3[i4];
                long j15 = jN8 - (jArr3[i6] + jArr4[i5]);
                long j16 = (j13 - jN9) - (jArr3[i7] + jArr4[i8]);
                long jN10 = z0.n(jN9 - (jArr3[i9] + j7), 5, j14);
                long j17 = j14 - jN10;
                long jN11 = z0.n(j15, 37, j16);
                long j18 = j16 - jN11;
                long jN12 = z0.n(jN11, 23, j17);
                long j19 = j17 - jN12;
                long jN13 = z0.n(jN10, 40, j18);
                long j20 = j18 - jN13;
                long jN14 = z0.n(jN13, 52, j19);
                long j21 = j19 - jN14;
                long jN15 = z0.n(jN12, 57, j20);
                long j22 = j20 - jN15;
                long jN16 = z0.n(jN15, 14, j21);
                j = j21 - jN16;
                jN = z0.n(jN14, 16, j22);
                j3 = j22 - jN;
                i2 -= 2;
                j2 = jN16;
                iArr = iArr3;
                iArr2 = iArr2;
                z = false;
            }
            boolean z2 = z;
            long j23 = j - jArr3[z2 ? 1 : 0];
            long j24 = j2 - (jArr3[1] + jArr4[z2 ? 1 : 0]);
            long j25 = j3 - (jArr3[2] + jArr4[1]);
            long j26 = jN - jArr3[3];
            jArr2[z2 ? 1 : 0] = j23;
            jArr2[1] = j24;
            jArr2[2] = j25;
            jArr2[3] = j26;
        }

        @Override // g.a.b.d0.z0.d
        public void b(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f4683b;
            long[] jArr4 = this.f4682a;
            int[] iArr = z0.f4675c;
            int[] iArr2 = z0.f4676d;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = j + jArr3[0];
            long j6 = j2 + jArr3[1] + jArr4[0];
            long j7 = j3 + jArr3[2] + jArr4[1];
            int i2 = 1;
            long j8 = j4 + jArr3[3];
            while (i2 < 18) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                long j9 = j5 + j6;
                long j10 = z0.j(j6, 14, j9);
                long j11 = j7 + j8;
                long j12 = z0.j(j8, 16, j11);
                long j13 = j9 + j12;
                long j14 = z0.j(j12, 52, j13);
                long j15 = j11 + j10;
                long j16 = z0.j(j10, 57, j15);
                long j17 = j13 + j16;
                long j18 = z0.j(j16, 23, j17);
                long j19 = j15 + j14;
                long j20 = z0.j(j14, 40, j19);
                long j21 = j17 + j20;
                long j22 = z0.j(j20, 5, j21);
                long j23 = j19 + j18;
                long j24 = z0.j(j18, 37, j23);
                long j25 = j21 + jArr3[i3];
                int i5 = i3 + 1;
                long j26 = j24 + jArr3[i5] + jArr4[i4];
                int i6 = i3 + 2;
                int i7 = i4 + 1;
                long j27 = j23 + jArr3[i6] + jArr4[i7];
                int i8 = i3 + 3;
                int[] iArr3 = iArr;
                long j28 = i2;
                long j29 = j22 + jArr3[i8] + j28;
                long j30 = j25 + j26;
                long j31 = z0.j(j26, 25, j30);
                long j32 = j27 + j29;
                long j33 = z0.j(j29, 33, j32);
                long j34 = j30 + j33;
                long j35 = z0.j(j33, 46, j34);
                long j36 = j32 + j31;
                long j37 = z0.j(j31, 12, j36);
                long j38 = j34 + j37;
                long j39 = z0.j(j37, 58, j38);
                long j40 = j36 + j35;
                long j41 = z0.j(j35, 22, j40);
                long j42 = j38 + j41;
                long j43 = z0.j(j41, 32, j42);
                long j44 = j40 + j39;
                long j45 = z0.j(j39, 32, j44);
                j5 = j42 + jArr3[i5];
                j6 = j45 + jArr3[i6] + jArr4[i7];
                j7 = j44 + jArr3[i8] + jArr4[i4 + 2];
                j8 = j43 + jArr3[i3 + 4] + j28 + 1;
                i2 += 2;
                iArr = iArr3;
                iArr2 = iArr2;
            }
            jArr2[0] = j5;
            jArr2[1] = j6;
            jArr2[2] = j7;
            jArr2[3] = j8;
        }
    }

    public static final class c extends d {
        public c(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // g.a.b.d0.z0.d
        public void a(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f4683b;
            long[] jArr4 = this.f4682a;
            int[] iArr = z0.f4673a;
            int[] iArr2 = z0.f4676d;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            boolean z = false;
            long j = jArr[0];
            int i2 = 1;
            long j2 = jArr[1];
            long j3 = jArr[2];
            long jN = jArr[3];
            long j4 = jArr[4];
            long j5 = jArr[5];
            long j6 = jArr[6];
            long jN2 = jArr[7];
            int i3 = 17;
            while (i3 >= i2) {
                int i4 = iArr[i3];
                int i5 = iArr2[i3];
                int i6 = i4 + 1;
                long j7 = j - jArr3[i6];
                int i7 = i4 + 2;
                long j8 = j2 - jArr3[i7];
                int i8 = i4 + 3;
                long j9 = j3 - jArr3[i8];
                int i9 = i4 + 4;
                long j10 = jN - jArr3[i9];
                int i10 = i4 + 5;
                long j11 = j4 - jArr3[i10];
                int i11 = i4 + 6;
                int i12 = i5 + 1;
                long j12 = j5 - (jArr3[i11] + jArr4[i12]);
                int i13 = i4 + 7;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j13 = j6 - (jArr3[i13] + jArr4[i5 + 2]);
                long[] jArr5 = jArr3;
                long j14 = i3;
                long j15 = jN2 - ((jArr3[i4 + 8] + j14) + 1);
                int i14 = i3;
                long jN3 = z0.n(j8, 8, j13);
                long j16 = j13 - jN3;
                long jN4 = z0.n(j15, 35, j7);
                long j17 = j7 - jN4;
                long jN5 = z0.n(j12, 56, j9);
                long j18 = j9 - jN5;
                long jN6 = z0.n(j10, 22, j11);
                long j19 = j11 - jN6;
                long jN7 = z0.n(jN3, 25, j19);
                long j20 = j19 - jN7;
                long jN8 = z0.n(jN6, 29, j16);
                long j21 = j16 - jN8;
                long jN9 = z0.n(jN5, 39, j17);
                long j22 = j17 - jN9;
                long jN10 = z0.n(jN4, 43, j18);
                long j23 = j18 - jN10;
                long jN11 = z0.n(jN7, 13, j23);
                long j24 = j23 - jN11;
                long jN12 = z0.n(jN10, 50, j20);
                long j25 = j20 - jN12;
                long jN13 = z0.n(jN9, 10, j21);
                long j26 = j21 - jN13;
                long jN14 = z0.n(jN8, 17, j22);
                long j27 = j22 - jN14;
                long jN15 = z0.n(jN11, 39, j27);
                long jN16 = z0.n(jN14, 30, j24);
                long jN17 = z0.n(jN13, 34, j25);
                long j28 = j25 - jN17;
                long jN18 = z0.n(jN12, 24, j26);
                long j29 = (j27 - jN15) - jArr5[i4];
                long j30 = jN15 - jArr5[i6];
                long j31 = (j24 - jN16) - jArr5[i7];
                long j32 = jN16 - jArr5[i8];
                long j33 = j28 - jArr5[i9];
                long j34 = jN17 - (jArr5[i10] + jArr4[i5]);
                long j35 = (j26 - jN18) - (jArr5[i11] + jArr4[i12]);
                long j36 = jN18 - (jArr5[i13] + j14);
                long jN19 = z0.n(j30, 44, j35);
                long j37 = j35 - jN19;
                long jN20 = z0.n(j36, 9, j29);
                long j38 = j29 - jN20;
                long jN21 = z0.n(j34, 54, j31);
                long j39 = j31 - jN21;
                long jN22 = z0.n(j32, 56, j33);
                long j40 = j33 - jN22;
                long jN23 = z0.n(jN19, 17, j40);
                long j41 = j40 - jN23;
                long jN24 = z0.n(jN22, 49, j37);
                long j42 = j37 - jN24;
                long jN25 = z0.n(jN21, 36, j38);
                long j43 = j38 - jN25;
                long jN26 = z0.n(jN20, 39, j39);
                long j44 = j39 - jN26;
                long jN27 = z0.n(jN23, 33, j44);
                long j45 = j44 - jN27;
                long jN28 = z0.n(jN26, 27, j41);
                long j46 = j41 - jN28;
                long jN29 = z0.n(jN25, 14, j42);
                long j47 = j42 - jN29;
                long[] jArr6 = jArr4;
                long jN30 = z0.n(jN24, 42, j43);
                long j48 = j43 - jN30;
                long jN31 = z0.n(jN27, 46, j48);
                long j49 = j48 - jN31;
                jN = z0.n(jN30, 36, j45);
                long jN32 = z0.n(jN29, 19, j46);
                j4 = j46 - jN32;
                jN2 = z0.n(jN28, 37, j47);
                j6 = j47 - jN2;
                j3 = j45 - jN;
                j2 = jN31;
                j5 = jN32;
                i3 = i14 - 2;
                iArr2 = iArr4;
                jArr3 = jArr5;
                z = false;
                i2 = 1;
                j = j49;
                jArr4 = jArr6;
                iArr = iArr3;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            boolean z2 = z;
            long j50 = j - jArr7[z2 ? 1 : 0];
            long j51 = j2 - jArr7[1];
            long j52 = j3 - jArr7[2];
            long j53 = jN - jArr7[3];
            long j54 = j4 - jArr7[4];
            long j55 = j5 - (jArr7[5] + jArr8[z2 ? 1 : 0]);
            long j56 = j6 - (jArr7[6] + jArr8[1]);
            long j57 = jN2 - jArr7[7];
            jArr2[z2 ? 1 : 0] = j50;
            jArr2[1] = j51;
            jArr2[2] = j52;
            jArr2[3] = j53;
            jArr2[4] = j54;
            jArr2[5] = j55;
            jArr2[6] = j56;
            jArr2[7] = j57;
        }

        @Override // g.a.b.d0.z0.d
        public void b(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f4683b;
            long[] jArr4 = this.f4682a;
            int[] iArr = z0.f4673a;
            int[] iArr2 = z0.f4676d;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = j + jArr3[0];
            long j10 = j2 + jArr3[1];
            long j11 = j3 + jArr3[2];
            long j12 = j4 + jArr3[3];
            long j13 = j5 + jArr3[4];
            long j14 = j6 + jArr3[5] + jArr4[0];
            long j15 = j7 + jArr3[6] + jArr4[1];
            int i2 = 1;
            long j16 = j12;
            long j17 = j14;
            long j18 = j8 + jArr3[7];
            while (i2 < 18) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                long j19 = j9 + j10;
                long j20 = z0.j(j10, 46, j19);
                long j21 = j11 + j16;
                long j22 = z0.j(j16, 36, j21);
                long[] jArr5 = jArr3;
                int[] iArr3 = iArr;
                long j23 = j17;
                long j24 = j13 + j23;
                long j25 = z0.j(j23, 19, j24);
                int i5 = i2;
                long j26 = j18;
                long j27 = j15 + j26;
                long j28 = z0.j(j26, 37, j27);
                long j29 = j21 + j20;
                long j30 = z0.j(j20, 33, j29);
                long j31 = j24 + j28;
                long j32 = z0.j(j28, 27, j31);
                long j33 = j27 + j25;
                long j34 = z0.j(j25, 14, j33);
                long j35 = j19 + j22;
                long j36 = z0.j(j22, 42, j35);
                long j37 = j31 + j30;
                long j38 = z0.j(j30, 17, j37);
                long j39 = j33 + j36;
                long j40 = z0.j(j36, 49, j39);
                long j41 = j35 + j34;
                long j42 = z0.j(j34, 36, j41);
                long j43 = j29 + j32;
                long j44 = z0.j(j32, 39, j43);
                long j45 = j39 + j38;
                long j46 = z0.j(j38, 44, j45);
                long j47 = j41 + j44;
                long j48 = z0.j(j44, 9, j47);
                long j49 = j43 + j42;
                long j50 = z0.j(j42, 54, j49);
                long j51 = j37 + j40;
                long j52 = z0.j(j40, 56, j51);
                long j53 = j47 + jArr5[i3];
                int i6 = i3 + 1;
                long j54 = j46 + jArr5[i6];
                int i7 = i3 + 2;
                long j55 = j49 + jArr5[i7];
                int i8 = i3 + 3;
                long j56 = j52 + jArr5[i8];
                int i9 = i3 + 4;
                long j57 = j51 + jArr5[i9];
                int i10 = i3 + 5;
                long j58 = j50 + jArr5[i10] + jArr4[i4];
                int i11 = i3 + 6;
                int i12 = i4 + 1;
                long j59 = j45 + jArr5[i11] + jArr4[i12];
                int i13 = i3 + 7;
                long j60 = i5;
                long j61 = j48 + jArr5[i13] + j60;
                long j62 = j53 + j54;
                long j63 = z0.j(j54, 39, j62);
                long j64 = j55 + j56;
                long j65 = z0.j(j56, 30, j64);
                long j66 = j57 + j58;
                long j67 = z0.j(j58, 34, j66);
                long j68 = j59 + j61;
                long j69 = z0.j(j61, 24, j68);
                long j70 = j64 + j63;
                long j71 = z0.j(j63, 13, j70);
                long j72 = j66 + j69;
                long j73 = z0.j(j69, 50, j72);
                long j74 = j68 + j67;
                long j75 = z0.j(j67, 10, j74);
                long j76 = j62 + j65;
                long j77 = z0.j(j65, 17, j76);
                long j78 = j72 + j71;
                long j79 = z0.j(j71, 25, j78);
                long j80 = j74 + j77;
                long j81 = z0.j(j77, 29, j80);
                long j82 = j76 + j75;
                long j83 = z0.j(j75, 39, j82);
                long j84 = j70 + j73;
                long j85 = z0.j(j73, 43, j84);
                long j86 = j80 + j79;
                long j87 = z0.j(j79, 8, j86);
                long j88 = j82 + j85;
                long j89 = z0.j(j85, 35, j88);
                long j90 = j84 + j83;
                long j91 = z0.j(j83, 56, j90);
                long j92 = j78 + j81;
                long j93 = z0.j(j81, 22, j92);
                long j94 = j88 + jArr5[i6];
                j10 = j87 + jArr5[i7];
                long j95 = j90 + jArr5[i8];
                long j96 = j93 + jArr5[i9];
                long j97 = j92 + jArr5[i10];
                long j98 = j91 + jArr5[i11] + jArr4[i12];
                j15 = j86 + jArr5[i13] + jArr4[i4 + 2];
                j18 = j89 + jArr5[i3 + 8] + j60 + 1;
                j13 = j97;
                j17 = j98;
                iArr = iArr3;
                iArr2 = iArr2;
                jArr3 = jArr5;
                i2 = i5 + 2;
                j16 = j96;
                j11 = j95;
                j9 = j94;
            }
            jArr2[0] = j9;
            jArr2[1] = j10;
            jArr2[2] = j11;
            jArr2[3] = j16;
            jArr2[4] = j13;
            jArr2[5] = j17;
            jArr2[6] = j15;
            jArr2[7] = j18;
        }
    }

    public static abstract class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long[] f4682a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long[] f4683b;

        public d(long[] jArr, long[] jArr2) {
            this.f4683b = jArr;
            this.f4682a = jArr2;
        }

        public abstract void a(long[] jArr, long[] jArr2);

        public abstract void b(long[] jArr, long[] jArr2);
    }

    static {
        int[] iArr = new int[80];
        f4673a = iArr;
        f4674b = new int[iArr.length];
        f4675c = new int[iArr.length];
        f4676d = new int[iArr.length];
        int i2 = 0;
        while (true) {
            int[] iArr2 = f4673a;
            if (i2 >= iArr2.length) {
                return;
            }
            f4674b[i2] = i2 % 17;
            iArr2[i2] = i2 % 9;
            f4675c[i2] = i2 % 5;
            f4676d[i2] = i2 % 3;
            i2++;
        }
    }

    public z0(int i2) {
        d bVar;
        long[] jArr = new long[5];
        this.f4680h = jArr;
        int i3 = i2 / 8;
        this.f4677e = i3;
        int i4 = i3 / 8;
        this.f4678f = i4;
        this.f4679g = new long[i4];
        long[] jArr2 = new long[(i4 * 2) + 1];
        this.f4681i = jArr2;
        if (i2 == 256) {
            bVar = new b(jArr2, jArr);
        } else if (i2 == 512) {
            bVar = new c(jArr2, jArr);
        } else {
            if (i2 != 1024) {
                throw new IllegalArgumentException("Invalid blocksize - Threefish is defined with block size of 256, 512, or 1024 bits");
            }
            bVar = new a(jArr2, jArr);
        }
        this.j = bVar;
    }

    public static long g(byte[] bArr, int i2) {
        if (i2 + 8 > bArr.length) {
            throw new IllegalArgumentException();
        }
        int i3 = i2 + 1;
        long j = ((long) bArr[i2]) & 255;
        int i4 = i3 + 1;
        int i5 = i4 + 1;
        long j2 = j | ((((long) bArr[i3]) & 255) << 8) | ((((long) bArr[i4]) & 255) << 16);
        int i6 = i5 + 1;
        long j3 = j2 | ((((long) bArr[i5]) & 255) << 24);
        int i7 = i6 + 1;
        long j4 = j3 | ((((long) bArr[i6]) & 255) << 32);
        int i8 = i7 + 1;
        long j5 = j4 | ((((long) bArr[i7]) & 255) << 40);
        int i9 = i8 + 1;
        return ((((long) bArr[i9]) & 255) << 56) | j5 | ((((long) bArr[i8]) & 255) << 48);
    }

    public static long j(long j, int i2, long j2) {
        return ((j >>> (-i2)) | (j << i2)) ^ j2;
    }

    public static void m(long j, byte[] bArr, int i2) {
        if (i2 + 8 > bArr.length) {
            throw new IllegalArgumentException();
        }
        int i3 = i2 + 1;
        bArr[i2] = (byte) j;
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (j >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (j >> 24);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (j >> 32);
        int i8 = i7 + 1;
        bArr[i7] = (byte) (j >> 40);
        bArr[i8] = (byte) (j >> 48);
        bArr[i8 + 1] = (byte) (j >> 56);
    }

    public static long n(long j, int i2, long j2) {
        long j3 = j ^ j2;
        return (j3 << (-i2)) | (j3 >>> i2);
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        int i4 = this.f4677e;
        if (i2 + i4 > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.f4677e; i6 += 8) {
            this.f4679g[i6 >> 3] = g(bArr, i2 + i6);
        }
        long[] jArr = this.f4679g;
        i(jArr, jArr);
        while (true) {
            int i7 = this.f4677e;
            if (i5 >= i7) {
                return i7;
            }
            m(this.f4679g[i5 >> 3], bArr2, i3 + i5);
            i5 += 8;
        }
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4677e;
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "Threefish-" + (this.f4677e * 8);
    }

    public void h(boolean z, long[] jArr, long[] jArr2) {
        this.k = z;
        if (jArr != null) {
            k(jArr);
        }
        if (jArr2 != null) {
            l(jArr2);
        }
    }

    public int i(long[] jArr, long[] jArr2) throws IllegalStateException, DataLengthException {
        long[] jArr3 = this.f4681i;
        int i2 = this.f4678f;
        if (jArr3[i2] == 0) {
            throw new IllegalStateException("Threefish engine not initialised");
        }
        if (jArr.length != i2) {
            throw new DataLengthException("Input buffer too short");
        }
        if (jArr2.length != i2) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.k) {
            this.j.b(jArr, jArr2);
        } else {
            this.j.a(jArr, jArr2);
        }
        return this.f4678f;
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        byte[] bArrA;
        byte[] bArrB;
        long[] jArr;
        long[] jArr2 = null;
        if (iVar instanceof h1) {
            h1 h1Var = (h1) iVar;
            bArrA = h1Var.a().a();
            bArrB = h1Var.b();
        } else {
            if (!(iVar instanceof g.a.b.j0.s0)) {
                throw new IllegalArgumentException("Invalid parameter passed to Threefish init - " + iVar.getClass().getName());
            }
            bArrA = ((g.a.b.j0.s0) iVar).a();
            bArrB = null;
        }
        if (bArrA == null) {
            jArr = null;
        } else {
            if (bArrA.length != this.f4677e) {
                throw new IllegalArgumentException("Threefish key must be same size as block (" + this.f4677e + " bytes)");
            }
            int i2 = this.f4678f;
            jArr = new long[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                jArr[i3] = g(bArrA, i3 * 8);
            }
        }
        if (bArrB != null) {
            if (bArrB.length != 16) {
                throw new IllegalArgumentException("Threefish tweak must be 16 bytes");
            }
            jArr2 = new long[]{g(bArrB, 0), g(bArrB, 8)};
        }
        h(z, jArr, jArr2);
    }

    public final void k(long[] jArr) {
        if (jArr.length != this.f4678f) {
            throw new IllegalArgumentException("Threefish key must be same size as block (" + this.f4678f + " words)");
        }
        long j = 2004413935125273122L;
        int i2 = 0;
        while (true) {
            int i3 = this.f4678f;
            if (i2 >= i3) {
                long[] jArr2 = this.f4681i;
                jArr2[i3] = j;
                System.arraycopy(jArr2, 0, jArr2, i3 + 1, i3);
                return;
            } else {
                long[] jArr3 = this.f4681i;
                jArr3[i2] = jArr[i2];
                j ^= jArr3[i2];
                i2++;
            }
        }
    }

    public final void l(long[] jArr) {
        if (jArr.length != 2) {
            throw new IllegalArgumentException("Tweak must be 2 words.");
        }
        long[] jArr2 = this.f4680h;
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr2[0] ^ jArr2[1];
        jArr2[3] = jArr2[0];
        jArr2[4] = jArr2[1];
    }

    @Override // g.a.b.e
    public void reset() {
    }
}
