package com.amazon.music.identity.profiles.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManager;
import com.amazon.music.identity.profiles.datasource.repository.ProfileDataRepository;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileListViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0006\u0010\u0010\u001a\u00020\u000fJ\b\u0010\u0011\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/identity/profiles/viewmodel/ProfileListViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "mDisposable", "Lio/reactivex/rxjava3/disposables/Disposable;", "mProfileDataRepository", "Lcom/amazon/music/identity/profiles/datasource/repository/ProfileDataRepository;", "pageData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/identity/profiles/datasource/mockprofile/MockProfileManager;", "getPageData", "()Landroidx/lifecycle/MutableLiveData;", "dispose", "", "fetchData", "onCleared", "Companion", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ProfileListViewModel extends AndroidViewModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = ProfileListViewModel.class.getSimpleName();
    private final Application application;
    private Disposable mDisposable;
    private final ProfileDataRepository mProfileDataRepository;
    private final MutableLiveData<MockProfileManager> pageData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileListViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.application = application;
        this.pageData = new MutableLiveData<>();
        this.mProfileDataRepository = new ProfileDataRepository();
    }

    /* JADX INFO: compiled from: ProfileListViewModel.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/identity/profiles/viewmodel/ProfileListViewModel$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return ProfileListViewModel.TAG;
        }
    }

    public final MutableLiveData<MockProfileManager> getPageData() {
        return this.pageData;
    }

    public final void fetchData() {
        dispose();
        this.mDisposable = (Disposable) this.mProfileDataRepository.getSingleMockProfileManager().subscribeWith(new DisposableSingleObserver<MockProfileManager>() { // from class: com.amazon.music.identity.profiles.viewmodel.ProfileListViewModel.fetchData.1
            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(MockProfileManager mockProfileManager) {
                Intrinsics.checkNotNullParameter(mockProfileManager, "mockProfileManager");
                ProfileListViewModel.this.getPageData().setValue(mockProfileManager);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                Log.i(Intrinsics.stringPlus("Error in ", ProfileListViewModel.INSTANCE.getTAG()), e.toString());
            }
        });
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        dispose();
    }

    private final void dispose() {
        Disposable disposable;
        Disposable disposable2 = this.mDisposable;
        if (disposable2 != null) {
            boolean z = false;
            if (disposable2 != null && disposable2.isDisposed()) {
                z = true;
            }
            if (z || (disposable = this.mDisposable) == null) {
                return;
            }
            disposable.dispose();
        }
    }
}
