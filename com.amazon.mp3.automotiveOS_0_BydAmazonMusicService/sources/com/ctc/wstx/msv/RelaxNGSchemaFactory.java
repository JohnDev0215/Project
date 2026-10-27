package com.ctc.wstx.msv;

import com.ctc.wstx.shaded.msv_core.reader.GrammarReaderController;
import com.ctc.wstx.shaded.msv_core.reader.util.IgnoreController;

/* JADX INFO: loaded from: classes5.dex */
public class RelaxNGSchemaFactory extends BaseSchemaFactory {
    protected final GrammarReaderController mDummyController;

    public RelaxNGSchemaFactory() {
        super("http://relaxng.org/ns/structure/0.9");
        this.mDummyController = new IgnoreController();
    }
}
