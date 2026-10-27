package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyPlaySoundEffect extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private DummyStatus playSoundEffect;

    public NotifyPlaySoundEffect() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.PlaySoundEffect");
    }

    public NotifyPlaySoundEffect(DummyStatus dummyStatus) {
        this();
        this.playSoundEffect = dummyStatus;
    }

    public DummyStatus getPlaySoundEffect() {
        return this.playSoundEffect;
    }

    public NotifyPlaySoundEffect setPlaySoundEffect(DummyStatus dummyStatus) {
        this.playSoundEffect = dummyStatus;
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
