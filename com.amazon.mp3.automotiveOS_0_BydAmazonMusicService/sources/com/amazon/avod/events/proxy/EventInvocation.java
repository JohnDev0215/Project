package com.amazon.avod.events.proxy;

import android.os.Handler;
import com.google.common.base.Preconditions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class EventInvocation {
    private final Object[] mArgs;
    private final Method mMethod;

    @SuppressFBWarnings(justification = "We are consciously passing in a mutable array because enforcing immutability would require copying the whole array at least once, a performance burden we wish to avoid", value = {"EI_EXPOSE_REP"})
    public EventInvocation(Method method, Object[] objArr) {
        this.mMethod = (Method) Preconditions.checkNotNull(method);
        this.mArgs = objArr;
    }

    public void invoke(final Object obj, Handler handler) {
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.amazon.avod.events.proxy.EventInvocation.1
                @Override // java.lang.Runnable
                public void run() {
                    EventInvocation.this.doInvoke(obj);
                }
            });
        } else {
            doInvoke(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doInvoke(Object obj) {
        try {
            this.mMethod.invoke(obj, this.mArgs);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3.getCause());
        }
    }
}
