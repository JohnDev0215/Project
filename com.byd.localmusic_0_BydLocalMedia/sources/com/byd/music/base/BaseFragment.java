package com.byd.music.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.music.BackInterface;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.base.BaseViewModel;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.DiLinkDumpUtils;
import com.byd.music.utils.DipUtil;
import com.byd.music.utils.DoubleClickHelper;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.OrientationUtil;
import com.byd.music.view.LoadingView;
import com.byd.music.view.commonAdapter.GridItemDecoration;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {
    private BackInterface backInterface;
    protected Activity context;
    public V dataBinding;
    private View fragmentView;
    protected boolean ifSwysClick;
    private boolean isLoaded;
    protected boolean isOnResume;
    protected int lastDeviceOrientation;
    protected boolean lastIsMultiMode;
    protected int lastOrientation;
    private OnBackPressedCallback onBackPressedCallback;
    public VM viewModel;
    protected String TAG = getClass().getSimpleName();
    private boolean needRegister = false;

    protected abstract boolean disableBackground();

    protected String getPageId() {
        return null;
    }

    protected String getPageName() {
        return null;
    }

    protected abstract void initData();

    protected abstract void initView();

    protected boolean isPreLoad() {
        return false;
    }

    protected abstract int layoutId();

    protected boolean needsMultiLayout() {
        return true;
    }

    public abstract boolean onBack();

    protected abstract void onLazyInit();

    protected void onWindowModeChanged(boolean z, int i, boolean z2, int i2) {
    }

    protected void setNoNetViewVisible(boolean z) {
    }

    protected abstract int viewModelId();

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() == null) {
            setArguments(new Bundle());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.lastIsMultiMode = OrientationUtil.isMultiWindowMode(this.context);
        if (needsMultiLayout() && this.lastIsMultiMode) {
            updateOrientationInMultiMode();
        }
        initOrientationParams();
        View view = OnLineMusic.preLoadMap.get(getClass().getCanonicalName());
        LogUtil.d("preloadView：" + getClass().getCanonicalName() + " ," + view);
        if (view != null && isPreLoad() && !OrientationUtil.isMultiWindowMode(getActivity()) && view.getTag(R.layout.activity_media_main) == DeviceInfoUtils.isNight() && view.getTag(268435456) != null && ((Boolean) view.getTag(268435456)).booleanValue() == OrientationUtil.isPortrait()) {
            setContentPreloadView(view);
        } else if (this.dataBinding == null || needsMultiLayout()) {
            this.dataBinding = (V) DataBindingUtil.inflate(layoutInflater, layoutId(), viewGroup, false);
            initDataBinding();
        }
        boolean zNeedRegisterEventBus = needRegisterEventBus(getClass());
        this.needRegister = zNeedRegisterEventBus;
        if (zNeedRegisterEventBus) {
            EventBus.getDefault().register(this);
        }
        this.fragmentView = this.dataBinding.getRoot();
        if (getActivity() instanceof BackInterface) {
            BackInterface backInterface = (BackInterface) getActivity();
            this.backInterface = backInterface;
            backInterface.onFragmentAdd(this);
        }
        this.onBackPressedCallback = new OnBackPressedCallback(true) { // from class: com.byd.music.base.BaseFragment.1
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                BaseFragment.this.handleOnBackPressed();
            }
        };
        return this.fragmentView;
    }

    protected void handleOnBackPressed() {
        if (DoubleClickHelper.isOnDoubleClick(60)) {
            return;
        }
        if (getParentFragmentManager().getBackStackEntryCount() > 0) {
            FragmentUtils.pop(getParentFragmentManager());
        } else {
            getActivity().finish();
        }
    }

    protected void goHomePage() {
        if (getActivity() != null) {
            if (getActivity().getLocalClassName().contains("MediaActivity") || getActivity().getLocalClassName().contains("MediaMainActivity")) {
                Intent intent = new Intent(Intent.ACTION_MAIN, (Uri) null);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.addFlags(270532608);
                this.context.startActivity(intent);
                LogUtil.e("close activity and to home page");
            }
        }
    }

    private void updateOrientationInMultiMode() {
        Configuration configuration = getResources().getConfiguration();
        configuration.orientation = 1;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }

    private void setContentPreloadView(View view) {
        if (this.dataBinding == null) {
            this.dataBinding = (V) DataBindingUtil.bind(view);
            initDataBinding();
        }
        this.fragmentView = view;
        OnLineMusic.preLoadMap.remove(getClass().getCanonicalName());
        LogUtil.d("preloadView fragment: ", getClass().getCanonicalName());
    }

    private void initOrientationParams() {
        this.lastOrientation = getResources().getConfiguration().orientation;
        this.lastDeviceOrientation = Utils.getApp().getResources().getConfiguration().orientation;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        LogUtil.e(this.TAG, "onSaveInstanceState called... isFinishing = " + getActivity().isFinishing());
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (disableBackground() && getView() != null) {
            getView().setBackground(null);
        }
        initData();
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.viewModel.isMultiMode.set(this.lastIsMultiMode);
        this.viewModel.isPortraitMode.set(this.lastIsMultiMode || this.lastDeviceOrientation == 1 || this.lastOrientation == 1);
        initView();
        setNoNetViewVisible(!this.viewModel.getNetEvent().getValue().booleanValue());
        LogUtil.d("loadFragmentView", "start init fragment view: " + getClass().getCanonicalName() + " cost time " + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    public View getFragmentView() {
        return this.fragmentView;
    }

    protected void setLoadingView(boolean z) {
        LoadingView loadingView = (LoadingView) this.fragmentView.findViewById(R.id.loading_view);
        if (loadingView != null) {
            loadingView.setRotate(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        OnBackPressedCallback onBackPressedCallback = this.onBackPressedCallback;
        if (onBackPressedCallback != null) {
            onBackPressedCallback.remove();
            requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), this.onBackPressedCallback);
        }
        this.isOnResume = true;
        View view = this.fragmentView;
        if (view != null) {
            view.requestFocus();
        }
        if (!this.isLoaded) {
            onLazyInit();
            this.isLoaded = true;
        }
        LogUtil.d("onResume = " + this.TAG);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.isOnResume = false;
        OnBackPressedCallback onBackPressedCallback = this.onBackPressedCallback;
        if (onBackPressedCallback != null) {
            onBackPressedCallback.remove();
        }
        LogUtil.d("onPause = " + this.TAG);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.isLoaded = false;
        OnBackPressedCallback onBackPressedCallback = this.onBackPressedCallback;
        if (onBackPressedCallback != null) {
            onBackPressedCallback.remove();
            this.onBackPressedCallback = null;
        }
        if (this.needRegister) {
            EventBus.getDefault().unregister(this);
        }
        BackInterface backInterface = this.backInterface;
        if (backInterface != null) {
            backInterface.onFragmentRemove(this);
        }
        this.fragmentView = null;
        V v = this.dataBinding;
        if (v != null) {
            v.unbind();
            this.dataBinding = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }

    private void initDataBinding() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            this.viewModel = (VM) new ViewModelProvider(this).get((Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1]);
        }
        this.dataBinding.setVariable(viewModelId(), this.viewModel);
    }

    private boolean needRegisterEventBus(Class cls) {
        this.needRegister = false;
        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                return true;
            }
        }
        if (cls.getSuperclass() != null) {
            return needRegisterEventBus(cls.getSuperclass());
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean zIsMultiWindowMode = OrientationUtil.isMultiWindowMode(this.context);
        int i = Utils.getApp().getResources().getConfiguration().orientation;
        if (zIsMultiWindowMode == this.lastIsMultiMode && configuration.orientation == this.lastOrientation && i == this.lastDeviceOrientation) {
            return;
        }
        this.viewModel.isMultiMode.set(zIsMultiWindowMode);
        ObservableBoolean observableBoolean = this.viewModel.isPortraitMode;
        boolean z = true;
        if (!zIsMultiWindowMode && i != 1 && configuration.orientation != 1) {
            z = false;
        }
        observableBoolean.set(z);
        onWindowModeChanged(zIsMultiWindowMode, i, this.lastIsMultiMode, this.lastDeviceOrientation);
        this.lastOrientation = configuration.orientation;
        this.lastIsMultiMode = zIsMultiWindowMode;
        this.lastDeviceOrientation = i;
    }

    protected void setRecyclerViewByWindowMode(RecyclerView recyclerView, boolean z, int i) {
        int i2;
        recyclerView.setItemAnimator(null);
        if (i == 2 && z) {
            i2 = DipUtil.ITEM_DECORATION_WITH_PORTRAIT;
        } else {
            i2 = DipUtil.ITEM_DECORATION_WITH_LANDSCAPE;
        }
        recyclerView.removeAllViews();
        recyclerView.setLayoutManager(new GridLayoutManager((Context) getActivity(), this.viewModel.isPortraitMode.get() ? 4 : 6, 1, false));
        for (int i3 = 0; i3 < recyclerView.getItemDecorationCount(); i3++) {
            recyclerView.removeItemDecorationAt(i3);
        }
        recyclerView.addItemDecoration(new GridItemDecoration(i2));
    }

    protected void onSoundEffectClick() {
        Activity activity = this.context;
        DiLinkDumpUtils.go2SoundEffectPage(activity, activity.getPackageManager());
    }
}
