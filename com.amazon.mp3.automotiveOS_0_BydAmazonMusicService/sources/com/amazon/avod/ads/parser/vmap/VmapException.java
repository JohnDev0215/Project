package com.amazon.avod.ads.parser.vmap;

import com.amazon.avod.ads.parser.parsers.VmapError;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VmapException extends XmlPullParserException {
    private final VmapError mVmapError;

    public VmapException(@Nonnull VmapError vmapError) {
        super(vmapError.toString());
        this.mVmapError = vmapError;
    }
}
