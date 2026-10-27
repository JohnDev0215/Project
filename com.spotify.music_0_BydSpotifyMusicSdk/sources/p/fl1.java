package p;

import com.spotify.connectivity.productstate.RxProductState;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class fl1 extends g00 {
    public final dl1 a;

    public fl1(com.squareup.moshi.o oVar) {
        this.a = new dl1(oVar);
    }

    @Override // p.g00
    public final h00 a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, e52 e52Var) {
        v03.j(type, RxProductState.Keys.KEY_TYPE);
        v03.j(annotationArr2, "methodAnnotations");
        v03.j(e52Var, "retrofit");
        if (p02.m(type)) {
            return this.a.a(type, annotationArr, annotationArr2, e52Var);
        }
        return null;
    }

    @Override // p.g00
    public final h00 b(Type type, Annotation[] annotationArr, e52 e52Var) {
        v03.j(type, RxProductState.Keys.KEY_TYPE);
        v03.j(annotationArr, "annotations");
        v03.j(e52Var, "retrofit");
        if (p02.m(type)) {
            return this.a.b(type, annotationArr, e52Var);
        }
        return null;
    }
}
