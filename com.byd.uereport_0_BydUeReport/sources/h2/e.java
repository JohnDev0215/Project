package h2;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: AppendableSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f5230a = new e();

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        if (obj == null) {
            i0Var.f5241k.c0(SerializerFeature.WriteNullStringAsEmpty);
        } else {
            i0Var.I(obj.toString());
        }
    }
}
