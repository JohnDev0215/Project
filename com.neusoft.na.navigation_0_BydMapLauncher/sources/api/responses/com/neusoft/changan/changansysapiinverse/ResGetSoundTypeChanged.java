package api.responses.com.neusoft.changan.changansysapiinverse;

import api.structs.com.neusoft.changan.SoundType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetSoundTypeChanged extends BaseResponse {
    private static final long serialVersionUID = -1478141320;

    @SerializedName("args")
    private SoundType soundTypeChanged;

    public ResGetSoundTypeChanged() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.changan.ChangAnSysApiInverse.GetSoundTypeChanged");
    }

    public ResGetSoundTypeChanged(SoundType soundType) {
        this();
        this.soundTypeChanged = soundType;
    }

    public SoundType getSoundTypeChanged() {
        return this.soundTypeChanged;
    }

    public ResGetSoundTypeChanged setSoundTypeChanged(SoundType soundType) {
        this.soundTypeChanged = soundType;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.soundTypeChanged = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetSoundTypeChanged)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()));
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
