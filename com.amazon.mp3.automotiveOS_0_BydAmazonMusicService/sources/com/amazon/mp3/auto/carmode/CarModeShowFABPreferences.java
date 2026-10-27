package com.amazon.mp3.auto.carmode;

import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeShowFABPreferences.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModeShowFABPreferences;", "", "entryValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getEntryValue", "()Ljava/lang/String;", "getEntryResourceDescription", "", "getEntryResourceId", "ALWAYS", "IN_CAR", "NEVER", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum CarModeShowFABPreferences {
    ALWAYS("ALWAYS"),
    IN_CAR("IN_CAR"),
    NEVER("NEVER");


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String entryValue;

    /* JADX INFO: compiled from: CarModeShowFABPreferences.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CarModeShowFABPreferences.values().length];
            iArr[CarModeShowFABPreferences.ALWAYS.ordinal()] = 1;
            iArr[CarModeShowFABPreferences.IN_CAR.ordinal()] = 2;
            iArr[CarModeShowFABPreferences.NEVER.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final CarModeShowFABPreferences fromEntryValue(String str) {
        return INSTANCE.fromEntryValue(str);
    }

    public final int getEntryResourceDescription() {
        return -1;
    }

    CarModeShowFABPreferences(String str) {
        this.entryValue = str;
    }

    public final String getEntryValue() {
        return this.entryValue;
    }

    /* JADX INFO: compiled from: CarModeShowFABPreferences.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModeShowFABPreferences$Companion;", "", "()V", "fromEntryValue", "Lcom/amazon/mp3/auto/carmode/CarModeShowFABPreferences;", "entryValue", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CarModeShowFABPreferences fromEntryValue(String entryValue) {
            CarModeShowFABPreferences[] carModeShowFABPreferencesArrValues = CarModeShowFABPreferences.values();
            int length = carModeShowFABPreferencesArrValues.length;
            int i = 0;
            while (i < length) {
                CarModeShowFABPreferences carModeShowFABPreferences = carModeShowFABPreferencesArrValues[i];
                i++;
                if (Intrinsics.areEqual(carModeShowFABPreferences.getEntryValue(), entryValue)) {
                    return carModeShowFABPreferences;
                }
            }
            return CarModeShowFABPreferences.ALWAYS;
        }
    }

    public final int getEntryResourceId() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return R.string.dmusic_settings_car_mode_ingress_fab_always;
        }
        if (i == 2) {
            return R.string.dmusic_settings_car_mode_ingress_fab_in_car;
        }
        if (i == 3) {
            return R.string.dmusic_settings_car_mode_ingress_fab_never;
        }
        throw new NoWhenBranchMatchedException();
    }
}
