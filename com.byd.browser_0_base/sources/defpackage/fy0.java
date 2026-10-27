package defpackage;

import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.util.concurrent.FutureTask;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.base.SplitCompatIntentService;

/* JADX INFO: compiled from: IncognitoNotificationServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class fy0 extends SplitCompatIntentService.a {
    @Override // org.chromium.chrome.browser.base.SplitCompatIntentService.a
    public final void a(Intent intent) {
        d73 d73Var = rh3.a;
        qe3 qe3Var = new qe3(10);
        Object obj = PostTask.a;
        FutureTask futureTask = new FutureTask(qe3Var, null);
        PostTask.d(d73Var, futureTask);
        try {
            futureTask.get();
            File[] fileArrListFiles = s33.a().listFiles();
            boolean z = true;
            if (fileArrListFiles != null) {
                boolean zDelete = true;
                for (File file : fileArrListFiles) {
                    Pair<Integer, Boolean> pairB = t33.b(file.getName());
                    if ((pairB != null && ((Boolean) pairB.second).booleanValue()) && !((zDelete = zDelete & file.delete()))) {
                        Log.e("cr_IncognitoFileDelete", "File " + file.getAbsolutePath() + " deletion unsuccessful.");
                    }
                }
                z = zDelete;
            }
            oj2.O("Android.IncognitoNotification.FileNotDeleted", !z);
            if (z) {
                d73 d73Var2 = rh3.a;
                FutureTask futureTask2 = new FutureTask(new wz(11), null);
                PostTask.d(d73Var2, futureTask2);
                try {
                    futureTask2.get();
                    FutureTask futureTask3 = new FutureTask(new zc0(8, this), null);
                    PostTask.d(d73Var2, futureTask3);
                    try {
                        futureTask3.get();
                    } catch (Exception e) {
                        throw new uv(e);
                    }
                } catch (Exception e2) {
                    throw new uv(e2);
                }
            }
        } catch (Exception e3) {
            throw new uv(e3);
        }
    }
}
