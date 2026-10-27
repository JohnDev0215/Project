package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.app.Fragment;
import j7.c;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(11)
public class ErrorDialogManager$HoneycombManagerFragment extends Fragment {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f6894d;

    @Override // android.app.Fragment
    public void onPause() {
        this.f6894d.q(this);
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        throw null;
    }
}
