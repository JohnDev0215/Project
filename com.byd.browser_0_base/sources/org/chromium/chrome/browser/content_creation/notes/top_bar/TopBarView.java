package org.chromium.chrome.browser.content_creation.notes.top_bar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import defpackage.nd3;
import defpackage.od3;
import org.chromium.chrome.browser.content_creation.internal.R;

/* JADX INFO: loaded from: classes.dex */
public class TopBarView extends FrameLayout {
    public static final /* synthetic */ int e = 0;

    public TopBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnCloseListener(Runnable runnable) {
        findViewById(R.id.close).setOnClickListener(new od3(0, runnable));
    }

    public void setOnNextListener(Runnable runnable) {
        findViewById(R.id.next).setOnClickListener(new nd3(0, runnable));
    }
}
