package defpackage;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.app.video_tutorials.VideoPlayerActivity;
import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gm3 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ String f;

    public /* synthetic */ gm3(String str, int i) {
        this.e = i;
        this.f = str;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        String str = this.f;
        switch (i) {
            case 0:
                for (Tutorial tutorial : (List) obj) {
                    if (TextUtils.equals(tutorial.h, str)) {
                        int i2 = tutorial.a;
                        n3.a1(i2, 6);
                        VideoPlayerActivity.K1(xz.a, i2);
                        break;
                    }
                }
                n3.a1(0, 7);
                break;
            default:
                for (ma0 ma0Var : (ArrayList) obj) {
                    if (str.contains(ma0Var.b)) {
                        oj2.U(ma0Var.e, 3, "MobileDownload.Location.Download.DirectoryType");
                        break;
                    }
                }
                break;
        }
    }
}
