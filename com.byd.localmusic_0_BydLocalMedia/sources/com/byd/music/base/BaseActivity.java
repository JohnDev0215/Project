package com.byd.music.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.base.BaseViewModel;
import com.byd.music.style.LayoutPreWrapper;
import com.byd.music.style.UiStyleResourceWrapper;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.OrientationUtil;
import io.reactivex.disposables.CompositeDisposable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseActivity<T extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();
    private CompositeDisposable compositeDisposable;
    protected T dataBinding;
    protected boolean isOnResume;
    protected int lastDeviceOrientation;
    protected boolean lastIsMultiMode;
    protected int lastOrientation;
    private boolean needRegister;
    protected long onCreateTime;
    protected VM viewModel;

    private void setFullScreen() {
    }

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

    protected boolean needsHideBottomFloatingCard() {
        return true;
    }

    protected void onWindowModeChanged(boolean z, int i, boolean z2, int i2) {
    }

    protected abstract int viewModelId();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        LayoutPreWrapper.setLayoutPreWrapper(this);
        super.onCreate(bundle);
        this.onCreateTime = System.currentTimeMillis();
        LogUtil.e(this.TAG, "activity onCreateTime = " + this.onCreateTime);
        boolean zIsMultiWindowMode = OrientationUtil.isMultiWindowMode(this);
        this.lastIsMultiMode = zIsMultiWindowMode;
        boolean z = true;
        if (zIsMultiWindowMode) {
            Configuration configuration = getResources().getConfiguration();
            configuration.orientation = 1;
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        }
        initOrientationParams();
        getWindow().setSoftInputMode(32);
        View view = OnLineMusic.preLoadMap.get(getClass().getCanonicalName());
        LogUtil.d("preloadView：" + getClass().getCanonicalName() + " ," + view);
        if (view != null && isPreLoad() && view.getTag(R.layout.activity_media_main) == DeviceInfoUtils.isNight()) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
            setContentView(view);
            this.dataBinding = (T) DataBindingUtil.bind(view);
            LogUtil.d("preloadView used：" + getClass().getCanonicalName());
            OnLineMusic.preLoadMap.remove(getClass().getCanonicalName());
        } else {
            this.dataBinding = (T) DataBindingUtil.setContentView(this, layoutId());
        }
        initViewModel();
        initData();
        if (needsHideBottomFloatingCard()) {
            hideBottomFloatingCard();
        }
        this.viewModel.isMultiMode.set(this.lastIsMultiMode);
        ObservableBoolean observableBoolean = this.viewModel.isPortraitMode;
        if (!this.lastIsMultiMode && this.lastDeviceOrientation != 1) {
            z = false;
        }
        observableBoolean.set(z);
        initView();
        boolean zNeedRegisterEventBus = needRegisterEventBus(getClass());
        this.needRegister = zNeedRegisterEventBus;
        if (zNeedRegisterEventBus) {
            EventBus.getDefault().register(this);
        }
        setFullScreen();
    }

    private void initOrientationParams() {
        this.lastDeviceOrientation = getResources().getConfiguration().orientation;
        this.lastIsMultiMode = OrientationUtil.isMultiWindowMode(this);
    }

    private void hideBottomFloatingCard() {
        Intent intent = new Intent();
        intent.setAction("hideWindow");
        sendBroadcast(intent);
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

    private void initViewModel() {
        int iViewModelId = viewModelId();
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            this.viewModel = (VM) new ViewModelProvider(this).get((Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1]);
        }
        this.dataBinding.setVariable(iViewModelId, this.viewModel);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        this.isOnResume = true;
        Glide.with((FragmentActivity) this).resumeRequests();
        super.onResume();
        setFullScreen();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        this.isOnResume = false;
        super.onPause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        Glide.with((FragmentActivity) this).pauseAllRequests();
        super.onStop();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.onCreateTime != 0) {
            LogUtil.e(this.TAG, "activity create to visible cost: " + (System.currentTimeMillis() - this.onCreateTime));
            this.onCreateTime = 0L;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        clearCompositeDisposable();
        if (this.needRegister) {
            EventBus.getDefault().unregister(this);
        }
    }

    public int getNavBarHeight() {
        int identifier = getResources().getIdentifier("navigation_bar_height", UiStyleResourceWrapper.RES_TYPE_DIMEN, "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public int getStatusHeight() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    protected void clearCompositeDisposable() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        if (compositeDisposable != null) {
            if (!compositeDisposable.isDisposed()) {
                this.compositeDisposable.dispose();
            }
            this.compositeDisposable.clear();
            this.compositeDisposable = null;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean zIsMultiWindowMode = OrientationUtil.isMultiWindowMode(this);
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

    @Override // androidx.appcompat.app.AppCompatActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return new UiStyleResourceWrapper(this, super.getResources());
    }
}
