package com.byd.auto_photo;

import android.widget.ImageView;
import android.widget.LinearLayout;
import com.byd.view.ListItemDecoration;

/* JADX INFO: loaded from: classes.dex */
public class ScrawlActivity extends ScrawlSuperActivity {
    private void W0() {
        ImageView imageView = this.d0;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        this.O.setVisibility(0);
        this.P.setVisibility(0);
    }

    @Override // com.byd.auto_photo.ScrawlSuperActivity, com.byd.auto_photo.BaseScrawlActivity
    protected String M0() {
        return "";
    }

    @Override // com.byd.auto_photo.BaseScrawlActivity
    protected void O0() {
        super.O0();
        if (getResources().getConfiguration().orientation == 1) {
            this.S.addItemDecoration(new ListItemDecoration(getResources().getDimensionPixelSize(R.dimen.dp_15), true));
        }
    }

    @Override // com.byd.auto_photo.BaseScrawlActivity, com.byd.auto_photo.SuperBaseEditActivity
    protected void i0(LinearLayout linearLayout) {
        super.i0(linearLayout);
        W0();
    }

    @Override // com.byd.auto_photo.ScrawlSuperActivity, com.byd.auto_photo.BaseScrawlActivity, com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity
    protected void t0() {
        super.t0();
        this.V.setVisibility(0);
        this.O.setVisibility(4);
        this.P.setVisibility(4);
    }

    @Override // com.byd.auto_photo.ScrawlSuperActivity, com.byd.auto_photo.SuperBaseEditActivity
    protected void z0(boolean z) {
        super.z0(z);
        W0();
    }
}
