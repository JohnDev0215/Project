package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: Notifications.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pp1 extends z<pp1, Object> implements bh1 {
    private static final pp1 DEFAULT_INSTANCE;
    public static final int DESTINATION_URL_FIELD_NUMBER = 4;
    public static final int DISPLAY_TEXT_FIELD_NUMBER = 2;
    public static final int DISPLAY_TITLE_FIELD_NUMBER = 1;
    public static final int EXPANDED_VIEW_FIELD_NUMBER = 5;
    public static final int ICON_IMAGE_URL_FIELD_NUMBER = 3;
    private static volatile wv1<pp1> PARSER;
    private int bitField0_;
    private sp1 expandedView_;
    private String displayTitle_ = "";
    private String displayText_ = "";
    private String iconImageUrl_ = "";
    private String destinationUrl_ = "";

    static {
        pp1 pp1Var = new pp1();
        DEFAULT_INSTANCE = pp1Var;
        z.M(pp1.class, pp1Var);
    }

    public static pp1 O() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဉ\u0004", new Object[]{"bitField0_", "displayTitle_", "displayText_", "iconImageUrl_", "destinationUrl_", "expandedView_"});
            case NEW_MUTABLE_INSTANCE:
                return new pp1();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<pp1> bVar = PARSER;
                if (bVar == null) {
                    synchronized (pp1.class) {
                        bVar = PARSER;
                        if (bVar == null) {
                            bVar = new z.b<>(DEFAULT_INSTANCE);
                            PARSER = bVar;
                        }
                        break;
                    }
                }
                return bVar;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final sp1 P() {
        sp1 sp1Var = this.expandedView_;
        return sp1Var == null ? sp1.P() : sp1Var;
    }

    public final String Q() {
        return this.iconImageUrl_;
    }

    public final boolean S() {
        return (this.bitField0_ & 16) != 0;
    }

    public final boolean T() {
        return (this.bitField0_ & 4) != 0;
    }
}
