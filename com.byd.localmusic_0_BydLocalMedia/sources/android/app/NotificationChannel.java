package android.app;

import android.annotation.SystemApi;
import android.content.ContentResolver;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.proto.ProtoOutputStream;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public final class NotificationChannel implements Parcelable {
    public static final int ALLOW_BUBBLE_OFF = 0;
    public static final int ALLOW_BUBBLE_ON = 1;
    private static final String ATT_ALLOW_BUBBLE = "allow_bubbles";
    private static final String ATT_BLOCKABLE_SYSTEM = "blockable_system";
    private static final String ATT_CONTENT_TYPE = "content_type";
    private static final String ATT_CONVERSATION_ID = "conv_id";
    private static final String ATT_DELETED = "deleted";
    private static final String ATT_DEMOTE = "dem";
    private static final String ATT_DESC = "desc";
    private static final String ATT_FG_SERVICE_SHOWN = "fgservice";
    private static final String ATT_FLAGS = "flags";
    private static final String ATT_GROUP = "group";
    private static final String ATT_ID = "id";
    private static final String ATT_IMPORTANCE = "importance";
    private static final String ATT_IMP_CONVERSATION = "imp_conv";
    private static final String ATT_LIGHTS = "lights";
    private static final String ATT_LIGHT_COLOR = "light_color";
    private static final String ATT_NAME = "name";
    private static final String ATT_ORIG_IMP = "orig_imp";
    private static final String ATT_PARENT_CHANNEL = "parent";
    private static final String ATT_PRIORITY = "priority";
    private static final String ATT_SHOW_BADGE = "show_badge";
    private static final String ATT_SOUND = "sound";
    private static final String ATT_USAGE = "usage";
    private static final String ATT_USER_LOCKED = "locked";
    private static final String ATT_VIBRATION = "vibration";
    private static final String ATT_VIBRATION_ENABLED = "vibration_enabled";
    private static final String ATT_VISIBILITY = "visibility";
    public static final String CONVERSATION_CHANNEL_ID_FORMAT = "%1$s : %2$s";
    public static final int DEFAULT_ALLOW_BUBBLE = -1;
    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
    private static final boolean DEFAULT_DELETED = false;
    private static final int DEFAULT_IMPORTANCE = -1000;
    private static final int DEFAULT_LIGHT_COLOR = 0;
    private static final boolean DEFAULT_SHOW_BADGE = true;
    private static final int DEFAULT_VISIBILITY = -1000;
    private static final String DELIMITER = ",";
    private static final int MAX_TEXT_LENGTH = 1000;
    public static final String PLACEHOLDER_CONVERSATION_ID = ":placeholder_id";
    private static final String TAG_CHANNEL = "channel";
    public static final int USER_LOCKED_ALLOW_BUBBLE = 256;
    public static final int USER_LOCKED_IMPORTANCE = 4;
    public static final int USER_LOCKED_LIGHTS = 8;
    public static final int USER_LOCKED_PRIORITY = 1;
    public static final int USER_LOCKED_SHOW_BADGE = 128;

    @SystemApi
    public static final int USER_LOCKED_SOUND = 32;
    public static final int USER_LOCKED_VIBRATION = 16;
    public static final int USER_LOCKED_VISIBILITY = 2;
    private int mAllowBubbles;
    private AudioAttributes mAudioAttributes;
    private boolean mBlockableSystem;
    private boolean mBypassDnd;
    private String mConversationId;
    private boolean mDeleted;
    private boolean mDemoted;
    private String mDesc;
    private boolean mFgServiceShown;
    private String mGroup;
    private String mId;
    private int mImportance;
    private boolean mImportanceLockedByOEM;
    private boolean mImportanceLockedDefaultApp;
    private boolean mImportantConvo;
    private int mLightColor;
    private boolean mLights;
    private int mLockscreenVisibility;
    private String mName;
    private int mOriginalImportance;
    private String mParentId;
    private boolean mShowBadge;
    private Uri mSound;
    private int mUserLockedFields;
    private long[] mVibration;
    private boolean mVibrationEnabled;
    public static final int[] LOCKABLE_FIELDS = {1, 2, 4, 8, 16, 32, 128, 256};
    public static final Parcelable.Creator<NotificationChannel> CREATOR = new Parcelable.Creator<NotificationChannel>() { // from class: android.app.NotificationChannel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationChannel createFromParcel(Parcel parcel) {
            return new NotificationChannel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationChannel[] newArray(int i) {
            return new NotificationChannel[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NotificationChannel(String str, CharSequence charSequence, int i) {
        this.mImportance = -1000;
        this.mOriginalImportance = -1000;
        this.mLockscreenVisibility = -1000;
        this.mSound = Settings.System.DEFAULT_NOTIFICATION_URI;
        this.mLightColor = 0;
        this.mShowBadge = true;
        this.mDeleted = false;
        this.mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
        this.mBlockableSystem = false;
        this.mAllowBubbles = -1;
        this.mParentId = null;
        this.mConversationId = null;
        this.mDemoted = false;
        this.mImportantConvo = false;
        this.mId = getTrimmedString(str);
        this.mName = charSequence != null ? getTrimmedString(charSequence.toString()) : null;
        this.mImportance = i;
    }

    protected NotificationChannel(Parcel parcel) {
        this.mImportance = -1000;
        this.mOriginalImportance = -1000;
        this.mLockscreenVisibility = -1000;
        this.mSound = Settings.System.DEFAULT_NOTIFICATION_URI;
        this.mLightColor = 0;
        this.mShowBadge = true;
        this.mDeleted = false;
        this.mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
        this.mBlockableSystem = false;
        this.mAllowBubbles = -1;
        this.mParentId = null;
        this.mConversationId = null;
        this.mDemoted = false;
        this.mImportantConvo = false;
        if (parcel.readByte() != 0) {
            this.mId = parcel.readString();
        } else {
            this.mId = null;
        }
        if (parcel.readByte() != 0) {
            this.mName = parcel.readString();
        } else {
            this.mName = null;
        }
        if (parcel.readByte() != 0) {
            this.mDesc = parcel.readString();
        } else {
            this.mDesc = null;
        }
        this.mImportance = parcel.readInt();
        this.mBypassDnd = parcel.readByte() != 0;
        this.mLockscreenVisibility = parcel.readInt();
        if (parcel.readByte() != 0) {
            this.mSound = Uri.CREATOR.createFromParcel(parcel);
        } else {
            this.mSound = null;
        }
        this.mLights = parcel.readByte() != 0;
        this.mVibration = parcel.createLongArray();
        this.mUserLockedFields = parcel.readInt();
        this.mFgServiceShown = parcel.readByte() != 0;
        this.mVibrationEnabled = parcel.readByte() != 0;
        this.mShowBadge = parcel.readByte() != 0;
        this.mDeleted = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            this.mGroup = parcel.readString();
        } else {
            this.mGroup = null;
        }
        this.mAudioAttributes = parcel.readInt() > 0 ? AudioAttributes.CREATOR.createFromParcel(parcel) : null;
        this.mLightColor = parcel.readInt();
        this.mBlockableSystem = parcel.readBoolean();
        this.mAllowBubbles = parcel.readInt();
        this.mImportanceLockedByOEM = parcel.readBoolean();
        this.mOriginalImportance = parcel.readInt();
        this.mParentId = parcel.readString();
        this.mConversationId = parcel.readString();
        this.mDemoted = parcel.readBoolean();
        this.mImportantConvo = parcel.readBoolean();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.mId != null) {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.mId);
        } else {
            parcel.writeByte((byte) 0);
        }
        if (this.mName != null) {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.mName);
        } else {
            parcel.writeByte((byte) 0);
        }
        if (this.mDesc != null) {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.mDesc);
        } else {
            parcel.writeByte((byte) 0);
        }
        parcel.writeInt(this.mImportance);
        parcel.writeByte(this.mBypassDnd ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mLockscreenVisibility);
        if (this.mSound != null) {
            parcel.writeByte((byte) 1);
            this.mSound.writeToParcel(parcel, 0);
        } else {
            parcel.writeByte((byte) 0);
        }
        parcel.writeByte(this.mLights ? (byte) 1 : (byte) 0);
        parcel.writeLongArray(this.mVibration);
        parcel.writeInt(this.mUserLockedFields);
        parcel.writeByte(this.mFgServiceShown ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mVibrationEnabled ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mShowBadge ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mDeleted ? (byte) 1 : (byte) 0);
        if (this.mGroup != null) {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.mGroup);
        } else {
            parcel.writeByte((byte) 0);
        }
        if (this.mAudioAttributes != null) {
            parcel.writeInt(1);
            this.mAudioAttributes.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.mLightColor);
        parcel.writeBoolean(this.mBlockableSystem);
        parcel.writeInt(this.mAllowBubbles);
        parcel.writeBoolean(this.mImportanceLockedByOEM);
        parcel.writeInt(this.mOriginalImportance);
        parcel.writeString(this.mParentId);
        parcel.writeString(this.mConversationId);
        parcel.writeBoolean(this.mDemoted);
        parcel.writeBoolean(this.mImportantConvo);
    }

    public void lockFields(int i) {
        this.mUserLockedFields = i | this.mUserLockedFields;
    }

    public void unlockFields(int i) {
        this.mUserLockedFields = (~i) & this.mUserLockedFields;
    }

    public void setFgServiceShown(boolean z) {
        this.mFgServiceShown = z;
    }

    public void setDeleted(boolean z) {
        this.mDeleted = z;
    }

    public void setImportantConversation(boolean z) {
        this.mImportantConvo = z;
    }

    @SystemApi
    public void setBlockable(boolean z) {
        this.mBlockableSystem = z;
    }

    public void setName(CharSequence charSequence) {
        this.mName = charSequence != null ? getTrimmedString(charSequence.toString()) : null;
    }

    public void setDescription(String str) {
        this.mDesc = getTrimmedString(str);
    }

    private String getTrimmedString(String str) {
        return (str == null || str.length() <= 1000) ? str : str.substring(0, 1000);
    }

    public void setId(String str) {
        this.mId = str;
    }

    public void setGroup(String str) {
        this.mGroup = str;
    }

    public void setShowBadge(boolean z) {
        this.mShowBadge = z;
    }

    public void setSound(Uri uri, AudioAttributes audioAttributes) {
        this.mSound = uri;
        this.mAudioAttributes = audioAttributes;
    }

    public void enableLights(boolean z) {
        this.mLights = z;
    }

    public void setLightColor(int i) {
        this.mLightColor = i;
    }

    public void enableVibration(boolean z) {
        this.mVibrationEnabled = z;
    }

    public void setVibrationPattern(long[] jArr) {
        this.mVibrationEnabled = jArr != null && jArr.length > 0;
        this.mVibration = jArr;
    }

    public void setImportance(int i) {
        this.mImportance = i;
    }

    public void setBypassDnd(boolean z) {
        this.mBypassDnd = z;
    }

    public void setLockscreenVisibility(int i) {
        this.mLockscreenVisibility = i;
    }

    public void setAllowBubbles(boolean z) {
        this.mAllowBubbles = z ? 1 : 0;
    }

    public void setAllowBubbles(int i) {
        this.mAllowBubbles = i;
    }

    public void setConversationId(String str, String str2) {
        this.mParentId = str;
        this.mConversationId = str2;
    }

    public String getId() {
        return this.mId;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public String getDescription() {
        return this.mDesc;
    }

    public int getImportance() {
        return this.mImportance;
    }

    public boolean canBypassDnd() {
        return this.mBypassDnd;
    }

    public boolean isImportantConversation() {
        return this.mImportantConvo;
    }

    public Uri getSound() {
        return this.mSound;
    }

    public AudioAttributes getAudioAttributes() {
        return this.mAudioAttributes;
    }

    public boolean shouldShowLights() {
        return this.mLights;
    }

    public int getLightColor() {
        return this.mLightColor;
    }

    public boolean shouldVibrate() {
        return this.mVibrationEnabled;
    }

    public long[] getVibrationPattern() {
        return this.mVibration;
    }

    public int getLockscreenVisibility() {
        return this.mLockscreenVisibility;
    }

    public boolean canShowBadge() {
        return this.mShowBadge;
    }

    public String getGroup() {
        return this.mGroup;
    }

    public boolean canBubble() {
        return this.mAllowBubbles == 1;
    }

    public int getAllowBubbles() {
        return this.mAllowBubbles;
    }

    public String getParentChannelId() {
        return this.mParentId;
    }

    public String getConversationId() {
        return this.mConversationId;
    }

    @SystemApi
    public boolean isDeleted() {
        return this.mDeleted;
    }

    @SystemApi
    public int getUserLockedFields() {
        return this.mUserLockedFields;
    }

    public boolean isFgServiceShown() {
        return this.mFgServiceShown;
    }

    public boolean isBlockable() {
        return this.mBlockableSystem;
    }

    public void setImportanceLockedByOEM(boolean z) {
        this.mImportanceLockedByOEM = z;
    }

    public void setImportanceLockedByCriticalDeviceFunction(boolean z) {
        this.mImportanceLockedDefaultApp = z;
    }

    public boolean isImportanceLockedByOEM() {
        return this.mImportanceLockedByOEM;
    }

    public boolean isImportanceLockedByCriticalDeviceFunction() {
        return this.mImportanceLockedDefaultApp;
    }

    public int getOriginalImportance() {
        return this.mOriginalImportance;
    }

    public void setOriginalImportance(int i) {
        this.mOriginalImportance = i;
    }

    public void setDemoted(boolean z) {
        this.mDemoted = z;
    }

    public boolean isDemoted() {
        return this.mDemoted;
    }

    public boolean hasUserSetImportance() {
        return (this.mUserLockedFields & 4) != 0;
    }

    public boolean hasUserSetSound() {
        return (this.mUserLockedFields & 32) != 0;
    }

    public void populateFromXmlForRestore(XmlPullParser xmlPullParser, Context context) {
        populateFromXml(xmlPullParser, true, context);
    }

    @SystemApi
    public void populateFromXml(XmlPullParser xmlPullParser) {
        populateFromXml(xmlPullParser, false, null);
    }

    private void populateFromXml(XmlPullParser xmlPullParser, boolean z, Context context) {
        Preconditions.checkArgument((z && context == null) ? false : true, "forRestore is true but got null context");
        setDescription(xmlPullParser.getAttributeValue(null, ATT_DESC));
        setBypassDnd(safeInt(xmlPullParser, "priority", 0) != 0);
        setLockscreenVisibility(safeInt(xmlPullParser, "visibility", -1000));
        Uri uriSafeUri = safeUri(xmlPullParser, ATT_SOUND);
        if (z) {
            uriSafeUri = restoreSoundUri(context, uriSafeUri);
        }
        setSound(uriSafeUri, safeAudioAttributes(xmlPullParser));
        enableLights(safeBool(xmlPullParser, "lights", false));
        setLightColor(safeInt(xmlPullParser, ATT_LIGHT_COLOR, 0));
        setVibrationPattern(safeLongArray(xmlPullParser, ATT_VIBRATION, null));
        enableVibration(safeBool(xmlPullParser, ATT_VIBRATION_ENABLED, false));
        setShowBadge(safeBool(xmlPullParser, ATT_SHOW_BADGE, false));
        setDeleted(safeBool(xmlPullParser, "deleted", false));
        setGroup(xmlPullParser.getAttributeValue(null, ATT_GROUP));
        lockFields(safeInt(xmlPullParser, "locked", 0));
        setFgServiceShown(safeBool(xmlPullParser, ATT_FG_SERVICE_SHOWN, false));
        setBlockable(safeBool(xmlPullParser, ATT_BLOCKABLE_SYSTEM, false));
        setAllowBubbles(safeInt(xmlPullParser, ATT_ALLOW_BUBBLE, -1));
        setOriginalImportance(safeInt(xmlPullParser, ATT_ORIG_IMP, -1000));
        setConversationId(xmlPullParser.getAttributeValue(null, "parent"), xmlPullParser.getAttributeValue(null, ATT_CONVERSATION_ID));
        setDemoted(safeBool(xmlPullParser, ATT_DEMOTE, false));
        setImportantConversation(safeBool(xmlPullParser, ATT_IMP_CONVERSATION, false));
    }

    private Uri restoreSoundUri(Context context, Uri uri) {
        if (uri == null || Uri.EMPTY.equals(uri)) {
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriCanonicalize = contentResolver.canonicalize(uri);
        if (uriCanonicalize == null) {
            return Settings.System.DEFAULT_NOTIFICATION_URI;
        }
        return contentResolver.uncanonicalize(uriCanonicalize);
    }

    @SystemApi
    public void writeXml(XmlSerializer xmlSerializer) throws IOException {
        writeXml(xmlSerializer, false, null);
    }

    public void writeXmlForBackup(XmlSerializer xmlSerializer, Context context) throws IOException {
        writeXml(xmlSerializer, true, context);
    }

    private Uri getSoundForBackup(Context context) {
        Uri sound = getSound();
        if (sound == null || Uri.EMPTY.equals(sound)) {
            return null;
        }
        Uri uriCanonicalize = context.getContentResolver().canonicalize(sound);
        return uriCanonicalize == null ? Settings.System.DEFAULT_NOTIFICATION_URI : uriCanonicalize;
    }

    private void writeXml(XmlSerializer xmlSerializer, boolean z, Context context) throws IOException {
        Preconditions.checkArgument((z && context == null) ? false : true, "forBackup is true but got null context");
        xmlSerializer.startTag(null, "channel");
        xmlSerializer.attribute(null, "id", getId());
        if (getName() != null) {
            xmlSerializer.attribute(null, "name", getName().toString());
        }
        if (getDescription() != null) {
            xmlSerializer.attribute(null, ATT_DESC, getDescription());
        }
        if (getImportance() != -1000) {
            xmlSerializer.attribute(null, ATT_IMPORTANCE, Integer.toString(getImportance()));
        }
        if (canBypassDnd()) {
            xmlSerializer.attribute(null, "priority", Integer.toString(2));
        }
        if (getLockscreenVisibility() != -1000) {
            xmlSerializer.attribute(null, "visibility", Integer.toString(getLockscreenVisibility()));
        }
        Uri soundForBackup = z ? getSoundForBackup(context) : getSound();
        if (soundForBackup != null) {
            xmlSerializer.attribute(null, ATT_SOUND, soundForBackup.toString());
        }
        if (getAudioAttributes() != null) {
            xmlSerializer.attribute(null, ATT_USAGE, Integer.toString(getAudioAttributes().getUsage()));
            xmlSerializer.attribute(null, "content_type", Integer.toString(getAudioAttributes().getContentType()));
            xmlSerializer.attribute(null, "flags", Integer.toString(getAudioAttributes().getFlags()));
        }
        if (shouldShowLights()) {
            xmlSerializer.attribute(null, "lights", Boolean.toString(shouldShowLights()));
        }
        if (getLightColor() != 0) {
            xmlSerializer.attribute(null, ATT_LIGHT_COLOR, Integer.toString(getLightColor()));
        }
        if (shouldVibrate()) {
            xmlSerializer.attribute(null, ATT_VIBRATION_ENABLED, Boolean.toString(shouldVibrate()));
        }
        if (getVibrationPattern() != null) {
            xmlSerializer.attribute(null, ATT_VIBRATION, longArrayToString(getVibrationPattern()));
        }
        if (getUserLockedFields() != 0) {
            xmlSerializer.attribute(null, "locked", Integer.toString(getUserLockedFields()));
        }
        if (isFgServiceShown()) {
            xmlSerializer.attribute(null, ATT_FG_SERVICE_SHOWN, Boolean.toString(isFgServiceShown()));
        }
        if (canShowBadge()) {
            xmlSerializer.attribute(null, ATT_SHOW_BADGE, Boolean.toString(canShowBadge()));
        }
        if (isDeleted()) {
            xmlSerializer.attribute(null, "deleted", Boolean.toString(isDeleted()));
        }
        if (getGroup() != null) {
            xmlSerializer.attribute(null, ATT_GROUP, getGroup());
        }
        if (isBlockable()) {
            xmlSerializer.attribute(null, ATT_BLOCKABLE_SYSTEM, Boolean.toString(isBlockable()));
        }
        if (getAllowBubbles() != -1) {
            xmlSerializer.attribute(null, ATT_ALLOW_BUBBLE, Integer.toString(getAllowBubbles()));
        }
        if (getOriginalImportance() != -1000) {
            xmlSerializer.attribute(null, ATT_ORIG_IMP, Integer.toString(getOriginalImportance()));
        }
        if (getParentChannelId() != null) {
            xmlSerializer.attribute(null, "parent", getParentChannelId());
        }
        if (getConversationId() != null) {
            xmlSerializer.attribute(null, ATT_CONVERSATION_ID, getConversationId());
        }
        if (isDemoted()) {
            xmlSerializer.attribute(null, ATT_DEMOTE, Boolean.toString(isDemoted()));
        }
        if (isImportantConversation()) {
            xmlSerializer.attribute(null, ATT_IMP_CONVERSATION, Boolean.toString(isImportantConversation()));
        }
        xmlSerializer.endTag(null, "channel");
    }

    @SystemApi
    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", getId());
        jSONObject.put("name", getName());
        jSONObject.put(ATT_DESC, getDescription());
        if (getImportance() != -1000) {
            jSONObject.put(ATT_IMPORTANCE, NotificationListenerService.Ranking.importanceToString(getImportance()));
        }
        if (canBypassDnd()) {
            jSONObject.put("priority", 2);
        }
        if (getLockscreenVisibility() != -1000) {
            jSONObject.put("visibility", Notification.visibilityToString(getLockscreenVisibility()));
        }
        if (getSound() != null) {
            jSONObject.put(ATT_SOUND, getSound().toString());
        }
        if (getAudioAttributes() != null) {
            jSONObject.put(ATT_USAGE, Integer.toString(getAudioAttributes().getUsage()));
            jSONObject.put("content_type", Integer.toString(getAudioAttributes().getContentType()));
            jSONObject.put("flags", Integer.toString(getAudioAttributes().getFlags()));
        }
        jSONObject.put("lights", Boolean.toString(shouldShowLights()));
        jSONObject.put(ATT_LIGHT_COLOR, Integer.toString(getLightColor()));
        jSONObject.put(ATT_VIBRATION_ENABLED, Boolean.toString(shouldVibrate()));
        jSONObject.put("locked", Integer.toString(getUserLockedFields()));
        jSONObject.put(ATT_FG_SERVICE_SHOWN, Boolean.toString(isFgServiceShown()));
        jSONObject.put(ATT_VIBRATION, longArrayToString(getVibrationPattern()));
        jSONObject.put(ATT_SHOW_BADGE, Boolean.toString(canShowBadge()));
        jSONObject.put("deleted", Boolean.toString(isDeleted()));
        jSONObject.put(ATT_GROUP, getGroup());
        jSONObject.put(ATT_BLOCKABLE_SYSTEM, isBlockable());
        jSONObject.put(ATT_ALLOW_BUBBLE, getAllowBubbles());
        return jSONObject;
    }

    private static AudioAttributes safeAudioAttributes(XmlPullParser xmlPullParser) {
        int iSafeInt = safeInt(xmlPullParser, ATT_USAGE, 5);
        int iSafeInt2 = safeInt(xmlPullParser, "content_type", 4);
        return new AudioAttributes.Builder().setUsage(iSafeInt).setContentType(iSafeInt2).setFlags(safeInt(xmlPullParser, "flags", 0)).build();
    }

    private static Uri safeUri(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return null;
        }
        return Uri.parse(attributeValue);
    }

    private static int safeInt(XmlPullParser xmlPullParser, String str, int i) {
        return tryParseInt(xmlPullParser.getAttributeValue(null, str), i);
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

    private static boolean safeBool(XmlPullParser xmlPullParser, String str, boolean z) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return TextUtils.isEmpty(attributeValue) ? z : Boolean.parseBoolean(attributeValue);
    }

    private static long[] safeLongArray(XmlPullParser xmlPullParser, String str, long[] jArr) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (TextUtils.isEmpty(attributeValue)) {
            return jArr;
        }
        String[] strArrSplit = attributeValue.split(",");
        long[] jArr2 = new long[strArrSplit.length];
        for (int i = 0; i < strArrSplit.length; i++) {
            try {
                jArr2[i] = Long.parseLong(strArrSplit[i]);
            } catch (NumberFormatException unused) {
                jArr2[i] = 0;
            }
        }
        return jArr2;
    }

    private static String longArrayToString(long[] jArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (jArr != null && jArr.length > 0) {
            for (int i = 0; i < jArr.length - 1; i++) {
                stringBuffer.append(jArr[i]);
                stringBuffer.append(",");
            }
            stringBuffer.append(jArr[jArr.length - 1]);
        }
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NotificationChannel notificationChannel = (NotificationChannel) obj;
        return getImportance() == notificationChannel.getImportance() && this.mBypassDnd == notificationChannel.mBypassDnd && getLockscreenVisibility() == notificationChannel.getLockscreenVisibility() && this.mLights == notificationChannel.mLights && getLightColor() == notificationChannel.getLightColor() && getUserLockedFields() == notificationChannel.getUserLockedFields() && isFgServiceShown() == notificationChannel.isFgServiceShown() && this.mVibrationEnabled == notificationChannel.mVibrationEnabled && this.mShowBadge == notificationChannel.mShowBadge && isDeleted() == notificationChannel.isDeleted() && isBlockable() == notificationChannel.isBlockable() && this.mAllowBubbles == notificationChannel.mAllowBubbles && Objects.equals(getId(), notificationChannel.getId()) && Objects.equals(getName(), notificationChannel.getName()) && Objects.equals(this.mDesc, notificationChannel.mDesc) && Objects.equals(getSound(), notificationChannel.getSound()) && Arrays.equals(this.mVibration, notificationChannel.mVibration) && Objects.equals(getGroup(), notificationChannel.getGroup()) && Objects.equals(getAudioAttributes(), notificationChannel.getAudioAttributes()) && this.mImportanceLockedByOEM == notificationChannel.mImportanceLockedByOEM && this.mImportanceLockedDefaultApp == notificationChannel.mImportanceLockedDefaultApp && this.mOriginalImportance == notificationChannel.mOriginalImportance && Objects.equals(getParentChannelId(), notificationChannel.getParentChannelId()) && Objects.equals(getConversationId(), notificationChannel.getConversationId()) && isDemoted() == notificationChannel.isDemoted() && isImportantConversation() == notificationChannel.isImportantConversation();
    }

    public int hashCode() {
        return (Objects.hash(getId(), getName(), this.mDesc, Integer.valueOf(getImportance()), Boolean.valueOf(this.mBypassDnd), Integer.valueOf(getLockscreenVisibility()), getSound(), Boolean.valueOf(this.mLights), Integer.valueOf(getLightColor()), Integer.valueOf(getUserLockedFields()), Boolean.valueOf(isFgServiceShown()), Boolean.valueOf(this.mVibrationEnabled), Boolean.valueOf(this.mShowBadge), Boolean.valueOf(isDeleted()), getGroup(), getAudioAttributes(), Boolean.valueOf(isBlockable()), Integer.valueOf(this.mAllowBubbles), Boolean.valueOf(this.mImportanceLockedByOEM), Boolean.valueOf(this.mImportanceLockedDefaultApp), Integer.valueOf(this.mOriginalImportance), this.mParentId, this.mConversationId, Boolean.valueOf(this.mDemoted), Boolean.valueOf(this.mImportantConvo)) * 31) + Arrays.hashCode(this.mVibration);
    }

    public void dump(PrintWriter printWriter, String str, boolean z) {
        printWriter.println(str + ("NotificationChannel{mId='" + this.mId + DateFormat.QUOTE + ", mName=" + (z ? (String) TextUtils.trimToLengthWithEllipsis(this.mName, 3) : this.mName) + getFieldsString() + '}'));
    }

    public String toString() {
        return "NotificationChannel{mId='" + this.mId + DateFormat.QUOTE + ", mName=" + this.mName + getFieldsString() + '}';
    }

    private String getFieldsString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", mDescription=");
        sb.append(!TextUtils.isEmpty(this.mDesc) ? "hasDescription " : "");
        sb.append(", mImportance=");
        sb.append(this.mImportance);
        sb.append(", mBypassDnd=");
        sb.append(this.mBypassDnd);
        sb.append(", mLockscreenVisibility=");
        sb.append(this.mLockscreenVisibility);
        sb.append(", mSound=");
        sb.append(this.mSound);
        sb.append(", mLights=");
        sb.append(this.mLights);
        sb.append(", mLightColor=");
        sb.append(this.mLightColor);
        sb.append(", mVibration=");
        sb.append(Arrays.toString(this.mVibration));
        sb.append(", mUserLockedFields=");
        sb.append(Integer.toHexString(this.mUserLockedFields));
        sb.append(", mFgServiceShown=");
        sb.append(this.mFgServiceShown);
        sb.append(", mVibrationEnabled=");
        sb.append(this.mVibrationEnabled);
        sb.append(", mShowBadge=");
        sb.append(this.mShowBadge);
        sb.append(", mDeleted=");
        sb.append(this.mDeleted);
        sb.append(", mGroup='");
        sb.append(this.mGroup);
        sb.append(DateFormat.QUOTE);
        sb.append(", mAudioAttributes=");
        sb.append(this.mAudioAttributes);
        sb.append(", mBlockableSystem=");
        sb.append(this.mBlockableSystem);
        sb.append(", mAllowBubbles=");
        sb.append(this.mAllowBubbles);
        sb.append(", mImportanceLockedByOEM=");
        sb.append(this.mImportanceLockedByOEM);
        sb.append(", mImportanceLockedDefaultApp=");
        sb.append(this.mImportanceLockedDefaultApp);
        sb.append(", mOriginalImp=");
        sb.append(this.mOriginalImportance);
        sb.append(", mParent=");
        sb.append(this.mParentId);
        sb.append(", mConversationId=");
        sb.append(this.mConversationId);
        sb.append(", mDemoted=");
        sb.append(this.mDemoted);
        sb.append(", mImportantConvo=");
        sb.append(this.mImportantConvo);
        return sb.toString();
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1138166333441L, this.mId);
        protoOutputStream.write(1138166333442L, this.mName);
        protoOutputStream.write(1138166333443L, this.mDesc);
        protoOutputStream.write(1120986464260L, this.mImportance);
        protoOutputStream.write(1133871366149L, this.mBypassDnd);
        protoOutputStream.write(1120986464262L, this.mLockscreenVisibility);
        Uri uri = this.mSound;
        if (uri != null) {
            protoOutputStream.write(1138166333447L, uri.toString());
        }
        protoOutputStream.write(1133871366152L, this.mLights);
        protoOutputStream.write(1120986464265L, this.mLightColor);
        long[] jArr = this.mVibration;
        if (jArr != null) {
            for (long j2 : jArr) {
                protoOutputStream.write(NotificationChannelProto.VIBRATION, j2);
            }
        }
        protoOutputStream.write(1120986464267L, this.mUserLockedFields);
        protoOutputStream.write(1133871366162L, this.mFgServiceShown);
        protoOutputStream.write(1133871366156L, this.mVibrationEnabled);
        protoOutputStream.write(1133871366157L, this.mShowBadge);
        protoOutputStream.write(1133871366158L, this.mDeleted);
        protoOutputStream.write(1138166333455L, this.mGroup);
        AudioAttributes audioAttributes = this.mAudioAttributes;
        if (audioAttributes != null) {
            audioAttributes.dumpDebug(protoOutputStream, 1146756268048L);
        }
        protoOutputStream.write(1133871366161L, this.mBlockableSystem);
        protoOutputStream.write(1133871366163L, this.mAllowBubbles);
        protoOutputStream.end(jStart);
    }
}
