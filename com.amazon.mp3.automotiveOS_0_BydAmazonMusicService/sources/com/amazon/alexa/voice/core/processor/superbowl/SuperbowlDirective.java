package com.amazon.alexa.voice.core.processor.superbowl;

import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SuperbowlDirective implements Directive {
    private final String dialogRequestId;
    private final String messageId;
    private final String name;
    private final String namespace;

    protected SuperbowlDirective(Builder<? extends SuperbowlDirective> builder) {
        Preconditions.notNull(((Builder) builder).namespace, "namespace == null");
        Preconditions.notNull(((Builder) builder).name, "name == null");
        Preconditions.notNull(((Builder) builder).messageId, "messageId == null");
        this.namespace = ((Builder) builder).namespace;
        this.name = ((Builder) builder).name;
        this.messageId = ((Builder) builder).messageId;
        this.dialogRequestId = ((Builder) builder).dialogRequestId;
    }

    public final String getDialogRequestId() {
        return this.dialogRequestId;
    }

    public final String getMessageId() {
        return this.messageId;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SuperbowlDirective superbowlDirective = (SuperbowlDirective) obj;
        if (!this.messageId.equals(superbowlDirective.messageId)) {
            return false;
        }
        String str = this.dialogRequestId;
        if (str == null ? superbowlDirective.dialogRequestId != null : !str.equals(superbowlDirective.dialogRequestId)) {
            return false;
        }
        if (this.namespace.equals(superbowlDirective.namespace)) {
            return this.name.equals(superbowlDirective.name);
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = this.messageId.hashCode() * 31;
        String str = this.dialogRequestId;
        return ((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + this.namespace.hashCode()) * 31) + this.name.hashCode();
    }

    public static abstract class Builder<T extends SuperbowlDirective> {
        private String dialogRequestId;
        private String messageId;
        private String name;
        private String namespace;

        public abstract T build();

        protected Builder(String str, String str2) {
            this.namespace = str;
            this.name = str2;
        }

        public final Builder<T> messageId(String str) {
            this.messageId = str;
            return this;
        }

        public final Builder<T> dialogRequestId(String str) {
            this.dialogRequestId = str;
            return this;
        }
    }
}
