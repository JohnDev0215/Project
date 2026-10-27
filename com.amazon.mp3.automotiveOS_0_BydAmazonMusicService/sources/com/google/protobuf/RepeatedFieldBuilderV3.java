package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.MessageOrBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public class RepeatedFieldBuilderV3<MType extends AbstractMessage, BType extends AbstractMessage.Builder, IType extends MessageOrBuilder> implements AbstractMessage.BuilderParent {
    private List<SingleFieldBuilderV3<MType, BType, IType>> builders;
    private boolean isClean;
    private boolean isMessagesListMutable;
    private List<MType> messages;
    private AbstractMessage.BuilderParent parent;

    private void incrementModCounts() {
    }

    public RepeatedFieldBuilderV3(List<MType> messages, boolean isMessagesListMutable, AbstractMessage.BuilderParent parent, boolean isClean) {
        this.messages = messages;
        this.isMessagesListMutable = isMessagesListMutable;
        this.parent = parent;
        this.isClean = isClean;
    }

    public void dispose() {
        this.parent = null;
    }

    private void ensureMutableMessageList() {
        if (this.isMessagesListMutable) {
            return;
        }
        this.messages = new ArrayList(this.messages);
        this.isMessagesListMutable = true;
    }

    public int getCount() {
        return this.messages.size();
    }

    public boolean isEmpty() {
        return this.messages.isEmpty();
    }

    public MType getMessage(int i) {
        return (MType) getMessage(i, false);
    }

    private MType getMessage(int i, boolean z) {
        List<SingleFieldBuilderV3<MType, BType, IType>> list = this.builders;
        if (list == null) {
            return this.messages.get(i);
        }
        SingleFieldBuilderV3<MType, BType, IType> singleFieldBuilderV3 = list.get(i);
        if (singleFieldBuilderV3 == null) {
            return this.messages.get(i);
        }
        return z ? (MType) singleFieldBuilderV3.build() : (MType) singleFieldBuilderV3.getMessage();
    }

    public RepeatedFieldBuilderV3<MType, BType, IType> addMessage(MType message) {
        Internal.checkNotNull(message);
        ensureMutableMessageList();
        this.messages.add(message);
        List<SingleFieldBuilderV3<MType, BType, IType>> list = this.builders;
        if (list != null) {
            list.add(null);
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    public RepeatedFieldBuilderV3<MType, BType, IType> addAllMessages(Iterable<? extends MType> values) {
        Iterator<? extends MType> it = values.iterator();
        while (it.hasNext()) {
            Internal.checkNotNull(it.next());
        }
        int size = -1;
        if (values instanceof Collection) {
            Collection collection = (Collection) values;
            if (collection.isEmpty()) {
                return this;
            }
            size = collection.size();
        }
        ensureMutableMessageList();
        if (size >= 0) {
            List<MType> list = this.messages;
            if (list instanceof ArrayList) {
                ((ArrayList) list).ensureCapacity(list.size() + size);
            }
        }
        Iterator<? extends MType> it2 = values.iterator();
        while (it2.hasNext()) {
            addMessage(it2.next());
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    public List<MType> build() {
        boolean z;
        this.isClean = true;
        boolean z2 = this.isMessagesListMutable;
        if (!z2 && this.builders == null) {
            return this.messages;
        }
        if (!z2) {
            int i = 0;
            while (true) {
                if (i >= this.messages.size()) {
                    z = true;
                    break;
                }
                MType mtype = this.messages.get(i);
                SingleFieldBuilderV3<MType, BType, IType> singleFieldBuilderV3 = this.builders.get(i);
                if (singleFieldBuilderV3 != null && singleFieldBuilderV3.build() != mtype) {
                    z = false;
                    break;
                }
                i++;
            }
            if (z) {
                return this.messages;
            }
        }
        ensureMutableMessageList();
        for (int i2 = 0; i2 < this.messages.size(); i2++) {
            this.messages.set(i2, (MType) getMessage(i2, true));
        }
        List<MType> listUnmodifiableList = Collections.unmodifiableList(this.messages);
        this.messages = listUnmodifiableList;
        this.isMessagesListMutable = false;
        return listUnmodifiableList;
    }

    private void onChanged() {
        AbstractMessage.BuilderParent builderParent;
        if (!this.isClean || (builderParent = this.parent) == null) {
            return;
        }
        builderParent.markDirty();
        this.isClean = false;
    }

    @Override // com.google.protobuf.AbstractMessage.BuilderParent
    public void markDirty() {
        onChanged();
    }
}
