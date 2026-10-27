package com.amazon.mp3.detailpages;

import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.TableRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: BaseViewsDragDropManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0014B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ.\u0010\u0010\u001a\u00020\u000b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/detailpages/BaseViewsDragDropManager;", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/mp3/detailpages/BaseViewsDragDropManager$Listener;", "(Lcom/amazon/mp3/detailpages/BaseViewsDragDropManager$Listener;)V", "calculateRowNumberOffset", "", "models", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "reorderModel", "", "fromIndex", "toIndex", "dataModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "updateIndexes", "startIndex", "endIndex", "offset", "Listener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BaseViewsDragDropManager {
    private final Listener listener;

    /* JADX INFO: compiled from: BaseViewsDragDropManager.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/detailpages/BaseViewsDragDropManager$Listener;", "", "onModelsReordered", "", "from", "", "to", "newDataModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Listener {
        void onModelsReordered(int from, int to, PageGridViewModel newDataModel);
    }

    public BaseViewsDragDropManager() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public BaseViewsDragDropManager(Listener listener) {
        this.listener = listener;
    }

    public /* synthetic */ BaseViewsDragDropManager(Listener listener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : listener);
    }

    public final void reorderModel(int fromIndex, int toIndex, PageGridViewModel dataModel) {
        List<BaseViewModel> models;
        List<? extends BaseViewModel> mutableList;
        if (dataModel == null || (models = dataModel.getModels()) == null || (mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) models)) == null) {
            return;
        }
        int iCalculateRowNumberOffset = calculateRowNumberOffset(mutableList);
        BaseViewModel baseViewModel = mutableList.get(fromIndex);
        mutableList.remove(baseViewModel);
        mutableList.add(toIndex, baseViewModel);
        IntRange intRange = new IntRange(Math.min(fromIndex, toIndex), Math.max(fromIndex, toIndex));
        updateIndexes(mutableList, intRange.getFirst(), intRange.getLast() + 1, iCalculateRowNumberOffset);
        dataModel.updateModels(mutableList);
        Listener listener = this.listener;
        if (listener == null) {
            return;
        }
        listener.onModelsReordered(fromIndex, toIndex, dataModel);
    }

    private final void updateIndexes(List<? extends BaseViewModel> models, int startIndex, int endIndex, int offset) {
        while (startIndex < endIndex) {
            int i = startIndex + 1;
            BaseViewModel baseViewModel = models.get(startIndex);
            if (baseViewModel instanceof TableRowItemModel) {
                ((TableRowItemModel) baseViewModel).updateRowNumber(Integer.valueOf(startIndex - offset));
            }
            startIndex = i;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int calculateRowNumberOffset(java.util.List<? extends com.amazon.music.views.library.models.base.BaseViewModel> r6) {
        /*
            r5 = this;
            java.lang.String r0 = "models"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.util.Iterator r0 = r6.iterator()
            r1 = 0
            r2 = r1
        Lb:
            boolean r3 = r0.hasNext()
            r4 = -1
            if (r3 == 0) goto L20
            java.lang.Object r3 = r0.next()
            com.amazon.music.views.library.models.base.BaseViewModel r3 = (com.amazon.music.views.library.models.base.BaseViewModel) r3
            boolean r3 = r3 instanceof com.amazon.music.views.library.models.TableRowItemModel
            if (r3 == 0) goto L1d
            goto L21
        L1d:
            int r2 = r2 + 1
            goto Lb
        L20:
            r2 = r4
        L21:
            if (r2 == r4) goto L3e
            java.lang.Object r6 = r6.get(r2)
            boolean r0 = r6 instanceof com.amazon.music.views.library.models.TableRowItemModel
            if (r0 == 0) goto L2e
            com.amazon.music.views.library.models.TableRowItemModel r6 = (com.amazon.music.views.library.models.TableRowItemModel) r6
            goto L2f
        L2e:
            r6 = 0
        L2f:
            if (r6 != 0) goto L32
            goto L3e
        L32:
            java.lang.Integer r6 = r6.getRowNumber()
            if (r6 != 0) goto L39
            goto L3e
        L39:
            int r6 = r6.intValue()
            goto L3f
        L3e:
            r6 = r1
        L3f:
            if (r2 == r4) goto L43
            int r1 = r2 - r6
        L43:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.detailpages.BaseViewsDragDropManager.calculateRowNumberOffset(java.util.List):int");
    }
}
