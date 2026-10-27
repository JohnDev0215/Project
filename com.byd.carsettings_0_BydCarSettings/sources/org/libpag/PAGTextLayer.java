package org.libpag;

/* JADX INFO: loaded from: classes3.dex */
public class PAGTextLayer extends PAGLayer {
    static {
        org.extra.tools.a.a("pag");
        nativeInit();
    }

    public PAGTextLayer(long j) {
        super(j);
    }

    private static native void nativeInit();

    private native void setFont(String str, String str2);

    public native int fillColor();

    public native PAGFont font();

    public native float fontSize();

    public native void reset();

    public native void setFillColor(int i);

    public void setFont(PAGFont pAGFont) {
        setFont(pAGFont.fontFamily, pAGFont.fontStyle);
    }

    public native void setFontSize(float f2);

    public native void setStrokeColor(int i);

    public native void setText(String str);

    public native int strokeColor();

    public native String text();
}
