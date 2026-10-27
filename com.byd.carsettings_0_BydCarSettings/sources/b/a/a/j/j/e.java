package b.a.a.j.j;

import java.lang.reflect.Type;

/* JADX INFO: compiled from: ContextObjectDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e implements t {
    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        return (T) f(aVar, type, obj, null, 0);
    }

    public abstract <T> T f(b.a.a.j.a aVar, Type type, Object obj, String str, int i);
}
