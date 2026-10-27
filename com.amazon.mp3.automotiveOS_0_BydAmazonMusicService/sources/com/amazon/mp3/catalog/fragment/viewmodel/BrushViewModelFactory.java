package com.amazon.mp3.catalog.fragment.viewmodel;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushViewModelFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\n\b\u0016\u0018\u00002\u00020\u0001B_\b\u0016\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\b\b\u0002\u0010\"\u001a\u00020\u0019\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u000f\u0012\u001c\b\u0002\u0010'\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0&\u0018\u00010%¢\u0006\u0004\b.\u0010/J'\u0010\u0006\u001a\u00028\u0000\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\"\u0010\t\u001a\u00020\b8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u0010\u001a\u00020\u000f8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u0004\u0018\u00010\u000f8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\"\u0010\u001a\u001a\u00020\u00198\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001a\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010\u001f\u001a\u0004\u0018\u00010\u000f8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010\u0011\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R\"\u0010\"\u001a\u00020\u00198\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u001b\u001a\u0004\b#\u0010\u001c\"\u0004\b$\u0010\u001eR6\u0010'\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0&\u0018\u00010%8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u00060"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/BrushViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "Landroidx/lifecycle/ViewModel;", "T", "Ljava/lang/Class;", "p0", "create", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "Landroid/app/Application;", "application", "Landroid/app/Application;", "getApplication", "()Landroid/app/Application;", "setApplication", "(Landroid/app/Application;)V", "", "uri", "Ljava/lang/String;", "getUri", "()Ljava/lang/String;", "setUri", "(Ljava/lang/String;)V", "nextPageToken", "getNextPageToken", "setNextPageToken", "", "isArtistUpsell", "Z", "()Z", "setArtistUpsell", "(Z)V", "activeFilterModel", "getActiveFilterModel", "setActiveFilterModel", "supportRefinements", "getSupportRefinements", "setSupportRefinements", "", "", "refinementLists", "Ljava/util/Map;", "getRefinementLists", "()Ljava/util/Map;", "setRefinementLists", "(Ljava/util/Map;)V", "activeFilterModelBrowseId", "<init>", "(Landroid/app/Application;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/util/Map;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BrushViewModelFactory implements ViewModelProvider.Factory {
    private String activeFilterModel;
    private Application application;
    private boolean isArtistUpsell;
    private String nextPageToken;
    private Map<String, ? extends List<String>> refinementLists;
    private boolean supportRefinements;
    private String uri;

    protected final Application getApplication() {
        return this.application;
    }

    protected final String getUri() {
        return this.uri;
    }

    protected final String getNextPageToken() {
        return this.nextPageToken;
    }

    /* JADX INFO: renamed from: isArtistUpsell, reason: from getter */
    protected final boolean getIsArtistUpsell() {
        return this.isArtistUpsell;
    }

    protected final String getActiveFilterModel() {
        return this.activeFilterModel;
    }

    protected final boolean getSupportRefinements() {
        return this.supportRefinements;
    }

    protected final Map<String, List<String>> getRefinementLists() {
        return this.refinementLists;
    }

    public /* synthetic */ BrushViewModelFactory(Application application, String str, String str2, boolean z, boolean z2, String str3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(application, str, str2, z, (i & 16) != 0 ? false : z2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : map);
    }

    public BrushViewModelFactory(Application application, String uri, String str, boolean z, boolean z2, String str2, Map<String, ? extends List<String>> map) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.application = application;
        this.uri = uri;
        this.nextPageToken = str;
        this.isArtistUpsell = z;
        this.supportRefinements = z2;
        this.activeFilterModel = str2;
        this.refinementLists = map;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return new BrushViewModel(this.application, this.uri, this.nextPageToken, this.isArtistUpsell, this.supportRefinements, this.activeFilterModel, this.refinementLists);
    }
}
