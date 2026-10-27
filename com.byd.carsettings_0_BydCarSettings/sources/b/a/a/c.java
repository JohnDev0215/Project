package b.a.a;

import b.a.a.k.d1;
import b.a.a.k.g0;
import b.a.a.k.i0;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: JSONPObject.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f176a = "/**/";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f177b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<Object> f178c = new ArrayList();

    public c() {
    }

    @Override // b.a.a.k.g0
    public void a(i0 i0Var, Object obj, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        int i2 = SerializerFeature.BrowserSecure.mask;
        if ((i & i2) != 0 || d1Var.k(i2)) {
            d1Var.write(f176a);
        }
        d1Var.write(this.f177b);
        d1Var.write(40);
        for (int i3 = 0; i3 < this.f178c.size(); i3++) {
            if (i3 != 0) {
                d1Var.write(44);
            }
            i0Var.H(this.f178c.get(i3));
        }
        d1Var.write(41);
    }

    public void b(Object obj) {
        this.f178c.add(obj);
    }

    public String toString() {
        return a.toJSONString(this);
    }

    public c(String str) {
        this.f177b = str;
    }
}
