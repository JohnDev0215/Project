package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableLong;
import androidx.databinding.ViewDataBinding;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.beans.RadioEpgDBBean;
import com.byd.music.fragment.radio.DABViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.musicPlayingSlideView.PlayingSlideAnimView;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes5.dex */
public class ItemDabStationBindingImpl extends ItemDabStationBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.collectLottie, 6);
    }

    public ItemDabStationBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private ItemDabStationBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, (ImageButton) objArr[5], (ImageView) objArr[3], (CollectLottieView) objArr[6], (ShapeableImageView) objArr[1], (PlayingSlideAnimView) objArr[4], (TextView) objArr[2]);
        this.mDirtyFlags = -1L;
        this.btnCollect.setTag(null);
        this.clock.setTag(null);
        this.ivStationImg.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.playingSlide.setTag(null);
        this.tvDabTitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128L;
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
        if (BR.isCollect == i) {
            setIsCollect(((Boolean) obj).booleanValue());
        } else if (BR.viewModel == i) {
            setViewModel((DABViewModel) obj);
        } else {
            if (BR.programInfo != i) {
                return false;
            }
            setProgramInfo((RadioDBBean) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.ItemDabStationBinding
    public void setIsCollect(boolean z) {
        this.mIsCollect = z;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.isCollect);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemDabStationBinding
    public void setViewModel(DABViewModel dABViewModel) {
        this.mViewModel = dABViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemDabStationBinding
    public void setProgramInfo(RadioDBBean radioDBBean) {
        this.mProgramInfo = radioDBBean;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.programInfo);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeViewModelCurrentDabSID((ObservableLong) obj, i2);
        }
        if (i == 1) {
            return onChangeViewModelDabCollectList((ObservableArrayList) obj, i2);
        }
        if (i == 2) {
            return onChangeRadioServiceWrapperGetInstanceGetDABPlaying((ObservableBoolean) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeRadioServiceWrapperGetInstanceRadioSubscribeEpgList((ObservableArrayList) obj, i2);
    }

    private boolean onChangeViewModelCurrentDabSID(ObservableLong observableLong, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelDabCollectList(ObservableArrayList<RadioDBBean> observableArrayList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeRadioServiceWrapperGetInstanceGetDABPlaying(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeRadioServiceWrapperGetInstanceRadioSubscribeEpgList(ObservableArrayList<RadioEpgDBBean> observableArrayList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0163  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.ItemDabStationBindingImpl.executeBindings():void");
    }
}
