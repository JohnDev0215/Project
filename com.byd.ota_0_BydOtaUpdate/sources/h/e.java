package h;

import e.b0;
import e.z;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Converter.java */
/* JADX INFO: loaded from: classes.dex */
public interface e<F, T> {

    /* JADX INFO: compiled from: Converter.java */
    public static abstract class a {
        @Nullable
        public e<?, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, m mVar) {
            return null;
        }

        @Nullable
        public e<b0, ?> b(Type type, Annotation[] annotationArr, m mVar) {
            return null;
        }

        @Nullable
        public e<?, String> c(Type type, Annotation[] annotationArr, m mVar) {
            return null;
        }
    }

    T a(F f2) throws IOException;
}
