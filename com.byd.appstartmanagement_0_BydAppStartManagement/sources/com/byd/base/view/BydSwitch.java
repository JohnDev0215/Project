package com.byd.base.view;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import b.a.d.f.c;
import com.byd.base.R$drawable;

/* JADX INFO: loaded from: classes.dex */
public class BydSwitch extends Switch implements b.a.b.e.c.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f552b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a f553c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f554d;
    public long e;
    public volatile boolean f;
    public boolean g;

    public interface a {
        void a(View view, MotionEvent motionEvent, boolean z);
    }

    public BydSwitch(@NonNull Context context) {
        super(context);
        this.e = 300L;
        this.g = false;
        a(context, null);
    }

    public final void a(Context context, @Nullable AttributeSet attributeSet) {
        setTrackDrawable(null);
        setBackground(ContextCompat.getDrawable(context, R$drawable.switch_off));
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.g = false;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        b.a.d.f.a.a(parcelable, "checked", Boolean.valueOf(this.f));
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.g = true;
        }
        if (action == 0 && c.a(this.e)) {
            return false;
        }
        a aVar = this.f553c;
        if (aVar == null || !this.f554d || action != 1) {
            return super.onTouchEvent(motionEvent);
        }
        aVar.a(this, motionEvent, !isChecked());
        return true;
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.g) {
            setChecked(z, true);
        }
    }

    public void setEverTouch(boolean z) {
        this.g = z;
    }

    @Override // b.a.b.e.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    @Override // b.a.b.e.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f552b = onCheckedChangeListener;
    }

    public void setOnInterceptTouchListener(a aVar) {
        this.f554d = true;
        this.f553c = aVar;
    }

    public void setTime(long j) {
        this.e = j;
    }

    public void setChecked(boolean z, boolean z2) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        this.f = z;
        super.setChecked(z);
        if (z) {
            setBackground(ContextCompat.getDrawable(getContext(), R$drawable.switch_on));
        } else {
            setBackground(ContextCompat.getDrawable(getContext(), R$drawable.switch_off));
        }
        if (!z2 || (onCheckedChangeListener = this.f552b) == null) {
            return;
        }
        onCheckedChangeListener.onCheckedChanged(this, z);
    }

    public BydSwitch(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 300L;
        this.g = false;
        a(context, attributeSet);
    }

    public BydSwitch(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 300L;
        this.g = false;
        a(context, attributeSet);
    }
}
