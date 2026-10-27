package l3;

import android.media.VolumeProvider;
import h2.AbstractC1343A;
import k3.r1;
import k3.s1;

/* JADX INFO: loaded from: classes.dex */
public final class Z extends VolumeProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ s1 f18564a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Z(s1 s1Var, int i7, int i10, int i11, String str) {
        super(i7, i10, i11, str);
        this.f18564a = s1Var;
    }

    @Override // android.media.VolumeProvider
    public final void onAdjustVolume(int i7) {
        s1 s1Var = this.f18564a;
        s1Var.getClass();
        AbstractC1343A.K(s1Var.f18115f, new r1(s1Var, i7, 1));
    }

    @Override // android.media.VolumeProvider
    public final void onSetVolumeTo(int i7) {
        s1 s1Var = this.f18564a;
        s1Var.getClass();
        AbstractC1343A.K(s1Var.f18115f, new r1(s1Var, i7, 0));
    }
}
