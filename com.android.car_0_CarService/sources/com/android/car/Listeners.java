package com.android.car;

import android.os.IBinder;
import com.android.car.Listeners.IListener;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes3.dex */
public class Listeners<ClientType extends IListener> {
    private final LinkedList<ClientWithRate<ClientType>> mClients = new LinkedList<>();
    private int mRate;

    public interface IListener extends IBinder.DeathRecipient {
        void release();
    }

    public static class ClientWithRate<ClientType extends IListener> {
        private final ClientType mClient;
        private int mRate;

        ClientWithRate(ClientType client, int rate) {
            this.mClient = client;
            this.mRate = rate;
        }

        public boolean equals(Object o) {
            if ((o instanceof ClientWithRate) && this.mClient == ((ClientWithRate) o).mClient) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.mClient.hashCode();
        }

        int getRate() {
            return this.mRate;
        }

        void setRate(int rate) {
            this.mRate = rate;
        }

        ClientType getClient() {
            return this.mClient;
        }
    }

    Listeners(int rate) {
        this.mRate = rate;
    }

    int getRate() {
        return this.mRate;
    }

    void setRate(int rate) {
        this.mRate = rate;
    }

    boolean updateRate() {
        int fastestRate = 1;
        for (ClientWithRate<ClientType> clientWithRate : this.mClients) {
            int clientRate = clientWithRate.getRate();
            if (clientRate < fastestRate) {
                fastestRate = clientRate;
            }
        }
        if (this.mRate != fastestRate) {
            this.mRate = fastestRate;
            return true;
        }
        return false;
    }

    void addClientWithRate(ClientWithRate<ClientType> clientWithRate) {
        this.mClients.add(clientWithRate);
    }

    void removeClientWithRate(ClientWithRate<ClientType> clientWithRate) {
        this.mClients.remove(clientWithRate);
    }

    int getNumberOfClients() {
        return this.mClients.size();
    }

    Iterable<ClientWithRate<ClientType>> getClients() {
        return this.mClients;
    }

    ClientWithRate<ClientType> findClientWithRate(ClientType client) {
        for (ClientWithRate<ClientType> clientWithRate : this.mClients) {
            if (clientWithRate.getClient() == client) {
                return clientWithRate;
            }
        }
        return null;
    }

    void release() {
        for (ClientWithRate<ClientType> clientWithRate : this.mClients) {
            clientWithRate.getClient().release();
        }
        this.mClients.clear();
    }
}
