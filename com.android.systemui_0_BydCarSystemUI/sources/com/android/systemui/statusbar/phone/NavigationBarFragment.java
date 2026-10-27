package com.android.systemui.statusbar.phone;

import android.app.Fragment;
import android.app.StatusBarManager;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.view.InsetsState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.internal.view.AppearanceRegion;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.statusbar.AutoHideUiElement;
import com.android.systemui.statusbar.CommandQueue;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class NavigationBarFragment extends Fragment implements CommandQueue.Callbacks {
    public static final boolean DEBUG = true;
    private static final String EXTRA_DISABLE2_STATE = "disabled2_state";
    private static final String EXTRA_DISABLE_STATE = "disabled_state";
    private static WindowManager.LayoutParams mLp;
    private static WindowManager.LayoutParams mLpChanged;
    private int mAppearance;
    private AutoHideController mAutoHideController;
    private int mBarHeight;
    private CommandQueue mCommandQueue;
    private Context mContext;
    private int mDisabledFlags1;
    private int mDisabledFlags2;
    private int mNavigationBarMode;
    private boolean mTransientShown;
    private NavigationBarInflaterView navigation_inflater;
    protected NavigationBarView mNavigationBarView = null;
    private int mNavigationBarWindowState = 0;
    private final AutoHideUiElement mAutoHideUiElement = new AutoHideUiElement() { // from class: com.android.systemui.statusbar.phone.NavigationBarFragment.1
        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public boolean shouldHideOnTouch() {
            return true;
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public void synchronizeState() {
            NavigationBarFragment.this.checkNavBarModes();
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public boolean isVisible() {
            return NavigationBarFragment.this.isTransientShown();
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public void hide() {
            NavigationBarFragment.this.clearTransient();
        }
    };

    private static int barMode(boolean isTransient, int appearance) {
        if (isTransient) {
            return 1;
        }
        if ((appearance & 6) == 6) {
            return 3;
        }
        if ((appearance & 4) != 0) {
            return 6;
        }
        return (appearance & 2) != 0 ? 4 : 0;
    }

    public void checkNavBarModes() {
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void onBydAppThemeChanged() {
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BydLogHelper.logD("onCreate state = " + (savedInstanceState != null ? savedInstanceState.toString() : "null"));
        if (savedInstanceState != null) {
            this.mDisabledFlags1 = savedInstanceState.getInt(EXTRA_DISABLE_STATE, 0);
            this.mDisabledFlags2 = savedInstanceState.getInt(EXTRA_DISABLE2_STATE, 0);
        }
        CommandQueue commandQueue = (CommandQueue) SysUiServiceProvider.getComponent(this.mContext, CommandQueue.class);
        this.mCommandQueue = commandQueue;
        commandQueue.addCallback(this);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BydLogHelper.logD("onViewCreated state = " + (savedInstanceState != null ? savedInstanceState.toString() : "null"));
        this.mNavigationBarView = (NavigationBarView) view;
        this.navigation_inflater = (NavigationBarInflaterView) view.findViewById(R.id.navigation_inflater);
    }

    public void updateNaviBarBg(boolean splitScreenStatus) {
        NavigationBarInflaterView navigationBarInflaterView = this.navigation_inflater;
        if (navigationBarInflaterView != null) {
            navigationBarInflaterView.updateNaviBarBg(splitScreenStatus);
        }
    }

    public void updateNaviBarStatus(int value) {
        NavigationBarInflaterView navigationBarInflaterView = this.navigation_inflater;
        if (navigationBarInflaterView != null) {
            navigationBarInflaterView.updateNaviBarStatus(value);
        }
    }

    public void bindBydCarSettingsService() {
        NavigationBarInflaterView navigationBarInflaterView = this.navigation_inflater;
        if (navigationBarInflaterView != null) {
            navigationBarInflaterView.bindBydCarSettingsService();
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BydLogHelper.logD("onCreateView state = " + (savedInstanceState != null ? savedInstanceState.toString() : "null"));
        return inflater.inflate(R.layout.navigation_bar, container, false);
    }

    public static View create(Context context, FragmentHostManager.FragmentListener listener) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2019, 8650856, -3);
        mLp = layoutParams;
        layoutParams.token = new Binder();
        mLp.gravity = 80;
        mLp.setTitle("NavigationBar");
        mLp.accessibilityTitle = context.getString(R.string.nav_bar);
        mLp.windowAnimations = 0;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.navigation_bar_window, (ViewGroup) null);
        BydLogHelper.logD("addNavigationBar: about to add " + viewInflate);
        if (viewInflate == null) {
            return null;
        }
        ((WindowManager) context.getSystemService(WindowManager.class)).addView(viewInflate, mLp);
        FragmentHostManager fragmentHostManager = FragmentHostManager.get(viewInflate);
        fragmentHostManager.getFragmentManager().beginTransaction().replace(R.id.navigation_bar_frame, new NavigationBarFragment(), NavigationBarFragment.class.getSimpleName()).commitNow();
        fragmentHostManager.addTagListener(NavigationBarFragment.class.getSimpleName(), listener);
        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams();
        mLpChanged = layoutParams2;
        layoutParams2.copyFrom(mLp);
        return viewInflate;
    }

    public void onBootCompleted() {
        this.navigation_inflater.onBootCompleted();
    }

    public void updateNaviBar(boolean visible, View naviView) {
        if (visible) {
            mLpChanged.height = -1;
        } else {
            mLpChanged.height = 0;
        }
        if (mLp.copyFrom(mLpChanged) != 0) {
            ((WindowManager) this.mContext.getSystemService(WindowManager.class)).updateViewLayout(naviView, mLpChanged);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void setIcon(String slot, StatusBarIcon icon) {
        BydLogHelper.logD("setIcon");
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void topAppWindowChanged(boolean showMenu) {
        NavigationBarView navigationBarView = this.mNavigationBarView;
        if (navigationBarView != null) {
            navigationBarView.setMenuVisibility(showMenu);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void setWindowState(int window, int state) {
        if (this.mNavigationBarView == null || window != 2 || this.mNavigationBarWindowState == state) {
            return;
        }
        this.mNavigationBarWindowState = state;
        BydLogHelper.logD("Navigation bar " + StatusBarManager.windowStateToString(state));
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_DISABLE_STATE, this.mDisabledFlags1);
        outState.putInt(EXTRA_DISABLE2_STATE, this.mDisabledFlags2);
    }

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        BydLogHelper.logD("onDetach");
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        CommandQueue commandQueue = this.mCommandQueue;
        if (commandQueue != null) {
            commandQueue.removeCallbacks(this);
            this.mCommandQueue = null;
        }
        super.onDestroy();
        BydLogHelper.logD("onDestroy");
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void showTransient(int displayId, int[] types) {
        if (InsetsState.containsType(types, 1) && !this.mTransientShown) {
            this.mTransientShown = true;
            handleTransientChanged();
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void abortTransient(int displayId, int[] types) {
        if (InsetsState.containsType(types, 1)) {
            clearTransient();
        }
    }

    private void handleTransientChanged() {
        if (getView() == null) {
            return;
        }
        updateBarMode(barMode(this.mTransientShown, this.mAppearance));
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void onSystemBarAppearanceChanged(int displayId, int appearance, AppearanceRegion[] appearanceRegions, boolean navbarColorManagedByIme) {
        if (this.mAppearance != appearance) {
            this.mAppearance = appearance;
        }
    }

    private boolean updateBarMode(int barMode) {
        if (this.mNavigationBarMode == barMode) {
            return false;
        }
        this.mNavigationBarMode = barMode;
        checkNavBarModes();
        AutoHideController autoHideController = this.mAutoHideController;
        if (autoHideController == null) {
            return true;
        }
        autoHideController.touchAutoHide();
        return true;
    }

    public void setAutoHideController(AutoHideController autoHideController) {
        this.mAutoHideController = autoHideController;
        if (autoHideController != null) {
            autoHideController.setNavigationBar(this.mAutoHideUiElement);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTransientShown() {
        return this.mTransientShown;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransient() {
        if (this.mTransientShown) {
            this.mTransientShown = false;
            handleTransientChanged();
        }
    }

    public void restoreAppearanceAndTransientState() {
        this.mNavigationBarMode = barMode(this.mTransientShown, this.mAppearance);
        checkNavBarModes();
    }
}
