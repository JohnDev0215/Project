package com.byd.carsettings.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import b.b.a.h.d;
import b.b.k.b.a;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.dipilot.model.DriverAssistModel;
import com.byd.dipilot.viewmodel.BaseAdasViewModel;

/* JADX INFO: loaded from: classes2.dex */
public class ItemDipilotAssistBindingLdrtlPortImpl extends ItemDipilotAssistBinding {

    @Nullable
    public static final ViewDataBinding.IncludedLayouts g = null;

    @Nullable
    public static final SparseIntArray h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        h = sparseIntArray;
        sparseIntArray.put(R.id.iv_assist_notice, 3);
        sparseIntArray.put(R.id.iv_assist, 4);
    }

    public ItemDipilotAssistBindingLdrtlPortImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, g, h));
    }

    @Override // com.byd.carsettings.databinding.ItemDipilotAssistBinding
    public void b(@Nullable DriverAssistModel driverAssistModel) {
        updateRegistration(0, driverAssistModel);
        this.f2836f = driverAssistModel;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(5);
        super.requestRebind();
    }

    public final boolean c(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        if (i == 10) {
            synchronized (this) {
                this.i |= 2;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.i |= 4;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.i |= 8;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.i |= 16;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.i |= 32;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        int i;
        synchronized (this) {
            j = this.i;
            this.i = 0L;
        }
        DriverAssistModel driverAssistModel = this.f2836f;
        float f2 = 0.0f;
        if ((127 & j) != 0) {
            boolean enable = ((j & 81) == 0 || driverAssistModel == null) ? false : driverAssistModel.getEnable();
            boolean assistState = ((j & 73) == 0 || driverAssistModel == null) ? false : driverAssistModel.getAssistState();
            int tag = ((j & 67) == 0 || driverAssistModel == null) ? 0 : driverAssistModel.getTag();
            long j2 = j & 69;
            if (j2 != 0) {
                boolean zIsAlpha = driverAssistModel != null ? driverAssistModel.isAlpha() : false;
                if (j2 != 0) {
                    j |= zIsAlpha ? 1024L : 512L;
                }
                f2 = zIsAlpha ? 1.0f : 0.45f;
            }
            long j3 = j & 97;
            if (j3 != 0) {
                zIsTouch = driverAssistModel != null ? driverAssistModel.isTouch() : false;
                if (j3 != 0) {
                    j = zIsTouch ? j | 256 : j | 128;
                }
            }
            z2 = enable;
            z = assistState;
            i = tag;
        } else {
            z = false;
            z2 = false;
            i = 0;
        }
        BaseAdasViewModel<? extends DriverAssistModel, ? extends a> baseAdasViewModel = null;
        BaseAdasViewModel<? extends DriverAssistModel, ? extends a> vm = ((j & 256) == 0 || driverAssistModel == null) ? null : driverAssistModel.getVm();
        long j4 = 97 & j;
        if (j4 != 0 && zIsTouch) {
            baseAdasViewModel = vm;
        }
        BaseAdasViewModel<? extends DriverAssistModel, ? extends a> baseAdasViewModel2 = baseAdasViewModel;
        if ((69 & j) != 0 && ViewDataBinding.getBuildSdkInt() >= 11) {
            this.f2834d.setAlpha(f2);
        }
        if ((j & 73) != 0) {
            d.a(this.f2834d, z);
        }
        if ((j & 81) != 0) {
            this.f2834d.setEnabled(z2);
        }
        if (j4 != 0) {
            this.f2834d.setOnInterceptTouchListener(baseAdasViewModel2);
        }
        if ((j & 67) != 0) {
            this.f2835e.setText(i);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.i != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 64L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return c((DriverAssistModel) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (5 != i) {
            return false;
        }
        b((DriverAssistModel) obj);
        return true;
    }

    public ItemDipilotAssistBindingLdrtlPortImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ConstraintLayout) objArr[0], (LottieAnimationView) objArr[4], (ImageView) objArr[3], (BydSwitch) objArr[2], (TextView) objArr[1]);
        this.i = -1L;
        this.f2831a.setTag(null);
        this.f2834d.setTag(null);
        this.f2835e.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
