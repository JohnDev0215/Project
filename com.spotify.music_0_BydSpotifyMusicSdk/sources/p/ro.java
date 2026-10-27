package p;

import com.google.common.base.Optional;
import com.spotify.player.esperanto.proto.EsResponseWithReasons$ResponseWithReasons;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public final class ro implements h00, io.reactivex.rxjava3.functions.o, io.reactivex.rxjava3.functions.p {
    public static final ro c = new ro();
    public static final ro e = new ro();
    public static final ro k = new ro();

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        EsResponseWithReasons$ResponseWithReasons esResponseWithReasons$ResponseWithReasons = (EsResponseWithReasons$ResponseWithReasons) obj;
        v03.j(esResponseWithReasons$ResponseWithReasons, "p0");
        return yu.a(esResponseWithReasons$ResponseWithReasons);
    }

    @Override // p.h00
    public Object convert(Object obj) {
        ((ResponseBody) obj).close();
        return lq2.a;
    }

    @Override // io.reactivex.rxjava3.functions.p
    public boolean test(Object obj) {
        Optional optional = (Optional) obj;
        v03.j(optional, "obj");
        return optional.d();
    }
}
