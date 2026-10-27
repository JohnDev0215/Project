package com.byd.music.base;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.byd.music.base.BaseViewModel;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseView<T extends ViewDataBinding, VM extends BaseViewModel> extends ConstraintLayout {
    public Context context;
    protected T dataBinding;
    protected VM viewModel;

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int layoutId();

    protected abstract int viewModelId();

    public BaseView(Context context) {
        super(context);
        this.context = context;
    }

    public BaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        this.dataBinding = (T) DataBindingUtil.getBinding(inflate(context, layoutId(), this));
        initViewModel();
        initData();
        initView();
    }

    public BaseView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        this.dataBinding = (T) DataBindingUtil.getBinding(inflate(context, layoutId(), this));
        initViewModel();
        initData();
        initView();
    }

    private void initViewModel() {
        int iViewModelId = viewModelId();
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            this.viewModel = (VM) new ViewModelProvider((ViewModelStoreOwner) this.context).get((Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1]);
        }
        this.dataBinding.setVariable(iViewModelId, this.viewModel);
    }
}
