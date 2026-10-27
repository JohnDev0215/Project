package p;

import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import com.spotify.playerlimited.player.models.ConnectDevice;
import com.spotify.playerlimited.player.models.GaiaState;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class gh0 implements io.reactivex.rxjava3.functions.o {
    public static final gh0 c = new gh0();

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        ConnectMessages$StateResponse connectMessages$StateResponse = (ConnectMessages$StateResponse) obj;
        GaiaState gaiaState = new GaiaState();
        gaiaState.a = connectMessages$StateResponse.f();
        gaiaState.c = connectMessages$StateResponse.g();
        gaiaState.b = connectMessages$StateResponse.h();
        gaiaState.d = connectMessages$StateResponse.i();
        v51<ConnectMessages$StateResponse.Device> v51VarE = connectMessages$StateResponse.e();
        v03.i(v51VarE, "it.devicesList");
        ArrayList arrayList = new ArrayList(lu.W(v51VarE));
        for (ConnectMessages$StateResponse.Device device : v51VarE) {
            ConnectDevice connectDevice = new ConnectDevice();
            connectDevice.a = device.f();
            connectDevice.b = device.g();
            connectDevice.c = device.getName();
            connectDevice.d = device.e();
            connectDevice.e = device.h();
            arrayList.add(connectDevice);
        }
        gaiaState.e = (ConnectDevice[]) arrayList.toArray(new ConnectDevice[0]);
        return gaiaState;
    }
}
