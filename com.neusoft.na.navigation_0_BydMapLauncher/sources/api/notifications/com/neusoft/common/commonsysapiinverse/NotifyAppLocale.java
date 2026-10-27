package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ILocale;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyAppLocale extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private ILocale appLocale;

    public NotifyAppLocale() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.AppLocale");
    }

    public NotifyAppLocale(ILocale iLocale) {
        this();
        this.appLocale = iLocale;
    }

    public ILocale getAppLocale() {
        return this.appLocale;
    }

    public NotifyAppLocale setAppLocale(ILocale iLocale) {
        this.appLocale = iLocale;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public EProjectInterfaceType getProjectInterfaceType() {
        return EProjectInterfaceType.OneCore;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public String toString() {
        return getProjectInterfaceType().getGson().toJson(this);
    }
}
