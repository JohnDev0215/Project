package defpackage;

import android.database.ContentObserver;
import android.net.Uri;
import android.provider.MediaStore;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: ScreenshotMonitorImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class ve2 extends te2 {
    public final a c;

    /* JADX INFO: compiled from: ScreenshotMonitorImpl.java */
    public class a extends ContentObserver {
        public final te2 a;

        public a(te2 te2Var) {
            super(null);
            this.a = te2Var;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            if (uri == null) {
                return;
            }
            uri.toString();
            uri.toString();
            if (uri.toString().startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString())) {
                PostTask.c(rh3.a, new z8(this, 13, uri));
            } else {
                fa1.k("ScreenshotMonitor", "uri: %s is not valid. Returning without processing screenshot", uri);
            }
        }
    }

    public ve2(ue2 ue2Var) {
        super(ue2Var);
        this.c = new a(this);
    }
}
