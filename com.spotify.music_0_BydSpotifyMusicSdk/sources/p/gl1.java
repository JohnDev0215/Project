package p;

import java.io.EOFException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.RequestBody$Companion$toRequestBody$1;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class gl1 implements h00 {
    public static final MediaType e;
    public final com.squareup.moshi.f c;

    static {
        MediaType.d.getClass();
        e = MediaType.Companion.a("application/json; charset=UTF-8");
    }

    public gl1(com.squareup.moshi.f fVar) {
        this.c = fVar;
    }

    @Override // p.h00
    public final Object convert(Object obj) throws EOFException {
        fo foVar = new fo();
        this.c.toJson(com.squareup.moshi.m.L0(foVar), obj);
        ByteString byteStringV = foVar.v(foVar.e);
        RequestBody.a.getClass();
        v03.j(byteStringV, "content");
        return new RequestBody$Companion$toRequestBody$1(e, byteStringV);
    }
}
