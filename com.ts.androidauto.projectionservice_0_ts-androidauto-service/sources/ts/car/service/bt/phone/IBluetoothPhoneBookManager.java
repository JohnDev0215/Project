package ts.car.service.bt.phone;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface IBluetoothPhoneBookManager extends IPlatformService {
    public static final String ACCESS_BLUETOOTH_EXT = "android.permission.ACCESS_BLUETOOTH_EXT";
    public static final String UPDATE_BLUETOOTH_EXT = "android.permission.UPDATE_BLUETOOTH_EXT";

    boolean cancelSync(int i) throws RemoteException;

    boolean deleteCallLogs(String str, int i, int i2) throws RemoteException;

    boolean deleteContacts(String str, int i) throws RemoteException;

    int getCallLogCount(String str, int i, int i2) throws RemoteException;

    List<CallLogItem> getCallLogs(String str, int i, int i2, int i3) throws RemoteException;

    int getContactCount(String str, int i) throws RemoteException;

    List<ContactItem> getDownloadedContacts(String str, int i) throws RemoteException;

    int getErrCode() throws RemoteException;

    List<ContactItem> getFrequentContacts(String str, int i, int i2) throws RemoteException;

    String getPhotoFromContact(String str, int i) throws RemoteException;

    int getSynchronizingJob(String str) throws RemoteException;

    List<CallLogItem> queryCallLogs(String str, int i, int i2, String str2) throws RemoteException;

    List<ContactItem> queryDownloadedContacts(String str, int i, String str2, int i2) throws RemoteException;

    boolean registerListener(IBluetoothPhoneBookListener iBluetoothPhoneBookListener) throws RemoteException;

    int syncCallLogs(String str, int i, int i2, int i3, int i4) throws RemoteException;

    int syncContacts(String str, int i, int i2, int i3, boolean z) throws RemoteException;

    boolean unregisterListener(IBluetoothPhoneBookListener iBluetoothPhoneBookListener) throws RemoteException;
}
