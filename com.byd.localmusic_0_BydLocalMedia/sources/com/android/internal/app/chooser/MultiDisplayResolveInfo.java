package com.android.internal.app.chooser;

import android.app.Activity;
import android.os.Bundle;
import android.os.UserHandle;
import com.android.internal.app.ResolverActivity;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class MultiDisplayResolveInfo extends DisplayResolveInfo {
    final DisplayResolveInfo mBaseInfo;
    private int mSelected;
    List<DisplayResolveInfo> mTargetInfos;

    @Override // com.android.internal.app.chooser.DisplayResolveInfo, com.android.internal.app.chooser.TargetInfo
    public CharSequence getExtendedInfo() {
        return null;
    }

    public MultiDisplayResolveInfo(String str, DisplayResolveInfo displayResolveInfo) {
        super(displayResolveInfo);
        ArrayList arrayList = new ArrayList();
        this.mTargetInfos = arrayList;
        this.mSelected = -1;
        this.mBaseInfo = displayResolveInfo;
        arrayList.add(displayResolveInfo);
    }

    public void addTarget(DisplayResolveInfo displayResolveInfo) {
        this.mTargetInfos.add(displayResolveInfo);
    }

    public List<DisplayResolveInfo> getTargets() {
        return this.mTargetInfos;
    }

    public void setSelected(int i) {
        this.mSelected = i;
    }

    public DisplayResolveInfo getSelectedTarget() {
        if (hasSelected()) {
            return this.mTargetInfos.get(this.mSelected);
        }
        return null;
    }

    public boolean hasSelected() {
        return this.mSelected >= 0;
    }

    @Override // com.android.internal.app.chooser.DisplayResolveInfo, com.android.internal.app.chooser.TargetInfo
    public boolean start(Activity activity, Bundle bundle) {
        return this.mTargetInfos.get(this.mSelected).start(activity, bundle);
    }

    @Override // com.android.internal.app.chooser.DisplayResolveInfo, com.android.internal.app.chooser.TargetInfo
    public boolean startAsCaller(ResolverActivity resolverActivity, Bundle bundle, int i) {
        return this.mTargetInfos.get(this.mSelected).startAsCaller(resolverActivity, bundle, i);
    }

    @Override // com.android.internal.app.chooser.DisplayResolveInfo, com.android.internal.app.chooser.TargetInfo
    public boolean startAsUser(Activity activity, Bundle bundle, UserHandle userHandle) {
        return this.mTargetInfos.get(this.mSelected).startAsUser(activity, bundle, userHandle);
    }
}
