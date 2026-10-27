package com.android.car.apps.common;

import android.car.drivingstate.XCarUxRestrictions;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.android.car.apps.common.UxrButton;
import com.android.car.ui.R$attr;
import com.android.car.ui.R$string;
import com.android.car.ui.R$styleable;
import com.blankj.utilcode.util.Utils;
import spi.e2;
import spi.gc;
import spi.hc;
import spi.ic;
import spi.n;
import spi.ya;

/* JADX INFO: loaded from: classes.dex */
public class UxrButton extends TextView {
    public static final int[] h = {R$attr.state_ux_restricted};
    public e2 a;
    public XCarUxRestrictions b;
    public View.OnClickListener c;
    public int d;
    public final Handler e;
    public final ic f;
    public final gc g;

    /* JADX WARN: Type inference failed for: r0v1, types: [spi.ic] */
    /* JADX WARN: Type inference failed for: r0v2, types: [spi.gc] */
    public UxrButton(Context context) {
        super(context);
        this.e = new Handler();
        final int i = 1;
        this.f = new e2.a(this) { // from class: spi.ic
            public final /* synthetic */ UxrButton b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // spi.e2.a
            public final void a(XCarUxRestrictions xCarUxRestrictions) {
                switch (i) {
                }
                UxrButton.b(this.b, xCarUxRestrictions);
            }
        };
        this.g = new View.OnClickListener(this) { // from class: spi.gc
            public final /* synthetic */ UxrButton b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i) {
                }
                UxrButton.a(this.b, view);
            }
        };
        c(context, null);
    }

    public static void a(UxrButton uxrButton, View view) {
        if (uxrButton.c == null) {
            return;
        }
        if (!e2.b(uxrButton.d, uxrButton.b)) {
            uxrButton.c.onClick(view);
        } else {
            n.b.a.execute(new ya(Utils.getApp().getResources().getString(R$string.restricted_while_driving), 0));
        }
    }

    public static void b(UxrButton uxrButton, XCarUxRestrictions xCarUxRestrictions) {
        uxrButton.b = xCarUxRestrictions;
        uxrButton.e.post(new hc(uxrButton, 1));
    }

    public final void c(Context context, AttributeSet attributeSet) {
        this.a = e2.a(context);
        super.setOnClickListener(this.g);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.UxrButton);
        try {
            this.d = typedArrayObtainStyledAttributes.getInteger(R$styleable.UxrButton_carUxRestrictions, 511);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a.register(this.f);
    }

    @Override // android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (e2.b(this.d, this.b)) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, h);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a.unregister(this.f);
    }

    @Override // android.view.View
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.c = onClickListener;
    }

    public void setUxRestrictions(int i) {
        this.d = i;
        this.e.post(new hc(this, 0));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [spi.ic] */
    /* JADX WARN: Type inference failed for: r0v2, types: [spi.gc] */
    public UxrButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new Handler();
        final int i = 2;
        this.f = new e2.a(this) { // from class: spi.ic
            public final /* synthetic */ UxrButton b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // spi.e2.a
            public final void a(XCarUxRestrictions xCarUxRestrictions) {
                switch (i) {
                }
                UxrButton.b(this.b, xCarUxRestrictions);
            }
        };
        this.g = new View.OnClickListener(this) { // from class: spi.gc
            public final /* synthetic */ UxrButton b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i) {
                }
                UxrButton.a(this.b, view);
            }
        };
        c(context, attributeSet);
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [spi.ic] */
    /* JADX WARN: Type inference failed for: r4v3, types: [spi.gc] */
    public UxrButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new Handler();
        final int i2 = 3;
        this.f = new e2.a(this) { // from class: spi.ic
            public final /* synthetic */ UxrButton b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // spi.e2.a
            public final void a(XCarUxRestrictions xCarUxRestrictions) {
                switch (i2) {
                }
                UxrButton.b(this.b, xCarUxRestrictions);
            }
        };
        this.g = new View.OnClickListener(this) { // from class: spi.gc
            public final /* synthetic */ UxrButton b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i2) {
                }
                UxrButton.a(this.b, view);
            }
        };
        c(context, attributeSet);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [spi.ic] */
    /* JADX WARN: Type inference failed for: r3v3, types: [spi.gc] */
    public UxrButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.e = new Handler();
        final int i3 = 0;
        this.f = new e2.a(this) { // from class: spi.ic
            public final /* synthetic */ UxrButton b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // spi.e2.a
            public final void a(XCarUxRestrictions xCarUxRestrictions) {
                switch (i3) {
                }
                UxrButton.b(this.b, xCarUxRestrictions);
            }
        };
        this.g = new View.OnClickListener(this) { // from class: spi.gc
            public final /* synthetic */ UxrButton b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i3) {
                }
                UxrButton.a(this.b, view);
            }
        };
        c(context, attributeSet);
    }
}
