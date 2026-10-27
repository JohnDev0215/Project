package com.ctc.wstx.shaded.msv_core.reader.util;

import com.ctc.wstx.shaded.msv_core.reader.GrammarReaderController;
import org.xml.sax.InputSource;

/* JADX INFO: loaded from: classes5.dex */
public class IgnoreController implements GrammarReaderController {
    @Override // org.xml.sax.EntityResolver
    public InputSource resolveEntity(String str, String str2) {
        return null;
    }
}
