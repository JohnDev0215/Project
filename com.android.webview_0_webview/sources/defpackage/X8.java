package defpackage;

import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import java.util.ArrayList;
import java.util.List;
import org.chromium.content_public.browser.NavigationEntry;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class X8 extends WebBackForwardList {
    public final List s;
    public final int t;

    public X8(VD vd) {
        this.t = vd.b;
        this.s = new ArrayList(vd.a());
        for (int i = 0; i < vd.a(); i++) {
            this.s.add(new Y8((NavigationEntry) vd.a.get(i)));
        }
    }

    @Override // android.webkit.WebBackForwardList
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public synchronized X8 clone() {
        ArrayList arrayList;
        arrayList = new ArrayList(getSize());
        for (int i = 0; i < getSize(); i++) {
            arrayList.add(((Y8) this.s.get(i)).clone());
        }
        return new X8(arrayList, this.t);
    }

    @Override // android.webkit.WebBackForwardList
    public synchronized int getCurrentIndex() {
        return this.t;
    }

    @Override // android.webkit.WebBackForwardList
    public synchronized WebHistoryItem getCurrentItem() {
        if (getSize() == 0) {
            return null;
        }
        return getItemAtIndex(getCurrentIndex());
    }

    @Override // android.webkit.WebBackForwardList
    public synchronized WebHistoryItem getItemAtIndex(int i) {
        if (i >= 0) {
            if (i < getSize()) {
                return (WebHistoryItem) this.s.get(i);
            }
        }
        return null;
    }

    @Override // android.webkit.WebBackForwardList
    public synchronized int getSize() {
        return this.s.size();
    }

    public X8(List list, int i) {
        this.s = list;
        this.t = i;
    }
}
