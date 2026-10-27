package com.neusoft.de.nts.OneCoreTest.communication.context;

import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class GenericCommContext<RQ, RS, TS, TR> {
    private Executor executor;
    private RS expectedResponse;
    private RQ request;
    private ITransport<TS, TR> transport;
    private Executor transportExecutor;

    public GenericCommContext(ITransport<TS, TR> iTransport, RQ rq, RS rs, Executor executor) {
        this(iTransport, rq, rs, executor, null);
    }

    public GenericCommContext(ITransport<TS, TR> iTransport, RQ rq, RS rs, Executor executor, Executor executor2) {
        this.transport = iTransport;
        this.request = rq;
        this.expectedResponse = rs;
        this.executor = executor;
        this.transportExecutor = executor2;
    }

    public ITransport<TS, TR> getTransport() {
        return this.transport;
    }

    public void setTransport(ITransport<TS, TR> iTransport) {
        this.transport = iTransport;
    }

    public RQ getRequest() {
        return this.request;
    }

    public void setRequest(RQ rq) {
        this.request = rq;
    }

    public RS getExpectedResponse() {
        return this.expectedResponse;
    }

    public void setExpectedResponse(RS rs) {
        this.expectedResponse = rs;
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public Executor getTransportExecutor() {
        return this.transportExecutor;
    }

    public void setTransportExecutor(Executor executor) {
        this.transportExecutor = executor;
    }
}
