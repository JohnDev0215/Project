package com.amazon.avod.ads.parser.vast;

import com.amazon.avod.ads.parser.parsers.VastError;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastException extends XmlPullParserException {
    private final VastError mVastError;

    public VastException(@Nonnull VastError vastError) {
        super(vastError.toString());
        this.mVastError = vastError;
    }
}
