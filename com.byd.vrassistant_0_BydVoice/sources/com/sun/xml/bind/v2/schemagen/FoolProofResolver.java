package com.sun.xml.bind.v2.schemagen;

import java.io.IOException;
import java.util.logging.Logger;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;

/* JADX INFO: loaded from: classes3.dex */
final class FoolProofResolver extends SchemaOutputResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger logger = com.sun.xml.bind.Util.getClassLogger();
    private final SchemaOutputResolver resolver;

    public FoolProofResolver(SchemaOutputResolver schemaOutputResolver) {
        this.resolver = schemaOutputResolver;
    }

    @Override // javax.xml.bind.SchemaOutputResolver
    public Result createOutput(String str, String str2) throws IOException {
        Logger logger2 = logger;
        logger2.entering(getClass().getName(), "createOutput", new Object[]{str, str2});
        Result resultCreateOutput = this.resolver.createOutput(str, str2);
        if (resultCreateOutput != null) {
            String systemId = resultCreateOutput.getSystemId();
            logger2.finer("system ID = " + systemId);
            if (systemId == null) {
                throw new AssertionError("system ID cannot be null");
            }
        }
        logger2.exiting(getClass().getName(), "createOutput", resultCreateOutput);
        return resultCreateOutput;
    }
}
