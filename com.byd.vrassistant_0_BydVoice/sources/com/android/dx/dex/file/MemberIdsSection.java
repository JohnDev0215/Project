package com.android.dx.dex.file;

import com.android.dex.DexIndexOverflowException;
import com.android.dx.command.dexer.Main;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class MemberIdsSection extends UniformItemSection {
    public MemberIdsSection(String str, DexFile dexFile) {
        super(str, dexFile, 4);
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    protected void orderItems() {
        if (items().size() > 65536) {
            throw new DexIndexOverflowException(getTooManyMembersMessage());
        }
        Iterator<? extends Item> it = items().iterator();
        int i = 0;
        while (it.hasNext()) {
            ((MemberIdItem) it.next()).setIndex(i);
            i++;
        }
    }

    private String getTooManyMembersMessage() {
        TreeMap treeMap = new TreeMap();
        Iterator<? extends Item> it = items().iterator();
        while (it.hasNext()) {
            String packageName = ((MemberIdItem) it.next()).getDefiningClass().getPackageName();
            AtomicInteger atomicInteger = (AtomicInteger) treeMap.get(packageName);
            if (atomicInteger == null) {
                atomicInteger = new AtomicInteger();
                treeMap.put(packageName, atomicInteger);
            }
            atomicInteger.incrementAndGet();
        }
        Formatter formatter = new Formatter();
        try {
            formatter.format("Too many %s references: %d; max is %d.%n" + Main.getTooManyIdsErrorMessage() + "%nReferences by package:", this instanceof MethodIdsSection ? "method" : "field", Integer.valueOf(items().size()), 65536);
            for (Map.Entry entry : treeMap.entrySet()) {
                formatter.format("%n%6d %s", Integer.valueOf(((AtomicInteger) entry.getValue()).get()), entry.getKey());
            }
            return formatter.toString();
        } finally {
            formatter.close();
        }
    }
}
