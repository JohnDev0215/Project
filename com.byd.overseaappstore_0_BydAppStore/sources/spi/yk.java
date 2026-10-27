package spi;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import com.byd.overseaappstore.module.ability.data.upper.PermissionDetail;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class yk {

    @NotNull
    public static final yk a = new yk();

    private yk() {
    }

    public final List<PermissionDetail> a(Context context) {
        pg pgVar = pg.a;
        Objects.requireNonNull(r3.a);
        Objects.requireNonNull(pgVar);
        ArrayList arrayList = new ArrayList();
        arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        arrayList.add(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        arrayList.add(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        arrayList.add(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
        ArrayList<PermissionDetail> arrayList2 = new ArrayList(CollectionsKt.b(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new PermissionDetail((String) it.next()));
        }
        ArrayList arrayList3 = new ArrayList();
        for (PermissionDetail permissionDetail : arrayList2) {
            if (context.checkSelfPermission(permissionDetail.getManifestName()) == -1) {
                ct.a(d.f("permission denied：", permissionDetail.getManifestName()), new Object[0]);
                arrayList3.add(permissionDetail);
            }
        }
        return arrayList3;
    }

    @NotNull
    public final String[] b() {
        pg pgVar = pg.a;
        Objects.requireNonNull(r3.a);
        Objects.requireNonNull(pgVar);
        return new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON, BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET, BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON};
    }
}
