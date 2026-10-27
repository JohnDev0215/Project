package com.byd.secretcode.view;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.byd.secretcode.R;
import defpackage.e7;
import defpackage.wg;

/* JADX INFO: loaded from: classes.dex */
public class BydSwitch extends Switch {
    public CompoundButton.OnCheckedChangeListener b;
    public a c;
    public boolean d;
    public long e;
    public volatile boolean f;
    public boolean g;

    public interface a {
        void a(View view, MotionEvent motionEvent, boolean z);
    }

    public BydSwitch(Context context) {
        super(context);
        this.e = 300L;
        this.g = false;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        setTrackDrawable(null);
        setBackground(e7.d(context, R.drawable.switch_off));
    }

    public void b(boolean z, boolean z2) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        this.f = z;
        super.setChecked(z);
        if (z) {
            setBackground(e7.d(getContext(), R.drawable.switch_on));
        } else {
            setBackground(e7.d(getContext(), R.drawable.switch_off));
        }
        if (!z2 || (onCheckedChangeListener = this.b) == null) {
            return;
        }
        onCheckedChangeListener.onCheckedChanged(this, z);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.g = false;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.g = true;
        }
        if (action == 0 && wg.a(this.e)) {
            return false;
        }
        a aVar = this.c;
        if (aVar == null || !this.d || action != 1) {
            return super.onTouchEvent(motionEvent);
        }
        aVar.a(this, motionEvent, !isChecked());
        return true;
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.g) {
            b(z, true);
        }
    }

    public void setEverTouch(boolean z) {
        this.g = z;
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.b = onCheckedChangeListener;
    }

    public void setOnInterceptTouchListener(a aVar) {
        this.d = true;
        this.c = aVar;
    }

    public void setTime(long j) {
        this.e = j;
    }

    public BydSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 300L;
        this.g = false;
        a(context, attributeSet);
    }

    public BydSwitch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 300L;
        this.g = false;
        a(context, attributeSet);
    }
}
