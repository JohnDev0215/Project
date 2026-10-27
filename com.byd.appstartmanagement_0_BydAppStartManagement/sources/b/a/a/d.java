package b.a.a;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.byd.appstartmanagement.R;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: compiled from: SystemOperator.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static Drawable a(String str, PackageInfo packageInfo, PackageManager packageManager) {
        str.hashCode();
        switch (str) {
            case "com.byd.filemanager":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_filemanager);
            case "com.byd.hvac":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_airconditioning);
            case "com.byd.vrassistant.xf":
            case "com.byd.vrsettings":
            case "com.byd.vrdownload.xf":
                return b.a.b.d.a.f181b == 1 ? SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_autovoice_deza) : SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_autovoice);
            case "com.byd.mycar":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_mycar);
            case "com.byd.carsettings":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_carsettings);
            case "com.byd.auto_camera":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_camera);
            case "com.byd.trafficmonitor":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_trafficmonitor);
            case "com.byd.auto_photo":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_photo);
            case "com.byd.automultipletheme":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_multipletheme);
            case "com.byd.bluetoothcall":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_bluetoothcall);
            case "com.byd.avm":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_avc);
            case "com.byd.localmusic":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_mediacenter);
            case "com.byd.smarttravel":
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_smarttravel);
            default:
                return packageInfo.applicationInfo.loadIcon(packageManager);
        }
    }
}
