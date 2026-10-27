package androidx.compose.ui.input.key;

import A7.m;
import A7.o;
import E0.V;
import g0.n;
import kotlin.Metadata;
import w0.e;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/key/KeyInputElement;", "LE0/V;", "Lw0/e;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class KeyInputElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2782k f11604a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f11605b;

    /* JADX WARN: Multi-variable type inference failed */
    public KeyInputElement(InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        this.f11604a = interfaceC2782k;
        this.f11605b = (o) interfaceC2782k2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyInputElement)) {
            return false;
        }
        KeyInputElement keyInputElement = (KeyInputElement) obj;
        return m.a(this.f11604a, keyInputElement.f11604a) && m.a(this.f11605b, keyInputElement.f11605b);
    }

    public final int hashCode() {
        InterfaceC2782k interfaceC2782k = this.f11604a;
        int iHashCode = (interfaceC2782k == null ? 0 : interfaceC2782k.hashCode()) * 31;
        o oVar = this.f11605b;
        return iHashCode + (oVar != null ? oVar.hashCode() : 0);
    }

    @Override // E0.V
    public final n i() {
        e eVar = new e();
        eVar.f23168y = this.f11604a;
        eVar.f23169z = this.f11605b;
        return eVar;
    }

    @Override // E0.V
    public final void l(n nVar) {
        e eVar = (e) nVar;
        eVar.f23168y = this.f11604a;
        eVar.f23169z = this.f11605b;
    }

    public final String toString() {
        return "KeyInputElement(onKeyEvent=" + this.f11604a + ", onPreKeyEvent=" + this.f11605b + ')';
    }
}
