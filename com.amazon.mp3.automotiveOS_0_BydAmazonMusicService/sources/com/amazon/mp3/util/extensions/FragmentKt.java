package com.amazon.mp3.util.extensions;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Fragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¨\u0006\b"}, d2 = {"Landroidx/fragment/app/Fragment;", "", "doOnlyOnce", "Lkotlin/Function0;", "", "action", "Landroidx/lifecycle/LifecycleObserver;", "doOnResume", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0})
public final class FragmentKt {
    public static final LifecycleObserver doOnResume(final Fragment fragment, final boolean z, final Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        LifecycleObserver lifecycleObserver = new LifecycleObserver() { // from class: com.amazon.mp3.util.extensions.FragmentKt$doOnResume$observer$1
            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            public final void onResume() {
                action.invoke();
                if (z) {
                    fragment.getLifecycle().removeObserver(this);
                }
            }
        };
        fragment.getLifecycle().addObserver(lifecycleObserver);
        return lifecycleObserver;
    }
}
