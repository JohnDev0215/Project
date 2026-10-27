package defpackage;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import org.chromium.components.webapps.R$id;
import org.chromium.components.webapps.R$layout;
import org.chromium.components.webapps.R$style;

/* JADX INFO: compiled from: ImageZoomView.java */
/* JADX INFO: loaded from: classes3.dex */
public final class jx0 extends AlertDialog {
    public static final /* synthetic */ int e = 0;

    public jx0(Context context, Bitmap bitmap) {
        super(context, R$style.ThemeOverlay_BrowserUI_Fullscreen);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.image_zoom_view, (ViewGroup) null);
        viewInflate.setOnClickListener(new by1(27, this));
        ((ImageView) viewInflate.findViewById(R$id.image_zoom)).setImageBitmap(bitmap);
        setView(viewInflate);
    }
}
