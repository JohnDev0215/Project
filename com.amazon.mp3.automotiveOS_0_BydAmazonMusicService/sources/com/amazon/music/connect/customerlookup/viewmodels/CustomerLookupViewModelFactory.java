package com.amazon.music.connect.customerlookup.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.music.connect.IdentityProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerLookupViewModelFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010\tJ%\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH\u0016¢\u0006\u0002\u0010\u000fR\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/connect/customerlookup/viewmodels/CustomerLookupViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "identityProvider", "Lcom/amazon/music/connect/IdentityProvider;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "endpoints", "", "", "(Lcom/amazon/music/connect/IdentityProvider;Lcom/amazon/music/views/library/styles/StyleSheet;Ljava/util/Map;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerLookupViewModelFactory implements ViewModelProvider.Factory {
    private final Map<String, String> endpoints;
    private final IdentityProvider identityProvider;
    private final StyleSheet styleSheet;

    public CustomerLookupViewModelFactory(IdentityProvider identityProvider, StyleSheet styleSheet, Map<String, String> endpoints) {
        Intrinsics.checkNotNullParameter(identityProvider, "identityProvider");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(endpoints, "endpoints");
        this.identityProvider = identityProvider;
        this.styleSheet = styleSheet;
        this.endpoints = endpoints;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        return new CustomerLookupViewModel(this.identityProvider, this.styleSheet, this.endpoints);
    }
}
