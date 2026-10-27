package com.stingray.karaoke.car.ui.viewmodel.base;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"com/stingray/karaoke/car/ui/viewmodel/base/LoadingInterface$LoadingState", "", "Lcom/stingray/karaoke/car/ui/viewmodel/base/LoadingInterface$LoadingState;", "<init>", "(Ljava/lang/String;I)V", "Loading", "Done", "Error", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LoadingInterface$LoadingState {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ LoadingInterface$LoadingState[] $VALUES;
    public static final LoadingInterface$LoadingState Loading = new LoadingInterface$LoadingState("Loading", 0);
    public static final LoadingInterface$LoadingState Done = new LoadingInterface$LoadingState("Done", 1);
    public static final LoadingInterface$LoadingState Error = new LoadingInterface$LoadingState("Error", 2);

    private static final /* synthetic */ LoadingInterface$LoadingState[] $values() {
        return new LoadingInterface$LoadingState[]{Loading, Done, Error};
    }

    static {
        LoadingInterface$LoadingState[] loadingInterface$LoadingStateArr$values = $values();
        $VALUES = loadingInterface$LoadingStateArr$values;
        $ENTRIES = r.e(loadingInterface$LoadingStateArr$values);
    }

    private LoadingInterface$LoadingState(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static LoadingInterface$LoadingState valueOf(String str) {
        return (LoadingInterface$LoadingState) Enum.valueOf(LoadingInterface$LoadingState.class, str);
    }

    public static LoadingInterface$LoadingState[] values() {
        return (LoadingInterface$LoadingState[]) $VALUES.clone();
    }
}
