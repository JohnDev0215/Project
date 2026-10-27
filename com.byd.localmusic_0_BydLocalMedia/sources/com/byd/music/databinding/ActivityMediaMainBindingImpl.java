package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.activity.main.MediaMainViewModel;
import com.byd.music.fragment.bluetooth.BluetoothViewModel;
import com.byd.viewsup.BydScrollerSupportedFrameLayout;

/* JADX INFO: loaded from: classes5.dex */
public class ActivityMediaMainBindingImpl extends ActivityMediaMainBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ImageView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.horizontalSourceListContainer, 9);
    }

    public ActivityMediaMainBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    private ActivityMediaMainBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 8, (ImageView) objArr[5], (ConstraintLayout) objArr[4], (RecyclerView) objArr[1], (BydScrollerSupportedFrameLayout) objArr[9], (ConstraintLayout) objArr[0], (TextView) objArr[7], (RecyclerView) objArr[3], (BydScrollerSupportedFrameLayout) objArr[2], (ViewPager2) objArr[8]);
        this.mDirtyFlags = -1L;
        this.btBluetooth.setTag(null);
        this.btMusicBattery.setTag(null);
        this.horizontalSourceList.setTag(null);
        this.mainConstraint.setTag(null);
        ImageView imageView = (ImageView) objArr[6];
        this.mboundView6 = imageView;
        imageView.setTag(null);
        this.textView.setTag(null);
        this.verticalSourceList.setTag(null);
        this.verticalSourceListContainer.setTag(null);
        this.viewPager.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1024L;
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
        if (BR.vm == i) {
            setVm((BluetoothViewModel) obj);
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((MediaMainViewModel) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.ActivityMediaMainBinding
    public void setVm(BluetoothViewModel bluetoothViewModel) {
        this.mVm = bluetoothViewModel;
    }

    @Override // com.byd.music.databinding.ActivityMediaMainBinding
    public void setViewModel(MediaMainViewModel mediaMainViewModel) {
        this.mViewModel = mediaMainViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeVmIconBattery((ObservableInt) obj, i2);
            case 1:
                return onChangeVmConnectedPhoneName((ObservableField) obj, i2);
            case 2:
                return onChangeViewModelIsMultiMode((ObservableBoolean) obj, i2);
            case 3:
                return onChangeVmIsHFPConnected((ObservableBoolean) obj, i2);
            case 4:
                return onChangeViewModelIsRight((ObservableBoolean) obj, i2);
            case 5:
                return onChangeViewModelIsBluetoothPage((ObservableBoolean) obj, i2);
            case 6:
                return onChangeVmIsA2DPConnected((ObservableBoolean) obj, i2);
            case 7:
                return onChangeViewModelIsPortraitMode((ObservableBoolean) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeVmIconBattery(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmConnectedPhoneName(ObservableField<String> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelIsMultiMode(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVmIsHFPConnected(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelIsRight(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelIsBluetoothPage(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeVmIsA2DPConnected(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelIsPortraitMode(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0087  */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r31v3 */
    /* JADX WARN: Type inference failed for: r34v0 */
    /* JADX WARN: Type inference failed for: r37v0, types: [com.byd.music.databinding.ActivityMediaMainBindingImpl] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v9 */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.ActivityMediaMainBindingImpl.executeBindings():void");
    }
}
