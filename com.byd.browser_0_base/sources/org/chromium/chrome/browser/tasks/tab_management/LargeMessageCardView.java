package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.components.browser_ui.widget.MaterialCardViewNoShadow;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: loaded from: classes2.dex */
class LargeMessageCardView extends FrameLayout {
    public static WeakReference<Bitmap> o;
    public final Context e;
    public final int f;
    public MaterialCardViewNoShadow g;
    public PriceCardView h;
    public ChromeImageView i;
    public TextView j;
    public TextView k;
    public ButtonCompat l;
    public ButtonCompat m;
    public ChromeImageView n;

    public LargeMessageCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = context;
        this.f = (int) context.getResources().getDimension(R$dimen.tab_grid_large_message_side_padding_landscape);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.g = (MaterialCardViewNoShadow) findViewById(R$id.large_message_card_view);
        this.h = (PriceCardView) findViewById(R$id.price_info_box);
        this.i = (ChromeImageView) findViewById(R$id.icon);
        this.j = (TextView) findViewById(R$id.title);
        this.k = (TextView) findViewById(R$id.description);
        this.l = (ButtonCompat) findViewById(R$id.action_button);
        this.m = (ButtonCompat) findViewById(R$id.secondary_action_button);
        this.n = (ChromeImageView) findViewById(R$id.close_button);
        WeakReference<Bitmap> weakReference = o;
        if (weakReference == null || weakReference.get() == null) {
            int dimension = (int) getResources().getDimension(R$dimen.tab_grid_close_button_size);
            o = new WeakReference<>(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R$drawable.btn_close), dimension, dimension, true));
        }
        this.n.setImageBitmap(o.get());
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        if (this.e.getResources().getConfiguration().orientation == 1) {
            setPadding(0, 0, 0, 0);
        } else {
            int i3 = this.f;
            setPadding(i3, 0, i3, 0);
        }
        super.onMeasure(i, i2);
    }

    public void setActionButtonOnClickListener(View.OnClickListener onClickListener) {
        this.l.setOnClickListener(onClickListener);
    }

    public void setDismissButtonOnClickListener(View.OnClickListener onClickListener) {
        this.n.setOnClickListener(onClickListener);
    }

    public void setSecondaryActionButtonOnClickListener(View.OnClickListener onClickListener) {
        this.m.setOnClickListener(onClickListener);
    }
}
