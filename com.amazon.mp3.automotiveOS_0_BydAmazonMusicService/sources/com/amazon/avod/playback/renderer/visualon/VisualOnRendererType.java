package com.amazon.avod.playback.renderer.visualon;

/* JADX INFO: loaded from: classes2.dex */
public enum VisualOnRendererType {
    SOFTWARE(0),
    IOMX(1),
    OPENMAXAL(2),
    OSCET_MC(3);

    private final int mRendererType;

    VisualOnRendererType(int i) {
        this.mRendererType = i;
    }

    public int getTypeAsInt() {
        return this.mRendererType;
    }
}
