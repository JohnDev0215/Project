package com.byd.auto_emanual.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.Parameters;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.fragment.SearchHistoryAdapter;
import com.byd.auto_emanual.util.DialogUtil;
import com.byd.auto_emanual.util.ListUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.ResourceUtil;
import com.byd.auto_emanual.util.SearchHistoryUtil;
import com.byd.auto_emanual.util.UIStyleUtil;
import com.byd.auto_emanual.util.Utils;
import com.google.android.flexbox.FlexboxLayoutManager;

/* JADX INFO: loaded from: classes.dex */
public class SearchFragment extends BaseFragment implements SearchHistoryAdapter.Callback {
    private static final String TAG = "SearchFragment";
    private ImageView delete;
    private RelativeLayout history;
    private TextView historyTextView;
    private RelativeLayout hot;
    private TextView hotTextView;
    private RelativeLayout input;
    private TextView inputTextView;
    private SearchInputListener listener;
    private int orientation;
    private AlertDialog removingWaringDialog;
    private SearchHistoryAdapter searchHistoryAdapter;
    private SearchHotAdapter searchHotAdapter;
    private ImageView searchIcon;
    private TextView voiceExample1;
    private TextView voiceExample2;
    private ImageView voiceIcon1;
    private ImageView voiceIcon2;
    private LinearLayout voiceLayout1;
    private LinearLayout voiceLayout2;
    private TextView voiceTextView;

    @Override // com.byd.auto_emanual.fragment.BaseFragment, com.byd.auto_emanual.fragment.IView
    public void showError() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.orientation = getResources().getConfiguration().orientation;
        MLog.v(TAG, "onCreate");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.search_fragment, container, false);
        initView(viewInflate);
        initData();
        return viewInflate;
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateHistory();
    }

    private void initData() {
        new String[]{"0字。", "1字面。", "2字滑油。", "3字。", Parameters.CONTENT_ITEM_FOOTER, "5燃料油或加入润滑油。", "6字面上的意思是给运作的机器或是车子之类的添加燃料油或加入润滑油。", "7料油或加入润滑油。", "8字面上的意思是给运作的机器或是车子之类的添加燃料油或加入润滑油。", "9字面上的类的添加燃料油或加入润滑油。", "字面上的意思是给运作的机器或是车子之类的添加燃料油或加入润滑油。"};
        SearchHistoryUtil.initHistoryCache(this.context);
    }

    private void initView(View view) {
        initInput(view);
        initVoice(view);
        initHistory(view);
        initHot(view);
        updateTextByConfig();
    }

    private void updateTextByConfig() {
        this.inputTextView.setHint(R.string.search_hint_text);
        this.voiceTextView.setText(R.string.search_voice_text);
        this.voiceExample1.setText(R.string.search_voice_how_to_open_light);
        this.voiceExample2.setText(R.string.search_voice_nfc_open_lock);
        this.historyTextView.setText(R.string.search_history_text);
        this.hotTextView.setText(R.string.search_hot_text);
    }

    private void initInput(View view) {
        this.searchIcon = (ImageView) view.findViewById(R.id.search_input_icon);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.search_input);
        this.input = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$SearchFragment$hUxtr2alJp_U61aUIA5U_CznxoA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$initInput$0$SearchFragment(view2);
            }
        });
        this.inputTextView = (TextView) view.findViewById(R.id.input_text_view);
        updateInputBackground();
    }

    public /* synthetic */ void lambda$initInput$0$SearchFragment(View view) {
        MLog.d(TAG, "onClick input");
        SearchInputListener searchInputListener = this.listener;
        if (searchInputListener != null) {
            searchInputListener.doInputClick();
        }
    }

    private void initVoice(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.voice);
        boolean z = this.context.getResources().getBoolean(R.bool.show_search_voice);
        this.voiceTextView = (TextView) view.findViewById(R.id.voice_text);
        this.voiceLayout1 = (LinearLayout) view.findViewById(R.id.search_voice_how_to_open_light);
        this.voiceExample1 = (TextView) view.findViewById(R.id.voice_example_1);
        this.voiceIcon1 = (ImageView) view.findViewById(R.id.voice_icon_1);
        this.voiceLayout2 = (LinearLayout) view.findViewById(R.id.search_voice_nfc_open_lock);
        this.voiceExample2 = (TextView) view.findViewById(R.id.voice_example_2);
        this.voiceIcon2 = (ImageView) view.findViewById(R.id.voice_icon_2);
        if (z) {
            relativeLayout.setVisibility(0);
        }
    }

    private void initHistory(View view) {
        this.history = (RelativeLayout) view.findViewById(R.id.history);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.history_recycler);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(view.getContext());
        flexboxLayoutManager.setFlexWrap(1);
        flexboxLayoutManager.setFlexDirection(0);
        flexboxLayoutManager.setAlignItems(4);
        flexboxLayoutManager.setJustifyContent(0);
        recyclerView.setLayoutManager(flexboxLayoutManager);
        SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter(this);
        this.searchHistoryAdapter = searchHistoryAdapter;
        recyclerView.setAdapter(searchHistoryAdapter);
        this.delete = (ImageView) view.findViewById(R.id.history_delete);
        this.historyTextView = (TextView) view.findViewById(R.id.history_text);
        this.delete.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$SearchFragment$_YeqeBy2wihRpDvaM9YSGMnljTs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$initHistory$1$SearchFragment(view2);
            }
        });
    }

    public /* synthetic */ void lambda$initHistory$1$SearchFragment(View view) {
        this.removingWaringDialog = DialogUtil.showHistoryRemovingWaring(this.context, this.searchHistoryAdapter);
    }

    private void initHot(View view) {
        this.hot = (RelativeLayout) view.findViewById(R.id.hot);
        if (Utils.notShowVideo(this.context)) {
            this.hot.setVisibility(8);
        }
        updateHotMargin();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.hot_recycler);
        this.searchHotAdapter = new SearchHotAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(MApp.context));
        recyclerView.setAdapter(this.searchHotAdapter);
        this.hotTextView = (TextView) view.findViewById(R.id.hot_text);
    }

    private void updateHotMargin() {
        int dimensionPixelSize;
        if (this.history.getVisibility() == 0) {
            dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.search_hot_margin_top_when_history_visible);
        } else {
            dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.search_clause_margin_top);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.hot.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, dimensionPixelSize, layoutParams.rightMargin, layoutParams.bottomMargin);
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, com.byd.auto_emanual.fragment.IView
    public void showData(PageData pageData) {
        super.showData(pageData);
        if (ListUtil.isNullOrEmpty(pageData.getClassifyData())) {
            return;
        }
        ClassifyData classifyData = pageData.getClassifyData().get(0);
        classifyData.setClassify(Classify.HOT);
        this.searchHotAdapter.setData(classifyData, this.orientation);
    }

    public void setSearchInputClickListener(SearchInputListener listener) {
        this.listener = listener;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.orientation = getResources().getConfiguration().orientation;
        updateColorByConfig();
        updateDataAdapter();
        updateTextByConfig();
        AlertDialog alertDialog = this.removingWaringDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        DialogUtil.updateDialogTextByConfig(this.removingWaringDialog, R.string.dialog_history_removing_title, R.string.dialog_history_removing_message, -1, R.string.dialog_button_confirm, R.string.dialog_button_cancel);
    }

    private void updateColorByConfig() {
        updateInputBackground();
        this.searchIcon.setImageDrawable(ResourceUtil.getDrawable(this.context, R.attr.icon_search));
        this.inputTextView.setHintTextColor(ResourceUtil.getColor(this.context, R.attr.search_input_hint_text_color));
        this.voiceTextView.setTextColor(ContextCompat.getColor(this.context, R.color.search_voice_text_color));
        this.voiceExample1.setTextColor(ContextCompat.getColor(this.context, R.color.search_voice_icon_text_color));
        this.voiceIcon1.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.icon_search_voice));
        this.voiceLayout2.setBackground(ContextCompat.getDrawable(this.context, R.drawable.search_voice_icon_background));
        this.voiceExample2.setTextColor(ContextCompat.getColor(this.context, R.color.search_voice_icon_text_color));
        this.voiceIcon2.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.icon_search_voice));
        ResourceUtil.setTextColor(this.historyTextView, R.attr.search_voice_text_color);
        this.delete.setImageDrawable(ResourceUtil.getDrawable(this.context, R.attr.icon_search_history_delete));
        this.searchHistoryAdapter.notifyDataSetChanged();
        this.hotTextView.setTextColor(ContextCompat.getColor(this.context, R.color.search_voice_text_color));
        AlertDialog alertDialog = this.removingWaringDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        DialogUtil.updateDialogColorByConfig(this.removingWaringDialog);
    }

    private void updateDataAdapter() {
        ClassifyData classifyData = NormalDataAdapter.cacheData.get(Classify.HOT);
        if (classifyData != null) {
            this.searchHotAdapter.setData(classifyData, this.orientation);
        }
    }

    private void updateInputBackground() {
        if (!Utils.is8155F()) {
            this.input.setBackground(ContextCompat.getDrawable(this.context, R.drawable.search_input_background));
            return;
        }
        if (UIStyleUtil.isDenza()) {
            this.input.setBackground(ContextCompat.getDrawable(this.context, R.drawable.search_result_input_background_denza));
        } else if (UIStyleUtil.isDynasty()) {
            this.input.setBackground(ContextCompat.getDrawable(this.context, R.drawable.search_result_input_background_dynasty));
        } else {
            this.input.setBackground(ContextCompat.getDrawable(this.context, R.drawable.search_result_input_background_dynasty));
        }
    }

    @Override // com.byd.auto_emanual.fragment.SearchHistoryAdapter.Callback
    public void updateState(int historyItemCount) {
        if (historyItemCount == 0) {
            this.history.setVisibility(8);
        } else {
            this.history.setVisibility(0);
        }
        updateHotMargin();
    }

    @Override // com.byd.auto_emanual.fragment.SearchHistoryAdapter.Callback
    public void doHistoryItemClick(String history) {
        SearchInputListener searchInputListener = this.listener;
        if (searchInputListener != null) {
            searchInputListener.doSearchHistory(history);
        }
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, com.byd.auto_emanual.fragment.IView
    public void loadData() {
        if (notLoadWhenNotShowVideo()) {
            return;
        }
        super.loadData();
        this.presenter.loadData(Page.SEARCH);
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment
    public boolean notLoadWhenNotShowVideo() {
        return Utils.notShowVideo(MApp.context);
    }

    public void updateHistory() {
        this.searchHistoryAdapter.setData(SearchHistoryUtil.getHistory());
    }
}
