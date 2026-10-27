package p;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import okhttp3.ResponseBody;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class hl1 implements h00 {
    public static final ByteString e;
    public final com.squareup.moshi.f c;

    static {
        ByteString byteString = ByteString.k;
        e = qo.o("EFBBBF");
    }

    public hl1(com.squareup.moshi.f fVar) {
        this.c = fVar;
    }

    @Override // p.h00
    public final Object convert(Object obj) {
        ResponseBody responseBody = (ResponseBody) obj;
        ko koVarI = responseBody.i();
        try {
            if (koVarI.x0(0L, e)) {
                koVarI.y(r1.e());
            }
            JsonReader jsonReaderG0 = JsonReader.G0(koVarI);
            Object objFromJson = this.c.fromJson(jsonReaderG0);
            if (jsonReaderG0.H0() != JsonReader.Token.END_DOCUMENT) {
                throw new JsonDataException("JSON document was not fully consumed.");
            }
            responseBody.close();
            return objFromJson;
        } catch (Throwable th) {
            responseBody.close();
            throw th;
        }
    }
}
