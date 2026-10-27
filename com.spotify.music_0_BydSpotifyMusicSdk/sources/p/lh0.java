package p;

import com.spotify.player.esperanto.proto.EsResponseWithReasons$ResponseWithReasons;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class lh0 implements io.reactivex.rxjava3.functions.o, h00 {
    public static final lh0 c = new lh0();
    public static final lh0 e = new lh0();

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        EsResponseWithReasons$ResponseWithReasons esResponseWithReasons$ResponseWithReasons = (EsResponseWithReasons$ResponseWithReasons) obj;
        v03.j(esResponseWithReasons$ResponseWithReasons, "p0");
        return yu.a(esResponseWithReasons$ResponseWithReasons);
    }

    @Override // p.h00
    public Object convert(Object obj) {
        return Byte.valueOf(((ResponseBody) obj).q());
    }
}
