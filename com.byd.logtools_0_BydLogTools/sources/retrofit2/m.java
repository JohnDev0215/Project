package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import javax.annotation.Nullable;
import okhttp3.ResponseBody;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import retrofit2.h;

/* JADX INFO: compiled from: OptionalConverterFactory.java */
/* JADX INFO: loaded from: classes.dex */
@IgnoreJRERequirement
final class m extends h.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final h.a f5241a = new m();

    /* JADX INFO: compiled from: OptionalConverterFactory.java */
    @IgnoreJRERequirement
    static final class a<T> implements h<ResponseBody, Optional<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final h<ResponseBody, T> f5242a;

        a(h<ResponseBody, T> hVar) {
            this.f5242a = hVar;
        }

        @Override // retrofit2.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Optional<T> a(ResponseBody responseBody) throws IOException {
            return Optional.ofNullable(this.f5242a.a(responseBody));
        }
    }

    m() {
    }

    @Override // retrofit2.h.a
    @Nullable
    public h<ResponseBody, ?> d(Type type, Annotation[] annotationArr, s sVar) {
        if (h.a.b(type) != Optional.class) {
            return null;
        }
        return new a(sVar.h(h.a.a(0, (ParameterizedType) type), annotationArr));
    }
}
