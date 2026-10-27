package com.amazon.music.curate.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ViewType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UiContentViewFragmentCallbacks.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J$\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/curate/view/UiContentViewFragmentCallbacks;", "Lcom/amazon/music/curate/view/FragmentCallbacks;", "fragment", "Landroidx/fragment/app/Fragment;", "pageViewId", "", "itemId", "itemIdType", "(Landroidx/fragment/app/Fragment;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getScreenOrientation", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ViewType;", "context", "Landroid/content/Context;", "onCreateView", "", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UiContentViewFragmentCallbacks extends FragmentCallbacks {
    private final String itemId;
    private final String itemIdType;
    private final String pageViewId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UiContentViewFragmentCallbacks(Fragment fragment, String pageViewId, String itemId, String itemIdType) {
        super(fragment);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pageViewId, "pageViewId");
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Intrinsics.checkNotNullParameter(itemIdType, "itemIdType");
        this.pageViewId = pageViewId;
        this.itemId = itemId;
        this.itemIdType = itemIdType;
    }

    @Override // com.amazon.music.curate.view.FragmentCallbacks
    public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(this.pageViewId).withViewType(getScreenOrientation(getFragment().getContext())).withDetailPageItemId(this.itemId).withDetailPageItemIdType(this.itemIdType).build());
    }

    private final ViewType getScreenOrientation(Context context) {
        if (context == null) {
            return null;
        }
        int i = context.getResources().getConfiguration().orientation;
        if (i == 0) {
            return ViewType.UNDEFINED;
        }
        if (i == 1) {
            return ViewType.PORTRAIT;
        }
        if (i == 2) {
            return ViewType.LANDSCAPE;
        }
        if (i != 3) {
            return null;
        }
        return ViewType.SQUARE;
    }
}
