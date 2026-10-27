package org.chromium.components.page_info;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.bz;
import defpackage.vu1;

/* JADX INFO: loaded from: classes3.dex */
public class PageInfoRowView extends FrameLayout {
    public static final /* synthetic */ int h = 0;
    public final ImageView e;
    public final TextView f;
    public final TextView g;

    public static class a {
        public boolean a;
        public int b;
        public int c;
        public boolean d = true;
        public String e;
        public CharSequence f;
        public Runnable g;
        public boolean h;
        public boolean i;
        public int j;
    }

    public PageInfoRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R$layout.page_info_row, (ViewGroup) this, true);
        this.e = (ImageView) findViewById(R$id.page_info_row_icon);
        this.f = (TextView) findViewById(R$id.page_info_row_title);
        this.g = (TextView) findViewById(R$id.page_info_row_subtitle);
        setVisibility(8);
    }

    public void setParams(a aVar) {
        setVisibility(aVar.a ? 0 : 8);
        if (aVar.a) {
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            int i = aVar.b;
            ImageView imageView = this.e;
            imageView.setImageResource(i);
            if (aVar.h) {
                int iRound = Math.round(TypedValue.applyDimension(1, 2.0f, displayMetrics));
                imageView.setPadding(iRound, iRound, iRound, iRound);
            }
            if (aVar.d) {
                imageView.setImageTintList(aVar.c != 0 ? ColorStateList.valueOf(getResources().getColor(aVar.c)) : bz.b(getContext(), R$color.default_icon_color_tint_list));
            }
            String str = aVar.e;
            TextView textView = this.f;
            textView.setText(str);
            textView.setVisibility(aVar.e != null ? 0 : 8);
            CharSequence charSequence = aVar.f;
            TextView textView2 = this.g;
            textView2.setText(charSequence);
            textView2.setVisibility(charSequence != null ? 0 : 8);
            textView2.setSingleLine(aVar.i);
            textView2.setEllipsize(aVar.i ? TextUtils.TruncateAt.END : null);
            if (aVar.e != null && aVar.f != null) {
                textView.setPadding(0, 0, 0, Math.round(TypedValue.applyDimension(1, 4.0f, displayMetrics)));
            }
            if (aVar.g != null) {
                setClickable(true);
                setFocusable(true);
                getChildAt(0).setOnClickListener(new vu1(0, aVar));
            }
            if (aVar.j != 0) {
                setBackgroundColor(bz.b(getContext(), aVar.j).getDefaultColor());
            }
        }
    }
}
