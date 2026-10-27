package com.byd.auto_emanual.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.ResourceUtil;
import com.byd.auto_emanual.util.Utils;
import com.byd.auto_emanual.util.ViewUtil;

/* JADX INFO: loaded from: classes.dex */
public class StartFragment extends BaseFragment {
    private static final String TAG = "StartFragment";
    private StartAllAdapter allAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int orientation;
    private RecyclerView startAll;
    private View view;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.orientation = getResources().getConfiguration().orientation;
        MLog.v(TAG, "onCreate");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.start_fragment, container, false);
        this.view = viewInflate;
        this.startAll = (RecyclerView) viewInflate.findViewById(R.id.start_all);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context) { // from class: com.byd.auto_emanual.fragment.StartFragment.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        this.linearLayoutManager = linearLayoutManager;
        this.startAll.setLayoutManager(linearLayoutManager);
        StartAllAdapter startAllAdapter = new StartAllAdapter();
        this.allAdapter = startAllAdapter;
        this.startAll.setAdapter(startAllAdapter);
        initNetState();
        return this.view;
    }

    private void initNetState() {
        this.netErrorState = (RelativeLayout) this.view.findViewById(R.id.net_error_state);
        this.netLoadingState = (RelativeLayout) this.view.findViewById(R.id.net_loading_state);
        updateNetState();
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, com.byd.auto_emanual.fragment.IView
    public void showData(PageData pageData) {
        super.showData(pageData);
        this.allAdapter.setData(pageData.getClassifyData());
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, com.byd.auto_emanual.fragment.IView
    public void loadData() {
        if (notLoadWhenNotShowVideo()) {
            return;
        }
        super.loadData();
        this.presenter.loadData(Page.START);
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment
    public boolean notLoadWhenNotShowVideo() {
        return Utils.notShowVideo(MApp.context);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.orientation = newConfig.orientation;
        this.allAdapter.notifyDataSetChanged();
        updateNetState();
    }

    public void updateNetState() {
        int dimensionPixelOffset;
        int dimensionPixelOffset2;
        TextView textView = (TextView) this.view.findViewById(R.id.error_text);
        textView.setText(R.string.state_net_error);
        textView.setTextColor(ResourceUtil.getColor(this.context, R.attr.state_text));
        ((ImageView) this.view.findViewById(R.id.error_icon)).setImageDrawable(ResourceUtil.getDrawable(this.context, R.attr.state_net_error));
        TextView textView2 = (TextView) this.view.findViewById(R.id.loading_text);
        textView2.setText(R.string.state_loading);
        textView2.setTextColor(ResourceUtil.getColor(this.context, R.attr.state_text));
        this.loadingIcon = (ImageView) this.view.findViewById(R.id.loading_icon);
        this.loadingIcon.setImageDrawable(ResourceUtil.getDrawable(this.context, R.attr.loading_54));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.netErrorState.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.netLoadingState.getLayoutParams();
        if (ViewUtil.isPort(this.orientation)) {
            dimensionPixelOffset = this.context.getResources().getDimensionPixelOffset(R.dimen.state_net_error_top_port);
            dimensionPixelOffset2 = this.context.getResources().getDimensionPixelOffset(R.dimen.state_loading_top_port);
        } else {
            dimensionPixelOffset = this.context.getResources().getDimensionPixelOffset(R.dimen.state_net_error_top);
            dimensionPixelOffset2 = this.context.getResources().getDimensionPixelOffset(R.dimen.state_loading_top);
        }
        layoutParams.setMargins(layoutParams.leftMargin, dimensionPixelOffset, layoutParams.rightMargin, layoutParams.bottomMargin);
        layoutParams2.setMargins(layoutParams2.leftMargin, dimensionPixelOffset2, layoutParams2.rightMargin, layoutParams2.bottomMargin);
    }
}
