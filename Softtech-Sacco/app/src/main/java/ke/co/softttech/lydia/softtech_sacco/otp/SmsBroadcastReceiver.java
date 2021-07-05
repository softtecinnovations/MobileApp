package ke.co.softttech.lydia.softtech_sacco.otp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

class SmsBroadcastReceiver extends BroadcastReceiver {
    SmsBroadcastReceiverListener smsBroadcastReceiverListner;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == SmsRetriever.SMS_RETRIEVED_ACTION){
            Bundle extra = intent.getExtras();
            Status smsRetrieverStatus = (Status) extra.get(SmsRetriever.EXTRA_STATUS);
            switch (smsRetrieverStatus.getStatusCode()){
                case CommonStatusCodes.SUCCESS:
                    Intent messageIntent = extra.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
                    smsBroadcastReceiverListner.onSuccess(messageIntent);
                    break;
                case CommonStatusCodes.TIMEOUT:
                    smsBroadcastReceiverListner.onFailure();
                    break;
            }
        }
    }

    public interface SmsBroadcastReceiverListener {
        void onSuccess(Intent intent);
        void  onFailure();
    }
}
