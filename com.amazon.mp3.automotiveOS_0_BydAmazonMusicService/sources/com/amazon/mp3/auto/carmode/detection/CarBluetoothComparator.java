package com.amazon.mp3.auto.carmode.detection;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.amazon.mp3.auto.carmode.detection.CarDetectionDataLoader;
import com.amazon.mp3.util.Log;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: CarBluetoothComparator.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothComparator;", "", "", "deviceName", "", "matchCar", "matchNonCar", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/auto/carmode/detection/CarDetectionDataLoader;", "carDetectionDataLoader", "Lcom/amazon/mp3/auto/carmode/detection/CarDetectionDataLoader;", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarBluetoothComparator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = CarBluetoothComparator.class.getSimpleName();
    private static List<CarDetectionDataLoader.CarDetectionRegex> carExcludeRegexList;
    private static List<CarDetectionDataLoader.CarDetectionRegex> carIncludeRegexList;
    private static Set<String> carNameSet;
    private final CarDetectionDataLoader carDetectionDataLoader;
    private final Context context;

    public CarBluetoothComparator(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.carDetectionDataLoader = new CarDetectionDataLoader(context);
    }

    /* JADX INFO: compiled from: CarBluetoothComparator.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothComparator$Companion;", "", "()V", "CAR_DETECTION_EXCLUDE_REGEX_FILE", "", "CAR_DETECTION_INCLUDE_REGEX_FILE", "CAR_DETECTION_NAME_FILE", "TAG", "kotlin.jvm.PlatformType", "carExcludeRegexList", "", "Lcom/amazon/mp3/auto/carmode/detection/CarDetectionDataLoader$CarDetectionRegex;", "carIncludeRegexList", "carNameSet", "", "compareCarDetectionBluetoothClass", "", "device", "Landroid/bluetooth/BluetoothDevice;", "initCarDetectionData", "", "context", "Landroid/content/Context;", "isCarDetectionDataReady", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean compareCarDetectionBluetoothClass(BluetoothDevice device) {
            Intrinsics.checkNotNullParameter(device, "device");
            BluetoothClass bluetoothClass = device.getBluetoothClass();
            Integer numValueOf = bluetoothClass == null ? null : Integer.valueOf(bluetoothClass.getDeviceClass());
            return (numValueOf != null && numValueOf.intValue() == 1056) || (numValueOf != null && numValueOf.intValue() == 1032);
        }

        public final synchronized void initCarDetectionData(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            CarDetectionDataLoader carDetectionDataLoader = new CarDetectionDataLoader(context);
            CarBluetoothComparator.carNameSet = carDetectionDataLoader.loadCarNameTxt("car-device-names.txt");
            CarBluetoothComparator.carIncludeRegexList = carDetectionDataLoader.loadCarRegexJson("car-include-regexes.json");
            CarBluetoothComparator.carExcludeRegexList = carDetectionDataLoader.loadCarRegexJson("car-exclude-regexes.json");
        }

        public final boolean isCarDetectionDataReady() {
            return (CarBluetoothComparator.carNameSet == null || CarBluetoothComparator.carIncludeRegexList == null || CarBluetoothComparator.carExcludeRegexList == null) ? false : true;
        }
    }

    public final boolean matchCar(String deviceName) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        if (carNameSet == null) {
            carNameSet = this.carDetectionDataLoader.loadCarNameTxt("car-device-names.txt");
        }
        Set<String> set = carNameSet;
        if (set != null && set.contains(StringsKt__StringsKt.trim((CharSequence) deviceName).toString())) {
            return true;
        }
        if (carIncludeRegexList == null) {
            carIncludeRegexList = this.carDetectionDataLoader.loadCarRegexJson("car-include-regexes.json");
        }
        List<CarDetectionDataLoader.CarDetectionRegex> list = carIncludeRegexList;
        if (list != null) {
            for (CarDetectionDataLoader.CarDetectionRegex carDetectionRegex : list) {
                if (carDetectionRegex.getPattern().matcher(deviceName).find()) {
                    Log.debug(TAG, "Car detection regex matches car type " + carDetectionRegex.getType() + " by bluetooth name " + deviceName);
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean matchNonCar(String deviceName) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        if (carExcludeRegexList == null) {
            carExcludeRegexList = this.carDetectionDataLoader.loadCarRegexJson("car-exclude-regexes.json");
        }
        List<CarDetectionDataLoader.CarDetectionRegex> list = carExcludeRegexList;
        if (list == null) {
            return false;
        }
        for (CarDetectionDataLoader.CarDetectionRegex carDetectionRegex : list) {
            if (carDetectionRegex.getPattern().matcher(deviceName).find()) {
                Log.debug(TAG, "Car detection exclusion regex matches device type " + carDetectionRegex.getType() + " by bluetooth name " + deviceName);
                return true;
            }
        }
        return false;
    }
}
