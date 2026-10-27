package com.blankj.utilcode.util;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: compiled from: ToastUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b extends Utils.ActivityLifecycleCallbacks {
    public final /* synthetic */ int a;
    public final /* synthetic */ ToastUtils.d b;

    public b(ToastUtils.d dVar, int i) {
        this.b = dVar;
        this.a = i;
    }

    @Override // com.blankj.utilcode.util.Utils.ActivityLifecycleCallbacks
    public final void onActivityCreated(@NonNull Activity activity) {
        ToastUtils.d dVar = this.b;
        if (dVar.d != null) {
            dVar.f(activity, this.a, false);
        }
    }
}
