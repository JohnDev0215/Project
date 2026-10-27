package g2;

import com.alibaba.fastjson.JSONException;
import f2.a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/* JADX INFO: compiled from: DefaultFieldDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public t f5028c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5029d;

    public f(f2.h hVar, Class<?> cls, l2.c cVar) {
        super(cls, cVar);
        boolean z7 = false;
        this.f5029d = false;
        d2.b bVarD = cVar.d();
        if (bVarD != null) {
            Class<?> clsDeserializeUsing = bVarD.deserializeUsing();
            if (clsDeserializeUsing != null && clsDeserializeUsing != Void.class) {
                z7 = true;
            }
            this.f5029d = z7;
        }
    }

    @Override // g2.l
    public int b() {
        t tVar = this.f5028c;
        if (tVar != null) {
            return tVar.e();
        }
        return 2;
    }

    @Override // g2.l
    public void c(f2.a aVar, Object obj, Type type, Map<String, Object> map) {
        Object objF;
        l2.c cVar;
        int i8;
        if (this.f5028c == null) {
            j(aVar.o());
        }
        t tVarO = this.f5028c;
        Type typeH = this.f5036a.f6174i;
        if (type instanceof ParameterizedType) {
            f2.g gVarQ = aVar.q();
            if (gVarQ != null) {
                gVarQ.f4905e = type;
            }
            if (typeH != type) {
                typeH = l2.c.h(this.f5037b, type, typeH);
                tVarO = aVar.o().o(typeH);
            }
        }
        Type type2 = typeH;
        if (!(tVarO instanceof o) || (i8 = (cVar = this.f5036a).f6178m) == 0) {
            l2.c cVar2 = this.f5036a;
            String str = cVar2.f6188w;
            objF = (!(str == null && cVar2.f6178m == 0) && (tVarO instanceof e)) ? ((e) tVarO).f(aVar, type2, cVar2.f6169d, str, cVar2.f6178m) : tVarO.b(aVar, type2, cVar2.f6169d);
        } else {
            objF = ((o) tVarO).h(aVar, type2, cVar.f6169d, i8);
        }
        if ((objF instanceof byte[]) && ("gzip".equals(this.f5036a.f6188w) || "gzip,base64".equals(this.f5036a.f6188w))) {
            try {
                GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream((byte[]) objF));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    byte[] bArr = new byte[1024];
                    int i9 = gZIPInputStream.read(bArr);
                    if (i9 == -1) {
                        break;
                    } else if (i9 > 0) {
                        byteArrayOutputStream.write(bArr, 0, i9);
                    }
                }
                objF = byteArrayOutputStream.toByteArray();
            } catch (IOException e8) {
                throw new JSONException("unzip bytes error.", e8);
            }
        }
        if (aVar.F() == 1) {
            a.C0039a c0039aB = aVar.B();
            c0039aB.f4874c = this;
            c0039aB.f4875d = aVar.q();
            aVar.u0(0);
            return;
        }
        if (obj == null) {
            map.put(this.f5036a.f6169d, objF);
        } else {
            g(obj, objF);
        }
    }

    public t j(f2.h hVar) {
        if (this.f5028c == null) {
            d2.b bVarD = this.f5036a.d();
            if (bVarD == null || bVarD.deserializeUsing() == Void.class) {
                l2.c cVar = this.f5036a;
                this.f5028c = hVar.n(cVar.f6173h, cVar.f6174i);
            } else {
                try {
                    this.f5028c = (t) bVarD.deserializeUsing().newInstance();
                } catch (Exception e8) {
                    throw new JSONException("create deserializeUsing ObjectDeserializer error", e8);
                }
            }
        }
        return this.f5028c;
    }
}
