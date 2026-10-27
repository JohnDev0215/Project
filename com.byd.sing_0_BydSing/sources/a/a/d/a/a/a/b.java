package a.a.d.a.a.a;

import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o;
import com.loostone.libtuning.data.config.vendor.EnvironmentalSoundItem;
import com.loostone.libtuning.inf.extern.IVolumeAdjustment;
import java.util.ArrayList;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class b implements IVolumeAdjustment {

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    static {
        new a(null);
    }

    public final void a() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustBySO", " ------> aef1ReverbVolumeDown");
        OooO oooO = OooO.f199a;
        if (oooO.g().a() == null) {
            return;
        }
        Integer numA = oooO.g().a();
        h.b(numA);
        int iIntValue = numA.intValue() - OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f466b;
        oooO.g().e(iIntValue >= 0 ? Integer.valueOf(iIntValue) : 0);
    }

    public final void b() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustBySO", " ------> aef1ReverbVolumeUp");
        OooO oooO = OooO.f199a;
        if (oooO.g().a() == null) {
            return;
        }
        Integer numA = oooO.g().a();
        h.b(numA);
        int iIntValue = numA.intValue() + OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f466b;
        oooO.g().e(iIntValue <= 100 ? Integer.valueOf(iIntValue) : 100);
    }

    public final void c() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustBySO", " ------> aef2ReverbVolumeDown");
        OooO oooO = OooO.f199a;
        if (oooO.V1().a() == null) {
            return;
        }
        Integer numA = oooO.V1().a();
        h.b(numA);
        int iIntValue = numA.intValue() - OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f466b;
        oooO.V1().e(iIntValue >= 0 ? Integer.valueOf(iIntValue) : 0);
    }

    public final void d() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustBySO", " ------> aef2ReverbVolumeUp");
        OooO oooO = OooO.f199a;
        if (oooO.V1().a() == null) {
            return;
        }
        Integer numA = oooO.V1().a();
        h.b(numA);
        int iIntValue = numA.intValue() + OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f466b;
        oooO.V1().e(iIntValue <= 100 ? Integer.valueOf(iIntValue) : 100);
    }

    public final void e() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustBySO", " ------> laBaMicCompanyVolumeUp");
        OooO oooO = OooO.f199a;
        if (oooO.t1().a() == null) {
            return;
        }
        Integer numA = oooO.t1().a();
        h.b(numA);
        int iIntValue = numA.intValue() + OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f467c;
        oooO.t1().e(iIntValue <= 100 ? Integer.valueOf(iIntValue) : 100);
    }

    public final void f() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustBySO", " ------> laBaMicCompanyVolumeDown");
        OooO oooO = OooO.f199a;
        if (oooO.t1().a() == null) {
            return;
        }
        Integer numA = oooO.t1().a();
        h.b(numA);
        int iIntValue = numA.intValue() - OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f467c;
        oooO.t1().e(iIntValue >= 0 ? Integer.valueOf(iIntValue) : 0);
    }

    public final void g() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustBySO", " ------> micVolumeDown");
        OooO oooO = OooO.f199a;
        if (oooO.d1().a() == null) {
            return;
        }
        Integer numA = oooO.d1().a();
        h.b(numA);
        int iIntValue = numA.intValue() - OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f465a;
        oooO.d1().e(iIntValue >= 0 ? Integer.valueOf(iIntValue) : 0);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef1ReverbOutGain() {
        Integer numA = OooO.f199a.g().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2CompressorAttack() {
        Integer numA = OooO.f199a.i().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2CompressorPregain() {
        Integer numA = OooO.f199a.l().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2CompressorRatio() {
        Integer numA = OooO.f199a.k().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2CompressorRelease() {
        Integer numA = OooO.f199a.m().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2CompressorThreshold() {
        Integer numA = OooO.f199a.n().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ1() {
        Integer numA = OooO.f199a.o().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ1Frequency() {
        Integer numA = OooO.f199a.a().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ2() {
        Integer numA = OooO.f199a.q().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ2Frequency() {
        Integer numA = OooO.f199a.p().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ3() {
        Integer numA = OooO.f199a.r().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ3Frequency() {
        Integer numA = OooO.f199a.s().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ4() {
        Integer numA = OooO.f199a.u().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ4Frequency() {
        Integer numA = OooO.f199a.t().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ5() {
        Integer numA = OooO.f199a.v().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ5Frequency() {
        Integer numA = OooO.f199a.w().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ6() {
        Integer numA = OooO.f199a.A().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ6Frequency() {
        Integer numA = OooO.f199a.x().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ7() {
        Integer numA = OooO.f199a.B().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ7Frequency() {
        Integer numA = OooO.f199a.z().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ8() {
        Integer numA = OooO.f199a.y().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ8Frequency() {
        Integer numA = OooO.f199a.C().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ9() {
        Integer numA = OooO.f199a.D().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ9Frequency() {
        Integer numA = OooO.f199a.E().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EchoFeedBack() {
        Integer numA = OooO.f199a.G().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EchoGain() {
        Integer numA = OooO.f199a.I().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EchoTime() {
        Integer numA = OooO.f199a.J().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EchoType() {
        Integer numA = OooO.f199a.M().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ1() {
        Integer numA = OooO.f199a.Q().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ1Frequency() {
        Integer numA = OooO.f199a.P().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ2() {
        Integer numA = OooO.f199a.R().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ2Frequency() {
        Integer numA = OooO.f199a.S().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ3() {
        Integer numA = OooO.f199a.U().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ3Frequency() {
        Integer numA = OooO.f199a.T().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ4() {
        Integer numA = OooO.f199a.X().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ4Frequency() {
        Integer numA = OooO.f199a.K().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ5() {
        Integer numA = OooO.f199a.Y().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ5Frequency() {
        Integer numA = OooO.f199a.W().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ6() {
        Integer numA = OooO.f199a.Z().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ6Frequency() {
        Integer numA = OooO.f199a.V().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ7() {
        Integer numA = OooO.f199a.a0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ7Frequency() {
        Integer numA = OooO.f199a.I1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ8() {
        Integer numA = OooO.f199a.c0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ8Frequency() {
        Integer numA = OooO.f199a.b0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ9() {
        Integer numA = OooO.f199a.e0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ9Frequency() {
        Integer numA = OooO.f199a.d0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2ReverbMode() {
        Integer numA = OooO.f199a.n0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2ReverbTime() {
        Integer numA = OooO.f199a.m0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2ReverbVolume() {
        Integer numA = OooO.f199a.V1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2VoiceMode() {
        Integer numA = OooO.f199a.J1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ1Type() {
        Integer numA = OooO.f199a.P1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ2Type() {
        Integer numA = OooO.f199a.Z1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ3Type() {
        Integer numA = OooO.f199a.O1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ4Type() {
        Integer numA = OooO.f199a.N1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ5Type() {
        Integer numA = OooO.f199a.W1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ6Type() {
        Integer numA = OooO.f199a.X1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ7Type() {
        Integer numA = OooO.f199a.Y1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ8Type() {
        Integer numA = OooO.f199a.S1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ9Type() {
        Integer numA = OooO.f199a.T1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EchoHicut() {
        Integer numA = OooO.f199a.K1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EchoLowcut() {
        Integer numA = OooO.f199a.L1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EchoStwide() {
        Integer numA = OooO.f199a.M1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ1() {
        Integer numA = OooO.f199a.R1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ2() {
        Integer numA = OooO.f199a.a2().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ3() {
        Integer numA = OooO.f199a.Q1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ4() {
        Integer numA = OooO.f199a.p1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ5() {
        Integer numA = OooO.f199a.r0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ6() {
        Integer numA = OooO.f199a.s0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ7() {
        Integer numA = OooO.f199a.t0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ8() {
        Integer numA = OooO.f199a.q0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ9() {
        Integer numA = OooO.f199a.v0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean getAef3FsEnable() {
        Integer numA = OooO.f199a.u0().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3ReverbHicut() {
        Integer numA = OooO.f199a.w0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3ReverbHidamp() {
        Integer numA = OooO.f199a.x0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3ReverbLowcut() {
        Integer numA = OooO.f199a.y0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3ReverbStwide() {
        Integer numA = OooO.f199a.M0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getBalance() {
        Integer numA = OooO.f199a.z0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getEnvironmentalSound() {
        Integer numA = OooO.f199a.S0().a();
        if (numA == null) {
            return 1;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getMicVolume() {
        Integer numA = OooO.f199a.d1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getMusicVolume() {
        Integer numA = OooO.f199a.t1().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getSystemVolume() {
        return 0;
    }

    public final void h() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustBySO", " ------> micVolumeUp");
        OooO oooO = OooO.f199a;
        if (oooO.d1().a() == null) {
            return;
        }
        Integer numA = oooO.d1().a();
        h.b(numA);
        int iIntValue = numA.intValue() + OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f465a;
        oooO.d1().e(iIntValue <= 100 ? Integer.valueOf(iIntValue) : 100);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef1ReverbEnable() {
        Integer numA = OooO.f199a.b().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef2CompressorEnable() {
        Integer numA = OooO.f199a.j().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef2EQEnable() {
        Integer numA = OooO.f199a.F().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef2EchoEnable() {
        Integer numA = OooO.f199a.H().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef2MusicEQEnable() {
        Integer numA = OooO.f199a.f0().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef2ReverbEnable() {
        Integer numA = OooO.f199a.k0().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef2VoiceChangeEnable() {
        Integer numA = OooO.f199a.b2().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isMicVolumeEnable() {
        Integer numA = OooO.f199a.A1().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void recoveryFromJson() {
        OooO0o oooO0o = OooO0o.f425a;
        Long lA = oooO0o.j().a();
        long jLongValue = lA == null ? -1L : lA.longValue();
        Integer numA = oooO0o.z().a();
        int iIntValue = numA == null ? 0 : numA.intValue();
        OooO0O0 oooO0O0 = OooO0O0.f354a;
        oooO0O0.h(jLongValue, iIntValue, true);
        oooO0O0.t();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef1ReverbEnable(boolean z) {
        OooO.f199a.b().e(Integer.valueOf(z ? 1 : 2));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef1ReverbOutGain(int i) {
        OooO.f199a.g().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2CompressorAttack(int i) {
        OooO.f199a.i().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2CompressorEnable(boolean z) {
        OooO.f199a.j().e(Integer.valueOf(z ? 1 : 2));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2CompressorPregain(int i) {
        OooO.f199a.l().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2CompressorRatio(int i) {
        OooO.f199a.k().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2CompressorRelease(int i) {
        OooO.f199a.m().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2CompressorThreshold(int i) {
        OooO.f199a.n().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ1(int i) {
        OooO.f199a.o().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ1Frequency(int i) {
        OooO.f199a.a().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ2(int i) {
        OooO.f199a.q().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ2Frequency(int i) {
        OooO.f199a.p().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ3(int i) {
        OooO.f199a.r().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ3Frequency(int i) {
        OooO.f199a.s().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ4(int i) {
        OooO.f199a.u().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ4Frequency(int i) {
        OooO.f199a.t().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ5(int i) {
        OooO.f199a.v().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ5Frequency(int i) {
        OooO.f199a.w().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ6(int i) {
        OooO.f199a.A().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ6Frequency(int i) {
        OooO.f199a.x().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ7(int i) {
        OooO.f199a.B().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ7Frequency(int i) {
        OooO.f199a.z().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ8(int i) {
        OooO.f199a.y().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ8Frequency(int i) {
        OooO.f199a.C().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ9(int i) {
        OooO.f199a.D().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ9Frequency(int i) {
        OooO.f199a.E().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQEnable(boolean z) {
        OooO.f199a.F().e(Integer.valueOf(z ? 1 : 2));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EchoEnable(boolean z) {
        OooO.f199a.H().e(Integer.valueOf(z ? 1 : 2));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EchoFeedBack(int i) {
        OooO.f199a.G().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EchoGain(int i) {
        OooO.f199a.I().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EchoTime(int i) {
        OooO.f199a.J().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EchoType(int i) {
        OooO.f199a.M().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ1(int i) {
        OooO.f199a.Q().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ1Frequency(int i) {
        OooO.f199a.P().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ2(int i) {
        OooO.f199a.R().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ2Frequency(int i) {
        OooO.f199a.S().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ3(int i) {
        OooO.f199a.U().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ3Frequency(int i) {
        OooO.f199a.T().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ4(int i) {
        OooO.f199a.X().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ4Frequency(int i) {
        OooO.f199a.K().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ5(int i) {
        OooO.f199a.Y().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ5Frequency(int i) {
        OooO.f199a.W().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ6(int i) {
        OooO.f199a.Z().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ6Frequency(int i) {
        OooO.f199a.V().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ7(int i) {
        OooO.f199a.a0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ7Frequency(int i) {
        OooO.f199a.I1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ8(int i) {
        OooO.f199a.c0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ8Frequency(int i) {
        OooO.f199a.b0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ9(int i) {
        OooO.f199a.e0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ9Frequency(int i) {
        OooO.f199a.d0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQEnable(boolean z) {
        OooO.f199a.f0().e(Integer.valueOf(z ? 1 : 2));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2ReverbEnable(boolean z) {
        OooO.f199a.k0().e(Integer.valueOf(z ? 1 : 2));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2ReverbMode(int i) {
        OooO.f199a.n0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2ReverbTime(int i) {
        OooO.f199a.m0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2ReverbVolume(int i) {
        OooO.f199a.V1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2VoiceChangeEnable(boolean z) {
        OooO.f199a.b2().e(Integer.valueOf(z ? 1 : 2));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2VoiceMode(int i) {
        OooO.f199a.J1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ1Type(int i) {
        OooO.f199a.P1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ2Type(int i) {
        OooO.f199a.Z1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ3Type(int i) {
        OooO.f199a.O1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ4Type(int i) {
        OooO.f199a.N1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ5Type(int i) {
        OooO.f199a.W1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ6Type(int i) {
        OooO.f199a.X1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ7Type(int i) {
        OooO.f199a.Y1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ8Type(int i) {
        OooO.f199a.S1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ9Type(int i) {
        OooO.f199a.T1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EchoHicut(int i) {
        OooO.f199a.K1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EchoLowcut(int i) {
        OooO.f199a.L1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EchoStwide(int i) {
        OooO.f199a.M1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ1(int i) {
        OooO.f199a.R1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ2(int i) {
        OooO.f199a.a2().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ3(int i) {
        OooO.f199a.Q1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ4(int i) {
        OooO.f199a.p1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ5(int i) {
        OooO.f199a.r0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ6(int i) {
        OooO.f199a.s0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ7(int i) {
        OooO.f199a.t0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ8(int i) {
        OooO.f199a.q0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ9(int i) {
        OooO.f199a.v0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3FsEnable(boolean z) {
        OooO.f199a.u0().e(Integer.valueOf(z ? 1 : 2));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3ReverbHicut(int i) {
        OooO.f199a.w0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3ReverbHidamp(int i) {
        OooO.f199a.x0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3ReverbLowcut(int i) {
        OooO.f199a.y0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3ReverbStwide(int i) {
        OooO.f199a.M0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setBalance(int i) {
        OooO.f199a.z0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setEnvironmentalSound(int i, double d2) {
        EnvironmentalSoundItem environmentalSoundItem;
        OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.b.a aVar = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.b.a.f469a;
        if (aVar.b() == null) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("AdjustBySO", " ------> 环境音效没有初始化");
            return;
        }
        OooO oooO = OooO.f199a;
        oooO.S0().e(Integer.valueOf(i));
        if (i == 0) {
            setAef2ReverbEnable(true);
            setAef2EchoEnable(true);
            ArrayList<EnvironmentalSoundItem> arrayListB = aVar.b();
            h.b(arrayListB);
            environmentalSoundItem = arrayListB.get(0);
        } else if (i == 1) {
            setAef2ReverbEnable(true);
            setAef2EchoEnable(true);
            ArrayList<EnvironmentalSoundItem> arrayListB2 = aVar.b();
            h.b(arrayListB2);
            environmentalSoundItem = arrayListB2.get(1);
        } else if (i == 2) {
            setAef2ReverbEnable(true);
            setAef2EchoEnable(true);
            ArrayList<EnvironmentalSoundItem> arrayListB3 = aVar.b();
            h.b(arrayListB3);
            environmentalSoundItem = arrayListB3.get(2);
        } else if (i == 3) {
            setAef2ReverbEnable(false);
            setAef2EchoEnable(false);
            ArrayList<EnvironmentalSoundItem> arrayListB4 = aVar.b();
            h.b(arrayListB4);
            environmentalSoundItem = arrayListB4.get(3);
        } else if (i != 4) {
            setAef2ReverbEnable(true);
            setAef2EchoEnable(true);
            ArrayList<EnvironmentalSoundItem> arrayListB5 = aVar.b();
            h.b(arrayListB5);
            environmentalSoundItem = arrayListB5.get(1);
        } else {
            setAef2ReverbEnable(true);
            setAef2EchoEnable(true);
            ArrayList<EnvironmentalSoundItem> arrayListB6 = aVar.b();
            h.b(arrayListB6);
            environmentalSoundItem = arrayListB6.get(4);
        }
        h.c(environmentalSoundItem, "when (mode) {\n            Constant.EnvironmentalSound.STUDIO -> {\n                setAef2ReverbEnable(true)\n                setAef2EchoEnable(true)\n\n                EnvironmentalSound.environmentalSounds!![0]\n            }\n\n            Constant.EnvironmentalSound.KTV -> {\n                setAef2ReverbEnable(true)\n                setAef2EchoEnable(true)\n\n                EnvironmentalSound.environmentalSounds!![1]\n            }\n\n            Constant.EnvironmentalSound.CONCERT -> {\n                setAef2ReverbEnable(true)\n                setAef2EchoEnable(true)\n\n                EnvironmentalSound.environmentalSounds!![2]\n            }\n\n            Constant.EnvironmentalSound.ORIGIN -> {\n                setAef2ReverbEnable(false)\n                setAef2EchoEnable(false)\n\n                EnvironmentalSound.environmentalSounds!![3]\n            }\n\n            Constant.EnvironmentalSound.CAVE -> {\n                setAef2ReverbEnable(true)\n                setAef2EchoEnable(true)\n\n                EnvironmentalSound.environmentalSounds!![4]\n            }\n\n            else -> {\n                setAef2ReverbEnable(true)\n                setAef2EchoEnable(true)\n\n                EnvironmentalSound.environmentalSounds!![1]\n            }\n        }");
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustBySO", "soundItem(" + i + ")=" + environmentalSoundItem);
        oooO.h(0);
        setAef2ReverbMode(environmentalSoundItem.getAef2ReverbMode());
        setAef2ReverbVolume((int) (((double) environmentalSoundItem.getAef2ReverbVolume()) * d2));
        setAef2ReverbTime(environmentalSoundItem.getAef2ReverbTime());
        oooO.d(0);
        setAef2EchoType(environmentalSoundItem.getAef2EchoMode());
        setAef2EchoGain((int) (((double) environmentalSoundItem.getAef2EchoGain()) * d2));
        setAef2EchoTime(environmentalSoundItem.getAef2EchoTime());
        setAef2EchoFeedBack(environmentalSoundItem.getAef2EchoFeedback());
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setMicVolume(int i) {
        OooO.f199a.d1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setMicVolumeEnable(boolean z) {
        OooO.f199a.A1().e(Integer.valueOf(z ? 1 : 2));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setMusicVolume(int i) {
        OooO.f199a.t1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setSystemVolume(int i) {
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void volumeDown(int i) {
        if (i == 1) {
            g();
            return;
        }
        if (i == 2) {
            a();
        } else if (i == 3) {
            c();
        } else {
            if (i != 4) {
                return;
            }
            f();
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void volumeUp(int i) {
        if (i == 1) {
            h();
            return;
        }
        if (i == 2) {
            b();
        } else if (i == 3) {
            d();
        } else {
            if (i != 4) {
                return;
            }
            e();
        }
    }
}
