package com.android.permissioncontroller.role.ui.specialappaccess;

import android.content.pm.ApplicationInfo;
import android.util.Pair;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.role.ui.RoleLiveData;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class MergeRoleLiveData extends MediatorLiveData<List<Pair<ApplicationInfo, Boolean>>> {
    private final RoleLiveData[] mLiveDatas;

    public MergeRoleLiveData(RoleLiveData... liveDatas) {
        this.mLiveDatas = liveDatas;
        int liveDatasLength = liveDatas.length;
        for (int i = 0; i < liveDatasLength; i++) {
            RoleLiveData liveData = this.mLiveDatas[i];
            addSource(liveData, new Observer() { // from class: com.android.permissioncontroller.role.ui.specialappaccess.-$$Lambda$MergeRoleLiveData$CJtSZN_GmGobjLADRNmpefsklvI
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$new$0$MergeRoleLiveData((List) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$new$0$MergeRoleLiveData(List roleItems) {
        onRoleChanged();
    }

    private void onRoleChanged() {
        ArrayList arrayList = new ArrayList();
        int liveDatasLength = this.mLiveDatas.length;
        for (int i = 0; i < liveDatasLength; i++) {
            RoleLiveData liveData = this.mLiveDatas[i];
            List<Pair<ApplicationInfo, Boolean>> qualifyingApplications = liveData.getValue();
            if (qualifyingApplications == null) {
                return;
            }
            arrayList.addAll(qualifyingApplications);
        }
        setValue(arrayList);
    }
}
