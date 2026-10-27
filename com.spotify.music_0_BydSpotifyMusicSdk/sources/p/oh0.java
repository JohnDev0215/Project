package p;

import com.spotify.player.esperanto.proto.EsResponseWithReasons$ResponseWithReasons;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class oh0 implements io.reactivex.rxjava3.functions.o, h00 {
    public static final oh0 c = new oh0();
    public static final oh0 e = new oh0();

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        EsResponseWithReasons$ResponseWithReasons esResponseWithReasons$ResponseWithReasons = (EsResponseWithReasons$ResponseWithReasons) obj;
        v03.j(esResponseWithReasons$ResponseWithReasons, "p0");
        return yu.a(esResponseWithReasons$ResponseWithReasons);
    }

    @Override // p.h00
    public Object convert(Object obj) {
        return Boolean.valueOf(((ResponseBody) obj).q());
    }
}
