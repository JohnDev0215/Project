package com.amazon.mp3.activity;

import android.app.Activity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.subscription.UserSubscription;

/* JADX INFO: loaded from: classes3.dex */
public class LaunchingAnimationController {
    private Activity mActivity;

    public LaunchingAnimationController(Activity mActivity, boolean playVideo) {
        this.mActivity = mActivity;
    }

    public void showSubscriptionBadge() throws DataNotReadyException {
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        ImageView imageView = (ImageView) this.mActivity.findViewById(R.id.subscription_badge);
        if (userSubscription.isKatana() || userSubscription.isHawkfireAllDevices()) {
            imageView.setImageResource(R.drawable.unlimited);
        } else if (userSubscription.isPrime()) {
            imageView.setImageResource(R.drawable.prime);
        } else if (UserSubscriptionUtil.isNightwingOnly()) {
            imageView.setImageResource(R.drawable.free);
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(400L);
        alphaAnimation.setFillAfter(true);
        imageView.startAnimation(alphaAnimation);
    }

    public void initLoadingScreen() {
        this.mActivity.getWindow().setFlags(1024, 1024);
        initLogoScreen();
    }

    private void initLogoScreen() {
        ((ImageView) this.mActivity.findViewById(R.id.logo_animation_iv)).setImageResource(R.drawable.logo_amazonmusic_00000);
        this.mActivity.findViewById(R.id.subscription_badge).setVisibility(8);
    }
}
