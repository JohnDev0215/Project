package g2;

import com.alibaba.fastjson.JSONException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: PropertyProcessableDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class x implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class<w> f5074a;

    public x(Class<w> cls) {
        this.f5074a = cls;
    }

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        try {
            return (T) aVar.O(this.f5074a.newInstance(), obj);
        } catch (Exception unused) {
            throw new JSONException("craete instance error");
        }
    }

    @Override // g2.t
    public int e() {
        return 12;
    }
}
