package com.amazon.mp3.brush.converters;

import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Barker;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.FeaturedBarker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushFeaturedBarkerConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0007H\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushFeaturedBarkerConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "Lcom/amazon/musicensembleservice/brush/Barker;", "barker", "Lcom/amazon/music/views/library/models/FeatureBarkerModel;", "convertBarker", "Lcom/amazon/musicensembleservice/brush/FeaturedBarker;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushFeaturedBarkerConverter implements SingleBaseModelConverter<Entity> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Barker) {
            return convertBarker((Barker) data);
        }
        if (data instanceof FeaturedBarker) {
            return convertBarker((FeaturedBarker) data);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.FeatureBarkerModel convertBarker(com.amazon.musicensembleservice.brush.Barker r13) {
        /*
            r12 = this;
            java.lang.String r1 = r13.getBlockRef()
            java.lang.String r2 = r13.getTarget()
            java.lang.String r8 = r13.getAltDescription()
            java.util.List r0 = r13.getImages()
            r3 = 2
            r4 = 0
            r5 = 0
            if (r0 != 0) goto L17
        L15:
            r6 = r5
            goto L4f
        L17:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r0 = r0.iterator()
        L20:
            boolean r7 = r0.hasNext()
            if (r7 == 0) goto L41
            java.lang.Object r7 = r0.next()
            r9 = r7
            com.amazon.musicensembleservice.brush.Image r9 = (com.amazon.musicensembleservice.brush.Image) r9
            if (r9 != 0) goto L31
            r9 = r5
            goto L35
        L31:
            java.lang.String r9 = r9.getType()
        L35:
            java.lang.String r10 = "4x1"
            boolean r9 = kotlin.text.StringsKt.equals$default(r9, r10, r4, r3, r5)
            if (r9 == 0) goto L20
            r6.add(r7)
            goto L20
        L41:
            java.lang.Object r0 = kotlin.collections.CollectionsKt.first(r6)
            com.amazon.musicensembleservice.brush.Image r0 = (com.amazon.musicensembleservice.brush.Image) r0
            if (r0 != 0) goto L4a
            goto L15
        L4a:
            java.lang.String r0 = r0.getUrl()
            r6 = r0
        L4f:
            java.util.List r13 = r13.getImages()
            if (r13 != 0) goto L56
            goto L8e
        L56:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r13 = r13.iterator()
        L5f:
            boolean r7 = r13.hasNext()
            if (r7 == 0) goto L80
            java.lang.Object r7 = r13.next()
            r9 = r7
            com.amazon.musicensembleservice.brush.Image r9 = (com.amazon.musicensembleservice.brush.Image) r9
            if (r9 != 0) goto L70
            r9 = r5
            goto L74
        L70:
            java.lang.String r9 = r9.getType()
        L74:
            java.lang.String r10 = "16x9"
            boolean r9 = kotlin.text.StringsKt.equals$default(r9, r10, r4, r3, r5)
            if (r9 == 0) goto L5f
            r0.add(r7)
            goto L5f
        L80:
            java.lang.Object r13 = kotlin.collections.CollectionsKt.first(r0)
            com.amazon.musicensembleservice.brush.Image r13 = (com.amazon.musicensembleservice.brush.Image) r13
            if (r13 != 0) goto L89
            goto L8e
        L89:
            java.lang.String r13 = r13.getUrl()
            r5 = r13
        L8e:
            com.amazon.music.views.library.models.FeatureBarkerModel r13 = new com.amazon.music.views.library.models.FeatureBarkerModel
            r3 = 0
            r4 = 0
            r7 = 0
            r9 = 0
            r10 = 332(0x14c, float:4.65E-43)
            r11 = 0
            r0 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.converters.BrushFeaturedBarkerConverter.convertBarker(com.amazon.musicensembleservice.brush.Barker):com.amazon.music.views.library.models.FeatureBarkerModel");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.FeatureBarkerModel convertBarker(com.amazon.musicensembleservice.brush.FeaturedBarker r26) {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.converters.BrushFeaturedBarkerConverter.convertBarker(com.amazon.musicensembleservice.brush.FeaturedBarker):com.amazon.music.views.library.models.FeatureBarkerModel");
    }
}
