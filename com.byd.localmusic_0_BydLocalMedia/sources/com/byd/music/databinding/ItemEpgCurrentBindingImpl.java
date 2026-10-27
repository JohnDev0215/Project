package com.byd.music.databinding;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.RadioEpgDBBean;
import com.byd.music.fragment.radio.EpgListViewModel;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ItemEpgCurrentBindingImpl extends ItemEpgCurrentBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public ItemEpgCurrentBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private ItemEpgCurrentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ImageButton) objArr[3], (TextView) objArr[1], (TextView) objArr[2]);
        this.mDirtyFlags = -1L;
        this.imgBtn.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.textView2.setTag(null);
        this.textView3.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        if (BR.epgBean == i) {
            setEpgBean((RadioEpgDBBean) obj);
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((EpgListViewModel) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.ItemEpgCurrentBinding
    public void setEpgBean(RadioEpgDBBean radioEpgDBBean) {
        this.mEpgBean = radioEpgDBBean;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.epgBean);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemEpgCurrentBinding
    public void setViewModel(EpgListViewModel epgListViewModel) {
        this.mViewModel = epgListViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModelSubscribeEpgList((ObservableArrayList) obj, i2);
    }

    private boolean onChangeViewModelSubscribeEpgList(ObservableArrayList<RadioEpgDBBean> observableArrayList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        BindingCommand<RadioEpgDBBean> bindingCommand;
        int colorFromResource;
        String strLongTransFormString;
        int colorFromResource2;
        Drawable drawable;
        String str;
        BindingCommand<RadioEpgDBBean> bindingCommand2;
        Context context;
        int i;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        RadioEpgDBBean radioEpgDBBean = this.mEpgBean;
        EpgListViewModel epgListViewModel = this.mViewModel;
        long j4 = j & 15;
        if (j4 != 0) {
            String desc = ((j & 10) == 0 || radioEpgDBBean == null) ? null : radioEpgDBBean.getDesc();
            if ((j & 14) != 0) {
                long epgStartTime = radioEpgDBBean != null ? radioEpgDBBean.getEpgStartTime() : 0L;
                bindingCommand2 = epgListViewModel != null ? epgListViewModel.subscribeCommand : null;
                strLongTransFormString = epgListViewModel != null ? epgListViewModel.longTransFormString(Long.valueOf(epgStartTime)) : null;
            } else {
                strLongTransFormString = null;
                bindingCommand2 = null;
            }
            ObservableArrayList<RadioEpgDBBean> observableArrayList = epgListViewModel != null ? epgListViewModel.subscribeEpgList : null;
            updateRegistration(0, observableArrayList);
            boolean zContains = observableArrayList != null ? observableArrayList.contains(radioEpgDBBean) : false;
            if (j4 != 0) {
                if (zContains) {
                    j2 = j | 32 | 128;
                    j3 = 512;
                } else {
                    j2 = j | 16 | 64;
                    j3 = 256;
                }
                j = j2 | j3;
            }
            colorFromResource = getColorFromResource(this.textView2, zContains ? R.color.text_color_selected : R.color.color_white_front);
            colorFromResource2 = getColorFromResource(this.textView3, zContains ? R.color.text_color_selected : R.color.color_white_front);
            if (zContains) {
                context = this.imgBtn.getContext();
                i = R.drawable.multi_pitch_regular_s;
            } else {
                context = this.imgBtn.getContext();
                i = R.drawable.multi_nromal_regular_s;
            }
            Drawable drawable2 = AppCompatResources.getDrawable(context, i);
            str = desc;
            BindingCommand<RadioEpgDBBean> bindingCommand3 = bindingCommand2;
            drawable = drawable2;
            bindingCommand = bindingCommand3;
        } else {
            bindingCommand = null;
            colorFromResource = 0;
            strLongTransFormString = null;
            colorFromResource2 = 0;
            drawable = null;
            str = null;
        }
        if ((15 & j) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.imgBtn, drawable);
            this.textView2.setTextColor(colorFromResource);
            this.textView3.setTextColor(colorFromResource2);
        }
        if ((j & 14) != 0) {
            BindingUtils.onClickCommand(this.mboundView0, bindingCommand, false, radioEpgDBBean, false);
            TextViewBindingAdapter.setText(this.textView2, strLongTransFormString);
        }
        if ((j & 10) != 0) {
            TextViewBindingAdapter.setText(this.textView3, str);
        }
    }
}
