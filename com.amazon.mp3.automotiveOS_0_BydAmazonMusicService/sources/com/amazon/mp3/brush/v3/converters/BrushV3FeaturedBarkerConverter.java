package com.amazon.mp3.brush.v3.converters;

import com.amazon.music.page.api.model.Barker;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3FeaturedBarkerConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3FeaturedBarkerConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/page/api/model/Entity;", "Lcom/amazon/music/page/api/model/Barker;", "barker", "Lcom/amazon/music/views/library/models/FeatureBarkerModel;", "convertBarker", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3FeaturedBarkerConverter implements SingleBaseModelConverter<Entity> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data.getBarker() == null) {
            return null;
        }
        Barker barker = data.getBarker();
        Intrinsics.checkNotNullExpressionValue(barker, "data.barker");
        return convertBarker(barker);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.FeatureBarkerModel convertBarker(com.amazon.music.page.api.model.Barker r14) {
        /*
            r13 = this;
            java.lang.String r1 = r14.getBlockRef()
            java.lang.String r2 = r14.getTarget()
            java.lang.String r8 = r14.getAltDescription()
            java.util.List r0 = r14.getImages()
            r3 = 1
            r4 = 0
            java.lang.String r5 = "Collection contains no element matching the predicate."
            r6 = 0
            if (r0 != 0) goto L19
        L17:
            r7 = r6
            goto L42
        L19:
            java.util.Iterator r0 = r0.iterator()
        L1d:
            boolean r7 = r0.hasNext()
            if (r7 == 0) goto L8c
            java.lang.Object r7 = r0.next()
            com.amazon.music.page.api.model.Image r7 = (com.amazon.music.page.api.model.Image) r7
            if (r7 != 0) goto L2d
            r9 = r6
            goto L31
        L2d:
            com.amazon.music.page.api.model.ImageType r9 = r7.getType()
        L31:
            com.amazon.music.page.api.model.ImageType r10 = com.amazon.music.page.api.model.ImageType.FOUR_BY_ONE
            if (r9 != r10) goto L37
            r9 = r3
            goto L38
        L37:
            r9 = r4
        L38:
            if (r9 == 0) goto L1d
            if (r7 != 0) goto L3d
            goto L17
        L3d:
            java.lang.String r0 = r7.getSrc()
            r7 = r0
        L42:
            java.util.List r14 = r14.getImages()
            if (r14 != 0) goto L4a
        L48:
            r5 = r6
            goto L73
        L4a:
            java.util.Iterator r14 = r14.iterator()
        L4e:
            boolean r0 = r14.hasNext()
            if (r0 == 0) goto L86
            java.lang.Object r0 = r14.next()
            com.amazon.music.page.api.model.Image r0 = (com.amazon.music.page.api.model.Image) r0
            if (r0 != 0) goto L5e
            r9 = r6
            goto L62
        L5e:
            com.amazon.music.page.api.model.ImageType r9 = r0.getType()
        L62:
            com.amazon.music.page.api.model.ImageType r10 = com.amazon.music.page.api.model.ImageType.SIXTEEN_BY_NINE
            if (r9 != r10) goto L68
            r9 = r3
            goto L69
        L68:
            r9 = r4
        L69:
            if (r9 == 0) goto L4e
            if (r0 != 0) goto L6e
            goto L48
        L6e:
            java.lang.String r14 = r0.getSrc()
            r5 = r14
        L73:
            com.amazon.music.views.library.models.FeatureBarkerModel r14 = new com.amazon.music.views.library.models.FeatureBarkerModel
            r3 = 0
            r4 = 0
            r9 = 0
            r10 = 0
            r11 = 332(0x14c, float:4.65E-43)
            r12 = 0
            r0 = r14
            r6 = r7
            r7 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return r14
        L86:
            java.util.NoSuchElementException r14 = new java.util.NoSuchElementException
            r14.<init>(r5)
            throw r14
        L8c:
            java.util.NoSuchElementException r14 = new java.util.NoSuchElementException
            r14.<init>(r5)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.v3.converters.BrushV3FeaturedBarkerConverter.convertBarker(com.amazon.music.page.api.model.Barker):com.amazon.music.views.library.models.FeatureBarkerModel");
    }
}
