package com.android.internal.os;

import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
class ZygoteArguments {
    boolean mAbiListQuery;
    String[] mApiBlacklistExemptions;
    String mAppDataDir;
    boolean mBindMountAppDataDirs;
    boolean mBindMountAppStorageDirs;
    boolean mBootCompleted;
    private boolean mCapabilitiesSpecified;
    long mEffectiveCapabilities;
    boolean mGidSpecified;
    int[] mGids;
    String mInstructionSet;
    String mInvokeWith;
    boolean mIsTopApp;
    String mNiceName;
    String mPackageName;
    long mPermittedCapabilities;
    boolean mPidQuery;
    String[] mPkgDataInfoList;
    String mPreloadApp;
    boolean mPreloadDefault;
    String mPreloadPackage;
    String mPreloadPackageCacheKey;
    String mPreloadPackageLibFileName;
    String mPreloadPackageLibs;
    ArrayList<int[]> mRLimits;
    String[] mRemainingArgs;
    int mRuntimeFlags;
    String mSeInfo;
    private boolean mSeInfoSpecified;
    boolean mStartChildZygote;
    int mTargetSdkVersion;
    private boolean mTargetSdkVersionSpecified;
    boolean mUidSpecified;
    boolean mUsapPoolEnabled;
    String[] mWhitelistedDataInfoList;
    int mUid = 0;
    int mGid = 0;
    int mMountExternal = 0;
    boolean mUsapPoolStatusSpecified = false;
    int mHiddenApiAccessLogSampleRate = -1;
    int mHiddenApiAccessStatslogSampleRate = -1;
    long[] mDisabledCompatChanges = null;

    ZygoteArguments(String[] strArr) throws IllegalArgumentException {
        parseArgs(strArr);
    }

    private void parseArgs(String[] strArr) throws IllegalArgumentException {
        boolean z = false;
        int length = 0;
        boolean z2 = false;
        boolean z3 = true;
        while (true) {
            if (length >= strArr.length) {
                break;
            }
            String str = strArr[length];
            if (str.equals("--")) {
                length++;
                break;
            }
            if (str.startsWith("--setuid=")) {
                if (this.mUidSpecified) {
                    throw new IllegalArgumentException("Duplicate arg specified");
                }
                this.mUidSpecified = true;
                this.mUid = Integer.parseInt(getAssignmentValue(str));
            } else if (str.startsWith("--setgid=")) {
                if (this.mGidSpecified) {
                    throw new IllegalArgumentException("Duplicate arg specified");
                }
                this.mGidSpecified = true;
                this.mGid = Integer.parseInt(getAssignmentValue(str));
            } else if (str.startsWith("--target-sdk-version=")) {
                if (this.mTargetSdkVersionSpecified) {
                    throw new IllegalArgumentException("Duplicate target-sdk-version specified");
                }
                this.mTargetSdkVersionSpecified = true;
                this.mTargetSdkVersion = Integer.parseInt(getAssignmentValue(str));
            } else if (str.equals("--runtime-args")) {
                z2 = true;
            } else if (str.startsWith("--runtime-flags=")) {
                this.mRuntimeFlags = Integer.parseInt(getAssignmentValue(str));
            } else if (str.startsWith("--seinfo=")) {
                if (this.mSeInfoSpecified) {
                    throw new IllegalArgumentException("Duplicate arg specified");
                }
                this.mSeInfoSpecified = true;
                this.mSeInfo = getAssignmentValue(str);
            } else if (str.startsWith("--capabilities=")) {
                if (this.mCapabilitiesSpecified) {
                    throw new IllegalArgumentException("Duplicate arg specified");
                }
                this.mCapabilitiesSpecified = true;
                String[] strArrSplit = getAssignmentValue(str).split(",", 2);
                if (strArrSplit.length == 1) {
                    long jLongValue = Long.decode(strArrSplit[0]).longValue();
                    this.mEffectiveCapabilities = jLongValue;
                    this.mPermittedCapabilities = jLongValue;
                } else {
                    this.mPermittedCapabilities = Long.decode(strArrSplit[0]).longValue();
                    this.mEffectiveCapabilities = Long.decode(strArrSplit[1]).longValue();
                }
            } else if (str.startsWith("--rlimit=")) {
                String[] assignmentList = getAssignmentList(str);
                if (assignmentList.length != 3) {
                    throw new IllegalArgumentException("--rlimit= should have 3 comma-delimited ints");
                }
                int[] iArr = new int[assignmentList.length];
                for (int i = 0; i < assignmentList.length; i++) {
                    iArr[i] = Integer.parseInt(assignmentList[i]);
                }
                if (this.mRLimits == null) {
                    this.mRLimits = new ArrayList<>();
                }
                this.mRLimits.add(iArr);
            } else if (str.startsWith("--setgroups=")) {
                if (this.mGids != null) {
                    throw new IllegalArgumentException("Duplicate arg specified");
                }
                String[] assignmentList2 = getAssignmentList(str);
                this.mGids = new int[assignmentList2.length];
                for (int length2 = assignmentList2.length - 1; length2 >= 0; length2--) {
                    this.mGids[length2] = Integer.parseInt(assignmentList2[length2]);
                }
            } else if (str.equals("--invoke-with")) {
                if (this.mInvokeWith != null) {
                    throw new IllegalArgumentException("Duplicate arg specified");
                }
                length++;
                try {
                    this.mInvokeWith = strArr[length];
                } catch (IndexOutOfBoundsException unused) {
                    throw new IllegalArgumentException("--invoke-with requires argument");
                }
            } else if (str.startsWith("--nice-name=")) {
                if (this.mNiceName != null) {
                    throw new IllegalArgumentException("Duplicate arg specified");
                }
                this.mNiceName = getAssignmentValue(str);
            } else if (str.equals("--mount-external-default")) {
                this.mMountExternal = 1;
            } else if (str.equals("--mount-external-read")) {
                this.mMountExternal = 2;
            } else if (str.equals("--mount-external-write")) {
                this.mMountExternal = 3;
            } else if (str.equals("--mount-external-full")) {
                this.mMountExternal = 6;
            } else if (str.equals("--mount-external-installer")) {
                this.mMountExternal = 5;
            } else if (str.equals("--mount-external-legacy")) {
                this.mMountExternal = 4;
            } else if (str.equals("--mount-external-pass-through")) {
                this.mMountExternal = 7;
            } else if (str.equals("--mount-external-android-writable")) {
                this.mMountExternal = 8;
            } else if (str.equals("--query-abi-list")) {
                this.mAbiListQuery = true;
            } else if (str.equals("--get-pid")) {
                this.mPidQuery = true;
            } else if (str.equals("--boot-completed")) {
                this.mBootCompleted = true;
            } else if (str.startsWith("--instruction-set=")) {
                this.mInstructionSet = getAssignmentValue(str);
            } else if (str.startsWith("--app-data-dir=")) {
                this.mAppDataDir = getAssignmentValue(str);
            } else if (str.equals("--preload-app")) {
                length++;
                this.mPreloadApp = strArr[length];
            } else if (str.equals("--preload-package")) {
                int i2 = length + 1;
                this.mPreloadPackage = strArr[i2];
                int i3 = i2 + 1;
                this.mPreloadPackageLibs = strArr[i3];
                int i4 = i3 + 1;
                this.mPreloadPackageLibFileName = strArr[i4];
                length = i4 + 1;
                this.mPreloadPackageCacheKey = strArr[length];
            } else {
                if (str.equals("--preload-default")) {
                    this.mPreloadDefault = true;
                } else if (str.equals("--start-child-zygote")) {
                    this.mStartChildZygote = true;
                } else if (str.equals("--set-api-blacklist-exemptions")) {
                    this.mApiBlacklistExemptions = (String[]) Arrays.copyOfRange(strArr, length + 1, strArr.length);
                    length = strArr.length;
                } else if (str.startsWith("--hidden-api-log-sampling-rate=")) {
                    String assignmentValue = getAssignmentValue(str);
                    try {
                        this.mHiddenApiAccessLogSampleRate = Integer.parseInt(assignmentValue);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid log sampling rate: " + assignmentValue, e);
                    }
                } else if (str.startsWith("--hidden-api-statslog-sampling-rate=")) {
                    String assignmentValue2 = getAssignmentValue(str);
                    try {
                        this.mHiddenApiAccessStatslogSampleRate = Integer.parseInt(assignmentValue2);
                    } catch (NumberFormatException e2) {
                        throw new IllegalArgumentException("Invalid statslog sampling rate: " + assignmentValue2, e2);
                    }
                } else if (str.startsWith("--package-name=")) {
                    if (this.mPackageName != null) {
                        throw new IllegalArgumentException("Duplicate arg specified");
                    }
                    this.mPackageName = getAssignmentValue(str);
                } else if (str.startsWith("--usap-pool-enabled=")) {
                    this.mUsapPoolStatusSpecified = true;
                    this.mUsapPoolEnabled = Boolean.parseBoolean(getAssignmentValue(str));
                } else if (str.startsWith(Zygote.START_AS_TOP_APP_ARG)) {
                    this.mIsTopApp = true;
                } else if (str.startsWith("--disabled-compat-changes=")) {
                    if (this.mDisabledCompatChanges != null) {
                        throw new IllegalArgumentException("Duplicate arg specified");
                    }
                    String[] assignmentList3 = getAssignmentList(str);
                    int length3 = assignmentList3.length;
                    this.mDisabledCompatChanges = new long[length3];
                    for (int i5 = 0; i5 < length3; i5++) {
                        this.mDisabledCompatChanges[i5] = Long.parseLong(assignmentList3[i5]);
                    }
                } else if (str.startsWith(Zygote.PKG_DATA_INFO_MAP)) {
                    this.mPkgDataInfoList = getAssignmentList(str);
                } else if (str.startsWith(Zygote.WHITELISTED_DATA_INFO_MAP)) {
                    this.mWhitelistedDataInfoList = getAssignmentList(str);
                } else if (str.equals(Zygote.BIND_MOUNT_APP_STORAGE_DIRS)) {
                    this.mBindMountAppStorageDirs = true;
                } else if (!str.equals(Zygote.BIND_MOUNT_APP_DATA_DIRS)) {
                    break;
                } else {
                    this.mBindMountAppDataDirs = true;
                }
                z3 = false;
            }
            length++;
        }
        if (this.mBootCompleted) {
            if (strArr.length - length > 0) {
                throw new IllegalArgumentException("Unexpected arguments after --boot-completed");
            }
        } else if (this.mAbiListQuery || this.mPidQuery) {
            if (strArr.length - length > 0) {
                throw new IllegalArgumentException("Unexpected arguments after --query-abi-list.");
            }
        } else if (this.mPreloadPackage != null) {
            if (strArr.length - length > 0) {
                throw new IllegalArgumentException("Unexpected arguments after --preload-package.");
            }
        } else if (this.mPreloadApp != null) {
            if (strArr.length - length > 0) {
                throw new IllegalArgumentException("Unexpected arguments after --preload-app.");
            }
        } else if (z3) {
            if (!z2) {
                throw new IllegalArgumentException("Unexpected argument : " + strArr[length]);
            }
            String[] strArr2 = new String[strArr.length - length];
            this.mRemainingArgs = strArr2;
            System.arraycopy(strArr, length, strArr2, 0, strArr2.length);
        }
        if (this.mStartChildZygote) {
            String[] strArr3 = this.mRemainingArgs;
            int length4 = strArr3.length;
            int i6 = 0;
            while (true) {
                if (i6 >= length4) {
                    break;
                }
                if (strArr3[i6].startsWith(Zygote.CHILD_ZYGOTE_SOCKET_NAME_ARG)) {
                    z = true;
                    break;
                }
                i6++;
            }
            if (!z) {
                throw new IllegalArgumentException("--start-child-zygote specified without --zygote-socket=");
            }
        }
    }

    private static String getAssignmentValue(String str) {
        return str.substring(str.indexOf(61) + 1);
    }

    private static String[] getAssignmentList(String str) {
        return getAssignmentValue(str).split(",");
    }
}
