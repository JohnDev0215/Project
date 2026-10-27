package com.byd.cleardata.ui.e;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.byd.cleardata.R;

/* JADX INFO: loaded from: classes.dex */
public class d extends com.byd.cleardata.ui.e.a<Object> {
    private TextView A0;
    private String y0;
    private String z0;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2801a;

        static {
            int[] iArr = new int[com.byd.cleardata.d.e.values().length];
            f2801a = iArr;
            try {
                iArr[com.byd.cleardata.d.e.COMPRESSING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2801a[com.byd.cleardata.d.e.COPYING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2801a[com.byd.cleardata.d.e.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2801a[com.byd.cleardata.d.e.DELETING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static d W1(int i) {
        d dVar = new d();
        Bundle bundle = new Bundle();
        bundle.putInt("key1", i);
        dVar.q1(bundle);
        return dVar;
    }

    public static d X1(Context context) {
        int i = a.f2801a[com.byd.cleardata.b.h().g().ordinal()];
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
        return W1(i2);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int H1() {
        return 0;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void I1(View view) {
        this.A0 = (TextView) view.findViewById(R.id.tv_message);
        if (TextUtils.isEmpty(this.y0)) {
            return;
        }
        this.A0.setText(this.y0);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected String L1() {
        return this.z0;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int Q1() {
        return this.x0 != 37 ? R.layout.dialog_confirm : R.layout.dialog_modify_type;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void U1(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = this.x0 != 37 ? R.dimen.dp_264 : R.dimen.dp_312;
    }

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        int i;
        super.k0(bundle);
        Bundle bundleW = w();
        if (bundleW != null) {
            int i2 = bundleW.getInt("key1", 33);
            this.x0 = i2;
            switch (i2) {
                case 33:
                    i = R.string.exit_copy;
                    break;
                case 34:
                    i = R.string.exit_move;
                    break;
                case 35:
                    i = R.string.exit_delete;
                    break;
                case 36:
                    i = R.string.exit_compress;
                    break;
                case 37:
                    this.y0 = R(R.string.modify_suffix);
                    this.z0 = R(R.string.modify_file_type);
                    return;
                default:
                    return;
            }
            this.y0 = R(i);
        }
    }
}
