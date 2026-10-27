package org.chromium.chrome.browser.ui.tablet.emptybackground;

import android.R;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import defpackage.a7;
import defpackage.fz2;
import defpackage.h7;
import defpackage.i7;
import defpackage.vg0;
import defpackage.wg0;
import defpackage.wz;
import defpackage.y13;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.ui.tablet.emptybackground.incognitotoggle.IncognitoToggleButtonTablet;

/* JADX INFO: loaded from: classes2.dex */
public class EmptyBackgroundViewTablet extends FrameLayout {
    public static final /* synthetic */ int k = 0;
    public y13 e;
    public fz2 f;
    public Animator g;
    public ObjectAnimator h;
    public ObjectAnimator i;
    public IncognitoToggleButtonTablet j;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            EmptyBackgroundViewTablet emptyBackgroundViewTablet = EmptyBackgroundViewTablet.this;
            if (emptyBackgroundViewTablet.f == null) {
                return;
            }
            emptyBackgroundViewTablet.e.g(false).c();
            emptyBackgroundViewTablet.f.f(2);
        }
    }

    public EmptyBackgroundViewTablet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        findViewById(R$id.empty_new_tab_button).setOnClickListener(new a());
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(R$style.ToolbarButton, new int[]{R.attr.layout_height});
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        View viewFindViewById = findViewById(R$id.empty_layout_button_container);
        float f = -dimensionPixelSize;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewFindViewById, (Property<View, Float>) View.TRANSLATION_Y, f, 0.0f);
        this.h = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(200L);
        this.h.addListener(new vg0(this));
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(viewFindViewById, (Property<View, Float>) View.TRANSLATION_Y, 0.0f, f);
        this.i = objectAnimatorOfFloat2;
        objectAnimatorOfFloat2.setDuration(200L);
        this.i.addListener(new wg0(this));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setEmptyContainerState(boolean r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L14
            int r0 = r2.getVisibility()
            if (r0 == 0) goto L14
            android.animation.Animator r0 = r2.g
            android.animation.ObjectAnimator r1 = r2.h
            if (r0 == r1) goto L14
            t41 r3 = defpackage.t41.f
            r3.e(r2)
            goto L26
        L14:
            if (r3 != 0) goto L25
            int r3 = r2.getVisibility()
            r0 = 8
            if (r3 == r0) goto L25
            android.animation.Animator r3 = r2.g
            android.animation.ObjectAnimator r1 = r2.i
            if (r3 == r1) goto L25
            goto L26
        L25:
            r1 = 0
        L26:
            if (r1 == 0) goto L34
            android.animation.Animator r3 = r2.g
            if (r3 == 0) goto L2f
            r3.cancel()
        L2f:
            r2.g = r1
            r1.start()
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ui.tablet.emptybackground.EmptyBackgroundViewTablet.setEmptyContainerState(boolean):void");
    }

    public void setMenuOnTouchListener(h7 h7Var) {
        ImageButton imageButton = (ImageButton) findViewById(R$id.empty_menu_button);
        i7 i7Var = (i7) h7Var;
        i7Var.getClass();
        a7 a7Var = new a7(i7Var);
        imageButton.setOnTouchListener(a7Var);
        a7Var.f = new wz(23);
    }

    public void setTabCreator(fz2 fz2Var) {
        this.f = fz2Var;
    }

    public void setTabModelSelector(y13 y13Var) {
        this.e = y13Var;
        IncognitoToggleButtonTablet incognitoToggleButtonTablet = (IncognitoToggleButtonTablet) findViewById(R$id.empty_incognito_toggle_button);
        this.j = incognitoToggleButtonTablet;
        incognitoToggleButtonTablet.setTabModelSelector(this.e);
    }
}
