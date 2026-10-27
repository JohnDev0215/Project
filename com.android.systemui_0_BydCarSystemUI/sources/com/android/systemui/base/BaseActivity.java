package com.android.systemui.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import com.android.systemui.base.BaseContract;
import com.android.systemui.base.BaseContract.BasePresenter;
import com.ts.avm.sdk.impl.AvmManagerImpl;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseActivity<T extends BaseContract.BasePresenter> extends AppCompatActivity implements BaseContract.BaseView {
    protected boolean isHideStatusBar;
    protected final String TAG = getClass().getSimpleName();
    public T mPresenter = (T) createModel();

    @Override // com.android.systemui.base.BaseContract.BaseView
    public Context getContext() {
        return this;
    }

    protected abstract int getLayoutResID();

    protected abstract void initView();

    private T createModel() {
        try {
            return (T) getRawType(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.isHideStatusBar) {
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                window.addFlags(Integer.MIN_VALUE);
                window.clearFlags(AvmManagerImpl.AVM_CALIBRATION_CODE);
                window.setStatusBarColor(0);
                window.getDecorView().setSystemUiVisibility(1280);
            }
        } else {
            getWindow().setFlags(1024, 1024);
        }
        setContentView(getLayoutResID());
        attachView();
        initView();
        listener();
        initData();
    }

    protected void initData() {
        T t = this.mPresenter;
        if (t != null) {
            t.initData();
        }
    }

    protected void attachView() {
        T t = this.mPresenter;
        if (t != null) {
            t.attachView(this);
        }
    }

    protected void detachView() {
        T t = this.mPresenter;
        if (t != null) {
            t.detachView();
            this.mPresenter.removeListener();
        }
    }

    protected void listener() {
        T t = this.mPresenter;
        if (t != null) {
            t.addListener();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        detachView();
        super.onDestroy();
    }
}
