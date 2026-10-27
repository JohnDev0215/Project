package p;

import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class my0 implements io.reactivex.rxjava3.functions.o {
    public static final my0 c = new my0();

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        ConnectMessages$StateResponse connectMessages$StateResponse = (ConnectMessages$StateResponse) obj;
        boolean zF = connectMessages$StateResponse.f();
        boolean zG = connectMessages$StateResponse.g();
        boolean zH = connectMessages$StateResponse.h();
        boolean zI = connectMessages$StateResponse.i();
        v51<ConnectMessages$StateResponse.Device> v51VarE = connectMessages$StateResponse.e();
        v03.i(v51VarE, "response.devicesList");
        ArrayList arrayList = new ArrayList(lu.W(v51VarE));
        for (ConnectMessages$StateResponse.Device device : v51VarE) {
            arrayList.add(new mx(device.f(), device.g(), device.getName(), device.h(), device.e(), device.getType()));
        }
        return new a01(zF, zH, zG, zI, arrayList);
    }
}
