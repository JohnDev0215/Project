package com.byd.hvac.view.tintradiobutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.byd.hvac.R;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class TintRadioButton extends AppCompatRadioButton implements CompoundButton.OnCheckedChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1087a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1088b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1089c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public volatile boolean j;
    public volatile boolean k;
    public volatile boolean l;
    public volatile boolean m;
    public b n;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f1090a;

        public a(boolean z) {
            this.f1090a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!TintRadioButton.this.j) {
                if (TintRadioButton.this.k) {
                    return;
                } else {
                    SystemClock.sleep(10L);
                }
            }
            Message messageObtain = Message.obtain();
            messageObtain.what = 18;
            messageObtain.obj = Boolean.valueOf(this.f1090a);
            TintRadioButton.this.n.sendMessage(messageObtain);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<TintRadioButton> f1092a;

        public /* synthetic */ b(TintRadioButton tintRadioButton, a aVar) {
            this(tintRadioButton);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            WeakReference<TintRadioButton> weakReference = this.f1092a;
            if (weakReference == null || weakReference.get() == null || message.what != 18) {
                return;
            }
            this.f1092a.get().g(((Boolean) message.obj).booleanValue());
        }

        public b(TintRadioButton tintRadioButton) {
            this.f1092a = new WeakReference<>(tintRadioButton);
        }
    }

    public TintRadioButton(Context context) {
        super(context);
        this.f1087a = -2;
        this.f1088b = -2;
        this.f1089c = -2;
        this.d = -2;
        this.e = -2;
        this.f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = false;
        this.k = false;
        this.l = true;
        this.m = true;
        j(context);
    }

    public void c(int i) {
        Drawable background = getBackground();
        if (!this.l || i <= 0) {
            setBackgroundDrawable(background);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(background.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i));
        setBackgroundDrawable(drawableWrap);
    }

    public void d(int i) {
        if (i == 0) {
            c(this.f1089c);
        } else {
            if (i != 1) {
                return;
            }
            c(this.f1088b);
        }
    }

    public void e(int i, int i2) {
        Drawable drawableH = h(i);
        if (!this.m || i2 <= 0) {
            l(i, drawableH);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(drawableH.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i2));
        l(i, drawableWrap);
    }

    public void f(int i) {
        if (i == 0) {
            e(this.f1087a, this.e);
        } else {
            if (i != 1) {
                return;
            }
            e(this.f1087a, this.d);
        }
    }

    public final void g(boolean z) {
        if (z) {
            f(1);
            d(1);
            int i = this.g;
            if (i != -2) {
                setTextSize(0, i);
            }
        } else {
            f(0);
            d(0);
            int i2 = this.f;
            if (i2 != -2) {
                setTextSize(0, i2);
            }
        }
        getTextSize();
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
        this.f1088b = typedArrayObtainStyledAttributes.getResourceId(1, this.f1088b);
        this.f1089c = typedArrayObtainStyledAttributes.getResourceId(0, this.f1089c);
        this.d = typedArrayObtainStyledAttributes.getResourceId(3, this.d);
        this.e = typedArrayObtainStyledAttributes.getResourceId(2, this.e);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, this.f);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, this.g);
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.h);
        this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, this.i);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void j(Context context) {
        setOnCheckedChangeListener(this);
        if (this.n == null) {
            this.n = new b(this, null);
        }
    }

    public final void k() {
        if (this.h == -2 || this.i == -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        for (Drawable drawable : compoundDrawables) {
            if (drawable != null) {
                drawable.setBounds(0, 0, this.h, this.i);
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
        if (this.j) {
            g(z);
        } else {
            new Thread(new a(z)).start();
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.k = true;
        b bVar = this.n;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        k();
        if (this.f1087a != -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        int i5 = 0;
        while (true) {
            if (i5 >= compoundDrawables.length) {
                break;
            }
            if (compoundDrawables[i5] != null) {
                this.f1087a = i5;
                break;
            }
            i5++;
        }
        this.j = true;
        int i6 = this.e;
        if (i6 != -2) {
            e(this.f1087a, i6);
        }
    }

    public void setSupportBgTint(boolean z) {
        this.l = z;
    }

    public void setSupportSrcTint(boolean z) {
        this.m = z;
    }

    public TintRadioButton(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1087a = -2;
        this.f1088b = -2;
        this.f1089c = -2;
        this.d = -2;
        this.e = -2;
        this.f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = false;
        this.k = false;
        this.l = true;
        this.m = true;
        i(context, attributeSet);
        j(context);
    }

    public TintRadioButton(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1087a = -2;
        this.f1088b = -2;
        this.f1089c = -2;
        this.d = -2;
        this.e = -2;
        this.f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = false;
        this.k = false;
        this.l = true;
        this.m = true;
        i(context, attributeSet);
        j(context);
    }
}
