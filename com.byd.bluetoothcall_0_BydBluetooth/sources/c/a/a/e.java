package c.a.a;

import com.byd.info.CallLogInfo;
import com.byd.info.ContactsInfo;
import com.byd.type_enum.Sync$SyncState;
import com.byd.type_enum.Sync$SyncType;
import java.util.List;

/* JADX INFO: compiled from: UIListener_SyncPhoneBook.java */
/* JADX INFO: loaded from: classes.dex */
public interface e {
    void SyncBookTotal(int i);

    void SyncCallLogOne_data(CallLogInfo callLogInfo);

    void SyncCallLog_data(List<CallLogInfo> list);

    void SyncPhoeBook_data(List<ContactsInfo> list);

    void SyncProgress(ContactsInfo contactsInfo, int i, int i2);

    void SyncState(Sync$SyncType sync$SyncType, Sync$SyncState sync$SyncState);
}
