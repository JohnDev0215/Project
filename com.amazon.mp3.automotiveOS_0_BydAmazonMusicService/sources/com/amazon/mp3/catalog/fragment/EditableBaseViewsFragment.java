package com.amazon.mp3.catalog.fragment;

import androidx.lifecycle.LifecycleOwnerKt;
import com.amazon.mp3.catalog.fragment.viewmodel.EditableViewModelProvider;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import kotlin.Metadata;

/* JADX INFO: compiled from: EditableBaseViewsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0002H\u0014J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0004J\b\u0010\b\u001a\u00020\u0006H\u0004R.\u0010\u000b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\t8\u0014@VX\u0094\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/EditableBaseViewsFragment;", "Lcom/amazon/mp3/catalog/fragment/AbstractBaseViewsFragment;", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "getAdapterProvider", "", "index", "", "deleteModel", "clearModels", "Lcom/amazon/mp3/catalog/fragment/viewmodel/EditableViewModelProvider;", "<set-?>", "pageModelProvider", "Lcom/amazon/mp3/catalog/fragment/viewmodel/EditableViewModelProvider;", "getPageModelProvider", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/EditableViewModelProvider;", "setPageModelProvider", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/EditableViewModelProvider;)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class EditableBaseViewsFragment extends AbstractBaseViewsFragment {
    private EditableViewModelProvider pageModelProvider;

    public EditableBaseViewsFragment() {
        setShouldUseDiffUtilForReorders(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public EditableViewModelProvider getPageModelProvider() {
        return this.pageModelProvider;
    }

    public void setPageModelProvider(EditableViewModelProvider editableViewModelProvider) {
        this.pageModelProvider = editableViewModelProvider;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected BaseViewModelAdapterProvider getAdapterProvider() {
        boolean zIsEnabled = Feature.use_optimized_observable_browse_views_factory.isEnabled();
        return new BaseViewModelAdapterProvider(this, getPageModelProvider(), LifecycleOwnerKt.getLifecycleScope(this), zIsEnabled);
    }

    protected final void deleteModel(int index) {
        EditableViewModelProvider pageModelProvider;
        if (getPageModel() == null || (pageModelProvider = getPageModelProvider()) == null) {
            return;
        }
        pageModelProvider.deleteModel(index);
    }

    protected final void clearModels() {
        EditableViewModelProvider pageModelProvider;
        if (getPageModel() == null || (pageModelProvider = getPageModelProvider()) == null) {
            return;
        }
        pageModelProvider.clearModels();
    }
}
