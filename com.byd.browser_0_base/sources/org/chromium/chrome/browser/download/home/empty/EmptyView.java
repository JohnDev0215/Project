package org.chromium.chrome.browser.download.home.empty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.ui.widget.LoadingView;

/* JADX INFO: loaded from: classes.dex */
class EmptyView {
    public final ViewGroup a;
    public final View b;
    public final TextView c;
    public final LoadingView d;

    public EmptyView(Context context) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R$layout.downloads_empty_view, (ViewGroup) null);
        this.a = viewGroup;
        this.b = viewGroup.findViewById(R$id.empty_container);
        this.c = (TextView) viewGroup.findViewById(R$id.empty);
        this.d = (LoadingView) viewGroup.findViewById(R$id.loading);
    }
}
