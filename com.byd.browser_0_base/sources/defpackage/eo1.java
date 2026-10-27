package defpackage;

import org.chromium.components.content_creation.notes.models.NoteTemplate;
import org.chromium.components.content_creation.notes.models.TextStyle;

/* JADX INFO: compiled from: NoteCreationMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class eo1 {
    public final ya1 a;
    public final go1 b;
    public final gt0 c;
    public final fx0 d;

    /* JADX INFO: compiled from: NoteCreationMediator.java */
    public class a {
        public final NoteTemplate a;
        public final mh3 b;

        public a(NoteTemplate noteTemplate) {
            this.a = noteTemplate;
            TextStyle textStyle = noteTemplate.d;
            this.b = new mh3(textStyle.a, textStyle.b);
        }
    }

    public eo1(ya1 ya1Var, gt0 gt0Var, go1 go1Var, fx0 fx0Var) {
        this.a = ya1Var;
        this.b = go1Var;
        this.c = gt0Var;
        this.d = fx0Var;
        go1Var.a(new wb(11, this));
    }
}
