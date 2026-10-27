package com.amazon.mp3.bluetooth;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.util.extensions.ContextKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BluetoothPermissionViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\u001f\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel;", "Landroidx/lifecycle/ViewModel;", "Lcom/amazon/mp3/bluetooth/BluetoothPermissionActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "", "checkPermissionState", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel$BluetoothAction;", "_action", "Landroidx/lifecycle/MutableLiveData;", "Landroidx/lifecycle/LiveData;", "getAction", "()Landroidx/lifecycle/LiveData;", "action", "<init>", "()V", "BluetoothAction", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BluetoothPermissionViewModel extends ViewModel {
    private final String TAG = BluetoothPermissionActivity.class.getSimpleName();
    private final MutableLiveData<BluetoothAction> _action = new MutableLiveData<>();

    public final LiveData<BluetoothAction> getAction() {
        return this._action;
    }

    /* JADX INFO: compiled from: BluetoothPermissionViewModel.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel$BluetoothAction;", "", "()V", "Finish", "ShowSettings", "ShowSystemDialog", "Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel$BluetoothAction$ShowSystemDialog;", "Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel$BluetoothAction$ShowSettings;", "Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel$BluetoothAction$Finish;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static abstract class BluetoothAction {
        public /* synthetic */ BluetoothAction(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: BluetoothPermissionViewModel.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel$BluetoothAction$ShowSystemDialog;", "Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel$BluetoothAction;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class ShowSystemDialog extends BluetoothAction {
            public static final ShowSystemDialog INSTANCE = new ShowSystemDialog();

            private ShowSystemDialog() {
                super(null);
            }
        }

        private BluetoothAction() {
        }

        /* JADX INFO: compiled from: BluetoothPermissionViewModel.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel$BluetoothAction$ShowSettings;", "Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel$BluetoothAction;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class ShowSettings extends BluetoothAction {
            public static final ShowSettings INSTANCE = new ShowSettings();

            private ShowSettings() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: BluetoothPermissionViewModel.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel$BluetoothAction$Finish;", "Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel$BluetoothAction;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class Finish extends BluetoothAction {
            public static final Finish INSTANCE = new Finish();

            private Finish() {
                super(null);
            }
        }
    }

    public final void checkPermissionState(BluetoothPermissionActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (ContextKt.isPermissionGranted(activity, "android.permission.BLUETOOTH_CONNECT")) {
            this._action.setValue(BluetoothAction.Finish.INSTANCE);
        } else if (!BluetoothUtil.INSTANCE.isFirstTimeRequestingPermission(activity) && !ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.BLUETOOTH_CONNECT")) {
            this._action.setValue(BluetoothAction.ShowSettings.INSTANCE);
        } else {
            this._action.setValue(BluetoothAction.ShowSystemDialog.INSTANCE);
        }
    }
}
