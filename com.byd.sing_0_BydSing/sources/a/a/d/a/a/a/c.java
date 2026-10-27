package a.a.d.a.a.a;

import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0OO;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o;
import com.loostone.libtuning.data.config.vendor.EnvironmentalSoundItem;
import com.loostone.libtuning.inf.extern.IVolumeAdjustment;
import java.util.ArrayList;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class c implements IVolumeAdjustment {

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
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustByKO", " ------> aef1ReverbVolumeDown");
        OooO0OO oooO0OO = OooO0OO.f361a;
        if (oooO0OO.e().a() == null) {
            return;
        }
        Integer numA = oooO0OO.e().a();
        h.b(numA);
        int iIntValue = numA.intValue() - OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f466b;
        oooO0OO.e().e(iIntValue >= 0 ? Integer.valueOf(iIntValue) : 0);
    }

    public final void b() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustByKO", " ------> aef1ReverbVolumeUp");
        OooO0OO oooO0OO = OooO0OO.f361a;
        if (oooO0OO.e().a() == null) {
            return;
        }
        Integer numA = oooO0OO.e().a();
        h.b(numA);
        int iIntValue = numA.intValue() + OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f466b;
        oooO0OO.e().e(iIntValue <= 100 ? Integer.valueOf(iIntValue) : 100);
    }

    public final void c() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustByKO", " ------> aef2ReverbVolumeDown");
        OooO0OO oooO0OO = OooO0OO.f361a;
        if (oooO0OO.d0().a() == null) {
            return;
        }
        Integer numA = oooO0OO.d0().a();
        h.b(numA);
        int iIntValue = numA.intValue() - OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f466b;
        oooO0OO.d0().e(iIntValue >= 0 ? Integer.valueOf(iIntValue) : 0);
    }

    public final void d() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustByKO", " ------> aef2ReverbVolumeUp");
        OooO0OO oooO0OO = OooO0OO.f361a;
        if (oooO0OO.d0().a() == null) {
            return;
        }
        Integer numA = oooO0OO.d0().a();
        h.b(numA);
        int iIntValue = numA.intValue() + OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f466b;
        oooO0OO.d0().e(iIntValue <= 100 ? Integer.valueOf(iIntValue) : 100);
    }

    public final void e() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustByKO", " ------> laBaMicCompanyVolumeUp");
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
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustByKO", " ------> laBaMicCompanyVolumeDown");
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
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustByKO", " ------> micVolumeDown");
        OooO0OO oooO0OO = OooO0OO.f361a;
        if (oooO0OO.i0().a() == null) {
            return;
        }
        Integer numA = oooO0OO.i0().a();
        h.b(numA);
        int iIntValue = numA.intValue() - OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f465a;
        oooO0OO.i0().e(iIntValue >= 0 ? Integer.valueOf(iIntValue) : 0);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef1ReverbOutGain() {
        Integer numA = OooO0OO.f361a.e().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2CompressorAttack() {
        Integer numA = OooO0OO.f361a.f().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2CompressorPregain() {
        Integer numA = OooO0OO.f361a.i().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2CompressorRatio() {
        Integer numA = OooO0OO.f361a.h().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2CompressorRelease() {
        Integer numA = OooO0OO.f361a.j().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2CompressorThreshold() {
        Integer numA = OooO0OO.f361a.k().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ1() {
        Integer numA = OooO0OO.f361a.l().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ1Frequency() {
        Integer numA = OooO0OO.f361a.a().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ2() {
        Integer numA = OooO0OO.f361a.n().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ2Frequency() {
        Integer numA = OooO0OO.f361a.m().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ3() {
        Integer numA = OooO0OO.f361a.o().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ3Frequency() {
        Integer numA = OooO0OO.f361a.p().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ4() {
        Integer numA = OooO0OO.f361a.r().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ4Frequency() {
        Integer numA = OooO0OO.f361a.q().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ5() {
        Integer numA = OooO0OO.f361a.s().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ5Frequency() {
        Integer numA = OooO0OO.f361a.t().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ6() {
        Integer numA = OooO0OO.f361a.x().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ6Frequency() {
        Integer numA = OooO0OO.f361a.u().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ7() {
        Integer numA = OooO0OO.f361a.y().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ7Frequency() {
        Integer numA = OooO0OO.f361a.w().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ8() {
        Integer numA = OooO0OO.f361a.v().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ8Frequency() {
        Integer numA = OooO0OO.f361a.z().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ9() {
        Integer numA = OooO0OO.f361a.A().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EQ9Frequency() {
        Integer numA = OooO0OO.f361a.B().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EchoFeedBack() {
        Integer numA = OooO0OO.f361a.D().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EchoGain() {
        Integer numA = OooO0OO.f361a.F().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EchoTime() {
        Integer numA = OooO0OO.f361a.G().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2EchoType() {
        Integer numA = OooO0OO.f361a.J().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ1() {
        Integer numA = OooO0OO.f361a.L().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ1Frequency() {
        Integer numA = OooO0OO.f361a.K().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ2() {
        Integer numA = OooO0OO.f361a.M().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ2Frequency() {
        Integer numA = OooO0OO.f361a.I().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ3() {
        Integer numA = OooO0OO.f361a.P().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ3Frequency() {
        Integer numA = OooO0OO.f361a.N().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ4() {
        Integer numA = OooO0OO.f361a.Q().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ4Frequency() {
        Integer numA = OooO0OO.f361a.O().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ5() {
        Integer numA = OooO0OO.f361a.H().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ5Frequency() {
        Integer numA = OooO0OO.f361a.R().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ6() {
        Integer numA = OooO0OO.f361a.T().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ6Frequency() {
        Integer numA = OooO0OO.f361a.U().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ7() {
        Integer numA = OooO0OO.f361a.S().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ7Frequency() {
        Integer numA = OooO0OO.f361a.V().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ8() {
        Integer numA = OooO0OO.f361a.k0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ8Frequency() {
        Integer numA = OooO0OO.f361a.W().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ9() {
        Integer numA = OooO0OO.f361a.Y().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2MusicEQ9Frequency() {
        Integer numA = OooO0OO.f361a.X().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2ReverbMode() {
        Integer numA = OooO0OO.f361a.c0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2ReverbTime() {
        Integer numA = OooO0OO.f361a.b0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2ReverbVolume() {
        Integer numA = OooO0OO.f361a.d0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef2VoiceMode() {
        Integer numA = OooO0OO.f361a.e0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ1Type() {
        return IVolumeAdjustment.DefaultImpls.getAef3EQ1Type(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ2Type() {
        return IVolumeAdjustment.DefaultImpls.getAef3EQ2Type(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ3Type() {
        return IVolumeAdjustment.DefaultImpls.getAef3EQ3Type(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ4Type() {
        return IVolumeAdjustment.DefaultImpls.getAef3EQ4Type(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ5Type() {
        return IVolumeAdjustment.DefaultImpls.getAef3EQ5Type(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ6Type() {
        return IVolumeAdjustment.DefaultImpls.getAef3EQ6Type(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ7Type() {
        return IVolumeAdjustment.DefaultImpls.getAef3EQ7Type(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ8Type() {
        return IVolumeAdjustment.DefaultImpls.getAef3EQ8Type(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EQ9Type() {
        return IVolumeAdjustment.DefaultImpls.getAef3EQ9Type(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EchoHicut() {
        return IVolumeAdjustment.DefaultImpls.getAef3EchoHicut(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EchoLowcut() {
        return IVolumeAdjustment.DefaultImpls.getAef3EchoLowcut(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EchoStwide() {
        return IVolumeAdjustment.DefaultImpls.getAef3EchoStwide(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ1() {
        return IVolumeAdjustment.DefaultImpls.getAef3EqQ1(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ2() {
        return IVolumeAdjustment.DefaultImpls.getAef3EqQ2(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ3() {
        return IVolumeAdjustment.DefaultImpls.getAef3EqQ3(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ4() {
        return IVolumeAdjustment.DefaultImpls.getAef3EqQ4(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ5() {
        return IVolumeAdjustment.DefaultImpls.getAef3EqQ5(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ6() {
        return IVolumeAdjustment.DefaultImpls.getAef3EqQ6(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ7() {
        return IVolumeAdjustment.DefaultImpls.getAef3EqQ7(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ8() {
        return IVolumeAdjustment.DefaultImpls.getAef3EqQ8(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3EqQ9() {
        return IVolumeAdjustment.DefaultImpls.getAef3EqQ9(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean getAef3FsEnable() {
        Integer numA = OooO0OO.f361a.f0().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3ReverbHicut() {
        return IVolumeAdjustment.DefaultImpls.getAef3ReverbHicut(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3ReverbHidamp() {
        return IVolumeAdjustment.DefaultImpls.getAef3ReverbHidamp(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3ReverbLowcut() {
        return IVolumeAdjustment.DefaultImpls.getAef3ReverbLowcut(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getAef3ReverbStwide() {
        return IVolumeAdjustment.DefaultImpls.getAef3ReverbStwide(this);
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
        return IVolumeAdjustment.DefaultImpls.getEnvironmentalSound(this);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public int getMicVolume() {
        Integer numA = OooO0OO.f361a.i0().a();
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
        Integer numA = OooO0OO.f361a.h0().a();
        if (numA == null) {
            return 0;
        }
        return numA.intValue();
    }

    public final void h() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("AdjustByKO", " ------> micVolumeUp");
        OooO0OO oooO0OO = OooO0OO.f361a;
        if (oooO0OO.i0().a() == null) {
            return;
        }
        Integer numA = oooO0OO.i0().a();
        h.b(numA);
        int iIntValue = numA.intValue() + OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f465a;
        oooO0OO.i0().e(iIntValue <= 100 ? Integer.valueOf(iIntValue) : 100);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef1ReverbEnable() {
        Integer numA = OooO0OO.f361a.b().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef2CompressorEnable() {
        Integer numA = OooO0OO.f361a.g().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef2EQEnable() {
        Integer numA = OooO0OO.f361a.C().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef2EchoEnable() {
        Integer numA = OooO0OO.f361a.E().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef2MusicEQEnable() {
        Integer numA = OooO0OO.f361a.Z().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef2ReverbEnable() {
        Integer numA = OooO0OO.f361a.a0().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isAef2VoiceChangeEnable() {
        Integer numA = OooO.f199a.b2().a();
        return numA != null && numA.intValue() == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public boolean isMicVolumeEnable() {
        Integer numA = OooO0OO.f361a.g0().a();
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
        OooO0OO.f361a.b().e(Integer.valueOf(z ? 1 : 0));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef1ReverbOutGain(int i) {
        OooO0OO.f361a.e().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2CompressorAttack(int i) {
        OooO0OO.f361a.f().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2CompressorEnable(boolean z) {
        OooO0OO.f361a.g().e(Integer.valueOf(z ? 1 : 2));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2CompressorPregain(int i) {
        OooO0OO.f361a.i().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2CompressorRatio(int i) {
        OooO0OO.f361a.h().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2CompressorRelease(int i) {
        OooO0OO.f361a.j().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2CompressorThreshold(int i) {
        OooO0OO.f361a.k().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ1(int i) {
        OooO0OO.f361a.l().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ1Frequency(int i) {
        OooO0OO.f361a.a().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ2(int i) {
        OooO0OO.f361a.n().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ2Frequency(int i) {
        OooO0OO.f361a.m().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ3(int i) {
        OooO0OO.f361a.o().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ3Frequency(int i) {
        OooO0OO.f361a.p().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ4(int i) {
        OooO0OO.f361a.r().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ4Frequency(int i) {
        OooO0OO.f361a.q().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ5(int i) {
        OooO0OO.f361a.s().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ5Frequency(int i) {
        OooO0OO.f361a.t().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ6(int i) {
        OooO0OO.f361a.x().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ6Frequency(int i) {
        OooO0OO.f361a.u().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ7(int i) {
        OooO0OO.f361a.y().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ7Frequency(int i) {
        OooO0OO.f361a.w().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ8(int i) {
        OooO0OO.f361a.v().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ8Frequency(int i) {
        OooO0OO.f361a.z().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ9(int i) {
        OooO0OO.f361a.A().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQ9Frequency(int i) {
        OooO0OO.f361a.B().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EQEnable(boolean z) {
        OooO0OO.f361a.C().e(Integer.valueOf(z ? 1 : 0));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EchoEnable(boolean z) {
        OooO0OO.f361a.E().e(Integer.valueOf(z ? 1 : 0));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EchoFeedBack(int i) {
        OooO0OO.f361a.D().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EchoGain(int i) {
        OooO0OO.f361a.F().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EchoTime(int i) {
        OooO0OO.f361a.G().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2EchoType(int i) {
        OooO0OO.f361a.J().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ1(int i) {
        OooO0OO.f361a.L().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ1Frequency(int i) {
        OooO0OO.f361a.K().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ2(int i) {
        OooO0OO.f361a.M().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ2Frequency(int i) {
        OooO0OO.f361a.I().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ3(int i) {
        OooO0OO.f361a.P().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ3Frequency(int i) {
        OooO0OO.f361a.N().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ4(int i) {
        OooO0OO.f361a.Q().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ4Frequency(int i) {
        OooO0OO.f361a.O().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ5(int i) {
        OooO0OO.f361a.H().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ5Frequency(int i) {
        OooO0OO.f361a.R().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ6(int i) {
        OooO0OO.f361a.T().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ6Frequency(int i) {
        OooO0OO.f361a.U().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ7(int i) {
        OooO0OO.f361a.S().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ7Frequency(int i) {
        OooO0OO.f361a.V().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ8(int i) {
        OooO0OO.f361a.k0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ8Frequency(int i) {
        OooO0OO.f361a.W().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ9(int i) {
        OooO0OO.f361a.Y().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQ9Frequency(int i) {
        OooO0OO.f361a.X().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2MusicEQEnable(boolean z) {
        OooO0OO.f361a.Z().e(Integer.valueOf(z ? 1 : 0));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2ReverbEnable(boolean z) {
        OooO0OO.f361a.a0().e(Integer.valueOf(z ? 1 : 0));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2ReverbMode(int i) {
        OooO0OO.f361a.c0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2ReverbTime(int i) {
        OooO0OO.f361a.b0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2ReverbVolume(int i) {
        OooO0OO.f361a.d0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2VoiceChangeEnable(boolean z) {
        OooO.f199a.b2().e(Integer.valueOf(z ? 1 : 2));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef2VoiceMode(int i) {
        OooO0OO.f361a.e0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ1Type(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EQ1Type(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ2Type(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EQ2Type(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ3Type(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EQ3Type(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ4Type(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EQ4Type(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ5Type(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EQ5Type(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ6Type(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EQ6Type(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ7Type(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EQ7Type(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ8Type(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EQ8Type(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EQ9Type(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EQ9Type(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EchoHicut(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EchoHicut(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EchoLowcut(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EchoLowcut(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EchoStwide(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EchoStwide(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ1(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EqQ1(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ2(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EqQ2(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ3(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EqQ3(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ4(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EqQ4(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ5(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EqQ5(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ6(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EqQ6(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ7(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EqQ7(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ8(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EqQ8(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3EqQ9(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3EqQ9(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3FsEnable(boolean z) {
        OooO0OO.f361a.f0().e(Integer.valueOf(z ? 1 : 0));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3ReverbHicut(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3ReverbHicut(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3ReverbHidamp(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3ReverbHidamp(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3ReverbLowcut(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3ReverbLowcut(this, i);
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setAef3ReverbStwide(int i) {
        IVolumeAdjustment.DefaultImpls.setAef3ReverbStwide(this, i);
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
            OooO00o.OooO00o.OooO0O0.b.f41a.d("AdjustByKO", " ------> 环境音效没有初始化");
            return;
        }
        if (i == 0) {
            setAef2ReverbEnable(true);
            ArrayList<EnvironmentalSoundItem> arrayListB = aVar.b();
            h.b(arrayListB);
            environmentalSoundItem = arrayListB.get(0);
        } else if (i == 1) {
            setAef2ReverbEnable(true);
            ArrayList<EnvironmentalSoundItem> arrayListB2 = aVar.b();
            h.b(arrayListB2);
            environmentalSoundItem = arrayListB2.get(1);
        } else if (i == 2) {
            setAef2ReverbEnable(true);
            ArrayList<EnvironmentalSoundItem> arrayListB3 = aVar.b();
            h.b(arrayListB3);
            environmentalSoundItem = arrayListB3.get(2);
        } else if (i == 3) {
            setAef2ReverbEnable(false);
            ArrayList<EnvironmentalSoundItem> arrayListB4 = aVar.b();
            h.b(arrayListB4);
            environmentalSoundItem = arrayListB4.get(3);
        } else if (i != 4) {
            setAef2ReverbEnable(true);
            ArrayList<EnvironmentalSoundItem> arrayListB5 = aVar.b();
            h.b(arrayListB5);
            environmentalSoundItem = arrayListB5.get(1);
        } else {
            setAef2ReverbEnable(true);
            ArrayList<EnvironmentalSoundItem> arrayListB6 = aVar.b();
            h.b(arrayListB6);
            environmentalSoundItem = arrayListB6.get(4);
        }
        h.c(environmentalSoundItem, "when (mode) {\n            Constant.EnvironmentalSound.STUDIO -> {\n                setAef2ReverbEnable(true)\n\n                EnvironmentalSound.environmentalSounds!![0]\n            }\n\n            Constant.EnvironmentalSound.KTV -> {\n                setAef2ReverbEnable(true)\n\n                EnvironmentalSound.environmentalSounds!![1]\n            }\n\n            Constant.EnvironmentalSound.CONCERT -> {\n                setAef2ReverbEnable(true)\n\n                EnvironmentalSound.environmentalSounds!![2]\n            }\n\n            Constant.EnvironmentalSound.ORIGIN -> {\n                setAef2ReverbEnable(false)\n\n                EnvironmentalSound.environmentalSounds!![3]\n            }\n\n            Constant.EnvironmentalSound.CAVE -> {\n                setAef2ReverbEnable(true)\n\n                EnvironmentalSound.environmentalSounds!![4]\n            }\n\n            else -> {\n                setAef2ReverbEnable(true)\n\n                EnvironmentalSound.environmentalSounds!![1]\n            }\n        }");
        setAef2ReverbMode(environmentalSoundItem.getAef2ReverbMode());
        setAef2ReverbVolume((int) (((double) environmentalSoundItem.getAef2ReverbVolume()) * d2));
        setAef2ReverbTime(environmentalSoundItem.getAef2ReverbTime());
        setAef2EchoType(environmentalSoundItem.getAef2EchoMode());
        setAef2EchoGain((int) (((double) environmentalSoundItem.getAef2EchoGain()) * d2));
        setAef2EchoTime(environmentalSoundItem.getAef2EchoTime());
        setAef2EchoFeedBack(environmentalSoundItem.getAef2EchoFeedback());
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setMicVolume(int i) {
        OooO0OO.f361a.i0().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setMicVolumeEnable(boolean z) {
        OooO0OO.f361a.g0().e(Integer.valueOf(z ? 1 : 0));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setMusicVolume(int i) {
        OooO.f199a.t1().e(Integer.valueOf(i));
    }

    @Override // com.loostone.libtuning.inf.extern.IVolumeAdjustment
    public void setSystemVolume(int i) {
        OooO0OO.f361a.h0().e(Integer.valueOf(i));
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
