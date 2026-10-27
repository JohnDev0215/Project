package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: GroupsProto.java */
/* JADX INFO: loaded from: classes3.dex */
public final class pt0 extends z<pt0, Object> implements bh1 {
    private static final pt0 DEFAULT_INSTANCE;
    public static final int HEADER_TEXT_FIELD_NUMBER = 1;
    private static volatile wv1<pt0> PARSER = null;
    public static final int RENDER_TYPE_FIELD_NUMBER = 3;
    public static final int SECTION_FIELD_NUMBER = 6;
    public static final int SIDE_TYPE_FIELD_NUMBER = 2;
    public static final int VISIBILITY_FIELD_NUMBER = 4;
    public static final int WHY_THIS_RESULT_REASON_FIELD_NUMBER = 5;
    private int bitField0_;
    private String headerText_ = "";
    private int renderType_;
    private int section_;
    private int sideType_;
    private int visibility_;
    private int whyThisResultReason_;

    static {
        pt0 pt0Var = new pt0();
        DEFAULT_INSTANCE = pt0Var;
        z.M(pt0.class, pt0Var);
    }

    public static pt0 O() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဌ\u0001\u0003ဌ\u0002\u0004ဌ\u0003\u0005ဋ\u0004\u0006ဌ\u0005", new Object[]{"bitField0_", "headerText_", "sideType_", nt0.a, "renderType_", mt0.a, "visibility_", ot0.a, "whyThisResultReason_", "section_", qt0.a});
            case 3:
                return new pt0();
            case 4:
                return new z.a(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                wv1<pt0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (pt0.class) {
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

    public final String P() {
        return this.headerText_;
    }

    public final int Q() {
        int iA = C0175b.a(this.section_);
        if (iA == 0) {
            return 1;
        }
        return iA;
    }
}
