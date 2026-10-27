package com.byd.auto_photo;

import android.os.Bundle;
import android.widget.ImageView;
import com.byd.util.x;

/* JADX INFO: loaded from: classes.dex */
public class ShareToOtherDevActivity extends BaseShareToOtherDevActivity {
    private OTAReceiver v;

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected int g(boolean z) {
        return b.a.a.a(this) != 1 ? super.g(z) : R.style.CustomDialogNightTS;
    }

    @Override // com.byd.auto_photo.BaseShareToOtherDevActivity, com.byd.auto_photo.BaseDialogSupActivity
    protected void h() {
        super.h();
        ((ImageView) findViewById(R.id.view_close)).setImageDrawable(x.s(this, R.drawable.icon_cancel));
    }

    @Override // com.byd.auto_photo.BaseShareToOtherDevActivity, com.byd.auto_photo.BaseDialogSupActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        OTAReceiver oTAReceiver = new OTAReceiver(this);
        this.v = oTAReceiver;
        oTAReceiver.a();
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.BaseShareToOtherDevActivity, com.byd.auto_photo.BaseDialogSupActivity, android.app.Activity
    protected void onDestroy() {
        this.v.b();
        super.onDestroy();
    }
}
