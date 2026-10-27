package com.amazon.music.router;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.amazon.music.destination.core.Destination;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class Router {
    private static Router INSTANCE;
    private final Context context;
    private Map<Class, DestinationHandler> destinationHandlers = new HashMap();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public static synchronized Router get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Router(context);
        }
        return INSTANCE;
    }

    private Router(Context context) {
        this.context = context.getApplicationContext();
    }

    public <T extends Destination> void register(Class<T> cls, DestinationHandler<T> destinationHandler) {
        Validate.notNull(cls, "Destination Class can't be null", new Object[0]);
        Validate.notNull(destinationHandler, "Destination Handler can't be null", new Object[0]);
        this.destinationHandlers.put(cls, destinationHandler);
    }

    public void navigateTo(final Destination destination) throws UnknownDestinationException {
        final DestinationHandler destinationHandler = this.destinationHandlers.get(destination.getClass());
        if (destinationHandler == null) {
            throw new UnknownDestinationException(destination);
        }
        this.handler.post(new Runnable() { // from class: com.amazon.music.router.Router.1
            @Override // java.lang.Runnable
            public void run() {
                destinationHandler.navigateTo(Router.this.context, destination);
            }
        });
    }

    public void navigateTo(final Destination destination, final Runnable runnable) throws UnknownDestinationException {
        final DestinationHandler destinationHandler = this.destinationHandlers.get(destination.getClass());
        if (destinationHandler == null) {
            throw new UnknownDestinationException(destination);
        }
        this.handler.post(new Runnable() { // from class: com.amazon.music.router.Router.2
            @Override // java.lang.Runnable
            public void run() {
                DestinationHandler destinationHandler2 = destinationHandler;
                if (destinationHandler2 instanceof AsyncDestinationHandler) {
                    ((AsyncDestinationHandler) destinationHandler2).navigateTo(Router.this.context, destination, runnable);
                    return;
                }
                destinationHandler2.navigateTo(Router.this.context, destination);
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
    }
}
