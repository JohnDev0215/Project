package com.neusoft.de.nts.OneCoreTest.communication.messageModel;

import com.google.gson.Gson;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class BaseCommand implements Serializable {
    private static Gson gson = new Gson();
    private static final long serialVersionUID = -1;
    private String command;
    private String component;
    private EMessageType messageType;
    private String routingKey;
    private BaseToken token;

    public BaseCommand() {
    }

    public BaseCommand(String str) {
        setComponent("nav");
        setCommand(str);
    }

    public BaseCommand(String str, String str2) {
        this.component = str;
        this.command = str2;
    }

    public BaseCommand(EMessageType eMessageType, String str, String str2) {
        this.messageType = eMessageType;
        this.component = str;
        this.command = str2;
    }

    public String getComponent() {
        return this.component;
    }

    public void setComponent(String str) {
        this.component = str;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public String getRoutingKey() {
        return this.routingKey;
    }

    public void setRoutingKey(String str) {
        this.routingKey = str;
    }

    public EMessageType getMessageType() {
        return this.messageType;
    }

    public void setMessageType(EMessageType eMessageType) {
        this.messageType = eMessageType;
    }

    public EProjectInterfaceType getProjectInterfaceType() {
        return EProjectInterfaceType.Unknown;
    }

    public boolean match(BaseCommand baseCommand) {
        return match(baseCommand, false);
    }

    public boolean match(BaseCommand baseCommand, boolean z) {
        String str;
        if (this == baseCommand) {
            return true;
        }
        if (baseCommand == null) {
            return false;
        }
        String str2 = this.component;
        if (str2 == null ? baseCommand.component != null : !str2.equals(baseCommand.component)) {
            return false;
        }
        String str3 = this.command;
        if (str3 == null ? baseCommand.command == null : str3.equals(baseCommand.command)) {
            return !z || ((str = this.routingKey) == null && baseCommand.routingKey == null) || (str != null && str.equals(baseCommand.routingKey));
        }
        return false;
    }

    public BaseToken getToken() {
        return this.token;
    }

    public void setToken(BaseToken baseToken) {
        this.token = baseToken;
    }

    public int hashCode() {
        return Objects.hash(this.component, this.command, this.routingKey, this.token);
    }

    public boolean equals(Object obj) {
        return (obj instanceof BaseCommand) && match((BaseCommand) obj);
    }

    public String toString() {
        return gson.toJson(this);
    }

    public BaseCommand shallowCopy() {
        return deepCopy();
    }

    public BaseCommand deepCopy() {
        Gson gson2 = new Gson();
        return (BaseCommand) gson2.fromJson(gson2.toJson(this), BaseCommand.class);
    }
}
