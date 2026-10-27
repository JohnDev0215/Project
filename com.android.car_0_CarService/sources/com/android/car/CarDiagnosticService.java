package com.android.car;

import android.car.diagnostic.CarDiagnosticEvent;
import android.car.diagnostic.ICarDiagnostic;
import android.car.diagnostic.ICarDiagnosticEventListener;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import com.android.car.CarDiagnosticService;
import com.android.car.Listeners;
import com.android.car.Manifest;
import com.android.car.hal.DiagnosticHalService;
import com.android.car.internal.CarPermission;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes3.dex */
public class CarDiagnosticService extends ICarDiagnostic.Stub implements CarServiceBase, DiagnosticHalService.DiagnosticListener {
    private final Context mContext;
    private final CarPermission mDiagnosticClearPermission;
    private final DiagnosticHalService mDiagnosticHal;
    private final CarPermission mDiagnosticReadPermission;
    private final ReentrantLock mDiagnosticLock = new ReentrantLock();
    private final LinkedList<DiagnosticClient> mClients = new LinkedList<>();
    private final HashMap<Integer, Listeners<DiagnosticClient>> mDiagnosticListeners = new HashMap<>();
    private final LiveFrameRecord mLiveFrameDiagnosticRecord = new LiveFrameRecord(this.mDiagnosticLock);
    private final FreezeFrameRecord mFreezeFrameDiagnosticRecords = new FreezeFrameRecord(this.mDiagnosticLock);

    public CarDiagnosticService(Context context, DiagnosticHalService diagnosticHal) {
        this.mContext = context;
        this.mDiagnosticHal = diagnosticHal;
        this.mDiagnosticReadPermission = new CarPermission(this.mContext, Manifest.permission.CAR_DIAGNOSTICS);
        this.mDiagnosticClearPermission = new CarPermission(this.mContext, Manifest.permission.CLEAR_CAR_DIAGNOSTICS);
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        this.mDiagnosticLock.lock();
        try {
            this.mDiagnosticHal.setDiagnosticListener(this);
            setInitialLiveFrame();
            setInitialFreezeFrames();
        } finally {
            this.mDiagnosticLock.unlock();
        }
    }

    private CarDiagnosticEvent setInitialLiveFrame() {
        if (!this.mDiagnosticHal.getDiagnosticCapabilities().isLiveFrameSupported()) {
            return null;
        }
        CarDiagnosticEvent liveFrame = setRecentmostLiveFrame(this.mDiagnosticHal.getCurrentLiveFrame());
        return liveFrame;
    }

    private void setInitialFreezeFrames() {
        long[] timestamps;
        if (this.mDiagnosticHal.getDiagnosticCapabilities().isFreezeFrameSupported() && this.mDiagnosticHal.getDiagnosticCapabilities().isFreezeFrameInfoSupported() && (timestamps = this.mDiagnosticHal.getFreezeFrameTimestamps()) != null) {
            for (long timestamp : timestamps) {
                setRecentmostFreezeFrame(this.mDiagnosticHal.getFreezeFrame(timestamp));
            }
        }
    }

    private CarDiagnosticEvent setRecentmostLiveFrame(CarDiagnosticEvent event) {
        if (event != null) {
            return this.mLiveFrameDiagnosticRecord.update(event.checkLiveFrame());
        }
        return null;
    }

    private CarDiagnosticEvent setRecentmostFreezeFrame(CarDiagnosticEvent event) {
        if (event != null) {
            return this.mFreezeFrameDiagnosticRecords.update(event.checkFreezeFrame());
        }
        return null;
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        this.mDiagnosticLock.lock();
        try {
            this.mDiagnosticListeners.forEach(new BiConsumer() { // from class: com.android.car.-$$Lambda$CarDiagnosticService$RWaSYphwlmCsrO3T7AJmx5iJvgE
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((Listeners) obj2).release();
                }
            });
            this.mDiagnosticListeners.clear();
            this.mLiveFrameDiagnosticRecord.disableIfNeeded();
            this.mFreezeFrameDiagnosticRecords.disableIfNeeded();
            this.mClients.clear();
        } finally {
            this.mDiagnosticLock.unlock();
        }
    }

    private void processDiagnosticData(List<CarDiagnosticEvent> events) {
        Listeners<DiagnosticClient> listeners;
        ArrayMap<DiagnosticClient, List<CarDiagnosticEvent>> eventsByClient = new ArrayMap<>();
        this.mDiagnosticLock.lock();
        for (CarDiagnosticEvent event : events) {
            if (event.isLiveFrame()) {
                setRecentmostLiveFrame(event);
                Listeners<DiagnosticClient> listeners2 = this.mDiagnosticListeners.get(0);
                listeners = listeners2;
            } else if (event.isFreezeFrame()) {
                setRecentmostFreezeFrame(event);
                Listeners<DiagnosticClient> listeners3 = this.mDiagnosticListeners.get(1);
                listeners = listeners3;
            } else {
                Log.w(CarLog.TAG_DIAGNOSTIC, String.format("received unknown diagnostic event: %s", event));
            }
            if (listeners != null) {
                Iterator it = listeners.getClients().iterator();
                while (it.hasNext()) {
                    Listeners.ClientWithRate<DiagnosticClient> clientWithRate = (Listeners.ClientWithRate) it.next();
                    DiagnosticClient client = (DiagnosticClient) clientWithRate.getClient();
                    List<CarDiagnosticEvent> clientEvents = eventsByClient.computeIfAbsent(client, new Function() { // from class: com.android.car.-$$Lambda$CarDiagnosticService$K3aBd1oODaMxNDR94wAy8IBGg9g
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return CarDiagnosticService.lambda$processDiagnosticData$1((CarDiagnosticService.DiagnosticClient) obj);
                        }
                    });
                    clientEvents.add(event);
                }
            }
        }
        this.mDiagnosticLock.unlock();
        for (Map.Entry<DiagnosticClient, List<CarDiagnosticEvent>> entry : eventsByClient.entrySet()) {
            DiagnosticClient client2 = entry.getKey();
            List<CarDiagnosticEvent> clientEvents2 = entry.getValue();
            client2.dispatchDiagnosticUpdate(clientEvents2);
        }
    }

    static /* synthetic */ List lambda$processDiagnosticData$1(DiagnosticClient diagnosticClient) {
        return new LinkedList();
    }

    @Override // com.android.car.hal.DiagnosticHalService.DiagnosticListener
    public void onDiagnosticEvents(List<CarDiagnosticEvent> events) {
        processDiagnosticData(events);
    }

    public boolean registerOrUpdateDiagnosticListener(int frameType, int rate, ICarDiagnosticEventListener listener) {
        boolean shouldStartDiagnostics = false;
        Integer oldRate = null;
        this.mDiagnosticLock.lock();
        try {
            this.mDiagnosticReadPermission.assertGranted();
            DiagnosticClient diagnosticClient = findDiagnosticClientLocked(listener);
            Listeners.ClientWithRate clientWithRateFindClientWithRate = null;
            if (diagnosticClient == null) {
                diagnosticClient = new DiagnosticClient(listener);
                listener.asBinder().linkToDeath(diagnosticClient, 0);
                this.mClients.add(diagnosticClient);
            }
            Listeners<DiagnosticClient> diagnosticListeners = this.mDiagnosticListeners.get(Integer.valueOf(frameType));
            if (diagnosticListeners == null) {
                diagnosticListeners = new Listeners<>(rate);
                this.mDiagnosticListeners.put(Integer.valueOf(frameType), diagnosticListeners);
                shouldStartDiagnostics = true;
            } else {
                oldRate = Integer.valueOf(diagnosticListeners.getRate());
                clientWithRateFindClientWithRate = diagnosticListeners.findClientWithRate(diagnosticClient);
            }
            if (clientWithRateFindClientWithRate == null) {
                Listeners.ClientWithRate<DiagnosticClient> diagnosticClientWithRate = new Listeners.ClientWithRate<>(diagnosticClient, rate);
                diagnosticListeners.addClientWithRate(diagnosticClientWithRate);
            } else {
                clientWithRateFindClientWithRate.setRate(rate);
            }
            if (diagnosticListeners.getRate() > rate) {
                diagnosticListeners.setRate(rate);
                shouldStartDiagnostics = true;
            }
            diagnosticClient.addDiagnostic(frameType);
            this.mDiagnosticLock.unlock();
            Log.i(CarLog.TAG_DIAGNOSTIC, String.format("shouldStartDiagnostics = %s for %s at rate %d", Boolean.valueOf(shouldStartDiagnostics), Integer.valueOf(frameType), Integer.valueOf(rate)));
            if (!shouldStartDiagnostics || startDiagnostic(frameType, rate)) {
                return true;
            }
            Log.w(CarLog.TAG_DIAGNOSTIC, "startDiagnostic failed");
            this.mDiagnosticLock.lock();
            try {
                diagnosticClient.removeDiagnostic(frameType);
                if (oldRate != null) {
                    diagnosticListeners.setRate(oldRate.intValue());
                } else {
                    this.mDiagnosticListeners.remove(Integer.valueOf(frameType));
                }
                return false;
            } finally {
            }
        } catch (RemoteException e) {
            Log.w(CarLog.TAG_DIAGNOSTIC, String.format("received RemoteException trying to register listener for %s", Integer.valueOf(frameType)));
            return false;
        } finally {
        }
    }

    private boolean startDiagnostic(int frameType, int rate) {
        Log.i(CarLog.TAG_DIAGNOSTIC, String.format("starting diagnostic %s at rate %d", Integer.valueOf(frameType), Integer.valueOf(rate)));
        DiagnosticHalService diagnosticHal = getDiagnosticHal();
        if (diagnosticHal != null) {
            if (!diagnosticHal.isReady()) {
                Log.w(CarLog.TAG_DIAGNOSTIC, "diagnosticHal not ready");
                return false;
            }
            if (frameType != 0) {
                if (frameType == 1) {
                    if (this.mFreezeFrameDiagnosticRecords.isEnabled()) {
                        return true;
                    }
                    if (diagnosticHal.requestDiagnosticStart(1, rate)) {
                        this.mFreezeFrameDiagnosticRecords.enable();
                        return true;
                    }
                }
            } else {
                if (this.mLiveFrameDiagnosticRecord.isEnabled()) {
                    return true;
                }
                if (diagnosticHal.requestDiagnosticStart(0, rate)) {
                    this.mLiveFrameDiagnosticRecord.enable();
                    return true;
                }
            }
        }
        return false;
    }

    public void unregisterDiagnosticListener(int frameType, ICarDiagnosticEventListener listener) {
        boolean shouldStopDiagnostic = false;
        boolean shouldRestartDiagnostic = false;
        int newRate = 0;
        this.mDiagnosticLock.lock();
        try {
            DiagnosticClient diagnosticClient = findDiagnosticClientLocked(listener);
            if (diagnosticClient == null) {
                Log.i(CarLog.TAG_DIAGNOSTIC, String.format("trying to unregister diagnostic client %s for %s which is not registered", listener, Integer.valueOf(frameType)));
                return;
            }
            diagnosticClient.removeDiagnostic(frameType);
            if (diagnosticClient.getNumberOfActiveDiagnostic() == 0) {
                diagnosticClient.release();
                this.mClients.remove(diagnosticClient);
            }
            Listeners<DiagnosticClient> diagnosticListeners = this.mDiagnosticListeners.get(Integer.valueOf(frameType));
            if (diagnosticListeners == null) {
                return;
            }
            Listeners.ClientWithRate<ClientType> clientWithRateFindClientWithRate = diagnosticListeners.findClientWithRate(diagnosticClient);
            if (clientWithRateFindClientWithRate == 0) {
                return;
            }
            diagnosticListeners.removeClientWithRate(clientWithRateFindClientWithRate);
            if (diagnosticListeners.getNumberOfClients() == 0) {
                shouldStopDiagnostic = true;
                this.mDiagnosticListeners.remove(Integer.valueOf(frameType));
            } else if (diagnosticListeners.updateRate()) {
                newRate = diagnosticListeners.getRate();
                shouldRestartDiagnostic = true;
            }
            this.mDiagnosticLock.unlock();
            Log.i(CarLog.TAG_DIAGNOSTIC, String.format("shouldStopDiagnostic = %s, shouldRestartDiagnostic = %s for type %s", Boolean.valueOf(shouldStopDiagnostic), Boolean.valueOf(shouldRestartDiagnostic), Integer.valueOf(frameType)));
            if (shouldStopDiagnostic) {
                stopDiagnostic(frameType);
            } else if (shouldRestartDiagnostic) {
                startDiagnostic(frameType, newRate);
            }
        } finally {
            this.mDiagnosticLock.unlock();
        }
    }

    private void stopDiagnostic(int frameType) {
        DiagnosticHalService diagnosticHal = getDiagnosticHal();
        if (diagnosticHal == null || !diagnosticHal.isReady()) {
            Log.w(CarLog.TAG_DIAGNOSTIC, "diagnosticHal not ready");
            return;
        }
        if (frameType == 0) {
            if (this.mLiveFrameDiagnosticRecord.disableIfNeeded()) {
                diagnosticHal.requestDiagnosticStop(0);
            }
        } else if (frameType == 1 && this.mFreezeFrameDiagnosticRecords.disableIfNeeded()) {
            diagnosticHal.requestDiagnosticStop(1);
        }
    }

    private DiagnosticHalService getDiagnosticHal() {
        return this.mDiagnosticHal;
    }

    public boolean isLiveFrameSupported() {
        return getDiagnosticHal().getDiagnosticCapabilities().isLiveFrameSupported();
    }

    public boolean isFreezeFrameNotificationSupported() {
        return getDiagnosticHal().getDiagnosticCapabilities().isFreezeFrameSupported();
    }

    public boolean isGetFreezeFrameSupported() {
        DiagnosticHalService.DiagnosticCapabilities diagnosticCapabilities = getDiagnosticHal().getDiagnosticCapabilities();
        return diagnosticCapabilities.isFreezeFrameInfoSupported() && diagnosticCapabilities.isFreezeFrameSupported();
    }

    public boolean isClearFreezeFramesSupported() {
        DiagnosticHalService.DiagnosticCapabilities diagnosticCapabilities = getDiagnosticHal().getDiagnosticCapabilities();
        return diagnosticCapabilities.isFreezeFrameClearSupported() && diagnosticCapabilities.isFreezeFrameSupported();
    }

    public boolean isSelectiveClearFreezeFramesSupported() {
        DiagnosticHalService.DiagnosticCapabilities diagnosticCapabilities = getDiagnosticHal().getDiagnosticCapabilities();
        return isClearFreezeFramesSupported() && diagnosticCapabilities.isSelectiveClearFreezeFramesSupported();
    }

    public CarDiagnosticEvent getLatestLiveFrame() {
        this.mLiveFrameDiagnosticRecord.lock();
        CarDiagnosticEvent liveFrame = this.mLiveFrameDiagnosticRecord.getLastEvent();
        this.mLiveFrameDiagnosticRecord.unlock();
        return liveFrame;
    }

    public long[] getFreezeFrameTimestamps() {
        this.mFreezeFrameDiagnosticRecords.lock();
        long[] timestamps = this.mFreezeFrameDiagnosticRecords.getFreezeFrameTimestamps();
        this.mFreezeFrameDiagnosticRecords.unlock();
        return timestamps;
    }

    public CarDiagnosticEvent getFreezeFrame(long timestamp) {
        this.mFreezeFrameDiagnosticRecords.lock();
        CarDiagnosticEvent freezeFrame = this.mFreezeFrameDiagnosticRecords.getEvent(timestamp);
        this.mFreezeFrameDiagnosticRecords.unlock();
        return freezeFrame;
    }

    public boolean clearFreezeFrames(long... timestamps) {
        this.mDiagnosticClearPermission.assertGranted();
        if (!isClearFreezeFramesSupported()) {
            return false;
        }
        if (timestamps != null && timestamps.length != 0 && !isSelectiveClearFreezeFramesSupported()) {
            return false;
        }
        this.mFreezeFrameDiagnosticRecords.lock();
        this.mDiagnosticHal.clearFreezeFrames(timestamps);
        this.mFreezeFrameDiagnosticRecords.clearEvents();
        this.mFreezeFrameDiagnosticRecords.unlock();
        return true;
    }

    private DiagnosticClient findDiagnosticClientLocked(ICarDiagnosticEventListener listener) {
        IBinder binder = listener.asBinder();
        for (DiagnosticClient diagnosticClient : this.mClients) {
            if (diagnosticClient.isHoldingListenerBinder(binder)) {
                return diagnosticClient;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeClient(DiagnosticClient diagnosticClient) {
        this.mDiagnosticLock.lock();
        try {
            for (int diagnostic : diagnosticClient.getDiagnosticArray()) {
                unregisterDiagnosticListener(diagnostic, diagnosticClient.getICarDiagnosticEventListener());
            }
            this.mClients.remove(diagnosticClient);
        } finally {
            this.mDiagnosticLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class DiagnosticClient implements Listeners.IListener {
        private final ICarDiagnosticEventListener mListener;
        private final Set<Integer> mActiveDiagnostics = new HashSet();
        private volatile boolean mActive = true;

        DiagnosticClient(ICarDiagnosticEventListener listener) {
            this.mListener = listener;
        }

        public boolean equals(Object o) {
            return (o instanceof DiagnosticClient) && this.mListener.asBinder() == ((DiagnosticClient) o).mListener.asBinder();
        }

        boolean isHoldingListenerBinder(IBinder listenerBinder) {
            return this.mListener.asBinder() == listenerBinder;
        }

        void addDiagnostic(int frameType) {
            this.mActiveDiagnostics.add(Integer.valueOf(frameType));
        }

        void removeDiagnostic(int frameType) {
            this.mActiveDiagnostics.remove(Integer.valueOf(frameType));
        }

        int getNumberOfActiveDiagnostic() {
            return this.mActiveDiagnostics.size();
        }

        int[] getDiagnosticArray() {
            return this.mActiveDiagnostics.stream().mapToInt($$Lambda$UV1wDVoVlbcxpr8zevj_aMFtUGw.INSTANCE).toArray();
        }

        ICarDiagnosticEventListener getICarDiagnosticEventListener() {
            return this.mListener;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.mListener.asBinder().unlinkToDeath(this, 0);
            CarDiagnosticService.this.removeClient(this);
        }

        void dispatchDiagnosticUpdate(List<CarDiagnosticEvent> events) {
            if (events.size() != 0 && this.mActive) {
                try {
                    this.mListener.onDiagnosticEvents(events);
                } catch (RemoteException e) {
                }
            }
        }

        @Override // com.android.car.Listeners.IListener
        public void release() {
            if (this.mActive) {
                this.mListener.asBinder().unlinkToDeath(this, 0);
                this.mActiveDiagnostics.clear();
                this.mActive = false;
            }
        }
    }

    private static abstract class DiagnosticRecord {
        protected boolean mEnabled = false;
        private final ReentrantLock mLock;

        abstract boolean disableIfNeeded();

        abstract CarDiagnosticEvent update(CarDiagnosticEvent carDiagnosticEvent);

        DiagnosticRecord(ReentrantLock lock) {
            this.mLock = lock;
        }

        void lock() {
            this.mLock.lock();
        }

        void unlock() {
            this.mLock.unlock();
        }

        boolean isEnabled() {
            return this.mEnabled;
        }

        void enable() {
            this.mEnabled = true;
        }
    }

    private static class LiveFrameRecord extends DiagnosticRecord {
        CarDiagnosticEvent mLastEvent;

        LiveFrameRecord(ReentrantLock lock) {
            super(lock);
            this.mLastEvent = null;
        }

        @Override // com.android.car.CarDiagnosticService.DiagnosticRecord
        boolean disableIfNeeded() {
            if (!this.mEnabled) {
                return false;
            }
            this.mEnabled = false;
            this.mLastEvent = null;
            return true;
        }

        @Override // com.android.car.CarDiagnosticService.DiagnosticRecord
        CarDiagnosticEvent update(CarDiagnosticEvent newEvent) {
            Objects.requireNonNull(newEvent);
            CarDiagnosticEvent carDiagnosticEvent = this.mLastEvent;
            if (carDiagnosticEvent == null || carDiagnosticEvent.isEarlierThan(newEvent)) {
                this.mLastEvent = newEvent;
            }
            return this.mLastEvent;
        }

        CarDiagnosticEvent getLastEvent() {
            return this.mLastEvent;
        }
    }

    private static class FreezeFrameRecord extends DiagnosticRecord {
        HashMap<Long, CarDiagnosticEvent> mEvents;

        FreezeFrameRecord(ReentrantLock lock) {
            super(lock);
            this.mEvents = new HashMap<>();
        }

        @Override // com.android.car.CarDiagnosticService.DiagnosticRecord
        boolean disableIfNeeded() {
            if (!this.mEnabled) {
                return false;
            }
            this.mEnabled = false;
            clearEvents();
            return true;
        }

        void clearEvents() {
            this.mEvents.clear();
        }

        @Override // com.android.car.CarDiagnosticService.DiagnosticRecord
        CarDiagnosticEvent update(CarDiagnosticEvent newEvent) {
            this.mEvents.put(Long.valueOf(newEvent.timestamp), newEvent);
            return newEvent;
        }

        long[] getFreezeFrameTimestamps() {
            return this.mEvents.keySet().stream().mapToLong(new ToLongFunction() { // from class: com.android.car.-$$Lambda$ELHKvd8JMVRD8rbALqYPKbDX2mM
                @Override // java.util.function.ToLongFunction
                public final long applyAsLong(Object obj) {
                    return ((Long) obj).longValue();
                }
            }).toArray();
        }

        CarDiagnosticEvent getEvent(long timestamp) {
            return this.mEvents.get(Long.valueOf(timestamp));
        }

        Iterable<CarDiagnosticEvent> getEvents() {
            return this.mEvents.values();
        }
    }

    @Override // com.android.car.CarServiceBase
    public void dump(final PrintWriter writer) {
        writer.println("*CarDiagnosticService*");
        writer.println("**last events for diagnostics**");
        if (this.mLiveFrameDiagnosticRecord.getLastEvent() != null) {
            writer.println("last live frame event: ");
            writer.println(this.mLiveFrameDiagnosticRecord.getLastEvent());
        }
        writer.println("freeze frame events: ");
        Iterable<CarDiagnosticEvent> events = this.mFreezeFrameDiagnosticRecords.getEvents();
        Objects.requireNonNull(writer);
        events.forEach(new Consumer() { // from class: com.android.car.-$$Lambda$iIXtk07mwjd9ZcasP_C4yfwPh0g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                writer.println((CarDiagnosticEvent) obj);
            }
        });
        writer.println("**clients**");
        try {
            for (DiagnosticClient client : this.mClients) {
                if (client != null) {
                    try {
                        writer.println("binder:" + client.mListener + " active diagnostics:" + Arrays.toString(client.getDiagnosticArray()));
                    } catch (ConcurrentModificationException e) {
                        writer.println("concurrent modification happened");
                    }
                } else {
                    writer.println("null client");
                }
            }
        } catch (ConcurrentModificationException e2) {
            writer.println("concurrent modification happened");
        }
        writer.println("**diagnostic listeners**");
        try {
            Iterator<Integer> it = this.mDiagnosticListeners.keySet().iterator();
            while (it.hasNext()) {
                int diagnostic = it.next().intValue();
                Listeners<DiagnosticClient> listeners = this.mDiagnosticListeners.get(Integer.valueOf(diagnostic));
                if (listeners != null) {
                    writer.println(" Diagnostic:" + diagnostic + " num client:" + listeners.getNumberOfClients() + " rate:" + listeners.getRate());
                }
            }
        } catch (ConcurrentModificationException e3) {
            writer.println("concurrent modification happened");
        }
    }
}
