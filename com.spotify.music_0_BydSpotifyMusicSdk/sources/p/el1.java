package p;

import com.squareup.moshi.f;
import com.squareup.moshi.o;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class el1 {
    public static final com.squareup.moshi.o a(Set set) {
        o.a aVar = new o.a();
        aVar.a(new m6());
        if (set != null) {
            for (Object obj : set) {
                if (obj instanceof f.e) {
                    aVar.a((f.e) obj);
                } else {
                    aVar.b(obj);
                }
            }
        }
        com.squareup.moshi.o oVarD = aVar.d();
        v03.i(oVarD, "builder.build()");
        return oVarD;
    }
}
