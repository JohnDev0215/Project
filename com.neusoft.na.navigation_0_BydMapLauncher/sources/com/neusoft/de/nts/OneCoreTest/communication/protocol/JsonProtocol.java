package com.neusoft.de.nts.OneCoreTest.communication.protocol;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.interfaces.IProtocol;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class JsonProtocol implements IProtocol<BaseCommand, String> {
    private static final Logger logger = Logger.getLogger((Class<?>) JsonProtocol.class);
    private final Gson gson;

    public JsonProtocol(Gson gson) {
        this.gson = gson;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IProtocol
    public String to(BaseCommand baseCommand) {
        return this.gson.toJson(baseCommand);
    }

    public JsonElement toTree(BaseCommand baseCommand) {
        return this.gson.toJsonTree(baseCommand);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IProtocol
    public <T extends BaseCommand> T from(String str, Class<T> cls) {
        try {
            return (T) this.gson.fromJson(str, (Class) cls);
        } catch (JsonParseException e) {
            logger.warn(e);
            return null;
        }
    }
}
