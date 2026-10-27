package com.amplifyframework.core.plugin;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.category.CategoryTypeable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public interface Plugin<E> extends CategoryTypeable {
    void configure(JSONObject jSONObject, @NonNull Context context) throws AmplifyException;

    @Nullable
    E getEscapeHatch();

    @NonNull
    String getPluginKey();

    @NonNull
    String getVersion();

    @WorkerThread
    void initialize(@NonNull Context context) throws AmplifyException;
}
