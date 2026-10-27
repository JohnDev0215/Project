package p;

import java.lang.reflect.Method;
import kotlin.KotlinNullPointerException;
import retrofit2.HttpException;

/* JADX INFO: loaded from: classes.dex */
public final class p81 implements pp {
    public final /* synthetic */ int c;
    public final /* synthetic */ yp e;

    public /* synthetic */ p81(zp zpVar, int i) {
        this.c = i;
        this.e = zpVar;
    }

    @Override // p.pp
    public final void a(jp jpVar, Throwable th) {
        int i = this.c;
        yp ypVar = this.e;
        switch (i) {
            case 0:
                v03.k(jpVar, "call");
                v03.k(th, "t");
                ((zp) ypVar).k(kotlin.a.b(th));
                break;
            case 1:
                v03.k(jpVar, "call");
                v03.k(th, "t");
                ((zp) ypVar).k(kotlin.a.b(th));
                break;
            default:
                v03.k(jpVar, "call");
                v03.k(th, "t");
                ((zp) ypVar).k(kotlin.a.b(th));
                break;
        }
    }

    @Override // p.pp
    public final void b(jp jpVar, u42 u42Var) {
        int i = this.c;
        yp ypVar = this.e;
        switch (i) {
            case 0:
                v03.k(jpVar, "call");
                v03.k(u42Var, "response");
                if (!u42Var.a.i()) {
                    ((zp) ypVar).k(kotlin.a.b(new HttpException(u42Var)));
                    return;
                }
                Object obj = u42Var.b;
                if (obj != null) {
                    ((zp) ypVar).k(obj);
                    return;
                }
                Object objCast = d61.class.cast(jpVar.c().e.get(d61.class));
                if (objCast == null) {
                    KotlinNullPointerException kotlinNullPointerException = new KotlinNullPointerException();
                    v03.I(v03.class.getName(), kotlinNullPointerException);
                    throw kotlinNullPointerException;
                }
                StringBuilder sb = new StringBuilder("Response from ");
                Method method = ((d61) objCast).a;
                v03.f(method, "method");
                Class<?> declaringClass = method.getDeclaringClass();
                v03.f(declaringClass, "method.declaringClass");
                sb.append(declaringClass.getName());
                sb.append('.');
                sb.append(method.getName());
                sb.append(" was null but response body type was declared as non-null");
                ((zp) ypVar).k(kotlin.a.b(new KotlinNullPointerException(sb.toString())));
                return;
            case 1:
                v03.k(jpVar, "call");
                v03.k(u42Var, "response");
                if (u42Var.a.i()) {
                    ((zp) ypVar).k(u42Var.b);
                    return;
                } else {
                    ((zp) ypVar).k(kotlin.a.b(new HttpException(u42Var)));
                    return;
                }
            default:
                v03.k(jpVar, "call");
                v03.k(u42Var, "response");
                ((zp) ypVar).k(u42Var);
                return;
        }
    }
}
