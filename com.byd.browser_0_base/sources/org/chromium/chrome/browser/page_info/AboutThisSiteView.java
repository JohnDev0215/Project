package org.chromium.chrome.browser.page_info;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes2.dex */
public class AboutThisSiteView extends LinearLayout implements View.OnClickListener {
    public TextView e;
    public TextView f;
    public Runnable g;

    public AboutThisSiteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Runnable runnable;
        if (view != this.f || (runnable = this.g) == null) {
            return;
        }
        runnable.run();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (TextView) findViewById(R$id.description_text);
        TextView textView = (TextView) findViewById(R$id.source_link);
        this.f = textView;
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
