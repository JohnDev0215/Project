package b.a.a.m.b;

import b.a.a.k.i0;
import b.a.a.k.t0;
import java.io.IOException;
import java.lang.reflect.Type;
import springfox.documentation.spring.web.json.Json;

/* JADX INFO: compiled from: SwaggerJsonSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f424a = new a();

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        i0Var.y().write(((Json) obj).value());
    }
}
