package net.bytebuddy.implementation.bytecode.collection;

import java.util.List;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;

/* JADX INFO: loaded from: classes3.dex */
public interface CollectionFactory {
    TypeDescription.Generic getComponentType();

    StackManipulation withValues(List<? extends StackManipulation> list);
}
