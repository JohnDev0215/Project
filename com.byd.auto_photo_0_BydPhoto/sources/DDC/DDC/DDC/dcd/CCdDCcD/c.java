package DDC.DDC.DDC.dcd.CCdDCcD;

import android.content.pm.ParceledListSlice;
import com.byd.dilink.DiLink;
import com.byd.dilink.app.am.IDiLinkParceledListSlice;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c implements IDiLinkParceledListSlice {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ParceledListSlice f39a;

    private c(ParceledListSlice parceledListSlice) {
        this.f39a = parceledListSlice;
    }

    public static IDiLinkParceledListSlice a(ParceledListSlice parceledListSlice) {
        if (parceledListSlice == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("parceledListSlice is null");
        }
        return new c(parceledListSlice);
    }

    @Override // com.byd.dilink.app.am.IDiLinkParceledListSlice
    public int describeContents() {
        return this.f39a.describeContents();
    }

    @Override // com.byd.dilink.app.am.IDiLinkParceledListSlice
    public List getList() {
        return this.f39a.getList();
    }

    @Override // com.byd.dilink.app.am.IDiLinkParceledListSlice
    public void setInlineCountLimit(int i) {
        this.f39a.setInlineCountLimit(i);
    }
}
