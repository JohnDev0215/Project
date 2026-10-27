package k2;

import h2.i0;
import h2.t0;
import java.io.IOException;
import java.lang.reflect.Type;
import springfox.documentation.spring.web.json.Json;

/* JADX INFO: compiled from: SwaggerJsonSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5844a = new a();

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        i0Var.y().write(((Json) obj).value());
    }
}
