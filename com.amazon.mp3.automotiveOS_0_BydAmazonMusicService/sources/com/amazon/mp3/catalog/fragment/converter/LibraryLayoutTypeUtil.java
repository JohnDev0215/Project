package com.amazon.mp3.catalog.fragment.converter;

import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import kotlin.Metadata;

/* JADX INFO: compiled from: LibraryLayoutTypeUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryLayoutTypeUtil;", "", "()V", "getLayoutType", "", "itemConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/library/item/AbstractItem;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryLayoutTypeUtil {
    public static final LibraryLayoutTypeUtil INSTANCE = new LibraryLayoutTypeUtil();

    private LibraryLayoutTypeUtil() {
    }

    public final int getLayoutType(SingleBaseModelConverter<AbstractItem> itemConverter) {
        if (itemConverter instanceof LibraryHorizontalTileConverter) {
            return 0;
        }
        return itemConverter instanceof LibraryDescriptiveRowItemConverter ? 18 : 2;
    }
}
