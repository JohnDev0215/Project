package com.byd.music.fragment.localMusic.search;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ResultReceiver;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Observable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.api.ApiMethod;
import com.byd.music.base.BaseActivity;
import com.byd.music.beans.EventString;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.beans.SearchHistoryBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.databinding.FragmentLocalSearchBinding;
import com.byd.music.db.SearchHistoryDB;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.adpter.ArtistExpandableSearchListAdapter;
import com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter;
import com.byd.music.utils.DoubleClickHelper;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.OrientationUtil;
import com.byd.music.utils.binding.RecyclerViewGridBinding;
import com.byd.music.view.FlowLayout;
import com.byd.music.view.LocalSearchView;
import com.google.android.material.tabs.TabLayout;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes5.dex */
public class LocalMusicSearchActivity extends BaseActivity<FragmentLocalSearchBinding, LocalMusicSearchViewModel> implements ExpandableListAdapter.onItemOperateListener, ArtistExpandableSearchListAdapter.OnGroupClickListener, FlowLayout.OnHistoryItemClickListener, TabLayout.OnTabSelectedListener {
    private static final String KEY_FOCUS_ON_SEARCH = "focusOnSearch";
    private static final String KEY_SOFT_INPUT_VISIBLE = "key_softInputVisible";
    private static final int MSG_DISMISS_LOCAL_SEARCH_PAGE = 3;
    private static final int MSG_HIDE_SOFT_KEYBOARD = 2;
    private static final int MSG_SHOW_SOFT_KEYBOARD = 1;
    private static final String TAG = "LocalMusicSearchActivity";
    private ArtistExpandableSearchListAdapter artistExpandableSearchListAdapter;
    Observable.OnPropertyChangedCallback mSdPluginChangedCallback;
    Observable.OnPropertyChangedCallback mUsbPluginChangedCallback;
    private int currentTag = 1;
    private boolean focusOnSearch = true;
    private final Handler uiHandler = new AnonymousClass1(Looper.getMainLooper());
    private final ResultReceiver mInputMethodShowStatusResultReceiver = new ResultReceiver(new Handler(Looper.getMainLooper())) { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.10
        @Override // android.os.ResultReceiver
        protected void onReceiveResult(int i, Bundle bundle) {
            super.onReceiveResult(i, bundle);
            LogUtil.d(LocalMusicSearchActivity.TAG, "onReceiveResult: " + i);
        }
    };

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter.onItemOperateListener
    public void onCollectClick(LocalMusicBean localMusicBean) {
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter.onItemOperateListener
    public void onDeleteClick(LocalMusicBean localMusicBean) {
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ArtistExpandableSearchListAdapter.OnGroupClickListener
    public void onGroupClick() {
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    /* JADX INFO: renamed from: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity$1, reason: invalid class name */
    class AnonymousClass1 extends Handler {
        AnonymousClass1(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                LocalMusicSearchActivity.this.setFocusOnSearch(true);
                LocalMusicSearchActivity.this.showSoftKeyboard();
            } else if (i == 2) {
                LocalMusicSearchActivity.this.setFocusOnSearch(false);
                LocalMusicSearchActivity.this.hideSoftKeyboard();
            } else {
                if (i != 3) {
                    return;
                }
                LocalMusicSearchActivity.this.setFocusOnSearch(false);
                LocalMusicSearchActivity.this.hideSoftKeyboard();
                postDelayed(new Runnable() { // from class: com.byd.music.fragment.localMusic.search.-$$Lambda$LocalMusicSearchActivity$1$_8MOoGhoOOpDMg7MTROZUfITHUY
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleMessage$0$LocalMusicSearchActivity$1();
                    }
                }, 200L);
            }
        }

        public /* synthetic */ void lambda$handleMessage$0$LocalMusicSearchActivity$1() {
            LocalMusicSearchActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager;
        if (this.dataBinding == 0 || (inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService(Context.INPUT_METHOD_SERVICE)) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.getEditText().getWindowToken(), 2);
        enableWidgetCard(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSoftKeyboard() {
        InputMethodManager inputMethodManager;
        if (this.dataBinding == 0 || (inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService(Context.INPUT_METHOD_SERVICE)) == null) {
            return;
        }
        enableWidgetCard(false);
        inputMethodManager.showSoftInput(((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.getEditText(), 1);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        executeBackAction();
    }

    @Override // com.byd.music.base.BaseActivity
    protected int layoutId() {
        return R.layout.fragment_local_search;
    }

    @Override // com.byd.music.base.BaseActivity
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseActivity
    protected void initData() {
        this.mSdPluginChangedCallback = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.2
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isSdAvailable = MusicPlayHelper.getInstance().getIsSDPlug().get();
                LogUtil.d(getClass().toString(), "SD Plug Stat Changed to " + ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isSdAvailable);
                AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).curKeyWord != null) {
                            ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).startMatch(((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).curKeyWord);
                        }
                    }
                });
            }
        };
        this.mUsbPluginChangedCallback = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.3
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isUsbAvailable = MusicPlayHelper.getInstance().getIsUsbPlug().get();
                LogUtil.d(getClass().toString(), "USB Plug Stat Changed to " + ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isUsbAvailable);
                AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).curKeyWord != null) {
                            ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).startMatch(((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).curKeyWord);
                        }
                    }
                });
            }
        };
        setUsbSdCallback();
    }

    @Override // com.byd.music.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.d(TAG, "onCreate!");
        getWindow().setBackgroundDrawableResource(R.drawable.app_bg);
        if (bundle != null) {
            this.focusOnSearch = bundle.getBoolean(KEY_FOCUS_ON_SEARCH, true);
            boolean z = bundle.getBoolean(KEY_SOFT_INPUT_VISIBLE, false);
            if (this.viewModel != 0) {
                ((LocalMusicSearchViewModel) this.viewModel).updateExpectKeyboardStatue(z);
                LogUtil.d(TAG, "onCreate! inputVisible = " + z);
            }
            LogUtil.d(TAG, "onCreate! focusOnSearch = " + this.focusOnSearch);
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        StringBuilder sb = new StringBuilder("onSaveInstanceState");
        sb.append(" focusOnSearch=");
        sb.append(this.focusOnSearch);
        bundle.putBoolean(KEY_FOCUS_ON_SEARCH, this.focusOnSearch);
        boolean zIsSoftInputVisible = KeyboardUtils.isSoftInputVisible(this);
        sb.append(" inputVisible=");
        sb.append(zIsSoftInputVisible);
        bundle.putBoolean(KEY_SOFT_INPUT_VISIBLE, zIsSoftInputVisible);
        LogUtil.d(TAG, sb.toString());
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Window window = getWindow();
        window.setLayout(-1, -1);
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.setDimAmount(0.0f);
        window.getDecorView().setSystemUiVisibility(1280);
        final View viewFindViewById = window.getDecorView().findViewById(16908290);
        viewFindViewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                LogUtil.d(getClass().toString(), "addOnGlobalLayoutListener");
                Rect rect = new Rect();
                viewFindViewById.getWindowVisibleDisplayFrame(rect);
                int height = viewFindViewById.getHeight() - rect.bottom;
                ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).setKeyboardActive(height > 100);
                if (height > 100) {
                    LocalMusicSearchActivity.this.enableWidgetCard(false);
                }
            }
        });
        viewFindViewById.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() { // from class: com.byd.music.fragment.localMusic.search.-$$Lambda$LocalMusicSearchActivity$Tc5Nn_wMy-FXO0VZcFDq6kPtzdU
            @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
            public final void onWindowFocusChanged(boolean z) {
                this.f$0.restoreLocalSearchWindowState(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreLocalSearchWindowState(boolean z) {
        StringBuilder sb = new StringBuilder("restoreLocalSearchWindowState");
        sb.append(" hasFocus=");
        sb.append(z);
        if (!z || this.dataBinding == 0 || ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.getEditText() == null || DoubleClickHelper.isOnDoubleClick(200)) {
            return;
        }
        this.uiHandler.removeMessages(1);
        this.uiHandler.removeMessages(2);
        boolean zIsExpectKeyboardStatus = ((LocalMusicSearchViewModel) this.viewModel).isExpectKeyboardStatus();
        sb.append(" focusOnSearch=");
        sb.append(this.focusOnSearch);
        sb.append(" expectState=");
        sb.append(zIsExpectKeyboardStatus);
        if (zIsExpectKeyboardStatus) {
            this.uiHandler.sendEmptyMessageDelayed(1, 100L);
        } else {
            this.uiHandler.sendEmptyMessageDelayed(2, 100L);
        }
        LogUtil.e(TAG, sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeBackAction() {
        if (((LocalMusicSearchViewModel) this.viewModel).isAlbumDetailShow.get()) {
            ((LocalMusicSearchViewModel) this.viewModel).showAlbumCoverList();
        } else {
            LogUtil.d(TAG, "dismiss local search page!");
            this.uiHandler.sendEmptyMessageDelayed(3, 100L);
        }
    }

    @Override // com.byd.music.base.BaseActivity
    protected void initView() {
        initSearchBar();
        initRadioGroup();
        initHistoryGroup();
        refreshSpanCount();
        ((LocalMusicSearchViewModel) this.viewModel).isSearchFragmentShow.observe(this, new Observer<Boolean>() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.5
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                LogUtil.d(getClass().toString().toString(), "Observed Back Clicked!");
                LocalMusicSearchActivity.this.executeBackAction();
            }
        });
        restoreSearchFocus();
        ((FragmentLocalSearchBinding) this.dataBinding).flHolderMask.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.music.fragment.localMusic.search.-$$Lambda$LocalMusicSearchActivity$2U9HvDBM6Oxu95DbEG301DaYcWY
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$initView$0$LocalMusicSearchActivity(view, motionEvent);
            }
        });
        ((FragmentLocalSearchBinding) this.dataBinding).rvLocalMusicList.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.music.fragment.localMusic.search.-$$Lambda$LocalMusicSearchActivity$mb3Qw4MkGk_c7RfD6XldQ4QMRoY
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$initView$1$LocalMusicSearchActivity(view, motionEvent);
            }
        });
        ((FragmentLocalSearchBinding) this.dataBinding).rvAlbumList.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.music.fragment.localMusic.search.-$$Lambda$LocalMusicSearchActivity$1KjOyiHT-cwaqL2A5UK8w-bo7Io
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$initView$2$LocalMusicSearchActivity(view, motionEvent);
            }
        });
        ((FragmentLocalSearchBinding) this.dataBinding).elArtistList.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.music.fragment.localMusic.search.-$$Lambda$LocalMusicSearchActivity$mZLimJSov9rIZPT0GTr-Yuut5l8
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$initView$3$LocalMusicSearchActivity(view, motionEvent);
            }
        });
    }

    public /* synthetic */ boolean lambda$initView$0$LocalMusicSearchActivity(View view, MotionEvent motionEvent) {
        hideKeyboardOnTouchView();
        return false;
    }

    public /* synthetic */ boolean lambda$initView$1$LocalMusicSearchActivity(View view, MotionEvent motionEvent) {
        hideKeyboardOnTouchView();
        return false;
    }

    public /* synthetic */ boolean lambda$initView$2$LocalMusicSearchActivity(View view, MotionEvent motionEvent) {
        hideKeyboardOnTouchView();
        return false;
    }

    public /* synthetic */ boolean lambda$initView$3$LocalMusicSearchActivity(View view, MotionEvent motionEvent) {
        hideKeyboardOnTouchView();
        return false;
    }

    private void hideKeyboardOnTouchView() {
        if (this.viewModel == 0 || !((LocalMusicSearchViewModel) this.viewModel).isKeyboardActive() || DoubleClickHelper.isOnDoubleClick(200)) {
            return;
        }
        LogUtil.d(TAG, "hideKeyboardOnTouchView.");
        this.uiHandler.removeMessages(1);
        this.uiHandler.removeMessages(2);
        this.uiHandler.sendEmptyMessageDelayed(2, 100L);
    }

    private void refreshSpanCount() {
        RecyclerView.LayoutManager gridLayoutManager;
        ((LocalMusicSearchViewModel) this.viewModel).refreshSpanCount();
        if (((LocalMusicSearchViewModel) this.viewModel).albumSpanSize.get() == 0) {
            gridLayoutManager = new LinearLayoutManager(((FragmentLocalSearchBinding) this.dataBinding).rvAlbumList.getContext());
        } else {
            String simpleName = RecyclerViewGridBinding.class.getSimpleName();
            StringBuilder sb = new StringBuilder();
            sb.append("The New SpanSize is: ");
            sb.append(OrientationUtil.isPortrait() ? 4 : ((LocalMusicSearchViewModel) this.viewModel).albumSpanSize.get());
            LogUtil.d(simpleName, sb.toString());
            gridLayoutManager = new GridLayoutManager(this, (OrientationUtil.isPortrait() || ((LocalMusicSearchViewModel) this.viewModel).isMultiMode.get()) ? 4 : ((LocalMusicSearchViewModel) this.viewModel).albumSpanSize.get());
            if (!((LocalMusicSearchViewModel) this.viewModel).isMultiMode.get()) {
                ((FragmentLocalSearchBinding) this.dataBinding).rvAlbumList.addItemDecoration(new RecyclerViewGridBinding.GridSpacingItemDecoration(OrientationUtil.isPortrait() ? 4 : ((LocalMusicSearchViewModel) this.viewModel).albumSpanSize.get(), ((LocalMusicSearchViewModel) this.viewModel).albumSpanHDistance.get(), ((LocalMusicSearchViewModel) this.viewModel).albumSpanVDistance.get(), false, ((LocalMusicSearchViewModel) this.viewModel).isRight.get()));
            } else {
                ((FragmentLocalSearchBinding) this.dataBinding).rvAlbumList.addItemDecoration(new RecyclerViewGridBinding.GridSpacingItemDecoration(4, ((LocalMusicSearchViewModel) this.viewModel).albumSpanHDistanceMulti.get(), ((LocalMusicSearchViewModel) this.viewModel).albumSpanVDistanceMulti.get(), false, ((LocalMusicSearchViewModel) this.viewModel).isRight.get()));
            }
        }
        ((FragmentLocalSearchBinding) this.dataBinding).rvAlbumList.setLayoutManager(gridLayoutManager);
        ((FragmentLocalSearchBinding) this.dataBinding).rvAlbumList.setItemAnimator(null);
        ((FragmentLocalSearchBinding) this.dataBinding).rvLocalMusicList.setItemAnimator(null);
        ((LocalMusicSearchViewModel) this.viewModel).refreshSpanCount();
    }

    private void initHistoryGroup() {
        ((FragmentLocalSearchBinding) this.dataBinding).historyGroup.addHistoryItemClickListener(this);
        ((LocalMusicSearchViewModel) this.viewModel).getHistoryBeanList().observe(this, new Observer<List<SearchHistoryBean>>() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.6
            @Override // androidx.lifecycle.Observer
            public void onChanged(List<SearchHistoryBean> list) {
                ((FragmentLocalSearchBinding) LocalMusicSearchActivity.this.dataBinding).historyGroup.removeAllViews();
                for (SearchHistoryBean searchHistoryBean : list) {
                    LogUtil.d(getClass().toString(), "History Bean " + searchHistoryBean.getTitle() + " found.");
                    LocalMusicSearchActivity.this.addTextViewWithBeanInfo(searchHistoryBean.getTitle());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void addTextViewWithBeanInfo(String str) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, (int) (ScreenUtils.getScreenDensity() * 32.0f), (int) (ScreenUtils.getScreenDensity() * 16.0f));
        textView.setTextSize(2, 18.0f);
        textView.setBackgroundResource(R.drawable.bg_search_history);
        textView.setTextColor(getResources().getColor(R.color.color_local_search_text, getTheme()));
        textView.setPadding((int) (ScreenUtils.getScreenDensity() * 27.0f), (int) (ScreenUtils.getScreenDensity() * 8.0f), (int) (ScreenUtils.getScreenDensity() * 27.0f), (int) (ScreenUtils.getScreenDensity() * 8.0f));
        textView.setMaxWidth((int) (ScreenUtils.getScreenDensity() * 200.0f));
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setText(str);
        textView.setLayoutParams(layoutParams);
        ((FragmentLocalSearchBinding) this.dataBinding).historyGroup.addView(textView);
    }

    private void initRadioGroup() {
        ((FragmentLocalSearchBinding) this.dataBinding).tabLayoutLocalSearch.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);
        ArtistExpandableSearchListAdapter artistExpandableSearchListAdapter = new ArtistExpandableSearchListAdapter(((LocalMusicSearchViewModel) this.viewModel).localSearchArtistBeansList, ((FragmentLocalSearchBinding) this.dataBinding).elArtistList);
        this.artistExpandableSearchListAdapter = artistExpandableSearchListAdapter;
        artistExpandableSearchListAdapter.setOnGroupClickListener(this);
        this.artistExpandableSearchListAdapter.setOnItemOperateListener(this);
        ((FragmentLocalSearchBinding) this.dataBinding).rvAlbumList.setLayoutManager(new GridLayoutManager((Context) this, 6, 1, false));
        ((FragmentLocalSearchBinding) this.dataBinding).rvLocalMusicList.setItemAnimator(null);
        if (((LocalMusicSearchViewModel) this.viewModel).isMusicListChecked.get()) {
            ((FragmentLocalSearchBinding) this.dataBinding).tabLayoutLocalSearch.getTabAt(0).select();
        } else if (((LocalMusicSearchViewModel) this.viewModel).isArtistListChecked.get()) {
            ((FragmentLocalSearchBinding) this.dataBinding).tabLayoutLocalSearch.getTabAt(1).select();
        } else if (((LocalMusicSearchViewModel) this.viewModel).isAlbumListChecked.get()) {
            ((FragmentLocalSearchBinding) this.dataBinding).tabLayoutLocalSearch.getTabAt(2).select();
        }
    }

    private void initSearchBar() {
        ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.addTextChangedListener(new LocalSearchView.TextChangedListener() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.7
            @Override // com.byd.music.view.LocalSearchView.TextChangedListener
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // com.byd.music.view.LocalSearchView.TextChangedListener
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // com.byd.music.view.LocalSearchView.TextChangedListener
            public void afterTextChanged(Editable editable) {
                LogUtil.d(getClass().toString().toString(), "Search Keyword Changed to " + editable.toString());
                if (editable.length() == 0 || StringUtils.isSpace(editable.toString())) {
                    ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).curKeyWord = "";
                    ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).localSearchMusicBeansList.clear();
                    ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isRadioGroupVisible.set(false);
                    ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isMusicListVisible.set(false);
                    ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isAlbumListVisible.set(false);
                    ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isAlbumDetailShow.set(false);
                    ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isExpandableListVisible.set(false);
                    ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isNoData.set(false);
                    ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isSearchHistoryVisible.set(true);
                    return;
                }
                ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).startMatch(editable.toString());
                ((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isMusicListVisible.set(((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).localSearchMusicBeansList.size() > 0);
            }
        });
        ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.8
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    LocalMusicSearchActivity.this.uiHandler.sendEmptyMessageDelayed(2, 100L);
                    LogUtil.d(getClass().toString(), "ET Focus Canceled.");
                } else {
                    LogUtil.d(getClass().toString(), "ET Focus Gained.");
                    LocalMusicSearchActivity.this.uiHandler.sendEmptyMessageDelayed(1, 100L);
                }
            }
        });
        ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.9
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i != 0 && i != 3) || keyEvent == null) {
                    return false;
                }
                textView.clearFocus();
                return true;
            }
        });
    }

    public void setFocusOnSearch(boolean z) {
        LogUtil.d(TAG, "setFocusOnSearch = " + z);
        this.focusOnSearch = z;
    }

    @Override // com.byd.music.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.d(TAG, "onResume!");
        addSearchFocusListener(true);
        restoreSearchFocus();
        super.onResume();
    }

    @Override // com.byd.music.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.d(TAG, "onPause!");
        super.onPause();
        this.uiHandler.removeMessages(1);
        this.uiHandler.removeMessages(2);
        this.uiHandler.removeMessages(3);
    }

    @Override // com.byd.music.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        addSearchFocusListener(false);
        LogUtil.d(TAG, "onStop!");
        enableWidgetCard(true);
        super.onStop();
    }

    @Override // com.byd.music.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        LogUtil.d(TAG, "onDestroy!");
        MusicPlayHelper.getInstance().getIsUsbPlug().removeOnPropertyChangedCallback(this.mUsbPluginChangedCallback);
        MusicPlayHelper.getInstance().getIsSDPlug().removeOnPropertyChangedCallback(this.mSdPluginChangedCallback);
        this.artistExpandableSearchListAdapter.removeOnGroupClickListener();
        this.artistExpandableSearchListAdapter.removeOnItemOperateListener();
        ((FragmentLocalSearchBinding) this.dataBinding).tabLayoutLocalSearch.removeOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(String str) {
        LogUtil.d(getClass().toString(), "receiveEvent type In Search =str=" + str);
    }

    private void setUsbSdCallback() {
        MusicPlayHelper.getInstance().getIsSDPlug().addOnPropertyChangedCallback(this.mSdPluginChangedCallback);
        MusicPlayHelper.getInstance().getIsUsbPlug().addOnPropertyChangedCallback(this.mUsbPluginChangedCallback);
        ((LocalMusicSearchViewModel) this.viewModel).artistListChangeEvent.observe(this, new Observer() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.11
            @Override // androidx.lifecycle.Observer
            public void onChanged(Object obj) {
                LogUtil.d(getClass().toString(), "The Artist List Has Changed!");
                if (LocalMusicSearchActivity.this.artistExpandableSearchListAdapter != null) {
                    LocalMusicSearchActivity.this.artistExpandableSearchListAdapter.setKeyword(((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).curKeyWord);
                    LocalMusicSearchActivity.this.artistExpandableSearchListAdapter.notifyDataSetChanged();
                }
                if (((LocalMusicSearchViewModel) LocalMusicSearchActivity.this.viewModel).isAlbumDetailShow.get()) {
                    LocalMusicSearchActivity.this.executeBackAction();
                }
            }
        });
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter.onItemOperateListener
    public void onChildClick(List<LocalMusicBean> list, LocalMusicBean localMusicBean, int i, int i2) {
        ((LocalMusicSearchViewModel) this.viewModel).playMusicList(list, localMusicBean);
        ((LocalMusicSearchViewModel) this.viewModel).updateSearchHistoryDb(localMusicBean, 2);
        executeBackAction();
        EventBus.getDefault().post(new EventString("switchTab", "onChildClick"));
    }

    @Override // com.byd.music.view.FlowLayout.OnHistoryItemClickListener
    public void onHistoryClick(String str) {
        ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.getEditText().setText(str);
        ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.getEditText().setSelection(str.length());
        if (SearchHistoryDB.getBeanType(str) == 1) {
            ((FragmentLocalSearchBinding) this.dataBinding).tabLayoutLocalSearch.getTabAt(0).select();
        } else if (SearchHistoryDB.getBeanType(str) == 3) {
            ((FragmentLocalSearchBinding) this.dataBinding).tabLayoutLocalSearch.getTabAt(2).select();
        } else if (SearchHistoryDB.getBeanType(str) == 2) {
            ((FragmentLocalSearchBinding) this.dataBinding).tabLayoutLocalSearch.getTabAt(1).select();
        }
    }

    @Override // com.byd.music.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabSelected(TabLayout.Tab tab) {
        ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.getEditText().clearFocus();
        if (tab.getPosition() == 0) {
            LogUtil.d(getClass().toString(), "Music List in Search Clicked!");
            ((LocalMusicSearchViewModel) this.viewModel).isMusicListVisible.set(((LocalMusicSearchViewModel) this.viewModel).localSearchMusicBeansList.size() > 0);
            LogUtil.d(getClass().toString(), "Music List Size When Checked Is " + ((LocalMusicSearchViewModel) this.viewModel).localSearchMusicBeansList.size());
            ((LocalMusicSearchViewModel) this.viewModel).isExpandableListVisible.set(false);
            ((LocalMusicSearchViewModel) this.viewModel).isAlbumListVisible.set(false);
            ((LocalMusicSearchViewModel) this.viewModel).localSearchMusicBeansList.clear();
            ((LocalMusicSearchViewModel) this.viewModel).localSearchMusicBeansList.addAll(((LocalMusicSearchViewModel) this.viewModel).matchedSongBeanList);
            ((LocalMusicSearchViewModel) this.viewModel).isMusicListChecked.set(true);
            LogUtil.d(getClass().toString(), "Music List is Visible ? " + ((LocalMusicSearchViewModel) this.viewModel).isMusicListVisible.get());
            ((LocalMusicSearchViewModel) this.viewModel).isArtistListChecked.set(false);
            ((LocalMusicSearchViewModel) this.viewModel).isAlbumListChecked.set(false);
        } else if (tab.getPosition() == 1) {
            LogUtil.d(getClass().toString(), "Artist List in Search Clicked!");
            ((LocalMusicSearchViewModel) this.viewModel).isMusicListVisible.set(false);
            ((LocalMusicSearchViewModel) this.viewModel).isExpandableListVisible.set(true);
            ((LocalMusicSearchViewModel) this.viewModel).isAlbumListVisible.set(false);
            this.artistExpandableSearchListAdapter.setKeyword(((LocalMusicSearchViewModel) this.viewModel).curKeyWord);
            ((FragmentLocalSearchBinding) this.dataBinding).elArtistList.setAdapter(this.artistExpandableSearchListAdapter);
            ((LocalMusicSearchViewModel) this.viewModel).isMusicListChecked.set(false);
            ((LocalMusicSearchViewModel) this.viewModel).isArtistListChecked.set(true);
            ((LocalMusicSearchViewModel) this.viewModel).isAlbumListChecked.set(false);
        } else if (tab.getPosition() == 2) {
            LogUtil.d(getClass().toString(), "Album List in Search Clicked!");
            ((LocalMusicSearchViewModel) this.viewModel).isMusicListVisible.set(false);
            ((LocalMusicSearchViewModel) this.viewModel).isExpandableListVisible.set(false);
            ((LocalMusicSearchViewModel) this.viewModel).isAlbumListVisible.set(true);
            ((LocalMusicSearchViewModel) this.viewModel).isMusicListChecked.set(false);
            ((LocalMusicSearchViewModel) this.viewModel).isArtistListChecked.set(false);
            ((LocalMusicSearchViewModel) this.viewModel).isAlbumListChecked.set(true);
        }
        ((LocalMusicSearchViewModel) this.viewModel).isAlbumDetailShow.set(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableWidgetCard(boolean z) {
        LogUtil.d(TAG, "enableWidgetCard " + z);
        try {
            if (z) {
                ApiMethod.showWidgetCards(this);
            } else {
                ApiMethod.hideWidgetCards(this);
            }
        } catch (Exception unused) {
            LogUtil.e(getClass().getSimpleName(), "Failed to invoke widget card api.");
        }
    }

    private void addSearchFocusListener(boolean z) {
        LogUtil.d(TAG, "addSearchFocusListener " + z);
        if (z) {
            ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity.12
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view, boolean z2) {
                    if (!z2) {
                        LocalMusicSearchActivity.this.uiHandler.sendEmptyMessageDelayed(2, 100L);
                        LogUtil.d(getClass().toString(), "ET Focus Canceled.");
                    } else {
                        LogUtil.d(getClass().toString(), "ET Focus Gained.");
                        LocalMusicSearchActivity.this.uiHandler.sendEmptyMessageDelayed(1, 100L);
                    }
                }
            });
        } else {
            ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.getEditText().setOnFocusChangeListener(null);
        }
    }

    private void restoreSearchFocus() {
        if (this.focusOnSearch) {
            ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.postDelayed(new Runnable() { // from class: com.byd.music.fragment.localMusic.search.-$$Lambda$LocalMusicSearchActivity$wFKj5Lz-7koXDtUY8RY_NE687oE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$restoreSearchFocus$4$LocalMusicSearchActivity();
                }
            }, 100L);
        }
    }

    public /* synthetic */ void lambda$restoreSearchFocus$4$LocalMusicSearchActivity() {
        StringBuilder sb = new StringBuilder("restoreSearchFocus ");
        if (this.dataBinding == 0 || ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch == null || ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.getEditText() == null) {
            sb.append("the LocalSearch View is null and return!!! ");
            LogUtil.e(TAG, sb.toString());
        } else {
            ((FragmentLocalSearchBinding) this.dataBinding).etLocalSearch.getEditText().requestFocus();
        }
    }
}
