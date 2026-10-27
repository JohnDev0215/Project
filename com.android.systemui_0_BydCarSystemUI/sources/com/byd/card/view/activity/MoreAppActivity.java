package com.byd.card.view.activity;

import android.app.UiStyleConfig;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Constants;
import com.android.systemui.R;
import com.byd.card.adapter.AutoLineLayoutManager;
import com.byd.card.adapter.MoreRecentAppAdapter;
import com.byd.card.adapter.SearchHistoryTextAdapter;
import com.byd.card.adapter.SearchResultAdapter;
import com.byd.card.base.BaseMvpActivity;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.bean.SearchHistory;
import com.byd.card.bean.ShortcutInfo;
import com.byd.card.callback.SoftKeyBoardListener;
import com.byd.card.contract.MoreAppContract;
import com.byd.card.drag.ApplicationInfo;
import com.byd.card.drag.CombineView;
import com.byd.card.floatcard.Executors;
import com.byd.card.presenter.MoreAppPresenter;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.BitmapUtils;
import com.byd.card.utils.CarPlayAndAutoManager;
import com.byd.card.utils.DensityUtil;
import com.byd.card.view.broadcast.AppInstallBroadcastReceiver;
import com.byd.card.view.dialog.SecondConfirmDialog;
import com.byd.card.widget.BlurView;
import com.byd.card.widget.OverScrollLayout;
import com.byd.card.widget.RecentUseItemDecoration;
import com.ts.avm.sdk.impl.AvmManagerImpl;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class MoreAppActivity extends BaseMvpActivity<MoreAppPresenter> implements MoreAppContract.View, TextWatcher {
    private static final String ACTION_DIALOG_DISMISS = "dialog_app_detail_dismiss";
    private static final String ACTION_DIALOG_SHOW = "dialog_app_detail_show";
    private static final String TAG = "MoreAppActivity";
    private Bitmap backgroundBitmap;
    public CountDownTimer cancelEdit;
    public CountDownTimer countDownTimer;
    private LinearLayout mAppMarket;
    private ImageView mBack;
    private BlurView mBlurView;
    private ImageView mClear;
    private ImageView mClose;
    private int mCombineViewWidth;
    private ImageView mDeleteAllHistory;
    private int mDialogHeight;
    private int mDialogWidth;
    private RecyclerView mHistoryTxtRecycler;
    private String mLastSearchText;
    private TextView mMyAppTextView;
    private TextView mNoHistory;
    private TextView mNoRecentUse;
    private TextView mNoSearchResult;
    private int mOrientationOld;
    private MoreRecentAppAdapter mRecentAdapter;
    private RecyclerView mRecentRecycler;
    private ScrollView mRecentScroll;
    private TextView mRecentUse;
    private RelativeLayout mSearchBg;
    private EditText mSearchEdit;
    private SearchHistoryTextAdapter mSearchHistoryAdapter;
    private TextView mSearchHistoryTxt;
    private SearchResultAdapter mSearchResultAdapter;
    private RecyclerView mSearchResultRV;
    private OverScrollLayout mSearchScroll;
    private int mSearchScrollMaxHeight;
    private SecondConfirmDialog mSecondConfirmDialog;
    private ImageView mVoice;
    private ImageView mVoiceDivider;
    private CombineView mCombineView = null;
    private long advertisingTime = 8000;
    private long cancelTime = 2000;
    private final int FLAG_RECEIVER_INCLUDE_BACKGROUND = 16777216;
    private boolean mClearFocus = false;
    private boolean mSearchFocus = false;
    private ApplicationInfo mSelectedAppInfo = null;
    private BroadcastReceiver mDialogAppDetail = new BroadcastReceiver() { // from class: com.byd.card.view.activity.MoreAppActivity.20
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            action.hashCode();
            if (action.equals(MoreAppActivity.ACTION_DIALOG_DISMISS)) {
                MoreAppActivity.this.startAD();
            } else if (action.equals(MoreAppActivity.ACTION_DIALOG_SHOW)) {
                MoreAppActivity.this.cancelTimer();
            }
        }
    };

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
    }

    public void setSelectedAppInfo(ApplicationInfo info) {
        this.mSelectedAppInfo = info;
    }

    public ApplicationInfo getSelectedAppInfo() {
        return this.mSelectedAppInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.card.base.BaseMvpActivity
    public MoreAppPresenter createPresenter() {
        return new MoreAppPresenter();
    }

    @Override // com.byd.card.base.BaseMvpActivity
    protected int getLayoutId() {
        int uiStyle = UiStyleConfig.getInstance(this).getUiStyle();
        return uiStyle != 1 ? uiStyle != 2 ? AppUtils.isRightRudder() ? R.layout.activity_more_app_right : R.layout.activity_more_app : AppUtils.isRightRudder() ? R.layout.activity_more_app_right : R.layout.activity_more_app : AppUtils.isRightRudder() ? R.layout.denza_activity_more_app_right : R.layout.denza_activity_more_app;
    }

    @Override // com.byd.card.base.BaseMvpActivity
    protected void initView() {
        EventBus.getDefault().register(this);
        registerDialogAppDetail();
        int i = getResources().getConfiguration().orientation;
        this.mOrientationOld = i;
        updateUI(i);
        findView(this.mOrientationOld);
        setBackgroundBitmap();
        initData();
        initListener();
    }

    private void findView(int orientation) {
        this.mClose = (ImageView) findViewById(R.id.more_close);
        this.mBack = (ImageView) findViewById(R.id.more_back);
        this.mClear = (ImageView) findViewById(R.id.clear);
        this.mVoice = (ImageView) findViewById(R.id.voice);
        this.mVoiceDivider = (ImageView) findViewById(R.id.voice_divider);
        this.mRecentUse = (TextView) findViewById(R.id.recent_use);
        this.mSearchHistoryTxt = (TextView) findViewById(R.id.search_history);
        this.mSearchScroll = (OverScrollLayout) findViewById(R.id.search_scroll);
        this.mSearchResultRV = (RecyclerView) findViewById(R.id.search_result_rv);
        this.mNoHistory = (TextView) findViewById(R.id.no_history);
        this.mNoSearchResult = (TextView) findViewById(R.id.no_search_result);
        this.mRecentScroll = (ScrollView) findViewById(R.id.recent_scroll);
        this.mNoRecentUse = (TextView) findViewById(R.id.no_recent_use);
        this.mRecentRecycler = (RecyclerView) findViewById(R.id.recent_recycler);
        this.mHistoryTxtRecycler = (RecyclerView) findViewById(R.id.history_recycler);
        this.mSearchBg = (RelativeLayout) findViewById(R.id.search_bg);
        this.mSearchEdit = (EditText) findViewById(R.id.more_search_edit);
        this.mDeleteAllHistory = (ImageView) findViewById(R.id.delete_history_all);
        this.mMyAppTextView = (TextView) findViewById(R.id.my_app);
        this.mAppMarket = (LinearLayout) findViewById(R.id.app_market_btn);
        this.mCombineView = new CombineView(this);
        if (orientation == 2) {
            this.mCombineViewWidth = (int) getResources().getDimension(R.dimen.dp_1232);
        } else if (orientation == 1) {
            this.mCombineViewWidth = (int) getResources().getDimension(R.dimen.dp_672);
        }
        this.mCombineView.initView(this, this.mAppMarket, this.mCombineViewWidth);
        this.mCombineView.setEnterAnimationComplete(true);
        BlurView blurView = (BlurView) findViewById(R.id.id_ConstraintLayout);
        this.mBlurView = blurView;
        blurView.setBlurViewRadius(getResources().getDimensionPixelOffset(R.dimen.dp_16));
    }

    public void updateForbidden(boolean isPGear) {
        this.mCombineView.updateForbidden(isPGear);
        this.mRecentAdapter.updateForbidden(isPGear);
        if (this.mSearchScroll.getVisibility() == 0) {
            this.mSearchResultAdapter.updateForbidden(isPGear);
        }
    }

    private void setBackgroundBitmap() {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.view.activity.MoreAppActivity.1
            @Override // java.lang.Runnable
            public void run() {
                MoreAppActivity.this.backgroundBitmap = BitmapUtils.getMoreAppBlurBitmap();
                if (MoreAppActivity.this.backgroundBitmap == null) {
                    MoreAppActivity moreAppActivity = MoreAppActivity.this;
                    moreAppActivity.backgroundBitmap = BitmapUtils.getScreenshot1(moreAppActivity, new Rect((int) MoreAppActivity.this.getResources().getDimension(R.dimen.dp_34), (int) MoreAppActivity.this.getResources().getDimension(R.dimen.dp_75), DensityUtil.getScreenWidth(MoreAppActivity.this) - ((int) MoreAppActivity.this.getResources().getDimension(R.dimen.dp_34)), DensityUtil.getScreenHeight(MoreAppActivity.this)), MoreAppActivity.this.mDialogWidth, MoreAppActivity.this.mDialogHeight);
                    BitmapUtils.setMoreAppBlurBitmap(MoreAppActivity.this.backgroundBitmap);
                }
                MoreAppActivity.this.runOnUiThread(new Runnable() { // from class: com.byd.card.view.activity.MoreAppActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MoreAppActivity.this.mBlurView.setBlurView(MoreAppActivity.this.backgroundBitmap);
                    }
                });
            }
        });
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        EditText editText = this.mSearchEdit;
        if (editText != null) {
            editText.removeTextChangedListener(this);
        }
        this.mLastSearchText = null;
        RecyclerView recyclerView = this.mSearchResultRV;
        if (recyclerView != null) {
            recyclerView.setVisibility(8);
        }
        SecondConfirmDialog secondConfirmDialog = this.mSecondConfirmDialog;
        if (secondConfirmDialog != null) {
            secondConfirmDialog.cancel();
        }
        CombineView combineView = this.mCombineView;
        if (combineView != null) {
            combineView.dismissAppInfoPopup();
            this.mCombineView.dismissFolderDialog();
            this.mCombineView.cancelUninstallDialog();
        }
        if (this.mOrientationOld != newConfig.orientation) {
            this.mOrientationOld = newConfig.orientation;
            setContentView(getLayoutId());
            updateUI(this.mOrientationOld);
            findView(this.mOrientationOld);
            setBackgroundBitmap();
            initData();
            initListener();
            startAD();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.view.activity.MoreAppActivity.2
            @Override // java.lang.Runnable
            public void run() {
                MoreAppActivity moreAppActivity = MoreAppActivity.this;
                moreAppActivity.backgroundBitmap = BitmapUtils.getScreenshot1(moreAppActivity, new Rect((int) MoreAppActivity.this.getResources().getDimension(R.dimen.dp_34), (int) MoreAppActivity.this.getResources().getDimension(R.dimen.dp_75), DensityUtil.getScreenWidth(MoreAppActivity.this) - ((int) MoreAppActivity.this.getResources().getDimension(R.dimen.dp_34)), DensityUtil.getScreenHeight(MoreAppActivity.this)), MoreAppActivity.this.mDialogWidth, MoreAppActivity.this.mDialogHeight);
                MoreAppActivity.this.runOnUiThread(new Runnable() { // from class: com.byd.card.view.activity.MoreAppActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MoreAppActivity.this.mBlurView.setBlurView(MoreAppActivity.this.backgroundBitmap);
                    }
                });
            }
        });
    }

    @Override // android.app.Activity
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        CombineView combineView = this.mCombineView;
        if (combineView != null) {
            combineView.setEnterAnimationComplete(true);
        }
    }

    private void initListener() {
        SearchHistoryTextAdapter searchHistoryTextAdapter = new SearchHistoryTextAdapter(this, new SearchHistoryTextAdapter.OnHistoryTextClickListener() { // from class: com.byd.card.view.activity.MoreAppActivity.3
            @Override // com.byd.card.adapter.SearchHistoryTextAdapter.OnHistoryTextClickListener
            public void onHistoryClick(String text) {
                MoreAppActivity.this.mSearchEdit.setText(text);
                MoreAppActivity.this.mSearchEdit.setSelection(text.length());
            }
        }, new SearchHistoryTextAdapter.OnHistoryTextDeleteListener() { // from class: com.byd.card.view.activity.MoreAppActivity.4
            @Override // com.byd.card.adapter.SearchHistoryTextAdapter.OnHistoryTextDeleteListener
            public void onHistoryDeleteSingle(SearchHistory searchHistory) {
                ((MoreAppPresenter) MoreAppActivity.this.mPresenter).deleteHistory(searchHistory);
            }

            @Override // com.byd.card.adapter.SearchHistoryTextAdapter.OnHistoryTextDeleteListener
            public void onHistoryDeleteAll() {
                ((MoreAppPresenter) MoreAppActivity.this.mPresenter).deleteAllHistory();
            }
        });
        this.mSearchHistoryAdapter = searchHistoryTextAdapter;
        searchHistoryTextAdapter.setOnHistoryTextLongClickListener(new SearchHistoryTextAdapter.OnHistoryTextLongClickListener() { // from class: com.byd.card.view.activity.MoreAppActivity.5
            @Override // com.byd.card.adapter.SearchHistoryTextAdapter.OnHistoryTextLongClickListener
            public void onHistoryLongClick(SearchHistory history) {
                MoreAppActivity.this.mDeleteAllHistory.setVisibility(8);
            }
        });
        AutoLineLayoutManager autoLineLayoutManager = new AutoLineLayoutManager();
        if (AppUtils.isRtl(this)) {
            this.mHistoryTxtRecycler.setLayoutDirection(1);
        } else {
            this.mHistoryTxtRecycler.setLayoutDirection(0);
        }
        this.mHistoryTxtRecycler.setLayoutManager(autoLineLayoutManager);
        this.mHistoryTxtRecycler.setAdapter(this.mSearchHistoryAdapter);
        restoreSearchEdit();
        this.mSearchEdit.addTextChangedListener(this);
        this.mSearchEdit.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.card.view.activity.MoreAppActivity.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    MoreAppActivity.this.mSearchEdit.playSoundEffect(0);
                }
                return false;
            }
        });
        this.mSearchEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.card.view.activity.MoreAppActivity.7
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean b) {
                MoreAppActivity.this.mSearchFocus = b;
                if (!b) {
                    MoreAppActivity.this.mSearchEdit.setHint(MoreAppActivity.this.getString(R.string.search));
                    MoreAppActivity.this.startAD();
                    return;
                }
                MoreAppActivity.this.mClearFocus = false;
                MoreAppActivity.this.mSearchEdit.setHint(MoreAppActivity.this.getString(R.string.search));
                MoreAppActivity.this.mBack.setVisibility(0);
                MoreAppActivity.this.mClose.setVisibility(8);
                MoreAppActivity.this.mRecentUse.setVisibility(8);
                MoreAppActivity.this.mRecentScroll.setVisibility(8);
                MoreAppActivity.this.mRecentRecycler.setVisibility(8);
                MoreAppActivity.this.mNoRecentUse.setVisibility(8);
                String strTrim = MoreAppActivity.this.mSearchEdit.getText().toString().trim();
                if (TextUtils.isEmpty(strTrim)) {
                    ((MoreAppPresenter) MoreAppActivity.this.mPresenter).searchHistory();
                } else {
                    ((MoreAppPresenter) MoreAppActivity.this.mPresenter).search(strTrim);
                }
                SoftKeyBoardListener.openKeyboard(view, MoreAppActivity.this);
                MoreAppActivity.this.cancelTimer();
            }
        });
        this.mSearchBg.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.view.activity.MoreAppActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MoreAppActivity.this.mSearchEdit.clearFocus();
                MoreAppActivity.this.mSearchEdit.requestFocus();
            }
        });
        this.mSearchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.card.view.activity.MoreAppActivity.9
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId != 6 && actionId != 1 && actionId != 5) {
                    return false;
                }
                String strTrim = MoreAppActivity.this.mSearchEdit.getText().toString().trim();
                ((MoreAppPresenter) MoreAppActivity.this.mPresenter).search(strTrim);
                if (!TextUtils.isEmpty(strTrim)) {
                    ((MoreAppPresenter) MoreAppActivity.this.mPresenter).insertHistory(strTrim);
                }
                MoreAppActivity.this.mSearchEdit.clearFocus();
                ((InputMethodManager) MoreAppActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(v.getWindowToken(), 2);
                return true;
            }
        });
        this.mRecentAdapter = new MoreRecentAppAdapter(this, new MoreRecentAppAdapter.OnRecentItemClickListener() { // from class: com.byd.card.view.activity.-$$Lambda$MoreAppActivity$2e7SL-TAvd4-UBCI7bRnrG2FDho
            @Override // com.byd.card.adapter.MoreRecentAppAdapter.OnRecentItemClickListener
            public final void onItemClick(View view, AppOrShortcutItem appOrShortcutItem) {
                this.f$0.lambda$initListener$0$MoreAppActivity(view, appOrShortcutItem);
            }
        });
        this.mClose.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.view.activity.-$$Lambda$MoreAppActivity$4597HRIPtFvHGGiBpsmr3PMwQ_I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initListener$1$MoreAppActivity(view);
            }
        });
        this.mBack.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.view.activity.-$$Lambda$MoreAppActivity$hbloI4lY-HISWWe9yC42OMtuNTY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initListener$2$MoreAppActivity(view);
            }
        });
        this.mClear.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.view.activity.-$$Lambda$MoreAppActivity$KIa4n4YIRZvH7NbI0pz2ZtQbazg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initListener$3$MoreAppActivity(view);
            }
        });
        this.mVoice.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.view.activity.MoreAppActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(Constants.PackageInfo.ACTION_START_VOICE_FROM_LAUNCHER);
                intent.setFlags(16777248);
                intent.setComponent(new ComponentName(Constants.VR_PACKAGE_NAME, Constants.VR_RECEIVER));
                MoreAppActivity.this.sendBroadcast(intent);
            }
        });
        this.mRecentRecycler.setAdapter(this.mRecentAdapter);
        this.mRecentRecycler.addItemDecoration(new RecentUseItemDecoration());
        this.mDeleteAllHistory.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.view.activity.MoreAppActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MoreAppActivity.this.mSecondConfirmDialog != null) {
                    MoreAppActivity.this.mSecondConfirmDialog = null;
                }
                int uiStyle = UiStyleConfig.getInstance(MoreAppActivity.this).getUiStyle();
                if (uiStyle != 1) {
                    if (uiStyle != 2) {
                        if (AppUtils.isRightRudder()) {
                            MoreAppActivity.this.mSecondConfirmDialog = new SecondConfirmDialog(MoreAppActivity.this, R.layout.dialog_second_confirm_right, null);
                        } else {
                            MoreAppActivity.this.mSecondConfirmDialog = new SecondConfirmDialog(MoreAppActivity.this, R.layout.dialog_second_confirm, null);
                        }
                    }
                } else if (AppUtils.isRightRudder()) {
                    MoreAppActivity.this.mSecondConfirmDialog = new SecondConfirmDialog(MoreAppActivity.this, R.layout.denza_dialog_second_confirm_right, null);
                } else {
                    MoreAppActivity.this.mSecondConfirmDialog = new SecondConfirmDialog(MoreAppActivity.this, R.layout.denza_dialog_second_confirm, null);
                }
                MoreAppActivity.this.mSecondConfirmDialog.setOnClickListener(new SecondConfirmDialog.OnClickListener() { // from class: com.byd.card.view.activity.MoreAppActivity.12.1
                    @Override // com.byd.card.view.dialog.SecondConfirmDialog.OnClickListener
                    public void onClick(View view) {
                        if (view.getId() == R.id.clear_history_confirm) {
                            ((MoreAppPresenter) MoreAppActivity.this.mPresenter).deleteAllHistory();
                        }
                        MoreAppActivity.this.mSecondConfirmDialog.cancel();
                    }
                });
                MoreAppActivity.this.mSecondConfirmDialog.show();
            }
        });
        this.mAppMarket.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.view.activity.MoreAppActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent launchIntentForPackage = MoreAppActivity.this.getPackageManager().getLaunchIntentForPackage("com.byd.appstore");
                if (launchIntentForPackage == null) {
                    Toast.makeText(MoreAppActivity.this, "未安装", 1).show();
                } else {
                    MoreAppActivity.this.startActivity(launchIntentForPackage);
                }
            }
        });
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(this, new SearchResultAdapter.OnSearchResultItemClickListener() { // from class: com.byd.card.view.activity.MoreAppActivity.14
            @Override // com.byd.card.adapter.SearchResultAdapter.OnSearchResultItemClickListener
            public void onSearchResultItemClick(AppOrShortcutItem item) {
                ((MoreAppPresenter) MoreAppActivity.this.mPresenter).insertHistory(item);
                SoftKeyBoardListener.closeKeyboard(MoreAppActivity.this.mSearchEdit, MoreAppActivity.this);
                MoreAppActivity.this.finish();
            }
        });
        this.mSearchResultAdapter = searchResultAdapter;
        this.mSearchResultRV.setAdapter(searchResultAdapter);
        this.mSearchResultRV.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.byd.card.view.activity.MoreAppActivity.15
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                SoftKeyBoardListener.closeKeyboard(MoreAppActivity.this.mSearchEdit, MoreAppActivity.this);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$MoreAppActivity(final View view, final AppOrShortcutItem appOrShortcutItem) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.view.activity.MoreAppActivity.10
            @Override // java.lang.Runnable
            public void run() {
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                Drawable itemDrawable = appOrShortcutItem.getItemDrawable();
                if (appOrShortcutItem instanceof AppInfo) {
                    CarPlayAndAutoManager carPlayAndAutoManager = CarPlayAndAutoManager.getInstance();
                    MoreAppActivity moreAppActivity = MoreAppActivity.this;
                    carPlayAndAutoManager.jumpApplication(moreAppActivity, (AppInfo) appOrShortcutItem, moreAppActivity, moreAppActivity.mRecentRecycler, view, ((BitmapDrawable) itemDrawable).getBitmap(), 60, 60, iArr[0] + 20, iArr[1] - 20, false);
                    if ("com.ts.carplay.app".equals(appOrShortcutItem.getItemIdentify()) || "com.ts.androidauto.app".equals(appOrShortcutItem.getItemIdentify())) {
                        MoreAppActivity.this.finish();
                        return;
                    }
                    return;
                }
                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_FLOAT_WINDOW));
                MoreAppActivity moreAppActivity2 = MoreAppActivity.this;
                AppUtils.openShortcut(moreAppActivity2, (ShortcutInfo) appOrShortcutItem, moreAppActivity2, moreAppActivity2.mRecentRecycler, view, ((BitmapDrawable) itemDrawable).getBitmap(), 60, 60, iArr[0] + 20, iArr[1] - 20, false);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$1$MoreAppActivity(View view) {
        finish();
    }

    public /* synthetic */ void lambda$initListener$2$MoreAppActivity(View view) {
        onBackPressed();
    }

    public /* synthetic */ void lambda$initListener$3$MoreAppActivity(View view) {
        this.mSearchEdit.setText("");
        this.mSearchScroll.setVisibility(8);
        this.mSearchResultRV.setVisibility(8);
        this.mClear.setVisibility(8);
        if (this.mSearchHistoryAdapter.getHistoryDate() != null && this.mSearchHistoryAdapter.getHistoryDate().size() > 0) {
            this.mDeleteAllHistory.setVisibility(0);
        }
        this.mHistoryTxtRecycler.setVisibility(0);
        this.mSearchHistoryTxt.setVisibility(0);
        ((MoreAppPresenter) this.mPresenter).searchHistory();
    }

    private void initData() {
        if (this.mSearchEdit.isFocused()) {
            return;
        }
        ((MoreAppPresenter) this.mPresenter).queryRecentList();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        if (TextUtils.isEmpty(charSequence)) {
            this.mClear.setVisibility(8);
            updateLandSearchLayout(false);
        } else {
            this.mClear.setVisibility(0);
            updateLandSearchLayout(true);
        }
        if (TextUtils.isEmpty(charSequence) && before == 0) {
            return;
        }
        String strTrim = charSequence.toString().trim();
        if (TextUtils.equals(this.mLastSearchText, strTrim)) {
            return;
        }
        this.mLastSearchText = strTrim;
        if (!TextUtils.isEmpty(strTrim)) {
            ((MoreAppPresenter) this.mPresenter).search(strTrim);
            return;
        }
        this.mNoSearchResult.setVisibility(8);
        this.mSearchResultRV.setVisibility(8);
        this.mSearchScroll.setVisibility(8);
        if (this.mSearchHistoryAdapter.getHistoryDate() != null && this.mSearchHistoryAdapter.getHistoryDate().size() > 0) {
            this.mDeleteAllHistory.setVisibility(0);
        }
        if (this.mSearchFocus) {
            ((MoreAppPresenter) this.mPresenter).searchHistory();
            this.mHistoryTxtRecycler.setVisibility(0);
            this.mSearchHistoryTxt.setVisibility(0);
            return;
        }
        this.mDeleteAllHistory.setVisibility(8);
    }

    @Override // com.byd.card.contract.MoreAppContract.View
    public void onSearchResult(final List<AppOrShortcutItem> list, final String inputText) {
        runOnUiThread(new Runnable() { // from class: com.byd.card.view.activity.MoreAppActivity.16
            @Override // java.lang.Runnable
            public void run() {
                List list2 = list;
                if (list2 == null || list2.isEmpty()) {
                    MoreAppActivity.this.mSearchScroll.setVisibility(8);
                    MoreAppActivity.this.mSearchResultRV.setVisibility(8);
                    MoreAppActivity.this.mNoSearchResult.setVisibility(0);
                } else {
                    MoreAppActivity.this.mNoSearchResult.setVisibility(8);
                    MoreAppActivity.this.mSearchResultAdapter.updateAdapter(list);
                    MoreAppActivity.this.setScrollHeight(list.size());
                    MoreAppActivity.this.mSearchScroll.setVisibility(0);
                    MoreAppActivity.this.mSearchResultRV.setVisibility(0);
                }
                MoreAppActivity.this.mDeleteAllHistory.setVisibility(8);
                MoreAppActivity.this.mNoHistory.setVisibility(8);
                MoreAppActivity.this.mHistoryTxtRecycler.setVisibility(8);
                MoreAppActivity.this.mSearchHistoryTxt.setVisibility(8);
                if (TextUtils.isEmpty(inputText)) {
                    MoreAppActivity.this.mNoSearchResult.setVisibility(8);
                    if (MoreAppActivity.this.mClearFocus) {
                        return;
                    }
                    ((MoreAppPresenter) MoreAppActivity.this.mPresenter).searchHistory();
                }
            }
        });
    }

    @Override // com.byd.card.contract.MoreAppContract.View
    public void onQueryRecentResult(final List<AppOrShortcutItem> list) {
        runOnUiThread(new Runnable() { // from class: com.byd.card.view.activity.-$$Lambda$MoreAppActivity$hjnTBmn2dNY2DHVCoPaze5R1iuQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onQueryRecentResult$4$MoreAppActivity(list);
            }
        });
    }

    public /* synthetic */ void lambda$onQueryRecentResult$4$MoreAppActivity(List list) {
        if (this.mSearchFocus) {
            return;
        }
        this.mRecentScroll.setVisibility(0);
        this.mRecentRecycler.setVisibility(0);
        if (list == null || list.size() == 0) {
            this.mNoRecentUse.setVisibility(0);
        } else {
            this.mNoRecentUse.setVisibility(8);
        }
        this.mRecentAdapter.updateAdapter(list);
    }

    @Override // com.byd.card.contract.MoreAppContract.View
    public void onSearchHistory(final List<SearchHistory> list) {
        runOnUiThread(new Runnable() { // from class: com.byd.card.view.activity.-$$Lambda$MoreAppActivity$KvtsemG78sD3yYI1x2IdCAs-rdM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onSearchHistory$5$MoreAppActivity(list);
            }
        });
    }

    public /* synthetic */ void lambda$onSearchHistory$5$MoreAppActivity(List list) {
        if (list != null && list.size() > 0) {
            this.mDeleteAllHistory.setVisibility(0);
            this.mHistoryTxtRecycler.setVisibility(0);
            this.mNoHistory.setVisibility(8);
        } else {
            this.mDeleteAllHistory.setVisibility(8);
            this.mNoHistory.setVisibility(0);
        }
        this.mSearchHistoryTxt.setVisibility(0);
        this.mSearchHistoryAdapter.updateAdapter(list);
    }

    @Override // com.byd.card.contract.MoreAppContract.View
    public void onDeleteHistory(final List<SearchHistory> list, SearchHistory delete) {
        runOnUiThread(new Runnable() { // from class: com.byd.card.view.activity.MoreAppActivity.17
            @Override // java.lang.Runnable
            public void run() {
                List list2 = list;
                if (list2 == null || list2.isEmpty()) {
                    MoreAppActivity.this.mDeleteAllHistory.setVisibility(8);
                    MoreAppActivity.this.mNoHistory.setVisibility(0);
                } else {
                    MoreAppActivity.this.mDeleteAllHistory.setVisibility(0);
                    MoreAppActivity.this.mNoHistory.setVisibility(8);
                }
                MoreAppActivity.this.mSearchHistoryAdapter.updateAdapter(list);
            }
        });
    }

    @Override // com.byd.card.contract.MoreAppContract.View
    public void onDeleteAllHistory() {
        runOnUiThread(new Runnable() { // from class: com.byd.card.view.activity.-$$Lambda$MoreAppActivity$6AG8SPjalMNEU2NWQpm6w74ussQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDeleteAllHistory$6$MoreAppActivity();
            }
        });
    }

    public /* synthetic */ void lambda$onDeleteAllHistory$6$MoreAppActivity() {
        this.mDeleteAllHistory.setVisibility(8);
        this.mNoHistory.setVisibility(0);
        this.mSearchHistoryAdapter.updateAdapter(new ArrayList());
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        startAD();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.i(TAG, "onMessageEvent event.tag = " + event.tag);
        String str = event.tag;
        str.hashCode();
        switch (str) {
            case "finish_app_center":
            case "home_key":
            case "click_ui_mode":
                finish();
                overridePendingTransition(0, 0);
                break;
            case "action_shortcuts_removed":
                ((MoreAppPresenter) this.mPresenter).queryRecentList();
                this.mCombineView.updateApp(AppInstallBroadcastReceiver.REMOVED_SHORTCUTS, null);
                break;
            case "auto_status":
            case "carplay_status":
                this.mCombineView.refreshAppListUI();
                this.mRecentAdapter.notifyDataSetChanged();
                break;
            case "action_package_removed":
                ((MoreAppPresenter) this.mPresenter).queryRecentList();
                this.mCombineView.updateApp(AppInstallBroadcastReceiver.REMOVED_APP, event.applicationInfo);
                break;
            case "action_package_added":
                this.mCombineView.updateApp(AppInstallBroadcastReceiver.ADD_APP, null);
                break;
        }
    }

    private void updateUI(int orientation) {
        Window window = getWindow();
        window.setGravity(17);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.clearFlags(2);
        window.addFlags(AvmManagerImpl.AVM_CALIBRATION_CODE);
        window.addFlags(134217728);
        if (orientation == 2) {
            this.mDialogWidth = (int) getResources().getDimension(R.dimen.dp_1232);
            this.mDialogHeight = (int) getResources().getDimension(R.dimen.dp_570);
            this.mSearchScrollMaxHeight = (int) getResources().getDimension(R.dimen.dp_420);
        } else if (orientation == 1) {
            this.mDialogWidth = (int) getResources().getDimension(R.dimen.dp_672);
            this.mDialogHeight = (int) getResources().getDimension(R.dimen.dp_1128);
            this.mSearchScrollMaxHeight = (int) getResources().getDimension(R.dimen.dp_420);
        }
        window.setAttributes(attributes);
    }

    @Override // com.byd.card.base.BaseMvpActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = this.cancelEdit;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        BroadcastReceiver broadcastReceiver = this.mDialogAppDetail;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        BitmapUtils.setMoreAppBlurBitmap(null);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        CombineView combineView = this.mCombineView;
        if (combineView != null) {
            combineView.stop();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        CombineView combineView = this.mCombineView;
        if (combineView != null && combineView.isOpenFolder()) {
            this.mCombineView.closeFolder();
            return;
        }
        if (this.mSearchFocus) {
            SoftKeyBoardListener.closeKeyboard(this.mSearchEdit, this);
        }
        if (this.mBack.getVisibility() == 0) {
            this.mClose.setVisibility(0);
            this.mBack.setVisibility(8);
            this.mSearchHistoryTxt.setVisibility(8);
            this.mHistoryTxtRecycler.setVisibility(8);
            this.mNoHistory.setVisibility(8);
            this.mDeleteAllHistory.setVisibility(8);
            this.mSearchEdit.clearFocus();
            this.mClearFocus = true;
            this.mSearchEdit.setText("");
            this.mRecentUse.setVisibility(0);
            this.mSearchResultRV.setVisibility(8);
            this.mSearchScroll.setVisibility(8);
            this.mNoSearchResult.setVisibility(8);
            ((MoreAppPresenter) this.mPresenter).queryRecentList();
            return;
        }
        super.onBackPressed();
        this.mCombineView.backPressed();
    }

    private void restoreSearchEdit() {
        EditText editText = this.mSearchEdit;
        if (editText != null) {
            editText.clearFocus();
            this.mClearFocus = true;
            this.mSearchEdit.setText("");
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            CountDownTimer countDownTimer = this.countDownTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            CountDownTimer countDownTimer2 = this.cancelEdit;
            if (countDownTimer2 != null) {
                countDownTimer2.cancel();
            }
        } else if (action == 1 || action == 3) {
            if (isShouldHideKeyboard(ev) && !this.mSearchFocus) {
                startAD();
            }
            startCombine();
            if (!SoftKeyBoardListener.isKeyBoardShow(this) && isTouchHistoryItem(ev)) {
                this.mSearchHistoryAdapter.clearDelete();
                List<SearchHistory> list = this.mSearchHistoryAdapter.getList();
                if (list != null && list.size() > 0 && this.mHistoryTxtRecycler.getVisibility() == 0) {
                    this.mDeleteAllHistory.setVisibility(0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(MotionEvent event) {
        int[] iArr = {0, 0};
        this.mSearchBg.getLocationInWindow(iArr);
        int dimensionPixelSize = iArr[0] - getResources().getDimensionPixelSize(R.dimen.dp_24);
        int dimensionPixelSize2 = iArr[1] - getResources().getDimensionPixelSize(R.dimen.dp_75);
        return event.getX() <= ((float) dimensionPixelSize) || event.getX() >= ((float) (this.mSearchBg.getWidth() + dimensionPixelSize)) || event.getY() <= ((float) dimensionPixelSize2) || event.getY() >= ((float) (this.mSearchBg.getHeight() + dimensionPixelSize2));
    }

    private boolean isTouchHistoryItem(MotionEvent event) {
        int[] iArr = {0, 0};
        this.mHistoryTxtRecycler.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return event.getX() <= ((float) i) || event.getX() >= ((float) (this.mHistoryTxtRecycler.getWidth() + i)) || event.getY() <= ((float) i2) || event.getY() >= ((float) (this.mHistoryTxtRecycler.getHeight() + i2));
    }

    public void cancelTimer() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        CombineView combineView = this.mCombineView;
        if (combineView != null) {
            combineView.dismissAppInfoPopup();
        }
    }

    public void startAD() {
        if (this.countDownTimer == null) {
            this.countDownTimer = new CountDownTimer(this.advertisingTime, 1000L) { // from class: com.byd.card.view.activity.MoreAppActivity.18
                @Override // android.os.CountDownTimer
                public void onTick(long millisUntilFinished) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    if (MoreAppActivity.this.mCombineView != null) {
                        MoreAppActivity.this.mCombineView.dismissAppInfoPopup();
                        MoreAppActivity.this.mCombineView.dismissFolderDialog();
                        MoreAppActivity.this.mCombineView.cancelUninstallDialog();
                    }
                    if (MoreAppActivity.this.mSecondConfirmDialog != null) {
                        MoreAppActivity.this.mSecondConfirmDialog.cancel();
                    }
                    MoreAppActivity.this.finish();
                }
            };
        }
        this.countDownTimer.start();
    }

    public void startCombine() {
        if (this.cancelEdit == null) {
            this.cancelEdit = new CountDownTimer(this.cancelTime, 1000L) { // from class: com.byd.card.view.activity.MoreAppActivity.19
                @Override // android.os.CountDownTimer
                public void onTick(long millisUntilFinished) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    if (MoreAppActivity.this.mCombineView != null) {
                        MoreAppActivity.this.mCombineView.cancelJiggle();
                    }
                }
            };
        }
        this.cancelEdit.start();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 1 && getCurrentFocus() != null) {
            this.mSearchEdit.clearFocus();
            SoftKeyBoardListener.closeKeyboard(this.mSearchEdit, this);
        }
        return super.onTouchEvent(event);
    }

    public void closeSoftKeyBoard() {
        SoftKeyBoardListener.closeKeyboard(this.mSearchEdit, this);
    }

    private void registerDialogAppDetail() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_DIALOG_SHOW);
        intentFilter.addAction(ACTION_DIALOG_DISMISS);
        registerReceiver(this.mDialogAppDetail, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScrollHeight(int count) {
        int dimension = (int) (getResources().getDimension(R.dimen.dp_72) * count);
        ViewGroup.LayoutParams layoutParams = this.mSearchScroll.getLayoutParams();
        int i = this.mSearchScrollMaxHeight;
        if (dimension > i) {
            layoutParams.height = i;
            this.mSearchScroll.setVerticalScrollBarEnabled(true);
            this.mSearchResultRV.setVerticalScrollBarEnabled(true);
        } else {
            layoutParams.height = dimension;
            this.mSearchScroll.setVerticalScrollBarEnabled(false);
            this.mSearchResultRV.setVerticalScrollBarEnabled(false);
        }
        this.mSearchScroll.setLayoutParams(layoutParams);
    }

    private void updateLandSearchLayout(boolean showDelete) {
        EditText editText;
        if (!AppUtils.isLandSpace(this) || (editText = this.mSearchEdit) == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = editText.getLayoutParams();
        if (showDelete) {
            layoutParams.width = getResources().getDimensionPixelOffset(R.dimen.dp_148);
        } else {
            layoutParams.width = -2;
        }
        this.mSearchEdit.setLayoutParams(layoutParams);
    }
}
