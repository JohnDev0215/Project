package p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class v80 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ x80 e;
    public final /* synthetic */ Runnable k;
    public final /* synthetic */ oh1 l;

    public /* synthetic */ v80(x80 x80Var, Runnable runnable, oh1 oh1Var, int i) {
        this.c = i;
        this.e = x80Var;
        this.k = runnable;
        this.l = oh1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        final oh1 oh1Var = this.l;
        final Runnable runnable = this.k;
        x80 x80Var = this.e;
        switch (i) {
            case 0:
                x80Var.getClass();
                final int i2 = 0;
                x80Var.c.execute(new Runnable() { // from class: p.w80
                    @Override // java.lang.Runnable
                    public final void run() throws Exception {
                        int i3 = i2;
                        oh1 oh1Var2 = oh1Var;
                        Runnable runnable2 = runnable;
                        switch (i3) {
                            case 0:
                                try {
                                    runnable2.run();
                                    return;
                                } catch (Exception e) {
                                    oh1Var2.A(e);
                                    throw e;
                                }
                            case 1:
                                try {
                                    runnable2.run();
                                    z80 z80Var = (z80) oh1Var2.e;
                                    int i4 = z80.q;
                                    z80Var.getClass();
                                    if (g2.n.g(z80Var, null, g2.o)) {
                                        g2.d(z80Var);
                                        return;
                                    }
                                    return;
                                } catch (Exception e2) {
                                    oh1Var2.A(e2);
                                    return;
                                }
                            default:
                                try {
                                    runnable2.run();
                                    return;
                                } catch (Exception e3) {
                                    oh1Var2.A(e3);
                                    return;
                                }
                        }
                    }
                });
                break;
            case 1:
                x80Var.getClass();
                final int i3 = 2;
                x80Var.c.execute(new Runnable() { // from class: p.w80
                    @Override // java.lang.Runnable
                    public final void run() throws Exception {
                        int i32 = i3;
                        oh1 oh1Var2 = oh1Var;
                        Runnable runnable2 = runnable;
                        switch (i32) {
                            case 0:
                                try {
                                    runnable2.run();
                                    return;
                                } catch (Exception e) {
                                    oh1Var2.A(e);
                                    throw e;
                                }
                            case 1:
                                try {
                                    runnable2.run();
                                    z80 z80Var = (z80) oh1Var2.e;
                                    int i4 = z80.q;
                                    z80Var.getClass();
                                    if (g2.n.g(z80Var, null, g2.o)) {
                                        g2.d(z80Var);
                                        return;
                                    }
                                    return;
                                } catch (Exception e2) {
                                    oh1Var2.A(e2);
                                    return;
                                }
                            default:
                                try {
                                    runnable2.run();
                                    return;
                                } catch (Exception e3) {
                                    oh1Var2.A(e3);
                                    return;
                                }
                        }
                    }
                });
                break;
            default:
                x80Var.getClass();
                final int i4 = 1;
                x80Var.c.execute(new Runnable() { // from class: p.w80
                    @Override // java.lang.Runnable
                    public final void run() throws Exception {
                        int i32 = i4;
                        oh1 oh1Var2 = oh1Var;
                        Runnable runnable2 = runnable;
                        switch (i32) {
                            case 0:
                                try {
                                    runnable2.run();
                                    return;
                                } catch (Exception e) {
                                    oh1Var2.A(e);
                                    throw e;
                                }
                            case 1:
                                try {
                                    runnable2.run();
                                    z80 z80Var = (z80) oh1Var2.e;
                                    int i42 = z80.q;
                                    z80Var.getClass();
                                    if (g2.n.g(z80Var, null, g2.o)) {
                                        g2.d(z80Var);
                                        return;
                                    }
                                    return;
                                } catch (Exception e2) {
                                    oh1Var2.A(e2);
                                    return;
                                }
                            default:
                                try {
                                    runnable2.run();
                                    return;
                                } catch (Exception e3) {
                                    oh1Var2.A(e3);
                                    return;
                                }
                        }
                    }
                });
                break;
        }
    }
}
