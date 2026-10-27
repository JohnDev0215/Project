package com.byd.filemanager.ui.c;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileState;

/* JADX INFO: compiled from: ConfirmDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends com.byd.filemanager.ui.c.a<Object> {
    private TextView A0;
    private String y0;
    private String z0;

    /* JADX INFO: compiled from: ConfirmDialog.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3741a;

        static {
            int[] iArr = new int[FileState.values().length];
            f3741a = iArr;
            try {
                iArr[FileState.COMPRESSING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3741a[FileState.COPYING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3741a[FileState.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3741a[FileState.DELETING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static c d2(int i) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putInt("key1", i);
        cVar.q1(bundle);
        return cVar;
    }

    public static c e2(Context context) {
        int i = a.f3741a[com.byd.filemanager.f.h().g().ordinal()];
        int i2 = 33;
        if (i == 1) {
            i2 = 36;
        } else if (i != 2) {
            if (i == 3) {
                i2 = 34;
            } else if (i == 4) {
                i2 = 35;
            }
        }
        return d2(i2);
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int N1() {
        return 0;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void O1(View view) {
        this.A0 = (TextView) view.findViewById(R.id.tv_message);
        if (TextUtils.isEmpty(this.y0)) {
            return;
        }
        this.A0.setText(this.y0);
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void P1(Bundle bundle) {
    }

    @Override // com.byd.filemanager.ui.c.a
    protected String R1() {
        return this.z0;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int X1() {
        return this.x0 != 37 ? R.layout.dialog_confirm : R.layout.dialog_modify_type;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void b2(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = this.x0 != 37 ? R.dimen.dp_264 : R.dimen.dp_312;
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        Bundle bundleV = v();
        if (bundleV != null) {
            int i = bundleV.getInt("key1", 33);
            this.x0 = i;
            switch (i) {
                case 33:
                    this.y0 = Q(R.string.exit_copy);
                    break;
                case 34:
                    this.y0 = Q(R.string.exit_move);
                    break;
                case 35:
                    this.y0 = Q(R.string.exit_delete);
                    break;
                case 36:
                    this.y0 = Q(R.string.exit_compress);
                    break;
                case 37:
                    this.y0 = Q(R.string.modify_suffix);
                    this.z0 = Q(R.string.modify_file_type);
                    break;
            }
        }
    }
}
