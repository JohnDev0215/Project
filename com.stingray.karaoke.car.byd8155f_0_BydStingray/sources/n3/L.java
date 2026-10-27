package n3;

import android.os.Bundle;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class L extends M {
    @Override // n3.M
    public final Object a(String str, Bundle bundle) {
        Object objF = AbstractC1347d.f(bundle, "bundle", str, "key", str);
        A7.m.d(objF, "null cannot be cast to non-null type kotlin.Float");
        return (Float) objF;
    }

    @Override // n3.M
    public final String b() {
        return "float";
    }

    @Override // n3.M
    public final Object d(String str) {
        return Float.valueOf(Float.parseFloat(str));
    }

    @Override // n3.M
    public final void e(Bundle bundle, String str, Object obj) {
        float fFloatValue = ((Number) obj).floatValue();
        A7.m.f(str, "key");
        bundle.putFloat(str, fFloatValue);
    }
}
