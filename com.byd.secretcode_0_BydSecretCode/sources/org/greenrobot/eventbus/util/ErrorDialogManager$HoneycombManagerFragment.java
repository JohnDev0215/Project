package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.app.Fragment;
import defpackage.bm;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(11)
public class ErrorDialogManager$HoneycombManagerFragment extends Fragment {
    public bm b;

    @Override // android.app.Fragment
    public void onPause() {
        this.b.q(this);
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        throw null;
    }
}
