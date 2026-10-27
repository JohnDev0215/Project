package com.android.internal.graphics.palette;

import com.android.internal.graphics.palette.Palette;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface Quantizer {
    List<Palette.Swatch> getQuantizedColors();

    void quantize(int[] iArr, int i, Palette.Filter[] filterArr);
}
