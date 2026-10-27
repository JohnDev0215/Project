package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class r extends h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4348d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4349e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4350f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4351g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4352h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4353i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int[] n;
    public int o;

    public r() {
        this.n = new int[16];
        reset();
    }

    public r(r rVar) {
        super(rVar);
        this.n = new int[16];
        j(rVar);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        j((r) eVar);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new r(this);
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        e();
        p(this.f4348d, bArr, i2);
        p(this.f4349e, bArr, i2 + 4);
        p(this.f4350f, bArr, i2 + 8);
        p(this.f4351g, bArr, i2 + 12);
        p(this.f4352h, bArr, i2 + 16);
        p(this.f4353i, bArr, i2 + 20);
        p(this.j, bArr, i2 + 24);
        p(this.k, bArr, i2 + 28);
        p(this.l, bArr, i2 + 32);
        p(this.m, bArr, i2 + 36);
        reset();
        return 40;
    }

    @Override // g.a.b.a0.h
    public void f() {
        int i2 = this.f4348d;
        int i3 = this.f4349e;
        int i4 = this.f4350f;
        int i5 = this.f4351g;
        int i6 = this.f4352h;
        int i7 = this.f4353i;
        int i8 = this.j;
        int i9 = this.k;
        int i10 = this.l;
        int i11 = this.m;
        int i12 = i(i2 + k(i3, i4, i5) + this.n[0], 11) + i6;
        int i13 = i(i4, 10);
        int i14 = i(i6 + k(i12, i3, i13) + this.n[1], 14) + i5;
        int i15 = i(i3, 10);
        int i16 = i(i5 + k(i14, i12, i15) + this.n[2], 15) + i13;
        int i17 = i(i12, 10);
        int i18 = i(i13 + k(i16, i14, i17) + this.n[3], 12) + i15;
        int i19 = i(i14, 10);
        int i20 = i(i15 + k(i18, i16, i19) + this.n[4], 5) + i17;
        int i21 = i(i16, 10);
        int i22 = i(i17 + k(i20, i18, i21) + this.n[5], 8) + i19;
        int i23 = i(i18, 10);
        int i24 = i(i19 + k(i22, i20, i23) + this.n[6], 7) + i21;
        int i25 = i(i20, 10);
        int i26 = i(i21 + k(i24, i22, i25) + this.n[7], 9) + i23;
        int i27 = i(i22, 10);
        int i28 = i(i23 + k(i26, i24, i27) + this.n[8], 11) + i25;
        int i29 = i(i24, 10);
        int i30 = i(i25 + k(i28, i26, i29) + this.n[9], 13) + i27;
        int i31 = i(i26, 10);
        int i32 = i(i27 + k(i30, i28, i31) + this.n[10], 14) + i29;
        int i33 = i(i28, 10);
        int i34 = i(i29 + k(i32, i30, i33) + this.n[11], 15) + i31;
        int i35 = i(i30, 10);
        int i36 = i(i31 + k(i34, i32, i35) + this.n[12], 6) + i33;
        int i37 = i(i32, 10);
        int i38 = i(i33 + k(i36, i34, i37) + this.n[13], 7) + i35;
        int i39 = i(i34, 10);
        int i40 = i(i35 + k(i38, i36, i39) + this.n[14], 9) + i37;
        int i41 = i(i36, 10);
        int i42 = i(i37 + k(i40, i38, i41) + this.n[15], 8) + i39;
        int i43 = i(i38, 10);
        int i44 = i(i7 + o(i8, i9, i10) + this.n[5] + 1352829926, 8) + i11;
        int i45 = i(i9, 10);
        int i46 = i(i11 + o(i44, i8, i45) + this.n[14] + 1352829926, 9) + i10;
        int i47 = i(i8, 10);
        int i48 = i(i10 + o(i46, i44, i47) + this.n[7] + 1352829926, 9) + i45;
        int i49 = i(i44, 10);
        int i50 = i(i45 + o(i48, i46, i49) + this.n[0] + 1352829926, 11) + i47;
        int i51 = i(i46, 10);
        int i52 = i(i47 + o(i50, i48, i51) + this.n[9] + 1352829926, 13) + i49;
        int i53 = i(i48, 10);
        int i54 = i(i49 + o(i52, i50, i53) + this.n[2] + 1352829926, 15) + i51;
        int i55 = i(i50, 10);
        int i56 = i(i51 + o(i54, i52, i55) + this.n[11] + 1352829926, 15) + i53;
        int i57 = i(i52, 10);
        int i58 = i(i53 + o(i56, i54, i57) + this.n[4] + 1352829926, 5) + i55;
        int i59 = i(i54, 10);
        int i60 = i(i55 + o(i58, i56, i59) + this.n[13] + 1352829926, 7) + i57;
        int i61 = i(i56, 10);
        int i62 = i(i57 + o(i60, i58, i61) + this.n[6] + 1352829926, 7) + i59;
        int i63 = i(i58, 10);
        int i64 = i(i59 + o(i62, i60, i63) + this.n[15] + 1352829926, 8) + i61;
        int i65 = i(i60, 10);
        int i66 = i(i61 + o(i64, i62, i65) + this.n[8] + 1352829926, 11) + i63;
        int i67 = i(i62, 10);
        int i68 = i(i63 + o(i66, i64, i67) + this.n[1] + 1352829926, 14) + i65;
        int i69 = i(i64, 10);
        int i70 = i(i65 + o(i68, i66, i69) + this.n[10] + 1352829926, 14) + i67;
        int i71 = i(i66, 10);
        int i72 = i(i67 + o(i70, i68, i71) + this.n[3] + 1352829926, 12) + i69;
        int i73 = i(i68, 10);
        int i74 = i(i69 + o(i72, i70, i73) + this.n[12] + 1352829926, 6) + i71;
        int i75 = i(i70, 10);
        int i76 = i(i39 + l(i74, i40, i43) + this.n[7] + 1518500249, 7) + i41;
        int i77 = i(i40, 10);
        int i78 = i(i41 + l(i76, i74, i77) + this.n[4] + 1518500249, 6) + i43;
        int i79 = i(i74, 10);
        int i80 = i(i43 + l(i78, i76, i79) + this.n[13] + 1518500249, 8) + i77;
        int i81 = i(i76, 10);
        int i82 = i(i77 + l(i80, i78, i81) + this.n[1] + 1518500249, 13) + i79;
        int i83 = i(i78, 10);
        int i84 = i(i79 + l(i82, i80, i83) + this.n[10] + 1518500249, 11) + i81;
        int i85 = i(i80, 10);
        int i86 = i(i81 + l(i84, i82, i85) + this.n[6] + 1518500249, 9) + i83;
        int i87 = i(i82, 10);
        int i88 = i(i83 + l(i86, i84, i87) + this.n[15] + 1518500249, 7) + i85;
        int i89 = i(i84, 10);
        int i90 = i(i85 + l(i88, i86, i89) + this.n[3] + 1518500249, 15) + i87;
        int i91 = i(i86, 10);
        int i92 = i(i87 + l(i90, i88, i91) + this.n[12] + 1518500249, 7) + i89;
        int i93 = i(i88, 10);
        int i94 = i(i89 + l(i92, i90, i93) + this.n[0] + 1518500249, 12) + i91;
        int i95 = i(i90, 10);
        int i96 = i(i91 + l(i94, i92, i95) + this.n[9] + 1518500249, 15) + i93;
        int i97 = i(i92, 10);
        int i98 = i(i93 + l(i96, i94, i97) + this.n[5] + 1518500249, 9) + i95;
        int i99 = i(i94, 10);
        int i100 = i(i95 + l(i98, i96, i99) + this.n[2] + 1518500249, 11) + i97;
        int i101 = i(i96, 10);
        int i102 = i(i97 + l(i100, i98, i101) + this.n[14] + 1518500249, 7) + i99;
        int i103 = i(i98, 10);
        int i104 = i(i99 + l(i102, i100, i103) + this.n[11] + 1518500249, 13) + i101;
        int i105 = i(i100, 10);
        int i106 = i(i101 + l(i104, i102, i105) + this.n[8] + 1518500249, 12) + i103;
        int i107 = i(i102, 10);
        int i108 = i(i71 + n(i42, i72, i75) + this.n[6] + 1548603684, 9) + i73;
        int i109 = i(i72, 10);
        int i110 = i(i73 + n(i108, i42, i109) + this.n[11] + 1548603684, 13) + i75;
        int i111 = i(i42, 10);
        int i112 = i(i75 + n(i110, i108, i111) + this.n[3] + 1548603684, 15) + i109;
        int i113 = i(i108, 10);
        int i114 = i(i109 + n(i112, i110, i113) + this.n[7] + 1548603684, 7) + i111;
        int i115 = i(i110, 10);
        int i116 = i(i111 + n(i114, i112, i115) + this.n[0] + 1548603684, 12) + i113;
        int i117 = i(i112, 10);
        int i118 = i(i113 + n(i116, i114, i117) + this.n[13] + 1548603684, 8) + i115;
        int i119 = i(i114, 10);
        int i120 = i(i115 + n(i118, i116, i119) + this.n[5] + 1548603684, 9) + i117;
        int i121 = i(i116, 10);
        int i122 = i(i117 + n(i120, i118, i121) + this.n[10] + 1548603684, 11) + i119;
        int i123 = i(i118, 10);
        int i124 = i(i119 + n(i122, i120, i123) + this.n[14] + 1548603684, 7) + i121;
        int i125 = i(i120, 10);
        int i126 = i(i121 + n(i124, i122, i125) + this.n[15] + 1548603684, 7) + i123;
        int i127 = i(i122, 10);
        int i128 = i(i123 + n(i126, i124, i127) + this.n[8] + 1548603684, 12) + i125;
        int i129 = i(i124, 10);
        int i130 = i(i125 + n(i128, i126, i129) + this.n[12] + 1548603684, 7) + i127;
        int i131 = i(i126, 10);
        int i132 = i(i127 + n(i130, i128, i131) + this.n[4] + 1548603684, 6) + i129;
        int i133 = i(i128, 10);
        int i134 = i(i129 + n(i132, i130, i133) + this.n[9] + 1548603684, 15) + i131;
        int i135 = i(i130, 10);
        int i136 = i(i131 + n(i134, i132, i135) + this.n[1] + 1548603684, 13) + i133;
        int i137 = i(i132, 10);
        int i138 = i(i133 + n(i136, i134, i137) + this.n[2] + 1548603684, 11) + i135;
        int i139 = i(i134, 10);
        int i140 = i(i103 + m(i106, i104, i139) + this.n[3] + 1859775393, 11) + i105;
        int i141 = i(i104, 10);
        int i142 = i(i105 + m(i140, i106, i141) + this.n[10] + 1859775393, 13) + i139;
        int i143 = i(i106, 10);
        int i144 = i(i139 + m(i142, i140, i143) + this.n[14] + 1859775393, 6) + i141;
        int i145 = i(i140, 10);
        int i146 = i(i141 + m(i144, i142, i145) + this.n[4] + 1859775393, 7) + i143;
        int i147 = i(i142, 10);
        int i148 = i(i143 + m(i146, i144, i147) + this.n[9] + 1859775393, 14) + i145;
        int i149 = i(i144, 10);
        int i150 = i(i145 + m(i148, i146, i149) + this.n[15] + 1859775393, 9) + i147;
        int i151 = i(i146, 10);
        int i152 = i(i147 + m(i150, i148, i151) + this.n[8] + 1859775393, 13) + i149;
        int i153 = i(i148, 10);
        int i154 = i(i149 + m(i152, i150, i153) + this.n[1] + 1859775393, 15) + i151;
        int i155 = i(i150, 10);
        int i156 = i(i151 + m(i154, i152, i155) + this.n[2] + 1859775393, 14) + i153;
        int i157 = i(i152, 10);
        int i158 = i(i153 + m(i156, i154, i157) + this.n[7] + 1859775393, 8) + i155;
        int i159 = i(i154, 10);
        int i160 = i(i155 + m(i158, i156, i159) + this.n[0] + 1859775393, 13) + i157;
        int i161 = i(i156, 10);
        int i162 = i(i157 + m(i160, i158, i161) + this.n[6] + 1859775393, 6) + i159;
        int i163 = i(i158, 10);
        int i164 = i(i159 + m(i162, i160, i163) + this.n[13] + 1859775393, 5) + i161;
        int i165 = i(i160, 10);
        int i166 = i(i161 + m(i164, i162, i165) + this.n[11] + 1859775393, 12) + i163;
        int i167 = i(i162, 10);
        int i168 = i(i163 + m(i166, i164, i167) + this.n[5] + 1859775393, 7) + i165;
        int i169 = i(i164, 10);
        int i170 = i(i165 + m(i168, i166, i169) + this.n[12] + 1859775393, 5) + i167;
        int i171 = i(i166, 10);
        int i172 = i(i135 + m(i138, i136, i107) + this.n[15] + 1836072691, 9) + i137;
        int i173 = i(i136, 10);
        int i174 = i(i137 + m(i172, i138, i173) + this.n[5] + 1836072691, 7) + i107;
        int i175 = i(i138, 10);
        int i176 = i(i107 + m(i174, i172, i175) + this.n[1] + 1836072691, 15) + i173;
        int i177 = i(i172, 10);
        int i178 = i(i173 + m(i176, i174, i177) + this.n[3] + 1836072691, 11) + i175;
        int i179 = i(i174, 10);
        int i180 = i(i175 + m(i178, i176, i179) + this.n[7] + 1836072691, 8) + i177;
        int i181 = i(i176, 10);
        int i182 = i(i177 + m(i180, i178, i181) + this.n[14] + 1836072691, 6) + i179;
        int i183 = i(i178, 10);
        int i184 = i(i179 + m(i182, i180, i183) + this.n[6] + 1836072691, 6) + i181;
        int i185 = i(i180, 10);
        int i186 = i(i181 + m(i184, i182, i185) + this.n[9] + 1836072691, 14) + i183;
        int i187 = i(i182, 10);
        int i188 = i(i183 + m(i186, i184, i187) + this.n[11] + 1836072691, 12) + i185;
        int i189 = i(i184, 10);
        int i190 = i(i185 + m(i188, i186, i189) + this.n[8] + 1836072691, 13) + i187;
        int i191 = i(i186, 10);
        int i192 = i(i187 + m(i190, i188, i191) + this.n[12] + 1836072691, 5) + i189;
        int i193 = i(i188, 10);
        int i194 = i(i189 + m(i192, i190, i193) + this.n[2] + 1836072691, 14) + i191;
        int i195 = i(i190, 10);
        int i196 = i(i191 + m(i194, i192, i195) + this.n[10] + 1836072691, 13) + i193;
        int i197 = i(i192, 10);
        int i198 = i(i193 + m(i196, i194, i197) + this.n[0] + 1836072691, 13) + i195;
        int i199 = i(i194, 10);
        int i200 = i(i195 + m(i198, i196, i199) + this.n[4] + 1836072691, 7) + i197;
        int i201 = i(i196, 10);
        int i202 = i(i197 + m(i200, i198, i201) + this.n[13] + 1836072691, 5) + i199;
        int i203 = i(i198, 10);
        int i204 = i(((i199 + n(i170, i168, i171)) + this.n[1]) - 1894007588, 11) + i169;
        int i205 = i(i168, 10);
        int i206 = i(((i169 + n(i204, i170, i205)) + this.n[9]) - 1894007588, 12) + i171;
        int i207 = i(i170, 10);
        int i208 = i(((i171 + n(i206, i204, i207)) + this.n[11]) - 1894007588, 14) + i205;
        int i209 = i(i204, 10);
        int i210 = i(((i205 + n(i208, i206, i209)) + this.n[10]) - 1894007588, 15) + i207;
        int i211 = i(i206, 10);
        int i212 = i(((i207 + n(i210, i208, i211)) + this.n[0]) - 1894007588, 14) + i209;
        int i213 = i(i208, 10);
        int i214 = i(((i209 + n(i212, i210, i213)) + this.n[8]) - 1894007588, 15) + i211;
        int i215 = i(i210, 10);
        int i216 = i(((i211 + n(i214, i212, i215)) + this.n[12]) - 1894007588, 9) + i213;
        int i217 = i(i212, 10);
        int i218 = i(((i213 + n(i216, i214, i217)) + this.n[4]) - 1894007588, 8) + i215;
        int i219 = i(i214, 10);
        int i220 = i(((i215 + n(i218, i216, i219)) + this.n[13]) - 1894007588, 9) + i217;
        int i221 = i(i216, 10);
        int i222 = i(((i217 + n(i220, i218, i221)) + this.n[3]) - 1894007588, 14) + i219;
        int i223 = i(i218, 10);
        int i224 = i(((i219 + n(i222, i220, i223)) + this.n[7]) - 1894007588, 5) + i221;
        int i225 = i(i220, 10);
        int i226 = i(((i221 + n(i224, i222, i225)) + this.n[15]) - 1894007588, 6) + i223;
        int i227 = i(i222, 10);
        int i228 = i(((i223 + n(i226, i224, i227)) + this.n[14]) - 1894007588, 8) + i225;
        int i229 = i(i224, 10);
        int i230 = i(((i225 + n(i228, i226, i229)) + this.n[5]) - 1894007588, 6) + i227;
        int i231 = i(i226, 10);
        int i232 = i(((i227 + n(i230, i228, i231)) + this.n[6]) - 1894007588, 5) + i229;
        int i233 = i(i228, 10);
        int i234 = i(((i229 + n(i232, i230, i233)) + this.n[2]) - 1894007588, 12) + i231;
        int i235 = i(i230, 10);
        int i236 = i(i167 + l(i202, i200, i203) + this.n[8] + 2053994217, 15) + i201;
        int i237 = i(i200, 10);
        int i238 = i(i201 + l(i236, i202, i237) + this.n[6] + 2053994217, 5) + i203;
        int i239 = i(i202, 10);
        int i240 = i(i203 + l(i238, i236, i239) + this.n[4] + 2053994217, 8) + i237;
        int i241 = i(i236, 10);
        int i242 = i(i237 + l(i240, i238, i241) + this.n[1] + 2053994217, 11) + i239;
        int i243 = i(i238, 10);
        int i244 = i(i239 + l(i242, i240, i243) + this.n[3] + 2053994217, 14) + i241;
        int i245 = i(i240, 10);
        int i246 = i(i241 + l(i244, i242, i245) + this.n[11] + 2053994217, 14) + i243;
        int i247 = i(i242, 10);
        int i248 = i(i243 + l(i246, i244, i247) + this.n[15] + 2053994217, 6) + i245;
        int i249 = i(i244, 10);
        int i250 = i(i245 + l(i248, i246, i249) + this.n[0] + 2053994217, 14) + i247;
        int i251 = i(i246, 10);
        int i252 = i(i247 + l(i250, i248, i251) + this.n[5] + 2053994217, 6) + i249;
        int i253 = i(i248, 10);
        int i254 = i(i249 + l(i252, i250, i253) + this.n[12] + 2053994217, 9) + i251;
        int i255 = i(i250, 10);
        int i256 = i(i251 + l(i254, i252, i255) + this.n[2] + 2053994217, 12) + i253;
        int i257 = i(i252, 10);
        int i258 = i(i253 + l(i256, i254, i257) + this.n[13] + 2053994217, 9) + i255;
        int i259 = i(i254, 10);
        int i260 = i(i255 + l(i258, i256, i259) + this.n[9] + 2053994217, 12) + i257;
        int i261 = i(i256, 10);
        int i262 = i(i257 + l(i260, i258, i261) + this.n[7] + 2053994217, 5) + i259;
        int i263 = i(i258, 10);
        int i264 = i(i259 + l(i262, i260, i263) + this.n[10] + 2053994217, 15) + i261;
        int i265 = i(i260, 10);
        int i266 = i(i261 + l(i264, i262, i265) + this.n[14] + 2053994217, 8) + i263;
        int i267 = i(i262, 10);
        int i268 = i(((i231 + o(i234, i264, i235)) + this.n[4]) - 1454113458, 9) + i233;
        int i269 = i(i264, 10);
        int i270 = i(((i233 + o(i268, i234, i269)) + this.n[0]) - 1454113458, 15) + i235;
        int i271 = i(i234, 10);
        int i272 = i(((i235 + o(i270, i268, i271)) + this.n[5]) - 1454113458, 5) + i269;
        int i273 = i(i268, 10);
        int i274 = i(((i269 + o(i272, i270, i273)) + this.n[9]) - 1454113458, 11) + i271;
        int i275 = i(i270, 10);
        int i276 = i(((i271 + o(i274, i272, i275)) + this.n[7]) - 1454113458, 6) + i273;
        int i277 = i(i272, 10);
        int i278 = i(((i273 + o(i276, i274, i277)) + this.n[12]) - 1454113458, 8) + i275;
        int i279 = i(i274, 10);
        int i280 = i(((i275 + o(i278, i276, i279)) + this.n[2]) - 1454113458, 13) + i277;
        int i281 = i(i276, 10);
        int i282 = i(((i277 + o(i280, i278, i281)) + this.n[10]) - 1454113458, 12) + i279;
        int i283 = i(i278, 10);
        int i284 = i(((i279 + o(i282, i280, i283)) + this.n[14]) - 1454113458, 5) + i281;
        int i285 = i(i280, 10);
        int i286 = i(((i281 + o(i284, i282, i285)) + this.n[1]) - 1454113458, 12) + i283;
        int i287 = i(i282, 10);
        int i288 = i(((i283 + o(i286, i284, i287)) + this.n[3]) - 1454113458, 13) + i285;
        int i289 = i(i284, 10);
        int i290 = i(((i285 + o(i288, i286, i289)) + this.n[8]) - 1454113458, 14) + i287;
        int i291 = i(i286, 10);
        int i292 = i(((i287 + o(i290, i288, i291)) + this.n[11]) - 1454113458, 11) + i289;
        int i293 = i(i288, 10);
        int i294 = i(((i289 + o(i292, i290, i293)) + this.n[6]) - 1454113458, 8) + i291;
        int i295 = i(i290, 10);
        int i296 = i(((i291 + o(i294, i292, i295)) + this.n[15]) - 1454113458, 5) + i293;
        int i297 = i(i292, 10);
        int i298 = i(((i293 + o(i296, i294, i297)) + this.n[13]) - 1454113458, 6) + i295;
        int i299 = i(i294, 10);
        int i300 = i(i263 + k(i266, i232, i267) + this.n[12], 8) + i265;
        int i301 = i(i232, 10);
        int i302 = i(i265 + k(i300, i266, i301) + this.n[15], 5) + i267;
        int i303 = i(i266, 10);
        int i304 = i(i267 + k(i302, i300, i303) + this.n[10], 12) + i301;
        int i305 = i(i300, 10);
        int i306 = i(i301 + k(i304, i302, i305) + this.n[4], 9) + i303;
        int i307 = i(i302, 10);
        int i308 = i(i303 + k(i306, i304, i307) + this.n[1], 12) + i305;
        int i309 = i(i304, 10);
        int i310 = i(i305 + k(i308, i306, i309) + this.n[5], 5) + i307;
        int i311 = i(i306, 10);
        int i312 = i(i307 + k(i310, i308, i311) + this.n[8], 14) + i309;
        int i313 = i(i308, 10);
        int i314 = i(i309 + k(i312, i310, i313) + this.n[7], 6) + i311;
        int i315 = i(i310, 10);
        int i316 = i(i311 + k(i314, i312, i315) + this.n[6], 8) + i313;
        int i317 = i(i312, 10);
        int i318 = i(i313 + k(i316, i314, i317) + this.n[2], 13) + i315;
        int i319 = i(i314, 10);
        int i320 = i(i315 + k(i318, i316, i319) + this.n[13], 6) + i317;
        int i321 = i(i316, 10);
        int i322 = i(i317 + k(i320, i318, i321) + this.n[14], 5) + i319;
        int i323 = i(i318, 10);
        int i324 = i(i319 + k(i322, i320, i323) + this.n[0], 15) + i321;
        int i325 = i(i320, 10);
        int i326 = i(i321 + k(i324, i322, i325) + this.n[3], 13) + i323;
        int i327 = i(i322, 10);
        int i328 = i(i323 + k(i326, i324, i327) + this.n[9], 11) + i325;
        int i329 = i(i324, 10);
        int i330 = i(i325 + k(i328, i326, i329) + this.n[11], 11) + i327;
        int i331 = i(i326, 10);
        this.f4348d += i295;
        this.f4349e += i298;
        this.f4350f += i296;
        this.f4351g += i299;
        this.f4352h += i329;
        this.f4353i += i327;
        this.j += i330;
        this.k += i328;
        this.l += i331;
        this.m += i297;
        this.o = 0;
        int i332 = 0;
        while (true) {
            int[] iArr = this.n;
            if (i332 == iArr.length) {
                return;
            }
            iArr[i332] = 0;
            i332++;
        }
    }

    @Override // g.a.b.a0.h
    public void g(long j) {
        if (this.o > 14) {
            f();
        }
        int[] iArr = this.n;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "RIPEMD320";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 40;
    }

    @Override // g.a.b.a0.h
    public void h(byte[] bArr, int i2) {
        int[] iArr = this.n;
        int i3 = this.o;
        int i4 = i3 + 1;
        this.o = i4;
        iArr[i3] = ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
        if (i4 == 16) {
            f();
        }
    }

    public final int i(int i2, int i3) {
        return (i2 >>> (32 - i3)) | (i2 << i3);
    }

    public final void j(r rVar) {
        super.d(rVar);
        this.f4348d = rVar.f4348d;
        this.f4349e = rVar.f4349e;
        this.f4350f = rVar.f4350f;
        this.f4351g = rVar.f4351g;
        this.f4352h = rVar.f4352h;
        this.f4353i = rVar.f4353i;
        this.j = rVar.j;
        this.k = rVar.k;
        this.l = rVar.l;
        this.m = rVar.m;
        int[] iArr = rVar.n;
        System.arraycopy(iArr, 0, this.n, 0, iArr.length);
        this.o = rVar.o;
    }

    public final int k(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    public final int l(int i2, int i3, int i4) {
        return ((~i2) & i4) | (i3 & i2);
    }

    public final int m(int i2, int i3, int i4) {
        return (i2 | (~i3)) ^ i4;
    }

    public final int n(int i2, int i3, int i4) {
        return (i2 & i4) | (i3 & (~i4));
    }

    public final int o(int i2, int i3, int i4) {
        return i2 ^ (i3 | (~i4));
    }

    public final void p(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3 + 2] = (byte) (i2 >>> 16);
        bArr[i3 + 3] = (byte) (i2 >>> 24);
    }

    @Override // g.a.b.a0.h, g.a.b.n
    public void reset() {
        super.reset();
        this.f4348d = 1732584193;
        this.f4349e = -271733879;
        this.f4350f = -1732584194;
        this.f4351g = 271733878;
        this.f4352h = -1009589776;
        this.f4353i = 1985229328;
        this.j = -19088744;
        this.k = -1985229329;
        this.l = 19088743;
        this.m = 1009589775;
        this.o = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.n;
            if (i2 == iArr.length) {
                return;
            }
            iArr[i2] = 0;
            i2++;
        }
    }
}
