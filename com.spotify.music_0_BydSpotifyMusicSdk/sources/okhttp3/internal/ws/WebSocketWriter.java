package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.Random;
import okio.ByteString;
import p.eo;
import p.fo;
import p.io;
import p.n80;
import p.q30;
import p.ux2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class WebSocketWriter implements Closeable {
    public final boolean c;
    public final io e;
    public final Random k;
    public final boolean l;
    public final boolean m;
    public final long n;
    public final fo o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final fo f104p;
    public boolean q;
    public MessageDeflater r;
    public final byte[] s;
    public final eo t;

    public WebSocketWriter(boolean z, io ioVar, Random random, boolean z2, boolean z3, long j) {
        v03.j(ioVar, "sink");
        v03.j(random, "random");
        this.c = z;
        this.e = ioVar;
        this.k = random;
        this.l = z2;
        this.m = z3;
        this.n = j;
        this.o = new fo();
        this.f104p = ioVar.d();
        this.s = z ? new byte[4] : null;
        this.t = z ? new eo() : null;
    }

    public final void b(int i, ByteString byteString) throws IOException {
        if (this.q) {
            throw new IOException("closed");
        }
        int iE = byteString.e();
        if (iE > 125) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125".toString());
        }
        fo foVar = this.f104p;
        foVar.W0(i | 128);
        if (this.c) {
            foVar.W0(iE | 128);
            byte[] bArr = this.s;
            v03.h(bArr);
            this.k.nextBytes(bArr);
            foVar.U0(bArr);
            if (iE > 0) {
                long j = foVar.e;
                foVar.T0(byteString);
                eo eoVar = this.t;
                v03.h(eoVar);
                foVar.L0(eoVar);
                eoVar.c(j);
                WebSocketProtocol.a.getClass();
                WebSocketProtocol.b(eoVar, bArr);
                eoVar.close();
            }
        } else {
            foVar.W0(iE);
            foVar.T0(byteString);
        }
        this.e.flush();
    }

    public final void c(int i, ByteString byteString) throws IOException {
        v03.j(byteString, "data");
        if (this.q) {
            throw new IOException("closed");
        }
        fo foVar = this.o;
        foVar.T0(byteString);
        int i2 = i | 128;
        if (this.l && byteString.e() >= this.n) {
            MessageDeflater messageDeflater = this.r;
            if (messageDeflater == null) {
                messageDeflater = new MessageDeflater(this.m);
                this.r = messageDeflater;
            }
            fo foVar2 = messageDeflater.e;
            if (foVar2.e != 0) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (messageDeflater.c) {
                messageDeflater.k.reset();
            }
            long j = foVar.e;
            n80 n80Var = messageDeflater.l;
            n80Var.Y(foVar, j);
            n80Var.flush();
            ByteString byteString2 = MessageDeflaterKt.a;
            if (foVar2.x0(foVar2.e - ((long) byteString2.e()), byteString2)) {
                long j2 = foVar2.e - ((long) 4);
                eo eoVarL0 = foVar2.L0(q30.a);
                try {
                    eoVarL0.b(j2);
                    ux2.e(eoVarL0, null);
                } finally {
                }
            } else {
                foVar2.W0(0);
            }
            foVar.Y(foVar2, foVar2.e);
            i2 = i | 192;
        }
        long j3 = foVar.e;
        fo foVar3 = this.f104p;
        foVar3.W0(i2);
        boolean z = this.c;
        int i3 = z ? 128 : 0;
        if (j3 <= 125) {
            foVar3.W0(i3 | ((int) j3));
        } else if (j3 <= 65535) {
            foVar3.W0(i3 | 126);
            foVar3.b1((int) j3);
        } else {
            foVar3.W0(i3 | 127);
            foVar3.a1(j3);
        }
        if (z) {
            byte[] bArr = this.s;
            v03.h(bArr);
            this.k.nextBytes(bArr);
            foVar3.U0(bArr);
            if (j3 > 0) {
                eo eoVar = this.t;
                v03.h(eoVar);
                foVar.L0(eoVar);
                eoVar.c(0L);
                WebSocketProtocol.a.getClass();
                WebSocketProtocol.b(eoVar, bArr);
                eoVar.close();
            }
        }
        foVar3.Y(foVar, j3);
        this.e.B();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        MessageDeflater messageDeflater = this.r;
        if (messageDeflater != null) {
            messageDeflater.close();
        }
    }
}
