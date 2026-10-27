package com.spotify.rcs.admin.grpc.v0;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.fd0;
import p.i01;
import p.qd0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class PropertyDefinition$Identifier extends i implements yj1 {
    private static final PropertyDefinition$Identifier DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int SCOPE_FIELD_NUMBER = 1;
    private String scope_ = "";
    private String name_ = "";

    static {
        PropertyDefinition$Identifier propertyDefinition$Identifier = new PropertyDefinition$Identifier();
        DEFAULT_INSTANCE = propertyDefinition$Identifier;
        i.registerDefaultInstance(PropertyDefinition$Identifier.class, propertyDefinition$Identifier);
    }

    private PropertyDefinition$Identifier() {
    }

    public static PropertyDefinition$Identifier f() {
        return DEFAULT_INSTANCE;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        fd0 fd0Var = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"scope_", "name_"});
            case 3:
                return new PropertyDefinition$Identifier();
            case 4:
                return new qd0(fd0Var);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (PropertyDefinition$Identifier.class) {
                        try {
                            i01Var = PARSER;
                            if (i01Var == null) {
                                i01Var = new i01(DEFAULT_INSTANCE);
                                PARSER = i01Var;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return i01Var;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final String g() {
        return this.scope_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final String getName() {
        return this.name_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
