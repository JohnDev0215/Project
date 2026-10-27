package com.byd.auto_emanual;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_emanual.fragment.BaseFragment;
import com.byd.auto_emanual.fragment.ManualFragment;
import com.byd.auto_emanual.fragment.SettingsFragment;
import com.byd.auto_emanual.util.DeviceUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.ResourceUtil;
import com.byd.auto_emanual.util.SharedPrefUtil;
import com.byd.auto_emanual.util.Utils;
import com.byd.auto_emanual.util.ViewUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class TabAdapter extends RecyclerView.Adapter<TabViewHolder> {
    private static final int DEFAULT_OLD_POSITION = -1;
    public static int DEFAULT_SHOW_FRAGMENT_POSITION = 0;
    public static int DEFAULT_VIDEO_FRAGMENT_COUNT = 2;
    private static final String TAG = "TabAdapter";
    private static int oldPosition = -1;
    private final MainActivity activity;
    private FragmentManager manager;
    private TabViewHolder moreHolder;
    private int normalTextColor;
    private TabViewHolder oldHolder;
    private int orientation;
    private int selectedTextColor;
    private String[] tabNames;
    private List<Drawable> tabBackgroundSelected = new ArrayList();
    private List<Drawable> tabImagesSelected = new ArrayList();
    private List<Drawable> tabImagesNormal = new ArrayList();

    public TabAdapter(MainActivity a, int orientation) {
        this.activity = a;
        this.orientation = orientation;
        initData();
        addFragment();
    }

    private void addFragment() {
        this.manager = this.activity.getSupportFragmentManager();
    }

    private void initData() {
        getTabNames();
        getTabImagesSelected();
        getTabImagesNormal();
        getTabBackground();
        getTabTextColor();
        MLog.v(TAG, Arrays.asList(this.tabNames).toString());
    }

    private void getTabNames() {
        String[] strArr;
        this.tabNames = this.activity.getTheme().getResources().getStringArray(R.array.tab_names);
        if (!Utils.notShowVideo(MApp.context) || (strArr = this.tabNames) == null || strArr.length <= 2) {
            return;
        }
        int length = strArr.length - 2;
        String[] strArr2 = new String[length];
        System.arraycopy(strArr, 2, strArr2, 0, length);
        this.tabNames = strArr2;
    }

    private void getTabImagesSelected() {
        this.tabImagesSelected.clear();
        if (!Utils.notShowVideo(MApp.context)) {
            this.tabImagesSelected.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_recommend_selected));
            this.tabImagesSelected.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_start_selected));
        }
        this.tabImagesSelected.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_manual_selected));
        this.tabImagesSelected.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_search_selected));
        this.tabImagesSelected.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_settings_selected));
    }

    private void getTabImagesNormal() {
        this.tabImagesNormal.clear();
        if (!Utils.notShowVideo(MApp.context)) {
            this.tabImagesNormal.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_recommend_normal));
            this.tabImagesNormal.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_start_normal));
        }
        this.tabImagesNormal.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_manual_normal));
        this.tabImagesNormal.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_search_normal));
        this.tabImagesNormal.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_settings_normal));
    }

    private void getTabBackground() {
        this.tabBackgroundSelected.clear();
        this.tabBackgroundSelected.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_selected_horizontal));
        this.tabBackgroundSelected.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_selected_horizontal_right));
        this.tabBackgroundSelected.add(ResourceUtil.getDrawable(this.activity, R.attr.tab_selected_port));
    }

    private void getTabTextColor() {
        this.normalTextColor = ResourceUtil.getColor(this.activity, R.attr.recommend_all_title_color);
        this.selectedTextColor = ResourceUtil.getColor(this.activity, R.attr.tab_item_text_selected);
    }

    private List<Integer> getTabImages(int imagesResId) {
        ArrayList arrayList = new ArrayList();
        TypedArray typedArrayObtainTypedArray = MApp.context.getResources().obtainTypedArray(imagesResId);
        for (int i = 0; i < typedArrayObtainTypedArray.length(); i++) {
            arrayList.add(Integer.valueOf(typedArrayObtainTypedArray.getResourceId(i, 0)));
        }
        typedArrayObtainTypedArray.recycle();
        return arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public TabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TabViewHolder(LayoutInflater.from(this.activity).inflate(ViewUtil.isPort(this.orientation) ? R.layout.tab_item_view_port : R.layout.tab_item_view, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final TabViewHolder holder, final int position) {
        holder.tabName.setText(this.tabNames[position]);
        bindNormalItem(holder, position);
        int i = oldPosition;
        if (position == i || (i == -1 && position == DEFAULT_SHOW_FRAGMENT_POSITION && this.oldHolder == null)) {
            bindSelectedItem(holder, position);
            oldPosition = position;
            this.oldHolder = holder;
            showFragment(position);
        }
        holder.tabItem.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.-$$Lambda$TabAdapter$FD3zkPU4UrWXTKEdryRunETAIl0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$0$TabAdapter(holder, position, view);
            }
        });
        if (position == getItemCount() - 1) {
            this.activity.enterManual();
        }
        if (position == getItemCount() - 1) {
            this.moreHolder = holder;
        }
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$TabAdapter(TabViewHolder tabViewHolder, int i, View view) {
        clickItemView(tabViewHolder, i);
    }

    private void clickItemView(TabViewHolder holder, int position) {
        setSelectedItem(holder, position);
        showFragment(position);
    }

    private void setSelectedItem(TabViewHolder holder, int position) {
        bindSelectedItem(holder, position);
        TabViewHolder tabViewHolder = this.oldHolder;
        if (tabViewHolder != null && !tabViewHolder.equals(holder)) {
            bindNormalItem(this.oldHolder, oldPosition);
        }
        this.oldHolder = holder;
        oldPosition = position;
    }

    private void bindSelectedItem(TabViewHolder holder, int position) {
        setItemSelected(holder, true);
        holder.tabImage.setBackground(this.tabImagesSelected.get(position));
    }

    private void bindNormalItem(TabViewHolder holder, int position) {
        setItemSelected(holder, false);
        holder.tabImage.setBackground(this.tabImagesNormal.get(position));
        if (holder.tabSplit != null) {
            if (position == getItemCount() - 1) {
                holder.tabSplit.setVisibility(8);
            }
            if (Utils.notShowVideo(holder.itemView.getContext())) {
                holder.tabSplit.setVisibility(0);
            } else {
                holder.tabSplit.setVisibility(8);
            }
        }
        updateDot(holder, position);
    }

    private void updateDot(TabViewHolder holder, int position) {
        if (position == getItemCount() - 1) {
            if (Boolean.parseBoolean(SharedPrefUtil.load(holder.tabDot.getContext(), ManualFragment.zipRequireUpdateKey()))) {
                holder.tabDot.setVisibility(0);
            } else {
                holder.tabDot.setVisibility(8);
            }
        }
    }

    public void updateDot() {
        TabViewHolder tabViewHolder = this.moreHolder;
        if (tabViewHolder != null) {
            updateDot(tabViewHolder, getItemCount() - 1);
        }
    }

    private void showFragment(int position) {
        FragmentManager fragmentManager = (FragmentManager) Objects.requireNonNull(FragmentHolder.getFragment(position).getFragmentManager());
        if (this.manager != fragmentManager) {
            this.manager = fragmentManager;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = this.manager.beginTransaction();
        hideFragments(fragmentTransactionBeginTransaction);
        showSelectedFragment(fragmentTransactionBeginTransaction, position);
        fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        for (BaseFragment baseFragment : FragmentHolder.getAllFragments()) {
            if (baseFragment != null) {
                transaction.hide(baseFragment);
            }
        }
    }

    private void showSelectedFragment(FragmentTransaction transaction, int position) {
        BaseFragment fragment = FragmentHolder.getFragment(position);
        if (fragment != null) {
            this.manager.executePendingTransactions();
            if (!fragment.isAdded()) {
                transaction.add(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
                fragment.loadData();
            } else if (fragment instanceof SettingsFragment) {
                fragment.loadData();
            }
            transaction.show(fragment);
            MLog.v(TAG, "showSelectedFragment " + position);
        }
    }

    public void setItemSelected(TabViewHolder holder, boolean isSelected) {
        if (isSelected) {
            if (ViewUtil.isPort(this.orientation)) {
                holder.tabItem.setBackground(this.tabBackgroundSelected.get(2));
            } else if (DeviceUtil.isRightDriver()) {
                holder.tabItem.setBackground(this.tabBackgroundSelected.get(1));
            } else {
                holder.tabItem.setBackground(this.tabBackgroundSelected.get(0));
            }
            holder.tabName.setTextColor(this.selectedTextColor);
            return;
        }
        holder.tabName.setTextColor(this.normalTextColor);
        holder.tabItem.setBackground(null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        String[] strArr = this.tabNames;
        if (strArr != null && strArr.length == this.tabImagesSelected.size()) {
            return this.tabNames.length;
        }
        MLog.e(TAG, "The number of name does not match the number of Image!");
        return 0;
    }

    class TabViewHolder extends RecyclerView.ViewHolder {
        ImageView tabDot;
        ImageView tabImage;
        View tabItem;
        TextView tabName;
        View tabSplit;

        public TabViewHolder(View itemView) {
            super(itemView);
            this.tabItem = itemView.findViewById(R.id.tab_item_container);
            this.tabImage = (ImageView) itemView.findViewById(R.id.cover);
            this.tabDot = (ImageView) itemView.findViewById(R.id.tab_dot);
            this.tabName = (TextView) itemView.findViewById(R.id.name);
            this.tabSplit = itemView.findViewById(R.id.split_view);
        }
    }

    public static int getOldPosition() {
        int i = oldPosition;
        return i == -1 ? DEFAULT_SHOW_FRAGMENT_POSITION : i;
    }
}
