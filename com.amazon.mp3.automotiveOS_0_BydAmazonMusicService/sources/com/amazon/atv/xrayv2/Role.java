package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.amazon.identity.auth.device.actor.ActorManagerCommunication;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Role implements NamedEnum {
    ARTIST("artist"),
    PRODUCER("producer"),
    ACTOR(ActorManagerCommunication.SetActorMappingAction.KEY_ACTOR),
    DIRECTOR("director");

    private final String strValue;

    Role(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Role forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Role role : values()) {
            if (role.strValue.equals(str)) {
                return role;
            }
        }
        return null;
    }
}
