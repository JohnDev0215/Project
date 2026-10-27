package com.amazon.music.destination;

import com.amazon.music.destination.parser.Action;

/* JADX INFO: loaded from: classes4.dex */
public class GenresDestination extends AsinActionDestination {
    private final Target target;

    public GenresDestination(Target target, String str, Action action, String str2, String str3) {
        super(str, null, action, str2, str3);
        this.target = target;
    }

    public Target getTarget() {
        return this.target;
    }
}
