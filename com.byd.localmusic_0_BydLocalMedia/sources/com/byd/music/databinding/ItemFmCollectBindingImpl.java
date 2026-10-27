package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableLong;
import androidx.databinding.ViewDataBinding;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.fragment.radio.FMViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.musicPlayingSlideView.PlayingSlideAnimView;

/* JADX INFO: loaded from: classes5.dex */
public class ItemFmCollectBindingImpl extends ItemFmCollectBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.emptyView, 6);
        sViewsWithIds.put(R.id.collectLottie, 7);
    }

    public ItemFmCollectBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private ItemFmCollectBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, (ImageButton) objArr[5], (CollectLottieView) objArr[7], (View) objArr[6], (PlayingSlideAnimView) objArr[4], (TextView) objArr[1], (TextView) objArr[2], (TextView) objArr[3]);
        this.mDirtyFlags = -1L;
        this.btnRemove2.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.playingSlideAnimView.setTag(null);
        this.tvAlignName.setTag(null);
        this.tvRadioName.setTag(null);
        this.tvRadioType.setTag(null);
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
        if (BR.fmCardBean == i) {
            setFmCardBean((RadioDBBean) obj);
        } else if (BR.isCollect == i) {
            setIsCollect(((Boolean) obj).booleanValue());
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((FMViewModel) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.ItemFmCollectBinding
    public void setFmCardBean(RadioDBBean radioDBBean) {
        this.mFmCardBean = radioDBBean;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.fmCardBean);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemFmCollectBinding
    public void setIsCollect(boolean z) {
        this.mIsCollect = z;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.isCollect);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemFmCollectBinding
    public void setViewModel(FMViewModel fMViewModel) {
        this.mViewModel = fMViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeViewModelFmCollectList((ObservableArrayList) obj, i2);
        }
        if (i == 1) {
            return onChangeRadioServiceWrapperGetInstanceGetFMPlaying((ObservableBoolean) obj, i2);
        }
        if (i == 2) {
            return onChangeViewModelCurrentFrequency((ObservableInt) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeViewModelCurrentPI((ObservableLong) obj, i2);
    }

    private boolean onChangeViewModelFmCollectList(ObservableArrayList<RadioDBBean> observableArrayList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeRadioServiceWrapperGetInstanceGetFMPlaying(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelCurrentFrequency(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelCurrentPI(ObservableLong observableLong, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 403
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.ItemFmCollectBindingImpl.executeBindings():void");
    }
}
