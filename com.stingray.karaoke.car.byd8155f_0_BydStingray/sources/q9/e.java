package q9;

import A7.m;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class e extends f9.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f21360e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ g f21361f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(String str, g gVar) {
        super(str, true);
        this.f21361f = gVar;
    }

    @Override // f9.a
    public final long a() {
        switch (this.f21360e) {
            case 0:
                g gVar = this.f21361f;
                try {
                    if (gVar.h()) {
                    }
                } catch (IOException e10) {
                    gVar.c(e10);
                    break;
                }
                break;
            default:
                g9.j jVar = this.f21361f.f21369g;
                m.c(jVar);
                jVar.d();
                break;
        }
        return -1L;
        return -1L;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(g gVar) {
        super(android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(), gVar.l, " writer"), true);
        this.f21361f = gVar;
    }
}
