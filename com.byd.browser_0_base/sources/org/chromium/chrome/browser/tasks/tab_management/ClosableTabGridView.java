package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import defpackage.n3;
import java.lang.ref.WeakReference;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ClosableTabGridView extends ViewLookupCachingFrameLayout {
    public static WeakReference<Bitmap> h;
    public boolean g;

    public ClosableTabGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean getIsAnimatingForTesting() {
        return this.g;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        ImageView imageView = (ImageView) j(R$id.action_button);
        WeakReference<Bitmap> weakReference = h;
        if (weakReference == null || weakReference.get() == null) {
            int dimension = (int) getResources().getDimension(R$dimen.tab_grid_close_button_size);
            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), R$drawable.btn_close);
            h = new WeakReference<>(Bitmap.createScaledBitmap(bitmapDecodeResource, dimension, dimension, true));
            bitmapDecodeResource.recycle();
        }
        imageView.setImageBitmap(h.get());
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        if (n3.Q0()) {
            layoutParams.gravity = 8388613;
        } else {
            layoutParams.gravity = 8388611;
        }
    }
}
