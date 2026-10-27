package org.apache.commons.net;

import java.io.Serializable;
import java.util.EventListener;
import org.apache.commons.net.util.ListenerList;

/* JADX INFO: loaded from: classes.dex */
public class ProtocolCommandSupport implements Serializable {
    private static final long serialVersionUID = -8017692739988399978L;
    private final ListenerList listeners = new ListenerList();
    private final Object source;

    public ProtocolCommandSupport(Object source) {
        this.source = source;
    }

    public void fireCommandSent(String command, String message) {
        ProtocolCommandEvent event = new ProtocolCommandEvent(this.source, command, message);
        for (EventListener listener : this.listeners) {
            ((ProtocolCommandListener) listener).protocolCommandSent(event);
        }
    }

    public void fireReplyReceived(int replyCode, String message) {
        ProtocolCommandEvent event = new ProtocolCommandEvent(this.source, replyCode, message);
        for (EventListener listener : this.listeners) {
            ((ProtocolCommandListener) listener).protocolReplyReceived(event);
        }
    }

    public void addProtocolCommandListener(ProtocolCommandListener listener) {
        this.listeners.addListener(listener);
    }

    public void removeProtocolCommandListener(ProtocolCommandListener listener) {
        this.listeners.removeListener(listener);
    }

    public int getListenerCount() {
        return this.listeners.getListenerCount();
    }
}
