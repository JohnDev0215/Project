package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.fragment.radio.AMViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.RulerView;
import com.byd.viewsup.BydScrollerSupportedFrameLayout;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentAmBindingImpl extends FragmentAmBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final TextView mboundView11;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rootContainer, 12);
        sViewsWithIds.put(R.id.groupPlayingCard, 13);
        sViewsWithIds.put(R.id.topMirror, 14);
        sViewsWithIds.put(R.id.topSearch, 15);
        sViewsWithIds.put(R.id.programFreqUnit, 16);
        sViewsWithIds.put(R.id.rulerView, 17);
        sViewsWithIds.put(R.id.btnPre, 18);
        sViewsWithIds.put(R.id.btnNext, 19);
        sViewsWithIds.put(R.id.collectLottie, 20);
        sViewsWithIds.put(R.id.groupProgramList, 21);
        sViewsWithIds.put(R.id.rbgProgramList, 22);
        sViewsWithIds.put(R.id.rbChannel, 23);
        sViewsWithIds.put(R.id.rbCollect, 24);
    }

    public FragmentAmBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 25, sIncludes, sViewsWithIds));
    }

    private FragmentAmBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 7, (ImageButton) objArr[2], (ImageButton) objArr[19], (ImageButton) objArr[4], (ImageButton) objArr[18], (ImageButton) objArr[5], (ImageButton) objArr[3], (BydScrollerSupportedFrameLayout) objArr[6], (BydScrollerSupportedFrameLayout) objArr[8], (CollectLottieView) objArr[20], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[21], (ImageView) objArr[10], (TextView) objArr[16], (TextView) objArr[1], (RadioButton) objArr[23], (RadioButton) objArr[24], (RadioGroup) objArr[22], (ConstraintLayout) objArr[12], (RulerView) objArr[17], (RecyclerView) objArr[7], (RecyclerView) objArr[9], (ImageView) objArr[14], (ImageButton) objArr[15]);
        this.mDirtyFlags = -1L;
        this.btnCollect.setTag(null);
        this.btnPlayOrPause.setTag(null);
        this.btnSearchNext.setTag(null);
        this.btnSearchPre.setTag(null);
        this.channelListContainer.setTag(null);
        this.collectListContainer.setTag(null);
        this.ivNoData.setTag(null);
        NestedScrollView nestedScrollView = (NestedScrollView) objArr[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
        TextView textView = (TextView) objArr[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        this.programFrequency.setTag(null);
        this.rvChannelList.setTag(null);
        this.rvCollectList.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256L;
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
        setViewModel((AMViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.FragmentAmBinding
    public void setViewModel(AMViewModel aMViewModel) {
        this.mViewModel = aMViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewModelAmCollectList((ObservableArrayList) obj, i2);
            case 1:
                return onChangeViewModelIsCollectShow((ObservableBoolean) obj, i2);
            case 2:
                return onChangeViewModelCurrentFrequency((ObservableInt) obj, i2);
            case 3:
                return onChangeViewModelAmChannelSelectedPosition((ObservableInt) obj, i2);
            case 4:
                return onChangeViewModelAmChannelList((ObservableArrayList) obj, i2);
            case 5:
                return onChangeViewModelIsPlayingCollect((ObservableBoolean) obj, i2);
            case 6:
                return onChangeRadioServiceWrapperGetInstanceGetAMPlaying((ObservableBoolean) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelAmCollectList(ObservableArrayList<RadioDBBean> observableArrayList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelIsCollectShow(ObservableBoolean observableBoolean, int i) {
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

    private boolean onChangeViewModelAmChannelSelectedPosition(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelAmChannelList(ObservableArrayList<RadioDBBean> observableArrayList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelIsPlayingCollect(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeRadioServiceWrapperGetInstanceGetAMPlaying(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x012f  */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7, types: [boolean, int] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 710
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.FragmentAmBindingImpl.executeBindings():void");
    }
}
