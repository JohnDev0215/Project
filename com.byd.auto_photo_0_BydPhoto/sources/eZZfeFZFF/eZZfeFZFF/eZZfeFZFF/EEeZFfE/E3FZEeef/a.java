package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.EEeZFfE.E3FZEeef;

import android.content.pm.ParceledListSlice;
import com.byd.dilink.DiLink;
import com.byd.dilink.app.am.IDiLinkParceledListSlice;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class a implements IDiLinkParceledListSlice {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ParceledListSlice f3023a;

    private a(ParceledListSlice parceledListSlice) {
        this.f3023a = parceledListSlice;
    }

    public static IDiLinkParceledListSlice a(ParceledListSlice parceledListSlice) {
        if (parceledListSlice == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("parceledListSlice is null");
        }
        return new a(parceledListSlice);
    }

    @Override // com.byd.dilink.app.am.IDiLinkParceledListSlice
    public int describeContents() {
        return this.f3023a.describeContents();
    }

    @Override // com.byd.dilink.app.am.IDiLinkParceledListSlice
    public List getList() {
        return this.f3023a.getList();
    }

    @Override // com.byd.dilink.app.am.IDiLinkParceledListSlice
    public void setInlineCountLimit(int i) {
        this.f3023a.setInlineCountLimit(i);
    }
}
