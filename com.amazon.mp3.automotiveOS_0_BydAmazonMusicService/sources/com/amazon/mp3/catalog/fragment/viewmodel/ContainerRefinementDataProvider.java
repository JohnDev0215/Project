package com.amazon.mp3.catalog.fragment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: RefinementDataProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J$\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\fH\u0002JX\u0010\r\u001a\u00020\t2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00102\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\f2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/ContainerRefinementDataProvider;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementDataProvider;", "()V", "createUnfilteredData", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "pageGridViewModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "swapFilterablesInPlace", "", "filteredModels", "pageLiveData", "Landroidx/lifecycle/MutableLiveData;", "updatePageGridViewModelWithFilteredResults", "filteredBaseViewModels", "filterableModelsContainer", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "pageData", "pageDescriptorModels", "appendedModels", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContainerRefinementDataProvider implements RefinementDataProvider {
    /* JADX WARN: Removed duplicated region for block: B:4:0x0005  */
    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinementDataProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.amazon.music.views.library.models.base.BaseViewModel> createUnfilteredData(com.amazon.music.views.library.models.PageGridViewModel r8) {
        /*
            r7 = this;
            r0 = 0
            r1 = 1
            r2 = 0
            if (r8 != 0) goto L7
        L5:
            r3 = r2
            goto L58
        L7:
            java.util.List r3 = r8.getModels()
            if (r3 != 0) goto Le
            goto L5
        Le:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r3 = r3.iterator()
        L17:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L29
            java.lang.Object r5 = r3.next()
            boolean r6 = r5 instanceof com.amazon.music.views.library.models.base.BaseViewContainerModel
            if (r6 == 0) goto L17
            r4.add(r5)
            goto L17
        L29:
            java.util.Iterator r3 = r4.iterator()
        L2d:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L4e
            java.lang.Object r4 = r3.next()
            r5 = r4
            com.amazon.music.views.library.models.base.BaseViewContainerModel r5 = (com.amazon.music.views.library.models.base.BaseViewContainerModel) r5
            java.util.List r5 = r5.getTags()
            if (r5 != 0) goto L42
        L40:
            r5 = r0
            goto L4b
        L42:
            java.lang.String r6 = "FILTERABLE_MODELS_CONTAINER_TAG"
            boolean r5 = r5.contains(r6)
            if (r5 != r1) goto L40
            r5 = r1
        L4b:
            if (r5 == 0) goto L2d
            goto L4f
        L4e:
            r4 = r2
        L4f:
            com.amazon.music.views.library.models.base.BaseViewContainerModel r4 = (com.amazon.music.views.library.models.base.BaseViewContainerModel) r4
            if (r4 != 0) goto L54
            goto L5
        L54:
            java.util.List r3 = r4.getModels()
        L58:
            if (r3 != 0) goto L99
            if (r8 != 0) goto L5d
            goto L91
        L5d:
            java.util.List r8 = r8.getModels()
            if (r8 != 0) goto L64
            goto L91
        L64:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r8 = r8.iterator()
        L6d:
            boolean r3 = r8.hasNext()
            if (r3 == 0) goto L91
            java.lang.Object r3 = r8.next()
            r4 = r3
            com.amazon.music.views.library.models.base.BaseViewModel r4 = (com.amazon.music.views.library.models.base.BaseViewModel) r4
            java.util.List r4 = r4.getTags()
            if (r4 != 0) goto L82
        L80:
            r4 = r0
            goto L8b
        L82:
            java.lang.String r5 = "FILTERABLE_MODEL_TAG"
            boolean r4 = r4.contains(r5)
            if (r4 != r1) goto L80
            r4 = r1
        L8b:
            if (r4 == 0) goto L6d
            r2.add(r3)
            goto L6d
        L91:
            if (r2 != 0) goto L98
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
            goto L99
        L98:
            r3 = r2
        L99:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.viewmodel.ContainerRefinementDataProvider.createUnfilteredData(com.amazon.music.views.library.models.PageGridViewModel):java.util.List");
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinementDataProvider
    public void updatePageGridViewModelWithFilteredResults(List<? extends BaseViewModel> filteredBaseViewModels, BaseViewContainerModel<BaseViewModel> filterableModelsContainer, MutableLiveData<PageGridViewModel> pageData, List<? extends BaseViewModel> pageDescriptorModels, List<? extends BaseViewModel> appendedModels) {
        if (filteredBaseViewModels == null) {
            return;
        }
        if (filterableModelsContainer != null) {
            Log.debug(ContainerRefinementDataProvider.class.getSimpleName(), "Updating container of filterable entities with filtered entities");
            filterableModelsContainer.updateModels(filteredBaseViewModels);
        } else if (pageData != null) {
            swapFilterablesInPlace(filteredBaseViewModels, pageData);
        }
    }

    private final void swapFilterablesInPlace(List<? extends BaseViewModel> filteredModels, MutableLiveData<PageGridViewModel> pageLiveData) {
        PageGridViewModel value = pageLiveData.getValue();
        List<BaseViewModel> models = value == null ? null : value.getModels();
        if (models == null) {
            return;
        }
        Iterator<BaseViewModel> it = models.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            List<String> tags = it.next().getTags();
            if (tags != null && tags.contains("PRE_FILTERABLE_CONTENT_TAG")) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            List<String> tags2 = ((BaseViewModel) obj).getTags();
            if (!(tags2 != null && tags2.contains("FILTERABLE_MODEL_TAG"))) {
                arrayList.add(obj);
            }
        }
        List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList);
        mutableList.addAll(i + 1, filteredModels);
        value.updateModels(mutableList);
        pageLiveData.postValue(value);
    }
}
