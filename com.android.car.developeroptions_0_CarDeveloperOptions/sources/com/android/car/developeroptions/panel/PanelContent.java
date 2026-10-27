package com.android.car.developeroptions.panel;

import android.content.Intent;
import android.net.Uri;
import com.android.settingslib.core.instrumentation.Instrumentable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface PanelContent extends Instrumentable {
    Intent getSeeMoreIntent();

    List<Uri> getSlices();

    CharSequence getTitle();
}
