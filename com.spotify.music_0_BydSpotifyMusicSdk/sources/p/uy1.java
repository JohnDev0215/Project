package p;

import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped$Reason;
import com.google.firebase.encoders.EncodingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class uy1 implements zo1 {
    public static final Charset f = Charset.forName("UTF-8");
    public static final wt0 g;
    public static final wt0 h;
    public static final d71 i;
    public OutputStream a;
    public final Map b;
    public final Map c;
    public final yo1 d;
    public final vy1 e = new vy1(this);

    static {
        ay2 ay2VarA = wt0.a("key");
        j6 j6VarC = j6.c();
        j6VarC.c = 1;
        ay2VarA.e(j6VarC.b());
        g = ay2VarA.c();
        ay2 ay2VarA2 = wt0.a("value");
        j6 j6VarC2 = j6.c();
        j6VarC2.c = 2;
        ay2VarA2.e(j6VarC2.b());
        h = ay2VarA2.c();
        i = new d71(1);
    }

    public uy1(ByteArrayOutputStream byteArrayOutputStream, Map map, Map map2, yo1 yo1Var) {
        this.a = byteArrayOutputStream;
        this.b = map;
        this.c = map2;
        this.d = yo1Var;
    }

    public static int i(wt0 wt0Var) {
        sy1 sy1Var = (sy1) ((Annotation) wt0Var.b.get(sy1.class));
        if (sy1Var != null) {
            return ((vc) sy1Var).a;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    @Override // p.zo1
    public final zo1 a(wt0 wt0Var, int i2) {
        d(wt0Var, i2, true);
        return this;
    }

    @Override // p.zo1
    public final zo1 b(wt0 wt0Var, boolean z) {
        d(wt0Var, z ? 1 : 0, true);
        return this;
    }

    @Override // p.zo1
    public final zo1 c(wt0 wt0Var, long j) throws IOException {
        e(wt0Var, j, true);
        return this;
    }

    public final void d(wt0 wt0Var, int i2, boolean z) {
        if (z && i2 == 0) {
            return;
        }
        sy1 sy1Var = (sy1) ((Annotation) wt0Var.b.get(sy1.class));
        if (sy1Var == null) {
            throw new EncodingException("Field has no @Protobuf config");
        }
        vc vcVar = (vc) sy1Var;
        int iOrdinal = vcVar.b.ordinal();
        int i3 = vcVar.a;
        if (iOrdinal == 0) {
            j(i3 << 3);
            j(i2);
        } else if (iOrdinal == 1) {
            j(i3 << 3);
            j((i2 << 1) ^ (i2 >> 31));
        } else {
            if (iOrdinal != 2) {
                return;
            }
            j((i3 << 3) | 5);
            this.a.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(i2).array());
        }
    }

    public final void e(wt0 wt0Var, long j, boolean z) throws IOException {
        if (z && j == 0) {
            return;
        }
        sy1 sy1Var = (sy1) ((Annotation) wt0Var.b.get(sy1.class));
        if (sy1Var == null) {
            throw new EncodingException("Field has no @Protobuf config");
        }
        vc vcVar = (vc) sy1Var;
        int iOrdinal = vcVar.b.ordinal();
        int i2 = vcVar.a;
        if (iOrdinal == 0) {
            j(i2 << 3);
            k(j);
        } else if (iOrdinal == 1) {
            j(i2 << 3);
            k((j >> 63) ^ (j << 1));
        } else {
            if (iOrdinal != 2) {
                return;
            }
            j((i2 << 3) | 1);
            this.a.write(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(j).array());
        }
    }

    @Override // p.zo1
    public final zo1 f(wt0 wt0Var, Object obj) {
        g(wt0Var, obj, true);
        return this;
    }

    public final void g(wt0 wt0Var, Object obj, boolean z) {
        if (obj == null) {
            return;
        }
        if (obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj;
            if (z && charSequence.length() == 0) {
                return;
            }
            j((i(wt0Var) << 3) | 2);
            byte[] bytes = charSequence.toString().getBytes(f);
            j(bytes.length);
            this.a.write(bytes);
            return;
        }
        if (obj instanceof Collection) {
            Iterator it = ((Collection) obj).iterator();
            while (it.hasNext()) {
                g(wt0Var, it.next(), false);
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                h(i, wt0Var, (Map.Entry) it2.next(), false);
            }
            return;
        }
        if (obj instanceof Double) {
            double dDoubleValue = ((Double) obj).doubleValue();
            if (z && dDoubleValue == 0.0d) {
                return;
            }
            j((i(wt0Var) << 3) | 1);
            this.a.write(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(dDoubleValue).array());
            return;
        }
        if (obj instanceof Float) {
            float fFloatValue = ((Float) obj).floatValue();
            if (z && fFloatValue == 0.0f) {
                return;
            }
            j((i(wt0Var) << 3) | 5);
            this.a.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(fFloatValue).array());
            return;
        }
        if (obj instanceof Number) {
            e(wt0Var, ((Number) obj).longValue(), z);
            return;
        }
        if (obj instanceof Boolean) {
            d(wt0Var, ((Boolean) obj).booleanValue() ? 1 : 0, z);
            return;
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            if (z && bArr.length == 0) {
                return;
            }
            j((i(wt0Var) << 3) | 2);
            j(bArr.length);
            this.a.write(bArr);
            return;
        }
        yo1 yo1Var = (yo1) this.b.get(obj.getClass());
        if (yo1Var != null) {
            h(yo1Var, wt0Var, obj, z);
            return;
        }
        rs2 rs2Var = (rs2) this.c.get(obj.getClass());
        if (rs2Var != null) {
            vy1 vy1Var = this.e;
            vy1Var.a = false;
            vy1Var.c = wt0Var;
            vy1Var.b = z;
            rs2Var.a(obj, vy1Var);
            return;
        }
        if (obj instanceof py1) {
            d(wt0Var, ((LogEventDropped$Reason) ((py1) obj)).a(), true);
        } else if (obj instanceof Enum) {
            d(wt0Var, ((Enum) obj).ordinal(), true);
        } else {
            h(this.d, wt0Var, obj, z);
        }
    }

    public final void h(yo1 yo1Var, wt0 wt0Var, Object obj, boolean z) throws IOException {
        d91 d91Var = new d91();
        d91Var.c = 0L;
        try {
            OutputStream outputStream = this.a;
            this.a = d91Var;
            try {
                yo1Var.a(obj, this);
                this.a = outputStream;
                long j = d91Var.c;
                d91Var.close();
                if (z && j == 0) {
                    return;
                }
                j((i(wt0Var) << 3) | 2);
                k(j);
                yo1Var.a(obj, this);
            } catch (Throwable th) {
                this.a = outputStream;
                throw th;
            }
        } catch (Throwable th2) {
            try {
                d91Var.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }

    public final void j(int i2) throws IOException {
        while ((i2 & (-128)) != 0) {
            this.a.write((i2 & 127) | 128);
            i2 >>>= 7;
        }
        this.a.write(i2 & 127);
    }

    public final void k(long j) throws IOException {
        while (((-128) & j) != 0) {
            this.a.write((((int) j) & 127) | 128);
            j >>>= 7;
        }
        this.a.write(((int) j) & 127);
    }
}
