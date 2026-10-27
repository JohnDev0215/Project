package com.android.inputmethod.event;

import com.android.inputmethod.latin.settings.SettingsValues;

/* JADX INFO: loaded from: classes2.dex */
public class InputTransaction {
    public static final int SHIFT_NO_UPDATE = 0;
    public static final int SHIFT_UPDATE_LATER = 2;
    public static final int SHIFT_UPDATE_NOW = 1;
    public final Event mEvent;
    public final SettingsValues mSettingsValues;
    public final int mShiftState;
    public final int mSpaceState;
    public final long mTimestamp;
    private int mRequiredShiftUpdate = 0;
    private boolean mRequiresUpdateSuggestions = false;
    private boolean mDidAffectContents = false;
    private boolean mDidAutoCorrect = false;

    public InputTransaction(final SettingsValues settingsValues, final Event event, final long timestamp, final int spaceState, final int shiftState) {
        this.mSettingsValues = settingsValues;
        this.mEvent = event;
        this.mTimestamp = timestamp;
        this.mSpaceState = spaceState;
        this.mShiftState = shiftState;
    }

    public void requireShiftUpdate(final int updateType) {
        this.mRequiredShiftUpdate = Math.max(this.mRequiredShiftUpdate, updateType);
    }

    public int getRequiredShiftUpdate() {
        return this.mRequiredShiftUpdate;
    }

    public void setRequiresUpdateSuggestions() {
        this.mRequiresUpdateSuggestions = true;
    }

    public boolean requiresUpdateSuggestions() {
        return this.mRequiresUpdateSuggestions;
    }

    public void setDidAffectContents() {
        this.mDidAffectContents = true;
    }

    public boolean didAffectContents() {
        return this.mDidAffectContents;
    }

    public void setDidAutoCorrect() {
        this.mDidAutoCorrect = true;
    }

    public boolean didAutoCorrect() {
        return this.mDidAutoCorrect;
    }
}
