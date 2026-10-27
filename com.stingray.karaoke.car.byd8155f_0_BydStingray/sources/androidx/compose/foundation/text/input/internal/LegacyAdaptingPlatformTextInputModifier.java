package androidx.compose.foundation.text.input.internal;

import A7.m;
import E0.V;
import K.C0331b0;
import M.f;
import M.v;
import O.K;
import g0.n;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/text/input/internal/LegacyAdaptingPlatformTextInputModifier;", "LE0/V;", "LM/v;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class LegacyAdaptingPlatformTextInputModifier extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f11507a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0331b0 f11508b;
    public final K c;

    public LegacyAdaptingPlatformTextInputModifier(f fVar, C0331b0 c0331b0, K k10) {
        this.f11507a = fVar;
        this.f11508b = c0331b0;
        this.c = k10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LegacyAdaptingPlatformTextInputModifier)) {
            return false;
        }
        LegacyAdaptingPlatformTextInputModifier legacyAdaptingPlatformTextInputModifier = (LegacyAdaptingPlatformTextInputModifier) obj;
        return m.a(this.f11507a, legacyAdaptingPlatformTextInputModifier.f11507a) && m.a(this.f11508b, legacyAdaptingPlatformTextInputModifier.f11508b) && m.a(this.c, legacyAdaptingPlatformTextInputModifier.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.f11508b.hashCode() + (this.f11507a.hashCode() * 31)) * 31);
    }

    @Override // E0.V
    public final n i() {
        K k10 = this.c;
        return new v(this.f11507a, this.f11508b, k10);
    }

    @Override // E0.V
    public final void l(n nVar) throws IllegalAccessException, InvocationTargetException {
        v vVar = (v) nVar;
        if (vVar.f14695x) {
            vVar.f4155y.f();
            vVar.f4155y.k(vVar);
        }
        f fVar = this.f11507a;
        vVar.f4155y = fVar;
        if (vVar.f14695x) {
            if (fVar.f4137a != null) {
                throw new IllegalStateException("Expected textInputModifierNode to be null");
            }
            fVar.f4137a = vVar;
        }
        vVar.f4156z = this.f11508b;
        vVar.f4154A = this.c;
    }

    public final String toString() {
        return "LegacyAdaptingPlatformTextInputModifier(serviceAdapter=" + this.f11507a + ", legacyTextFieldState=" + this.f11508b + ", textFieldSelectionManager=" + this.c + ')';
    }
}
