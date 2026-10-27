package org.apache.commons.net;

import java.util.EventObject;

/* JADX INFO: loaded from: classes.dex */
public class ProtocolCommandEvent extends EventObject {
    private static final long serialVersionUID = 403743538418947240L;
    private final String command;
    private final boolean isCommand;
    private final String message;
    private final int replyCode;

    public ProtocolCommandEvent(Object source, String command, String message) {
        super(source);
        this.replyCode = 0;
        this.message = message;
        this.isCommand = true;
        this.command = command;
    }

    public ProtocolCommandEvent(Object source, int replyCode, String message) {
        super(source);
        this.replyCode = replyCode;
        this.message = message;
        this.isCommand = false;
        this.command = null;
    }

    public String getCommand() {
        return this.command;
    }

    public int getReplyCode() {
        return this.replyCode;
    }

    public boolean isCommand() {
        return this.isCommand;
    }

    public boolean isReply() {
        return !isCommand();
    }

    public String getMessage() {
        return this.message;
    }
}
