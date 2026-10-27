package com.android.car.ui.preference;

import androidx.preference.Preference;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes6.dex */
public interface UxRestrictablePreference {
    Consumer<Preference> getOnClickWhileRestrictedListener();

    boolean isUxRestricted();

    void setOnClickWhileRestrictedListener(Consumer<Preference> consumer);

    void setUxRestricted(boolean z);
}
