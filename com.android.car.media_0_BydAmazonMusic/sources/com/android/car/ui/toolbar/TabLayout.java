package com.android.car.ui.toolbar;

import android.annotation.TargetApi;
import android.car.utils.Logs;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.car.ui.R$color;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$style;
import com.android.car.ui.toolbar.TabLayout;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import spi.b2;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class TabLayout extends com.google.android.material.tabs.TabLayout {
    public static final /* synthetic */ int d = 0;

    @LayoutRes
    public final int a;

    @NonNull
    public List<g> b;
    public int c;

    @Deprecated
    public static class a {
        public final Drawable a;
        public final CharSequence b;

        public a(@Nullable Drawable drawable, @Nullable CharSequence charSequence) {
            this.a = drawable;
            this.b = charSequence;
        }
    }

    public TabLayout(@NonNull Context context) {
        this(context, null);
    }

    public final void a(final int i) {
        if (i < 0 || i >= this.b.size()) {
            Logs.e("TabLayout", "Tab position is invalid: " + i);
            return;
        }
        if (getTabAt(i) == null || getTabAt(i).getCustomView() == null) {
            Logs.e("TabLayout", "Tab position is null: " + i);
            return;
        }
        View customView = getTabAt(i).getCustomView();
        g gVar = this.b.get(i);
        ImageView imageView = (ImageView) b2.n(customView, R$id.car_ui_toolbar_tab_item_icon);
        TextView textView = (TextView) b2.n(customView, R$id.car_ui_toolbar_tab_item_text);
        customView.setOnClickListener(new View.OnClickListener() { // from class: spi.ra
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TabLayout tabLayout = this.a;
                int i2 = i;
                int i3 = TabLayout.d;
                tabLayout.b(i2);
            }
        });
        customView.setActivated(i == this.c);
        if (gVar.d) {
            imageView.setImageTintList(getContext().getColorStateList(R$color.car_ui_toolbar_tab_item_selector));
        } else {
            imageView.setImageTintList(null);
        }
        imageView.setImageDrawable(gVar.b);
        textView.setText(gVar.a);
        textView.setTextAppearance(i == this.c ? R$style.TextAppearance_CarUi_Widget_Toolbar_Tab_Selected : R$style.TextAppearance_CarUi_Widget_Toolbar_Tab);
    }

    public final void b(int i) {
        if (i < 0 || i >= this.b.size()) {
            throw new IllegalArgumentException(spi.c.d("Tab position is invalid: ", i));
        }
        int i2 = this.c;
        if (i == i2) {
            return;
        }
        this.c = i;
        a(i2);
        a(i);
        g gVar = this.b.get(i);
        Consumer<g> consumer = gVar.c;
        if (consumer != null) {
            consumer.accept(gVar);
        }
    }

    public final void c(List<g> list, int i) {
        if (list == null) {
            this.b = Collections.emptyList();
        } else {
            this.b = Collections.unmodifiableList(new ArrayList(list));
        }
        this.c = i;
        removeAllTabs();
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.a, (ViewGroup) this, false);
            TabLayout.Tab tabNewTab = newTab();
            tabNewTab.setCustomView(viewInflate);
            tabNewTab.view.setPadding(0, 0, 0, 0);
            addTab(tabNewTab, false);
            a(i2);
        }
    }

    public int getSelectedTab() {
        if (!this.b.isEmpty() || this.c == -1) {
            return this.c;
        }
        throw new IllegalStateException("mSelectedTab should've been -1");
    }

    public List<g> getTabs() {
        return this.b;
    }

    public TabLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TabLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = Collections.emptyList();
        this.c = -1;
        this.a = R$layout.car_ui_toolbar_tab_item_layout;
    }
}
