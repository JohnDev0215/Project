package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.app.Fragment;
import org.greenrobot.eventbus.c;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(11)
public class ErrorDialogManager$HoneycombManagerFragment extends Fragment {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f4731b;

    @Override // android.app.Fragment
    public void onPause() {
        this.f4731b.q(this);
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        throw null;
    }
}
