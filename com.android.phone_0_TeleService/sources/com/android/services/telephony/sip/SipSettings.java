package com.android.services.telephony.sip;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.android.phone.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SipSettings extends PreferenceActivity {
    private PackageManager mPackageManager;
    private SipProfile mProfile;
    private SipProfileDb mProfileDb;
    private PreferenceCategory mSipListContainer;
    private SipManager mSipManager;
    private Map<String, SipPreference> mSipPreferenceMap;
    private SipPreferences mSipPreferences;
    private List<SipProfile> mSipProfileList;
    private int mUid = Process.myUid();

    private class SipPreference extends Preference {
        SipProfile mProfile;

        SipPreference(Context context, SipProfile sipProfile) {
            super(context);
            setProfile(sipProfile);
        }

        void setProfile(SipProfile sipProfile) {
            String string;
            this.mProfile = sipProfile;
            setTitle(SipSettings.this.getProfileName(sipProfile));
            if (SipSettings.this.mSipPreferences.isReceivingCallsEnabled()) {
                string = SipSettings.this.getString(R.string.registration_status_checking_status);
            } else {
                string = SipSettings.this.getString(R.string.registration_status_not_receiving);
            }
            updateSummary(string);
        }

        void updateSummary(String str) {
            int callingUid = this.mProfile.getCallingUid();
            if (callingUid > 0 && callingUid != SipSettings.this.mUid) {
                SipSettings sipSettings = SipSettings.this;
                str = sipSettings.getString(R.string.third_party_account_summary, new Object[]{sipSettings.getPackageNameFromUid(callingUid)});
            }
            setSummary(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getPackageNameFromUid(int i) {
        try {
            return this.mPackageManager.getApplicationInfo(this.mPackageManager.getPackagesForUid(i)[0], 0).loadLabel(this.mPackageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            log("getPackageNameFromUid, cannot find name of uid: " + i + ", exception: " + e);
            return "uid:" + i;
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSipManager = SipManager.newInstance(this);
        this.mSipPreferences = new SipPreferences(this);
        this.mProfileDb = new SipProfileDb(this);
        this.mPackageManager = getPackageManager();
        setContentView(R.layout.sip_settings_ui);
        addPreferencesFromResource(R.xml.sip_setting);
        this.mSipListContainer = (PreferenceCategory) findPreference("sip_account_list");
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        updateProfilesStatus();
    }

    @Override // android.preference.PreferenceActivity, android.app.ListActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterForContextMenu(getListView());
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onActivityResult(int i, final int i2, final Intent intent) {
        if (i2 == -1 || i2 == 1) {
            new Thread() { // from class: com.android.services.telephony.sip.SipSettings.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        if (SipSettings.this.mProfile != null) {
                            SipSettings.this.deleteProfile(SipSettings.this.mProfile);
                        }
                        SipProfile sipProfile = (SipProfile) intent.getParcelableExtra("sip_profile");
                        if (i2 == -1) {
                            SipSettings.this.addProfile(sipProfile);
                        }
                        SipSettings.this.updateProfilesStatus();
                    } catch (IOException e) {
                        SipSettings.log("onActivityResult, can not handle the profile:  " + e);
                    }
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProfilesStatus() {
        new Thread(new Runnable() { // from class: com.android.services.telephony.sip.SipSettings.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SipSettings.this.retrieveSipLists();
                } catch (Exception e) {
                    SipSettings.log("updateProfilesStatus, exception: " + e);
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getProfileName(SipProfile sipProfile) {
        String profileName = sipProfile.getProfileName();
        if (!TextUtils.isEmpty(profileName)) {
            return profileName;
        }
        return sipProfile.getUserName() + "@" + sipProfile.getSipDomain();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retrieveSipLists() {
        this.mSipPreferenceMap = new LinkedHashMap();
        this.mSipProfileList = this.mProfileDb.retrieveSipProfileList();
        processActiveProfilesFromSipService();
        Collections.sort(this.mSipProfileList, new Comparator<SipProfile>() { // from class: com.android.services.telephony.sip.SipSettings.3
            @Override // java.util.Comparator
            public int compare(SipProfile sipProfile, SipProfile sipProfile2) {
                return SipSettings.this.getProfileName(sipProfile).compareTo(SipSettings.this.getProfileName(sipProfile2));
            }
        });
        this.mSipListContainer.removeAll();
        if (this.mSipProfileList.isEmpty()) {
            getPreferenceScreen().removePreference(this.mSipListContainer);
        } else {
            getPreferenceScreen().addPreference(this.mSipListContainer);
            Iterator<SipProfile> it = this.mSipProfileList.iterator();
            while (it.hasNext()) {
                addPreferenceFor(it.next());
            }
        }
        if (this.mSipPreferences.isReceivingCallsEnabled()) {
            for (SipProfile sipProfile : this.mSipProfileList) {
                if (this.mUid == sipProfile.getCallingUid()) {
                    try {
                        this.mSipManager.setRegistrationListener(sipProfile.getUriString(), createRegistrationListener());
                    } catch (SipException e) {
                        log("retrieveSipLists, cannot set registration listener: " + e);
                    }
                }
            }
        }
    }

    private void processActiveProfilesFromSipService() {
        List<SipProfile> arrayList = new ArrayList();
        try {
            arrayList = this.mSipManager.getProfiles();
        } catch (SipException e) {
            log("SipManager could not retrieve SIP profiles: " + e);
        }
        for (SipProfile sipProfile : arrayList) {
            SipProfile profileFromList = getProfileFromList(sipProfile);
            if (profileFromList == null) {
                this.mSipProfileList.add(sipProfile);
            } else {
                profileFromList.setCallingUid(sipProfile.getCallingUid());
            }
        }
    }

    private SipProfile getProfileFromList(SipProfile sipProfile) {
        for (SipProfile sipProfile2 : this.mSipProfileList) {
            if (sipProfile2.getUriString().equals(sipProfile.getUriString())) {
                return sipProfile2;
            }
        }
        return null;
    }

    private void addPreferenceFor(SipProfile sipProfile) {
        SipPreference sipPreference = new SipPreference(this, sipProfile);
        this.mSipPreferenceMap.put(sipProfile.getUriString(), sipPreference);
        this.mSipListContainer.addPreference(sipPreference);
        sipPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.services.telephony.sip.SipSettings.4
            @Override // android.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(Preference preference) {
                SipSettings.this.handleProfileClick(((SipPreference) preference).mProfile);
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleProfileClick(final SipProfile sipProfile) {
        int callingUid = sipProfile.getCallingUid();
        if (callingUid == this.mUid || callingUid == 0) {
            startSipEditor(sipProfile);
        } else {
            new AlertDialog.Builder(this).setTitle(R.string.alert_dialog_close).setIconAttribute(android.R.attr.alertDialogIcon).setPositiveButton(R.string.close_profile, new DialogInterface.OnClickListener() { // from class: com.android.services.telephony.sip.SipSettings.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    SipSettings.this.deleteProfile(sipProfile);
                    SipSettings.this.unregisterProfile(sipProfile);
                }
            }).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterProfile(final SipProfile sipProfile) {
        new Thread(new Runnable() { // from class: com.android.services.telephony.sip.SipSettings.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SipSettings.this.mSipManager.close(sipProfile.getUriString());
                } catch (Exception e) {
                    SipSettings.log("unregisterProfile, unregister failed, SipService died? Exception: " + e);
                }
            }
        }, "unregisterProfile").start();
    }

    void deleteProfile(SipProfile sipProfile) {
        this.mSipProfileList.remove(sipProfile);
        SipPreference sipPreferenceRemove = this.mSipPreferenceMap.remove(sipProfile.getUriString());
        if (sipPreferenceRemove != null) {
            this.mSipListContainer.removePreference(sipPreferenceRemove);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProfile(SipProfile sipProfile) throws IOException {
        try {
            this.mSipManager.setRegistrationListener(sipProfile.getUriString(), createRegistrationListener());
        } catch (Exception e) {
            log("addProfile, cannot set registration listener: " + e);
        }
        this.mSipProfileList.add(sipProfile);
        addPreferenceFor(sipProfile);
    }

    private void startSipEditor(SipProfile sipProfile) {
        this.mProfile = sipProfile;
        Intent intent = new Intent(this, (Class<?>) SipEditor.class);
        intent.putExtra("sip_profile", (Parcelable) sipProfile);
        startActivityForResult(intent, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRegistrationMessage(final String str, final String str2) {
        runOnUiThread(new Runnable() { // from class: com.android.services.telephony.sip.SipSettings.7
            @Override // java.lang.Runnable
            public void run() {
                SipPreference sipPreference = (SipPreference) SipSettings.this.mSipPreferenceMap.get(str);
                if (sipPreference != null) {
                    sipPreference.updateSummary(str2);
                }
            }
        });
    }

    private SipRegistrationListener createRegistrationListener() {
        return new SipRegistrationListener() { // from class: com.android.services.telephony.sip.SipSettings.8
            @Override // android.net.sip.SipRegistrationListener
            public void onRegistrationDone(String str, long j) {
                SipSettings sipSettings = SipSettings.this;
                sipSettings.showRegistrationMessage(str, sipSettings.getString(R.string.registration_status_done));
            }

            @Override // android.net.sip.SipRegistrationListener
            public void onRegistering(String str) {
                SipSettings sipSettings = SipSettings.this;
                sipSettings.showRegistrationMessage(str, sipSettings.getString(R.string.registration_status_registering));
            }

            @Override // android.net.sip.SipRegistrationListener
            public void onRegistrationFailed(String str, int i, String str2) {
                if (i == -12) {
                    SipSettings sipSettings = SipSettings.this;
                    sipSettings.showRegistrationMessage(str, sipSettings.getString(R.string.registration_status_server_unreachable));
                    return;
                }
                if (i == -4) {
                    SipSettings sipSettings2 = SipSettings.this;
                    sipSettings2.showRegistrationMessage(str, sipSettings2.getString(R.string.registration_status_not_running));
                    return;
                }
                if (i == -10) {
                    if (SipManager.isSipWifiOnly(SipSettings.this.getApplicationContext())) {
                        SipSettings sipSettings3 = SipSettings.this;
                        sipSettings3.showRegistrationMessage(str, sipSettings3.getString(R.string.registration_status_no_wifi_data));
                        return;
                    } else {
                        SipSettings sipSettings4 = SipSettings.this;
                        sipSettings4.showRegistrationMessage(str, sipSettings4.getString(R.string.registration_status_no_data));
                        return;
                    }
                }
                if (i == -9) {
                    SipSettings sipSettings5 = SipSettings.this;
                    sipSettings5.showRegistrationMessage(str, sipSettings5.getString(R.string.registration_status_still_trying));
                } else if (i == -8) {
                    SipSettings sipSettings6 = SipSettings.this;
                    sipSettings6.showRegistrationMessage(str, sipSettings6.getString(R.string.registration_status_invalid_credentials));
                } else {
                    SipSettings sipSettings7 = SipSettings.this;
                    sipSettings7.showRegistrationMessage(str, sipSettings7.getString(R.string.registration_status_failed_try_later, new Object[]{str2}));
                }
            }
        };
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem menuItemAdd = menu.add(0, 1, 0, R.string.add_sip_account);
        menuItemAdd.setIcon(R.drawable.ic_add_gnu_grey);
        menuItemAdd.setShowAsAction(1);
        return true;
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(1).setEnabled(SipUtil.isPhoneIdle(this));
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            startSipEditor(null);
            return true;
        }
        if (itemId == 16908332) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str) {
        Log.d("SIP", "[SipSettings] " + str);
    }
}
