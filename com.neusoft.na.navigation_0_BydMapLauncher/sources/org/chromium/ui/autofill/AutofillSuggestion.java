package org.chromium.ui.autofill;

import org.chromium.ui.DropdownItem;

/* JADX INFO: loaded from: classes3.dex */
public class AutofillSuggestion implements DropdownItem {
    private final boolean mDeletable;
    private final int mIconId;
    private final boolean mIsMultilineLabel;
    private final String mLabel;
    private final String mSublabel;
    private final int mSuggestionId;

    @Override // org.chromium.ui.DropdownItem
    public boolean isEnabled() {
        return true;
    }

    @Override // org.chromium.ui.DropdownItem
    public boolean isGroupHeader() {
        return false;
    }

    public AutofillSuggestion(String str, String str2, int i, int i2, boolean z, boolean z2) {
        this.mLabel = str;
        this.mSublabel = str2;
        this.mIconId = i;
        this.mSuggestionId = i2;
        this.mDeletable = z;
        this.mIsMultilineLabel = z2;
    }

    @Override // org.chromium.ui.DropdownItem
    public String getLabel() {
        return this.mLabel;
    }

    @Override // org.chromium.ui.DropdownItem
    public String getSublabel() {
        return this.mSublabel;
    }

    @Override // org.chromium.ui.DropdownItem
    public int getIconId() {
        return this.mIconId;
    }

    @Override // org.chromium.ui.DropdownItem
    public boolean isMultilineLabel() {
        return this.mIsMultilineLabel;
    }

    public int getSuggestionId() {
        return this.mSuggestionId;
    }

    public boolean isDeletable() {
        return this.mDeletable;
    }

    public boolean isFillable() {
        return this.mSuggestionId >= 0;
    }
}
