package com.amazon.mp3.activity;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.playback.casting.CastingVolumeController;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.InteractionType;
import com.amazon.music.views.library.views.presentation.ActionBarDecorator;
import com.amazon.podcast.ActionBarInfo;

/* JADX INFO: loaded from: classes3.dex */
public abstract class NavigationActivity extends BaseActivity implements ActionBarProvider {
    private ActionBar mActionBar;
    private BottomNavigationBarContainer mBottomBarContainer;
    private boolean mCloseIndicatorIsEnabled;
    private View mCustomActionView;
    private ConstraintLayout mParentLayout;

    @Override // com.amazon.mp3.actionbar.ActionBarProvider
    public void requestHomeButtonAsBack() {
        ActionBar actionBar;
        if (isDestroyed() || (actionBar = this.mActionBar) == null) {
            return;
        }
        View view = this.mCustomActionView;
        if (view != null && (view instanceof ActionBarDecorator)) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            ((ActionBarDecorator) this.mCustomActionView).setBackButtonVisible(true);
            ((ActionBarDecorator) this.mCustomActionView).getBackButtonView().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.NavigationActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$requestHomeButtonAsBack$0(view2);
                }
            });
        } else {
            actionBar.setDisplayHomeAsUpEnabled(true);
            this.mActionBar.setHomeAsUpIndicator(R.drawable.ic_navigation_goback);
            this.mActionBar.setHomeActionContentDescription(getString(R.string.actionbar_navigate_up_content_description));
            this.mCloseIndicatorIsEnabled = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$requestHomeButtonAsBack$0(View view) {
        handleNavigationButtonClick();
    }

    @Override // com.amazon.mp3.actionbar.ActionBarProvider
    public void requestHomeButtonAsStoreClose() {
        ActionBar actionBar;
        if (isDestroyed() || (actionBar = this.mActionBar) == null) {
            return;
        }
        this.mCloseIndicatorIsEnabled = true;
        actionBar.setDisplayHomeAsUpEnabled(true);
        this.mActionBar.setHomeAsUpIndicator(R.drawable.btn_close);
        this.mActionBar.setHomeActionContentDescription(getString(R.string.close_button_content_description));
    }

    @Override // com.amazon.mp3.actionbar.ActionBarProvider
    public void restoreHomeButton() {
        ActionBar actionBar;
        if (isDestroyed() || (actionBar = this.mActionBar) == null) {
            return;
        }
        actionBar.setDisplayHomeAsUpEnabled(false);
        this.mCloseIndicatorIsEnabled = false;
    }

    @Override // com.amazon.mp3.actionbar.ActionBarProvider
    public void setHomeButtonVisibility(final int visiblity) {
        ActionBar actionBar;
        if (isDestroyed() || (actionBar = this.mActionBar) == null) {
            return;
        }
        if (visiblity == 8) {
            actionBar.setDisplayShowHomeEnabled(false);
        } else {
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override // com.amazon.mp3.actionbar.ActionBarProvider
    public void setHeaderView(View headerView) {
        ActionBar actionBar;
        if (isDestroyed() || (actionBar = this.mActionBar) == null) {
            return;
        }
        this.mCustomActionView = headerView;
        actionBar.setCustomView(headerView);
        this.mActionBar.setDisplayShowTitleEnabled(false);
        this.mActionBar.setDisplayShowCustomEnabled(true);
    }

    @Override // com.amazon.mp3.actionbar.ActionBarProvider
    public void removeHeaderView() {
        ActionBar actionBar;
        if (isDestroyed() || (actionBar = this.mActionBar) == null) {
            return;
        }
        actionBar.setDisplayShowCustomEnabled(false);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == 16908332) {
            handleNavigationButtonClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 25) {
            CastingVolumeController.get().getCastingVolumeProvider().onAdjustVolume(-1);
        } else if (keyCode == 24) {
            CastingVolumeController.get().getCastingVolumeProvider().onAdjustVolume(1);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void handleNavigationButtonClick() {
        if (this.mCloseIndicatorIsEnabled) {
            if (this.mBottomBarContainer == null) {
                return;
            }
            if (AmazonApplication.getCapabilities().shouldBrowseBeSupported()) {
                this.mBottomBarContainer.navigateToTab(LastViewedScreenUtil.LastViewedScreen.PRIME_MUSIC);
                return;
            } else {
                this.mBottomBarContainer.navigateToTab(LastViewedScreenUtil.LastViewedScreen.LIBRARY);
                return;
            }
        }
        onBackPressed();
        UserInteractionAppEvent.builder("goBack").withInteractionType(InteractionType.TAP).publish();
    }

    protected void initToolbar() {
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.home_menu_drawer);
        this.mParentLayout = constraintLayout;
        setSupportActionBar((Toolbar) constraintLayout.findViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        this.mActionBar = supportActionBar;
        ActionBarInfo.INSTANCE.setActionBar(supportActionBar);
        ActionBar actionBar = this.mActionBar;
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            this.mActionBar.setDisplayHomeAsUpEnabled(false);
            this.mActionBar.setHomeButtonEnabled(true);
        }
        this.mBottomBarContainer = BottomNavigationBarContainer.getInstance();
    }
}
