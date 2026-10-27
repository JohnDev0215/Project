package com.ts.android.carplay;

import com.ts.android.carplay.AudioResource;
import com.ts.android.carplay.ScreenResource;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayResourceManager {
    public static final int APPSTATE_NONE = 0;
    public static final int APPSTATE_PHONECALL = 2;
    public static final int APPSTATE_SPEECH = 1;
    public static final int APPSTATE_TURNBYTURN = 3;
    public static final int AUDIO = 2;
    public static final String BorrowID_AudioOff = "audioOff";
    public static final String BorrowID_BackupCamera = "backupCamera";
    public static final String BorrowID_DisplayOff = "displayOff";
    public static final String BorrowID_NativeVR = "nativeVR";
    public static final String BorrowID_PhoneCall = "phoneCall";
    public static final String BorrowID_Standby = "standby";
    public static final String BorrowID_UINotification = "uiNotification";
    public static final int CONSTRAINT_ANYTIME = 1;
    public static final int CONSTRAINT_INVALID = 0;
    public static final int CONSTRAINT_NEVER = 3;
    public static final int CONSTRAINT_USER = 2;
    public static final int ENTITY_ACCESSORY = 2;
    public static final int ENTITY_CONTROLLER = 1;
    public static final int ENTITY_NONE = 0;
    public static final int ENTITY_WAITING_AVAILABLE = 3;
    public static final int PRIORITY_INVALID = 0;
    public static final int PRIORITY_NICETOHAVE = 1;
    public static final int PRIORITY_USER = 2;
    public static final int SCREEN = 1;
    public static final int SPEECH_INVALID = 0;
    public static final int SPEECH_NONE = -1;
    public static final int SPEECH_RECOGNIZING = 2;
    public static final int SPEECH_SPEAKING = 1;
    public static final int STATUS_AVAILABLE = 1;
    public static final int STATUS_BUSY = 0;
    private static final String TAG = "CarPlayResourceManager";
    public static final int TRANSFER_BORROW = 3;
    public static final int TRANSFER_NONE = 0;
    public static final int TRANSFER_TAKE = 1;
    public static final int TRANSFER_UNBORROW = 4;
    public static final int TRANSFER_UNTAKE = 2;
    public static final int TRISTATE_FALSE = 0;
    public static final int TRISTATE_INVALID = -1;
    public static final int TRISTATE_TRUE = 1;
    AudioResource mAudioResource;
    NavigationFocus mNavigationFocus;
    PhoneCallFocus mPhoneCallFocus;
    ScreenResource mScreenResource;
    VoiceFocus mVoiceFocus;

    public static abstract class AppFocus {
        protected CarPlayNative mNative = null;

        public abstract int release() throws Exception;

        public abstract int request() throws Exception;
    }

    public static abstract class Resource {
        protected CarPlayNative mNative = null;

        public abstract int acquire(ResourceChange resourceChange) throws Exception;

        public abstract int available() throws Exception;

        public abstract int release(int i) throws Exception;
    }

    public AudioResource audioResource() {
        if (this.mAudioResource == null) {
            this.mAudioResource = new AudioResource();
        }
        return this.mAudioResource;
    }

    public int changeAppState(int speechMode, boolean phoneCall, boolean turnByTurn) {
        return CarPlayNative.getInstance().changeAppState(speechMode, phoneCall, turnByTurn);
    }

    public int changeResourceStates(AudioResource.Change audioChange, ScreenResource.Change screenChange, AppStateChange appStateChange) {
        return 1;
    }

    public NavigationFocus navigationFocus() {
        if (this.mNavigationFocus == null) {
            this.mNavigationFocus = new NavigationFocus();
        }
        return this.mNavigationFocus;
    }

    public PhoneCallFocus phoneFocus() {
        if (this.mPhoneCallFocus == null) {
            this.mPhoneCallFocus = new PhoneCallFocus();
        }
        return this.mPhoneCallFocus;
    }

    public ScreenResource screenResource() {
        if (this.mScreenResource == null) {
            this.mScreenResource = new ScreenResource();
        }
        return this.mScreenResource;
    }

    public VoiceFocus voiceFocus() {
        if (this.mVoiceFocus == null) {
            this.mVoiceFocus = new VoiceFocus();
        }
        return this.mVoiceFocus;
    }

    public static final class AppStateChange {
        public int mPhone;
        public int mSpeech;
        public int mTbt;

        public AppStateChange(int phoneActive, int turnByturnActive, int speechMode) {
            this.mPhone = phoneActive;
            this.mTbt = turnByturnActive;
            this.mSpeech = speechMode;
        }
    }

    public static class PermanentResource {
        public int mBorrowConstraint;
        public int mPermanentEntity;
        public int mTakeConstraint;

        public PermanentResource() {
            this.mPermanentEntity = 1;
            this.mTakeConstraint = 0;
            this.mBorrowConstraint = 0;
        }

        public PermanentResource(int permanentEntity, int takeConstraint, int borrowConstraint) {
            this.mPermanentEntity = permanentEntity;
            if (1 == permanentEntity) {
                LogUtil.info(CarPlayResourceManager.TAG, "mPermanentEntity is ENTITY_CONTROLLER, not need Constraint");
                this.mTakeConstraint = 0;
                this.mBorrowConstraint = 0;
            } else {
                this.mTakeConstraint = takeConstraint;
                this.mBorrowConstraint = borrowConstraint;
            }
        }

        public PermanentResource(PermanentResource that) {
            this.mPermanentEntity = that.mPermanentEntity;
            this.mTakeConstraint = that.mTakeConstraint;
            this.mBorrowConstraint = that.mBorrowConstraint;
        }
    }

    public static class ResourceChange {
        public String mBorrowID;
        public int mBorrowOrUnborrowConstraint;
        public int mTakeConstraint;
        public int mTransferPriority;
        public int mTransferType;

        public ResourceChange() {
            this.mTransferType = 0;
            this.mTransferPriority = 1;
            this.mTakeConstraint = 1;
            this.mBorrowOrUnborrowConstraint = 1;
            this.mBorrowID = "";
        }

        public ResourceChange(int transferType, int transferPriority, int takeConstraint, int borrowOrUnborrowConstraint, String borrowID) {
            this.mTransferType = transferType;
            this.mTransferPriority = transferPriority;
            this.mTakeConstraint = takeConstraint;
            this.mBorrowOrUnborrowConstraint = borrowOrUnborrowConstraint;
            this.mBorrowID = borrowID;
        }

        public ResourceChange(ResourceChange res) {
            this.mTransferType = res.mTransferType;
            this.mTransferPriority = res.mTransferPriority;
            this.mTakeConstraint = res.mTakeConstraint;
            this.mBorrowOrUnborrowConstraint = res.mBorrowOrUnborrowConstraint;
            this.mBorrowID = res.mBorrowID;
        }
    }
}
