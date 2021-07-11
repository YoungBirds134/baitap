package com.example.baitap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVER"))
        {

            Toast.makeText(context, "Co Tin Nhan Toi", Toast.LENGTH_SHORT).show();;
            String format = intent.getStringExtra("format");
            Bundle extras = intent.getExtras();
            String message="";
            if (extras!=null){
                Object[] smsExtra = (Object[]) extras.get("pdus") ;
                for (int i = 0 ; i< smsExtra.length;i++){
                    SmsMessage sms = SmsMessage.createFromPdu((byte[])smsExtra[i], format);
                    String body = sms.getMessageBody().toString();
                    String address =sms.getOriginatingAddress();
                    message+="SMS from"  +" " + address +"\n";
                }
            }
        }else if (intent.getAction().equals("android.intent.action.PHONE_STATE")){
            Toast.makeText(context, "Co Tin Nhan Toi", Toast.LENGTH_SHORT).show();;
            Bundle extras = intent.getExtras();
            String state= extras.getString(TelephonyManager.EXTRA_STATE);
            if (extras!=null) {
                String phonenumber = extras.getString(TelephonyManager.EXTRA_STATE);
                Toast.makeText(context, "Phone : " + phonenumber, Toast.LENGTH_SHORT).show();
                ;
            }
        }
    }
}
