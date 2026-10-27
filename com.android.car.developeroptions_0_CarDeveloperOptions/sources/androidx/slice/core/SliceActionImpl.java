package androidx.slice.core;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.SliceItem;

/* JADX INFO: loaded from: classes.dex */
public class SliceActionImpl implements SliceAction {
    private PendingIntent mAction;
    private SliceItem mActionItem;
    private CharSequence mContentDescription;
    private IconCompat mIcon;
    private int mImageMode;
    private boolean mIsChecked;
    private boolean mIsToggle;
    private int mPriority;
    private SliceItem mSliceItem;
    private CharSequence mTitle;

    public void setActivity(boolean z) {
    }

    public SliceActionImpl(PendingIntent pendingIntent, IconCompat iconCompat, int i, CharSequence charSequence) {
        this.mImageMode = 5;
        this.mPriority = -1;
        this.mAction = pendingIntent;
        this.mIcon = iconCompat;
        this.mTitle = charSequence;
        this.mImageMode = i;
    }

    public SliceActionImpl(SliceItem sliceItem) {
        this.mImageMode = 5;
        this.mPriority = -1;
        this.mSliceItem = sliceItem;
        SliceItem sliceItemFind = SliceQuery.find(sliceItem, "action");
        if (sliceItemFind == null) {
            return;
        }
        this.mActionItem = sliceItemFind;
        this.mAction = sliceItemFind.getAction();
        SliceItem sliceItemFind2 = SliceQuery.find(sliceItemFind.getSlice(), "image");
        if (sliceItemFind2 != null) {
            this.mIcon = sliceItemFind2.getIcon();
            this.mImageMode = parseImageMode(sliceItemFind2);
        }
        SliceItem sliceItemFind3 = SliceQuery.find(sliceItemFind.getSlice(), "text", "title", (String) null);
        if (sliceItemFind3 != null) {
            this.mTitle = sliceItemFind3.getSanitizedText();
        }
        SliceItem sliceItemFindSubtype = SliceQuery.findSubtype(sliceItemFind.getSlice(), "text", "content_description");
        if (sliceItemFindSubtype != null) {
            this.mContentDescription = sliceItemFindSubtype.getText();
        }
        boolean zEquals = "toggle".equals(sliceItemFind.getSubType());
        this.mIsToggle = zEquals;
        if (zEquals) {
            this.mIsChecked = sliceItemFind.hasHint("selected");
        }
        this.mSliceItem.hasHint("activity");
        SliceItem sliceItemFindSubtype2 = SliceQuery.findSubtype(sliceItemFind.getSlice(), "int", "priority");
        this.mPriority = sliceItemFindSubtype2 != null ? sliceItemFindSubtype2.getInt() : -1;
    }

    @Override // androidx.slice.core.SliceAction
    public PendingIntent getAction() {
        PendingIntent pendingIntent = this.mAction;
        return pendingIntent != null ? pendingIntent : this.mActionItem.getAction();
    }

    public SliceItem getActionItem() {
        return this.mActionItem;
    }

    @Override // androidx.slice.core.SliceAction
    public IconCompat getIcon() {
        return this.mIcon;
    }

    @Override // androidx.slice.core.SliceAction
    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    @Override // androidx.slice.core.SliceAction
    public int getPriority() {
        return this.mPriority;
    }

    @Override // androidx.slice.core.SliceAction
    public boolean isToggle() {
        return this.mIsToggle;
    }

    public boolean isChecked() {
        return this.mIsChecked;
    }

    @Override // androidx.slice.core.SliceAction
    public int getImageMode() {
        return this.mImageMode;
    }

    public boolean isDefaultToggle() {
        return this.mIsToggle && this.mIcon == null;
    }

    public SliceItem getSliceItem() {
        return this.mSliceItem;
    }

    public static int parseImageMode(SliceItem sliceItem) {
        if (sliceItem.hasHint("no_tint")) {
            return sliceItem.hasHint("raw") ? sliceItem.hasHint("large") ? 4 : 3 : sliceItem.hasHint("large") ? 2 : 1;
        }
        return 0;
    }
}
