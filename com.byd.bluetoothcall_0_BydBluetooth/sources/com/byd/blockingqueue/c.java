package com.byd.blockingqueue;

import com.byd.info.CallLogInfo;
import com.byd.info.ContactsInfo;
import com.byd.type_enum.Sync$SyncState;
import com.byd.type_enum.Sync$SyncType;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: PbapTask.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<ContactsInfo> f3216d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<CallLogInfo> f3217e = new ArrayList();
    private CallLogInfo f;
    private ContactsInfo g;
    private Sync$SyncState h;
    private Sync$SyncType i;
    private int j;
    private int k;
    private int l;

    public c(int i) {
        this.l = i;
    }

    private void g(int i) {
        switch (i) {
            case 0:
                c.a.b.c.t().n(this.i, this.h);
                break;
            case 1:
                c.a.b.c.t().j(this.f3216d);
                break;
            case 2:
                c.a.b.c.t().l(this.f3217e);
                break;
            case 3:
                c.a.b.c.t().k(this.f);
                break;
            case 4:
                c.a.b.c.t().m(this.g, this.j, this.k);
                break;
            case 5:
                c.a.b.b.f0().q(this.h);
                break;
            case 6:
                c.a.b.c.t().i(this.f3216d);
                break;
            case 7:
                c.a.b.c.t().h(this.j);
                break;
        }
    }

    public void e(List<CallLogInfo> list) {
        this.f3217e.clear();
        this.f3217e.addAll(list);
    }

    public void h(CallLogInfo callLogInfo) {
        this.f = callLogInfo;
    }

    @Override // com.byd.blockingqueue.b
    public void run() {
        g(this.l);
    }
}
