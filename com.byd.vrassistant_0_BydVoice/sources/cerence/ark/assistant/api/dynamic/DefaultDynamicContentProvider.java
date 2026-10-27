package cerence.ark.assistant.api.dynamic;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.CancellationSignal;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.core.LocalMusicQuery;
import cerence.ark.assistant.core.phone.PhoneDatabase;
import cerence.ark.assistant.library.Utils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DefaultDynamicContentProvider implements IDynamicContentProvider {
    private static final String TAG = "DefaultDynamicContentProvider";
    private final PhoneDatabase mPhoneDatabase = new PhoneDatabase();

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
    }

    @Override // cerence.ark.assistant.api.dynamic.IDynamicContentProvider
    public List<Contact> getContacts(CancellationSignal cancellationSignal) {
        return this.mPhoneDatabase.getContacts(Utils.getContext(), cancellationSignal);
    }

    @Override // cerence.ark.assistant.api.dynamic.IDynamicContentProvider
    public List<Music> getMusic(CancellationSignal cancellationSignal) {
        return LocalMusicQuery.getMusic(Utils.getContext(), cancellationSignal);
    }

    @Override // cerence.ark.assistant.api.dynamic.IDynamicContentProvider
    public List<App> getApps(CancellationSignal cancellationSignal) {
        CharSequence charSequenceLoadLabel;
        PackageManager packageManager = Utils.getContext().getPackageManager();
        ArrayList arrayList = new ArrayList();
        if (packageManager == null) {
            Logger.error(TAG, "getApps: packageManager is null");
            return arrayList;
        }
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        if (installedPackages == null || installedPackages.isEmpty()) {
            Logger.error(TAG, "getApps: installedPackages is empty");
            return arrayList;
        }
        for (int i = 0; i < installedPackages.size(); i++) {
            if (cancellationSignal != null && cancellationSignal.isCanceled()) {
                return arrayList;
            }
            PackageInfo packageInfo = installedPackages.get(i);
            if (packageInfo != null && packageInfo.applicationInfo != null && (charSequenceLoadLabel = packageInfo.applicationInfo.loadLabel(packageManager)) != null) {
                App app = new App();
                app.setId(i);
                app.setName(charSequenceLoadLabel.toString());
                arrayList.add(app);
            }
        }
        return arrayList;
    }

    @Override // cerence.ark.assistant.api.dynamic.IDynamicContentProvider
    public List<RadioStation> getRadioStations(CancellationSignal cancellationSignal) {
        return new ArrayList();
    }

    @Override // cerence.ark.assistant.api.dynamic.IDynamicContentProvider
    public List<String> getDynamicContent(String str, CancellationSignal cancellationSignal) {
        return new ArrayList();
    }
}
