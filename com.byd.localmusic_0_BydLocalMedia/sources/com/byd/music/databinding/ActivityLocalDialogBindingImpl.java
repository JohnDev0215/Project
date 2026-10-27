package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.fragment.localMusic.activity.DialogActivityViewModel;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ActivityLocalDialogBindingImpl extends ActivityLocalDialogBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tvDiv, 4);
        sViewsWithIds.put(R.id.tv_desc, 5);
    }

    public ActivityLocalDialogBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private ActivityLocalDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (TextView) objArr[2], (TextView) objArr[3], (TextView) objArr[5], (TextView) objArr[4]);
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
            this.mDirtyFlags = 4L;
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
        setViewModel((DialogActivityViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.ActivityLocalDialogBinding
    public void setViewModel(DialogActivityViewModel dialogActivityViewModel) {
        this.mViewModel = dialogActivityViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModelIsRight((ObservableBoolean) obj, i2);
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

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        BindingCommand bindingCommand;
        int i;
        int i2;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        DialogActivityViewModel dialogActivityViewModel = this.mViewModel;
        long j4 = j & 7;
        if (j4 != 0) {
            bindingCommand = ((j & 6) == 0 || dialogActivityViewModel == null) ? null : dialogActivityViewModel.clContentCommand;
            ObservableBoolean observableBoolean = dialogActivityViewModel != null ? dialogActivityViewModel.isRight : null;
            updateRegistration(0, observableBoolean);
            boolean z = observableBoolean != null ? observableBoolean.get() : false;
            if (j4 != 0) {
                if (z) {
                    j2 = j | 16;
                    j3 = 64;
                } else {
                    j2 = j | 8;
                    j3 = 32;
                }
                j = j2 | j3;
            }
            i2 = z ? 0 : R.id.tvDiv;
            i = z ? R.id.tvDiv : 0;
        } else {
            bindingCommand = null;
            i = 0;
            i2 = 0;
        }
        if ((6 & j) != 0) {
            BindingUtils.onClickCommand(this.mboundView1, bindingCommand, false, null, false);
        }
        if ((j & 7) != 0) {
            BindingUtils.setConstraintCustomAndChain(this.tvCancel, 0, i2, i, 0, 0);
            BindingUtils.setConstraintCustomAndChain(this.tvConfirm, 0, i, i2, 0, 0);
        }
    }
}
