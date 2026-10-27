package GGW.GGW.GGW.W4H.hGHGh;

import android.content.pm.ParceledListSlice;
import com.byd.dilink.DiLink;
import com.byd.dilink.app.am.IDiLinkParceledListSlice;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b implements IDiLinkParceledListSlice {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ParceledListSlice f51a;

    private b(ParceledListSlice parceledListSlice) {
        this.f51a = parceledListSlice;
    }

    public static IDiLinkParceledListSlice a(ParceledListSlice parceledListSlice) {
        if (parceledListSlice == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("parceledListSlice is null");
        }
        return new b(parceledListSlice);
    }

    @Override // com.byd.dilink.app.am.IDiLinkParceledListSlice
    public int describeContents() {
        return this.f51a.describeContents();
    }

    @Override // com.byd.dilink.app.am.IDiLinkParceledListSlice
    public List getList() {
        return this.f51a.getList();
    }

    @Override // com.byd.dilink.app.am.IDiLinkParceledListSlice
    public void setInlineCountLimit(int i) {
        this.f51a.setInlineCountLimit(i);
    }
}
