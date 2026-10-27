package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.byd.music.fragment.radio.FMViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.RulerView;
import com.byd.viewsup.BydScrollerSupportedFrameLayout;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentFmBindingImpl extends FragmentFmBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final TextView mboundView15;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rootContainer, 16);
        sViewsWithIds.put(R.id.groupPlayingCard, 17);
        sViewsWithIds.put(R.id.topSetting, 18);
        sViewsWithIds.put(R.id.topMirror, 19);
        sViewsWithIds.put(R.id.topSearch, 20);
        sViewsWithIds.put(R.id.RDSFreq, 21);
        sViewsWithIds.put(R.id.RDSPTY, 22);
        sViewsWithIds.put(R.id.rulerView, 23);
        sViewsWithIds.put(R.id.btnPre, 24);
        sViewsWithIds.put(R.id.btnNext, 25);
        sViewsWithIds.put(R.id.collectLottie, 26);
        sViewsWithIds.put(R.id.groupProgramList, 27);
        sViewsWithIds.put(R.id.rbgProgramList, 28);
        sViewsWithIds.put(R.id.rbChannel, 29);
        sViewsWithIds.put(R.id.rbCollect, 30);
    }

    public FragmentFmBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 31, sIncludes, sViewsWithIds));
    }

    private FragmentFmBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 8, (LinearLayout) objArr[4], (TextView) objArr[21], (TextView) objArr[3], (TextView) objArr[22], (TextView) objArr[5], (ImageButton) objArr[6], (ImageButton) objArr[25], (ImageButton) objArr[8], (ImageButton) objArr[24], (ImageButton) objArr[9], (ImageButton) objArr[7], (BydScrollerSupportedFrameLayout) objArr[10], (BydScrollerSupportedFrameLayout) objArr[12], (CollectLottieView) objArr[26], (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[27], (ImageView) objArr[14], (NestedScrollView) objArr[0], (TextView) objArr[2], (TextView) objArr[1], (RadioButton) objArr[29], (RadioButton) objArr[30], (RadioGroup) objArr[28], (ConstraintLayout) objArr[16], (RulerView) objArr[23], (RecyclerView) objArr[11], (RecyclerView) objArr[13], (ImageView) objArr[19], (ImageButton) objArr[20], (ImageButton) objArr[18]);
        this.mDirtyFlags = -1L;
        this.RDSContainer.setTag(null);
        this.RDSPS.setTag(null);
        this.RDSRT.setTag(null);
        this.btnCollect.setTag(null);
        this.btnPlayOrPause.setTag(null);
        this.btnSearchNext.setTag(null);
        this.btnSearchPre.setTag(null);
        this.channelListContainer.setTag(null);
        this.collectListContainer.setTag(null);
        this.ivNoData.setTag(null);
        TextView textView = (TextView) objArr[15];
        this.mboundView15 = textView;
        textView.setTag(null);
        this.nestedScrollView.setTag(null);
        this.programFreqUnit.setTag(null);
        this.programFrequency.setTag(null);
        this.rvChannelList.setTag(null);
        this.rvCollectList.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 512L;
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
        setViewModel((FMViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.FragmentFmBinding
    public void setViewModel(FMViewModel fMViewModel) {
        this.mViewModel = fMViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewModelFmCollectList((ObservableArrayList) obj, i2);
            case 1:
                return onChangeViewModelIsCollectShow((ObservableBoolean) obj, i2);
            case 2:
                return onChangeViewModelFmChannelSelectedPosition((ObservableInt) obj, i2);
            case 3:
                return onChangeRadioServiceWrapperGetInstanceGetFMPlaying((ObservableBoolean) obj, i2);
            case 4:
                return onChangeRadioServiceWrapperGetInstanceMRadioType((ObservableInt) obj, i2);
            case 5:
                return onChangeViewModelCurrentFrequency((ObservableInt) obj, i2);
            case 6:
                return onChangeViewModelFmChannelList((ObservableArrayList) obj, i2);
            case 7:
                return onChangeViewModelIsPlayingCollect((ObservableBoolean) obj, i2);
            default:
                return false;
        }
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

    private boolean onChangeViewModelIsCollectShow(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelFmChannelSelectedPosition(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeRadioServiceWrapperGetInstanceGetFMPlaying(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeRadioServiceWrapperGetInstanceMRadioType(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelCurrentFrequency(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelFmChannelList(ObservableArrayList<RadioDBBean> observableArrayList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelIsPlayingCollect(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0124  */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v7 */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 799
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.FragmentFmBindingImpl.executeBindings():void");
    }
}
