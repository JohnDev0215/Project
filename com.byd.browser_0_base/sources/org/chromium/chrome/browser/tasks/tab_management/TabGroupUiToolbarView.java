package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import defpackage.bz;
import defpackage.ii2;
import defpackage.yp;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$style;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: loaded from: classes2.dex */
public class TabGroupUiToolbarView extends FrameLayout {
    public ChromeImageView e;
    public ChromeImageView f;
    public ChromeImageView g;
    public ChromeImageView h;
    public ChromeImageView i;
    public ViewGroup j;
    public EditText k;
    public LinearLayout l;

    public TabGroupUiToolbarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a() {
        Context context = getContext();
        this.f.setImageResource(R$drawable.ic_arrow_back_24dp);
        ((ViewGroup.MarginLayoutParams) this.k.getLayoutParams()).setMarginStart((int) context.getResources().getDimension(R$dimen.tab_group_toolbar_topic_margin));
        this.k.setGravity(8388627);
        this.k.setTextAppearance(R$style.TextAppearance_Headline_Primary);
    }

    public ViewGroup getViewContainer() {
        return this.j;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f = (ChromeImageView) findViewById(R$id.toolbar_left_button);
        this.e = (ChromeImageView) findViewById(R$id.toolbar_right_button);
        this.g = (ChromeImageView) findViewById(R$id.toolbar_menu_button);
        this.h = (ChromeImageView) findViewById(R$id.tab_strip_fading_edge_start);
        this.i = (ChromeImageView) findViewById(R$id.tab_strip_fading_edge_end);
        this.j = (ViewGroup) findViewById(R$id.toolbar_container_view);
        this.k = (EditText) findViewById(R$id.title);
        this.l = (LinearLayout) findViewById(R$id.main_content);
    }

    public void setBackgroundColorTint(int i) {
        getBackground().setTint(i);
    }

    public void setIsIncognito(boolean z) {
        int iC;
        if (z) {
            iC = getResources().getColor(R$color.dialog_bg_color_dark_baseline);
        } else {
            Context context = getContext();
            String str = ii2.a;
            iC = yp.c(context, org.chromium.components.browser_ui.styles.R$dimen.dialog_bg_color_elev);
        }
        setPrimaryColor(iC);
        setTint(bz.b(getContext(), z ? R$color.default_icon_color_light_tint_list : R$color.default_icon_color_tint_list));
    }

    public void setLeftButtonContentDescription(String str) {
        this.f.setContentDescription(str);
    }

    public void setLeftButtonDrawableId(int i) {
        this.f.setImageResource(i);
    }

    public void setLeftButtonOnClickListener(View.OnClickListener onClickListener) {
        this.f.setOnClickListener(onClickListener);
    }

    public void setMainContentVisibility(boolean z) {
        if (this.j == null) {
            throw new IllegalStateException("Current Toolbar doesn't have a container view");
        }
        for (int i = 0; i < this.j.getChildCount(); i++) {
            this.j.getChildAt(i).setVisibility(z ? 0 : 4);
        }
    }

    public void setMenuButtonOnClickListener(View.OnClickListener onClickListener) {
        this.g.setOnClickListener(onClickListener);
    }

    public void setPrimaryColor(int i) {
        this.l.setBackgroundColor(i);
        ChromeImageView chromeImageView = this.h;
        if (chromeImageView == null || this.i == null) {
            return;
        }
        chromeImageView.setColorFilter(i, PorterDuff.Mode.SRC_IN);
        this.i.setColorFilter(i, PorterDuff.Mode.SRC_IN);
    }

    public void setRightButtonContentDescription(String str) {
        this.e.setContentDescription(str);
    }

    public void setRightButtonOnClickListener(View.OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    public void setTint(ColorStateList colorStateList) {
        this.f.setImageTintList(colorStateList);
        this.e.setImageTintList(colorStateList);
        EditText editText = this.k;
        if (editText != null) {
            editText.setTextColor(colorStateList);
        }
        ChromeImageView chromeImageView = this.g;
        if (chromeImageView != null) {
            chromeImageView.setImageTintList(colorStateList);
        }
    }

    public void setTitle(String str) {
        EditText editText = this.k;
        if (editText == null) {
            throw new IllegalStateException("Current Toolbar doesn't have a title text view");
        }
        editText.setText(str);
    }

    public void setTitleCursorVisibility(boolean z) {
        this.k.setCursorVisible(z);
    }

    public void setTitleTextOnChangedListener(TextWatcher textWatcher) {
        this.k.addTextChangedListener(textWatcher);
    }

    public void setTitleTextOnFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.k.setOnFocusChangeListener(onFocusChangeListener);
    }
}
