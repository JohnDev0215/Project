package com.android.car.settings.common;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.android.car.settings.accounts.AccountSettingsFragment;
import com.android.car.settings.accounts.ChooseAccountFragment;
import com.android.car.settings.applications.ApplicationDetailsFragment;
import com.android.car.settings.applications.ApplicationsSettingsFragment;
import com.android.car.settings.applications.AppsAndNotificationsFragment;
import com.android.car.settings.applications.defaultapps.DefaultAutofillPickerFragment;
import com.android.car.settings.applications.specialaccess.ModifySystemSettingsFragment;
import com.android.car.settings.applications.specialaccess.NotificationAccessFragment;
import com.android.car.settings.applications.specialaccess.PremiumSmsAccessFragment;
import com.android.car.settings.applications.specialaccess.SpecialAccessSettingsFragment;
import com.android.car.settings.applications.specialaccess.UsageAccessFragment;
import com.android.car.settings.applications.specialaccess.WifiControlFragment;
import com.android.car.settings.bluetooth.BluetoothSettingsFragment;
import com.android.car.settings.datausage.DataUsageFragment;
import com.android.car.settings.datetime.DatetimeSettingsFragment;
import com.android.car.settings.display.DisplaySettingsFragment;
import com.android.car.settings.home.HomepageFragment;
import com.android.car.settings.inputmethod.KeyboardFragment;
import com.android.car.settings.language.LanguagePickerFragment;
import com.android.car.settings.language.LanguagesAndInputFragment;
import com.android.car.settings.location.LocationSettingsFragment;
import com.android.car.settings.network.MobileNetworkFragment;
import com.android.car.settings.network.MobileNetworkListFragment;
import com.android.car.settings.network.NetworkAndInternetFragment;
import com.android.car.settings.privacy.PrivacySettingsFragment;
import com.android.car.settings.quicksettings.QuickSettingFragment;
import com.android.car.settings.security.SecuritySettingsFragment;
import com.android.car.settings.sound.RingtonePickerFragment;
import com.android.car.settings.sound.SoundSettingsFragment;
import com.android.car.settings.storage.StorageSettingsFragment;
import com.android.car.settings.system.AboutSettingsFragment;
import com.android.car.settings.system.LegalInformationFragment;
import com.android.car.settings.system.ResetOptionsFragment;
import com.android.car.settings.system.SystemSettingsFragment;
import com.android.car.settings.tts.TextToSpeechOutputFragment;
import com.android.car.settings.units.UnitsSettingsFragment;
import com.android.car.settings.users.UserDetailsFragment;
import com.android.car.settings.users.UsersListFragment;
import com.android.car.settings.wifi.AddWifiFragment;
import com.android.car.settings.wifi.WifiSettingsFragment;
import com.android.car.settings.wifi.WifiTetherFragment;
import com.android.car.settings.wifi.preferences.WifiPreferencesFragment;

/* JADX INFO: loaded from: classes5.dex */
public class CarSettingActivities {
    private static final Logger LOG = new Logger((Class<?>) CarSettingActivities.class);

    public static class QuickSettingActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new QuickSettingFragment();
        }
    }

    public static class HomepageActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new HomepageFragment();
        }
    }

    public static class DisplaySettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new DisplaySettingsFragment();
        }
    }

    public static class SoundSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new SoundSettingsFragment();
        }
    }

    public static class RingtonePickerActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            Bundle args = getIntent().getExtras().deepCopy();
            Fragment fragment = new RingtonePickerFragment();
            fragment.setArguments(args);
            return fragment;
        }
    }

    public static class NetworkAndInternetActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new NetworkAndInternetFragment();
        }
    }

    public static class BluetoothSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new BluetoothSettingsFragment();
        }
    }

    public static class UnitsSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new UnitsSettingsFragment();
        }
    }

    public static class LocationSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new LocationSettingsFragment();
        }
    }

    public static class AppsAndNotificationsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new AppsAndNotificationsFragment();
        }
    }

    public static class DatetimeSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new DatetimeSettingsFragment();
        }
    }

    public static class UserDetailsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            UserManager userManager = UserManager.get(this);
            if (userManager.isAdminUser()) {
                CarSettingActivities.LOG.v("Creating UsersListFragment for admin user.");
                return new UsersListFragment();
            }
            CarSettingActivities.LOG.v("Creating UserDetailsFragment for non-admin.");
            return UserDetailsFragment.newInstance(UserHandle.myUserId());
        }
    }

    public static class AccountSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new AccountSettingsFragment();
        }
    }

    public static class PrivacySettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new PrivacySettingsFragment();
        }
    }

    public static class StorageSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new StorageSettingsFragment();
        }
    }

    public static class SecuritySettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new SecuritySettingsFragment();
        }
    }

    public static class SystemSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new SystemSettingsFragment();
        }
    }

    public static class WifiSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new WifiSettingsFragment();
        }
    }

    public static class WifiTetherActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new WifiTetherFragment();
        }
    }

    public static class MobileNetworkActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return MobileNetworkFragment.newInstance(getIntent().getIntExtra("network_sub_id", Integer.MIN_VALUE));
        }
    }

    public static class MobileNetworkListActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new MobileNetworkListFragment();
        }
    }

    public static class DataUsageActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new DataUsageFragment();
        }
    }

    public static class ApplicationsSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new ApplicationsSettingsFragment();
        }
    }

    public static class SpecialAccessSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new SpecialAccessSettingsFragment();
        }
    }

    public static class LanguagesAndInputActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new LanguagesAndInputFragment();
        }
    }

    public static class AboutSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new AboutSettingsFragment();
        }
    }

    public static class LegalInformationActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new LegalInformationFragment();
        }
    }

    public static class ResetOptionsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new ResetOptionsFragment();
        }
    }

    public static class AddWifiActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new AddWifiFragment();
        }
    }

    public static class WifiPreferencesActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new WifiPreferencesFragment();
        }
    }

    public static class ApplicationsDetailsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            Intent intent = getIntent();
            String pkg = intent.getStringExtra("android.provider.extra.APP_PACKAGE");
            if (TextUtils.isEmpty(pkg)) {
                CarSettingActivities.LOG.w("No package provided for application detailed intent");
                Uri uri = intent.getData();
                if (uri == null) {
                    CarSettingActivities.LOG.w("No uri provided for application detailed intent");
                    return null;
                }
                pkg = uri.getSchemeSpecificPart();
            }
            return ApplicationDetailsFragment.getInstance(pkg);
        }
    }

    public static class ModifySystemSettingsActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new ModifySystemSettingsFragment();
        }
    }

    public static class NotificationAccessActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new NotificationAccessFragment();
        }
    }

    public static class PremiumSmsAccessActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new PremiumSmsAccessFragment();
        }
    }

    public static class UsageAccessActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new UsageAccessFragment();
        }
    }

    public static class WifiControlActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new WifiControlFragment();
        }
    }

    public static class ChooseAccountActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new ChooseAccountFragment();
        }
    }

    public static class LanguagePickerActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new LanguagePickerFragment();
        }
    }

    public static class DefaultAutofillPickerActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new DefaultAutofillPickerFragment();
        }
    }

    public static class KeyboardActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new KeyboardFragment();
        }
    }

    public static class TextToSpeechOutputActivity extends BaseCarSettingsActivity {
        @Override // com.android.car.settings.common.BaseCarSettingsActivity
        protected Fragment getInitialFragment() {
            return new TextToSpeechOutputFragment();
        }
    }
}
