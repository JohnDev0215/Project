package com.byd.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import b.a.d;
import b.a.k.v;
import com.byd.auto_photo.AboutActivity;
import com.byd.auto_photo.MainBaseActivity;
import com.byd.auto_photo.R;
import com.byd.auto_photo.a;
import com.byd.bean.AlbumItem;
import com.byd.taskmanager.b.c;
import com.byd.util.e;
import com.byd.util.f;
import com.byd.util.g;
import com.byd.util.k;
import com.byd.util.u;
import com.byd.util.w;
import com.byd.util.x;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SearchAndSelectBaseFragment extends SubBaseFragment implements View.OnClickListener, TextWatcher, View.OnFocusChangeListener {
    protected static final int MAX_TEXT_LEN = 20;
    public static final int MODE_SEARCH = 1;
    public static final int MODE_TITLE = 2;
    private static final int MSG_SHOW_INPUT = 2;
    private static final int MSG_START_SEARCH = 1;
    protected AlbumItem album;
    protected View btnAbout;
    protected ImageView btnCancelSearch;
    protected TextView btnCopy;
    protected TextView btnMove;
    protected TextView btnReName;
    protected TextView btnSelectAll;
    protected TextView btnSelectCancel;
    protected TextView btnSelectDelete;
    protected TextView btnShare;
    protected TextView btnTitleToSelect;
    protected View btnToSelect;
    protected EditText etSearchWord;
    private InputMethodManager imm;
    protected boolean isInSearchPage;
    protected boolean isSelectAll;
    protected boolean isSelectMode;
    protected boolean isStarted;
    protected View layoutSearch;
    protected View layoutSelect;
    protected View layoutTitle;
    private MediaFileGridFragment mediaFileGridFragment;
    protected int mode;
    protected OnHeaderListener onHeaderListener;
    private ProgressBar progressSearch;
    private SearchCallback searchCallback;
    private boolean searchForVoice;
    protected int searchSource;
    private v searchTask;
    private boolean softInputShow;
    protected TextView tvTitleBack;
    protected TextView viewToSearchMode;
    private int searchType = 0;
    protected int defShareVisibility = 0;
    private Point screenSize = new Point();
    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() { // from class: com.byd.fragment.SearchAndSelectBaseFragment.2
        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            String strTrim = textView.getText().toString().trim();
            if (TextUtils.isEmpty(strTrim)) {
                textView.setText("");
            } else {
                f.k(SearchAndSelectBaseFragment.this.getActivity()).d(strTrim);
                MediaFileGridBaseFragment mediaFileGridBaseFragmentC0 = ((MainBaseActivity) SearchAndSelectBaseFragment.this.getActivity()).C0();
                if (mediaFileGridBaseFragmentC0 != null) {
                    mediaFileGridBaseFragmentC0.onSearchEditorAction();
                }
            }
            SearchAndSelectBaseFragment.this.hideSoftInput();
            return false;
        }
    };
    private Handler mHandler = new MHandler(this);

    private static class MHandler extends d<SearchAndSelectBaseFragment> {
        public MHandler(SearchAndSelectBaseFragment searchAndSelectBaseFragment) {
            super(searchAndSelectBaseFragment);
        }

        @Override // b.a.d
        public void handleMessage(SearchAndSelectBaseFragment searchAndSelectBaseFragment, Message message) {
            int i = message.what;
            if (i == 1) {
                searchAndSelectBaseFragment.startSearch(message);
            } else {
                if (i != 2) {
                    return;
                }
                searchAndSelectBaseFragment.showSoftInput();
            }
        }
    }

    public interface OnHeaderListener {
        public static final int SORT_TIME = 1;

        void onCopy(boolean z);

        void onDelete();

        void onRename();

        void onSelectModeChange(boolean z);

        void onShare();

        void onShowDetail();

        void onSort(int i, boolean z);

        void onToolBarViewClick(View view);

        void selectAllOrCancel();
    }

    private class SearchCallback<T> extends c<List<T>> {
        private SearchCallback() {
        }

        @Override // com.byd.taskmanager.b.b
        public void onComplete(String str, List<T> list) {
            if (SearchAndSelectBaseFragment.this.progressSearch.getVisibility() == 0) {
                SearchAndSelectBaseFragment.this.progressSearch.setVisibility(8);
            }
            MainBaseActivity mainBaseActivity = (MainBaseActivity) SearchAndSelectBaseFragment.this.getActivity();
            if (mainBaseActivity.y0() == null && mainBaseActivity.A0() == null && SearchAndSelectBaseFragment.this.etSearchWord.getText().length() == 0) {
                SearchAndSelectBaseFragment.this.searchForVoice = false;
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("on search onComplete : ");
            sb.append(list == null ? 0 : list.size());
            w.c(sb.toString());
            u uVar = new u();
            uVar.d(list);
            mainBaseActivity.T0(uVar);
            int iN = SearchAndSelectBaseFragment.this.searchTask.n();
            if (SearchAndSelectBaseFragment.this.searchForVoice) {
                x.u(SearchAndSelectBaseFragment.this.getActivity(), "search", iN);
                SearchAndSelectBaseFragment.this.searchForVoice = false;
            }
            MediaFileGridBaseFragment mediaFileGridBaseFragmentC0 = ((MainBaseActivity) SearchAndSelectBaseFragment.this.getActivity()).C0();
            if (mediaFileGridBaseFragmentC0 == null) {
                return;
            }
            mediaFileGridBaseFragmentC0.setSearchResultDatas(uVar, iN);
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    private void doSearch(String str) {
        this.mHandler.removeMessages(1);
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(1, str), 600L);
    }

    private void reset() {
        if (this.etSearchWord.getVisibility() == 0) {
            this.etSearchWord.removeTextChangedListener(this);
        }
        this.etSearchWord.setText((CharSequence) null);
        this.mode = 0;
        this.album = null;
        this.isInSearchPage = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSoftInput() {
        String str;
        if (e.E()) {
            return;
        }
        MediaFileGridBaseFragment mediaFileGridBaseFragmentC0 = ((MainBaseActivity) getActivity()).C0();
        if ((mediaFileGridBaseFragmentC0 == null || !mediaFileGridBaseFragmentC0.isOutOfFragment()) && this.isStarted) {
            getActivity().getWindowManager().getDefaultDisplay().getRealSize(this.screenSize);
            if (!x.n(getActivity(), this.screenSize)) {
                showSoftInputUnCheck();
                return;
            }
            str = "soft input already show";
        } else {
            str = "out of fragment!!";
        }
        w.c(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSoftInputUnCheck() {
        boolean zShowSoftInput = this.imm.showSoftInput(this.etSearchWord, 0);
        if (!this.etSearchWord.isFocused()) {
            this.etSearchWord.requestFocus();
        }
        w.c("showSoftInput : " + zShowSoftInput);
        if (zShowSoftInput || !this.isStarted) {
            return;
        }
        this.mHandler.sendEmptyMessageDelayed(2, 300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSearch(Message message) {
        String str = (String) message.obj;
        if (this.searchTask == null) {
            this.searchCallback = a.f1178a.intValue() == 2 ? new SearchCallback() : new SearchCallback();
            v vVar = new v(getActivity(), this.searchCallback);
            this.searchTask = vVar;
            addTask(0, vVar, this.searchCallback);
        } else {
            cancelTask(0);
        }
        ((MainBaseActivity) getActivity()).S0(str);
        this.searchTask.o(str, this.searchSource, this.searchType);
        if (this.progressSearch.getVisibility() != 0) {
            this.progressSearch.setVisibility(0);
        }
        executeTask(0);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        w.c("==afterTextChanged:" + ((Object) editable));
        if (editable.length() > 0) {
            String strTrim = editable.toString().trim();
            if (strTrim.length() > 0) {
                doSearch(strTrim);
                return;
            } else {
                this.mHandler.removeMessages(1);
                ((MainBaseActivity) getActivity()).s0();
                return;
            }
        }
        this.mHandler.removeMessages(1);
        cancelTask(0);
        ((MainBaseActivity) getActivity()).s0();
        if (((MainBaseActivity) getActivity()).C0() != null) {
            ((MainBaseActivity) getActivity()).C0().setSearchResultDatas(null, 0);
        }
    }

    protected void applyTextSize(int i) {
        float f = i;
        this.btnSelectAll.setTextSize(0, f);
        this.btnShare.setTextSize(0, f);
        this.btnSelectDelete.setTextSize(0, f);
        this.btnMove.setTextSize(0, f);
        this.btnReName.setTextSize(0, f);
        this.btnCopy.setTextSize(0, f);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void cancelSelectMode() {
        this.isSelectMode = false;
        OnHeaderListener onHeaderListener = this.onHeaderListener;
        if (onHeaderListener != null) {
            onHeaderListener.onSelectModeChange(false);
        }
        checkMode();
        setSelectAll(false);
    }

    protected void checkMode() {
        View view;
        TextView textView;
        int i = this.mode;
        if (i != 1) {
            if (i != 2) {
                return;
            }
            if (!this.isSelectMode) {
                this.layoutTitle.setVisibility(0);
                view = this.layoutSelect;
                view.setVisibility(8);
                return;
            }
            this.layoutTitle.setVisibility(8);
            this.layoutSelect.setVisibility(0);
            if (a.f1178a.intValue() == 2) {
                this.btnCopy.setVisibility(0);
            }
            this.btnMove.setVisibility(getMoveBtnVisibility());
            if (this.searchSource != 1) {
                this.btnReName.setVisibility(8);
                this.btnShare.setVisibility(this.defShareVisibility);
                return;
            } else {
                this.btnReName.setVisibility(0);
                textView = this.btnShare;
                textView.setVisibility(8);
            }
        }
        if (!this.isSelectMode) {
            this.layoutSearch.setVisibility(0);
            this.layoutSelect.setVisibility(8);
            if (this.isInSearchPage || this.searchSource != 1 || a.f1178a.intValue() == 2) {
                return;
            }
            view = this.btnToSelect;
            view.setVisibility(8);
            return;
        }
        this.layoutSearch.setVisibility(8);
        this.layoutSelect.setVisibility(0);
        if (this.searchSource != 1) {
            this.btnReName.setVisibility(8);
            this.btnShare.setVisibility(this.defShareVisibility);
        } else {
            this.btnReName.setVisibility(0);
            this.btnShare.setVisibility(8);
        }
        if (this.isInSearchPage || this.searchSource != 1) {
            if (a.f1178a.intValue() == 2) {
                this.btnCopy.setVisibility(0);
            }
            this.btnMove.setVisibility(getMoveBtnVisibility());
        }
        if (a.f1178a.intValue() == 2 || this.searchSource != 1) {
            return;
        }
        textView = this.btnSelectCancel;
        textView.setVisibility(8);
    }

    protected void checkToClearOnStop() {
        MainBaseActivity mainBaseActivity = (MainBaseActivity) getActivity();
        if (this.etSearchWord.getVisibility() != 0 || this.etSearchWord.getText().length() != 0 || mainBaseActivity.y0() == null || "".equals(mainBaseActivity.y0())) {
            return;
        }
        mainBaseActivity.s0();
    }

    protected Drawable createDrawableInTheme(int i) {
        return x.s(getActivity(), i);
    }

    @Override // com.byd.ui.a
    protected int getLayout() {
        return R.layout.fragment_search_and_select;
    }

    protected int getMoveBtnVisibility() {
        return 0;
    }

    protected void hideSearchResult() {
        MainBaseActivity mainBaseActivity = (MainBaseActivity) getActivity();
        if (mainBaseActivity.C0() != null) {
            mainBaseActivity.C0().setSearchResultDatas(null, 0);
        }
        mainBaseActivity.Q0();
    }

    public void hideSoftInput() {
        if (e.E()) {
            return;
        }
        this.mHandler.removeMessages(2);
        if (this.etSearchWord == null) {
            return;
        }
        w.c("search page hideSoftInput");
        w.c("hideSoftInput : " + this.imm.hideSoftInputFromWindow(this.etSearchWord.getWindowToken(), 0));
    }

    protected void initSearchEditView(View view) {
        this.etSearchWord = (EditText) view.findViewById(R.id.et_search_word);
        x.w(getActivity(), 16, this.etSearchWord);
    }

    public void initSearchModeUI(final boolean z, final int i) {
        w.c("initSearchModeUI " + z + " " + i);
        this.isInSearchPage = true;
        this.searchForVoice = z;
        this.etSearchWord.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.fragment.SearchAndSelectBaseFragment.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                SearchAndSelectBaseFragment.this.etSearchWord.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (z || i >= 2) {
                    return;
                }
                w.c("initSearchModeUI start show soft input " + SearchAndSelectBaseFragment.this.screenSize);
                SearchAndSelectBaseFragment.this.mHandler.removeMessages(2);
                SearchAndSelectBaseFragment.this.showSoftInputUnCheck();
            }
        });
        this.etSearchWord.setVisibility(0);
        this.viewToSearchMode.setVisibility(8);
        this.btnToSelect.setVisibility(8);
        this.btnAbout.setVisibility(8);
        this.etSearchWord.addTextChangedListener(this);
        this.etSearchWord.setOnFocusChangeListener(this);
        this.etSearchWord.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        this.etSearchWord.setOnEditorActionListener(this.editorActionListener);
        k.c(getActivity()).g("10");
    }

    public void intoSelectMode(boolean z) {
        OnHeaderListener onHeaderListener;
        this.isSelectMode = true;
        if (this.isInSearchPage) {
            hideSoftInput();
        }
        if (z && (onHeaderListener = this.onHeaderListener) != null) {
            onHeaderListener.onSelectModeChange(this.isSelectMode);
        }
        checkMode();
    }

    public boolean isSelectMode() {
        return this.isSelectMode;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_about /* 2131230791 */:
                startActivity(new Intent(getActivity(), (Class<?>) AboutActivity.class));
                return;
            case R.id.btn_copy /* 2131230798 */:
                OnHeaderListener onHeaderListener = this.onHeaderListener;
                if (onHeaderListener != null) {
                    onHeaderListener.onCopy(false);
                    return;
                }
                return;
            case R.id.btn_delete /* 2131230800 */:
                OnHeaderListener onHeaderListener2 = this.onHeaderListener;
                if (onHeaderListener2 != null) {
                    onHeaderListener2.onDelete();
                    return;
                }
                return;
            case R.id.btn_move /* 2131230808 */:
                OnHeaderListener onHeaderListener3 = this.onHeaderListener;
                if (onHeaderListener3 != null) {
                    onHeaderListener3.onCopy(true);
                    return;
                }
                return;
            case R.id.btn_rename /* 2131230814 */:
                OnHeaderListener onHeaderListener4 = this.onHeaderListener;
                if (onHeaderListener4 != null) {
                    onHeaderListener4.onRename();
                    return;
                }
                return;
            case R.id.btn_select_all /* 2131230818 */:
                OnHeaderListener onHeaderListener5 = this.onHeaderListener;
                if (onHeaderListener5 != null) {
                    onHeaderListener5.selectAllOrCancel();
                    return;
                }
                return;
            case R.id.btn_select_cancel /* 2131230819 */:
                cancelSelectMode();
                return;
            case R.id.btn_share /* 2131230821 */:
                OnHeaderListener onHeaderListener6 = this.onHeaderListener;
                if (onHeaderListener6 != null) {
                    onHeaderListener6.onShare();
                    return;
                }
                return;
            case R.id.btn_title_to_select /* 2131230825 */:
                break;
            case R.id.btn_to_select /* 2131230828 */:
                if (this.isSelectMode) {
                    return;
                }
                break;
            case R.id.tv_title_back /* 2131231162 */:
                reset();
                ((MainBaseActivity) getActivity()).Q0();
                return;
            case R.id.tv_to_search_mode /* 2131231163 */:
                if (this.mediaFileGridFragment == null) {
                    MediaFileGridFragment mediaFileGridFragment = new MediaFileGridFragment();
                    this.mediaFileGridFragment = mediaFileGridFragment;
                    mediaFileGridFragment.setShowLevel(1);
                }
                this.mediaFileGridFragment.setSearchSource(this.searchSource);
                MainBaseActivity mainBaseActivity = (MainBaseActivity) getActivity();
                if (mainBaseActivity == null) {
                    w.c("to search mode fail by activity is null");
                    return;
                } else {
                    w.c("to search mode");
                    mainBaseActivity.j0(this.mediaFileGridFragment, true);
                    return;
                }
            case R.id.view_cancel_search /* 2131231179 */:
                w.c("onClick cancel search !!!");
                if (a.f1178a.intValue() == 2) {
                    hideSoftInput();
                    if (this.etSearchWord.getVisibility() == 0) {
                        hideSearchResult();
                        return;
                    }
                    return;
                }
                if (this.etSearchWord.getVisibility() == 0) {
                    this.etSearchWord.removeTextChangedListener(this);
                }
                this.btnCancelSearch.setVisibility(8);
                this.etSearchWord.setText((CharSequence) null);
                MainBaseActivity mainBaseActivity2 = (MainBaseActivity) getActivity();
                mainBaseActivity2.s0();
                if (mainBaseActivity2.C0() != null) {
                    mainBaseActivity2.C0().setSearchResultDatas(null, 0);
                }
                if (this.etSearchWord.getVisibility() == 0) {
                    this.etSearchWord.addTextChangedListener(this);
                    return;
                }
                return;
            default:
                OnHeaderListener onHeaderListener7 = this.onHeaderListener;
                if (onHeaderListener7 != null) {
                    onHeaderListener7.onToolBarViewClick(view);
                    return;
                }
                return;
        }
        intoSelectMode(true);
    }

    @Override // com.byd.fragment.SubBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        this.listenExternalState = false;
        super.onCreate(bundle);
        this.imm = (InputMethodManager) getActivity().getSystemService("input_method");
    }

    public void onDataSizeChange(int i) {
    }

    @Override // com.byd.fragment.SubBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.softInputShow = false;
    }

    @Override // com.byd.ui.a, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        reset();
        super.onDestroyView();
    }

    @Override // com.byd.fragment.SubBaseFragment, b.a.k.h.b
    public void onExternalEject(String str, boolean z) {
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
    }

    @Override // com.byd.fragment.SubBaseFragment
    protected void onLocaleChanged(Configuration configuration) {
        TextView textView;
        int i;
        if (this.isSelectAll) {
            textView = this.btnSelectAll;
            i = R.string.cancel_select_all;
        } else {
            textView = this.btnSelectAll;
            i = R.string.select_all;
        }
        textView.setText(i);
        this.btnShare.setText(R.string.share);
        this.btnCopy.setText(R.string.copy);
        this.btnSelectDelete.setText(R.string.delete);
        this.etSearchWord.setHint(R.string.search_tips);
        this.btnMove.setText(R.string.move);
        this.btnReName.setText(R.string.rename);
        applyTextSize(getActivity().getResources().getDimensionPixelSize(R.dimen.search_item_text_size));
        onShowTitle();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        this.isStarted = false;
        w.c("search fragment onPause");
        super.onPause();
        this.softInputShow = false;
        if (this.isInSearchPage) {
            this.softInputShow = x.n(getActivity(), this.screenSize) || this.mHandler.hasMessages(2);
            w.c("softInputShow : " + this.softInputShow);
            if (this.softInputShow) {
                hideSoftInput();
            } else {
                this.mHandler.removeMessages(2);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        this.isStarted = true;
        super.onResume();
        if (this.isInSearchPage && !this.mHandler.hasMessages(2) && this.softInputShow) {
            w.c("search onResume to show");
            this.mHandler.sendEmptyMessageDelayed(2, 500L);
            this.softInputShow = false;
        }
    }

    public void onSelectedSizeChange(int i) {
    }

    protected void onShowTitle() {
        TextView textView;
        if (this.album == null) {
            this.tvTitleBack.setText("");
            return;
        }
        String strD = x.d(getActivity(), this.album, g.i(getActivity(), "/"));
        if (strD.length() > 18) {
            textView = this.tvTitleBack;
            strD = strD.substring(0, 18) + "...";
        } else {
            textView = this.tvTitleBack;
        }
        textView.setText(strD);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        checkToClearOnStop();
        super.onStop();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        w.c("searchAndSelect onViewCreated");
        this.btnSelectCancel = (TextView) view.findViewById(R.id.btn_select_cancel);
        this.btnSelectAll = (TextView) view.findViewById(R.id.btn_select_all);
        this.btnShare = (TextView) view.findViewById(R.id.btn_share);
        this.btnReName = (TextView) view.findViewById(R.id.btn_rename);
        this.btnSelectDelete = (TextView) view.findViewById(R.id.btn_delete);
        this.btnCancelSearch = (ImageView) view.findViewById(R.id.view_cancel_search);
        this.progressSearch = (ProgressBar) view.findViewById(R.id.progress_search);
        this.btnToSelect = view.findViewById(R.id.btn_to_select);
        this.btnAbout = view.findViewById(R.id.btn_about);
        initSearchEditView(view);
        this.btnCopy = (TextView) view.findViewById(R.id.btn_copy);
        this.btnMove = (TextView) view.findViewById(R.id.btn_move);
        this.tvTitleBack = (TextView) view.findViewById(R.id.tv_title_back);
        this.viewToSearchMode = (TextView) view.findViewById(R.id.tv_to_search_mode);
        this.btnTitleToSelect = (TextView) view.findViewById(R.id.btn_title_to_select);
        this.layoutSearch = view.findViewById(R.id.layout_search);
        this.layoutSelect = view.findViewById(R.id.layout_select);
        this.layoutTitle = view.findViewById(R.id.layout_title);
        this.btnSelectCancel.setOnClickListener(this);
        this.btnSelectAll.setOnClickListener(this);
        this.btnShare.setOnClickListener(this);
        this.btnReName.setOnClickListener(this);
        this.btnSelectDelete.setOnClickListener(this);
        this.btnCancelSearch.setOnClickListener(this);
        this.btnToSelect.setOnClickListener(this);
        this.btnAbout.setOnClickListener(this);
        this.btnCopy.setOnClickListener(this);
        this.btnMove.setOnClickListener(this);
        this.tvTitleBack.setOnClickListener(this);
        this.viewToSearchMode.setOnClickListener(this);
        this.btnTitleToSelect.setOnClickListener(this);
        onShowTitle();
        getActivity().getWindowManager().getDefaultDisplay().getRealSize(this.screenSize);
        w.c("searchAndSelect onViewCreated finish");
    }

    public void setAlbum(AlbumItem albumItem) {
        this.album = albumItem;
        if (this.tvTitleBack != null) {
            onShowTitle();
        }
    }

    public void setMode(int i) {
        this.mode = i;
        checkMode();
    }

    public void setOnHeaderListener(OnHeaderListener onHeaderListener) {
        this.onHeaderListener = onHeaderListener;
    }

    public void setRenameEnable(boolean z) {
        if (this.btnReName.isEnabled() != z) {
            this.btnReName.setEnabled(z);
        }
    }

    public void setSearchForVoice(boolean z) {
        this.searchForVoice = z;
    }

    public void setSearchSource(int i) {
        this.searchSource = i;
    }

    public void setSearchTextNoWatcher(String str) {
        if (str == null || "".equals(str)) {
            return;
        }
        this.etSearchWord.removeTextChangedListener(this);
        this.etSearchWord.setText(str);
        this.etSearchWord.setSelection(str.length());
        this.etSearchWord.addTextChangedListener(this);
    }

    public void setSelectAll(boolean z) {
        TextView textView;
        int i;
        if (this.isSelectAll != z) {
            this.isSelectAll = z;
            if (z) {
                textView = this.btnSelectAll;
                i = R.string.cancel_select_all;
            } else {
                textView = this.btnSelectAll;
                i = R.string.select_all;
            }
            textView.setText(i);
        }
    }

    public void setShareEnable(boolean z) {
        if (this.btnShare.isEnabled() != z) {
            this.btnShare.setEnabled(z);
        }
    }

    protected void setTextViewDrawableLeft(TextView textView, int i) {
        setTextViewDrawableLeft(textView, i, false);
    }

    protected void setTextViewDrawableLeft(TextView textView, int i, boolean z) {
        if (getActivity() == null) {
            return;
        }
        if (z || getActivity().getResources().getConfiguration().getLayoutDirection() != 1) {
            textView.setCompoundDrawablesWithIntrinsicBounds(createDrawableInTheme(i), (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, createDrawableInTheme(i), (Drawable) null);
        }
    }

    protected void setTextViewDrawableTop(TextView textView, int i) {
        textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, createDrawableInTheme(i), (Drawable) null, (Drawable) null);
    }

    public void startSearch(String str, int i) {
        this.searchType = i;
        if (this.etSearchWord != null && str != null && !"".equals(str.trim())) {
            this.etSearchWord.setText(str);
            this.etSearchWord.setSelection(str.length());
        } else if (i != 0) {
            doSearch("");
        }
    }
}
