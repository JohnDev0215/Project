package com.android.car.media.common.source;

import android.car.media.XCarMediaIntents;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import java.util.Objects;
import spi.b0;
import spi.b7;

/* JADX INFO: loaded from: classes.dex */
public class MediaTrampolineHelper implements LifecycleObserver {
    public final FragmentActivity a;
    public final MediaSourceViewModel b;
    public b7 c;
    public boolean d;

    public MediaTrampolineHelper(@NonNull FragmentActivity fragmentActivity) {
        this.a = fragmentActivity;
        MediaSourceViewModel mediaSourceViewModelA = MediaSourceViewModel.a(fragmentActivity.getApplication(), 1);
        this.b = mediaSourceViewModelA;
        fragmentActivity.getLifecycle().addObserver(this);
        mediaSourceViewModelA.c.observe(fragmentActivity, new b0(this, 6));
    }

    public final void a(b7 b7Var) {
        if (Objects.equals(this.c, b7Var)) {
            return;
        }
        if (Log.isLoggable("MediaTrampolineHlp", 4)) {
            Log.i("MediaTrampolineHlp", "Launching: " + b7Var + " old: " + this.c);
        }
        Intent intent = new Intent("android.car.intent.action.MEDIA_TEMPLATE");
        if (b7Var != null) {
            intent.putExtra(XCarMediaIntents.EXTRA_MEDIA_COMPONENT, b7Var.a.flattenToString());
        } else if (Log.isLoggable("MediaTrampolineHlp", 5)) {
            Log.w("MediaTrampolineHlp", "latestSource is null in onResume");
        }
        this.a.startActivity(intent);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        this.d = false;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        this.d = true;
        a(this.b.c.getValue());
    }
}
