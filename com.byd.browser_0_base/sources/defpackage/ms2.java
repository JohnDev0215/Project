package defpackage;

import java.util.HashMap;
import org.chromium.base.b;

/* JADX INFO: compiled from: StartupPaintPreviewMetrics.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ms2 {
    public static final HashMap d;
    public long a;
    public boolean b;
    public final b<a> c = new b<>();

    /* JADX INFO: compiled from: StartupPaintPreviewMetrics.java */
    public interface a {
        void a();

        void b(long j);
    }

    static {
        HashMap map = new HashMap();
        d = map;
        map.put(0, "Browser.PaintPreview.TabbedPlayer.UpTime.RemovedByPullToRefresh");
        map.put(1, "Browser.PaintPreview.TabbedPlayer.UpTime.RemovedBySnackBar");
        map.put(2, "Browser.PaintPreview.TabbedPlayer.UpTime.RemovedByCompositorFailure");
        map.put(3, "Browser.PaintPreview.TabbedPlayer.UpTime.RemovedOnLoad");
        map.put(4, "Browser.PaintPreview.TabbedPlayer.UpTime.RemovedByLinkClick");
        map.put(5, "Browser.PaintPreview.TabbedPlayer.UpTime.RemovedByNavigation");
        map.put(6, "Browser.PaintPreview.TabbedPlayer.UpTime.RemovedOnTabDestroy");
        map.put(7, "Browser.PaintPreview.TabbedPlayer.UpTime.RemovedOnTabHidden");
        map.put(8, "Browser.PaintPreview.TabbedPlayer.UpTime.RemovedOnOfflineAvailable");
        map.put(9, "Browser.PaintPreview.TabbedPlayer.UpTime.RemovedOnAccessibilityNotSupported");
    }
}
