package H1;

import B2.O;
import F1.C0245d;
import F1.H;
import F1.InterfaceC0244c;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import h6.n;
import r.AbstractC2260s;

/* JADX INFO: loaded from: classes.dex */
public final class b extends InputConnectionWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ G2.a f2770a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(InputConnection inputConnection, G2.a aVar) {
        super(inputConnection, false);
        this.f2770a = aVar;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i7, Bundle bundle) {
        Bundle bundle2;
        InterfaceC0244c o;
        O o4 = inputContentInfo == null ? null : new O(new O(inputContentInfo, 16), 17);
        G2.a aVar = this.f2770a;
        if ((i7 & 1) != 0) {
            try {
                ((InputContentInfo) ((O) o4.f527m).f527m).requestPermission();
                InputContentInfo inputContentInfo2 = (InputContentInfo) ((O) o4.f527m).f527m;
                bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
                bundle2.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", inputContentInfo2);
            } catch (Exception e10) {
                n.u("InputConnectionCompat", "Can't insert content from IME; requestPermission() failed", e10);
            }
        } else {
            bundle2 = bundle;
        }
        ClipDescription description = ((InputContentInfo) ((O) o4.f527m).f527m).getDescription();
        O o9 = (O) o4.f527m;
        ClipData clipData = new ClipData(description, new ClipData.Item(((InputContentInfo) o9.f527m).getContentUri()));
        if (Build.VERSION.SDK_INT >= 31) {
            o = new O(clipData, 2);
        } else {
            C0245d c0245d = new C0245d();
            c0245d.f2326m = clipData;
            c0245d.f2327n = 2;
            o = c0245d;
        }
        o.D(((InputContentInfo) o9.f527m).getLinkUri());
        o.n(bundle2);
        if (H.d((AbstractC2260s) aVar.f2649m, o.a()) == null) {
            return true;
        }
        return super.commitContent(inputContentInfo, i7, bundle);
    }
}
