package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableLong;
import androidx.databinding.ViewDataBinding;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.fragment.bluetooth.BluetoothViewModel;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentMusicBluetoothBindingImpl extends FragmentMusicBluetoothBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final ConstraintLayout mboundView11;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.cl_progress, 18);
        sViewsWithIds.put(R.id.clControl, 19);
        sViewsWithIds.put(R.id.bt_radio_play_pre, 20);
        sViewsWithIds.put(R.id.bt_radio_play_next, 21);
        sViewsWithIds.put(R.id.ll_bluetooth, 22);
        sViewsWithIds.put(R.id.iv_no_connect, 23);
        sViewsWithIds.put(R.id.tv_no_connect, 24);
        sViewsWithIds.put(R.id.androidauto, 25);
        sViewsWithIds.put(R.id.auto_img_tow, 26);
        sViewsWithIds.put(R.id.auto_text_phone, 27);
    }

    public FragmentMusicBluetoothBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 28, sIncludes, sViewsWithIds));
    }

    private FragmentMusicBluetoothBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 13, (ConstraintLayout) objArr[25], (ImageView) objArr[26], (TextView) objArr[27], (ImageView) objArr[12], (ImageButton) objArr[10], (Button) objArr[21], (Button) objArr[20], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[16], (ConstraintLayout) objArr[18], (ImageView) objArr[13], (ShapeableImageView) objArr[15], (ShapeableImageView) objArr[6], (ImageView) objArr[23], (ImageView) objArr[1], (LinearLayout) objArr[3], (LinearLayout) objArr[22], (SeekBar) objArr[9], (TextView) objArr[14], (TextView) objArr[4], (TextView) objArr[24], (TextView) objArr[7], (TextView) objArr[8], (TextView) objArr[5], (TextView) objArr[17]);
        this.mDirtyFlags = -1L;
        this.btBluetooth.setTag(null);
        this.btRadioPlay.setTag(null);
        this.clContent.setTag(null);
        this.clNoConnect.setTag(null);
        this.imageView2.setTag(null);
        this.ivIconAblum.setTag(null);
        this.ivIconAlbumPort.setTag(null);
        this.ivVoice.setTag(null);
        this.llBlueContent.setTag(null);
        NestedScrollView nestedScrollView = (NestedScrollView) objArr[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[11];
        this.mboundView11 = constraintLayout;
        constraintLayout.setTag(null);
        this.seekBar.setTag(null);
        this.textView.setTag(null);
        this.tvMusicName.setTag(null);
        this.tvRadioCurrentTime.setTag(null);
        this.tvRadioTotalTime.setTag(null);
        this.tvSinger.setTag(null);
        this.tvToConnect.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16384L;
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
        setViewModel((BluetoothViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.FragmentMusicBluetoothBinding
    public void setViewModel(BluetoothViewModel bluetoothViewModel) {
        this.mViewModel = bluetoothViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8192;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewModelArtist((ObservableField) obj, i2);
            case 1:
                return onChangeViewModelTitle((ObservableField) obj, i2);
            case 2:
                return onChangeViewModelIsMultiMode((ObservableBoolean) obj, i2);
            case 3:
                return onChangeViewModelIsRight((ObservableBoolean) obj, i2);
            case 4:
                return onChangeViewModelTotalTime((ObservableLong) obj, i2);
            case 5:
                return onChangeViewModelConnectedPhoneName((ObservableField) obj, i2);
            case 6:
                return onChangeViewModelIsA2DPConnected((ObservableBoolean) obj, i2);
            case 7:
                return onChangeViewModelIconBattery((ObservableInt) obj, i2);
            case 8:
                return onChangeViewModelCurrentTime((ObservableLong) obj, i2);
            case 9:
                return onChangeViewModelPlayOrPause((ObservableBoolean) obj, i2);
            case 10:
                return onChangeViewModelImageUrl((ObservableField) obj, i2);
            case 11:
                return onChangeViewModelIsPortraitMode((ObservableBoolean) obj, i2);
            case 12:
                return onChangeViewModelIsHFPConnected((ObservableBoolean) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelArtist(ObservableField<String> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelTitle(ObservableField<String> observableField, int i) {
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

    private boolean onChangeViewModelIsRight(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelTotalTime(ObservableLong observableLong, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelConnectedPhoneName(ObservableField<String> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelIsA2DPConnected(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelIconBattery(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelCurrentTime(ObservableLong observableLong, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelPlayOrPause(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeViewModelImageUrl(ObservableField<String> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        return true;
    }

    private boolean onChangeViewModelIsPortraitMode(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2048;
        }
        return true;
    }

    private boolean onChangeViewModelIsHFPConnected(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4096;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0406  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x043d  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0469  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0477  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0485  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x04a1  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x04af  */
    /* JADX WARN: Removed duplicated region for block: B:284:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x013d  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 1210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.FragmentMusicBluetoothBindingImpl.executeBindings():void");
    }
}
