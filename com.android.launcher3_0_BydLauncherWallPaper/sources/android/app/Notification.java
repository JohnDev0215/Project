package android.app;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.content.LocusId;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.media.PlayerBase;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.TextAppearanceSpan;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.util.proto.ProtoOutputStream;
import android.widget.RemoteViews;
import com.android.internal.R;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.ContrastColorUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class Notification implements Parcelable {
    public static final AudioAttributes AUDIO_ATTRIBUTES_DEFAULT;
    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";

    @SystemApi
    public static final String CATEGORY_CAR_EMERGENCY = "car_emergency";

    @SystemApi
    public static final String CATEGORY_CAR_INFORMATION = "car_information";

    @SystemApi
    public static final String CATEGORY_CAR_WARNING = "car_warning";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_NAVIGATION = "navigation";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    public static final int COLOR_DEFAULT = 0;
    public static final int COLOR_INVALID = 1;
    public static final Parcelable.Creator<Notification> CREATOR;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;

    @SystemApi
    public static final String EXTRA_ALLOW_DURING_SETUP = "android.allowDuringSetup";
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_BUILDER_APPLICATION_INFO = "android.appInfo";
    public static final String EXTRA_CHANNEL_GROUP_ID = "android.intent.extra.CHANNEL_GROUP_ID";
    public static final String EXTRA_CHANNEL_ID = "android.intent.extra.CHANNEL_ID";
    public static final String EXTRA_CHRONOMETER_COUNT_DOWN = "android.chronometerCountDown";
    public static final String EXTRA_COLORIZED = "android.colorized";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_CONTAINS_CUSTOM_VIEW = "android.contains.customView";
    public static final String EXTRA_CONVERSATION_ICON = "android.conversationIcon";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_CONVERSATION_UNREAD_MESSAGE_COUNT = "android.conversationUnreadMessageCount";
    public static final String EXTRA_FOREGROUND_APPS = "android.foregroundApps";
    public static final String EXTRA_HIDE_SMART_REPLIES = "android.hideSmartReplies";
    public static final String EXTRA_HISTORIC_MESSAGES = "android.messages.historic";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";

    @Deprecated
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_MESSAGING_PERSON = "android.messagingUser";
    public static final String EXTRA_NOTIFICATION_ID = "android.intent.extra.NOTIFICATION_ID";
    public static final String EXTRA_NOTIFICATION_TAG = "android.intent.extra.NOTIFICATION_TAG";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PEOPLE_LIST = "android.people.list";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REDUCED_IMAGES = "android.reduced.images";
    public static final String EXTRA_REMOTE_INPUT_DRAFT = "android.remoteInputDraft";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_REMOTE_INPUT_HISTORY_ITEMS = "android.remoteInputHistoryItems";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_REMOTE_INPUT_SPINNER = "android.remoteInputSpinner";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";

    @Deprecated
    public static final String EXTRA_SMALL_ICON = "android.icon";

    @SystemApi
    public static final String EXTRA_SUBSTITUTE_APP_NAME = "android.substName";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";

    @SystemApi
    public static final int FLAG_AUTOGROUP_SUMMARY = 1024;
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_BUBBLE = 4096;
    public static final int FLAG_CAN_COLORIZE = 2048;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;

    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;

    @Deprecated
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final String INTENT_CATEGORY_NOTIFICATION_PREFERENCES = "android.intent.category.NOTIFICATION_PREFERENCES";
    public static final int MAX_ACTION_BUTTONS = 3;
    private static final int MAX_CHARSEQUENCE_LENGTH = 1024;
    private static final int MAX_REPLY_HISTORY = 5;

    @Deprecated
    public static final int PRIORITY_DEFAULT = 0;

    @Deprecated
    public static final int PRIORITY_HIGH = 1;

    @Deprecated
    public static final int PRIORITY_LOW = -1;

    @Deprecated
    public static final int PRIORITY_MAX = 2;

    @Deprecated
    public static final int PRIORITY_MIN = -2;
    private static final ArraySet<Integer> STANDARD_LAYOUTS;

    @Deprecated
    public static final int STREAM_DEFAULT = -1;
    private static final String TAG = "Notification";
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;
    public static IBinder processWhitelistToken;
    public Action[] actions;
    public ArraySet<PendingIntent> allPendingIntents;

    @Deprecated
    public AudioAttributes audioAttributes;

    @Deprecated
    public int audioStreamType;

    @Deprecated
    public RemoteViews bigContentView;
    public String category;
    public int color;
    public PendingIntent contentIntent;

    @Deprecated
    public RemoteViews contentView;
    private long creationTime;

    @Deprecated
    public int defaults;
    public PendingIntent deleteIntent;
    public Bundle extras;
    public int flags;
    public PendingIntent fullScreenIntent;

    @Deprecated
    public RemoteViews headsUpContentView;

    @Deprecated
    public int icon;
    public int iconLevel;

    @Deprecated
    public Bitmap largeIcon;

    @Deprecated
    public int ledARGB;

    @Deprecated
    public int ledOffMS;

    @Deprecated
    public int ledOnMS;
    private boolean mAllowSystemGeneratedContextualActions;
    private int mBadgeIcon;
    private BubbleMetadata mBubbleMetadata;
    private String mChannelId;
    private int mGroupAlertBehavior;
    private String mGroupKey;
    private Icon mLargeIcon;
    private LocusId mLocusId;
    private CharSequence mSettingsText;
    private String mShortcutId;
    private Icon mSmallIcon;
    private String mSortKey;
    private long mTimeout;
    private boolean mUsesStandardHeader;
    private IBinder mWhitelistToken;
    public int number;

    @Deprecated
    public int priority;
    public Notification publicVersion;

    @Deprecated
    public Uri sound;
    public CharSequence tickerText;

    @Deprecated
    public RemoteViews tickerView;

    @Deprecated
    public long[] vibrate;
    public int visibility;
    public long when;

    public interface Extender {
        Builder extend(Builder builder);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupAlertBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NotificationFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Priority {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static {
        ArraySet<Integer> arraySet = new ArraySet<>();
        STANDARD_LAYOUTS = arraySet;
        arraySet.add(Integer.valueOf(R.layout.notification_template_material_base));
        arraySet.add(Integer.valueOf(R.layout.notification_template_material_big_base));
        arraySet.add(Integer.valueOf(R.layout.notification_template_material_big_picture));
        arraySet.add(Integer.valueOf(R.layout.notification_template_material_big_text));
        arraySet.add(Integer.valueOf(R.layout.notification_template_material_inbox));
        arraySet.add(Integer.valueOf(R.layout.notification_template_material_messaging));
        arraySet.add(Integer.valueOf(R.layout.notification_template_material_conversation));
        arraySet.add(Integer.valueOf(R.layout.notification_template_material_media));
        arraySet.add(Integer.valueOf(R.layout.notification_template_material_big_media));
        arraySet.add(Integer.valueOf(R.layout.notification_template_header));
        AUDIO_ATTRIBUTES_DEFAULT = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
        CREATOR = new Parcelable.Creator<Notification>() { // from class: android.app.Notification.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Notification createFromParcel(Parcel parcel) {
                return new Notification(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Notification[] newArray(int i) {
                return new Notification[i];
            }
        };
    }

    public String getGroup() {
        return this.mGroupKey;
    }

    public String getSortKey() {
        return this.mSortKey;
    }

    public static class Action implements Parcelable {
        public static final Parcelable.Creator<Action> CREATOR = new Parcelable.Creator<Action>() { // from class: android.app.Notification.Action.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Action createFromParcel(Parcel parcel) {
                return new Action(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Action[] newArray(int i) {
                return new Action[i];
            }
        };
        private static final String EXTRA_DATA_ONLY_INPUTS = "android.extra.DATA_ONLY_INPUTS";
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;
        public static final int SEMANTIC_ACTION_CALL = 10;
        public static final int SEMANTIC_ACTION_DELETE = 4;
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
        public static final int SEMANTIC_ACTION_MUTE = 6;
        public static final int SEMANTIC_ACTION_NONE = 0;
        public static final int SEMANTIC_ACTION_REPLY = 1;
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
        public static final int SEMANTIC_ACTION_UNMUTE = 7;
        public PendingIntent actionIntent;

        @Deprecated
        public int icon;
        private boolean mAllowGeneratedReplies;
        private final Bundle mExtras;
        private Icon mIcon;
        private final boolean mIsContextual;
        private final RemoteInput[] mRemoteInputs;
        private final int mSemanticAction;
        public CharSequence title;

        public interface Extender {
            Builder extend(Builder builder);
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SemanticAction {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        private Action(Parcel parcel) {
            this.mAllowGeneratedReplies = true;
            if (parcel.readInt() != 0) {
                Icon iconCreateFromParcel = Icon.CREATOR.createFromParcel(parcel);
                this.mIcon = iconCreateFromParcel;
                if (iconCreateFromParcel.getType() == 2) {
                    this.icon = this.mIcon.getResId();
                }
            }
            this.title = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            if (parcel.readInt() == 1) {
                this.actionIntent = PendingIntent.CREATOR.createFromParcel(parcel);
            }
            this.mExtras = Bundle.setDefusable(parcel.readBundle(), true);
            this.mRemoteInputs = (RemoteInput[]) parcel.createTypedArray(RemoteInput.CREATOR);
            this.mAllowGeneratedReplies = parcel.readInt() == 1;
            this.mSemanticAction = parcel.readInt();
            this.mIsContextual = parcel.readInt() == 1;
        }

        @Deprecated
        public Action(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this(Icon.createWithResource("", i), charSequence, pendingIntent, new Bundle(), null, true, 0, false);
        }

        private Action(Icon icon, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, boolean z, int i, boolean z2) {
            this.mAllowGeneratedReplies = true;
            this.mIcon = icon;
            if (icon != null && icon.getType() == 2) {
                this.icon = icon.getResId();
            }
            this.title = charSequence;
            this.actionIntent = pendingIntent;
            this.mExtras = bundle == null ? new Bundle() : bundle;
            this.mRemoteInputs = remoteInputArr;
            this.mAllowGeneratedReplies = z;
            this.mSemanticAction = i;
            this.mIsContextual = z2;
        }

        public Icon getIcon() {
            int i;
            if (this.mIcon == null && (i = this.icon) != 0) {
                this.mIcon = Icon.createWithResource("", i);
            }
            return this.mIcon;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public boolean isContextual() {
            return this.mIsContextual;
        }

        public RemoteInput[] getDataOnlyRemoteInputs() {
            return (RemoteInput[]) Notification.getParcelableArrayFromBundle(this.mExtras, EXTRA_DATA_ONLY_INPUTS, RemoteInput.class);
        }

        public static final class Builder {
            private boolean mAllowGeneratedReplies;
            private final Bundle mExtras;
            private final Icon mIcon;
            private final PendingIntent mIntent;
            private boolean mIsContextual;
            private ArrayList<RemoteInput> mRemoteInputs;
            private int mSemanticAction;
            private final CharSequence mTitle;

            @Deprecated
            public Builder(int i, CharSequence charSequence, PendingIntent pendingIntent) {
                this(Icon.createWithResource("", i), charSequence, pendingIntent);
            }

            public Builder(Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
                this(icon, charSequence, pendingIntent, new Bundle(), null, true, 0);
            }

            public Builder(Action action) {
                this(action.getIcon(), action.title, action.actionIntent, new Bundle(action.mExtras), action.getRemoteInputs(), action.getAllowGeneratedReplies(), action.getSemanticAction());
            }

            private Builder(Icon icon, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, boolean z, int i) {
                this.mAllowGeneratedReplies = true;
                this.mIcon = icon;
                this.mTitle = charSequence;
                this.mIntent = pendingIntent;
                this.mExtras = bundle;
                if (remoteInputArr != null) {
                    ArrayList<RemoteInput> arrayList = new ArrayList<>(remoteInputArr.length);
                    this.mRemoteInputs = arrayList;
                    Collections.addAll(arrayList, remoteInputArr);
                }
                this.mAllowGeneratedReplies = z;
                this.mSemanticAction = i;
            }

            public Builder addExtras(Bundle bundle) {
                if (bundle != null) {
                    this.mExtras.putAll(bundle);
                }
                return this;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public Builder addRemoteInput(RemoteInput remoteInput) {
                if (this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList<>();
                }
                this.mRemoteInputs.add(remoteInput);
                return this;
            }

            public Builder setAllowGeneratedReplies(boolean z) {
                this.mAllowGeneratedReplies = z;
                return this;
            }

            public Builder setSemanticAction(int i) {
                this.mSemanticAction = i;
                return this;
            }

            public Builder setContextual(boolean z) {
                this.mIsContextual = z;
                return this;
            }

            public Builder extend(Extender extender) {
                extender.extend(this);
                return this;
            }

            private void checkContextualActionNullFields() {
                if (this.mIsContextual) {
                    Objects.requireNonNull(this.mIcon, "Contextual Actions must contain a valid icon");
                    Objects.requireNonNull(this.mIntent, "Contextual Actions must contain a valid PendingIntent");
                }
            }

            public Action build() {
                checkContextualActionNullFields();
                ArrayList arrayList = new ArrayList();
                RemoteInput[] remoteInputArr = (RemoteInput[]) Notification.getParcelableArrayFromBundle(this.mExtras, Action.EXTRA_DATA_ONLY_INPUTS, RemoteInput.class);
                if (remoteInputArr != null) {
                    for (RemoteInput remoteInput : remoteInputArr) {
                        arrayList.add(remoteInput);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                ArrayList<RemoteInput> arrayList3 = this.mRemoteInputs;
                if (arrayList3 != null) {
                    for (RemoteInput remoteInput2 : arrayList3) {
                        if (remoteInput2.isDataOnly()) {
                            arrayList.add(remoteInput2);
                        } else {
                            arrayList2.add(remoteInput2);
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    this.mExtras.putParcelableArray(Action.EXTRA_DATA_ONLY_INPUTS, (RemoteInput[]) arrayList.toArray(new RemoteInput[arrayList.size()]));
                }
                return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayList2.isEmpty() ? null : (RemoteInput[]) arrayList2.toArray(new RemoteInput[arrayList2.size()]), this.mAllowGeneratedReplies, this.mSemanticAction, this.mIsContextual);
            }
        }

        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Action m11clone() {
            return new Action(getIcon(), this.title, this.actionIntent, this.mExtras == null ? new Bundle() : new Bundle(this.mExtras), getRemoteInputs(), getAllowGeneratedReplies(), getSemanticAction(), isContextual());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            Icon icon = getIcon();
            if (icon != null) {
                parcel.writeInt(1);
                icon.writeToParcel(parcel, 0);
            } else {
                parcel.writeInt(0);
            }
            TextUtils.writeToParcel(this.title, parcel, i);
            if (this.actionIntent != null) {
                parcel.writeInt(1);
                this.actionIntent.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
            parcel.writeBundle(this.mExtras);
            parcel.writeTypedArray(this.mRemoteInputs, i);
            parcel.writeInt(this.mAllowGeneratedReplies ? 1 : 0);
            parcel.writeInt(this.mSemanticAction);
            parcel.writeInt(this.mIsContextual ? 1 : 0);
        }

        public static final class WearableExtender implements Extender {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final int FLAG_HINT_DISPLAY_INLINE = 4;
            private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags;
            private CharSequence mInProgressLabel;

            public WearableExtender() {
                this.mFlags = 1;
            }

            public WearableExtender(Action action) {
                this.mFlags = 1;
                Bundle bundle = action.getExtras().getBundle(EXTRA_WEARABLE_EXTENSIONS);
                if (bundle != null) {
                    this.mFlags = bundle.getInt("flags", 1);
                    this.mInProgressLabel = bundle.getCharSequence(KEY_IN_PROGRESS_LABEL);
                    this.mConfirmLabel = bundle.getCharSequence(KEY_CONFIRM_LABEL);
                    this.mCancelLabel = bundle.getCharSequence(KEY_CANCEL_LABEL);
                }
            }

            @Override // android.app.Notification.Action.Extender
            public Builder extend(Builder builder) {
                Bundle bundle = new Bundle();
                int i = this.mFlags;
                if (i != 1) {
                    bundle.putInt("flags", i);
                }
                CharSequence charSequence = this.mInProgressLabel;
                if (charSequence != null) {
                    bundle.putCharSequence(KEY_IN_PROGRESS_LABEL, charSequence);
                }
                CharSequence charSequence2 = this.mConfirmLabel;
                if (charSequence2 != null) {
                    bundle.putCharSequence(KEY_CONFIRM_LABEL, charSequence2);
                }
                CharSequence charSequence3 = this.mCancelLabel;
                if (charSequence3 != null) {
                    bundle.putCharSequence(KEY_CANCEL_LABEL, charSequence3);
                }
                builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, bundle);
                return builder;
            }

            /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
            public WearableExtender m12clone() {
                WearableExtender wearableExtender = new WearableExtender();
                wearableExtender.mFlags = this.mFlags;
                wearableExtender.mInProgressLabel = this.mInProgressLabel;
                wearableExtender.mConfirmLabel = this.mConfirmLabel;
                wearableExtender.mCancelLabel = this.mCancelLabel;
                return wearableExtender;
            }

            public WearableExtender setAvailableOffline(boolean z) {
                setFlag(1, z);
                return this;
            }

            public boolean isAvailableOffline() {
                return (this.mFlags & 1) != 0;
            }

            private void setFlag(int i, boolean z) {
                if (z) {
                    this.mFlags = i | this.mFlags;
                } else {
                    this.mFlags = (~i) & this.mFlags;
                }
            }

            @Deprecated
            public WearableExtender setInProgressLabel(CharSequence charSequence) {
                this.mInProgressLabel = charSequence;
                return this;
            }

            @Deprecated
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }

            @Deprecated
            public WearableExtender setConfirmLabel(CharSequence charSequence) {
                this.mConfirmLabel = charSequence;
                return this;
            }

            @Deprecated
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }

            @Deprecated
            public WearableExtender setCancelLabel(CharSequence charSequence) {
                this.mCancelLabel = charSequence;
                return this;
            }

            @Deprecated
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }

            public WearableExtender setHintLaunchesActivity(boolean z) {
                setFlag(2, z);
                return this;
            }

            public boolean getHintLaunchesActivity() {
                return (this.mFlags & 2) != 0;
            }

            public WearableExtender setHintDisplayActionInline(boolean z) {
                setFlag(4, z);
                return this;
            }

            public boolean getHintDisplayActionInline() {
                return (this.mFlags & 4) != 0;
            }
        }
    }

    public Notification() {
        this.number = 0;
        this.audioStreamType = -1;
        this.audioAttributes = AUDIO_ATTRIBUTES_DEFAULT;
        this.color = 0;
        this.extras = new Bundle();
        this.mGroupAlertBehavior = 0;
        this.mBadgeIcon = 0;
        this.mAllowSystemGeneratedContextualActions = true;
        this.when = System.currentTimeMillis();
        this.creationTime = System.currentTimeMillis();
        this.priority = 0;
    }

    public Notification(Context context, int i, CharSequence charSequence, long j, CharSequence charSequence2, CharSequence charSequence3, Intent intent) {
        this.number = 0;
        this.audioStreamType = -1;
        this.audioAttributes = AUDIO_ATTRIBUTES_DEFAULT;
        this.color = 0;
        this.extras = new Bundle();
        this.mGroupAlertBehavior = 0;
        this.mBadgeIcon = 0;
        this.mAllowSystemGeneratedContextualActions = true;
        new Builder(context).setWhen(j).setSmallIcon(i).setTicker(charSequence).setContentTitle(charSequence2).setContentText(charSequence3).setContentIntent(PendingIntent.getActivity(context, 0, intent, 0)).buildInto(this);
    }

    @Deprecated
    public Notification(int i, CharSequence charSequence, long j) {
        this.number = 0;
        this.audioStreamType = -1;
        this.audioAttributes = AUDIO_ATTRIBUTES_DEFAULT;
        this.color = 0;
        this.extras = new Bundle();
        this.mGroupAlertBehavior = 0;
        this.mBadgeIcon = 0;
        this.mAllowSystemGeneratedContextualActions = true;
        this.icon = i;
        this.tickerText = charSequence;
        this.when = j;
        this.creationTime = System.currentTimeMillis();
    }

    public Notification(Parcel parcel) {
        this.number = 0;
        this.audioStreamType = -1;
        this.audioAttributes = AUDIO_ATTRIBUTES_DEFAULT;
        this.color = 0;
        this.extras = new Bundle();
        this.mGroupAlertBehavior = 0;
        this.mBadgeIcon = 0;
        this.mAllowSystemGeneratedContextualActions = true;
        readFromParcelImpl(parcel);
        this.allPendingIntents = parcel.readArraySet(null);
    }

    private void readFromParcelImpl(Parcel parcel) {
        parcel.readInt();
        IBinder strongBinder = parcel.readStrongBinder();
        this.mWhitelistToken = strongBinder;
        if (strongBinder == null) {
            this.mWhitelistToken = processWhitelistToken;
        }
        parcel.setClassCookie(PendingIntent.class, this.mWhitelistToken);
        this.when = parcel.readLong();
        this.creationTime = parcel.readLong();
        if (parcel.readInt() != 0) {
            Icon iconCreateFromParcel = Icon.CREATOR.createFromParcel(parcel);
            this.mSmallIcon = iconCreateFromParcel;
            if (iconCreateFromParcel.getType() == 2) {
                this.icon = this.mSmallIcon.getResId();
            }
        }
        this.number = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.contentIntent = PendingIntent.CREATOR.createFromParcel(parcel);
        }
        if (parcel.readInt() != 0) {
            this.deleteIntent = PendingIntent.CREATOR.createFromParcel(parcel);
        }
        if (parcel.readInt() != 0) {
            this.tickerText = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
        if (parcel.readInt() != 0) {
            this.tickerView = RemoteViews.CREATOR.createFromParcel(parcel);
        }
        if (parcel.readInt() != 0) {
            this.contentView = RemoteViews.CREATOR.createFromParcel(parcel);
        }
        if (parcel.readInt() != 0) {
            this.mLargeIcon = Icon.CREATOR.createFromParcel(parcel);
        }
        this.defaults = parcel.readInt();
        this.flags = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.sound = Uri.CREATOR.createFromParcel(parcel);
        }
        this.audioStreamType = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.audioAttributes = AudioAttributes.CREATOR.createFromParcel(parcel);
        }
        this.vibrate = parcel.createLongArray();
        this.ledARGB = parcel.readInt();
        this.ledOnMS = parcel.readInt();
        this.ledOffMS = parcel.readInt();
        this.iconLevel = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.fullScreenIntent = PendingIntent.CREATOR.createFromParcel(parcel);
        }
        this.priority = parcel.readInt();
        this.category = parcel.readString8();
        this.mGroupKey = parcel.readString8();
        this.mSortKey = parcel.readString8();
        this.extras = Bundle.setDefusable(parcel.readBundle(), true);
        fixDuplicateExtras();
        this.actions = (Action[]) parcel.createTypedArray(Action.CREATOR);
        if (parcel.readInt() != 0) {
            this.bigContentView = RemoteViews.CREATOR.createFromParcel(parcel);
        }
        if (parcel.readInt() != 0) {
            this.headsUpContentView = RemoteViews.CREATOR.createFromParcel(parcel);
        }
        this.visibility = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.publicVersion = CREATOR.createFromParcel(parcel);
        }
        this.color = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.mChannelId = parcel.readString8();
        }
        this.mTimeout = parcel.readLong();
        if (parcel.readInt() != 0) {
            this.mShortcutId = parcel.readString8();
        }
        if (parcel.readInt() != 0) {
            this.mLocusId = LocusId.CREATOR.createFromParcel(parcel);
        }
        this.mBadgeIcon = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.mSettingsText = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
        this.mGroupAlertBehavior = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.mBubbleMetadata = BubbleMetadata.CREATOR.createFromParcel(parcel);
        }
        this.mAllowSystemGeneratedContextualActions = parcel.readBoolean();
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Notification m10clone() {
        Notification notification = new Notification();
        cloneInto(notification, true);
        return notification;
    }

    public void cloneInto(Notification notification, boolean z) {
        RemoteViews remoteViews;
        RemoteViews remoteViews2;
        Icon icon;
        RemoteViews remoteViews3;
        RemoteViews remoteViews4;
        notification.mWhitelistToken = this.mWhitelistToken;
        notification.when = this.when;
        notification.creationTime = this.creationTime;
        notification.mSmallIcon = this.mSmallIcon;
        notification.number = this.number;
        notification.contentIntent = this.contentIntent;
        notification.deleteIntent = this.deleteIntent;
        notification.fullScreenIntent = this.fullScreenIntent;
        CharSequence charSequence = this.tickerText;
        if (charSequence != null) {
            notification.tickerText = charSequence.toString();
        }
        if (z && (remoteViews4 = this.tickerView) != null) {
            notification.tickerView = remoteViews4.mo13clone();
        }
        if (z && (remoteViews3 = this.contentView) != null) {
            notification.contentView = remoteViews3.mo13clone();
        }
        if (z && (icon = this.mLargeIcon) != null) {
            notification.mLargeIcon = icon;
        }
        notification.iconLevel = this.iconLevel;
        notification.sound = this.sound;
        notification.audioStreamType = this.audioStreamType;
        if (this.audioAttributes != null) {
            notification.audioAttributes = new AudioAttributes.Builder(this.audioAttributes).build();
        }
        long[] jArr = this.vibrate;
        int i = 0;
        if (jArr != null) {
            int length = jArr.length;
            long[] jArr2 = new long[length];
            notification.vibrate = jArr2;
            System.arraycopy(jArr, 0, jArr2, 0, length);
        }
        notification.ledARGB = this.ledARGB;
        notification.ledOnMS = this.ledOnMS;
        notification.ledOffMS = this.ledOffMS;
        notification.defaults = this.defaults;
        notification.flags = this.flags;
        notification.priority = this.priority;
        notification.category = this.category;
        notification.mGroupKey = this.mGroupKey;
        notification.mSortKey = this.mSortKey;
        if (this.extras != null) {
            try {
                Bundle bundle = new Bundle(this.extras);
                notification.extras = bundle;
                bundle.size();
            } catch (BadParcelableException e) {
                Log.e(TAG, "could not unparcel extras from notification: " + this, e);
                notification.extras = null;
            }
        }
        if (!ArrayUtils.isEmpty(this.allPendingIntents)) {
            notification.allPendingIntents = new ArraySet<>((ArraySet) this.allPendingIntents);
        }
        Action[] actionArr = this.actions;
        if (actionArr != null) {
            notification.actions = new Action[actionArr.length];
            while (true) {
                Action[] actionArr2 = this.actions;
                if (i >= actionArr2.length) {
                    break;
                }
                if (actionArr2[i] != null) {
                    notification.actions[i] = actionArr2[i].m11clone();
                }
                i++;
            }
        }
        if (z && (remoteViews2 = this.bigContentView) != null) {
            notification.bigContentView = remoteViews2.mo13clone();
        }
        if (z && (remoteViews = this.headsUpContentView) != null) {
            notification.headsUpContentView = remoteViews.mo13clone();
        }
        notification.visibility = this.visibility;
        if (this.publicVersion != null) {
            Notification notification2 = new Notification();
            notification.publicVersion = notification2;
            this.publicVersion.cloneInto(notification2, z);
        }
        notification.color = this.color;
        notification.mChannelId = this.mChannelId;
        notification.mTimeout = this.mTimeout;
        notification.mShortcutId = this.mShortcutId;
        notification.mLocusId = this.mLocusId;
        notification.mBadgeIcon = this.mBadgeIcon;
        notification.mSettingsText = this.mSettingsText;
        notification.mGroupAlertBehavior = this.mGroupAlertBehavior;
        notification.mBubbleMetadata = this.mBubbleMetadata;
        notification.mAllowSystemGeneratedContextualActions = this.mAllowSystemGeneratedContextualActions;
        if (z) {
            return;
        }
        notification.lightenPayload();
    }

    public void visitUris(Consumer<Uri> consumer) {
        Bundle bundle;
        consumer.accept(this.sound);
        RemoteViews remoteViews = this.tickerView;
        if (remoteViews != null) {
            remoteViews.visitUris(consumer);
        }
        RemoteViews remoteViews2 = this.contentView;
        if (remoteViews2 != null) {
            remoteViews2.visitUris(consumer);
        }
        RemoteViews remoteViews3 = this.bigContentView;
        if (remoteViews3 != null) {
            remoteViews3.visitUris(consumer);
        }
        RemoteViews remoteViews4 = this.headsUpContentView;
        if (remoteViews4 != null) {
            remoteViews4.visitUris(consumer);
        }
        Bundle bundle2 = this.extras;
        if (bundle2 != null) {
            consumer.accept((Uri) bundle2.getParcelable("android.audioContents"));
            if (this.extras.containsKey("android.backgroundImageUri")) {
                consumer.accept(Uri.parse(this.extras.getString("android.backgroundImageUri")));
            }
            ArrayList<Person> parcelableArrayList = this.extras.getParcelableArrayList(EXTRA_PEOPLE_LIST);
            if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
                for (Person person : parcelableArrayList) {
                    if (person.getIconUri() != null) {
                        consumer.accept(person.getIconUri());
                    }
                }
            }
            Person person2 = (Person) this.extras.getParcelable(EXTRA_MESSAGING_PERSON);
            if (person2 != null && person2.getIconUri() != null) {
                consumer.accept(person2.getIconUri());
            }
        }
        if (MessagingStyle.class.equals(getNotificationStyle()) && (bundle = this.extras) != null) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("android.messages");
            if (!ArrayUtils.isEmpty(parcelableArray)) {
                for (MessagingStyle.Message message : MessagingStyle.Message.getMessagesFromBundleArray(parcelableArray)) {
                    consumer.accept(message.getDataUri());
                    Person senderPerson = message.getSenderPerson();
                    if (senderPerson != null && senderPerson.getIconUri() != null) {
                        consumer.accept(senderPerson.getIconUri());
                    }
                }
            }
            Parcelable[] parcelableArray2 = this.extras.getParcelableArray(EXTRA_HISTORIC_MESSAGES);
            if (!ArrayUtils.isEmpty(parcelableArray2)) {
                for (MessagingStyle.Message message2 : MessagingStyle.Message.getMessagesFromBundleArray(parcelableArray2)) {
                    consumer.accept(message2.getDataUri());
                    Person senderPerson2 = message2.getSenderPerson();
                    if (senderPerson2 != null && senderPerson2.getIconUri() != null) {
                        consumer.accept(senderPerson2.getIconUri());
                    }
                }
            }
        }
        BubbleMetadata bubbleMetadata = this.mBubbleMetadata;
        if (bubbleMetadata == null || bubbleMetadata.getIcon() == null) {
            return;
        }
        Icon icon = this.mBubbleMetadata.getIcon();
        int type = icon.getType();
        if (type == 6 || type == 4) {
            consumer.accept(icon.getUri());
        }
    }

    public final void lightenPayload() {
        Object obj;
        this.tickerView = null;
        this.contentView = null;
        this.bigContentView = null;
        this.headsUpContentView = null;
        this.mLargeIcon = null;
        Bundle bundle = this.extras;
        if (bundle == null || bundle.isEmpty()) {
            return;
        }
        Set<String> setKeySet = this.extras.keySet();
        int size = setKeySet.size();
        String[] strArr = (String[]) setKeySet.toArray(new String[size]);
        for (int i = 0; i < size; i++) {
            String str = strArr[i];
            if (!"android.tv.EXTENSIONS".equals(str) && (obj = this.extras.get(str)) != null && ((obj instanceof Parcelable) || (obj instanceof Parcelable[]) || (obj instanceof SparseArray) || (obj instanceof ArrayList))) {
                this.extras.remove(str);
            }
        }
    }

    public static CharSequence safeCharSequence(CharSequence charSequence) {
        if (charSequence == null) {
            return charSequence;
        }
        if (charSequence.length() > 1024) {
            charSequence = charSequence.subSequence(0, 1024);
        }
        if (charSequence instanceof Parcelable) {
            Log.e(TAG, "warning: " + charSequence.getClass().getCanonicalName() + " instance is a custom Parcelable and not allowed in Notification");
            return charSequence.toString();
        }
        return removeTextSizeSpans(charSequence);
    }

    private static CharSequence removeTextSizeSpans(CharSequence charSequence) {
        Object textAppearanceSpan;
        if (!(charSequence instanceof Spanned)) {
            return charSequence;
        }
        Spanned spanned = (Spanned) charSequence;
        Object[] spans = spanned.getSpans(0, spanned.length(), Object.class);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spanned.toString());
        for (Object obj : spans) {
            Object underlying = obj instanceof CharacterStyle ? ((CharacterStyle) obj).getUnderlying() : obj;
            if (underlying instanceof TextAppearanceSpan) {
                TextAppearanceSpan textAppearanceSpan2 = (TextAppearanceSpan) underlying;
                textAppearanceSpan = new TextAppearanceSpan(textAppearanceSpan2.getFamily(), textAppearanceSpan2.getTextStyle(), -1, textAppearanceSpan2.getTextColor(), textAppearanceSpan2.getLinkTextColor());
            } else if (!(underlying instanceof RelativeSizeSpan) && !(underlying instanceof AbsoluteSizeSpan)) {
                textAppearanceSpan = obj;
            }
            spannableStringBuilder.setSpan(textAppearanceSpan, spanned.getSpanStart(obj), spanned.getSpanEnd(obj), spanned.getSpanFlags(obj));
        }
        return spannableStringBuilder;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(final Parcel parcel, int i) {
        boolean z = this.allPendingIntents == null;
        if (z) {
            PendingIntent.setOnMarshaledListener(new PendingIntent.OnMarshaledListener() { // from class: android.app.-$$Lambda$Notification$stAYBd-DYtZ2AXSHoBRVAVM4d4k
                @Override // android.app.PendingIntent.OnMarshaledListener
                public final void onMarshaled(PendingIntent pendingIntent, Parcel parcel2, int i2) {
                    this.f$0.lambda$writeToParcel$0$Notification(parcel, pendingIntent, parcel2, i2);
                }
            });
        }
        try {
            writeToParcelImpl(parcel, i);
            synchronized (this) {
                parcel.writeArraySet(this.allPendingIntents);
            }
        } finally {
            if (z) {
                PendingIntent.setOnMarshaledListener(null);
            }
        }
    }

    public /* synthetic */ void lambda$writeToParcel$0$Notification(Parcel parcel, PendingIntent pendingIntent, Parcel parcel2, int i) {
        if (parcel == parcel2) {
            synchronized (this) {
                if (this.allPendingIntents == null) {
                    this.allPendingIntents = new ArraySet<>();
                }
                this.allPendingIntents.add(pendingIntent);
            }
        }
    }

    private void writeToParcelImpl(Parcel parcel, int i) {
        Bitmap bitmap;
        int i2;
        parcel.writeInt(1);
        parcel.writeStrongBinder(this.mWhitelistToken);
        parcel.writeLong(this.when);
        parcel.writeLong(this.creationTime);
        if (this.mSmallIcon == null && (i2 = this.icon) != 0) {
            this.mSmallIcon = Icon.createWithResource("", i2);
        }
        if (this.mSmallIcon != null) {
            parcel.writeInt(1);
            this.mSmallIcon.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.number);
        if (this.contentIntent != null) {
            parcel.writeInt(1);
            this.contentIntent.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        if (this.deleteIntent != null) {
            parcel.writeInt(1);
            this.deleteIntent.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        if (this.tickerText != null) {
            parcel.writeInt(1);
            TextUtils.writeToParcel(this.tickerText, parcel, i);
        } else {
            parcel.writeInt(0);
        }
        if (this.tickerView != null) {
            parcel.writeInt(1);
            this.tickerView.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        if (this.contentView != null) {
            parcel.writeInt(1);
            this.contentView.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        if (this.mLargeIcon == null && (bitmap = this.largeIcon) != null) {
            this.mLargeIcon = Icon.createWithBitmap(bitmap);
        }
        if (this.mLargeIcon != null) {
            parcel.writeInt(1);
            this.mLargeIcon.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.defaults);
        parcel.writeInt(this.flags);
        if (this.sound != null) {
            parcel.writeInt(1);
            this.sound.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.audioStreamType);
        if (this.audioAttributes != null) {
            parcel.writeInt(1);
            this.audioAttributes.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeLongArray(this.vibrate);
        parcel.writeInt(this.ledARGB);
        parcel.writeInt(this.ledOnMS);
        parcel.writeInt(this.ledOffMS);
        parcel.writeInt(this.iconLevel);
        if (this.fullScreenIntent != null) {
            parcel.writeInt(1);
            this.fullScreenIntent.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.priority);
        parcel.writeString8(this.category);
        parcel.writeString8(this.mGroupKey);
        parcel.writeString8(this.mSortKey);
        parcel.writeBundle(this.extras);
        parcel.writeTypedArray(this.actions, 0);
        if (this.bigContentView != null) {
            parcel.writeInt(1);
            this.bigContentView.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        if (this.headsUpContentView != null) {
            parcel.writeInt(1);
            this.headsUpContentView.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.visibility);
        if (this.publicVersion != null) {
            parcel.writeInt(1);
            this.publicVersion.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.color);
        if (this.mChannelId != null) {
            parcel.writeInt(1);
            parcel.writeString8(this.mChannelId);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeLong(this.mTimeout);
        if (this.mShortcutId != null) {
            parcel.writeInt(1);
            parcel.writeString8(this.mShortcutId);
        } else {
            parcel.writeInt(0);
        }
        if (this.mLocusId != null) {
            parcel.writeInt(1);
            this.mLocusId.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.mBadgeIcon);
        if (this.mSettingsText != null) {
            parcel.writeInt(1);
            TextUtils.writeToParcel(this.mSettingsText, parcel, i);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.mGroupAlertBehavior);
        if (this.mBubbleMetadata != null) {
            parcel.writeInt(1);
            this.mBubbleMetadata.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeBoolean(this.mAllowSystemGeneratedContextualActions);
    }

    public static boolean areActionsVisiblyDifferent(Notification notification, Notification notification2) {
        Action[] actionArr = notification.actions;
        Action[] actionArr2 = notification2.actions;
        if ((actionArr == null && actionArr2 != null) || (actionArr != null && actionArr2 == null)) {
            return true;
        }
        if (actionArr != null && actionArr2 != null) {
            if (actionArr.length != actionArr2.length) {
                return true;
            }
            for (int i = 0; i < actionArr.length; i++) {
                if (!Objects.equals(String.valueOf(actionArr[i].title), String.valueOf(actionArr2[i].title))) {
                    return true;
                }
                RemoteInput[] remoteInputs = actionArr[i].getRemoteInputs();
                RemoteInput[] remoteInputs2 = actionArr2[i].getRemoteInputs();
                if (remoteInputs == null) {
                    remoteInputs = new RemoteInput[0];
                }
                if (remoteInputs2 == null) {
                    remoteInputs2 = new RemoteInput[0];
                }
                if (remoteInputs.length != remoteInputs2.length) {
                    return true;
                }
                for (int i2 = 0; i2 < remoteInputs.length; i2++) {
                    if (!Objects.equals(String.valueOf(remoteInputs[i2].getLabel()), String.valueOf(remoteInputs2[i2].getLabel()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean areStyledNotificationsVisiblyDifferent(Builder builder, Builder builder2) {
        if (builder.getStyle() == null) {
            return builder2.getStyle() != null;
        }
        if (builder2.getStyle() == null) {
            return true;
        }
        return builder.getStyle().areNotificationsVisiblyDifferent(builder2.getStyle());
    }

    public static boolean areRemoteViewsChanged(Builder builder, Builder builder2) {
        return !Objects.equals(Boolean.valueOf(builder.usesStandardHeader()), Boolean.valueOf(builder2.usesStandardHeader())) || areRemoteViewsChanged(builder.mN.contentView, builder2.mN.contentView) || areRemoteViewsChanged(builder.mN.bigContentView, builder2.mN.bigContentView) || areRemoteViewsChanged(builder.mN.headsUpContentView, builder2.mN.headsUpContentView);
    }

    private static boolean areRemoteViewsChanged(RemoteViews remoteViews, RemoteViews remoteViews2) {
        if (remoteViews == null && remoteViews2 == null) {
            return false;
        }
        return (remoteViews == null && remoteViews2 != null) || !((remoteViews == null || remoteViews2 != null) && Objects.equals(Integer.valueOf(remoteViews.getLayoutId()), Integer.valueOf(remoteViews2.getLayoutId())) && Objects.equals(Integer.valueOf(remoteViews.getSequenceNumber()), Integer.valueOf(remoteViews2.getSequenceNumber())));
    }

    private void fixDuplicateExtras() {
        if (this.extras != null) {
            fixDuplicateExtra(this.mLargeIcon, "android.largeIcon");
        }
    }

    private void fixDuplicateExtra(Parcelable parcelable, String str) {
        if (parcelable == null || this.extras.getParcelable(str) == null) {
            return;
        }
        this.extras.putParcelable(str, parcelable);
    }

    @Deprecated
    public void setLatestEventInfo(Context context, CharSequence charSequence, CharSequence charSequence2, PendingIntent pendingIntent) {
        if (context.getApplicationInfo().targetSdkVersion > 22) {
            Log.e(TAG, "setLatestEventInfo() is deprecated and you should feel deprecated.", new Throwable());
        }
        if (context.getApplicationInfo().targetSdkVersion < 24) {
            this.extras.putBoolean("android.showWhen", true);
        }
        Builder builder = new Builder(context, this);
        if (charSequence != null) {
            builder.setContentTitle(charSequence);
        }
        if (charSequence2 != null) {
            builder.setContentText(charSequence2);
        }
        builder.setContentIntent(pendingIntent);
        builder.build();
    }

    public void setAllowlistToken(IBinder iBinder) {
        this.mWhitelistToken = iBinder;
    }

    public static void addFieldsFromContext(Context context, Notification notification) {
        addFieldsFromContext(context.getApplicationInfo(), notification);
    }

    public static void addFieldsFromContext(ApplicationInfo applicationInfo, Notification notification) {
        notification.extras.putParcelable(EXTRA_BUILDER_APPLICATION_INFO, applicationInfo);
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1138166333441L, getChannelId());
        protoOutputStream.write(1133871366146L, this.tickerText != null);
        protoOutputStream.write(1120986464259L, this.flags);
        protoOutputStream.write(1120986464260L, this.color);
        protoOutputStream.write(1138166333445L, this.category);
        protoOutputStream.write(1138166333446L, this.mGroupKey);
        protoOutputStream.write(1138166333447L, this.mSortKey);
        Action[] actionArr = this.actions;
        if (actionArr != null) {
            protoOutputStream.write(1120986464264L, actionArr.length);
        }
        int i = this.visibility;
        if (i >= -1 && i <= 1) {
            protoOutputStream.write(1159641169929L, i);
        }
        Notification notification = this.publicVersion;
        if (notification != null) {
            notification.dumpDebug(protoOutputStream, 1146756268042L);
        }
        protoOutputStream.end(jStart);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Notification(channel=");
        sb.append(getChannelId());
        sb.append(" shortcut=");
        sb.append(getShortcutId());
        sb.append(" contentView=");
        RemoteViews remoteViews = this.contentView;
        if (remoteViews != null) {
            sb.append(remoteViews.getPackage());
            sb.append("/0x");
            sb.append(Integer.toHexString(this.contentView.getLayoutId()));
        } else {
            sb.append("null");
        }
        sb.append(" vibrate=");
        if ((this.defaults & 2) != 0) {
            sb.append("default");
        } else {
            long[] jArr = this.vibrate;
            if (jArr != null) {
                int length = jArr.length - 1;
                sb.append("[");
                for (int i = 0; i < length; i++) {
                    sb.append(this.vibrate[i]);
                    sb.append(',');
                }
                if (length != -1) {
                    sb.append(this.vibrate[length]);
                }
                sb.append("]");
            } else {
                sb.append("null");
            }
        }
        sb.append(" sound=");
        if ((this.defaults & 1) != 0) {
            sb.append("default");
        } else {
            Uri uri = this.sound;
            if (uri != null) {
                sb.append(uri.toString());
            } else {
                sb.append("null");
            }
        }
        if (this.tickerText != null) {
            sb.append(" tick");
        }
        sb.append(" defaults=0x");
        sb.append(Integer.toHexString(this.defaults));
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.flags));
        sb.append(String.format(" color=0x%08x", Integer.valueOf(this.color)));
        if (this.category != null) {
            sb.append(" category=");
            sb.append(this.category);
        }
        if (this.mGroupKey != null) {
            sb.append(" groupKey=");
            sb.append(this.mGroupKey);
        }
        if (this.mSortKey != null) {
            sb.append(" sortKey=");
            sb.append(this.mSortKey);
        }
        if (this.actions != null) {
            sb.append(" actions=");
            sb.append(this.actions.length);
        }
        sb.append(" vis=");
        sb.append(visibilityToString(this.visibility));
        if (this.publicVersion != null) {
            sb.append(" publicVersion=");
            sb.append(this.publicVersion.toString());
        }
        if (this.mLocusId != null) {
            sb.append(" locusId=");
            sb.append(this.mLocusId);
        }
        sb.append(")");
        return sb.toString();
    }

    public static String visibilityToString(int i) {
        return i != -1 ? i != 0 ? i != 1 ? "UNKNOWN(" + String.valueOf(i) + ")" : "PUBLIC" : "PRIVATE" : "SECRET";
    }

    public static String priorityToString(int i) {
        return i != -2 ? i != -1 ? i != 0 ? i != 1 ? i != 2 ? "UNKNOWN(" + String.valueOf(i) + ")" : "MAX" : "HIGH" : "DEFAULT" : "LOW" : "MIN";
    }

    public boolean hasCompletedProgress() {
        return this.extras.containsKey("android.progress") && this.extras.containsKey("android.progressMax") && this.extras.getInt("android.progressMax") != 0 && this.extras.getInt("android.progress") == this.extras.getInt("android.progressMax");
    }

    @Deprecated
    public String getChannel() {
        return this.mChannelId;
    }

    public String getChannelId() {
        return this.mChannelId;
    }

    @Deprecated
    public long getTimeout() {
        return this.mTimeout;
    }

    public long getTimeoutAfter() {
        return this.mTimeout;
    }

    public int getBadgeIconType() {
        return this.mBadgeIcon;
    }

    public String getShortcutId() {
        return this.mShortcutId;
    }

    public LocusId getLocusId() {
        return this.mLocusId;
    }

    public CharSequence getSettingsText() {
        return this.mSettingsText;
    }

    public int getGroupAlertBehavior() {
        return this.mGroupAlertBehavior;
    }

    public BubbleMetadata getBubbleMetadata() {
        return this.mBubbleMetadata;
    }

    public void setBubbleMetadata(BubbleMetadata bubbleMetadata) {
        this.mBubbleMetadata = bubbleMetadata;
    }

    public boolean getAllowSystemGeneratedContextualActions() {
        return this.mAllowSystemGeneratedContextualActions;
    }

    public Icon getSmallIcon() {
        return this.mSmallIcon;
    }

    public void setSmallIcon(Icon icon) {
        this.mSmallIcon = icon;
    }

    public Icon getLargeIcon() {
        return this.mLargeIcon;
    }

    public boolean isGroupSummary() {
        return (this.mGroupKey == null || (this.flags & 512) == 0) ? false : true;
    }

    public boolean isGroupChild() {
        return this.mGroupKey != null && (this.flags & 512) == 0;
    }

    public boolean suppressAlertingDueToGrouping() {
        if (isGroupSummary() && getGroupAlertBehavior() == 2) {
            return true;
        }
        return isGroupChild() && getGroupAlertBehavior() == 1;
    }

    public Pair<RemoteInput, Action> findRemoteInputActionPair(boolean z) {
        Action[] actionArr = this.actions;
        if (actionArr == null) {
            return null;
        }
        for (Action action : actionArr) {
            if (action.getRemoteInputs() != null) {
                RemoteInput remoteInput = null;
                for (RemoteInput remoteInput2 : action.getRemoteInputs()) {
                    if (remoteInput2.getAllowFreeFormInput() || !z) {
                        remoteInput = remoteInput2;
                    }
                }
                if (remoteInput != null) {
                    return Pair.create(remoteInput, action);
                }
            }
        }
        return null;
    }

    public List<Action> getContextualActions() {
        if (this.actions == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Action action : this.actions) {
            if (action.isContextual()) {
                arrayList.add(action);
            }
        }
        return arrayList;
    }

    public static class Builder {
        public static final String EXTRA_REBUILD_BIG_CONTENT_VIEW_ACTION_COUNT = "android.rebuild.bigViewActionCount";
        public static final String EXTRA_REBUILD_CONTENT_VIEW_ACTION_COUNT = "android.rebuild.contentViewActionCount";
        public static final String EXTRA_REBUILD_HEADS_UP_CONTENT_VIEW_ACTION_COUNT = "android.rebuild.hudViewActionCount";
        private static final int LIGHTNESS_TEXT_DIFFERENCE_DARK = -10;
        private static final int LIGHTNESS_TEXT_DIFFERENCE_LIGHT = 20;
        private static final boolean USE_ONLY_TITLE_IN_LOW_PRIORITY_SUMMARY = SystemProperties.getBoolean("notifications.only_title", true);
        private ArrayList<Action> mActions;
        private int mBackgroundColor;
        private int mCachedContrastColor;
        private int mCachedContrastColorIsFor;
        private ContrastColorUtil mColorUtil;
        private Context mContext;
        private int mForegroundColor;
        private boolean mInNightMode;
        private boolean mIsLegacy;
        private boolean mIsLegacyInitialized;
        private Notification mN;
        private int mNeutralColor;
        private ArrayList<Action> mOriginalActions;
        StandardTemplateParams mParams;
        private ArrayList<Person> mPersonList;
        private int mPrimaryTextColor;
        private boolean mRebuildStyledRemoteViews;
        private int mSecondaryTextColor;
        private Style mStyle;
        private int mTextColorsAreForBackground;
        private boolean mTintActionButtons;
        private Bundle mUserExtras;

        private int getActionLayoutResource() {
            return R.layout.notification_material_action;
        }

        private int getActionTombstoneLayoutResource() {
            return R.layout.notification_material_action_tombstone;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getBaseLayoutResource() {
            return R.layout.notification_template_material_base;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getBigBaseLayoutResource() {
            return R.layout.notification_template_material_big_base;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getBigPictureLayoutResource() {
            return R.layout.notification_template_material_big_picture;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getBigTextLayoutResource() {
            return R.layout.notification_template_material_big_text;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getConversationLayoutResource() {
            return R.layout.notification_template_material_conversation;
        }

        private int getEmphasizedActionLayoutResource() {
            return R.layout.notification_material_action_emphasized;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getInboxLayoutResource() {
            return R.layout.notification_template_material_inbox;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getMessagingLayoutResource() {
            return R.layout.notification_template_material_messaging;
        }

        public Builder(Context context, String str) {
            this(context, (Notification) null);
            this.mN.mChannelId = str;
        }

        @Deprecated
        public Builder(Context context) {
            this(context, (Notification) null);
        }

        public Builder(Context context, Notification notification) {
            this.mUserExtras = new Bundle();
            this.mActions = new ArrayList<>(3);
            this.mPersonList = new ArrayList<>();
            this.mCachedContrastColor = 1;
            this.mCachedContrastColorIsFor = 1;
            this.mNeutralColor = 1;
            this.mParams = new StandardTemplateParams();
            this.mTextColorsAreForBackground = 1;
            this.mPrimaryTextColor = 1;
            this.mSecondaryTextColor = 1;
            this.mBackgroundColor = 1;
            this.mForegroundColor = 1;
            this.mContext = context;
            Resources resources = context.getResources();
            this.mTintActionButtons = resources.getBoolean(R.bool.config_tintNotificationActionButtons);
            if (resources.getBoolean(R.bool.config_enableNightMode)) {
                this.mInNightMode = (resources.getConfiguration().uiMode & 48) == 32;
            }
            if (notification == null) {
                this.mN = new Notification();
                if (context.getApplicationInfo().targetSdkVersion < 24) {
                    this.mN.extras.putBoolean("android.showWhen", true);
                }
                this.mN.priority = 0;
                this.mN.visibility = 0;
                return;
            }
            this.mN = notification;
            if (notification.actions != null) {
                Collections.addAll(this.mActions, this.mN.actions);
            }
            if (this.mN.extras.containsKey(Notification.EXTRA_PEOPLE_LIST)) {
                this.mPersonList.addAll(this.mN.extras.getParcelableArrayList(Notification.EXTRA_PEOPLE_LIST));
            }
            if (this.mN.getSmallIcon() == null && this.mN.icon != 0) {
                setSmallIcon(this.mN.icon);
            }
            if (this.mN.getLargeIcon() == null && this.mN.largeIcon != null) {
                setLargeIcon(this.mN.largeIcon);
            }
            String string = this.mN.extras.getString("android.template");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            Class<? extends Style> notificationStyleClass = Notification.getNotificationStyleClass(string);
            if (notificationStyleClass == null) {
                Log.d(Notification.TAG, "Unknown style class: " + string);
                return;
            }
            try {
                Constructor<? extends Style> declaredConstructor = notificationStyleClass.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                Style styleNewInstance = declaredConstructor.newInstance(new Object[0]);
                styleNewInstance.restoreFromExtras(this.mN.extras);
                if (styleNewInstance != null) {
                    setStyle(styleNewInstance);
                }
            } catch (Throwable th) {
                Log.e(Notification.TAG, "Could not create Style", th);
            }
        }

        private ContrastColorUtil getColorUtil() {
            if (this.mColorUtil == null) {
                this.mColorUtil = ContrastColorUtil.getInstance(this.mContext);
            }
            return this.mColorUtil;
        }

        public Builder setShortcutId(String str) {
            this.mN.mShortcutId = str;
            return this;
        }

        public Builder setLocusId(LocusId locusId) {
            this.mN.mLocusId = locusId;
            return this;
        }

        public Builder setBadgeIconType(int i) {
            this.mN.mBadgeIcon = i;
            return this;
        }

        public Builder setGroupAlertBehavior(int i) {
            this.mN.mGroupAlertBehavior = i;
            return this;
        }

        public Builder setBubbleMetadata(BubbleMetadata bubbleMetadata) {
            this.mN.mBubbleMetadata = bubbleMetadata;
            return this;
        }

        @Deprecated
        public Builder setChannel(String str) {
            this.mN.mChannelId = str;
            return this;
        }

        public Builder setChannelId(String str) {
            this.mN.mChannelId = str;
            return this;
        }

        @Deprecated
        public Builder setTimeout(long j) {
            this.mN.mTimeout = j;
            return this;
        }

        public Builder setTimeoutAfter(long j) {
            this.mN.mTimeout = j;
            return this;
        }

        public Builder setWhen(long j) {
            this.mN.when = j;
            return this;
        }

        public Builder setShowWhen(boolean z) {
            this.mN.extras.putBoolean("android.showWhen", z);
            return this;
        }

        public Builder setUsesChronometer(boolean z) {
            this.mN.extras.putBoolean("android.showChronometer", z);
            return this;
        }

        public Builder setChronometerCountDown(boolean z) {
            this.mN.extras.putBoolean("android.chronometerCountDown", z);
            return this;
        }

        public Builder setSmallIcon(int i) {
            return setSmallIcon(i != 0 ? Icon.createWithResource(this.mContext, i) : null);
        }

        public Builder setSmallIcon(int i, int i2) {
            this.mN.iconLevel = i2;
            return setSmallIcon(i);
        }

        public Builder setSmallIcon(Icon icon) {
            this.mN.setSmallIcon(icon);
            if (icon != null && icon.getType() == 2) {
                this.mN.icon = icon.getResId();
            }
            return this;
        }

        public Builder setContentTitle(CharSequence charSequence) {
            this.mN.extras.putCharSequence("android.title", Notification.safeCharSequence(charSequence));
            return this;
        }

        public Builder setContentText(CharSequence charSequence) {
            this.mN.extras.putCharSequence("android.text", Notification.safeCharSequence(charSequence));
            return this;
        }

        public Builder setSubText(CharSequence charSequence) {
            this.mN.extras.putCharSequence("android.subText", Notification.safeCharSequence(charSequence));
            return this;
        }

        public Builder setSettingsText(CharSequence charSequence) {
            this.mN.mSettingsText = Notification.safeCharSequence(charSequence);
            return this;
        }

        public Builder setRemoteInputHistory(CharSequence[] charSequenceArr) {
            if (charSequenceArr == null) {
                this.mN.extras.putCharSequenceArray("android.remoteInputHistory", null);
            } else {
                int iMin = Math.min(5, charSequenceArr.length);
                CharSequence[] charSequenceArr2 = new CharSequence[iMin];
                RemoteInputHistoryItem[] remoteInputHistoryItemArr = new RemoteInputHistoryItem[iMin];
                for (int i = 0; i < iMin; i++) {
                    charSequenceArr2[i] = Notification.safeCharSequence(charSequenceArr[i]);
                    remoteInputHistoryItemArr[i] = new RemoteInputHistoryItem(charSequenceArr[i]);
                }
                this.mN.extras.putCharSequenceArray("android.remoteInputHistory", charSequenceArr2);
                this.mN.extras.putParcelableArray(Notification.EXTRA_REMOTE_INPUT_HISTORY_ITEMS, remoteInputHistoryItemArr);
            }
            return this;
        }

        public Builder setRemoteInputHistory(RemoteInputHistoryItem[] remoteInputHistoryItemArr) {
            if (remoteInputHistoryItemArr == null) {
                this.mN.extras.putParcelableArray(Notification.EXTRA_REMOTE_INPUT_HISTORY_ITEMS, null);
            } else {
                int iMin = Math.min(5, remoteInputHistoryItemArr.length);
                RemoteInputHistoryItem[] remoteInputHistoryItemArr2 = new RemoteInputHistoryItem[iMin];
                for (int i = 0; i < iMin; i++) {
                    remoteInputHistoryItemArr2[i] = remoteInputHistoryItemArr[i];
                }
                this.mN.extras.putParcelableArray(Notification.EXTRA_REMOTE_INPUT_HISTORY_ITEMS, remoteInputHistoryItemArr2);
            }
            return this;
        }

        public Builder setShowRemoteInputSpinner(boolean z) {
            this.mN.extras.putBoolean(Notification.EXTRA_SHOW_REMOTE_INPUT_SPINNER, z);
            return this;
        }

        public Builder setHideSmartReplies(boolean z) {
            this.mN.extras.putBoolean(Notification.EXTRA_HIDE_SMART_REPLIES, z);
            return this;
        }

        public Builder setNumber(int i) {
            this.mN.number = i;
            return this;
        }

        @Deprecated
        public Builder setContentInfo(CharSequence charSequence) {
            this.mN.extras.putCharSequence("android.infoText", Notification.safeCharSequence(charSequence));
            return this;
        }

        public Builder setProgress(int i, int i2, boolean z) {
            this.mN.extras.putInt("android.progress", i2);
            this.mN.extras.putInt("android.progressMax", i);
            this.mN.extras.putBoolean("android.progressIndeterminate", z);
            return this;
        }

        @Deprecated
        public Builder setContent(RemoteViews remoteViews) {
            return setCustomContentView(remoteViews);
        }

        public Builder setCustomContentView(RemoteViews remoteViews) {
            this.mN.contentView = remoteViews;
            return this;
        }

        public Builder setCustomBigContentView(RemoteViews remoteViews) {
            this.mN.bigContentView = remoteViews;
            return this;
        }

        public Builder setCustomHeadsUpContentView(RemoteViews remoteViews) {
            this.mN.headsUpContentView = remoteViews;
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingIntent) {
            this.mN.contentIntent = pendingIntent;
            return this;
        }

        public Builder setDeleteIntent(PendingIntent pendingIntent) {
            this.mN.deleteIntent = pendingIntent;
            return this;
        }

        public Builder setFullScreenIntent(PendingIntent pendingIntent, boolean z) {
            this.mN.fullScreenIntent = pendingIntent;
            setFlag(128, z);
            return this;
        }

        public Builder setTicker(CharSequence charSequence) {
            this.mN.tickerText = Notification.safeCharSequence(charSequence);
            return this;
        }

        @Deprecated
        public Builder setTicker(CharSequence charSequence, RemoteViews remoteViews) {
            setTicker(charSequence);
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap) {
            return setLargeIcon(bitmap != null ? Icon.createWithBitmap(bitmap) : null);
        }

        public Builder setLargeIcon(Icon icon) {
            this.mN.mLargeIcon = icon;
            this.mN.extras.putParcelable("android.largeIcon", icon);
            return this;
        }

        @Deprecated
        public Builder setSound(Uri uri) {
            this.mN.sound = uri;
            this.mN.audioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
            return this;
        }

        @Deprecated
        public Builder setSound(Uri uri, int i) {
            PlayerBase.deprecateStreamTypeForPlayback(i, Notification.TAG, "setSound()");
            this.mN.sound = uri;
            this.mN.audioStreamType = i;
            return this;
        }

        @Deprecated
        public Builder setSound(Uri uri, AudioAttributes audioAttributes) {
            this.mN.sound = uri;
            this.mN.audioAttributes = audioAttributes;
            return this;
        }

        @Deprecated
        public Builder setVibrate(long[] jArr) {
            this.mN.vibrate = jArr;
            return this;
        }

        @Deprecated
        public Builder setLights(int i, int i2, int i3) {
            this.mN.ledARGB = i;
            this.mN.ledOnMS = i2;
            this.mN.ledOffMS = i3;
            if (i2 != 0 || i3 != 0) {
                this.mN.flags |= 1;
            }
            return this;
        }

        public Builder setOngoing(boolean z) {
            setFlag(2, z);
            return this;
        }

        public Builder setColorized(boolean z) {
            this.mN.extras.putBoolean(Notification.EXTRA_COLORIZED, z);
            return this;
        }

        public Builder setOnlyAlertOnce(boolean z) {
            setFlag(8, z);
            return this;
        }

        public Builder setAutoCancel(boolean z) {
            setFlag(16, z);
            return this;
        }

        public Builder setLocalOnly(boolean z) {
            setFlag(256, z);
            return this;
        }

        @Deprecated
        public Builder setDefaults(int i) {
            this.mN.defaults = i;
            return this;
        }

        @Deprecated
        public Builder setPriority(int i) {
            this.mN.priority = i;
            return this;
        }

        public Builder setCategory(String str) {
            this.mN.category = str;
            return this;
        }

        public Builder addPerson(String str) {
            addPerson(new Person.Builder().setUri(str).build());
            return this;
        }

        public Builder addPerson(Person person) {
            this.mPersonList.add(person);
            return this;
        }

        public Builder setGroup(String str) {
            this.mN.mGroupKey = str;
            return this;
        }

        public Builder setGroupSummary(boolean z) {
            setFlag(512, z);
            return this;
        }

        public Builder setSortKey(String str) {
            this.mN.mSortKey = str;
            return this;
        }

        public Builder addExtras(Bundle bundle) {
            if (bundle != null) {
                this.mUserExtras.putAll(bundle);
            }
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            if (bundle != null) {
                this.mUserExtras = bundle;
            }
            return this;
        }

        public Bundle getExtras() {
            return this.mUserExtras;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Bundle getAllExtras() {
            Bundle bundle = (Bundle) this.mUserExtras.clone();
            bundle.putAll(this.mN.extras);
            return bundle;
        }

        @Deprecated
        public Builder addAction(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this.mActions.add(new Action(i, Notification.safeCharSequence(charSequence), pendingIntent));
            return this;
        }

        public Builder addAction(Action action) {
            if (action != null) {
                this.mActions.add(action);
            }
            return this;
        }

        public Builder setActions(Action... actionArr) {
            this.mActions.clear();
            for (int i = 0; i < actionArr.length; i++) {
                if (actionArr[i] != null) {
                    this.mActions.add(actionArr[i]);
                }
            }
            return this;
        }

        public Builder setStyle(Style style) {
            if (this.mStyle != style) {
                this.mStyle = style;
                if (style != null) {
                    style.setBuilder(this);
                    this.mN.extras.putString("android.template", style.getClass().getName());
                } else {
                    this.mN.extras.remove("android.template");
                }
            }
            return this;
        }

        public Style getStyle() {
            return this.mStyle;
        }

        public Builder setVisibility(int i) {
            this.mN.visibility = i;
            return this;
        }

        public Builder setPublicVersion(Notification notification) {
            if (notification != null) {
                this.mN.publicVersion = new Notification();
                notification.cloneInto(this.mN.publicVersion, true);
            } else {
                this.mN.publicVersion = null;
            }
            return this;
        }

        public Builder extend(Extender extender) {
            extender.extend(this);
            return this;
        }

        public Builder setFlag(int i, boolean z) {
            if (z) {
                Notification notification = this.mN;
                notification.flags = i | notification.flags;
            } else {
                Notification notification2 = this.mN;
                notification2.flags = (~i) & notification2.flags;
            }
            return this;
        }

        public Builder setColor(int i) {
            this.mN.color = i;
            sanitizeColor();
            return this;
        }

        private Drawable getProfileBadgeDrawable() {
            if (this.mContext.getUserId() == 0) {
                return null;
            }
            return this.mContext.getPackageManager().getUserBadgeForDensityNoBackground(new UserHandle(this.mContext.getUserId()), 0);
        }

        private Bitmap getProfileBadge() {
            Drawable profileBadgeDrawable = getProfileBadgeDrawable();
            if (profileBadgeDrawable == null) {
                return null;
            }
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.notification_badge_size);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            profileBadgeDrawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            profileBadgeDrawable.draw(canvas);
            return bitmapCreateBitmap;
        }

        private void bindProfileBadge(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            Bitmap profileBadge = getProfileBadge();
            if (profileBadge != null) {
                remoteViews.setImageViewBitmap(R.id.profile_badge, profileBadge);
                remoteViews.setViewVisibility(R.id.profile_badge, 0);
                if (isColorized(standardTemplateParams)) {
                    remoteViews.setDrawableTint(R.id.profile_badge, false, getPrimaryTextColor(standardTemplateParams), PorterDuff.Mode.SRC_ATOP);
                }
            }
        }

        private void bindAlertedIcon(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            remoteViews.setDrawableTint(R.id.alerted_icon, false, getNeutralColor(standardTemplateParams), PorterDuff.Mode.SRC_ATOP);
        }

        public boolean usesStandardHeader() {
            if (this.mN.mUsesStandardHeader) {
                return true;
            }
            if (this.mContext.getApplicationInfo().targetSdkVersion >= 24 && this.mN.contentView == null && this.mN.bigContentView == null) {
                return true;
            }
            return (this.mN.contentView == null || Notification.STANDARD_LAYOUTS.contains(Integer.valueOf(this.mN.contentView.getLayoutId()))) && (this.mN.bigContentView == null || Notification.STANDARD_LAYOUTS.contains(Integer.valueOf(this.mN.bigContentView.getLayoutId())));
        }

        private void resetStandardTemplate(RemoteViews remoteViews) {
            resetNotificationHeader(remoteViews);
            remoteViews.setViewVisibility(R.id.right_icon, 8);
            remoteViews.setViewVisibility(16908310, 8);
            remoteViews.setTextViewText(16908310, null);
            remoteViews.setViewVisibility(R.id.text, 8);
            remoteViews.setTextViewText(R.id.text, null);
            remoteViews.setViewVisibility(R.id.text_line_1, 8);
            remoteViews.setTextViewText(R.id.text_line_1, null);
        }

        private void resetNotificationHeader(RemoteViews remoteViews) {
            remoteViews.setBoolean(R.id.notification_header, "setExpanded", false);
            remoteViews.setTextViewText(R.id.app_name_text, null);
            remoteViews.setViewVisibility(R.id.chronometer, 8);
            remoteViews.setViewVisibility(R.id.header_text, 8);
            remoteViews.setTextViewText(R.id.header_text, null);
            remoteViews.setViewVisibility(R.id.header_text_secondary, 8);
            remoteViews.setTextViewText(R.id.header_text_secondary, null);
            remoteViews.setViewVisibility(R.id.header_text_divider, 8);
            remoteViews.setViewVisibility(R.id.header_text_secondary_divider, 8);
            remoteViews.setViewVisibility(R.id.time_divider, 8);
            remoteViews.setViewVisibility(R.id.time, 8);
            remoteViews.setImageViewIcon(R.id.profile_badge, null);
            remoteViews.setViewVisibility(R.id.profile_badge, 8);
            this.mN.mUsesStandardHeader = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public RemoteViews applyStandardTemplate(int i, TemplateBindResult templateBindResult) {
            return applyStandardTemplate(i, this.mParams.reset().fillTextsFrom(this), templateBindResult);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public RemoteViews applyStandardTemplate(int i, StandardTemplateParams standardTemplateParams, TemplateBindResult templateBindResult) {
            BuilderRemoteViews builderRemoteViews = new BuilderRemoteViews(this.mContext.getApplicationInfo(), i);
            resetStandardTemplate(builderRemoteViews);
            Bundle bundle = this.mN.extras;
            updateBackgroundColor(builderRemoteViews, standardTemplateParams);
            bindNotificationHeader(builderRemoteViews, standardTemplateParams);
            bindLargeIconAndReply(builderRemoteViews, standardTemplateParams, templateBindResult);
            boolean zHandleProgressBar = handleProgressBar(builderRemoteViews, bundle, standardTemplateParams);
            if (standardTemplateParams.title != null) {
                builderRemoteViews.setViewVisibility(16908310, 0);
                builderRemoteViews.setTextViewText(16908310, processTextSpans(standardTemplateParams.title));
                setTextViewColorPrimary(builderRemoteViews, 16908310, standardTemplateParams);
                builderRemoteViews.setViewLayoutWidth(16908310, zHandleProgressBar ? -2 : -1);
            }
            if (standardTemplateParams.text != null) {
                int i2 = zHandleProgressBar ? R.id.text_line_1 : R.id.text;
                builderRemoteViews.setTextViewText(i2, processTextSpans(standardTemplateParams.text));
                setTextViewColorSecondary(builderRemoteViews, i2, standardTemplateParams);
                builderRemoteViews.setViewVisibility(i2, 0);
            }
            setContentMinHeight(builderRemoteViews, zHandleProgressBar || this.mN.hasLargeIcon());
            return builderRemoteViews;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CharSequence processTextSpans(CharSequence charSequence) {
            return (hasForegroundColor() || this.mInNightMode) ? ContrastColorUtil.clearColorSpans(charSequence) : charSequence;
        }

        private void setTextViewColorPrimary(RemoteViews remoteViews, int i, StandardTemplateParams standardTemplateParams) {
            ensureColors(standardTemplateParams);
            remoteViews.setTextColor(i, this.mPrimaryTextColor);
        }

        private boolean hasForegroundColor() {
            return this.mForegroundColor != 1;
        }

        public int getPrimaryTextColor() {
            return getPrimaryTextColor(this.mParams);
        }

        public int getPrimaryTextColor(StandardTemplateParams standardTemplateParams) {
            ensureColors(standardTemplateParams);
            return this.mPrimaryTextColor;
        }

        public int getSecondaryTextColor() {
            return getSecondaryTextColor(this.mParams);
        }

        public int getSecondaryTextColor(StandardTemplateParams standardTemplateParams) {
            ensureColors(standardTemplateParams);
            return this.mSecondaryTextColor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTextViewColorSecondary(RemoteViews remoteViews, int i, StandardTemplateParams standardTemplateParams) {
            ensureColors(standardTemplateParams);
            remoteViews.setTextColor(i, this.mSecondaryTextColor);
        }

        private void ensureColors(StandardTemplateParams standardTemplateParams) {
            int backgroundColor = getBackgroundColor(standardTemplateParams);
            if (this.mPrimaryTextColor == 1 || this.mSecondaryTextColor == 1 || this.mTextColorsAreForBackground != backgroundColor) {
                this.mTextColorsAreForBackground = backgroundColor;
                if (!hasForegroundColor() || !isColorized(standardTemplateParams)) {
                    this.mPrimaryTextColor = ContrastColorUtil.resolvePrimaryColor(this.mContext, backgroundColor, this.mInNightMode);
                    this.mSecondaryTextColor = ContrastColorUtil.resolveSecondaryColor(this.mContext, backgroundColor, this.mInNightMode);
                    if (backgroundColor == 0 || !isColorized(standardTemplateParams)) {
                        return;
                    }
                    this.mPrimaryTextColor = ContrastColorUtil.findAlphaToMeetContrast(this.mPrimaryTextColor, backgroundColor, 4.5d);
                    this.mSecondaryTextColor = ContrastColorUtil.findAlphaToMeetContrast(this.mSecondaryTextColor, backgroundColor, 4.5d);
                    return;
                }
                double dCalculateLuminance = ContrastColorUtil.calculateLuminance(backgroundColor);
                double dCalculateLuminance2 = ContrastColorUtil.calculateLuminance(this.mForegroundColor);
                double dCalculateContrast = ContrastColorUtil.calculateContrast(this.mForegroundColor, backgroundColor);
                boolean z = (dCalculateLuminance > dCalculateLuminance2 && ContrastColorUtil.satisfiesTextContrast(backgroundColor, -16777216)) || (dCalculateLuminance <= dCalculateLuminance2 && !ContrastColorUtil.satisfiesTextContrast(backgroundColor, -1));
                if (dCalculateContrast < 4.5d) {
                    if (z) {
                        int iFindContrastColor = ContrastColorUtil.findContrastColor(this.mForegroundColor, backgroundColor, true, 4.5d);
                        this.mSecondaryTextColor = iFindContrastColor;
                        this.mPrimaryTextColor = ContrastColorUtil.changeColorLightness(iFindContrastColor, -20);
                        return;
                    } else {
                        int iFindContrastColorAgainstDark = ContrastColorUtil.findContrastColorAgainstDark(this.mForegroundColor, backgroundColor, true, 4.5d);
                        this.mSecondaryTextColor = iFindContrastColorAgainstDark;
                        this.mPrimaryTextColor = ContrastColorUtil.changeColorLightness(iFindContrastColorAgainstDark, 10);
                        return;
                    }
                }
                int i = this.mForegroundColor;
                this.mPrimaryTextColor = i;
                int iChangeColorLightness = ContrastColorUtil.changeColorLightness(i, z ? 20 : -10);
                this.mSecondaryTextColor = iChangeColorLightness;
                if (ContrastColorUtil.calculateContrast(iChangeColorLightness, backgroundColor) < 4.5d) {
                    if (z) {
                        this.mSecondaryTextColor = ContrastColorUtil.findContrastColor(this.mSecondaryTextColor, backgroundColor, true, 4.5d);
                    } else {
                        this.mSecondaryTextColor = ContrastColorUtil.findContrastColorAgainstDark(this.mSecondaryTextColor, backgroundColor, true, 4.5d);
                    }
                    this.mPrimaryTextColor = ContrastColorUtil.changeColorLightness(this.mSecondaryTextColor, z ? -20 : 10);
                }
            }
        }

        private void updateBackgroundColor(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            if (isColorized(standardTemplateParams)) {
                remoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", getBackgroundColor(standardTemplateParams));
            } else {
                remoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundResource", 0);
            }
        }

        void setContentMinHeight(RemoteViews remoteViews, boolean z) {
            remoteViews.setInt(R.id.notification_main_column, "setMinimumHeight", z ? this.mContext.getResources().getDimensionPixelSize(R.dimen.notification_min_content_height) : 0);
        }

        private boolean handleProgressBar(RemoteViews remoteViews, Bundle bundle, StandardTemplateParams standardTemplateParams) {
            int i = bundle.getInt("android.progressMax", 0);
            int i2 = bundle.getInt("android.progress", 0);
            boolean z = bundle.getBoolean("android.progressIndeterminate");
            if (standardTemplateParams.hasProgress && (i != 0 || z)) {
                remoteViews.setViewVisibility(16908301, 0);
                remoteViews.setProgressBar(16908301, i, i2, z);
                remoteViews.setProgressBackgroundTintList(16908301, ColorStateList.valueOf(this.mContext.getColor(R.color.notification_progress_background_color)));
                if (getRawColor(standardTemplateParams) == 0) {
                    return true;
                }
                ColorStateList colorStateListValueOf = ColorStateList.valueOf(isColorized(standardTemplateParams) ? getPrimaryTextColor(standardTemplateParams) : resolveContrastColor(standardTemplateParams));
                remoteViews.setProgressTintList(16908301, colorStateListValueOf);
                remoteViews.setProgressIndeterminateTintList(16908301, colorStateListValueOf);
                return true;
            }
            remoteViews.setViewVisibility(16908301, 8);
            return false;
        }

        private void bindLargeIconAndReply(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams, TemplateBindResult templateBindResult) {
            boolean zBindLargeIcon = bindLargeIcon(remoteViews, standardTemplateParams);
            boolean zBindReplyIcon = bindReplyIcon(remoteViews, standardTemplateParams);
            boolean z = zBindLargeIcon || zBindReplyIcon;
            remoteViews.setViewVisibility(R.id.right_icon_container, z ? 0 : 8);
            int iCalculateMarginEnd = calculateMarginEnd(zBindLargeIcon, zBindReplyIcon);
            remoteViews.setViewLayoutMarginEnd(R.id.line1, iCalculateMarginEnd);
            remoteViews.setViewLayoutMarginEnd(R.id.text, iCalculateMarginEnd);
            remoteViews.setViewLayoutMarginEnd(16908301, iCalculateMarginEnd);
            if (templateBindResult != null) {
                templateBindResult.setIconMarginEnd(iCalculateMarginEnd);
                templateBindResult.setRightIconContainerVisible(z);
            }
        }

        private int calculateMarginEnd(boolean z, boolean z2) {
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.notification_content_margin_end);
            int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.notification_right_icon_size);
            int dimensionPixelSize3 = z2 ? (dimensionPixelSize2 + 0) - (this.mContext.getResources().getDimensionPixelSize(R.dimen.notification_reply_inset) * 2) : 0;
            if (z) {
                dimensionPixelSize3 += dimensionPixelSize2;
                if (z2) {
                    dimensionPixelSize3 += dimensionPixelSize;
                }
            }
            return (z2 || z) ? dimensionPixelSize3 + dimensionPixelSize : dimensionPixelSize3;
        }

        private boolean bindLargeIcon(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            if (this.mN.mLargeIcon == null && this.mN.largeIcon != null) {
                Notification notification = this.mN;
                notification.mLargeIcon = Icon.createWithBitmap(notification.largeIcon);
            }
            boolean z = (this.mN.mLargeIcon == null || standardTemplateParams.hideLargeIcon) ? false : true;
            if (z) {
                remoteViews.setViewVisibility(R.id.right_icon, 0);
                remoteViews.setImageViewIcon(R.id.right_icon, this.mN.mLargeIcon);
                processLargeLegacyIcon(this.mN.mLargeIcon, remoteViews, standardTemplateParams);
            }
            return z;
        }

        private boolean bindReplyIcon(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            Action action;
            boolean z = !standardTemplateParams.hideReplyIcon;
            if (z) {
                Action actionFindReplyAction = findReplyAction();
                action = actionFindReplyAction;
                z = actionFindReplyAction != null;
            } else {
                action = null;
            }
            if (z) {
                remoteViews.setViewVisibility(R.id.reply_icon_action, 0);
                remoteViews.setDrawableTint(R.id.reply_icon_action, false, getNeutralColor(standardTemplateParams), PorterDuff.Mode.SRC_ATOP);
                remoteViews.setOnClickPendingIntent(R.id.reply_icon_action, action.actionIntent);
                remoteViews.setRemoteInputs(R.id.reply_icon_action, action.mRemoteInputs);
            } else {
                remoteViews.setRemoteInputs(R.id.reply_icon_action, null);
            }
            remoteViews.setViewVisibility(R.id.reply_icon_action, z ? 0 : 8);
            return z;
        }

        private Action findReplyAction() {
            ArrayList<Action> arrayList = this.mActions;
            ArrayList<Action> arrayList2 = this.mOriginalActions;
            if (arrayList2 != null) {
                arrayList = arrayList2;
            }
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Action action = arrayList.get(i);
                if (hasValidRemoteInput(action)) {
                    return action;
                }
            }
            return null;
        }

        private void bindNotificationHeader(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            bindSmallIcon(remoteViews, standardTemplateParams);
            bindHeaderAppName(remoteViews, standardTemplateParams);
            bindHeaderText(remoteViews, standardTemplateParams);
            bindHeaderTextSecondary(remoteViews, standardTemplateParams);
            bindHeaderChronometerAndTime(remoteViews, standardTemplateParams);
            bindProfileBadge(remoteViews, standardTemplateParams);
            bindAlertedIcon(remoteViews, standardTemplateParams);
            bindActivePermissions(remoteViews, standardTemplateParams);
            bindExpandButton(remoteViews, standardTemplateParams);
            this.mN.mUsesStandardHeader = true;
        }

        private void bindActivePermissions(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            int neutralColor = getNeutralColor(standardTemplateParams);
            remoteViews.setDrawableTint(R.id.camera, false, neutralColor, PorterDuff.Mode.SRC_ATOP);
            remoteViews.setDrawableTint(R.id.mic, false, neutralColor, PorterDuff.Mode.SRC_ATOP);
            remoteViews.setDrawableTint(R.id.overlay, false, neutralColor, PorterDuff.Mode.SRC_ATOP);
        }

        private void bindExpandButton(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            int primaryTextColor = isColorized(standardTemplateParams) ? getPrimaryTextColor(standardTemplateParams) : getSecondaryTextColor(standardTemplateParams);
            remoteViews.setDrawableTint(R.id.expand_button, false, primaryTextColor, PorterDuff.Mode.SRC_ATOP);
            remoteViews.setInt(R.id.expand_button, "setOriginalNotificationColor", primaryTextColor);
        }

        private void bindHeaderChronometerAndTime(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            if (showsTimeOrChronometer()) {
                remoteViews.setViewVisibility(R.id.time_divider, 0);
                setTextViewColorSecondary(remoteViews, R.id.time_divider, standardTemplateParams);
                if (this.mN.extras.getBoolean("android.showChronometer")) {
                    remoteViews.setViewVisibility(R.id.chronometer, 0);
                    remoteViews.setLong(R.id.chronometer, "setBase", this.mN.when + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
                    remoteViews.setBoolean(R.id.chronometer, "setStarted", true);
                    remoteViews.setChronometerCountDown(R.id.chronometer, this.mN.extras.getBoolean("android.chronometerCountDown"));
                    setTextViewColorSecondary(remoteViews, R.id.chronometer, standardTemplateParams);
                    return;
                }
                remoteViews.setViewVisibility(R.id.time, 0);
                remoteViews.setLong(R.id.time, "setTime", this.mN.when);
                setTextViewColorSecondary(remoteViews, R.id.time, standardTemplateParams);
                return;
            }
            long j = this.mN.when;
            Notification notification = this.mN;
            remoteViews.setLong(R.id.time, "setTime", j != 0 ? notification.when : notification.creationTime);
        }

        private void bindHeaderText(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            Style style;
            CharSequence charSequence = standardTemplateParams.summaryText;
            if (charSequence == null && (style = this.mStyle) != null && style.mSummaryTextSet && this.mStyle.hasSummaryInHeader()) {
                charSequence = this.mStyle.mSummaryText;
            }
            if (charSequence == null && this.mContext.getApplicationInfo().targetSdkVersion < 24 && this.mN.extras.getCharSequence("android.infoText") != null) {
                charSequence = this.mN.extras.getCharSequence("android.infoText");
            }
            if (charSequence != null) {
                remoteViews.setTextViewText(R.id.header_text, processTextSpans(processLegacyText(charSequence)));
                setTextViewColorSecondary(remoteViews, R.id.header_text, standardTemplateParams);
                remoteViews.setViewVisibility(R.id.header_text, 0);
                remoteViews.setViewVisibility(R.id.header_text_divider, 0);
                setTextViewColorSecondary(remoteViews, R.id.header_text_divider, standardTemplateParams);
            }
        }

        private void bindHeaderTextSecondary(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            if (TextUtils.isEmpty(standardTemplateParams.headerTextSecondary)) {
                return;
            }
            remoteViews.setTextViewText(R.id.header_text_secondary, processTextSpans(processLegacyText(standardTemplateParams.headerTextSecondary)));
            setTextViewColorSecondary(remoteViews, R.id.header_text_secondary, standardTemplateParams);
            remoteViews.setViewVisibility(R.id.header_text_secondary, 0);
            remoteViews.setViewVisibility(R.id.header_text_secondary_divider, 0);
            setTextViewColorSecondary(remoteViews, R.id.header_text_secondary_divider, standardTemplateParams);
        }

        public String loadHeaderAppName() {
            String str;
            PackageManager packageManager = this.mContext.getPackageManager();
            if (this.mN.extras.containsKey(Notification.EXTRA_SUBSTITUTE_APP_NAME)) {
                String packageName = this.mContext.getPackageName();
                String string = this.mN.extras.getString(Notification.EXTRA_SUBSTITUTE_APP_NAME);
                str = string;
                if (packageManager.checkPermission(Manifest.permission.SUBSTITUTE_NOTIFICATION_APP_NAME, packageName) != 0) {
                    Log.w(Notification.TAG, "warning: pkg " + packageName + " attempting to substitute app name '" + string + "' without holding perm " + Manifest.permission.SUBSTITUTE_NOTIFICATION_APP_NAME);
                    str = null;
                }
            } else {
                str = null;
            }
            boolean zIsEmpty = TextUtils.isEmpty(str);
            CharSequence applicationLabel = str;
            if (zIsEmpty) {
                applicationLabel = packageManager.getApplicationLabel(this.mContext.getApplicationInfo());
            }
            if (TextUtils.isEmpty(applicationLabel)) {
                return null;
            }
            return String.valueOf(applicationLabel);
        }

        private void bindHeaderAppName(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            remoteViews.setTextViewText(R.id.app_name_text, loadHeaderAppName());
            if (isColorized(standardTemplateParams)) {
                setTextViewColorPrimary(remoteViews, R.id.app_name_text, standardTemplateParams);
            } else {
                remoteViews.setTextColor(R.id.app_name_text, getSecondaryTextColor(standardTemplateParams));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isColorized(StandardTemplateParams standardTemplateParams) {
            return standardTemplateParams.allowColorization && this.mN.isColorized();
        }

        private void bindSmallIcon(RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            if (this.mN.mSmallIcon == null && this.mN.icon != 0) {
                Notification notification = this.mN;
                notification.mSmallIcon = Icon.createWithResource(this.mContext, notification.icon);
            }
            remoteViews.setImageViewIcon(16908294, this.mN.mSmallIcon);
            remoteViews.setInt(16908294, "setImageLevel", this.mN.iconLevel);
            processSmallIconColor(this.mN.mSmallIcon, remoteViews, standardTemplateParams);
        }

        private boolean showsTimeOrChronometer() {
            return this.mN.showsTime() || this.mN.showsChronometer();
        }

        private void resetStandardTemplateWithActions(RemoteViews remoteViews) {
            remoteViews.setViewVisibility(R.id.actions, 8);
            remoteViews.removeAllViews(R.id.actions);
            remoteViews.setViewVisibility(R.id.notification_material_reply_container, 8);
            remoteViews.setTextViewText(R.id.notification_material_reply_text_1, null);
            remoteViews.setViewVisibility(R.id.notification_material_reply_text_1_container, 8);
            remoteViews.setViewVisibility(R.id.notification_material_reply_progress, 8);
            remoteViews.setViewVisibility(R.id.notification_material_reply_text_2, 8);
            remoteViews.setTextViewText(R.id.notification_material_reply_text_2, null);
            remoteViews.setViewVisibility(R.id.notification_material_reply_text_3, 8);
            remoteViews.setTextViewText(R.id.notification_material_reply_text_3, null);
            remoteViews.setViewLayoutMarginBottomDimen(R.id.notification_action_list_margin_target, R.dimen.notification_content_margin);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public RemoteViews applyStandardTemplateWithActions(int i, TemplateBindResult templateBindResult) {
            return applyStandardTemplateWithActions(i, this.mParams.reset().fillTextsFrom(this), templateBindResult);
        }

        private static List<Action> filterOutContextualActions(List<Action> list) {
            ArrayList arrayList = new ArrayList();
            for (Action action : list) {
                if (!action.isContextual()) {
                    arrayList.add(action);
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public RemoteViews applyStandardTemplateWithActions(int i, StandardTemplateParams standardTemplateParams, TemplateBindResult templateBindResult) {
            boolean z;
            RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(i, standardTemplateParams, templateBindResult);
            resetStandardTemplateWithActions(remoteViewsApplyStandardTemplate);
            List<Action> listFilterOutContextualActions = filterOutContextualActions(this.mActions);
            int size = listFilterOutContextualActions.size();
            boolean z2 = this.mN.fullScreenIntent != null;
            remoteViewsApplyStandardTemplate.setBoolean(R.id.actions, "setEmphasizedMode", z2);
            if (size > 0) {
                remoteViewsApplyStandardTemplate.setViewVisibility(R.id.actions_container, 0);
                remoteViewsApplyStandardTemplate.setViewVisibility(R.id.actions, 0);
                remoteViewsApplyStandardTemplate.setViewLayoutMarginBottomDimen(R.id.notification_action_list_margin_target, 0);
                if (size > 3) {
                    size = 3;
                }
                z = false;
                for (int i2 = 0; i2 < size; i2++) {
                    Action action = listFilterOutContextualActions.get(i2);
                    boolean zHasValidRemoteInput = hasValidRemoteInput(action);
                    z |= zHasValidRemoteInput;
                    RemoteViews remoteViewsGenerateActionButton = generateActionButton(action, z2, standardTemplateParams);
                    if (zHasValidRemoteInput && !z2) {
                        remoteViewsGenerateActionButton.setInt(R.id.action0, "setBackgroundResource", 0);
                    }
                    remoteViewsApplyStandardTemplate.addView(R.id.actions, remoteViewsGenerateActionButton);
                }
            } else {
                remoteViewsApplyStandardTemplate.setViewVisibility(R.id.actions_container, 8);
                z = false;
            }
            RemoteInputHistoryItem[] remoteInputHistoryItemArr = (RemoteInputHistoryItem[]) Notification.getParcelableArrayFromBundle(this.mN.extras, Notification.EXTRA_REMOTE_INPUT_HISTORY_ITEMS, RemoteInputHistoryItem.class);
            if (z && remoteInputHistoryItemArr != null && remoteInputHistoryItemArr.length > 0 && !TextUtils.isEmpty(remoteInputHistoryItemArr[0].getText()) && standardTemplateParams.maxRemoteInputHistory > 0) {
                boolean z3 = this.mN.extras.getBoolean(Notification.EXTRA_SHOW_REMOTE_INPUT_SPINNER);
                remoteViewsApplyStandardTemplate.setViewVisibility(R.id.notification_material_reply_container, 0);
                remoteViewsApplyStandardTemplate.setViewVisibility(R.id.notification_material_reply_text_1_container, 0);
                remoteViewsApplyStandardTemplate.setTextViewText(R.id.notification_material_reply_text_1, processTextSpans(remoteInputHistoryItemArr[0].getText()));
                setTextViewColorSecondary(remoteViewsApplyStandardTemplate, R.id.notification_material_reply_text_1, standardTemplateParams);
                remoteViewsApplyStandardTemplate.setViewVisibility(R.id.notification_material_reply_progress, z3 ? 0 : 8);
                remoteViewsApplyStandardTemplate.setProgressIndeterminateTintList(R.id.notification_material_reply_progress, ColorStateList.valueOf(isColorized(standardTemplateParams) ? getPrimaryTextColor(standardTemplateParams) : resolveContrastColor(standardTemplateParams)));
                if (remoteInputHistoryItemArr.length > 1 && !TextUtils.isEmpty(remoteInputHistoryItemArr[1].getText()) && standardTemplateParams.maxRemoteInputHistory > 1) {
                    remoteViewsApplyStandardTemplate.setViewVisibility(R.id.notification_material_reply_text_2, 0);
                    remoteViewsApplyStandardTemplate.setTextViewText(R.id.notification_material_reply_text_2, processTextSpans(remoteInputHistoryItemArr[1].getText()));
                    setTextViewColorSecondary(remoteViewsApplyStandardTemplate, R.id.notification_material_reply_text_2, standardTemplateParams);
                    if (remoteInputHistoryItemArr.length > 2 && !TextUtils.isEmpty(remoteInputHistoryItemArr[2].getText()) && standardTemplateParams.maxRemoteInputHistory > 2) {
                        remoteViewsApplyStandardTemplate.setViewVisibility(R.id.notification_material_reply_text_3, 0);
                        remoteViewsApplyStandardTemplate.setTextViewText(R.id.notification_material_reply_text_3, processTextSpans(remoteInputHistoryItemArr[2].getText()));
                        setTextViewColorSecondary(remoteViewsApplyStandardTemplate, R.id.notification_material_reply_text_3, standardTemplateParams);
                    }
                }
            }
            return remoteViewsApplyStandardTemplate;
        }

        private boolean hasValidRemoteInput(Action action) {
            RemoteInput[] remoteInputs;
            if (TextUtils.isEmpty(action.title) || action.actionIntent == null || (remoteInputs = action.getRemoteInputs()) == null) {
                return false;
            }
            for (RemoteInput remoteInput : remoteInputs) {
                CharSequence[] choices = remoteInput.getChoices();
                if (remoteInput.getAllowFreeFormInput()) {
                    return true;
                }
                if (choices != null && choices.length != 0) {
                    return true;
                }
            }
            return false;
        }

        public RemoteViews createContentView() {
            return createContentView(false);
        }

        public RemoteViews createContentView(boolean z) {
            RemoteViews remoteViewsMakeContentView;
            if (this.mN.contentView != null && useExistingRemoteView()) {
                return this.mN.contentView;
            }
            Style style = this.mStyle;
            return (style == null || (remoteViewsMakeContentView = style.makeContentView(z)) == null) ? applyStandardTemplate(getBaseLayoutResource(), null) : remoteViewsMakeContentView;
        }

        private boolean useExistingRemoteView() {
            Style style = this.mStyle;
            return style == null || !(style.displayCustomViewInline() || this.mRebuildStyledRemoteViews);
        }

        public RemoteViews createBigContentView() {
            if (this.mN.bigContentView != null && useExistingRemoteView()) {
                return this.mN.bigContentView;
            }
            Style style = this.mStyle;
            RemoteViews remoteViewsApplyStandardTemplateWithActions = null;
            if (style != null) {
                remoteViewsApplyStandardTemplateWithActions = style.makeBigContentView();
                hideLine1Text(remoteViewsApplyStandardTemplateWithActions);
            } else if (this.mActions.size() != 0) {
                remoteViewsApplyStandardTemplateWithActions = applyStandardTemplateWithActions(getBigBaseLayoutResource(), null);
            }
            makeHeaderExpanded(remoteViewsApplyStandardTemplateWithActions);
            return remoteViewsApplyStandardTemplateWithActions;
        }

        public RemoteViews makeNotificationHeader() {
            return makeNotificationHeader(this.mParams.reset().fillTextsFrom(this));
        }

        private RemoteViews makeNotificationHeader(StandardTemplateParams standardTemplateParams) {
            standardTemplateParams.disallowColorization();
            BuilderRemoteViews builderRemoteViews = new BuilderRemoteViews(this.mContext.getApplicationInfo(), R.layout.notification_template_header);
            resetNotificationHeader(builderRemoteViews);
            bindNotificationHeader(builderRemoteViews, standardTemplateParams);
            return builderRemoteViews;
        }

        public RemoteViews makeAmbientNotification() {
            RemoteViews remoteViewsCreateHeadsUpContentView = createHeadsUpContentView(false);
            return remoteViewsCreateHeadsUpContentView != null ? remoteViewsCreateHeadsUpContentView : createContentView();
        }

        private void hideLine1Text(RemoteViews remoteViews) {
            if (remoteViews != null) {
                remoteViews.setViewVisibility(R.id.text_line_1, 8);
            }
        }

        public static void makeHeaderExpanded(RemoteViews remoteViews) {
            if (remoteViews != null) {
                remoteViews.setBoolean(R.id.notification_header, "setExpanded", true);
            }
        }

        public RemoteViews createHeadsUpContentView(boolean z) {
            if (this.mN.headsUpContentView != null && useExistingRemoteView()) {
                return this.mN.headsUpContentView;
            }
            Style style = this.mStyle;
            if (style != null) {
                RemoteViews remoteViewsMakeHeadsUpContentView = style.makeHeadsUpContentView(z);
                if (remoteViewsMakeHeadsUpContentView != null) {
                    return remoteViewsMakeHeadsUpContentView;
                }
            } else if (this.mActions.size() == 0) {
                return null;
            }
            return applyStandardTemplateWithActions(getBigBaseLayoutResource(), this.mParams.reset().fillTextsFrom(this).setMaxRemoteInputHistory(1), null);
        }

        public RemoteViews createHeadsUpContentView() {
            return createHeadsUpContentView(false);
        }

        public RemoteViews makePublicContentView(boolean z) {
            if (this.mN.publicVersion != null) {
                return recoverBuilder(this.mContext, this.mN.publicVersion).createContentView();
            }
            Bundle bundle = this.mN.extras;
            Style style = this.mStyle;
            this.mStyle = null;
            Icon icon = this.mN.mLargeIcon;
            this.mN.mLargeIcon = null;
            Bitmap bitmap = this.mN.largeIcon;
            this.mN.largeIcon = null;
            ArrayList<Action> arrayList = this.mActions;
            this.mActions = new ArrayList<>();
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("android.showWhen", bundle.getBoolean("android.showWhen"));
            bundle2.putBoolean("android.showChronometer", bundle.getBoolean("android.showChronometer"));
            bundle2.putBoolean("android.chronometerCountDown", bundle.getBoolean("android.chronometerCountDown"));
            String string = bundle.getString(Notification.EXTRA_SUBSTITUTE_APP_NAME);
            if (string != null) {
                bundle2.putString(Notification.EXTRA_SUBSTITUTE_APP_NAME, string);
            }
            this.mN.extras = bundle2;
            StandardTemplateParams standardTemplateParamsFillTextsFrom = this.mParams.reset().fillTextsFrom(this);
            if (z) {
                standardTemplateParamsFillTextsFrom.forceDefaultColor();
            }
            RemoteViews remoteViewsMakeNotificationHeader = makeNotificationHeader(standardTemplateParamsFillTextsFrom);
            remoteViewsMakeNotificationHeader.setBoolean(R.id.notification_header, "setExpandOnlyOnButton", true);
            this.mN.extras = bundle;
            this.mN.mLargeIcon = icon;
            this.mN.largeIcon = bitmap;
            this.mActions = arrayList;
            this.mStyle = style;
            return remoteViewsMakeNotificationHeader;
        }

        public RemoteViews makeLowPriorityContentView(boolean z) {
            StandardTemplateParams standardTemplateParamsFillTextsFrom = this.mParams.reset().forceDefaultColor().fillTextsFrom(this);
            if (!z || TextUtils.isEmpty(this.mParams.summaryText)) {
                standardTemplateParamsFillTextsFrom.summaryText(createSummaryText());
            }
            RemoteViews remoteViewsMakeNotificationHeader = makeNotificationHeader(standardTemplateParamsFillTextsFrom);
            remoteViewsMakeNotificationHeader.setBoolean(R.id.notification_header, "setAcceptAllTouches", true);
            return remoteViewsMakeNotificationHeader;
        }

        private CharSequence createSummaryText() {
            CharSequence charSequence = this.mN.extras.getCharSequence("android.title");
            if (USE_ONLY_TITLE_IN_LOW_PRIORITY_SUMMARY) {
                return charSequence;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (charSequence == null) {
                charSequence = this.mN.extras.getCharSequence("android.title.big");
            }
            BidiFormatter bidiFormatter = BidiFormatter.getInstance();
            if (charSequence != null) {
                spannableStringBuilder.append(bidiFormatter.unicodeWrap(charSequence));
            }
            CharSequence charSequence2 = this.mN.extras.getCharSequence("android.text");
            if (charSequence != null && charSequence2 != null) {
                spannableStringBuilder.append(bidiFormatter.unicodeWrap(this.mContext.getText(R.string.notification_header_divider_symbol_with_spaces)));
            }
            if (charSequence2 != null) {
                spannableStringBuilder.append(bidiFormatter.unicodeWrap(charSequence2));
            }
            return spannableStringBuilder;
        }

        private RemoteViews generateActionButton(Action action, boolean z, StandardTemplateParams standardTemplateParams) {
            int actionLayoutResource;
            CharSequence charSequenceEnsureColorSpanContrast;
            int primaryTextColor;
            boolean z2 = action.actionIntent == null;
            ApplicationInfo applicationInfo = this.mContext.getApplicationInfo();
            if (z) {
                actionLayoutResource = getEmphasizedActionLayoutResource();
            } else if (z2) {
                actionLayoutResource = getActionTombstoneLayoutResource();
            } else {
                actionLayoutResource = getActionLayoutResource();
            }
            BuilderRemoteViews builderRemoteViews = new BuilderRemoteViews(applicationInfo, actionLayoutResource);
            if (!z2) {
                builderRemoteViews.setOnClickPendingIntent(R.id.action0, action.actionIntent);
            }
            builderRemoteViews.setContentDescription(R.id.action0, action.title);
            if (action.mRemoteInputs != null) {
                builderRemoteViews.setRemoteInputs(R.id.action0, action.mRemoteInputs);
            }
            if (z) {
                CharSequence charSequence = action.title;
                ColorStateList[] colorStateListArr = null;
                int iResolveBackgroundColor = resolveBackgroundColor(standardTemplateParams);
                if (isLegacy()) {
                    charSequenceEnsureColorSpanContrast = ContrastColorUtil.clearColorSpans(charSequence);
                } else {
                    colorStateListArr = new ColorStateList[1];
                    charSequenceEnsureColorSpanContrast = ensureColorSpanContrast(charSequence, iResolveBackgroundColor, colorStateListArr);
                }
                builderRemoteViews.setTextViewText(R.id.action0, processTextSpans(charSequenceEnsureColorSpanContrast));
                setTextViewColorPrimary(builderRemoteViews, R.id.action0, standardTemplateParams);
                boolean z3 = (colorStateListArr == null || colorStateListArr[0] == null) ? false : true;
                if (z3) {
                    iResolveBackgroundColor = colorStateListArr[0].getDefaultColor();
                    primaryTextColor = ContrastColorUtil.resolvePrimaryColor(this.mContext, iResolveBackgroundColor, this.mInNightMode);
                    builderRemoteViews.setTextColor(R.id.action0, primaryTextColor);
                } else if (getRawColor(standardTemplateParams) != 0 && !isColorized(standardTemplateParams) && this.mTintActionButtons && !this.mInNightMode) {
                    primaryTextColor = resolveContrastColor(standardTemplateParams);
                    builderRemoteViews.setTextColor(R.id.action0, primaryTextColor);
                } else {
                    primaryTextColor = getPrimaryTextColor(standardTemplateParams);
                }
                builderRemoteViews.setColorStateList(R.id.action0, "setRippleColor", ColorStateList.valueOf((primaryTextColor & 16777215) | 855638016));
                builderRemoteViews.setColorStateList(R.id.action0, "setButtonBackground", ColorStateList.valueOf(iResolveBackgroundColor));
                builderRemoteViews.setBoolean(R.id.action0, "setHasStroke", !z3);
            } else {
                builderRemoteViews.setTextViewText(R.id.action0, processTextSpans(processLegacyText(action.title)));
                if (isColorized(standardTemplateParams)) {
                    setTextViewColorPrimary(builderRemoteViews, R.id.action0, standardTemplateParams);
                } else if (getRawColor(standardTemplateParams) != 0 && this.mTintActionButtons) {
                    builderRemoteViews.setTextColor(R.id.action0, resolveContrastColor(standardTemplateParams));
                }
            }
            builderRemoteViews.setIntTag(R.id.action0, R.id.notification_action_index_tag, this.mActions.indexOf(action));
            return builderRemoteViews;
        }

        private CharSequence ensureColorSpanContrast(CharSequence charSequence, int i, ColorStateList[] colorStateListArr) {
            Object[] objArr;
            int i2;
            ForegroundColorSpan foregroundColorSpan;
            ColorStateList colorStateList;
            if (!(charSequence instanceof Spanned)) {
                return charSequence;
            }
            Spanned spanned = (Spanned) charSequence;
            boolean z = false;
            Object[] spans = spanned.getSpans(0, spanned.length(), Object.class);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spanned.toString());
            int length = spans.length;
            int i3 = 0;
            while (i3 < length) {
                Object obj = spans[i3];
                int spanStart = spanned.getSpanStart(obj);
                int spanEnd = spanned.getSpanEnd(obj);
                boolean z2 = spanEnd - spanStart == charSequence.length() ? true : z;
                Object underlying = obj instanceof CharacterStyle ? ((CharacterStyle) obj).getUnderlying() : obj;
                if (underlying instanceof TextAppearanceSpan) {
                    TextAppearanceSpan textAppearanceSpan = (TextAppearanceSpan) underlying;
                    ColorStateList textColor = textAppearanceSpan.getTextColor();
                    if (textColor != null) {
                        int[] colors = textColor.getColors();
                        int length2 = colors.length;
                        int[] iArr = new int[length2];
                        objArr = spans;
                        int i4 = 0;
                        while (i4 < length2) {
                            iArr[i4] = ContrastColorUtil.ensureLargeTextContrast(colors[i4], i, this.mInNightMode);
                            i4++;
                            length = length;
                            colors = colors;
                        }
                        i2 = length;
                        ColorStateList colorStateList2 = new ColorStateList((int[][]) textColor.getStates().clone(), iArr);
                        if (z2) {
                            colorStateListArr[0] = colorStateList2;
                            colorStateList = null;
                        } else {
                            colorStateList = colorStateList2;
                        }
                        underlying = new TextAppearanceSpan(textAppearanceSpan.getFamily(), textAppearanceSpan.getTextStyle(), textAppearanceSpan.getTextSize(), colorStateList, textAppearanceSpan.getLinkTextColor());
                    } else {
                        objArr = spans;
                        i2 = length;
                    }
                    z = false;
                } else {
                    objArr = spans;
                    i2 = length;
                    if (underlying instanceof ForegroundColorSpan) {
                        int iEnsureLargeTextContrast = ContrastColorUtil.ensureLargeTextContrast(((ForegroundColorSpan) underlying).getForegroundColor(), i, this.mInNightMode);
                        if (z2) {
                            z = false;
                            colorStateListArr[0] = ColorStateList.valueOf(iEnsureLargeTextContrast);
                            foregroundColorSpan = null;
                        } else {
                            z = false;
                            foregroundColorSpan = new ForegroundColorSpan(iEnsureLargeTextContrast);
                        }
                        underlying = foregroundColorSpan;
                    } else {
                        z = false;
                        underlying = obj;
                    }
                }
                if (underlying != null) {
                    spannableStringBuilder.setSpan(underlying, spanStart, spanEnd, spanned.getSpanFlags(obj));
                }
                i3++;
                spans = objArr;
                length = i2;
            }
            return spannableStringBuilder;
        }

        private boolean isLegacy() {
            if (!this.mIsLegacyInitialized) {
                this.mIsLegacy = this.mContext.getApplicationInfo().targetSdkVersion < 21;
                this.mIsLegacyInitialized = true;
            }
            return this.mIsLegacy;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CharSequence processLegacyText(CharSequence charSequence) {
            return isLegacy() || textColorsNeedInversion() ? getColorUtil().invertCharSequenceColors(charSequence) : charSequence;
        }

        private void processSmallIconColor(Icon icon, RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            int iResolveContrastColor;
            boolean z = !isLegacy() || getColorUtil().isGrayscaleIcon(this.mContext, icon);
            if (isColorized(standardTemplateParams)) {
                iResolveContrastColor = getPrimaryTextColor(standardTemplateParams);
            } else {
                iResolveContrastColor = resolveContrastColor(standardTemplateParams);
            }
            if (z) {
                remoteViews.setDrawableTint(16908294, false, iResolveContrastColor, PorterDuff.Mode.SRC_ATOP);
            }
            remoteViews.setInt(16908294, "setOriginalIconColor", z ? iResolveContrastColor : 1);
        }

        private void processLargeLegacyIcon(Icon icon, RemoteViews remoteViews, StandardTemplateParams standardTemplateParams) {
            if (icon != null && isLegacy() && getColorUtil().isGrayscaleIcon(this.mContext, icon)) {
                remoteViews.setDrawableTint(16908294, false, resolveContrastColor(standardTemplateParams), PorterDuff.Mode.SRC_ATOP);
            }
        }

        private void sanitizeColor() {
            if (this.mN.color != 0) {
                this.mN.color |= -16777216;
            }
        }

        int resolveContrastColor(StandardTemplateParams standardTemplateParams) {
            int iResolveContrastColor;
            int i;
            int rawColor = getRawColor(standardTemplateParams);
            if (this.mCachedContrastColorIsFor == rawColor && (i = this.mCachedContrastColor) != 1) {
                return i;
            }
            int color = this.mContext.getColor(R.color.notification_material_background_color);
            if (rawColor == 0) {
                ensureColors(standardTemplateParams);
                iResolveContrastColor = ContrastColorUtil.resolveDefaultColor(this.mContext, color, this.mInNightMode);
            } else {
                iResolveContrastColor = ContrastColorUtil.resolveContrastColor(this.mContext, rawColor, color, this.mInNightMode);
            }
            if (Color.alpha(iResolveContrastColor) < 255) {
                iResolveContrastColor = ContrastColorUtil.compositeColors(iResolveContrastColor, color);
            }
            this.mCachedContrastColorIsFor = rawColor;
            this.mCachedContrastColor = iResolveContrastColor;
            return iResolveContrastColor;
        }

        private int getRawColor(StandardTemplateParams standardTemplateParams) {
            if (standardTemplateParams.forceDefaultColor) {
                return 0;
            }
            return this.mN.color;
        }

        int resolveNeutralColor() {
            int i = this.mNeutralColor;
            if (i != 1) {
                return i;
            }
            int color = this.mContext.getColor(R.color.notification_material_background_color);
            int iResolveDefaultColor = ContrastColorUtil.resolveDefaultColor(this.mContext, color, this.mInNightMode);
            this.mNeutralColor = iResolveDefaultColor;
            if (Color.alpha(iResolveDefaultColor) < 255) {
                this.mNeutralColor = ContrastColorUtil.compositeColors(this.mNeutralColor, color);
            }
            return this.mNeutralColor;
        }

        public Notification buildUnstyled() {
            if (this.mActions.size() > 0) {
                this.mN.actions = new Action[this.mActions.size()];
                this.mActions.toArray(this.mN.actions);
            }
            if (!this.mPersonList.isEmpty()) {
                this.mN.extras.putParcelableArrayList(Notification.EXTRA_PEOPLE_LIST, this.mPersonList);
            }
            if (this.mN.bigContentView != null || this.mN.contentView != null || this.mN.headsUpContentView != null) {
                this.mN.extras.putBoolean(Notification.EXTRA_CONTAINS_CUSTOM_VIEW, true);
            }
            return this.mN;
        }

        public static Builder recoverBuilder(Context context, Notification notification) {
            ApplicationInfo applicationInfo = (ApplicationInfo) notification.extras.getParcelable(Notification.EXTRA_BUILDER_APPLICATION_INFO);
            if (applicationInfo != null) {
                try {
                    context = context.createApplicationContext(applicationInfo, 4);
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.e(Notification.TAG, "ApplicationInfo " + applicationInfo + " not found");
                }
            }
            return new Builder(context, notification);
        }

        public Builder setAllowSystemGeneratedContextualActions(boolean z) {
            this.mN.mAllowSystemGeneratedContextualActions = z;
            return this;
        }

        @Deprecated
        public Notification getNotification() {
            return build();
        }

        public Notification build() {
            if (this.mN.mShortcutId != null && this.mN.mBubbleMetadata != null && this.mN.mBubbleMetadata.getShortcutId() != null && !this.mN.mShortcutId.equals(this.mN.mBubbleMetadata.getShortcutId())) {
                throw new IllegalArgumentException("Notification and BubbleMetadata shortcut id's don't match, notification: " + this.mN.mShortcutId + " vs bubble: " + this.mN.mBubbleMetadata.getShortcutId());
            }
            if (this.mUserExtras != null) {
                this.mN.extras = getAllExtras();
            }
            this.mN.creationTime = System.currentTimeMillis();
            Notification.addFieldsFromContext(this.mContext, this.mN);
            buildUnstyled();
            Style style = this.mStyle;
            if (style != null) {
                style.reduceImageSizes(this.mContext);
                this.mStyle.purgeResources();
                this.mStyle.validate(this.mContext);
                this.mStyle.buildStyled(this.mN);
            }
            this.mN.reduceImageSizes(this.mContext);
            if (this.mContext.getApplicationInfo().targetSdkVersion < 24 && useExistingRemoteView()) {
                if (this.mN.contentView == null) {
                    this.mN.contentView = createContentView();
                    this.mN.extras.putInt(EXTRA_REBUILD_CONTENT_VIEW_ACTION_COUNT, this.mN.contentView.getSequenceNumber());
                }
                if (this.mN.bigContentView == null) {
                    this.mN.bigContentView = createBigContentView();
                    if (this.mN.bigContentView != null) {
                        this.mN.extras.putInt(EXTRA_REBUILD_BIG_CONTENT_VIEW_ACTION_COUNT, this.mN.bigContentView.getSequenceNumber());
                    }
                }
                if (this.mN.headsUpContentView == null) {
                    this.mN.headsUpContentView = createHeadsUpContentView();
                    if (this.mN.headsUpContentView != null) {
                        this.mN.extras.putInt(EXTRA_REBUILD_HEADS_UP_CONTENT_VIEW_ACTION_COUNT, this.mN.headsUpContentView.getSequenceNumber());
                    }
                }
            }
            if ((this.mN.defaults & 4) != 0) {
                this.mN.flags |= 1;
            }
            this.mN.allPendingIntents = null;
            return this.mN;
        }

        public Notification buildInto(Notification notification) {
            build().cloneInto(notification, true);
            return notification;
        }

        public static Notification maybeCloneStrippedForDelivery(Notification notification) {
            String string = notification.extras.getString("android.template");
            if (!TextUtils.isEmpty(string) && Notification.getNotificationStyleClass(string) == null) {
                return notification;
            }
            boolean z = (notification.contentView instanceof BuilderRemoteViews) && notification.extras.getInt(EXTRA_REBUILD_CONTENT_VIEW_ACTION_COUNT, -1) == notification.contentView.getSequenceNumber();
            boolean z2 = (notification.bigContentView instanceof BuilderRemoteViews) && notification.extras.getInt(EXTRA_REBUILD_BIG_CONTENT_VIEW_ACTION_COUNT, -1) == notification.bigContentView.getSequenceNumber();
            boolean z3 = (notification.headsUpContentView instanceof BuilderRemoteViews) && notification.extras.getInt(EXTRA_REBUILD_HEADS_UP_CONTENT_VIEW_ACTION_COUNT, -1) == notification.headsUpContentView.getSequenceNumber();
            if (!z && !z2 && !z3) {
                return notification;
            }
            Notification notificationM10clone = notification.m10clone();
            if (z) {
                notificationM10clone.contentView = null;
                notificationM10clone.extras.remove(EXTRA_REBUILD_CONTENT_VIEW_ACTION_COUNT);
            }
            if (z2) {
                notificationM10clone.bigContentView = null;
                notificationM10clone.extras.remove(EXTRA_REBUILD_BIG_CONTENT_VIEW_ACTION_COUNT);
            }
            if (z3) {
                notificationM10clone.headsUpContentView = null;
                notificationM10clone.extras.remove(EXTRA_REBUILD_HEADS_UP_CONTENT_VIEW_ACTION_COUNT);
            }
            return notificationM10clone;
        }

        private int getBackgroundColor(StandardTemplateParams standardTemplateParams) {
            if (!isColorized(standardTemplateParams)) {
                return 0;
            }
            int i = this.mBackgroundColor;
            return i != 1 ? i : getRawColor(standardTemplateParams);
        }

        private int getNeutralColor(StandardTemplateParams standardTemplateParams) {
            if (isColorized(standardTemplateParams)) {
                return getSecondaryTextColor(standardTemplateParams);
            }
            return resolveNeutralColor();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int resolveBackgroundColor(StandardTemplateParams standardTemplateParams) {
            int backgroundColor = getBackgroundColor(standardTemplateParams);
            return backgroundColor == 0 ? this.mContext.getColor(R.color.notification_material_background_color) : backgroundColor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldTintActionButtons() {
            return this.mTintActionButtons;
        }

        private boolean textColorsNeedInversion() {
            int i;
            Style style = this.mStyle;
            return style != null && MediaStyle.class.equals(style.getClass()) && (i = this.mContext.getApplicationInfo().targetSdkVersion) > 23 && i < 26;
        }

        public void setColorPalette(int i, int i2) {
            this.mBackgroundColor = i;
            this.mForegroundColor = i2;
            this.mTextColorsAreForBackground = 1;
            ensureColors(this.mParams.reset().fillTextsFrom(this));
        }

        public void setRebuildStyledRemoteViews(boolean z) {
            this.mRebuildStyledRemoteViews = z;
        }

        public CharSequence getHeadsUpStatusBarText(boolean z) {
            Style style = this.mStyle;
            if (style != null && !z) {
                CharSequence headsUpStatusBarText = style.getHeadsUpStatusBarText();
                if (!TextUtils.isEmpty(headsUpStatusBarText)) {
                    return headsUpStatusBarText;
                }
            }
            return loadHeaderAppName();
        }

        public boolean usesTemplate() {
            Style style;
            return (this.mN.contentView == null && this.mN.headsUpContentView == null && this.mN.bigContentView == null) || ((style = this.mStyle) != null && style.displayCustomViewInline());
        }
    }

    void reduceImageSizes(Context context) {
        int i;
        if (this.extras.getBoolean(EXTRA_REDUCED_IMAGES)) {
            return;
        }
        boolean zIsLowRamDeviceStatic = ActivityManager.isLowRamDeviceStatic();
        if (this.mLargeIcon != null || this.largeIcon != null) {
            Resources resources = context.getResources();
            Class<? extends Style> notificationStyle = getNotificationStyle();
            int dimensionPixelSize = resources.getDimensionPixelSize(zIsLowRamDeviceStatic ? R.dimen.notification_right_icon_size_low_ram : R.dimen.notification_right_icon_size);
            if (MediaStyle.class.equals(notificationStyle) || DecoratedMediaCustomViewStyle.class.equals(notificationStyle)) {
                int dimensionPixelSize2 = resources.getDimensionPixelSize(zIsLowRamDeviceStatic ? R.dimen.notification_media_image_max_height_low_ram : R.dimen.notification_media_image_max_height);
                dimensionPixelSize = resources.getDimensionPixelSize(zIsLowRamDeviceStatic ? R.dimen.notification_media_image_max_width_low_ram : R.dimen.notification_media_image_max_width);
                i = dimensionPixelSize2;
            } else {
                i = dimensionPixelSize;
            }
            Icon icon = this.mLargeIcon;
            if (icon != null) {
                icon.scaleDownIfNecessary(dimensionPixelSize, i);
            }
            Bitmap bitmap = this.largeIcon;
            if (bitmap != null) {
                this.largeIcon = Icon.scaleDownIfNecessary(bitmap, dimensionPixelSize, i);
            }
        }
        reduceImageSizesForRemoteView(this.contentView, context, zIsLowRamDeviceStatic);
        reduceImageSizesForRemoteView(this.headsUpContentView, context, zIsLowRamDeviceStatic);
        reduceImageSizesForRemoteView(this.bigContentView, context, zIsLowRamDeviceStatic);
        this.extras.putBoolean(EXTRA_REDUCED_IMAGES, true);
    }

    private void reduceImageSizesForRemoteView(RemoteViews remoteViews, Context context, boolean z) {
        if (remoteViews != null) {
            Resources resources = context.getResources();
            remoteViews.reduceImageSizes(resources.getDimensionPixelSize(z ? R.dimen.notification_custom_view_max_image_width_low_ram : R.dimen.notification_custom_view_max_image_width), resources.getDimensionPixelSize(z ? R.dimen.notification_custom_view_max_image_height_low_ram : R.dimen.notification_custom_view_max_image_height));
        }
    }

    public boolean isForegroundService() {
        return (this.flags & 64) != 0;
    }

    public boolean hasMediaSession() {
        return this.extras.getParcelable("android.mediaSession") != null;
    }

    public Class<? extends Style> getNotificationStyle() {
        String string = this.extras.getString("android.template");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return getNotificationStyleClass(string);
    }

    public boolean isColorized() {
        if (isColorizedMedia()) {
            return true;
        }
        return this.extras.getBoolean(EXTRA_COLORIZED) && (hasColorizedPermission() || isForegroundService());
    }

    private boolean hasColorizedPermission() {
        return (this.flags & 2048) != 0;
    }

    public boolean isColorizedMedia() {
        Class<? extends Style> notificationStyle = getNotificationStyle();
        if (!MediaStyle.class.equals(notificationStyle)) {
            return DecoratedMediaCustomViewStyle.class.equals(notificationStyle) && this.extras.getBoolean(EXTRA_COLORIZED) && hasMediaSession();
        }
        Boolean bool = (Boolean) this.extras.get(EXTRA_COLORIZED);
        return (bool == null || bool.booleanValue()) && hasMediaSession();
    }

    public boolean isMediaNotification() {
        Class<? extends Style> notificationStyle = getNotificationStyle();
        return MediaStyle.class.equals(notificationStyle) || DecoratedMediaCustomViewStyle.class.equals(notificationStyle);
    }

    public boolean isBubbleNotification() {
        return (this.flags & 4096) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasLargeIcon() {
        return (this.mLargeIcon == null && this.largeIcon == null) ? false : true;
    }

    public boolean showsTime() {
        return this.when != 0 && this.extras.getBoolean("android.showWhen");
    }

    public boolean showsChronometer() {
        return this.when != 0 && this.extras.getBoolean("android.showChronometer");
    }

    @SystemApi
    public static Class<? extends Style> getNotificationStyleClass(String str) {
        Class<? extends Style>[] clsArr = {BigTextStyle.class, BigPictureStyle.class, InboxStyle.class, MediaStyle.class, DecoratedCustomViewStyle.class, DecoratedMediaCustomViewStyle.class, MessagingStyle.class};
        for (int i = 0; i < 7; i++) {
            Class<? extends Style> cls = clsArr[i];
            if (str.equals(cls.getName())) {
                return cls;
            }
        }
        return null;
    }

    public static abstract class Style {
        static final int MAX_REMOTE_INPUT_HISTORY_LINES = 3;
        private CharSequence mBigContentTitle;
        protected Builder mBuilder;
        protected CharSequence mSummaryText = null;
        protected boolean mSummaryTextSet = false;

        public abstract boolean areNotificationsVisiblyDifferent(Style style);

        public boolean displayCustomViewInline() {
            return false;
        }

        public CharSequence getHeadsUpStatusBarText() {
            return null;
        }

        protected boolean hasProgress() {
            return true;
        }

        public boolean hasSummaryInHeader() {
            return true;
        }

        public RemoteViews makeBigContentView() {
            return null;
        }

        public RemoteViews makeContentView(boolean z) {
            return null;
        }

        public RemoteViews makeHeadsUpContentView(boolean z) {
            return null;
        }

        public void purgeResources() {
        }

        public void reduceImageSizes(Context context) {
        }

        public void validate(Context context) {
        }

        protected void internalSetBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = charSequence;
        }

        protected void internalSetSummaryText(CharSequence charSequence) {
            this.mSummaryText = charSequence;
            this.mSummaryTextSet = true;
        }

        public void setBuilder(Builder builder) {
            if (this.mBuilder != builder) {
                this.mBuilder = builder;
                if (builder != null) {
                    builder.setStyle(this);
                }
            }
        }

        protected void checkBuilder() {
            if (this.mBuilder == null) {
                throw new IllegalArgumentException("Style requires a valid Builder object");
            }
        }

        protected RemoteViews getStandardView(int i) {
            return getStandardView(i, this.mBuilder.mParams.reset().fillTextsFrom(this.mBuilder), null);
        }

        protected RemoteViews getStandardView(int i, StandardTemplateParams standardTemplateParams, TemplateBindResult templateBindResult) {
            checkBuilder();
            CharSequence charSequence = this.mBigContentTitle;
            if (charSequence != null) {
                standardTemplateParams.title = charSequence;
            }
            RemoteViews remoteViewsApplyStandardTemplateWithActions = this.mBuilder.applyStandardTemplateWithActions(i, standardTemplateParams, templateBindResult);
            CharSequence charSequence2 = this.mBigContentTitle;
            if (charSequence2 != null && charSequence2.equals("")) {
                remoteViewsApplyStandardTemplateWithActions.setViewVisibility(R.id.line1, 8);
            } else {
                remoteViewsApplyStandardTemplateWithActions.setViewVisibility(R.id.line1, 0);
            }
            return remoteViewsApplyStandardTemplateWithActions;
        }

        public void addExtras(Bundle bundle) {
            if (this.mSummaryTextSet) {
                bundle.putCharSequence("android.summaryText", this.mSummaryText);
            }
            CharSequence charSequence = this.mBigContentTitle;
            if (charSequence != null) {
                bundle.putCharSequence("android.title.big", charSequence);
            }
            bundle.putString("android.template", getClass().getName());
        }

        protected void restoreFromExtras(Bundle bundle) {
            if (bundle.containsKey("android.summaryText")) {
                this.mSummaryText = bundle.getCharSequence("android.summaryText");
                this.mSummaryTextSet = true;
            }
            if (bundle.containsKey("android.title.big")) {
                this.mBigContentTitle = bundle.getCharSequence("android.title.big");
            }
        }

        public Notification buildStyled(Notification notification) {
            addExtras(notification.extras);
            return notification;
        }

        public Notification build() {
            checkBuilder();
            return this.mBuilder.build();
        }
    }

    public static class BigPictureStyle extends Style {
        public static final int MIN_ASHMEM_BITMAP_SIZE = 131072;
        private Icon mBigLargeIcon;
        private boolean mBigLargeIconSet = false;
        private Bitmap mPicture;

        @Override // android.app.Notification.Style
        public boolean hasSummaryInHeader() {
            return false;
        }

        public BigPictureStyle() {
        }

        @Deprecated
        public BigPictureStyle(Builder builder) {
            setBuilder(builder);
        }

        public BigPictureStyle setBigContentTitle(CharSequence charSequence) {
            internalSetBigContentTitle(Notification.safeCharSequence(charSequence));
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence charSequence) {
            internalSetSummaryText(Notification.safeCharSequence(charSequence));
            return this;
        }

        public Bitmap getBigPicture() {
            return this.mPicture;
        }

        public BigPictureStyle bigPicture(Bitmap bitmap) {
            this.mPicture = bitmap;
            return this;
        }

        public BigPictureStyle bigLargeIcon(Bitmap bitmap) {
            return bigLargeIcon(bitmap != null ? Icon.createWithBitmap(bitmap) : null);
        }

        public BigPictureStyle bigLargeIcon(Icon icon) {
            this.mBigLargeIconSet = true;
            this.mBigLargeIcon = icon;
            return this;
        }

        @Override // android.app.Notification.Style
        public void purgeResources() {
            super.purgeResources();
            Bitmap bitmap = this.mPicture;
            if (bitmap != null && bitmap.isMutable() && this.mPicture.getAllocationByteCount() >= 131072) {
                this.mPicture = this.mPicture.createAshmemBitmap();
            }
            Icon icon = this.mBigLargeIcon;
            if (icon != null) {
                icon.convertToAshmem();
            }
        }

        @Override // android.app.Notification.Style
        public void reduceImageSizes(Context context) {
            super.reduceImageSizes(context);
            Resources resources = context.getResources();
            boolean zIsLowRamDeviceStatic = ActivityManager.isLowRamDeviceStatic();
            if (this.mPicture != null) {
                this.mPicture = Icon.scaleDownIfNecessary(this.mPicture, resources.getDimensionPixelSize(zIsLowRamDeviceStatic ? R.dimen.notification_big_picture_max_height_low_ram : R.dimen.notification_big_picture_max_height), resources.getDimensionPixelSize(zIsLowRamDeviceStatic ? R.dimen.notification_big_picture_max_width_low_ram : R.dimen.notification_big_picture_max_width));
            }
            if (this.mBigLargeIcon != null) {
                int dimensionPixelSize = resources.getDimensionPixelSize(zIsLowRamDeviceStatic ? R.dimen.notification_right_icon_size_low_ram : R.dimen.notification_right_icon_size);
                this.mBigLargeIcon.scaleDownIfNecessary(dimensionPixelSize, dimensionPixelSize);
            }
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeBigContentView() {
            Icon icon;
            Bitmap bitmap;
            if (this.mBigLargeIconSet) {
                icon = this.mBuilder.mN.mLargeIcon;
                this.mBuilder.mN.mLargeIcon = this.mBigLargeIcon;
                bitmap = this.mBuilder.mN.largeIcon;
                this.mBuilder.mN.largeIcon = null;
            } else {
                icon = null;
                bitmap = null;
            }
            StandardTemplateParams standardTemplateParamsFillTextsFrom = this.mBuilder.mParams.reset().fillTextsFrom(this.mBuilder);
            RemoteViews standardView = getStandardView(this.mBuilder.getBigPictureLayoutResource(), standardTemplateParamsFillTextsFrom, null);
            if (this.mSummaryTextSet) {
                standardView.setTextViewText(R.id.text, this.mBuilder.processTextSpans(this.mBuilder.processLegacyText(this.mSummaryText)));
                this.mBuilder.setTextViewColorSecondary(standardView, R.id.text, standardTemplateParamsFillTextsFrom);
                standardView.setViewVisibility(R.id.text, 0);
            }
            this.mBuilder.setContentMinHeight(standardView, this.mBuilder.mN.hasLargeIcon());
            if (this.mBigLargeIconSet) {
                this.mBuilder.mN.mLargeIcon = icon;
                this.mBuilder.mN.largeIcon = bitmap;
            }
            standardView.setImageViewBitmap(R.id.big_picture, this.mPicture);
            return standardView;
        }

        @Override // android.app.Notification.Style
        public void addExtras(Bundle bundle) {
            super.addExtras(bundle);
            if (this.mBigLargeIconSet) {
                bundle.putParcelable("android.largeIcon.big", this.mBigLargeIcon);
            }
            bundle.putParcelable("android.picture", this.mPicture);
        }

        @Override // android.app.Notification.Style
        protected void restoreFromExtras(Bundle bundle) {
            super.restoreFromExtras(bundle);
            if (bundle.containsKey("android.largeIcon.big")) {
                this.mBigLargeIconSet = true;
                this.mBigLargeIcon = (Icon) bundle.getParcelable("android.largeIcon.big");
            }
            this.mPicture = (Bitmap) bundle.getParcelable("android.picture");
        }

        @Override // android.app.Notification.Style
        public boolean areNotificationsVisiblyDifferent(Style style) {
            if (style == null || getClass() != style.getClass()) {
                return true;
            }
            return areBitmapsObviouslyDifferent(getBigPicture(), ((BigPictureStyle) style).getBigPicture());
        }

        private static boolean areBitmapsObviouslyDifferent(Bitmap bitmap, Bitmap bitmap2) {
            if (bitmap == bitmap2) {
                return false;
            }
            if (bitmap == null || bitmap2 == null) {
                return true;
            }
            return (bitmap.getWidth() == bitmap2.getWidth() && bitmap.getHeight() == bitmap2.getHeight() && bitmap.getConfig() == bitmap2.getConfig() && bitmap.getGenerationId() == bitmap2.getGenerationId()) ? false : true;
        }
    }

    public static class BigTextStyle extends Style {
        private CharSequence mBigText;

        public BigTextStyle() {
        }

        @Deprecated
        public BigTextStyle(Builder builder) {
            setBuilder(builder);
        }

        public BigTextStyle setBigContentTitle(CharSequence charSequence) {
            internalSetBigContentTitle(Notification.safeCharSequence(charSequence));
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence charSequence) {
            internalSetSummaryText(Notification.safeCharSequence(charSequence));
            return this;
        }

        public BigTextStyle bigText(CharSequence charSequence) {
            this.mBigText = Notification.safeCharSequence(charSequence);
            return this;
        }

        public CharSequence getBigText() {
            return this.mBigText;
        }

        @Override // android.app.Notification.Style
        public void addExtras(Bundle bundle) {
            super.addExtras(bundle);
            bundle.putCharSequence("android.bigText", this.mBigText);
        }

        @Override // android.app.Notification.Style
        protected void restoreFromExtras(Bundle bundle) {
            super.restoreFromExtras(bundle);
            this.mBigText = bundle.getCharSequence("android.bigText");
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeContentView(boolean z) {
            if (z) {
                this.mBuilder.mOriginalActions = this.mBuilder.mActions;
                this.mBuilder.mActions = new ArrayList();
                RemoteViews remoteViewsMakeBigContentView = makeBigContentView();
                this.mBuilder.mActions = this.mBuilder.mOriginalActions;
                this.mBuilder.mOriginalActions = null;
                return remoteViewsMakeBigContentView;
            }
            return super.makeContentView(z);
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeHeadsUpContentView(boolean z) {
            if (z && this.mBuilder.mActions.size() > 0) {
                return makeBigContentView();
            }
            return super.makeHeadsUpContentView(z);
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeBigContentView() {
            StandardTemplateParams standardTemplateParamsText = this.mBuilder.mParams.reset().fillTextsFrom(this.mBuilder).text(null);
            TemplateBindResult templateBindResult = new TemplateBindResult();
            RemoteViews standardView = getStandardView(this.mBuilder.getBigTextLayoutResource(), standardTemplateParamsText, templateBindResult);
            standardView.setInt(R.id.big_text, "setImageEndMargin", templateBindResult.getIconMarginEnd());
            CharSequence charSequenceProcessLegacyText = this.mBuilder.processLegacyText(this.mBigText);
            if (TextUtils.isEmpty(charSequenceProcessLegacyText)) {
                charSequenceProcessLegacyText = this.mBuilder.processLegacyText(this.mBuilder.getAllExtras().getCharSequence("android.text"));
            }
            standardView.setTextViewText(R.id.big_text, this.mBuilder.processTextSpans(charSequenceProcessLegacyText));
            this.mBuilder.setTextViewColorSecondary(standardView, R.id.big_text, standardTemplateParamsText);
            standardView.setViewVisibility(R.id.big_text, TextUtils.isEmpty(charSequenceProcessLegacyText) ? 8 : 0);
            standardView.setBoolean(R.id.big_text, "setHasImage", templateBindResult.isRightIconContainerVisible());
            return standardView;
        }

        @Override // android.app.Notification.Style
        public boolean areNotificationsVisiblyDifferent(Style style) {
            if (style == null || getClass() != style.getClass()) {
                return true;
            }
            return !Objects.equals(String.valueOf(getBigText()), String.valueOf(((BigTextStyle) style).getBigText()));
        }
    }

    public static class MessagingStyle extends Style {
        public static final int CONVERSATION_TYPE_IMPORTANT = 2;
        public static final int CONVERSATION_TYPE_LEGACY = 0;
        public static final int CONVERSATION_TYPE_NORMAL = 1;
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        CharSequence mConversationTitle;
        int mConversationType;
        List<Message> mHistoricMessages;
        boolean mIsGroupConversation;
        List<Message> mMessages;
        Icon mShortcutIcon;
        int mUnreadMessageCount;
        Person mUser;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ConversationType {
        }

        MessagingStyle() {
            this.mMessages = new ArrayList();
            this.mHistoricMessages = new ArrayList();
            this.mConversationType = 0;
        }

        public MessagingStyle(CharSequence charSequence) {
            this(new Person.Builder().setName(charSequence).build());
        }

        public MessagingStyle(Person person) {
            this.mMessages = new ArrayList();
            this.mHistoricMessages = new ArrayList();
            this.mConversationType = 0;
            this.mUser = person;
        }

        @Override // android.app.Notification.Style
        public void validate(Context context) {
            super.validate(context);
            if (context.getApplicationInfo().targetSdkVersion >= 28) {
                Person person = this.mUser;
                if (person == null || person.getName() == null) {
                    throw new RuntimeException("User must be valid and have a name.");
                }
            }
        }

        @Override // android.app.Notification.Style
        public CharSequence getHeadsUpStatusBarText() {
            CharSequence charSequence;
            if (!TextUtils.isEmpty(((Style) this).mBigContentTitle)) {
                charSequence = ((Style) this).mBigContentTitle;
            } else {
                charSequence = this.mConversationTitle;
            }
            if (this.mConversationType != 0 || TextUtils.isEmpty(charSequence) || hasOnlyWhiteSpaceSenders()) {
                return null;
            }
            return charSequence;
        }

        public Person getUser() {
            return this.mUser;
        }

        public CharSequence getUserDisplayName() {
            return this.mUser.getName();
        }

        public MessagingStyle setConversationTitle(CharSequence charSequence) {
            this.mConversationTitle = charSequence;
            return this;
        }

        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }

        public MessagingStyle setShortcutIcon(Icon icon) {
            this.mShortcutIcon = icon;
            return this;
        }

        public Icon getShortcutIcon() {
            return this.mShortcutIcon;
        }

        public MessagingStyle setConversationType(int i) {
            this.mConversationType = i;
            return this;
        }

        public int getConversationType() {
            return this.mConversationType;
        }

        public int getUnreadMessageCount() {
            return this.mUnreadMessageCount;
        }

        public MessagingStyle setUnreadMessageCount(int i) {
            this.mUnreadMessageCount = i;
            return this;
        }

        public MessagingStyle addMessage(CharSequence charSequence, long j, CharSequence charSequence2) {
            return addMessage(charSequence, j, charSequence2 == null ? null : new Person.Builder().setName(charSequence2).build());
        }

        public MessagingStyle addMessage(CharSequence charSequence, long j, Person person) {
            return addMessage(new Message(charSequence, j, person));
        }

        public MessagingStyle addMessage(Message message) {
            this.mMessages.add(message);
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        public MessagingStyle addHistoricMessage(Message message) {
            this.mHistoricMessages.add(message);
            if (this.mHistoricMessages.size() > 25) {
                this.mHistoricMessages.remove(0);
            }
            return this;
        }

        public List<Message> getMessages() {
            return this.mMessages;
        }

        public List<Message> getHistoricMessages() {
            return this.mHistoricMessages;
        }

        public MessagingStyle setGroupConversation(boolean z) {
            this.mIsGroupConversation = z;
            return this;
        }

        public boolean isGroupConversation() {
            if (this.mBuilder == null || this.mBuilder.mContext.getApplicationInfo().targetSdkVersion >= 28) {
                return this.mIsGroupConversation;
            }
            return this.mConversationTitle != null;
        }

        @Override // android.app.Notification.Style
        public void addExtras(Bundle bundle) {
            super.addExtras(bundle);
            Person person = this.mUser;
            if (person != null) {
                bundle.putCharSequence("android.selfDisplayName", person.getName());
                bundle.putParcelable(Notification.EXTRA_MESSAGING_PERSON, this.mUser);
            }
            CharSequence charSequence = this.mConversationTitle;
            if (charSequence != null) {
                bundle.putCharSequence("android.conversationTitle", charSequence);
            }
            if (!this.mMessages.isEmpty()) {
                bundle.putParcelableArray("android.messages", Message.getBundleArrayForMessages(this.mMessages));
            }
            if (!this.mHistoricMessages.isEmpty()) {
                bundle.putParcelableArray(Notification.EXTRA_HISTORIC_MESSAGES, Message.getBundleArrayForMessages(this.mHistoricMessages));
            }
            Icon icon = this.mShortcutIcon;
            if (icon != null) {
                bundle.putParcelable(Notification.EXTRA_CONVERSATION_ICON, icon);
            }
            bundle.putInt(Notification.EXTRA_CONVERSATION_UNREAD_MESSAGE_COUNT, this.mUnreadMessageCount);
            fixTitleAndTextExtras(bundle);
            bundle.putBoolean("android.isGroupConversation", this.mIsGroupConversation);
        }

        private void fixTitleAndTextExtras(Bundle bundle) {
            Message messageFindLatestIncomingMessage = findLatestIncomingMessage();
            CharSequence string = null;
            CharSequence charSequence = messageFindLatestIncomingMessage == null ? null : messageFindLatestIncomingMessage.mText;
            if (messageFindLatestIncomingMessage != null) {
                string = ((messageFindLatestIncomingMessage.mSender == null || TextUtils.isEmpty(messageFindLatestIncomingMessage.mSender.getName())) ? this.mUser : messageFindLatestIncomingMessage.mSender).getName();
            }
            if (!TextUtils.isEmpty(this.mConversationTitle)) {
                if (!TextUtils.isEmpty(string)) {
                    BidiFormatter bidiFormatter = BidiFormatter.getInstance();
                    string = this.mBuilder.mContext.getString(R.string.notification_messaging_title_template, bidiFormatter.unicodeWrap(this.mConversationTitle), bidiFormatter.unicodeWrap(string));
                } else {
                    string = this.mConversationTitle;
                }
            }
            if (string != null) {
                bundle.putCharSequence("android.title", string);
            }
            if (charSequence != null) {
                bundle.putCharSequence("android.text", charSequence);
            }
        }

        @Override // android.app.Notification.Style
        protected void restoreFromExtras(Bundle bundle) {
            super.restoreFromExtras(bundle);
            Person person = (Person) bundle.getParcelable(Notification.EXTRA_MESSAGING_PERSON);
            this.mUser = person;
            if (person == null) {
                this.mUser = new Person.Builder().setName(bundle.getCharSequence("android.selfDisplayName")).build();
            }
            this.mConversationTitle = bundle.getCharSequence("android.conversationTitle");
            this.mMessages = Message.getMessagesFromBundleArray(bundle.getParcelableArray("android.messages"));
            this.mHistoricMessages = Message.getMessagesFromBundleArray(bundle.getParcelableArray(Notification.EXTRA_HISTORIC_MESSAGES));
            this.mIsGroupConversation = bundle.getBoolean("android.isGroupConversation");
            this.mUnreadMessageCount = bundle.getInt(Notification.EXTRA_CONVERSATION_UNREAD_MESSAGE_COUNT);
            this.mShortcutIcon = (Icon) bundle.getParcelable(Notification.EXTRA_CONVERSATION_ICON);
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeContentView(boolean z) {
            this.mBuilder.mOriginalActions = this.mBuilder.mActions;
            this.mBuilder.mActions = new ArrayList();
            RemoteViews remoteViewsMakeMessagingView = makeMessagingView(true, false);
            this.mBuilder.mActions = this.mBuilder.mOriginalActions;
            this.mBuilder.mOriginalActions = null;
            return remoteViewsMakeMessagingView;
        }

        @Override // android.app.Notification.Style
        public boolean areNotificationsVisiblyDifferent(Style style) {
            CharSequence name;
            CharSequence name2;
            if (style == null || getClass() != style.getClass()) {
                return true;
            }
            List<Message> messages = getMessages();
            List<Message> messages2 = ((MessagingStyle) style).getMessages();
            if (messages == null || messages2 == null) {
                messages2 = new ArrayList<>();
            }
            int size = messages.size();
            if (size != messages2.size()) {
                return true;
            }
            for (int i = 0; i < size; i++) {
                Message message = messages.get(i);
                Message message2 = messages2.get(i);
                if (!Objects.equals(String.valueOf(message.getText()), String.valueOf(message2.getText())) || !Objects.equals(message.getDataUri(), message2.getDataUri())) {
                    return true;
                }
                if (message.getSenderPerson() == null) {
                    name = message.getSender();
                } else {
                    name = message.getSenderPerson().getName();
                }
                String strValueOf = String.valueOf(name);
                if (message2.getSenderPerson() == null) {
                    name2 = message2.getSender();
                } else {
                    name2 = message2.getSenderPerson().getName();
                }
                if (!Objects.equals(strValueOf, String.valueOf(name2))) {
                    return true;
                }
                if (!Objects.equals(message.getSenderPerson() == null ? null : message.getSenderPerson().getKey(), message2.getSenderPerson() != null ? message2.getSenderPerson().getKey() : null)) {
                    return true;
                }
            }
            return false;
        }

        private Message findLatestIncomingMessage() {
            return findLatestIncomingMessage(this.mMessages);
        }

        public static Message findLatestIncomingMessage(List<Message> list) {
            for (int size = list.size() - 1; size >= 0; size--) {
                Message message = list.get(size);
                if (message.mSender != null && !TextUtils.isEmpty(message.mSender.getName())) {
                    return message;
                }
            }
            if (list.isEmpty()) {
                return null;
            }
            return list.get(list.size() - 1);
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeBigContentView() {
            return makeMessagingView(false, true);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0044  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0085  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x008c  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00a3  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00b5  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00bc  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0119  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private android.widget.RemoteViews makeMessagingView(boolean r12, boolean r13) {
            /*
                Method dump skipped, instruction units count: 316
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.app.Notification.MessagingStyle.makeMessagingView(boolean, boolean):android.widget.RemoteViews");
        }

        private boolean hasOnlyWhiteSpaceSenders() {
            for (int i = 0; i < this.mMessages.size(); i++) {
                Person senderPerson = this.mMessages.get(i).getSenderPerson();
                if (senderPerson != null && !isWhiteSpace(senderPerson.getName())) {
                    return false;
                }
            }
            return true;
        }

        private boolean isWhiteSpace(CharSequence charSequence) {
            if (TextUtils.isEmpty(charSequence) || charSequence.toString().matches("^\\s*$")) {
                return true;
            }
            for (int i = 0; i < charSequence.length(); i++) {
                if (charSequence.charAt(i) != 8203) {
                    return false;
                }
            }
            return true;
        }

        private CharSequence createConversationTitleFromMessages() {
            ArraySet arraySet = new ArraySet();
            for (int i = 0; i < this.mMessages.size(); i++) {
                Person senderPerson = this.mMessages.get(i).getSenderPerson();
                if (senderPerson != null) {
                    arraySet.add(senderPerson.getName());
                }
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            int size = arraySet.size();
            for (int i2 = 0; i2 < size; i2++) {
                CharSequence charSequence = (CharSequence) arraySet.valueAt(i2);
                if (!TextUtils.isEmpty(spannableStringBuilder)) {
                    spannableStringBuilder.append((CharSequence) ", ");
                }
                spannableStringBuilder.append(BidiFormatter.getInstance().unicodeWrap(charSequence));
            }
            return spannableStringBuilder;
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeHeadsUpContentView(boolean z) {
            RemoteViews remoteViewsMakeMessagingView = makeMessagingView(true, true);
            if (this.mConversationType == 0) {
                remoteViewsMakeMessagingView.setInt(R.id.notification_messaging, "setMaxDisplayedLines", 1);
            }
            return remoteViewsMakeMessagingView;
        }

        private static TextAppearanceSpan makeFontColorSpan(int i) {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i), null);
        }

        public static final class Message {
            static final String KEY_DATA_MIME_TYPE = "type";
            static final String KEY_DATA_URI = "uri";
            static final String KEY_EXTRAS_BUNDLE = "extras";
            static final String KEY_REMOTE_INPUT_HISTORY = "remote_input_history";
            static final String KEY_SENDER = "sender";
            static final String KEY_SENDER_PERSON = "sender_person";
            public static final String KEY_TEXT = "text";
            static final String KEY_TIMESTAMP = "time";
            private String mDataMimeType;
            private Uri mDataUri;
            private Bundle mExtras;
            private final boolean mRemoteInputHistory;
            private final Person mSender;
            private final CharSequence mText;
            private final long mTimestamp;

            public Message(CharSequence charSequence, long j, CharSequence charSequence2) {
                this(charSequence, j, charSequence2 == null ? null : new Person.Builder().setName(charSequence2).build());
            }

            public Message(CharSequence charSequence, long j, Person person) {
                this(charSequence, j, person, false);
            }

            public Message(CharSequence charSequence, long j, Person person, boolean z) {
                this.mExtras = new Bundle();
                this.mText = Notification.safeCharSequence(charSequence);
                this.mTimestamp = j;
                this.mSender = person;
                this.mRemoteInputHistory = z;
            }

            public Message setData(String str, Uri uri) {
                this.mDataMimeType = str;
                this.mDataUri = uri;
                return this;
            }

            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public CharSequence getSender() {
                Person person = this.mSender;
                if (person == null) {
                    return null;
                }
                return person.getName();
            }

            public Person getSenderPerson() {
                return this.mSender;
            }

            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            public Uri getDataUri() {
                return this.mDataUri;
            }

            public boolean isRemoteInputHistory() {
                return this.mRemoteInputHistory;
            }

            public Bundle toBundle() {
                Bundle bundle = new Bundle();
                CharSequence charSequence = this.mText;
                if (charSequence != null) {
                    bundle.putCharSequence("text", charSequence);
                }
                bundle.putLong("time", this.mTimestamp);
                Person person = this.mSender;
                if (person != null) {
                    bundle.putCharSequence("sender", Notification.safeCharSequence(person.getName()));
                    bundle.putParcelable(KEY_SENDER_PERSON, this.mSender);
                }
                String str = this.mDataMimeType;
                if (str != null) {
                    bundle.putString("type", str);
                }
                Uri uri = this.mDataUri;
                if (uri != null) {
                    bundle.putParcelable("uri", uri);
                }
                Bundle bundle2 = this.mExtras;
                if (bundle2 != null) {
                    bundle.putBundle(KEY_EXTRAS_BUNDLE, bundle2);
                }
                boolean z = this.mRemoteInputHistory;
                if (z) {
                    bundle.putBoolean(KEY_REMOTE_INPUT_HISTORY, z);
                }
                return bundle;
            }

            static Bundle[] getBundleArrayForMessages(List<Message> list) {
                Bundle[] bundleArr = new Bundle[list.size()];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    bundleArr[i] = list.get(i).toBundle();
                }
                return bundleArr;
            }

            public static List<Message> getMessagesFromBundleArray(Parcelable[] parcelableArr) {
                Message messageFromBundle;
                if (parcelableArr == null) {
                    return new ArrayList();
                }
                ArrayList arrayList = new ArrayList(parcelableArr.length);
                for (int i = 0; i < parcelableArr.length; i++) {
                    if ((parcelableArr[i] instanceof Bundle) && (messageFromBundle = getMessageFromBundle((Bundle) parcelableArr[i])) != null) {
                        arrayList.add(messageFromBundle);
                    }
                }
                return arrayList;
            }

            public static Message getMessageFromBundle(Bundle bundle) {
                CharSequence charSequence;
                try {
                    if (bundle.containsKey("text") && bundle.containsKey("time")) {
                        Person personBuild = (Person) bundle.getParcelable(KEY_SENDER_PERSON);
                        if (personBuild == null && (charSequence = bundle.getCharSequence("sender")) != null) {
                            personBuild = new Person.Builder().setName(charSequence).build();
                        }
                        Message message = new Message(bundle.getCharSequence("text"), bundle.getLong("time"), personBuild, bundle.getBoolean(KEY_REMOTE_INPUT_HISTORY, false));
                        if (bundle.containsKey("type") && bundle.containsKey("uri")) {
                            message.setData(bundle.getString("type"), (Uri) bundle.getParcelable("uri"));
                        }
                        if (bundle.containsKey(KEY_EXTRAS_BUNDLE)) {
                            message.getExtras().putAll(bundle.getBundle(KEY_EXTRAS_BUNDLE));
                        }
                        return message;
                    }
                } catch (ClassCastException unused) {
                }
                return null;
            }
        }
    }

    public static class InboxStyle extends Style {
        private static final int NUMBER_OF_HISTORY_ALLOWED_UNTIL_REDUCTION = 1;
        private ArrayList<CharSequence> mTexts = new ArrayList<>(5);

        public InboxStyle() {
        }

        @Deprecated
        public InboxStyle(Builder builder) {
            setBuilder(builder);
        }

        public InboxStyle setBigContentTitle(CharSequence charSequence) {
            internalSetBigContentTitle(Notification.safeCharSequence(charSequence));
            return this;
        }

        public InboxStyle setSummaryText(CharSequence charSequence) {
            internalSetSummaryText(Notification.safeCharSequence(charSequence));
            return this;
        }

        public InboxStyle addLine(CharSequence charSequence) {
            this.mTexts.add(Notification.safeCharSequence(charSequence));
            return this;
        }

        public ArrayList<CharSequence> getLines() {
            return this.mTexts;
        }

        @Override // android.app.Notification.Style
        public void addExtras(Bundle bundle) {
            super.addExtras(bundle);
            bundle.putCharSequenceArray("android.textLines", (CharSequence[]) this.mTexts.toArray(new CharSequence[this.mTexts.size()]));
        }

        @Override // android.app.Notification.Style
        protected void restoreFromExtras(Bundle bundle) {
            super.restoreFromExtras(bundle);
            this.mTexts.clear();
            if (bundle.containsKey("android.textLines")) {
                Collections.addAll(this.mTexts, bundle.getCharSequenceArray("android.textLines"));
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x008c A[PHI: r3
  0x008c: PHI (r3v2 int) = (r3v1 int), (r3v1 int), (r3v1 int), (r3v15 int) binds: [B:9:0x0067, B:11:0x006a, B:13:0x007a, B:16:0x0085] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // android.app.Notification.Style
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.widget.RemoteViews makeBigContentView() {
            /*
                Method dump skipped, instruction units count: 272
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.app.Notification.InboxStyle.makeBigContentView():android.widget.RemoteViews");
        }

        @Override // android.app.Notification.Style
        public boolean areNotificationsVisiblyDifferent(Style style) {
            if (style == null || getClass() != style.getClass()) {
                return true;
            }
            ArrayList<CharSequence> lines = getLines();
            ArrayList<CharSequence> lines2 = ((InboxStyle) style).getLines();
            int size = lines.size();
            if (size != lines2.size()) {
                return true;
            }
            for (int i = 0; i < size; i++) {
                if (!Objects.equals(String.valueOf(lines.get(i)), String.valueOf(lines2.get(i)))) {
                    return true;
                }
            }
            return false;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x002a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void handleInboxImageMargin(android.widget.RemoteViews r3, int r4, boolean r5, int r6) {
            /*
                r2 = this;
                r0 = 0
                if (r5 == 0) goto L2a
                android.app.Notification$Builder r5 = r2.mBuilder
                android.app.Notification r5 = android.app.Notification.Builder.access$400(r5)
                android.os.Bundle r5 = r5.extras
                java.lang.String r1 = "android.progressMax"
                int r5 = r5.getInt(r1, r0)
                android.app.Notification$Builder r2 = r2.mBuilder
                android.app.Notification r2 = android.app.Notification.Builder.access$400(r2)
                android.os.Bundle r2 = r2.extras
                java.lang.String r1 = "android.progressIndeterminate"
                boolean r2 = r2.getBoolean(r1)
                if (r5 != 0) goto L26
                if (r2 == 0) goto L24
                goto L26
            L24:
                r2 = r0
                goto L27
            L26:
                r2 = 1
            L27:
                if (r2 != 0) goto L2a
                goto L2b
            L2a:
                r6 = r0
            L2b:
                r3.setViewLayoutMarginEnd(r4, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.app.Notification.InboxStyle.handleInboxImageMargin(android.widget.RemoteViews, int, boolean, int):void");
        }
    }

    public static class MediaStyle extends Style {
        static final int MAX_MEDIA_BUTTONS = 5;
        static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        private static final int[] MEDIA_BUTTON_IDS = {R.id.action0, R.id.action1, R.id.action2, R.id.action3, R.id.action4};
        private int[] mActionsToShowInCompact = null;
        private MediaSession.Token mToken;

        @Override // android.app.Notification.Style
        protected boolean hasProgress() {
            return false;
        }

        public MediaStyle() {
        }

        @Deprecated
        public MediaStyle(Builder builder) {
            setBuilder(builder);
        }

        public MediaStyle setShowActionsInCompactView(int... iArr) {
            this.mActionsToShowInCompact = iArr;
            return this;
        }

        public MediaStyle setMediaSession(MediaSession.Token token) {
            this.mToken = token;
            return this;
        }

        @Override // android.app.Notification.Style
        public Notification buildStyled(Notification notification) {
            super.buildStyled(notification);
            if (notification.category == null) {
                notification.category = "transport";
            }
            return notification;
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeContentView(boolean z) {
            return makeMediaContentView();
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeBigContentView() {
            return makeMediaBigContentView();
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeHeadsUpContentView(boolean z) {
            return makeMediaContentView();
        }

        @Override // android.app.Notification.Style
        public void addExtras(Bundle bundle) {
            super.addExtras(bundle);
            MediaSession.Token token = this.mToken;
            if (token != null) {
                bundle.putParcelable("android.mediaSession", token);
            }
            int[] iArr = this.mActionsToShowInCompact;
            if (iArr != null) {
                bundle.putIntArray("android.compactActions", iArr);
            }
        }

        @Override // android.app.Notification.Style
        protected void restoreFromExtras(Bundle bundle) {
            super.restoreFromExtras(bundle);
            if (bundle.containsKey("android.mediaSession")) {
                this.mToken = (MediaSession.Token) bundle.getParcelable("android.mediaSession");
            }
            if (bundle.containsKey("android.compactActions")) {
                this.mActionsToShowInCompact = bundle.getIntArray("android.compactActions");
            }
        }

        @Override // android.app.Notification.Style
        public boolean areNotificationsVisiblyDifferent(Style style) {
            return style == null || getClass() != style.getClass();
        }

        private void bindMediaActionButton(RemoteViews remoteViews, int i, Action action, StandardTemplateParams standardTemplateParams) {
            int actionColor;
            boolean z = action.actionIntent == null;
            remoteViews.setViewVisibility(i, 0);
            if (i != 16909158) {
                remoteViews.setImageViewIcon(i, action.getIcon());
            }
            boolean z2 = (this.mBuilder.mContext.getResources().getConfiguration().uiMode & 48) == 32;
            if (!this.mBuilder.shouldTintActionButtons() && !this.mBuilder.isColorized(standardTemplateParams)) {
                actionColor = ContrastColorUtil.resolveColor(this.mBuilder.mContext, 0, z2);
            } else {
                actionColor = getActionColor(standardTemplateParams);
            }
            remoteViews.setDrawableTint(i, false, actionColor, PorterDuff.Mode.SRC_ATOP);
            TypedArray typedArrayObtainStyledAttributes = this.mBuilder.mContext.obtainStyledAttributes(new int[]{16843820});
            int iAlpha = Color.alpha(typedArrayObtainStyledAttributes.getColor(0, 0));
            typedArrayObtainStyledAttributes.recycle();
            remoteViews.setRippleDrawableColor(i, ColorStateList.valueOf(Color.argb(iAlpha, Color.red(actionColor), Color.green(actionColor), Color.blue(actionColor))));
            if (!z) {
                remoteViews.setOnClickPendingIntent(i, action.actionIntent);
            }
            remoteViews.setContentDescription(i, action.title);
        }

        private RemoteViews makeMediaContentView() {
            StandardTemplateParams standardTemplateParamsFillTextsFrom = this.mBuilder.mParams.reset().hasProgress(false).fillTextsFrom(this.mBuilder);
            RemoteViews remoteViewsApplyStandardTemplate = this.mBuilder.applyStandardTemplate(R.layout.notification_template_material_media, standardTemplateParamsFillTextsFrom, null);
            int size = this.mBuilder.mActions.size();
            int[] iArr = this.mActionsToShowInCompact;
            int iMin = iArr == null ? 0 : Math.min(iArr.length, 3);
            if (iMin > size) {
                throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", Integer.valueOf(size), Integer.valueOf(size - 1)));
            }
            for (int i = 0; i < 3; i++) {
                if (i < iMin) {
                    bindMediaActionButton(remoteViewsApplyStandardTemplate, MEDIA_BUTTON_IDS[i], (Action) this.mBuilder.mActions.get(this.mActionsToShowInCompact[i]), standardTemplateParamsFillTextsFrom);
                } else {
                    remoteViewsApplyStandardTemplate.setViewVisibility(MEDIA_BUTTON_IDS[i], 8);
                }
            }
            bindMediaActionButton(remoteViewsApplyStandardTemplate, R.id.media_seamless, new Action(R.drawable.ic_media_seamless, this.mBuilder.mContext.getString(R.string.ext_media_seamless_action), null), standardTemplateParamsFillTextsFrom);
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.media_seamless, 8);
            handleImage(remoteViewsApplyStandardTemplate);
            int i2 = R.dimen.notification_content_margin_end;
            if (this.mBuilder.mN.hasLargeIcon()) {
                i2 = R.dimen.notification_media_image_margin_end;
            }
            remoteViewsApplyStandardTemplate.setViewLayoutMarginEndDimen(R.id.notification_main_column, i2);
            return remoteViewsApplyStandardTemplate;
        }

        private int getActionColor(StandardTemplateParams standardTemplateParams) {
            return this.mBuilder.isColorized(standardTemplateParams) ? this.mBuilder.getPrimaryTextColor(standardTemplateParams) : this.mBuilder.resolveContrastColor(standardTemplateParams);
        }

        private RemoteViews makeMediaBigContentView() {
            int iMin = Math.min(this.mBuilder.mActions.size(), 5);
            int[] iArr = this.mActionsToShowInCompact;
            int iMin2 = iArr == null ? 0 : Math.min(iArr.length, 3);
            if (!this.mBuilder.mN.hasLargeIcon() && iMin <= iMin2) {
                return null;
            }
            StandardTemplateParams standardTemplateParamsFillTextsFrom = this.mBuilder.mParams.reset().hasProgress(false).fillTextsFrom(this.mBuilder);
            RemoteViews remoteViewsApplyStandardTemplate = this.mBuilder.applyStandardTemplate(R.layout.notification_template_material_big_media, standardTemplateParamsFillTextsFrom, null);
            for (int i = 0; i < 5; i++) {
                if (i < iMin) {
                    bindMediaActionButton(remoteViewsApplyStandardTemplate, MEDIA_BUTTON_IDS[i], (Action) this.mBuilder.mActions.get(i), standardTemplateParamsFillTextsFrom);
                } else {
                    remoteViewsApplyStandardTemplate.setViewVisibility(MEDIA_BUTTON_IDS[i], 8);
                }
            }
            bindMediaActionButton(remoteViewsApplyStandardTemplate, R.id.media_seamless, new Action(R.drawable.ic_media_seamless, this.mBuilder.mContext.getString(R.string.ext_media_seamless_action), null), standardTemplateParamsFillTextsFrom);
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.media_seamless, 8);
            handleImage(remoteViewsApplyStandardTemplate);
            return remoteViewsApplyStandardTemplate;
        }

        private void handleImage(RemoteViews remoteViews) {
            if (this.mBuilder.mN.hasLargeIcon()) {
                remoteViews.setViewLayoutMarginEndDimen(R.id.line1, 0);
                remoteViews.setViewLayoutMarginEndDimen(R.id.text, 0);
            }
        }
    }

    public static class DecoratedCustomViewStyle extends Style {
        @Override // android.app.Notification.Style
        public boolean displayCustomViewInline() {
            return true;
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeContentView(boolean z) {
            return makeStandardTemplateWithCustomContent(this.mBuilder.mN.contentView);
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeBigContentView() {
            return makeDecoratedBigContentView();
        }

        @Override // android.app.Notification.Style
        public RemoteViews makeHeadsUpContentView(boolean z) {
            return makeDecoratedHeadsUpContentView();
        }

        private RemoteViews makeDecoratedHeadsUpContentView() {
            RemoteViews remoteViews = this.mBuilder.mN.headsUpContentView == null ? this.mBuilder.mN.contentView : this.mBuilder.mN.headsUpContentView;
            if (this.mBuilder.mActions.size() == 0) {
                return makeStandardTemplateWithCustomContent(remoteViews);
            }
            TemplateBindResult templateBindResult = new TemplateBindResult();
            RemoteViews remoteViewsApplyStandardTemplateWithActions = this.mBuilder.applyStandardTemplateWithActions(this.mBuilder.getBigBaseLayoutResource(), templateBindResult);
            buildIntoRemoteViewContent(remoteViewsApplyStandardTemplateWithActions, remoteViews, templateBindResult);
            return remoteViewsApplyStandardTemplateWithActions;
        }

        private RemoteViews makeStandardTemplateWithCustomContent(RemoteViews remoteViews) {
            TemplateBindResult templateBindResult = new TemplateBindResult();
            RemoteViews remoteViewsApplyStandardTemplate = this.mBuilder.applyStandardTemplate(this.mBuilder.getBaseLayoutResource(), templateBindResult);
            buildIntoRemoteViewContent(remoteViewsApplyStandardTemplate, remoteViews, templateBindResult);
            return remoteViewsApplyStandardTemplate;
        }

        private RemoteViews makeDecoratedBigContentView() {
            RemoteViews remoteViews = this.mBuilder.mN.bigContentView == null ? this.mBuilder.mN.contentView : this.mBuilder.mN.bigContentView;
            if (this.mBuilder.mActions.size() == 0) {
                return makeStandardTemplateWithCustomContent(remoteViews);
            }
            TemplateBindResult templateBindResult = new TemplateBindResult();
            RemoteViews remoteViewsApplyStandardTemplateWithActions = this.mBuilder.applyStandardTemplateWithActions(this.mBuilder.getBigBaseLayoutResource(), templateBindResult);
            buildIntoRemoteViewContent(remoteViewsApplyStandardTemplateWithActions, remoteViews, templateBindResult);
            return remoteViewsApplyStandardTemplateWithActions;
        }

        private void buildIntoRemoteViewContent(RemoteViews remoteViews, RemoteViews remoteViews2, TemplateBindResult templateBindResult) {
            int i = 0;
            if (remoteViews2 != null) {
                RemoteViews remoteViewsMo13clone = remoteViews2.mo13clone();
                remoteViews.removeAllViewsExceptId(R.id.notification_main_column, 16908301);
                remoteViews.addView(R.id.notification_main_column, remoteViewsMo13clone, 0);
                remoteViews.addFlags(1);
            } else {
                i = -1;
            }
            remoteViews.setIntTag(R.id.notification_main_column, R.id.notification_custom_view_index_tag, i);
            remoteViews.setViewLayoutMarginEnd(R.id.notification_main_column, this.mBuilder.mContext.getResources().getDimensionPixelSize(R.dimen.notification_content_margin_end) + templateBindResult.getIconMarginEnd());
        }

        @Override // android.app.Notification.Style
        public boolean areNotificationsVisiblyDifferent(Style style) {
            return style == null || getClass() != style.getClass();
        }
    }

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        @Override // android.app.Notification.Style
        public boolean displayCustomViewInline() {
            return true;
        }

        @Override // android.app.Notification.MediaStyle, android.app.Notification.Style
        public RemoteViews makeContentView(boolean z) {
            return buildIntoRemoteView(super.makeContentView(false), R.id.notification_content_container, this.mBuilder.mN.contentView);
        }

        @Override // android.app.Notification.MediaStyle, android.app.Notification.Style
        public RemoteViews makeBigContentView() {
            return makeBigContentViewWithCustomContent(this.mBuilder.mN.bigContentView != null ? this.mBuilder.mN.bigContentView : this.mBuilder.mN.contentView);
        }

        private RemoteViews makeBigContentViewWithCustomContent(RemoteViews remoteViews) {
            RemoteViews remoteViewsMakeBigContentView = super.makeBigContentView();
            if (remoteViewsMakeBigContentView == null) {
                if (remoteViews != this.mBuilder.mN.contentView) {
                    return buildIntoRemoteView(super.makeContentView(false), R.id.notification_content_container, remoteViews);
                }
                return null;
            }
            return buildIntoRemoteView(remoteViewsMakeBigContentView, R.id.notification_main_column, remoteViews);
        }

        @Override // android.app.Notification.MediaStyle, android.app.Notification.Style
        public RemoteViews makeHeadsUpContentView(boolean z) {
            return makeBigContentViewWithCustomContent(this.mBuilder.mN.headsUpContentView != null ? this.mBuilder.mN.headsUpContentView : this.mBuilder.mN.contentView);
        }

        @Override // android.app.Notification.MediaStyle, android.app.Notification.Style
        public boolean areNotificationsVisiblyDifferent(Style style) {
            return style == null || getClass() != style.getClass();
        }

        private RemoteViews buildIntoRemoteView(RemoteViews remoteViews, int i, RemoteViews remoteViews2) {
            if (remoteViews2 != null) {
                RemoteViews remoteViewsMo13clone = remoteViews2.mo13clone();
                remoteViewsMo13clone.overrideTextColors(this.mBuilder.getPrimaryTextColor(this.mBuilder.mParams));
                remoteViews.removeAllViews(i);
                remoteViews.addView(i, remoteViewsMo13clone);
                remoteViews.addFlags(1);
            }
            return remoteViews;
        }
    }

    public static final class BubbleMetadata implements Parcelable {
        public static final Parcelable.Creator<BubbleMetadata> CREATOR = new Parcelable.Creator<BubbleMetadata>() { // from class: android.app.Notification.BubbleMetadata.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BubbleMetadata createFromParcel(Parcel parcel) {
                return new BubbleMetadata(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BubbleMetadata[] newArray(int i) {
                return new BubbleMetadata[i];
            }
        };
        public static final int FLAG_AUTO_EXPAND_BUBBLE = 1;
        public static final int FLAG_SUPPRESS_NOTIFICATION = 2;
        private PendingIntent mDeleteIntent;
        private int mDesiredHeight;
        private int mDesiredHeightResId;
        private int mFlags;
        private Icon mIcon;
        private PendingIntent mPendingIntent;
        private String mShortcutId;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        private BubbleMetadata(PendingIntent pendingIntent, PendingIntent pendingIntent2, Icon icon, int i, int i2, String str) {
            this.mPendingIntent = pendingIntent;
            this.mIcon = icon;
            this.mDesiredHeight = i;
            this.mDesiredHeightResId = i2;
            this.mDeleteIntent = pendingIntent2;
            this.mShortcutId = str;
        }

        private BubbleMetadata(Parcel parcel) {
            if (parcel.readInt() != 0) {
                this.mPendingIntent = PendingIntent.CREATOR.createFromParcel(parcel);
            }
            if (parcel.readInt() != 0) {
                this.mIcon = Icon.CREATOR.createFromParcel(parcel);
            }
            this.mDesiredHeight = parcel.readInt();
            this.mFlags = parcel.readInt();
            if (parcel.readInt() != 0) {
                this.mDeleteIntent = PendingIntent.CREATOR.createFromParcel(parcel);
            }
            this.mDesiredHeightResId = parcel.readInt();
            if (parcel.readInt() != 0) {
                this.mShortcutId = parcel.readString8();
            }
        }

        public String getShortcutId() {
            return this.mShortcutId;
        }

        public PendingIntent getIntent() {
            return this.mPendingIntent;
        }

        @Deprecated
        public PendingIntent getBubbleIntent() {
            return this.mPendingIntent;
        }

        public PendingIntent getDeleteIntent() {
            return this.mDeleteIntent;
        }

        public Icon getIcon() {
            return this.mIcon;
        }

        @Deprecated
        public Icon getBubbleIcon() {
            return this.mIcon;
        }

        public int getDesiredHeight() {
            return this.mDesiredHeight;
        }

        public int getDesiredHeightResId() {
            return this.mDesiredHeightResId;
        }

        public boolean getAutoExpandBubble() {
            return (this.mFlags & 1) != 0;
        }

        public boolean isNotificationSuppressed() {
            return (this.mFlags & 2) != 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mPendingIntent != null ? 1 : 0);
            PendingIntent pendingIntent = this.mPendingIntent;
            if (pendingIntent != null) {
                pendingIntent.writeToParcel(parcel, 0);
            }
            parcel.writeInt(this.mIcon != null ? 1 : 0);
            Icon icon = this.mIcon;
            if (icon != null) {
                icon.writeToParcel(parcel, 0);
            }
            parcel.writeInt(this.mDesiredHeight);
            parcel.writeInt(this.mFlags);
            parcel.writeInt(this.mDeleteIntent != null ? 1 : 0);
            PendingIntent pendingIntent2 = this.mDeleteIntent;
            if (pendingIntent2 != null) {
                pendingIntent2.writeToParcel(parcel, 0);
            }
            parcel.writeInt(this.mDesiredHeightResId);
            parcel.writeInt(!TextUtils.isEmpty(this.mShortcutId) ? 1 : 0);
            if (TextUtils.isEmpty(this.mShortcutId)) {
                return;
            }
            parcel.writeString8(this.mShortcutId);
        }

        public void setFlags(int i) {
            this.mFlags = i;
        }

        public int getFlags() {
            return this.mFlags;
        }

        public static final class Builder {
            private PendingIntent mDeleteIntent;
            private int mDesiredHeight;
            private int mDesiredHeightResId;
            private int mFlags;
            private Icon mIcon;
            private PendingIntent mPendingIntent;
            private String mShortcutId;

            @Deprecated
            public Builder() {
            }

            public Builder(String str) {
                if (TextUtils.isEmpty(str)) {
                    throw new NullPointerException("Bubble requires a non-null shortcut id");
                }
                this.mShortcutId = str;
            }

            public Builder(PendingIntent pendingIntent, Icon icon) {
                Objects.requireNonNull(pendingIntent, "Bubble requires non-null pending intent");
                Objects.requireNonNull(icon, "Bubbles require non-null icon");
                if (icon.getType() != 6 && icon.getType() != 4) {
                    Log.w(Notification.TAG, "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required.");
                }
                this.mPendingIntent = pendingIntent;
                this.mIcon = icon;
            }

            @Deprecated
            public Builder createShortcutBubble(String str) {
                if (!TextUtils.isEmpty(str)) {
                    this.mPendingIntent = null;
                    this.mIcon = null;
                }
                this.mShortcutId = str;
                return this;
            }

            @Deprecated
            public Builder createIntentBubble(PendingIntent pendingIntent, Icon icon) {
                if (pendingIntent == null) {
                    throw new IllegalArgumentException("Bubble requires non-null pending intent");
                }
                if (icon == null) {
                    throw new IllegalArgumentException("Bubbles require non-null icon");
                }
                if (icon.getType() != 6 && icon.getType() != 4) {
                    Log.w(Notification.TAG, "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required.");
                }
                this.mShortcutId = null;
                this.mPendingIntent = pendingIntent;
                this.mIcon = icon;
                return this;
            }

            public Builder setIntent(PendingIntent pendingIntent) {
                if (this.mShortcutId != null) {
                    throw new IllegalStateException("Created as a shortcut bubble, cannot set a PendingIntent. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
                }
                Objects.requireNonNull(pendingIntent, "Bubble requires non-null pending intent");
                this.mPendingIntent = pendingIntent;
                return this;
            }

            public Builder setIcon(Icon icon) {
                if (this.mShortcutId != null) {
                    throw new IllegalStateException("Created as a shortcut bubble, cannot set an Icon. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
                }
                Objects.requireNonNull(icon, "Bubbles require non-null icon");
                if (icon.getType() != 6 && icon.getType() != 4) {
                    Log.w(Notification.TAG, "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required.");
                }
                this.mIcon = icon;
                return this;
            }

            public Builder setDesiredHeight(int i) {
                this.mDesiredHeight = Math.max(i, 0);
                this.mDesiredHeightResId = 0;
                return this;
            }

            public Builder setDesiredHeightResId(int i) {
                this.mDesiredHeightResId = i;
                this.mDesiredHeight = 0;
                return this;
            }

            public Builder setAutoExpandBubble(boolean z) {
                setFlag(1, z);
                return this;
            }

            public Builder setSuppressNotification(boolean z) {
                setFlag(2, z);
                return this;
            }

            public Builder setDeleteIntent(PendingIntent pendingIntent) {
                this.mDeleteIntent = pendingIntent;
                return this;
            }

            public BubbleMetadata build() {
                String str = this.mShortcutId;
                if (str == null) {
                    Objects.requireNonNull(this.mPendingIntent, "Must supply pending intent or shortcut to bubble");
                }
                if (str == null) {
                    Objects.requireNonNull(this.mIcon, "Must supply an icon or shortcut for the bubble");
                }
                BubbleMetadata bubbleMetadata = new BubbleMetadata(this.mPendingIntent, this.mDeleteIntent, this.mIcon, this.mDesiredHeight, this.mDesiredHeightResId, this.mShortcutId);
                bubbleMetadata.setFlags(this.mFlags);
                return bubbleMetadata;
            }

            public Builder setFlag(int i, boolean z) {
                if (z) {
                    this.mFlags = i | this.mFlags;
                } else {
                    this.mFlags = (~i) & this.mFlags;
                }
                return this;
            }
        }
    }

    public static final class WearableExtender implements Extender {
        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";

        @Deprecated
        public static final int SCREEN_TIMEOUT_LONG = -1;

        @Deprecated
        public static final int SCREEN_TIMEOUT_SHORT = 0;

        @Deprecated
        public static final int SIZE_DEFAULT = 0;

        @Deprecated
        public static final int SIZE_FULL_SCREEN = 5;

        @Deprecated
        public static final int SIZE_LARGE = 4;

        @Deprecated
        public static final int SIZE_MEDIUM = 3;

        @Deprecated
        public static final int SIZE_SMALL = 2;

        @Deprecated
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList<Action> mActions;
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags;
        private int mGravity;
        private int mHintScreenTimeout;
        private ArrayList<Notification> mPages;

        public WearableExtender() {
            this.mActions = new ArrayList<>();
            this.mFlags = 1;
            this.mPages = new ArrayList<>();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
        }

        public WearableExtender(Notification notification) {
            this.mActions = new ArrayList<>();
            this.mFlags = 1;
            this.mPages = new ArrayList<>();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
            Bundle bundle = notification.extras.getBundle(EXTRA_WEARABLE_EXTENSIONS);
            if (bundle != null) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList("actions");
                if (parcelableArrayList != null) {
                    this.mActions.addAll(parcelableArrayList);
                }
                this.mFlags = bundle.getInt("flags", 1);
                this.mDisplayIntent = (PendingIntent) bundle.getParcelable(KEY_DISPLAY_INTENT);
                Notification[] notificationArr = (Notification[]) Notification.getParcelableArrayFromBundle(bundle, KEY_PAGES, Notification.class);
                if (notificationArr != null) {
                    Collections.addAll(this.mPages, notificationArr);
                }
                this.mBackground = (Bitmap) bundle.getParcelable(KEY_BACKGROUND);
                this.mContentIcon = bundle.getInt(KEY_CONTENT_ICON);
                this.mContentIconGravity = bundle.getInt(KEY_CONTENT_ICON_GRAVITY, 8388613);
                this.mContentActionIndex = bundle.getInt(KEY_CONTENT_ACTION_INDEX, -1);
                this.mCustomSizePreset = bundle.getInt(KEY_CUSTOM_SIZE_PRESET, 0);
                this.mCustomContentHeight = bundle.getInt(KEY_CUSTOM_CONTENT_HEIGHT);
                this.mGravity = bundle.getInt(KEY_GRAVITY, 80);
                this.mHintScreenTimeout = bundle.getInt(KEY_HINT_SCREEN_TIMEOUT);
                this.mDismissalId = bundle.getString(KEY_DISMISSAL_ID);
                this.mBridgeTag = bundle.getString(KEY_BRIDGE_TAG);
            }
        }

        @Override // android.app.Notification.Extender
        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            if (!this.mActions.isEmpty()) {
                bundle.putParcelableArrayList("actions", this.mActions);
            }
            int i = this.mFlags;
            if (i != 1) {
                bundle.putInt("flags", i);
            }
            PendingIntent pendingIntent = this.mDisplayIntent;
            if (pendingIntent != null) {
                bundle.putParcelable(KEY_DISPLAY_INTENT, pendingIntent);
            }
            if (!this.mPages.isEmpty()) {
                ArrayList<Notification> arrayList = this.mPages;
                bundle.putParcelableArray(KEY_PAGES, (Parcelable[]) arrayList.toArray(new Notification[arrayList.size()]));
            }
            Bitmap bitmap = this.mBackground;
            if (bitmap != null) {
                bundle.putParcelable(KEY_BACKGROUND, bitmap);
            }
            int i2 = this.mContentIcon;
            if (i2 != 0) {
                bundle.putInt(KEY_CONTENT_ICON, i2);
            }
            int i3 = this.mContentIconGravity;
            if (i3 != 8388613) {
                bundle.putInt(KEY_CONTENT_ICON_GRAVITY, i3);
            }
            int i4 = this.mContentActionIndex;
            if (i4 != -1) {
                bundle.putInt(KEY_CONTENT_ACTION_INDEX, i4);
            }
            int i5 = this.mCustomSizePreset;
            if (i5 != 0) {
                bundle.putInt(KEY_CUSTOM_SIZE_PRESET, i5);
            }
            int i6 = this.mCustomContentHeight;
            if (i6 != 0) {
                bundle.putInt(KEY_CUSTOM_CONTENT_HEIGHT, i6);
            }
            int i7 = this.mGravity;
            if (i7 != 80) {
                bundle.putInt(KEY_GRAVITY, i7);
            }
            int i8 = this.mHintScreenTimeout;
            if (i8 != 0) {
                bundle.putInt(KEY_HINT_SCREEN_TIMEOUT, i8);
            }
            String str = this.mDismissalId;
            if (str != null) {
                bundle.putString(KEY_DISMISSAL_ID, str);
            }
            String str2 = this.mBridgeTag;
            if (str2 != null) {
                bundle.putString(KEY_BRIDGE_TAG, str2);
            }
            builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, bundle);
            return builder;
        }

        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public WearableExtender m14clone() {
            WearableExtender wearableExtender = new WearableExtender();
            wearableExtender.mActions = new ArrayList<>(this.mActions);
            wearableExtender.mFlags = this.mFlags;
            wearableExtender.mDisplayIntent = this.mDisplayIntent;
            wearableExtender.mPages = new ArrayList<>(this.mPages);
            wearableExtender.mBackground = this.mBackground;
            wearableExtender.mContentIcon = this.mContentIcon;
            wearableExtender.mContentIconGravity = this.mContentIconGravity;
            wearableExtender.mContentActionIndex = this.mContentActionIndex;
            wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
            wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
            wearableExtender.mGravity = this.mGravity;
            wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
            wearableExtender.mDismissalId = this.mDismissalId;
            wearableExtender.mBridgeTag = this.mBridgeTag;
            return wearableExtender;
        }

        public WearableExtender addAction(Action action) {
            this.mActions.add(action);
            return this;
        }

        public WearableExtender addActions(List<Action> list) {
            this.mActions.addAll(list);
            return this;
        }

        public WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }

        public List<Action> getActions() {
            return this.mActions;
        }

        @Deprecated
        public WearableExtender setDisplayIntent(PendingIntent pendingIntent) {
            this.mDisplayIntent = pendingIntent;
            return this;
        }

        @Deprecated
        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }

        @Deprecated
        public WearableExtender addPage(Notification notification) {
            this.mPages.add(notification);
            return this;
        }

        @Deprecated
        public WearableExtender addPages(List<Notification> list) {
            this.mPages.addAll(list);
            return this;
        }

        @Deprecated
        public WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }

        @Deprecated
        public List<Notification> getPages() {
            return this.mPages;
        }

        @Deprecated
        public WearableExtender setBackground(Bitmap bitmap) {
            this.mBackground = bitmap;
            return this;
        }

        @Deprecated
        public Bitmap getBackground() {
            return this.mBackground;
        }

        @Deprecated
        public WearableExtender setContentIcon(int i) {
            this.mContentIcon = i;
            return this;
        }

        @Deprecated
        public int getContentIcon() {
            return this.mContentIcon;
        }

        @Deprecated
        public WearableExtender setContentIconGravity(int i) {
            this.mContentIconGravity = i;
            return this;
        }

        @Deprecated
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }

        public WearableExtender setContentAction(int i) {
            this.mContentActionIndex = i;
            return this;
        }

        public int getContentAction() {
            return this.mContentActionIndex;
        }

        @Deprecated
        public WearableExtender setGravity(int i) {
            this.mGravity = i;
            return this;
        }

        @Deprecated
        public int getGravity() {
            return this.mGravity;
        }

        @Deprecated
        public WearableExtender setCustomSizePreset(int i) {
            this.mCustomSizePreset = i;
            return this;
        }

        @Deprecated
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }

        @Deprecated
        public WearableExtender setCustomContentHeight(int i) {
            this.mCustomContentHeight = i;
            return this;
        }

        @Deprecated
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }

        public WearableExtender setStartScrollBottom(boolean z) {
            setFlag(8, z);
            return this;
        }

        public boolean getStartScrollBottom() {
            return (this.mFlags & 8) != 0;
        }

        public WearableExtender setContentIntentAvailableOffline(boolean z) {
            setFlag(1, z);
            return this;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.mFlags & 1) != 0;
        }

        @Deprecated
        public WearableExtender setHintHideIcon(boolean z) {
            setFlag(2, z);
            return this;
        }

        @Deprecated
        public boolean getHintHideIcon() {
            return (this.mFlags & 2) != 0;
        }

        @Deprecated
        public WearableExtender setHintShowBackgroundOnly(boolean z) {
            setFlag(4, z);
            return this;
        }

        @Deprecated
        public boolean getHintShowBackgroundOnly() {
            return (this.mFlags & 4) != 0;
        }

        @Deprecated
        public WearableExtender setHintAvoidBackgroundClipping(boolean z) {
            setFlag(16, z);
            return this;
        }

        @Deprecated
        public boolean getHintAvoidBackgroundClipping() {
            return (this.mFlags & 16) != 0;
        }

        @Deprecated
        public WearableExtender setHintScreenTimeout(int i) {
            this.mHintScreenTimeout = i;
            return this;
        }

        @Deprecated
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }

        @Deprecated
        public WearableExtender setHintAmbientBigPicture(boolean z) {
            setFlag(32, z);
            return this;
        }

        @Deprecated
        public boolean getHintAmbientBigPicture() {
            return (this.mFlags & 32) != 0;
        }

        public WearableExtender setHintContentIntentLaunchesActivity(boolean z) {
            setFlag(64, z);
            return this;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.mFlags & 64) != 0;
        }

        public WearableExtender setDismissalId(String str) {
            this.mDismissalId = str;
            return this;
        }

        public String getDismissalId() {
            return this.mDismissalId;
        }

        public WearableExtender setBridgeTag(String str) {
            this.mBridgeTag = str;
            return this;
        }

        public String getBridgeTag() {
            return this.mBridgeTag;
        }

        private void setFlag(int i, boolean z) {
            if (z) {
                this.mFlags = i | this.mFlags;
            } else {
                this.mFlags = (~i) & this.mFlags;
            }
        }
    }

    public static final class CarExtender implements Extender {
        private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String TAG = "CarExtender";
        private int mColor;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public CarExtender() {
            this.mColor = 0;
        }

        public CarExtender(Notification notification) {
            this.mColor = 0;
            Bundle bundle = notification.extras == null ? null : notification.extras.getBundle(EXTRA_CAR_EXTENDER);
            if (bundle != null) {
                this.mLargeIcon = (Bitmap) bundle.getParcelable(EXTRA_LARGE_ICON);
                this.mColor = bundle.getInt(EXTRA_COLOR, 0);
                this.mUnreadConversation = UnreadConversation.getUnreadConversationFromBundle(bundle.getBundle(EXTRA_CONVERSATION));
            }
        }

        @Override // android.app.Notification.Extender
        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            Bitmap bitmap = this.mLargeIcon;
            if (bitmap != null) {
                bundle.putParcelable(EXTRA_LARGE_ICON, bitmap);
            }
            int i = this.mColor;
            if (i != 0) {
                bundle.putInt(EXTRA_COLOR, i);
            }
            UnreadConversation unreadConversation = this.mUnreadConversation;
            if (unreadConversation != null) {
                bundle.putBundle(EXTRA_CONVERSATION, unreadConversation.getBundleForUnreadConversation());
            }
            builder.getExtras().putBundle(EXTRA_CAR_EXTENDER, bundle);
            return builder;
        }

        public CarExtender setColor(int i) {
            this.mColor = i;
            return this;
        }

        public int getColor() {
            return this.mColor;
        }

        public CarExtender setLargeIcon(Bitmap bitmap) {
            this.mLargeIcon = bitmap;
            return this;
        }

        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }

        public CarExtender setUnreadConversation(UnreadConversation unreadConversation) {
            this.mUnreadConversation = unreadConversation;
            return this;
        }

        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }

        public static class UnreadConversation {
            private static final String KEY_AUTHOR = "author";
            private static final String KEY_MESSAGES = "messages";
            private static final String KEY_ON_READ = "on_read";
            private static final String KEY_ON_REPLY = "on_reply";
            private static final String KEY_PARTICIPANTS = "participants";
            private static final String KEY_REMOTE_INPUT = "remote_input";
            private static final String KEY_TEXT = "text";
            private static final String KEY_TIMESTAMP = "timestamp";
            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;

            UnreadConversation(String[] strArr, RemoteInput remoteInput, PendingIntent pendingIntent, PendingIntent pendingIntent2, String[] strArr2, long j) {
                this.mMessages = strArr;
                this.mRemoteInput = remoteInput;
                this.mReadPendingIntent = pendingIntent2;
                this.mReplyPendingIntent = pendingIntent;
                this.mParticipants = strArr2;
                this.mLatestTimestamp = j;
            }

            public String[] getMessages() {
                return this.mMessages;
            }

            public RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }

            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }

            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }

            public String[] getParticipants() {
                return this.mParticipants;
            }

            public String getParticipant() {
                String[] strArr = this.mParticipants;
                if (strArr.length > 0) {
                    return strArr[0];
                }
                return null;
            }

            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }

            Bundle getBundleForUnreadConversation() {
                Bundle bundle = new Bundle();
                String[] strArr = this.mParticipants;
                String str = (strArr == null || strArr.length <= 1) ? null : strArr[0];
                int length = this.mMessages.length;
                Parcelable[] parcelableArr = new Parcelable[length];
                for (int i = 0; i < length; i++) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("text", this.mMessages[i]);
                    bundle2.putString("author", str);
                    parcelableArr[i] = bundle2;
                }
                bundle.putParcelableArray(KEY_MESSAGES, parcelableArr);
                RemoteInput remoteInput = this.mRemoteInput;
                if (remoteInput != null) {
                    bundle.putParcelable(KEY_REMOTE_INPUT, remoteInput);
                }
                bundle.putParcelable(KEY_ON_REPLY, this.mReplyPendingIntent);
                bundle.putParcelable(KEY_ON_READ, this.mReadPendingIntent);
                bundle.putStringArray(KEY_PARTICIPANTS, this.mParticipants);
                bundle.putLong("timestamp", this.mLatestTimestamp);
                return bundle;
            }

            static UnreadConversation getUnreadConversationFromBundle(Bundle bundle) {
                String[] strArr;
                if (bundle == null) {
                    return null;
                }
                Parcelable[] parcelableArray = bundle.getParcelableArray(KEY_MESSAGES);
                if (parcelableArray != null) {
                    int length = parcelableArray.length;
                    String[] strArr2 = new String[length];
                    boolean z = false;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            z = true;
                            break;
                        }
                        if (!(parcelableArray[i] instanceof Bundle)) {
                            break;
                        }
                        strArr2[i] = ((Bundle) parcelableArray[i]).getString("text");
                        if (strArr2[i] == null) {
                            break;
                        }
                        i++;
                    }
                    if (!z) {
                        return null;
                    }
                    strArr = strArr2;
                } else {
                    strArr = null;
                }
                PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable(KEY_ON_READ);
                PendingIntent pendingIntent2 = (PendingIntent) bundle.getParcelable(KEY_ON_REPLY);
                RemoteInput remoteInput = (RemoteInput) bundle.getParcelable(KEY_REMOTE_INPUT);
                String[] stringArray = bundle.getStringArray(KEY_PARTICIPANTS);
                if (stringArray == null || stringArray.length != 1) {
                    return null;
                }
                return new UnreadConversation(strArr, remoteInput, pendingIntent2, pendingIntent, stringArray, bundle.getLong("timestamp"));
            }
        }

        public static class Builder {
            private long mLatestTimestamp;
            private final List<String> mMessages = new ArrayList();
            private final String mParticipant;
            private PendingIntent mReadPendingIntent;
            private RemoteInput mRemoteInput;
            private PendingIntent mReplyPendingIntent;

            public Builder(String str) {
                this.mParticipant = str;
            }

            public Builder addMessage(String str) {
                this.mMessages.add(str);
                return this;
            }

            public Builder setReplyAction(PendingIntent pendingIntent, RemoteInput remoteInput) {
                this.mRemoteInput = remoteInput;
                this.mReplyPendingIntent = pendingIntent;
                return this;
            }

            public Builder setReadPendingIntent(PendingIntent pendingIntent) {
                this.mReadPendingIntent = pendingIntent;
                return this;
            }

            public Builder setLatestTimestamp(long j) {
                this.mLatestTimestamp = j;
                return this;
            }

            public UnreadConversation build() {
                List<String> list = this.mMessages;
                return new UnreadConversation((String[]) list.toArray(new String[list.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[]{this.mParticipant}, this.mLatestTimestamp);
            }
        }
    }

    @SystemApi
    public static final class TvExtender implements Extender {
        private static final String EXTRA_CHANNEL_ID = "channel_id";
        private static final String EXTRA_CONTENT_INTENT = "content_intent";
        private static final String EXTRA_DELETE_INTENT = "delete_intent";
        private static final String EXTRA_FLAGS = "flags";
        private static final String EXTRA_SUPPRESS_SHOW_OVER_APPS = "suppressShowOverApps";
        private static final String EXTRA_TV_EXTENDER = "android.tv.EXTENSIONS";
        private static final int FLAG_AVAILABLE_ON_TV = 1;
        private static final String TAG = "TvExtender";
        private String mChannelId;
        private PendingIntent mContentIntent;
        private PendingIntent mDeleteIntent;
        private int mFlags;
        private boolean mSuppressShowOverApps;

        public TvExtender() {
            this.mFlags = 1;
        }

        public TvExtender(Notification notification) {
            Bundle bundle = notification.extras == null ? null : notification.extras.getBundle(EXTRA_TV_EXTENDER);
            if (bundle != null) {
                this.mFlags = bundle.getInt("flags");
                this.mChannelId = bundle.getString("channel_id");
                this.mSuppressShowOverApps = bundle.getBoolean(EXTRA_SUPPRESS_SHOW_OVER_APPS);
                this.mContentIntent = (PendingIntent) bundle.getParcelable(EXTRA_CONTENT_INTENT);
                this.mDeleteIntent = (PendingIntent) bundle.getParcelable(EXTRA_DELETE_INTENT);
            }
        }

        @Override // android.app.Notification.Extender
        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            bundle.putInt("flags", this.mFlags);
            bundle.putString("channel_id", this.mChannelId);
            bundle.putBoolean(EXTRA_SUPPRESS_SHOW_OVER_APPS, this.mSuppressShowOverApps);
            PendingIntent pendingIntent = this.mContentIntent;
            if (pendingIntent != null) {
                bundle.putParcelable(EXTRA_CONTENT_INTENT, pendingIntent);
            }
            PendingIntent pendingIntent2 = this.mDeleteIntent;
            if (pendingIntent2 != null) {
                bundle.putParcelable(EXTRA_DELETE_INTENT, pendingIntent2);
            }
            builder.getExtras().putBundle(EXTRA_TV_EXTENDER, bundle);
            return builder;
        }

        public boolean isAvailableOnTv() {
            return (this.mFlags & 1) != 0;
        }

        public TvExtender setChannel(String str) {
            this.mChannelId = str;
            return this;
        }

        public TvExtender setChannelId(String str) {
            this.mChannelId = str;
            return this;
        }

        @Deprecated
        public String getChannel() {
            return this.mChannelId;
        }

        public String getChannelId() {
            return this.mChannelId;
        }

        public TvExtender setContentIntent(PendingIntent pendingIntent) {
            this.mContentIntent = pendingIntent;
            return this;
        }

        public PendingIntent getContentIntent() {
            return this.mContentIntent;
        }

        public TvExtender setDeleteIntent(PendingIntent pendingIntent) {
            this.mDeleteIntent = pendingIntent;
            return this;
        }

        public PendingIntent getDeleteIntent() {
            return this.mDeleteIntent;
        }

        public TvExtender setSuppressShowOverApps(boolean z) {
            this.mSuppressShowOverApps = z;
            return this;
        }

        public boolean getSuppressShowOverApps() {
            return this.mSuppressShowOverApps;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T extends Parcelable> T[] getParcelableArrayFromBundle(Bundle bundle, String str, Class<T> cls) {
        T[] tArr = (T[]) bundle.getParcelableArray(str);
        if (Array.newInstance((Class<?>) cls, 0).getClass().isInstance(tArr) || tArr == null) {
            return tArr;
        }
        T[] tArr2 = (T[]) ((Parcelable[]) Array.newInstance((Class<?>) cls, tArr.length));
        for (int i = 0; i < tArr.length; i++) {
            tArr2[i] = tArr[i];
        }
        bundle.putParcelableArray(str, tArr2);
        return tArr2;
    }

    private static class BuilderRemoteViews extends RemoteViews {
        @Override // android.widget.RemoteViews
        protected boolean shouldUseStaticFilter() {
            return true;
        }

        public BuilderRemoteViews(Parcel parcel) {
            super(parcel);
        }

        public BuilderRemoteViews(ApplicationInfo applicationInfo, int i) {
            super(applicationInfo, i);
        }

        @Override // android.widget.RemoteViews
        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public BuilderRemoteViews mo13clone() {
            Parcel parcelObtain = Parcel.obtain();
            writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            BuilderRemoteViews builderRemoteViews = new BuilderRemoteViews(parcelObtain);
            parcelObtain.recycle();
            return builderRemoteViews;
        }
    }

    private static class TemplateBindResult {
        int mIconMarginEnd;
        boolean mRightIconContainerVisible;

        private TemplateBindResult() {
        }

        public int getIconMarginEnd() {
            return this.mIconMarginEnd;
        }

        public boolean isRightIconContainerVisible() {
            return this.mRightIconContainerVisible;
        }

        public void setIconMarginEnd(int i) {
            this.mIconMarginEnd = i;
        }

        public void setRightIconContainerVisible(boolean z) {
            this.mRightIconContainerVisible = z;
        }
    }

    private static class StandardTemplateParams {
        boolean allowColorization;
        boolean forceDefaultColor;
        boolean hasProgress;
        CharSequence headerTextSecondary;
        boolean hideLargeIcon;
        boolean hideReplyIcon;
        int maxRemoteInputHistory;
        CharSequence summaryText;
        CharSequence text;
        CharSequence title;

        private StandardTemplateParams() {
            this.hasProgress = true;
            this.maxRemoteInputHistory = 3;
            this.allowColorization = true;
            this.forceDefaultColor = false;
        }

        final StandardTemplateParams reset() {
            this.hasProgress = true;
            this.title = null;
            this.text = null;
            this.summaryText = null;
            this.headerTextSecondary = null;
            this.maxRemoteInputHistory = 3;
            this.allowColorization = true;
            this.forceDefaultColor = false;
            return this;
        }

        final StandardTemplateParams hasProgress(boolean z) {
            this.hasProgress = z;
            return this;
        }

        final StandardTemplateParams title(CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }

        final StandardTemplateParams text(CharSequence charSequence) {
            this.text = charSequence;
            return this;
        }

        final StandardTemplateParams summaryText(CharSequence charSequence) {
            this.summaryText = charSequence;
            return this;
        }

        final StandardTemplateParams headerTextSecondary(CharSequence charSequence) {
            this.headerTextSecondary = charSequence;
            return this;
        }

        final StandardTemplateParams hideLargeIcon(boolean z) {
            this.hideLargeIcon = z;
            return this;
        }

        final StandardTemplateParams hideReplyIcon(boolean z) {
            this.hideReplyIcon = z;
            return this;
        }

        final StandardTemplateParams disallowColorization() {
            this.allowColorization = false;
            return this;
        }

        final StandardTemplateParams forceDefaultColor() {
            this.forceDefaultColor = true;
            return this;
        }

        final StandardTemplateParams fillTextsFrom(Builder builder) {
            Bundle bundle = builder.mN.extras;
            this.title = builder.processLegacyText(bundle.getCharSequence("android.title"));
            this.text = builder.processLegacyText(bundle.getCharSequence("android.text"));
            this.summaryText = bundle.getCharSequence("android.subText");
            return this;
        }

        public StandardTemplateParams setMaxRemoteInputHistory(int i) {
            this.maxRemoteInputHistory = i;
            return this;
        }
    }
}
