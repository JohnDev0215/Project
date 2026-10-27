package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import defpackage.AbstractC0371Um;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MoreProgressButton extends FrameLayout implements View.OnClickListener {
    public View s;
    public View t;
    public int u;

    public MoreProgressButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.u = -1;
    }

    public void a(int i) {
        if (i == this.u) {
            return;
        }
        this.u = i;
        this.t.setVisibility(1 == i ? 0 : 8);
        this.s.setVisibility(2 != i ? 8 : 0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        a(2);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        View viewFindViewById = findViewById(AbstractC0371Um.q);
        this.t = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        this.s = findViewById(AbstractC0371Um.b3);
        a(0);
    }
}
