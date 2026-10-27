package com.spotify.eventsender;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import java.util.ArrayList;
import java.util.List;
import p.a2;
import p.ds1;
import p.i01;
import p.iy0;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentsContainer extends i implements yj1 {
    private static final FragmentsContainer DEFAULT_INSTANCE;
    public static final int FRAGMENT_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private v51 fragment_ = i.emptyProtobufList();

    static {
        FragmentsContainer fragmentsContainer = new FragmentsContainer();
        DEFAULT_INSTANCE = fragmentsContainer;
        i.registerDefaultInstance(FragmentsContainer.class, fragmentsContainer);
    }

    private FragmentsContainer() {
    }

    public static void e(FragmentsContainer fragmentsContainer, Fragment fragment) {
        fragmentsContainer.getClass();
        v51 v51Var = fragmentsContainer.fragment_;
        if (!((a2) v51Var).c) {
            fragmentsContainer.fragment_ = i.mutableCopy(v51Var);
        }
        fragmentsContainer.fragment_.add(fragment);
    }

    public static void f(FragmentsContainer fragmentsContainer, ArrayList arrayList) {
        v51 v51Var = fragmentsContainer.fragment_;
        if (!((a2) v51Var).c) {
            fragmentsContainer.fragment_ = i.mutableCopy(v51Var);
        }
        a.addAll((Iterable) arrayList, (List) fragmentsContainer.fragment_);
    }

    public static FragmentsContainer g() {
        return DEFAULT_INSTANCE;
    }

    public static iy0 i() {
        return (iy0) DEFAULT_INSTANCE.createBuilder();
    }

    public static FragmentsContainer j(byte[] bArr) {
        return (FragmentsContainer) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"fragment_", Fragment.class});
            case 3:
                return new FragmentsContainer();
            case 4:
                return new iy0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (FragmentsContainer.class) {
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

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final v51 h() {
        return this.fragment_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
