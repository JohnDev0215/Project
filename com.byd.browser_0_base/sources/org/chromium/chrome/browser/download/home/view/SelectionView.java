package org.chromium.chrome.browser.download.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import defpackage.p5;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;

/* JADX INFO: loaded from: classes.dex */
public class SelectionView extends FrameLayout {
    public final ImageView e;
    public final ImageView f;
    public final p5 g;
    public boolean h;
    public boolean i;
    public boolean j;

    public SelectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R$layout.list_selection_handle_view, (ViewGroup) this, true);
        this.e = (ImageView) findViewById(R$id.check);
        this.f = (ImageView) findViewById(R$id.circle);
        this.g = p5.b(context, R$drawable.ic_check_googblue_24dp_animated);
    }

    @Override // android.view.View
    public final boolean isSelected() {
        return this.h;
    }
}
