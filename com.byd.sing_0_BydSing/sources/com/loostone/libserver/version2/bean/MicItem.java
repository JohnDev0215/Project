package com.loostone.libserver.version2.bean;

import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class MicItem {
    private String modelName;
    private int pregainMic;

    public MicItem(String str, int i) {
        h.d(str, "modelName");
        this.modelName = str;
        this.pregainMic = i;
    }

    public final String getModelName() {
        return this.modelName;
    }

    public final int getPregainMic() {
        return this.pregainMic;
    }

    public final void setModelName(String str) {
        h.d(str, "<set-?>");
        this.modelName = str;
    }

    public final void setPregainMic(int i) {
        this.pregainMic = i;
    }
}
