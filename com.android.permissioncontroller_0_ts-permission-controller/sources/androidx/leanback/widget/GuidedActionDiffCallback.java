package androidx.leanback.widget;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public class GuidedActionDiffCallback extends DiffCallback<GuidedAction> {
    static final GuidedActionDiffCallback sInstance = new GuidedActionDiffCallback();

    public static GuidedActionDiffCallback getInstance() {
        return sInstance;
    }

    @Override // androidx.leanback.widget.DiffCallback
    public boolean areItemsTheSame(GuidedAction oldItem, GuidedAction newItem) {
        return oldItem == null ? newItem == null : newItem != null && oldItem.getId() == newItem.getId();
    }

    @Override // androidx.leanback.widget.DiffCallback
    public boolean areContentsTheSame(GuidedAction oldItem, GuidedAction newItem) {
        if (oldItem == null) {
            return newItem == null;
        }
        if (newItem == null) {
            return false;
        }
        return oldItem.getCheckSetId() == newItem.getCheckSetId() && oldItem.mActionFlags == newItem.mActionFlags && TextUtils.equals(oldItem.getTitle(), newItem.getTitle()) && TextUtils.equals(oldItem.getDescription(), newItem.getDescription()) && oldItem.getInputType() == newItem.getInputType() && TextUtils.equals(oldItem.getEditTitle(), newItem.getEditTitle()) && TextUtils.equals(oldItem.getEditDescription(), newItem.getEditDescription()) && oldItem.getEditInputType() == newItem.getEditInputType() && oldItem.getDescriptionEditInputType() == newItem.getDescriptionEditInputType();
    }
}
