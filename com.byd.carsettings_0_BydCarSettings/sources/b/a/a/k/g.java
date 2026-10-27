package b.a.a.k;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: compiled from: AtomicCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f367a = new g();

    /* JADX WARN: Type inference failed for: r4v2, types: [T, java.util.concurrent.atomic.AtomicLongArray] */
    /* JADX WARN: Type inference failed for: r4v3, types: [T, java.util.concurrent.atomic.AtomicIntegerArray] */
    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        if (aVar.g.j() == 8) {
            aVar.g.F(16);
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        aVar.G(jSONArray);
        int i = 0;
        if (type == AtomicIntegerArray.class) {
            ?? r4 = (T) new AtomicIntegerArray(jSONArray.size());
            while (i < jSONArray.size()) {
                r4.set(i, jSONArray.getInteger(i).intValue());
                i++;
            }
            return r4;
        }
        ?? r42 = (T) new AtomicLongArray(jSONArray.size());
        while (i < jSONArray.size()) {
            r42.set(i, jSONArray.getLong(i).longValue());
            i++;
        }
        return r42;
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj instanceof AtomicInteger) {
            d1Var.F(((AtomicInteger) obj).get());
            return;
        }
        if (obj instanceof AtomicLong) {
            d1Var.H(((AtomicLong) obj).get());
            return;
        }
        if (obj instanceof AtomicBoolean) {
            d1Var.append(((AtomicBoolean) obj).get() ? "true" : "false");
            return;
        }
        if (obj == null) {
            d1Var.M(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        int i2 = 0;
        if (obj instanceof AtomicIntegerArray) {
            AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
            int length = atomicIntegerArray.length();
            d1Var.write(91);
            while (i2 < length) {
                int i3 = atomicIntegerArray.get(i2);
                if (i2 != 0) {
                    d1Var.write(44);
                }
                d1Var.F(i3);
                i2++;
            }
            d1Var.write(93);
            return;
        }
        AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
        int length2 = atomicLongArray.length();
        d1Var.write(91);
        while (i2 < length2) {
            long j = atomicLongArray.get(i2);
            if (i2 != 0) {
                d1Var.write(44);
            }
            d1Var.H(j);
            i2++;
        }
        d1Var.write(93);
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 14;
    }
}
