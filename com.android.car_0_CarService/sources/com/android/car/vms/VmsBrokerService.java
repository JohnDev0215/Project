package com.android.car.vms;

import android.car.vms.IVmsBrokerService;
import android.car.vms.IVmsClientCallback;
import android.car.vms.VmsAssociatedLayer;
import android.car.vms.VmsAvailableLayers;
import android.car.vms.VmsLayer;
import android.car.vms.VmsLayerDependency;
import android.car.vms.VmsLayersOffering;
import android.car.vms.VmsProviderInfo;
import android.car.vms.VmsRegistrationInfo;
import android.car.vms.VmsSubscriptionState;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SharedMemory;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import com.android.car.CarServiceBase;
import com.android.car.ICarImpl;
import com.android.car.stats.CarStatsService;
import com.android.internal.util.FunctionalUtils;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
public class VmsBrokerService extends IVmsBrokerService.Stub implements CarServiceBase {
    private static final boolean DBG = false;
    private static final String TAG = VmsBrokerService.class.getSimpleName();
    private Set<VmsLayersOffering> mAllOfferings;
    private final VmsLayerAvailability mAvailableLayers;
    private final Map<IBinder, VmsClientInfo> mClientMap;
    private final Context mContext;
    private final IntSupplier mGetCallingUid;
    private final Object mLock;
    private final PackageManager mPackageManager;
    private final VmsProviderInfoStore mProviderInfoStore;
    private final CarStatsService mStatsService;
    private VmsSubscriptionState mSubscriptionState;

    public VmsBrokerService(Context context, CarStatsService statsService) {
        this(context, statsService, new IntSupplier() { // from class: com.android.car.vms.-$$Lambda$OLUSIA110KxM3wbFP4L-5xrTvHw
            @Override // java.util.function.IntSupplier
            public final int getAsInt() {
                return Binder.getCallingUid();
            }
        });
    }

    VmsBrokerService(Context context, CarStatsService statsService, IntSupplier getCallingUid) {
        this.mProviderInfoStore = new VmsProviderInfoStore();
        this.mAvailableLayers = new VmsLayerAvailability();
        this.mLock = new Object();
        this.mClientMap = new ArrayMap();
        this.mAllOfferings = Collections.emptySet();
        this.mSubscriptionState = new VmsSubscriptionState(0, Collections.emptySet(), Collections.emptySet());
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
        this.mStatsService = statsService;
        this.mGetCallingUid = getCallingUid;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
    }

    @Override // com.android.car.CarServiceBase
    public void dump(final PrintWriter writer) {
        writer.println("*" + TAG + "*");
        synchronized (this.mLock) {
            writer.println("mAvailableLayers: " + this.mAvailableLayers.getAvailableLayers());
            writer.println();
            writer.println("mSubscriptionState: " + this.mSubscriptionState);
            writer.println();
            writer.println("mClientMap:");
            this.mClientMap.values().stream().sorted(Comparator.comparingInt(new ToIntFunction() { // from class: com.android.car.vms.-$$Lambda$FpH67nLkR_C85Cd0ZOjn7gzGS4o
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((VmsClientInfo) obj).getUid();
                }
            })).forEach(new Consumer() { // from class: com.android.car.vms.-$$Lambda$VmsBrokerService$-_Rz4qBBgg7g-IPwKIBnM8AFbyQ
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((VmsClientInfo) obj).dump(writer, "  ");
                }
            });
        }
    }

    public VmsRegistrationInfo registerClient(final IBinder clientToken, IVmsClientCallback callback, boolean legacyClient) {
        VmsRegistrationInfo vmsRegistrationInfo;
        ICarImpl.assertAnyVmsPermission(this.mContext);
        int clientUid = this.mGetCallingUid.getAsInt();
        String clientPackage = this.mPackageManager.getNameForUid(clientUid);
        this.mStatsService.getVmsClientLogger(clientUid).logConnectionState(2);
        try {
            IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() { // from class: com.android.car.vms.-$$Lambda$VmsBrokerService$kqNXWn7xTnedJ7XhMzb9w4rKC5g
                @Override // android.os.IBinder.DeathRecipient
                public final void binderDied() {
                    this.f$0.lambda$registerClient$1$VmsBrokerService(clientToken);
                }
            };
            callback.asBinder().linkToDeath(deathRecipient, 0);
            synchronized (this.mLock) {
                this.mClientMap.put(clientToken, new VmsClientInfo(clientUid, clientPackage, callback, legacyClient, deathRecipient));
                vmsRegistrationInfo = new VmsRegistrationInfo(this.mAvailableLayers.getAvailableLayers(), this.mSubscriptionState);
            }
            return vmsRegistrationInfo;
        } catch (RemoteException e) {
            this.mStatsService.getVmsClientLogger(clientUid).logConnectionState(3);
            throw new IllegalStateException("Client callback is already dead");
        }
    }

    public /* synthetic */ void lambda$registerClient$1$VmsBrokerService(IBinder clientToken) {
        unregisterClient(clientToken, 3);
    }

    public void unregisterClient(IBinder clientToken) {
        ICarImpl.assertAnyVmsPermission(this.mContext);
        unregisterClient(clientToken, 4);
    }

    public VmsProviderInfo getProviderInfo(IBinder clientToken, int providerId) {
        ICarImpl.assertAnyVmsPermission(this.mContext);
        getClient(clientToken);
        return new VmsProviderInfo(this.mProviderInfoStore.getProviderInfo(providerId));
    }

    public void setSubscriptions(IBinder clientToken, List<VmsAssociatedLayer> layers) {
        ICarImpl.assertVmsSubscriberPermission(this.mContext);
        getClient(clientToken).setSubscriptions(layers);
        updateSubscriptionState();
    }

    public void setMonitoringEnabled(IBinder clientToken, boolean enabled) {
        ICarImpl.assertVmsSubscriberPermission(this.mContext);
        getClient(clientToken).setMonitoringEnabled(enabled);
    }

    public int registerProvider(IBinder clientToken, VmsProviderInfo providerInfo) {
        int providerId;
        ICarImpl.assertVmsPublisherPermission(this.mContext);
        VmsClientInfo client = getClient(clientToken);
        synchronized (this.mLock) {
            providerId = this.mProviderInfoStore.getProviderId(providerInfo.getDescription());
        }
        client.addProviderId(providerId);
        return providerId;
    }

    public void setProviderOfferings(IBinder clientToken, int providerId, List<VmsLayerDependency> offerings) {
        ICarImpl.assertVmsPublisherPermission(this.mContext);
        VmsClientInfo client = getClient(clientToken);
        if (!client.hasProviderId(providerId) && !client.isLegacyClient()) {
            throw new IllegalArgumentException("Client not registered to offer layers as " + providerId);
        }
        if (client.setProviderOfferings(providerId, offerings)) {
            updateAvailableLayers();
        }
    }

    public void publishPacket(IBinder clientToken, final int providerId, final VmsLayer layer, final byte[] packet) {
        ICarImpl.assertVmsPublisherPermission(this.mContext);
        deliverToSubscribers(clientToken, providerId, layer, packet.length, new FunctionalUtils.ThrowingConsumer() { // from class: com.android.car.vms.-$$Lambda$VmsBrokerService$Brxor4uUQ7buwKjzVZ4b08q1Jik
            public final void acceptOrThrow(Object obj) {
                ((IVmsClientCallback) obj).onPacketReceived(providerId, layer, packet);
            }
        });
    }

    public void publishLargePacket(IBinder clientToken, final int providerId, final VmsLayer layer, final SharedMemory packet) {
        try {
            ICarImpl.assertVmsPublisherPermission(this.mContext);
            deliverToSubscribers(clientToken, providerId, layer, packet.getSize(), new FunctionalUtils.ThrowingConsumer() { // from class: com.android.car.vms.-$$Lambda$VmsBrokerService$1Rk26BLpbsapV0SdBEc0HKK3jJM
                public final void acceptOrThrow(Object obj) {
                    ((IVmsClientCallback) obj).onLargePacketReceived(providerId, layer, packet);
                }
            });
            if (packet != null) {
                packet.close();
            }
        } catch (Throwable th) {
            if (packet != null) {
                try {
                    packet.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void deliverToSubscribers(IBinder clientToken, final int providerId, final VmsLayer layer, int packetLength, FunctionalUtils.ThrowingConsumer<IVmsClientCallback> callbackConsumer) {
        Collection<VmsClientInfo> subscribers;
        VmsClientInfo client = getClient(clientToken);
        if (!client.hasOffering(providerId, layer) && !client.isLegacyClient()) {
            throw new IllegalArgumentException("Client does not offer " + layer + " as " + providerId);
        }
        this.mStatsService.getVmsClientLogger(client.getUid()).logPacketSent(layer, packetLength);
        synchronized (this.mLock) {
            subscribers = (Collection) this.mClientMap.values().stream().filter(new Predicate() { // from class: com.android.car.vms.-$$Lambda$VmsBrokerService$aG9XzTVwYZpWliKljYozU5cfQrU
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((VmsClientInfo) obj).isSubscribed(providerId, layer);
                }
            }).collect(Collectors.toList());
        }
        if (subscribers.isEmpty()) {
            this.mStatsService.getVmsClientLogger(-1).logPacketDropped(layer, packetLength);
            return;
        }
        for (VmsClientInfo subscriber : subscribers) {
            try {
                callbackConsumer.accept(subscriber.getCallback());
                this.mStatsService.getVmsClientLogger(subscriber.getUid()).logPacketReceived(layer, packetLength);
            } catch (RuntimeException e) {
                this.mStatsService.getVmsClientLogger(subscriber.getUid()).logPacketDropped(layer, packetLength);
                Log.e(TAG, String.format("Unable to publish to listener: %s", subscriber.getPackageName()), e);
            }
        }
    }

    private void unregisterClient(IBinder clientToken, int connectionState) {
        VmsClientInfo client;
        synchronized (this.mLock) {
            client = this.mClientMap.remove(clientToken);
        }
        if (client != null) {
            client.getCallback().asBinder().unlinkToDeath(client.getDeathRecipient(), 0);
            this.mStatsService.getVmsClientLogger(client.getUid()).logConnectionState(connectionState);
            updateAvailableLayers();
            updateSubscriptionState();
        }
    }

    private VmsClientInfo getClient(IBinder clientToken) {
        VmsClientInfo client;
        synchronized (this.mLock) {
            client = this.mClientMap.get(clientToken);
            if (client == null) {
                throw new IllegalStateException("Unknown client token");
            }
        }
        return client;
    }

    private Collection<VmsClientInfo> getActiveClients() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mClientMap.values());
        }
        return arrayList;
    }

    private void updateAvailableLayers() {
        synchronized (this.mLock) {
            Set<VmsLayersOffering> allOfferings = (Set) this.mClientMap.values().stream().map(new Function() { // from class: com.android.car.vms.-$$Lambda$xUy-vfFwcZ-7oRxnHKWeNYeRl5o
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((VmsClientInfo) obj).getAllOfferings();
                }
            }).flatMap(new Function() { // from class: com.android.car.vms.-$$Lambda$72U6ffwsZ0Sm2BXYilXSg7hTsO8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Collection) obj).stream();
                }
            }).collect(Collectors.toCollection($$Lambda$Wul933eTbCG5LciVpnY1xFYbHAc.INSTANCE));
            if (this.mAllOfferings.equals(allOfferings)) {
                return;
            }
            this.mAllOfferings = allOfferings;
            this.mAvailableLayers.setPublishersOffering(allOfferings);
            notifyOfAvailabilityChange(this.mAvailableLayers.getAvailableLayers());
        }
    }

    private void notifyOfAvailabilityChange(VmsAvailableLayers availableLayers) {
        Log.i(TAG, "Notifying clients of layer availability change: " + availableLayers);
        for (VmsClientInfo client : getActiveClients()) {
            try {
                client.getCallback().onLayerAvailabilityChanged(availableLayers);
            } catch (RemoteException e) {
                Log.w(TAG, "onLayersAvailabilityChanged failed: " + client.getPackageName(), e);
            }
        }
    }

    private void updateSubscriptionState() {
        synchronized (this.mLock) {
            Set<VmsLayer> layerSubscriptions = new ArraySet<>();
            final Map<VmsLayer, Set<Integer>> layerAndProviderSubscriptions = new ArrayMap<>();
            for (VmsClientInfo client : this.mClientMap.values()) {
                layerSubscriptions.addAll(client.getLayerSubscriptions());
                client.getLayerAndProviderSubscriptions().forEach(new BiConsumer() { // from class: com.android.car.vms.-$$Lambda$VmsBrokerService$Nbq_bBLhUb56mu54m5TEKl28J10
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        VmsBrokerService.lambda$updateSubscriptionState$6(layerAndProviderSubscriptions, (VmsLayer) obj, (Set) obj2);
                    }
                });
            }
            Objects.requireNonNull(layerAndProviderSubscriptions);
            layerSubscriptions.forEach(new Consumer() { // from class: com.android.car.vms.-$$Lambda$6s5L8htgQklBp_orwTFJz9pd1w4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    layerAndProviderSubscriptions.remove((VmsLayer) obj);
                }
            });
            Set<VmsAssociatedLayer> associatedLayers = (Set) layerAndProviderSubscriptions.entrySet().stream().map(new Function() { // from class: com.android.car.vms.-$$Lambda$VmsBrokerService$_QPYpe2alf4eaIs4kH1Ogwz2Qtk
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return VmsBrokerService.lambda$updateSubscriptionState$7((Map.Entry) obj);
                }
            }).collect(Collectors.toCollection($$Lambda$Wul933eTbCG5LciVpnY1xFYbHAc.INSTANCE));
            if (this.mSubscriptionState.getLayers().equals(layerSubscriptions) && this.mSubscriptionState.getAssociatedLayers().equals(associatedLayers)) {
                return;
            }
            VmsSubscriptionState subscriptionState = new VmsSubscriptionState(this.mSubscriptionState.getSequenceNumber() + 1, layerSubscriptions, associatedLayers);
            this.mSubscriptionState = subscriptionState;
            notifyOfSubscriptionChange(subscriptionState);
        }
    }

    static /* synthetic */ void lambda$updateSubscriptionState$6(Map layerAndProviderSubscriptions, VmsLayer layer, Set providerIds) {
        Set<Integer> providerSubscriptions = (Set) layerAndProviderSubscriptions.computeIfAbsent(layer, new Function() { // from class: com.android.car.vms.-$$Lambda$VmsBrokerService$Bnqgyw94jjNaqwLI9P8xxPUvHOM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return VmsBrokerService.lambda$updateSubscriptionState$5((VmsLayer) obj);
            }
        });
        providerSubscriptions.addAll(providerIds);
    }

    static /* synthetic */ Set lambda$updateSubscriptionState$5(VmsLayer ignored) {
        return new ArraySet();
    }

    static /* synthetic */ VmsAssociatedLayer lambda$updateSubscriptionState$7(Map.Entry entry) {
        return new VmsAssociatedLayer((VmsLayer) entry.getKey(), (Set) entry.getValue());
    }

    private void notifyOfSubscriptionChange(VmsSubscriptionState subscriptionState) {
        Log.i(TAG, "Notifying clients of subscription state change: " + subscriptionState);
        for (VmsClientInfo client : getActiveClients()) {
            try {
                client.getCallback().onSubscriptionStateChanged(subscriptionState);
            } catch (RemoteException e) {
                Log.w(TAG, "onSubscriptionStateChanged failed: " + client.getPackageName(), e);
            }
        }
    }
}
