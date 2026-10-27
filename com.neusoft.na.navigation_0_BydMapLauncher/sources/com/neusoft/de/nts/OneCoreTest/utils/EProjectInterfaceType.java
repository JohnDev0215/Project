package com.neusoft.de.nts.OneCoreTest.utils;

import androidx.core.os.EnvironmentCompat;
import com.google.gson.Gson;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.protocol.JsonProtocol;
import com.neusoft.de.nts.OneCoreTest.interfaces.IProtocol;
import java9.util.function.BiFunction;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'OneCore' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes2.dex */
public abstract class EProjectInterfaceType {
    private static final /* synthetic */ EProjectInterfaceType[] $VALUES;
    public static final EProjectInterfaceType OCTA;
    public static final EProjectInterfaceType OneCore;
    public static final EProjectInterfaceType Unknown;
    private final String component;
    private final String description;
    private final Gson gson;
    private final IProtocol<BaseCommand, String> jsonProtocol;
    private final BiFunction<BaseCommand, BaseCommand, Boolean> matchFunction;
    private final String websocketAddressKey;

    protected abstract Gson initGson();

    protected abstract BiFunction<BaseCommand, BaseCommand, Boolean> initMatchFunction();

    protected abstract String initWebsocketAddressKey();

    public static EProjectInterfaceType valueOf(String str) {
        return (EProjectInterfaceType) Enum.valueOf(EProjectInterfaceType.class, str);
    }

    public static EProjectInterfaceType[] values() {
        return (EProjectInterfaceType[]) $VALUES.clone();
    }

    static {
        String str = "OneCore";
        EProjectInterfaceType eProjectInterfaceType = new EProjectInterfaceType(str, 0, str, str) { // from class: com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType.1
            @Override // com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType
            protected String initWebsocketAddressKey() {
                return "NavigationWebsocketOneCore";
            }

            @Override // com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType
            protected Gson initGson() {
                return OneCoreJson.INSTANCE.get();
            }

            @Override // com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType
            protected BiFunction<BaseCommand, BaseCommand, Boolean> initMatchFunction() {
                return CommandMatchUtil.matchWithoutRoutingKey();
            }
        };
        OneCore = eProjectInterfaceType;
        String str2 = "Unknown";
        EProjectInterfaceType eProjectInterfaceType2 = new EProjectInterfaceType(str2, 1, str2, EnvironmentCompat.MEDIA_UNKNOWN) { // from class: com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType.2
            @Override // com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType
            protected String initWebsocketAddressKey() {
                return null;
            }

            @Override // com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType
            protected Gson initGson() {
                return new Gson();
            }

            @Override // com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType
            protected BiFunction<BaseCommand, BaseCommand, Boolean> initMatchFunction() {
                return CommandMatchUtil.matchWithoutRoutingKey();
            }
        };
        Unknown = eProjectInterfaceType2;
        EProjectInterfaceType eProjectInterfaceType3 = new EProjectInterfaceType("OCTA", 2, "OneCoreTestAutomation", "octa") { // from class: com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType.3
            @Override // com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType
            protected String initWebsocketAddressKey() {
                return null;
            }

            @Override // com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType
            protected Gson initGson() {
                return OneCoreJson.INSTANCE.get();
            }

            @Override // com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType
            protected BiFunction<BaseCommand, BaseCommand, Boolean> initMatchFunction() {
                return CommandMatchUtil.matchWithoutRoutingKey();
            }
        };
        OCTA = eProjectInterfaceType3;
        $VALUES = new EProjectInterfaceType[]{eProjectInterfaceType, eProjectInterfaceType2, eProjectInterfaceType3};
    }

    private EProjectInterfaceType(String str, int i, String str2, String str3) {
        this.description = str2;
        this.component = str3;
        Gson gsonInitGson = initGson();
        this.gson = gsonInitGson;
        this.websocketAddressKey = initWebsocketAddressKey();
        this.matchFunction = initMatchFunction();
        this.jsonProtocol = new JsonProtocol(gsonInitGson);
    }

    public Gson getGson() {
        return this.gson;
    }

    public IProtocol<BaseCommand, String> getProtocol() {
        return this.jsonProtocol;
    }

    public BiFunction<BaseCommand, BaseCommand, Boolean> getMatchFunction() {
        return this.matchFunction;
    }

    public String getWebsocketAddressKey() {
        return this.websocketAddressKey;
    }

    public String getDescription() {
        return this.description;
    }

    public String getComponent() {
        return this.component;
    }

    @Override // java.lang.Enum
    public String toString() {
        return getDescription();
    }
}
