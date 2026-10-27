package m3;

import androidx.media3.ui.AspectRatioFrameLayout;

/* JADX INFO: renamed from: m3.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1802b implements Runnable {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f19007m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f19008n;

    public RunnableC1802b(J3.f fVar, boolean z6) {
        this.f19008n = fVar;
        this.f19007m = z6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj = this.f19008n;
        switch (this.l) {
            case 0:
                this.f19007m = false;
                int i7 = AspectRatioFrameLayout.o;
                ((AspectRatioFrameLayout) obj).getClass();
                break;
            default:
                J3.f fVar = (J3.f) obj;
                fVar.getClass();
                A4.q.a();
                A2.c cVar = (A2.c) fVar.f3081b;
                boolean z6 = cVar.f311b;
                boolean z10 = this.f19007m;
                cVar.f311b = z10;
                if (z6 != z10) {
                    ((t4.d) cVar.c).a(z10);
                }
                break;
        }
    }

    public RunnableC1802b(AspectRatioFrameLayout aspectRatioFrameLayout) {
        this.f19008n = aspectRatioFrameLayout;
    }
}
