package androidx.leanback.widget;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.leanback.transition.TransitionHelper;
import androidx.leanback.transition.TransitionListener;
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class FullWidthDetailsOverviewSharedElementHelper extends FullWidthDetailsOverviewRowPresenter.Listener {
    static final boolean DEBUG = false;
    private static final long DEFAULT_TIMEOUT = 5000;
    static final String TAG = "DetailsTransitionHelper";
    Activity mActivityToRunTransition;
    private boolean mAutoStartSharedElementTransition = true;
    String mSharedElementName;
    private boolean mStartedPostpone;
    FullWidthDetailsOverviewRowPresenter.ViewHolder mViewHolder;

    static class TransitionTimeOutRunnable implements Runnable {
        WeakReference<FullWidthDetailsOverviewSharedElementHelper> mHelperRef;

        TransitionTimeOutRunnable(FullWidthDetailsOverviewSharedElementHelper helper) {
            this.mHelperRef = new WeakReference<>(helper);
        }

        @Override // java.lang.Runnable
        public void run() {
            FullWidthDetailsOverviewSharedElementHelper helper = this.mHelperRef.get();
            if (helper == null) {
                return;
            }
            helper.startPostponedEnterTransition();
        }
    }

    public void setSharedElementEnterTransition(Activity activity, String sharedElementName) {
        setSharedElementEnterTransition(activity, sharedElementName, DEFAULT_TIMEOUT);
    }

    public void setSharedElementEnterTransition(Activity activity, String sharedElementName, long timeoutMs) {
        if ((activity == null && !TextUtils.isEmpty(sharedElementName)) || (activity != null && TextUtils.isEmpty(sharedElementName))) {
            throw new IllegalArgumentException();
        }
        if (activity == this.mActivityToRunTransition && TextUtils.equals(sharedElementName, this.mSharedElementName)) {
            return;
        }
        this.mActivityToRunTransition = activity;
        this.mSharedElementName = sharedElementName;
        Object transition = TransitionHelper.getSharedElementEnterTransition(activity.getWindow());
        setAutoStartSharedElementTransition(transition != null);
        ActivityCompat.postponeEnterTransition(this.mActivityToRunTransition);
        if (timeoutMs > 0) {
            new Handler().postDelayed(new TransitionTimeOutRunnable(this), timeoutMs);
        }
    }

    public void setAutoStartSharedElementTransition(boolean enabled) {
        this.mAutoStartSharedElementTransition = enabled;
    }

    public boolean getAutoStartSharedElementTransition() {
        return this.mAutoStartSharedElementTransition;
    }

    @Override // androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter.Listener
    public void onBindLogo(FullWidthDetailsOverviewRowPresenter.ViewHolder vh) {
        this.mViewHolder = vh;
        if (!this.mAutoStartSharedElementTransition) {
            return;
        }
        if (vh != null) {
            ViewCompat.setTransitionName(vh.getLogoViewHolder().view, null);
        }
        this.mViewHolder.getDetailsDescriptionFrame().postOnAnimation(new Runnable() { // from class: androidx.leanback.widget.FullWidthDetailsOverviewSharedElementHelper.1
            @Override // java.lang.Runnable
            public void run() {
                ViewCompat.setTransitionName(FullWidthDetailsOverviewSharedElementHelper.this.mViewHolder.getLogoViewHolder().view, FullWidthDetailsOverviewSharedElementHelper.this.mSharedElementName);
                Object transition = TransitionHelper.getSharedElementEnterTransition(FullWidthDetailsOverviewSharedElementHelper.this.mActivityToRunTransition.getWindow());
                if (transition != null) {
                    TransitionHelper.addTransitionListener(transition, new TransitionListener() { // from class: androidx.leanback.widget.FullWidthDetailsOverviewSharedElementHelper.1.1
                        @Override // androidx.leanback.transition.TransitionListener
                        public void onTransitionEnd(Object transition2) {
                            if (FullWidthDetailsOverviewSharedElementHelper.this.mViewHolder.getActionsRow().isFocused()) {
                                FullWidthDetailsOverviewSharedElementHelper.this.mViewHolder.getActionsRow().requestFocus();
                            }
                            TransitionHelper.removeTransitionListener(transition2, this);
                        }
                    });
                }
                FullWidthDetailsOverviewSharedElementHelper.this.startPostponedEnterTransitionInternal();
            }
        });
    }

    public void startPostponedEnterTransition() {
        new Handler().post(new Runnable() { // from class: androidx.leanback.widget.FullWidthDetailsOverviewSharedElementHelper.2
            @Override // java.lang.Runnable
            public void run() {
                FullWidthDetailsOverviewSharedElementHelper.this.startPostponedEnterTransitionInternal();
            }
        });
    }

    void startPostponedEnterTransitionInternal() {
        if (!this.mStartedPostpone && this.mViewHolder != null) {
            ActivityCompat.startPostponedEnterTransition(this.mActivityToRunTransition);
            this.mStartedPostpone = true;
        }
    }
}
