package c2;

import com.alibaba.fastjson.serializer.SerializerFeature;
import h2.d1;
import h2.g0;
import h2.i0;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: JSONPObject.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f2299a = "/**/";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f2300b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<Object> f2301c = new ArrayList();

    public c() {
    }

    @Override // h2.g0
    public void a(i0 i0Var, Object obj, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        int i9 = SerializerFeature.BrowserSecure.mask;
        if ((i8 & i9) != 0 || d1Var.r(i9)) {
            d1Var.write(f2299a);
        }
        d1Var.write(this.f2300b);
        d1Var.write(40);
        for (int i10 = 0; i10 < this.f2301c.size(); i10++) {
            if (i10 != 0) {
                d1Var.write(44);
            }
            i0Var.H(this.f2301c.get(i10));
        }
        d1Var.write(41);
    }

    public void b(Object obj) {
        this.f2301c.add(obj);
    }

    public String toString() {
        return a.toJSONString(this);
    }

    public c(String str) {
        this.f2300b = str;
    }
}
