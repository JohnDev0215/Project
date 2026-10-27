package org.apache.commons.net.nntp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Threader {
    public Threadable thread(List<? extends Threadable> messages) {
        return thread((Iterable<? extends Threadable>) messages);
    }

    public Threadable thread(Iterable<? extends Threadable> messages) {
        if (messages == null) {
            return null;
        }
        HashMap<String, ThreadContainer> idTable = new HashMap<>();
        for (Threadable t : messages) {
            if (!t.isDummy()) {
                buildContainer(t, idTable);
            }
        }
        if (idTable.isEmpty()) {
            return null;
        }
        ThreadContainer root = findRootSet(idTable);
        idTable.clear();
        pruneEmptyContainers(root);
        root.reverseChildren();
        gatherSubjects(root);
        if (root.next != null) {
            throw new RuntimeException("root node has a next:" + root);
        }
        for (ThreadContainer r = root.child; r != null; r = r.next) {
            if (r.threadable == null) {
                r.threadable = r.child.threadable.makeDummy();
            }
        }
        ThreadContainer r2 = root.child;
        Threadable result = r2 != null ? root.child.threadable : null;
        root.flush();
        return result;
    }

    private void buildContainer(Threadable threadable, HashMap<String, ThreadContainer> idTable) {
        String id = threadable.messageThreadId();
        ThreadContainer container = idTable.get(id);
        if (container != null) {
            if (container.threadable != null) {
                int bogusIdCount = 0 + 1;
                id = "<Bogus-id:" + bogusIdCount + ">";
                container = null;
            } else {
                container.threadable = threadable;
            }
        }
        if (container == null) {
            container = new ThreadContainer();
            container.threadable = threadable;
            idTable.put(id, container);
        }
        ThreadContainer parentRef = null;
        String[] references = threadable.messageThreadReferences();
        for (String refString : references) {
            ThreadContainer ref = idTable.get(refString);
            if (ref == null) {
                ref = new ThreadContainer();
                idTable.put(refString, ref);
            }
            if (parentRef != null && ref.parent == null && parentRef != ref && !ref.findChild(parentRef)) {
                ref.parent = parentRef;
                ref.next = parentRef.child;
                parentRef.child = ref;
            }
            parentRef = ref;
        }
        if (parentRef != null && (parentRef == container || container.findChild(parentRef))) {
            parentRef = null;
        }
        if (container.parent != null) {
            ThreadContainer prev = null;
            ThreadContainer rest = container.parent.child;
            while (rest != null && rest != container) {
                prev = rest;
                rest = rest.next;
            }
            if (rest == null) {
                throw new RuntimeException("Didnt find " + container + " in parent" + container.parent);
            }
            if (prev == null) {
                container.parent.child = container.next;
            } else {
                prev.next = container.next;
            }
            container.next = null;
            container.parent = null;
        }
        if (parentRef != null) {
            container.parent = parentRef;
            container.next = parentRef.child;
            parentRef.child = container;
        }
    }

    private ThreadContainer findRootSet(HashMap<String, ThreadContainer> idTable) {
        ThreadContainer root = new ThreadContainer();
        for (Map.Entry<String, ThreadContainer> entry : idTable.entrySet()) {
            ThreadContainer c = entry.getValue();
            if (c.parent == null) {
                if (c.next != null) {
                    throw new RuntimeException("c.next is " + c.next.toString());
                }
                c.next = root.child;
                root.child = c;
            }
        }
        return root;
    }

    private void pruneEmptyContainers(ThreadContainer parent) {
        ThreadContainer prev = null;
        ThreadContainer container = parent.child;
        ThreadContainer next = container.next;
        while (container != null) {
            if (container.threadable == null && container.child == null) {
                if (prev == null) {
                    parent.child = container.next;
                } else {
                    prev.next = container.next;
                }
                container = prev;
            } else if (container.threadable == null && container.child != null && (container.parent != null || container.child.next == null)) {
                ThreadContainer kids = container.child;
                if (prev == null) {
                    parent.child = kids;
                } else {
                    prev.next = kids;
                }
                ThreadContainer tail = kids;
                while (tail.next != null) {
                    tail.parent = container.parent;
                    tail = tail.next;
                }
                tail.parent = container.parent;
                tail.next = container.next;
                next = kids;
                container = prev;
            } else if (container.child != null) {
                pruneEmptyContainers(container);
            }
            prev = container;
            container = next;
            next = container == null ? null : container.next;
        }
    }

    private void gatherSubjects(ThreadContainer root) {
        ThreadContainer old;
        ThreadContainer old2;
        int count = 0;
        for (ThreadContainer c = root.child; c != null; c = c.next) {
            count++;
        }
        HashMap<String, ThreadContainer> subjectTable = new HashMap<>((int) (((double) count) * 1.2d), 0.9f);
        int count2 = 0;
        for (ThreadContainer c2 = root.child; c2 != null; c2 = c2.next) {
            Threadable threadable = c2.threadable;
            if (threadable == null) {
                threadable = c2.child.threadable;
            }
            String subj = threadable.simplifiedSubject();
            if (subj != null && subj.length() != 0 && ((old2 = subjectTable.get(subj)) == null || ((c2.threadable == null && old2.threadable != null) || (old2.threadable != null && old2.threadable.subjectIsReply() && c2.threadable != null && !c2.threadable.subjectIsReply())))) {
                subjectTable.put(subj, c2);
                count2++;
            }
        }
        if (count2 == 0) {
            return;
        }
        ThreadContainer prev = null;
        ThreadContainer c3 = root.child;
        ThreadContainer rest = c3.next;
        while (c3 != null) {
            Threadable threadable2 = c3.threadable;
            if (threadable2 == null) {
                threadable2 = c3.child.threadable;
            }
            String subj2 = threadable2.simplifiedSubject();
            ThreadContainer threadContainer = null;
            if (subj2 != null && subj2.length() != 0 && (old = subjectTable.get(subj2)) != c3) {
                if (prev == null) {
                    root.child = c3.next;
                } else {
                    prev.next = c3.next;
                }
                c3.next = null;
                if (old.threadable == null && c3.threadable == null) {
                    ThreadContainer tail = old.child;
                    while (tail != null && tail.next != null) {
                        tail = tail.next;
                    }
                    if (tail != null) {
                        tail.next = c3.child;
                    }
                    for (ThreadContainer tail2 = c3.child; tail2 != null; tail2 = tail2.next) {
                        tail2.parent = old;
                    }
                    c3.child = null;
                } else if (old.threadable == null || (c3.threadable != null && c3.threadable.subjectIsReply() && !old.threadable.subjectIsReply())) {
                    c3.parent = old;
                    c3.next = old.child;
                    old.child = c3;
                } else {
                    ThreadContainer newc = new ThreadContainer();
                    newc.threadable = old.threadable;
                    newc.child = old.child;
                    for (ThreadContainer tail3 = newc.child; tail3 != null; tail3 = tail3.next) {
                        tail3.parent = newc;
                    }
                    old.threadable = null;
                    old.child = null;
                    c3.parent = old;
                    newc.parent = old;
                    old.child = c3;
                    c3.next = newc;
                }
                c3 = prev;
            }
            prev = c3;
            c3 = rest;
            if (rest != null) {
                threadContainer = rest.next;
            }
            rest = threadContainer;
        }
        subjectTable.clear();
    }

    @Deprecated
    public Threadable thread(Threadable[] messages) {
        if (messages == null) {
            return null;
        }
        return thread(Arrays.asList(messages));
    }
}
