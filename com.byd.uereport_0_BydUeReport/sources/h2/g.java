package h2;

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
public class g implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f5237a = new g();

    /* JADX WARN: Type inference failed for: r4v2, types: [T, java.util.concurrent.atomic.AtomicLongArray] */
    /* JADX WARN: Type inference failed for: r4v3, types: [T, java.util.concurrent.atomic.AtomicIntegerArray] */
    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        if (aVar.f4861j.q() == 8) {
            aVar.f4861j.V(16);
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        aVar.X(jSONArray);
        int i8 = 0;
        if (type == AtomicIntegerArray.class) {
            ?? r42 = (T) new AtomicIntegerArray(jSONArray.size());
            while (i8 < jSONArray.size()) {
                r42.set(i8, jSONArray.getInteger(i8).intValue());
                i8++;
            }
            return r42;
        }
        ?? r43 = (T) new AtomicLongArray(jSONArray.size());
        while (i8 < jSONArray.size()) {
            r43.set(i8, jSONArray.getLong(i8).longValue());
            i8++;
        }
        return r43;
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj instanceof AtomicInteger) {
            d1Var.V(((AtomicInteger) obj).get());
            return;
        }
        if (obj instanceof AtomicLong) {
            d1Var.Y(((AtomicLong) obj).get());
            return;
        }
        if (obj instanceof AtomicBoolean) {
            d1Var.append(((AtomicBoolean) obj).get() ? "true" : "false");
            return;
        }
        if (obj == null) {
            d1Var.c0(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        int i9 = 0;
        if (obj instanceof AtomicIntegerArray) {
            AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
            int length = atomicIntegerArray.length();
            d1Var.write(91);
            while (i9 < length) {
                int i10 = atomicIntegerArray.get(i9);
                if (i9 != 0) {
                    d1Var.write(44);
                }
                d1Var.V(i10);
                i9++;
            }
            d1Var.write(93);
            return;
        }
        AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
        int length2 = atomicLongArray.length();
        d1Var.write(91);
        while (i9 < length2) {
            long j8 = atomicLongArray.get(i9);
            if (i9 != 0) {
                d1Var.write(44);
            }
            d1Var.Y(j8);
            i9++;
        }
        d1Var.write(93);
    }

    @Override // g2.t
    public int e() {
        return 14;
    }
}
