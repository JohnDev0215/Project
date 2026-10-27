package p;

import com.spotify.player.esperanto.proto.EsResponseWithReasons$ResponseWithReasons;
import java.io.IOException;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class mh0 implements io.reactivex.rxjava3.functions.o, h00 {
    public static final mh0 c = new mh0();
    public static final mh0 e = new mh0();

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        EsResponseWithReasons$ResponseWithReasons esResponseWithReasons$ResponseWithReasons = (EsResponseWithReasons$ResponseWithReasons) obj;
        v03.j(esResponseWithReasons$ResponseWithReasons, "p0");
        return yu.a(esResponseWithReasons$ResponseWithReasons);
    }

    @Override // p.h00
    public Object convert(Object obj) throws IOException {
        String strQ = ((ResponseBody) obj).q();
        if (strQ.length() == 1) {
            return Character.valueOf(strQ.charAt(0));
        }
        throw new IOException("Expected body of length 1 for Character conversion but was " + strQ.length());
    }
}
