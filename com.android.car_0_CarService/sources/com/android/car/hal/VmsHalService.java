package com.android.car.hal;

import android.car.vms.VmsAssociatedLayer;
import android.car.vms.VmsAvailableLayers;
import android.car.vms.VmsClient;
import android.car.vms.VmsClientManager;
import android.car.vms.VmsLayer;
import android.car.vms.VmsLayerDependency;
import android.car.vms.VmsSubscriptionHelper;
import android.car.vms.VmsSubscriptionState;
import android.content.Context;
import android.hardware.automotive.vehicle.V2_0.VehiclePropConfig;
import android.hardware.automotive.vehicle.V2_0.VehiclePropValue;
import android.hardware.automotive.vehicle.V2_0.VmsMessageType;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.HandlerThread;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.ArraySet;
import android.util.Log;
import com.android.car.CarLocalServices;
import com.android.car.CarServiceUtils;
import com.android.car.R;
import com.android.car.vms.VmsBrokerService;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public class VmsHalService extends HalServiceBase {
    private static final boolean DBG = false;
    private static final int HAL_PROPERTY_ID = 299895808;
    private static final int NUM_INTEGERS_IN_VMS_LAYER = 3;
    private static final String TAG = "VmsHalService";
    private static final int UNKNOWN_CLIENT_ID = -1;
    private VmsClient mClient;
    private final VmsClientManager.VmsClientCallback mClientCallback;
    private final int mClientMetricsProperty;
    private final int mCoreId;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private final BiFunction<Handler, VmsClientManager.VmsClientCallback, VmsClient> mInitVmsClient;
    private boolean mIsSupported;
    private final Object mLock;
    private final boolean mPropagatePropertyException;
    private final VmsSubscriptionHelper mSubscriptionHelper;
    private final VehicleHal mVehicleHal;
    private static final int[] SUPPORTED_PROPERTIES = {299895808};
    private static final byte[] DEFAULT_PUBLISHER_INFO = new byte[0];

    VmsHalService(Context context, VehicleHal vehicleHal) {
        this(context, vehicleHal, new Supplier() { // from class: com.android.car.hal.-$$Lambda$-xfp9icEnUYvJbxkgTT4aGLVw-8
            @Override // java.util.function.Supplier
            public final Object get() {
                return Long.valueOf(SystemClock.uptimeMillis());
            }
        }, new BiFunction() { // from class: com.android.car.hal.-$$Lambda$VmsHalService$wh5LCL2XV2jkmQOJ6jCJ_9Ae4LI
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return VmsHalService.initVmsClient((Handler) obj, (VmsClientManager.VmsClientCallback) obj2);
            }
        }, Build.IS_DEBUGGABLE);
    }

    VmsHalService(Context context, VehicleHal vehicleHal, Supplier<Long> getCoreId, BiFunction<Handler, VmsClientManager.VmsClientCallback, VmsClient> initVmsClient, boolean propagatePropertyException) {
        this.mHandlerThread = CarServiceUtils.getHandlerThread(getClass().getSimpleName());
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mSubscriptionHelper = new VmsSubscriptionHelper(new Consumer() { // from class: com.android.car.hal.-$$Lambda$VmsHalService$K4ONF2dLiQJKQWwRb1VvUbuz6T0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.setSubscriptions((Set) obj);
            }
        });
        this.mLock = new Object();
        this.mClientCallback = new VmsClientManager.VmsClientCallback() { // from class: com.android.car.hal.VmsHalService.1
            public void onClientConnected(VmsClient client) {
                Log.wtf(VmsHalService.TAG, "onClientConnnected triggered for local client");
            }

            public void onSubscriptionStateChanged(VmsSubscriptionState subscriptionState) {
                VmsHalService.this.setPropertyValue(VmsHalService.createSubscriptionStateMessage(11, subscriptionState));
            }

            public void onLayerAvailabilityChanged(VmsAvailableLayers availableLayers) {
                VmsHalService.this.setPropertyValue(VmsHalService.createAvailableLayersMessage(9, availableLayers));
            }

            public void onPacketReceived(int providerId, VmsLayer layer, byte[] packet) {
                VmsHalService.this.setPropertyValue(VmsHalService.createDataMessage(layer, providerId, packet));
            }
        };
        this.mVehicleHal = vehicleHal;
        this.mCoreId = (int) (getCoreId.get().longValue() % 2147483647L);
        this.mInitVmsClient = initVmsClient;
        this.mClientMetricsProperty = getClientMetricsProperty(context);
        this.mPropagatePropertyException = propagatePropertyException;
    }

    private static int getClientMetricsProperty(Context context) {
        int propId = context.getResources().getInteger(R.integer.vmsHalClientMetricsProperty);
        if (propId == 0) {
            Log.i(TAG, "Metrics collection disabled");
            return 0;
        }
        if (((-268435456) & propId) != 536870912) {
            Log.w(TAG, String.format("Metrics collection disabled, non-vendor property: 0x%x", Integer.valueOf(propId)));
            return 0;
        }
        Log.i(TAG, String.format("Metrics collection property: 0x%x", Integer.valueOf(propId)));
        return propId;
    }

    Handler getHandler() {
        return this.mHandler;
    }

    @Override // com.android.car.hal.HalServiceBase
    public int[] getAllSupportedProperties() {
        return SUPPORTED_PROPERTIES;
    }

    @Override // com.android.car.hal.HalServiceBase
    public void takeProperties(Collection<VehiclePropConfig> properties) {
        if (properties.isEmpty()) {
            return;
        }
        synchronized (this.mLock) {
            this.mIsSupported = true;
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void init() {
        synchronized (this.mLock) {
            if (!this.mIsSupported) {
                Log.i(TAG, "VmsHalService VHAL property not supported");
                return;
            }
            connectVmsClient();
            Log.i(TAG, "Initializing VmsHalService VHAL property");
            this.mVehicleHal.subscribeProperty(this, 299895808);
            this.mHandler.post(new Runnable() { // from class: com.android.car.hal.-$$Lambda$VmsHalService$oxlE_RTVBTaV_AjKxMu6mGGk8VU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$init$0$VmsHalService();
                }
            });
        }
    }

    public /* synthetic */ void lambda$init$0$VmsHalService() {
        setPropertyValue(createStartSessionMessage(this.mCoreId, -1));
    }

    @Override // com.android.car.hal.HalServiceBase
    public void release() {
        synchronized (this.mLock) {
            disconnectVmsClient();
            if (this.mIsSupported) {
                this.mVehicleHal.unsubscribeProperty(this, 299895808);
            }
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void dump(PrintWriter writer) {
        synchronized (this.mLock) {
            writer.println("*VMS HAL*");
            Object[] objArr = new Object[1];
            objArr[0] = this.mIsSupported ? "supported" : "unsupported";
            writer.printf("VmsProperty: %s\n", objArr);
            if (this.mClient == null) {
                writer.println("VmsClient: disconnected");
                return;
            }
            writer.println("VmsClient: connected");
            writer.printf("Subscriptions: %s\n", this.mSubscriptionHelper.getSubscriptions());
            writer.printf("AvailableLayers: %s\n", this.mClient.getAvailableLayers());
            writer.printf("SubscriptionState: %s\n", this.mClient.getSubscriptionState());
        }
    }

    public void dumpMetrics(FileDescriptor fd) {
        int i = this.mClientMetricsProperty;
        if (i == 0) {
            Log.w(TAG, "Metrics collection is disabled");
            return;
        }
        VehiclePropValue vehicleProp = null;
        try {
            vehicleProp = this.mVehicleHal.get(i);
        } catch (RuntimeException e) {
            Log.e(TAG, "While reading metrics from client", e);
        }
        if (vehicleProp == null) {
            return;
        }
        try {
            FileOutputStream fout = new FileOutputStream(fd);
            try {
                fout.write(CarServiceUtils.toByteArray(vehicleProp.value.bytes));
                fout.flush();
                fout.close();
            } finally {
            }
        } catch (IOException e2) {
            Log.e(TAG, "Error writing metrics to output stream", e2);
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void onHalEvents(List<VehiclePropValue> values) {
        for (VehiclePropValue v : values) {
            ArrayList<Integer> vec = v.value.int32Values;
            int messageType = vec.get(0).intValue();
            if (messageType == 12) {
                handleDataEvent(vec, CarServiceUtils.toByteArray(v.value.bytes));
            } else if (messageType == 13) {
                handlePublisherIdRequest(CarServiceUtils.toByteArray(v.value.bytes));
            } else {
                if (messageType == 15) {
                    handlePublisherInfoRequest(vec);
                } else if (messageType != 17) {
                    switch (messageType) {
                        case 1:
                            handleSubscribeEvent(vec);
                            continue;
                        case 2:
                            handleSubscribeToPublisherEvent(vec);
                            continue;
                        case 3:
                            handleUnsubscribeEvent(vec);
                            continue;
                        case 4:
                            handleUnsubscribeFromPublisherEvent(vec);
                            continue;
                        case 5:
                            break;
                        case 6:
                            handleAvailabilityRequestEvent();
                            continue;
                        case 7:
                            handleSubscriptionsRequestEvent();
                            continue;
                        default:
                            try {
                                Log.e(TAG, "Unexpected message type: " + messageType);
                                continue;
                            } catch (IndexOutOfBoundsException e) {
                                Log.e(TAG, "While handling " + VmsMessageType.toString(messageType), e);
                            }
                            break;
                    }
                } else {
                    handleStartSessionEvent(vec);
                }
                handleOfferingEvent(vec);
            }
        }
    }

    private void connectVmsClient() {
        synchronized (this.mLock) {
            this.mClient = this.mInitVmsClient.apply(this.mHandler, this.mClientCallback);
        }
    }

    private void disconnectVmsClient() {
        synchronized (this.mLock) {
            if (this.mClient != null) {
                try {
                    this.mClient.unregister();
                } catch (RemoteException e) {
                    Log.wtf(TAG, "Local broker should not throw RemoteException", e);
                }
                this.mClient = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static VmsClient initVmsClient(Handler handler, VmsClientManager.VmsClientCallback callback) {
        VmsBrokerService brokerService = (VmsBrokerService) CarLocalServices.getService(VmsBrokerService.class);
        if (brokerService == null) {
            Log.e(TAG, "Broker service is not enabled");
            return null;
        }
        VmsClient client = new VmsClient(brokerService, new HandlerExecutor(handler), callback, true, false, new Consumer() { // from class: com.android.car.hal.-$$Lambda$VmsHalService$7nxmG74rxiOwc20kLwdsNif-Hgc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                VmsHalService.lambda$initVmsClient$1((RemoteException) obj);
            }
        });
        try {
            client.register();
        } catch (RemoteException e) {
            Log.wtf(TAG, "Local broker should not throw RemoteException", e);
        }
        return client;
    }

    static /* synthetic */ void lambda$initVmsClient$1(RemoteException ignored) {
    }

    private VmsClient getVmsClient() {
        VmsClient vmsClient;
        synchronized (this.mLock) {
            if (this.mClient == null) {
                throw new IllegalStateException("VmsClient is not connected");
            }
            vmsClient = this.mClient;
        }
        return vmsClient;
    }

    private void handleStartSessionEvent(List<Integer> message) {
        int coreId = message.get(1).intValue();
        int clientId = message.get(2).intValue();
        Log.i(TAG, "Starting new session with coreId: " + coreId + " client: " + clientId);
        if (coreId != this.mCoreId) {
            disconnectVmsClient();
            connectVmsClient();
            setPropertyValue(createStartSessionMessage(this.mCoreId, clientId));
        }
        this.mClientCallback.onLayerAvailabilityChanged(getVmsClient().getAvailableLayers());
    }

    private void handleDataEvent(List<Integer> message, byte[] payload) {
        VmsLayer vmsLayer = parseVmsLayerFromMessage(message);
        int publisherId = parsePublisherIdFromMessage(message);
        getVmsClient().publishPacket(publisherId, vmsLayer, payload);
    }

    private void handleSubscribeEvent(List<Integer> message) {
        VmsLayer vmsLayer = parseVmsLayerFromMessage(message);
        this.mSubscriptionHelper.subscribe(vmsLayer);
    }

    private void handleSubscribeToPublisherEvent(List<Integer> message) {
        VmsLayer vmsLayer = parseVmsLayerFromMessage(message);
        int publisherId = parsePublisherIdFromMessage(message);
        this.mSubscriptionHelper.subscribe(vmsLayer, publisherId);
    }

    private void handleUnsubscribeEvent(List<Integer> message) {
        VmsLayer vmsLayer = parseVmsLayerFromMessage(message);
        this.mSubscriptionHelper.unsubscribe(vmsLayer);
    }

    private void handleUnsubscribeFromPublisherEvent(List<Integer> message) {
        VmsLayer vmsLayer = parseVmsLayerFromMessage(message);
        int publisherId = parsePublisherIdFromMessage(message);
        this.mSubscriptionHelper.unsubscribe(vmsLayer, publisherId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSubscriptions(Set<VmsAssociatedLayer> subscriptions) {
        getVmsClient().setSubscriptions(subscriptions);
    }

    private void handlePublisherIdRequest(byte[] payload) {
        VehiclePropValue vehicleProp = createVmsMessage(14);
        vehicleProp.value.int32Values.add(Integer.valueOf(getVmsClient().registerProvider(payload)));
        setPropertyValue(vehicleProp);
    }

    private void handlePublisherInfoRequest(List<Integer> message) {
        int publisherId = message.get(1).intValue();
        VehiclePropValue vehicleProp = createVmsMessage(16);
        byte[] publisherInfo = getVmsClient().getProviderDescription(publisherId);
        appendBytes(vehicleProp.value.bytes, publisherInfo != null ? publisherInfo : DEFAULT_PUBLISHER_INFO);
        setPropertyValue(vehicleProp);
    }

    private void handleOfferingEvent(List<Integer> message) {
        int publisherId = message.get(1).intValue();
        int numLayerDependencies = message.get(2).intValue();
        Set<VmsLayerDependency> offeredLayers = new ArraySet<>(numLayerDependencies);
        int numDependenciesForLayer = 3;
        for (int i = 0; i < numLayerDependencies; i++) {
            VmsLayer offeredLayer = parseVmsLayerAtIndex(message, numDependenciesForLayer);
            int idx = numDependenciesForLayer + 3;
            int idx2 = idx + 1;
            int numDependenciesForLayer2 = message.get(idx).intValue();
            if (numDependenciesForLayer2 == 0) {
                offeredLayers.add(new VmsLayerDependency(offeredLayer));
            } else {
                Set<VmsLayer> dependencies = new HashSet<>();
                for (int j = 0; j < numDependenciesForLayer2; j++) {
                    VmsLayer dependantLayer = parseVmsLayerAtIndex(message, idx2);
                    idx2 += 3;
                    dependencies.add(dependantLayer);
                }
                offeredLayers.add(new VmsLayerDependency(offeredLayer, dependencies));
            }
            numDependenciesForLayer = idx2;
        }
        getVmsClient().setProviderOfferings(publisherId, offeredLayers);
    }

    private void handleAvailabilityRequestEvent() {
        setPropertyValue(createAvailableLayersMessage(8, getVmsClient().getAvailableLayers()));
    }

    private void handleSubscriptionsRequestEvent() {
        setPropertyValue(createSubscriptionStateMessage(10, getVmsClient().getSubscriptionState()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPropertyValue(VehiclePropValue vehicleProp) {
        int messageType = vehicleProp.value.int32Values.get(0).intValue();
        synchronized (this.mLock) {
            if (!this.mIsSupported) {
                Log.w(TAG, "HAL unsupported while attempting to send " + VmsMessageType.toString(messageType));
                return;
            }
            try {
                this.mVehicleHal.set(vehicleProp);
            } catch (RuntimeException e) {
                Log.e(TAG, "While sending " + VmsMessageType.toString(messageType), e);
                if (this.mPropagatePropertyException) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    private static VehiclePropValue createStartSessionMessage(int coreId, int clientId) {
        VehiclePropValue vehicleProp = createVmsMessage(17);
        List<Integer> message = vehicleProp.value.int32Values;
        message.add(Integer.valueOf(coreId));
        message.add(Integer.valueOf(clientId));
        return vehicleProp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static VehiclePropValue createDataMessage(VmsLayer layer, int publisherId, byte[] payload) {
        VehiclePropValue vehicleProp = createVmsMessage(12);
        appendLayer(vehicleProp.value.int32Values, layer);
        List<Integer> message = vehicleProp.value.int32Values;
        message.add(Integer.valueOf(publisherId));
        appendBytes(vehicleProp.value.bytes, payload);
        return vehicleProp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static VehiclePropValue createSubscriptionStateMessage(int messageType, VmsSubscriptionState subscriptionState) {
        VehiclePropValue vehicleProp = createVmsMessage(messageType);
        List<Integer> message = vehicleProp.value.int32Values;
        message.add(Integer.valueOf(subscriptionState.getSequenceNumber()));
        Set<VmsLayer> layers = subscriptionState.getLayers();
        Set<VmsAssociatedLayer> associatedLayers = subscriptionState.getAssociatedLayers();
        message.add(Integer.valueOf(layers.size()));
        message.add(Integer.valueOf(associatedLayers.size()));
        for (VmsLayer layer : layers) {
            appendLayer(message, layer);
        }
        for (VmsAssociatedLayer layer2 : associatedLayers) {
            appendAssociatedLayer(message, layer2);
        }
        return vehicleProp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static VehiclePropValue createAvailableLayersMessage(int messageType, VmsAvailableLayers availableLayers) {
        VehiclePropValue vehicleProp = createVmsMessage(messageType);
        List<Integer> message = vehicleProp.value.int32Values;
        message.add(Integer.valueOf(availableLayers.getSequence()));
        message.add(Integer.valueOf(availableLayers.getAssociatedLayers().size()));
        for (VmsAssociatedLayer layer : availableLayers.getAssociatedLayers()) {
            appendAssociatedLayer(message, layer);
        }
        return vehicleProp;
    }

    private static VehiclePropValue createVmsMessage(int messageType) {
        VehiclePropValue vehicleProp = new VehiclePropValue();
        vehicleProp.prop = 299895808;
        vehicleProp.areaId = 0;
        vehicleProp.value.int32Values.add(Integer.valueOf(messageType));
        return vehicleProp;
    }

    private static void appendLayer(List<Integer> message, VmsLayer layer) {
        message.add(Integer.valueOf(layer.getType()));
        message.add(Integer.valueOf(layer.getSubtype()));
        message.add(Integer.valueOf(layer.getVersion()));
    }

    private static void appendAssociatedLayer(List<Integer> message, VmsAssociatedLayer layer) {
        message.add(Integer.valueOf(layer.getVmsLayer().getType()));
        message.add(Integer.valueOf(layer.getVmsLayer().getSubtype()));
        message.add(Integer.valueOf(layer.getVmsLayer().getVersion()));
        message.add(Integer.valueOf(layer.getProviderIds().size()));
        message.addAll(layer.getProviderIds());
    }

    private static void appendBytes(ArrayList<Byte> dst, byte[] src) {
        dst.ensureCapacity(src.length);
        for (byte b : src) {
            dst.add(Byte.valueOf(b));
        }
    }

    private static VmsLayer parseVmsLayerFromMessage(List<Integer> message) {
        return parseVmsLayerAtIndex(message, 1);
    }

    private static VmsLayer parseVmsLayerAtIndex(List<Integer> message, int index) {
        List<Integer> layerValues = message.subList(index, index + 3);
        return new VmsLayer(layerValues.get(0).intValue(), layerValues.get(1).intValue(), layerValues.get(2).intValue());
    }

    private static int parsePublisherIdFromMessage(List<Integer> message) {
        return message.get(4).intValue();
    }
}
