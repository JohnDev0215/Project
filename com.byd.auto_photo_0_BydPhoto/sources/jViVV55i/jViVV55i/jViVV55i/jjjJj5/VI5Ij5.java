package jViVV55i.jViVV55i.jViVV55i.jjjJj5;

import android.content.Context;
import android.os.BydPerfManager;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPrefManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0})
@ServiceImpl(service = IDiLinkPrefManager.class, singleton = true)
public class VI5Ij5 implements IDiLinkPrefManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BydPerfManager f3073a;

    public VI5Ij5(Context context) {
        if (DiLink.isPlatform(DiLink.VERSION_NAME_3_0, false)) {
            this.f3073a = (BydPerfManager) context.getSystemService("BYDMgmt");
        }
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventEnd(@Nullable String str) {
        this.f3073a.noteEventEnd(str);
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventStart(@Nullable String str) {
        this.f3073a.noteEventStart(str);
    }
}
