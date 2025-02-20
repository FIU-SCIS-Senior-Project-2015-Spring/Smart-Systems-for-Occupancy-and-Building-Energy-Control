package fiu.ssobec.Synchronization.DataSync;

/**
 * Created by Maria on 2/18/2015.
 */

import android.accounts.Account;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import fiu.ssobec.Synchronization.SyncConstants;

/**
 * A bound Service that instantiates the authenticator
 * when started.
 */
public class AuthenticatorService extends Service {


    public static final String ACCOUNT = SyncConstants.ACCOUNT;
    public static final String ACCOUNT_TYPE = SyncConstants.ACCOUNT_TYPE;
    public static final String LOG_TAG = "AuthenticatorService";

    // Instance field that stores the authenticator object
    private Authenticator mAuthenticator;
    @Override
    public void onCreate() {
        // Create a new authenticator object
        Log.i(LOG_TAG, "Service created");
        mAuthenticator = new Authenticator(this);
    }

    @Override
    public void onDestroy() {
        Log.i(LOG_TAG, "Service destroyed");
    }

    public static Account GetAccount() {
        // Note: Normally the account name is set to the user's identity (username or email
        // address). However, since we aren't actually using any user accounts, it makes more sense
        // to use a generic string in this case.
        //
        // This string should *not* be localized. If the user switches locale, we would not be
        // able to locate the old account, and may erroneously register multiple accounts.
        final String accountName = ACCOUNT;
        return new Account(accountName, ACCOUNT_TYPE);
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }

    public static Account GetAccount(String accountType) {

        // This string should *not* be localized. If the user switches locale, we would not be
        // able to locate the old account, and may erroneously register multiple accounts.
        final String accountName = ACCOUNT;
        return new Account(accountName, accountType);
    }
}
