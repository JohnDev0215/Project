package com.android.car.developeroptions.notification;

import android.app.ActivityManager;
import android.app.AutomaticZenRule;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.icu.text.ListFormatter;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.service.notification.ZenModeConfig;
import android.service.notification.ZenPolicy;
import android.util.Log;
import com.android.car.developeroptions.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeBackend {
    public static final Comparator<Map.Entry<String, AutomaticZenRule>> RULE_COMPARATOR = new Comparator<Map.Entry<String, AutomaticZenRule>>() { // from class: com.android.car.developeroptions.notification.ZenModeBackend.1
        @Override // java.util.Comparator
        public int compare(Map.Entry<String, AutomaticZenRule> entry, Map.Entry<String, AutomaticZenRule> entry2) {
            boolean zContains = ZenModeBackend.getDefaultRuleIds().contains(entry.getKey());
            if (zContains != ZenModeBackend.getDefaultRuleIds().contains(entry2.getKey())) {
                return zContains ? -1 : 1;
            }
            int iCompare = Long.compare(entry.getValue().getCreationTime(), entry2.getValue().getCreationTime());
            return iCompare != 0 ? iCompare : key(entry.getValue()).compareTo(key(entry2.getValue()));
        }

        private String key(AutomaticZenRule automaticZenRule) {
            int i;
            if (ZenModeConfig.isValidScheduleConditionId(automaticZenRule.getConditionId())) {
                i = 1;
            } else {
                i = ZenModeConfig.isValidEventConditionId(automaticZenRule.getConditionId()) ? 2 : 3;
            }
            return i + automaticZenRule.getName().toString();
        }
    };
    protected static final String ZEN_MODE_FROM_ANYONE = "zen_mode_from_anyone";
    protected static final String ZEN_MODE_FROM_CONTACTS = "zen_mode_from_contacts";
    protected static final String ZEN_MODE_FROM_NONE = "zen_mode_from_none";
    protected static final String ZEN_MODE_FROM_STARRED = "zen_mode_from_starred";
    private static List<String> mDefaultRuleIds;
    private static ZenModeBackend sInstance;
    private String TAG = "ZenModeSettingsBackend";
    private final Context mContext;
    private final NotificationManager mNotificationManager;
    protected NotificationManager.Policy mPolicy;
    protected int mZenMode;

    private int clearDeprecatedEffects(int i) {
        return i & (-4);
    }

    protected static String getKeyFromSetting(int i) {
        return i != 0 ? i != 1 ? i != 2 ? ZEN_MODE_FROM_NONE : ZEN_MODE_FROM_STARRED : ZEN_MODE_FROM_CONTACTS : ZEN_MODE_FROM_ANYONE;
    }

    protected static String getKeyFromZenPolicySetting(int i) {
        return i != 1 ? i != 2 ? i != 3 ? ZEN_MODE_FROM_NONE : ZEN_MODE_FROM_STARRED : ZEN_MODE_FROM_CONTACTS : ZEN_MODE_FROM_ANYONE;
    }

    protected int getAlarmsTotalSilenceCallsMessagesSummary(int i) {
        if (i == 4) {
            return R.string.zen_mode_from_none_messages;
        }
        if (i == 8) {
            return R.string.zen_mode_from_none_calls;
        }
        return 0;
    }

    public static ZenModeBackend getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ZenModeBackend(context);
        }
        return sInstance;
    }

    public ZenModeBackend(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
        updateZenMode();
        updatePolicy();
    }

    protected void updatePolicy() {
        NotificationManager notificationManager = this.mNotificationManager;
        if (notificationManager != null) {
            this.mPolicy = notificationManager.getNotificationPolicy();
        }
    }

    protected void updateZenMode() {
        this.mZenMode = Settings.Global.getInt(this.mContext.getContentResolver(), ZenModeSoundSettingsPreferenceController.ZEN_MODE_KEY, this.mZenMode);
    }

    protected boolean updateZenRule(String str, AutomaticZenRule automaticZenRule) {
        return NotificationManager.from(this.mContext).updateAutomaticZenRule(str, automaticZenRule);
    }

    protected void setZenMode(int i) {
        NotificationManager.from(this.mContext).setZenMode(i, null, this.TAG);
        this.mZenMode = getZenMode();
    }

    protected void setZenModeForDuration(int i) {
        this.mNotificationManager.setZenMode(1, ZenModeConfig.toTimeCondition(this.mContext, i, ActivityManager.getCurrentUser(), true).id, this.TAG);
        this.mZenMode = getZenMode();
    }

    protected int getZenMode() {
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), ZenModeSoundSettingsPreferenceController.ZEN_MODE_KEY, this.mZenMode);
        this.mZenMode = i;
        return i;
    }

    protected boolean isVisualEffectSuppressed(int i) {
        return (this.mPolicy.suppressedVisualEffects & i) != 0;
    }

    protected boolean isPriorityCategoryEnabled(int i) {
        return (this.mPolicy.priorityCategories & i) != 0;
    }

    protected int getNewDefaultPriorityCategories(boolean z, int i) {
        int i2 = this.mPolicy.priorityCategories;
        return z ? i2 | i : i2 & (~i);
    }

    protected int getPriorityCallSenders() {
        if (isPriorityCategoryEnabled(8)) {
            return this.mPolicy.priorityCallSenders;
        }
        return -1;
    }

    protected int getPriorityMessageSenders() {
        if (isPriorityCategoryEnabled(4)) {
            return this.mPolicy.priorityMessageSenders;
        }
        return -1;
    }

    protected void saveVisualEffectsPolicy(int i, boolean z) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "zen_settings_updated", 1);
        int newSuppressedEffects = getNewSuppressedEffects(z, i);
        NotificationManager.Policy policy = this.mPolicy;
        savePolicy(policy.priorityCategories, policy.priorityCallSenders, policy.priorityMessageSenders, newSuppressedEffects);
    }

    protected void saveSoundPolicy(int i, boolean z) {
        int newDefaultPriorityCategories = getNewDefaultPriorityCategories(z, i);
        NotificationManager.Policy policy = this.mPolicy;
        savePolicy(newDefaultPriorityCategories, policy.priorityCallSenders, policy.priorityMessageSenders, policy.suppressedVisualEffects);
    }

    protected void savePolicy(int i, int i2, int i3, int i4) {
        NotificationManager.Policy policy = new NotificationManager.Policy(i, i2, i3, i4);
        this.mPolicy = policy;
        this.mNotificationManager.setNotificationPolicy(policy);
    }

    private int getNewSuppressedEffects(boolean z, int i) {
        int i2 = this.mPolicy.suppressedVisualEffects;
        return clearDeprecatedEffects(z ? i2 | i : (~i) & i2);
    }

    protected void saveSenders(int i, int i2) {
        String str;
        int priorityCallSenders = getPriorityCallSenders();
        int priorityMessageSenders = getPriorityMessageSenders();
        int prioritySenders = getPrioritySenders(i);
        boolean z = i2 != -1;
        if (i2 == -1) {
            i2 = prioritySenders;
        }
        if (i == 8) {
            str = "Calls";
            priorityCallSenders = i2;
        } else {
            str = "";
        }
        if (i == 4) {
            str = "Messages";
            priorityMessageSenders = i2;
        }
        savePolicy(getNewDefaultPriorityCategories(z, i), priorityCallSenders, priorityMessageSenders, this.mPolicy.suppressedVisualEffects);
        if (ZenModeSettingsBase.DEBUG) {
            Log.d(this.TAG, "onPrefChange allow" + str + "=" + z + " allow" + str + "From=" + ZenModeConfig.sourceToString(i2));
        }
    }

    private int getPrioritySenders(int i) {
        if (i == 8) {
            return getPriorityCallSenders();
        }
        if (i == 4) {
            return getPriorityMessageSenders();
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int getContactsSummary(int r3) {
        /*
            r2 = this;
            r0 = 4
            if (r3 != r0) goto Le
            boolean r1 = r2.isPriorityCategoryEnabled(r3)
            if (r1 == 0) goto L1d
            int r2 = r2.getPriorityMessageSenders()
            goto L1e
        Le:
            r1 = 8
            if (r3 != r1) goto L1d
            boolean r1 = r2.isPriorityCategoryEnabled(r3)
            if (r1 == 0) goto L1d
            int r2 = r2.getPriorityCallSenders()
            goto L1e
        L1d:
            r2 = -1
        L1e:
            if (r2 == 0) goto L38
            r1 = 1
            if (r2 == r1) goto L34
            r1 = 2
            if (r2 == r1) goto L30
            if (r3 != r0) goto L2c
            r2 = 2131890735(0x7f12122f, float:1.941617E38)
            return r2
        L2c:
            r2 = 2131890734(0x7f12122e, float:1.9416168E38)
            return r2
        L30:
            r2 = 2131890736(0x7f121230, float:1.9416172E38)
            return r2
        L34:
            r2 = 2131890732(0x7f12122c, float:1.9416164E38)
            return r2
        L38:
            r2 = 2131890731(0x7f12122b, float:1.9416162E38)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.notification.ZenModeBackend.getContactsSummary(int):int");
    }

    protected int getContactsCallsSummary(ZenPolicy zenPolicy) {
        int priorityCallSenders = zenPolicy.getPriorityCallSenders();
        return priorityCallSenders != 1 ? priorityCallSenders != 2 ? priorityCallSenders != 3 ? R.string.zen_mode_from_none_calls : R.string.zen_mode_from_starred : R.string.zen_mode_from_contacts : R.string.zen_mode_from_anyone;
    }

    protected int getContactsMessagesSummary(ZenPolicy zenPolicy) {
        int priorityMessageSenders = zenPolicy.getPriorityMessageSenders();
        return priorityMessageSenders != 1 ? priorityMessageSenders != 2 ? priorityMessageSenders != 3 ? R.string.zen_mode_from_none_messages : R.string.zen_mode_from_starred : R.string.zen_mode_from_contacts : R.string.zen_mode_from_anyone;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected static int getZenPolicySettingFromPrefKey(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r0) {
                case -946901971: goto L29;
                case -423126328: goto L1f;
                case 187510959: goto L15;
                case 462773226: goto Lb;
                default: goto La;
            }
        La:
            goto L33
        Lb:
            java.lang.String r0 = "zen_mode_from_starred"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L33
            r4 = r2
            goto L34
        L15:
            java.lang.String r0 = "zen_mode_from_anyone"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L33
            r4 = 0
            goto L34
        L1f:
            java.lang.String r0 = "zen_mode_from_contacts"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L33
            r4 = r3
            goto L34
        L29:
            java.lang.String r0 = "zen_mode_from_none"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L33
            r4 = r1
            goto L34
        L33:
            r4 = -1
        L34:
            if (r4 == 0) goto L3e
            if (r4 == r3) goto L3d
            if (r4 == r2) goto L3c
            r4 = 4
            return r4
        L3c:
            return r1
        L3d:
            return r2
        L3e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.notification.ZenModeBackend.getZenPolicySettingFromPrefKey(java.lang.String):int");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    protected static int getSettingFromPrefKey(String str) {
        byte b;
        switch (str.hashCode()) {
            case -946901971:
                b = !str.equals(ZEN_MODE_FROM_NONE) ? (byte) -1 : (byte) 3;
                break;
            case -423126328:
                b = !str.equals(ZEN_MODE_FROM_CONTACTS) ? (byte) -1 : (byte) 1;
                break;
            case 187510959:
                b = !str.equals(ZEN_MODE_FROM_ANYONE) ? (byte) -1 : (byte) 0;
                break;
            case 462773226:
                b = !str.equals(ZEN_MODE_FROM_STARRED) ? (byte) -1 : (byte) 2;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0) {
            return 0;
        }
        if (b != 1) {
            return b != 2 ? -1 : 2;
        }
        return 1;
    }

    public boolean removeZenRule(String str) {
        return NotificationManager.from(this.mContext).removeAutomaticZenRule(str);
    }

    public NotificationManager.Policy getConsolidatedPolicy() {
        return NotificationManager.from(this.mContext).getConsolidatedNotificationPolicy();
    }

    protected String addZenRule(AutomaticZenRule automaticZenRule) {
        try {
            return NotificationManager.from(this.mContext).addAutomaticZenRule(automaticZenRule);
        } catch (Exception unused) {
            return null;
        }
    }

    ZenPolicy setDefaultZenPolicy(ZenPolicy zenPolicy) {
        return new ZenPolicy.Builder(zenPolicy).allowAlarms(this.mPolicy.allowAlarms()).allowCalls(this.mPolicy.allowCalls() ? ZenModeConfig.getZenPolicySenders(this.mPolicy.allowCallsFrom()) : 4).allowEvents(this.mPolicy.allowEvents()).allowMedia(this.mPolicy.allowMedia()).allowMessages(this.mPolicy.allowMessages() ? ZenModeConfig.getZenPolicySenders(this.mPolicy.allowMessagesFrom()) : 4).allowReminders(this.mPolicy.allowReminders()).allowRepeatCallers(this.mPolicy.allowRepeatCallers()).allowSystem(this.mPolicy.allowSystem()).showFullScreenIntent(this.mPolicy.showFullScreenIntents()).showLights(this.mPolicy.showLights()).showInAmbientDisplay(this.mPolicy.showAmbient()).showInNotificationList(this.mPolicy.showInNotificationList()).showBadges(this.mPolicy.showBadges()).showPeeking(this.mPolicy.showPeeking()).showStatusBarIcons(this.mPolicy.showStatusBarIcons()).build();
    }

    protected Map.Entry<String, AutomaticZenRule>[] getAutomaticZenRules() {
        Map<String, AutomaticZenRule> automaticZenRules = NotificationManager.from(this.mContext).getAutomaticZenRules();
        Map.Entry<String, AutomaticZenRule>[] entryArr = (Map.Entry[]) automaticZenRules.entrySet().toArray(new Map.Entry[automaticZenRules.size()]);
        Arrays.sort(entryArr, RULE_COMPARATOR);
        return entryArr;
    }

    protected AutomaticZenRule getAutomaticZenRule(String str) {
        return NotificationManager.from(this.mContext).getAutomaticZenRule(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> getDefaultRuleIds() {
        if (mDefaultRuleIds == null) {
            mDefaultRuleIds = ZenModeConfig.DEFAULT_RULE_IDS;
        }
        return mDefaultRuleIds;
    }

    NotificationManager.Policy toNotificationPolicy(ZenPolicy zenPolicy) {
        return new ZenModeConfig().toNotificationPolicy(zenPolicy);
    }

    List<String> getStarredContacts(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String string = cursor.getString(0);
                if (string != null) {
                    arrayList.add(string);
                }
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    private List<String> getStarredContacts() throws Throwable {
        Cursor cursorQueryData;
        try {
            cursorQueryData = queryData();
            try {
                List<String> starredContacts = getStarredContacts(cursorQueryData);
                if (cursorQueryData != null) {
                    cursorQueryData.close();
                }
                return starredContacts;
            } catch (Throwable th) {
                th = th;
                if (cursorQueryData != null) {
                    cursorQueryData.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursorQueryData = null;
        }
    }

    public String getStarredContactsSummary() throws Throwable {
        List<String> starredContacts = getStarredContacts();
        int size = starredContacts.size();
        ArrayList arrayList = new ArrayList();
        if (size == 0) {
            arrayList.add(this.mContext.getString(R.string.zen_mode_from_none));
        } else {
            for (int i = 0; i < 2 && i < size; i++) {
                arrayList.add(starredContacts.get(i));
            }
            if (size == 3) {
                arrayList.add(starredContacts.get(2));
            } else if (size > 2) {
                int i2 = size - 2;
                arrayList.add(this.mContext.getResources().getQuantityString(R.plurals.zen_mode_starred_contacts_summary_additional_contacts, i2, Integer.valueOf(i2)));
            }
        }
        return ListFormatter.getInstance().format(arrayList);
    }

    private Cursor queryData() {
        return this.mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"display_name"}, "starred=1", null, "times_contacted");
    }
}
