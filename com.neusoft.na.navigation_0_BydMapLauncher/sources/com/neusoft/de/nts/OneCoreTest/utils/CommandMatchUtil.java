package com.neusoft.de.nts.OneCoreTest.utils;

import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.transportMessages.BaseProtocolError;
import java.util.Objects;
import java9.util.function.BiFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class CommandMatchUtil {
    private static final Logger logger = Logger.getLogger((Class<?>) CommandMatchUtil.class);

    private CommandMatchUtil() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <A extends BaseCommand, B extends BaseCommand> boolean standardMatch(A a, B b, boolean z) {
        if (a == null || b == null) {
            return false;
        }
        if ((a.getComponent() != null || b.getComponent() != null) && !Objects.equals(a.getComponent(), b.getComponent())) {
            return false;
        }
        if ((a.getCommand() != null || b.getCommand() != null) && !Objects.equals(a.getCommand(), b.getCommand())) {
            return false;
        }
        if (!z) {
            return true;
        }
        if (a.getRoutingKey() == null && b.getRoutingKey() == null) {
            return true;
        }
        return Objects.equals(a.getRoutingKey(), b.getRoutingKey());
    }

    public static BiFunction<BaseCommand, BaseCommand, Boolean> matchWithoutRoutingKey() {
        return new BiFunction<BaseCommand, BaseCommand, Boolean>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.CommandMatchUtil.1
            @Override // java9.util.function.BiFunction
            public Boolean apply(BaseCommand baseCommand, BaseCommand baseCommand2) {
                return Boolean.valueOf(CommandMatchUtil.standardMatch(baseCommand, baseCommand2, false));
            }
        };
    }

    public static boolean matchTransportError(BaseProtocolError baseProtocolError) {
        return (baseProtocolError.getMessageType() != EMessageType.Error || baseProtocolError.getBuiltinError() == null || baseProtocolError.getCommand() == null) ? false : true;
    }
}
