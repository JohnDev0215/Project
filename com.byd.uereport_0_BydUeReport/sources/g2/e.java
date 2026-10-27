package g2;

import java.lang.reflect.Type;

/* JADX INFO: compiled from: ContextObjectDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e implements t {
    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        return (T) f(aVar, type, obj, null, 0);
    }

    public abstract <T> T f(f2.a aVar, Type type, Object obj, String str, int i8);
}
