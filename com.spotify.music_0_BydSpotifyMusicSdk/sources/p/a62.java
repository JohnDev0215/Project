package p;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class a62 extends kp {
    public final boolean a;

    public a62(boolean z) {
        this.a = z;
    }

    @Override // p.kp
    public final lp a(Type type, Annotation[] annotationArr) {
        Type typeW;
        boolean z;
        boolean z2;
        Class clsX = bk0.x(type);
        if (clsX == Completable.class) {
            return new z52(Void.class, this.a, false, true, false, false, false, true);
        }
        boolean z3 = clsX == Flowable.class;
        boolean z4 = clsX == Single.class;
        boolean z5 = clsX == Maybe.class;
        if (clsX != Observable.class && !z3 && !z4 && !z5) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            String str = !z3 ? !z4 ? z5 ? "Maybe" : "Observable" : "Single" : "Flowable";
            throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
        }
        Type typeW2 = bk0.w(0, (ParameterizedType) type);
        Class clsX2 = bk0.x(typeW2);
        if (clsX2 == u42.class) {
            if (!(typeW2 instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
            }
            typeW = bk0.w(0, (ParameterizedType) typeW2);
            z2 = false;
            z = false;
        } else if (clsX2 != x42.class) {
            typeW = typeW2;
            z = true;
            z2 = false;
        } else {
            if (!(typeW2 instanceof ParameterizedType)) {
                throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
            }
            typeW = bk0.w(0, (ParameterizedType) typeW2);
            z2 = true;
            z = false;
        }
        return new z52(typeW, this.a, z2, z, z3, z4, z5, false);
    }
}
