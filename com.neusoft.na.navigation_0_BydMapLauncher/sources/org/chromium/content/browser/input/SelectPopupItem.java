package org.chromium.content.browser.input;

import org.chromium.ui.DropdownItem;

/* JADX INFO: loaded from: classes3.dex */
public class SelectPopupItem implements DropdownItem {
    private final String mLabel;
    private final int mType;

    @Override // org.chromium.ui.DropdownItem
    public int getIconId() {
        return 0;
    }

    @Override // org.chromium.ui.DropdownItem
    public String getSublabel() {
        return null;
    }

    @Override // org.chromium.ui.DropdownItem
    public boolean isMultilineLabel() {
        return false;
    }

    public SelectPopupItem(String str, int i) {
        this.mLabel = str;
        this.mType = i;
    }

    @Override // org.chromium.ui.DropdownItem
    public String getLabel() {
        return this.mLabel;
    }

    @Override // org.chromium.ui.DropdownItem
    public boolean isEnabled() {
        int i = this.mType;
        return i == 2 || i == 0;
    }

    @Override // org.chromium.ui.DropdownItem
    public boolean isGroupHeader() {
        return this.mType == 0;
    }

    public int getType() {
        return this.mType;
    }
}
