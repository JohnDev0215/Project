package mNTT77n7n.mNTT77n7n.mNTT77n7n.MnmmTM7.N7nMTTTTM;

import android.content.pm.ParceledListSlice;
import com.byd.dilink.DiLink;
import com.byd.dilink.app.am.IDiLinkParceledListSlice;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class a implements IDiLinkParceledListSlice {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ParceledListSlice f3140a;

    private a(ParceledListSlice parceledListSlice) {
        this.f3140a = parceledListSlice;
    }

    public static IDiLinkParceledListSlice a(ParceledListSlice parceledListSlice) {
        if (parceledListSlice == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("parceledListSlice is null");
        }
        return new a(parceledListSlice);
    }

    @Override // com.byd.dilink.app.am.IDiLinkParceledListSlice
    public int describeContents() {
        return this.f3140a.describeContents();
    }

    @Override // com.byd.dilink.app.am.IDiLinkParceledListSlice
    public List getList() {
        return this.f3140a.getList();
    }

    @Override // com.byd.dilink.app.am.IDiLinkParceledListSlice
    public void setInlineCountLimit(int i) {
        this.f3140a.setInlineCountLimit(i);
    }
}
