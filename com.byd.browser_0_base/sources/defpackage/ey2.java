package defpackage;

import J.N;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.chromium.base.CollectionUtil;
import org.chromium.base.LocaleUtils;

/* JADX INFO: compiled from: SystemInfoFeedbackSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class ey2 extends x8<StatFs> {
    @Override // defpackage.gm0
    public final Map<String, String> c() {
        x8<Result>.a aVar;
        HashMap mapD = CollectionUtil.d(Pair.create("CPU Architecture", N.MpdXuPgt()), Pair.create("Available Memory (MB)", Integer.toString(N.Mnh$RUKx())), Pair.create("Total Memory (MB)", Integer.toString(N.MEPW6xxL())), Pair.create("GPU Vendor", N.M3VLomue()), Pair.create("GPU Model", N.MZ6RjAhF()), Pair.create("UI Locale", LocaleUtils.getDefaultLocaleString()));
        try {
            aVar = this.a;
        } catch (InterruptedException | ExecutionException unused) {
        }
        Object objG = (aVar == null || aVar.b != 2) ? null : this.a.g();
        StatFs statFs = (StatFs) objG;
        if (statFs != null) {
            long blockSizeLong = statFs.getBlockSizeLong();
            long availableBlocksLong = (statFs.getAvailableBlocksLong() * blockSizeLong) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            long blockCountLong = (statFs.getBlockCountLong() * blockSizeLong) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            mapD.put("Available Storage (MB)", Long.toString(availableBlocksLong));
            mapD.put("Total Storage (MB)", Long.toString(blockCountLong));
        }
        return mapD;
    }
}
