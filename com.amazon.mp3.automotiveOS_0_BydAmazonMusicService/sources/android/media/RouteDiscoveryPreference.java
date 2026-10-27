package android.media;

import android.annotation.NonNull;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RouteDiscoveryPreference implements Parcelable {
    static {
        throw new NoClassDefFoundError();
    }

    @NonNull
    public native /* synthetic */ List<String> getPreferredFeatures();

    public native /* synthetic */ boolean shouldPerformActiveScan();
}
