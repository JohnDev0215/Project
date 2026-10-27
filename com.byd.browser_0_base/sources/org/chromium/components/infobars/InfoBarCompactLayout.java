package org.chromium.components.infobars;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import defpackage.un1;
import defpackage.wz0;
import org.chromium.base.Callback;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: loaded from: classes2.dex */
public class InfoBarCompactLayout extends LinearLayout implements View.OnClickListener {
    public final wz0 e;
    public final int f;
    public final ChromeImageButton g;

    public static class a {
        public final InfoBarCompactLayout a;
        public CharSequence b;
        public SpannableString c;

        public a(InfoBarCompactLayout infoBarCompactLayout) {
            this.a = infoBarCompactLayout;
        }

        public final void a() {
            InfoBarCompactLayout infoBarCompactLayout = this.a;
            int dimensionPixelOffset = infoBarCompactLayout.getResources().getDimensionPixelOffset(R$dimen.infobar_compact_message_vertical_padding);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append(this.b);
            if (this.c != null) {
                spannableStringBuilder.append((CharSequence) " ").append((CharSequence) this.c);
            }
            InfoBarMessageView infoBarMessageView = new InfoBarMessageView(infoBarCompactLayout.getContext());
            infoBarMessageView.setTextAppearance(infoBarMessageView.getContext(), R$style.TextAppearance_TextMedium_Primary);
            infoBarMessageView.setText(spannableStringBuilder);
            infoBarMessageView.setGravity(16);
            infoBarMessageView.setPadding(0, dimensionPixelOffset, 0, dimensionPixelOffset);
            if (this.c != null) {
                infoBarMessageView.setMovementMethod(LinkMovementMethod.getInstance());
            }
            infoBarCompactLayout.a(infoBarMessageView);
        }

        public final void b(String str, Callback callback) {
            Context context = this.a.getContext();
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new un1(context, callback), 0, str.length(), 17);
            this.c = spannableString;
        }
    }

    public InfoBarCompactLayout(Context context, wz0 wz0Var, int i, int i2, Bitmap bitmap) {
        super(context);
        this.e = wz0Var;
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R$dimen.infobar_compact_size);
        this.f = dimensionPixelOffset;
        int dimensionPixelOffset2 = context.getResources().getDimensionPixelOffset(R$dimen.infobar_big_icon_size);
        setOrientation(0);
        setGravity(16);
        View viewC = InfoBarLayout.c(getContext(), i, i2, bitmap);
        if (viewC != null) {
            addView(viewC, new LinearLayout.LayoutParams(dimensionPixelOffset2, dimensionPixelOffset));
        }
        ChromeImageButton chromeImageButtonB = InfoBarLayout.b(getContext());
        chromeImageButtonB.setOnClickListener(this);
        addView(chromeImageButtonB, new LinearLayout.LayoutParams(dimensionPixelOffset, dimensionPixelOffset));
        this.g = chromeImageButtonB;
    }

    public final void a(View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
        view.setMinimumHeight(this.f);
        layoutParams.gravity = 80;
        addView(view, indexOfChild(this.g), layoutParams);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (view.getId() == R$id.infobar_close_button) {
            this.e.k();
        }
    }
}
