package defpackage;

import android.os.Build;
import android.util.Pair;
import java.util.Map;
import org.chromium.base.CollectionUtil;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: DeviceInfoFeedbackSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class i90 implements gm0 {
    @Override // defpackage.gm0
    public final Map<String, String> c() {
        return CollectionUtil.d(Pair.create("device_name", Build.DEVICE), Pair.create("device_type", DeviceFormFactor.a(xz.a) ? "tablet" : "phone"));
    }
}
