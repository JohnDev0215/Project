package com.amazon.music.views.library.recyclerview.adapters;

import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.providers.OnListItemRenderedListener;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentViewedProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/adapters/ContentViewedProvider;", "Lcom/amazon/music/views/library/providers/OnListItemRenderedListener;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "viewId", "", "(Lcom/amazon/music/uicontentview/ContentViewedListener;Ljava/lang/String;)V", "onListItemRendered", "", "position", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContentViewedProvider implements OnListItemRenderedListener {
    private final ContentViewedListener contentViewedListener;
    private String viewId;

    public ContentViewedProvider(ContentViewedListener contentViewedListener, String viewId) {
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        Intrinsics.checkNotNullParameter(viewId, "viewId");
        this.contentViewedListener = contentViewedListener;
        this.viewId = viewId;
    }

    @Override // com.amazon.music.views.library.providers.OnListItemRenderedListener
    public void onListItemRendered(int position) {
        String str = this.viewId;
        if (str == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("blockRef", str);
        this.contentViewedListener.onItemRenderedInSequence(str, position, map);
    }
}
