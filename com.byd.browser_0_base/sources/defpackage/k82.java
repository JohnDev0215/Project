package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;

/* JADX INFO: compiled from: QrCodeShareView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k82 {
    public final Context a;
    public final View b;
    public boolean c;
    public boolean d;
    public boolean e;

    public k82(Activity activity, vt vtVar) {
        this.a = activity;
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.qrcode_share_layout, (ViewGroup) null, false);
        this.b = viewInflate;
        ((Button) viewInflate.findViewById(R$id.download)).setOnClickListener(vtVar);
        ((Button) viewInflate.findViewById(R$id.settings)).setOnClickListener(new j82(this));
        a();
    }

    public final void a() {
        if (this.e) {
            int i = R$id.download;
            View view = this.b;
            Button button = (Button) view.findViewById(i);
            Button button2 = (Button) view.findViewById(R$id.settings);
            if (this.c) {
                button.setVisibility(0);
                button2.setVisibility(8);
            } else if (this.d) {
                button.setVisibility(0);
                button2.setVisibility(8);
            } else {
                button.setVisibility(8);
                button2.setVisibility(0);
            }
        }
    }
}
