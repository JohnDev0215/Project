package b.a;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import com.byd.util.w;

/* JADX INFO: loaded from: classes.dex */
public class b implements MediaScannerConnection.MediaScannerConnectionClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f375a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f376b;

    public b(Context context) {
        this.f375a = context;
    }

    public void a(c cVar) {
        this.f376b = cVar;
    }

    @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
    public void onMediaScannerConnected() {
        w.c("===onMediaScannerConnected==");
        this.f376b.c();
    }

    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
    public void onScanCompleted(String str, Uri uri) {
        w.c("===onScanCompleted==" + str + " " + uri);
        if (this.f376b.b() == 0) {
            this.f376b.disconnect();
        } else {
            this.f376b.c();
        }
    }
}
