package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.chrome.R$layout;
import org.chromium.components.thinwebview.internal.ThinWebViewImpl;

/* JADX INFO: compiled from: VideoPlayerView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cm3 {
    public final FrameLayout a;
    public final s83 b;
    public final View c;
    public final View d;
    public final View e;

    public cm3(Context context, ThinWebViewImpl thinWebViewImpl) {
        this.b = thinWebViewImpl;
        FrameLayout frameLayout = new FrameLayout(context);
        this.a = frameLayout;
        frameLayout.addView(thinWebViewImpl.getView());
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.video_player_controls, (ViewGroup) null);
        this.d = viewInflate;
        View viewInflate2 = LayoutInflater.from(context).inflate(R$layout.video_player_loading, (ViewGroup) null);
        this.c = viewInflate2;
        View viewInflate3 = LayoutInflater.from(context).inflate(R$layout.language_picker, (ViewGroup) null);
        this.e = viewInflate3;
        frameLayout.addView(viewInflate);
        frameLayout.addView(viewInflate2);
        frameLayout.addView(viewInflate3);
    }
}
