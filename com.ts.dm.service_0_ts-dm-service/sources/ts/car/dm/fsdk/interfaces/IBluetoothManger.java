package ts.car.dm.fsdk.interfaces;

import com.ts.car.core.annotation.FoundationInterface;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@FoundationInterface
public interface IBluetoothManger {
    public static final int FAILED = 0;
    public static final int PROFILE_NOT_SURPPORT = -1;
    public static final int SUCCESS = 1;

    int connectProfile(String str, int i);

    int disconnectProfile(String str, int i);

    List<BluetoothDevice> getBondedDevices();

    int getProfileConnectionState(String str, int i);

    boolean removeBond(String str);
}
