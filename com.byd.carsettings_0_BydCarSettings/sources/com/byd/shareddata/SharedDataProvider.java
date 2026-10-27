package com.byd.shareddata;

import android.content.Context;
import b.b.l.p;
import b.b.l.u.b;
import b.b.l.u.g;
import b.b.l.u.i;
import b.b.l.u.k;
import com.byd.mvcadapter.GsonData;
import com.byd.mvcadapter.Reply;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class SharedDataProvider {
    private static DataMonitorManager mDataMonitorManager;
    private static b mInterceptInvoker;
    private static k mRpcResponse;
    private static b sharedDataInterceptInvoker = new b() { // from class: com.byd.shareddata.SharedDataProvider.1
        @Override // b.b.l.u.b
        public Reply invoke(i iVar, g gVar) {
            String strC = gVar.c();
            if (strC.equals("addDataMonitor") || strC.equals("removeDataMonitor") || SharedDataProvider.mInterceptInvoker == null) {
                return null;
            }
            return SharedDataProvider.mInterceptInvoker.invoke(iVar, gVar);
        }
    };

    public static class DataMonitorManager implements p.c {
        private ArrayList<DataMonitorWrapper> mDataMonitors;

        public class DataMonitorWrapper {
            public String methodName;
            public DataMonitor monitor;
            public int pid;
            public String returnType;

            public DataMonitorWrapper(int i, String str, String str2, DataMonitor dataMonitor) {
                this.pid = i;
                this.methodName = str;
                this.returnType = str2;
                this.monitor = dataMonitor;
            }
        }

        private DataMonitorManager() {
            this.mDataMonitors = new ArrayList<>();
        }

        public void addDataMonitor(int i, String str, String str2, DataMonitor dataMonitor) {
            synchronized (this.mDataMonitors) {
                this.mDataMonitors.add(new DataMonitorWrapper(i, str, str2, dataMonitor));
            }
        }

        @Override // b.b.l.p.c
        public void clientDisconnect(int i) {
            ArrayList arrayList = new ArrayList();
            synchronized (this.mDataMonitors) {
                for (DataMonitorWrapper dataMonitorWrapper : this.mDataMonitors) {
                    if (dataMonitorWrapper.pid == i) {
                        arrayList.add(dataMonitorWrapper);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    this.mDataMonitors.remove((DataMonitorWrapper) it.next());
                }
            }
        }

        public void notifyDataChanged(String str, Object obj, Object... objArr) {
            String strN = c.a.a.a.a.b.n(obj instanceof GsonData ? ((GsonData) obj).b() : obj.getClass().getName());
            synchronized (this.mDataMonitors) {
                for (DataMonitorWrapper dataMonitorWrapper : this.mDataMonitors) {
                    if (dataMonitorWrapper.methodName.equals(str) && (strN == null || strN.equals(dataMonitorWrapper.returnType))) {
                        dataMonitorWrapper.monitor.dataValueChangedWithValue(obj, objArr);
                    }
                }
            }
        }

        public void removeDataMonitor(int i, String str, String str2, DataMonitor dataMonitor) {
            synchronized (this.mDataMonitors) {
                for (DataMonitorWrapper dataMonitorWrapper : this.mDataMonitors) {
                    if (dataMonitorWrapper.pid == i && dataMonitorWrapper.methodName.equals(str) && dataMonitorWrapper.returnType.equals(str2) && dataMonitorWrapper.monitor.equals(dataMonitor)) {
                        this.mDataMonitors.remove(dataMonitorWrapper);
                        return;
                    }
                }
            }
        }
    }

    public static k getRpcResponse() {
        return mRpcResponse;
    }

    public static synchronized void init(Context context) {
        if (mRpcResponse != null) {
            return;
        }
        k kVar = new k(context, "SharedData");
        mRpcResponse = kVar;
        kVar.F(context);
        mRpcResponse.G(sharedDataInterceptInvoker);
        DataMonitorManager dataMonitorManager = new DataMonitorManager();
        mDataMonitorManager = dataMonitorManager;
        mRpcResponse.t(dataMonitorManager);
    }

    public static void notifyDataChanged(String str, Object obj, Object... objArr) {
        mDataMonitorManager.notifyDataChanged(str, obj, objArr);
    }

    public static void register(Object obj) {
        mRpcResponse.t(obj);
    }

    public static void release() {
        mRpcResponse.E();
        mRpcResponse = null;
    }

    public static void setInterceptHandler(b bVar) {
        mInterceptInvoker = bVar;
    }

    public static void unregister(Object obj) {
        mRpcResponse.x(obj);
    }

    public static void unregisterAll() {
        mRpcResponse.y();
    }
}
