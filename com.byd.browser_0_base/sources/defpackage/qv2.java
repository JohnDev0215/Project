package defpackage;

import com.google.protobuf.c0;

/* JADX INFO: compiled from: StudyOuterClass.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qv2 implements c0.b {
    public static final qv2 a = new qv2();

    @Override // com.google.protobuf.c0.b
    public final boolean a(int i) {
        char c;
        switch (i) {
            case 0:
                c = 1;
                break;
            case 1:
                c = 2;
                break;
            case 2:
                c = 3;
                break;
            case 3:
                c = 4;
                break;
            case 4:
                c = 5;
                break;
            case 5:
                c = 6;
                break;
            case 6:
                c = 7;
                break;
            case 7:
                c = '\b';
                break;
            case 8:
                c = '\t';
                break;
            case 9:
                c = '\n';
                break;
            default:
                c = 0;
                break;
        }
        return c != 0;
    }
}
