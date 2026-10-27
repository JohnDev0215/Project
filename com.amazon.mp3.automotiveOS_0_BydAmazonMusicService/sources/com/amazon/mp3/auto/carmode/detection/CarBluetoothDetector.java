package com.amazon.mp3.auto.carmode.detection;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.carmode.detection.CarBluetoothComparator;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.ThrottleDistinctEvent;
import com.amazon.music.metrics.mts.event.definition.automotive.CarDetectionBluetoothEvent;
import com.amazon.music.metrics.mts.event.types.CarClassification;
import com.amazon.music.metrics.mts.event.types.CarSubDeviceAction;
import com.google.android.gms.common.Scopes;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarBluetoothDetector.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 72\u00020\u0001:\u0004897:B\u0019\u0012\b\u0010!\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010$\u001a\u00020#¢\u0006\u0004\b5\u00106J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\f\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u001c\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\u001c\u0010\u0011\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0006H\u0002J*\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u0012H\u0002J\u0010\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u001f\u001a\u00020\u0002H\u0014J\u000e\u0010 \u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0006R\u0016\u0010!\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R.\u0010'\u001a\b\u0018\u00010&R\u00020\u00008\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b'\u0010(\u0012\u0004\b-\u0010.\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0018\u00100\u001a\u0004\u0018\u00010/8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u00101R\u0016\u00103\u001a\u0002028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104¨\u0006;"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector;", "Lcom/amazon/mp3/auto/carmode/detection/SpecializedCarDetector;", "", "startBluetoothReceivers", "Landroid/bluetooth/BluetoothDevice;", "device", "Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector$ConnectionStatus;", "checkConnectionStatus", "Landroid/bluetooth/BluetoothAdapter;", "bluetoothAdapter", "", Scopes.PROFILE, "checkAlreadyConnectedDevices", "Landroid/content/Context;", "context", "", "onBluetoothDeviceConnected", "onBluetoothDeviceDisconnected", "", "address", NotificationCompat.CATEGORY_STATUS, "constructBluetoothId", "Lcom/amazon/music/metrics/mts/event/types/CarClassification;", "classification", "Lcom/amazon/music/metrics/mts/event/types/CarSubDeviceAction;", "action", "bluetoothId", "deviceName", "sendMetrics", "sendConnectEvent", "sendDisconnectEvent", "registerListeners", "isCarDevice", "mBluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "Lcom/amazon/music/metrics/MetricsManager;", "metricsManager", "Lcom/amazon/music/metrics/MetricsManager;", "Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector$BluetoothBroadcastReceiver;", "bluetoothBroadcastReceiver", "Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector$BluetoothBroadcastReceiver;", "getBluetoothBroadcastReceiver", "()Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector$BluetoothBroadcastReceiver;", "setBluetoothBroadcastReceiver", "(Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector$BluetoothBroadcastReceiver;)V", "getBluetoothBroadcastReceiver$annotations", "()V", "Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothComparator;", "carBluetoothComparator", "Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothComparator;", "Lcom/amazon/music/metrics/mts/event/ThrottleDistinctEvent;", "throttlingEvent", "Lcom/amazon/music/metrics/mts/event/ThrottleDistinctEvent;", "<init>", "(Landroid/bluetooth/BluetoothAdapter;Lcom/amazon/music/metrics/MetricsManager;)V", "Companion", "BluetoothBroadcastReceiver", "CarBluetoothServiceListener", "ConnectionStatus", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarBluetoothDetector extends SpecializedCarDetector {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = CarBluetoothDetector.class.getSimpleName();
    private static final Set<BluetoothDevice> connectedDevices = new LinkedHashSet();
    private BluetoothBroadcastReceiver bluetoothBroadcastReceiver;
    private CarBluetoothComparator carBluetoothComparator;
    private final BluetoothAdapter mBluetoothAdapter;
    private final MetricsManager metricsManager;
    private ThrottleDistinctEvent throttlingEvent;

    /* JADX INFO: compiled from: CarBluetoothDetector.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector$ConnectionStatus;", "", "(Ljava/lang/String;I)V", "CAR", "ADDITIONAL_DETECTION_CAR", "NON_CAR", "ADDITIONAL_DETECTION_NON_CAR", "DEVICE_NAME_ERROR", "ADDITIONAL_DETECTION_ERROR", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ConnectionStatus {
        CAR,
        ADDITIONAL_DETECTION_CAR,
        NON_CAR,
        ADDITIONAL_DETECTION_NON_CAR,
        DEVICE_NAME_ERROR,
        ADDITIONAL_DETECTION_ERROR
    }

    /* JADX INFO: compiled from: CarBluetoothDetector.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConnectionStatus.values().length];
            iArr[ConnectionStatus.CAR.ordinal()] = 1;
            iArr[ConnectionStatus.ADDITIONAL_DETECTION_CAR.ordinal()] = 2;
            iArr[ConnectionStatus.ADDITIONAL_DETECTION_NON_CAR.ordinal()] = 3;
            iArr[ConnectionStatus.ADDITIONAL_DETECTION_ERROR.ordinal()] = 4;
            iArr[ConnectionStatus.DEVICE_NAME_ERROR.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CarBluetoothDetector(BluetoothAdapter bluetoothAdapter, MetricsManager metricsManager) {
        Intrinsics.checkNotNullParameter(metricsManager, "metricsManager");
        this.mBluetoothAdapter = bluetoothAdapter;
        this.metricsManager = metricsManager;
        this.throttlingEvent = new ThrottleDistinctEvent(AmazonApplication.getCapabilities().getCarBluetoothEventThrottleValue(), new Consumer() { // from class: com.amazon.mp3.auto.carmode.detection.CarBluetoothDetector$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CarBluetoothDetector.m260throttlingEvent$lambda0(this.f$0, (MTSEvent) obj);
            }
        });
    }

    /* JADX INFO: compiled from: CarBluetoothDetector.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \n*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector$Companion;", "", "()V", "CAR_BLUETOOTH_CONNECT_ACTION", "", "CAR_BLUETOOTH_DISCONNECT_ACTION", "DEFAULT_THRESHOLD_MS", "", "SEPARATOR", "TAG", "kotlin.jvm.PlatformType", "UNKNOWN_DEVICE_ADDRESS", "UNKNOWN_DEVICE_NAME", "V1_LOGIC_NAME", "V1_UNION_V2_LOGIC_NAME", "V2_LOGIC_ERROR", "V2_LOGIC_NAME", "connectedDevices", "", "Landroid/bluetooth/BluetoothDevice;", "isCarBluetoothConnected", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isCarBluetoothConnected() {
            return !CarBluetoothDetector.connectedDevices.isEmpty();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: throttlingEvent$lambda-0, reason: not valid java name */
    public static final void m260throttlingEvent$lambda0(CarBluetoothDetector this$0, MTSEvent mTSEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.metricsManager.handleEvent(mTSEvent);
    }

    @Override // com.amazon.mp3.auto.carmode.detection.SpecializedCarDetector
    protected void registerListeners() {
        if (this.mBluetoothAdapter == null) {
            Log.error(TAG, "Device does not have a Bluetooth adapter. Stopping BT detection.");
            return;
        }
        connectedDevices.clear();
        checkAlreadyConnectedDevices(this.mBluetoothAdapter, 1);
        checkAlreadyConnectedDevices(this.mBluetoothAdapter, 2);
        this.carBluetoothComparator = new CarBluetoothComparator(getContext());
        startBluetoothReceivers();
    }

    private final void startBluetoothReceivers() {
        if (this.bluetoothBroadcastReceiver != null) {
            Log.warning(TAG, "Car Bluetooth broadcast receivers already started.");
            return;
        }
        Log.info(TAG, "Starting car bluetooth detection broadcast receivers.");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
        this.bluetoothBroadcastReceiver = new BluetoothBroadcastReceiver(this);
        getContext().registerReceiver(this.bluetoothBroadcastReceiver, intentFilter);
    }

    public final boolean isCarDevice(ConnectionStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        return status == ConnectionStatus.CAR || status == ConnectionStatus.ADDITIONAL_DETECTION_CAR;
    }

    private final ConnectionStatus checkConnectionStatus(BluetoothDevice device) {
        CarBluetoothComparator.Companion companion = CarBluetoothComparator.INSTANCE;
        if (companion.compareCarDetectionBluetoothClass(device)) {
            return ConnectionStatus.CAR;
        }
        Log.info(TAG, "Car Bluetooth device: %s", device.getName());
        String name = device.getName();
        if (name == null) {
            return ConnectionStatus.DEVICE_NAME_ERROR;
        }
        CarBluetoothComparator carBluetoothComparator = this.carBluetoothComparator;
        if (carBluetoothComparator == null) {
            return ConnectionStatus.ADDITIONAL_DETECTION_ERROR;
        }
        if (carBluetoothComparator != null) {
            if (carBluetoothComparator.matchNonCar(name)) {
                return ConnectionStatus.ADDITIONAL_DETECTION_NON_CAR;
            }
            if (carBluetoothComparator.matchCar(name)) {
                return ConnectionStatus.ADDITIONAL_DETECTION_CAR;
            }
            if (!companion.isCarDetectionDataReady()) {
                return ConnectionStatus.ADDITIONAL_DETECTION_ERROR;
            }
            return ConnectionStatus.ADDITIONAL_DETECTION_NON_CAR;
        }
        return ConnectionStatus.NON_CAR;
    }

    private final void checkAlreadyConnectedDevices(BluetoothAdapter bluetoothAdapter, int profile) {
        if (bluetoothAdapter.getProfileConnectionState(profile) == 2) {
            bluetoothAdapter.getProfileProxy(getContext(), new CarBluetoothServiceListener(this, getContext()), profile);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean onBluetoothDeviceConnected(Context context, BluetoothDevice device) {
        if (device == null) {
            return false;
        }
        connectedDevices.add(device);
        if (device.getName() == null || device.getAddress() == null) {
            Log.error(TAG, "Unable to send bluetooth metrics due to null device address or name");
            sendMetrics(CarClassification.NOT_CAR, CarSubDeviceAction.CONNECTED, "UNKNOWN_BLUETOOTH_DEVICE_ADDRESS", "UNKNOWN_BLUETOOTH_DEVICE_NAME");
            return false;
        }
        ConnectionStatus connectionStatusCheckConnectionStatus = checkConnectionStatus(device);
        if (isCarDevice(connectionStatusCheckConnectionStatus)) {
            Log.info(TAG, "Car Bluetooth device connected: %s", device.getName());
            CarClassification carClassification = CarClassification.CAR;
            CarSubDeviceAction carSubDeviceAction = CarSubDeviceAction.CONNECTED;
            String address = device.getAddress();
            Intrinsics.checkNotNullExpressionValue(address, "device.address");
            sendMetrics(carClassification, carSubDeviceAction, constructBluetoothId(address, connectionStatusCheckConnectionStatus), device.getName());
            ((Function2) getUpdateCarStatus()).mo4invoke(Boolean.TRUE, CarDetectionSource.BLUETOOTH);
            if (context != null) {
                sendConnectEvent(context);
            }
            return true;
        }
        Log.info(TAG, "Non-car Bluetooth device connected: %s", device.getName());
        CarClassification carClassification2 = CarClassification.NOT_CAR;
        CarSubDeviceAction carSubDeviceAction2 = CarSubDeviceAction.CONNECTED;
        String address2 = device.getAddress();
        Intrinsics.checkNotNullExpressionValue(address2, "device.address");
        sendMetrics(carClassification2, carSubDeviceAction2, constructBluetoothId(address2, connectionStatusCheckConnectionStatus), device.getName());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBluetoothDeviceDisconnected(Context context, BluetoothDevice device) {
        if (device == null) {
            return;
        }
        connectedDevices.remove(device);
        if (device.getName() == null || device.getAddress() == null) {
            Log.error(TAG, "Unable to send bluetooth metrics due to null device address or name");
            sendMetrics(CarClassification.NOT_CAR, CarSubDeviceAction.DISCONNECTED, "UNKNOWN_BLUETOOTH_DEVICE_ADDRESS", "UNKNOWN_BLUETOOTH_DEVICE_NAME");
            return;
        }
        ConnectionStatus connectionStatusCheckConnectionStatus = checkConnectionStatus(device);
        if (isCarDevice(connectionStatusCheckConnectionStatus)) {
            Log.info(TAG, "Car Bluetooth device disconnected: %s", device.getName());
            CarClassification carClassification = CarClassification.CAR;
            CarSubDeviceAction carSubDeviceAction = CarSubDeviceAction.DISCONNECTED;
            String address = device.getAddress();
            Intrinsics.checkNotNullExpressionValue(address, "device.address");
            sendMetrics(carClassification, carSubDeviceAction, constructBluetoothId(address, connectionStatusCheckConnectionStatus), device.getName());
            ((Function2) getUpdateCarStatus()).mo4invoke(Boolean.FALSE, CarDetectionSource.BLUETOOTH);
            if (context == null) {
                return;
            }
            sendDisconnectEvent(context);
            return;
        }
        Log.info(TAG, "Non-car Bluetooth device disconnected: %s", device.getName());
        CarClassification carClassification2 = CarClassification.NOT_CAR;
        CarSubDeviceAction carSubDeviceAction2 = CarSubDeviceAction.DISCONNECTED;
        String address2 = device.getAddress();
        Intrinsics.checkNotNullExpressionValue(address2, "device.address");
        sendMetrics(carClassification2, carSubDeviceAction2, constructBluetoothId(address2, connectionStatusCheckConnectionStatus), device.getName());
    }

    private final String constructBluetoothId(String address, ConnectionStatus status) {
        int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
        if (i == 1) {
            return Intrinsics.stringPlus(address, ";$;V1_Logic");
        }
        if (i == 2) {
            return Intrinsics.stringPlus(address, ";$;V2_Logic");
        }
        if (i != 3) {
            return (i == 4 || i == 5) ? Intrinsics.stringPlus(address, ";$;V2_Logic_Error") : address;
        }
        return Intrinsics.stringPlus(address, ";$;V1&V2_Logic");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendMetrics(CarClassification classification, CarSubDeviceAction action, String bluetoothId, String deviceName) {
        CarDetectionBluetoothEvent carDetectionBluetoothEvent;
        if (deviceName == null) {
            carDetectionBluetoothEvent = new CarDetectionBluetoothEvent(classification, action, bluetoothId, "UNKNOWN_BLUETOOTH_DEVICE_NAME");
        } else {
            carDetectionBluetoothEvent = new CarDetectionBluetoothEvent(classification, action, bluetoothId, deviceName);
        }
        this.throttlingEvent.postEvent(carDetectionBluetoothEvent);
    }

    /* JADX INFO: compiled from: CarBluetoothDetector.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector$CarBluetoothServiceListener;", "Landroid/bluetooth/BluetoothProfile$ServiceListener;", "", Scopes.PROFILE, "Landroid/bluetooth/BluetoothProfile;", "proxy", "", "checkConnectedBluetoothDevices", "onServiceDisconnected", "onServiceConnected", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "<init>", "(Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector;Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public final class CarBluetoothServiceListener implements BluetoothProfile.ServiceListener {
        private final Context context;
        final /* synthetic */ CarBluetoothDetector this$0;

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int profile) {
        }

        public CarBluetoothServiceListener(CarBluetoothDetector this$0, Context context) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.context = context;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (ContextCompat.checkSelfPermission(this.this$0.getContext(), "android.permission.BLUETOOTH_CONNECT") == 0 && Build.VERSION.SDK_INT >= 31) {
                checkConnectedBluetoothDevices(profile, proxy);
            } else if (Build.VERSION.SDK_INT < 31) {
                checkConnectedBluetoothDevices(profile, proxy);
            }
        }

        private final void checkConnectedBluetoothDevices(int profile, BluetoothProfile proxy) {
            if (proxy == null) {
                return;
            }
            for (BluetoothDevice bluetoothDevice : proxy.getConnectedDevices()) {
                Intrinsics.checkNotNullExpressionValue(bluetoothDevice, "proxy.connectedDevices");
                if (this.this$0.onBluetoothDeviceConnected(this.context, bluetoothDevice)) {
                    break;
                }
            }
            BluetoothAdapter.getDefaultAdapter().closeProfileProxy(profile, proxy);
        }
    }

    /* JADX INFO: compiled from: CarBluetoothDetector.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector$BluetoothBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector;)V", "onReceive", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class BluetoothBroadcastReceiver extends BroadcastReceiver {
        final /* synthetic */ CarBluetoothDetector this$0;

        public BluetoothBroadcastReceiver(CarBluetoothDetector this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ((intent == null ? null : intent.getAction()) == null) {
                return;
            }
            String action = intent.getAction();
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (!Intrinsics.areEqual(action, "android.bluetooth.adapter.action.STATE_CHANGED")) {
                String str = CarBluetoothDetector.TAG;
                Object[] objArr = new Object[2];
                objArr[0] = action;
                objArr[1] = bluetoothDevice != null ? bluetoothDevice.getName() : null;
                Log.debug(str, "Handling action %s for device %s", objArr);
                if (action != null) {
                    int iHashCode = action.hashCode();
                    if (iHashCode != -1492944353) {
                        if (iHashCode != -301431627) {
                            if (iHashCode == 1821585647 && action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                                this.this$0.onBluetoothDeviceDisconnected(context, bluetoothDevice);
                                return;
                            }
                        } else if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
                            this.this$0.onBluetoothDeviceConnected(context, bluetoothDevice);
                            return;
                        }
                    } else if (action.equals("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED")) {
                        this.this$0.onBluetoothDeviceDisconnected(context, bluetoothDevice);
                        return;
                    }
                }
                Log.warning(CarBluetoothDetector.TAG, "Unexpected action: %s", action);
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            if (intExtra == 10) {
                Log.info(CarBluetoothDetector.TAG, "Bluetooth adapter has turned off. Cannot detect cars via bluetooth.");
                if (bluetoothDevice != null) {
                    this.this$0.onBluetoothDeviceDisconnected(context, bluetoothDevice);
                    obj = Unit.INSTANCE;
                }
                if (obj == null) {
                    CarBluetoothDetector carBluetoothDetector = this.this$0;
                    carBluetoothDetector.sendMetrics(CarClassification.CAR, CarSubDeviceAction.DISCONNECTED, "UNKNOWN_BLUETOOTH_DEVICE_ADDRESS", "UNKNOWN_BLUETOOTH_DEVICE_NAME");
                    ((Function2) carBluetoothDetector.getUpdateCarStatus()).mo4invoke(Boolean.FALSE, CarDetectionSource.BLUETOOTH);
                    if (context != null) {
                        carBluetoothDetector.sendDisconnectEvent(context);
                    }
                }
            }
            if (intExtra == 12) {
                Log.info(CarBluetoothDetector.TAG, "Bluetooth adapter has turned on. We can now detect cars via bluetooth.");
            }
        }
    }

    private final void sendConnectEvent(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.amazon.mp3.auto.detection.Car_Bluetooth_Connect");
        context.sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendDisconnectEvent(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.amazon.mp3.auto.detection.Car_Bluetooth_Disconnect");
        context.sendBroadcast(intent);
    }
}
