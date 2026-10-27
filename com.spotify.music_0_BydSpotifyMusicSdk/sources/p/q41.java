package p;

import android.content.ClipData;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: loaded from: classes.dex */
public final class q41 extends InputConnectionWrapper {
    public final /* synthetic */ p41 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q41(InputConnection inputConnection, p41 p41Var) {
        super(inputConnection, false);
        this.a = p41Var;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
        Bundle bundle2;
        hk0 hk0Var = inputContentInfo == null ? null : new hk0(10, new p01(inputContentInfo));
        p41 p41Var = this.a;
        p41Var.getClass();
        if ((i & 1) != 0) {
            try {
                ((r41) hk0Var.e).n();
                Parcelable parcelable = (Parcelable) ((r41) hk0Var.e).h();
                bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
                bundle2.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", parcelable);
            } catch (Exception unused) {
            }
        } else {
            bundle2 = bundle;
        }
        oh1 oh1Var = new oh1(new ClipData(((r41) hk0Var.e).getDescription(), new ClipData.Item(((r41) hk0Var.e).j())), 2);
        ((gz) oh1Var.e).b(((r41) hk0Var.e).o());
        ((gz) oh1Var.e).a(bundle2);
        if (zt2.g(p41Var.c, ((gz) oh1Var.e).build()) == null) {
            return true;
        }
        return super.commitContent(inputContentInfo, i, bundle);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean performPrivateCommand(String str, Bundle bundle) {
        return super.performPrivateCommand(str, bundle);
    }
}
