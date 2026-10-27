package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ContactsPickerListener.java */
/* JADX INFO: loaded from: classes3.dex */
public interface hy {

    /* JADX INFO: compiled from: ContactsPickerListener.java */
    public static class a {
        public final List<String> a;
        public final List<String> b;
        public final List<String> c;
        public final ArrayList d;
        public final ArrayList e;

        public a(List<String> list, List<String> list2, List<String> list3, List<ky1> list4, List<fy> list5) {
            this.a = list;
            this.b = list2;
            this.c = list3;
            if (list4 != null) {
                this.d = new ArrayList();
                Iterator<ky1> it = list4.iterator();
                while (it.hasNext()) {
                    this.d.add(it.next().b());
                }
            } else {
                this.d = null;
            }
            if (list5 == null) {
                this.e = null;
                return;
            }
            this.e = new ArrayList();
            Iterator<fy> it2 = list5.iterator();
            while (it2.hasNext()) {
                this.e.add(it2.next().b());
            }
        }
    }
}
