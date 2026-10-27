package defpackage;

import android.content.Intent;
import java.util.function.Function;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ik2 implements Function {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ ik2(int i, String str) {
        this.a = i;
        this.b = str;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        int i = this.a;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                String str = (String) obj2;
                String str2 = (String) obj;
                return Boolean.valueOf(str2 == null || str == null || !str2.endsWith(str));
            case 1:
                String str3 = (String) obj2;
                StackTraceElement[] stackTraceElementArr = ((jo3) obj).b;
                int length = stackTraceElementArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        if (stackTraceElementArr[i2].getClassName().startsWith(str3)) {
                            z = true;
                        } else {
                            i2++;
                        }
                    }
                }
                return z ? null : 19;
            default:
                ((hj0) obj2).getClass();
                return hj0.n((Intent) obj);
        }
    }
}
