package com.byd.engineermode.db;

import com.byd.engineermode.bean.ElectricBean;
import java.util.List;

/* JADX INFO: compiled from: ElectricContactDao.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public abstract List<com.byd.engineermode.bean.a> a();

    public void b(TestDatabase testDatabase, com.byd.engineermode.bean.a aVar) {
        com.byd.engineermode.utils.a.a("insert");
        long jA = testDatabase.u().a(aVar.a());
        for (ElectricBean electricBean : aVar.b()) {
            electricBean.o(jA);
            electricBean.n(testDatabase.s().a(electricBean));
        }
    }
}
