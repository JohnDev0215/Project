package org.chromium.chrome.browser.creator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.nd3;
import defpackage.od3;
import org.chromium.chrome.R$id;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: loaded from: classes.dex */
public class CreatorProfileView extends LinearLayout {
    public static final /* synthetic */ int i = 0;
    public TextView e;
    public TextView f;
    public ButtonCompat g;
    public ButtonCompat h;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            CreatorProfileView creatorProfileView = CreatorProfileView.this;
            creatorProfileView.g.setEnabled(false);
            creatorProfileView.h.setEnabled(false);
        }
    }

    public class b extends AnimatorListenerAdapter {
        public b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            CreatorProfileView creatorProfileView = CreatorProfileView.this;
            creatorProfileView.g.setEnabled(true);
            creatorProfileView.h.setEnabled(true);
        }
    }

    public CreatorProfileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (TextView) findViewById(R$id.creator_name);
        this.f = (TextView) findViewById(R$id.creator_url);
        this.g = (ButtonCompat) findViewById(R$id.creator_follow_button);
        this.h = (ButtonCompat) findViewById(R$id.creator_following_button);
    }

    public void setFollowButtonOnClickListener(Runnable runnable) {
        this.g.setOnClickListener(new nd3(1, runnable));
    }

    public void setFollowingButtonOnClickListener(Runnable runnable) {
        this.h.setOnClickListener(new od3(1, runnable));
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

    public void setProfileVisibility(boolean z) {
        if (z) {
            setAlpha(1.0f);
            animate().alpha(0.0f).setDuration(300L).setListener(new a()).start();
        } else {
            setAlpha(0.0f);
            animate().alpha(1.0f).setDuration(150L).setListener(new b()).start();
        }
    }

    public void setTitle(String str) {
        this.e.setText(str);
        this.e.setTooltipText(str);
    }

    public void setUrl(String str) {
        this.f.setText(str);
        this.f.setTooltipText(str);
    }
}
