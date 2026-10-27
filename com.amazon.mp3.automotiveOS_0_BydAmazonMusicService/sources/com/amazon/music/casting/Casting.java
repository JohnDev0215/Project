package com.amazon.music.casting;

import android.content.Context;
import com.amazon.music.casting.providers.AmazonDeviceListProvider;
import com.amazon.music.casting.providers.ChromecastDeviceListFeed;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class Casting {
    private final Configuration configuration;
    private final Context context;
    private final CastingService service;

    private Casting(Context context, CastingService castingService, Configuration configuration) {
        Validate.notNull(castingService, "service can't be null", new Object[0]);
        this.configuration = configuration;
        this.service = castingService;
        this.context = context.getApplicationContext();
    }

    public Context getContext() {
        return this.context;
    }

    public CastingService getService() {
        return this.service;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public List<com.amazon.musicplayqueueservice.client.common.CastingDevice> getAmazonDeviceList() throws CastingException {
        return new AmazonDeviceListProvider(this.service).getDeviceList().getDevices();
    }

    public ChromecastDeviceListFeed getChromecastDeviceListFeed() {
        return new ChromecastDeviceListFeed(this.context);
    }

    public static Builder builder(Context context, Configuration configuration) {
        return new Builder(context, configuration);
    }

    public static final class Builder {
        private Configuration configuration;
        private Context context;
        private CastingService service;

        private Builder(Context context, Configuration configuration) {
            Validate.notNull(configuration, "configuration can't be null", new Object[0]);
            this.configuration = configuration;
            this.context = context;
        }

        private void setService() {
            if (this.service == null) {
                this.service = new HttpCastingService(this.configuration, new ServiceEnvironment_Prod());
            }
        }

        public Casting build() {
            setService();
            return new Casting(this.context, this.service, this.configuration);
        }
    }
}
