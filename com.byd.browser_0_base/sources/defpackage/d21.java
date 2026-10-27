package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: InterfaceRegistrar.java */
/* JADX INFO: loaded from: classes3.dex */
public interface d21<ParamType> {

    /* JADX INFO: compiled from: InterfaceRegistrar.java */
    public static class a<ParamType> {
        public static a<Void> b;
        public static a<WebContents> c;
        public static a<RenderFrameHost> d;
        public final ArrayList a = new ArrayList();

        public final void a(e21 e21Var, ParamType paramtype) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((d21) it.next()).a(e21Var, paramtype);
            }
        }
    }

    void a(e21 e21Var, ParamType paramtype);
}
