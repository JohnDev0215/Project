package com.android.car.settings.search;

import com.android.settingslib.search.SearchIndexableData;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.car.settings.search.-$$Lambda$_spiwCrcbSeMlBfhG-o4fvFwkk0, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class $$Lambda$_spiwCrcbSeMlBfhGo4fvFwkk0 implements Function {
    public static final /* synthetic */ $$Lambda$_spiwCrcbSeMlBfhGo4fvFwkk0 INSTANCE = new $$Lambda$_spiwCrcbSeMlBfhGo4fvFwkk0();

    private /* synthetic */ $$Lambda$_spiwCrcbSeMlBfhGo4fvFwkk0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((SearchIndexableData) obj).getSearchIndexProvider();
    }
}
