package p;

import com.spotify.automotive.baseui.settings.SettingsActivity;
import com.spotify.automotive.socialconnect.SocialConnectController;

/* JADX INFO: loaded from: classes.dex */
public final class o23 implements i13 {
    public final Object c;
    public final Object e;
    public final Object k;
    public final Object l;
    public final Object m;
    public final Object n;
    public final Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Object f161p;
    public final Object q;
    public final Object r;

    public o23(i13 i13Var, w03 w03Var, w03 w03Var2, i13 i13Var2, i13 i13Var3, i13 i13Var4, i13 i13Var5, i13 i13Var6, i13 i13Var7, i13 i13Var8) {
        this.c = i13Var;
        this.e = w03Var;
        this.k = w03Var2;
        this.l = i13Var2;
        this.m = i13Var3;
        this.n = i13Var4;
        this.o = i13Var5;
        this.f161p = i13Var6;
        this.q = i13Var7;
        this.r = i13Var8;
    }

    @Override // p.i13
    public final /* bridge */ /* synthetic */ Object a() {
        Object objA = ((i13) this.c).a();
        f13 f13VarB = h13.b((i13) this.e);
        Object objA2 = ((i13) this.k).a();
        Object objA3 = ((i13) this.m).a();
        Object objA4 = ((i13) this.n).a();
        Object objA5 = ((i13) this.o).a();
        f13 f13VarB2 = h13.b((i13) this.f161p);
        return new m23((com.google.android.play.core.assetpacks.c) objA, f13VarB, (com.google.android.play.core.assetpacks.b) objA2, f13VarB2);
    }

    public o23(SettingsActivity settingsActivity, ad1 ad1Var, rd1 rd1Var, vv2 vv2Var, jc2 jc2Var, SocialConnectController socialConnectController, m mVar, l0 l0Var, m60 m60Var) {
        this.c = new io.reactivex.rxjava3.disposables.a();
        this.r = settingsActivity;
        this.e = ad1Var;
        this.k = rd1Var;
        this.l = vv2Var;
        this.n = jc2Var;
        this.o = socialConnectController;
        this.f161p = mVar;
        this.m = l0Var;
        this.q = m60Var;
    }
}
