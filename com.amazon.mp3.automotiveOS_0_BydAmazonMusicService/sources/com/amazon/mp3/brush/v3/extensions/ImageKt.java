package com.amazon.mp3.brush.v3.extensions;

import com.amazon.music.page.api.model.Image;
import com.amazon.music.page.api.model.ImageType;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Image.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\"\u001d\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"originalArtOrDefault", "Lcom/amazon/music/page/api/model/Image;", "", "getOriginalArtOrDefault", "(Ljava/util/List;)Lcom/amazon/music/page/api/model/Image;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ImageKt {
    public static final Image getOriginalArtOrDefault(List<? extends Image> list) {
        Object next;
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((Image) next).getType() == ImageType.ORIGINAL_ART) {
                break;
            }
        }
        Image image = (Image) next;
        return image == null ? (Image) CollectionsKt___CollectionsKt.firstOrNull((List) list) : image;
    }
}
