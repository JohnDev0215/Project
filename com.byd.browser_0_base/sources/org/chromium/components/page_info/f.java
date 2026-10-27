package org.chromium.components.page_info;

import android.content.Context;
import java.util.ArrayList;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.permissions.AndroidPermissionDelegate;

/* JADX INFO: compiled from: PermissionParamsListBuilder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f {
    public final ArrayList a = new ArrayList();
    public final Context b;
    public final AndroidPermissionDelegate c;

    /* JADX INFO: compiled from: PermissionParamsListBuilder.java */
    public static final class a {
        public final String a;
        public final String b;
        public final int c;
        public final int d;

        public a(int i, int i2, String str, String str2) {
            this.a = str;
            this.b = str2;
            this.c = i;
            this.d = i2;
        }

        public final String toString() {
            return this.a;
        }
    }

    public f(Context context, WindowAndroid windowAndroid) {
        this.b = context;
        this.c = windowAndroid;
    }
}
