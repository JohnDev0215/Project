package b.a.a.k;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: AppendableSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f360a = new e();

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        if (obj == null) {
            i0Var.k.M(SerializerFeature.WriteNullStringAsEmpty);
        } else {
            i0Var.I(obj.toString());
        }
    }
}
