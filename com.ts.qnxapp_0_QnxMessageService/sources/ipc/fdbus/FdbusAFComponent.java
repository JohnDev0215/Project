package ipc.fdbus;

import ipc.fdbus.FdbusAppListener;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class FdbusAFComponent {
    private long mNativeHandle;

    private native long fdb_create(String str);

    private native long fdb_offer_service(long j, String str, ArrayList<SubscribeItem> arrayList, FdbusAppListener.Connection connection);

    private native long fdb_query_service(long j, String str, ArrayList<SubscribeItem> arrayList, FdbusAppListener.Connection connection);

    public FdbusAFComponent(String name) {
        this.mNativeHandle = fdb_create(name);
    }

    public FdbusClient queryService(String bus_name, ArrayList<SubscribeItem> evt_handle_tbl, FdbusAppListener.Connection conn_callback) {
        return new FdbusClient(fdb_query_service(this.mNativeHandle, bus_name, evt_handle_tbl, conn_callback));
    }

    public FdbusServer offerService(String bus_name, ArrayList<SubscribeItem> msg_handle_tbl, FdbusAppListener.Connection conn_callback) {
        return new FdbusServer(fdb_offer_service(this.mNativeHandle, bus_name, msg_handle_tbl, conn_callback));
    }
}
