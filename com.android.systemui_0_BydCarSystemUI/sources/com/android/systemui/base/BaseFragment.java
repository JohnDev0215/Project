package com.android.systemui.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.base.BaseContract;
import com.android.systemui.base.BaseContract.BasePresenter;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseFragment<T extends BaseContract.BasePresenter> extends Fragment implements BaseContract.BaseView {
    protected Context mContext;
    protected View rootView;
    protected boolean mIsPrepare = false;
    boolean mIsVisible = false;
    boolean mIsFirstLoad = true;
    protected T mPresenter = (T) createModel();

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

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

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        BydLogHelper.logD("onAttach: " + this);
        super.onAttach(context);
        this.mContext = context;
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BydLogHelper.logD("onCreate: ");
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BydLogHelper.logD("onCreateView: ");
        if (this.rootView == null) {
            this.rootView = inflater.inflate(getLayoutId(), container, false);
        }
        attachView();
        initView(this.rootView);
        listener();
        initData();
        return this.rootView;
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mIsPrepare = true;
        BydLogHelper.logD("onViewCreated: ");
        lazyLoad();
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        BydLogHelper.logD("setUserVisibleHint: isVisibleToUser：" + isVisibleToUser);
        if (isVisibleToUser) {
            this.mIsVisible = true;
            lazyLoad();
        } else {
            this.mIsVisible = false;
        }
    }

    private void lazyLoad() {
        if (this.mIsPrepare && this.mIsVisible && this.mIsFirstLoad) {
            this.mIsFirstLoad = false;
        }
    }

    protected void initData() {
        T t = this.mPresenter;
        if (t != null) {
            t.initData();
        }
    }

    protected void listener() {
        T t = this.mPresenter;
        if (t != null) {
            t.addListener();
        }
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        BydLogHelper.logD("onDestroyView: " + this);
        this.mIsFirstLoad = true;
        this.mIsPrepare = false;
        this.mIsVisible = false;
        View view = this.rootView;
        if (view != null && view.getParent() != null) {
            ((ViewGroup) this.rootView.getParent()).removeView(this.rootView);
        }
        detachView();
        super.onDestroyView();
    }

    protected void attachView() {
        BydLogHelper.logD("attachView: ");
        T t = this.mPresenter;
        if (t != null) {
            t.attachView(this);
        }
    }

    protected void detachView() {
        BydLogHelper.logD("detachView: ");
        T t = this.mPresenter;
        if (t != null) {
            t.detachView();
            this.mPresenter.removeListener();
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BydLogHelper.logD("onDestroy: ");
    }

    @Override // android.app.Fragment, com.android.systemui.base.BaseContract.BaseView
    public Context getContext() {
        return this.mContext;
    }
}
