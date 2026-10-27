package p;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public final class to extends g00 {
    public boolean a;

    @Override // p.g00
    public final h00 a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, e52 e52Var) {
        if (RequestBody.class.isAssignableFrom(bk0.x(type))) {
            return u11.m;
        }
        return null;
    }

    @Override // p.g00
    public final h00 b(Type type, Annotation[] annotationArr, e52 e52Var) {
        if (type == ResponseBody.class) {
            return bk0.F(annotationArr, hg2.class) ? po.c : zb0.e;
        }
        if (type == Void.class) {
            return so.c;
        }
        if (!this.a || type != lq2.class) {
            return null;
        }
        try {
            return ro.c;
        } catch (NoClassDefFoundError unused) {
            this.a = false;
            return null;
        }
    }
}
