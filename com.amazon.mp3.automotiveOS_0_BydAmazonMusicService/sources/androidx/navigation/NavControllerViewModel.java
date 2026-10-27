package androidx.navigation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
class NavControllerViewModel extends ViewModel {
    private static final ViewModelProvider.Factory FACTORY = new ViewModelProvider.Factory() { // from class: androidx.navigation.NavControllerViewModel.1
        @Override // androidx.lifecycle.ViewModelProvider.Factory
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> cls) {
            return new NavControllerViewModel();
        }
    };
    private final HashMap<UUID, ViewModelStore> mViewModelStores = new HashMap<>();

    NavControllerViewModel() {
    }

    @NonNull
    static NavControllerViewModel getInstance(ViewModelStore viewModelStore) {
        return (NavControllerViewModel) new ViewModelProvider(viewModelStore, FACTORY).get(NavControllerViewModel.class);
    }

    void clear(@NonNull UUID uuid) {
        ViewModelStore viewModelStoreRemove = this.mViewModelStores.remove(uuid);
        if (viewModelStoreRemove != null) {
            viewModelStoreRemove.clear();
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        Iterator<ViewModelStore> it = this.mViewModelStores.values().iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.mViewModelStores.clear();
    }

    @NonNull
    ViewModelStore getViewModelStore(@NonNull UUID uuid) {
        ViewModelStore viewModelStore = this.mViewModelStores.get(uuid);
        if (viewModelStore != null) {
            return viewModelStore;
        }
        ViewModelStore viewModelStore2 = new ViewModelStore();
        this.mViewModelStores.put(uuid, viewModelStore2);
        return viewModelStore2;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("NavControllerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} ViewModelStores (");
        Iterator<UUID> it = this.mViewModelStores.keySet().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
