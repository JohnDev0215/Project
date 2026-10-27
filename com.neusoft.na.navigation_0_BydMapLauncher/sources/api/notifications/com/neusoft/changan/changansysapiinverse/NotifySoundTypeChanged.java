package api.notifications.com.neusoft.changan.changansysapiinverse;

import api.structs.com.neusoft.changan.SoundType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifySoundTypeChanged extends BaseNotification {
    private static final long serialVersionUID = -1478141320;

    @SerializedName("args")
    private SoundType soundTypeChanged;

    public NotifySoundTypeChanged() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.changan.ChangAnSysApiInverse.SoundTypeChanged");
    }

    public NotifySoundTypeChanged(SoundType soundType) {
        this();
        this.soundTypeChanged = soundType;
    }

    public SoundType getSoundTypeChanged() {
        return this.soundTypeChanged;
    }

    public NotifySoundTypeChanged setSoundTypeChanged(SoundType soundType) {
        this.soundTypeChanged = soundType;
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
