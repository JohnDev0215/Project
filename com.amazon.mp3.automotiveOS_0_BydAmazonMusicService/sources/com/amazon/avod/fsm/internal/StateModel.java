package com.amazon.avod.fsm.internal;

import com.amazon.avod.fsm.State;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class StateModel<S, T> {
    private final Map<State<S, T>, StateModel<S, T>.Node> mStateMap = Maps.newHashMap();
    private final StateModel<S, T>.Node mRoot = new Node(null, null);

    public void addState(State<S, T> state) {
        addState(this.mRoot, state);
    }

    public void addState(State<S, T> state, State<S, T> state2) {
        Preconditions.checkNotNull(state);
        Preconditions.checkState(this.mStateMap.containsKey(state), "Parent state %s was not declared.", state);
        addState(this.mStateMap.get(state), state2);
    }

    public State<S, T> getCommonParent(State<S, T> state, State<S, T> state2) {
        Preconditions.checkNotNull(state);
        Preconditions.checkNotNull(state2);
        Preconditions.checkState(this.mStateMap.containsKey(state), "State %s was never declared.", state);
        Preconditions.checkState(this.mStateMap.containsKey(state2), "State %s was never declared.", state2);
        return getCommonParent(this.mStateMap.get(state), this.mStateMap.get(state2)).getState();
    }

    public List<State<S, T>> getAncestors(State<S, T> state) {
        return getAncestors(state, this.mRoot);
    }

    public List<State<S, T>> getAncestors(State<S, T> state, State<S, T> state2) {
        Preconditions.checkNotNull(state2);
        Preconditions.checkState(this.mStateMap.containsKey(state2), "State %s was never declared.", state2);
        return getAncestors(state, this.mStateMap.get(state2));
    }

    public void getTransitionPath(State<S, T> state, State<S, T> state2, List<State<S, T>> list, List<State<S, T>> list2) {
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        if (state == null) {
            Preconditions.checkNotNull(state2);
            list2.addAll(Lists.reverse(getAncestors(state2)));
            list2.add(state2);
            return;
        }
        if (state2 == null) {
            Preconditions.checkNotNull(state);
            List<State<S, T>> ancestors = getAncestors(state);
            list.add(state);
            list.addAll(ancestors);
            return;
        }
        List<State<S, T>> ancestors2 = getAncestors(state2);
        if (getAncestors(state).contains(state2)) {
            list.add(state);
            list.addAll(getAncestors(state, state2));
            list.add(state2);
            list2.add(state2);
            return;
        }
        if (ancestors2.contains(state)) {
            list2.addAll(Lists.reverse(getAncestors(state2, state)));
            list2.add(state2);
            return;
        }
        State<S, T> commonParent = getCommonParent(state2, state);
        if (commonParent != null) {
            list.add(state);
            list.addAll(getAncestors(state, commonParent));
            list2.addAll(Lists.reverse(getAncestors(state2, commonParent)));
            list2.add(state2);
            return;
        }
        list.add(state);
        list.addAll(getAncestors(state));
        list2.addAll(Lists.reverse(getAncestors(state2)));
        list2.add(state2);
    }

    private List<State<S, T>> getAncestors(State<S, T> state, StateModel<S, T>.Node node) {
        Preconditions.checkNotNull(state);
        Preconditions.checkState(this.mStateMap.containsKey(state), "State %s was never declared.", state);
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        for (StateModel<S, T>.Node parent = this.mStateMap.get(state); parent != node && parent.getParent() != node; parent = parent.getParent()) {
            linkedListNewLinkedList.add(parent.getParent().getState());
        }
        return linkedListNewLinkedList;
    }

    private StateModel<S, T>.Node getCommonParent(StateModel<S, T>.Node node, StateModel<S, T>.Node node2) {
        int lengthToRoot = getLengthToRoot(node);
        int lengthToRoot2 = getLengthToRoot(node2);
        while (lengthToRoot > lengthToRoot2) {
            node = node.getParent();
            lengthToRoot--;
        }
        while (lengthToRoot2 > lengthToRoot) {
            node2 = node2.getParent();
            lengthToRoot2--;
        }
        StateModel<S, T>.Node node3 = this.mRoot;
        while (true) {
            StateModel<S, T>.Node node4 = this.mRoot;
            if (node == node4 || node2 == node4) {
                return node3;
            }
            if (node == node2) {
                return node;
            }
            node = node.getParent();
            node2 = node2.getParent();
        }
    }

    private int getLengthToRoot(StateModel<S, T>.Node node) {
        int i = 0;
        while (node != this.mRoot) {
            node = node.getParent();
            i++;
        }
        return i;
    }

    private void addState(StateModel<S, T>.Node node, State<S, T> state) {
        Preconditions.checkNotNull(state);
        Preconditions.checkState(!this.mStateMap.containsKey(state), "State %s was already declared.", state);
        StateModel<S, T>.Node node2 = new Node(state, node);
        this.mStateMap.put(state, node2);
        node.addChild(node2);
    }

    private class Node {
        private final List<StateModel<S, T>.Node> mChildren = Lists.newLinkedList();
        private final StateModel<S, T>.Node mParent;
        private final State<S, T> mState;

        public Node(State<S, T> state, StateModel<S, T>.Node node) {
            this.mState = state;
            this.mParent = node;
        }

        public State<S, T> getState() {
            return this.mState;
        }

        public StateModel<S, T>.Node getParent() {
            return this.mParent;
        }

        public void addChild(StateModel<S, T>.Node node) {
            this.mChildren.add(node);
        }
    }
}
