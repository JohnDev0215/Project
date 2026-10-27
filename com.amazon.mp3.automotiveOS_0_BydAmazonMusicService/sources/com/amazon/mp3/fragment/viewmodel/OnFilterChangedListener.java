package com.amazon.mp3.fragment.viewmodel;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OnFilterChangedListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "", "downloadSelectionChanged", "", "selected", "", "shouldOverrideResetFilters", "shouldOverrideSelectionChange", "filterType", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface OnFilterChangedListener {

    /* JADX INFO: compiled from: OnFilterChangedListener.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void downloadSelectionChanged(OnFilterChangedListener onFilterChangedListener, boolean z) {
            Intrinsics.checkNotNullParameter(onFilterChangedListener, "this");
        }

        public static boolean shouldOverrideResetFilters(OnFilterChangedListener onFilterChangedListener) {
            Intrinsics.checkNotNullParameter(onFilterChangedListener, "this");
            return false;
        }

        public static boolean shouldOverrideSelectionChange(OnFilterChangedListener onFilterChangedListener, String filterType, boolean z) {
            Intrinsics.checkNotNullParameter(onFilterChangedListener, "this");
            Intrinsics.checkNotNullParameter(filterType, "filterType");
            return false;
        }
    }

    void downloadSelectionChanged(boolean selected);

    boolean shouldOverrideResetFilters();

    boolean shouldOverrideSelectionChange(String filterType, boolean selected);
}
