package org.chromium.content.browser;

import android.content.Intent;

/* JADX INFO: loaded from: classes3.dex */
public class ChildProcessCreationParams {
    private static final String EXTRA_LIBRARY_PROCESS_TYPE = "org.chromium.content.common.child_service_params.library_process_type";
    private static volatile ChildProcessCreationParams sChildProcessCreationParams;
    private final int mExtraBindFlags;
    private final int mLibraryProcessType;
    private final String mPackageName;

    public static void set(ChildProcessCreationParams childProcessCreationParams) {
        sChildProcessCreationParams = childProcessCreationParams;
    }

    public static ChildProcessCreationParams get() {
        return sChildProcessCreationParams;
    }

    public ChildProcessCreationParams(String str, int i, int i2) {
        this.mPackageName = str;
        this.mExtraBindFlags = i;
        this.mLibraryProcessType = i2;
    }

    public ChildProcessCreationParams copy() {
        return new ChildProcessCreationParams(this.mPackageName, this.mExtraBindFlags, this.mLibraryProcessType);
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public int getExtraBindFlags() {
        return this.mExtraBindFlags;
    }

    public int getLibraryProcessType() {
        return this.mLibraryProcessType;
    }

    public int addExtraBindFlags(int i) {
        return i | this.mExtraBindFlags;
    }

    public void addIntentExtras(Intent intent) {
        intent.putExtra(EXTRA_LIBRARY_PROCESS_TYPE, this.mLibraryProcessType);
    }

    public static int getLibraryProcessType(Intent intent) {
        return intent.getIntExtra(EXTRA_LIBRARY_PROCESS_TYPE, 2);
    }
}
