package p;

import com.spotify.connectivity.productstate.RxProductState;
import com.squareup.moshi.f;
import java.lang.reflect.Type;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class m6 implements f.e {
    @Override // com.squareup.moshi.f.e
    public final com.squareup.moshi.f a(Type type, Set set, com.squareup.moshi.o oVar) {
        v03.j(type, RxProductState.Keys.KEY_TYPE);
        v03.j(set, "annotations");
        v03.j(oVar, "moshi");
        Class<?> clsG = zo2.g(type);
        if (clsG.isAnnotationPresent(l6.class) || qr2.class.isAssignableFrom(clsG)) {
            return oVar.i(this, type, set).serializeNulls();
        }
        return null;
    }
}
