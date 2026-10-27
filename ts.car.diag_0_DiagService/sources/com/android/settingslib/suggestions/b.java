package com.android.settingslib.suggestions;

import android.content.Context;
import android.service.settings.suggestions.Suggestion;
import android.util.Log;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class b extends com.android.settingslib.utils.a<List<Suggestion>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f1372b;

    public b(Context context, a aVar) {
        super(context);
        this.f1372b = aVar;
    }

    @Override // android.content.AsyncTaskLoader
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public List<Suggestion> loadInBackground() {
        List<Suggestion> listA = this.f1372b.a();
        if (listA == null) {
            Log.d("SuggestionLoader", "data is null");
        } else {
            Log.d("SuggestionLoader", "data size " + listA.size());
        }
        return listA;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.utils.a
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void a(List<Suggestion> list) {
    }
}
