package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.Action;

/* JADX INFO: loaded from: classes4.dex */
public class HomeDestination extends Destination {
    private final Action action;
    private final Target target;

    public HomeDestination(Target target, Action action, String str, String str2) {
        super(str, str2);
        this.target = target;
        this.action = action;
    }

    public Target getTarget() {
        return this.target;
    }

    public Action getAction() {
        return this.action;
    }
}
