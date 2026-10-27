package org.chromium.chrome.browser.app.video_tutorials;

import J.N;
import android.os.Bundle;
import android.view.ViewGroup;
import defpackage.c3;
import defpackage.dx0;
import defpackage.em3;
import defpackage.ie0;
import defpackage.sg3;
import defpackage.ys0;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.SynchronousInitializationActivity;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes.dex */
public class VideoTutorialListActivity extends SynchronousInitializationActivity {
    public static final /* synthetic */ int I = 0;

    @Override // org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.video_tutorial_list);
        Profile profileD = Profile.d();
        new sg3((ViewGroup) findViewById(R$id.video_tutorial_list), (em3) N.MBuXqyoS(profileD), dx0.c(profileD.g(), ys0.a), new c3(4, this));
        findViewById(R$id.close_button).setOnClickListener(new ie0(2, this));
    }
}
