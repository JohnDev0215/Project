package com.byd.secretcode.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import com.byd.secretcode.R;
import defpackage.e7;
import defpackage.y7;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class TintRadioButton extends AppCompatRadioButton implements CompoundButton.OnCheckedChangeListener {
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public volatile boolean n;
    public volatile boolean o;
    public volatile boolean p;
    public volatile boolean q;
    public b r;

    public class a implements Runnable {
        public final /* synthetic */ boolean b;

        public a(boolean z) {
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!TintRadioButton.this.n) {
                if (TintRadioButton.this.o) {
                    return;
                } else {
                    SystemClock.sleep(10L);
                }
            }
            Message messageObtain = Message.obtain();
            messageObtain.what = 18;
            messageObtain.obj = Boolean.valueOf(this.b);
            TintRadioButton.this.r.sendMessage(messageObtain);
        }
    }

    public static class b extends Handler {
        public WeakReference<TintRadioButton> a;

        public /* synthetic */ b(TintRadioButton tintRadioButton, a aVar) {
            this(tintRadioButton);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<TintRadioButton> weakReference = this.a;
            if (weakReference == null || weakReference.get() == null || message.what != 18) {
                return;
            }
            this.a.get().g(((Boolean) message.obj).booleanValue());
        }

        public b(TintRadioButton tintRadioButton) {
            this.a = new WeakReference<>(tintRadioButton);
        }
    }

    public TintRadioButton(Context context) {
        super(context);
        this.e = -2;
        this.f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = -2;
        this.k = -2;
        this.l = -2;
        this.m = -2;
        this.n = false;
        this.o = false;
        this.p = true;
        this.q = true;
        j(context);
    }

    public void c(int i) {
        Drawable background = getBackground();
        if (!this.p || i <= 0) {
            setBackgroundDrawable(background);
            return;
        }
        Drawable drawableR = y7.r(background.mutate());
        y7.n(drawableR, e7.b(getContext(), i));
        setBackgroundDrawable(drawableR);
    }

    public void d(int i) {
        if (i == 0) {
            c(this.g);
        } else {
            if (i != 1) {
                return;
            }
            c(this.f);
        }
    }

    public void e(int i, int i2) {
        Drawable drawableH = h(i);
        if (!this.q || i2 <= 0) {
            l(i, drawableH);
            return;
        }
        Drawable drawableR = y7.r(drawableH.mutate());
        y7.n(drawableR, e7.b(getContext(), i2));
        l(i, drawableR);
    }

    public void f(int i) {
        if (i == 0) {
            e(this.e, this.i);
        } else {
            if (i != 1) {
                return;
            }
            e(this.e, this.h);
        }
    }

    public final void g(boolean z) {
        if (z) {
            f(1);
            d(1);
            int i = this.k;
            if (i != -2) {
                setTextSize(0, i);
                return;
            }
            return;
        }
        f(0);
        d(0);
        int i2 = this.j;
        if (i2 != -2) {
            setTextSize(0, i2);
        }
    }

    public final Drawable h(int i) {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (i == 0) {
            return compoundDrawables[0];
        }
        if (i == 1) {
            return compoundDrawables[1];
        }
        if (i == 2) {
            return compoundDrawables[2];
        }
        if (i != 3) {
            return null;
        }
        return compoundDrawables[3];
    }

    public final void i(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.tint_rbtn);
        this.f = typedArrayObtainStyledAttributes.getResourceId(1, this.f);
        this.g = typedArrayObtainStyledAttributes.getResourceId(0, this.g);
        this.h = typedArrayObtainStyledAttributes.getResourceId(3, this.h);
        this.i = typedArrayObtainStyledAttributes.getResourceId(2, this.i);
        this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, this.j);
        this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, this.k);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.l);
        this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, this.m);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void j(Context context) {
        setOnCheckedChangeListener(this);
        if (this.r == null) {
            this.r = new b(this, null);
        }
    }

    public final void k() {
        if (this.l == -2 || this.m == -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        for (Drawable drawable : compoundDrawables) {
            if (drawable != null) {
                drawable.setBounds(0, 0, this.l, this.m);
            }
        }
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
    }

    public final void l(int i, Drawable drawable) {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (i == 0) {
            setCompoundDrawables(drawable, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
            return;
        }
        if (i == 1) {
            setCompoundDrawables(compoundDrawables[0], drawable, compoundDrawables[2], compoundDrawables[3]);
        } else if (i == 2) {
            setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], drawable, compoundDrawables[3]);
        } else {
            if (i != 3) {
                return;
            }
            setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], drawable);
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (this.n) {
            g(z);
        } else {
            new Thread(new a(z)).start();
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.o = true;
        b bVar = this.r;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        k();
        if (this.e != -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        int i5 = 0;
        while (true) {
            if (i5 >= compoundDrawables.length) {
                break;
            }
            if (compoundDrawables[i5] != null) {
                this.e = i5;
                break;
            }
            i5++;
        }
        this.n = true;
        int i6 = this.i;
        if (i6 != -2) {
            e(this.e, i6);
        }
    }

    public void setSupportBgTint(boolean z) {
        this.p = z;
    }

    public void setSupportSrcTint(boolean z) {
        this.q = z;
    }

    public TintRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = -2;
        this.f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = -2;
        this.k = -2;
        this.l = -2;
        this.m = -2;
        this.n = false;
        this.o = false;
        this.p = true;
        this.q = true;
        i(context, attributeSet);
        j(context);
    }

    public TintRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = -2;
        this.f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = -2;
        this.k = -2;
        this.l = -2;
        this.m = -2;
        this.n = false;
        this.o = false;
        this.p = true;
        this.q = true;
        i(context, attributeSet);
        j(context);
    }
}
