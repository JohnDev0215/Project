package org.mockito.internal.session;

import org.mockito.MockitoSession;
import org.mockito.internal.framework.DefaultMockitoSession;
import org.mockito.internal.util.ConsoleMockitoLogger;
import org.mockito.quality.Strictness;
import org.mockito.session.MockitoSessionBuilder;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultMockitoSessionBuilder implements MockitoSessionBuilder {
    private Strictness strictness;
    private Object testClassInstance;

    @Override // org.mockito.session.MockitoSessionBuilder
    public MockitoSessionBuilder initMocks(Object obj) {
        this.testClassInstance = obj;
        return this;
    }

    @Override // org.mockito.session.MockitoSessionBuilder
    public MockitoSessionBuilder strictness(Strictness strictness) {
        this.strictness = strictness;
        return this;
    }

    @Override // org.mockito.session.MockitoSessionBuilder
    public MockitoSession startMocking() {
        Object obj = this.testClassInstance;
        if (obj == null) {
            obj = new Object();
        }
        Strictness strictness = this.strictness;
        if (strictness == null) {
            strictness = Strictness.STRICT_STUBS;
        }
        return new DefaultMockitoSession(obj, strictness, new ConsoleMockitoLogger());
    }
}
