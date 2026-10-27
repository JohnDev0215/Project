package B5;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public final class w extends B3.m {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f737m;

    public w() {
        this.l = 0;
        this.f737m = new LinkedList();
    }

    private final void z() {
    }

    @Override // B3.m
    public final void b(byte[] bArr) {
        boolean z6;
        int iV;
        switch (this.l) {
            case 0:
                ((LinkedList) this.f737m).add(bArr);
                return;
            default:
                D d = (D) this.f737m;
                int length = bArr.length;
                synchronized (d) {
                    if (length >= 0) {
                        if (length <= bArr.length) {
                            d.g(length);
                            synchronized (d) {
                                z6 = d.f650n == 0;
                            }
                            return;
                        }
                    }
                    throw new IndexOutOfBoundsException();
                }
                if (z6) {
                    iV = 16;
                } else {
                    B b10 = d.f651p;
                    iV = d.v(b10.f643a + 4 + b10.f644b);
                }
                B b11 = new B(iV, length);
                D.z(0, length, d.f652q);
                d.q(iV, 4, d.f652q);
                d.q(iV + 4, length, bArr);
                d.y(d.f649m, d.f650n + 1, z6 ? iV : d.o.f643a, iV);
                d.f651p = b11;
                d.f650n++;
                if (z6) {
                    d.o = b11;
                }
                return;
        }
    }

    @Override // B3.m, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        switch (this.l) {
            case 0:
                break;
            default:
                ((D) this.f737m).close();
                break;
        }
    }

    @Override // B3.m
    public final void k(A2.g gVar) {
        switch (this.l) {
            case 0:
                int i7 = 0;
                while (true) {
                    LinkedList linkedList = (LinkedList) this.f737m;
                    if (i7 < linkedList.size()) {
                        byte[] bArr = (byte[]) linkedList.get(i7);
                        if (gVar.a(new ByteArrayInputStream(bArr), bArr.length)) {
                            i7++;
                        }
                    }
                    break;
                }
                break;
            default:
                ((D) this.f737m).i(gVar);
                break;
        }
    }

    @Override // B3.m
    public final void o(int i7) throws IOException {
        switch (this.l) {
            case 0:
                for (int i10 = 0; i10 < i7; i10++) {
                    ((LinkedList) this.f737m).remove();
                }
                return;
            default:
                try {
                    ((D) this.f737m).m(i7);
                    return;
                } catch (ArrayIndexOutOfBoundsException e10) {
                    throw new IOException(e10);
                }
        }
    }

    @Override // B3.m
    public final int q() {
        int i7;
        switch (this.l) {
            case 0:
                return ((LinkedList) this.f737m).size();
            default:
                D d = (D) this.f737m;
                synchronized (d) {
                    i7 = d.f650n;
                }
                return i7;
        }
    }

    public w(D d) {
        this.l = 1;
        this.f737m = d;
    }
}
