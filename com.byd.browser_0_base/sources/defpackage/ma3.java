package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.components.browser_ui.widget.R$color;
import org.chromium.components.browser_ui.widget.R$id;

/* JADX INFO: compiled from: TileViewHolder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ma3 extends RecyclerView.a0 {
    public static final /* synthetic */ int y = 0;

    public ma3(View view) {
        super(view);
    }

    public final void u(Bitmap bitmap) {
        int i = R$id.thumbnail;
        View view = this.e;
        ImageView imageView = (ImageView) view.findViewById(i);
        ImageView imageView2 = (ImageView) view.findViewById(R$id.gradient_overlay);
        if (bitmap == null) {
            imageView.setImageDrawable(new ColorDrawable(imageView.getResources().getColor(R$color.image_loading_color)));
        } else {
            imageView.setImageBitmap(bitmap);
        }
        imageView2.setVisibility(bitmap == null ? 8 : 0);
    }
}
