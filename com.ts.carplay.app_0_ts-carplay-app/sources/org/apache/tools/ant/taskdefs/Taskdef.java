package org.apache.tools.ant.taskdefs;

/* JADX INFO: loaded from: classes.dex */
public class Taskdef extends Typedef {
    static /* synthetic */ Class class$org$apache$tools$ant$Task;
    static /* synthetic */ Class class$org$apache$tools$ant$TaskAdapter;

    public Taskdef() {
        Class clsClass$ = class$org$apache$tools$ant$TaskAdapter;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.TaskAdapter");
            class$org$apache$tools$ant$TaskAdapter = clsClass$;
        }
        setAdapterClass(clsClass$);
        Class clsClass$2 = class$org$apache$tools$ant$Task;
        if (clsClass$2 == null) {
            clsClass$2 = class$("org.apache.tools.ant.Task");
            class$org$apache$tools$ant$Task = clsClass$2;
        }
        setAdaptToClass(clsClass$2);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}
