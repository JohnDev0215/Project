package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.Util;
import okio.ByteString;
import p.eo;
import p.fo;
import p.ko;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class WebSocketReader implements Closeable {
    public final boolean c;
    public final ko e;
    public final FrameCallback k;
    public final boolean l;
    public final boolean m;
    public boolean n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f103p;
    public boolean q;
    public boolean r;
    public boolean s;
    public final fo t;
    public final fo u;
    public MessageInflater v;
    public final byte[] w;
    public final eo x;

    public interface FrameCallback {
        void d(ByteString byteString);

        void e(String str);

        void f(ByteString byteString);

        void g(ByteString byteString);

        void h(int i, String str);
    }

    public WebSocketReader(boolean z, ko koVar, RealWebSocket realWebSocket, boolean z2, boolean z3) {
        v03.j(koVar, "source");
        v03.j(realWebSocket, "frameCallback");
        this.c = z;
        this.e = koVar;
        this.k = realWebSocket;
        this.l = z2;
        this.m = z3;
        this.t = new fo();
        this.u = new fo();
        this.w = z ? null : new byte[4];
        this.x = z ? null : new eo();
    }

    public final void b() {
        short s;
        String strQ0;
        long j = this.f103p;
        fo foVar = this.t;
        if (j > 0) {
            this.e.p(foVar, j);
            if (!this.c) {
                eo eoVar = this.x;
                v03.h(eoVar);
                foVar.L0(eoVar);
                eoVar.c(0L);
                WebSocketProtocol webSocketProtocol = WebSocketProtocol.a;
                byte[] bArr = this.w;
                v03.h(bArr);
                webSocketProtocol.getClass();
                WebSocketProtocol.b(eoVar, bArr);
                eoVar.close();
            }
        }
        int i = this.o;
        FrameCallback frameCallback = this.k;
        switch (i) {
            case 8:
                long j2 = foVar.e;
                if (j2 == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (j2 != 0) {
                    s = foVar.readShort();
                    strQ0 = foVar.Q0();
                    WebSocketProtocol.a.getClass();
                    String strA = WebSocketProtocol.a(s);
                    if (strA != null) {
                        throw new ProtocolException(strA);
                    }
                } else {
                    s = 1005;
                    strQ0 = "";
                }
                frameCallback.h(s, strQ0);
                this.n = true;
                return;
            case 9:
                frameCallback.g(foVar.v(foVar.e));
                return;
            case 10:
                frameCallback.f(foVar.v(foVar.e));
                return;
            default:
                int i2 = this.o;
                byte[] bArr2 = Util.a;
                String hexString = Integer.toHexString(i2);
                v03.i(hexString, "toHexString(this)");
                throw new ProtocolException("Unknown control opcode: ".concat(hexString));
        }
    }

    public final void c() {
        boolean z;
        if (this.n) {
            throw new IOException("closed");
        }
        ko koVar = this.e;
        long jH = koVar.e().h();
        koVar.e().b();
        try {
            byte b = koVar.readByte();
            byte[] bArr = Util.a;
            koVar.e().g(jH, TimeUnit.NANOSECONDS);
            int i = b & 15;
            this.o = i;
            boolean z2 = (b & 128) != 0;
            this.q = z2;
            boolean z3 = (b & 8) != 0;
            this.r = z3;
            if (z3 && !z2) {
                throw new ProtocolException("Control frames must be final.");
            }
            boolean z4 = (b & 64) != 0;
            if (i == 1 || i == 2) {
                if (!z4) {
                    z = false;
                } else {
                    if (!this.l) {
                        throw new ProtocolException("Unexpected rsv1 flag");
                    }
                    z = true;
                }
                this.s = z;
            } else if (z4) {
                throw new ProtocolException("Unexpected rsv1 flag");
            }
            if ((b & 32) != 0) {
                throw new ProtocolException("Unexpected rsv2 flag");
            }
            if ((b & 16) != 0) {
                throw new ProtocolException("Unexpected rsv3 flag");
            }
            byte b2 = koVar.readByte();
            boolean z5 = (b2 & 128) != 0;
            boolean z6 = this.c;
            if (z5 == z6) {
                throw new ProtocolException(z6 ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
            }
            long j = b2 & 127;
            this.f103p = j;
            if (j == 126) {
                this.f103p = koVar.readShort() & 65535;
            } else if (j == 127) {
                long j2 = koVar.readLong();
                this.f103p = j2;
                if (j2 < 0) {
                    StringBuilder sb = new StringBuilder("Frame length 0x");
                    String hexString = Long.toHexString(this.f103p);
                    v03.i(hexString, "toHexString(this)");
                    sb.append(hexString);
                    sb.append(" > 0x7FFFFFFFFFFFFFFF");
                    throw new ProtocolException(sb.toString());
                }
            }
            if (this.r && this.f103p > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            }
            if (z5) {
                byte[] bArr2 = this.w;
                v03.h(bArr2);
                koVar.readFully(bArr2);
            }
        } catch (Throwable th) {
            koVar.e().g(jH, TimeUnit.NANOSECONDS);
            throw th;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        MessageInflater messageInflater = this.v;
        if (messageInflater != null) {
            messageInflater.close();
        }
    }
}
