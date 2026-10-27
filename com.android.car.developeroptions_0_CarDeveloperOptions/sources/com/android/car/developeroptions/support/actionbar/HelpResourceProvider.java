package com.android.car.developeroptions.support.actionbar;

import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public interface HelpResourceProvider {
    default int getHelpResource() {
        return R.string.help_uri_default;
    }
}
