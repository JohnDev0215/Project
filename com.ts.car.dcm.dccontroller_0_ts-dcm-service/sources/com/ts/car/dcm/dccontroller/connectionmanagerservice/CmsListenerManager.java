package com.ts.car.dcm.dccontroller.connectionmanagerservice;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import com.ts.car.dcm.common.data.DcmConsumer;
import com.ts.car.dcm.dccontroller.configdata.ConfigManager;
import com.ts.car.dcm.dccontroller.connectionmanagerservice.CmsListenerManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import ts.car.dcm.common.aidl.IDmListener;
import ts.car.dcm.common.aidl.IPcmListener;
import ts.car.dcm.common.data.CmsConstants;

/* JADX INFO: loaded from: classes.dex */
public class CmsListenerManager {
    public static final String TAG = "CmsListenerManager";
    private ConfigManager mConfigManager;
    private HashMap<Integer, RemoteCallbackList<IDmListener>> mDmCbList;
    private Handler mHandler;
    private HashMap<Integer, RemoteCallbackList<IPcmListener>> mPcmCbList;
    private List<ProjectionServiceStatusNotifier> mPssnList;

    CmsListenerManager(Handler handler, ConfigManager configManager) {
        this.mHandler = handler;
        this.mConfigManager = configManager;
    }

    void registerDmListener(IDmListener listener, int objectType) {
        synchronized (this.mDmCbList) {
            RemoteCallbackList<IDmListener> list = this.mDmCbList.get(Integer.valueOf(objectType));
            if (list == null) {
                Log.e(TAG, "registerDmListener, create listener:" + listener + ", objectType:" + objectType);
                RemoteCallbackList<IDmListener> list2 = new RemoteCallbackList<>();
                list2.register(listener);
                this.mDmCbList.put(Integer.valueOf(objectType), list2);
            } else {
                Log.e(TAG, "registerDmListener, listener:" + listener + ", objectType:" + objectType);
                list.register(listener);
            }
            this.mPssnList.add(new ProjectionServiceStatusNotifier(listener));
        }
    }

    void unregisterDmListener(final IDmListener listener, int objectType) {
        synchronized (this.mDmCbList) {
            RemoteCallbackList<IDmListener> list = this.mDmCbList.get(Integer.valueOf(objectType));
            if (list != null) {
                Log.e(TAG, "unregisterDmListener, listener:" + listener + ", objectType:" + objectType);
                list.unregister(listener);
            } else {
                Log.e(TAG, "unregisterDmListener, The listener is not exist:" + objectType);
            }
            this.mPssnList.removeIf(new Predicate() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmsListenerManager$EltrSu5dJMZzAxAWKWSLhqjx_q0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return CmsListenerManager.lambda$unregisterDmListener$0(listener, (CmsListenerManager.ProjectionServiceStatusNotifier) obj);
                }
            });
        }
    }

    static /* synthetic */ boolean lambda$unregisterDmListener$0(IDmListener listener, ProjectionServiceStatusNotifier e) {
        return e.getListener() == listener;
    }

    private void addPcmListener(IPcmListener listener, int service) {
        RemoteCallbackList<IPcmListener> list = this.mPcmCbList.get(Integer.valueOf(service));
        if (list == null) {
            Log.e(TAG, "addPcmListener, create listener:" + listener + ", service:" + service);
            RemoteCallbackList<IPcmListener> list2 = new RemoteCallbackList<IPcmListener>() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.CmsListenerManager.1
                @Override // android.os.RemoteCallbackList
                public void onCallbackDied(IPcmListener callback, Object cookie) {
                    int service2 = ((Integer) cookie).intValue();
                    CmsListenerManager.this.mPcmCbList.remove(Integer.valueOf(service2));
                    Log.e(CmsListenerManager.TAG, "onCallbackDied, detach service:" + service2);
                    Message msg = new Message();
                    msg.what = 0;
                    msg.arg1 = service2;
                    CmsListenerManager.this.mHandler.sendMessage(msg);
                }
            };
            list2.register(listener, Integer.valueOf(service));
            this.mPcmCbList.put(Integer.valueOf(service), list2);
            return;
        }
        Log.e(TAG, "addPcmListener, listener:" + listener + ", service:" + service);
        list.register(listener);
    }

    void registerPcmListener(IPcmListener listener, List<String> services) {
        synchronized (this.mPcmCbList) {
            if (services != null) {
                for (String service : services) {
                    int srv = Integer.parseInt(service);
                    if (CmsConstants.ServiceType.isAvailableType(srv)) {
                        addPcmListener(listener, srv);
                    } else {
                        Log.e(TAG, "registerPcmListener, unknown service:" + service);
                    }
                }
            } else {
                Log.e(TAG, "registerPcmListener, services is null.");
            }
        }
    }

    private void rmPcmListener(IPcmListener listener, int service) {
        RemoteCallbackList<IPcmListener> list = this.mPcmCbList.get(Integer.valueOf(service));
        if (list != null) {
            Log.e(TAG, "rmPcmListener, listener: " + listener + ", service:" + service);
            list.unregister(listener);
            return;
        }
        Log.e(TAG, "rmPcmListener, The listener is not exist:" + service);
    }

    void unregisterPcmListener(IPcmListener listener, List<String> services) {
        synchronized (this.mPcmCbList) {
            if (services != null) {
                for (String service : services) {
                    int srv = Integer.parseInt(service);
                    if (CmsConstants.ServiceType.isAvailableType(srv)) {
                        rmPcmListener(listener, srv);
                    } else {
                        Log.e(TAG, "unregisterPcmListener, unknown service:" + service);
                    }
                }
            } else {
                Log.e(TAG, "unregisterPcmListener, services is null.");
            }
        }
    }

    void init() {
        this.mPcmCbList = new HashMap<>();
        this.mDmCbList = new HashMap<>();
        this.mPssnList = new ArrayList();
    }

    void clear() {
        this.mPcmCbList.values().forEach(new Consumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$sFSqU4NZ4dTjwM4DLKQY2Io8T98
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((RemoteCallbackList) obj).kill();
            }
        });
        this.mPcmCbList.clear();
        this.mDmCbList.clear();
        this.mPssnList.clear();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    boolean isAvailablePmService(int service) {
        boolean result = false;
        RemoteCallbackList<IPcmListener> listeners = this.mPcmCbList.get(Integer.valueOf(service));
        if (listeners != null && listeners.getRegisteredCallbackCount() > 0) {
            result = true;
        }
        Log.d(TAG, "isAvailablePmService, service:" + service + ", result:" + result);
        return result;
    }

    boolean isAvailableHmi() {
        boolean result = false;
        RemoteCallbackList<IDmListener> listeners = this.mDmCbList.get(1);
        if (listeners != null && listeners.getRegisteredCallbackCount() > 0) {
            result = true;
        }
        Log.e(TAG, "isAvailableHmi, result:" + result);
        return result;
    }

    boolean isProjectionRegisterCompleted() {
        List<Integer> srvTypes = this.mConfigManager.getProjectionReadyCase();
        Iterator<Integer> it = srvTypes.iterator();
        while (it.hasNext()) {
            int srvType = it.next().intValue();
            if (!isAvailablePmService(srvType)) {
                Log.d(TAG, "isProjectionRegisterCompleted, srvType:" + srvType + " not completed!");
                return false;
            }
        }
        return true;
    }

    boolean isReportProjectionReady() {
        return isAvailableHmi() && isProjectionRegisterCompleted();
    }

    public void broadcast2PcmListener(final DcmConsumer.ReConsumer<IPcmListener> consumer, int type) {
        Log.d(TAG, "broadcast2PcmListener, service:" + type + ", thId:" + Thread.currentThread().getId());
        final RemoteCallbackList<IPcmListener> listeners = this.mPcmCbList.get(Integer.valueOf(type));
        if (listeners != null && listeners.getRegisteredCallbackCount() > 0) {
            this.mHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmsListenerManager$MwheTnE8KSQJaS40NmhUzXnq6dk
                @Override // java.lang.Runnable
                public final void run() {
                    CmsListenerManager.lambda$broadcast2PcmListener$1(listeners, consumer);
                }
            });
        }
    }

    static /* synthetic */ void lambda$broadcast2PcmListener$1(RemoteCallbackList listeners, DcmConsumer.ReConsumer consumer) {
        int count = listeners.beginBroadcast();
        try {
            Log.d(TAG, "broadcast2PcmListener, count:" + count + ", thId:" + Thread.currentThread().getId());
            for (int i = 0; i < count; i++) {
                try {
                    consumer.accept((IPcmListener) listeners.getBroadcastItem(i));
                } catch (RemoteException ex) {
                    Log.e(TAG, "broadcast2PcmListener, RemoteException ex:" + ex);
                }
            }
        } finally {
            listeners.finishBroadcast();
            Log.d(TAG, "broadcast2PcmListener, finish.");
        }
    }

    public void broadcast2DmListener(final DcmConsumer.ReConsumer<IDmListener> consumer) {
        Log.d(TAG, "broadcast2DmListener, thId=" + Thread.currentThread().getId());
        for (final RemoteCallbackList<IDmListener> listeners : this.mDmCbList.values()) {
            if (listeners.getRegisteredCallbackCount() > 0) {
                this.mHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmsListenerManager$8lMYwaFJTIreRRyWh-Kvesx12u4
                    @Override // java.lang.Runnable
                    public final void run() {
                        CmsListenerManager.lambda$broadcast2DmListener$2(listeners, consumer);
                    }
                });
            }
        }
    }

    static /* synthetic */ void lambda$broadcast2DmListener$2(RemoteCallbackList listeners, DcmConsumer.ReConsumer consumer) {
        int count = listeners.beginBroadcast();
        try {
            Log.d(TAG, "broadcast2DmListener, count:" + count + ", thId:" + Thread.currentThread().getId());
            for (int i = 0; i < count; i++) {
                try {
                    consumer.accept((IDmListener) listeners.getBroadcastItem(i));
                } catch (RemoteException ex) {
                    Log.e(TAG, "broadcast2DmListener, RemoteException ex:" + ex);
                }
            }
        } finally {
            listeners.finishBroadcast();
            Log.d(TAG, "broadcast2DmListener, finish.");
        }
    }

    public void broadcastProjectionServiceStatus(final int serviceType, final boolean status) {
        Log.e(TAG, "broadcastProjectionServiceStatus, serviceType: " + serviceType + ", status: " + status + ", thId: " + Thread.currentThread().getId());
        if (!isAvailableHmi()) {
            Log.e(TAG, "broadcastProjectionServiceStatus, DMHMI not registered.");
        } else {
            this.mHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmsListenerManager$HxOG0icfPjTC47OXtp7NiybbEwI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$broadcastProjectionServiceStatus$4$CmsListenerManager(serviceType, status);
                }
            });
        }
    }

    public /* synthetic */ void lambda$broadcastProjectionServiceStatus$4$CmsListenerManager(final int serviceType, final boolean status) {
        synchronized (this.mDmCbList) {
            Log.e(TAG, "broadcast begin, List size=" + this.mPssnList.size());
            this.mPssnList.forEach(new Consumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmsListenerManager$V83aw4KRRu691jc_dv5hMrz2NCw
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((CmsListenerManager.ProjectionServiceStatusNotifier) obj).sendNotification(serviceType, status);
                }
            });
            Log.e(TAG, "broadcast end.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ProjectionServiceStatusNotifier {
        private static final int MAX_SERVICES = 4;
        private static final int STATUS_NONE = -1;
        private static final int STATUS_NOT_READY = 1;
        private static final int STATUS_READY = 0;
        private IDmListener listener;
        private int[] notifiedStatus = new int[4];

        public ProjectionServiceStatusNotifier(IDmListener listener) {
            this.listener = listener;
            for (int i = 0; i < 4; i++) {
                this.notifiedStatus[i] = -1;
            }
        }

        public IDmListener getListener() {
            return this.listener;
        }

        public void sendNotification(int serviceType, boolean status) {
            Log.e(CmsListenerManager.TAG, "sendNotification, serviceType=" + serviceType + " status=" + status);
            if (!CmsConstants.ServiceType.isAvailableType(serviceType)) {
                Log.e(CmsListenerManager.TAG, "sendNotification failed for invalid serviceType: " + serviceType);
                return;
            }
            if (this.listener == null) {
                Log.e(CmsListenerManager.TAG, "sendNotification failed for null listener.");
                return;
            }
            int preStatus = this.notifiedStatus[serviceType - 1];
            if ((preStatus != 0 && status) || (preStatus == 0 && !status)) {
                Log.e(CmsListenerManager.TAG, "sendNotification for projection service status change.");
                try {
                    this.listener.onProjectionServiceStatus(serviceType, status);
                } catch (RemoteException ex) {
                    Log.e(CmsListenerManager.TAG, "sendNotification, RemoteException ex:" + ex);
                }
                this.notifiedStatus[serviceType - 1] = status ? 0 : 1;
            }
        }
    }
}
