package defpackage;

import J.N;
import defpackage.f83;
import defpackage.y71;

/* JADX INFO: compiled from: LinkToTextHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x71 implements f83.d {
    public final /* synthetic */ y71.a e;

    public x71(ke0 ke0Var) {
        this.e = ke0Var;
    }

    @Override // defpackage.tl
    public final void d(String str, Integer num, Integer num2) {
        String str2 = str;
        Integer num3 = num;
        Integer num4 = num2;
        sk skVar = bq.a;
        if (N.M09VlOh_("PreemptiveLinkToTextGeneration")) {
            oj2.U(2, 3, "SharedHighlights.LinkToTextDiagnoseStatus");
        }
        s71 s71Var = (s71) ((ke0) this.e).f;
        if (s71Var.n == 3) {
            return;
        }
        s71Var.n = 2;
        int i = !str2.isEmpty() ? 1 : 0;
        if (i != 0) {
            y71.c(new gp(s71Var, 21, str2), s71Var.f);
        } else {
            s71Var.y1(num3.intValue());
        }
        N.Mb$rtZJa(i, num4.intValue());
    }
}
