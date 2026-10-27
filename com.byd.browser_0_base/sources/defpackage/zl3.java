package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class zl3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ am3 f;

    public /* synthetic */ zl3(am3 am3Var, int i) {
        this.e = i;
        this.f = am3Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        am3 am3Var = this.f;
        switch (i) {
            case 0:
                n3.a1(am3Var.f.a, 3);
                Context context = am3Var.a;
                Tutorial tutorial = am3Var.f;
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.addFlags(268435456);
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.TEXT", tutorial.h);
                try {
                    context.startActivity(Intent.createChooser(intent, context.getString(R$string.share_link_chooser_title)));
                } catch (ActivityNotFoundException unused) {
                    Log.e("cr_VideoTutorialShare", "Cannot find activity for sharing");
                } catch (Exception e) {
                    Log.e("cr_VideoTutorialShare", "Cannot start activity for sharing, exception: " + e);
                    return;
                }
                break;
            default:
                am3Var.getClass();
                am3Var.c.l(bm3.b, false);
                am3Var.b.b(new jb1(29, am3Var), am3Var.f.a);
                break;
        }
    }
}
