package com.amazon.mp3.detailpages.album.data;

import android.app.Application;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.amazon.mp3.catalog.fragment.viewmodel.AlbumBrushViewModelFactory;
import com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModelFactory;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.featuregate.Feature;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PageViewModelProviderFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/detailpages/album/data/PageViewModelProviderFactory;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PageViewModelProviderFactory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: PageViewModelProviderFactory.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ4\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/detailpages/album/data/PageViewModelProviderFactory$Companion;", "", "Landroidx/fragment/app/Fragment;", "fragment", "Landroid/app/Application;", "application", "", "uri", "nextPageToken", "albumAsin", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "getCatalogPageViewModelProvider", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final PageViewModelProvider getCatalogPageViewModelProvider(Fragment fragment, Application application, String uri, String nextPageToken, String albumAsin) {
            ViewModelProvider.Factory brushViewModelFactory;
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            Intrinsics.checkNotNullParameter(application, "application");
            Intrinsics.checkNotNullParameter(uri, "uri");
            if (Feature.update_purchased_track_asin_substitution.isEnabled()) {
                brushViewModelFactory = new AlbumBrushViewModelFactory(application, uri, nextPageToken, false, true, null, null, albumAsin, 64, null);
            } else {
                brushViewModelFactory = new BrushViewModelFactory(application, uri, nextPageToken, false, true, null, null, 64, null);
            }
            return (PageViewModelProvider) ViewModelProviders.of(fragment, brushViewModelFactory).get(BrushViewModel.class);
        }
    }
}
