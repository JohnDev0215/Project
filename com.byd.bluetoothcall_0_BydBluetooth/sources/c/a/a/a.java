package c.a.a;

import com.byd.info.CallLogInfo;
import com.byd.info.ContactsInfo;
import com.byd.type_enum.Sync$SyncType;
import java.util.List;

/* JADX INFO: compiled from: PbapSyncListener.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {
    List<ContactsInfo> a(String str, String str2);

    void b(CallLogInfo callLogInfo);

    void c();

    void d();

    List<CallLogInfo> e(String str, String str2);

    void f();

    CallLogInfo g(String str, String str2);

    void h(String str);

    List<ContactsInfo> i(String str, String str2);

    List<ContactsInfo> j(String str, String str2);

    CallLogInfo k(String str);

    List<CallLogInfo> l(Sync$SyncType sync$SyncType, String str);
}
