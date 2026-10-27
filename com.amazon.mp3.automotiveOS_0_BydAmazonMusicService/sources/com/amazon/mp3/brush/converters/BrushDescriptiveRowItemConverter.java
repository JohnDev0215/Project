package com.amazon.mp3.brush.converters;

import com.amazon.mp3.baseviews.model.configuration.ModelConfiguration;
import com.amazon.mp3.cache.CollectionCache;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Track;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushDescriptiveRowItemConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B3\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r\u0012\u0012\b\u0002\u0010\u0012\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\"\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u00022\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0016R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u001e\u0010\u0012\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushDescriptiveRowItemConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "Lcom/amazon/musicensembleservice/brush/Track;", "item", "Lcom/amazon/music/views/library/models/DescriptiveRowItemModel;", "convertTrack", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "modelConfiguration", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "parentModelConfiguration", "Lcom/amazon/mp3/cache/CollectionCache;", "cache", "Lcom/amazon/mp3/cache/CollectionCache;", "<init>", "(Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;Lcom/amazon/mp3/cache/CollectionCache;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushDescriptiveRowItemConverter implements SingleBaseModelConverter<Entity> {
    private final CollectionCache<?, ?> cache;
    private final ModelConfiguration modelConfiguration;
    private final ModelConfiguration parentModelConfiguration;

    public BrushDescriptiveRowItemConverter() {
        this(null, null, null, 7, null);
    }

    public BrushDescriptiveRowItemConverter(ModelConfiguration modelConfiguration, ModelConfiguration modelConfiguration2, CollectionCache<?, ?> collectionCache) {
        this.modelConfiguration = modelConfiguration;
        this.parentModelConfiguration = modelConfiguration2;
        this.cache = collectionCache;
    }

    public /* synthetic */ BrushDescriptiveRowItemConverter(ModelConfiguration modelConfiguration, ModelConfiguration modelConfiguration2, CollectionCache collectionCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : modelConfiguration, (i & 2) != 0 ? null : modelConfiguration2, (i & 4) != 0 ? null : collectionCache);
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convert(data, null);
    }

    public BaseViewModel convert(Entity data, List<? extends ContentMetadata> metadataList) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Track) {
            return convertTrack((Track) data);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x034f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0105 A[PHI: r7
  0x0105: PHI (r7v8 java.lang.String) = (r7v7 java.lang.String), (r7v42 java.lang.String), (r7v45 java.lang.String) binds: [B:60:0x00ee, B:66:0x0101, B:63:0x00f4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0126 A[PHI: r7
  0x0126: PHI (r7v11 java.lang.String) = (r7v10 java.lang.String), (r7v36 java.lang.String), (r7v39 java.lang.String) binds: [B:71:0x010f, B:77:0x0122, B:74:0x0115] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.DescriptiveRowItemModel convertTrack(com.amazon.musicensembleservice.brush.Track r62) {
        /*
            Method dump skipped, instruction units count: 901
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.converters.BrushDescriptiveRowItemConverter.convertTrack(com.amazon.musicensembleservice.brush.Track):com.amazon.music.views.library.models.DescriptiveRowItemModel");
    }
}
