package defpackage;

import android.media.MediaDrm;
import android.util.Log;
import java.util.UUID;
import org.chromium.chrome.browser.jni.NetworkBlock;

/* JADX INFO: compiled from: NetworkBlock.java */
/* JADX INFO: loaded from: classes.dex */
public final class vl1 implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        try {
            Log.i("NetworkBlock", "initWidevineSecurityLevel");
            String propertyString = new MediaDrm(new UUID(-1301668207276963122L, -6645017420763422227L)).getPropertyString("securityLevel");
            if ("L1".equals(propertyString)) {
                NetworkBlock.e = 1;
            } else if ("L2".equals(propertyString)) {
                NetworkBlock.e = 2;
            } else if ("L3".equals(propertyString)) {
                NetworkBlock.e = 3;
            } else {
                NetworkBlock.e = 0;
            }
        } catch (Exception e) {
            Log.e("cr_NetworkBlock", "getWidevineSecurityLevel exception: %s", e);
            NetworkBlock.e = 0;
        }
        Log.i("NetworkBlock", "support widevine level: " + NetworkBlock.e);
    }
}
