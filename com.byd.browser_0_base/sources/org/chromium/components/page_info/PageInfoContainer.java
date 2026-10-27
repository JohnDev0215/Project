package org.chromium.components.page_info;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import defpackage.by1;
import defpackage.es3;
import defpackage.gs;
import defpackage.ie0;
import defpackage.iu1;
import defpackage.ju1;
import defpackage.n3;
import defpackage.nh;
import defpackage.qt3;
import defpackage.ve3;
import org.chromium.components.page_info.PageInfoContainer;
import org.chromium.ui.ElidedUrlTextView;

/* JADX INFO: loaded from: classes3.dex */
public class PageInfoContainer extends FrameLayout {
    public static final /* synthetic */ int l = 0;
    public ElidedUrlTextView e;
    public TextView f;
    public final ViewGroup g;
    public final ViewGroup h;
    public View i;
    public final View j;
    public final TextView k;

    public static class a {
        public SpannableStringBuilder a;
        public int b;
        public String c;
        public boolean d;
        public es3 e;
        public ve3 f;
        public iu1 g;
        public ju1 h;
    }

    public PageInfoContainer(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R$layout.page_info_container, (ViewGroup) this, true);
        this.g = (ViewGroup) findViewById(R$id.page_info_wrapper);
        this.h = (ViewGroup) findViewById(R$id.page_info_content);
        this.j = findViewById(R$id.page_info_subpage_header);
        this.k = (TextView) findViewById(R$id.page_info_subpage_title);
    }

    public final void a(View view, CharSequence charSequence) {
        ViewGroup viewGroup = this.h;
        viewGroup.removeAllViews();
        this.i = view;
        this.j.setVisibility(charSequence != null ? 0 : 8);
        this.k.setText(charSequence);
        viewGroup.addView(view);
        if (charSequence == null) {
            charSequence = getResources().getString(R$string.accessibility_toolbar_btn_site_info);
        }
        announceForAccessibility(charSequence);
        post(new qt3(view, 2));
    }

    public final void b(View view, String str, ju1 ju1Var) {
        if (this.i == null) {
            a(view, str);
        } else {
            this.g.animate().setDuration(90L).alpha(0.0f).setInterpolator(new AccelerateInterpolator()).withEndAction(new nh(this, view, str, ju1Var, 1));
        }
    }

    public void setFavicon(Drawable drawable) {
        this.f.setCompoundDrawablesRelative(drawable, null, null, null);
    }

    public void setParams(final a aVar) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R$id.rl_page_info);
        if (n3.Q0()) {
            relativeLayout.setLayoutDirection(1);
        }
        View viewFindViewById = findViewById(R$id.page_info_url_wrapper);
        if (n3.O0()) {
            viewFindViewById.setLayoutDirection(1);
        } else {
            viewFindViewById.setLayoutDirection(0);
        }
        if (aVar.e != null) {
            viewFindViewById.setOnClickListener(new by1(25, aVar));
        }
        if (aVar.f != null) {
            viewFindViewById.setOnLongClickListener(new View.OnLongClickListener() { // from class: hu1
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    int i = PageInfoContainer.l;
                    aVar.f.run();
                    return true;
                }
            });
        }
        ElidedUrlTextView elidedUrlTextView = (ElidedUrlTextView) findViewById(R$id.page_info_url);
        this.e = elidedUrlTextView;
        elidedUrlTextView.q(aVar.b, aVar.a);
        this.e.r();
        TextView textView = (TextView) findViewById(R$id.page_info_truncated_url);
        this.f = textView;
        textView.setText(aVar.c);
        ImageButton imageButton = (ImageButton) findViewById(R$id.page_info_close);
        imageButton.setVisibility(aVar.d ? 0 : 8);
        imageButton.setOnClickListener(new gs(20, aVar));
        ((ImageButton) findViewById(R$id.subpage_back_button)).setOnClickListener(new ie0(29, aVar));
    }
}
