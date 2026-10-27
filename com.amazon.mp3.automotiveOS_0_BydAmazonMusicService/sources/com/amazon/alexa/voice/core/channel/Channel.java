package com.amazon.alexa.voice.core.channel;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class Channel {
    private volatile int activationCounter;
    private final int id;
    private volatile boolean inForeground;
    private final List<Listener> listeners = new ArrayList();
    private final int priority;

    public interface Listener {
        void onBecameActive(Channel channel);

        void onBecameInactive(Channel channel);

        void onBroughtToBackground(Channel channel);

        void onBroughtToForeground(Channel channel);
    }

    public static class SimpleListener implements Listener {
        @Override // com.amazon.alexa.voice.core.channel.Channel.Listener
        public void onBecameActive(Channel channel) {
        }

        @Override // com.amazon.alexa.voice.core.channel.Channel.Listener
        public void onBecameInactive(Channel channel) {
        }

        @Override // com.amazon.alexa.voice.core.channel.Channel.Listener
        public void onBroughtToBackground(Channel channel) {
        }

        @Override // com.amazon.alexa.voice.core.channel.Channel.Listener
        public void onBroughtToForeground(Channel channel) {
        }
    }

    public Channel(int i, int i2) {
        this.id = i;
        this.priority = i2;
    }

    public void addListener(Listener listener) {
        Preconditions.notNull(listener, "listener == null");
        synchronized (this.listeners) {
            this.listeners.add(listener);
        }
    }

    public int getId() {
        return this.id;
    }

    public int getPriority() {
        return this.priority;
    }

    public boolean isInactive() {
        return this.activationCounter == 0;
    }

    public boolean isActive() {
        return this.activationCounter > 0;
    }

    public boolean isInBackground() {
        return !this.inForeground;
    }

    public boolean bringToForeground() {
        if (this.inForeground || !isActive()) {
            return false;
        }
        synchronized (this) {
            if (this.inForeground) {
                return false;
            }
            this.inForeground = true;
            synchronized (this.listeners) {
                for (int size = this.listeners.size() - 1; size >= 0; size--) {
                    this.listeners.get(size).onBroughtToForeground(this);
                }
            }
            return true;
        }
    }

    public boolean bringToBackground() {
        if (!this.inForeground) {
            return false;
        }
        synchronized (this) {
            if (!this.inForeground) {
                return false;
            }
            this.inForeground = false;
            synchronized (this.listeners) {
                for (int size = this.listeners.size() - 1; size >= 0; size--) {
                    this.listeners.get(size).onBroughtToBackground(this);
                }
            }
            return true;
        }
    }

    public boolean acquireActivation() {
        synchronized (this) {
            int i = this.activationCounter;
            this.activationCounter = i + 1;
            if (i > 0) {
                return true;
            }
            synchronized (this.listeners) {
                for (int size = this.listeners.size() - 1; size >= 0; size--) {
                    this.listeners.get(size).onBecameActive(this);
                }
            }
            return true;
        }
    }

    public boolean releaseActivation() {
        if (this.activationCounter <= 0) {
            return false;
        }
        synchronized (this) {
            int i = this.activationCounter - 1;
            this.activationCounter = i;
            if (i > 0) {
                return false;
            }
            bringToBackground();
            synchronized (this.listeners) {
                for (int size = this.listeners.size() - 1; size >= 0; size--) {
                    this.listeners.get(size).onBecameInactive(this);
                }
            }
            return true;
        }
    }
}
