package android.service.notification;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.MediaMetrics;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Contacts;
import android.provider.Settings;
import android.provider.SettingsStringUtil;
import android.provider.Telephony;
import android.service.notification.ZenPolicy;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Slog;
import android.util.proto.ProtoOutputStream;
import com.android.internal.R;
import com.android.internal.content.NativeLibraryHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes3.dex */
public class ZenModeConfig implements Parcelable {
    private static final String ALLOW_ATT_ALARMS = "alarms";
    private static final String ALLOW_ATT_CALLS = "calls";
    private static final String ALLOW_ATT_CALLS_FROM = "callsFrom";
    private static final String ALLOW_ATT_CONV = "convos";
    private static final String ALLOW_ATT_CONV_FROM = "convosFrom";
    private static final String ALLOW_ATT_EVENTS = "events";
    private static final String ALLOW_ATT_FROM = "from";
    private static final String ALLOW_ATT_MEDIA = "media";
    private static final String ALLOW_ATT_MESSAGES = "messages";
    private static final String ALLOW_ATT_MESSAGES_FROM = "messagesFrom";
    private static final String ALLOW_ATT_REMINDERS = "reminders";
    private static final String ALLOW_ATT_REPEAT_CALLERS = "repeatCallers";
    private static final String ALLOW_ATT_SCREEN_OFF = "visualScreenOff";
    private static final String ALLOW_ATT_SCREEN_ON = "visualScreenOn";
    private static final String ALLOW_ATT_SYSTEM = "system";
    private static final String ALLOW_TAG = "allow";
    private static final String AUTOMATIC_TAG = "automatic";
    private static final String CONDITION_ATT_FLAGS = "flags";
    private static final String CONDITION_ATT_ICON = "icon";
    private static final String CONDITION_ATT_ID = "id";
    private static final String CONDITION_ATT_LINE1 = "line1";
    private static final String CONDITION_ATT_LINE2 = "line2";
    private static final String CONDITION_ATT_STATE = "state";
    private static final String CONDITION_ATT_SUMMARY = "summary";
    public static final String COUNTDOWN_PATH = "countdown";
    private static final int DAY_MINUTES = 1440;
    private static final boolean DEFAULT_ALLOW_ALARMS = true;
    private static final boolean DEFAULT_ALLOW_CALLS = true;
    private static final boolean DEFAULT_ALLOW_CONV = false;
    private static final int DEFAULT_ALLOW_CONV_FROM = 3;
    private static final boolean DEFAULT_ALLOW_EVENTS = false;
    private static final boolean DEFAULT_ALLOW_MEDIA = true;
    private static final boolean DEFAULT_ALLOW_MESSAGES = false;
    private static final boolean DEFAULT_ALLOW_REMINDERS = false;
    private static final boolean DEFAULT_ALLOW_REPEAT_CALLERS = true;
    private static final boolean DEFAULT_ALLOW_SYSTEM = false;
    private static final int DEFAULT_CALLS_SOURCE = 2;
    private static final boolean DEFAULT_CHANNELS_BYPASSING_DND = false;
    private static final int DEFAULT_SOURCE = 1;
    private static final int DEFAULT_SUPPRESSED_VISUAL_EFFECTS = 0;
    private static final String DISALLOW_ATT_VISUAL_EFFECTS = "visualEffects";
    private static final String DISALLOW_TAG = "disallow";
    public static final String EVENT_PATH = "event";
    public static final String IS_ALARM_PATH = "alarm";
    public static final String MANUAL_RULE_ID = "MANUAL_RULE";
    private static final String MANUAL_TAG = "manual";
    public static final int MAX_SOURCE = 2;
    private static final int MINUTES_MS = 60000;
    private static final String RULE_ATT_COMPONENT = "component";
    private static final String RULE_ATT_CONDITION_ID = "conditionId";
    private static final String RULE_ATT_CONFIG_ACTIVITY = "configActivity";
    private static final String RULE_ATT_CREATION_TIME = "creationTime";
    private static final String RULE_ATT_ENABLED = "enabled";
    private static final String RULE_ATT_ENABLER = "enabler";
    private static final String RULE_ATT_ID = "ruleId";
    private static final String RULE_ATT_MODIFIED = "modified";
    private static final String RULE_ATT_NAME = "name";
    private static final String RULE_ATT_SNOOZING = "snoozing";
    private static final String RULE_ATT_ZEN = "zen";
    public static final String SCHEDULE_PATH = "schedule";
    private static final int SECONDS_MS = 1000;
    private static final String SHOW_ATT_AMBIENT = "showAmbient";
    private static final String SHOW_ATT_BADGES = "showBadges";
    private static final String SHOW_ATT_FULL_SCREEN_INTENT = "showFullScreenIntent";
    private static final String SHOW_ATT_LIGHTS = "showLights";
    private static final String SHOW_ATT_NOTIFICATION_LIST = "showNotificationList";
    private static final String SHOW_ATT_PEEK = "shoePeek";
    private static final String SHOW_ATT_STATUS_BAR_ICONS = "showStatusBarIcons";
    public static final int SOURCE_ANYONE = 0;
    public static final int SOURCE_CONTACT = 1;
    public static final int SOURCE_STAR = 2;
    private static final String STATE_ATT_CHANNELS_BYPASSING_DND = "areChannelsBypassingDnd";
    private static final String STATE_TAG = "state";
    public static final String SYSTEM_AUTHORITY = "android";
    private static String TAG = "ZenModeConfig";
    public static final int XML_VERSION = 8;
    private static final String ZEN_ATT_USER = "user";
    private static final String ZEN_ATT_VERSION = "version";
    private static final String ZEN_POLICY_TAG = "zen_policy";
    public static final String ZEN_TAG = "zen";
    private static final int ZERO_VALUE_MS = 10000;
    public boolean allowAlarms;
    public boolean allowCalls;
    public int allowCallsFrom;
    public boolean allowConversations;
    public int allowConversationsFrom;
    public boolean allowEvents;
    public boolean allowMedia;
    public boolean allowMessages;
    public int allowMessagesFrom;
    public boolean allowReminders;
    public boolean allowRepeatCallers;
    public boolean allowSystem;
    public boolean areChannelsBypassingDnd;
    public ArrayMap<String, ZenRule> automaticRules;
    public ZenRule manualRule;
    public int suppressedVisualEffects;
    public int user;
    public int version;
    public static final String EVERY_NIGHT_DEFAULT_RULE_ID = "EVERY_NIGHT_DEFAULT_RULE";
    public static final String EVENTS_DEFAULT_RULE_ID = "EVENTS_DEFAULT_RULE";
    public static final List<String> DEFAULT_RULE_IDS = Arrays.asList(EVERY_NIGHT_DEFAULT_RULE_ID, EVENTS_DEFAULT_RULE_ID);
    public static final int[] ALL_DAYS = {1, 2, 3, 4, 5, 6, 7};
    public static final int[] MINUTE_BUCKETS = generateMinuteBuckets();
    public static final Parcelable.Creator<ZenModeConfig> CREATOR = new Parcelable.Creator<ZenModeConfig>() { // from class: android.service.notification.ZenModeConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ZenModeConfig createFromParcel(Parcel parcel) {
            return new ZenModeConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ZenModeConfig[] newArray(int i) {
            return new ZenModeConfig[i];
        }
    };

    private int getNotificationPolicySenders(int i, int i2) {
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        if (i != 3) {
            return i2;
        }
        return 2;
    }

    public static int getZenPolicySenders(int i) {
        if (i != 0) {
            return i != 1 ? 3 : 2;
        }
        return 1;
    }

    public static boolean isValidHour(int i) {
        return i >= 0 && i < 24;
    }

    public static boolean isValidMinute(int i) {
        return i >= 0 && i < 60;
    }

    private static boolean isValidSource(int i) {
        return i >= 0 && i <= 2;
    }

    private static int normalizeConversationSenders(boolean z, int i, int i2) {
        if (z) {
            return (i == 1 || i == 2 || i == 3) ? i : i2;
        }
        return 3;
    }

    private static int normalizePrioritySenders(int i, int i2) {
        return (i == 1 || i == 2 || i == 0) ? i : i2;
    }

    private static int prioritySendersToSource(int i, int i2) {
        if (i == 0) {
            return 0;
        }
        int i3 = 1;
        if (i != 1) {
            i3 = 2;
            if (i != 2) {
                return i2;
            }
        }
        return i3;
    }

    private static int sourceToPrioritySenders(int i, int i2) {
        if (i == 0) {
            return 0;
        }
        int i3 = 1;
        if (i != 1) {
            i3 = 2;
            if (i != 2) {
                return i2;
            }
        }
        return i3;
    }

    public static String sourceToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "UNKNOWN" : "stars" : Contacts.AUTHORITY : "anyone";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ZenModeConfig() {
        this.allowAlarms = true;
        this.allowMedia = true;
        this.allowSystem = false;
        this.allowCalls = true;
        this.allowRepeatCallers = true;
        this.allowMessages = false;
        this.allowReminders = false;
        this.allowEvents = false;
        this.allowCallsFrom = 2;
        this.allowMessagesFrom = 1;
        this.allowConversations = false;
        this.allowConversationsFrom = 3;
        this.user = 0;
        this.suppressedVisualEffects = 0;
        this.areChannelsBypassingDnd = false;
        this.automaticRules = new ArrayMap<>();
    }

    public ZenModeConfig(Parcel parcel) {
        this.allowAlarms = true;
        this.allowMedia = true;
        this.allowSystem = false;
        this.allowCalls = true;
        this.allowRepeatCallers = true;
        this.allowMessages = false;
        this.allowReminders = false;
        this.allowEvents = false;
        this.allowCallsFrom = 2;
        this.allowMessagesFrom = 1;
        this.allowConversations = false;
        this.allowConversationsFrom = 3;
        this.user = 0;
        this.suppressedVisualEffects = 0;
        this.areChannelsBypassingDnd = false;
        this.automaticRules = new ArrayMap<>();
        this.allowCalls = parcel.readInt() == 1;
        this.allowRepeatCallers = parcel.readInt() == 1;
        this.allowMessages = parcel.readInt() == 1;
        this.allowReminders = parcel.readInt() == 1;
        this.allowEvents = parcel.readInt() == 1;
        this.allowCallsFrom = parcel.readInt();
        this.allowMessagesFrom = parcel.readInt();
        this.user = parcel.readInt();
        this.manualRule = (ZenRule) parcel.readParcelable(null);
        int i = parcel.readInt();
        if (i > 0) {
            String[] strArr = new String[i];
            ZenRule[] zenRuleArr = new ZenRule[i];
            parcel.readStringArray(strArr);
            parcel.readTypedArray(zenRuleArr, ZenRule.CREATOR);
            for (int i2 = 0; i2 < i; i2++) {
                this.automaticRules.put(strArr[i2], zenRuleArr[i2]);
            }
        }
        this.allowAlarms = parcel.readInt() == 1;
        this.allowMedia = parcel.readInt() == 1;
        this.allowSystem = parcel.readInt() == 1;
        this.suppressedVisualEffects = parcel.readInt();
        this.areChannelsBypassingDnd = parcel.readInt() == 1;
        this.allowConversations = parcel.readBoolean();
        this.allowConversationsFrom = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.allowCalls ? 1 : 0);
        parcel.writeInt(this.allowRepeatCallers ? 1 : 0);
        parcel.writeInt(this.allowMessages ? 1 : 0);
        parcel.writeInt(this.allowReminders ? 1 : 0);
        parcel.writeInt(this.allowEvents ? 1 : 0);
        parcel.writeInt(this.allowCallsFrom);
        parcel.writeInt(this.allowMessagesFrom);
        parcel.writeInt(this.user);
        parcel.writeParcelable(this.manualRule, 0);
        if (!this.automaticRules.isEmpty()) {
            int size = this.automaticRules.size();
            String[] strArr = new String[size];
            ZenRule[] zenRuleArr = new ZenRule[size];
            for (int i2 = 0; i2 < size; i2++) {
                strArr[i2] = this.automaticRules.keyAt(i2);
                zenRuleArr[i2] = this.automaticRules.valueAt(i2);
            }
            parcel.writeInt(size);
            parcel.writeStringArray(strArr);
            parcel.writeTypedArray(zenRuleArr, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.allowAlarms ? 1 : 0);
        parcel.writeInt(this.allowMedia ? 1 : 0);
        parcel.writeInt(this.allowSystem ? 1 : 0);
        parcel.writeInt(this.suppressedVisualEffects);
        parcel.writeInt(this.areChannelsBypassingDnd ? 1 : 0);
        parcel.writeBoolean(this.allowConversations);
        parcel.writeInt(this.allowConversationsFrom);
    }

    public String toString() {
        return ZenModeConfig.class.getSimpleName() + "[user=" + this.user + ",allowAlarms=" + this.allowAlarms + ",allowMedia=" + this.allowMedia + ",allowSystem=" + this.allowSystem + ",allowReminders=" + this.allowReminders + ",allowEvents=" + this.allowEvents + ",allowCalls=" + this.allowCalls + ",allowRepeatCallers=" + this.allowRepeatCallers + ",allowMessages=" + this.allowMessages + ",allowConversations=" + this.allowConversations + ",allowCallsFrom=" + sourceToString(this.allowCallsFrom) + ",allowMessagesFrom=" + sourceToString(this.allowMessagesFrom) + ",allowConvFrom=" + ZenPolicy.conversationTypeToString(this.allowConversationsFrom) + ",suppressedVisualEffects=" + this.suppressedVisualEffects + ",areChannelsBypassingDnd=" + this.areChannelsBypassingDnd + ",\nautomaticRules=" + rulesToString() + ",\nmanualRule=" + this.manualRule + ']';
    }

    private String rulesToString() {
        if (this.automaticRules.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.automaticRules.size() * 28);
        sb.append('{');
        for (int i = 0; i < this.automaticRules.size(); i++) {
            if (i > 0) {
                sb.append(",\n");
            }
            sb.append(this.automaticRules.valueAt(i));
        }
        sb.append('}');
        return sb.toString();
    }

    public Diff diff(ZenModeConfig zenModeConfig) {
        Diff diff = new Diff();
        if (zenModeConfig != null) {
            int i = this.user;
            if (i != zenModeConfig.user) {
                diff.addLine("user", Integer.valueOf(i), Integer.valueOf(zenModeConfig.user));
            }
            boolean z = this.allowAlarms;
            if (z != zenModeConfig.allowAlarms) {
                diff.addLine("allowAlarms", Boolean.valueOf(z), Boolean.valueOf(zenModeConfig.allowAlarms));
            }
            boolean z2 = this.allowMedia;
            if (z2 != zenModeConfig.allowMedia) {
                diff.addLine("allowMedia", Boolean.valueOf(z2), Boolean.valueOf(zenModeConfig.allowMedia));
            }
            boolean z3 = this.allowSystem;
            if (z3 != zenModeConfig.allowSystem) {
                diff.addLine("allowSystem", Boolean.valueOf(z3), Boolean.valueOf(zenModeConfig.allowSystem));
            }
            boolean z4 = this.allowCalls;
            if (z4 != zenModeConfig.allowCalls) {
                diff.addLine("allowCalls", Boolean.valueOf(z4), Boolean.valueOf(zenModeConfig.allowCalls));
            }
            boolean z5 = this.allowReminders;
            if (z5 != zenModeConfig.allowReminders) {
                diff.addLine("allowReminders", Boolean.valueOf(z5), Boolean.valueOf(zenModeConfig.allowReminders));
            }
            boolean z6 = this.allowEvents;
            if (z6 != zenModeConfig.allowEvents) {
                diff.addLine("allowEvents", Boolean.valueOf(z6), Boolean.valueOf(zenModeConfig.allowEvents));
            }
            boolean z7 = this.allowRepeatCallers;
            if (z7 != zenModeConfig.allowRepeatCallers) {
                diff.addLine("allowRepeatCallers", Boolean.valueOf(z7), Boolean.valueOf(zenModeConfig.allowRepeatCallers));
            }
            boolean z8 = this.allowMessages;
            if (z8 != zenModeConfig.allowMessages) {
                diff.addLine("allowMessages", Boolean.valueOf(z8), Boolean.valueOf(zenModeConfig.allowMessages));
            }
            int i2 = this.allowCallsFrom;
            if (i2 != zenModeConfig.allowCallsFrom) {
                diff.addLine("allowCallsFrom", Integer.valueOf(i2), Integer.valueOf(zenModeConfig.allowCallsFrom));
            }
            int i3 = this.allowMessagesFrom;
            if (i3 != zenModeConfig.allowMessagesFrom) {
                diff.addLine("allowMessagesFrom", Integer.valueOf(i3), Integer.valueOf(zenModeConfig.allowMessagesFrom));
            }
            int i4 = this.suppressedVisualEffects;
            if (i4 != zenModeConfig.suppressedVisualEffects) {
                diff.addLine("suppressedVisualEffects", Integer.valueOf(i4), Integer.valueOf(zenModeConfig.suppressedVisualEffects));
            }
            ArraySet arraySet = new ArraySet();
            addKeys(arraySet, this.automaticRules);
            addKeys(arraySet, zenModeConfig.automaticRules);
            int size = arraySet.size();
            for (int i5 = 0; i5 < size; i5++) {
                String str = (String) arraySet.valueAt(i5);
                ArrayMap<String, ZenRule> arrayMap = this.automaticRules;
                ZenRule zenRule = null;
                ZenRule zenRule2 = arrayMap != null ? arrayMap.get(str) : null;
                ArrayMap<String, ZenRule> arrayMap2 = zenModeConfig.automaticRules;
                if (arrayMap2 != null) {
                    zenRule = arrayMap2.get(str);
                }
                ZenRule.appendDiff(diff, "automaticRule[" + str + "]", zenRule2, zenRule);
            }
            ZenRule.appendDiff(diff, "manualRule", this.manualRule, zenModeConfig.manualRule);
            boolean z9 = this.areChannelsBypassingDnd;
            if (z9 != zenModeConfig.areChannelsBypassingDnd) {
                diff.addLine(STATE_ATT_CHANNELS_BYPASSING_DND, Boolean.valueOf(z9), Boolean.valueOf(zenModeConfig.areChannelsBypassingDnd));
            }
            return diff;
        }
        return diff.addLine("config", "delete");
    }

    public static Diff diff(ZenModeConfig zenModeConfig, ZenModeConfig zenModeConfig2) {
        if (zenModeConfig == null) {
            Diff diff = new Diff();
            if (zenModeConfig2 != null) {
                diff.addLine("config", "insert");
            }
            return diff;
        }
        return zenModeConfig.diff(zenModeConfig2);
    }

    private static <T> void addKeys(ArraySet<T> arraySet, ArrayMap<T, ?> arrayMap) {
        if (arrayMap != null) {
            for (int i = 0; i < arrayMap.size(); i++) {
                arraySet.add(arrayMap.keyAt(i));
            }
        }
    }

    public boolean isValid() {
        if (!isValidManualRule(this.manualRule)) {
            return false;
        }
        int size = this.automaticRules.size();
        for (int i = 0; i < size; i++) {
            if (!isValidAutomaticRule(this.automaticRules.valueAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidManualRule(ZenRule zenRule) {
        return zenRule == null || (Settings.Global.isValidZenMode(zenRule.zenMode) && sameCondition(zenRule));
    }

    private static boolean isValidAutomaticRule(ZenRule zenRule) {
        return (zenRule == null || TextUtils.isEmpty(zenRule.name) || !Settings.Global.isValidZenMode(zenRule.zenMode) || zenRule.conditionId == null || !sameCondition(zenRule)) ? false : true;
    }

    private static boolean sameCondition(ZenRule zenRule) {
        if (zenRule == null) {
            return false;
        }
        return zenRule.conditionId == null ? zenRule.condition == null : zenRule.condition == null || zenRule.conditionId.equals(zenRule.condition.id);
    }

    private static int[] generateMinuteBuckets() {
        int[] iArr = new int[15];
        iArr[0] = 15;
        iArr[1] = 30;
        iArr[2] = 45;
        for (int i = 1; i <= 12; i++) {
            iArr[i + 2] = i * 60;
        }
        return iArr;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ZenModeConfig)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ZenModeConfig zenModeConfig = (ZenModeConfig) obj;
        return zenModeConfig.allowAlarms == this.allowAlarms && zenModeConfig.allowMedia == this.allowMedia && zenModeConfig.allowSystem == this.allowSystem && zenModeConfig.allowCalls == this.allowCalls && zenModeConfig.allowRepeatCallers == this.allowRepeatCallers && zenModeConfig.allowMessages == this.allowMessages && zenModeConfig.allowCallsFrom == this.allowCallsFrom && zenModeConfig.allowMessagesFrom == this.allowMessagesFrom && zenModeConfig.allowReminders == this.allowReminders && zenModeConfig.allowEvents == this.allowEvents && zenModeConfig.user == this.user && Objects.equals(zenModeConfig.automaticRules, this.automaticRules) && Objects.equals(zenModeConfig.manualRule, this.manualRule) && zenModeConfig.suppressedVisualEffects == this.suppressedVisualEffects && zenModeConfig.areChannelsBypassingDnd == this.areChannelsBypassingDnd && zenModeConfig.allowConversations == this.allowConversations && zenModeConfig.allowConversationsFrom == this.allowConversationsFrom;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.allowAlarms), Boolean.valueOf(this.allowMedia), Boolean.valueOf(this.allowSystem), Boolean.valueOf(this.allowCalls), Boolean.valueOf(this.allowRepeatCallers), Boolean.valueOf(this.allowMessages), Integer.valueOf(this.allowCallsFrom), Integer.valueOf(this.allowMessagesFrom), Boolean.valueOf(this.allowReminders), Boolean.valueOf(this.allowEvents), Integer.valueOf(this.user), this.automaticRules, this.manualRule, Integer.valueOf(this.suppressedVisualEffects), Boolean.valueOf(this.areChannelsBypassingDnd), Boolean.valueOf(this.allowConversations), Integer.valueOf(this.allowConversationsFrom));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String toDayList(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iArr.length; i++) {
            if (i > 0) {
                sb.append('.');
            }
            sb.append(iArr[i]);
        }
        return sb.toString();
    }

    private static int[] tryParseDayList(String str, String str2) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(str2);
        if (strArrSplit.length == 0) {
            return null;
        }
        int[] iArr = new int[strArrSplit.length];
        for (int i = 0; i < strArrSplit.length; i++) {
            int iTryParseInt = tryParseInt(strArrSplit[i], -1);
            if (iTryParseInt == -1) {
                return null;
            }
            iArr[i] = iTryParseInt;
        }
        return iArr;
    }

    private static int tryParseInt(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    private static long tryParseLong(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    private static Long tryParseLong(String str, Long l) {
        if (TextUtils.isEmpty(str)) {
            return l;
        }
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException unused) {
            return l;
        }
    }

    public static ZenModeConfig readXml(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() != 2 || !"zen".equals(xmlPullParser.getName())) {
            return null;
        }
        ZenModeConfig zenModeConfig = new ZenModeConfig();
        zenModeConfig.version = safeInt(xmlPullParser, "version", 8);
        zenModeConfig.user = safeInt(xmlPullParser, "user", zenModeConfig.user);
        boolean z = false;
        while (true) {
            int next = xmlPullParser.next();
            if (next != 1) {
                String name = xmlPullParser.getName();
                if (next == 3 && "zen".equals(name)) {
                    return zenModeConfig;
                }
                if (next == 2) {
                    if (ALLOW_TAG.equals(name)) {
                        zenModeConfig.allowCalls = safeBoolean(xmlPullParser, ALLOW_ATT_CALLS, true);
                        zenModeConfig.allowRepeatCallers = safeBoolean(xmlPullParser, ALLOW_ATT_REPEAT_CALLERS, true);
                        zenModeConfig.allowMessages = safeBoolean(xmlPullParser, ALLOW_ATT_MESSAGES, false);
                        zenModeConfig.allowReminders = safeBoolean(xmlPullParser, ALLOW_ATT_REMINDERS, false);
                        zenModeConfig.allowConversations = safeBoolean(xmlPullParser, ALLOW_ATT_CONV, false);
                        zenModeConfig.allowEvents = safeBoolean(xmlPullParser, ALLOW_ATT_EVENTS, false);
                        int iSafeInt = safeInt(xmlPullParser, ALLOW_ATT_FROM, -1);
                        int iSafeInt2 = safeInt(xmlPullParser, ALLOW_ATT_CALLS_FROM, -1);
                        int iSafeInt3 = safeInt(xmlPullParser, ALLOW_ATT_MESSAGES_FROM, -1);
                        zenModeConfig.allowConversationsFrom = safeInt(xmlPullParser, ALLOW_ATT_CONV_FROM, 3);
                        if (isValidSource(iSafeInt2) && isValidSource(iSafeInt3)) {
                            zenModeConfig.allowCallsFrom = iSafeInt2;
                            zenModeConfig.allowMessagesFrom = iSafeInt3;
                        } else if (isValidSource(iSafeInt)) {
                            Slog.i(TAG, "Migrating existing shared 'from': " + sourceToString(iSafeInt));
                            zenModeConfig.allowCallsFrom = iSafeInt;
                            zenModeConfig.allowMessagesFrom = iSafeInt;
                        } else {
                            zenModeConfig.allowCallsFrom = 2;
                            zenModeConfig.allowMessagesFrom = 1;
                        }
                        zenModeConfig.allowAlarms = safeBoolean(xmlPullParser, ALLOW_ATT_ALARMS, true);
                        zenModeConfig.allowMedia = safeBoolean(xmlPullParser, ALLOW_ATT_MEDIA, true);
                        zenModeConfig.allowSystem = safeBoolean(xmlPullParser, "system", false);
                        Boolean boolUnsafeBoolean = unsafeBoolean(xmlPullParser, ALLOW_ATT_SCREEN_OFF);
                        if (boolUnsafeBoolean != null) {
                            if (!boolUnsafeBoolean.booleanValue()) {
                                zenModeConfig.suppressedVisualEffects |= 12;
                            }
                            z = true;
                        }
                        Boolean boolUnsafeBoolean2 = unsafeBoolean(xmlPullParser, ALLOW_ATT_SCREEN_ON);
                        if (boolUnsafeBoolean2 != null) {
                            if (!boolUnsafeBoolean2.booleanValue()) {
                                zenModeConfig.suppressedVisualEffects |= 16;
                            }
                            z = true;
                        }
                        if (z) {
                            Slog.d(TAG, "Migrated visual effects to " + zenModeConfig.suppressedVisualEffects);
                        }
                    } else if (DISALLOW_TAG.equals(name) && !z) {
                        zenModeConfig.suppressedVisualEffects = safeInt(xmlPullParser, DISALLOW_ATT_VISUAL_EFFECTS, 0);
                    } else if ("manual".equals(name)) {
                        zenModeConfig.manualRule = readRuleXml(xmlPullParser);
                    } else if (AUTOMATIC_TAG.equals(name)) {
                        String attributeValue = xmlPullParser.getAttributeValue(null, RULE_ATT_ID);
                        ZenRule ruleXml = readRuleXml(xmlPullParser);
                        if (attributeValue != null && ruleXml != null) {
                            ruleXml.id = attributeValue;
                            zenModeConfig.automaticRules.put(attributeValue, ruleXml);
                        }
                    } else if ("state".equals(name)) {
                        zenModeConfig.areChannelsBypassingDnd = safeBoolean(xmlPullParser, STATE_ATT_CHANNELS_BYPASSING_DND, false);
                    }
                }
            } else {
                throw new IllegalStateException("Failed to reach END_DOCUMENT");
            }
        }
    }

    public void writeXml(XmlSerializer xmlSerializer, Integer num) throws IOException {
        xmlSerializer.startTag(null, "zen");
        xmlSerializer.attribute(null, "version", Integer.toString(num == null ? 8 : num.intValue()));
        xmlSerializer.attribute(null, "user", Integer.toString(this.user));
        xmlSerializer.startTag(null, ALLOW_TAG);
        xmlSerializer.attribute(null, ALLOW_ATT_CALLS, Boolean.toString(this.allowCalls));
        xmlSerializer.attribute(null, ALLOW_ATT_REPEAT_CALLERS, Boolean.toString(this.allowRepeatCallers));
        xmlSerializer.attribute(null, ALLOW_ATT_MESSAGES, Boolean.toString(this.allowMessages));
        xmlSerializer.attribute(null, ALLOW_ATT_REMINDERS, Boolean.toString(this.allowReminders));
        xmlSerializer.attribute(null, ALLOW_ATT_EVENTS, Boolean.toString(this.allowEvents));
        xmlSerializer.attribute(null, ALLOW_ATT_CALLS_FROM, Integer.toString(this.allowCallsFrom));
        xmlSerializer.attribute(null, ALLOW_ATT_MESSAGES_FROM, Integer.toString(this.allowMessagesFrom));
        xmlSerializer.attribute(null, ALLOW_ATT_ALARMS, Boolean.toString(this.allowAlarms));
        xmlSerializer.attribute(null, ALLOW_ATT_MEDIA, Boolean.toString(this.allowMedia));
        xmlSerializer.attribute(null, "system", Boolean.toString(this.allowSystem));
        xmlSerializer.attribute(null, ALLOW_ATT_CONV, Boolean.toString(this.allowConversations));
        xmlSerializer.attribute(null, ALLOW_ATT_CONV_FROM, Integer.toString(this.allowConversationsFrom));
        xmlSerializer.endTag(null, ALLOW_TAG);
        xmlSerializer.startTag(null, DISALLOW_TAG);
        xmlSerializer.attribute(null, DISALLOW_ATT_VISUAL_EFFECTS, Integer.toString(this.suppressedVisualEffects));
        xmlSerializer.endTag(null, DISALLOW_TAG);
        if (this.manualRule != null) {
            xmlSerializer.startTag(null, "manual");
            writeRuleXml(this.manualRule, xmlSerializer);
            xmlSerializer.endTag(null, "manual");
        }
        int size = this.automaticRules.size();
        for (int i = 0; i < size; i++) {
            String strKeyAt = this.automaticRules.keyAt(i);
            ZenRule zenRuleValueAt = this.automaticRules.valueAt(i);
            xmlSerializer.startTag(null, AUTOMATIC_TAG);
            xmlSerializer.attribute(null, RULE_ATT_ID, strKeyAt);
            writeRuleXml(zenRuleValueAt, xmlSerializer);
            xmlSerializer.endTag(null, AUTOMATIC_TAG);
        }
        xmlSerializer.startTag(null, "state");
        xmlSerializer.attribute(null, STATE_ATT_CHANNELS_BYPASSING_DND, Boolean.toString(this.areChannelsBypassingDnd));
        xmlSerializer.endTag(null, "state");
        xmlSerializer.endTag(null, "zen");
    }

    public static ZenRule readRuleXml(XmlPullParser xmlPullParser) {
        String packageName;
        ZenRule zenRule = new ZenRule();
        zenRule.enabled = safeBoolean(xmlPullParser, "enabled", true);
        zenRule.name = xmlPullParser.getAttributeValue(null, "name");
        String attributeValue = xmlPullParser.getAttributeValue(null, "zen");
        zenRule.zenMode = tryParseZenMode(attributeValue, -1);
        if (zenRule.zenMode == -1) {
            Slog.w(TAG, "Bad zen mode in rule xml:" + attributeValue);
            return null;
        }
        zenRule.conditionId = safeUri(xmlPullParser, RULE_ATT_CONDITION_ID);
        zenRule.component = safeComponentName(xmlPullParser, "component");
        zenRule.configurationActivity = safeComponentName(xmlPullParser, RULE_ATT_CONFIG_ACTIVITY);
        if (zenRule.component != null) {
            packageName = zenRule.component.getPackageName();
        } else {
            packageName = zenRule.configurationActivity != null ? zenRule.configurationActivity.getPackageName() : null;
        }
        zenRule.pkg = packageName;
        zenRule.creationTime = safeLong(xmlPullParser, "creationTime", 0L);
        zenRule.enabler = xmlPullParser.getAttributeValue(null, RULE_ATT_ENABLER);
        zenRule.condition = readConditionXml(xmlPullParser);
        if (zenRule.zenMode != 1 && Condition.isValidId(zenRule.conditionId, "android")) {
            Slog.i(TAG, "Updating zenMode of automatic rule " + zenRule.name);
            zenRule.zenMode = 1;
        }
        zenRule.modified = safeBoolean(xmlPullParser, "modified", false);
        zenRule.zenPolicy = readZenPolicyXml(xmlPullParser);
        return zenRule;
    }

    public static void writeRuleXml(ZenRule zenRule, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.attribute(null, "enabled", Boolean.toString(zenRule.enabled));
        if (zenRule.name != null) {
            xmlSerializer.attribute(null, "name", zenRule.name);
        }
        xmlSerializer.attribute(null, "zen", Integer.toString(zenRule.zenMode));
        if (zenRule.component != null) {
            xmlSerializer.attribute(null, "component", zenRule.component.flattenToString());
        }
        if (zenRule.configurationActivity != null) {
            xmlSerializer.attribute(null, RULE_ATT_CONFIG_ACTIVITY, zenRule.configurationActivity.flattenToString());
        }
        if (zenRule.conditionId != null) {
            xmlSerializer.attribute(null, RULE_ATT_CONDITION_ID, zenRule.conditionId.toString());
        }
        xmlSerializer.attribute(null, "creationTime", Long.toString(zenRule.creationTime));
        if (zenRule.enabler != null) {
            xmlSerializer.attribute(null, RULE_ATT_ENABLER, zenRule.enabler);
        }
        if (zenRule.condition != null) {
            writeConditionXml(zenRule.condition, xmlSerializer);
        }
        if (zenRule.zenPolicy != null) {
            writeZenPolicyXml(zenRule.zenPolicy, xmlSerializer);
        }
        xmlSerializer.attribute(null, "modified", Boolean.toString(zenRule.modified));
    }

    public static Condition readConditionXml(XmlPullParser xmlPullParser) {
        Uri uriSafeUri = safeUri(xmlPullParser, "id");
        if (uriSafeUri == null) {
            return null;
        }
        try {
            return new Condition(uriSafeUri, xmlPullParser.getAttributeValue(null, "summary"), xmlPullParser.getAttributeValue(null, CONDITION_ATT_LINE1), xmlPullParser.getAttributeValue(null, CONDITION_ATT_LINE2), safeInt(xmlPullParser, "icon", -1), safeInt(xmlPullParser, "state", -1), safeInt(xmlPullParser, "flags", -1));
        } catch (IllegalArgumentException e) {
            Slog.w(TAG, "Unable to read condition xml", e);
            return null;
        }
    }

    public static void writeConditionXml(Condition condition, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.attribute(null, "id", condition.id.toString());
        xmlSerializer.attribute(null, "summary", condition.summary);
        xmlSerializer.attribute(null, CONDITION_ATT_LINE1, condition.line1);
        xmlSerializer.attribute(null, CONDITION_ATT_LINE2, condition.line2);
        xmlSerializer.attribute(null, "icon", Integer.toString(condition.icon));
        xmlSerializer.attribute(null, "state", Integer.toString(condition.state));
        xmlSerializer.attribute(null, "flags", Integer.toString(condition.flags));
    }

    public static ZenPolicy readZenPolicyXml(XmlPullParser xmlPullParser) {
        boolean z;
        ZenPolicy.Builder builder = new ZenPolicy.Builder();
        int iSafeInt = safeInt(xmlPullParser, ALLOW_ATT_CALLS_FROM, 0);
        int iSafeInt2 = safeInt(xmlPullParser, ALLOW_ATT_MESSAGES_FROM, 0);
        int iSafeInt3 = safeInt(xmlPullParser, ALLOW_ATT_REPEAT_CALLERS, 0);
        int iSafeInt4 = safeInt(xmlPullParser, ALLOW_ATT_ALARMS, 0);
        int iSafeInt5 = safeInt(xmlPullParser, ALLOW_ATT_MEDIA, 0);
        int iSafeInt6 = safeInt(xmlPullParser, "system", 0);
        int iSafeInt7 = safeInt(xmlPullParser, ALLOW_ATT_EVENTS, 0);
        int iSafeInt8 = safeInt(xmlPullParser, ALLOW_ATT_REMINDERS, 0);
        boolean z2 = true;
        if (iSafeInt != 0) {
            builder.allowCalls(iSafeInt);
            z = true;
        } else {
            z = false;
        }
        if (iSafeInt2 != 0) {
            builder.allowMessages(iSafeInt2);
            z = true;
        }
        if (iSafeInt3 != 0) {
            builder.allowRepeatCallers(iSafeInt3 == 1);
            z = true;
        }
        if (iSafeInt4 != 0) {
            builder.allowAlarms(iSafeInt4 == 1);
            z = true;
        }
        if (iSafeInt5 != 0) {
            builder.allowMedia(iSafeInt5 == 1);
            z = true;
        }
        if (iSafeInt6 != 0) {
            builder.allowSystem(iSafeInt6 == 1);
            z = true;
        }
        if (iSafeInt7 != 0) {
            builder.allowEvents(iSafeInt7 == 1);
            z = true;
        }
        if (iSafeInt8 != 0) {
            builder.allowReminders(iSafeInt8 == 1);
            z = true;
        }
        int iSafeInt9 = safeInt(xmlPullParser, SHOW_ATT_FULL_SCREEN_INTENT, 0);
        int iSafeInt10 = safeInt(xmlPullParser, SHOW_ATT_LIGHTS, 0);
        int iSafeInt11 = safeInt(xmlPullParser, SHOW_ATT_PEEK, 0);
        int iSafeInt12 = safeInt(xmlPullParser, SHOW_ATT_STATUS_BAR_ICONS, 0);
        int iSafeInt13 = safeInt(xmlPullParser, SHOW_ATT_BADGES, 0);
        int iSafeInt14 = safeInt(xmlPullParser, SHOW_ATT_AMBIENT, 0);
        int iSafeInt15 = safeInt(xmlPullParser, SHOW_ATT_NOTIFICATION_LIST, 0);
        if (iSafeInt9 != 0) {
            builder.showFullScreenIntent(iSafeInt9 == 1);
            z = true;
        }
        if (iSafeInt10 != 0) {
            builder.showLights(iSafeInt10 == 1);
            z = true;
        }
        if (iSafeInt11 != 0) {
            builder.showPeeking(iSafeInt11 == 1);
            z = true;
        }
        if (iSafeInt12 != 0) {
            builder.showStatusBarIcons(iSafeInt12 == 1);
            z = true;
        }
        if (iSafeInt13 != 0) {
            builder.showBadges(iSafeInt13 == 1);
            z = true;
        }
        if (iSafeInt14 != 0) {
            builder.showInAmbientDisplay(iSafeInt14 == 1);
            z = true;
        }
        if (iSafeInt15 != 0) {
            builder.showInNotificationList(iSafeInt15 == 1);
        } else {
            z2 = z;
        }
        if (z2) {
            return builder.build();
        }
        return null;
    }

    public static void writeZenPolicyXml(ZenPolicy zenPolicy, XmlSerializer xmlSerializer) throws IOException {
        writeZenPolicyState(ALLOW_ATT_CALLS_FROM, zenPolicy.getPriorityCallSenders(), xmlSerializer);
        writeZenPolicyState(ALLOW_ATT_MESSAGES_FROM, zenPolicy.getPriorityMessageSenders(), xmlSerializer);
        writeZenPolicyState(ALLOW_ATT_REPEAT_CALLERS, zenPolicy.getPriorityCategoryRepeatCallers(), xmlSerializer);
        writeZenPolicyState(ALLOW_ATT_ALARMS, zenPolicy.getPriorityCategoryAlarms(), xmlSerializer);
        writeZenPolicyState(ALLOW_ATT_MEDIA, zenPolicy.getPriorityCategoryMedia(), xmlSerializer);
        writeZenPolicyState("system", zenPolicy.getPriorityCategorySystem(), xmlSerializer);
        writeZenPolicyState(ALLOW_ATT_REMINDERS, zenPolicy.getPriorityCategoryReminders(), xmlSerializer);
        writeZenPolicyState(ALLOW_ATT_EVENTS, zenPolicy.getPriorityCategoryEvents(), xmlSerializer);
        writeZenPolicyState(SHOW_ATT_FULL_SCREEN_INTENT, zenPolicy.getVisualEffectFullScreenIntent(), xmlSerializer);
        writeZenPolicyState(SHOW_ATT_LIGHTS, zenPolicy.getVisualEffectLights(), xmlSerializer);
        writeZenPolicyState(SHOW_ATT_PEEK, zenPolicy.getVisualEffectPeek(), xmlSerializer);
        writeZenPolicyState(SHOW_ATT_STATUS_BAR_ICONS, zenPolicy.getVisualEffectStatusBar(), xmlSerializer);
        writeZenPolicyState(SHOW_ATT_BADGES, zenPolicy.getVisualEffectBadge(), xmlSerializer);
        writeZenPolicyState(SHOW_ATT_AMBIENT, zenPolicy.getVisualEffectAmbient(), xmlSerializer);
        writeZenPolicyState(SHOW_ATT_NOTIFICATION_LIST, zenPolicy.getVisualEffectNotificationList(), xmlSerializer);
    }

    private static void writeZenPolicyState(String str, int i, XmlSerializer xmlSerializer) throws IOException {
        if (Objects.equals(str, ALLOW_ATT_CALLS_FROM) || Objects.equals(str, ALLOW_ATT_MESSAGES_FROM)) {
            if (i != 0) {
                xmlSerializer.attribute(null, str, Integer.toString(i));
            }
        } else if (i != 0) {
            xmlSerializer.attribute(null, str, Integer.toString(i));
        }
    }

    private static Boolean unsafeBoolean(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (TextUtils.isEmpty(attributeValue)) {
            return null;
        }
        return Boolean.valueOf(Boolean.parseBoolean(attributeValue));
    }

    private static boolean safeBoolean(XmlPullParser xmlPullParser, String str, boolean z) {
        return safeBoolean(xmlPullParser.getAttributeValue(null, str), z);
    }

    private static boolean safeBoolean(String str, boolean z) {
        return TextUtils.isEmpty(str) ? z : Boolean.parseBoolean(str);
    }

    private static int safeInt(XmlPullParser xmlPullParser, String str, int i) {
        return tryParseInt(xmlPullParser.getAttributeValue(null, str), i);
    }

    private static ComponentName safeComponentName(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (TextUtils.isEmpty(attributeValue)) {
            return null;
        }
        return ComponentName.unflattenFromString(attributeValue);
    }

    private static Uri safeUri(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (TextUtils.isEmpty(attributeValue)) {
            return null;
        }
        return Uri.parse(attributeValue);
    }

    private static long safeLong(XmlPullParser xmlPullParser, String str, long j) {
        return tryParseLong(xmlPullParser.getAttributeValue(null, str), j);
    }

    public ZenModeConfig copy() {
        Parcel parcelObtain = Parcel.obtain();
        try {
            writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            return new ZenModeConfig(parcelObtain);
        } finally {
            parcelObtain.recycle();
        }
    }

    public ZenPolicy toZenPolicy() {
        ZenPolicy.Builder builderAllowConversations = new ZenPolicy.Builder().allowCalls(this.allowCalls ? getZenPolicySenders(this.allowCallsFrom) : 4).allowRepeatCallers(this.allowRepeatCallers).allowMessages(this.allowMessages ? getZenPolicySenders(this.allowMessagesFrom) : 4).allowReminders(this.allowReminders).allowEvents(this.allowEvents).allowAlarms(this.allowAlarms).allowMedia(this.allowMedia).allowSystem(this.allowSystem).allowConversations(this.allowConversations ? getZenPolicySenders(this.allowConversationsFrom) : 4);
        int i = this.suppressedVisualEffects;
        if (i == 0) {
            builderAllowConversations.showAllVisualEffects();
        } else {
            builderAllowConversations.showFullScreenIntent((i & 4) == 0);
            builderAllowConversations.showLights((this.suppressedVisualEffects & 8) == 0);
            builderAllowConversations.showPeeking((this.suppressedVisualEffects & 16) == 0);
            builderAllowConversations.showStatusBarIcons((this.suppressedVisualEffects & 32) == 0);
            builderAllowConversations.showBadges((this.suppressedVisualEffects & 64) == 0);
            builderAllowConversations.showInAmbientDisplay((this.suppressedVisualEffects & 128) == 0);
            builderAllowConversations.showInNotificationList((this.suppressedVisualEffects & 256) == 0);
        }
        return builderAllowConversations.build();
    }

    public NotificationManager.Policy toNotificationPolicy(ZenPolicy zenPolicy) {
        NotificationManager.Policy notificationPolicy = toNotificationPolicy();
        int notificationPolicySenders = notificationPolicy.priorityCallSenders;
        int notificationPolicySenders2 = notificationPolicy.priorityMessageSenders;
        int notificationPolicySenders3 = notificationPolicy.priorityConversationSenders;
        int i = zenPolicy.isCategoryAllowed(0, isPriorityCategoryEnabled(1, notificationPolicy)) ? 1 : 0;
        if (zenPolicy.isCategoryAllowed(1, isPriorityCategoryEnabled(2, notificationPolicy))) {
            i |= 2;
        }
        if (zenPolicy.isCategoryAllowed(2, isPriorityCategoryEnabled(4, notificationPolicy))) {
            i |= 4;
            notificationPolicySenders2 = getNotificationPolicySenders(zenPolicy.getPriorityMessageSenders(), notificationPolicySenders2);
        }
        int i2 = notificationPolicySenders2;
        if (zenPolicy.isCategoryAllowed(8, isPriorityCategoryEnabled(256, notificationPolicy))) {
            i |= 256;
            notificationPolicySenders3 = getNotificationPolicySenders(zenPolicy.getPriorityConversationSenders(), notificationPolicySenders3);
        }
        int i3 = notificationPolicySenders3;
        if (zenPolicy.isCategoryAllowed(3, isPriorityCategoryEnabled(8, notificationPolicy))) {
            i |= 8;
            notificationPolicySenders = getNotificationPolicySenders(zenPolicy.getPriorityCallSenders(), notificationPolicySenders);
        }
        int i4 = notificationPolicySenders;
        if (zenPolicy.isCategoryAllowed(4, isPriorityCategoryEnabled(16, notificationPolicy))) {
            i |= 16;
        }
        if (zenPolicy.isCategoryAllowed(5, isPriorityCategoryEnabled(32, notificationPolicy))) {
            i |= 32;
        }
        if (zenPolicy.isCategoryAllowed(6, isPriorityCategoryEnabled(64, notificationPolicy))) {
            i |= 64;
        }
        int i5 = zenPolicy.isCategoryAllowed(7, isPriorityCategoryEnabled(128, notificationPolicy)) ? i | 128 : i;
        boolean z = !zenPolicy.isVisualEffectAllowed(0, isVisualEffectAllowed(4, notificationPolicy));
        boolean z2 = !zenPolicy.isVisualEffectAllowed(1, isVisualEffectAllowed(8, notificationPolicy));
        boolean z3 = !zenPolicy.isVisualEffectAllowed(5, isVisualEffectAllowed(128, notificationPolicy));
        int i6 = (z && z2 && z3) ? 1 : 0;
        if (z) {
            i6 |= 4;
        }
        if (z2) {
            i6 |= 8;
        }
        if (!zenPolicy.isVisualEffectAllowed(2, isVisualEffectAllowed(16, notificationPolicy))) {
            i6 = i6 | 16 | 2;
        }
        if (!zenPolicy.isVisualEffectAllowed(3, isVisualEffectAllowed(32, notificationPolicy))) {
            i6 |= 32;
        }
        if (!zenPolicy.isVisualEffectAllowed(4, isVisualEffectAllowed(64, notificationPolicy))) {
            i6 |= 64;
        }
        if (z3) {
            i6 |= 128;
        }
        return new NotificationManager.Policy(i5, i4, i2, !zenPolicy.isVisualEffectAllowed(6, isVisualEffectAllowed(256, notificationPolicy)) ? i6 | 256 : i6, notificationPolicy.state, i3);
    }

    private boolean isPriorityCategoryEnabled(int i, NotificationManager.Policy policy) {
        return (i & policy.priorityCategories) != 0;
    }

    private boolean isVisualEffectAllowed(int i, NotificationManager.Policy policy) {
        return (i & policy.suppressedVisualEffects) == 0;
    }

    public NotificationManager.Policy toNotificationPolicy() {
        int i = this.allowConversations ? 256 : 0;
        if (this.allowCalls) {
            i |= 8;
        }
        if (this.allowMessages) {
            i |= 4;
        }
        if (this.allowEvents) {
            i |= 2;
        }
        if (this.allowReminders) {
            i |= 1;
        }
        if (this.allowRepeatCallers) {
            i |= 16;
        }
        if (this.allowAlarms) {
            i |= 32;
        }
        if (this.allowMedia) {
            i |= 64;
        }
        if (this.allowSystem) {
            i |= 128;
        }
        return new NotificationManager.Policy(i, sourceToPrioritySenders(this.allowCallsFrom, 1), sourceToPrioritySenders(this.allowMessagesFrom, 1), this.suppressedVisualEffects, this.areChannelsBypassingDnd ? 1 : 0, this.allowConversationsFrom);
    }

    public static ScheduleCalendar toScheduleCalendar(Uri uri) {
        ScheduleInfo scheduleInfoTryParseScheduleConditionId = tryParseScheduleConditionId(uri);
        if (scheduleInfoTryParseScheduleConditionId == null || scheduleInfoTryParseScheduleConditionId.days == null || scheduleInfoTryParseScheduleConditionId.days.length == 0) {
            return null;
        }
        ScheduleCalendar scheduleCalendar = new ScheduleCalendar();
        scheduleCalendar.setSchedule(scheduleInfoTryParseScheduleConditionId);
        scheduleCalendar.setTimeZone(TimeZone.getDefault());
        return scheduleCalendar;
    }

    public void applyNotificationPolicy(NotificationManager.Policy policy) {
        if (policy == null) {
            return;
        }
        this.allowAlarms = (policy.priorityCategories & 32) != 0;
        this.allowMedia = (policy.priorityCategories & 64) != 0;
        this.allowSystem = (policy.priorityCategories & 128) != 0;
        this.allowEvents = (policy.priorityCategories & 2) != 0;
        this.allowReminders = (policy.priorityCategories & 1) != 0;
        this.allowCalls = (policy.priorityCategories & 8) != 0;
        this.allowMessages = (policy.priorityCategories & 4) != 0;
        this.allowRepeatCallers = (policy.priorityCategories & 16) != 0;
        this.allowCallsFrom = normalizePrioritySenders(policy.priorityCallSenders, this.allowCallsFrom);
        this.allowMessagesFrom = normalizePrioritySenders(policy.priorityMessageSenders, this.allowMessagesFrom);
        if (policy.suppressedVisualEffects != -1) {
            this.suppressedVisualEffects = policy.suppressedVisualEffects;
        }
        boolean z = (policy.priorityCategories & 256) != 0;
        this.allowConversations = z;
        this.allowConversationsFrom = normalizeConversationSenders(z, policy.priorityConversationSenders, this.allowConversationsFrom);
        if (policy.state != -1) {
            this.areChannelsBypassingDnd = (policy.state & 1) != 0;
        }
    }

    public static Condition toTimeCondition(Context context, int i, int i2) {
        return toTimeCondition(context, i, i2, false);
    }

    public static Condition toTimeCondition(Context context, int i, int i2, boolean z) {
        return toTimeCondition(context, System.currentTimeMillis() + (i == 0 ? JobInfo.MIN_BACKOFF_MILLIS : 60000 * i), i, i2, z);
    }

    public static Condition toTimeCondition(Context context, long j, int i, int i2, boolean z) {
        String string;
        String str;
        String str2;
        String quantityString;
        String quantityString2;
        String string2;
        CharSequence formattedTime = getFormattedTime(context, j, isToday(j), i2);
        Resources resources = context.getResources();
        if (i < 60) {
            quantityString = resources.getQuantityString(z ? R.plurals.zen_mode_duration_minutes_summary_short : R.plurals.zen_mode_duration_minutes_summary, i, Integer.valueOf(i), formattedTime);
            quantityString2 = resources.getQuantityString(z ? R.plurals.zen_mode_duration_minutes_short : R.plurals.zen_mode_duration_minutes, i, Integer.valueOf(i), formattedTime);
            string2 = resources.getString(R.string.zen_mode_until, formattedTime);
        } else if (i < 1440) {
            int iRound = Math.round(i / 60.0f);
            quantityString = resources.getQuantityString(z ? R.plurals.zen_mode_duration_hours_summary_short : R.plurals.zen_mode_duration_hours_summary, iRound, Integer.valueOf(iRound), formattedTime);
            quantityString2 = resources.getQuantityString(z ? R.plurals.zen_mode_duration_hours_short : R.plurals.zen_mode_duration_hours, iRound, Integer.valueOf(iRound), formattedTime);
            string2 = resources.getString(R.string.zen_mode_until, formattedTime);
        } else {
            string = resources.getString(R.string.zen_mode_until, formattedTime);
            str = string;
            str2 = str;
            return new Condition(toCountdownConditionId(j, false), string, str, str2, 0, 1, 1);
        }
        str2 = string2;
        str = quantityString2;
        string = quantityString;
        return new Condition(toCountdownConditionId(j, false), string, str, str2, 0, 1, 1);
    }

    public static Condition toNextAlarmCondition(Context context, long j, int i) {
        return new Condition(toCountdownConditionId(j, true), "", context.getResources().getString(R.string.zen_mode_until, getFormattedTime(context, j, isToday(j), i)), "", 0, 1, 1);
    }

    public static CharSequence getFormattedTime(Context context, long j, boolean z, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(!z ? "EEE " : "");
        sb.append(DateFormat.is24HourFormat(context, i) ? "Hm" : "hma");
        return DateFormat.format(DateFormat.getBestDateTimePattern(Locale.getDefault(), sb.toString()), j);
    }

    public static boolean isToday(long j) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        gregorianCalendar2.setTimeInMillis(j);
        return gregorianCalendar.get(1) == gregorianCalendar2.get(1) && gregorianCalendar.get(2) == gregorianCalendar2.get(2) && gregorianCalendar.get(5) == gregorianCalendar2.get(5);
    }

    public static Uri toCountdownConditionId(long j, boolean z) {
        return new Uri.Builder().scheme(Condition.SCHEME).authority("android").appendPath(COUNTDOWN_PATH).appendPath(Long.toString(j)).appendPath("alarm").appendPath(Boolean.toString(z)).build();
    }

    public static long tryParseCountdownConditionId(Uri uri) {
        if (Condition.isValidId(uri, "android") && uri.getPathSegments().size() >= 2 && COUNTDOWN_PATH.equals(uri.getPathSegments().get(0))) {
            try {
                return Long.parseLong(uri.getPathSegments().get(1));
            } catch (RuntimeException e) {
                Slog.w(TAG, "Error parsing countdown condition: " + uri, e);
            }
        }
        return 0L;
    }

    public static boolean isValidCountdownConditionId(Uri uri) {
        return tryParseCountdownConditionId(uri) != 0;
    }

    public static boolean isValidCountdownToAlarmConditionId(Uri uri) {
        if (tryParseCountdownConditionId(uri) != 0 && uri.getPathSegments().size() >= 4 && "alarm".equals(uri.getPathSegments().get(2))) {
            try {
                return Boolean.parseBoolean(uri.getPathSegments().get(3));
            } catch (RuntimeException e) {
                Slog.w(TAG, "Error parsing countdown alarm condition: " + uri, e);
            }
        }
        return false;
    }

    public static Uri toScheduleConditionId(ScheduleInfo scheduleInfo) {
        return new Uri.Builder().scheme(Condition.SCHEME).authority("android").appendPath(SCHEDULE_PATH).appendQueryParameter("days", toDayList(scheduleInfo.days)).appendQueryParameter(Telephony.BaseMmsColumns.START, scheduleInfo.startHour + MediaMetrics.SEPARATOR + scheduleInfo.startMinute).appendQueryParameter("end", scheduleInfo.endHour + MediaMetrics.SEPARATOR + scheduleInfo.endMinute).appendQueryParameter("exitAtAlarm", String.valueOf(scheduleInfo.exitAtAlarm)).build();
    }

    public static boolean isValidScheduleConditionId(Uri uri) {
        ScheduleInfo scheduleInfoTryParseScheduleConditionId;
        try {
            scheduleInfoTryParseScheduleConditionId = tryParseScheduleConditionId(uri);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException unused) {
        }
        return (scheduleInfoTryParseScheduleConditionId == null || scheduleInfoTryParseScheduleConditionId.days == null || scheduleInfoTryParseScheduleConditionId.days.length == 0) ? false : true;
    }

    public static boolean isValidScheduleConditionId(Uri uri, boolean z) {
        try {
            ScheduleInfo scheduleInfoTryParseScheduleConditionId = tryParseScheduleConditionId(uri);
            if (scheduleInfoTryParseScheduleConditionId != null) {
                if (z) {
                    return true;
                }
                if (scheduleInfoTryParseScheduleConditionId.days != null && scheduleInfoTryParseScheduleConditionId.days.length != 0) {
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException unused) {
        }
        return false;
    }

    public static ScheduleInfo tryParseScheduleConditionId(Uri uri) {
        if (!(uri != null && Condition.SCHEME.equals(uri.getScheme()) && "android".equals(uri.getAuthority()) && uri.getPathSegments().size() == 1 && SCHEDULE_PATH.equals(uri.getPathSegments().get(0)))) {
            return null;
        }
        int[] iArrTryParseHourAndMinute = tryParseHourAndMinute(uri.getQueryParameter(Telephony.BaseMmsColumns.START));
        int[] iArrTryParseHourAndMinute2 = tryParseHourAndMinute(uri.getQueryParameter("end"));
        if (iArrTryParseHourAndMinute == null || iArrTryParseHourAndMinute2 == null) {
            return null;
        }
        ScheduleInfo scheduleInfo = new ScheduleInfo();
        scheduleInfo.days = tryParseDayList(uri.getQueryParameter("days"), "\\.");
        scheduleInfo.startHour = iArrTryParseHourAndMinute[0];
        scheduleInfo.startMinute = iArrTryParseHourAndMinute[1];
        scheduleInfo.endHour = iArrTryParseHourAndMinute2[0];
        scheduleInfo.endMinute = iArrTryParseHourAndMinute2[1];
        scheduleInfo.exitAtAlarm = safeBoolean(uri.getQueryParameter("exitAtAlarm"), false);
        return scheduleInfo;
    }

    public static ComponentName getScheduleConditionProvider() {
        return new ComponentName("android", "ScheduleConditionProvider");
    }

    public static class ScheduleInfo {
        public int[] days;
        public int endHour;
        public int endMinute;
        public boolean exitAtAlarm;
        public long nextAlarm;
        public int startHour;
        public int startMinute;

        public int hashCode() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ScheduleInfo)) {
                return false;
            }
            ScheduleInfo scheduleInfo = (ScheduleInfo) obj;
            return ZenModeConfig.toDayList(this.days).equals(ZenModeConfig.toDayList(scheduleInfo.days)) && this.startHour == scheduleInfo.startHour && this.startMinute == scheduleInfo.startMinute && this.endHour == scheduleInfo.endHour && this.endMinute == scheduleInfo.endMinute && this.exitAtAlarm == scheduleInfo.exitAtAlarm;
        }

        public ScheduleInfo copy() {
            ScheduleInfo scheduleInfo = new ScheduleInfo();
            int[] iArr = this.days;
            if (iArr != null) {
                int[] iArr2 = new int[iArr.length];
                scheduleInfo.days = iArr2;
                int[] iArr3 = this.days;
                System.arraycopy(iArr3, 0, iArr2, 0, iArr3.length);
            }
            scheduleInfo.startHour = this.startHour;
            scheduleInfo.startMinute = this.startMinute;
            scheduleInfo.endHour = this.endHour;
            scheduleInfo.endMinute = this.endMinute;
            scheduleInfo.exitAtAlarm = this.exitAtAlarm;
            scheduleInfo.nextAlarm = this.nextAlarm;
            return scheduleInfo;
        }

        public String toString() {
            return "ScheduleInfo{days=" + Arrays.toString(this.days) + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", exitAtAlarm=" + this.exitAtAlarm + ", nextAlarm=" + ts(this.nextAlarm) + '}';
        }

        protected static String ts(long j) {
            return new Date(j) + " (" + j + ")";
        }
    }

    public static Uri toEventConditionId(EventInfo eventInfo) {
        return new Uri.Builder().scheme(Condition.SCHEME).authority("android").appendPath("event").appendQueryParameter("userId", Long.toString(eventInfo.userId)).appendQueryParameter("calendar", eventInfo.calName != null ? eventInfo.calName : "").appendQueryParameter("calendarId", eventInfo.calendarId != null ? eventInfo.calendarId.toString() : "").appendQueryParameter("reply", Integer.toString(eventInfo.reply)).build();
    }

    public static boolean isValidEventConditionId(Uri uri) {
        return tryParseEventConditionId(uri) != null;
    }

    public static EventInfo tryParseEventConditionId(Uri uri) {
        if (!(uri != null && Condition.SCHEME.equals(uri.getScheme()) && "android".equals(uri.getAuthority()) && uri.getPathSegments().size() == 1 && "event".equals(uri.getPathSegments().get(0)))) {
            return null;
        }
        EventInfo eventInfo = new EventInfo();
        eventInfo.userId = tryParseInt(uri.getQueryParameter("userId"), -10000);
        eventInfo.calName = uri.getQueryParameter("calendar");
        if (TextUtils.isEmpty(eventInfo.calName)) {
            eventInfo.calName = null;
        }
        eventInfo.calendarId = tryParseLong(uri.getQueryParameter("calendarId"), (Long) null);
        eventInfo.reply = tryParseInt(uri.getQueryParameter("reply"), 0);
        return eventInfo;
    }

    public static ComponentName getEventConditionProvider() {
        return new ComponentName("android", "EventConditionProvider");
    }

    public static class EventInfo {
        public static final int REPLY_ANY_EXCEPT_NO = 0;
        public static final int REPLY_YES = 2;
        public static final int REPLY_YES_OR_MAYBE = 1;
        public String calName;
        public Long calendarId;
        public int reply;
        public int userId = -10000;

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.userId), this.calName, this.calendarId, Integer.valueOf(this.reply));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof EventInfo)) {
                return false;
            }
            EventInfo eventInfo = (EventInfo) obj;
            return this.userId == eventInfo.userId && Objects.equals(this.calName, eventInfo.calName) && this.reply == eventInfo.reply && Objects.equals(this.calendarId, eventInfo.calendarId);
        }

        public EventInfo copy() {
            EventInfo eventInfo = new EventInfo();
            eventInfo.userId = this.userId;
            eventInfo.calName = this.calName;
            eventInfo.reply = this.reply;
            eventInfo.calendarId = this.calendarId;
            return eventInfo;
        }

        public static int resolveUserId(int i) {
            return i == -10000 ? ActivityManager.getCurrentUser() : i;
        }
    }

    private static int[] tryParseHourAndMinute(String str) {
        int iIndexOf;
        if (TextUtils.isEmpty(str) || (iIndexOf = str.indexOf(46)) < 1 || iIndexOf >= str.length() - 1) {
            return null;
        }
        int iTryParseInt = tryParseInt(str.substring(0, iIndexOf), -1);
        int iTryParseInt2 = tryParseInt(str.substring(iIndexOf + 1), -1);
        if (isValidHour(iTryParseInt) && isValidMinute(iTryParseInt2)) {
            return new int[]{iTryParseInt, iTryParseInt2};
        }
        return null;
    }

    private static int tryParseZenMode(String str, int i) {
        int iTryParseInt = tryParseInt(str, i);
        return Settings.Global.isValidZenMode(iTryParseInt) ? iTryParseInt : i;
    }

    public static String newRuleId() {
        return UUID.randomUUID().toString().replace(NativeLibraryHelper.CLEAR_ABI_OVERRIDE, "");
    }

    public static String getOwnerCaption(Context context, String str) {
        CharSequence charSequenceLoadLabel;
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            if (applicationInfo == null || (charSequenceLoadLabel = applicationInfo.loadLabel(packageManager)) == null) {
                return "";
            }
            String strTrim = charSequenceLoadLabel.toString().trim();
            return strTrim.length() > 0 ? strTrim : "";
        } catch (Throwable th) {
            Slog.w(TAG, "Error loading owner caption", th);
            return "";
        }
    }

    public static String getConditionSummary(Context context, ZenModeConfig zenModeConfig, int i, boolean z) {
        return getConditionLine(context, zenModeConfig, i, false, z);
    }

    private static String getConditionLine(Context context, ZenModeConfig zenModeConfig, int i, boolean z, boolean z2) {
        String string;
        String str;
        String string2 = "";
        if (zenModeConfig == null) {
            return "";
        }
        ZenRule zenRule = zenModeConfig.manualRule;
        if (zenRule != null) {
            Uri uri = zenRule.conditionId;
            if (zenModeConfig.manualRule.enabler != null) {
                string2 = getOwnerCaption(context, zenModeConfig.manualRule.enabler);
            } else if (uri == null) {
                string2 = context.getString(R.string.zen_mode_forever);
            } else {
                long jTryParseCountdownConditionId = tryParseCountdownConditionId(uri);
                Condition timeCondition = zenModeConfig.manualRule.condition;
                if (jTryParseCountdownConditionId > 0) {
                    timeCondition = toTimeCondition(context, jTryParseCountdownConditionId, Math.round((jTryParseCountdownConditionId - System.currentTimeMillis()) / 60000.0f), i, z2);
                }
                if (timeCondition == null) {
                    str = "";
                } else {
                    str = z ? timeCondition.line1 : timeCondition.summary;
                }
                if (!TextUtils.isEmpty(str)) {
                    string2 = str;
                }
            }
        }
        for (ZenRule zenRule2 : zenModeConfig.automaticRules.values()) {
            if (zenRule2.isAutomaticActive()) {
                if (string2.isEmpty()) {
                    string = zenRule2.name;
                } else {
                    string = context.getResources().getString(R.string.zen_mode_rule_name_combination, string2, zenRule2.name);
                }
                string2 = string;
            }
        }
        return string2;
    }

    public static class ZenRule implements Parcelable {
        public static final Parcelable.Creator<ZenRule> CREATOR = new Parcelable.Creator<ZenRule>() { // from class: android.service.notification.ZenModeConfig.ZenRule.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ZenRule createFromParcel(Parcel parcel) {
                return new ZenRule(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ZenRule[] newArray(int i) {
                return new ZenRule[i];
            }
        };
        public ComponentName component;
        public Condition condition;
        public Uri conditionId;
        public ComponentName configurationActivity;
        public long creationTime;
        public boolean enabled;
        public String enabler;
        public String id;
        public boolean modified;
        public String name;
        public String pkg;
        public boolean snoozing;
        public int zenMode;
        public ZenPolicy zenPolicy;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public ZenRule() {
        }

        public ZenRule(Parcel parcel) {
            this.enabled = parcel.readInt() == 1;
            this.snoozing = parcel.readInt() == 1;
            if (parcel.readInt() == 1) {
                this.name = parcel.readString();
            }
            this.zenMode = parcel.readInt();
            this.conditionId = (Uri) parcel.readParcelable(null);
            this.condition = (Condition) parcel.readParcelable(null);
            this.component = (ComponentName) parcel.readParcelable(null);
            this.configurationActivity = (ComponentName) parcel.readParcelable(null);
            if (parcel.readInt() == 1) {
                this.id = parcel.readString();
            }
            this.creationTime = parcel.readLong();
            if (parcel.readInt() == 1) {
                this.enabler = parcel.readString();
            }
            this.zenPolicy = (ZenPolicy) parcel.readParcelable(null);
            this.modified = parcel.readInt() == 1;
            this.pkg = parcel.readString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.enabled ? 1 : 0);
            parcel.writeInt(this.snoozing ? 1 : 0);
            if (this.name != null) {
                parcel.writeInt(1);
                parcel.writeString(this.name);
            } else {
                parcel.writeInt(0);
            }
            parcel.writeInt(this.zenMode);
            parcel.writeParcelable(this.conditionId, 0);
            parcel.writeParcelable(this.condition, 0);
            parcel.writeParcelable(this.component, 0);
            parcel.writeParcelable(this.configurationActivity, 0);
            if (this.id != null) {
                parcel.writeInt(1);
                parcel.writeString(this.id);
            } else {
                parcel.writeInt(0);
            }
            parcel.writeLong(this.creationTime);
            if (this.enabler != null) {
                parcel.writeInt(1);
                parcel.writeString(this.enabler);
            } else {
                parcel.writeInt(0);
            }
            parcel.writeParcelable(this.zenPolicy, 0);
            parcel.writeInt(this.modified ? 1 : 0);
            parcel.writeString(this.pkg);
        }

        public String toString() {
            return ZenRule.class.getSimpleName() + "[id=" + this.id + ",enabled=" + String.valueOf(this.enabled).toUpperCase() + ",snoozing=" + this.snoozing + ",name=" + this.name + ",zenMode=" + Settings.Global.zenModeToString(this.zenMode) + ",conditionId=" + this.conditionId + ",condition=" + this.condition + ",pkg=" + this.pkg + ",component=" + this.component + ",configActivity=" + this.configurationActivity + ",creationTime=" + this.creationTime + ",enabler=" + this.enabler + ",zenPolicy=" + this.zenPolicy + ",modified=" + this.modified + ']';
        }

        public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
            long jStart = protoOutputStream.start(j);
            protoOutputStream.write(1138166333441L, this.id);
            protoOutputStream.write(1138166333442L, this.name);
            protoOutputStream.write(1112396529667L, this.creationTime);
            protoOutputStream.write(1133871366148L, this.enabled);
            protoOutputStream.write(1138166333445L, this.enabler);
            protoOutputStream.write(1133871366150L, this.snoozing);
            protoOutputStream.write(1159641169927L, this.zenMode);
            Uri uri = this.conditionId;
            if (uri != null) {
                protoOutputStream.write(1138166333448L, uri.toString());
            }
            Condition condition = this.condition;
            if (condition != null) {
                condition.dumpDebug(protoOutputStream, 1146756268041L);
            }
            ComponentName componentName = this.component;
            if (componentName != null) {
                componentName.dumpDebug(protoOutputStream, 1146756268042L);
            }
            ZenPolicy zenPolicy = this.zenPolicy;
            if (zenPolicy != null) {
                zenPolicy.dumpDebug(protoOutputStream, 1146756268043L);
            }
            protoOutputStream.write(1133871366156L, this.modified);
            protoOutputStream.end(jStart);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void appendDiff(Diff diff, String str, ZenRule zenRule, ZenRule zenRule2) {
            if (diff == null) {
                return;
            }
            if (zenRule != null) {
                zenRule.appendDiff(diff, str, zenRule2);
            } else if (zenRule2 != null) {
                diff.addLine(str, "insert");
            }
        }

        private void appendDiff(Diff diff, String str, ZenRule zenRule) {
            if (zenRule != null) {
                boolean z = this.enabled;
                if (z != zenRule.enabled) {
                    diff.addLine(str, "enabled", Boolean.valueOf(z), Boolean.valueOf(zenRule.enabled));
                }
                boolean z2 = this.snoozing;
                if (z2 != zenRule.snoozing) {
                    diff.addLine(str, ZenModeConfig.RULE_ATT_SNOOZING, Boolean.valueOf(z2), Boolean.valueOf(zenRule.snoozing));
                }
                if (!Objects.equals(this.name, zenRule.name)) {
                    diff.addLine(str, "name", this.name, zenRule.name);
                }
                int i = this.zenMode;
                if (i != zenRule.zenMode) {
                    diff.addLine(str, "zenMode", Integer.valueOf(i), Integer.valueOf(zenRule.zenMode));
                }
                if (!Objects.equals(this.conditionId, zenRule.conditionId)) {
                    diff.addLine(str, ZenModeConfig.RULE_ATT_CONDITION_ID, this.conditionId, zenRule.conditionId);
                }
                if (!Objects.equals(this.condition, zenRule.condition)) {
                    diff.addLine(str, Condition.SCHEME, this.condition, zenRule.condition);
                }
                if (!Objects.equals(this.component, zenRule.component)) {
                    diff.addLine(str, "component", this.component, zenRule.component);
                }
                if (!Objects.equals(this.configurationActivity, zenRule.configurationActivity)) {
                    diff.addLine(str, ZenModeConfig.RULE_ATT_CONFIG_ACTIVITY, this.configurationActivity, zenRule.configurationActivity);
                }
                if (!Objects.equals(this.id, zenRule.id)) {
                    diff.addLine(str, "id", this.id, zenRule.id);
                }
                long j = this.creationTime;
                if (j != zenRule.creationTime) {
                    diff.addLine(str, "creationTime", Long.valueOf(j), Long.valueOf(zenRule.creationTime));
                }
                if (!Objects.equals(this.enabler, zenRule.enabler)) {
                    diff.addLine(str, ZenModeConfig.RULE_ATT_ENABLER, this.enabler, zenRule.enabler);
                }
                if (!Objects.equals(this.zenPolicy, zenRule.zenPolicy)) {
                    diff.addLine(str, "zenPolicy", this.zenPolicy, zenRule.zenPolicy);
                }
                boolean z3 = this.modified;
                if (z3 != zenRule.modified) {
                    diff.addLine(str, "modified", Boolean.valueOf(z3), Boolean.valueOf(zenRule.modified));
                }
                if (Objects.equals(this.pkg, zenRule.pkg)) {
                    return;
                }
                diff.addLine(str, "pkg", this.pkg, zenRule.pkg);
                return;
            }
            diff.addLine(str, "delete");
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ZenRule)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            ZenRule zenRule = (ZenRule) obj;
            return zenRule.enabled == this.enabled && zenRule.snoozing == this.snoozing && Objects.equals(zenRule.name, this.name) && zenRule.zenMode == this.zenMode && Objects.equals(zenRule.conditionId, this.conditionId) && Objects.equals(zenRule.condition, this.condition) && Objects.equals(zenRule.component, this.component) && Objects.equals(zenRule.configurationActivity, this.configurationActivity) && Objects.equals(zenRule.id, this.id) && Objects.equals(zenRule.enabler, this.enabler) && Objects.equals(zenRule.zenPolicy, this.zenPolicy) && Objects.equals(zenRule.pkg, this.pkg) && zenRule.modified == this.modified;
        }

        public int hashCode() {
            return Objects.hash(Boolean.valueOf(this.enabled), Boolean.valueOf(this.snoozing), this.name, Integer.valueOf(this.zenMode), this.conditionId, this.condition, this.component, this.configurationActivity, this.pkg, this.id, this.enabler, this.zenPolicy, Boolean.valueOf(this.modified));
        }

        public boolean isAutomaticActive() {
            return this.enabled && !this.snoozing && this.pkg != null && isTrueOrUnknown();
        }

        public boolean isTrueOrUnknown() {
            Condition condition = this.condition;
            return condition != null && (condition.state == 1 || this.condition.state == 2);
        }
    }

    public static class Diff {
        private final ArrayList<String> lines = new ArrayList<>();

        public String toString() {
            StringBuilder sb = new StringBuilder("Diff[");
            int size = this.lines.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    sb.append(",\n");
                }
                sb.append(this.lines.get(i));
            }
            sb.append(']');
            return sb.toString();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Diff addLine(String str, String str2) {
            this.lines.add(str + SettingsStringUtil.DELIMITER + str2);
            return this;
        }

        public Diff addLine(String str, String str2, Object obj, Object obj2) {
            return addLine(str + MediaMetrics.SEPARATOR + str2, obj, obj2);
        }

        public Diff addLine(String str, Object obj, Object obj2) {
            return addLine(str, obj + Session.SUBSESSION_SEPARATION_CHAR + obj2);
        }
    }

    public static boolean areAllPriorityOnlyRingerSoundsMuted(NotificationManager.Policy policy) {
        return (((policy.priorityCategories & 1) != 0) || ((policy.priorityCategories & 8) != 0) || ((policy.priorityCategories & 4) != 0) || ((policy.priorityCategories & 2) != 0) || ((policy.priorityCategories & 16) != 0) || ((policy.state & 1) != 0) || ((policy.priorityCategories & 128) != 0) || ((policy.priorityConversationSenders & 256) != 0)) ? false : true;
    }

    public static boolean areAllZenBehaviorSoundsMuted(NotificationManager.Policy policy) {
        return (((policy.priorityCategories & 32) != 0) || ((policy.priorityCategories & 64) != 0) || !areAllPriorityOnlyRingerSoundsMuted(policy)) ? false : true;
    }

    public static boolean isZenOverridingRinger(int i, NotificationManager.Policy policy) {
        if (i == 2 || i == 3) {
            return true;
        }
        return i == 1 && areAllPriorityOnlyRingerSoundsMuted(policy);
    }

    public static boolean areAllPriorityOnlyRingerSoundsMuted(ZenModeConfig zenModeConfig) {
        return (zenModeConfig.allowReminders || zenModeConfig.allowCalls || zenModeConfig.allowMessages || zenModeConfig.allowEvents || zenModeConfig.allowRepeatCallers || zenModeConfig.areChannelsBypassingDnd || zenModeConfig.allowSystem) ? false : true;
    }

    public static boolean areAllZenBehaviorSoundsMuted(ZenModeConfig zenModeConfig) {
        return (zenModeConfig.allowAlarms || zenModeConfig.allowMedia || !areAllPriorityOnlyRingerSoundsMuted(zenModeConfig)) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0057 A[PHI: r1
  0x0057: PHI (r1v1 long) = (r1v0 long), (r1v6 long), (r1v0 long) binds: [B:7:0x000d, B:19:0x003b, B:11:0x0023] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getDescription(android.content.Context r7, boolean r8, android.service.notification.ZenModeConfig r9, boolean r10) {
        /*
            r0 = 0
            if (r8 == 0) goto L9d
            if (r9 != 0) goto L7
            goto L9d
        L7:
            r1 = -1
            android.service.notification.ZenModeConfig$ZenRule r8 = r9.manualRule
            java.lang.String r3 = ""
            if (r8 == 0) goto L57
            android.net.Uri r8 = r8.conditionId
            android.service.notification.ZenModeConfig$ZenRule r4 = r9.manualRule
            java.lang.String r4 = r4.enabler
            if (r4 == 0) goto L26
            android.service.notification.ZenModeConfig$ZenRule r8 = r9.manualRule
            java.lang.String r8 = r8.enabler
            java.lang.String r8 = getOwnerCaption(r7, r8)
            boolean r10 = r8.isEmpty()
            if (r10 != 0) goto L57
            goto L58
        L26:
            if (r8 != 0) goto L33
            if (r10 == 0) goto L32
            r8 = 17041524(0x1040874, float:2.4250636E-38)
            java.lang.String r7 = r7.getString(r8)
            return r7
        L32:
            return r0
        L33:
            long r1 = tryParseCountdownConditionId(r8)
            r4 = 0
            int r8 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r8 <= 0) goto L57
            boolean r8 = isToday(r1)
            int r10 = r7.getUserId()
            java.lang.CharSequence r8 = getFormattedTime(r7, r1, r8, r10)
            r10 = 17041527(0x1040877, float:2.4250644E-38)
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r5 = 0
            r4[r5] = r8
            java.lang.String r8 = r7.getString(r10, r4)
            goto L58
        L57:
            r8 = r3
        L58:
            android.util.ArrayMap<java.lang.String, android.service.notification.ZenModeConfig$ZenRule> r9 = r9.automaticRules
            java.util.Collection r9 = r9.values()
            java.util.Iterator r9 = r9.iterator()
        L62:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L96
            java.lang.Object r10 = r9.next()
            android.service.notification.ZenModeConfig$ZenRule r10 = (android.service.notification.ZenModeConfig.ZenRule) r10
            boolean r4 = r10.isAutomaticActive()
            if (r4 == 0) goto L62
            android.net.Uri r4 = r10.conditionId
            boolean r4 = isValidEventConditionId(r4)
            if (r4 != 0) goto L88
            android.net.Uri r4 = r10.conditionId
            boolean r4 = isValidScheduleConditionId(r4)
            if (r4 == 0) goto L85
            goto L88
        L85:
            java.lang.String r7 = r10.name
            return r7
        L88:
            android.net.Uri r4 = r10.conditionId
            long r4 = parseAutomaticRuleEndTime(r7, r4)
            int r6 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r6 <= 0) goto L62
            java.lang.String r8 = r10.name
            r1 = r4
            goto L62
        L96:
            boolean r7 = r8.equals(r3)
            if (r7 != 0) goto L9d
            r0 = r8
        L9d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.service.notification.ZenModeConfig.getDescription(android.content.Context, boolean, android.service.notification.ZenModeConfig, boolean):java.lang.String");
    }

    private static long parseAutomaticRuleEndTime(Context context, Uri uri) {
        if (isValidEventConditionId(uri)) {
            return Long.MAX_VALUE;
        }
        if (!isValidScheduleConditionId(uri)) {
            return -1L;
        }
        ScheduleCalendar scheduleCalendar = toScheduleCalendar(uri);
        long nextChangeTime = scheduleCalendar.getNextChangeTime(System.currentTimeMillis());
        if (scheduleCalendar.exitAtAlarm()) {
            long nextAlarm = getNextAlarm(context);
            scheduleCalendar.maybeSetNextAlarm(System.currentTimeMillis(), nextAlarm);
            if (scheduleCalendar.shouldExitForAlarm(nextChangeTime)) {
                return nextAlarm;
            }
        }
        return nextChangeTime;
    }

    private static long getNextAlarm(Context context) {
        AlarmManager.AlarmClockInfo nextAlarmClock = ((AlarmManager) context.getSystemService("alarm")).getNextAlarmClock(context.getUserId());
        if (nextAlarmClock != null) {
            return nextAlarmClock.getTriggerTime();
        }
        return 0L;
    }
}
