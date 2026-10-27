package com.amazon.mp3.brush.converters;

import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.enums.RefinementListType;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Header;
import com.amazon.musicensembleservice.brush.NavigationEntity;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushHeaderConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushHeaderConverter;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushHeaderConverter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: BrushHeaderConverter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u0006H\u0002¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushHeaderConverter$Companion;", "", "()V", "convert", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "data", "Lcom/amazon/musicensembleservice/brush/Header;", "convertHeader", "header", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ExposedRefinementListModel convert(Header data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return convertHeader(data);
        }

        private final ExposedRefinementListModel convertHeader(Header header) {
            List<Block> blocks;
            Content content = header.getContent();
            if (content == null || (blocks = content.getBlocks()) == null) {
                return null;
            }
            for (Block block : blocks) {
                if (Intrinsics.areEqual(block.getRenderingType(), "PILL_NAVIGATOR")) {
                    ArrayList arrayList = new ArrayList();
                    ExposedRefinementListModel exposedRefinementListModel = new ExposedRefinementListModel(block.getBlockRef(), arrayList, null, RefinementListType.NAVIGATION, 4, null);
                    List<Entity> entities = block.getContent().getEntities();
                    Intrinsics.checkNotNullExpressionValue(entities, "block.content.entities");
                    for (Entity entity : entities) {
                        if (entity instanceof NavigationEntity) {
                            NavigationEntity navigationEntity = (NavigationEntity) entity;
                            String target = navigationEntity.getTarget();
                            String title = navigationEntity.getTitle();
                            Intrinsics.checkNotNullExpressionValue(title, "entity.title");
                            arrayList.add(new RefinementPillModel(navigationEntity.getBlockRef(), new FilterItemModel(target, title, null, false, navigationEntity.getBlockRef(), null, false, null, null, 356, null), false, null, exposedRefinementListModel, 8, null));
                        }
                    }
                    exposedRefinementListModel.setRefinementList(arrayList);
                    return exposedRefinementListModel;
                }
            }
            return null;
        }
    }
}
