package p;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.RequestBody$Companion$toRequestBody$1;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class c11 implements h00 {
    public static final MediaType k;
    public static final Charset l;
    public final com.google.gson.a c;
    public final com.google.gson.b e;

    static {
        MediaType.d.getClass();
        k = MediaType.Companion.a("application/json; charset=UTF-8");
        l = Charset.forName("UTF-8");
    }

    public c11(com.google.gson.a aVar, com.google.gson.b bVar) {
        this.c = aVar;
        this.e = bVar;
    }

    @Override // p.h00
    public final Object convert(Object obj) throws IOException {
        fo foVar = new fo();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new xo(foVar), l);
        com.google.gson.a aVar = this.c;
        if (aVar.g) {
            outputStreamWriter.write(")]}'\n");
        }
        z71 z71Var = new z71(outputStreamWriter);
        if (aVar.h) {
            z71Var.l = "  ";
            z71Var.m = ": ";
        }
        z71Var.f214p = aVar.f;
        this.e.c(z71Var, obj);
        z71Var.close();
        ByteString byteStringV = foVar.v(foVar.e);
        RequestBody.a.getClass();
        v03.j(byteStringV, "content");
        return new RequestBody$Companion$toRequestBody$1(k, byteStringV);
    }
}
