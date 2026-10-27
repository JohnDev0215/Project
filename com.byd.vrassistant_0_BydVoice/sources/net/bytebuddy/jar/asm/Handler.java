package net.bytebuddy.jar.asm;

/* JADX INFO: loaded from: classes3.dex */
class Handler {
    String desc;
    Label end;
    Label handler;
    Handler next;
    Label start;
    int type;

    Handler() {
    }

    static Handler remove(Handler handler, Label label, Label label2) {
        if (handler == null) {
            return null;
        }
        handler.next = remove(handler.next, label, label2);
        int i = handler.start.position;
        int i2 = handler.end.position;
        int i3 = label.position;
        int i4 = label2 == null ? Integer.MAX_VALUE : label2.position;
        if (i3 >= i2 || i4 <= i) {
            return handler;
        }
        if (i3 <= i) {
            if (i4 >= i2) {
                return handler.next;
            }
            handler.start = label2;
            return handler;
        }
        if (i4 >= i2) {
            handler.end = label;
            return handler;
        }
        Handler handler2 = new Handler();
        handler2.start = label2;
        handler2.end = handler.end;
        handler2.handler = handler.handler;
        handler2.desc = handler.desc;
        handler2.type = handler.type;
        handler2.next = handler.next;
        handler.end = label;
        handler.next = handler2;
        return handler;
    }
}
