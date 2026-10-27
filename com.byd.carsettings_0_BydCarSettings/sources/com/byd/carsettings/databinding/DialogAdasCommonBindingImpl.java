package com.byd.carsettings.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import b.b.g.i.a.a;
import com.byd.carsettings.R;
import com.byd.dipilot.viewmodel.NoConfigDialogModel;
import com.byd.dipilot.widget.CountDownButton;
import com.byd.dipilot.widget.MaxHeightLinearLayout;
import okio.SegmentPool;

/* JADX INFO: loaded from: classes2.dex */
public class DialogAdasCommonBindingImpl extends DialogAdasCommonBinding implements a.InterfaceC0046a {

    @Nullable
    public static final ViewDataBinding.IncludedLayouts k = null;

    @Nullable
    public static final SparseIntArray l;

    @Nullable
    public final View.OnClickListener m;
    public long n;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.ll_content, 8);
    }

    public DialogAdasCommonBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 9, k, l));
    }

    @Override // b.b.g.i.a.a.InterfaceC0046a
    public final void a(int i, View view) {
        NoConfigDialogModel noConfigDialogModel = this.j;
        if (noConfigDialogModel != null) {
            noConfigDialogModel.n(false);
        }
    }

    @Override // com.byd.carsettings.databinding.DialogAdasCommonBinding
    public void c(@Nullable NoConfigDialogModel noConfigDialogModel) {
        this.j = noConfigDialogModel;
        synchronized (this) {
            this.n |= 1;
        }
        notifyPropertyChanged(5);
        super.requestRebind();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9, types: [int] */
    /* JADX WARN: Type inference failed for: r4v5, types: [android.widget.LinearLayout] */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        Drawable drawable;
        int iH;
        int i;
        int i2;
        boolean z;
        int colorFromResource;
        int i3;
        boolean zL;
        boolean zM;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int i4;
        byte b2;
        int i5;
        boolean z6;
        int i6;
        int i7;
        boolean z7;
        int i8;
        boolean zD;
        int i9;
        String str;
        String str2;
        int i10;
        ?? r12;
        int iG;
        int iC;
        boolean zK;
        byte bD;
        int iB;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        synchronized (this) {
            j = this.n;
            this.n = 0L;
        }
        NoConfigDialogModel noConfigDialogModel = this.j;
        long j8 = j & 3;
        if (j8 != 0) {
            if (noConfigDialogModel != null) {
                zL = noConfigDialogModel.l();
                iG = noConfigDialogModel.g();
                iC = noConfigDialogModel.c();
                zM = noConfigDialogModel.m();
                zK = noConfigDialogModel.k();
                bD = noConfigDialogModel.d();
                iB = noConfigDialogModel.b();
                iH = noConfigDialogModel.h();
            } else {
                iH = 0;
                zL = false;
                iG = 0;
                iC = 0;
                zM = false;
                zK = false;
                bD = 0;
                iB = 0;
            }
            if (j8 != 0) {
                j |= zL ? 8192L : 4096L;
            }
            if ((j & 3) != 0) {
                j |= zM ? 32768L : 16384L;
            }
            if ((j & 3) != 0) {
                j |= zK ? 33554432L : 16777216L;
            }
            z = iG > 0;
            z2 = iG == 0;
            z3 = iC > 0;
            int i11 = zK ? 0 : 8;
            boolean z8 = bD == 0;
            boolean z9 = bD > 0;
            z4 = iH > 0;
            z5 = iH == 0;
            if ((j & 3) != 0) {
                if (z) {
                    j6 = j | 128;
                    j7 = 2097152;
                } else {
                    j6 = j | 64;
                    j7 = 1048576;
                }
                j = j6 | j7;
            }
            if ((j & 3) != 0) {
                j |= z2 ? 524288L : 262144L;
            }
            if ((j & 3) != 0) {
                if (z3) {
                    j4 = j | 32;
                    j5 = 8589934592L;
                } else {
                    j4 = j | 16;
                    j5 = 4294967296L;
                }
                j = j4 | j5;
            }
            if ((j & 3) != 0) {
                if (z9) {
                    j2 = j | 131072;
                    j3 = 134217728;
                } else {
                    j2 = j | SegmentPool.MAX_SIZE;
                    j3 = 67108864;
                }
                j = j2 | j3;
            }
            if ((j & 3) != 0) {
                j = z4 ? j | 2048 : j | 1024;
            }
            if ((j & 3) != 0) {
                j |= z5 ? 512L : 256L;
            }
            int i12 = z ? 0 : 8;
            int i13 = z3 ? 0 : 8;
            colorFromResource = z9 ? ViewDataBinding.getColorFromResource(this.f2797b, R.color.dialog_cancle_text_color) : ViewDataBinding.getColorFromResource(this.f2797b, R.color.dialog_btn_text_white_selector);
            drawable = AppCompatResources.getDrawable(this.f2797b.getContext(), z9 ? R.drawable.selector_dialog_white : R.drawable.selector_dialog_blue);
            i3 = z5 ? 8 : 0;
            i = iG;
            i2 = iC;
            i4 = i11;
            b2 = bD;
            i5 = iB;
            z6 = z8;
            i6 = i12;
            i7 = i13;
        } else {
            drawable = null;
            iH = 0;
            i = 0;
            i2 = 0;
            z = false;
            colorFromResource = 0;
            i3 = 0;
            zL = false;
            zM = false;
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
            i4 = 0;
            b2 = 0;
            i5 = 0;
            z6 = false;
            i6 = 0;
            i7 = 0;
        }
        long j9 = j & 2;
        if (j9 != 0) {
            z7 = b.b.a.i.a.f531a;
            if (j9 != 0) {
                j |= z7 ? 536870912L : 268435456L;
            }
        } else {
            z7 = false;
        }
        String string = (128 & j) != 0 ? getRoot().getContext().getString(i) : null;
        String string2 = (2048 & j) != 0 ? getRoot().getContext().getString(iH) : null;
        long j10 = j & 3;
        if (j10 != 0) {
            if (!z2) {
                z5 = false;
            }
            if (j10 != 0) {
                j |= z5 ? 8388608L : 4194304L;
            }
            i8 = z5 ? 8 : 0;
        } else {
            i8 = 0;
        }
        String string3 = (32 & j) != 0 ? getRoot().getContext().getString(i2) : null;
        if ((268455936 & j) != 0) {
            zD = b.b.a.i.a.d();
            if ((j & 20480) != 0) {
                j |= zD ? 2147483648L : 1073741824L;
            }
            i9 = (20480 & j) != 0 ? zD ? 5 : 3 : 0;
        } else {
            zD = false;
            i9 = 0;
        }
        if ((j & 3) != 0) {
            if (!z3) {
                string3 = "";
            }
            String str3 = string3;
            if (!z) {
                string = "";
            }
            if (!z4) {
                string2 = "";
            }
            i10 = zL ? 1 : i9;
            if (zM) {
                i9 = 1;
            }
            str2 = string2;
            str = str3;
        } else {
            str = null;
            string = null;
            str2 = null;
            i10 = 0;
            i9 = 0;
        }
        long j11 = j & 2;
        if (j11 != 0) {
            boolean z10 = z7 ? true : zD;
            if (j11 != 0) {
                j |= z10 ? 8L : 4L;
            }
            r12 = z10;
        } else {
            r12 = 0;
        }
        if ((j & 2) != 0) {
            this.f2796a.setOnClickListener(this.m);
            if (ViewDataBinding.getBuildSdkInt() >= 17) {
                this.f2798c.setLayoutDirection(r12);
            }
        }
        if ((j & 3) != 0) {
            this.f2796a.setVisibility(i4);
            ViewBindingAdapter.setBackground(this.f2797b, drawable);
            this.f2797b.setClickable(z6);
            this.f2797b.setTextColor(colorFromResource);
            this.f2797b.setDuration(b2);
            this.f2797b.setText(i5);
            TextViewBindingAdapter.setText(this.f2801f, str);
            this.f2801f.setVisibility(i7);
            this.f2801f.setGravity(i10);
            TextViewBindingAdapter.setText(this.g, string);
            this.g.setVisibility(i6);
            this.g.setGravity(i9);
            TextViewBindingAdapter.setText(this.h, str2);
            this.h.setVisibility(i3);
            this.i.setVisibility(i8);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.n != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.n = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (5 != i) {
            return false;
        }
        c((NoConfigDialogModel) obj);
        return true;
    }

    public DialogAdasCommonBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[7], (CountDownButton) objArr[6], (LinearLayout) objArr[5], (LinearLayout) objArr[8], (MaxHeightLinearLayout) objArr[0], (TextView) objArr[3], (TextView) objArr[4], (TextView) objArr[1], (View) objArr[2]);
        this.n = -1L;
        this.f2796a.setTag(null);
        this.f2797b.setTag(null);
        this.f2798c.setTag(null);
        this.f2800e.setTag(null);
        this.f2801f.setTag(null);
        this.g.setTag(null);
        this.h.setTag(null);
        this.i.setTag(null);
        setRootTag(view);
        this.m = new a(this, 1);
        invalidateAll();
    }
}
