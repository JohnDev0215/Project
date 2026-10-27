package com.facebook.react.views.switchview;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

/* JADX INFO: loaded from: classes6.dex */
class ReactSwitch extends SwitchCompat {
    private boolean mAllowChange;

    @Nullable
    private Integer mTrackColorForFalse;

    @Nullable
    private Integer mTrackColorForTrue;

    public ReactSwitch(Context context) {
        super(context);
        this.mAllowChange = true;
        this.mTrackColorForFalse = null;
        this.mTrackColorForTrue = null;
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.mAllowChange && isChecked() != z) {
            this.mAllowChange = false;
            super.setChecked(z);
            setTrackColor(z);
            return;
        }
        super.setChecked(isChecked());
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        setBackground(new RippleDrawable(createRippleDrawableColorStateList(Integer.valueOf(i)), new ColorDrawable(i), null));
    }

    void setColor(Drawable drawable, @Nullable Integer num) {
        if (num == null) {
            drawable.clearColorFilter();
        } else {
            drawable.setColorFilter(num.intValue(), PorterDuff.Mode.MULTIPLY);
        }
    }

    public void setTrackColor(@Nullable Integer num) {
        setColor(super.getTrackDrawable(), num);
    }

    public void setThumbColor(@Nullable Integer num) {
        setColor(super.getThumbDrawable(), num);
        if (num == null || !(super.getBackground() instanceof RippleDrawable)) {
            return;
        }
        ((RippleDrawable) super.getBackground()).setColor(createRippleDrawableColorStateList(num));
    }

    void setOn(boolean z) {
        if (isChecked() != z) {
            super.setChecked(z);
            setTrackColor(z);
        }
        this.mAllowChange = true;
    }

    public void setTrackColorForTrue(@Nullable Integer num) {
        if (num == this.mTrackColorForTrue) {
            return;
        }
        this.mTrackColorForTrue = num;
        if (isChecked()) {
            setTrackColor(this.mTrackColorForTrue);
        }
    }

    public void setTrackColorForFalse(@Nullable Integer num) {
        if (num == this.mTrackColorForFalse) {
            return;
        }
        this.mTrackColorForFalse = num;
        if (isChecked()) {
            return;
        }
        setTrackColor(this.mTrackColorForFalse);
    }

    private void setTrackColor(boolean z) {
        Integer num = this.mTrackColorForTrue;
        if (num == null && this.mTrackColorForFalse == null) {
            return;
        }
        if (!z) {
            num = this.mTrackColorForFalse;
        }
        setTrackColor(num);
    }

    private ColorStateList createRippleDrawableColorStateList(@Nullable Integer num) {
        return new ColorStateList(new int[][]{new int[]{R.attr.state_pressed}}, new int[]{num.intValue()});
    }
}
