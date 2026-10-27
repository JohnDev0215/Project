package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.widget.DualControlLayout;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: compiled from: DownloadInterstitialView.java */
/* JADX INFO: loaded from: classes.dex */
public final class mc0 {
    public final View a;
    public final TextView b;
    public final TextView c;
    public final gs0 d;
    public final rx0 e;
    public final ButtonCompat f;
    public final ButtonCompat g;

    public mc0(Context context, View view) {
        this.a = view;
        this.b = (TextView) view.findViewById(R$id.heading);
        this.c = (TextView) view.findViewById(R$id.loading_message);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R$id.file_info);
        int i = gs0.L;
        View viewInflate = LayoutInflater.from(frameLayout.getContext()).inflate(R$layout.download_manager_generic_item, (ViewGroup) null);
        this.d = new gs0(viewInflate);
        int i2 = rx0.F;
        View viewInflate2 = LayoutInflater.from(frameLayout.getContext()).inflate(R$layout.download_manager_in_progress_item, (ViewGroup) null);
        this.e = new rx0(viewInflate2);
        viewInflate.setVisibility(8);
        viewInflate2.setVisibility(4);
        frameLayout.addView(viewInflate);
        frameLayout.addView(viewInflate2);
        ButtonCompat buttonCompatA = DualControlLayout.a(context, true, "", null);
        this.f = buttonCompatA;
        buttonCompatA.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        buttonCompatA.setVisibility(4);
        ButtonCompat buttonCompatA2 = DualControlLayout.a(context, false, view.getResources().getString(R$string.cancel), null);
        this.g = buttonCompatA2;
        buttonCompatA2.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        buttonCompatA2.setVisibility(4);
        DualControlLayout dualControlLayout = (DualControlLayout) view.findViewById(R$id.button_bar);
        dualControlLayout.addView(buttonCompatA);
        dualControlLayout.addView(buttonCompatA2);
    }
}
