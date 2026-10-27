package com.amazon.mp3.activity.navigation;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.help.HelpUtil;
import com.amazon.mp3.library.fragment.DownloadsFragment;
import com.amazon.music.events.UserInteractionAppEvent;

/* JADX INFO: loaded from: classes3.dex */
final class MoreMenuFragmentLoader extends FragmentLoader {
    public MoreMenuFragmentLoader(FragmentActivity fragmentActivity, FragmentController fragmentController) {
        super(fragmentActivity, fragmentController);
    }

    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    public boolean navigateFromMenu(final int menuId) {
        switch (menuId) {
            case R.id.more_downloads_tab /* 2131429176 */:
                onDownloadsMenuSelected(false);
                return true;
            case R.id.more_help_tab /* 2131429177 */:
                onHelpSelected();
                return true;
            case R.id.more_settings_tab /* 2131429181 */:
                onSettingsMenuSelected();
                return true;
            default:
                return false;
        }
    }

    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    public boolean navigateFromIntent(final Intent intent) {
        Bundle extras = intent.getExtras();
        String string = extras.getString("com.amazon.mp3.fragment.extra");
        boolean z = extras.getBoolean("add_to_back_stack", false);
        if (!DownloadsFragment.class.getSimpleName().equals(string)) {
            return false;
        }
        onDownloadsMenuSelected(z);
        return true;
    }

    private void onSettingsMenuSelected() {
        this.mFragmentActivity.startActivityForResult(SettingsActivity.getStartIntent(this.mFragmentActivity), SettingsActivity.SETTINGS_REQUEST_CODE);
        UserInteractionAppEvent.builder("goSettings").publish();
    }

    private void onDownloadsMenuSelected(boolean addToBackStack) {
        this.mFragmentController.changeScreenFragment(DownloadsFragment.newInstance(addToBackStack), addToBackStack, false);
    }

    private void onHelpSelected() {
        this.mFragmentActivity.startActivity(HelpUtil.getHelpIntent(getActivity().getApplicationContext(), HelpUtil.HelpType.LIBRARY));
    }
}
