package com.amazon.mp3.platform.providers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.platform.data.ConnectionType;
import com.amazon.music.platform.data.Listener;
import com.amazon.music.platform.data.NetworkInfo;
import com.amazon.music.platform.providers.NetworkInfoProvider;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: NetworkInfoProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0016\u0010\u0018\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0016J\b\u0010\u0019\u001a\u00020\u000eH\u0007J\b\u0010\u001a\u001a\u00020\u000eH\u0007J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/platform/providers/NetworkInfoProviderImpl;", "Lcom/amazon/music/platform/providers/NetworkInfoProvider;", "Landroid/content/BroadcastReceiver;", "Landroidx/lifecycle/LifecycleObserver;", "()V", "currentNetworkInfo", "Lcom/amazon/music/platform/data/NetworkInfo;", "listenerSet", "", "Lcom/amazon/music/platform/data/Listener;", "networkInfo", "getNetworkInfo", "()Lcom/amazon/music/platform/data/NetworkInfo;", "addNetworkChangeListener", "", "networkChangeListener", "callListeners", "getConnectionType", "Lcom/amazon/music/platform/data/ConnectionType;", "context", "Landroid/content/Context;", "onReceive", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "removeNetworkChangeListener", "startListening", "stopListening", "updateNetworkInfo", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class NetworkInfoProviderImpl extends BroadcastReceiver implements NetworkInfoProvider, LifecycleObserver {
    private final Set<Listener<NetworkInfo>> listenerSet = new LinkedHashSet();
    private NetworkInfo currentNetworkInfo = new NetworkInfo(false, ConnectionType.NoConnection.INSTANCE, false, false);

    public NetworkInfoProviderImpl() {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public final void startListening() {
        AmazonApplication.getApplication().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void stopListening() {
        AmazonApplication.getApplication().unregisterReceiver(this);
    }

    @Override // com.amazon.music.platform.providers.NetworkInfoProvider
    public synchronized NetworkInfo getNetworkInfo() {
        return this.currentNetworkInfo;
    }

    @Override // com.amazon.music.platform.providers.NetworkInfoProvider
    public synchronized void addNetworkChangeListener(Listener<NetworkInfo> networkChangeListener) {
        Intrinsics.checkNotNullParameter(networkChangeListener, "networkChangeListener");
        this.listenerSet.add(networkChangeListener);
    }

    @Override // com.amazon.music.platform.providers.NetworkInfoProvider
    public synchronized void removeNetworkChangeListener(Listener<NetworkInfo> networkChangeListener) {
        Intrinsics.checkNotNullParameter(networkChangeListener, "networkChangeListener");
        this.listenerSet.remove(networkChangeListener);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (updateNetworkInfo(context)) {
            callListeners();
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.NetworkInfoProviderImpl$callListeners$1, reason: invalid class name */
    /* JADX INFO: compiled from: NetworkInfoProviderImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.NetworkInfoProviderImpl$callListeners$1", f = "NetworkInfoProviderImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NetworkInfoProviderImpl.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                NetworkInfoProviderImpl networkInfoProviderImpl = NetworkInfoProviderImpl.this;
                synchronized (networkInfoProviderImpl) {
                    Iterator it = networkInfoProviderImpl.listenerSet.iterator();
                    while (it.hasNext()) {
                        ((Listener) it.next()).onEvent(networkInfoProviderImpl.currentNetworkInfo);
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final void callListeners() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    private final boolean updateNetworkInfo(Context context) {
        boolean zIsWifiOnlyDevice = ConnectivityUtil.isWifiOnlyDevice(context);
        boolean zIsDataRoaming = ConnectivityUtil.isDataRoaming(context);
        ConnectionType connectionType = getConnectionType(context);
        if (Intrinsics.areEqual(connectionType, this.currentNetworkInfo.getConnectionType()) && zIsDataRoaming == this.currentNetworkInfo.getIsRoaming() && zIsWifiOnlyDevice == this.currentNetworkInfo.getIsWiFiOnlyDevice()) {
            return false;
        }
        this.currentNetworkInfo = new NetworkInfo(!Intrinsics.areEqual(connectionType, ConnectionType.NoConnection.INSTANCE), connectionType, zIsWifiOnlyDevice, zIsDataRoaming);
        Log.info(Reflection.getOrCreateKotlinClass(NetworkInfoProviderImpl.class).getSimpleName(), Intrinsics.stringPlus("NetworkInfo has changed\n", this.currentNetworkInfo));
        return true;
    }

    private final ConnectionType getConnectionType(Context context) {
        if (ConnectivityUtil.isWifiConnected(context)) {
            return ConnectionType.WiFiConnection.INSTANCE;
        }
        if (ConnectivityUtil.hasAnyInternetConnection(context)) {
            int connectionType = ConnectivityUtil.getConnectionType(context);
            return new ConnectionType.DataConnection(connectionType != 3 ? connectionType != 4 ? "CONNECTION_TYPE_UNKNOWN" : "CONNECTION_TYPE_CELLULAR_HIGH_SPEED" : "CONNECTION_TYPE_CELLULAR_LOW_SPEED");
        }
        return ConnectionType.NoConnection.INSTANCE;
    }
}
