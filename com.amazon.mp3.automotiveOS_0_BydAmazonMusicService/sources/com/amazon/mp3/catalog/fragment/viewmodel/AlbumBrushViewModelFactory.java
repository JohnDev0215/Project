package com.amazon.mp3.catalog.fragment.viewmodel;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlbumBrushViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\b\u0012\u001c\b\u0002\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0016\u0018\u00010\u0015\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0018\u0010\u0019J'\u0010\u0006\u001a\u00028\u0000\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/AlbumBrushViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BrushViewModelFactory;", "Landroidx/lifecycle/ViewModel;", "T", "Ljava/lang/Class;", "p0", "create", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "", "albumAsin", "Ljava/lang/String;", "getAlbumAsin", "()Ljava/lang/String;", "Landroid/app/Application;", "application", "uri", "nextPageToken", "", "isArtistUpsell", "supportRefinements", "activeFilterModelBrowseId", "", "", "refinementLists", "<init>", "(Landroid/app/Application;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/util/Map;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AlbumBrushViewModelFactory extends BrushViewModelFactory {
    private final String albumAsin;

    public /* synthetic */ AlbumBrushViewModelFactory(Application application, String str, String str2, boolean z, boolean z2, String str3, Map map, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(application, str, str2, z, (i & 16) != 0 ? false : z2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : map, str4);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumBrushViewModelFactory(Application application, String uri, String str, boolean z, boolean z2, String str2, Map<String, ? extends List<String>> map, String str3) {
        super(application, uri, str, z, z2, str2, map);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.albumAsin = str3;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModelFactory, androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return new AlbumBrushViewModel(getApplication(), getUri(), getNextPageToken(), getIsArtistUpsell(), getSupportRefinements(), getActiveFilterModel(), getRefinementLists(), this.albumAsin);
    }
}
