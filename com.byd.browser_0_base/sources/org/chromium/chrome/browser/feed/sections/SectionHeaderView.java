package org.chromium.chrome.browser.feed.sections;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.tabs.TabLayout;
import defpackage.C0173a;
import defpackage.ho3;
import defpackage.is1;
import defpackage.om3;
import defpackage.rb2;
import defpackage.zo1;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.feed.sections.SectionHeaderView;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* JADX INFO: loaded from: classes.dex */
public class SectionHeaderView extends LinearLayout {
    public static final /* synthetic */ int l = 0;
    public ImageView e;
    public TabLayout f;
    public TextView g;
    public ListMenuButton h;
    public a i;
    public View j;
    public boolean k;

    public class a implements TabLayout.d {
        public is1 e;

        public a() {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void a(TabLayout.g gVar) {
            if (this.e != null) {
                int i = gVar.e;
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void h(TabLayout.g gVar) {
            TabLayout.TabView tabView = gVar.i;
            SectionHeaderView sectionHeaderView = SectionHeaderView.this;
            Resources resources = sectionHeaderView.getResources();
            int i = R$drawable.header_title_tab_selected_background;
            Resources.Theme theme = sectionHeaderView.getContext().getTheme();
            ThreadLocal<TypedValue> threadLocal = rb2.a;
            tabView.setBackground(resources.getDrawable(i, theme));
            is1 is1Var = this.e;
            if (is1Var != null) {
                is1Var.a();
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void l(TabLayout.g gVar) {
            gVar.i.setBackground(null);
        }
    }

    public class b {
        public boolean a;
        public c b;
        public String c;
        public String d = "";
        public boolean e;
    }

    public class c implements ViewTreeObserver.OnGlobalLayoutListener {
        public final View e;
        public final SectionHeaderBadgeDrawable f;

        public c(SectionHeaderView sectionHeaderView, View view) {
            this.e = view;
            this.f = new SectionHeaderBadgeDrawable(sectionHeaderView.getContext());
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        public final void a() {
            View view = this.e;
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            SectionHeaderBadgeDrawable sectionHeaderBadgeDrawable = this.f;
            View view2 = sectionHeaderBadgeDrawable.g;
            if (view2 == null || !view2.equals(view)) {
                return;
            }
            sectionHeaderBadgeDrawable.g = null;
            view.getOverlay().remove(sectionHeaderBadgeDrawable);
            sectionHeaderBadgeDrawable.invalidateSelf();
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            View view = this.e;
            if (view.getParent() != null) {
                SectionHeaderBadgeDrawable sectionHeaderBadgeDrawable = this.f;
                View view2 = sectionHeaderBadgeDrawable.g;
                if (view2 != null && view2.equals(view)) {
                    sectionHeaderBadgeDrawable.invalidateSelf();
                    return;
                }
                sectionHeaderBadgeDrawable.g = view;
                sectionHeaderBadgeDrawable.setBounds(sectionHeaderBadgeDrawable.a(view, sectionHeaderBadgeDrawable.e));
                view.getOverlay().add(sectionHeaderBadgeDrawable);
                sectionHeaderBadgeDrawable.invalidateSelf();
                if (sectionHeaderBadgeDrawable.h) {
                    sectionHeaderBadgeDrawable.h = false;
                    sectionHeaderBadgeDrawable.b();
                }
            }
        }
    }

    public SectionHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a(TabLayout.g gVar) {
        b bVar = (b) gVar.a;
        gVar.d(bVar.d);
        gVar.i.setClickable(this.k);
        gVar.i.setEnabled(this.k);
        String string = bVar.d;
        if (bVar.a && this.k) {
            StringBuilder sbK = C0173a.k(string, ", ");
            sbK.append(getResources().getString(R$string.accessibility_ntp_following_unread_content));
            string = sbK.toString();
            c cVar = bVar.b;
            if (cVar != null) {
                cVar.a();
            }
            if (gVar.f != null) {
                bVar.b = new c(this, gVar.i.findViewById(R.id.text1));
            }
            SectionHeaderBadgeDrawable sectionHeaderBadgeDrawable = bVar.b.f;
            String str = bVar.c;
            sectionHeaderBadgeDrawable.getClass();
            if (str == null) {
                str = "";
            }
            if (!sectionHeaderBadgeDrawable.e.equals(str)) {
                sectionHeaderBadgeDrawable.e = str;
                if (str.isEmpty()) {
                    TextPaint textPaint = sectionHeaderBadgeDrawable.a;
                    textPaint.setAlpha(255);
                    textPaint.setTextSize(sectionHeaderBadgeDrawable.d);
                    sectionHeaderBadgeDrawable.h = false;
                    View view = sectionHeaderBadgeDrawable.g;
                    if (view != null) {
                        sectionHeaderBadgeDrawable.setBounds(sectionHeaderBadgeDrawable.a(view, sectionHeaderBadgeDrawable.e));
                        sectionHeaderBadgeDrawable.invalidateSelf();
                    }
                }
            }
            if (bVar.e) {
                bVar.b.f.c();
            }
        } else {
            c cVar2 = bVar.b;
            if (cVar2 != null) {
                cVar2.a();
                bVar.b = null;
            }
        }
        gVar.d = string;
        gVar.e();
    }

    public final TabLayout.g b(int i) {
        TabLayout tabLayout = this.f;
        if (tabLayout != null) {
            return tabLayout.k(i);
        }
        return null;
    }

    public final void c(int i, boolean z) {
        int i2;
        TabLayout tabLayout = this.f;
        if (tabLayout == null || tabLayout.getTabCount() <= i) {
            return;
        }
        final TabLayout.g gVarK = this.f.k(i);
        ImageView imageView = (ImageView) gVarK.i.findViewById(R$id.options_indicator);
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        if (z) {
            Resources resources = getResources();
            int i3 = R$drawable.mtrl_ic_arrow_drop_up;
            Resources.Theme theme = getContext().getTheme();
            ThreadLocal<TypedValue> threadLocal = rb2.a;
            imageView.setImageDrawable(resources.getDrawable(i3, theme));
            i2 = R$string.feed_options_dropdown_description_close;
        } else {
            Resources resources2 = getResources();
            int i4 = R$drawable.mtrl_ic_arrow_drop_down;
            Resources.Theme theme2 = getContext().getTheme();
            ThreadLocal<TypedValue> threadLocal2 = rb2.a;
            imageView.setImageDrawable(resources2.getDrawable(i4, theme2));
            i2 = R$string.feed_options_dropdown_description;
        }
        gVarK.i.setOnLongClickListener(new View.OnLongClickListener() { // from class: ah2
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                this.e.i.a(gVarK);
                return true;
            }
        });
        om3.j(gVarK.i, AccessibilityNodeInfoCompat.a.h, getResources().getString(i2), new zo1(this, 3, gVarK));
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.g = (TextView) findViewById(R$id.header_title);
        this.h = (ListMenuButton) findViewById(R$id.header_menu);
        this.e = (ImageView) findViewById(R$id.section_status_indicator);
        this.f = (TabLayout) findViewById(R$id.tab_list_view);
        TabLayout tabLayout = this.f;
        if (tabLayout != null) {
            a aVar = new a();
            this.i = aVar;
            tabLayout.addOnTabSelectedListener((TabLayout.d) aVar);
        }
        final int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.feed_v2_header_menu_touch_size);
        this.h.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: zg2
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                int i9 = SectionHeaderView.l;
                SectionHeaderView sectionHeaderView = this.e;
                sectionHeaderView.getClass();
                Rect rect = new Rect();
                sectionHeaderView.h.getHitRect(rect);
                int width = sectionHeaderView.h.getWidth();
                int i10 = dimensionPixelSize;
                int iMax = Math.max((i10 - width) / 2, 0);
                int iMax2 = Math.max((i10 - sectionHeaderView.h.getHeight()) / 2, 0);
                rect.left -= iMax;
                rect.right += iMax;
                rect.top -= iMax2;
                rect.bottom += iMax2;
                sectionHeaderView.setTouchDelegate(new TouchDelegate(rect, sectionHeaderView.h));
            }
        });
        setImportantForAccessibility(2);
    }

    public void setActiveTab(int i) {
        TabLayout.g gVarB = b(i);
        if (gVarB == null || this.f.getSelectedTabPosition() == i) {
            return;
        }
        this.f.p(gVarB, true);
    }

    public void setHeaderText(String str) {
        this.g.setText(str);
    }

    public void setIndicatorVisibility(ho3 ho3Var) {
        ImageView imageView = this.e;
        if (imageView != null) {
            int iOrdinal = ho3Var.ordinal();
            imageView.setVisibility(iOrdinal != 0 ? iOrdinal != 1 ? 8 : 4 : 0);
        }
    }

    public void setIsLogo(boolean z) {
        ImageView imageView = this.e;
        if (imageView == null) {
            return;
        }
        if (z) {
            imageView.setImageResource(R$drawable.ic_logo_googleg_24dp);
            this.e.setImageTintMode(PorterDuff.Mode.DST);
        } else {
            imageView.setImageResource(R$drawable.ic_visibility_off_black);
            this.e.setImageTintMode(PorterDuff.Mode.SRC_IN);
        }
    }

    public void setOptionsPanel(View view) {
        View view2 = this.j;
        if (view2 != null) {
            removeView(view2);
        }
        addView(view);
        this.j = view;
    }

    public void setTabChangeListener(is1 is1Var) {
        a aVar = this.i;
        if (aVar != null) {
            aVar.e = is1Var;
        }
    }

    public void setTabMode(boolean z) {
        if (this.f != null) {
            this.g.setVisibility(z ? 8 : 0);
            this.f.setVisibility(z ? 0 : 8);
        }
    }

    public void setTextsEnabled(boolean z) {
        this.k = z;
        if (this.f != null) {
            for (int i = 0; i < this.f.getTabCount(); i++) {
                a(this.f.k(i));
            }
            this.f.setEnabled(z);
        }
        this.g.setEnabled(z);
    }

    public void d(int i) {
    }

    public void setStickyHeaderOptionsPanel(View view) {
    }

    public void setStickyHeaderVisible(boolean z) {
    }

    public void setToolbarHeight(int i) {
    }
}
