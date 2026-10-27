package C3;

import A7.F;
import A7.m;
import A7.o;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.IOException;
import r9.C2313c;
import r9.C2319i;
import r9.H;
import r9.p;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class f extends p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f993m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f994n;
    public final m7.c o;

    public f(H h10, F f10) {
        super(h10);
        this.o = f10;
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [A7.o, z7.k] */
    @Override // r9.p, r9.H, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        switch (this.f993m) {
            case 0:
                try {
                    super.close();
                } catch (IOException e10) {
                    this.f994n = true;
                    ((F) this.o).b(e10);
                    return;
                }
                break;
            default:
                if (!this.f994n) {
                    try {
                        super.close();
                    } catch (IOException e11) {
                        this.f994n = true;
                        ((o) this.o).b(e11);
                    }
                    break;
                }
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [A7.o, z7.k] */
    @Override // r9.p, r9.H, java.io.Flushable
    public final void flush() {
        switch (this.f993m) {
            case 0:
                try {
                    super.flush();
                } catch (IOException e10) {
                    this.f994n = true;
                    ((F) this.o).b(e10);
                    return;
                }
                break;
            default:
                if (!this.f994n) {
                    try {
                        super.flush();
                    } catch (IOException e11) {
                        this.f994n = true;
                        ((o) this.o).b(e11);
                    }
                    break;
                }
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [A7.o, z7.k] */
    @Override // r9.p, r9.H
    public final void t(C2319i c2319i, long j3) {
        switch (this.f993m) {
            case 0:
                if (this.f994n) {
                    c2319i.R(j3);
                } else {
                    try {
                        super.t(c2319i, j3);
                    } catch (IOException e10) {
                        this.f994n = true;
                        ((F) this.o).b(e10);
                        return;
                    }
                }
                break;
            default:
                m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
                if (this.f994n) {
                    c2319i.R(j3);
                } else {
                    try {
                        super.t(c2319i, j3);
                    } catch (IOException e11) {
                        this.f994n = true;
                        ((o) this.o).b(e11);
                    }
                }
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public f(C2313c c2313c, InterfaceC2782k interfaceC2782k) {
        super(c2313c);
        this.o = (o) interfaceC2782k;
    }
}
