package com.byd.vrassistant.customizedimpl;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.CancellationSignal;
import cerence.ark.assistant.api.dynamic.App;
import cerence.ark.assistant.api.dynamic.Contact;
import cerence.ark.assistant.api.dynamic.DefaultDynamicContentProvider;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import cerence.ark.assistant.api.dynamic.Music;
import cerence.ark.assistant.api.dynamic.RadioStation;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.phone.PhoneRequestsEnum;
import cerence.ark.assistant.library.Utils;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.AppHelper;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.utils.NavUtil;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BydDynamicContentProvider implements IDynamicContentProvider {
    private static final String TAG = "NEU_DynamicProvider";
    private DefaultDynamicContentProvider defaultDynamicContentProvider;

    public BydDynamicContentProvider() {
        this.defaultDynamicContentProvider = null;
        this.defaultDynamicContentProvider = new DefaultDynamicContentProvider();
    }

    @Override // cerence.ark.assistant.api.dynamic.IDynamicContentProvider
    public List<Contact> getContacts(CancellationSignal cancellationSignal) {
        RLog.i("NEU_PHONEProvider", PhoneRequestsEnum.GET_CONTACTS);
        List<Contact> contacts = PhoneHelper.getInstance().readContacts(cancellationSignal);
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
        RLog.i("NEU_PHONEProvider", "getContacts size = " + contacts.size());
        return contacts;
    }

    @Override // cerence.ark.assistant.api.dynamic.IDynamicContentProvider
    public List<Music> getMusic(CancellationSignal cancellationSignal) {
        RLog.i(TAG, "getMusic");
        return this.defaultDynamicContentProvider.getMusic(cancellationSignal);
    }

    @Override // cerence.ark.assistant.api.dynamic.IDynamicContentProvider
    public List<App> getApps(CancellationSignal cancellationSignal) {
        RLog.i(TAG, "getApps");
        PackageManager packageManager = Utils.getContext().getPackageManager();
        ArrayList arrayList = new ArrayList();
        if (packageManager == null) {
            Logger.error(TAG, "getApps: packageManager is null");
            return arrayList;
        }
        String countryCode = CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext());
        String[] strArr = new String[Constant.VoiceControlApps8155.length + 1];
        System.arraycopy(Constant.VoiceControlApps8155, 0, strArr, 0, Constant.VoiceControlApps8155.length);
        strArr[Constant.VoiceControlApps8155.length] = NavUtil.codeToPackage(countryCode);
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        if (installedPackages != null && !installedPackages.isEmpty()) {
            for (int i = 0; i < installedPackages.size(); i++) {
                if (cancellationSignal != null && cancellationSignal.isCanceled()) {
                    return arrayList;
                }
                PackageInfo packageInfo = installedPackages.get(i);
                CharSequence charSequenceLoadLabel = packageInfo.applicationInfo.loadLabel(packageManager);
                int iIndexOf = StringUtils.indexOf(strArr, packageInfo.packageName);
                if (packageInfo != null && iIndexOf > -1 && packageInfo.applicationInfo != null && charSequenceLoadLabel != null) {
                    App app = new App();
                    app.setId(i);
                    app.setName(charSequenceLoadLabel.toString());
                    arrayList.add(app);
                }
            }
            Collection<String> genericsLabel = AppHelper.getInstance().getGenericsLabel();
            if (genericsLabel != null) {
                int i2 = 200;
                for (String str : genericsLabel) {
                    App app2 = new App();
                    int i3 = i2 + 1;
                    app2.setId(i2);
                    if (str.contains("dab plus")) {
                        str = "DAB+";
                    } else if (str.equals("bluetooth phone")) {
                        str = "Bluetooth phone";
                    } else if (str.equals("spotify")) {
                        str = "Spotify";
                    } else if (str.equals("carplay")) {
                        str = "CarPlay";
                    }
                    app2.setName(str);
                    RLog.s_log(TAG, "GenericsLabel id=" + i3 + ",name=" + str);
                    arrayList.add(app2);
                    i2 = i3;
                }
            }
            return arrayList;
        }
        Logger.error(TAG, "getApps: installedPackages is empty");
        return arrayList;
    }

    @Override // cerence.ark.assistant.api.dynamic.IDynamicContentProvider
    public List<RadioStation> getRadioStations(CancellationSignal cancellationSignal) {
        RLog.i(TAG, "getRadioStations");
        return new ArrayList();
    }

    @Override // cerence.ark.assistant.api.dynamic.IDynamicContentProvider
    public List<String> getDynamicContent(String str, CancellationSignal cancellationSignal) {
        RLog.i(TAG, "getDynamicContent");
        return this.defaultDynamicContentProvider.getDynamicContent(str, cancellationSignal);
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        RLog.i(TAG, "BydDynamicContentProvider activiate");
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
        RLog.i(TAG, "BydDynamicContentProvider deactivate");
    }
}
