package com.loostone.libtuning.data.config.vendor;

import java.util.ArrayList;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class EnvironmentalSoundConfig {
    private ArrayList<EnvironmentalSoundItem> environmentalSoundList = new ArrayList<>();

    public final ArrayList<EnvironmentalSoundItem> getEnvironmentalSoundList() {
        return this.environmentalSoundList;
    }

    public final void setEnvironmentalSoundList(ArrayList<EnvironmentalSoundItem> arrayList) {
        h.d(arrayList, "<set-?>");
        this.environmentalSoundList = arrayList;
    }
}
