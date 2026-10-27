package com.neusoft.de.nts.OneCoreTest.interfaces;

import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;

/* JADX INFO: loaded from: classes2.dex */
public interface IProtocol<A extends BaseCommand, B> {
    <T extends BaseCommand> T from(B b, Class<T> cls);

    B to(A a);
}
