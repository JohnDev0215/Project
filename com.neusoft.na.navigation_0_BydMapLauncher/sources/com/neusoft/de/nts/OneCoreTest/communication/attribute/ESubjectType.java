package com.neusoft.de.nts.OneCoreTest.communication.attribute;

import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

/* JADX INFO: loaded from: classes2.dex */
public enum ESubjectType {
    Behaviour { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType.1
        @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType
        public <V> Subject<V, V> create() {
            return BehaviorSubject.create();
        }
    },
    Publish { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType.2
        @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType
        public <V> Subject<V, V> create() {
            return PublishSubject.create();
        }
    },
    Replay { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType.3
        @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType
        public <V> Subject<V, V> create() {
            return ReplaySubject.create();
        }
    },
    Async { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType.4
        @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType
        public <V> Subject<V, V> create() {
            return AsyncSubject.create();
        }
    };

    public abstract <V> Subject<V, V> create();

    static ESubjectType getDefaultSubjectType() {
        return Publish;
    }
}
