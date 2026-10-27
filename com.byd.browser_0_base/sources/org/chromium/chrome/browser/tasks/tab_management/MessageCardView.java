package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import java.lang.ref.WeakReference;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.components.browser_ui.widget.text.TemplatePreservingTextView;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public class MessageCardView extends LinearLayout {
    public static WeakReference<Bitmap> i;
    public ChromeImageView e;
    public TemplatePreservingTextView f;
    public ButtonCompat g;
    public ChromeImageView h;

    public interface a {
        void a(int i);
    }

    public interface b {
        Drawable a();
    }

    public interface c {
        void a();
    }

    public MessageCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (ChromeImageView) findViewById(R$id.icon);
        this.f = (TemplatePreservingTextView) findViewById(R$id.description);
        this.g = (ButtonCompat) findViewById(R$id.action_button);
        this.h = (ChromeImageView) findViewById(R$id.close_button);
        WeakReference<Bitmap> weakReference = i;
        if (weakReference == null || weakReference.get() == null) {
            int dimension = (int) getResources().getDimension(R$dimen.tab_grid_close_button_size);
            i = new WeakReference<>(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R$drawable.btn_close), dimension, dimension, true));
        }
        this.h.setImageBitmap(i.get());
    }

    public void setActionButtonOnClickListener(View.OnClickListener onClickListener) {
        this.g.setOnClickListener(onClickListener);
    }

    public void setDismissButtonOnClickListener(View.OnClickListener onClickListener) {
        this.h.setOnClickListener(onClickListener);
    }
}
