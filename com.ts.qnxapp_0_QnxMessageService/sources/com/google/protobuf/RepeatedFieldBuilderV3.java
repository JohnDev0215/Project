package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.MessageOrBuilder;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public class RepeatedFieldBuilderV3<MType extends AbstractMessage, BType extends AbstractMessage.Builder, IType extends MessageOrBuilder> implements AbstractMessage.BuilderParent {
    private List<SingleFieldBuilderV3<MType, BType, IType>> builders;
    private BuilderExternalList<MType, BType, IType> externalBuilderList;
    private MessageExternalList<MType, BType, IType> externalMessageList;
    private MessageOrBuilderExternalList<MType, BType, IType> externalMessageOrBuilderList;
    private boolean isClean;
    private boolean isMessagesListMutable;
    private List<MType> messages;
    private AbstractMessage.BuilderParent parent;

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
        if (!this.isMessagesListMutable) {
            this.messages = new ArrayList(this.messages);
            this.isMessagesListMutable = true;
        }
    }

    private void ensureBuilders() {
        if (this.builders == null) {
            this.builders = new ArrayList(this.messages.size());
            for (int i = 0; i < this.messages.size(); i++) {
                this.builders.add(null);
            }
        }
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

    public BType getBuilder(int i) {
        ensureBuilders();
        SingleFieldBuilderV3<MType, BType, IType> singleFieldBuilderV3 = this.builders.get(i);
        if (singleFieldBuilderV3 == null) {
            singleFieldBuilderV3 = new SingleFieldBuilderV3<>(this.messages.get(i), this, this.isClean);
            this.builders.set(i, singleFieldBuilderV3);
        }
        return (BType) singleFieldBuilderV3.getBuilder();
    }

    public IType getMessageOrBuilder(int i) {
        List<SingleFieldBuilderV3<MType, BType, IType>> list = this.builders;
        if (list == null) {
            return this.messages.get(i);
        }
        SingleFieldBuilderV3<MType, BType, IType> singleFieldBuilderV3 = list.get(i);
        if (singleFieldBuilderV3 == null) {
            return this.messages.get(i);
        }
        return (IType) singleFieldBuilderV3.getMessageOrBuilder();
    }

    public RepeatedFieldBuilderV3<MType, BType, IType> setMessage(int index, MType message) {
        SingleFieldBuilderV3<MType, BType, IType> entry;
        Internal.checkNotNull(message);
        ensureMutableMessageList();
        this.messages.set(index, message);
        List<SingleFieldBuilderV3<MType, BType, IType>> list = this.builders;
        if (list != null && (entry = list.set(index, null)) != null) {
            entry.dispose();
        }
        onChanged();
        incrementModCounts();
        return this;
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

    public RepeatedFieldBuilderV3<MType, BType, IType> addMessage(int index, MType message) {
        Internal.checkNotNull(message);
        ensureMutableMessageList();
        this.messages.add(index, message);
        List<SingleFieldBuilderV3<MType, BType, IType>> list = this.builders;
        if (list != null) {
            list.add(index, null);
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    public RepeatedFieldBuilderV3<MType, BType, IType> addAllMessages(Iterable<? extends MType> values) {
        for (MType value : values) {
            Internal.checkNotNull(value);
        }
        int size = -1;
        if (values instanceof Collection) {
            Collection<?> collection = (Collection) values;
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
        for (MType value2 : values) {
            addMessage(value2);
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    public BType addBuilder(MType mtype) {
        ensureMutableMessageList();
        ensureBuilders();
        SingleFieldBuilderV3<MType, BType, IType> singleFieldBuilderV3 = new SingleFieldBuilderV3<>(mtype, this, this.isClean);
        this.messages.add(null);
        this.builders.add(singleFieldBuilderV3);
        onChanged();
        incrementModCounts();
        return (BType) singleFieldBuilderV3.getBuilder();
    }

    public BType addBuilder(int i, MType mtype) {
        ensureMutableMessageList();
        ensureBuilders();
        SingleFieldBuilderV3<MType, BType, IType> singleFieldBuilderV3 = new SingleFieldBuilderV3<>(mtype, this, this.isClean);
        this.messages.add(i, null);
        this.builders.add(i, singleFieldBuilderV3);
        onChanged();
        incrementModCounts();
        return (BType) singleFieldBuilderV3.getBuilder();
    }

    public void remove(int index) {
        SingleFieldBuilderV3<MType, BType, IType> entry;
        ensureMutableMessageList();
        this.messages.remove(index);
        List<SingleFieldBuilderV3<MType, BType, IType>> list = this.builders;
        if (list != null && (entry = list.remove(index)) != null) {
            entry.dispose();
        }
        onChanged();
        incrementModCounts();
    }

    public void clear() {
        this.messages = Collections.emptyList();
        this.isMessagesListMutable = false;
        List<SingleFieldBuilderV3<MType, BType, IType>> list = this.builders;
        if (list != null) {
            for (SingleFieldBuilderV3<MType, BType, IType> entry : list) {
                if (entry != null) {
                    entry.dispose();
                }
            }
            this.builders = null;
        }
        onChanged();
        incrementModCounts();
    }

    public List<MType> build() {
        this.isClean = true;
        if (!this.isMessagesListMutable && this.builders == null) {
            return this.messages;
        }
        boolean z = true;
        if (!this.isMessagesListMutable) {
            int i = 0;
            while (true) {
                if (i >= this.messages.size()) {
                    break;
                }
                MType mtype = this.messages.get(i);
                SingleFieldBuilderV3<MType, BType, IType> singleFieldBuilderV3 = this.builders.get(i);
                if (singleFieldBuilderV3 == null || singleFieldBuilderV3.build() == mtype) {
                    i++;
                } else {
                    z = false;
                    break;
                }
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

    public List<MType> getMessageList() {
        if (this.externalMessageList == null) {
            this.externalMessageList = new MessageExternalList<>(this);
        }
        return this.externalMessageList;
    }

    public List<BType> getBuilderList() {
        if (this.externalBuilderList == null) {
            this.externalBuilderList = new BuilderExternalList<>(this);
        }
        return this.externalBuilderList;
    }

    public List<IType> getMessageOrBuilderList() {
        if (this.externalMessageOrBuilderList == null) {
            this.externalMessageOrBuilderList = new MessageOrBuilderExternalList<>(this);
        }
        return this.externalMessageOrBuilderList;
    }

    private void onChanged() {
        AbstractMessage.BuilderParent builderParent;
        if (this.isClean && (builderParent = this.parent) != null) {
            builderParent.markDirty();
            this.isClean = false;
        }
    }

    @Override // com.google.protobuf.AbstractMessage.BuilderParent
    public void markDirty() {
        onChanged();
    }

    private void incrementModCounts() {
        MessageExternalList<MType, BType, IType> messageExternalList = this.externalMessageList;
        if (messageExternalList != null) {
            messageExternalList.incrementModCount();
        }
        BuilderExternalList<MType, BType, IType> builderExternalList = this.externalBuilderList;
        if (builderExternalList != null) {
            builderExternalList.incrementModCount();
        }
        MessageOrBuilderExternalList<MType, BType, IType> messageOrBuilderExternalList = this.externalMessageOrBuilderList;
        if (messageOrBuilderExternalList != null) {
            messageOrBuilderExternalList.incrementModCount();
        }
    }

    private static class MessageExternalList<MType extends AbstractMessage, BType extends AbstractMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<MType> implements List<MType>, RandomAccess {
        RepeatedFieldBuilderV3<MType, BType, IType> builder;

        MessageExternalList(RepeatedFieldBuilderV3<MType, BType, IType> builder) {
            this.builder = builder;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.builder.getCount();
        }

        @Override // java.util.AbstractList, java.util.List
        public MType get(int i) {
            return (MType) this.builder.getMessage(i);
        }

        void incrementModCount() {
            this.modCount++;
        }
    }

    private static class BuilderExternalList<MType extends AbstractMessage, BType extends AbstractMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<BType> implements List<BType>, RandomAccess {
        RepeatedFieldBuilderV3<MType, BType, IType> builder;

        BuilderExternalList(RepeatedFieldBuilderV3<MType, BType, IType> builder) {
            this.builder = builder;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.builder.getCount();
        }

        @Override // java.util.AbstractList, java.util.List
        public BType get(int i) {
            return (BType) this.builder.getBuilder(i);
        }

        void incrementModCount() {
            this.modCount++;
        }
    }

    private static class MessageOrBuilderExternalList<MType extends AbstractMessage, BType extends AbstractMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<IType> implements List<IType>, RandomAccess {
        RepeatedFieldBuilderV3<MType, BType, IType> builder;

        MessageOrBuilderExternalList(RepeatedFieldBuilderV3<MType, BType, IType> builder) {
            this.builder = builder;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.builder.getCount();
        }

        @Override // java.util.AbstractList, java.util.List
        public IType get(int i) {
            return (IType) this.builder.getMessageOrBuilder(i);
        }

        void incrementModCount() {
            this.modCount++;
        }
    }
}
