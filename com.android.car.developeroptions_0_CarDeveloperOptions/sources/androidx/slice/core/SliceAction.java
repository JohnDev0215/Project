package androidx.slice.core;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
public interface SliceAction {
    PendingIntent getAction();

    IconCompat getIcon();

    int getImageMode();

    int getPriority();

    CharSequence getTitle();

    boolean isToggle();
}
