package b7;

import c7.InterfaceC1035a;
import java.util.logging.Level;

/* JADX INFO: renamed from: b7.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1002c implements InterfaceC1035a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12279a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ i f12280b;

    public /* synthetic */ C1002c(i iVar, int i7) {
        this.f12279a = i7;
        this.f12280b = iVar;
    }

    @Override // c7.InterfaceC1035a
    public final void q(Object... objArr) {
        switch (this.f12279a) {
            case 0:
                this.f12280b.N0("transport", objArr);
                break;
            case 1:
                Object obj = objArr[0];
                try {
                    boolean z6 = obj instanceof String;
                    i iVar = this.f12280b;
                    if (z6) {
                        iVar.f12289A.C((String) obj);
                    } else if (obj instanceof byte[]) {
                        iVar.f12289A.D((byte[]) obj);
                    }
                } catch (j7.b e10) {
                    i.f12288D.fine("error while decoding the packet: " + e10.getMessage());
                    return;
                }
                break;
            case 2:
                Exception exc = (Exception) objArr[0];
                i iVar2 = this.f12280b;
                iVar2.getClass();
                i.f12288D.log(Level.FINE, "error", (Throwable) exc);
                iVar2.N0("error", exc);
                break;
            default:
                String str = (String) objArr[0];
                i iVar3 = this.f12280b;
                iVar3.getClass();
                i.f12288D.fine("onclose");
                iVar3.a1();
                iVar3.f12295s.d = 0;
                iVar3.f12290C = 1;
                iVar3.N0("close", str);
                if (iVar3.f12291n && !iVar3.o) {
                    iVar3.c1();
                    break;
                }
                break;
        }
    }
}
