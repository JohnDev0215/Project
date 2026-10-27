package com.byd.auto_photo;

import android.os.Bundle;
import b.a.k.h;
import com.byd.theme.ThemeActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class SubBaseTaskActivity extends ThemeActivity implements h.b {
    private ReceiverForVoice q;

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReceiverForVoice receiverForVoice = new ReceiverForVoice(this);
        this.q = receiverForVoice;
        receiverForVoice.a();
        h.e(this).m(this);
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        ReceiverForVoice receiverForVoice = this.q;
        if (receiverForVoice != null) {
            receiverForVoice.b();
            this.q = null;
        }
        h.e(this).o(this);
        super.onDestroy();
    }

    public void onExternalEject(String str, boolean z) {
    }
}
