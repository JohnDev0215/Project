package com.android.car.ui.toolbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class TabLayout extends LinearLayout {
    private final Set<Listener> mListeners;
    private final TabAdapter mTabAdapter;

    public interface Listener {
        default void onTabSelected(Tab tab) {
        }

        default void onTabUnselected(Tab tab) {
        }

        default void onTabReselected(Tab tab) {
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyle) {
        int tabLayoutRes;
        super(context, attrs, defStyle);
        this.mListeners = new ArraySet();
        Resources resources = context.getResources();
        boolean tabFlexibleLayout = resources.getBoolean(R.bool.car_ui_toolbar_tab_flexible_layout);
        if (tabFlexibleLayout) {
            tabLayoutRes = R.layout.car_ui_toolbar_tab_item_layout_flexible;
        } else {
            tabLayoutRes = R.layout.car_ui_toolbar_tab_item_layout;
        }
        this.mTabAdapter = new TabAdapter(context, tabLayoutRes, this);
    }

    public void addTab(Tab tab) {
        this.mTabAdapter.add(tab);
        if (this.mTabAdapter.getCount() != 1) {
            return;
        }
        this.mTabAdapter.selectTab(0);
    }

    public void selectTab(Tab tab) {
        this.mTabAdapter.selectTab(tab);
    }

    public void selectTab(int position) {
        this.mTabAdapter.selectTab(position);
    }

    public int getTabCount() {
        return this.mTabAdapter.getCount();
    }

    public int getTabPosition(Tab tab) {
        return this.mTabAdapter.getPosition(tab);
    }

    public Tab get(int position) {
        return this.mTabAdapter.getItem(position);
    }

    public void clearAllTabs() {
        this.mTabAdapter.clear();
    }

    public void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.mListeners.remove(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnTabSelected(Tab tab) {
        for (Listener listener : this.mListeners) {
            listener.onTabSelected(tab);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnTabUnselected(Tab tab) {
        for (Listener listener : this.mListeners) {
            listener.onTabUnselected(tab);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnTabReselected(Tab tab) {
        for (Listener listener : this.mListeners) {
            listener.onTabReselected(tab);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTabView(View tabView, int position) {
        addView(tabView, position);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class TabAdapter extends BaseAdapter {
        private final Context mContext;
        private final int mTabItemLayoutRes;
        private final TabLayout mTabLayout;
        private final List<Tab> mTabList;

        private TabAdapter(Context context, int res, TabLayout tabLayout) {
            this.mTabList = new ArrayList();
            this.mContext = context;
            this.mTabItemLayoutRes = res;
            this.mTabLayout = tabLayout;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void add(Tab tab) {
            this.mTabList.add(tab);
            notifyItemInserted(this.mTabList.size() - 1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clear() {
            this.mTabList.clear();
            this.mTabLayout.removeAllViews();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getPosition(Tab tab) {
            return this.mTabList.indexOf(tab);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mTabList.size();
        }

        @Override // android.widget.Adapter
        public Tab getItem(int position) {
            return this.mTabList.get(position);
        }

        @Override // android.widget.Adapter
        public long getItemId(int position) {
            return position;
        }

        @Override // android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            View tabItemView = LayoutInflater.from(this.mContext).inflate(this.mTabItemLayoutRes, parent, false);
            presentTabItemView(position, tabItemView);
            return tabItemView;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void selectTab(Tab tab) {
            selectTab(getPosition(tab));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void selectTab(int position) {
            if (position < 0 || position >= getCount()) {
                throw new IndexOutOfBoundsException("Invalid position");
            }
            int i = 0;
            while (i < getCount()) {
                Tab tab = this.mTabList.get(i);
                boolean isTabSelected = position == i;
                if (tab.mIsSelected != isTabSelected) {
                    tab.mIsSelected = isTabSelected;
                    notifyItemChanged(i);
                    if (tab.mIsSelected) {
                        this.mTabLayout.dispatchOnTabSelected(tab);
                    } else {
                        this.mTabLayout.dispatchOnTabUnselected(tab);
                    }
                } else if (tab.mIsSelected) {
                    this.mTabLayout.dispatchOnTabReselected(tab);
                }
                i++;
            }
        }

        private void notifyItemChanged(int position) {
            View tabItemView = this.mTabLayout.getChildAt(position);
            presentTabItemView(position, tabItemView);
        }

        private void notifyItemInserted(int position) {
            View insertedView = getView(position, null, this.mTabLayout);
            this.mTabLayout.addTabView(insertedView, position);
        }

        private void presentTabItemView(int position, View tabItemView) {
            int i;
            final Tab tab = this.mTabList.get(position);
            ImageView iconView = (ImageView) CarUiUtils.requireViewByRefId(tabItemView, R.id.car_ui_toolbar_tab_item_icon);
            TextView textView = (TextView) CarUiUtils.requireViewByRefId(tabItemView, R.id.car_ui_toolbar_tab_item_text);
            tabItemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$TabLayout$TabAdapter$0Vjm7Veshp6Oh0zTkPnpEz-k18k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$presentTabItemView$0$TabLayout$TabAdapter(tab, view);
                }
            });
            tab.bindText(textView);
            tab.bindIcon(iconView);
            tabItemView.setActivated(tab.mIsSelected);
            if (tab.mIsSelected) {
                i = R.style.TextAppearance_CarUi_Widget_Toolbar_Tab_Selected;
            } else {
                i = R.style.TextAppearance_CarUi_Widget_Toolbar_Tab;
            }
            textView.setTextAppearance(i);
        }

        public /* synthetic */ void lambda$presentTabItemView$0$TabLayout$TabAdapter(Tab tab, View view) {
            selectTab(tab);
        }
    }

    public static class Tab {
        private final Drawable mIcon;
        private boolean mIsSelected;
        private final CharSequence mText;

        public Tab(Drawable icon, CharSequence text) {
            this.mIcon = icon;
            this.mText = text;
        }

        protected void bindText(TextView textView) {
            textView.setText(this.mText);
        }

        protected void bindIcon(ImageView imageView) {
            imageView.setImageDrawable(this.mIcon);
        }
    }
}
