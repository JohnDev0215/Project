package k3;

import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import h2.AbstractC1346c;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: renamed from: k3.e1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1638e1 implements l1 {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ l1 f17941m;

    public /* synthetic */ C1638e1(l1 l1Var, int i7) {
        this.l = i7;
        this.f17941m = l1Var;
    }

    @Override // k3.l1
    public final Object a(I0 i02, final C1679y0 c1679y0, final int i7) {
        switch (this.l) {
            case 0:
                if (i02 != null) {
                    throw new ClassCastException();
                }
                final int i10 = 0;
                m1.q1(null, c1679y0, i7, this.f17941m, new h2.g() { // from class: k3.f1
                    @Override // h2.g
                    public final void accept(Object obj) {
                        r rVar;
                        C1 c1;
                        int i11 = i7;
                        C1679y0 c1679y02 = c1679y0;
                        switch (i10) {
                            case 0:
                                try {
                                    rVar = (r) ((h5.y) obj).get();
                                    AbstractC1346c.h(rVar, "LibraryResult must not be null");
                                } catch (InterruptedException | ExecutionException e10) {
                                    AbstractC1346c.A("MediaSessionStub", "Library operation failed", e10);
                                    String str = r.f18093g;
                                    A1 a12 = new A1("no error message provided", -1, Bundle.EMPTY);
                                    rVar = new r(a12.f17717a, SystemClock.elapsedRealtime(), null, a12, null, 4);
                                } catch (CancellationException e11) {
                                    AbstractC1346c.A("MediaSessionStub", "Library operation cancelled", e11);
                                    String str2 = r.f18093g;
                                    A1 a13 = new A1("no error message provided", 1, Bundle.EMPTY);
                                    rVar = new r(a13.f17717a, SystemClock.elapsedRealtime(), null, a13, null, 4);
                                }
                                try {
                                    InterfaceC1677x0 interfaceC1677x0 = c1679y02.d;
                                    AbstractC1346c.k(interfaceC1677x0);
                                    interfaceC1677x0.j(i11, rVar);
                                } catch (RemoteException e12) {
                                    AbstractC1346c.A("MediaSessionStub", "Failed to send result to browser " + c1679y02, e12);
                                    return;
                                }
                                break;
                            default:
                                try {
                                    c1 = (C1) ((h5.y) obj).get();
                                    AbstractC1346c.h(c1, "SessionResult must not be null");
                                } catch (InterruptedException | ExecutionException e13) {
                                    AbstractC1346c.A("MediaSessionStub", "Session operation failed", e13);
                                    c1 = new C1(e13.getCause() instanceof UnsupportedOperationException ? -6 : -1);
                                } catch (CancellationException e14) {
                                    AbstractC1346c.A("MediaSessionStub", "Session operation cancelled", e14);
                                    c1 = new C1(1);
                                }
                                m1.u1(c1679y02, i11, c1);
                                break;
                        }
                    }
                });
                throw null;
            default:
                final int i11 = 1;
                return m1.q1(i02, c1679y0, i7, this.f17941m, new h2.g() { // from class: k3.f1
                    @Override // h2.g
                    public final void accept(Object obj) {
                        r rVar;
                        C1 c1;
                        int i112 = i7;
                        C1679y0 c1679y02 = c1679y0;
                        switch (i11) {
                            case 0:
                                try {
                                    rVar = (r) ((h5.y) obj).get();
                                    AbstractC1346c.h(rVar, "LibraryResult must not be null");
                                } catch (InterruptedException | ExecutionException e10) {
                                    AbstractC1346c.A("MediaSessionStub", "Library operation failed", e10);
                                    String str = r.f18093g;
                                    A1 a12 = new A1("no error message provided", -1, Bundle.EMPTY);
                                    rVar = new r(a12.f17717a, SystemClock.elapsedRealtime(), null, a12, null, 4);
                                } catch (CancellationException e11) {
                                    AbstractC1346c.A("MediaSessionStub", "Library operation cancelled", e11);
                                    String str2 = r.f18093g;
                                    A1 a13 = new A1("no error message provided", 1, Bundle.EMPTY);
                                    rVar = new r(a13.f17717a, SystemClock.elapsedRealtime(), null, a13, null, 4);
                                }
                                try {
                                    InterfaceC1677x0 interfaceC1677x0 = c1679y02.d;
                                    AbstractC1346c.k(interfaceC1677x0);
                                    interfaceC1677x0.j(i112, rVar);
                                } catch (RemoteException e12) {
                                    AbstractC1346c.A("MediaSessionStub", "Failed to send result to browser " + c1679y02, e12);
                                    return;
                                }
                                break;
                            default:
                                try {
                                    c1 = (C1) ((h5.y) obj).get();
                                    AbstractC1346c.h(c1, "SessionResult must not be null");
                                } catch (InterruptedException | ExecutionException e13) {
                                    AbstractC1346c.A("MediaSessionStub", "Session operation failed", e13);
                                    c1 = new C1(e13.getCause() instanceof UnsupportedOperationException ? -6 : -1);
                                } catch (CancellationException e14) {
                                    AbstractC1346c.A("MediaSessionStub", "Session operation cancelled", e14);
                                    c1 = new C1(1);
                                }
                                m1.u1(c1679y02, i112, c1);
                                break;
                        }
                    }
                });
        }
    }
}
