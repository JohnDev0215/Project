package p;

import com.spotify.connect.esperanto.proto.CommonMessages$StatusResponse;
import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.ConcurrentSkipListMap;

/* JADX INFO: loaded from: classes.dex */
public final class px implements io.reactivex.rxjava3.functions.o, xo1 {
    public static final px e = new px(0);
    public static final px k = new px(1);
    public final /* synthetic */ int c;

    public /* synthetic */ px(int i) {
        this.c = i;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        switch (this.c) {
            case 0:
                byte[] bArr = (byte[]) obj;
                v03.j(bArr, "data");
                try {
                    return CommonMessages$StatusResponse.f(bArr);
                } catch (Exception e2) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connect.esperanto.proto.CommonMessages.StatusResponse: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e2);
                }
            default:
                byte[] bArr2 = (byte[]) obj;
                v03.j(bArr2, "data");
                try {
                    return ConnectMessages$StateResponse.j(bArr2);
                } catch (Exception e3) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connect.esperanto.proto.ConnectMessages.StateResponse: '", Base64.getEncoder().encodeToString(bArr2), "' (Base64)"), e3);
                }
        }
    }

    @Override // p.xo1
    public Object k() {
        switch (this.c) {
            case 0:
                return new LinkedHashMap();
            case 1:
                return new LinkedHashSet();
            default:
                return new ConcurrentSkipListMap();
        }
    }
}
