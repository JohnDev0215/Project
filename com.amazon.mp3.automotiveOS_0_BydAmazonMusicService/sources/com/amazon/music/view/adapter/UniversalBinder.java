package com.amazon.music.view.adapter;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public interface UniversalBinder<View extends View, Model> {
    void bind(View view, Model model);

    View createView(Context context);

    Class<Model> getModelClass();
}
