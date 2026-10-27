package com.ctc.wstx.msv;

import com.ctc.wstx.shaded.msv_core.reader.GrammarReaderController;
import com.ctc.wstx.shaded.msv_core.reader.util.IgnoreController;

/* JADX INFO: loaded from: classes5.dex */
public class W3CSchemaFactory extends BaseSchemaFactory {
    protected final GrammarReaderController mDummyController;

    public W3CSchemaFactory() {
        super("http://www.w3.org/2001/XMLSchema");
        this.mDummyController = new IgnoreController();
    }
}
