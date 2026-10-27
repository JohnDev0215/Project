package p;

import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.playerlimited.player.moshi.MoshiRootName;
import com.squareup.moshi.f;
import java.lang.reflect.Type;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class jl1 implements f.e {
    @Override // com.squareup.moshi.f.e
    public final com.squareup.moshi.f a(Type type, Set set, com.squareup.moshi.o oVar) {
        MoshiRootName moshiRootName;
        v03.j(type, RxProductState.Keys.KEY_TYPE);
        v03.j(set, "annotations");
        v03.j(oVar, "moshi");
        try {
            moshiRootName = (MoshiRootName) zo2.g(type).getAnnotation(MoshiRootName.class);
        } catch (NoClassDefFoundError e) {
            Logger.c(e, "Error reading annotation for %s", type);
            moshiRootName = null;
        }
        if (moshiRootName == null) {
            return null;
        }
        com.squareup.moshi.f fVarI = oVar.i(this, type, set);
        v03.i(fVarI, "delegate");
        return new il1(fVarI, moshiRootName.value()).nullSafe();
    }
}
