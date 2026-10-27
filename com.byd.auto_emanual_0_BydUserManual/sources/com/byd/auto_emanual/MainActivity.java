package com.byd.auto_emanual;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_emanual.fragment.BaseFragment;
import com.byd.auto_emanual.fragment.ClassifyData;
import com.byd.auto_emanual.fragment.ManualFragment;
import com.byd.auto_emanual.fragment.NormalDataAdapter;
import com.byd.auto_emanual.fragment.RecommendFragment;
import com.byd.auto_emanual.fragment.SearchFragment;
import com.byd.auto_emanual.fragment.SearchHistoryAdapter;
import com.byd.auto_emanual.fragment.SearchInputListener;
import com.byd.auto_emanual.fragment.SearchResultAdapter;
import com.byd.auto_emanual.fragment.SettingsFragment;
import com.byd.auto_emanual.fragment.StartFragment;
import com.byd.auto_emanual.util.AutoInfoUtil;
import com.byd.auto_emanual.util.DeviceUtil;
import com.byd.auto_emanual.util.DialogUtil;
import com.byd.auto_emanual.util.ListUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.MobileDataUtil;
import com.byd.auto_emanual.util.ResourceUtil;
import com.byd.auto_emanual.util.SearchHistoryUtil;
import com.byd.auto_emanual.util.SearchUtil;
import com.byd.auto_emanual.util.SharedPrefUtil;
import com.byd.auto_emanual.util.UIStyleUtil;
import com.byd.auto_emanual.util.Utils;
import com.byd.auto_emanual.util.ViewUtil;
import com.google.android.flexbox.FlexboxLayoutManager;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, SearchInputListener, IStopOrGo, IRefresh, SearchHistoryAdapter.Callback, MobileDataUtil.MobileDataUsageListener {
    public static final String BYD_AUTO_BODYWORK_COMMON = "android.permission.BYDAUTO_BODYWORK_COMMON";
    public static final String BYD_AUTO_GEARBOX_COMMON = "android.permission.BYDAUTO_GEARBOX_COMMON";
    public static final String BYD_AUTO_SETTING_COMMON = "android.permission.BYDAUTO_SETTING_COMMON";
    private static final String BYD_AUTO_TEST_COMMON = "android.permission.BYDAUTO_TEST_COMMON";
    public static final String HAS_PERMISSION = "hsa_permission";
    public static final String HAS_PERMISSION_VALUE = "1";
    public static final int MSG_MOBILE_DATA_SHOW_PROMPT = 1;
    public static final int MSG_SHOW_STOP_GO = 2;
    private static final String TAG = "MainActivity";
    private static final int TIME_DELAY_TO_SHOW = 1000;
    private RelativeLayout bar;
    private boolean checkPermissions;
    private ConnectivityManager connectivityManager;
    private AlertDialog disclaimerDialog;
    private FrameLayout frameLayout;
    private GearboxListener gearboxListener;
    private Handler handler;
    private ImageView inputDelete;
    private boolean isForeground;
    private Locale locale;
    private RelativeLayout mainContainer;
    private AlertDialog mobileDataPromptDialog;
    private RelativeLayout noResult;
    private ImageView noResultIcon;
    private TextView noResultTextView;
    private int orientation;
    private RecyclerView result;
    private SearchResultAdapter resultAdapter;
    private ImageView resultBack;
    private ImageView resultDelete;
    private RelativeLayout resultHistory;
    private TextView resultHistoryTextView;
    private EditText resultInput;
    private AlertDialog resultRemovingWaringDialog;
    private SearchHistoryAdapter resultSearchHistoryAdapter;
    private ImageView searchIcon;
    private RelativeLayout searchInputLayout;
    private RelativeLayout searchResultContainer;
    private AlertDialog stopOrGoDialog;
    private TabAdapter tabAdapter;
    private RelativeLayout tabContainer;
    private LinearLayoutManager tabManager;
    private RecyclerView tabView;
    private String urlToManual;
    private final int BYD_PERMISSION_REQUEST_CODE = 1;
    private ArrayList<String> permissionList = new ArrayList<>();

    private void updateResultByConfig() {
    }

    @Override // com.byd.auto_emanual.IStopOrGo
    public Activity getSelf() {
        return this;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(UIStyleUtil.getTheme());
        this.handler = new MainHandler(getMainLooper());
        this.orientation = getResources().getConfiguration().orientation;
        MLog.v(TAG, "onCreate " + UIStyleUtil.getStyle());
        MLog.i("Version", Utils.getVersionName(getApplicationContext()));
        removeOldFragments(savedInstanceState);
        this.checkPermissions = checkPermissions();
        ActivityManager.getInstance().addActivity(this);
        if (this.checkPermissions && "1".equals(SharedPrefUtil.load(getApplicationContext(), HAS_PERMISSION))) {
            init();
            if (DialogUtil.isShowDisclaimer(getApplicationContext())) {
                showDisclaimerLater();
            }
        }
        this.locale = getResources().getConfiguration().getLocales().get(0);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        boolean zCheckPermissions = checkPermissions();
        this.checkPermissions = zCheckPermissions;
        if (!zCheckPermissions || "1".equals(SharedPrefUtil.load(getApplicationContext(), HAS_PERMISSION))) {
            return;
        }
        init();
        if (DialogUtil.isShowDisclaimer(getApplicationContext())) {
            showDisclaimerLater();
        }
        SharedPrefUtil.save(getApplicationContext(), HAS_PERMISSION, "1");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MLog.d(TAG, "onNewIntent " + intent.toString());
    }

    private void enterManualImpl() {
        if (TextUtils.isEmpty(this.urlToManual)) {
            return;
        }
        doResultBack();
        int i = Utils.notShowVideo(MApp.context) ? 0 : 2;
        final BaseFragment fragment = FragmentHolder.getFragment(i);
        int i2 = 0;
        for (int i3 = 0; i3 <= 5; i3++) {
            goToManual(i, i2, fragment);
            i2 += 100;
        }
        this.tabContainer.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$6xqxmuGdkWy-oxz5YlTLCl8UEOU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$enterManualImpl$0$MainActivity(fragment);
            }
        }, fragment.isAdded() ? 600 : 1200);
    }

    public /* synthetic */ void lambda$enterManualImpl$0$MainActivity(Fragment fragment) {
        if (fragment == null || !(fragment instanceof ManualFragment) || TextUtils.isEmpty(this.urlToManual)) {
            return;
        }
        ((ManualFragment) fragment).loadUrlFromSearch(this.urlToManual);
        this.urlToManual = null;
    }

    private void goToManual(final int indexOfManual, final int delayTime, final Fragment fragment) {
        LinearLayoutManager linearLayoutManager = this.tabManager;
        if (linearLayoutManager == null || linearLayoutManager.getChildCount() <= 0) {
            return;
        }
        this.tabContainer.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$4ksOa2TbSu-5BkKbCzxGUsLWbkY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$goToManual$1$MainActivity(fragment, delayTime, indexOfManual);
            }
        }, delayTime);
    }

    public /* synthetic */ void lambda$goToManual$1$MainActivity(Fragment fragment, int i, int i2) {
        if (fragment != null && fragment.isVisible()) {
            MLog.d(TAG, "onNewIntent fragment VISIBLE " + i);
        } else {
            ((RelativeLayout) this.tabManager.getChildAt(i2)).getChildAt(0).performClick();
        }
    }

    private void goToMore() {
        int i = Utils.notShowVideo(MApp.context) ? 2 : 4;
        BaseFragment fragment = FragmentHolder.getFragment(i);
        int i2 = 0;
        for (int i3 = 0; i3 <= 5; i3++) {
            goToMore(i, i2, fragment);
            i2 += 100;
        }
    }

    private void goToMore(final int indexOfMore, final int delayTime, final Fragment fragment) {
        LinearLayoutManager linearLayoutManager = this.tabManager;
        if (linearLayoutManager == null || linearLayoutManager.getChildCount() <= 0) {
            return;
        }
        this.tabContainer.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$_pdM8uwujIBdJL77upbXoXrAn9c
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$goToMore$2$MainActivity(fragment, delayTime, indexOfMore);
            }
        }, delayTime);
    }

    public /* synthetic */ void lambda$goToMore$2$MainActivity(Fragment fragment, int i, int i2) {
        if (fragment != null && fragment.isVisible()) {
            MLog.d(TAG, "goToMore fragment VISIBLE " + i);
        } else {
            ((RelativeLayout) this.tabManager.getChildAt(i2)).getChildAt(0).performClick();
        }
    }

    private void init() {
        MobileDataUtil.calcMobileData(this);
        ExecutorManager.clearRecords();
        GearboxListener.getInstance().register(this);
        registerNetworkCallback();
        setContentView(R.layout.activity_main);
        initView();
        TabAdapter.DEFAULT_SHOW_FRAGMENT_POSITION = TabAdapter.getOldPosition();
        AutoInfoUtil.getAutoPowerType(this);
    }

    private void registerNetworkCallback() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
            this.connectivityManager = connectivityManager;
            connectivityManager.registerDefaultNetworkCallback(NetStatusCallback.getInstance());
        } catch (Exception e) {
            MLog.e(TAG, "registerNetworkCallback error", e);
        }
    }

    private void unRegisterNetworkCallback() {
        ConnectivityManager connectivityManager = this.connectivityManager;
        if (connectivityManager != null) {
            connectivityManager.unregisterNetworkCallback(NetStatusCallback.getInstance());
        }
    }

    private void removeOldFragments(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        removeFragment(supportFragmentManager, fragmentTransactionBeginTransaction, RecommendFragment.class.getSimpleName());
        removeFragment(supportFragmentManager, fragmentTransactionBeginTransaction, StartFragment.class.getSimpleName());
        removeFragment(supportFragmentManager, fragmentTransactionBeginTransaction, ManualFragment.class.getSimpleName());
        removeFragment(supportFragmentManager, fragmentTransactionBeginTransaction, SearchFragment.class.getSimpleName());
        removeFragment(supportFragmentManager, fragmentTransactionBeginTransaction, SettingsFragment.class.getSimpleName());
        fragmentTransactionBeginTransaction.commit();
    }

    private void removeFragment(FragmentManager manager, FragmentTransaction transaction, String fragmentTag) {
        Fragment fragmentFindFragmentByTag = manager.findFragmentByTag(fragmentTag);
        MLog.d(TAG, "fragment = " + fragmentFindFragmentByTag);
        if (fragmentFindFragmentByTag != null) {
            transaction.remove(fragmentFindFragmentByTag);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.isForeground = true;
        GearboxListener.getInstance().setActivity(this);
        showStopOrGo();
        updateByConfig();
        String strLoad = SharedPrefUtil.load(this, Parameters.CONTENT_ENTER_MANUAL_URL);
        if (TextUtils.isEmpty(strLoad)) {
            return;
        }
        SharedPrefUtil.remove(this, Parameters.CONTENT_ENTER_MANUAL_URL);
        this.urlToManual = strLoad;
        enterManualImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showStopOrGo() {
        RelativeLayout relativeLayout = this.mainContainer;
        if (relativeLayout == null || this.stopOrGoDialog != null) {
            return;
        }
        relativeLayout.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$Wb4U5fcmuwLfjZyDfBwjMNT4kb4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$showStopOrGo$3$MainActivity();
            }
        }, 1000L);
    }

    public /* synthetic */ void lambda$showStopOrGo$3$MainActivity() {
        this.stopOrGoDialog = DialogUtil.showStopOrGo(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        RelativeLayout relativeLayout = this.searchResultContainer;
        if (relativeLayout != null && relativeLayout.getVisibility() == 0) {
            hideInputMethod();
        }
        super.onPause();
        this.isForeground = false;
    }

    private void initView() {
        initMainContainer();
        initFragmentsContainer();
        initFragments();
        initTabs();
        if (this.searchResultContainer == null) {
            initResultView();
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        MLog.v(TAG, "screenWidth = " + displayMetrics.widthPixels + ", screenHeight = " + displayMetrics.heightPixels);
    }

    private void initMainContainer() {
        this.mainContainer = (RelativeLayout) findViewById(R.id.main_container);
    }

    private void initFragmentsContainer() {
        this.frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
    }

    private void initResultView() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.search_result_container);
        this.searchResultContainer = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$mF3PbF38apdNCagfT1X8RYb4pX4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initResultView$4$MainActivity(view);
            }
        });
        initSearchBar();
        initResultBack();
        initResultInput();
        initResultDelete();
        initNoResult();
        initResult();
        initResultHistory();
        updateTextByConfig();
        updateColorByConfig();
    }

    public /* synthetic */ void lambda$initResultView$4$MainActivity(View view) {
        hideInputMethod();
    }

    private void updateColorByConfig() {
        RelativeLayout relativeLayout = this.searchResultContainer;
        if (relativeLayout == null) {
            return;
        }
        ResourceUtil.setBackground(relativeLayout, R.attr.background_horizontal);
        updateResultInputBackground(false);
        ImageView imageView = this.searchIcon;
        imageView.setImageDrawable(ResourceUtil.getDrawable(imageView.getContext(), R.attr.icon_search));
        ImageView imageView2 = this.inputDelete;
        imageView2.setImageDrawable(ResourceUtil.getDrawable(imageView2.getContext(), R.attr.icon_search_input_text_delete));
        ResourceUtil.setBackground(this.resultBack, R.attr.icon_search_result_back);
        ResourceUtil.setTextColor(this.resultInput, R.attr.search_input_text_color);
        ResourceUtil.setHintTextColor(this.resultInput, R.attr.search_input_hint_text_color);
        ResourceUtil.setBackground(this.noResultIcon, R.attr.icon_search_result_no);
        ResourceUtil.setTextColor(this.noResultTextView, R.attr.search_result_no_text_color);
        ResourceUtil.setTextColor(this.resultHistoryTextView, R.attr.search_voice_text_color);
        ResourceUtil.setBackground(this.resultDelete, R.attr.icon_search_history_delete);
        this.resultSearchHistoryAdapter.notifyDataSetChanged();
        AlertDialog alertDialog = this.disclaimerDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            DialogUtil.updateDialogColorByConfig(this.disclaimerDialog);
        }
        AlertDialog alertDialog2 = this.stopOrGoDialog;
        if (alertDialog2 != null && alertDialog2.isShowing()) {
            DialogUtil.updateDialogColorByConfig(this.stopOrGoDialog);
        }
        AlertDialog alertDialog3 = this.mobileDataPromptDialog;
        if (alertDialog3 != null && alertDialog3.isShowing()) {
            DialogUtil.updateDialogColorByConfig(this.mobileDataPromptDialog);
        }
        AlertDialog alertDialog4 = this.resultRemovingWaringDialog;
        if (alertDialog4 != null && alertDialog4.isShowing()) {
            DialogUtil.updateDialogColorByConfig(this.resultRemovingWaringDialog);
        }
        this.resultAdapter.notifyDataSetChanged();
    }

    private void updateTextByConfig() {
        this.resultInput.setHint(R.string.search_hint_text);
        this.noResultTextView.setText(R.string.search_result_no_text);
        this.resultHistoryTextView.setText(R.string.search_history_text);
        AlertDialog alertDialog = this.disclaimerDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            DialogUtil.updateDialogTextByConfig(this.disclaimerDialog, R.string.dialog_disclaimer_title, R.string.dialog_disclaimer_detail, R.string.dialog_disclaimer_radio, R.string.dialog_button_confirm, R.string.dialog_button_cancel);
        }
        AlertDialog alertDialog2 = this.stopOrGoDialog;
        if (alertDialog2 != null && alertDialog2.isShowing()) {
            DialogUtil.updateDialogTextByConfig(this.stopOrGoDialog, -1, R.string.stop_or_go_detail, -1, -1, -1);
        }
        AlertDialog alertDialog3 = this.mobileDataPromptDialog;
        if (alertDialog3 != null && alertDialog3.isShowing()) {
            DialogUtil.updateDialogTextByConfig(this.mobileDataPromptDialog, -1, R.string.mobile_data_prompt_detail, -1, -1, R.string.mobile_data_prompt_cancel);
        }
        AlertDialog alertDialog4 = this.resultRemovingWaringDialog;
        if (alertDialog4 == null || !alertDialog4.isShowing()) {
            return;
        }
        DialogUtil.updateDialogTextByConfig(this.resultRemovingWaringDialog, R.string.dialog_history_removing_title, R.string.dialog_history_removing_message, -1, R.string.dialog_button_confirm, R.string.dialog_button_cancel);
    }

    private void updateResultInputBackground(boolean isSelected) {
        if (!Utils.is8155F()) {
            if (isSelected) {
                this.searchInputLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.search_input_background_selected));
                return;
            } else {
                this.searchInputLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.search_input_background));
                return;
            }
        }
        if (UIStyleUtil.isDenza()) {
            if (isSelected) {
                this.searchInputLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.search_result_input_background_selected_denza));
                return;
            } else {
                this.searchInputLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.search_result_input_background_denza));
                return;
            }
        }
        if (UIStyleUtil.isDynasty()) {
            this.searchInputLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.search_result_input_background_dynasty));
        } else {
            this.searchInputLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.search_result_input_background_dynasty));
        }
    }

    private void initSearchBar() {
        this.bar = (RelativeLayout) findViewById(R.id.bar);
    }

    private void initResultHistory() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.result_history);
        this.resultHistory = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$pEifOrTqcHp-tWLD_CTeOsn8myI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initResultHistory$5$MainActivity(view);
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.result_history_recycler);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getApplicationContext());
        flexboxLayoutManager.setFlexWrap(1);
        flexboxLayoutManager.setFlexDirection(0);
        flexboxLayoutManager.setAlignItems(4);
        flexboxLayoutManager.setJustifyContent(0);
        recyclerView.setLayoutManager(flexboxLayoutManager);
        SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter(this);
        this.resultSearchHistoryAdapter = searchHistoryAdapter;
        recyclerView.setAdapter(searchHistoryAdapter);
        this.resultDelete = (ImageView) findViewById(R.id.result_history_delete);
        this.resultHistoryTextView = (TextView) findViewById(R.id.result_history_text);
        this.resultDelete.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$e0cRJvlg5cNmTWp4MHGjdZsGUzQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initResultHistory$6$MainActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initResultHistory$5$MainActivity(View view) {
        hideInputMethod();
    }

    public /* synthetic */ void lambda$initResultHistory$6$MainActivity(View view) {
        this.resultRemovingWaringDialog = DialogUtil.showHistoryRemovingWaring(this, this.resultSearchHistoryAdapter);
    }

    private void initResult() {
        this.result = (RecyclerView) findViewById(R.id.result);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getApplicationContext());
        flexboxLayoutManager.setFlexWrap(1);
        flexboxLayoutManager.setFlexDirection(0);
        flexboxLayoutManager.setAlignItems(4);
        flexboxLayoutManager.setJustifyContent(0);
        this.result.setLayoutManager(flexboxLayoutManager);
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter();
        this.resultAdapter = searchResultAdapter;
        this.result.setAdapter(searchResultAdapter);
        this.result.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$_ab8mHshry0D8Zr2ZeFbu3y7nok
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$initResult$7$MainActivity(view, motionEvent);
            }
        });
    }

    public /* synthetic */ boolean lambda$initResult$7$MainActivity(View view, MotionEvent motionEvent) {
        hideInputMethod();
        return false;
    }

    private void initNoResult() {
        this.noResult = (RelativeLayout) findViewById(R.id.no_result);
        this.noResultIcon = (ImageView) findViewById(R.id.icon);
        this.noResultTextView = (TextView) findViewById(R.id.no_result_text);
    }

    private void initResultDelete() {
        ImageView imageView = (ImageView) findViewById(R.id.input_text_delete);
        this.inputDelete = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$3GK2yU9FPHn59BCc9L63JymRJOU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initResultDelete$8$MainActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initResultDelete$8$MainActivity(View view) {
        this.resultInput.setText("");
    }

    private void initResultInput() {
        this.searchInputLayout = (RelativeLayout) findViewById(R.id.result_input);
        this.searchIcon = (ImageView) findViewById(R.id.search_icon);
        EditText editText = (EditText) findViewById(R.id.input_edit_text);
        this.resultInput = editText;
        editText.addTextChangedListener(new TextWatcher() { // from class: com.byd.auto_emanual.MainActivity.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.doTextChange(s);
            }
        });
        this.resultInput.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$_OKkX87Ra0c0hsUdLZciP5IJcTs
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                this.f$0.lambda$initResultInput$9$MainActivity(view, z);
            }
        });
    }

    public /* synthetic */ void lambda$initResultInput$9$MainActivity(View view, boolean z) {
        if (!z) {
            hideInputMethod();
            updateResultInputBackground(false);
        } else {
            showInputMethod();
            updateResultInputBackground(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doTextChange(CharSequence s) {
        MLog.d(TAG, "doTextChange s = " + ((Object) s));
        if (TextUtils.isEmpty(s)) {
            this.resultSearchHistoryAdapter.setData(SearchHistoryUtil.getHistory());
        } else {
            this.resultHistory.setVisibility(8);
        }
        String strFilterKeyword = Utils.filterKeyword(s);
        ClassifyData classifyDataSearchByKey = SearchUtil.searchByKey(getApplicationContext(), strFilterKeyword);
        if (strFilterKeyword.length() == 0) {
            this.inputDelete.setVisibility(8);
            this.noResult.setVisibility(8);
            this.result.setVisibility(8);
            return;
        }
        this.inputDelete.setVisibility(0);
        if (classifyDataSearchByKey == null || ListUtil.isNullOrEmpty(classifyDataSearchByKey.getContentItems())) {
            this.noResult.setVisibility(0);
            this.result.setVisibility(8);
        } else {
            this.noResult.setVisibility(8);
            this.result.setVisibility(0);
            this.resultAdapter.setData(classifyDataSearchByKey, strFilterKeyword);
        }
    }

    private void initResultBack() {
        ImageView imageView = (ImageView) findViewById(R.id.result_back);
        this.resultBack = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$xynfIj0pezAmXmhL2cEn6ckzmtk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initResultBack$10$MainActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initResultBack$10$MainActivity(View view) {
        MLog.d(TAG, "setOnClickListener");
        doResultBack();
    }

    private void doResultBack() {
        this.mainContainer.setVisibility(0);
        hideInputMethod();
        this.searchResultContainer.setVisibility(8);
        this.resultInput.setText("");
        SearchFragment searchFragment = (SearchFragment) FragmentHolder.getFragment(SearchFragment.class);
        if (searchFragment == null || SearchHistoryUtil.getHistory().size() != 0) {
            return;
        }
        searchFragment.updateHistory();
    }

    public void hideInputMethod() {
        this.resultInput.clearFocus();
        try {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.resultInput.getWindowToken(), 0);
        } catch (Exception e) {
            MLog.e(TAG, "hideInputMethod error", e);
        }
    }

    private void showInputMethod() {
        this.resultInput.requestFocus();
        try {
            ((InputMethodManager) getSystemService("input_method")).toggleSoftInput(2, 2);
        } catch (Exception e) {
            MLog.e(TAG, "showInputMethod error", e);
        }
    }

    private void initTabs() {
        this.tabContainer = (RelativeLayout) findViewById(R.id.tab_container);
        this.tabView = (RecyclerView) findViewById(R.id.tab_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MApp.context);
        this.tabManager = linearLayoutManager;
        this.tabView.setLayoutManager(linearLayoutManager);
        FragmentHolder.addFragment(this);
    }

    private void initFragments() {
        FragmentHolder.createAll();
        for (BaseFragment baseFragment : FragmentHolder.getAllFragments()) {
            if (baseFragment instanceof SearchFragment) {
                ((SearchFragment) baseFragment).setSearchInputClickListener(this);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        MLog.v(TAG, "onClick");
    }

    public boolean checkPermissions() {
        int iCheckSelfPermission;
        int iCheckSelfPermission2;
        int iCheckSelfPermission3;
        try {
            int iCheckSelfPermission4 = checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            if (Config.CAN_GET_AUTO_INFO) {
                iCheckSelfPermission = checkSelfPermission("android.permission.BYDAUTO_GEARBOX_COMMON");
                iCheckSelfPermission2 = checkSelfPermission("android.permission.BYDAUTO_BODYWORK_COMMON");
                iCheckSelfPermission3 = checkSelfPermission("android.permission.BYDAUTO_SETTING_COMMON");
            } else {
                iCheckSelfPermission = 0;
                iCheckSelfPermission2 = 0;
                iCheckSelfPermission3 = 0;
            }
            this.permissionList.clear();
            if (iCheckSelfPermission != 0) {
                this.permissionList.add("android.permission.BYDAUTO_GEARBOX_COMMON");
            }
            if (iCheckSelfPermission2 != 0) {
                this.permissionList.add("android.permission.BYDAUTO_BODYWORK_COMMON");
            }
            if (iCheckSelfPermission4 != 0) {
                this.permissionList.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }
            if (ListUtil.isNullOrEmpty(this.permissionList)) {
                return true;
            }
            if (iCheckSelfPermission == 0 && iCheckSelfPermission2 == 0 && iCheckSelfPermission3 == 0 && iCheckSelfPermission4 == 0) {
                return true;
            }
            getWindow().getDecorView().postDelayed(new Runnable() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$scOsZz0abxVRV-KUkG3e47FQNDw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$checkPermissions$11$MainActivity();
                }
            }, 1000L);
        } catch (Exception e) {
            MLog.e(TAG, "checkPermissions error", e);
            finish();
        }
        return false;
    }

    public /* synthetic */ void lambda$checkPermissions$11$MainActivity() {
        if (ListUtil.isNullOrEmpty(this.permissionList)) {
            return;
        }
        ArrayList<String> arrayList = this.permissionList;
        requestPermissions((String[]) arrayList.toArray(new String[arrayList.size()]), 1);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean z;
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MLog.d(TAG, "onRequestPermissionsResult " + grantResults.length + ", " + requestCode);
        if (grantResults.length > 0 && requestCode == 1) {
            boolean z2 = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != 0) {
                    z2 = false;
                }
                MLog.d(TAG, "onRequestPermissionsResult i=" + i + ", " + grantResults[i] + ", " + permissions[i]);
            }
            if (Utils.is8155F()) {
                z = z2 && checkPermissions();
            } else {
                z = z2;
            }
            if (!z) {
                if (z2) {
                    return;
                }
                MLog.i(TAG, "permission not granted.");
                finish();
                overridePendingTransition(0, 0);
                return;
            }
            MLog.i(TAG, "permission granted.");
            this.checkPermissions = true;
            SharedPrefUtil.save(getApplicationContext(), HAS_PERMISSION, "1");
            init();
            if (DialogUtil.isShowDisclaimer(getApplicationContext())) {
                showDisclaimerLater();
            }
            updateByConfig();
        }
    }

    private void showDisclaimerLater() {
        RelativeLayout relativeLayout = this.mainContainer;
        if (relativeLayout == null) {
            return;
        }
        relativeLayout.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$x20O_-kHqusvgIZoGLHD0IRiWmU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$showDisclaimerLater$12$MainActivity();
            }
        }, 1000L);
    }

    public /* synthetic */ void lambda$showDisclaimerLater$12$MainActivity() {
        this.disclaimerDialog = DialogUtil.showDisclaimer(this, true, this);
    }

    @Override // com.byd.auto_emanual.fragment.SearchInputListener
    public void doInputClick() {
        this.result.setVisibility(8);
        this.mainContainer.setVisibility(8);
        this.searchResultContainer.setVisibility(0);
        this.resultSearchHistoryAdapter.setData(SearchHistoryUtil.getHistory());
        showInputMethod();
    }

    @Override // com.byd.auto_emanual.fragment.SearchInputListener
    public void doSearchHistory(String history) {
        doInputClick();
        this.resultInput.setText(history);
        this.resultInput.setSelection(history.length());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        RelativeLayout relativeLayout = this.searchResultContainer;
        if (relativeLayout != null && relativeLayout.getVisibility() == 0) {
            doResultBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.orientation = getResources().getConfiguration().orientation;
        updateByConfig();
        hideResultByLocalChanged(newConfig);
    }

    private void hideResultByLocalChanged(Configuration newConfig) {
        Locale locale = newConfig.getLocales().get(0);
        if (locale.equals(this.locale)) {
            return;
        }
        this.locale = locale;
        RelativeLayout relativeLayout = this.searchResultContainer;
        if (relativeLayout == null || relativeLayout.getVisibility() != 0) {
            return;
        }
        doResultBack();
    }

    private void updateByConfig() {
        if (this.checkPermissions) {
            updateMainContainerByConfig();
            updateTabsByConfig();
            updateFragmentsContainerByConfig();
            NormalDataAdapter.updateDefaultColumnAndRowByConfig(this.orientation);
            updateBarByConfig();
            updateResultByConfig();
            updateTextByConfig();
            updateColorByConfig();
            updateResultInputByConfig();
            return;
        }
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.background_horizontal));
    }

    private void updateResultInputByConfig() {
        if (ViewUtil.isRtl(getResources().getConfiguration().getLayoutDirection())) {
            this.resultInput.setGravity(21);
        } else {
            this.resultInput.setGravity(19);
        }
    }

    private void updateMainContainerByConfig() {
        ResourceUtil.setBackground(this.mainContainer, ViewUtil.isPort(this.orientation) ? R.attr.background_port : R.attr.background_horizontal);
    }

    private void updateTabsByConfig() {
        int dimensionPixelOffset;
        int dimensionPixelSize;
        int dimensionPixelOffset2;
        int dimensionPixelOffset3;
        int i;
        TabAdapter tabAdapter = new TabAdapter(this, this.orientation);
        this.tabAdapter = tabAdapter;
        this.tabView.setAdapter(tabAdapter);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.tabContainer.getLayoutParams();
        if (ViewUtil.isPort(this.orientation)) {
            this.tabManager.setOrientation(0);
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.tab_item_margin_start_port);
            if (Utils.notShowVideo(getApplicationContext())) {
                dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.tab_item_margin_start_port_not_show_video);
            }
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.tab_item_margin_top_port);
            dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.tab_width_port);
            dimensionPixelOffset3 = getResources().getDimensionPixelOffset(R.dimen.tab_height_port);
            i = R.attr.tab_background_port;
        } else {
            this.tabManager.setOrientation(1);
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.tab_item_margin_start);
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.tab_item_margin_top);
            if (Utils.notShowVideo(getApplicationContext())) {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.tab_item_margin_top_not_show_video);
            }
            dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.tab_width);
            dimensionPixelOffset3 = getResources().getDimensionPixelOffset(R.dimen.tab_height);
            i = R.attr.tab_background_horizontal;
            if (DeviceUtil.isRightDriver() || ViewUtil.isRtl(getResources().getConfiguration().getLayoutDirection())) {
                layoutParams.addRule(21);
            } else {
                layoutParams.removeRule(21);
            }
        }
        layoutParams.setMarginStart(dimensionPixelOffset);
        layoutParams.topMargin = dimensionPixelSize;
        layoutParams.width = dimensionPixelOffset2;
        layoutParams.height = dimensionPixelOffset3;
        this.tabContainer.setLayoutParams(layoutParams);
        ResourceUtil.setBackground(this.tabContainer, i);
        if (ViewUtil.isPort(this.orientation) && ViewUtil.isRtl(getResources().getConfiguration().getLayoutDirection())) {
            this.tabManager.setReverseLayout(true);
        } else {
            this.tabManager.setReverseLayout(false);
        }
    }

    private void updateFragmentsContainerByConfig() {
        int dimensionPixelOffset;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.frameLayout.getLayoutParams();
        if (DeviceUtil.isRightDriver()) {
            if (ViewUtil.isPort(this.orientation)) {
                layoutParams.addRule(3, R.id.tab_container);
                layoutParams.removeRule(0);
                dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.fragment_container_margin_top_port);
            } else {
                layoutParams.removeRule(3);
                layoutParams.addRule(0, R.id.tab_container);
                dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.fragment_container_margin_top);
            }
        } else if (ViewUtil.isPort(this.orientation)) {
            layoutParams.addRule(3, R.id.tab_container);
            layoutParams.removeRule(1);
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.fragment_container_margin_top_port);
        } else {
            layoutParams.removeRule(3);
            layoutParams.addRule(1, R.id.tab_container);
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.fragment_container_margin_top);
        }
        layoutParams.topMargin = dimensionPixelOffset;
        this.frameLayout.setLayoutParams(layoutParams);
    }

    private void updateBarByConfig() {
        if (this.resultBack != null) {
            if (ViewUtil.isRtl(getResources().getConfiguration().getLayoutDirection())) {
                this.resultBack.setRotation(180.0f);
            } else {
                this.resultBack.setRotation(0.0f);
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        MLog.i(TAG, "onDestroy");
        unRegisterNetworkCallback();
        if (this.checkPermissions) {
            GearboxListener.getInstance().unregister();
        }
        NormalDataAdapter.menuData.clear();
        NormalDataAdapter.menuData = null;
        NormalDataAdapter.netAddress = null;
        NormalDataAdapter.zipUrl = null;
        ActivityManager.getInstance().deleteActivity(this);
        Process.killProcess(Process.myPid());
    }

    public void enterManual() {
        enterManualImpl();
    }

    @Override // com.byd.auto_emanual.IStopOrGo
    public boolean isForeground() {
        return this.isForeground;
    }

    @Override // com.byd.auto_emanual.IStopOrGo
    public AlertDialog getStopOrGoDialog() {
        return this.stopOrGoDialog;
    }

    @Override // com.byd.auto_emanual.IStopOrGo
    public void setStopOrGoDialog(AlertDialog dialog) {
        this.stopOrGoDialog = dialog;
    }

    @Override // com.byd.auto_emanual.IStopOrGo
    public void showStopOrGoDialog(boolean showStopOrGo) {
        if (showStopOrGo) {
            this.handler.sendMessage(this.handler.obtainMessage(2));
        } else {
            this.handler.removeMessages(2);
            this.stopOrGoDialog = null;
        }
    }

    @Override // com.byd.auto_emanual.IRefresh
    public void refresh() {
        TabAdapter tabAdapter = this.tabAdapter;
        if (tabAdapter != null) {
            tabAdapter.updateDot();
        }
    }

    @Override // com.byd.auto_emanual.util.MobileDataUtil.MobileDataUsageListener
    public void onMobileDataUsageReceived(long useBytes) {
        calcMobileDataMain(Long.valueOf(useBytes));
    }

    class MainHandler extends Handler {
        public MainHandler(Looper mainLooper) {
            super(mainLooper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i == 1) {
                MainActivity.this.showMobileDataPrompt(msg);
            } else {
                if (i != 2) {
                    return;
                }
                MainActivity.this.showStopOrGo();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMobileDataPrompt(Message msg) {
        int i;
        try {
            i = Integer.parseInt(String.valueOf(msg.obj));
        } catch (NumberFormatException e) {
            MLog.e(TAG, "showMobileDataPrompt", e);
            i = 30;
        }
        MobileDataUtil.promptMobileData(i);
        AlertDialog alertDialog = this.mobileDataPromptDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            this.mobileDataPromptDialog = DialogUtil.mobileDataPromptDialog(this, i, new View.OnClickListener() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$lo_1TFNRSWtChTWlDY6JmSedwEI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showMobileDataPrompt$13$MainActivity(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$showMobileDataPrompt$13$MainActivity(View view) {
        goToMore();
    }

    private void calcMobileDataMain(Long useBytes) {
        double dCeil = Math.ceil(((useBytes.longValue() * 1.0d) / 1024.0d) / 1024.0d);
        MLog.i(TAG, "calcMobileDataMain bytes " + dCeil);
        this.handler.removeCallbacks(new Runnable() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$bTUuxjyejohNSxElvP5SqQpMAbw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$calcMobileDataMain$14$MainActivity();
            }
        });
        if (dCeil >= 200.0d) {
            if (MobileDataUtil.isShowedMobileDataPrompt(MobileDataUtil.SHOW_MOBILE_DATA_PROMPT_MB_200)) {
                return;
            }
            this.handler.sendMessage(this.handler.obtainMessage(1, 200));
            return;
        }
        if (dCeil >= 100.0d) {
            if (!MobileDataUtil.isShowedMobileDataPrompt(MobileDataUtil.SHOW_MOBILE_DATA_PROMPT_MB_100)) {
                this.handler.sendMessage(this.handler.obtainMessage(1, 100));
            }
            this.handler.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$vmLjlPGkWsRaCYKGsgh1UmTqPfU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$calcMobileDataMain$15$MainActivity();
                }
            }, 10000L);
            return;
        }
        if (dCeil >= 30.0d) {
            if (!MobileDataUtil.isShowedMobileDataPrompt(MobileDataUtil.SHOW_MOBILE_DATA_PROMPT_MB_30)) {
                this.handler.sendMessage(this.handler.obtainMessage(1, 30));
            }
            this.handler.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$UYnrT4on885co56cvJDvCvYLRQo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$calcMobileDataMain$16$MainActivity();
                }
            }, 10000L);
            return;
        }
        this.handler.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.-$$Lambda$MainActivity$PHYNzKw-S5Kpu9JKo2mLHcPgd3g
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$calcMobileDataMain$17$MainActivity();
            }
        }, 10000L);
    }

    public /* synthetic */ void lambda$calcMobileDataMain$14$MainActivity() {
        MobileDataUtil.calcMobileData(this);
    }

    public /* synthetic */ void lambda$calcMobileDataMain$15$MainActivity() {
        MobileDataUtil.calcMobileData(this);
    }

    public /* synthetic */ void lambda$calcMobileDataMain$16$MainActivity() {
        MobileDataUtil.calcMobileData(this);
    }

    public /* synthetic */ void lambda$calcMobileDataMain$17$MainActivity() {
        MobileDataUtil.calcMobileData(this);
    }

    @Override // com.byd.auto_emanual.fragment.SearchHistoryAdapter.Callback
    public void updateState(int historyItemCount) {
        if (historyItemCount == 0) {
            this.resultHistory.setVisibility(8);
        } else {
            this.resultHistory.setVisibility(0);
        }
    }

    @Override // com.byd.auto_emanual.fragment.SearchHistoryAdapter.Callback
    public void doHistoryItemClick(String history) {
        doSearchHistory(history);
    }
}
