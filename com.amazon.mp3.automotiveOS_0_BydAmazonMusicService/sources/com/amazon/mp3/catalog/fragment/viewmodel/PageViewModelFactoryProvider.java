package com.amazon.mp3.catalog.fragment.viewmodel;

import android.app.Application;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.amazon.mp3.catalog.fragment.datasource.repository.EditablePageDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository;
import com.amazon.mp3.detailpages.album.LibraryAlbumDetailViewModel;
import com.amazon.mp3.detailpages.artist.LibraryArtistDetailViewModel;
import com.amazon.mp3.detailpages.artist.LibraryArtistViewModel;
import com.amazon.mp3.detailpages.artist.LibraryFollowedArtistViewModel;
import com.amazon.mp3.detailpages.playlist.LibraryAlbumsViewModel;
import com.amazon.mp3.detailpages.playlist.LibraryPlaylistsViewModel;
import com.amazon.mp3.detailpages.track.LibraryTracksViewModel;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PageViewModelFactoryProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001:\u000b\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider;", "", "()V", "getViewModel", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "fragment", "Landroidx/fragment/app/Fragment;", "application", "Landroid/app/Application;", "pageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "viewModelFactory", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "BaseViewModelFactory", "LibraryAlbumDetailViewModelFactory", "LibraryAlbumsViewModelFactory", "LibraryArtistDetailViewModelFactory", "LibraryArtistViewModelFactory", "LibraryFollowedArtistViewModelFactory", "LibraryPlaylistsViewModelFactory", "LibraryTracksViewModelFactory", "PageViewModelFactory", "PlayQueueViewModelFactory", "PlaylistDetailViewModelFactory", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PageViewModelFactoryProvider {
    public static final PageViewModelFactoryProvider INSTANCE = new PageViewModelFactoryProvider();

    /* JADX INFO: compiled from: PageViewModelFactoryProvider.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelClass", "Ljava/lang/Class;", "Landroidx/lifecycle/ViewModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface PageViewModelFactory extends ViewModelProvider.Factory {
        Class<ViewModel> getViewModelClass();
    }

    private PageViewModelFactoryProvider() {
    }

    public final PageViewModelProvider getViewModel(Fragment fragment, Application application, PageDataRepository pageDataRepository) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepository, "pageDataRepository");
        return getViewModel(fragment, new BaseViewModelFactory(application, pageDataRepository));
    }

    public final PageViewModelProvider getViewModel(Fragment fragment, PageViewModelFactory viewModelFactory) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(viewModelFactory, "viewModelFactory");
        return (PageViewModelProvider) ViewModelProviders.of(fragment, viewModelFactory).get(viewModelFactory.getViewModelClass());
    }

    /* JADX INFO: compiled from: PageViewModelFactoryProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$BaseViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "application", "Landroid/app/Application;", "pageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "p0", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getViewModelClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class BaseViewModelFactory implements PageViewModelFactory {
        private Application application;
        private PageDataRepository pageDataRepository;

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider.PageViewModelFactory
        public Class<ViewModel> getViewModelClass() {
            return BaseAndroidViewModel.class;
        }

        public BaseViewModelFactory(Application application, PageDataRepository pageDataRepository) {
            Intrinsics.checkNotNullParameter(application, "application");
            Intrinsics.checkNotNullParameter(pageDataRepository, "pageDataRepository");
            this.application = application;
            this.pageDataRepository = pageDataRepository;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return new BaseAndroidViewModel(this.application, this.pageDataRepository, false, false, 12, null);
        }
    }

    /* JADX INFO: compiled from: PageViewModelFactoryProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PlayQueueViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "application", "Landroid/app/Application;", "editablePageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/EditablePageDataRepository;", "initEqualizer", "", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/EditablePageDataRepository;Z)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "p0", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getViewModelClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class PlayQueueViewModelFactory implements PageViewModelFactory {
        private Application application;
        private EditablePageDataRepository editablePageDataRepository;
        private final boolean initEqualizer;

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider.PageViewModelFactory
        public Class<ViewModel> getViewModelClass() {
            return PlayQueueViewModel.class;
        }

        public PlayQueueViewModelFactory(Application application, EditablePageDataRepository editablePageDataRepository, boolean z) {
            Intrinsics.checkNotNullParameter(application, "application");
            Intrinsics.checkNotNullParameter(editablePageDataRepository, "editablePageDataRepository");
            this.application = application;
            this.editablePageDataRepository = editablePageDataRepository;
            this.initEqualizer = z;
        }

        public /* synthetic */ PlayQueueViewModelFactory(Application application, EditablePageDataRepository editablePageDataRepository, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(application, editablePageDataRepository, (i & 4) != 0 ? false : z);
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return new PlayQueueViewModel(this.application, this.editablePageDataRepository, this.initEqualizer);
        }
    }

    /* JADX INFO: compiled from: PageViewModelFactoryProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$LibraryFollowedArtistViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "application", "Landroid/app/Application;", "pageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "usePagination", "", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;Z)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "p0", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getViewModelClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class LibraryFollowedArtistViewModelFactory implements PageViewModelFactory {
        private Application application;
        private PageDataRepository pageDataRepository;
        private boolean usePagination;

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider.PageViewModelFactory
        public Class<ViewModel> getViewModelClass() {
            return LibraryFollowedArtistViewModel.class;
        }

        public LibraryFollowedArtistViewModelFactory(Application application, PageDataRepository pageDataRepository, boolean z) {
            Intrinsics.checkNotNullParameter(application, "application");
            Intrinsics.checkNotNullParameter(pageDataRepository, "pageDataRepository");
            this.application = application;
            this.pageDataRepository = pageDataRepository;
            this.usePagination = z;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return new LibraryFollowedArtistViewModel(this.application, this.pageDataRepository, this.usePagination);
        }
    }

    /* JADX INFO: compiled from: PageViewModelFactoryProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$LibraryAlbumDetailViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "application", "Landroid/app/Application;", "pageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getViewModelClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class LibraryAlbumDetailViewModelFactory implements PageViewModelFactory {
        private Application application;
        private PageDataRepository pageDataRepository;

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider.PageViewModelFactory
        public Class<ViewModel> getViewModelClass() {
            return LibraryAlbumDetailViewModel.class;
        }

        public LibraryAlbumDetailViewModelFactory(Application application, PageDataRepository pageDataRepository) {
            Intrinsics.checkNotNullParameter(application, "application");
            Intrinsics.checkNotNullParameter(pageDataRepository, "pageDataRepository");
            this.application = application;
            this.pageDataRepository = pageDataRepository;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkNotNullParameter(modelClass, "modelClass");
            return new LibraryAlbumDetailViewModel(this.application, this.pageDataRepository);
        }
    }

    /* JADX INFO: compiled from: PageViewModelFactoryProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$LibraryArtistDetailViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "application", "Landroid/app/Application;", "pageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getViewModelClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class LibraryArtistDetailViewModelFactory implements PageViewModelFactory {
        private Application application;
        private PageDataRepository pageDataRepository;

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider.PageViewModelFactory
        public Class<ViewModel> getViewModelClass() {
            return LibraryArtistDetailViewModel.class;
        }

        public LibraryArtistDetailViewModelFactory(Application application, PageDataRepository pageDataRepository) {
            Intrinsics.checkNotNullParameter(application, "application");
            Intrinsics.checkNotNullParameter(pageDataRepository, "pageDataRepository");
            this.application = application;
            this.pageDataRepository = pageDataRepository;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkNotNullParameter(modelClass, "modelClass");
            return new LibraryArtistDetailViewModel(this.application, this.pageDataRepository);
        }
    }

    /* JADX INFO: compiled from: PageViewModelFactoryProvider.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ%\u0010\u000f\u001a\u0002H\u0010\"\b\b\u0000\u0010\u0010*\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0013H\u0016¢\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$LibraryTracksViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "application", "Landroid/app/Application;", "pageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;", "Lcom/amazon/mp3/library/item/MusicTrack;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "defaultFilters", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "refinementSelectionOccurred", "Lkotlin/Function0;", "", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;Ljava/util/Set;Lkotlin/jvm/functions/Function0;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getViewModelClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class LibraryTracksViewModelFactory implements PageViewModelFactory {
        private Application application;
        private final Set<RefinementFilterType> defaultFilters;
        private RefinablePageDataRepository<MusicTrack, DatabaseSource<MusicTrack>> pageDataRepository;
        private final Function0<Unit> refinementSelectionOccurred;

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider.PageViewModelFactory
        public Class<ViewModel> getViewModelClass() {
            return LibraryTracksViewModel.class;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public LibraryTracksViewModelFactory(Application application, RefinablePageDataRepository<MusicTrack, DatabaseSource<MusicTrack>> pageDataRepository, Set<? extends RefinementFilterType> set, Function0<Unit> refinementSelectionOccurred) {
            Intrinsics.checkNotNullParameter(application, "application");
            Intrinsics.checkNotNullParameter(pageDataRepository, "pageDataRepository");
            Intrinsics.checkNotNullParameter(refinementSelectionOccurred, "refinementSelectionOccurred");
            this.application = application;
            this.pageDataRepository = pageDataRepository;
            this.defaultFilters = set;
            this.refinementSelectionOccurred = refinementSelectionOccurred;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkNotNullParameter(modelClass, "modelClass");
            return new LibraryTracksViewModel(this.application, this.pageDataRepository, this.defaultFilters, this.refinementSelectionOccurred);
        }
    }

    /* JADX INFO: compiled from: PageViewModelFactoryProvider.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ%\u0010\f\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$LibraryArtistViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "application", "Landroid/app/Application;", "pageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;", "Lcom/amazon/mp3/library/item/Artist;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "defaultFilters", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;Ljava/util/Set;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getViewModelClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class LibraryArtistViewModelFactory implements PageViewModelFactory {
        private Application application;
        private final Set<RefinementFilterType> defaultFilters;
        private RefinablePageDataRepository<Artist, DatabaseSource<Artist>> pageDataRepository;

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider.PageViewModelFactory
        public Class<ViewModel> getViewModelClass() {
            return LibraryArtistViewModel.class;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public LibraryArtistViewModelFactory(Application application, RefinablePageDataRepository<Artist, DatabaseSource<Artist>> pageDataRepository, Set<? extends RefinementFilterType> set) {
            Intrinsics.checkNotNullParameter(application, "application");
            Intrinsics.checkNotNullParameter(pageDataRepository, "pageDataRepository");
            this.application = application;
            this.pageDataRepository = pageDataRepository;
            this.defaultFilters = set;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkNotNullParameter(modelClass, "modelClass");
            return new LibraryArtistViewModel(this.application, this.pageDataRepository, this.defaultFilters);
        }
    }

    /* JADX INFO: compiled from: PageViewModelFactoryProvider.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ%\u0010\f\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$LibraryAlbumsViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "application", "Landroid/app/Application;", "pageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;", "Lcom/amazon/mp3/library/item/Album;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "defaultFilters", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;Ljava/util/Set;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getViewModelClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class LibraryAlbumsViewModelFactory implements PageViewModelFactory {
        private Application application;
        private final Set<RefinementFilterType> defaultFilters;
        private RefinablePageDataRepository<Album, DatabaseSource<Album>> pageDataRepository;

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider.PageViewModelFactory
        public Class<ViewModel> getViewModelClass() {
            return LibraryAlbumsViewModel.class;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public LibraryAlbumsViewModelFactory(Application application, RefinablePageDataRepository<Album, DatabaseSource<Album>> pageDataRepository, Set<? extends RefinementFilterType> set) {
            Intrinsics.checkNotNullParameter(application, "application");
            Intrinsics.checkNotNullParameter(pageDataRepository, "pageDataRepository");
            this.application = application;
            this.pageDataRepository = pageDataRepository;
            this.defaultFilters = set;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkNotNullParameter(modelClass, "modelClass");
            return new LibraryAlbumsViewModel(this.application, this.pageDataRepository, this.defaultFilters);
        }
    }

    /* JADX INFO: compiled from: PageViewModelFactoryProvider.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ%\u0010\f\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$LibraryPlaylistsViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "application", "Landroid/app/Application;", "pageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;", "Lcom/amazon/mp3/library/item/Playlist;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "defaultFilters", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;Ljava/util/Set;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getViewModelClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class LibraryPlaylistsViewModelFactory implements PageViewModelFactory {
        private Application application;
        private final Set<RefinementFilterType> defaultFilters;
        private RefinablePageDataRepository<Playlist, DatabaseSource<Playlist>> pageDataRepository;

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider.PageViewModelFactory
        public Class<ViewModel> getViewModelClass() {
            return LibraryPlaylistsViewModel.class;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public LibraryPlaylistsViewModelFactory(Application application, RefinablePageDataRepository<Playlist, DatabaseSource<Playlist>> pageDataRepository, Set<? extends RefinementFilterType> set) {
            Intrinsics.checkNotNullParameter(application, "application");
            Intrinsics.checkNotNullParameter(pageDataRepository, "pageDataRepository");
            this.application = application;
            this.pageDataRepository = pageDataRepository;
            this.defaultFilters = set;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkNotNullParameter(modelClass, "modelClass");
            return new LibraryPlaylistsViewModel(this.application, this.pageDataRepository, this.defaultFilters);
        }
    }

    /* JADX INFO: compiled from: PageViewModelFactoryProvider.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ%\u0010\f\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PlaylistDetailViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "application", "Landroid/app/Application;", "playlistDetailDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PlaylistDetailDataRepository;", "isCatalog", "", "isVideoPlaylist", "contentUri", "Landroid/net/Uri;", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PlaylistDetailDataRepository;ZZLandroid/net/Uri;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "p0", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getViewModelClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class PlaylistDetailViewModelFactory implements PageViewModelFactory {
        private Application application;
        private final Uri contentUri;
        private final boolean isCatalog;
        private final boolean isVideoPlaylist;
        private PlaylistDetailDataRepository playlistDetailDataRepository;

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider.PageViewModelFactory
        public Class<ViewModel> getViewModelClass() {
            return PlaylistDetailViewModel.class;
        }

        public PlaylistDetailViewModelFactory(Application application, PlaylistDetailDataRepository playlistDetailDataRepository, boolean z, boolean z2, Uri uri) {
            Intrinsics.checkNotNullParameter(application, "application");
            Intrinsics.checkNotNullParameter(playlistDetailDataRepository, "playlistDetailDataRepository");
            this.application = application;
            this.playlistDetailDataRepository = playlistDetailDataRepository;
            this.isCatalog = z;
            this.isVideoPlaylist = z2;
            this.contentUri = uri;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return new PlaylistDetailViewModel(this.application, this.playlistDetailDataRepository, this.isCatalog, !this.isVideoPlaylist, this.contentUri);
        }
    }
}
