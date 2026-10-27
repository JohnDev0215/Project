package com.neusoft.de.nts.OneCoreTest.communication;

import com.neusoft.de.nts.OneCoreTest.communication.context.AMQPCommClassContext;
import com.neusoft.de.nts.OneCoreTest.communication.context.GenericCommContext;
import com.neusoft.de.nts.OneCoreTest.communication.context.POJOCommClassContext;
import com.neusoft.de.nts.OneCoreTest.communication.context.WSCommClassContext;
import com.neusoft.de.nts.OneCoreTest.communication.future.AMQPClassFuture;
import com.neusoft.de.nts.OneCoreTest.communication.future.OneCoreClassFuture;
import com.neusoft.de.nts.OneCoreTest.communication.future.POJOClassFuture;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.FutureUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java9.util.concurrent.CompletableFuture;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes2.dex */
public final class Communication {
    private Communication() {
    }

    public static <RS extends BaseResponse> CompletableFuture<RS> request(WSCommClassContext<RS> wSCommClassContext) {
        return (CompletableFuture<RS>) new OneCoreClassFuture(wSCommClassContext, false).getFuture();
    }

    public static <RS extends BaseResponse> CompletableFuture<RS> request(AMQPCommClassContext<RS> aMQPCommClassContext) {
        return (CompletableFuture<RS>) new AMQPClassFuture(aMQPCommClassContext).getFuture();
    }

    public static <RS extends BaseResponse> CompletableFuture<RS> request(POJOCommClassContext<RS> pOJOCommClassContext) {
        return (CompletableFuture<RS>) new POJOClassFuture(pOJOCommClassContext, false).getFuture();
    }

    public static CompletableFuture<?> request(GenericCommContext genericCommContext) {
        if (genericCommContext instanceof WSCommClassContext) {
            return request((WSCommClassContext) genericCommContext);
        }
        if (genericCommContext instanceof AMQPCommClassContext) {
            return request((AMQPCommClassContext) genericCommContext);
        }
        if (genericCommContext instanceof POJOCommClassContext) {
            return request((POJOCommClassContext) genericCommContext);
        }
        throw new IllegalArgumentException("Unsupported context type " + genericCommContext.getClass().getName());
    }

    public static CompletableFuture<Void> requestAll(ICommunicationContextUtil iCommunicationContextUtil, BaseRequest... baseRequestArr) {
        return requestList(iCommunicationContextUtil, Arrays.asList(baseRequestArr));
    }

    public static CompletableFuture<Void> requestList(ICommunicationContextUtil iCommunicationContextUtil, List<BaseRequest> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<BaseRequest> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(request(iCommunicationContextUtil.createFromClass(it.next())));
        }
        return CompletableFuture.allOf((CompletableFuture[]) arrayList.toArray(new CompletableFuture[0])).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.Communication.1
            @Override // java9.util.function.Function
            public Void apply(Throwable th) {
                return (Void) FutureUtil.logAndThrow(th);
            }
        });
    }
}
