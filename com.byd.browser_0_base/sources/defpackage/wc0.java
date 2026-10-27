package defpackage;

import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: DownloadManagerUiConfig.java */
/* JADX INFO: loaded from: classes.dex */
public final class wc0 {
    public final OTRProfileID a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final boolean g;

    /* JADX INFO: compiled from: DownloadManagerUiConfig.java */
    public static class a {
        public OTRProfileID a;
        public boolean b;
        public boolean e;
        public boolean f;
        public boolean g;
        public final boolean d = !DeviceFormFactor.a(xz.a);
        public final boolean c = SysUtils.isLowEndDevice();
    }

    public wc0(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
    }
}
