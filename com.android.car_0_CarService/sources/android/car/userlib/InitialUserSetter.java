package android.car.userlib;

import android.R;
import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.Context;
import android.content.pm.UserInfo;
import android.os.IProgressListener;
import android.os.RemoteException;
import android.os.UserManager;
import android.provider.Settings;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.util.Pair;
import android.util.Slog;
import android.util.TimingsTraceLog;
import com.android.internal.util.Preconditions;
import com.android.internal.widget.LockPatternUtils;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public final class InitialUserSetter {
    private static final boolean DBG = false;
    private static final String TAG = InitialUserSetter.class.getSimpleName();
    public static final int TYPE_CREATE = 2;
    public static final int TYPE_DEFAULT_BEHAVIOR = 0;
    public static final int TYPE_REPLACE_GUEST = 3;
    public static final int TYPE_SWITCH = 1;
    private final Context mContext;
    private final CarUserManagerHelper mHelper;
    private final Consumer<UserInfo> mListener;
    private final LockPatternUtils mLockPatternUtils;
    private final String mNewGuestName;
    private final String mNewUserName;
    private final UserManager mUm;

    @Retention(RetentionPolicy.SOURCE)
    public @interface InitialUserInfoType {
    }

    public InitialUserSetter(Context context, Consumer<UserInfo> listener) {
        this(context, listener, null);
    }

    public InitialUserSetter(Context context, Consumer<UserInfo> listener, String newGuestName) {
        this(context, new CarUserManagerHelper(context), UserManager.get(context), listener, new LockPatternUtils(context), context.getString(R.string.keyguard_password_wrong_pin_code), newGuestName);
    }

    public InitialUserSetter(Context context, CarUserManagerHelper helper, UserManager um, Consumer<UserInfo> listener, LockPatternUtils lockPatternUtils, String newUserName, String newGuestName) {
        this.mContext = context;
        this.mHelper = helper;
        this.mUm = um;
        this.mListener = listener;
        this.mLockPatternUtils = lockPatternUtils;
        this.mNewUserName = newUserName;
        this.mNewGuestName = newGuestName;
    }

    public static final class Builder {
        private int mNewUserFlags;
        private String mNewUserName;
        private boolean mReplaceGuest;
        private boolean mSupportsOverrideUserIdProperty;
        private int mSwitchUserId;
        private final int mType;
        private String mUserLocales;

        public Builder(int type) {
            boolean z = true;
            if (type != 0 && type != 1 && type != 2 && type != 3) {
                z = false;
            }
            Preconditions.checkArgument(z, "invalid builder type");
            this.mType = type;
        }

        public Builder setSwitchUserId(int userId) {
            Preconditions.checkArgument(this.mType == 1, "invalid builder type: " + this.mType);
            this.mSwitchUserId = userId;
            return this;
        }

        public Builder setReplaceGuest(boolean value) {
            this.mReplaceGuest = value;
            return this;
        }

        public Builder setNewUserName(String name) {
            Preconditions.checkArgument(this.mType == 2, "invalid builder type: " + this.mType);
            this.mNewUserName = name;
            return this;
        }

        public Builder setNewUserFlags(int flags) {
            Preconditions.checkArgument(this.mType == 2, "invalid builder type: " + this.mType);
            this.mNewUserFlags = flags;
            return this;
        }

        public Builder setSupportsOverrideUserIdProperty(boolean value) {
            this.mSupportsOverrideUserIdProperty = value;
            return this;
        }

        public Builder setUserLocales(String userLocales) {
            this.mUserLocales = userLocales;
            return this;
        }

        public InitialUserInfo build() {
            return new InitialUserInfo(this);
        }
    }

    public static final class InitialUserInfo {
        public final int newUserFlags;
        public final String newUserName;
        public final boolean replaceGuest;
        public final boolean supportsOverrideUserIdProperty;
        public final int switchUserId;
        public final int type;
        public String userLocales;

        private InitialUserInfo(Builder builder) {
            this.type = builder.mType;
            this.switchUserId = builder.mSwitchUserId;
            this.replaceGuest = builder.mReplaceGuest;
            this.newUserName = builder.mNewUserName;
            this.newUserFlags = builder.mNewUserFlags;
            this.supportsOverrideUserIdProperty = builder.mSupportsOverrideUserIdProperty;
            this.userLocales = builder.mUserLocales;
        }
    }

    public void set(InitialUserInfo info) {
        Preconditions.checkArgument(info != null, "info cannot be null");
        int i = info.type;
        if (i == 0) {
            executeDefaultBehavior(info, false);
            return;
        }
        if (i == 1) {
            try {
                switchUser(info, true);
                return;
            } catch (Exception e) {
                fallbackDefaultBehavior(info, true, "Exception switching user: " + e);
                return;
            }
        }
        if (i == 2) {
            try {
                createAndSwitchUser(info, true);
                return;
            } catch (Exception e2) {
                fallbackDefaultBehavior(info, true, "Exception createUser user with name " + UserHelper.safeName(info.newUserName) + " and flags " + UserHalHelper.userFlagsToString(info.newUserFlags) + ": " + e2);
                return;
            }
        }
        if (i == 3) {
            try {
                replaceUser(info, true);
                return;
            } catch (Exception e3) {
                fallbackDefaultBehavior(info, true, "Exception replace guest user: " + e3);
                return;
            }
        }
        throw new IllegalArgumentException("invalid InitialUserInfo type: " + info.type);
    }

    private void replaceUser(InitialUserInfo info, boolean fallback) {
        int currentUserId = ActivityManager.getCurrentUser();
        UserInfo currentUser = this.mUm.getUserInfo(currentUserId);
        UserInfo newUser = replaceGuestIfNeeded(currentUser);
        if (newUser == null) {
            fallbackDefaultBehavior(info, fallback, "could not replace guest " + currentUser.toFullString());
            return;
        }
        switchUser(new Builder(1).setSwitchUserId(newUser.id).build(), fallback);
        if (newUser.id != currentUser.id) {
            Slog.i(TAG, "Removing old guest " + currentUser.id);
            if (!this.mUm.removeUser(currentUser.id)) {
                Slog.w(TAG, "Could not remove old guest " + currentUser.id);
            }
        }
    }

    private void executeDefaultBehavior(InitialUserInfo info, boolean fallback) {
        if (!this.mHelper.hasInitialUser()) {
            createAndSwitchUser(new Builder(2).setNewUserName(this.mNewUserName).setNewUserFlags(8).setSupportsOverrideUserIdProperty(info.supportsOverrideUserIdProperty).setUserLocales(info.userLocales).build(), fallback);
        } else {
            int userId = this.mHelper.getInitialUser(info.supportsOverrideUserIdProperty);
            switchUser(new Builder(1).setSwitchUserId(userId).setSupportsOverrideUserIdProperty(info.supportsOverrideUserIdProperty).setReplaceGuest(info.replaceGuest).build(), fallback);
        }
    }

    void fallbackDefaultBehavior(InitialUserInfo info, boolean fallback, String reason) {
        if (!fallback) {
            Log.w(TAG, reason);
            notifyListener(null);
            return;
        }
        Log.w(TAG, "Falling back to default behavior. Reason: " + reason);
        executeDefaultBehavior(info, false);
    }

    private void switchUser(InitialUserInfo info, boolean fallback) {
        int userId = info.switchUserId;
        boolean replaceGuest = info.replaceGuest;
        UserInfo user = this.mUm.getUserInfo(userId);
        if (user == null) {
            fallbackDefaultBehavior(info, fallback, "user with id " + userId + " doesn't exist");
            return;
        }
        UserInfo actualUser = user;
        if (user.isGuest() && replaceGuest && (actualUser = replaceGuestIfNeeded(user)) == null) {
            fallbackDefaultBehavior(info, fallback, "could not replace guest " + user.toFullString());
            return;
        }
        int actualUserId = actualUser.id;
        unlockSystemUserIfNecessary(actualUserId);
        int currentUserId = ActivityManager.getCurrentUser();
        if (actualUserId != currentUserId) {
            if (!startForegroundUser(actualUserId)) {
                fallbackDefaultBehavior(info, fallback, "am.switchUser(" + actualUserId + ") failed");
                return;
            }
            this.mHelper.setLastActiveUser(actualUserId);
        }
        notifyListener(actualUser);
        if (actualUserId != userId) {
            Slog.i(TAG, "Removing old guest " + userId);
            if (!this.mUm.removeUser(userId)) {
                Slog.w(TAG, "Could not remove old guest " + userId);
            }
        }
    }

    private void unlockSystemUserIfNecessary(int userId) {
        if (userId != 0) {
            unlockSystemUser();
        }
    }

    public boolean canReplaceGuestUser(UserInfo user) {
        return user.isGuest() && !this.mLockPatternUtils.isSecure(user.id);
    }

    UserInfo replaceGuestIfNeeded(UserInfo user) {
        Preconditions.checkArgument(user != null, "user cannot be null");
        if (!canReplaceGuestUser(user)) {
            return user;
        }
        Log.i(TAG, "Replacing guest (" + user.toFullString() + ")");
        int halFlags = 2;
        if (user.isEphemeral()) {
            halFlags = 2 | 4;
        } else {
            Log.w(TAG, "guest being replaced is not ephemeral: " + user.toFullString());
        }
        if (!this.mUm.markGuestForDeletion(user.id)) {
            Log.w(TAG, "failed to mark guest " + user.id + " for deletion");
        }
        Pair<UserInfo, String> result = createNewUser(new Builder(2).setNewUserName(this.mNewGuestName).setNewUserFlags(halFlags).build());
        String errorMessage = (String) result.second;
        if (errorMessage != null) {
            Log.w(TAG, "could not replace guest " + user.toFullString() + ": " + errorMessage);
            return null;
        }
        return (UserInfo) result.first;
    }

    private void createAndSwitchUser(InitialUserInfo info, boolean fallback) {
        Pair<UserInfo, String> result = createNewUser(info);
        String reason = (String) result.second;
        if (reason != null) {
            fallbackDefaultBehavior(info, fallback, reason);
        } else {
            switchUser(new Builder(1).setSwitchUserId(((UserInfo) result.first).id).setSupportsOverrideUserIdProperty(info.supportsOverrideUserIdProperty).build(), fallback);
        }
    }

    private Pair<UserInfo, String> createNewUser(InitialUserInfo info) {
        String name = info.newUserName;
        int halFlags = info.newUserFlags;
        if (UserHalHelper.isSystem(halFlags)) {
            return new Pair<>(null, "Cannot create system user");
        }
        if (UserHalHelper.isAdmin(halFlags)) {
            boolean validAdmin = true;
            if (UserHalHelper.isGuest(halFlags)) {
                Log.w(TAG, "Cannot create guest admin");
                validAdmin = false;
            }
            if (UserHalHelper.isEphemeral(halFlags)) {
                Log.w(TAG, "Cannot create ephemeral admin");
                validAdmin = false;
            }
            if (!validAdmin) {
                return new Pair<>(null, "Invalid flags for admin user");
            }
        }
        int flags = UserHalHelper.toUserInfoFlags(halFlags);
        String type = UserHalHelper.isGuest(halFlags) ? "android.os.usertype.full.GUEST" : "android.os.usertype.full.SECONDARY";
        UserInfo userInfo = this.mUm.createUser(name, type, flags);
        if (userInfo == null) {
            return new Pair<>(null, "createUser(name=" + UserHelper.safeName(name) + ", flags=" + UserHalHelper.userFlagsToString(halFlags) + "): failed to create user");
        }
        if (info.userLocales != null) {
            Settings.System.putStringForUser(this.mContext.getContentResolver(), "system_locales", info.userLocales, userInfo.id);
        }
        return new Pair<>(userInfo, null);
    }

    void unlockSystemUser() {
        Log.i(TAG, "unlocking system user");
        IActivityManager am = ActivityManager.getService();
        TimingsTraceLog t = new TimingsTraceLog(TAG, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED);
        t.traceBegin("UnlockSystemUser");
        try {
            try {
                t.traceBegin("am.startUser");
                boolean started = am.startUserInBackground(0);
                t.traceEnd();
                if (!started) {
                    Log.w(TAG, "could not restart system user in foreground; trying unlock instead");
                    t.traceBegin("am.unlockUser");
                    boolean unlocked = am.unlockUser(0, (byte[]) null, (byte[]) null, (IProgressListener) null);
                    t.traceEnd();
                    if (!unlocked) {
                        Log.w(TAG, "could not unlock system user neither");
                    }
                }
            } catch (RemoteException e) {
                Log.wtf("RemoteException from AMS", e);
            }
        } finally {
            t.traceEnd();
        }
    }

    boolean startForegroundUser(int userId) {
        if (UserHelper.isHeadlessSystemUser(userId)) {
            return false;
        }
        try {
            return ActivityManager.getService().startUserInForegroundWithListener(userId, (IProgressListener) null);
        } catch (RemoteException e) {
            Log.w(TAG, "failed to start user " + userId, e);
            return false;
        }
    }

    private void notifyListener(UserInfo initialUser) {
        this.mListener.accept(initialUser);
    }

    public void dump(PrintWriter writer) {
        writer.println("InitialUserSetter");
        writer.printf("%smNewUserName: %s\n", "  ", this.mNewUserName);
        writer.printf("%smNewGuestName: %s\n", "  ", this.mNewGuestName);
    }
}
