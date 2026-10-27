package com.amazon.avod.util.sequence;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CompositeIntSequence extends AbstractIntSequence {
    private AbstractIntSequence mCurrentSubsequence;
    private final Iterator<? extends AbstractIntSequence> mSubsequenceIterator;

    public CompositeIntSequence(List<? extends AbstractIntSequence> list) {
        super(list.get(0).getNextValue(), list.get(list.size() - 1).getMaxValue());
        Iterator<? extends AbstractIntSequence> it = list.iterator();
        this.mSubsequenceIterator = it;
        this.mCurrentSubsequence = it.next();
    }

    @Override // com.amazon.avod.util.sequence.AbstractIntSequence
    protected int computeNextValue(int i) {
        int iComputeNextValue = this.mCurrentSubsequence.computeNextValue(i);
        while (iComputeNextValue > this.mCurrentSubsequence.getMaxValue()) {
            if (!this.mSubsequenceIterator.hasNext()) {
                return getMaxValue();
            }
            AbstractIntSequence next = this.mSubsequenceIterator.next();
            this.mCurrentSubsequence = next;
            iComputeNextValue = next.getNextValue();
        }
        return iComputeNextValue;
    }
}
