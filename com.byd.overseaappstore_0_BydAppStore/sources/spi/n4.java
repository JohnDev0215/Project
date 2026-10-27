package spi;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.PreferenceDataStoreDelegateKt;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class n4 {
    public static final /* synthetic */ KProperty<Object>[] a = {Reflection.property1(new PropertyReference1Impl(n4.class, "OverseaAppStoreDataStore", "getOverseaAppStoreDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", 1))};

    @NotNull
    public static final ReadOnlyProperty b = PreferenceDataStoreDelegateKt.preferencesDataStore$default("OverseaAppStore", null, null, null, 14, null);

    @NotNull
    public static final Preferences.Key<Boolean> c = PreferencesKeys.booleanKey("useByWifi");

    @NotNull
    public static final Preferences.Key<String> d = PreferencesKeys.stringKey("token");

    @NotNull
    public static final Preferences.Key<Boolean> e = PreferencesKeys.booleanKey("protocolAgree");

    @NotNull
    public static final Preferences.Key<Integer> f = PreferencesKeys.intKey("protocolVersionLocal");

    @NotNull
    public static final DataStore<Preferences> a(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (DataStore) b.getValue(context, a[0]);
    }
}
