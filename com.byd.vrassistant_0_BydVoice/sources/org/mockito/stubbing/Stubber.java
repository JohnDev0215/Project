package org.mockito.stubbing;

/* JADX INFO: loaded from: classes4.dex */
public interface Stubber {
    Stubber doAnswer(Answer answer);

    Stubber doCallRealMethod();

    Stubber doNothing();

    Stubber doReturn(Object obj);

    Stubber doReturn(Object obj, Object... objArr);

    Stubber doThrow(Class<? extends Throwable> cls);

    Stubber doThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr);

    Stubber doThrow(Throwable... thArr);

    <T> T when(T t);
}
