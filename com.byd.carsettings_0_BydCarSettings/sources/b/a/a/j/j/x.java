package b.a.a.j.j;

import com.alibaba.fastjson.JSONException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: PropertyProcessableDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class x implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class<w> f301a;

    public x(Class<w> cls) {
        this.f301a = cls;
    }

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        try {
            return (T) aVar.A(this.f301a.newInstance(), obj);
        } catch (Exception unused) {
            throw new JSONException("craete instance error");
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 12;
    }
}
