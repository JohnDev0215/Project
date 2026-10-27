package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
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
import com.byd.music.fragment.radio.DABViewModel;
import com.byd.viewsup.BydScrollerSupportedFrameLayout;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentDabBindingImpl extends FragmentDabBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final TextView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rootContainer, 7);
        sViewsWithIds.put(R.id.groupDabList, 8);
        sViewsWithIds.put(R.id.rbgProgramList, 9);
        sViewsWithIds.put(R.id.rbChannel, 10);
        sViewsWithIds.put(R.id.rbCollect, 11);
        sViewsWithIds.put(R.id.topSearch, 12);
        sViewsWithIds.put(R.id.topSetting, 13);
        sViewsWithIds.put(R.id.epg, 14);
        sViewsWithIds.put(R.id.groupPlayingDabCard, 15);
        sViewsWithIds.put(R.id.dab_pic, 16);
        sViewsWithIds.put(R.id.dab_service, 17);
        sViewsWithIds.put(R.id.dab_ensemble, 18);
        sViewsWithIds.put(R.id.dab_pty, 19);
        sViewsWithIds.put(R.id.dab_dls, 20);
        sViewsWithIds.put(R.id.btn_tune_dab, 21);
        sViewsWithIds.put(R.id.btn_update_list, 22);
        sViewsWithIds.put(R.id.testbtn, 23);
    }

    public FragmentDabBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 24, sIncludes, sViewsWithIds));
    }

    private FragmentDabBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, (Button) objArr[21], (Button) objArr[22], (BydScrollerSupportedFrameLayout) objArr[1], (BydScrollerSupportedFrameLayout) objArr[3], (TextView) objArr[20], (TextView) objArr[18], (ShapeableImageView) objArr[16], (TextView) objArr[19], (TextView) objArr[17], (Button) objArr[14], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[15], (ImageView) objArr[5], (RadioButton) objArr[10], (RadioButton) objArr[11], (RadioGroup) objArr[9], (ConstraintLayout) objArr[7], (RecyclerView) objArr[2], (RecyclerView) objArr[4], (Button) objArr[23], (ImageButton) objArr[12], (ImageButton) objArr[13]);
        this.mDirtyFlags = -1L;
        this.channelListContainer.setTag(null);
        this.collectListContainer.setTag(null);
        this.ivNoData.setTag(null);
        NestedScrollView nestedScrollView = (NestedScrollView) objArr[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
        TextView textView = (TextView) objArr[6];
        this.mboundView6 = textView;
        textView.setTag(null);
        this.rvChannelList.setTag(null);
        this.rvCollectList.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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
        if (BR.isCollectShow == i) {
            setIsCollectShow((ObservableBoolean) obj);
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((DABViewModel) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.FragmentDabBinding
    public void setIsCollectShow(ObservableBoolean observableBoolean) {
        updateRegistration(0, observableBoolean);
        this.mIsCollectShow = observableBoolean;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.isCollectShow);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.FragmentDabBinding
    public void setViewModel(DABViewModel dABViewModel) {
        this.mViewModel = dABViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeIsCollectShow((ObservableBoolean) obj, i2);
        }
        if (i == 1) {
            return onChangeViewModelDabCollectList((ObservableArrayList) obj, i2);
        }
        if (i == 2) {
            return onChangeViewModelDabSelectedPosition((ObservableInt) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeViewModelDabChannelList((ObservableArrayList) obj, i2);
    }

    private boolean onChangeIsCollectShow(ObservableBoolean observableBoolean, int i) {
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

    private boolean onChangeViewModelDabSelectedPosition(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelDabChannelList(ObservableArrayList<RadioDBBean> observableArrayList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:111:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0137  */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v6 */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.FragmentDabBindingImpl.executeBindings():void");
    }
}
