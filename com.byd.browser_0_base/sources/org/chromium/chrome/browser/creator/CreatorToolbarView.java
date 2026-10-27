package org.chromium.chrome.browser.creator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.ga1;
import defpackage.nd3;
import defpackage.p10;
import defpackage.q10;
import defpackage.r10;
import java.util.ArrayList;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: loaded from: classes.dex */
public class CreatorToolbarView extends LinearLayout {
    public static final /* synthetic */ int k = 0;
    public TextView e;
    public FrameLayout f;
    public ButtonCompat g;
    public ButtonCompat h;
    public View i;
    public int j;

    public CreatorToolbarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static ObjectAnimator b(View view) {
        view.setVisibility(0);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 0.0f, 1.0f);
        objectAnimatorOfFloat.setDuration(300L);
        objectAnimatorOfFloat.addListener(new q10(view));
        return objectAnimatorOfFloat;
    }

    public static ObjectAnimator c(View view) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 1.0f, 0.0f);
        objectAnimatorOfFloat.setDuration(300L);
        objectAnimatorOfFloat.addListener(new r10(view));
        return objectAnimatorOfFloat;
    }

    public final void a(ButtonCompat buttonCompat) {
        Rect rect = new Rect();
        buttonCompat.getHitRect(rect);
        int iMax = Math.max((this.j - buttonCompat.getWidth()) / 2, 0);
        int iMax2 = Math.max((this.j - buttonCompat.getHeight()) / 2, 0);
        rect.left -= iMax;
        rect.right += iMax;
        rect.top -= iMax2;
        rect.bottom += iMax2;
        this.f.setTouchDelegate(new TouchDelegate(rect, buttonCompat));
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (TextView) findViewById(R$id.creator_title_toolbar);
        this.f = (FrameLayout) findViewById(R$id.creator_all_buttons_toolbar);
        this.g = (ButtonCompat) findViewById(R$id.creator_follow_button_toolbar);
        this.h = (ButtonCompat) findViewById(R$id.creator_following_button_toolbar);
        this.i = findViewById(R$id.creator_toolbar_bottom_border);
        this.j = getResources().getDimensionPixelSize(R$dimen.creator_toolbar_button_touch_size);
        this.f.addOnLayoutChangeListener(new ga1(1, this));
    }

    public void setFollowButtonToolbarOnClickListener(Runnable runnable) {
        this.g.setOnClickListener(new p10(0, runnable));
    }

    public void setFollowingButtonToolbarOnClickListener(Runnable runnable) {
        this.h.setOnClickListener(new nd3(2, runnable));
    }

    public void setIsFollowedStatus(boolean z) {
        if (z) {
            this.g.setVisibility(8);
            this.h.setVisibility(0);
        } else {
            this.g.setVisibility(0);
            this.h.setVisibility(8);
        }
    }

    public void setTitle(String str) {
        this.e.setText(str);
        this.e.setTooltipText(str);
    }

    public void setToolbarVisibility(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(b(this.e));
            arrayList.add(b(this.f));
            arrayList.add(b(this.i));
        } else {
            arrayList.add(c(this.e));
            arrayList.add(c(this.f));
            arrayList.add(c(this.i));
        }
        animatorSet.playTogether(arrayList);
        animatorSet.start();
    }
}
