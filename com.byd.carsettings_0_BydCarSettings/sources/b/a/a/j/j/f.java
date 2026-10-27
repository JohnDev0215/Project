package b.a.a.j.j;

import b.a.a.j.a;
import com.alibaba.fastjson.JSONException;
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
    public t f274c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f275d;

    public f(b.a.a.j.h hVar, Class<?> cls, b.a.a.n.c cVar) {
        super(cls, cVar);
        boolean z = false;
        this.f275d = false;
        b.a.a.h.b bVarD = cVar.d();
        if (bVarD != null) {
            Class<?> clsDeserializeUsing = bVarD.deserializeUsing();
            if (clsDeserializeUsing != null && clsDeserializeUsing != Void.class) {
                z = true;
            }
            this.f275d = z;
        }
    }

    @Override // b.a.a.j.j.l
    public int b() {
        t tVar = this.f274c;
        if (tVar != null) {
            return tVar.e();
        }
        return 2;
    }

    @Override // b.a.a.j.j.l
    public void c(b.a.a.j.a aVar, Object obj, Type type, Map<String, Object> map) {
        Object objF;
        b.a.a.n.c cVar;
        int i;
        if (this.f274c == null) {
            j(aVar.i());
        }
        t tVarO = this.f274c;
        Type typeH = this.f282a.f434f;
        if (type instanceof ParameterizedType) {
            b.a.a.j.g gVarJ = aVar.j();
            if (gVarJ != null) {
                gVarJ.f249e = type;
            }
            if (typeH != type) {
                typeH = b.a.a.n.c.h(this.f283b, type, typeH);
                tVarO = aVar.i().o(typeH);
            }
        }
        Type type2 = typeH;
        if (!(tVarO instanceof o) || (i = (cVar = this.f282a).j) == 0) {
            b.a.a.n.c cVar2 = this.f282a;
            String str = cVar2.t;
            objF = (!(str == null && cVar2.j == 0) && (tVarO instanceof e)) ? ((e) tVarO).f(aVar, type2, cVar2.f429a, str, cVar2.j) : tVarO.b(aVar, type2, cVar2.f429a);
        } else {
            objF = ((o) tVarO).h(aVar, type2, cVar.f429a, i);
        }
        if ((objF instanceof byte[]) && ("gzip".equals(this.f282a.t) || "gzip,base64".equals(this.f282a.t))) {
            try {
                GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream((byte[]) objF));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    byte[] bArr = new byte[1024];
                    int i2 = gZIPInputStream.read(bArr);
                    if (i2 == -1) {
                        break;
                    } else if (i2 > 0) {
                        byteArrayOutputStream.write(bArr, 0, i2);
                    }
                }
                objF = byteArrayOutputStream.toByteArray();
            } catch (IOException e2) {
                throw new JSONException("unzip bytes error.", e2);
            }
        }
        if (aVar.v() == 1) {
            a.C0016a c0016aS = aVar.s();
            c0016aS.f237c = this;
            c0016aS.f238d = aVar.j();
            aVar.b0(0);
            return;
        }
        if (obj == null) {
            map.put(this.f282a.f429a, objF);
        } else {
            g(obj, objF);
        }
    }

    public t j(b.a.a.j.h hVar) {
        if (this.f274c == null) {
            b.a.a.h.b bVarD = this.f282a.d();
            if (bVarD == null || bVarD.deserializeUsing() == Void.class) {
                b.a.a.n.c cVar = this.f282a;
                this.f274c = hVar.n(cVar.f433e, cVar.f434f);
            } else {
                try {
                    this.f274c = (t) bVarD.deserializeUsing().newInstance();
                } catch (Exception e2) {
                    throw new JSONException("create deserializeUsing ObjectDeserializer error", e2);
                }
            }
        }
        return this.f274c;
    }
}
