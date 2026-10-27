package com.amazon.music.views.library.click;

import android.view.View;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ClickListenerFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J*\u0010\u0002\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&¨\u0006\f"}, d2 = {"Lcom/amazon/music/views/library/click/ClickListenerFactory;", "", "create", "Landroid/view/View$OnClickListener;", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadataList", "", "position", "", "title", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ClickListenerFactory {
    View.OnClickListener create(ContentMetadata contentMetadata);

    View.OnClickListener create(List<? extends ContentMetadata> contentMetadataList, int position, String title);

    /* JADX INFO: compiled from: ClickListenerFactory.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ View.OnClickListener create$default(ClickListenerFactory clickListenerFactory, List list, int i, String str, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: create");
            }
            if ((i2 & 4) != 0) {
                str = null;
            }
            return clickListenerFactory.create(list, i, str);
        }
    }
}
