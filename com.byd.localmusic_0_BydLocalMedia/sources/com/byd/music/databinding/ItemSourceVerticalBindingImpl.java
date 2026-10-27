package com.byd.music.databinding;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.activity.main.MediaMainViewModel;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingUtils;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ItemSourceVerticalBindingImpl extends ItemSourceVerticalBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public ItemSourceVerticalBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private ItemSourceVerticalBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (ImageView) objArr[1], (TextView) objArr[2]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.sourceImage.setTag(null);
        this.sourceTitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
        if (BR.titleId == i) {
            setTitleId((Integer) obj);
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((MediaMainViewModel) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.ItemSourceVerticalBinding
    public void setTitleId(Integer num) {
        this.mTitleId = num;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.titleId);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemSourceVerticalBinding
    public void setViewModel(MediaMainViewModel mediaMainViewModel) {
        this.mViewModel = mediaMainViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeViewModelIsRight((ObservableBoolean) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeViewModelCheckedPosition((ObservableInt) obj, i2);
    }

    private boolean onChangeViewModelIsRight(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelCheckedPosition(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        int iSafeUnbox;
        boolean z;
        Drawable drawable;
        BindingCommand<Integer> bindingCommand;
        int iIndexOf;
        Context context;
        int i;
        List<Integer> list;
        List<Integer> list2;
        ObservableInt observableInt;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Integer num = this.mTitleId;
        MediaMainViewModel mediaMainViewModel = this.mViewModel;
        long j2 = j & 30;
        int iSafeUnbox2 = (j2 == 0 || (j & 20) == 0) ? 0 : ViewDataBinding.safeUnbox(num);
        Drawable drawable2 = null;
        if ((31 & j) != 0) {
            if (j2 != 0) {
                if (mediaMainViewModel != null) {
                    list2 = mediaMainViewModel.titleList;
                    observableInt = mediaMainViewModel.checkedPosition;
                } else {
                    list2 = null;
                    observableInt = null;
                }
                z = true;
                updateRegistration(1, observableInt);
                iIndexOf = list2 != null ? list2.indexOf(num) : 0;
                if ((observableInt != null ? observableInt.get() : 0) != iIndexOf) {
                    z = false;
                }
            } else {
                iIndexOf = 0;
                z = false;
            }
            if ((j & 28) != 0) {
                if (mediaMainViewModel != null) {
                    bindingCommand = mediaMainViewModel.sourceClickCommand;
                    list = mediaMainViewModel.sourceImageList;
                } else {
                    bindingCommand = null;
                    list = null;
                }
                iSafeUnbox = ViewDataBinding.safeUnbox(list != null ? list.get(iIndexOf) : null);
            } else {
                iSafeUnbox = 0;
                bindingCommand = null;
            }
            long j3 = j & 25;
            if (j3 != 0) {
                ObservableBoolean observableBoolean = mediaMainViewModel != null ? mediaMainViewModel.isRight : null;
                updateRegistration(0, observableBoolean);
                boolean z2 = observableBoolean != null ? observableBoolean.get() : false;
                if (j3 != 0) {
                    j |= z2 ? 64L : 32L;
                }
                if (z2) {
                    context = this.mboundView0.getContext();
                    i = R.drawable.bg_main_tab_right;
                } else {
                    context = this.mboundView0.getContext();
                    i = R.drawable.bg_main_tab;
                }
                drawable2 = AppCompatResources.getDrawable(context, i);
            }
            drawable = drawable2;
        } else {
            iSafeUnbox = 0;
            z = false;
            drawable = null;
            bindingCommand = null;
        }
        if ((30 & j) != 0) {
            BindingUtils.setSelected(this.mboundView0, z);
        }
        if ((j & 25) != 0) {
            ViewBindingAdapter.setBackground(this.mboundView0, drawable);
        }
        if ((j & 28) != 0) {
            BindingUtils.onClickCommand(this.mboundView0, bindingCommand, false, num, false);
            BindingUtils.setImageRes(this.sourceImage, iSafeUnbox);
        }
        if ((j & 20) != 0) {
            this.sourceTitle.setText(iSafeUnbox2);
        }
    }
}
