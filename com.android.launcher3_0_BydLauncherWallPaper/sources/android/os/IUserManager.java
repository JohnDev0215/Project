package android.os;

import android.content.IntentSender;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.os.IUserRestrictionsListener;
import android.os.UserManager;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IUserManager extends IInterface {

    public static class Default implements IUserManager {
        @Override // android.os.IUserManager
        public void addUserRestrictionsListener(IUserRestrictionsListener iUserRestrictionsListener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IUserManager
        public boolean canAddMoreManagedProfiles(int i, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean canAddMoreProfilesToUser(String str, int i, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean canHaveRestrictedProfile(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public void clearSeedAccountData() throws RemoteException {
        }

        @Override // android.os.IUserManager
        public UserInfo createProfileForUserEvenWhenDisallowedWithThrow(String str, String str2, int i, int i2, String[] strArr) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public UserInfo createProfileForUserWithThrow(String str, String str2, int i, int i2, String[] strArr) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public UserInfo createRestrictedProfileWithThrow(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public UserInfo createUserWithThrow(String str, String str2, int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public void evictCredentialEncryptionKey(int i) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public UserInfo findCurrentGuestUser() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public Bundle getApplicationRestrictions(String str) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public Bundle getApplicationRestrictionsForUser(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public int getCredentialOwnerProfile(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public Bundle getDefaultGuestRestrictions() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public UserInfo getPrimaryUser() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public int[] getProfileIds(int i, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public UserInfo getProfileParent(int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public int getProfileParentId(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public List<UserInfo> getProfiles(int i, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public String getSeedAccountName() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public PersistableBundle getSeedAccountOptions() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public String getSeedAccountType() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public String getUserAccount(int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public int getUserBadgeColorResId(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public int getUserBadgeDarkColorResId(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public int getUserBadgeLabelResId(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public int getUserBadgeNoBackgroundResId(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public int getUserBadgeResId(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public long getUserCreationTime(int i) throws RemoteException {
            return 0L;
        }

        @Override // android.os.IUserManager
        public int getUserHandle(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public ParcelFileDescriptor getUserIcon(int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public int getUserIconBadgeResId(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public UserInfo getUserInfo(int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public String getUserName() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public int getUserRestrictionSource(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public List<UserManager.EnforcingUser> getUserRestrictionSources(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public Bundle getUserRestrictions(int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public int getUserSerialNumber(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public long getUserStartRealtime() throws RemoteException {
            return 0L;
        }

        @Override // android.os.IUserManager
        public long getUserUnlockRealtime() throws RemoteException {
            return 0L;
        }

        @Override // android.os.IUserManager
        public List<UserInfo> getUsers(boolean z, boolean z2, boolean z3) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public boolean hasBadge(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean hasBaseUserRestriction(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean hasRestrictedProfiles() throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean hasUserRestriction(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean hasUserRestrictionOnAnyUser(String str) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isDemoUser(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isManagedProfile(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isPreCreated(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isProfile(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isQuietModeEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isRestricted() throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isSameProfileGroup(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isSettingRestrictedForUser(String str, int i, String str2, int i2) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isUserNameSet(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isUserOfType(int i, String str) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isUserRunning(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isUserUnlocked(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isUserUnlockingOrUnlocked(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean markGuestForDeletion(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public UserInfo preCreateUserWithThrow(String str) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public boolean removeUser(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean removeUserEvenWhenDisallowed(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean requestQuietModeEnabled(String str, boolean z, int i, IntentSender intentSender, int i2) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public void setApplicationRestrictions(String str, Bundle bundle, int i) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void setDefaultGuestRestrictions(Bundle bundle) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void setSeedAccountData(int i, String str, String str2, PersistableBundle persistableBundle, boolean z) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void setUserAccount(int i, String str) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void setUserAdmin(int i) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void setUserEnabled(int i) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void setUserIcon(int i, Bitmap bitmap) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void setUserName(int i, String str) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void setUserRestriction(String str, boolean z, int i) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public boolean someUserHasSeedAccount(String str, String str2) throws RemoteException {
            return false;
        }
    }

    void addUserRestrictionsListener(IUserRestrictionsListener iUserRestrictionsListener) throws RemoteException;

    boolean canAddMoreManagedProfiles(int i, boolean z) throws RemoteException;

    boolean canAddMoreProfilesToUser(String str, int i, boolean z) throws RemoteException;

    boolean canHaveRestrictedProfile(int i) throws RemoteException;

    void clearSeedAccountData() throws RemoteException;

    UserInfo createProfileForUserEvenWhenDisallowedWithThrow(String str, String str2, int i, int i2, String[] strArr) throws RemoteException;

    UserInfo createProfileForUserWithThrow(String str, String str2, int i, int i2, String[] strArr) throws RemoteException;

    UserInfo createRestrictedProfileWithThrow(String str, int i) throws RemoteException;

    UserInfo createUserWithThrow(String str, String str2, int i) throws RemoteException;

    void evictCredentialEncryptionKey(int i) throws RemoteException;

    UserInfo findCurrentGuestUser() throws RemoteException;

    Bundle getApplicationRestrictions(String str) throws RemoteException;

    Bundle getApplicationRestrictionsForUser(String str, int i) throws RemoteException;

    int getCredentialOwnerProfile(int i) throws RemoteException;

    Bundle getDefaultGuestRestrictions() throws RemoteException;

    UserInfo getPrimaryUser() throws RemoteException;

    int[] getProfileIds(int i, boolean z) throws RemoteException;

    UserInfo getProfileParent(int i) throws RemoteException;

    int getProfileParentId(int i) throws RemoteException;

    List<UserInfo> getProfiles(int i, boolean z) throws RemoteException;

    String getSeedAccountName() throws RemoteException;

    PersistableBundle getSeedAccountOptions() throws RemoteException;

    String getSeedAccountType() throws RemoteException;

    String getUserAccount(int i) throws RemoteException;

    int getUserBadgeColorResId(int i) throws RemoteException;

    int getUserBadgeDarkColorResId(int i) throws RemoteException;

    int getUserBadgeLabelResId(int i) throws RemoteException;

    int getUserBadgeNoBackgroundResId(int i) throws RemoteException;

    int getUserBadgeResId(int i) throws RemoteException;

    long getUserCreationTime(int i) throws RemoteException;

    int getUserHandle(int i) throws RemoteException;

    ParcelFileDescriptor getUserIcon(int i) throws RemoteException;

    int getUserIconBadgeResId(int i) throws RemoteException;

    UserInfo getUserInfo(int i) throws RemoteException;

    String getUserName() throws RemoteException;

    int getUserRestrictionSource(String str, int i) throws RemoteException;

    List<UserManager.EnforcingUser> getUserRestrictionSources(String str, int i) throws RemoteException;

    Bundle getUserRestrictions(int i) throws RemoteException;

    int getUserSerialNumber(int i) throws RemoteException;

    long getUserStartRealtime() throws RemoteException;

    long getUserUnlockRealtime() throws RemoteException;

    List<UserInfo> getUsers(boolean z, boolean z2, boolean z3) throws RemoteException;

    boolean hasBadge(int i) throws RemoteException;

    boolean hasBaseUserRestriction(String str, int i) throws RemoteException;

    boolean hasRestrictedProfiles() throws RemoteException;

    boolean hasUserRestriction(String str, int i) throws RemoteException;

    boolean hasUserRestrictionOnAnyUser(String str) throws RemoteException;

    boolean isDemoUser(int i) throws RemoteException;

    boolean isManagedProfile(int i) throws RemoteException;

    boolean isPreCreated(int i) throws RemoteException;

    boolean isProfile(int i) throws RemoteException;

    boolean isQuietModeEnabled(int i) throws RemoteException;

    boolean isRestricted() throws RemoteException;

    boolean isSameProfileGroup(int i, int i2) throws RemoteException;

    boolean isSettingRestrictedForUser(String str, int i, String str2, int i2) throws RemoteException;

    boolean isUserNameSet(int i) throws RemoteException;

    boolean isUserOfType(int i, String str) throws RemoteException;

    boolean isUserRunning(int i) throws RemoteException;

    boolean isUserUnlocked(int i) throws RemoteException;

    boolean isUserUnlockingOrUnlocked(int i) throws RemoteException;

    boolean markGuestForDeletion(int i) throws RemoteException;

    UserInfo preCreateUserWithThrow(String str) throws RemoteException;

    boolean removeUser(int i) throws RemoteException;

    boolean removeUserEvenWhenDisallowed(int i) throws RemoteException;

    boolean requestQuietModeEnabled(String str, boolean z, int i, IntentSender intentSender, int i2) throws RemoteException;

    void setApplicationRestrictions(String str, Bundle bundle, int i) throws RemoteException;

    void setDefaultGuestRestrictions(Bundle bundle) throws RemoteException;

    void setSeedAccountData(int i, String str, String str2, PersistableBundle persistableBundle, boolean z) throws RemoteException;

    void setUserAccount(int i, String str) throws RemoteException;

    void setUserAdmin(int i) throws RemoteException;

    void setUserEnabled(int i) throws RemoteException;

    void setUserIcon(int i, Bitmap bitmap) throws RemoteException;

    void setUserName(int i, String str) throws RemoteException;

    void setUserRestriction(String str, boolean z, int i) throws RemoteException;

    boolean someUserHasSeedAccount(String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IUserManager {
        private static final String DESCRIPTOR = "android.os.IUserManager";
        static final int TRANSACTION_addUserRestrictionsListener = 39;
        static final int TRANSACTION_canAddMoreManagedProfiles = 20;
        static final int TRANSACTION_canAddMoreProfilesToUser = 19;
        static final int TRANSACTION_canHaveRestrictedProfile = 29;
        static final int TRANSACTION_clearSeedAccountData = 53;
        static final int TRANSACTION_createProfileForUserEvenWhenDisallowedWithThrow = 59;
        static final int TRANSACTION_createProfileForUserWithThrow = 5;
        static final int TRANSACTION_createRestrictedProfileWithThrow = 6;
        static final int TRANSACTION_createUserWithThrow = 3;
        static final int TRANSACTION_evictCredentialEncryptionKey = 9;
        static final int TRANSACTION_findCurrentGuestUser = 47;
        static final int TRANSACTION_getApplicationRestrictions = 42;
        static final int TRANSACTION_getApplicationRestrictionsForUser = 43;
        static final int TRANSACTION_getCredentialOwnerProfile = 1;
        static final int TRANSACTION_getDefaultGuestRestrictions = 45;
        static final int TRANSACTION_getPrimaryUser = 15;
        static final int TRANSACTION_getProfileIds = 18;
        static final int TRANSACTION_getProfileParent = 21;
        static final int TRANSACTION_getProfileParentId = 2;
        static final int TRANSACTION_getProfiles = 17;
        static final int TRANSACTION_getSeedAccountName = 50;
        static final int TRANSACTION_getSeedAccountOptions = 52;
        static final int TRANSACTION_getSeedAccountType = 51;
        static final int TRANSACTION_getUserAccount = 25;
        static final int TRANSACTION_getUserBadgeColorResId = 65;
        static final int TRANSACTION_getUserBadgeDarkColorResId = 66;
        static final int TRANSACTION_getUserBadgeLabelResId = 64;
        static final int TRANSACTION_getUserBadgeNoBackgroundResId = 63;
        static final int TRANSACTION_getUserBadgeResId = 62;
        static final int TRANSACTION_getUserCreationTime = 27;
        static final int TRANSACTION_getUserHandle = 31;
        static final int TRANSACTION_getUserIcon = 14;
        static final int TRANSACTION_getUserIconBadgeResId = 61;
        static final int TRANSACTION_getUserInfo = 24;
        static final int TRANSACTION_getUserName = 73;
        static final int TRANSACTION_getUserRestrictionSource = 32;
        static final int TRANSACTION_getUserRestrictionSources = 33;
        static final int TRANSACTION_getUserRestrictions = 34;
        static final int TRANSACTION_getUserSerialNumber = 30;
        static final int TRANSACTION_getUserStartRealtime = 74;
        static final int TRANSACTION_getUserUnlockRealtime = 75;
        static final int TRANSACTION_getUsers = 16;
        static final int TRANSACTION_hasBadge = 67;
        static final int TRANSACTION_hasBaseUserRestriction = 35;
        static final int TRANSACTION_hasRestrictedProfiles = 71;
        static final int TRANSACTION_hasUserRestriction = 36;
        static final int TRANSACTION_hasUserRestrictionOnAnyUser = 37;
        static final int TRANSACTION_isDemoUser = 57;
        static final int TRANSACTION_isManagedProfile = 56;
        static final int TRANSACTION_isPreCreated = 58;
        static final int TRANSACTION_isProfile = 55;
        static final int TRANSACTION_isQuietModeEnabled = 48;
        static final int TRANSACTION_isRestricted = 28;
        static final int TRANSACTION_isSameProfileGroup = 22;
        static final int TRANSACTION_isSettingRestrictedForUser = 38;
        static final int TRANSACTION_isUserNameSet = 70;
        static final int TRANSACTION_isUserOfType = 23;
        static final int TRANSACTION_isUserRunning = 69;
        static final int TRANSACTION_isUserUnlocked = 68;
        static final int TRANSACTION_isUserUnlockingOrUnlocked = 60;
        static final int TRANSACTION_markGuestForDeletion = 46;
        static final int TRANSACTION_preCreateUserWithThrow = 4;
        static final int TRANSACTION_removeUser = 10;
        static final int TRANSACTION_removeUserEvenWhenDisallowed = 11;
        static final int TRANSACTION_requestQuietModeEnabled = 72;
        static final int TRANSACTION_setApplicationRestrictions = 41;
        static final int TRANSACTION_setDefaultGuestRestrictions = 44;
        static final int TRANSACTION_setSeedAccountData = 49;
        static final int TRANSACTION_setUserAccount = 26;
        static final int TRANSACTION_setUserAdmin = 8;
        static final int TRANSACTION_setUserEnabled = 7;
        static final int TRANSACTION_setUserIcon = 13;
        static final int TRANSACTION_setUserName = 12;
        static final int TRANSACTION_setUserRestriction = 40;
        static final int TRANSACTION_someUserHasSeedAccount = 54;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getCredentialOwnerProfile";
                case 2:
                    return "getProfileParentId";
                case 3:
                    return "createUserWithThrow";
                case 4:
                    return "preCreateUserWithThrow";
                case 5:
                    return "createProfileForUserWithThrow";
                case 6:
                    return "createRestrictedProfileWithThrow";
                case 7:
                    return "setUserEnabled";
                case 8:
                    return "setUserAdmin";
                case 9:
                    return "evictCredentialEncryptionKey";
                case 10:
                    return "removeUser";
                case 11:
                    return "removeUserEvenWhenDisallowed";
                case 12:
                    return "setUserName";
                case 13:
                    return "setUserIcon";
                case 14:
                    return "getUserIcon";
                case 15:
                    return "getPrimaryUser";
                case 16:
                    return "getUsers";
                case 17:
                    return "getProfiles";
                case 18:
                    return "getProfileIds";
                case 19:
                    return "canAddMoreProfilesToUser";
                case 20:
                    return "canAddMoreManagedProfiles";
                case 21:
                    return "getProfileParent";
                case 22:
                    return "isSameProfileGroup";
                case 23:
                    return "isUserOfType";
                case 24:
                    return "getUserInfo";
                case 25:
                    return "getUserAccount";
                case 26:
                    return "setUserAccount";
                case 27:
                    return "getUserCreationTime";
                case 28:
                    return "isRestricted";
                case 29:
                    return "canHaveRestrictedProfile";
                case 30:
                    return "getUserSerialNumber";
                case 31:
                    return "getUserHandle";
                case 32:
                    return "getUserRestrictionSource";
                case 33:
                    return "getUserRestrictionSources";
                case 34:
                    return "getUserRestrictions";
                case 35:
                    return "hasBaseUserRestriction";
                case 36:
                    return "hasUserRestriction";
                case 37:
                    return "hasUserRestrictionOnAnyUser";
                case 38:
                    return "isSettingRestrictedForUser";
                case 39:
                    return "addUserRestrictionsListener";
                case 40:
                    return "setUserRestriction";
                case 41:
                    return "setApplicationRestrictions";
                case 42:
                    return "getApplicationRestrictions";
                case 43:
                    return "getApplicationRestrictionsForUser";
                case 44:
                    return "setDefaultGuestRestrictions";
                case 45:
                    return "getDefaultGuestRestrictions";
                case 46:
                    return "markGuestForDeletion";
                case 47:
                    return "findCurrentGuestUser";
                case 48:
                    return "isQuietModeEnabled";
                case 49:
                    return "setSeedAccountData";
                case 50:
                    return "getSeedAccountName";
                case 51:
                    return "getSeedAccountType";
                case 52:
                    return "getSeedAccountOptions";
                case 53:
                    return "clearSeedAccountData";
                case 54:
                    return "someUserHasSeedAccount";
                case 55:
                    return "isProfile";
                case 56:
                    return "isManagedProfile";
                case 57:
                    return "isDemoUser";
                case 58:
                    return "isPreCreated";
                case 59:
                    return "createProfileForUserEvenWhenDisallowedWithThrow";
                case 60:
                    return "isUserUnlockingOrUnlocked";
                case 61:
                    return "getUserIconBadgeResId";
                case 62:
                    return "getUserBadgeResId";
                case 63:
                    return "getUserBadgeNoBackgroundResId";
                case 64:
                    return "getUserBadgeLabelResId";
                case 65:
                    return "getUserBadgeColorResId";
                case 66:
                    return "getUserBadgeDarkColorResId";
                case 67:
                    return "hasBadge";
                case 68:
                    return "isUserUnlocked";
                case 69:
                    return "isUserRunning";
                case 70:
                    return "isUserNameSet";
                case 71:
                    return "hasRestrictedProfiles";
                case 72:
                    return "requestQuietModeEnabled";
                case 73:
                    return "getUserName";
                case 74:
                    return "getUserStartRealtime";
                case 75:
                    return "getUserUnlockRealtime";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUserManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IUserManager)) {
                return (IUserManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    int credentialOwnerProfile = getCredentialOwnerProfile(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(credentialOwnerProfile);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int profileParentId = getProfileParentId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(profileParentId);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserInfo userInfoCreateUserWithThrow = createUserWithThrow(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (userInfoCreateUserWithThrow != null) {
                        parcel2.writeInt(1);
                        userInfoCreateUserWithThrow.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserInfo userInfoPreCreateUserWithThrow = preCreateUserWithThrow(parcel.readString());
                    parcel2.writeNoException();
                    if (userInfoPreCreateUserWithThrow != null) {
                        parcel2.writeInt(1);
                        userInfoPreCreateUserWithThrow.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserInfo userInfoCreateProfileForUserWithThrow = createProfileForUserWithThrow(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    if (userInfoCreateProfileForUserWithThrow != null) {
                        parcel2.writeInt(1);
                        userInfoCreateProfileForUserWithThrow.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserInfo userInfoCreateRestrictedProfileWithThrow = createRestrictedProfileWithThrow(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (userInfoCreateRestrictedProfileWithThrow != null) {
                        parcel2.writeInt(1);
                        userInfoCreateRestrictedProfileWithThrow.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUserEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUserAdmin(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    evictCredentialEncryptionKey(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveUser = removeUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveUser ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveUserEvenWhenDisallowed = removeUserEvenWhenDisallowed(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveUserEvenWhenDisallowed ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUserName(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUserIcon(parcel.readInt(), parcel.readInt() != 0 ? Bitmap.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor userIcon = getUserIcon(parcel.readInt());
                    parcel2.writeNoException();
                    if (userIcon != null) {
                        parcel2.writeInt(1);
                        userIcon.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserInfo primaryUser = getPrimaryUser();
                    parcel2.writeNoException();
                    if (primaryUser != null) {
                        parcel2.writeInt(1);
                        primaryUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<UserInfo> users = getUsers(parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(users);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<UserInfo> profiles = getProfiles(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(profiles);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] profileIds = getProfileIds(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeIntArray(profileIds);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanAddMoreProfilesToUser = canAddMoreProfilesToUser(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanAddMoreProfilesToUser ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanAddMoreManagedProfiles = canAddMoreManagedProfiles(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanAddMoreManagedProfiles ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserInfo profileParent = getProfileParent(parcel.readInt());
                    parcel2.writeNoException();
                    if (profileParent != null) {
                        parcel2.writeInt(1);
                        profileParent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSameProfileGroup = isSameProfileGroup(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSameProfileGroup ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsUserOfType = isUserOfType(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUserOfType ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserInfo userInfo = getUserInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (userInfo != null) {
                        parcel2.writeInt(1);
                        userInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    String userAccount = getUserAccount(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(userAccount);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUserAccount(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    long userCreationTime = getUserCreationTime(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(userCreationTime);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsRestricted = isRestricted();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsRestricted ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanHaveRestrictedProfile = canHaveRestrictedProfile(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanHaveRestrictedProfile ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    int userSerialNumber = getUserSerialNumber(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(userSerialNumber);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    int userHandle = getUserHandle(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(userHandle);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    int userRestrictionSource = getUserRestrictionSource(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(userRestrictionSource);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<UserManager.EnforcingUser> userRestrictionSources = getUserRestrictionSources(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(userRestrictionSources);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle userRestrictions = getUserRestrictions(parcel.readInt());
                    parcel2.writeNoException();
                    if (userRestrictions != null) {
                        parcel2.writeInt(1);
                        userRestrictions.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasBaseUserRestriction = hasBaseUserRestriction(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasBaseUserRestriction ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasUserRestriction = hasUserRestriction(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasUserRestriction ? 1 : 0);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasUserRestrictionOnAnyUser = hasUserRestrictionOnAnyUser(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasUserRestrictionOnAnyUser ? 1 : 0);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSettingRestrictedForUser = isSettingRestrictedForUser(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSettingRestrictedForUser ? 1 : 0);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    addUserRestrictionsListener(IUserRestrictionsListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUserRestriction(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    setApplicationRestrictions(parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle applicationRestrictions = getApplicationRestrictions(parcel.readString());
                    parcel2.writeNoException();
                    if (applicationRestrictions != null) {
                        parcel2.writeInt(1);
                        applicationRestrictions.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle applicationRestrictionsForUser = getApplicationRestrictionsForUser(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (applicationRestrictionsForUser != null) {
                        parcel2.writeInt(1);
                        applicationRestrictionsForUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDefaultGuestRestrictions(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle defaultGuestRestrictions = getDefaultGuestRestrictions();
                    parcel2.writeNoException();
                    if (defaultGuestRestrictions != null) {
                        parcel2.writeInt(1);
                        defaultGuestRestrictions.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zMarkGuestForDeletion = markGuestForDeletion(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zMarkGuestForDeletion ? 1 : 0);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserInfo userInfoFindCurrentGuestUser = findCurrentGuestUser();
                    parcel2.writeNoException();
                    if (userInfoFindCurrentGuestUser != null) {
                        parcel2.writeInt(1);
                        userInfoFindCurrentGuestUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsQuietModeEnabled = isQuietModeEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsQuietModeEnabled ? 1 : 0);
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSeedAccountData(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? PersistableBundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    String seedAccountName = getSeedAccountName();
                    parcel2.writeNoException();
                    parcel2.writeString(seedAccountName);
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    String seedAccountType = getSeedAccountType();
                    parcel2.writeNoException();
                    parcel2.writeString(seedAccountType);
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    PersistableBundle seedAccountOptions = getSeedAccountOptions();
                    parcel2.writeNoException();
                    if (seedAccountOptions != null) {
                        parcel2.writeInt(1);
                        seedAccountOptions.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearSeedAccountData();
                    parcel2.writeNoException();
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSomeUserHasSeedAccount = someUserHasSeedAccount(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zSomeUserHasSeedAccount ? 1 : 0);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsProfile = isProfile(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsProfile ? 1 : 0);
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsManagedProfile = isManagedProfile(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsManagedProfile ? 1 : 0);
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDemoUser = isDemoUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDemoUser ? 1 : 0);
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPreCreated = isPreCreated(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPreCreated ? 1 : 0);
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserInfo userInfoCreateProfileForUserEvenWhenDisallowedWithThrow = createProfileForUserEvenWhenDisallowedWithThrow(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    if (userInfoCreateProfileForUserEvenWhenDisallowedWithThrow != null) {
                        parcel2.writeInt(1);
                        userInfoCreateProfileForUserEvenWhenDisallowedWithThrow.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsUserUnlockingOrUnlocked = isUserUnlockingOrUnlocked(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUserUnlockingOrUnlocked ? 1 : 0);
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    int userIconBadgeResId = getUserIconBadgeResId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(userIconBadgeResId);
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    int userBadgeResId = getUserBadgeResId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(userBadgeResId);
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    int userBadgeNoBackgroundResId = getUserBadgeNoBackgroundResId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(userBadgeNoBackgroundResId);
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    int userBadgeLabelResId = getUserBadgeLabelResId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(userBadgeLabelResId);
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    int userBadgeColorResId = getUserBadgeColorResId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(userBadgeColorResId);
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    int userBadgeDarkColorResId = getUserBadgeDarkColorResId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(userBadgeDarkColorResId);
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasBadge = hasBadge(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasBadge ? 1 : 0);
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsUserUnlocked = isUserUnlocked(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUserUnlocked ? 1 : 0);
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsUserRunning = isUserRunning(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUserRunning ? 1 : 0);
                    return true;
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsUserNameSet = isUserNameSet(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUserNameSet ? 1 : 0);
                    return true;
                case 71:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasRestrictedProfiles = hasRestrictedProfiles();
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasRestrictedProfiles ? 1 : 0);
                    return true;
                case 72:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestQuietModeEnabled = requestQuietModeEnabled(parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0 ? IntentSender.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestQuietModeEnabled ? 1 : 0);
                    return true;
                case 73:
                    parcel.enforceInterface(DESCRIPTOR);
                    String userName = getUserName();
                    parcel2.writeNoException();
                    parcel2.writeString(userName);
                    return true;
                case 74:
                    parcel.enforceInterface(DESCRIPTOR);
                    long userStartRealtime = getUserStartRealtime();
                    parcel2.writeNoException();
                    parcel2.writeLong(userStartRealtime);
                    return true;
                case 75:
                    parcel.enforceInterface(DESCRIPTOR);
                    long userUnlockRealtime = getUserUnlockRealtime();
                    parcel2.writeNoException();
                    parcel2.writeLong(userUnlockRealtime);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IUserManager {
            public static IUserManager sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.os.IUserManager
            public int getCredentialOwnerProfile(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentialOwnerProfile(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getProfileParentId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProfileParentId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo createUserWithThrow(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createUserWithThrow(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo preCreateUserWithThrow(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().preCreateUserWithThrow(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo createProfileForUserWithThrow(String str, String str2, int i, int i2, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createProfileForUserWithThrow(str, str2, i, i2, strArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo createRestrictedProfileWithThrow(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createRestrictedProfileWithThrow(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setUserEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUserEnabled(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setUserAdmin(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUserAdmin(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void evictCredentialEncryptionKey(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().evictCredentialEncryptionKey(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean removeUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean removeUserEvenWhenDisallowed(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeUserEvenWhenDisallowed(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setUserName(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUserName(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setUserIcon(int i, Bitmap bitmap) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (bitmap != null) {
                        parcelObtain.writeInt(1);
                        bitmap.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUserIcon(i, bitmap);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public ParcelFileDescriptor getUserIcon(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserIcon(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo getPrimaryUser() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPrimaryUser();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public List<UserInfo> getUsers(boolean z, boolean z2, boolean z3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (!z3) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUsers(z, z2, z3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(UserInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public List<UserInfo> getProfiles(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProfiles(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(UserInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int[] getProfileIds(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProfileIds(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean canAddMoreProfilesToUser(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canAddMoreProfilesToUser(str, i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean canAddMoreManagedProfiles(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canAddMoreManagedProfiles(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo getProfileParent(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProfileParent(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isSameProfileGroup(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSameProfileGroup(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isUserOfType(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserOfType(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo getUserInfo(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserInfo(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public String getUserAccount(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserAccount(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setUserAccount(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUserAccount(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public long getUserCreationTime(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserCreationTime(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isRestricted() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRestricted();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean canHaveRestrictedProfile(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canHaveRestrictedProfile(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserSerialNumber(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserSerialNumber(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserHandle(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserHandle(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserRestrictionSource(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserRestrictionSource(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public List<UserManager.EnforcingUser> getUserRestrictionSources(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserRestrictionSources(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(UserManager.EnforcingUser.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public Bundle getUserRestrictions(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserRestrictions(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean hasBaseUserRestriction(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasBaseUserRestriction(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean hasUserRestriction(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasUserRestriction(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean hasUserRestrictionOnAnyUser(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasUserRestrictionOnAnyUser(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isSettingRestrictedForUser(String str, int i, String str2, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSettingRestrictedForUser(str, i, str2, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void addUserRestrictionsListener(IUserRestrictionsListener iUserRestrictionsListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iUserRestrictionsListener != null ? iUserRestrictionsListener.asBinder() : null);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addUserRestrictionsListener(iUserRestrictionsListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setUserRestriction(String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUserRestriction(str, z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setApplicationRestrictions(String str, Bundle bundle, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setApplicationRestrictions(str, bundle, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public Bundle getApplicationRestrictions(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApplicationRestrictions(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public Bundle getApplicationRestrictionsForUser(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApplicationRestrictionsForUser(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setDefaultGuestRestrictions(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDefaultGuestRestrictions(bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public Bundle getDefaultGuestRestrictions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultGuestRestrictions();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean markGuestForDeletion(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().markGuestForDeletion(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo findCurrentGuestUser() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().findCurrentGuestUser();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isQuietModeEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isQuietModeEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setSeedAccountData(int i, String str, String str2, PersistableBundle persistableBundle, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    int i2 = 1;
                    if (persistableBundle != null) {
                        parcelObtain.writeInt(1);
                        persistableBundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i2 = 0;
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSeedAccountData(i, str, str2, persistableBundle, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public String getSeedAccountName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSeedAccountName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public String getSeedAccountType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSeedAccountType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public PersistableBundle getSeedAccountOptions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSeedAccountOptions();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PersistableBundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void clearSeedAccountData() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearSeedAccountData();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean someUserHasSeedAccount(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().someUserHasSeedAccount(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isProfile(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isProfile(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isManagedProfile(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(56, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isManagedProfile(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isDemoUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(57, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDemoUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isPreCreated(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(58, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPreCreated(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo createProfileForUserEvenWhenDisallowedWithThrow(String str, String str2, int i, int i2, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(59, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createProfileForUserEvenWhenDisallowedWithThrow(str, str2, i, i2, strArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isUserUnlockingOrUnlocked(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(60, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserUnlockingOrUnlocked(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserIconBadgeResId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(61, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserIconBadgeResId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserBadgeResId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(62, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserBadgeResId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserBadgeNoBackgroundResId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(63, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserBadgeNoBackgroundResId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserBadgeLabelResId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(64, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserBadgeLabelResId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserBadgeColorResId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(65, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserBadgeColorResId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserBadgeDarkColorResId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(66, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserBadgeDarkColorResId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean hasBadge(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(67, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasBadge(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isUserUnlocked(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(68, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserUnlocked(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isUserRunning(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(69, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserRunning(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isUserNameSet(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(70, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserNameSet(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean hasRestrictedProfiles() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(71, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasRestrictedProfiles();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean requestQuietModeEnabled(String str, boolean z, int i, IntentSender intentSender, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (intentSender != null) {
                        parcelObtain.writeInt(1);
                        intentSender.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(72, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestQuietModeEnabled(str, z, i, intentSender, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public String getUserName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(73, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public long getUserStartRealtime() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(74, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserStartRealtime();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IUserManager
            public long getUserUnlockRealtime() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(75, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserUnlockRealtime();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUserManager iUserManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iUserManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iUserManager;
            return true;
        }

        public static IUserManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
