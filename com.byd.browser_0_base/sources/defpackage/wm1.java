package defpackage;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.app.video_tutorials.VideoPlayerActivity;
import org.chromium.chrome.browser.app.video_tutorials.VideoTutorialListActivity;
import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wm1 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ ym1 f;

    public /* synthetic */ wm1(ym1 ym1Var, int i) {
        this.e = i;
        this.f = ym1Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        ym1 ym1Var = this.f;
        switch (i) {
            case 0:
                Tutorial tutorial = (Tutorial) obj;
                ym1Var.getClass();
                int i2 = tutorial.a;
                ym1Var.b.notifyEvent(i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? null : "video_tutorial_iph_clicked_voice_search" : "video_tutorial_iph_clicked_search" : "video_tutorial_iph_clicked_download" : "video_tutorial_iph_clicked_chrome_intro" : "video_tutorial_iph_clicked_summary");
                int i3 = tutorial.a;
                n3.a1(i3, 9);
                Context context = ym1Var.a;
                if (i3 != 1) {
                    VideoPlayerActivity.K1(context, i3);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(context, VideoTutorialListActivity.class);
                    context.startActivity(intent);
                }
                break;
            default:
                List list = (List) obj;
                ym1Var.getClass();
                if (!list.isEmpty()) {
                    ym1Var.d.b(new gp(ym1Var, 1, new ArrayList(list)), 1);
                    break;
                }
                break;
        }
    }
}
