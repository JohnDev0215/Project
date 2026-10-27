package p;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: classes.dex */
public final class qy1 implements h00 {
    public static final MediaType c;

    static {
        MediaType.d.getClass();
        c = MediaType.Companion.a("application/x-protobuf");
    }

    @Override // p.h00
    public final Object convert(Object obj) {
        byte[] byteArray = ((wj1) obj).toByteArray();
        RequestBody.a.getClass();
        v03.j(byteArray, "content");
        return RequestBody.Companion.a(byteArray, c, 0, byteArray.length);
    }
}
