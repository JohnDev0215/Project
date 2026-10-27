package com.byd.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import c.a.d.b;
import com.byd.bydbase.R$drawable;

/* JADX INFO: loaded from: classes.dex */
public class BydSwitch extends Switch {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient CompoundButton.OnCheckedChangeListener f1975b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient a f1976c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient boolean f1977d;
    public transient long e;

    public interface a {
        void c(View view, MotionEvent motionEvent, boolean z);
    }

    public BydSwitch(Context context) {
        super(context);
        this.e = 300L;
        a(context);
    }

    public final void a(Context context) {
        setTrackDrawable(null);
        setThumbDrawable(null);
        setBackground(b.g.b.a.d(getContext(), R$drawable.switch_off));
    }

    public void b(boolean z, boolean z2) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        super.setChecked(z);
        if (z) {
            setBackground(b.g.b.a.d(getContext(), R$drawable.switch_on));
        } else {
            setBackground(b.g.b.a.d(getContext(), R$drawable.switch_off));
        }
        if (!z2 || (onCheckedChangeListener = this.f1975b) == null) {
            return;
        }
        onCheckedChangeListener.onCheckedChanged(this, z);
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0 && b.b(this.e)) {
            return false;
        }
        a aVar = this.f1976c;
        if (aVar == null || !this.f1977d || action != 1) {
            return super.onTouchEvent(motionEvent);
        }
        aVar.c(this, motionEvent, !isChecked());
        return true;
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f1975b = onCheckedChangeListener;
    }

    public void setOnInterceptTouchListener(a aVar) {
        this.f1977d = true;
        this.f1976c = aVar;
    }

    public void setTime(long j) {
        this.e = j;
    }

    public BydSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 300L;
        a(context);
    }

    public BydSwitch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 300L;
        a(context);
    }
}
