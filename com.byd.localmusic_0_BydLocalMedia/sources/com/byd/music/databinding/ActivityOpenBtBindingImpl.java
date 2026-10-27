package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.fragment.bluetooth.activity.OpenBTViewModel;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ActivityOpenBtBindingImpl extends ActivityOpenBtBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_desc, 4);
    }

    public ActivityOpenBtBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private ActivityOpenBtBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[2], (TextView) objArr[3], (TextView) objArr[4]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) objArr[1];
        this.mboundView1 = constraintLayout2;
        constraintLayout2.setTag(null);
        this.tvCancel.setTag(null);
        this.tvConfirm.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
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
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((OpenBTViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.ActivityOpenBtBinding
    public void setViewModel(OpenBTViewModel openBTViewModel) {
        this.mViewModel = openBTViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        BindingCommand bindingCommand;
        BindingCommand bindingCommand2;
        BindingCommand bindingCommand3;
        BindingCommand bindingCommand4;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        OpenBTViewModel openBTViewModel = this.mViewModel;
        long j2 = j & 3;
        if (j2 == 0 || openBTViewModel == null) {
            bindingCommand = null;
            bindingCommand2 = null;
            bindingCommand3 = null;
            bindingCommand4 = null;
        } else {
            bindingCommand = openBTViewModel.confirmCommand;
            bindingCommand2 = openBTViewModel.outsizeClickCommand;
            bindingCommand4 = openBTViewModel.cancelCommand;
            bindingCommand3 = openBTViewModel.inClickCommand;
        }
        if (j2 != 0) {
            BindingUtils.onClickCommand(this.mboundView0, bindingCommand2, false, null, false);
            BindingUtils.onClickCommand(this.mboundView1, bindingCommand3, false, null, false);
            BindingUtils.onClickCommand(this.tvCancel, bindingCommand4, false, null, false);
            BindingUtils.onClickCommand(this.tvConfirm, bindingCommand, false, null, false);
        }
    }
}
